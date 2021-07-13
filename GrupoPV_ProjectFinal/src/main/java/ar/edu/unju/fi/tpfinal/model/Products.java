package ar.edu.unju.fi.tpfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Entity
@Table(name="PRODUCTS")
@Component
public class Products {
	@Id
	@Column(name = "product_code")
	@NotEmpty(message="You must enter a product code")
	@Size(min = 1,max = 50, message="Enter a minimum of 1 characters and a maximum of 50")
	private  String  productCode;
	
	@Column(name = "prpoduct_name")
	@NotEmpty(message="You must enter a product name")
	@Size(min = 3,max = 30, message="Enter a minimum of 3 characters and a maximum of 30")
	private String productName;
	
	@Column(name = "product_scale")
	@NotEmpty(message="Debes ingresar productScale")
	@Size(min = 3,max = 30, message="Enter a minimum of 3 characters and a maximum of 30")
	private  String  productScale;
	
	@Column(name = "product_vendor")
	@NotEmpty(message="You must enter a product vendor")
	@Size(min = 3,max = 30, message="Enter a minimum of 3 characters and a maximum of 30")
	private  String  productVendor;
	
	@Column(name = "product_description")
	@NotEmpty(message="You must enter  a product description")
	@Size(min = 5,max = 30, message="Enter a minimum of 5 characters and a maximum of 30")
	private String productDescripton;
	
	@Column(name = "quantity_in_stock")
	@NotNull(message = "You must enter a quantity in stock")
	@Min(value=1,message="Minimum 1 digit")
	private Integer quantityInStock;
	
	@Column(name = "buy_price")
	@NotNull(message = "The buy price field cannot be empty")
	@Min(value=1,message="Minimum 1 digit")
	private Double buyPrice;
	
	@Column(name = "msrp")
	@NotNull(message = "Msrp field cannot be empty")
	@Min(value=1,message="Minimum 1 digit")
	private Double msrp;
	
	//RELACION CON LA CLASE PRODUCTLINES
	
	@Autowired
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="productline")
	private Productlines productlines;
	
	//CONSTRUCTORES
	
	public Products() {
	}

	/**
	 * 
	 * @param productCode
	 * @param productName
	 * @param productLine 
	 * @param productScale
	 * @param productVendor
	 * @param productDescripton
	 * @param quantityInStock
	 * @param buyPrice
	 * @param MSRP
	 * 
	 */
	
	public Products(String productCode, String productName, String productScale, String productVendor,
			String productDescripton, Integer quantityInStock, Double buyPrice, Double msrp,
			Productlines productlines) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.productDescripton = productDescripton;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		this.msrp = msrp;
		this.productlines = productlines;
	}
	
	//----- METODOS ACCESORES ------
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductScale() {
		return productScale;
	}

	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}

	public String getProductVendor() {
		return productVendor;
	}

	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	public String getProductDescripton() {
		return productDescripton;
	}

	public void setProductDescripton(String productDescripton) {
		this.productDescripton = productDescripton;
	}

	public Integer getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Double getMsrp() {
		return msrp;
	}

	public void setMsrp(Double msrp) {
		this.msrp = msrp;
	}

	public Productlines getProductlines() {
		return productlines;
	}

	public void setProductlines(Productlines productlines) {
		this.productlines = productlines;
	}

	//----- METODO TOSTRING------
	
	@Override
	public String toString() {
		return "Products [productCode=" + productCode + ", productName=" + productName + ", productScale="
				+ productScale + ", productVendor=" + productVendor + ", productDescripton=" + productDescripton
				+ ", quantityInStock=" + quantityInStock + ", buyPrice=" + buyPrice + ", msrp=" + msrp
				+ ", productlines=" + productlines + "]";
	}

}
