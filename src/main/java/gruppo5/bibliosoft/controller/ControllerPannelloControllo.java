package gruppo5.bibliosoft.controller;

import gruppo5.bibliosoft.servizi.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerPannelloControllo {

    private ServizioPrestiti servizioPrestiti;
    private ServizioUtenti servizioUtenti;
    private ServizioLibri servizioLibri;
    
    @FXML
    private Label prestitiInCorso;

    @FXML
    private Label prestitiInRitardo;

    @FXML
    private Label prestitiConclusi;

    @FXML
    private Label utentiTotali;

    @FXML
    private Label utentiPrestitiAttivi;
    
    @FXML
    private Label libriTotali;
            
    @FXML
    private Label copieTotali;
    
    @FXML
    private Label copieDisponibili;
    
    public void impostaServizi(ServizioPrestiti servizioPrestiti,
            ServizioUtenti servizioUtenti,
            ServizioLibri servizioLibri) {
        this.servizioPrestiti = servizioPrestiti;
        this.servizioUtenti = servizioUtenti;
        this.servizioLibri = servizioLibri;
        aggiorna();
    }
    
    public void aggiorna(){
        aggiornaStatistichePrestiti();
        aggiornaStatisticheUtenti();
        aggiornaStatisticheLibri();
    }

    public void aggiornaStatistichePrestiti() {
        prestitiInRitardo.setText(Integer.toString(servizioPrestiti.getPrestitiInRitardo()));
        prestitiConclusi.setText(Integer.toString(servizioPrestiti.getPrestitiConclusi()));
        prestitiInCorso.setText(Integer.toString(servizioPrestiti.getPrestitiInCorso()));
    }

    public void aggiornaStatisticheUtenti() {
        utentiTotali.setText(Integer.toString(servizioUtenti.getUtentiTotali()));
        utentiPrestitiAttivi.setText(Integer.toString(servizioUtenti.getUtentiAttivi()));
    }
    
    public void aggiornaStatisticheLibri(){
        libriTotali.setText(Integer.toString(servizioLibri.getLibriTotali()));
        copieTotali.setText(Integer.toString(servizioLibri.getCopieTotali()));
        copieDisponibili.setText(Integer.toString(servizioLibri.getCopieDisponibili()));
    }
}
