package PredictiveText;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

import java.util.ArrayList;

public class MarkovRunner {
    public void runMarkovZero() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovZero markov = new MarkovZero();
		markov.setRandom(1024);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	public void runMarkovOne(String st) {
		MarkovOne markov = new MarkovOne();
		markov.setRandom(365);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	public void runMarkovFour(String st) {
		MarkovFour markov = new MarkovFour();
		markov.setRandom(715);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	public void runMarkovModel(String st) {
		MarkovModel markov = new MarkovModel(7);
		markov.setRandom(953);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}

		ArrayList<Character> chars = markov.getFollows("he");
		System.out.println(chars.size());
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
