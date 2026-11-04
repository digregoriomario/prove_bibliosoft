
package gruppo5.bibliosoft.archivi.filtri.prestiti;

import gruppo5.bibliosoft.archivi.filtri.Filtro;
import gruppo5.bibliosoft.modelli.Prestito;
import gruppo5.bibliosoft.modelli.StatoPrestito;

public class FiltroMatricolaAttivi implements Filtro<Prestito> {
    private final String matricolaFiltro;
    
    public FiltroMatricolaAttivi(String matricolaFiltro){
        this.matricolaFiltro = matricolaFiltro;
    }
    @Override
    public boolean controlla(Prestito prestito) {
        if(prestito == null)
            return false;
        
        return (new FiltroMatricola(matricolaFiltro).controlla(prestito) && (new FiltroAttivi()).controlla(prestito));
    }
}
