package gruppo5.bibliosoft.servizi;

import gruppo5.bibliosoft.modelli.Libro;
import java.util.List;

public interface InterfacciaServizioLibri {
    void aggiungiLibro(Libro libro);
    void modificaLibro(Libro libro);
    void eliminaLibro(Libro libro);
    List<Libro> listaLibri();
    List<Libro> cerca(String filtro);
    int getLibriTotali();
    int getCopieTotali();
    int getCopieDisponibili();
}
