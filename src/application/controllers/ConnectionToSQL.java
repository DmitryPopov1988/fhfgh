package application.controllers;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConnectionToSQL {
	
	 private static FileInputStream fis;
     private static Properties property = new Properties();
	
	public static Connection getConnection() {
		try {
			fis = new FileInputStream("src/application/view/dbconnection.properties");
			property.load(fis);
			String driver = property.getProperty("db.driver");
			String url = property.getProperty("db.url");
			String username = property.getProperty("db.username");
			String password = property.getProperty("db.password");
			Class.forName(driver);		
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");
			return conn;
		} catch (Exception exc) {
			System.out.println(exc);
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText(null);
			alert.setContentText("Problem with DB connection.");
			alert.showAndWait();
		}
		return null;
	}
}