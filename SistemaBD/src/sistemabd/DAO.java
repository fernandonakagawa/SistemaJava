/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/*
Erros comuns:

---
Problema com o timezone
Erro:"java.sql.SQLException: The server time zone value 'Hora oficial do Brasil' 
is unrecognized or represents more than one time zone. You must configure 
either the server or JDBC driver (via the serverTimezone configuration property)
to use a more specifc time zone value if you want to utilize time zone support."

Parar o serviço MySQL e modificar o my.ini
(provavelmente localizado em: C:\ProgramData\MySQL\MySQL Server 8.0)
adicionar a linha abaixo de [mysqld]
[mysqld]
default-time-zone = +3:00
---

Utilização de método errado.
Erro: "can not issue data manipulation statements with executeQuery()."
- executeQuery(): utilizado para realizar select, retorna um ResultSet 
com os dados
- executeUpdate(): utilizado para realizar DML (INSERT, UPDATE, DELETE)
retorna 0 se nenhuma linha foi afetada ou o número de linhas afetadas
- execute(): utilizado para os dois, porém retorna true ou false.
---
 */
public class DAO {

    private static DAO instance = new DAO();
    static final String DB_URL
            = "jdbc:mysql://localhost:3306/sistemajava";
    static final String DB_DRV
            = "com.mysql.jdbc.Driver";
    static final String DB_USER = "root";
    static final String DB_PASSWD = "senai";

    private DAO() {

    }

    public static DAO getInstance() {
        return instance;
    }

    public ResultSet executarSelectSQL(String sql) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.printf("%s %s %s %s \n",
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
            return resultSet;
        }
    }

    public int executarDMLSQL(String sql) {
        Connection connection = null;
        Statement statement = null;
        int rowsAffected = -1;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            try {
                //resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return -1;
            }
            return rowsAffected;
        }
    }

    public boolean inserirCliente(ClienteCadastro c) {
        String sql = "INSERT INTO clientes (nome, cpf,"
                + "endereco, telefone) VALUES (\""
                + c.getNome() + "\",\""
                + c.getCpf() + "\",\""
                + c.getEndereco() + "\",\""
                + c.getTelefone() + "\")";
        System.out.println(sql);
        int rowsAffected = executarDMLSQL(sql);
        if (rowsAffected > 0) {
            System.out.println("Cliente adicionado! Linhas afetadas:" + rowsAffected);
            return true;
        } else {
            System.out.println("Erro! Linhas afetadas: " + rowsAffected);
            return false;
        }

    }

    public ResultSet buscarCliente(String atributo, String termo) {
        String sql = String.format("SELECT nome, cpf, endereco, telefone "
                + "FROM clientes WHERE %s LIKE '%s'", atributo, termo);
        System.out.println(sql);
        return executarSelectSQL(sql);

    }

    
}
