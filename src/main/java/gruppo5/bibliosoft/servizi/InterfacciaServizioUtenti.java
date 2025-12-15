package gruppo5.bibliosoft.servizi;

import gruppo5.bibliosoft.modelli.Utente;
import java.util.List;

public interface InterfacciaServizioUtenti {
    void aggiungiUtente(Utente utente);
    void modificaUtente(Utente utente);
    void eliminaUtente(Utente utente);
    List<Utente> listaUtenti();
    List<Utente> cerca(String filtro);
    int getUtentiTotali();
    int getUtentiAttivi();
}
