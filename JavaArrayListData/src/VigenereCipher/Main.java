package VigenereCipher;

import edu.duke.FileResource;

import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
//        String slices = vb.sliceString("abcdefghijklm", 1, 4);
//        System.out.println(slices);
//
//        FileResource fr = new FileResource("VigenereCipher/messages/secretmessage1.txt");
//        int[] keys = vb.tryKeyLength(fr.asString(), 4, 'e');
//        System.out.println(Arrays.toString(keys));

        vb.breakVigenere();
    }
}
