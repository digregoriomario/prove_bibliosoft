package gruppo5.bibliosoft.servizi;

import gruppo5.bibliosoft.modelli.*;
import java.time.LocalDate;
import java.util.List;
import gruppo5.bibliosoft.archivi.filtri.InterfacciaFiltro;

public interface InterfacciaServizioPrestiti {
    Prestito registraPrestito(Utente utente, Libro libro, LocalDate dataPrevista);
    void registraRestituzione(Prestito prestito);
    void aggiornaRitardi();
    List<Prestito> lista();
    List<Prestito> cerca(InterfacciaFiltro<Prestito> filtro);
    List<Prestito> storico(Utente utente);
    int getPrestitiInRitardo();
    int getPrestitiConclusi();
    int getPrestitiInCorso();
}
