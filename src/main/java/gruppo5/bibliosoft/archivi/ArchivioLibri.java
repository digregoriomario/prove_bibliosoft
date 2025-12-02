package gruppo5.bibliosoft.archivi;

import gruppo5.bibliosoft.modelli.Libro;
import java.util.List;

public class ArchivioLibri extends Archivio<Libro> {    //archivio dei libri estende un archivio classico
    @Override
    public void modifica(Libro libroModificato){    //metodo che modifica un libro nella collezione di libri
        for(Libro libro : elementi) //per ogni libro nella collezzione di libri...
            if(libro.equals(libroModificato)){    //controllo se il libro in questione è quello da modificare
                libro = libroModificato;    //apporto le modifiche
                return; //chiudo il metodo
            }        
    }
}
