/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vinicius
 */
public class ConnectionFactory {

    public Connection getConnection() {
        String urlLocal = "jdbc:mysql://localhost:3306/produtobd"; // Old
        String urlAzure = "jdbc:mysql://senac-tads-pi3-scorpions-mysql.mysql.database.azure.com:3306/empresa_tades?useSSL=true&requireSSL=false";
        String usuarioAzure = "sistema_tades@senac-tads-pi3-scorpions-mysql";
        String senhaAzure = "?"; // Nunca fazer commit com a senha do Azure no repositório. Testar e apagar.

        try {
            return DriverManager.getConnection(urlAzure, usuarioAzure, senhaAzure);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
