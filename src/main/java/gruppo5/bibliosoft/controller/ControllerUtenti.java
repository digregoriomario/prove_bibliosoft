package gruppo5.bibliosoft.controller;

import gruppo5.bibliosoft.modelli.*;
import gruppo5.bibliosoft.servizi.*;
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
import javafx.scene.layout.Region;

public class ControllerUtenti {
    @FXML private TextField campoRicerca;
    @FXML private TableView<Utente> tabellaUtenti;
    @FXML private TableColumn<Utente, String> colMatricola;
    @FXML private TableColumn<Utente, String> colCognome;
    @FXML private TableColumn<Utente, String> colNome;
    @FXML private TableColumn<Utente, String> colEmail;
    @FXML private TableColumn<Utente, Number> colPrestiti;

    private ServizioUtenti servizioUtenti;
    private ServizioPrestiti servizioPrestiti;
    private final ObservableList<Utente> dati = FXCollections.observableArrayList();

    public void impostaServizi(ServizioUtenti servizioUtenti, ServizioPrestiti servizioPrestiti) {
        this.servizioUtenti = servizioUtenti;
        this.servizioPrestiti = servizioPrestiti;
        inizializzaTabella();
        aggiorna();
    }

    private void inizializzaTabella() {
        colMatricola.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getMatricola()));
        colCognome.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getCognome()));
        colNome.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNome()));
        colEmail.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getEmail()));
        colPrestiti.setCellValueFactory(c ->
                new javafx.beans.property.SimpleIntegerProperty(c.getValue().getPrestitiAttivi().size()));
        tabellaUtenti.setPlaceholder(new Label("Nessun utente presente"));
        tabellaUtenti.setItems(dati);
    }
    
    @FXML
    private void onRicerca() {
        String filtro = campoRicerca.getText();
        List<Utente> result = servizioUtenti.cerca(filtro);
        dati.setAll(result);
    }

    @FXML
    private void onAggiungi() {
        Dialog<Utente> finestraDialogo = creaDialogUtente(null);
        finestraDialogo.setTitle("Nuovo utente");
        finestraDialogo.showAndWait().ifPresent(u -> {
            try {
                servizioUtenti.aggiungiUtente(u);
                ControllerPrincipale.modificheEffettuate = true;
                aggiorna();
            } catch (Exception ex) {
                mostraErrore(ex.getMessage());
            }
        });
    }

    @FXML
    private void onModifica() {
        Utente selezionato = tabellaUtenti.getSelectionModel().getSelectedItem();
        if (selezionato == null) {
            mostraErrore("Seleziona un utente.");
            return;
        }
        Dialog<Utente> finestraDialogo = creaDialogUtente(selezionato);
        finestraDialogo.setTitle("Modifica utente");
        finestraDialogo.showAndWait().ifPresent(u -> {
            try {
                selezionato.setNome(u.getNome());
                selezionato.setCognome(u.getCognome());
                selezionato.setEmail(u.getEmail());
                servizioUtenti.modificaUtente(selezionato);
                ControllerPrincipale.modificheEffettuate = true;
                aggiorna();
            } catch (Exception ex) {
                mostraErrore(ex.getMessage());
            }
        });
    }

    @FXML
    private void onElimina() {
        Utente selezionato = tabellaUtenti.getSelectionModel().getSelectedItem();
        if (selezionato == null) {
            mostraErrore("Seleziona un utente.");
            return;
        }
        if(selezionato.haPrestitiAttivi()){
            mostraErrore("L'utente selezionato ha dei prestiti attivi");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Vuoi davvero eliminare l'utente selezionato?",
                ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Conferma eliminazione");
        alert.showAndWait().ifPresent(bt -> {
            if (bt == ButtonType.YES) {
                try {
                    servizioUtenti.eliminaUtente(selezionato);
                    ControllerPrincipale.modificheEffettuate = true;
                    aggiorna();
                } catch (Exception ex) {
                    mostraErrore(ex.getMessage());
                }
            }
        });
    }

    @FXML
    private void onStorico() {
        Utente selezionato = tabellaUtenti.getSelectionModel().getSelectedItem();
        if (selezionato == null) {
            mostraErrore("Seleziona un utente.");
            return;
        }
        List<Prestito> storico = servizioPrestiti.storico(selezionato);
        StringBuilder sb = new StringBuilder();
        //In corso, ritardo -> Titolo - Inzio - Prevista - Stato
        //Concluso -> Titolo - Inzio - Prevista  - Effettiva - Stato
        for(Prestito prestito : storico){
            switch(prestito.getStato()){
                case IN_CORSO: case IN_RITARDO:
                    sb.append(prestito.getLibro().getTitolo())
                    .append(" - Inizio: ").append(prestito.getDataInizio())
                    .append(" Prevista: ").append(prestito.getDataPrevista())
                    .append(" Stato: ").append(prestito.getStato())
                    .append("\n\n");
                    break;
                    
                case CONCLUSO:
                    sb.append(prestito.getLibro().getTitolo())
                    .append(" - Inizio: ").append(prestito.getDataInizio())
                    .append(" Prevista: ").append(prestito.getDataPrevista())
                    .append(" Effetiva: ").append(prestito. getDataRestituzioneEffettiva())
                    .append(" Stato: ").append(prestito.getStato())
                    .append("\n\n");
                    break;
            }
        }
        Label contenuto = new Label(sb.length() == 0 ? "Nessun prestito effettuato." : sb.toString());
        contenuto.setWrapText(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        alert.getDialogPane().setContent(contenuto);
        alert.setHeaderText("Storico prestiti di " + selezionato);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    public void aggiorna() {
        dati.setAll(servizioUtenti.listaUtenti());
    }

    private Dialog<Utente> creaDialogUtente(Utente iniziale) {
        Dialog<Utente> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        TextField matricolaField = new TextField();
        TextField nomeField = new TextField();
        TextField cognomeField = new TextField();
        TextField emailField = new TextField();

        if (iniziale != null) {
            matricolaField.setText(iniziale.getMatricola());
            matricolaField.setDisable(true);
            nomeField.setText(iniziale.getNome());
            cognomeField.setText(iniziale.getCognome());
            emailField.setText(iniziale.getEmail());
        }

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Matricola:"), 0, 0);
        grid.add(matricolaField, 1, 0);
        grid.add(new Label("Nome:"), 0, 1);
        grid.add(nomeField, 1, 1);
        grid.add(new Label("Cognome:"), 0, 2);
        grid.add(cognomeField, 1, 2);
        grid.add(new Label("Email istituzionale:"), 0, 3);
        grid.add(emailField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(bt -> {
            if (bt == ButtonType.OK) {
                try {
                    String m = matricolaField.getText();
                    String n = nomeField.getText();
                    String c = cognomeField.getText();
                    String e = emailField.getText();
                    return new Utente(m, n, c, e);
                } catch (Exception ex) {
                    mostraErrore("Dati non validi: " + ex.getMessage());
                    return null;
                }
            }
            return null;
        });

        return dialog;
    }

    private void mostraErrore(String messaggio) {
        Alert alert = new Alert(Alert.AlertType.ERROR, messaggio, ButtonType.OK);
        alert.setHeaderText("Errore");
        alert.showAndWait();
    }
}
