package teste;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.dataformat.xmljson.XmlJsonDataFormat;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;

import teste.model.Teste;
import teste.model.Teste2;
import teste.processors.EnrichProcessor;

public class TesteRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		XmlJsonDataFormat xmlJson = new XmlJsonDataFormat();
		xmlJson.setRootName("teste");
		BindyCsvDataFormat bindyCsv = new BindyCsvDataFormat(Teste.class);
		JaxbDataFormat jaxb = new JaxbDataFormat();
		// TODO Auto-generated method stub
		from("file:entrada?include=.*.txt&noop=true")
			.log("${body}")
			.marshal().jaxb()
			.log("xml: ${body}")
			.split().tokenizeXML("teste")
			.log("xml Partido: ${body}")
			.marshal(xmlJson)
			.log("json Partido: ${body}")
			.unmarshal(xmlJson)
			.log("xml Partido: ${body}")
			.aggregate(constant(true),new TesteAggreggationStrategy())
			.completionInterval(2000)
			.marshal().jaxb()
			.log("depois: ${body}")
			//.wireTap("direct:desvio")
			//.setProperty("campo4").jsonpath("$.campo4")
			//.log("prop: ${property.campo4}")
			.to("file:saida");
		
		from("direct:desvio")
		.log("Dentro do desvio: ${body}")
		.to("file:saida2");
		
		from("direct:enrichTeste")
		.process(new EnrichProcessor());
		
//		from("file:entrada?include=.*.csv&noop=true")
//		.unmarshal(bindyCsv)
//		.log("${body}")
//		.marshal(jaxb)
//		.log("xml: ${body}")
//		.enrich("direct:enrichTeste")
//		.marshal(xmlJson)
//		.log("json: ${body}")
//		.setProperty("campo4").jsonpath("$.campo4")
//		.log("prop: ${property.campo4}")
//		//.wireTap("direct:desvio")
//		//.to("file:saida");
//		.end();
	}

}
