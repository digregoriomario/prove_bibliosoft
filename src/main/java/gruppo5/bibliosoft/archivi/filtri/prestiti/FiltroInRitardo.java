
package gruppo5.bibliosoft.archivi.filtri.prestiti;

import gruppo5.bibliosoft.archivi.filtri.Filtro;
import gruppo5.bibliosoft.modelli.Prestito;
import gruppo5.bibliosoft.modelli.StatoPrestito;

public class FiltroInRitardo  implements Filtro<Prestito>{
    @Override
    public boolean controlla(Prestito elemento) {
        return elemento.getStato() == StatoPrestito.IN_RITARDO;
    }  
}
