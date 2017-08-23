package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest {
	private static final String DB_TYPE   = "oracle.jdbc.driver.OracleDriver";
	private static final String CONN_IP   = "192.168.1.22";
	private static final String CONN_PORT = "1521";
	private static final String CONN_ID   = "dev";
	private static final String CONN_PW   = "dev";
	private static final String CONN_ENV  = "xe";
	
	public static void main(String[] args) {
		//객체 생성 
		Connection conn = null;
		Statement  stmt = null;
		
		try {
			//1. JDBC 드라이버 로딩(JDBC 클래스 로딩)			
			Class.forName( DB_TYPE );
			
			//2. Connection 가져오기
			String url = "jdbc:oracle:thin:@"+CONN_IP+":"+CONN_PORT+":"+CONN_ENV;
			conn = DriverManager.getConnection(url, CONN_ID, CONN_PW);
			System.out.println("Connection Success");
			
			//3. Statement 객체 생성.
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String name = "도우넛";
			Long   no   = 3L;
			String sql = "update author set name='"+name+"' where no = "+no;
			int result = stmt.executeUpdate(sql);
			
			//5. 결과 가져오기(성공유무)
			if ( result == 1 ) {
				System.out.println("Update Success");
			} else {
				System.out.println("Update fail. SQL : " + sql );
			}
			

			
		} catch (ClassNotFoundException e) {
			System.out.println( "Driver load fail : " + e );
		} catch (SQLException e) {
			System.out.println( "Error : " + e );
		} finally {
			try {
				//객체 삭제 
				if ( stmt != null ) {	stmt.close();	}
				if ( conn != null ) {	conn.close();	}
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
		}
	}

}
