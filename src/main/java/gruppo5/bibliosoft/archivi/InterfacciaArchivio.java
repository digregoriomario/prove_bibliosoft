package gruppo5.bibliosoft.archivi;

import gruppo5.bibliosoft.archivi.filtri.*;

import java.util.List;

public interface InterfacciaArchivio<T> {
    void aggiungi(T elemento);
    void modifica(T elemento);
    void rimuovi(T elemento);
    List<T> lista();
    List<T> cerca(Filtro<T> filtro);
}
