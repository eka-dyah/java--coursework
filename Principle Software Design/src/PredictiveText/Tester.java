package PredictiveText;

import edu.duke.FileResource;

public class Tester {
    public void testGetFollows() {
//        String st = "this is a test yes this is a test.";
        MarkovRunner markovRunner = new MarkovRunner();
        markovRunner.runMarkovZero();
    }

    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovRunner markovRunner = new MarkovRunner();
        markovRunner.runMarkovModel(st);
    }

    public void testerMarkovWithInterfase() {
        MarkovRunnerWithInterface markovRunner = new MarkovRunnerWithInterface();
        markovRunner.testHashMap();
    }
}
