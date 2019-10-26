/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vinicius
 */
public class ConnectionFactory {

    public Connection getConnection() {

        // Inicializado variáveis de conexão
        String host = "senac-tads-pi3-scorpions.cgcgme9nfnpo.us-east-1.rds.amazonaws.com";
        String database = "empresa_tades";
        String user = "sistema_tades";
        String password = "SistemaScorpions123"; // Nunca subir no repositório com a senha salva
        String jdbcURL = "jdbc:mysql://senac-tads-pi3-scorpions.cgcgme9nfnpo.us-east-1.rds.amazonaws.com:3306/"
                + database
                + "?user=" + user
                + "&password=" + password;

        // Checar se o Driver está instalado
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver NOT detected in library path.");
            return null;
        }

        System.out.println("MySQL JDBC driver detected in library path.");

        // Inicializa objeto de conexão
        try {
            /*String url = String.format("jdbc:mariadb://%s/%s", host, database);

            // Seta propriedades da conexão
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", "true");
            properties.setProperty("verifyServerCertificate", "true");
            properties.setProperty("requireSSL", "false");*/

            // Retorna conexão
            return DriverManager.getConnection(jdbcURL);
        } catch (SQLException e) {
            System.out.println("Falha ao criar conexão com o banco de dados.");
            throw new RuntimeException(e);
        }

    }
}
