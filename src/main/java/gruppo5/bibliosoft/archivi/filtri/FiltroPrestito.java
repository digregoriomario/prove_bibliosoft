package gruppo5.bibliosoft.archivi.filtri;

import gruppo5.bibliosoft.modelli.Prestito;
import gruppo5.bibliosoft.modelli.StatoPrestito;

public class FiltroPrestito {

    // Ricerca generica su titolo, autore, editore, ISBN
    public static Filtro<Prestito> filtraConclusi() {
        return l -> l.getStato() == StatoPrestito.CONCLUSO;
    }

    public static Filtro<Prestito> filtraInCorso() {
        return l -> l.getStato() == StatoPrestito.IN_CORSO;
    }

    public static Filtro<Prestito> filtraInRitardo() {
        return l -> l.getStato() == StatoPrestito.IN_RITARDO;
    }

    public static Filtro<Prestito> filtraAttivi() {
        Filtro<Prestito> inCorso = filtraInCorso();
        Filtro<Prestito> inRitardo = filtraInRitardo();

        return l -> inCorso.filtra(l) || inRitardo.filtra(l);
    }

    public static Filtro<Prestito> ricercaMatricola(String matricola) {
        return l -> l.getUtente().getMatricola().equalsIgnoreCase(matricola);
    }

    public static Filtro<Prestito> ricercaAttiviMatricola(String matricola) {
        Filtro<Prestito> ricercaMatricola = ricercaMatricola(matricola);
        Filtro<Prestito> filtraAttivi = filtraAttivi();

        return l -> ricercaMatricola.filtra(l) && filtraAttivi.filtra(l);
    }
}
