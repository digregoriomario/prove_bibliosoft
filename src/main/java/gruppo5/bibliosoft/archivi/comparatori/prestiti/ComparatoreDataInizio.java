
package gruppo5.bibliosoft.archivi.comparatori.prestiti;

import java.util.Comparator;
import gruppo5.bibliosoft.modelli.Prestito;

public class ComparatoreDataInizio implements Comparator<Prestito> {
    @Override
    public int compare(Prestito prestitoA, Prestito prestitoB) {
        return prestitoA.getDataInizio().compareTo(prestitoB.getDataInizio());
    }
    
}
