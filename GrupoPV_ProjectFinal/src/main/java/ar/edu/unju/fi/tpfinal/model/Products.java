package ar.edu.unju.fi.tpfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name="PRODUCTS")
@Component
public class Products {
	@Id
	@Column(name = "PRODUCT_CODE")
	private  String  productCode;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "PRODUCT_LINE")
	private  String  productLine;
	
	@Column(name = "PRODUCT_SCALE")
	private  String  productScale;
	
	@Column(name = "PRODUCT_VENDOR")
	private  String  productVendor;
	
	@Column(name = "PRODUCT_DESCRIPTION")
	private String productDescripton;
	
	@Column(name = "QUANTITY_IN_STOCK")
	private Integer quantityInStock;
	
	@Column(name = "BUY_PRICE")
	private Double buyPrice;
	
	@Column(name = "MSRP")
	private Double MSRP;
	
	//RELACION CON LA CLASE PRODUCTLINES
	/*
	@Autowired
	@OneToMany(mappedBy = "order_details")
	private OrderDetails orderDetails;
	*/
	@Autowired
	@ManyToOne
	@JoinColumn(name = "product_lines")
	private Productlines productLines;
	
	
	
	//------ CONSTRUCTORES -------
	public Products() {
		super();
	}


	
	




	public Products(String productCode, String productName, String productLine, String productScale,
			String productVendor, String productDescripton, Integer quantityInStock, Double buyPrice, Double mSRP,
			OrderDetails orderDetails, Productlines productLines) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productLine = productLine;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.productDescripton = productDescripton;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		MSRP = mSRP;
		//this.orderDetails = orderDetails;
		this.productLines = productLines;
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



	public String getProductLine() {
		return productLine;
	}



	public void setProductLine(String productLine) {
		this.productLine = productLine;
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



	public Double getMSRP() {
		return MSRP;
	}



	public void setMSRP(Double mSRP) {
		MSRP = mSRP;
	}



	public Productlines getProductLines() {
		return productLines;
	}



	public void setProductLines(Productlines productLines) {
		this.productLines = productLines;
	}



/*
	public OrderDetails getOrderDetails() {
		return orderDetails;
	}

*/




/*

	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}


*/





	@Override
	public String toString() {
		return "Products [productCode=" + productCode + ", productName=" + productName + ", productLine=" + productLine
				+ ", productScale=" + productScale + ", productVendor=" + productVendor + ", productDescripton="
				+ productDescripton + ", quantityInStock=" + quantityInStock + ", buyPrice=" + buyPrice + ", MSRP="
				+ MSRP + ", orderDetails=" +  ", productLines=" + productLines + "]";
	}



}
