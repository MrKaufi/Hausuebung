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

        CalculationOperation rCadd = (x, y) -> {
            double gNenner = x.getB() * y.getB();
            double upperMx = gNenner / x.getB();
            double upperMy = gNenner / y.getB();
            return new Number((x.getA() * upperMx) + (y.getA() * upperMy), gNenner);
        };

        CalculationOperation rCsub = (x, y) -> {
            double gNenner = x.getB() * y.getB();
            double upperMx = gNenner / x.getB();
            double upperMy = gNenner / y.getB();
            return new Number((x.getA() * upperMx) - (y.getA() * upperMy), gNenner);
        };

        CalculationOperation rCmult = (x, y) -> {
            return new Number(x.getA() * y.getA(), x.getB() * y.getB());
        };

        CalculationOperation rCdiv = (x, y) -> {
            return new Number(x.getA() * y.getB(), x.getB() * y.getA());
        };

        
        
        CalculationOperation vCadd = (x, y) -> {
            return new Number(x.getA() + y.getA(), x.getB() + y.getB());
        };
        CalculationOperation vCsub = (x, y) -> {
            return new Number(x.getA() - y.getA(), x.getB() - y.getB());
        };
        CalculationOperation vCmult = (x, y) -> {
            return new Number((x.getA() * y.getA()) + (x.getB() * y.getB()) ,1);
        };
        CalculationOperation vCdiv = (x, y) -> {
            return null; 
        };

        RationalCalculator rC = new RationalCalculator(rCadd, rCsub, rCmult, rCdiv);
        VectorCalculator vC = new VectorCalculator(vCadd, vCsub, vCmult, vCdiv);
        //ComplexCalculator cC = new ComplexCalculator(rCadd, subtract, multiply, divide);
        System.out.println("Rationaleberechnungen: ");
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
        
        System.out.println("Vektorberechnungen: ");
        
        System.out.println(vC.add(a, b).getA());
        System.out.println(vC.add(a, b).getB());
        System.out.println("");
        System.out.println(vC.subtract(a, b).getA());
        System.out.println(vC.subtract(a, b).getB());
        System.out.println("");
        System.out.println(vC.multiply(a, b).getA());
        System.out.println(vC.multiply(a, b).getB());
        System.out.println("");
        //System.out.println(vC.divide(a, b).getA());
        //System.out.println(vC.divide(a, b).getB());
        
        System.out.println("Komplexeberechnungen: ");
        NumberTester nt = new NumberTester("Hausuebung2 zahlen.txt");
        nt.testFile();

    }
}
