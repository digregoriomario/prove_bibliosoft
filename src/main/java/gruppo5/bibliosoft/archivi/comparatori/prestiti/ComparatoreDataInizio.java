
package gruppo5.bibliosoft.archivi.comparatori.prestiti;

import java.util.Comparator;
import gruppo5.bibliosoft.modelli.Prestito;

public class ComparatoreDataInizio implements Comparator<Prestito> {    //comparatore in base alla data di inizio del prestito, implementa il comparatore di prestiti
    @Override
    public int compare(Prestito prestitoA, Prestito prestitoB) {    //ovveride della compare dell'interfaccia
        return prestitoA.getDataInizio().compareTo(prestitoB.getDataInizio());  //restituisco la compareTo delle date di inizio dei due prestiti
    }
    
}
