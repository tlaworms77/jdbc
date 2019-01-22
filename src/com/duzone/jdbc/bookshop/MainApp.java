package com.duzone.jdbc.bookshop;

import java.util.List;
import java.util.Scanner;

import com.duzone.jdbc.bookshop.dao.BookDao;
import com.duzone.jdbc.bookshop.vo.BookVo;

public class MainApp {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("도서서비스");
			System.out.println("1:도서 정보 출력 / 2:도서 대여 / 3:도서 반납 / 4: 서비스 종료");
			System.out.print("inputServiceNumber >> ");
			String key = scanner.nextLine();
			switch (key) {
			case "1":
				// (1) Book 객체의 정보를 출력
				displayBookInfo();
				break;
			case "2":
				System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
				long no = scanner.nextLong();
				scanner.nextLine();
				rent(no);
				break;
			case "3":
				System.out.print("반납하실 책의 번호를 입력하세요:");
				no = scanner.nextLong();
				scanner.nextLine();
				returnBook(no);
				break;
			case "4":
				System.out.println("도서서비스를 종료 하겠습니다.");
				System.out.println("Release Resources...");
				scanner.close();
				System.out.println("정상종료되었습니다.");
				return ;
			default:
				System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
		
	}
	
	private static void rent(long no) {
		boolean result = new BookDao().updateStatus(no);
	}
	
	// 바코드 입력을 통한 식별을 하는 것을 대신하여 간단한 책 고유 번호로 반납 메소드
	private static void returnBook(long no) {
		boolean result = new BookDao().returnBook(no);
	}

	private static void displayBookInfo() {
		System.out.println("*****도서 정보 출력하기******");
		List<BookVo> list = new BookDao().getList();
		
		for(BookVo vo : list) {
			System.out.println(vo);
		}
		//db getlist로 출력~~~
	}
	
}
