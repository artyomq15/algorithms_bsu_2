package by.bsu.alg.rsa;

import java.util.Random;

public class RSA {

    private int n;
    private int euler;
    private int e;
    private int d;

    public RSA(int p, int q){
        countN(p,q);
        countEuler(p,q);
        findE();
        findD();
    }

    public int[] getPublicKey(){
        return new int[]{e, n};
    }

    public int[] getPrivateKey(){
        return new int[]{d, n};
    }


    public int encrypt(int message, int[] key){
        return findRemainder(message, key[0], key[1]);
    }

    public int decrypt(int message, int[] key){
        return findRemainder(message, key[0], key[1]);
    }

    private void countN(int p, int q){
        n = p*q;
    }

    private void countEuler(int p, int q){
        euler = (p-1)*(q-1);
    }

    private void findE(){
        /*for (int i = 2; i < euler; ++i){
            if (gcd(i, euler) == 1){
                e = i;
                return;
            }
        }
        throw new IllegalArgumentException("Wrong euler value");*/

        e = new Random().ints(2, euler).limit(1).findFirst().getAsInt();
        int g = gcd(e, euler);

        while (g != 1){
            e = new Random().ints(2, euler).limit(1).findFirst().getAsInt();
            g = gcd(e, euler);
        }

    }

    private void findD(){
        d = extGcd(e, euler)[0];
        while (d <= 0){
            d += euler;
        }
    }

    private int gcd(int a, int b) {
        if (b == 0)
            return Math.abs(a);
        return gcd(b, a % b);
    }

    private static int[] extGcd (int a, int b) {
        if (a == 0) {
            return new int[]{0, 1, b};
        }
        int[] sol = extGcd (b%a, a);
        int x = sol[1] - (b / a) * sol[0];
        int y = sol[0];
        return new int[]{x, y, sol[2]};
    }

    private int findRemainder(int message, int power, int mod){
        int result = message;
        for (int i = 2; i <= power; ++i){
            result *= message;
            result %= mod;
        }
        return result;
    }
}
