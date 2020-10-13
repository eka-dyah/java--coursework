package NGrams;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import PredictiveText.MarkovWordTwo;
import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){
            String st = markov.getRandomText(size); 
            printOut(st); 
        }
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordOne markovWord = new MarkovWordOne();
        runModel(markovWord, st, 120, 139);
    }

    public void runMarkov2() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovWordTwo markovWord = new MarkovWordTwo();
        runModel(markovWord, st, 50, 832);
    }

    public void runMarkovWord() {
        FileResource fr = new FileResource();
        String st = fr.asString();
//        String st = "this is just a test yes this is just a simple test\"";
        st = st.replace('\n', ' ');
        MarkovWord markovWord = new MarkovWord(3);
        runModel(markovWord, st, 50, 643);
    }

    public void testHashMap() {
//        FileResource fr = new FileResource();
//        String st = fr.asString();
        String st = "this is a test yes this is really a test yes a test this is wow";
        st = st.replace('\n', ' ');
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2);
        runModel(markovWord, st, 50, 42);
        markovWord.printHashMapInfo();
    }

    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
//        String st = "this is just a test yes this is just a simple test";
        st = st.replace('\n', ' ');
//        MarkovWord markovWord = new MarkovWord(2);
        EfficientMarkovWord efficientMarkovWord = new EfficientMarkovWord(3);
        long start = System.nanoTime()/(10^9);


        runModel(efficientMarkovWord, st, 50, 371);
        System.out.println(System.nanoTime() - start);

//        runModel(markovWord, st, 100, 42);
//        System.out.println(System.nanoTime() - start);



    }

    public void runMarkovSimple() {
        String st = "this is just a test yes this is a simple test";
        st = st.replace('\n', ' ');
        MarkovWordTwo markovWord = new MarkovWordTwo();
        runModel(markovWord, st, 200);
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}

/*
* you must return the medium with your written explanation. The
* agreement violates the law of the state applicable to this agreement,
*
* The last part of the music, when all the instruments were played
* ministers, and there was order below heaven. King Wu[80] said,
*
* 30045 (3, 371)
*
* 376 (2, 65)
*
* */