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
    
    public void impostaServizi(ServizioPrestiti servizioPrestiti,
            ServizioUtenti servizioUtenti,
            ServizioLibri servizioLibri) {
        this.servizioPrestiti = servizioPrestiti;
        this.servizioUtenti = servizioUtenti;
        this.servizioLibri = servizioLibri;
        aggiorna();
    }
    
    public void aggiorna(){
        prestitiInRitardo.setText(Integer.toString(servizioPrestiti.getPrestitiInRitardo()));
        prestitiConclusi.setText(Integer.toString(servizioPrestiti.getPrestitiConclusi()));
        prestitiInCorso.setText(Integer.toString(servizioPrestiti.getPrestitiInCorso()));
    }
           
    
}
