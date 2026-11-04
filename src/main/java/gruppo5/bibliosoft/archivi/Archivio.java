package gruppo5.bibliosoft.archivi;

import gruppo5.bibliosoft.archivi.filtri.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class Archivio<T> implements InterfacciaArchivio<T> {
    protected Set<T> elementi = new TreeSet<>();
    
    @Override
    public void aggiungi(T elemento){
        elementi.add(elemento);
    }
    
    @Override         
    public void rimuovi(T elemento){
        elementi.remove(elemento);
    }
    
    @Override
    public List<T> cerca(Filtro<T> filtro){
        if(filtro == null)
            return new ArrayList<>(elementi);
        
        List<T> risultati = new ArrayList<>();
        
        for(T elemento : elementi)
            if(filtro.controlla(elemento))
                risultati.add(elemento);
        
        return risultati;
    }
    
    @Override
    public List<T> lista(){
        return new ArrayList<>(elementi);
    }
}
