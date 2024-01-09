package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 一覧画面
 */
@WebServlet("/editBook")
public class editBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost/sample";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	private static final String SQL_SELECT = "SELECT * FROM BOOK WHERE JAN_CD = ? ";
	private static final String SQL_UPDATE = "UPDATE BOOK SET ISBN_CD= ?, BOOK_NM= ?, BOOK_KANA= ? , PRICE= ?, ISSUE_DATE= ?, CREATE_DATETIME= ?, UPDATE_DATETIME= ? WHERE JAN_CD= ? ";
	

	public editBook() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		jdbc_Connection();
		String jan_code = request.getParameter("JAN_CD");
		
		try ( Connection connection = DriverManager.getConnection (JDBC_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(SQL_SELECT)){
			
			statement.setString(1, jan_code);
			ResultSet results = statement.executeQuery();
			
				while (results.next()) {
				String jan_cd = results.getString("JAN_CD");
				request.setAttribute("JAN_CD", jan_cd);
				
				String isbn_cd = results.getString("ISBN_CD");
				request.setAttribute("ISBN_CD", isbn_cd);
				
				String book_nm = results.getString("BOOK_NM");
				request.setAttribute("BOOK_NM", book_nm);
				
				String book_kana = results.getString("BOOK_KANA");
				request.setAttribute("BOOK_KANA", book_kana);
				
				int price = results.getInt("PRICE");
				request.setAttribute("PRICE", Integer.toString(price));
				
				String issue_date = results.getString("ISSUE_DATE");
				request.setAttribute("ISSUE_DATE", issue_date);
				
				String create_datetime = results.getString("CREATE_DATETIME");
				request.setAttribute("CREATE_DATETIME", create_datetime);
				
				String update_datetime = results.getString("UPDATE_DATETIME");
				request.setAttribute("UPDATE_DATETIME", update_datetime);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("message", "SQLException:" + e.getMessage());
			}
		
		// 編集画面
		String view = "/WEB-INF/views/editBook.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		jdbc_Connection();
		
		String jan_cd = request.getParameter("JAN_CD");
		String isbn_cd = request.getParameter("ISBN_CD");
		String book_nm = request.getParameter("BOOK_NM");
		String book_kana = request.getParameter("BOOK_KANA");
		int price = Integer.parseInt(request.getParameter("PRICE").replaceAll("[^\\d]", ""));
		
		String issue_date = request.getParameter("ISSUE_DATE");
		String create_datetime = request.getParameter("CREATE_DATETIME");
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		LocalDateTime nowDate = LocalDateTime.now();
		String update_time = dateTimeFormatter.format(nowDate);
		
		try ( Connection connection = DriverManager.getConnection (JDBC_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)){
			
			statement.setString(1, isbn_cd);
			statement.setString(2, book_nm);
			statement.setString(3, book_kana);
			statement.setInt(4, price);
			statement.setString(5, issue_date);
			statement.setString(6, create_datetime);
			statement.setString(7, update_time);
			statement.setString(8, jan_cd);
			statement.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("message", "SQLException:" + e.getMessage());
				return;
			}
		
		// 編集画面
		response.sendRedirect("indexBook");
	}
	
	// JDBCドライバー接続メソッド
	private void jdbc_Connection() {
		try {
			// JDBCドライバ接続
			Class.forName(JDBC_DRIVER);
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
