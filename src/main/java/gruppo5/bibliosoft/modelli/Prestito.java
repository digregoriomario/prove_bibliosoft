package gruppo5.bibliosoft.modelli;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Prestito implements Serializable, Comparable<Prestito>{

    private String id;
    private Utente utente;
    private Libro libro;
    private LocalDate dataInizio;
    private LocalDate dataPrevista;
    private LocalDate dataRestituzioneEffettiva;
    private StatoPrestito stato;

    public Prestito(Utente utente, Libro libro, LocalDate dataInizio, LocalDate dataPrevista) {
        this.id = UUID.randomUUID().toString();
        this.utente = utente;
        this.libro = libro;
        this.dataInizio = dataInizio;
        this.dataPrevista = dataPrevista;
        this.stato = StatoPrestito.IN_CORSO;
    }

    public String getId() {
        return id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(LocalDate dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    public StatoPrestito getStato() {
        return stato;
    }

    public void setStato(StatoPrestito stato) {
        this.stato = stato;
    }

    public void aggiornaStato(LocalDate oggi) {
        if (stato == StatoPrestito.IN_CORSO && oggi.isAfter(dataPrevista)) {
            stato = StatoPrestito.IN_RITARDO;
        }
    }

    public boolean eInRitardo(LocalDate oggi) {
        aggiornaStato(oggi);
        return stato == StatoPrestito.IN_RITARDO;
    }

    public boolean equals(Object oggetto) {
        if (this == oggetto) {
            return true;
        }

        if (oggetto == null || getClass() != oggetto.getClass()) {
            return false;
        }

        
        return id.equals(((Prestito) oggetto).getId());
    }

    @Override
    public int compareTo(Prestito prestito) {
        int cmp = dataPrevista.compareTo(prestito.getDataPrevista());
        return (cmp != 0) ? cmp : id.compareTo(prestito.getId());
    }
}
