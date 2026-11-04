package gruppo5.bibliosoft.archivi;

import gruppo5.bibliosoft.modelli.Libro;
import java.util.List;

public class ArchivioLibri extends Archivio<Libro> {
    @Override
    public void modifica(Libro libroModificato){
        //if(libroModificato == null)return;
        for(Libro libro : elementi)
            if(libro.equals(libroModificato)){
                libro = libroModificato;
                return;
            }        
        //se non va implementa il for normale e usa elementi.set(indice, libro)
    }
}
