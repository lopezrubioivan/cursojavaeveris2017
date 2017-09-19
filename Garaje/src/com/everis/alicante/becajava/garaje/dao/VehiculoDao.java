package com.everis.alicante.becajava.garaje.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VehiculoDao {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String driverName = "org.gjt.mm.mysql.Driver";
		Class.forName(driverName);

		String serverName = "localhost";
		String mydatabase = "garaje";
		String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

		String username = "root";
		String password = "";
		Connection connection;
		connection = DriverManager.getConnection(url, username, password);
		Statement m_Statement = connection.createStatement();
		String query = "SELECT * FROM vehiculo";

		ResultSet m_ResultSet = m_Statement.executeQuery(query);
		while (m_ResultSet.next()) {
			System.out.println(m_ResultSet.getString(1) + ", " + m_ResultSet.getString(2) + ", " + m_ResultSet.getString(3));
		}
	}
}
