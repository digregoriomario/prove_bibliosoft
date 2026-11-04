package gruppo5.bibliosoft.archivi.filtri.utenti;

import gruppo5.bibliosoft.archivi.filtri.Filtro;
import gruppo5.bibliosoft.modelli.Utente;

public class FiltroMatricola implements Filtro<Utente> {
    private final String matricolaFiltro;
    
    public FiltroMatricola(String matricolaFiltro){
        this.matricolaFiltro = matricolaFiltro;
    }
    @Override
    public boolean controlla(Utente utente) {
        if(utente == null)
            return false;
        
        return utente.getMatricola().equals(matricolaFiltro);
    }
}
