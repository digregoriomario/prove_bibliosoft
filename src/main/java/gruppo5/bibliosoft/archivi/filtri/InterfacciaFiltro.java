package gruppo5.bibliosoft.archivi.filtri;

public interface InterfacciaFiltro<T> {    //interfaccia filtro per implementare i diversi filtri necessarie alle ricerche
    boolean filtra(T elemento);  //devono implmentare il metodo controlla, che controlla se un elemento rispetta determinate condizioni
}
