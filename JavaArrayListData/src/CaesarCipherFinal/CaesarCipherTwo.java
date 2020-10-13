package CaesarCipherFinal;

import CaesharChiper.CaesarCipher;

public class CaesarCipherTwo {
    private final String alphabet;
    private final String shiftedAlphabet1;
    private final String shiftedAlphabet2;
    private final int mainKey1;
    private final int mainKey2;

    public CaesarCipherTwo(int key1, int key2) {
        mainKey1 = key1;
        mainKey2 = key2;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
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

    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);

        return cc.encrypt(input);
    }
}
