package CSVNew;

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
        FileResource fs = new FileResource("CSVNew/exports_small.csv");
        CSVParser parser = fs.getCSVParser();
        listExporters(parser, "coffee");
    }

    public String countryinfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            if (country.contains(record.get("Country"))) {
                return country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return "Not Found";
    }

    public void tester() {
        FileResource fr = new FileResource("CSVNew/exportdata.csv");
        CSVParser parser = fr.getCSVParser();
//        System.out.println(countryinfo(parser, "Nauru"));
//        listExportersTwoProducts(parser, "cotton", "flowers");

//        parser = fr.getCSVParser();
//        System.out.println(numberOfExporters(parser, "cocoa"));
//
//        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportItem)) {
                count = count + 1;
            }
        }
        return count;
    }

    public void bigExporters(CSVParser parser, String dollar) {
        for (CSVRecord record : parser) {
            if (record.get("Value (dollars)").length() > dollar.length()) {
                System.out.println(record.get("Country") + ": " + record.get("Value (dollars)"));
            }
        }
    }

    public static void main(String[] args) {
        WhichCountriesExport cntry = new WhichCountriesExport();
//        cntry.whoExportsCoffee();
        cntry.tester();
    }
}
