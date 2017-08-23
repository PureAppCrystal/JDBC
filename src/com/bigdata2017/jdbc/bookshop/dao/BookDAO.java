package com.bigdata2017.jdbc.bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bigdata2017.jdbc.bookshop.vo.BookVO;

public class BookDAO {
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
	
	
	public int delete( ) {
		//객체 생성 
		Connection conn = null;
		Statement  stmt = null;
		
		int result = 0;
		
		try {
			conn = getConnection();
			
			//3. Statement 객체 생성.
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql = "delete from book ";
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
	
	
	
	public int updateState( Long no, String state ) {
		//객체 생성 
				Connection 			conn = null;
				PreparedStatement  pstmt = null;
				int result = 0;
				
				try {
					conn = getConnection();
					
					//3. Statement 준비
					String sql = "update book "+
								 "set state = ? "+
								 "where no = ? ";
					pstmt = conn.prepareStatement(sql);
					
					//4. 바인딩
					//pstmt.setLong( 1, vo.getNo() );
					pstmt.setString( 1, state );
					pstmt.setLong(   2, no );
					
					
					//5. 결과 가져오기(성공유무)
					result = pstmt.executeUpdate(); //insert 시에는 excute에 sql을 넣지 않는다.
					if ( result == 1 ) {
						System.out.println("Update Success");
					} else {
						System.out.println("Update fail. SQL : " + sql );
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
	
	
	
	public int insert( BookVO vo ) {
		//객체 생성 
		Connection 			conn = null;
		PreparedStatement  pstmt = null;
		int result = 0;
		
		try {
			conn = getConnection();
			
			//3. Statement 준비
			String sql = "insert into book values ( seq_book.nextval, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩
			//pstmt.setLong( 1, vo.getNo() );
			pstmt.setString( 1, vo.getTitle() );
			pstmt.setLong(   2, vo.getAuthorNO() );
			pstmt.setString( 3, vo.getState() );
			
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
	
	
	public boolean getIsRent(Long no) {
		//객체 생성 
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs 	= null;
		
		boolean result = false;
		
		try {
			conn = getConnection();
			
			//3. Statement 객체 생성.
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql = "select state from book where no = "+no;
			rs = stmt.executeQuery(sql);
			
			//5. 결과 가져오기
			while( rs.next() ) {
				String  state	 = rs.getString( 1 );
				
				if ("대여가능".equals(state)) {
					result = true;
				} 
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
		
		return result;
	}
	
	
	
	public List<BookVO> getList() {
		List<BookVO> list = new ArrayList<BookVO>();
		

		//객체 생성 
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs 	= null;
		
		try {
			conn = getConnection();
			
			//3. Statement 객체 생성.
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql = "select b.no, b.TITLE, b.STATE, a.NO, a.NAME\r\n " + 
						 "from book b, author a\r\n " + 
						 "where b.AUTHOR_NO = a.NO " +
						 "order by b.no asc";
			rs = stmt.executeQuery(sql);
			
			//5. 결과 가져오기
			while( rs.next() ) {
				Long 	no		 = rs.getLong( 1 );
				String  title 	 = rs.getString( 2 );
				String  state	 = rs.getString( 3 );
				Long 	authorNo = rs.getLong( 4 );
				String  authorName = rs.getString( 5 );
				
				
				BookVO vo = new BookVO();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setState(state);
				vo.setAuthorNO(authorNo);
				vo.setAuthorName(authorName);
				
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
