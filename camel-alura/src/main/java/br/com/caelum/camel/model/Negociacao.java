package br.com.caelum.camel.model;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
public class Negociacao {
	
	@XmlElement
	private double preco;
	@XmlElement
    private int quantidade;
	@XmlElement
    private Calendar data;

    //getters e setters

    @Override
    public String toString() {
        return "Negociacao [preco=" + preco + ", quantidade=" + quantidade + "]";
    }

}
