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
import java.util.Arrays;

public class Bibliosoft extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // archivi:
        ArchivioLibri archivioLibri = new ArchivioLibri();
        ArchivioUtenti archivioUtenti = new ArchivioUtenti();
        ArchivioPrestiti archivioPrestiti = new ArchivioPrestiti();

        // servizi:
        ServizioLibri servizioLibri = new ServizioLibri(archivioLibri);
        ServizioUtenti servizioUtenti = new ServizioUtenti(archivioUtenti);
        ServizioPrestiti servizioPrestiti = new ServizioPrestiti(archivioPrestiti, archivioLibri, archivioUtenti);
        ServizioArchivio servizioArchivio
                = new ServizioArchivio("archivio.dat", archivioLibri, archivioUtenti, archivioPrestiti);

        
 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gruppo5/bibliosoft/fxml/main.fxml"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/gruppo5/bibliosoft/css/style.css").toExternalForm());

        ControllerPrincipale controller = loader.getController();
        controller.inizializzaServizi(servizioLibri, servizioUtenti, servizioPrestiti, servizioArchivio);

        stage.setTitle("BiblioSoft - Gestionale Biblioteca");
        stage.setScene(scene);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.show();

        try {
            servizioArchivio.carica();
            controller.aggiornaTutto();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Bibliosoft.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
