package CaesarCipherFinal;

public class TestCaesarCipherTwo {

    public int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k=0; k<message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int indexChar = alph.indexOf(ch);
            if (indexChar != -1) {
                counts[indexChar] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] counts) {
        int max = 0;
        for (int i=0; i< counts.length; i++) {
            if (counts[i] > counts[max]) {
                max = i;
            }
        }
        return max;
    }

    public String halfOfString(String message, int start) {
        StringBuilder str = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            str.append(message.charAt(i));
        }
        return str.toString();
    }

    public int breakCaesarCipher(String input) {
        int[] freq = countLetters(input);
        int maxDex = maxIndex(freq);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }

    public void simpleTest() {
        CaesarCipherTwo cc2 = new CaesarCipherTwo(17, 3);
        String encrypted = cc2.encrypt("Haiiee heheee");

        int key1 = breakCaesarCipher(halfOfString(encrypted, 0));
        int key2 = breakCaesarCipher(halfOfString(encrypted, 1));
        CaesarCipherTwo cc2OtherKey = new CaesarCipherTwo(key1, key2);
        String decrypted = cc2OtherKey.decrypt("Ydzlvh kvkvhv");
        System.out.println(encrypted + " " + decrypted);
    }

    public static void main(String[] args) {
        TestCaesarCipherTwo tct = new TestCaesarCipherTwo();
        tct.simpleTest();
    }
}
