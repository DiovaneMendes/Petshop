package Util;

import dao.impl_BD.ClienteDaoBd;
import dao.impl_BD.ItemVendaDaoBd;
import dao.impl_BD.PetDaoBd;
import dao.impl_BD.TipoServicoDaoBd;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.ItemVenda;
import model.Pet;
import model.TipoServico;

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
    
    public List<Pet> retornaListaPets(){
        List<Pet> listaPets = new ArrayList<>();
        PetDaoBd pet = new PetDaoBd();    
        return listaPets = pet.listar();    
    }
    
    public void listaPets(){
        System.out.println("\nPETS");
        for(Pet p: retornaListaPets()){
            System.out.println(p.getNomePet());
        }
    }
    
    public int retornaIdPet(String pet){
        int fkPet = -1;
        for(Pet p: retornaListaPets()){
            if(pet.equals(p.getNomePet())){
                fkPet = p.getIdPet();                
            }
        }
        return fkPet;
    }

    public String retornaNomePet(int fkPet){
        String nome = "";
        for(Pet p: retornaListaPets()){
            if(fkPet == p.getIdPet()){
                nome = p.getNomePet();
            }            
        }
        return nome;
    }
    
    //==========================================================================
    
    public List<TipoServico> retornaListaServicos(){
        List<TipoServico> listaServicos = new ArrayList<>();
        TipoServicoDaoBd servico = new TipoServicoDaoBd();    
        return listaServicos = servico.listar();    
    }
    
    public void listaServicos(){
        System.out.println("\nSERVICOS");
        for(TipoServico ts: retornaListaServicos()){
            System.out.println(ts.getNomeServico());
        }
    }
    
    public int retornaIdServico(String servico){
        int fkServico = -1;
        for(TipoServico ts: retornaListaServicos()){
            if(servico.equals(ts.getNomeServico())){
                fkServico = ts.getId();                
            }
        }
        return fkServico;
    }
    
    public String retornaNomeServico(int fkServico){
        String nome = "";
        for(TipoServico ts : retornaListaServicos()){
            if(fkServico == ts.getId()){
                nome = ts.getNomeServico();
            }            
        }
        return nome;
    }
    
    public double retornaValorServico(int fkServico){
        double valor = 0;
        for(TipoServico ts : retornaListaServicos()){
            if(fkServico == ts.getId()){
                valor = ts.getPrecoServico();
            }            
        }
        return valor;
    }
    
    //==========================================================================
    
    public List<ItemVenda> retornaListaItens(){
        List<ItemVenda> listaItens = new ArrayList<>();
        ItemVendaDaoBd item = new ItemVendaDaoBd();    
        return listaItens = item.listar();    
    }
    
    public void listaItens(){
        System.out.println("\nITENS DE VENDA");
        for(ItemVenda iv: retornaListaItens()){
            System.out.println("ID: "+ iv.getId() +" PET: "+ retornaNomePet(iv.getFkPet()) +" SERVICO: "
                    + retornaNomeServico(iv.getFkServico()) +" VALOR: "+ retornaValorServico(iv.getFkServico()));
        }
        System.out.println();
    }
    
    public void retornItensExclusao(int excluir){
        for(ItemVenda iv: retornaListaItens()){
            if(iv.getFkVenda() == excluir){
                
            }
        }
    }
    
    public double retornaValorItem(int fk){
        double valor = 0;
        for(ItemVenda iv: retornaListaItens()){
            if(iv.getFkVenda() == fk){
                for(TipoServico ts: retornaListaServicos()){
                    if(iv.getFkServico() == ts.getId()){
                        valor += ts.getPrecoServico();
                    }
                }
            }
        }
        return valor;
    }
//    
//    public int retornaIdServico(String servico){
//        int fkServico = -1;
//        for(TipoServico ts: retornaListaServicos()){
//            if(servico.equals(ts.getNomeServico())){
//                fkServico = ts.getId();                
//            }
//        }
//        return fkServico;
//    }
}
