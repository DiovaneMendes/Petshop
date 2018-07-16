
package petshopfx.controller;

import Model.Cliente;
import Model.Pet;
import Negocio.NegocioException;
import Negocio.PetNegocio;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import petshopfx.PetshopFX;
import petshopfx.view.PrintUtil;

/**
 * FXML Controller class
 *
 * @author Diovane
 */
public class MenuPetController implements Initializable {    
    @FXML
    private VBox menuPet;    
    @FXML
    private TableView<Pet> tableViewPets;
    @FXML
    private TableColumn<Pet, String> tableColumnNomePet;
    @FXML
    private TableColumn<Pet, String> tableColumnTipoAnimal;
    @FXML
    private TableColumn<Pet, String> tableColumnCliente;
    @FXML
    private AnchorPane painelFormularioPet;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldTipoAnimal;
    @FXML
    private ChoiceBox seletorCliente;
    
    private List<Pet> listaPets;
    private Pet petSelecionado;
    
    private ObservableList<Pet> observableListaPets;      
    private PetNegocio petNegocio;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        petNegocio = new PetNegocio();
        
        if (tableViewPets != null) {
            carregarTableViewPets();
        }
    }  

    private void carregarTableViewPets() {
        tableColumnNomePet.setCellValueFactory(new PropertyValueFactory<>("nomePet"));
        tableColumnTipoAnimal.setCellValueFactory(new PropertyValueFactory<>("tipoAnimal"));
        tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("dono"));

        listaPets = petNegocio.listarPets();

        observableListaPets = FXCollections.observableArrayList(listaPets);
        tableViewPets.setItems(observableListaPets);
    }
    
    @FXML
    public void botaoCadastrar(ActionEvent event) throws IOException {
        petSelecionado = null;
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(PetshopFX.class.getResource("/petshopfx/view/FormularioPet.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(menuPet.getScene().getWindow());
        stage.showAndWait();
        carregarTableViewPets();
    }
    
    @FXML
    public void botaoEditar(ActionEvent event) throws IOException {
        Pet petSelec = tableViewPets.getSelectionModel().getSelectedItem();
        if (petSelec != null) {
            FXMLLoader loader = new FXMLLoader(PetshopFX.class.getResource("/petshopfx/view/FormularioPet.fxml"));
            Parent root = (Parent) loader.load();

            MenuPetController controller = (MenuPetController) loader.getController();
            controller.setPetSelecionado(petSelec);

            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(menuPet.getScene().getWindow());
            dialogStage.showAndWait();
            carregarTableViewPets();
        } else {
            PrintUtil.printMessageError("Necessita selecionar um pet");
        }
    } 
    
    @FXML
    public void botaoRemover(ActionEvent event) throws IOException, NegocioException {
        Pet petSelec = tableViewPets.getSelectionModel().getSelectedItem();
        if (petSelec != null) {
            try {
                petNegocio.deletar(petSelec);
                this.carregarTableViewPets();
            } catch (NegocioException ex) {
                PrintUtil.printMessageError(ex.getMessage());
            }
        } else {
            PrintUtil.printMessageError("Necessita selecionar um pet");
        }
    }    
    
    @FXML
    public void botaoSalvar(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelFormularioPet.getScene().getWindow();
        
        if(petSelecionado == null){ 
            try {
                petNegocio.salvar(new Pet( 
                        textFieldNome.getText(),
                        textFieldTipoAnimal.getText(),
                        (Cliente) seletorCliente.getValue()));                
                stage.close();
            } catch (NegocioException ex) {
                PrintUtil.printMessageError(ex.getMessage());
            }            
        }
        else { 
            try {
                petSelecionado.setNomePet(textFieldNome.getText());                
                petSelecionado.setTipoAnimal(textFieldTipoAnimal.getText());
                petSelecionado.setDono((Cliente) seletorCliente.getValue());
                
                petNegocio.atualizar(petSelecionado);
                stage.close();
            } catch (NegocioException ex) {
                PrintUtil.printMessageError(ex.getMessage());
            }            
        } 
    }
    
    @FXML
    public void botaoCancelar(ActionEvent event) throws IOException {
        Stage stage = (Stage) painelFormularioPet.getScene().getWindow();
        stage.close();
    }
    
    public Pet getPetSelecionado() {
        return petSelecionado;
    }
    
    private void setPetSelecionado(Pet petSelecionado){
        this.petSelecionado = petSelecionado;
        textFieldNome.setText(petSelecionado.getNomePet());
        textFieldTipoAnimal.setText(petSelecionado.getTipoAnimal());
        seletorCliente.setValue(String.valueOf(petSelecionado.getDono().getNome()));
    }   
    
    @FXML
    private void voltarMenuPrincipal(ActionEvent event) throws IOException {
        Parent painelPrincipal = FXMLLoader.load(this.getClass().getResource("/petshopfx/view/MenuPrincipal.fxml"));
        Stage janela = (Stage) menuPet.getScene().getWindow();
        janela.setScene(new Scene(painelPrincipal)); 
    }
}
