package gruppo5.bibliosoft.archivi;

import gruppo5.bibliosoft.modelli.Prestito;

public class ArchivioPrestiti extends Archivio<Prestito> {  //archivio prestiti estende un archivio classico
    @Override
    public void modifica(Prestito prestitoModificato){  //metodo che modifica i prestiti
        for(Prestito prestito : elementi)   //per ogni prestito nella collezzione di prestiti
            if(prestito.equals(prestitoModificato)){    //controllo se il prestito in questione è quello da modificare
                prestito = prestitoModificato;  //apporto le modifiche
                return; //chiudo il metodo
            }        
    }
}
