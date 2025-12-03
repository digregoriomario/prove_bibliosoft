package gruppo5.bibliosoft.servizi;

import gruppo5.bibliosoft.archivi.ArchivioLibri;
import gruppo5.bibliosoft.modelli.Libro;
import gruppo5.bibliosoft.archivi.filtri.FiltroLibro;
import gruppo5.bibliosoft.strumenti.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ServizioLibri {

    private final ArchivioLibri archivioLibri;

    public ServizioLibri(ArchivioLibri archivioLibri) {
        this.archivioLibri = archivioLibri;
    }

    public void aggiungiLibro(Libro libro) {
        Validatore.validaLibro(libro);
        if(! archivioLibri.cerca(FiltroLibro.ricerca(libro.getIsbn())).isEmpty())
            throw new IllegalArgumentException("ISBN già presente");
        
        archivioLibri.aggiungi(libro);
    }

    public void modificaLibro(Libro libro) {
        Validatore.validaLibro(libro);
        archivioLibri.modifica(libro);
    }

    public void eliminaLibro(Libro libro) {
        if (!libro.isDisponibile()) {
            throw new IllegalStateException("Impossibile eliminare: libro in prestito o senza copie disponibili");
        }
        archivioLibri.rimuovi(libro);
    }

    public List<Libro> listaLibri() {
        return archivioLibri.lista();
    }

    public List<Libro> cerca(String filtro) {
        if (filtro == null || filtro.isBlank())
            return listaLibri();
        
        return archivioLibri.cerca(FiltroLibro.ricerca(filtro));
    }
    
    public int getLibriTotali(){
        return archivioLibri.contaElementi();
    }
    
    public int getCopieTotali(){
        int contatore = 0;
        for(Libro libro : archivioLibri.lista())
            contatore += libro.getCopieTotali();
        
        return contatore;
    }
    
        public int getCopieDisponibili(){
        int contatore = 0;
        for(Libro libro : archivioLibri.lista())
            contatore += libro.getCopieDisponibili();
        
        return contatore;
    }
}
