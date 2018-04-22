
package view;

import Negocio.PetNegocio;
import Repositorio.RepositorioClientes;
import Util.Console;
import model.Cliente;
import model.Pet;
import model.TipoServico;
import view_menu.PetMenu;

/**
 *
 * @author Diovane
 */

//Aqui é criada toda a lógica de adicionar e listar cliente
public class PetUI {
    private PetNegocio petNegocio;
    
    //Executnado menu de cliente
    public void executar() {
        int opcao = 0;
        do {
            System.out.println(PetMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case PetMenu.OP_ADICIONAR:
                    adicionarPet();
                    break;
                case PetMenu.OP_LISTAR:
                    listarPets();
                    break;
                case PetMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != PetMenu.OP_VOLTAR);
    }
    
    private void adicionarPet() {
        String nomePet = Console.scanString("Nome: ");
        String tipoAnimal = Console.scanString("Tipo pet: ");
        Cliente dono = null;

        //LISTANDO CLIENTES PARA ESCOLHA DE DONO
        System.out.println(String.format("%-20s","\n|LISTA DE CLIENTES:"));
        for(Cliente c: RepositorioClientes.getInstance().getClientes()){
            System.out.println(String.format("%-20s",c.getNome()));
        }
        //ESCOLHENDO DONO
        String donoString = Console.scanString("Dono: ");
        for(Cliente c: RepositorioClientes.getInstance().getClientes()){
            if(donoString.equals(c.getNome())){
                dono = c;
            }
        }

        TipoServico servicoRealizado = null; 
        //LISTANDO SERVICOS PARA ESCOLHA
        System.out.println(String.format("%-20s","\n|LISTA DE SERVICOS:"));
        for(TipoServico ts: RepositorioServicos.getInstance().getNomeServico()){
            System.out.println(String.format("%-20s",ts.getNomeServico()));
        }
        //ESCOLHENDO SERVICO DESEJADO
        String servicoReal = Console.scanString("Servico Realizado: ");
        for(TipoServico ts: RepositorioServicos.getInstance().getNomeServico()){
            if(servicoReal.equals(ts.getNomeServico())){
                servicoRealizado = ts;
            }
        }
        petNegocio.salvar(new Pet(nomePet, tipoAnimal, dono, servicoRealizado));
        System.out.println("Cadastro realizado com sucesso!");
    }
    
    //LISTANDO PETS CADASTRADOS
    private void listarPets() {
        if (petNegocio.naoHaPets()) {
            System.out.println("=============================");
            System.out.println("Nao ha pets cadastrados");
            System.out.println("=============================\n");
        }else{
            System.out.println("=============================");
            System.out.print(String.format("%-10s","|NOME") + "\t" 
                + String.format("%-15s","|TIPO ANIMAL") + "\t"
                + String.format("%-20s","|CLIENTE") + "\t"
                + String.format("%-10s","|TIPO SERVICO"));
            for(Pet p: petNegocio.listarPets()){
                System.out.println(String.format("%-10s",p.getNomePet()) + "\t"
                    + String.format("%-15s",p.getTipoAnimal()) + "\t"
                    + String.format("%-20s",p.getDono().getNome()) + "\t"
                    + String.format("%-10s",p.getServicoRealizado().getNomeServico()));
            }
        }  
    }
}

