package gruppo5.bibliosoft.servizi;

import gruppo5.bibliosoft.archivi.*;
import gruppo5.bibliosoft.modelli.*;
import gruppo5.bibliosoft.archivi.filtri.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ServizioPrestiti{
    private final Archivio archivio;

    public ServizioPrestiti(Archivio archivio) {
        this.archivio = archivio;
    }

    public Prestito registraPrestito(Utente utente, Libro libro, LocalDate dataPrevista) {
        if (!libro.isDisponibile())
            throw new IllegalStateException("Copie non disponibili");
        
        if(archivio.cercaPrestiti(FiltroPrestito.ricercaAttiviMatricola(utente.getMatricola())).size() >= 3) 
            throw new IllegalStateException("L'utente ha già 3 prestiti attivi");
        

        if (!dataPrevista.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("La data di restituzione deve essere futura");
        

        Prestito prestito = new Prestito(utente, libro, LocalDate.now(), dataPrevista);
        archivio.aggiungiPrestito(prestito);

        libro.setCopieDisponibili(libro.getCopieDisponibili() - 1);
        utente.aggiungiPrestito(prestito);
        return prestito;
    }

    public void registraRestituzione(Prestito prestito) {
        if (prestito.getStato() == StatoPrestito.CONCLUSO)
            throw new IllegalStateException("Prestito concluso");
            //return;

        prestito.setDataRestituzioneEffettiva(LocalDate.now());
        prestito.setStato(StatoPrestito.CONCLUSO);
        prestito.getLibro().setCopieDisponibili(prestito.getLibro().getCopieDisponibili() + 1);
        prestito.getUtente().rimuoviPrestito(prestito);
        archivio.modificaPrestito(prestito);
    }

    public void aggiornaRitardi() {
        LocalDate oggi = LocalDate.now();
        for(Prestito prestito : archivio.cercaPrestiti(FiltroPrestito.filtraAttivi())){
            prestito.aggiornaStato(oggi);
            archivio.modificaPrestito(prestito);
        }
    }

    public List<Prestito> lista() {
        aggiornaRitardi();
        return archivio.listaPrestiti();
    }
    
   public List<Prestito> cerca(InterfacciaFiltro<Prestito> filtro){
       return archivio.cercaPrestiti(filtro);
   }

    public List<Prestito> storico(Utente utente) {
        return  archivio.cercaPrestiti(FiltroPrestito.ricercaMatricola(utente.getMatricola()));
    }
    
    public int getPrestitiInRitardo(){
        return archivio.cercaPrestiti(FiltroPrestito.filtraInRitardo()).size();
    }
    
    public int getPrestitiConclusi(){
        return archivio.cercaPrestiti(FiltroPrestito.filtraConclusi()).size();
    }
    
    public int getPrestitiInCorso(){
        return archivio.cercaPrestiti(FiltroPrestito.filtraInCorso()).size();
    }
}
