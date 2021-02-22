/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung.pkg2;

import java.util.Scanner;

/**
 *
 * @author flori
 */
public class Hausuebung2 {

    public static void main(String[] args) {
        Number a = new Number(1, 1);
        Number b = new Number(1, 1);

        //Rational
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

        //Vektor
        CalculationOperation vCadd = (x, y) -> {
            return new Number(x.getA() + y.getA(), x.getB() + y.getB());
        };
        CalculationOperation vCsub = (x, y) -> {
            return new Number(x.getA() - y.getA(), x.getB() - y.getB());
        };
        CalculationOperation vCmult = (x, y) -> {
            return new Number((x.getA() * y.getA()) - (x.getB() * y.getB()), (x.getA() * y.getB() + (x.getB() * y.getA())));
        };
        CalculationOperation vCdiv = (x, y) -> {
            return new Number((x.getA() / y.getA()) - (x.getB() / y.getB()), (x.getA() / y.getB()) + (x.getB() / y.getA()));
        };

        //Complex
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

        Scanner sc = new Scanner(System.in);

        //Console
        System.out.println("Choose calculator:");
        System.out.println("1: Rationalen calculator");
        System.out.println("2: Vector calculator");
        System.out.println("3: Complex calculator");
        System.out.println("4: Exit program");
        String input = sc.next();
        while (input != "4") {
            switch (input) {
                case "1":
                    System.out.println("Enter number x_a:");
                    a.setA(Double.parseDouble(sc.next()));
                    System.out.println("Enter number x_b:");
                    a.setB(Double.parseDouble(sc.next()));
                    System.out.println("Enter number y_a:");
                    b.setA(Double.parseDouble(sc.next()));
                    System.out.println("Enter number y_b:");
                    b.setB(Double.parseDouble(sc.next()));

                    System.out.println("Choose operation: ");
                    System.out.println("1: add");
                    System.out.println("2: subtract");
                    System.out.println("3: multiply");
                    System.out.println("4: divide");
                    switch (sc.next()) {
                        case "1":
                            System.out.println(rC.add(a, b).getA());
                            System.out.println(rC.add(a, b).getB());
                            break;
                        case "2":
                            System.out.println(rC.subtract(a, b).getA());
                            System.out.println(rC.subtract(a, b).getB());
                            break;
                        case "3":
                            System.out.println(rC.multiply(a, b).getA());
                            System.out.println(rC.multiply(a, b).getB());
                            break;
                        case "4":
                            System.out.println(rC.divide(a, b).getA());
                            System.out.println(rC.divide(a, b).getB());
                            break;
                    }

                    break;
                case "2":
                    System.out.println("Enter number x_a:");
                    a.setA(Double.parseDouble(sc.next()));
                    System.out.println("Enter number x_b:");
                    a.setB(Double.parseDouble(sc.next()));
                    System.out.println("Enter number y_a:");
                    b.setA(Double.parseDouble(sc.next()));
                    System.out.println("Enter number y_b:");
                    b.setB(Double.parseDouble(sc.next()));
                    System.out.println("Choose operation: ");
                    System.out.println("1: add");
                    System.out.println("2: subtract");
                    System.out.println("3: multiply");
                    System.out.println("4: divide");
                    switch (sc.next()) {
                        case "1":
                            System.out.println(vC.add(a, b).getA());
                            System.out.println(vC.add(a, b).getB());
                            break;
                        case "2":
                            System.out.println(vC.subtract(a, b).getA());
                            System.out.println(vC.subtract(a, b).getB());
                            break;
                        case "3":
                            System.out.println(vC.multiply(a, b).getA());
                            System.out.println(vC.multiply(a, b).getB());
                            break;
                        case "4":
                            System.out.println(vC.divide(a, b).getA());
                            System.out.println(vC.divide(a, b).getB());
                            break;
                    }
                    break;
                case "3":
                    System.out.println("Enter number x_a:");
                    a.setA(Double.parseDouble(sc.next()));
                    System.out.println("Enter number x_b:");
                    a.setB(Double.parseDouble(sc.next()));
                    System.out.println("Enter number y_a:");
                    b.setA(Double.parseDouble(sc.next()));
                    System.out.println("Enter number y_b:");
                    b.setB(Double.parseDouble(sc.next()));
                    System.out.println("Choose operation: ");
                    System.out.println("1: add");
                    System.out.println("2: subtract");
                    System.out.println("3: multiply");
                    System.out.println("4: divide");
                    switch (sc.next()) {
                        case "1":
                            System.out.println(xC.add(a, b).getA());
                            System.out.println(xC.add(a, b).getB());
                            break;
                        case "2":
                            System.out.println(xC.subtract(a, b).getA());
                            System.out.println(xC.subtract(a, b).getB());
                            break;
                        case "3":
                            System.out.println(xC.multiply(a, b).getA());
                            System.out.println(xC.multiply(a, b).getB());
                            break;
                        case "4":
                            System.out.println(xC.divide(a, b).getA());
                            System.out.println(xC.divide(a, b).getB());
                            break;
                    }
                    break;

            }
            System.out.println("Choose calculator:");
            System.out.println("1: Relational calculator");
            System.out.println("2: Vector calculator");
            System.out.println("3: Complex calculator");
            System.out.println("4: Exit program");
            input = sc.next();
        }

    }
}
