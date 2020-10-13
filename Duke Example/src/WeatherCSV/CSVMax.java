package WeatherCSV;
import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class CSVMax {
    public CSVRecord hottestHourInFile (CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            largestSoFar = getLargestOfTwoNumber(largestSoFar, currentRow);
        }
        return largestSoFar;
    }

    public CSVRecord hottestHourInManyDays () {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largest = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = hottestHourInFile(fr.getCSVParser());
            largest = getLargestOfTwoNumber(largest, current);
        }
        return largest;
    }

    public CSVRecord coldestHourInFile (CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser) {
            smallestSoFar = getSmallestOfTwoNumber(smallestSoFar, currentRow);
        }
        return smallestSoFar;
    }

    public String fileWithColdestTemperature () {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallest = null;
        String fileName = "";
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            if (smallest == null) {
                smallest = current;
                fileName = f.getPath();
            } else {
                double currTemp = Double.parseDouble(current.get(("TemperatureF")));
                double smallestTemp = Double.parseDouble(smallest.get(("TemperatureF")));
                if (currTemp < smallestTemp) {
                    smallest = current;
                    fileName = f.getPath();
                }
            }
        }
        return fileName;
    }

    public CSVRecord getLargestOfTwoNumber(CSVRecord largestSoFar, CSVRecord currentRow) {
        CSVRecord largest = largestSoFar;
        if (largest == null) {
            largest = currentRow;
        } else {
            double currTemp = Double.parseDouble(currentRow.get(("TemperatureF")));
            double largestTemp = Double.parseDouble(largest.get(("TemperatureF")));
            if (currTemp > largestTemp) {
                largest = currentRow;
            }
        }
        return largest;
    }

    public CSVRecord getSmallestOfTwoNumber(CSVRecord smallestSoFar, CSVRecord currentRow) {
        CSVRecord smallest = smallestSoFar;
        if (smallest == null) {
            smallest = currentRow;
        } else {
            double currTemp = Double.parseDouble(currentRow.get(("TemperatureF")));
            double smallestTemp = Double.parseDouble(smallest.get(("TemperatureF")));
            if (currTemp > -999 && currTemp < smallestTemp) {
                smallest = currentRow;
            }
        }
        return smallest;
    }

    public void testHottestInDay() {
        FileResource fr = new FileResource("WeatherCSV/2015/weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Max: " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
    }

    public void testHottestInManyDays() {
        CSVRecord largest = hottestHourInManyDays();
        System.out.println("Max: " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
    }

    public void testColdestInDay() {
        FileResource fr = new FileResource("WeatherCSV/2014/weather-2014-05-01.csv");
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Min: " + smallest.get("TemperatureF") + " at " + smallest.get("TimeEDT"));
    }

    public void testFileWithColdestTemperature() {
        String file = fileWithColdestTemperature();
        System.out.println("Coldest temp in file " + file);
        FileResource fr = new FileResource(file);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Min: " + smallest.get("TemperatureF"));
        System.out.println("All the temp on the coldest day were: ");
        for (CSVRecord currentRow : fr.getCSVParser()) {
            System.out.println(currentRow.get("DateUTC") + " " + currentRow.get("TemperatureF"));
        }
    }

    public CSVRecord getLowestHumidBetweenTwo(CSVRecord lowest, CSVRecord currentRow) {
        String currHumStr = currentRow.get("Humidity");
        if (!currHumStr.equals("N/A")) {
            if (lowest == null) {
                lowest = currentRow;
            } else {
                double currHum = Double.parseDouble(currHumStr);
                double lowestHum = Double.parseDouble(lowest.get("Humidity"));
                if (currHum < lowestHum) {
                    lowest = currentRow;
                }
            }
        }
        return lowest;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowest = null;
        for (CSVRecord currentRow : parser) {
            lowest = getLowestHumidBetweenTwo(lowest, currentRow);
        }
        return  lowest;
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource("WeatherCSV/2014/weather-2014-07-22.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at "  + csv.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowest = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            lowest = getLowestHumidBetweenTwo(lowest, current);
        }
        return lowest;
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at "  + csv.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser) {
        double count = 0;
        double sum = 0.0;
        for (CSVRecord currentRow : parser) {
            sum = sum + Double.parseDouble(currentRow.get("TemperatureF"));
            count = count + 1;
        }
        return  sum/count;
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource("WeatherCSV/2013/weather-2013-08-10.csv");
        CSVParser parser = fr.getCSVParser();
        double avrg = averageTemperatureInFile(parser);
        System.out.println("Average Temperature was " + avrg);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double count = 0;
        double sum = 0.0;
        for (CSVRecord currentRow : parser) {
            String currHumStr = currentRow.get("Humidity");
            if (!currHumStr.equals("N/A")) {
                double currHum = Double.parseDouble(currHumStr);
                if (currHum >= value) {
                    sum = sum + Double.parseDouble(currentRow.get("TemperatureF"));
                    count = count + 1;
                }
            }
        }
        if (sum == 0.0) {
            return sum;
        }
        return  sum/count;
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource("WeatherCSV/2013/weather-2013-09-02.csv");
        CSVParser parser = fr.getCSVParser();
        double avrg = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (avrg == 0.0) {
            System.out.println("No temperatures with that humidity");
        }
        System.out.println("Average Temp when high Humidity is " + avrg);
    }

    public static void main(String[] args) {
        CSVMax csvMax = new CSVMax();
//        csvMax.testHottestInDay();
//        csvMax.testHottestInManyDays();
        csvMax.testFileWithColdestTemperature();
//        csvMax.testLowestHumidityInFile();
//        csvMax.testLowestHumidityInManyFiles();
//        csvMax.testAverageTemperatureInFile();
//        csvMax.testAverageTemperatureWithHighHumidityInFile();
    }

}
