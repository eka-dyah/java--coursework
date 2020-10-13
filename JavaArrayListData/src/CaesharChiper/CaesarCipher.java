package CaesharChiper;

import edu.duke.*;

import java.util.Scanner;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for (int i=0; i <encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idxUpper = alphabet.indexOf(currChar);
            int idxLower = alphabet.toLowerCase().indexOf(currChar);
            if (idxUpper != -1) {
                char newChar = shiftedAlphabet.charAt(idxUpper);
                encrypted.setCharAt(i, newChar);
            } else if (idxLower != -1) {
                char newChar = shiftedAlphabet.charAt(idxLower);
                encrypted.setCharAt(i, Character.toLowerCase(newChar));
            }
        }
        return encrypted.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        for (int i=0; i <encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idxUpper = alphabet.indexOf(currChar);
            int idxLower = alphabet.toLowerCase().indexOf(currChar);
            if (idxUpper != -1) {
                char newChar;
                if (i % 2 == 0) {
                    newChar = shiftedAlphabet1.charAt(idxUpper);
                }
                else {
                    newChar = shiftedAlphabet2.charAt(idxUpper);
                }
                encrypted.setCharAt(i, newChar);
            } else if (idxLower != -1) {
                char newChar;
                if (i % 2 == 0) {
                    newChar = shiftedAlphabet1.charAt(idxLower);
                }
                else {
                    newChar = shiftedAlphabet2.charAt(idxLower);
                }
                encrypted.setCharAt(i, Character.toLowerCase(newChar));
            }
        }
        return encrypted.toString();
    }

//    public void testCaesar() {
//        FileResource fr = new FileResource();
//        String message = fr.asString();
//        String encrypted = encrypt(message, 23);
//        System.out.println("key is " + 23 + "\n" + encrypted);
//    }

    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
        System.out.println(cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15));
        System.out.println(cc.encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8));
//        System.out.println(cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15));
        System.out.println(cc.encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.", 26-14, 26-24));

    }
}
