/*
Cadastro de clientes: para cada cliente, anota-se o RG, nome e telefone.
*/
package model;

//Criando o objeto cliente
public class Cliente{
    private String nome;
    private String rg, telefone;
    private int quantidadeCompra;

    public Cliente(String nome, String rg, String telefone) {
        this.nome = nome;
        this.rg = rg;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getRg() {
        return rg;
    }

    public String getTelefone() {
        return telefone;
    }
    
    public void setQuantidadeCompra(int quantidadeCompra){
        this.quantidadeCompra = quantidadeCompra;
    }
    
    public int getQuantidadeCompra(){
        return quantidadeCompra;
    }
}