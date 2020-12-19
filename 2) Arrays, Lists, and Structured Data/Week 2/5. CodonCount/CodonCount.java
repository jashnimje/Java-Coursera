import java.util.*;
import edu.duke.*;

public class CodonCount {
    private HashMap <String, Integer> dnaHash;
    public CodonCount() {
        dnaHash = new HashMap<String, Integer>();
    }

    public void buildCodonMap(int start, String dna) {
        dnaHash.clear();
        int count = 0;
        for (int i=start; i<dna.length()-2; i+=3) {
            String codon = dna.substring(i, i+3);
            if (!dnaHash.containsKey(codon)) {
                dnaHash.put(codon, 1);
                count++;
            } else {
                dnaHash.put(codon, dnaHash.get(codon)+1);
            }
        }
        System.out.println("Reading frame starting with " + start +
            ", results in " + count + " unique codons");
    }

    public String getMostCommonCodon() {
        int maxValue = 0;
        String bestValue = "";
        for (String s : dnaHash.keySet()) {
            if (dnaHash.get(s) > maxValue) {
                maxValue = dnaHash.get(s);
                bestValue = s;
            }
        }
        return "and most common codon is " + bestValue.toUpperCase() + " with count " + maxValue;
    }

    public void printCodonCounts(int start, int end) {
        System.out.println("Counts of codons between "
            + start + " and " + end + " inclusive are:");
        for (String s: dnaHash.keySet()) {
            if (dnaHash.get(s) >= start && dnaHash.get(s) <= end) {
                System.out.println( s.toUpperCase() + "\t" + dnaHash.get(s));
            }
        }
    }

    public void test(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        int start = 1;
        dna = dna.toLowerCase().trim();
        buildCodonMap(start,dna);
        System.out.println(getMostCommonCodon());
        printCodonCounts(2,8);
        
    }
}
