package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
	private static final String CONN_ID   = "dev";
	private static final String CONN_PW   = "dev";
	private static final String CONN_IP   = "192.168.1.22";
	
	public static void main(String[] args) {
		
		Connection conn = null;
		
		try {
			//1. JDBC 드라이버 로딩(JDBC 클래스 로딩)			
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
			
			//2. Connection 가져오기
			String url = "jdbc:oracle:thin:@"+CONN_IP+":1521:xe";
			conn = DriverManager.getConnection(url, CONN_ID, CONN_PW);
			System.out.println("Connection Success");
			
			
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println( "Driver load fail : " + e );
		} catch (SQLException e) {
			System.out.println( "Connection fail : " + e );
		} finally {
			try {
				if( conn != null ) {
					conn.close();
				}
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
		}
	}

}
