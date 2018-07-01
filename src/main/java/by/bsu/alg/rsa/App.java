package by.bsu.alg.rsa;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        RSA rsa = new RSA(97, 59);
        int[] publicKey = rsa.getPublicKey();
        int[] privateKey = rsa.getPrivateKey();

        int secretMessage = 18;

        System.out.println("Secret message: " + secretMessage);

        int message = rsa.encrypt(secretMessage, publicKey);

        System.out.println("Encrypted message: " + message);

        int decrypted = rsa.decrypt(message, privateKey);

        System.out.println("Decrypted message: " + decrypted);

        System.out.println("\nPublic key: " + Arrays.toString(publicKey));


        int d;
        for (int i = 1; ; ++i){
            if (rsa.decrypt(message, new int[]{i, publicKey[1]}) == secretMessage){
                d = i;
                break;
            }
        }

        System.out.println(d);

        System.out.println("\nPrivate key: " + Arrays.toString(privateKey));
    }




}
