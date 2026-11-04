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

public class ServizioArchivio {

    private final String fileArchivio;
    private final ArchivioLibri archivioLibri;
    private final ArchivioUtenti archivioUtenti;
    private final ArchivioPrestiti archivioPrestiti;

    public ServizioArchivio(String fileArchivio,
            ArchivioLibri archivioLibri,
            ArchivioUtenti archivioUtenti,
            ArchivioPrestiti archivioPrestiti) {
        this.fileArchivio = fileArchivio;
        this.archivioLibri = archivioLibri;
        this.archivioUtenti = archivioUtenti;
        this.archivioPrestiti = archivioPrestiti;
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
                    archivioLibri.aggiungi(libro);
            
            if (utenti != null)
                for(Utente utente : utenti)
                    archivioUtenti.aggiungi(utente);
            
            if (prestiti != null)
                for(Prestito prestito : prestiti)
                    archivioPrestiti.aggiungi(prestito);
        }
    }

    public void salva() throws IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("libri", archivioLibri.lista());
        root.put("utenti", archivioUtenti.lista());
        root.put("prestiti", archivioPrestiti.lista());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileArchivio))) {
            oos.writeObject(root);
        }
    }
}
