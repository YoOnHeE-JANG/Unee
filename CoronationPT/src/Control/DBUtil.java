package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static final String driver = "oracle.jdbc.driver.OracleDriver";
	static final String url = "jdbc:oracle:thin:@localhost:1521:myoracle";

	public static Connection getConnection() throws Exception {
	 Class.forName(driver);
		Connection con = DriverManager.getConnection(url, "scott", "tiger");
		return con;
	}

	public static void main(String arg[]) throws SQLException {

	}

}