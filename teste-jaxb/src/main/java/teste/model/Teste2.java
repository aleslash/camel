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
public class Teste2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@DataField(pos=1)
	private Integer campo1;
	@DataField(pos=2)
	private Integer campo2;
	@DataField(pos=3)
	private Integer campo3;
	@DataField(pos=4)
	private String campo4;

}
