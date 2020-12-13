package hausuebung.pkg10;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import sun.security.util.Password;

public class StringUtil implements Runnable{

    /**
     * Applies SHA-256 algorithm to the given String.
     *
     * SHA (Secure Hash Algorithm 2) is a set of cryptographic hash functions
     * designed by the United States National Security Agency
     * (NSA).Cryptographic hash functions are mathematical operations run on
     * digital data; by com- paring the computed "hash" (the output from
     * execution of the algorithm) to a known and expected hash value, a person
     * can determine the data's in- tegrity.
     *
     * @param input Some String.
     * @return Generated SHA-256 signature.
     *
     */
    
    
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String solvePassword0() {
        boolean found = false;
        char[] chars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        String erg = "";

        while (!found) {
            for (int i = 0; i < 4; i++) {
                erg += chars[(int)(Math.random()*chars.length)];
            }
            if (applySha256(erg).equals("95511ec2b03a441daada2e54cad5a8a7ae990e99b4a9f3512b92f672467186b2")) {
                found = true;
                return erg;
            }
            erg = "";
        }
        return erg;
    }
    
    public String solvePassword1() {
        boolean found = false;
        char[] chars = {'A','B','C','D','E','F','G','H','I','J','K','L','M','M','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String erg = "";

        while (!found) {
            for (int i = 0; i < 6; i++) {
                erg += chars[(int)(Math.random()*chars.length)];
            }
            if (applySha256(erg).equals("6cd2017cdafb4b2d6412eb50c7a8e457dac6e5c5a5a528d03231462e5d774589")) {
                found = true;
                return erg;
            }
            erg = "";
        }
        return erg;
    }
    
    public String solvePassword2() {
        boolean found = false;
        char[] chars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A',
            'B','C','D','E','F','G','H','I','J','K','L','M','M','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            '0','1','2','3','4','5','6','7','8','9'};
        
        String erg = "";

        while (!found) {
            for (int i = 0; i < 5; i++) {
                erg += chars[(int)(Math.random()*chars.length)];
            }
            if (applySha256(erg).equals("a8d6f454f4b4ff90aef14abe614f61eede264190e088dee0995e17434d1dc2bf")) {
                found = true;
                return erg;
            }
            erg = "";
        }
        return erg;
    }
    
    public String solvePassword3() {
        boolean found = false;
        char[] chars = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        String erg = "";

        while (!found) {
            for (int i = 0; i < 4; i++) {
                erg += chars[(int)(Math.random()*chars.length)];
            }
            if (applySha256(erg).equals("520da0807c1e972fb9a862485009d47ad1c4978db1369652f5ae176085eb9df7")) {
                found = true;
                return erg;
            }
            erg = "";
        }
        return erg;
    }

    @Override
    public void run() {
        solvePassword0();
        solvePassword1();
        solvePassword2();
    }
}
