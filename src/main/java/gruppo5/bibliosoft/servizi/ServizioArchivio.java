package gruppo5.bibliosoft.servizi;

import gruppo5.bibliosoft.archivi.*;
import gruppo5.bibliosoft.modelli.Libro;
import gruppo5.bibliosoft.modelli.Prestito;
import gruppo5.bibliosoft.modelli.Utente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServizioArchivio{

    private final String fileArchivio;
    private final Archivio archivio;

    public ServizioArchivio(String fileArchivio,
            Archivio archivio) {
        this.fileArchivio = fileArchivio;
        this.archivio = archivio;
    }

    @SuppressWarnings("unchecked")
    public void carica() throws IOException, ClassNotFoundException {
        File file = new File(fileArchivio);
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Map<String, Object> root = (Map<String, Object>) ois.readObject();
            List<Libro> libri = (List<Libro>) root.get("libri");
            List<Utente> utenti = (List<Utente>) root.get("utenti");
            List<Prestito> prestiti = (List<Prestito>) root.get("prestiti");
            if (libri != null)
                for(Libro libro : libri)
                    archivio.aggiungiLibro(libro);
            
            if (utenti != null)
                for(Utente utente : utenti)
                    archivio.aggiungiUtente(utente);
            
            if (prestiti != null)
                for(Prestito prestito : prestiti)
                    archivio.aggiungiPrestito(prestito);
        }
    }

    public void salva() throws IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("libri", archivio.listaLibri());
        root.put("utenti", archivio.listaUtenti());
        root.put("prestiti", archivio.listaPrestiti());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileArchivio))) {
            oos.writeObject(root);
        }
    }
}
