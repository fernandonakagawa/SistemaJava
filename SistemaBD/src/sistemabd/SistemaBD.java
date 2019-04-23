/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabd;

import javax.swing.JFrame;


public class SistemaBD {
  
    public static void main(String[] args) {
        FormCadastroCliente formCadastroCliente = 
                new FormCadastroCliente();
        formCadastroCliente.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);
        formCadastroCliente.setVisible(true);
        
    }
    
}
