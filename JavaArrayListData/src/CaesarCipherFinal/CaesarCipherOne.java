package CaesarCipherFinal;

public class CaesarCipherOne {
    private final String alphabet;
    private final String shiftedAlphabet;
    private final int mainKey;

    public CaesarCipherOne(int key) {
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
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

    public String decrypt(String input) {
        CaesarCipherOne cc = new CaesarCipherOne(26 - mainKey);
        return cc.encrypt(input);
    }
}
