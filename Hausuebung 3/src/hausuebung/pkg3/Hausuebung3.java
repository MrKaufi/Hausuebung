/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg3;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flori
 */
public class Hausuebung3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WeaponsReader wr = new WeaponsReader();
        try {
            wr.readCsv();
        } catch (IOException ex) {
            Logger.getLogger(Hausuebung3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Weapon> list = new LinkedList<>();
        list.sort((w1, w2) -> Integer.compare(w1.getDamage(), w2.getDamage()));
        for (int i = 0; i < list.size(); i++) {
            list.get(i).toString();
        }
    }
    
}
