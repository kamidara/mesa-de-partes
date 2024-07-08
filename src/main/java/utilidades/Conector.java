package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	static Connection con;

	static String url = "jdbc:mysql://localhost:3306/mesaPartes";
	static String Driver = "com.mysql.cj.jdbc.Driver";
	static String user = "root";
	static String clave = "";
	
	public static Connection conectar() {
		try {
			Class.forName(Driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return con=DriverManager.getConnection(url,user,clave);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	
	
}
