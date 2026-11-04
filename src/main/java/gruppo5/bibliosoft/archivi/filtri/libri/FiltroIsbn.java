package gruppo5.bibliosoft.archivi.filtri.libri;

import gruppo5.bibliosoft.archivi.filtri.Filtro;
import gruppo5.bibliosoft.modelli.Libro;

public class FiltroIsbn implements Filtro<Libro> {
    private final String isbnFiltro;
    
    public FiltroIsbn(String isbnFiltro){
        this.isbnFiltro = isbnFiltro;
    }
    @Override
    public boolean controlla(Libro libro) {
        if(libro == null)
            return false;
        
        return libro.getIsbn().equals(isbnFiltro);
    }
}
