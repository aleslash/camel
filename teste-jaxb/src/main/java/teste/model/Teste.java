package teste.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator=",")
@XmlRootElement(name="teste")
@XmlAccessorType(XmlAccessType.FIELD)
public class Teste implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@DataField(pos=1)
	@XmlElement
	private Integer campo1;
	@DataField(pos=2)
	@XmlElement
	private Integer campo2;
	@DataField(pos=3)
	@XmlElement
	private Integer campo3;
	@DataField(pos=4)
	@XmlElement
	private String campo4;
	@XmlElement
	private String campo5;
	public String getCampo5() {
		return campo5;
	}
	public void setCampo5(String campo5) {
		this.campo5 = campo5;
	}
	

}
