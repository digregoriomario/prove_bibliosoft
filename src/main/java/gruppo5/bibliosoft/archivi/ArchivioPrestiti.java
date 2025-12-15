package gruppo5.bibliosoft.archivi;

import gruppo5.bibliosoft.modelli.Prestito;

public class ArchivioPrestiti extends Archivio<Prestito> {  //archivio prestiti estende un archivio classico
    @Override
    public void modifica(Prestito prestitoModificato){  //metodo che modifica gli elementi dell'archivio
        elementi.remove(prestitoModificato);   //rimuovo l'elemento precedente (se presente)
        elementi.add(prestitoModificato);      //aggiungo l'elemento modificato
    }
}
