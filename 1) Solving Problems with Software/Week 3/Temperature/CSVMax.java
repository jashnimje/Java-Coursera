
/**
 * Write a description of CSVMax here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currRow :  parser) {
            largestSoFar = getLargestOfTwo(currRow, largestSoFar);
        }
        return largestSoFar;
    }
    
    public void testHottestInDay() {
        FileResource fr = new FileResource("data/2015/weather-2015-01-02.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
        " at " + largest.get("TimeEST"));
    }
    
    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currRow, largestSoFar);
        }
        return largestSoFar;
    }
    
    public CSVRecord getLargestOfTwo (CSVRecord currRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
            largestSoFar = currRow;
        } else {
            double currentTemp = Double.parseDouble(currRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if (currentTemp > largestTemp) {
                largestSoFar = currRow;
            }
        }
        return largestSoFar;
    }
    
    public void testHottestInManyDays () {
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") + 
        " at " + largest.get("DateUTC"));
    }
}
