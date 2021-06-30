package ar.edu.unju.fi.tpfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Entity
@Table(name="PRODUCTLINES")
@Component
public class Productlines {

	@Id
	@Column(name = "PRODUCT_LINE")
	private String productLine;
	
	@Column(name = "TEXT_DESCRIPTION")
	private String textDescripton;
	
	@Column(name = "HTML_DESCRIPTION")
	private String htmlDescription;
	
	@Column(name = "IMAGE")
	private String image;
	
	
	
	
	//------ CONSTRUCTORES -------
	
	public Productlines() {
		// TODO Auto-generated constructor stub
		super();
	}

	/**
	 * Constructor parametrizado
	 * 
	 * @param productline
	 * @param textDescription
	 * @param htmlDescription
	 * @param image
	 * 
	 */
	
	public Productlines(String productLine, String textDescripton, String htmlDescription, String image) {
		super();
		this.productLine = productLine;
		this.textDescripton = textDescripton;
		this.htmlDescription = htmlDescription;
		this.image = image;
	}
	

	//----- METODOS ACCESORES ------

	
	
	
	public String getTextDescripton() {
		return textDescripton;
	}



	public String getProductLine() {
		return productLine;
	}



	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}



	public void setTextDescripton(String textDescripton) {
		this.textDescripton = textDescripton;
	}



	public String getHtmlDescription() {
		return htmlDescription;
	}



	public void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Productlines [productLine=" + productLine + ", textDescripton=" + textDescripton + ", htmlDescription="
				+ htmlDescription + ", image=" + image + "]";
	}


	
/*
	public Products getProduct() {
		return product;
	}


	public void setProduct(Products product) {
		this.product = product;
	}

*/
	

	
}
