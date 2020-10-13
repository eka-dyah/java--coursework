package CaesarCipherFinal;

public class TestCaesarCipherOne {
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

    public int breakCaesarCipher(String input) {
        int[] freq = countLetters(input);
        int maxDex = maxIndex(freq);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }

    public void simpleTests() {
        CaesarCipherOne cc = new CaesarCipherOne(18);
        String encrypted = cc.encrypt("Hai ee kk ee");
        int key = breakCaesarCipher(encrypted);

        CaesarCipherOne ccWithKey = new CaesarCipherOne(key);
        String decrypted = ccWithKey.decrypt("Zsa ww cc ww");
        System.out.println(encrypted + "\n" + decrypted);
    }

    public static void main(String[] args) {
        TestCaesarCipherOne tcc = new TestCaesarCipherOne();
        tcc.simpleTests();
    }
}
