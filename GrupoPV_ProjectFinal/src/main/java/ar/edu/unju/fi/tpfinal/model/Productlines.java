package ar.edu.unju.fi.tpfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Entity
@Table(name="PRODUCTLINES")
@Component
public class Productlines {

	@Id
	@Column(name = "PRODUCT_LINE")
	@NotEmpty(message="Debes ingresar productline")
	private String productLine;
	
	@Column(name = "TEXT_DESCRIPTION")
	@NotBlank(message="Debes ingresar textDescription")
	@Size(min = 3,max = 30, message="Minimo 3 y Maximo 30 caracteres")
	private String textDescription;
	
	@Column(name = "HTML_DESCRIPTION")
	@NotEmpty(message="Debes ingresar htmlDescription")
	@Size(min = 5,max = 30, message="Minimo 5 y Maximo 30 caracteres")
	private String htmlDescription;
	
	@Lob
	@Column(name = "IMAGE")
	//@NotEmpty(message="Debes ingresar image")
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
	
	
	public Productlines(@NotEmpty(message = "Debes ingresar productline") String productLine,
			@NotBlank(message = "Debes ingresar textDescription") @Size(min = 3, max = 30, message = "Minimo 3 y Maximo 30 caracteres") String textDescription,
			@NotEmpty(message = "Debes ingresar htmlDescription") @Size(min = 5, max = 30, message = "Minimo 5 y Maximo 30 caracteres") String htmlDescription,
			String image) {
		super();
		this.productLine = productLine;
		this.textDescription = textDescription;
		this.htmlDescription = htmlDescription;
		this.image = image;
	}

	//----- METODOS ACCESORES ------

	
	
	
	public String getTextDescription() {
		return textDescription;
	}





	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}

	public String getProductLine() {
		return productLine;
	}



	public void setProductLine(String productLine) {
		this.productLine = productLine;
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
		return "Productlines [productLine=" + productLine + ", textDescription=" + textDescription + ", htmlDescription="
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
