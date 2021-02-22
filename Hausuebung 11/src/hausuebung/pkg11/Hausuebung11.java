/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg11;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Scanner;

/**
 *
 * @author Florian
 */
public class Hausuebung11 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LocalDateTime date1 = LocalDateTime.of(2004, 01, 26, 12, 30, 30);
        LocalDateTime date2 = LocalDateTime.now();

        Duration difference = Duration.between(date1, date2);
//        LocalDateTime dateErg = LocalDateTime.ofEpochSecond(difference.getSeconds(), 0, ZoneOffset.UTC);
        System.out.println(Duration.ofSeconds(difference.getSeconds()));
        System.out.println();

        int[] dateArray = new int[6];
        Scanner sc = new Scanner(System.in);
        System.out.println("Years of Date1: ");
        dateArray[0] = Integer.valueOf(sc.nextLine());
        System.out.println("Months of Date1: ");
        dateArray[1] = Integer.valueOf(sc.nextLine());
        System.out.println("Days of Date1: ");
        dateArray[2] = Integer.valueOf(sc.nextLine());
        System.out.println("Hours of Date1: ");
        dateArray[3] = Integer.valueOf(sc.nextLine());
        System.out.println("Minutes of Date1: ");
        dateArray[4] = Integer.valueOf(sc.nextLine());
        System.out.println("Seconds of Date1: ");
        dateArray[5] = Integer.valueOf(sc.nextLine());
        
        LocalDateTime inputDate = LocalDateTime.of(dateArray[0], dateArray[1], dateArray[2],dateArray[3],dateArray[4],dateArray[5]);
        System.out.println(inputDate.toString());
        
    }

}
