package gruppo5.bibliosoft.archivi;

import gruppo5.bibliosoft.archivi.filtri.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Sottoarchivio<T> implements InterfacciaSottoarchivio<T> {   //archivio implementa l'interfaccia archivio
    protected Set<T> elementi = new TreeSet<>();    //il tree set organizza gli elementi in base a un criterio di ordinamento (da creare)
    
    @Override
    public void aggiungi(T elemento){   //metodo che aggiunge un elemento alla collezzione
        if(elementi.contains(elemento))
            throw new IllegalArgumentException(elemento.getClass().getSimpleName() + " già presente");
        elementi.add(elemento);
    }
    
    @Override         
    public void rimuovi(T elemento){    //metodo che rimuove un elemento dalla collezzione
        elementi.remove(elemento);
    }
    
    @Override
    public List<T> cerca(InterfacciaFiltro<T> filtro){ //funzione che restituisce una lista filtrata in base a un filtro
        if(filtro == null)  //se il filtro è null, allora restuisco l'intera lista (non filtrata)
            return lista();
        
        List<T> risultati = new ArrayList<>();  //creo una lista per il risultato della ricerca
        
        for(T elemento : elementi)  //per ogni elemento della collezzione...
            if(filtro.filtra(elemento))  //controllo se rispetta il filtro
                risultati.add(elemento);    //lo aggiungo alla lista dei risultati
        
        return risultati;   //restituisco i risultati
    }
    
    @Override
    public List<T> lista(){ //funzione che restuisce la lista degli elementi
        return new ArrayList<>(elementi);   //restituisco la lista degli elementi
    }
    
    @Override
    public int conta(){
        return elementi.size();
    }
    
    @Override
    public void modifica(T elemento){
        elementi.remove(elemento);
        elementi.add(elemento);
    }
}
