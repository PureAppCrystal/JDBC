package com.bigdata2017.jdbc.bookshop.dao.test;

import java.util.List;

import com.bigdata2017.jdbc.bookshop.dao.BookDAO;
import com.bigdata2017.jdbc.bookshop.vo.BookVO;

public class BookDaoTest {

	public static void main(String[] args) {
		rentTest();
		
		/*
		delete();
		insertTest();
		updateTest();
		testGetList();
		*/
	}
	
	
	
	public static void rentTest() {
		if (new BookDAO().getIsRent(1L) ) {
			System.out.println("1L 대여가능 ");
		} else {
			System.out.println("1L 대여불가능");
		}
	}
	
	public static void updateTest() {
		BookDAO dao = new BookDAO();
		
		dao.updateState(1L, "대여중");
	}
	
	public static void delete() {
		new BookDAO().delete();
	}
	
	public static void updateCheck() {
		BookDAO dao = new BookDAO();
		
	}
	
	public static void testGetList() {
		BookDAO dao = new BookDAO();
		List<BookVO> list = dao.getList();
		
		for ( BookVO vo : list ) {
			System.out.println( vo );
		}
	}
	
	public static void insertTest() {
		BookDAO dao = new BookDAO();
		
		BookVO vo = new BookVO();
		
		vo.setTitle("트와일라잇");
		vo.setAuthorNO( 1L );
		vo.setState("대여가능");
		dao.insert(vo);
		
		vo.setTitle("뉴문");
		vo.setAuthorNO( 1L );
		vo.setState("대여가능");
		dao.insert(vo);
		
		vo.setTitle("이클립스");
		vo.setAuthorNO( 1L );
		vo.setState("대여가능");
		dao.insert(vo);
		
		vo.setTitle("브레이킹던");
		vo.setAuthorNO( 1L );
		vo.setState("대여가능");
		dao.insert(vo);
		
		vo.setTitle("아리랑");
		vo.setAuthorNO( 2L );
		vo.setState("대여가능");
		dao.insert(vo);
		
		vo.setTitle("젊은그들");
		vo.setAuthorNO( 3L );
		vo.setState("대여가능");
		dao.insert(vo);
		
		vo.setTitle("아프니까 청춘이다");
		vo.setAuthorNO( 4L );
		vo.setState("대여가능");
		dao.insert(vo);
		
		vo.setTitle("귀천");
		vo.setAuthorNO( 5L );
		vo.setState("대여가능");
		dao.insert(vo);
		
		vo.setTitle("태백산맥");
		vo.setAuthorNO( 2L );
		vo.setState("대여가능");
		dao.insert(vo);
		
		vo.setTitle("풀하우스");
		vo.setAuthorNO( 6L );
		vo.setState("대여가능");
		dao.insert(vo);
		
		
		
	}

}
