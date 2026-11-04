package gruppo5.bibliosoft.modelli;

public enum StatoPrestito {
    IN_CORSO("In Corso"),
    IN_RITARDO("In Ritardo"),
    CONCLUSO("Concluso");

    private final String descrizione;

    StatoPrestito(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return descrizione;
    }
}
