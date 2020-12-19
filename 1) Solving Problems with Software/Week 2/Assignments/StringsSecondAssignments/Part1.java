
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    
    public void testFindStopCodon() {
        String dna;
        int result;
        // DNA with genes
        dna = "xxxyyyzzzATGzzzxxxyyyTAA";
        result = findStopCodon(dna, 0, "TAA");
        System.out.println("DNA Strands stop codon Index is: " + result);
        // DNA without genes
        dna = "xxxyyyzzzzzzxxxyyy";
        result = findStopCodon(dna, 0, "TAA");
        System.out.println("DNA Strands stop codon Index is: " + result);
        // Other stopCodon
        dna = "xxxyyyzzzATGzzzxxxyyyTAATAG";
        result = findStopCodon(dna, 0, "TAG");
        System.out.println("DNA Strands stop codon Index is: " + result);
    }
    
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        if (minIndex == -1) {
            return "";
        }
        
        return dna.substring(startIndex, minIndex+3);
    }
    
    public void testFindGene() {
        String str, result;
        // DNA with no "ATG"
        str = "xxxyyyzzzxxxyyyzzzTAATAGTGA";
        result = findGene(str, 0);
        System.out.println("DNA Strand Gene: "+ result);
        
        // DNA with "ATG"
        str = "xxxyyyzzzxxxATGyyyzzzTAAxxx";
        result = findGene(str, 0);
        System.out.println("DNA Strand Gene: "+ result);
        
        // DNA with "ATG" and multiple valid stop codons
        
        str = "xxxyyyzzzATGxxxyyyzzzTAATAGTGA";
        result = findGene(str, 0);
        System.out.println("DNA Strand Gene: "+ result);
        
        //  DNA with "ATG" and no valid stop codons
        str = "xxxyyyzzzxxxyyyzzz";
        result = findGene(str, 0);
        System.out.println("DNA Strand Gene: "+ result);
        
        // TGA
        str = "xxxyyyzzzATGxxxyyyzzzTGATAGTAA";
        result = findGene(str, 0);
        System.out.println("DNA Strand Gene: "+ result);
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currGene = findGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }  
    }
}
