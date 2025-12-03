
package gruppo5.bibliosoft.archivi.filtri.prestiti;

import gruppo5.bibliosoft.archivi.filtri.*;
import gruppo5.bibliosoft.modelli.Prestito;
import gruppo5.bibliosoft.modelli.StatoPrestito;

public class FiltroConclusi implements Filtro<Prestito> {
    @Override
    public boolean controlla(Prestito elemento) {
        return elemento.getStato() == StatoPrestito.CONCLUSO;
    }
}
