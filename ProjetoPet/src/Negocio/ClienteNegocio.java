
package Negocio;

import Repositorio.RepositorioClientes;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Diovane
 */

//Tudo que for preciso em referencia ao objeto cliente, é feito aqui
public class ClienteNegocio {
    
    //Validando campos e adicionando a lista
    public void salvar(Cliente c) throws NegocioException{
        this.validarCamposObrigatorios(c);
        this.validarClientesExistente(c);
        RepositorioClientes.getInstance().add(c);
    }
    
    //Listar a lista de clientes
    public List<Cliente> listar() {
        return(RepositorioClientes.getInstance().getClientes());
    }
    
    //Retorn se não há clientes na lista
    public boolean naoHaClientes(){
        return RepositorioClientes.getInstance().estaVazio();
    }
    
    //Validando campos para não ficar em branco
    private void validarCamposObrigatorios(Cliente c) throws NegocioException{
        if(c.getNome() == null || c.getNome().isEmpty()){
            throw new NegocioException("Campo Nome nao informado!");
        }
        
        if(c.getRg() <= 0){
            throw new NegocioException("Campo RG nao informado!");
        }          
        
        if(c.getTelefone() <= 0){
            throw new NegocioException("Campo Telefone nao informado!");
        } 
    }
    
    //Validando com o rg se o cliente é existente
    private void validarClientesExistente(Cliente c) throws NegocioException {
        if(RepositorioClientes.getInstance().clienteExiste(c.getRg())){
            throw new NegocioException("Cliente ja existente!");
        }
    }
}
