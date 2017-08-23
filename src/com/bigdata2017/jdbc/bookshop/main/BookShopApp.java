package com.bigdata2017.jdbc.bookshop.main;

import java.util.List;
import java.util.Scanner;

import com.bigdata2017.jdbc.bookshop.dao.BookDAO;
import com.bigdata2017.jdbc.bookshop.vo.BookVO;

public class BookShopApp {

	public static void main(String[] args) {
		Scanner scanner = null;
		while ( true ) {
			//Book 객체의 정보를 출력
			System.out.println("\n\n*****도서 정보 출력하기 *****");
			displayBookInfo();
			
			scanner = new Scanner(System.in);
			System.out.print("대여 하고 싶은 책의 번호를 입력하세요 : ");
			String input = scanner.nextLine();
			
			
			//quit  이면 종료 
			if ( "quit".equals( input.toLowerCase() )) {
				System.out.println("종료합니다.");
				break;
			}
			
			// 숫자 확인
			if ( input.matches("\\d+") == false ) {
				System.out.println("잘못된 입력입니다.");
				continue;
			}
			
			//입력받은 번호 출력 
			Long no = Long.parseLong( input );
			System.out.println("Input No : " + no );
			
			
			
			BookDAO dao = new BookDAO();
			List<BookVO> list = dao.getList();
			BookVO bvo = null;
			
			/* 선택된 도서 찾기 */
			for ( BookVO vo : list ) {
				if ( vo.getNo().equals(no) ) {
					bvo = vo;
				}
			}
			
			/* 입력된 번호의 책 유무 확인 */
			if (bvo == null) {
				System.out.println("존재하지 않는 책의 번호입니다.");
				continue;
			}
			
			
			/* 선택 도서정보 대여가능 여부 확인하기 */
			if ( dao.getIsRent(bvo.getNo()) == false ) {
				System.out.println(bvo.getTitle()+"번 책은 이미 대여중입니다.");
				continue;
			}
			
			
			/* state 정보 바꾸기 */
			if ( dao.updateState( bvo.getNo(), "대여중" ) != 1 ) {
				System.out.println(bvo.getTitle()+" 대여 실패");
			} else {
				System.out.println(bvo.getTitle()+"이(가) 대여 됬습니다.");
			}
			
			
			
			
		}
		scanner.close();
		
	}
	
	
	private static void displayBookInfo() {
		BookDAO dao = new BookDAO();
		List<BookVO> list = dao.getList();
		
		
		for ( BookVO vo : list ) {
			System.out.printf("책제목 : %-20s\t, 저자 : %-20s, 대여 유무 : %-20s \n",vo.getTitle(), vo.getAuthorName(), vo.getState());
		}
	}

}
