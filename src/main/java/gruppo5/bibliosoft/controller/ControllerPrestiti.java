package gruppo5.bibliosoft.controller;

import gruppo5.bibliosoft.modelli.*;
import gruppo5.bibliosoft.servizi.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControllerPrestiti implements Initializable {

    @FXML
    private ComboBox<Utente> comboUtente;
    @FXML
    private ComboBox<Libro> comboLibro;
    @FXML
    private DatePicker dataPrevista;
    @FXML
    private TableView<Prestito> tabellaPrestiti;
    @FXML
    private TableColumn<Prestito, String> colUtente;
    @FXML
    private TableColumn<Prestito, String> colLibro;
    @FXML
    private TableColumn<Prestito, LocalDate> colInizio;
    @FXML
    private TableColumn<Prestito, LocalDate> colPrevista;
    @FXML
    private TableColumn<Prestito, LocalDate> colEffettiva;
    @FXML
    private TableColumn<Prestito, StatoPrestito> colStato;

    private ServizioPrestiti servizioPrestiti;
    private ServizioUtenti servizioUtenti;
    private ServizioLibri servizioLibri;

    private final ObservableList<Prestito> datiPrestiti = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void impostaServizi(ServizioPrestiti servizioPrestiti,
            ServizioUtenti servizioUtenti,
            ServizioLibri servizioLibri) {
        this.servizioPrestiti = servizioPrestiti;
        this.servizioUtenti = servizioUtenti;
        this.servizioLibri = servizioLibri;
        inizializzaTabella();
        aggiorna();
    }

    private void inizializzaTabella() {
        colUtente.setCellValueFactory(c
                -> new javafx.beans.property.SimpleStringProperty(c.getValue().getUtente().toString()));
        colLibro.setCellValueFactory(c
                -> new javafx.beans.property.SimpleStringProperty(c.getValue().getLibro().getTitolo()));
        colInizio.setCellValueFactory(c
                -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getDataInizio()));
        colPrevista.setCellValueFactory(c
                -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getDataPrevista()));
        colEffettiva.setCellValueFactory(c
                -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getDataRestituzioneEffettiva()));
        colStato.setCellValueFactory(c
                -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getStato()));

        tabellaPrestiti.setItems(datiPrestiti);
    }

    private void aggiornaCombo() {
        comboUtente.setItems(FXCollections.observableArrayList(servizioUtenti.listaUtenti()));
        comboLibro.setItems(FXCollections.observableArrayList(servizioLibri.listaLibri()));
    }
    
    public void pulisciCampi(){
        comboUtente.getSelectionModel().clearSelection();
        comboLibro.getSelectionModel().clearSelection();
        dataPrevista.setValue(null);
    }

    @FXML
    private void onRegistraPrestito() {
        Utente utente = comboUtente.getValue();
        Libro libro = comboLibro.getValue();
        LocalDate data = dataPrevista.getValue();
        if (utente == null || libro == null || data == null) {
            mostraErrore("Seleziona utente, libro e data prevista.");
            return;
        }
        try {
            servizioPrestiti.registraPrestito(utente, libro, data);
            ControllerPrincipale.modificheEffettuate = true;
            aggiorna();
        } catch (Exception ex) {
            mostraErrore(ex.getMessage());
        }
    }

    @FXML
    private void onRegistraRestituzione() {
        Prestito prestito = tabellaPrestiti.getSelectionModel().getSelectedItem();
        if (prestito == null) {
            mostraErrore("Seleziona un prestito da restituire.");
            return;
        }
        try {
            servizioPrestiti.registraRestituzione(prestito);
            ControllerPrincipale.modificheEffettuate = true;
            aggiorna();
        } catch (Exception ex) {
            mostraErrore(ex.getMessage());
        }
    }

    public void aggiorna() {
        datiPrestiti.setAll(servizioPrestiti.monitoraggio());
        aggiornaCombo();
        pulisciCampi();
        dataPrevista.setDayCellFactory(dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                LocalDate today = LocalDate.now();
                if (item != null && (item.isBefore(today) || item.isEqual(today)))
                    setDisable(true);
                
            }
        });
    }

    private void mostraErrore(String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR, messaggio, ButtonType.OK);
        alert.setHeaderText("Errore");
        alert.showAndWait();
    }
}
