package gruppo5.bibliosoft.archivi.filtri;

import gruppo5.bibliosoft.modelli.Libro;

public class FiltroLibro {

    // Ricerca generica su titolo, autore, editore, ISBN
    public static InterfacciaFiltro<Libro> ricerca(String testo) {
        String t = testo.toLowerCase();
        return l
                -> l.getTitolo().toLowerCase().contains(t)
                || l.getAutori().contains(t)//<-controlla qui, (devi vedere nella lista degli autori
                || l.getIsbn().toLowerCase().contains(t);
    }

    // Filtro ISBN esatto
    public static InterfacciaFiltro<Libro> ricercaIsbn(String codice) {
        return l -> l.getIsbn().equalsIgnoreCase(codice);
    }
}
