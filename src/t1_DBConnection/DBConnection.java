package t1_DBConnection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public DBConnection() {
		try {
			// 1.드라이버 검색
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 검색 성공!!!");
			
			// 2.데이터베이스 연결
			DriverManager.getConnection("jdbc:mysql://localhost:3306/javagreen", "root", "1234");
			System.out.println("데이터베이스 연결 성공!!!");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~~");
			System.exit(0);		// 프로그램 종료...
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패ㅠㅠㅠ");
			System.exit(0);
		}
		System.out.println("작업끝...");
	}
}
