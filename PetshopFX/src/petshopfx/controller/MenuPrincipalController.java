package petshopfx.controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuPrincipalController implements Initializable {
    @FXML
    AnchorPane painelMenuInicial;
    
    @FXML
    private void irTelaCliente(ActionEvent event) throws IOException{
        Parent painelCliente = FXMLLoader.load(this.getClass().getResource("/petshopfx/view/MenuCliente.fxml"));
        Stage janela = (Stage) painelMenuInicial.getScene().getWindow();
        janela.setScene(new Scene(painelCliente));
    }
    
    @FXML
    private void irTelaPet(ActionEvent event) throws IOException{
        Parent painelPet = FXMLLoader.load(this.getClass().getResource("/petshopfx/view/MenuPet.fxml"));
        Stage janela = (Stage) painelMenuInicial.getScene().getWindow();
        janela.setScene(new Scene(painelPet));
    }
        
    @FXML
    private void irTelaServico(ActionEvent event) throws IOException{
        Parent painelServico = FXMLLoader.load(this.getClass().getResource("/petshopfx/view/MenuServico.fxml"));
        Stage janela = (Stage) painelMenuInicial.getScene().getWindow();
        janela.setScene(new Scene(painelServico));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}