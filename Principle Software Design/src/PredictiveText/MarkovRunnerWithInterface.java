package PredictiveText;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setRandom(seed);
    	markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 101;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }

    public void testHashMap() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
//		String st = "yes-this-is-a-thin-pretty-pink-thistle";
		EfficientMarkovModel emm = new EfficientMarkovModel(5);
		emm.setRandom(531);
		emm.setTraining(st);
		emm.getRandomText(50);
		emm.printHashMapInfo();
	}

	public void testHashMap2() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		long time = System.nanoTime();

		EfficientMarkovModel emm = new EfficientMarkovModel(1);
		runModel(emm, st, 1000, 42);
		long time1 = System.nanoTime() - time;
		System.out.println(time1/10^9);

		MarkovOne mOne = new MarkovOne();
		runModel(mOne, st, 1000, 42);
		long time2 = System.nanoTime() - time1 - time;
		System.out.println(time2/10^9);
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
