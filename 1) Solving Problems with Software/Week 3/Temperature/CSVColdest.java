
/**
 * Write a description of CSVColdest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVColdest {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currRow :  parser) {
            smallestSoFar = getSmallestOfTwo(currRow, smallestSoFar);
        }
        return smallestSoFar;
    }
    
    public void testcoldestDay () {
        FileResource fr = new FileResource("data/2014/weather-2014-05-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF") 
        + " at " + smallest.get("DateUTC"));
    }
    
    public CSVRecord coldestInManyDays() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currRow = coldestHourInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo(currRow, smallestSoFar);
        }
        return smallestSoFar;
    }
    
    public CSVRecord getSmallestOfTwo (CSVRecord currRow, CSVRecord smallestSoFar) {
        if (smallestSoFar == null) {
            smallestSoFar = currRow;
        } else {
            double currentTemp = Double.parseDouble(currRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if (currentTemp < smallestTemp && currentTemp != -9999) {
                smallestSoFar = currRow;
            }
        }
        return smallestSoFar;
    }
    
    public void testColdestInManyDays () {
        CSVRecord smallest = coldestInManyDays();
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF") 
        + " at " + smallest.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        String coldestFile = "";
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currRow = coldestHourInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo(currRow, smallestSoFar);
            coldestFile = f.getPath();
        }
        return coldestFile;
    }
    
    public void testFileWithColdestTemperature() {
        String coldestFileName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file: " + coldestFileName);
        FileResource fr = new FileResource(coldestFileName);
        CSVRecord smallestTemp = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was: " + smallestTemp.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord current : fr.getCSVParser()) {
            System.out.println(current.get("DateUTC") + ": " + current.get("TemperatureF"));
        }
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currRow :  parser) {
            smallestSoFar = getLowestHumidityOfTwo(currRow, smallestSoFar);
        }
        return smallestSoFar;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord smallest = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + smallest.get("Humidity") 
        + " at " + smallest.get("DateUTC"));
    }
    
    public CSVRecord getLowestHumidityOfTwo(CSVRecord currRow, CSVRecord smallestSoFar) {
        if (smallestSoFar == null) {
            smallestSoFar = currRow;
        } else {
            if (!currRow.get("Humidity").equals("N/A")) {
                int currHumid = Integer.parseInt(currRow.get("Humidity"));
                int smallestHumid = Integer.parseInt(smallestSoFar.get("Humidity"));
                if (currHumid < smallestHumid) {
                    smallestSoFar = currRow;
                }
            }
        }
        return smallestSoFar;
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestRow = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currRow = lowestHumidityInFile(fr.getCSVParser());
            smallestRow = getLowestHumidityOfTwo(currRow, smallestRow);
        }
        return smallestRow;
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHumidity.get("Humidity") 
        + " at " + lowestHumidity.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser) {
        double averageTemperature = 0.0;
        int numberOfLines = 0;
        for (CSVRecord currentRow : parser) {
            double currentRowTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            averageTemperature = averageTemperature + currentRowTemp;
            numberOfLines += 1;
        }
        averageTemperature = averageTemperature / numberOfLines;
        return averageTemperature;
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avgTemp);
    }

    public double averageTemperatureWithHighHumidity(CSVParser parser, int value) {
        double result = 0.0;
        int numberOfRecordedTemps = 0;
        for (CSVRecord currentRow : parser) {
            if (!currentRow.get("Humidity").equals("N/A") && Integer.parseInt(currentRow.get("Humidity")) >= value) {
                double currentRowTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                result += currentRowTemp;
                numberOfRecordedTemps += 1;
            }
        }
        result = result / numberOfRecordedTemps;
        return result;
    }

    public void testAverageTemperatureWithHighHumidity() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double avgTemp = averageTemperatureWithHighHumidity(parser, value);
        if (Double.isNaN(avgTemp)) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + avgTemp);
        }
    }
}