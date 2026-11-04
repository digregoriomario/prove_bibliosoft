package gruppo5.bibliosoft.controller;

import gruppo5.bibliosoft.servizi.*;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class ControllerPrincipale {

    @FXML
    private BorderPane root;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabLibri;
    @FXML
    private Tab tabUtenti;
    @FXML
    private Tab tabPrestiti;

    private ServizioLibri servizioLibri;
    private ServizioUtenti servizioUtenti;
    private ServizioPrestiti servizioPrestiti;
    private ServizioArchivio servizioArchivio;

    private ControllerLibri controllerLibri;
    private ControllerUtenti controllerUtenti;
    private ControllerPrestiti controllerPrestiti;

    @FXML
    public void cambioTab() {
        System.out.print("\n\ncambiota\n\n");
        aggiornaTutto();
    }

    public void inizializzaServizi(ServizioLibri servizioLibri,
            ServizioUtenti servizioUtenti,
            ServizioPrestiti servizioPrestiti,
            ServizioArchivio servizioArchivio) {
        this.servizioLibri = servizioLibri;
        this.servizioUtenti = servizioUtenti;
        this.servizioPrestiti = servizioPrestiti;
        this.servizioArchivio = servizioArchivio;

        caricaVistaLibri();
        caricaVistaUtenti();
        caricaVistaPrestiti();
    }

    private void caricaVistaLibri() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gruppo5/bibliosoft/fxml/libri.fxml"));
            Node content = loader.load();
            controllerLibri = loader.getController();
            controllerLibri.impostaServizi(servizioLibri);
            tabLibri.setContent(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void caricaVistaUtenti() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gruppo5/bibliosoft/fxml/utenti.fxml"));
            Node content = loader.load();
            controllerUtenti = loader.getController();
            controllerUtenti.impostaServizi(servizioUtenti, servizioPrestiti);
            tabUtenti.setContent(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void caricaVistaPrestiti() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gruppo5/bibliosoft/fxml/prestiti.fxml"));
            Node content = loader.load();
            controllerPrestiti = loader.getController();
            controllerPrestiti.impostaServizi(servizioPrestiti, servizioUtenti, servizioLibri);
            tabPrestiti.setContent(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aggiornaTutto() {
        if (controllerLibri != null) {
            controllerLibri.aggiorna();
        }
        if (controllerUtenti != null) {
            controllerUtenti.aggiorna();
        }
        if (controllerPrestiti != null) {
            controllerPrestiti.aggiorna();
        }
    }

    @FXML
    private void onSalvaArchivio(ActionEvent event) {
        try {
            servizioArchivio.salva();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Archivio salvato con successo.");
            alert.setHeaderText("Salvataggio completato");
            alert.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Errore durante il salvataggio.");
            alert.setHeaderText("Errore");
            alert.showAndWait();
        }
    }

    @FXML
    private void onEsci(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Ci sono modifiche non salvate. Vuoi\n"
                + "salvare prima di uscire?",
                ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Conferma chiusura");
        alert.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.YES) {
                try {
                    servizioArchivio.salva();
                    root.getScene().getWindow().hide();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (bt == ButtonType.NO) {
                root.getScene().getWindow().hide();
            }

        });

    }
}
