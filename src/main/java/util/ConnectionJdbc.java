/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Gustavo
 */
public class ConnectionJdbc {
    
    private static ConnectionJdbc instance;
    private Connection connection;
    
    private static final String URL_CONEXAO = "jdbc:mysql://localhost:13306/primefaces?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    public ConnectionJdbc() throws SQLException {
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionJdbc getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionJdbc();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConnectionJdbc();
        }

        return instance;
    }
    
}
