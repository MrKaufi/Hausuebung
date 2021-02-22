/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.timeutil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Florian
 */
public class Main {

    public static void main(String[] ARGS){
        TimeUtilPro tUP = new TimeUtilPro();
        System.out.println(tUP.localDateTimeToInt(LocalDateTime.of(2004, 1, 26, 12, 30)));
    }
    
}
