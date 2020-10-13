package BabyNames;

import edu.duke.*;
import org.apache.commons.csv.*;

import java.io.File;

public class BabyNames {
    public void printNames(FileResource fr) {
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn >= 100) {
                System.out.println("Name " + rec.get(0) +
                        " Gender " + rec.get(1) +
                        " Num born " + rec.get((2)));
            }
        }
    }

    public void testPrintNames() {
        FileResource fr = new FileResource("BabyNames/example-small.csv");
        printNames(fr);
    }

    public void totalBirths (FileResource fr) {
        int totalBirth = 0;
        int boyBirth = 0;
        int girlBirth = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirth += numBorn;
            if (rec.get(1).equals("M")) {
                boyBirth += numBorn;
            } else if (rec.get(1).equals("F")) {
                girlBirth += numBorn;
            }

        }
        System.out.println("Total birth = " + totalBirth);
        System.out.println("Total boy birth = " + boyBirth);
        System.out.println("Total girl birth = " + girlBirth);
    }

    public void testTotalBirths () {
        FileResource fr = new FileResource("BabyNames/us_babynames_by_year/yob1900.csv");
        totalBirths(fr);
    }

    public int getRank (int year, String name, String gender, FileResource fr) {
        if (fr == null) {
            fr = new FileResource("BabyNames/us_babynames_by_year/yob" + year + ".csv");
        }
        int boyRank = 0;
        int girlRank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals("M")) {
                boyRank += 1;
                String nameResult = rec.get(0).toLowerCase();
                if (nameResult.equals(name.toLowerCase()) && rec.get(1).equals(gender.toUpperCase())) {
                    return boyRank;
                }
            } else if (rec.get(1).equals("F")) {
                girlRank += 1;
                String nameResult = rec.get(0).toLowerCase();
                if (nameResult.equals(name.toLowerCase()) && rec.get(1).equals(gender.toUpperCase())) {
                    return girlRank;
                }
            }
        }
        return -1;
    }

    public void testRank() {
        int rank = getRank(1980, "Mia", "F", null);
        System.out.println(rank);
    }

    public String getName (int year, int rank, String gender) {
        FileResource fr = new FileResource("BabyNames/us_babynames_by_year/yob" + year + ".csv");
        int boyRank = 0;
        int girlRank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals("M")) {
                boyRank += 1;
                String nameResult = rec.get(0);
                if (boyRank == rank && rec.get(1).equals(gender.toUpperCase())) {
                    return nameResult;
                }
            } else if (rec.get(1).equals("F")) {
                girlRank += 1;
                String nameResult = rec.get(0);
                if (girlRank == rank && rec.get(1).equals(gender.toUpperCase())) {
                    return nameResult;
                }
            }
        }
        return "NO NAME";
    }

    public void testName() {
        String name = getName(1980, 350, "F");
        System.out.println(name);
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender, null);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if born in " + newYear);
    }

    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 0;
        int highestAtYear = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String yearStr = f.getName().substring(3, 7);
            int year = Integer.parseInt(yearStr);
            int rank = getRank(year, name, gender, fr);

            if (highestRank == 0 && rank != -1) {
                highestRank = rank;
                highestAtYear = year;
                continue;
            }
            if (rank < highestRank && year > highestAtYear && rank != -1) {
                highestRank = rank;
                highestAtYear = year;
            }
        }

        if (highestAtYear == 0) {
            return -1;
        }
        return highestAtYear;
    }

    public void testYearOfHighestRank() {
        int year = yearOfHighestRank("Mich", "M");
        System.out.println(year);
    }

    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double sum = 0.0;
        int count = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String yearStr = f.getName().substring(3, 7);
            int year = Integer.parseInt(yearStr);
            double rank = getRank(year, name, gender, fr);

            if (rank != -1) {
                sum += rank;
                count += 1;
            }
        }

        if (sum == 0) {
            return -1.0;
        }
        return sum / count;
    }

    public void testGetAverageRank() {
        System.out.println(getAverageRank("Robert", "M"));
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource("BabyNames/us_babynames_by_year/yob" + year + ".csv");
        int rankNameRank = getRank(year, name, gender, fr);
        if (rankNameRank == -1) {
            return rankNameRank;
        }
        int totalBirth = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                int rankOther = getRank(year, rec.get(0), rec.get(1), fr);
                if (rankOther < rankNameRank && rankOther != -1) {
                    totalBirth += Integer.parseInt(rec.get(2));
                }
                if (rankOther == rankNameRank) {
                    break;
                }
            }
        }
        return totalBirth;
    }

    public void testGetTotalBirthsRankedHigher() {
        int totalBirthHigherUp = getTotalBirthsRankedHigher(1990, "Emily", "F");
        System.out.println(totalBirthHigherUp);
    }

    public static void main(String[] args) {
        BabyNames babyNames = new BabyNames();
//        babyNames.testPrintNames();
//        babyNames.testTotalBirths();
        babyNames.testRank();
        babyNames.testName();
        babyNames.whatIsNameInYear("Susan", 1972, 2014, "F");
        babyNames.whatIsNameInYear("Owen", 1974, 2014, "M");
//        babyNames.testYearOfHighestRank();
//        babyNames.testGetAverageRank();
        babyNames.testGetTotalBirthsRankedHigher();
    }

}
