package gruppo5.bibliosoft.archivi.comparatori.prestiti;

import java.util.Comparator;
import gruppo5.bibliosoft.modelli.Prestito;

public class ComparatoreDataPrevista implements Comparator<Prestito> {    //comparatore in base alla data prevista del prestito, implementa il comparatore di prestiti
    @Override
    public int compare(Prestito prestitoA, Prestito prestitoB) {    //ovveride della compare dell'interfaccia
        return prestitoA.getDataPrevista().compareTo(prestitoB.getDataPrevista());  //restituisco la compareTo delle date previste dei due prestiti
    } 
}
