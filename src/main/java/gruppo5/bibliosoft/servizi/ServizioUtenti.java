package gruppo5.bibliosoft.servizi;

import gruppo5.bibliosoft.archivi.Archivio;
import gruppo5.bibliosoft.archivi.filtri.FiltroUtente;
import gruppo5.bibliosoft.modelli.Utente;
import gruppo5.bibliosoft.strumenti.*;

import java.util.List;

public class ServizioUtenti{

    private final Archivio archivio;

    public ServizioUtenti(Archivio archivio) {
        this.archivio = archivio;
    }

    public void aggiungiUtente(Utente utente) {
        Validatore.validaUtente(utente);
        if(! archivio.cercaUtenti(FiltroUtente.ricercaMatricola(utente.getMatricola())).isEmpty())
            throw new IllegalArgumentException("Matricola già presente");
        
        archivio.aggiungiUtente(utente);
    }

    public void modificaUtente(Utente utente) {
        Validatore.validaUtente(utente);
        archivio.modificaUtente(utente);
    }

    public void eliminaUtente(Utente utente) {
        if (utente.haPrestitiAttivi())
            throw new IllegalStateException("Utente con prestiti attivi: eliminazione negata");
        
        archivio.rimuoviUtente(utente);
    }

    public List<Utente> listaUtenti() {
        return archivio.listaUtenti();
    }

    public List<Utente> cerca(String filtro) {
        if (filtro == null || filtro.isBlank())
            return listaUtenti();
        
        return archivio.cercaUtenti(FiltroUtente.ricerca(filtro));
    }
    
    public int getUtentiTotali(){
        return archivio.contaUtenti();
    }
    
    public int getUtentiAttivi(){
        return archivio.cercaUtenti(FiltroUtente.ricercaUtentiAttivi()).size();
    }
}
