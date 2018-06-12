package Util;

import dao.impl_BD.ClienteDaoBd;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Diovane
 */
public class MostraCoisas {
    
    public MostraCoisas(){
        
    }
    
    //=========================METODOS DE APOIO=================================
    public List<Cliente> retornaListaClientes(){
        List<Cliente> listaClientes = new ArrayList<>();
        ClienteDaoBd dono = new ClienteDaoBd();    
        return listaClientes = dono.listar();    
    }
    
    public void listaDonos(){
        System.out.println("\nCLIENTES");
        for(Cliente c: retornaListaClientes()){
            System.out.println(c.getNome());
        }
    }
    
    public int retornaIdDono(String dono){
        int fkDono = -1;
        for(Cliente c: retornaListaClientes()){
            if(dono.equals(c.getNome())){
                fkDono = c.getId();
            }            
        }
        return fkDono;
    }
    
    public String retornaNomeDono(int fkDono){
        String nome = "";
        for(Cliente c: retornaListaClientes()){
            if(fkDono == c.getId()){
                nome = c.getNome();
            }            
        }
        return nome;
    }
    //==========================================================================
}
