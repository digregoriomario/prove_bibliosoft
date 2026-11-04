package gruppo5.bibliosoft.archivi;

import gruppo5.bibliosoft.modelli.Prestito;

public class ArchivioPrestiti extends Archivio<Prestito> {
    @Override
    public void modifica(Prestito prestitoModificato){
        //if(prestitoModificato == null)return;
        for(Prestito prestito : elementi)
            if(prestito.equals(prestitoModificato)){
                prestito = prestitoModificato;
                return;
            }        
        //se non va implementa il for normale e usa elementi.set(indice, prestito)
    }
}
