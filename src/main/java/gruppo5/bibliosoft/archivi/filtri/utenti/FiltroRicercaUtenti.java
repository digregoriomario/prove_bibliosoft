package gruppo5.bibliosoft.archivi.filtri.utenti;

import gruppo5.bibliosoft.archivi.filtri.Filtro;
import gruppo5.bibliosoft.modelli.Utente;

public class FiltroRicercaUtenti implements Filtro<Utente> {
    private final String stringaFiltro;
    
    public FiltroRicercaUtenti(String stringaFiltro){
        this.stringaFiltro = stringaFiltro;
    }
    
    @Override
    public boolean controlla(Utente utente) {
        if(utente == null)
            return false;
        
        return utente.getCognome().toLowerCase().contains(stringaFiltro.toLowerCase())
                || utente.getMatricola().contains(stringaFiltro);
    }
}
