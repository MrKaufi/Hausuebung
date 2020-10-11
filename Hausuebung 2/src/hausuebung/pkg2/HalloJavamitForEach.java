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
            return new Number((x.getA() * y.getA()) + (x.getB() * y.getB()), 1);
        };
        CalculationOperation vCdiv = (x, y) -> {
            return null;
        };

        
        
        CalculationOperation xCadd = (x, y) -> {
            return new Number(x.getA() + y.getA(), x.getB() + y.getB());
        };
        CalculationOperation xCsub = (x, y) -> {
            return new Number(x.getA() - y.getA(), x.getB() - y.getB());
        };
        CalculationOperation xCmult = (x, y) -> {
            return new Number((x.getA() * y.getA()) - (x.getB() * y.getB()), (x.getA() * y.getB() + (x.getB() * y.getA())));
        };
        CalculationOperation xCdiv = (x, y) -> {
            return new Number((x.getA() / y.getA()) - (x.getB() / y.getB()), (x.getA() / y.getB()) + (x.getB() / y.getA()));
        };

        RationalCalculator rC = new RationalCalculator(rCadd, rCsub, rCmult, rCdiv);
        VectorCalculator vC = new VectorCalculator(vCadd, vCsub, vCmult, vCdiv);
        ComplexCalculator xC = new ComplexCalculator(xCadd, xCsub, xCmult, xCdiv);

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
        
        System.out.println(xC.add(a, b).getA());
        System.out.println(xC.add(a, b).getB());
        System.out.println("");
        System.out.println(xC.subtract(a, b).getA());
        System.out.println(xC.subtract(a, b).getB());
        System.out.println("");
        System.out.println(xC.multiply(a, b).getA());
        System.out.println(xC.multiply(a, b).getB());
        System.out.println("");
        System.out.println(xC.divide(a, b).getA());
        System.out.println(xC.divide(a, b).getB());

        NumberTester nt = new NumberTester("Hausuebung2 zahlen.txt");
        nt.testFile();

    }
}
