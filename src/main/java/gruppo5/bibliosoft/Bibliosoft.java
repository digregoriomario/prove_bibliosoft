package gruppo5.bibliosoft;

import gruppo5.bibliosoft.modelli.*;
import gruppo5.bibliosoft.archivi.*;
import gruppo5.bibliosoft.servizi.*;
import gruppo5.bibliosoft.controller.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Bibliosoft extends Application {

    private static Scene scena; //scena principale

    @Override
    public void start(Stage stage) throws IOException {
        //instanzio gli archivi:
        Archivio archivio = new Archivio();
       

        //istanzio i servizi:
        ServizioLibri servizioLibri = new ServizioLibri(archivio);
        ServizioUtenti servizioUtenti = new ServizioUtenti(archivio);
        ServizioPrestiti servizioPrestiti = new ServizioPrestiti(archivio);
        ServizioArchivio servizioArchivio = new ServizioArchivio("archivio.dat", archivio);
        
        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gruppo5/bibliosoft/fxml/main.fxml"));   //carico il file fxml contenente la menubar e le tab
        Scene scena = new Scene(loader.load()); //carico la scena
        scena.getStylesheets().add(getClass().getResource("/gruppo5/bibliosoft/css/stile_principale.css").toExternalForm());   //aggiungo il foglio di stile alla scena

        ControllerPrincipale controller = loader.getController();   //instanzio il controller principale
        controller.inizializzaServizi(servizioLibri, servizioUtenti, servizioPrestiti, servizioArchivio);   //inizializzo i diversi servizi nel controller principale

        stage.setOnCloseRequest(e -> {
            controller.chiudiApplicazione(null);
        });
        stage.setTitle("Bibliosoft - Gestionale Biblioteca");   //do un titolo allo stage
        stage.setScene(scena);  //setto la scena nello stage
        stage.setMinWidth(900); //imposto una larghezza minima per lo stage (più di questo non può essere rimpicciolito
        stage.setMinHeight(600); //imposto un'altezza minima per lo stage (più di questo non può essere rimpicciolito
        stage.show();   //mostro lo stage

        try {
            servizioArchivio.carica();  //carico il servizio che si preoccupa di caricare o salvare i dati in un file oggetto (archivio.dat)
            controller.aggiornaTutto(); //dico al controller di aggiornare tutto
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /* static void setRoot(String fxml) throws IOException {
        scena.setRoot(loadFXML(fxml));
    }*/
    private static Parent loadFXML(String fxml) throws IOException {    //funzione per caricare i file fxml cercandoli nelle risorse del progetto
        FXMLLoader fxmlLoader = new FXMLLoader(Bibliosoft.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {    //il main lancia l'applicazione
        launch();   //lancio l'applicazione
    }

}
