
package gruppo5.bibliosoft.archivi.filtri.prestiti;

import gruppo5.bibliosoft.archivi.filtri.Filtro;
import gruppo5.bibliosoft.modelli.Prestito;

public class FiltroMatricola implements Filtro<Prestito>{
    private final String matricolaFiltro;
    
    public FiltroMatricola(String matricolaFiltro){
        this.matricolaFiltro = matricolaFiltro;
    }
    
    @Override
    public boolean controlla(Prestito prestito) {
        if(prestito == null)
            return false;
        
        return prestito.getUtente().getMatricola().compareToIgnoreCase(matricolaFiltro) == 0;
    }
}