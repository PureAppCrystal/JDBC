package com.bigdata2017.jdbc.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bigdata2017.jdbc.bookshop.vo.AuthorVO;

public class AuthorDAO {
	private static final String DB_TYPE   = "oracle.jdbc.driver.OracleDriver";
	private static final String CONN_IP   = "192.168.1.22";
	private static final String CONN_PORT = "1521";
	private static final String CONN_ID   = "dev";
	private static final String CONN_PW   = "dev";
	private static final String CONN_ENV  = "xe";
	
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			//1. JDBC 드라이버 로딩(JDBC 클래스 로딩)				
			Class.forName( DB_TYPE );
			
			//2. Connection 가져오기
			String url = "jdbc:oracle:thin:@"+CONN_IP+":"+CONN_PORT+":"+CONN_ENV;
			conn = DriverManager.getConnection(url, CONN_ID, CONN_PW);
			System.out.println("Connection Success");
			
		} catch (ClassNotFoundException e) {
			System.out.println( "Driver load fail : " + e );
		} 
		
		return conn;
	}
	
	
	
	public int delete() {
		//객체 생성 
		Connection conn = null;
		Statement  stmt = null;
		
		int result = 0;
		
		try {
			conn = getConnection();
			
			//3. Statement 객체 생성.
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql = "delete from author ";
			stmt.executeQuery(sql);
			
			
		} catch (SQLException e) {
			System.out.println( "Error : " + e );
		} finally {
			try {
				//객체 삭제 
				if ( stmt != null ) {	stmt.close();	}
				if ( conn != null )	{	conn.close();	}
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static int delete( Long no ) {
		
		return 0;
	}
	
	public static int delete( String name ) {
		
		return 0;
	}
	
	
	
	
	public int insert( AuthorVO vo ) {
		//객체 생성 
		Connection 			conn = null;
		PreparedStatement  pstmt = null;
		int result = 0;
		
		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql = "insert into author values( seq_author.nextval, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩
			pstmt.setString( 1, vo.getName() );
			pstmt.setString( 2, vo.getProfile() );
			
			//5. 결과 가져오기(성공유무)
			result = pstmt.executeUpdate(); //insert 시에는 excute에 sql을 넣지 않는다.
			if ( result == 1 ) {
				System.out.println("Insert Success");
			} else {
				System.out.println("Insert fail. SQL : " + sql );
			}
			

			
		} catch (SQLException e) {
			System.out.println( "Error : " + e );
		} finally {
			try {
				//객체 삭제 
				if ( pstmt != null ) {	pstmt.close();	}
				if ( conn  != null ) {	conn.close();	}
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	
	
	
	
	

	public List<AuthorVO> getList() {
		List<AuthorVO> list = new ArrayList<AuthorVO>();
		
		//객체 생성 
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs 	= null;
		
		try {
			conn = getConnection();
			
			//3. Statement 객체 생성.
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql = "select no, name, profile from author order by no ";
			rs = stmt.executeQuery(sql);
			
			//5. 결과 가져오기
			while( rs.next() ) {
				Long 	no		= rs.getLong( 1 );
				String  name 	= rs.getString( 2 );
				String  profile	= rs.getString( 3 );
				
				
				AuthorVO vo = new AuthorVO();
				vo.setNo(no);
				vo.setName(name);;
				vo.setProfile(profile);
				
				list.add( vo );
			}

			
		} catch (SQLException e) {
			System.out.println( "Error : " + e );
		} finally {
			try {
				//객체 삭제 
				if ( rs   != null )	{	rs.close();		}
				if ( stmt != null ) {	stmt.close();	}
				if ( conn != null )	{	conn.close();	}
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
}
