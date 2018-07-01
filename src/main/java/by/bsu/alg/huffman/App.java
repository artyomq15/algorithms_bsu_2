package by.bsu.alg.huffman;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        HuffmanCompression huffmanCompression = new HuffmanCompression();
        try {
            huffmanCompression.encode("text.txt", "encoded");

            huffmanCompression.decode("encoded", "decoded");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
