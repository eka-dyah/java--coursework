package LogEntry;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("LogEntry/short-test_log");
        logAnalyzer.printAll();
    }

    public void testUniqueIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("LogEntry/weblog2_log");

        int countUniqueIps = logAnalyzer.countUniqueIPs();
        System.out.println("unique ip: " + countUniqueIps);

//        logAnalyzer.printAllHigherThanNum(400);

        ArrayList<String> log = logAnalyzer.uniqueIPVisitsOnDay("Sep 24");
        System.out.println(log.toString());
        System.out.println(log.size());

        int logCount = logAnalyzer.countUniqueIPsInRange(200, 299);
        System.out.println(logCount);
    }

    public void testCounts() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("LogEntry/weblog2_log");

        HashMap<String, Integer> counts = logAnalyzer.countVisitsPerIp();
        System.out.println(counts);

        System.out.println(logAnalyzer.mostNumberVisitsByIP(counts));
        System.out.println(logAnalyzer.iPsMostVisits(counts));
    }

    public void testIpForDays() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("LogEntry/weblog2_log");
        HashMap<String, ArrayList<String>> ipDays = logAnalyzer.iPsForDays();
        System.out.println(logAnalyzer.dayWithMostIPVisits(ipDays));
    }

    public void testIpsMostDay() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("LogEntry/weblog2_log");
        HashMap<String, ArrayList<String>> ipDays = logAnalyzer.iPsForDays();
        System.out.println(logAnalyzer.iPsWithMostVisitsOnDay(ipDays, "Sep 29"));

    }
}
