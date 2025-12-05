package gruppo5.bibliosoft.controller;

import gruppo5.bibliosoft.archivi.filtri.FiltroLibro;
import gruppo5.bibliosoft.modelli.Libro;
import gruppo5.bibliosoft.servizi.ServizioLibri;

import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ControllerLibri {

    @FXML
    private TextField campoRicerca;
    @FXML
    private TableView<Libro> tabellaLibri;
    @FXML
    private TableColumn<Libro, String> colIsbn;
    @FXML
    private TableColumn<Libro, String> colTitolo;
    @FXML
    private TableColumn<Libro, String> colAutori;
    @FXML
    private TableColumn<Libro, Integer> colAnno;
    @FXML
    private TableColumn<Libro, Integer> colCopieTot;
    @FXML
    private TableColumn<Libro, Integer> colCopieDisp;

    private ServizioLibri servizioLibri;
    private final ObservableList<Libro> dati = FXCollections.observableArrayList();

    public void impostaServizi(ServizioLibri servizioLibri) {
        this.servizioLibri = servizioLibri;
        inizializzaTabella();
        aggiorna();
    }

    private void inizializzaTabella() {
        colIsbn.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getIsbn()));
        colTitolo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTitolo()));
        colAutori.setCellValueFactory(c
                -> new javafx.beans.property.SimpleStringProperty(String.join(", ", c.getValue().getAutori())));
        colAnno.setCellValueFactory(c
                -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getAnnoPubblicazione()).asObject());
        colCopieTot.setCellValueFactory(c
                -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getCopieTotali()).asObject());
        colCopieDisp.setCellValueFactory(c
                -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getCopieDisponibili()).asObject());
        tabellaLibri.setPlaceholder(new Label("Nessun libro presente"));

        tabellaLibri.setItems(dati);
    }

    @FXML
    private void onRicerca() {
        String filtro = campoRicerca.getText();
        List<Libro> risultati = servizioLibri.cercaLibri(FiltroLibro.ricerca(filtro));
        dati.setAll(risultati);
    }

    @FXML
    private void onAggiungi() {
        Dialog<Libro> dialog = creaDialogLibro(null);
        dialog.setTitle("Nuovo libro");
        dialog.showAndWait().ifPresent(libro -> {
            try {
                servizioLibri.aggiungiLibro(libro);
                ControllerPrincipale.modificheEffettuate = true;
                aggiorna();
            } catch (Exception ex) {
                mostraErrore(ex.getMessage());
            }
        });
    }

    @FXML
    private void onModifica() {
        Libro selezionato = tabellaLibri.getSelectionModel().getSelectedItem();
        if (selezionato == null) {
            mostraErrore("Seleziona un libro dalla tabella.");
            return;
        }
        Dialog<Libro> finestraDialog = creaDialogLibro(selezionato);
        finestraDialog.setTitle("Modifica libro");
        finestraDialog.showAndWait().ifPresent(libro -> {
            try {
                selezionato.setTitolo(libro.getTitolo());
                selezionato.setAutori(libro.getAutori());
                selezionato.setAnnoPubblicazione(libro.getAnnoPubblicazione());
                selezionato.setCopieTotali(libro.getCopieTotali());
                selezionato.setCopieDisponibili(libro.getCopieDisponibili());
                servizioLibri.modificaLibro(selezionato);
                ControllerPrincipale.modificheEffettuate = true;
                aggiorna();
            } catch (Exception ex) {
                mostraErrore(ex.getMessage());
            }
        });
    }

    @FXML
    private void onElimina() {
        Libro selezionato = tabellaLibri.getSelectionModel().getSelectedItem();
        if (selezionato == null) {
            mostraErrore("Seleziona un libro da eliminare.");
            return;
        }
        if (selezionato.getCopieDisponibili() != selezionato.getCopieTotali()) {
            mostraErrore("Alcune copie di questo libro sono in prestito.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Vuoi davvero eliminare il libro selezionato?",
                ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Conferma eliminazione");
        alert.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.YES) {
                try {
                    servizioLibri.eliminaLibro(selezionato);
                    ControllerPrincipale.modificheEffettuate = true;
                    aggiorna();
                } catch (Exception ex) {
                    mostraErrore(ex.getMessage());
                }
            }
        });
    }

    public void aggiorna() {
        dati.setAll(servizioLibri.listaLibri());
    }

    private Dialog<Libro> creaDialogLibro(Libro iniziale) {
        Dialog<Libro> finestraDialog = new Dialog<>();
        finestraDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        TextField isbnField = new TextField();
        TextField titoloField = new TextField();
        TextField autoriField = new TextField();
        TextField annoField = new TextField();
        TextField copieField = new TextField();

        if (iniziale != null) {
            isbnField.setText(iniziale.getIsbn());
            isbnField.setDisable(true);
            titoloField.setText(iniziale.getTitolo());
            autoriField.setText(String.join(", ", iniziale.getAutori()));
            annoField.setText(String.valueOf(iniziale.getAnnoPubblicazione()));
            copieField.setText(String.valueOf(iniziale.getCopieTotali()));
        }

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("ISBN:"), 0, 0);
        grid.add(isbnField, 1, 0);
        grid.add(new Label("Titolo:"), 0, 1);
        grid.add(titoloField, 1, 1);
        grid.add(new Label("Autori (separati da ,):"), 0, 2);
        grid.add(autoriField, 1, 2);
        grid.add(new Label("Anno:"), 0, 3);
        grid.add(annoField, 1, 3);
        grid.add(new Label("Copie totali:"), 0, 4);
        grid.add(copieField, 1, 4);

        finestraDialog.getDialogPane().setContent(grid);

        finestraDialog.setResultConverter(bt -> {
            if (bt == ButtonType.OK) {
                try {
                    String isbn = isbnField.getText();
                    String titolo = titoloField.getText();
                    List<String> autori = Arrays.asList(autoriField.getText().split("\s*,\s*"));
                    int anno = Integer.parseInt(annoField.getText());
                    int copie = Integer.parseInt(copieField.getText());
                    return new Libro(isbn, titolo, autori, anno, copie);
                } catch (Exception ex) {
                    mostraErrore("Dati non validi: " + ex.getMessage());
                    return null;
                }
            }
            return null;
        });

        return finestraDialog;
    }

    private void mostraErrore(String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR, messaggio, ButtonType.OK);
        alert.setHeaderText("Errore");
        alert.showAndWait();
    }

}
