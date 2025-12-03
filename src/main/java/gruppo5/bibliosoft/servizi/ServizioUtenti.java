package gruppo5.bibliosoft.servizi;

import gruppo5.bibliosoft.archivi.filtri.FiltroUtente;
import gruppo5.bibliosoft.archivi.ArchivioUtenti;
import gruppo5.bibliosoft.modelli.Utente;
import gruppo5.bibliosoft.strumenti.*;

import java.util.List;

public class ServizioUtenti {

    private final ArchivioUtenti archivioUtenti;

    public ServizioUtenti(ArchivioUtenti archivioUtenti) {
        this.archivioUtenti = archivioUtenti;
    }

    public void aggiungiUtente(Utente utente) {
        Validatore.validaUtente(utente);
        if(! archivioUtenti.cerca(FiltroUtente.ricercaMatricola(utente.getMatricola())).isEmpty())
            throw new IllegalArgumentException("Matricola già presente");
        
        archivioUtenti.aggiungi(utente);
    }

    public void modificaUtente(Utente utente) {
        Validatore.validaUtente(utente);
        archivioUtenti.modifica(utente);
    }

    public void eliminaUtente(Utente utente) {
        if (utente.haPrestitiAttivi())
            throw new IllegalStateException("Utente con prestiti attivi: eliminazione negata");
        
        archivioUtenti.rimuovi(utente);
    }

    public List<Utente> listaUtenti() {
        return archivioUtenti.lista();
    }

    public List<Utente> cerca(String filtro) {
        if (filtro == null || filtro.isBlank())
            return listaUtenti();
        
        return archivioUtenti.cerca(FiltroUtente.ricerca(filtro));
    }
}
