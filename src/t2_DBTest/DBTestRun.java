package t2_DBTest;

import java.util.Scanner;

public class DBTestRun {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// DB연동후 작업처리...
		DBTest dbTest = new DBTest();
		
		System.out.println("DB 처리 작업중입니다.....");

		// DB검색작업(이름 검색)
		String name;
		System.out.print("찾고자 하는 성명을 입력하세요? ");
		name = scanner.next();
		dbTest.searchTest(name);
		
		System.out.println("DB 처리 작업끝..입니다.....");
		
		// DB Close처리
		dbTest.rsClose();
		dbTest.dbClose();
		
		scanner.close();
	}
}
