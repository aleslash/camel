package teste.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import teste.model.Teste;

public class EnrichProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		Teste entrada = exchange.getIn().getBody(Teste.class);
		//System.out.println("####=> " + entrada);
		entrada.setCampo5("testeOla");
		exchange.getIn().setBody(entrada);
		/*JAXBContext context = JAXBContext.newInstance(Teste.class);
		Marshaller marshall = context.createMarshaller();
		StringBuilder sb = new StringBuilder();
		StringWriter sw = new StringWriter();
		marshall.marshal(entrada, sw);
		System.out.println(sw.toString());
		sw.close();*/
	}

}
