package t3_DBTest_CRUD;

import java.util.Scanner;

public class DBTestRun {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// DB연결
		DBTest dbTest = new DBTest();
		
		// 메뉴 작업
		int sel = 6, res, no;
		String name = "";
			
		while(true) {
			System.out.println("___ 작업선택 ___");
			System.out.print("1.자료입력  2.개별조회  3.전체조회  4.수정  5.삭제  6.종료 ==>> ");
			sel = scanner.nextInt();
			if(sel < 1 || sel > 5) break;
			
			int age;
			String gender;
			String joinday;
			
			switch(sel) {
				case 1:
					// 입력처리
					System.out.print("성명을 입력하세요? "); name = scanner.next();
					System.out.print("나이를 입력하세요? "); age = scanner.nextInt();
					System.out.print("성별을 입력하세요?(1:남자, 2:여자) "); gender = scanner.next();
					System.out.print("입사일을 입력하세요?(yyyy-mm-dd) "); joinday = scanner.next();
					if(gender.equals("1")) gender = "m";
					else gender = "f";
					dbTest.input(name, age, gender, joinday);
					break;
				case 2:
					// 개별자료 검색
					while(true) {
						System.out.print("검색할 성명을 입력하세요? ");
						name = scanner.next();
						dbTest.searchTest(name, "s");	// 개별조회 메소드 호출
						System.out.print("계속할까요?(y/n) ");
						String ans = scanner.next();
						if(!ans.toUpperCase().equals("Y")) break;
					}
					break;
				case 3:
					// 전체조회
					dbTest.list();	// 전체조회 메소드 호출
					break;
				case 4:
					// 수정처리
					System.out.print("수정할 성명을 입력하세요? ");
					name = scanner.next();
					res = dbTest.searchTest(name, "u");	// 개별조회 메소드 호출
					if(res == 1) {	// 찾는 자료가 있으므로 수정처리한다.
						while(true) {
							System.out.println("==>> 수정할 항목을 선택하세요? ");
							System.out.print("1.나이  2.성별  3.가입일 : 4.종료 ==>> ");
							no = scanner.nextInt();
							if(no == 1) {
								System.out.print("나이 : "); age = scanner.nextInt();
								dbTest.update(no, age, name);
							}
							else if(no == 2) {
								System.out.print("성별(남자:1, 여자:2) : "); gender = scanner.next();
								dbTest.update(no, gender, name);
							}
							else if(no == 3) {
								System.out.print("가입일(yyyy-mm-dd) : "); joinday = scanner.next();
								dbTest.update(no, joinday, name);
							}
							else {
								break;
							}
						}
					}
					break;
				case 5:
					// 삭제처리
					System.out.print("삭제할 성명을 입력하세요? ");
					name = scanner.next();
					res = dbTest.searchTest(name, "d");	// 개별조회 메소드 호출
					if(res == 1) {	// 찾는 자료가 있으므로 수정처리한다.
						dbTest.delete(name);
					}
					break;
				default:
					break;
			}
		}
		System.out.println("=====================================================");
		System.out.println(".......작업끝..입니다.....");
		
		// DB Close처리
		// dbTest.rsClose();
		dbTest.dbClose();
		scanner.close();
	}
}
