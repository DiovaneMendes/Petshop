
package Relatorio;

public class PetGeraMaisLucro implements Comparable<PetGeraMaisLucro>{
    private int quantidade;
    private String tipoPet;
    
    public PetGeraMaisLucro(int quantidade, String tipoPet){
        this.quantidade = quantidade;
        this.tipoPet = tipoPet;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getTipoPet() {
        return tipoPet;
    }

    @Override
    public int compareTo(PetGeraMaisLucro pt) {
        if(this.getQuantidade() < pt.getQuantidade()){
            return 1;
        }
        if(this.getQuantidade() > pt.getQuantidade()){
            return -1;
        }
        return 0;
    }
}