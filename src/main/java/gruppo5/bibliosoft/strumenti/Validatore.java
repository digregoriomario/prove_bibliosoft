package gruppo5.bibliosoft.strumenti;

import gruppo5.bibliosoft.modelli.*;

public class Validatore {
    
    public static void validaLibro(Libro libro) {
        if (libro.getIsbn() == null || libro.getIsbn().isBlank())
            throw new IllegalArgumentException("ISBN obbligatorio");
        
        if (libro.getTitolo() == null || libro.getTitolo().isBlank())
            throw new IllegalArgumentException("Titolo obbligatorio");

        if (libro.getCopieTotali() < 1)
            throw new IllegalArgumentException("Numero copie deve essere >= 1");
    }

    public static void validaUtente(Utente utente) {
        if (utente.getMatricola() == null || utente.getMatricola().isBlank())
            throw new IllegalArgumentException("Matricola obbligatoria");
        
        if (utente.getEmail() == null || !emailValida(utente.getEmail()))
            throw new IllegalArgumentException("Email istituzionale non valida");
    }

    public static boolean emailValida(String email) {
        return email.toLowerCase().endsWith("@studenti.unisa.it");
    }
}
