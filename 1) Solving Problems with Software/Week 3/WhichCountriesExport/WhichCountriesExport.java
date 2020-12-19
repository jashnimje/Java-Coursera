
/**
 * Write a description of WhichCountriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportOfInterest)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }        
    }
    
    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String ans = countryInfo(parser, "Nauru");
        System.out.println(ans);
        parser = fr.getCSVParser();
    }
    
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            String c = record.get("Country");
            String exports = record.get("Exports");
            String value = record.get("Value (dollars)");
            if (c.contains(country)) {
                return c + " : " + exports + " : " + value; 
            }
        }  
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String country = record.get("Country");
            String export =record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                System.out.println(country);
            }
        }
    }
    
    public void testListExports() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String country = record.get("Country");
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                count++;
            }
        }
        return count;        
    }
    
    public void testNumberOfExporters() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int count = numberOfExporters(parser, "cocoa");
        System.out.println(count);
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String country = record.get("Country");
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length() ) {
                System.out.println(country + " " + value );
            }
        }
    }
    
    public void testBigExporters() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
}
