package gruppo5.bibliosoft.modelli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Utente implements Serializable, Comparable<Utente>{
    private final String matricola;
    private String nome;
    private String cognome;
    private String email;
    private List<Prestito> prestitiAttivi = new ArrayList<>();

    public Utente(String matricola, String nome, String cognome, String email) {
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    public String getMatricola() { return matricola; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Prestito> getPrestitiAttivi() { return new ArrayList<>(prestitiAttivi); }

    public void aggiungiPrestito(Prestito prestito) { prestitiAttivi.add(prestito); }

    public void rimuoviPrestito(Prestito prestito) { prestitiAttivi.remove(prestito); }

    public boolean haPrestitiAttivi() { return !prestitiAttivi.isEmpty(); }

    @Override
    public String toString() {
        return cognome + " " + nome + " (" + matricola + ")";
    }
    
    public boolean equals(Object oggetto){
        if(this == oggetto)
            return true;
        
        if(oggetto == null || getClass() != oggetto.getClass())
            return false;
        
        return matricola.equals(((Utente) oggetto).getMatricola());
    }

    @Override
    public int compareTo(Utente utente) {
        if(matricola.compareToIgnoreCase(utente.getMatricola()) == 0)
            return 0;
        
        if(email.compareToIgnoreCase(utente.getEmail()) == 0)
            return 0;
        
        int cmp = cognome.compareToIgnoreCase(utente.getCognome());
        cmp = (cmp != 0)? cmp : nome.compareToIgnoreCase(utente.getNome());
        return (cmp != 0) ? cmp : matricola.compareToIgnoreCase(utente.getMatricola());
    }
}
