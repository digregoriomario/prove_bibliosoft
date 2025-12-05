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

        /*servizioLibri.aggiungiLibro(new Libro("9788845292619", "Il Signore degli Anelli",
                List.of("J. R. R. Tolkien"), 1954, 10));

        servizioLibri.aggiungiLibro(new Libro("9780451524935", "1984",
                List.of("George Orwell"), 1949, 8));

        servizioLibri.aggiungiLibro(new Libro("9788807810468", "Il Nome della Rosa",
                List.of("Umberto Eco"), 1980, 7));

        servizioLibri.aggiungiLibro(new Libro("9780141439518", "Orgoglio e Pregiudizio",
                List.of("Jane Austen"), 1813, 5));

        servizioLibri.aggiungiLibro(new Libro("9780743273565", "Il Grande Gatsby",
                List.of("F. Scott Fitzgerald"), 1925, 6));

        servizioLibri.aggiungiLibro(new Libro("9781503280786", "Moby Dick",
                List.of("Herman Melville"), 1851, 4));

        servizioLibri.aggiungiLibro(new Libro("9780747532699", "Harry Potter e la Pietra Filosofale",
                List.of("J. K. Rowling"), 1997, 12));

        servizioLibri.aggiungiLibro(new Libro("9780553103540", "Il Trono di Spade",
                List.of("George R. R. Martin"), 1996, 9));

        servizioLibri.aggiungiLibro(new Libro("9780553213690", "La Metamorfosi",
                List.of("Franz Kafka"), 1915, 5));

        servizioLibri.aggiungiLibro(new Libro("9780156012195", "Il Piccolo Principe",
                List.of("Antoine de SaintExupéry"), 1943, 10));

        servizioLibri.aggiungiLibro(new Libro("9780192835086", "Le Avventure di Sherlock Holmes",
                List.of("Arthur Conan Doyle"), 1892, 6));

        servizioLibri.aggiungiLibro(new Libro("9788807900534", "I Promessi Sposi",
                List.of("Alessandro Manzoni"), 1827, 7));

        servizioLibri.aggiungiLibro(new Libro("9780805209990", "Il Processo",
                List.of("Franz Kafka"), 1925, 5));

        servizioLibri.aggiungiLibro(new Libro("9780060934347", "Don Chisciotte",
                List.of("Miguel de Cervantes"), 1605, 4));

        servizioLibri.aggiungiLibro(new Libro("9780199232765", "Guerra e Pace",
                List.of("Lev Tolstoj"), 1869, 6));

        servizioLibri.aggiungiLibro(new Libro("9780486411095", "Dracula",
                List.of("Bram Stoker"), 1897, 8));

        servizioLibri.aggiungiLibro(new Libro("9780486282114", "Frankenstein",
                List.of("Mary Shelley"), 1818, 6));

        servizioLibri.aggiungiLibro(new Libro("9788811581026", "Il Gattopardo",
                List.of("Giuseppe Tomasi di Lampedusa"), 1958, 5));

        servizioLibri.aggiungiLibro(new Libro("9788804486901", "Il Barone Rampante",
                List.of("Italo Calvino"), 1957, 7));

        servizioLibri.aggiungiLibro(new Libro("9788893818927", "La Storia Infinita",
                List.of("Michael Ende"), 1979, 6));

        servizioUtenti.aggiungiUtente(new Utente("1001", "Mario", "Rossi", "mario.rossi@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1002", "Luigi", "Bianchi", "luigi.bianchi@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1003", "Anna", "Verdi", "anna.verdi@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1004", "Francesca", "Neri", "francesca.neri@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1005", "Giorgio", "Russo", "giorgio.russo@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1006", "Chiara", "Galli", "chiara.galli@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1007", "Luca", "Marini", "luca.marini@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1008", "Giulia", "Ferrari", "giulia.ferrari@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1009", "Marco", "Serra", "marco.serra@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1010", "Sara", "Costa", "sara.costa@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1011", "Paolo", "Conti", "paolo.conti@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1012", "Elena", "Moretti", "elena.moretti@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1013", "Davide", "Colombo", "davide.colombo@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1014", "Marta", "Gatti", "marta.gatti@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1015", "Simone", "Ferrara", "simone.ferrara@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1016", "Laura", "Rizzo", "laura.rizzo@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1017", "Alessandro", "Fontana", "alessandro.fontana@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1018", "Irene", "Caruso", "irene.caruso@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1019", "Fabio", "Greco", "fabio.greco@studenti.unisa.it"));
        servizioUtenti.aggiungiUtente(new Utente("1020", "Serena", "Palmieri", "serena.palmieri@studenti.unisa.it"));*/

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gruppo5/bibliosoft/fxml/main.fxml"));   //carico il file fxml contenente la menubar e le tab
        Scene scena = new Scene(loader.load()); //carico la scena
        scena.getStylesheets().add(getClass().getResource("/gruppo5/bibliosoft/css/style.css").toExternalForm());   //aggiungo il foglio di stile alla scena

        ControllerPrincipale controller = loader.getController();   //instanzio il controller principale
        controller.inizializzaServizi(servizioLibri, servizioUtenti, servizioPrestiti, servizioArchivio);   //inizializzo i diversi servizi nel controller principale

        stage.setOnCloseRequest(e -> {
            controller.chiudiApplicazione(null);
        });
        stage.setTitle("BiblioSoft - Gestionale Biblioteca");   //do un titolo allo stage
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
