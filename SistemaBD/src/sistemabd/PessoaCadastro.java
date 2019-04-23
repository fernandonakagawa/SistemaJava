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
public class PessoaCadastro {
    private int id;
    private String nome, cpf, endereco, 
            telefone;

    public PessoaCadastro(String nome, 
            String cpf, String endereco, 
            String telefone) {
        this.id = -1;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }
    public int getId(){ return this.id;}
    public String getNome(){return this.nome;}
    public String getCpf(){return this.cpf;}
    public String getEndereco(){return this.endereco;}
    public String getTelefone(){return this.telefone;}
    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    private void setCpf(String cpf){
        this.cpf = cpf;
    }
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
}
