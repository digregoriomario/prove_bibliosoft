package gruppo5.bibliosoft.controller;

import gruppo5.bibliosoft.modelli.*;
import gruppo5.bibliosoft.servizi.*;
import gruppo5.bibliosoft.archivi.filtri.FiltroPrestito;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import gruppo5.bibliosoft.archivi.filtri.InterfacciaFiltro;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;

public class ControllerPrestiti {

    private InterfacciaFiltro<Prestito> filtroCorrente = FiltroPrestito.filtraAttivi();

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

    @FXML
    private Button bottoneAttivi;

    @FXML
    private Button bottoneConclusi;

    @FXML
    private Button bottoneTutti;

    private ServizioPrestiti servizioPrestiti;
    private ServizioUtenti servizioUtenti;
    private ServizioLibri servizioLibri;

    private final ObservableList<Prestito> datiPrestiti = FXCollections.observableArrayList();

    public void impostaServizi(ServizioPrestiti servizioPrestiti,
            ServizioUtenti servizioUtenti,
            ServizioLibri servizioLibri) {
        this.servizioPrestiti = servizioPrestiti;
        this.servizioUtenti = servizioUtenti;
        this.servizioLibri = servizioLibri;
        inizializzaTabella();

        selezionaFiltro(bottoneAttivi);
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
        colStato.setCellFactory(col -> new TableCell<Prestito, StatoPrestito>() {
            @Override
            protected void updateItem(StatoPrestito item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                    return;
                }

                setText(item.toString());
                setStyle("");

                switch (item) {
                    case IN_RITARDO ->
                        setStyle("-fx-text-fill: red;");

                    case IN_CORSO ->
                        setStyle("-fx-text-fill: orange;");

                    case CONCLUSO ->
                        setStyle("-fx-text-fill: green;");

                }
            }
        });

        tabellaPrestiti.setPlaceholder(new Label("Nessun prestito presente"));

        tabellaPrestiti.setItems(datiPrestiti);
    }

    @FXML
    private void mostraAttivi(ActionEvent event) {
        filtroCorrente = FiltroPrestito.filtraAttivi();
        selezionaFiltro(bottoneAttivi);
        aggiorna();
    }

    @FXML
    private void mostraConclusi(ActionEvent event) {
        filtroCorrente = FiltroPrestito.filtraConclusi();
        selezionaFiltro(bottoneConclusi);
        aggiorna();
    }

    @FXML
    private void mostraTutti(ActionEvent event) {
        filtroCorrente = null;
        selezionaFiltro(bottoneTutti);
        aggiorna();
    }

    private void selezionaFiltro(Button attivo) {
        // rimuovo lo stile "attivo" da tutti
        bottoneAttivi.getStyleClass().remove("bottoni_filtro_attivo");
        bottoneConclusi.getStyleClass().remove("bottoni_filtro_attivo");
        bottoneTutti.getStyleClass().remove("bottoni_filtro_attivo");

        // aggiungo lo stile "attivo" solo a quello selezionato
        if (!attivo.getStyleClass().contains("bottoni_filtro_attivo")) {
            attivo.getStyleClass().add("bottoni_filtro_attivo");
        }
    }

    private void aggiornaCombo() {
        comboUtente.setItems(FXCollections.observableArrayList(servizioUtenti.listaUtenti()));
        comboLibro.setItems(FXCollections.observableArrayList(servizioLibri.listaLibri()));
    }

    public void pulisciCampi() {
        comboUtente.getSelectionModel().clearSelection();
        comboUtente.setValue(null);

        comboLibro.getSelectionModel().clearSelection();
        comboLibro.setValue(null);

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

        servizioPrestiti.aggiornaRitardi();
        datiPrestiti.setAll(servizioPrestiti.cerca(filtroCorrente));
        aggiornaCombo();
        pulisciCampi();

        dataPrevista.setDayCellFactory(dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate elemento, boolean vuoto) {
                super.updateItem(elemento, vuoto);
                LocalDate oggi = LocalDate.now();
                if (elemento != null && (elemento.isBefore(oggi) || elemento.isEqual(oggi))) {
                    setDisable(true);
                }

            }
        });
    }

    private void mostraErrore(String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR, messaggio, ButtonType.OK);
        alert.setHeaderText("Errore");
        alert.getDialogPane().getStylesheets().add(
                getClass().getResource("/gruppo5/bibliosoft/css/stile_viste.css").toExternalForm()
        );
        alert.showAndWait();
    }
}
