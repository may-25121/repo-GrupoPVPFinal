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
	
	/*@Autowired
	@OneToMany(mappedBy = "product")
	private Products product;
	*/
	
	
	//------ CONSTRUCTORES -------
	
	/**
	 * Constructor por defecto
	 */
	
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

	
	/**
	 * @return textDescripton
	 */
	
	public String getTextDescripton() {
		return textDescripton;
	}

	/**
	 * @param status the setTextDescripton
	 */
	public void setTextDescripton(String textDescripton) {
		this.textDescripton = textDescripton;
	}

	/**
	 * @return productLine
	 */
	
	public String getProductLine() {
		return productLine;
	}

	/**
	 * @param status the setProductLine
	 */

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	/**
	 * @return htmlDescription
	 */

	public String getHtmlDescription() {
		return htmlDescription;
	}

	/**
	 * @param status the setHtmlDescription
	 */

	public void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}

	/**
	 * @return image
	 */

	public String getImage() {
		return image;
	}

	/**
	 * @param status the setImage
	 */

	public void setImage(String image) {
		this.image = image;
	}
	
//ToString
	
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
