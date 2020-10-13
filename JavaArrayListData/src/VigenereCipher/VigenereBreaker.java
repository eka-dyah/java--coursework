package VigenereCipher;

import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slices = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i+=totalSlices) {
            slices.append(message.charAt(i));
        }
        return slices.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i=0; i<klength; i++) {
            CaesarCracker cc = new CaesarCracker(mostCommon);
            String slices = sliceString(encrypted, i, klength);
            int getKey = cc.getKey(slices);
            key[i] = getKey;
        }
        return key;
    }

    public void breakVigenere () {
        DirectoryResource dictionaryFiles = new DirectoryResource();
        HashMap<String, HashSet<String>> dictionary = new HashMap<>();
        for (File dct : dictionaryFiles.selectedFiles()) {
            FileResource fr = new FileResource(dct);
            HashSet<String> dict = readDictionary(fr);
            dictionary.put(dct.getName(), dict);
        }
        FileResource fr = new FileResource();
        breakForAllLangs(fr.asString(), dictionary);
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> words = new HashSet<>();
        for (String line : fr.lines()) {
            String lineLower = line.toLowerCase();
            words.add(lineLower);
        }
        return words;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        String[] splitMessage = message.split("\\W");
        int count = 0;
        for (String words : splitMessage) {
            if(dictionary.contains(words.toLowerCase())) {
                count += 1;
            }
        }
        return count;
    }

    public int breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int count = 0;
        int keyLength = 0;
        for (int i = 1; i < 100; i++) {
            char common = mostCommonCharIn(dictionary);
            int[] keys = tryKeyLength(encrypted, i, common);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int countWords = countWords(decrypted, dictionary);
            if (countWords > count) {
                count = countWords;
                keyLength = i;
            }
        }
        return keyLength;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        int[] counts = new int[26];
        String alphabet = "abcdefghiklmnopqrstuvwxyz";
        for (String words : dictionary) {
            for (Character ch : words.toCharArray()) {
                int index = alphabet.indexOf(ch);
                if (index != -1) {
                    counts[index] += 1;
                }
            }
        }

        int max = 0;
        char ch = 'a';
        for (int i=0; i<counts.length; i++) {
            if (counts[i] > max) {
                max = counts[i];
                ch = alphabet.charAt(i);
            }
        }

        return ch;
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        String decrypted = "";
        int count = 0;
        String lg = "";
        char common = 'a';
        int[] keyUsed = new int[0];

        for (String language : languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            char mostCommon = mostCommonCharIn(dictionary);
            int keyLength = breakForLanguage(encrypted, dictionary);
            int[] keys = tryKeyLength(encrypted, keyLength, mostCommon);

            VigenereCipher vc = new VigenereCipher(keys);
            String resultDecrypted = vc.decrypt(encrypted);
            int countWords = countWords(resultDecrypted, dictionary);

            if (countWords > count) {
                count = countWords;
                lg = language;
                decrypted = resultDecrypted;
                common = mostCommon;
                keyUsed = keys;
            }
        }

        System.out.println("Words match: " + count);
        System.out.println("Languange: " + lg);
        System.out.println("Most common char: " + common);
        System.out.println("Keylength " + Arrays.toString(keyUsed));
        System.out.println(decrypted);

    }
    
}
