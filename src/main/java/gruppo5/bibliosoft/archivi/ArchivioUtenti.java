package gruppo5.bibliosoft.archivi;

import gruppo5.bibliosoft.modelli.Utente;

public class ArchivioUtenti extends Archivio<Utente> {  //archivio utenti estende archivio classico
    @Override
    public void modifica(Utente utenteModificato){  //metodo che modifica gli elementi dell'archivio
        elementi.remove(utenteModificato);   //rimuovo l'elemento precedente (se presente)
        elementi.add(utenteModificato);      //aggiungo l'elemento modificato
    }
}
