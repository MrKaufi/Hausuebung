/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg2;

/**
 *
 * @author flori
 */
public class HalloJavamitForEach {
    
    public static void main(String[] args) {
        Number a = new Number(5, 7);
        Number b = new Number(4, 5);
        
        CalculationOperation rCadd = (x,y) -> {
        double gNenner = x.getB() * y.getB();
        double upperMx = gNenner/x.getB();
        double upperMy = gNenner/y.getB();
        return new Number((x.getA() * upperMx) + (y.getA() * upperMy) , gNenner);
        };
        
        CalculationOperation rCsub = (x,y) -> {
        double gNenner = x.getB() * y.getB();
        double upperMx = gNenner/x.getB();
        double upperMy = gNenner/y.getB();
        return new Number((x.getA() * upperMx) - (y.getA() * upperMy) , gNenner);
        };
        CalculationOperation rCmult = (x,y) -> {
        return new Number(x.getA() * y.getA(), x.getB() * y.getB());
        };
        CalculationOperation rCdiv = (x,y) -> {
        return new Number(x.getA() * y.getB(), x.getB() * y.getA());
        };
        /*
        VectorCalculator vC = new VectorCalculator(rCadd, subtract, multiply, divide);
        ComplexCalculator cC = new ComplexCalculator(rCadd, subtract, multiply, divide);
        */
        RationalCalculator rC = new RationalCalculator(rCadd, rCsub, rCmult, rCdiv);
        
        
        System.out.println(rC.add(a, b).getA());
        System.out.println(rC.add(a, b).getB());
        System.out.println("");
        System.out.println(rC.subtract(a, b).getA());
        System.out.println(rC.subtract(a, b).getB());
        System.out.println("");
        System.out.println(rC.multiply(a, b).getA());
        System.out.println(rC.multiply(a, b).getB());
        System.out.println("");
        System.out.println(rC.divide(a, b).getA());
        System.out.println(rC.divide(a, b).getB());
        NumberTester nt = new NumberTester("Hausuebung2 zahlen.txt");
        nt.testFile();
        
    }   
}
