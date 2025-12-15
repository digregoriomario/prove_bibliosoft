package gruppo5.bibliosoft.strumenti;

import gruppo5.bibliosoft.modelli.*;
import java.time.Year;

public class Validatore {

    public static void validaLibro(Libro libro) {
        //controlli isbn: ok
        if (libro.getIsbn() == null || libro.getIsbn().isBlank()) {
            throw new IllegalArgumentException("ISBN obbligatorio");
        }

        if (!libro.getIsbn().matches("[0-9]+")) {
            throw new IllegalArgumentException("ISBN non valido");
        }

        if (libro.getIsbn().length() != 10 && libro.getIsbn().length() != 13) {
            throw new IllegalArgumentException("Lunghezza ISBN non valida (ISBN10 o ISBN13)");
        }

        //controlli titolo: ok
        if (libro.getTitolo() == null || libro.getTitolo().isBlank()) {
            throw new IllegalArgumentException("Titolo obbligatorio");
        }

        //controlli copietotali: ok
        if (libro.getCopieTotali() <= 0) {
            throw new IllegalArgumentException("Numero copie deve essere >= 1");
        }

        //controlli anno: ok
        if (libro.getAnnoPubblicazione() > Year.now().getValue()) {
            throw new IllegalArgumentException("L'anno deve essere precedente o pari al " + Year.now().getValue());
        }

        //controlli autori: ok
        for (String autore : libro.getAutori()) {
            if (autore == null || autore.isBlank()) {
                throw new IllegalArgumentException("Autore vuoto");
            }

            if (!autore.matches("^[A-Za-zÀ-ÖØ-öø-ÿ .]+(,[A-Za-zÀ-ÖØ-öø-ÿ .]+)*$")) {
                throw new IllegalArgumentException("Autore non valido");
            }

        }
    }

    public static void validaUtente(Utente utente) {
        //controlli matricola: ok
        if (utente.getMatricola() == null || utente.getMatricola().isBlank()) {
            throw new IllegalArgumentException("Matricola obbligatoria");
        }

        if (!utente.getMatricola().matches("[0-9]+")) {
            throw new IllegalArgumentException("Matricola non valida");
        }

        //controllo nome:
        if (utente.getNome() == null || utente.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome obbligatorio");
        }

        if (!utente.getNome().matches("^[A-Za-zÀ-ÖØ-öø-ÿ .'-]+(,[A-Za-zÀ-ÖØ-öø-ÿ .'-]+)*$")) {
            throw new IllegalArgumentException("Nome non valido");
        }

        //controllo cognome:
        if (utente.getCognome() == null || utente.getCognome().isBlank()) {
            throw new IllegalArgumentException("Cognome obbligatorio");
        }

        if (!utente.getCognome().matches("^[A-Za-zÀ-ÖØ-öø-ÿ .'-]+(,[A-Za-zÀ-ÖØ-öø-ÿ .'-]+)*$")) {
            throw new IllegalArgumentException("Cognome non valido");
        }

        //controllo email:
        if (utente.getEmail() == null || !utente.getEmail().toLowerCase().endsWith("@studenti.unisa.it")) {
            throw new IllegalArgumentException("Email istituzionale non valida");
        }
    }
}
