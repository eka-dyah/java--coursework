package LogEntry;

import java.util.*;

import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<>();
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line : fr.lines()) {
            LogEntry logEntry = WebLogParser.parseEntry(line);
            records.add(logEntry);
        }
    }

    public HashMap<String, Integer> countVisitsPerIp() {
        HashMap<String, Integer> counts = new HashMap<>();
        for (LogEntry le : records) {
            String ip = le.getIpAddress();
            if (counts.containsKey(ip)) {
                int count = counts.get(ip);
                counts.put(ip, count + 1);
            } else {
                counts.put(ip, 1);
            }
        }
        return counts;
    }

    public int countUniqueIPs() {
        HashMap<String, Integer> counts = countVisitsPerIp();
        return counts.size();
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public void printAllHigherThanNum(int num) {
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                System.out.println(le);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> log = new ArrayList<>();
        String monthName = someday.substring(0, 3);
        String day = someday.substring(3);
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            if (date.contains(monthName) && date.contains(day)) {
                if (!log.contains(le.getIpAddress())) {
                    log.add(le.getIpAddress());
                }
            }
        }
        return log;
    }

    public int countUniqueIPsInRange(int low, int high) {
        int count = 0;
        ArrayList<String> ip = new ArrayList<>();
        if (low <= high) {
            for (LogEntry le : records) {
                int statusCode = le.getStatusCode();
                if (statusCode >= low && statusCode <= high) {
                    if (!ip.contains(le.getIpAddress())) {
                        ip.add(le.getIpAddress());
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int max = 0;
        for (int count : counts.values()) {
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        int max = mostNumberVisitsByIP(counts);
        ArrayList<String> ip = new ArrayList<>();
        for (String ipCount : counts.keySet()) {
            int count = counts.get(ipCount);
            if (count == max) {
                ip.add(ipCount);
            }
        }
        return ip;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> daysIp = new HashMap<>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            String day = date.substring(4, 10);
            ArrayList<String> ips;
            if (daysIp.containsKey(day)) {
                ips = daysIp.get(day);
            } else {
                ips = new ArrayList<>();
            }
            ips.add(le.getIpAddress());
            daysIp.put(day, ips);
        }

        return daysIp;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipDays) {
        int max = 0;
        String day = "";
        for (String dayIp : ipDays.keySet()) {
            int counts = ipDays.get(dayIp).size();
            if (counts > max) {
                max = counts;
                day = dayIp;
            }
        }
        return day;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> rec, String day) {
        ArrayList<String> ipDays = rec.get(day);
        HashMap<String, Integer> ipCounts = new HashMap<>();
        for(String ip : ipDays) {
            if (ipCounts.containsKey(ip)) {
                int count = ipCounts.get(ip);
                ipCounts.put(ip, count + 1);
            } else {
                ipCounts.put(ip, 1);
            }
        }
        return iPsMostVisits(ipCounts);
    }
}
