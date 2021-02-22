/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twelsch.htl.pos3.demo.java8.unittesting;

import java.util.Date;

/**
 *
 * @author Torsten Welsch
 */
public class Main {

    private int i;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    
    public static int foo(int i) {
        return -i;
    }
    
    public int fooNonStatic(int i) {
        return i * 4711;   
    }
    
    public void bar(int i) {
        this.i = i;
    }
    
    public int getI() {
        return i;
    }
    
    public Date getNow() {
        return new Date();
    }
    
}
