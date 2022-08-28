

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import eCommerce.Product;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Step 1: Prepare list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/eCommerce";
	private String jdbcUsername = "root";
	private String jdbcPassword = "123456";
	
	//Step 2: Prepare list of SQL prepared statements to perform CRUD to our database
	private static final String INSERT_PRODUCTS_SQL = "INSERT INTO Products" + "(product_name,product_country_of_origin,product_category,product_status) VALUES " + "(?, ?, ?);";
	private static final String SELECT_PRODUCT_BY_ID = "select product_name,product_country_of_origin,product_category,product_status from Products where product_name =?;";
	private static final String SELECT_ALL_PRODUCTS = "select * from Products;";
	private static final String DELETE_PRODUCTS_SQL = "delete from Products where product_name = ?;";
	private static final String UPDATE_PRODUCTS_SQL = "update Products set product_name= ?,product_country_of_origin= ?, product_category =?,product_status =? where product_name = ?;";
	//private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected Connection getConnection() {
    	Connection connection = null;
    	try {
    	Class.forName("com.mysql.jdbc.Driver");
    	connection = DriverManager.getConnection(jdbcURL, jdbcUsername,
    	jdbcPassword);
    	} catch (SQLException e) {
    	e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    	e.printStackTrace();
    	}
    	return connection;
    	}
    
  //method to get parameter, query database for existing product data and redirect to product
  	//edit page
  	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
  	throws SQLException, ServletException, IOException {
  	//get parameter passed in the URL
  	String product_name = request.getParameter("product_name");
  	Product existingProduct = new Product("", "", "", "");
  	// Step 1: Establishing a Connection
  	try (Connection connection = getConnection();
  	// Step 2:Create a statement using connection object
  	PreparedStatement preparedStatement =
  	connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
  	preparedStatement.setString(1, product_name);
  	// Step 3: Execute the query or update query
  	ResultSet rs = preparedStatement.executeQuery();
  	// Step 4: Process the ResultSet object
  	while (rs.next()) {
  	product_name = rs.getString("product_name");
  	String product_country_of_origin = rs.getString("product_country_of_origin");
  	String product_category = rs.getString("product_category");
  	String product_status = rs.getString("product_status");
  	existingProduct = new Product(product_name, product_country_of_origin, product_category, product_status);
  	}
  	} catch (SQLException e) {
  	System.out.println(e.getMessage());
  	}
  	//Step 5: Set existingProduct to request and serve up the productEdit form
  	request.setAttribute("product", existingProduct);
  	request.getRequestDispatcher("/productEdit.jsp").forward(request, response);
  	}
  	
  	//method to delete product
  	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
  	throws SQLException, IOException {
  	//Step 1: Retrieve value from the request
  	String productName = request.getParameter("product_name");
  	//Step 2: Attempt connection with database and execute delete product SQL query
  	try (Connection connection = getConnection(); 
  			PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTS_SQL);) {
  	statement.setString(1, productName);
  	int i = statement.executeUpdate();
  	}
  	//Step 3: redirect back to ProductServlet dashboard (note: remember to change the
  	//url to your project name)
  	response.sendRedirect("http://localhost:8090/eCommerce/ProductServlet/dashboard");
  	}
    
  //method to update the product table base on the form data
  	private void updateProduct(HttpServletRequest request, HttpServletResponse response)
  	throws SQLException, IOException {
  	//Step 1: Retrieve value from the request
  	String oriName = request.getParameter("oriName");
  	String product_name = request.getParameter("product_name");
  	String product_country_of_origin = request.getParameter("product_country_of_origin");
  	String product_category = request.getParameter("product_category");
  	String product_status = request.getParameter("product_status");
  	//Step 2: Attempt connection with database and execute update product SQL query
  	try (Connection connection = getConnection(); PreparedStatement statement =
  	connection.prepareStatement(UPDATE_PRODUCTS_SQL);) {
  	statement.setString(1, product_name);
  	statement.setString(2, product_country_of_origin);
  	statement.setString(3, product_category);
  	statement.setString(4, product_status);
  	statement.setString(5, oriName);
  	int i = statement.executeUpdate();
  	}
  	//Step 3: redirect back to ProductServlet (note: remember to change the url to
  	//your project name)
  	// The sample uses project name as HelloWorldJaveEE. Use your own proj name if its not
  	//the same.
  	response.sendRedirect("http://localhost:8090/eCommerce/ProductServlet/dashboard");
  	}
  	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Step 4: Depending on the request servlet path, determine the function to invoke using the following switch statement.
		String action = request.getServletPath();
//		try {
//		switch (action) {
//		case "/insert":
//		break;
//		case "/delete":
//		break;
//		case "/edit":
//		break;
//		case "/update":
//		break;
//		default:
//		listProducts(request, response);
//		break;
//		}
		try {
			switch (action) {
			case "/ProductServlet/delete":
			deleteProduct(request, response);
			break;
			case "/ProductServlet/edit":
			showEditForm(request, response);
			break;
			case "/ProductServlet/update":
			updateProduct(request, response);
			break;
			case "/ProductServlet/dashboard":
			listProducts(request, response);
			break;
			}
			} catch (SQLException ex) {
			throw new ServletException(ex);
			}

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//Step 5: listProducts function to connect to the database and retrieve all products records
	private void listProducts(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException
		{
		List <Product> products = new ArrayList <>();
		try (Connection connection = getConnection();
		// Step 5.1: Create a statement using connection object
		PreparedStatement preparedStatement =
		connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
		// Step 5.2: Execute the query or update query
		ResultSet rs = preparedStatement.executeQuery();
		// Step 5.3: Process the ResultSet object.
		while (rs.next()) {
		String product_name = rs.getString("product_name");
		String product_country_of_origin = rs.getString("product_country_of_origin");
		String product_category = rs.getString("product_category");
		String product_status = rs.getString("product_status");
		products.add(new Product(product_name, product_country_of_origin, product_category, product_status));
		}
		} catch (SQLException e) {
		System.out.println(e.getMessage());
		}
		// Step 5.4: Set the products list into the listProducts attribute to be pass to the productManagement.jsp
		request.setAttribute("listProducts", products);
		request.getRequestDispatcher("/productManagement.jsp").forward(request, response);
		}

}
