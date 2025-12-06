package gruppo5.bibliosoft.modelli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Libro implements Serializable, Comparable<Libro>{

    private final String isbn;
    private String titolo;
    private List<String> autori = new ArrayList<>();
    private int annoPubblicazione;
    private int copieTotali;
    private int copieDisponibili;

    public Libro(String isbn, String titolo, List<String> autori, int annoPubblicazione, int copieTotali) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.autori = new ArrayList<>(autori);
        this.annoPubblicazione = annoPubblicazione;
        this.copieTotali = copieTotali;
        this.copieDisponibili = copieTotali;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public List<String> getAutori() {
        return new ArrayList<>(autori);
    }

    public void setAutori(List<String> autori) {
        this.autori = new ArrayList<>(autori);
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public int getCopieTotali() {
        return copieTotali;
    }

    public void setCopieTotali(int copieTotali) {
        this.copieTotali = copieTotali;
    }

    public int getCopieDisponibili() {
        return copieDisponibili;
    }

    public void setCopieDisponibili(int copieDisponibili) {
        this.copieDisponibili = copieDisponibili;
    }

    public boolean isDisponibile() {
        return copieDisponibili > 0;
    }
    
    public boolean haPrestitiAttivi(){
        return copieDisponibili != copieTotali;
    }

    @Override
    public String toString() {
        return titolo + " (" + isbn + ")";
    }

    public boolean equals(Object oggetto){
        if(this == oggetto)
            return true;
        
        if(oggetto == null || getClass() != oggetto.getClass())
            return false;
        
        return isbn.equals(((Libro) oggetto).getIsbn());
    }

    @Override
    public int compareTo(Libro libro) {
        if(isbn.compareToIgnoreCase(libro.getIsbn()) == 0)
            return 0;
        
        int cmp = titolo.compareToIgnoreCase(libro.getTitolo());
        return (cmp != 0)? cmp : isbn.compareToIgnoreCase(libro.getIsbn());
    }
    
        
    public boolean contieneAutore(String filtroAutore){
        for(String autore : autori)
            if(autore.toLowerCase().contains(filtroAutore.toLowerCase()))return true;
        
        return false;
    }
}
