/*
Cadastro de clientes: para cada cliente, anota-se o RG, nome e telefone.
*/
package model;

public class Cliente{
    private String nome;
    private long rg, telefone;
    private int quantidadeCompra;

    public Cliente(String nome, long rg, long telefone) {
        this.nome = nome;
        this.rg = rg;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public long getRg() {
        return rg;
    }

    public long getTelefone() {
        return telefone;
    }
    
    public void setQuantidadeCompra(int quantidadeCompra){
        this.quantidadeCompra = quantidadeCompra;
    }
    
    public int getQuantidadeCompra(){
        return quantidadeCompra;
    }
}