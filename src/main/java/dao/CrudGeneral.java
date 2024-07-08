package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import utilidades.Conector;

public class CrudGeneral {
	PreparedStatement preparedStatement;
	Statement statement;
	Connection conex;
	
	public CrudGeneral () {
		try {
			conex=Conector.conectar();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
