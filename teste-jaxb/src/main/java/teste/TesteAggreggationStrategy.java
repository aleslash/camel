package teste;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import teste.model.Teste;
import teste.model.Testes;

public class TesteAggreggationStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		// TODO Auto-generated method stub
		
		if (oldExchange == null) {
			Testes testes = new Testes();
			testes.getTestes().add(newExchange.getIn().getBody(Teste.class));
			newExchange.getIn().setBody(testes);
			return newExchange;
		}
		
		Testes testes = oldExchange.getIn().getBody(Testes.class);
		testes.getTestes().add(newExchange.getIn().getBody(Teste.class));
		oldExchange.getIn().setBody(testes);
		return oldExchange;
	}

}
