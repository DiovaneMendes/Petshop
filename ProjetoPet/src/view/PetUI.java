package view;

import model.Pet;
import model.Cliente;
import Util.Console;
import view_menu.PetMenu;
import Negocio.PetNegocio;
import Negocio.NegocioException;
import Util.MostraCoisas;
import dao.impl_BD.ClienteDaoBd;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/*
 *
 * @author Diovane
 */

//Aqui é criada toda a lógica de adicionar e listar pet
public class PetUI {
    private PetNegocio petNegocio;  
    private MostraCoisas mc = new MostraCoisas();
    
    public PetUI() {
        petNegocio = new PetNegocio();
    }
    
    //Executnado menu de pet
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
        if (mc.retornaListaClientes() == null) {
            System.out.println("=============================");
            System.out.println("Cadastrar clientes primeiro!");
            System.out.println("=============================\n");
        }else{
            String nome = Console.scanString("Nome Pet: ");
            String tipoAnimal = Console.scanString("Tipo Animal: ");      
            mc.listaDonos(); 
            String nomeDono = Console.scanString("Escolha o dono: ");
            
            try {
                petNegocio.salvar(new Pet(nome, tipoAnimal, mc.retornaIdDono(nomeDono)));
                System.out.println("Pet " + nome + " cadastrado com sucesso!");
            } catch (NegocioException ex) {
                UIUtil.mostrarErro(ex.getMessage());
            }  
        }
    }
    
    private void deletarPet(){
        String nome = Console.scanString("Nome do pet a ser deletado: ");
        try {            
            Pet pet = petNegocio.procurarPorNome(nome);
            this.mostraPet(pet);
            
            if (UIUtil.getConfirmacao("Realmente deseja excluir esse pet?")) {
                petNegocio.deletar(pet);
                System.out.println("Pet deletado com sucesso!");
            } else {
                System.out.println("Operacao cancelada!");
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
    
    private void atualizarPet() {
        String nomeP = Console.scanString("Nome do pet a ser alterado: ");
        try {
            Pet pet = petNegocio.procurarPorNome(nomeP);
            this.mostraPet(pet);

            System.out.println("Digite os dados do pet que quer alterar");
            String nome = Console.scanString("Nome[Deixe vazio caso não queira]: ");
            String tipoAnimal = Console.scanString("Tipo de animal[Deixe vazio caso não queira]: ");
            mc.listaDonos(); 
            String nomeDono = Console.scanString("Escolha o dono: ");
            int fkDono = mc.retornaIdDono(nomeDono);
            
            if (!nome.isEmpty()) {
                pet.setNomePet(nome);
            }
            if (!tipoAnimal.isEmpty()) {
                pet.setTipoAnimal(tipoAnimal);
            }
            if (fkDono < 0) {
                pet.setFkDono(fkDono);
            }
            petNegocio.atualizar(pet);
            System.out.println("Pet " + nome + " atualizado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }
    
    private void consultarPetsPorNome() {
        String nome = Console.scanString("Nome: ");
        try {
            List<Pet> listaPet = petNegocio.listarPorNome(nome);
            this.listarPets(listaPet);
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
    
    private void listarPets(List<Pet> listaPets) {
        if (listaPets.isEmpty()) {
            System.out.println("=============================");
            System.out.println("   Nao ha pets cadastrados");
            System.out.println("=============================\n");
        } else {
            System.out.println("=============================\n");
            System.out.println(String.format("%-2s", "ID") + "\t"
                    + String.format("%-10s", "|NOME") + "\t"
                    + String.format("%-10s", "|TIPO ANIMAL") + "\t"
                    + String.format("%-10s", "|DONO"));
            for(Pet p : listaPets) {
                System.out.println(String.format("%-2s", p.getIdPet()) + "\t"
                        + String.format("%-10s", "|" + p.getNomePet()) + "\t"
                        + String.format("%-10s", "|" + p.getTipoAnimal()) + "\t"
                        + String.format("%-10s", "|" + mc.retornaNomeDono(p.getFkDono())));
            }
        }
    }
    
    public void mostrarPets() {
        List<Pet> listaPets = petNegocio.listarPets();
        this.listarPets(listaPets);
    }
    
    private void mostraPet(Pet p){
        System.out.println("\nPet");
        System.out.println("Nome: " + p.getNomePet());
        System.out.println("Tipo animal: " + p.getTipoAnimal());
        System.out.println("Dono: " + mc.retornaNomeDono(p.getFkDono()) + "\n");
    }
}