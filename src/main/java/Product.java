
public class Product {
	
	protected String product_name;
	protected String product_country_of_origin;
	protected String product_category;
	protected String product_status;
	
	public Product(String product_name, String product_country_of_origin, String product_category, String sellerName) {
		super();
		this.product_name = product_name;
		this.product_country_of_origin = product_country_of_origin;
		this.product_category = product_category;
		this.product_status = product_status;
	}
	public String getProductName() {
		return product_name;
	}
	public void setProductName(String product_name) {
		this.product_name = product_name;
	}
	public String getproduct_country_of_origin() {
		return product_country_of_origin;
	}
	public void setDescription(String product_country_of_origin) {
		this.product_country_of_origin = product_country_of_origin;
	}
	public String getproduct_category() {
		return product_category;
	}
	public void setLocation(String product_category) {
		this.product_category = product_category;
	}
	public String getproduct_status() {
		return product_status;
	}
	public void setSellerName(String product_status) {
		this.product_status = product_status;
	}

}
