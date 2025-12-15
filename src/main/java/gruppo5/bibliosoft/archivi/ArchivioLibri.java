package gruppo5.bibliosoft.archivi;

import gruppo5.bibliosoft.modelli.Libro;

public class ArchivioLibri extends Archivio<Libro> {    //archivio dei libri estende un archivio classico
    @Override
    public void modifica(Libro libroModificato){  //metodo che modifica gli elementi dell'archivio
        elementi.remove(libroModificato);   //rimuovo l'elemento precedente (se presente)
        elementi.add(libroModificato);      //aggiungo l'elemento modificato
    }
}
