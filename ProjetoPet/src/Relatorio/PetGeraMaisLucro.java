package Relatorio;

import model.Pet;

public class PetGeraMaisLucro implements Comparable<PetGeraMaisLucro>{
    private int quantidade;
    private Pet pet;
    
    public PetGeraMaisLucro(int quantidade, Pet pet){
        this.quantidade = quantidade;
        this.pet = pet;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Pet getPet() {
        return pet;
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