
package view_menu;

/**
 *
 * @author Diovane
 */

//Cria o modelo de menu para o objeto pet
public class PetMenu{
    public static final int OP_ADICIONAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes(){
        return ("\n==============MENU PET================\n"
                + "1- Adicionar Pets\n"
                + "2- Listar Pets\n"
                + "0- Voltar"
                + "\n======================================");
    }
}
