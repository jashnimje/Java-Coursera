
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";        
        }
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex == -1) {
            return "";
        }
        String finalString = dna.substring(startIndex, stopIndex+3);
        int length = finalString.length();
        if (length % 3 == 0) {
            return finalString;
        }
        return "";
    }
    
    public void testSimpleGene() {
        //DNA with no "ATG" and no "TAA"
        String dna1 = "ACGTGTGAAA";
        System.out.println("String DNA: " + dna1);
        String result1 = findSimpleGene(dna1);
        System.out.println("String after Method: " + result1);
        
        //DNA with no "ATG" or "TAA"
        String dna2 = "ATGGCCAA";
        System.out.println("String DNA: " + dna2);
        String result2 = findSimpleGene(dna2);
        System.out.println("String after Method: " + result2);
        
        //DNA with ATG, TAA multiple of 3 (a gene)
        String dna3 = "GCATGGCACAATAAAA";
        System.out.println("String DNA: " + dna3);
        String result3 = findSimpleGene(dna3);
        System.out.println("String after Method: " + result3);
        
        //DNA with ATG, TAA not a multiple of 3
        String dna4 = "GCATGGCAGCCAATAAAA";
        System.out.println("String DNA: " + dna4);
        String result4 = findSimpleGene(dna4);
        System.out.println("String after Method: " + result4);
    }
}
