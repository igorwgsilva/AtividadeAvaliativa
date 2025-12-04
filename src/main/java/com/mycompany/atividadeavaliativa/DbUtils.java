/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atividadeavaliativa;

/**
 *
 * @author SSMR-TG-001
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    
    // URL do banco de dados SQLite
    private static final String URL = "jdbc:sqlite:atividade.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            // Tenta se conectar. Se o arquivo DB não existe, ele é criado.
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexão com SQLite estabelecida com sucesso.");
            return conn;
            
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados SQLite: " + e.getMessage());
            return null; 
        }
    }
    
public static void createNewTable() {
    
    String sql = """
                 CREATE TABLE IF NOT EXISTS usuario (
                    id INTEGER PRIMARY KEY,
                    tipo_perfil TEXT NOT NULL,
                    status_usuario TEXT NOT NULL,
                    nome_de_usuario TEXT UNIQUE NOT NULL,
                    nome TEXT NOT NULL,
                    senha TEXT NOT NULL,
                    data_cadastro TEXT NOT NULL,
                    notificacoes_recebidas INTEGER DEFAULT 0,
                    notificacoes_lidas INTEGER DEFAULT 0
                 );
                 """;

    try (Connection conn = connect();
         java.sql.Statement stmt = conn.createStatement()) {
        
        stmt.execute(sql);
        System.out.println("Tabela 'usuario' criada ou já existente.");
        
    } catch (SQLException e) {
        System.err.println("Erro na criação da tabela: " + e.getMessage());
    }
}
}
