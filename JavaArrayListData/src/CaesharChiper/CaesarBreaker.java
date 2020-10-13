package CaesharChiper;

import edu.duke.FileResource;

public class CaesarBreaker {
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

    public int dkeyRes(String encrypted) {
        int[] freq = countLetters(encrypted);
        int maxDex = maxIndex(freq);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return dkey;
    }

    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int dkey = dkeyRes(encrypted);

        return cc.encrypt(encrypted, 26-dkey);
    }

    public String halfOfString(String message, int start) {
        StringBuilder str = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            str.append(message.charAt(i));
        }
        return str.toString();
    }

    public int getKey(String s) {
        int[] freq = countLetters(s);
        return maxIndex(freq);
    }

    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String halfFirst = halfOfString(encrypted, 0);
        String halfSecond = halfOfString(encrypted, 1);
        int dkey1 = dkeyRes(halfFirst);
        int dkey2 = dkeyRes(halfSecond);
        System.out.println("key1: " + dkey1 + " and key2: " + dkey2);
        return cc.encryptTwoKeys(encrypted, 26-dkey1, 26-dkey2);
    }

    public void testDecrypt() {
        FileResource fr = new FileResource();
        String message = fr.asString();
//        String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
//        String decrypted = decrypt("Yjhi p ithi higxcv lxiw adih du ttttttttttttttttth");
//        System.out.println(decrypted);
//        System.out.println(halfOfString("Qbkm Zgis", 0));
        System.out.println(decryptTwoKeys(message));
    }

    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();
        cb.testDecrypt();
    }
}
