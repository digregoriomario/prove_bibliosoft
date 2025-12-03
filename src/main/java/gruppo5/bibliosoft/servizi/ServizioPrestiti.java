package gruppo5.bibliosoft.servizi;

import gruppo5.bibliosoft.archivi.*;
import gruppo5.bibliosoft.modelli.*;
import gruppo5.bibliosoft.archivi.filtri.prestiti.*;
import gruppo5.bibliosoft.archivi.comparatori.prestiti.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ServizioPrestiti {
    private final ArchivioPrestiti archivioPrestiti;
    private final ArchivioLibri archivioLibri;
    private final ArchivioUtenti archivioUtenti;

    public ServizioPrestiti(ArchivioPrestiti archivioPrestiti,
                           ArchivioLibri archivioLibri,
                           ArchivioUtenti archivioUtenti) {
        this.archivioPrestiti = archivioPrestiti;
        this.archivioLibri = archivioLibri;
        this.archivioUtenti = archivioUtenti;
    }

    public Prestito registraPrestito(Utente utente, Libro libro, LocalDate dataPrevista) {
        if (!libro.isDisponibile())
            throw new IllegalStateException("Copie non disponibili");
        
        if(archivioPrestiti.cerca(new FiltroMatricolaAttivi(utente.getMatricola())).size() >= 3) 
            throw new IllegalStateException("L'utente ha già 3 prestiti attivi");
        

        if (!dataPrevista.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("La data di restituzione deve essere futura");
        

        Prestito prestito = new Prestito(utente, libro, LocalDate.now(), dataPrevista);
        archivioPrestiti.aggiungi(prestito);

        libro.setCopieDisponibili(libro.getCopieDisponibili() - 1);
        utente.aggiungiPrestito(prestito);
        return prestito;
    }

    public void registraRestituzione(Prestito prestito) {
        if (prestito.getStato() == StatoPrestito.CONCLUSO)
            return;

        prestito.setDataRestituzioneEffettiva(LocalDate.now());
        prestito.setStato(StatoPrestito.CONCLUSO);
        prestito.getLibro().setCopieDisponibili(prestito.getLibro().getCopieDisponibili() + 1);
        prestito.getUtente().rimuoviPrestito(prestito);
        archivioPrestiti.modifica(prestito);
    }

    public void aggiornaRitardi() {
        LocalDate oggi = LocalDate.now();
        for(Prestito prestito : archivioPrestiti.cerca(new FiltroAttivi())){
            prestito.aggiornaStato(oggi);
            archivioPrestiti.modifica(prestito);
        }
    }

    public List<Prestito> monitoraggio() {
        aggiornaRitardi();
        List<Prestito> risultato =  archivioPrestiti.cerca(new FiltroAttivi());
        risultato.sort(new ComparatoreDataPrevista());
        return risultato;
    }

    public List<Prestito> storico(Utente utente) {
        List<Prestito> risultato =  archivioPrestiti.cerca(new FiltroMatricola(utente.getMatricola()));
        risultato.sort(new ComparatoreDataInizio());
        return risultato;
    }
    
    public int getPrestitiInRitardo(){
        return archivioPrestiti.cerca(new FiltroInRitardo()).size();
    }
    
    public int getPrestitiConclusi(){
        return archivioPrestiti.cerca(new FiltroConclusi()).size();
    }
    
    public int getPrestitiInCorso(){
        return archivioPrestiti.cerca(new FiltroInCorso()).size();
    }
}
