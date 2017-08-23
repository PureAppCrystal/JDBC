package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest {
	private static final String DB_TYPE   = "oracle.jdbc.driver.OracleDriver";
	private static final String CONN_IP   = "192.168.1.22";
	private static final String CONN_PORT = "1521";
	private static final String CONN_ID   = "dev";
	private static final String CONN_PW   = "dev";
	private static final String CONN_ENV  = "xe";
	
	public static void main(String[] args) {
		//객체 생성 
		Connection 			conn = null;
		PreparedStatement  pstmt = null;
		
		try {
			//1. JDBC 드라이버 로딩(JDBC 클래스 로딩)			
			Class.forName( DB_TYPE );
			
			//2. Connection 가져오기
			String url = "jdbc:oracle:thin:@"+CONN_IP+":"+CONN_PORT+":"+CONN_ENV;
			conn = DriverManager.getConnection(url, CONN_ID, CONN_PW);
			System.out.println("Connection Success");
			
			//3. Statement 준비
			String sql = "insert into author values( seq_author.nextval, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩
			pstmt.setString( 1, "공자" );
			pstmt.setString( 2, "징징자라징징자" );
			
			//5. 결과 가져오기(성공유무)
			int result = pstmt.executeUpdate(); //insert 시에는 excute에 sql을 넣지 않는다.
			if ( result == 1 ) {
				System.out.println("Insert Success");
			} else {
				System.out.println("Insert fail. SQL : " + sql );
			}
			

			
		} catch (ClassNotFoundException e) {
			System.out.println( "Driver load fail : " + e );
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
	}
}
