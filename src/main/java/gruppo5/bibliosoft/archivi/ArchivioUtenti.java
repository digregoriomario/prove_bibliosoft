package gruppo5.bibliosoft.archivi;

import gruppo5.bibliosoft.modelli.Utente;

public class ArchivioUtenti extends Archivio<Utente> {
    @Override
    public void modifica(Utente utenteModificato){
        //if(utenteModificato == null)return;
        for(Utente utente : elementi)
            if(utente.equals(utenteModificato)){
                utente = utenteModificato;
                return;
            }        
        //se non va implementa il for normale e usa elementi.set(indice, utente)
    }
}
