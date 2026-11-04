package gruppo5.bibliosoft.archivi.filtri.libri;

import gruppo5.bibliosoft.archivi.filtri.Filtro;
import gruppo5.bibliosoft.modelli.Libro;

public class FiltroRicerca implements Filtro<Libro> {
    private final String stringaFiltro;
    
    public FiltroRicerca(String stringaFiltro){
        this.stringaFiltro = stringaFiltro;
    }
    @Override
    public boolean controlla(Libro libro) {
        if(libro == null)
            return false;
        
        return libro.getTitolo().toLowerCase().contains(stringaFiltro.toLowerCase())
                || libro.getAutori().contains(stringaFiltro)
                || libro.getIsbn().contains(stringaFiltro);
    }
    
}
