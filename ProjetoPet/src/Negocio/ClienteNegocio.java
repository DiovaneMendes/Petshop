
package Negocio;

import Repositorio.RepositorioClientes;
import dao.ClienteDao;
import dao.impl_BD.ClienteDaoBd;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Diovane
 */

//Tudo que for preciso em referencia ao objeto cliente, é feito aqui
public class ClienteNegocio {
    private ClienteDao clienteDao;
    
    public ClienteNegocio(){
        clienteDao = new ClienteDaoBd();
    }
    //Validando campos e adicionando a lista
    public void salvar(Cliente c) throws NegocioException{
        this.validarCamposObrigatorios(c);
        this.validarClientesExistente(c);
        clienteDao.salvar(c);
    }
    
    //Listar a lista de clientes
    public List<Cliente> listar() {
        return(clienteDao.listar());
    }
    
    //Retorn se não há clientes na lista
    public boolean naoHaClientes(){
        return RepositorioClientes.getInstance().estaVazio();
    }
    
    public void deletar(Cliente cliente) throws NegocioException {
        if (cliente == null || cliente.getRg() == 0) {
            throw new NegocioException("Cliente nao existe!");
        }
        clienteDao.deletar(cliente);
    }
    
    public void atualizar(Cliente cliente) throws NegocioException {
        if (cliente == null || cliente.getRg() == 0) {
            throw new NegocioException("Cliente nao existe!");
        }
        this.validarCamposObrigatorios(cliente);
        clienteDao.atualizar(cliente);
    }
    
    public Cliente procurarPorRg(long rg) throws NegocioException {
        if (rg == 0) {
            throw new NegocioException("Campo RG nao informado");
        }
        Cliente cliente = clienteDao.procurarPorRg(rg);
        if (cliente == null) {
            throw new NegocioException("Cliente nao encontrado");
        }
        return (cliente);
    }
    
    public List<Cliente> listarPorNome(String nome) throws NegocioException {
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Campo nome nao informado");
        }
        return(clienteDao.listarPorNome(nome));
    }
    
    public boolean clienteExiste(long rg) {
        Cliente cliente = clienteDao.procurarPorRg(rg);
        return (cliente != null);
    }    
    
    //Validando campos para não ficar em branco
    private void validarCamposObrigatorios(Cliente c) throws NegocioException{
        if(c.getNome() == null || c.getNome().isEmpty()){
            throw new NegocioException("Campo Nome nao informado!");
        }       
        
        String apoioRg = Long.toString(c.getRg());
        if(apoioRg.length() < 10 || apoioRg.length() > 10){
            throw new NegocioException("Rg informado nao é valido!");
        }
        
        String apoioTelefone = Long.toString(c.getTelefone());
        if(apoioTelefone.length() < 9 || apoioTelefone.length() > 9){ 
            throw new NegocioException("Telefone informado nao é valido!");
        }        
    }
    
    //Validando com o rg se o cliente é existente
    private void validarClientesExistente(Cliente c) throws NegocioException {
        if(RepositorioClientes.getInstance().clienteExiste(c.getRg())){
            throw new NegocioException("Cliente ja existente!");
        }
    }
}
