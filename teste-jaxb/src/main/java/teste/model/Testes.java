package teste.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="testes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Testes {
	@XmlElement(name="teste")
	ArrayList<Teste> testes = new ArrayList<>();

	public ArrayList<Teste> getTestes() {
		return testes;
	}

	public void setTestes(ArrayList<Teste> testes) {
		this.testes = testes;
	}

}
