
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        String s1 = "this is a test yes this is a test.";
        markov.setTraining(s1);
        ArrayList<String> result = markov.getFollows("es");
        System.out.println("Size: " + result.size());
        System.out.println(result);
    }
    
    public void testGetFollowsWithFile() {
        MarkovOne markov = new MarkovOne();
        FileResource fr  = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        markov.setTraining(st);
        ArrayList<String> result = markov.getFollows("he");
        System.out.println("Size: " + result.size());
        System.out.println(result);
    }
}
