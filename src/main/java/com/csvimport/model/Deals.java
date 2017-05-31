package com.csvimport.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="deals")
@XmlAccessorType(XmlAccessType.FIELD)
public class Deals {

	@XmlElement(name="deal")
   private List<Deal> deals = null;

public List<Deal> getDeals() {
	return deals;
}

public void setDeals(List<Deal> deals) {
	this.deals = deals;
} 
}
