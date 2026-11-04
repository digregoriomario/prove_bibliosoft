package gruppo5.bibliosoft.archivi.filtri;

public interface Filtro<T> {
    boolean controlla(T elemento);
}
