package com.bigdata2017.jdbc.bookshop.dao.test;

import java.util.List;

import com.bigdata2017.jdbc.bookshop.dao.AuthorDAO;
import com.bigdata2017.jdbc.bookshop.vo.AuthorVO;

public class AuthorDaoTest {

	public static void main(String[] args) {
		testDelete();
		testInsert();
		testGetList();
		
	}
	
	public static void testDelete() {
		new AuthorDAO().delete();
	}
	
	
	public static void testGetList() {
		AuthorDAO dao = new AuthorDAO();
		List<AuthorVO> list = dao.getList();
		
		for ( AuthorVO vo : list ) {
			System.out.println( vo );
		}
	}
	
	
	public static void testInsert() {
		AuthorDAO dao = new AuthorDAO();
		
		AuthorVO vo = new AuthorVO();
		vo.setName( "스테파니메이어" );
		//vo.setProfile( "...." );
		dao.insert(vo);
		
		vo.setName( "조정래" );
		//vo.setProfile( "...." );
		dao.insert(vo);
		
		vo.setName( "김동인" );
		//vo.setProfile( "...." );
		dao.insert(vo);
		
		vo.setName( "김난도" );
		//vo.setProfile( "...." );
		dao.insert(vo);
		
		vo.setName( "천상병" );
		//vo.setProfile( "...." );
		dao.insert(vo);
		
		
		vo.setName( "원수연" );
		//vo.setProfile( "...." );
		dao.insert(vo);
		
		
		
		
		
		
	}
	

}
