/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petshopfx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Diovane
 */
public class MenuServicoController implements Initializable {
@FXML
    VBox menuServico;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void voltarMenuPrincipal(ActionEvent event) throws IOException {
        Parent painelPrincipal = FXMLLoader.load(this.getClass().getResource("/petshopfx/view/MenuPrincipal.fxml"));
        Stage janela = (Stage) menuServico.getScene().getWindow();
        janela.setScene(new Scene(painelPrincipal)); 
    }
    
    @FXML
    private void botaoCadastrar(ActionEvent event) throws IOException {
 
    }
    
    @FXML
    private void botaoEditar(ActionEvent event) throws IOException {
 
    } 
    
    @FXML
    private void botaoRemover(ActionEvent event) throws IOException {
 
    }   
}
