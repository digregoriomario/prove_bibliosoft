package gruppo5.bibliosoft.archivi;

import gruppo5.bibliosoft.modelli.Utente;

public class ArchivioUtenti extends Archivio<Utente> {  //archivio utenti estende archivio classico
    @Override
    public void modifica(Utente utenteModificato){  //metodo che modifica gli utenti
        for(Utente utente : elementi)   //per ogni utente della collezzione di utenti...
            if(utente.equals(utenteModificato)){    //controllo se l'utente in questione è quello da modificare
                utente = utenteModificato;  //apporto le modifiche
                return; //chiudo il metodo
            }        
    }
}
