/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausübung.pkg1;

/**
 *
 * @author Florian
 */
public class EratosthenesPrimeSieve implements PrimeSieve {

    int p;

    public EratosthenesPrimeSieve(int p) {
        this.p = p;
    }

    @Override
    public boolean isPrime(int p) {
        for (int i = 2; i < p; i++) {
            if (p % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void printPrimes() {
        for (int i = 2; i < p; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    public void algorythm(int o) {
        int a = 0;
        int b = 0;
        for (int i = 0; i <= o; i++) {
            if (i % 2 == 0 && i > 2) {
                for (int j = 2; j < i; j++) {
                    if (isPrime(j)) {
                        
                        for (int k = 2; k < i; k++) {
                            if (isPrime(k) && k + j == i) {
                                a = j;
                                b = k;
                            }
                        }
                    }
                }
                            System.out.println(i + "=" + a + "+" + b);
            }

        }
    }
}
