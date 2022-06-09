/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabd;

//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Senai
 */
public class ControladorBDNew {
    private static MysqlDataSource dataSource;
    private static Connection con;
    private static Statement st;
    private static ResultSet rs;
    private static boolean executarSQLConsulta(String sql){
        dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("senai");
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);
        dataSource.setDatabaseName("sistemajava");
        
        try{
            dataSource.setServerTimezone("UTC");
            con = dataSource.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if(rs.next()){
                System.out.println(rs.getString(1));
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }finally{
            try{
                if(rs != null){rs.close();}
                if(st != null){st.close();}
                if(con != null){ con.close();}
            }catch(SQLException e){
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    private static boolean executarSQL(String sql){
        dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("senai");
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);
        dataSource.setDatabaseName("sistemajava");
        boolean flag = true;
        try{
            dataSource.setServerTimezone("UTC");
            con = dataSource.getConnection();
            st = con.createStatement();
            if(!st.execute(sql)){
                throw new SQLException ();
            }
        }catch(SQLException e){
            System.out.println(e.getCause().toString() + e.getErrorCode() + e.getSQLState() + e.getMessage());
            e.printStackTrace();
            flag = false;
        }finally{
            try{
                if(rs != null){rs.close();}
                if(st != null){st.close();}
                if(con != null){ con.close();}
            }catch(SQLException e){
                System.out.println(e.getCause().toString() + e.getErrorCode() + e.getSQLState() + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
        return flag;
    }
    public static void inserirCliente
        (ClienteCadastro c){
        String sql = "INSERT INTO clientes (nome, cpf,"
                + "endereco, telefone) VALUES (\""
                + c.getNome()+ "\",\""
                + c.getCpf()+ "\",\""
                + c.getEndereco()+ "\",\""
                + c.getTelefone()+ "\")";
        System.out.println(sql);
        if(executarSQL(sql)) 
            System.out.println("Cliente adicionado!");
        else System.out.println("Erro!");
        
    }
}
