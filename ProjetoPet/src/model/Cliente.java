/*
Cadastro de clientes: para cada cliente, anota-se o RG, nome e telefone.
*/
package model;

//Criando o objeto cliente

import java.util.Objects;

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
    
    //Implementando equals para verificações futuras
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.rg, other.rg)) {
            return false;
        }
        return true;
    }
    
    //Implementando hash para ajudar a validar rg
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.rg);
        return hash;
    }
    
}