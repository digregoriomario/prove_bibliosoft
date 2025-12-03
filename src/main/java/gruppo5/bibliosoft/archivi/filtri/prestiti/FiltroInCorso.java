/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo5.bibliosoft.archivi.filtri.prestiti;


import gruppo5.bibliosoft.archivi.filtri.*;
import gruppo5.bibliosoft.modelli.Prestito;
import gruppo5.bibliosoft.modelli.StatoPrestito;

public class FiltroInCorso implements Filtro<Prestito> {
    @Override
    public boolean controlla(Prestito elemento) {
        return elemento.getStato() == StatoPrestito.IN_CORSO;
    }
}
