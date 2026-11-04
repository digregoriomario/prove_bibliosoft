package gruppo5.bibliosoft.archivi.filtri.prestiti;

import gruppo5.bibliosoft.archivi.filtri.Filtro;
import gruppo5.bibliosoft.modelli.Prestito;
import gruppo5.bibliosoft.modelli.StatoPrestito;

public class FiltroAttivi implements Filtro<Prestito> {
    @Override
    public boolean controlla(Prestito prestito) {
        return prestito.getStato() == StatoPrestito.IN_CORSO || prestito.getStato() == StatoPrestito.IN_RITARDO;
    }
}
