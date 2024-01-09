package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 一覧画面
 */
@WebServlet("/indexBook")
public class IndexBook extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost/sample";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	private static final String SQL = "SELECT * FROM BOOK";

	public IndexBook() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		jdbc_Connection();
		
		try ( Connection connection = DriverManager.getConnection (JDBC_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement(SQL)){
			
			ResultSet results = statement.executeQuery();
				
			// 取得したデータをリスト化
			ArrayList<HashMap<String, String>> rows = new
			ArrayList<HashMap<String, String>> ();
					
			while (results.next()) {
				HashMap<String, String> conlums = new
				HashMap<String, String> ();
				
				String jan_cd = results.getString("JAN_CD");
				conlums.put("JAN_CD", jan_cd);
				
				String isbn_cd = results.getString("ISBN_CD");
				conlums.put("ISBN_CD", isbn_cd);
				
				String book_nm = results.getString("BOOK_NM");
				conlums.put("BOOK_NM", book_nm);
				
				String book_kana = results.getString("BOOK_KANA");
				conlums.put("BOOK_KANA", book_kana);
				
				int price = results.getInt("PRICE");
				conlums.put("PRICE", Integer.toString(price));
				
				String issue_date = results.getString("ISSUE_DATE");
				conlums.put("ISSUE_DATE", issue_date);
				
				String create_datetime = results.getString("CREATE_DATETIME");
				conlums.put("CREATE_DATETIME", create_datetime);
				
				String update_datetime = results.getString("UPDATE_DATETIME");
				conlums.put("UPDATE_DATETIME", update_datetime);
				
				rows.add(conlums);
				}
				request.setAttribute("rows", rows);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("message", "SQLException:" + e.getMessage());
			}
		
		// 一覧画面
		String view = "/WEB-INF/views/indexBook.jsp";
		request.getRequestDispatcher(view).forward(request, response);
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
