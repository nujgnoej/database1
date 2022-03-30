package t3_DBTest_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// DAO(Data Access Object)
public class DBTest {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	String sql = "";
	
	public DBTest() {
		try {
			// 1. JDBC 드라이버 검색
			Class.forName("com.mysql.jdbc.Driver");
			
			// 2. 데이터베이스 연결
			String url = "jdbc:mysql://localhost:3306/javagreen";
			String user = "root";
			String password = "1234";
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~~");
			System.exit(0);
		} catch (SQLException e) {
			System.out.println("데이터베이스 연동 실패~~~");
			System.exit(0);
		}
	}

	// 데이터베이스안의 테이블 검색(개별조회)
	public int searchTest(String name, String flag) {
		int res = 0;
		try {
			stmt = conn.createStatement();
			sql = "select * from aaa where name='"+name+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				if(flag.equals("s")) {
					//System.out.println("홍길동을 찾았다.");
					name = rs.getString("name");
					int age = rs.getInt("age");
					String gender = rs.getString("gender");
					String joinday = rs.getString("joinday");
					System.out.println("========================");
					System.out.println("성명 : " + name);
					System.out.println("나이 : " + age);
					System.out.println("성별 : " + gender);
					System.out.println("가입일 : " + joinday);
					System.out.println("========================");
				}
				else {
					res = 1;
				}
			}
			else {
				System.out.println("========================");
				System.out.println(name + "을(를) 찾지 못했습니다.");
				System.out.println("========================");
			}
			
		} catch (SQLException e) {
			System.out.println("SQL오류" + e.getMessage());
		} finally {
			rsClose();
		}
		return res;
	}
	
	// stmt객체 Close
	public void stmtClose() {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {}
		}
	}
	
	// rs객체 close
	public void rsClose() {
		if(rs != null) {
			try {
				rs.close();
				stmtClose();
			} catch (SQLException e) {}
		}
	}
	
  // 데이터베이스객체 Close 
	public void dbClose() {
		try {
			conn.close();
			//System.out.println("DB를 닫았습니다.");
		} catch (Exception e) {}
	}

	// 전체 조회 처리
	public void list() {
		try {
			stmt = conn.createStatement();
			sql = "select * from aaa order by name";
			rs = stmt.executeQuery(sql);
			
			System.out.println("=======================================");
			System.out.println("성 명\t 나이\t 성별\t 가입일자");
			System.out.println("---------------------------------------");
			
			while(rs.next()) {
				String gender = "";
				if(rs.getString("gender").equals("m")) gender = "남자";
				else gender = "여자";
				
				System.out.println(rs.getString("name")+"\t"+rs.getInt("age")+"\t"+gender+"\t"+rs.getString("joinday").substring(0,10));
			}
			
			System.out.println("=======================================");
		} catch (SQLException e) {
			System.out.println("SQL오류" + e.getMessage());
		} finally {
			rsClose();
		}
	}

	// 자료 등록처리
	public void input(String name, int age, String gender, String joinday) {
		try {
			stmt = conn.createStatement();
			sql = "insert into aaa values ('"+name+"',"+age+",'"+gender+"','"+joinday+"')";
			stmt.executeUpdate(sql);
			System.out.println("자료가 등록되었습니다.");
		} catch (SQLException e) {
			System.out.println("SQL오류" + e.getMessage());
		} finally {
			stmtClose();
		}
	}

	// 나이 수정
	public void update(int no, int age, String name) {
		try {
			stmt = conn.createStatement();
			sql = "update aaa set age = "+age+" where name='"+name+"'";
			stmt.executeUpdate(sql);
			System.out.println("수정완료!");
		} catch (SQLException e) {
			System.out.println("SQL오류" + e.getMessage());
		} finally {
			stmtClose();
		}
	}

	// 성별이나 가입일수정
	public void update(int no, String imsi, String name) {
		try {
			stmt = conn.createStatement();
			if(no == 2) {			// 성별수정
				String gender;
				if(imsi.equals("1")) gender = "m";
				else gender = "f";
				sql = "update aaa set gender = '"+gender+"' where name='"+name+"'";
			}
			else {			// 가입일 수정
				sql = "update aaa set joinday = '"+imsi+"' where name='"+name+"'";
			}
			stmt.executeUpdate(sql);
			System.out.println("수정완료!");
		} catch (SQLException e) {
			System.out.println("SQL오류" + e.getMessage());
		} finally {
			stmtClose();
		}
	}

	// 자료 삭제처리
	public void delete(String name) {
		try {
			stmt = conn.createStatement();
			sql = "delete from aaa where name='"+name+"'";
			stmt.executeUpdate(sql);
			System.out.println("삭제완료!");
		} catch (SQLException e) {
			System.out.println("SQL오류" + e.getMessage());
		} finally {
			stmtClose();
		}
	}
}
