/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo5.bibliosoft.archivi;

import gruppo5.bibliosoft.archivi.filtri.FiltroPrestito;
import gruppo5.bibliosoft.archivi.filtri.InterfacciaFiltro;
import gruppo5.bibliosoft.modelli.*;
import java.util.List;

/**
 *
 * @author mariodigregorio
 */
public class Archivio{
    private final Sottoarchivio<Libro> libri = new Sottoarchivio<>();
    private final Sottoarchivio<Utente> utenti = new Sottoarchivio<>();
    private final Sottoarchivio<Prestito> prestiti = new Sottoarchivio<>();
    
    
    //Gestione libri:
    public void aggiungiLibro(Libro libro){
        libri.aggiungi(libro);
    }
    
    public void modificaLibro(Libro libro){
        libri.modifica(libro);
    }
    
    public void rimuoviLibro(Libro libro){
        libri.rimuovi(libro);
    }
    
    public List<Libro> listaLibri(){
        return libri.lista();
    }
    
    public List<Libro> cercaLibri(InterfacciaFiltro<Libro> filtro){
        return libri.cerca(filtro);
    }
    
    public int contaLibri(){
        return libri.conta();
    }
    
    
    //Gestione utenti:
    public void aggiungiUtente(Utente utente){
        utenti.aggiungi(utente);
    }
    
    public void modificaUtente(Utente utente){
        utenti.modifica(utente);
    }
    
    public void rimuoviUtente(Utente utente){
        utenti.rimuovi(utente);
    }
    
    public List<Utente> listaUtenti(){
        return utenti.lista();
    }
    
    public List<Utente> cercaUtenti(InterfacciaFiltro<Utente> filtro){
        return utenti.cerca(filtro);
    }
    
    public int contaUtenti(){
        return utenti.conta();
    }
    
    
    //Gestione prestiti:
    public void aggiungiPrestito(Prestito prestito){
        prestiti.aggiungi(prestito); 
    }
    
    public void modificaPrestito(Prestito prestito){
        prestiti.modifica(prestito);
    }
    
    public void rimuoviPrestito(Prestito prestito){
        prestiti.rimuovi(prestito);
    }
    
    public List<Prestito> listaPrestiti(){
        return prestiti.lista();
    }
    
    public List<Prestito> cercaPrestiti(InterfacciaFiltro<Prestito> filtro){
        return prestiti.cerca(filtro);
    }
    
    public int contaPrestiti(){
        return prestiti.conta();
    }
}
