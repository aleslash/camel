package br.com.caelum.camel;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.model.dataformat.JaxbDataFormat;

public class RotaPedidos extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		
		getContext().addComponent("activemq", ActiveMQComponent.activeMQComponent("tcp://localhost:61616"));
		
		errorHandler(deadLetterChannel("activemq:queue:pedidos.DLQ")
				.logExhaustedMessageHistory(true)
				.maximumRedeliveries(3)
				.redeliveryDelay(2000)
				.onRedelivery(new Processor() {
					
					@Override
					public void process(Exchange exchange) throws Exception {
						// TODO Auto-generated method stub
						int count = (int) exchange.getIn().getHeader(Exchange.REDELIVERY_COUNTER);
						int max = (int) exchange.getIn().getHeader(Exchange.REDELIVERY_MAX_COUNTER);
						System.out.println("Redelivery " + count + "/" + max);
						
					}
				})
				);
		
		/*JaxbDataFormat df = new JaxbDataFormat();
		df.setContextPath("br.com.caelum.camel.model");
		
		from("timer://negociacoes?fixedRate=true&delay=1s&period=360s")
	      .to("http4://argentumws.caelum.com.br/negociacoes")
	          //.convertBodyTo(String.class)
	      .split().xpath("/list/negociacao")
	      		.unmarshal(df)
	      		.convertBodyTo(String.class)
	          .log("${body}")
	          .end();
	          //.setHeader(Exchange.FILE_NAME, constant("negociacoes.xml"))
	          //.to("file:saida");
	           */
		
		from("file:pedidos?noop=true")
			.routeId("pedidos:queue")
			.to("activemq:queue:pedidos");
		
		// TODO Auto-generated method stub
		from("activemq:queue:pedidos")
		.routeId("rota-pedidos")
		.to("validator:pedido.xsd");
//		.multicast()
//			.to("direct:http")
//			.to("direct:soap");
		
		from("direct:http")
		.routeId("direct:http")
		.setProperty("pedidoId", xpath("/pedido/id/text()"))
		.setProperty("clienteId", xpath("/pedido/pagamento/email-titular/text()"))
		.split().xpath("/pedido/itens/item")
		//.log("Antes de filtrar: ${body}")
		.filter().xpath("/item/formato[text()='EBOOK']")
		.setProperty("ebookId", xpath("/item/livro/codigo/text()") )
		.marshal().xmljson()
		//.log("${body}")
		//.to("file:saida?fileName=${file:name.noext}-${header.CamelSplitIndex}.json");
		.setHeader(Exchange.HTTP_QUERY,simple("ebookId=${property.ebookId}&pedidoId=${property.pedidoId}&clienteId=${property.clienteId}"))
		.to("http4://localhost:8080/webservices/ebook/item");
		
		from("direct:soap")
		.routeId("direct:soap")
		.to("xslt:pedido-para-soap.xslt")
		.setHeader(Exchange.CONTENT_TYPE, constant("text/xml"))
		.to("http4://localhost:8080/webservices/financeiro");
		
	}	
}
