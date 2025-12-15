package gruppo5.bibliosoft.archivi.filtri;

import gruppo5.bibliosoft.modelli.Utente;

public class FiltroUtente {

    // Ricerca generica su titolo, autore, editore, ISBN
    public static InterfacciaFiltro<Utente> ricercaMatricola(String matricola) {
        return l-> l.getMatricola().equalsIgnoreCase(matricola);
    }

    // Filtro ISBN esatto
    public static InterfacciaFiltro<Utente> ricerca(String stringaFiltro) {
        return l -> l.getCognome().toLowerCase().contains(stringaFiltro.toLowerCase())
                || l.getMatricola().contains(stringaFiltro);
    }
    
    public static InterfacciaFiltro<Utente> ricercaUtentiAttivi() {
        return l -> ! l.getPrestitiAttivi().isEmpty();
    }
}
