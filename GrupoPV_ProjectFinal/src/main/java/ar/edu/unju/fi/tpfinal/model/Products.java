package ar.edu.unju.fi.tpfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	/*
	@Column(name = "PRODUCT_LINE")
	private  String  productLine;
	*/
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
	private Double msrp;
	
	//RELACION CON LA CLASE PRODUCTLINES
	
	@Autowired
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="productline")
	private Productlines productlines;
	
	//CONSTRUCTORES
	
	public Products() {
		super();
	}
	 



	/**
	 * 
	 * @param productCode
	 * @param productName
	 * @param productScale
	 * @param productVendor
	 * @param productDescripton
	 * @param quantityInStock
	 * @param buyPrice
	 * @param msrp
	 * @param productlines
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
	
	/**
	 * @return productCode
	 */

	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param status the setProductCode
	 */
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return productName
	 */

	public String getProductName() {
		return productName;
	}

	/**
	 * @param status the setProductName
	 */

	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return productScale
	 */

	public String getProductScale() {
		return productScale;
	}

	/**
	 * @param status the setProductScale
	 */

	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}

	/**
	 * @return productVendor
	 */

	public String getProductVendor() {
		return productVendor;
	}

	/**
	 * @param status the setProductVendor
	 */

	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	/**
	 * @return productDescripton
	 */

	public String getProductDescripton() {
		return productDescripton;
	}

	/**
	 * @param status the setProductDescripton
	 */

	public void setProductDescripton(String productDescripton) {
		this.productDescripton = productDescripton;
	}


	/**
	 * @return quantityInStock
	 */
	

	public Integer getQuantityInStock() {
		return quantityInStock;
	}


	/**
	 * @param status the setQuantityInStock
	 */

	public void setQuantityInStock(Integer quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	/**
	 * @return buyPrice
	 */


	public Double getBuyPrice() {
		return buyPrice;
	}

	/**
	 * @param status the setBuyPrice
	 */

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}


	/**
	 * @return msrp
	 */
	

	public Double getMsrp() {
		return msrp;
	}


	/**
	 * @param status the setMsrp
	 */

	public void setMsrp(Double msrp) {
		this.msrp = msrp;
	}


	/**
	 * @return productlines
	 */

	public Productlines getProductlines() {
		return productlines;
	}

	/**
	 * @param status the setProductlines
	 */


	public void setProductlines(Productlines productlines) {
		this.productlines = productlines;
	}


//ToString

	@Override
	public String toString() {
		return "Products [productCode=" + productCode + ", productName=" + productName + ", productScale="
				+ productScale + ", productVendor=" + productVendor + ", productDescripton=" + productDescripton
				+ ", quantityInStock=" + quantityInStock + ", buyPrice=" + buyPrice + ", msrp=" + msrp
				+ ", productlines=" + productlines + "]";
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

}
