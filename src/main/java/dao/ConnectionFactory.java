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

    public Connection getConnection(){
        
         // Inicializado variáveis de conexão
        String host = "senac-tads-pi3-scorpions-mysql.mysql.database.azure.com";
        String database = "empresa_tades";
        String user = "sistema_tades@senac-tads-pi3-scorpions-mysql";
        String password = "?"; // Nunca subir no repositório com a senha salva
        
        // Checar se o Driver está instalado
        try
        {
            Class.forName("org.mariadb.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("MariaDB JDBC driver NOT detected in library path.");
            return null;
        }
        
        System.out.println("MariaDB JDBC driver detected in library path.");

        // Inicializa objeto de conexão
        try
        {
            String url = String.format("jdbc:mariadb://%s/%s", host, database);

            // Seta propriedades da conexão
            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);
            properties.setProperty("useSSL", "true");
            properties.setProperty("verifyServerCertificate", "true");
            properties.setProperty("requireSSL", "false");

            // Retorna conexão
            return DriverManager.getConnection(url, properties);
        }
        catch (SQLException e)
        {
            System.out.println("Falha ao criar conexão com o banco de dados.");
            throw new RuntimeException(e);
        }
        
        
        
        /*
        String urlLocal = "jdbc:mysql://localhost:3306/produtobd"; // Old
        String urlAzure = "jdbc:mysql://senac-tads-pi3-scorpions-mysql.mysql.database.azure.com:3306/empresa_tades?useSSL=true&requireSSL=false&useTimezone=true&serverTimezone=UTC";
        String usuarioAzure = "sistema_tades@senac-tads-pi3-scorpions-mysql";
        String senhaAzure = "SistemaScorpions123"; // Nunca fazer commit com a senha do Azure no repositório. Testar e apagar.

        try {
            Class.forName("org.mariadb.jdbc");
            return DriverManager.getConnection(urlAzure, usuarioAzure, senhaAzure);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }*/
    }
}
