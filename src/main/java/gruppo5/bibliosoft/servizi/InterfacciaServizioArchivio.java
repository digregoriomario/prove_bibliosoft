package gruppo5.bibliosoft.servizi;

import java.io.IOException;

public interface InterfacciaServizioArchivio {
    void carica() throws IOException, ClassNotFoundException;
    void salva() throws IOException;
}
