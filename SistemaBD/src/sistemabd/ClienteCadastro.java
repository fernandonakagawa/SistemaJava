/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemabd;

/**
 *
 * @author Senai
 */
public class ClienteCadastro extends 
        PessoaCadastro{

    public ClienteCadastro(String nome, 
            String cpf, String endereco, 
            String telefone) {
        super(nome, cpf, endereco, telefone);
    }
    
}
