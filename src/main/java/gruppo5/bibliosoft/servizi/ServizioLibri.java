package gruppo5.bibliosoft.servizi;

import gruppo5.bibliosoft.archivi.Archivio;
import gruppo5.bibliosoft.modelli.Libro;
import gruppo5.bibliosoft.archivi.filtri.FiltroLibro;
import gruppo5.bibliosoft.archivi.filtri.InterfacciaFiltro;
import gruppo5.bibliosoft.strumenti.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ServizioLibri{
    private final Archivio archivio;

    public ServizioLibri(Archivio archivio) {
        this.archivio = archivio;
    }


    public void aggiungiLibro(Libro libro) {
        Validatore.validaLibro(libro);
        archivio.aggiungiLibro(libro);
    }


    public void modificaLibro(Libro libro) {
        Validatore.validaLibro(libro);
        archivio.modificaLibro(libro);
    }


    public void eliminaLibro(Libro libro) {
        if (libro.haPrestitiAttivi()) {
            throw new IllegalStateException("Impossibile eliminare: libro in prestito o senza copie disponibili");
        }
        archivio.rimuoviLibro(libro);
    }

    public List<Libro> listaLibri() {
        return archivio.listaLibri();
    }

    public List<Libro> cercaLibri(InterfacciaFiltro<Libro> filtro) {
        if (filtro == null) {
            return listaLibri();
        }

        return archivio.cercaLibri(filtro);
    }

    public int getLibriTotali() {
        return archivio.contaLibri();
    }

    public int getCopieTotali() {
        int contatore = 0;
        for (Libro libro : archivio.listaLibri()) {
            contatore += libro.getCopieTotali();
        }

        return contatore;
    }

    public int getCopieDisponibili() {
        int contatore = 0;
        for (Libro libro : archivio.listaLibri()) {
            contatore += libro.getCopieDisponibili();
        }

        return contatore;
    }

}
