package gruppo5.bibliosoft.archivi.filtri;

import gruppo5.bibliosoft.modelli.Prestito;
import gruppo5.bibliosoft.modelli.StatoPrestito;

public class FiltroPrestito {

    // Ricerca generica su titolo, autore, editore, ISBN
    public static InterfacciaFiltro<Prestito> filtraConclusi() {
        return l -> l.getStato() == StatoPrestito.CONCLUSO;
    }

    public static InterfacciaFiltro<Prestito> filtraInCorso() {
        return l -> l.getStato() == StatoPrestito.IN_CORSO;
    }

    public static InterfacciaFiltro<Prestito> filtraInRitardo() {
        return l -> l.getStato() == StatoPrestito.IN_RITARDO;
    }

    public static InterfacciaFiltro<Prestito> filtraAttivi() {
        InterfacciaFiltro<Prestito> inCorso = filtraInCorso();
        InterfacciaFiltro<Prestito> inRitardo = filtraInRitardo();

        return l -> inCorso.filtra(l) || inRitardo.filtra(l);
    }

    public static InterfacciaFiltro<Prestito> ricercaMatricola(String matricola) {
        return l -> l.getUtente().getMatricola().equalsIgnoreCase(matricola);
    }

    public static InterfacciaFiltro<Prestito> ricercaAttiviMatricola(String matricola) {
        InterfacciaFiltro<Prestito> ricercaMatricola = ricercaMatricola(matricola);
        InterfacciaFiltro<Prestito> filtraAttivi = filtraAttivi();

        return l -> ricercaMatricola.filtra(l) && filtraAttivi.filtra(l);
    }
}
