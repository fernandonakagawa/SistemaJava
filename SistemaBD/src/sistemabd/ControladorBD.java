/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Senai
 */
public class ControladorBD {
    private static Connection con;
    private static String driver;
    private static void init(){
        driver = "jdbc:mysql:localhost:3306/sistemajava";
        try{
            //Class.forName("com.jdbc.mysql.Driver");
            con = DriverManager.getConnection(driver,"root","senai");
        }catch(SQLException e){
            System.out.println(con);
        }
    }
    public static void inserirCliente(ClienteCadastro c){
        if(con == null) init();
        String sql = "INSERT INTO clientes (nome, cpf,"
                + "endereco, telefone) VALUES (?,?,?,?)";
        PreparedStatement st;
        try{
            st = con.prepareStatement(sql);
            st.setString(1, c.getNome());
            st.setString(2, c.getCpf());
            st.setString(3, c.getEndereco());
            st.setString(4, c.getTelefone());
            st.execute();
            st.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
