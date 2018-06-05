package view;

import model.Pet;
import model.Cliente;
import model.TipoServico;
import Util.Console;
import view_menu.PetMenu;
import Negocio.PetNegocio;
import Negocio.NegocioException;
import Repositorio.RepositorioPet;
import Repositorio.RepositorioClientes;
import Repositorio.RepositorioTipoServico;
import java.util.InputMismatchException;
import java.util.List;

/*
 *
 * @author Diovane
 */

//Aqui é criada toda a lógica de adicionar e listar pet
public class PetUI {
    private PetNegocio petNegocio;
    
    public PetUI() {
        petNegocio = new PetNegocio();
    }
    
    //Executnado menu de cliente
    public void executar() {
        int opcao = -1;
        do {
            try {
                System.out.println(PetMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção:");
                switch (opcao) {
                    case PetMenu.OP_CADASTRAR:
                        cadastrarPet();
                        break;
                    case PetMenu.OP_DELETAR:
                        deletarPet();
                        break;
                    case PetMenu.OP_ATUALIZAR:
                        atualizarPet();
                        break;
                    case PetMenu.OP_LISTAR:
                        mostrarPets();
                        break;
                    case PetMenu.OP_CONSULTAR:
                        consultarPetsPorNome();
                        break;
                    case PetMenu.OP_SAIR:
                        System.out.println("Finalizando a aplicacao..");
                        break;
                    default:
                        System.out.println("Opção inválida..");
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Somente numeros sao permitidos!");
            }

        } while (opcao != PetMenu.OP_SAIR);
    }
    
    private void cadastrarPet() {
        String nome = Console.scanString("Nome: ");
        String tipoAnimal = Console.scanString("Tipo Animal: ");
        listarDonos();
        String dono = Console.scanString("Qual o dono? ");
        try {
            petNegocio.salvar(new Pet(nome, tipoAnimal, telefone));
            System.out.println("Pet " + nome + " cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void adicionarPet() {
        if (RepositorioTipoServico.getInstance().estaVazio() || RepositorioClientes.getInstance().estaVazio()) {
            System.out.println("    =============================");
            System.out.println("Cadastrar clientes e servicos primeiro!");
            System.out.println("    =============================\n");
        }else{
            String nomePet = Console.scanString("Nome: ");
            String tipoAnimal = Console.scanString("Tipo pet: ");

            //LISTANDO CLIENTES PARA ESCOLHA DE DONO
            System.out.println(String.format("%-20s","\n|LISTA DE CLIENTES:"));
            for(Cliente c: RepositorioClientes.getInstance().getClientes()){
                System.out.println(String.format("%-20s",c.getNome()));
            }
            
            System.out.println("------------------------");
            //ESCOLHENDO DONO
            String donoString = Console.scanString("Dono: ");            
            Cliente dono = RepositorioClientes.getInstance().testeParaPet(donoString);
            
            //LISTANDO SERVICOS PARA ESCOLHA
            System.out.println(String.format("%-20s","\n|LISTA DE SERVICOS:"));
            for(TipoServico ts: RepositorioTipoServico.getInstance().getTipoServico()){
                System.out.println(String.format("%-20s",ts.getNomeServico()));
            }            
            System.out.println("------------------------");
            
            //ESCOLHENDO SERVICO DESEJADO
            String servicoString = Console.scanString("Servico Realizado: ");            
            TipoServico servicoRealizado = RepositorioTipoServico.getInstance().testeParaPet(servicoString);
            
            try{
                petNegocio.salvar(new Pet(nomePet, tipoAnimal, dono, servicoRealizado));
                System.out.println("Cadastro realizado com sucesso!");
            } catch(NegocioException ne){
                System.err.println(ne.getMessage());
            }
        }
    }
    
    //LISTANDO PETS CADASTRADOS
    private void listarPets() {
        if (RepositorioPet.getInstance().estaVazio()) {
            System.out.println("=============================");
            System.out.println("  Nao ha pets cadastrados");
            System.out.println("=============================\n");
        }else{
            System.out.println("=============================");
            System.out.println(String.format("%-10s","|NOME") + "\t" 
                + String.format("%-15s","|TIPO ANIMAL") + "\t"
                + String.format("%-20s","|CLIENTE") + "\t"
                + String.format("%-10s","|TIPO SERVICO"));
            for(Pet p: petNegocio.listarPets()){
                System.out.println(String.format("%-10s",p.getNomePet()) + "\t"
                    + String.format("%-15s", "|" + p.getTipoAnimal()) + "\t"
                    + String.format("%-20s", "|" + p.getDono().getNome()) + "\t"
                    + String.format("%-10s", "|" + p.getServicoRealizado().getNomeServico()));
            }
        }  
    }
}