package gruppo5.bibliosoft.archivi;

import gruppo5.bibliosoft.archivi.filtri.*;

import java.util.List;

public interface InterfacciaSottoarchivio<T> {   //generica interfaccia di un archivio
    void aggiungi(T elemento);  //previsto un metodo di aggiunta degli elementi
    void modifica(T elemento);  //previsto un metodo di modifica degli elementi
    void rimuovi(T elemento);  //previsto un metodo di rimozione degli elementi
    List<T> lista();    //prevista una funzione che restituisca la lista degli elementi
    List<T> cerca(InterfacciaFiltro<T> filtro);    //prevista una funzione che restituisca una lista filtrata in base a un filtro
    int conta();    //prevista una funzione che conta gli elementi
}
