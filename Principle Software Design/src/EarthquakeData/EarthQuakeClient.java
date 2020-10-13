package EarthquakeData;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            Location locCurr = qe.getLocation();
            if (locCurr.distanceTo(from)/1000 < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/EarthquakeData/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
//        for (QuakeEntry qe : list) {
//            if (qe.getMagnitude() > 5.0) {
//                System.out.println(qe);
//            }
//        }
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
            System.out.println(qe);
        }
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "src/EarthquakeData/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
//         Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> filtered = filterByDistanceFrom(list, 1000, city);
        for (QuakeEntry qe : filtered) {
            System.out.println(qe);
            System.out.println(qe.getInfo());
        }
        System.out.println(filtered.size());
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry qe : quakeData) {
            double depth = qe.getDepth();
            if (depth > minDepth && depth < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/EarthquakeData/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> lists = parser.read(source);

        ArrayList<QuakeEntry> filtered = filterByDepth(lists, -4000, -2000);
        System.out.println(filtered.size());
        for (QuakeEntry qe : filtered) {
            System.out.println(qe);
        }
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry qe : quakeData) {
            String prhs = qe.getInfo();
            switch (phrase) {
                case "start": {
                    int index = prhs.indexOf(where, 0);
                    if (index == 0) {
                        answer.add(qe);
                    }
                    break;
                }
                case "end": {
                    int index = prhs.indexOf(where, prhs.length() - where.length() - 1);
                    if (index != -1) {
                        answer.add(qe);
                    }
                    break;
                }
                case "any": {
                    int index = prhs.indexOf(where, 0);
                    if (index != -1) {
                        answer.add(qe);
                    }
                    break;
                }
            }
        }
        return  answer;
    }

    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "src/EarthquakeData/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> lists = parser.read(source);

        String where = "Can";
        String phrase = "any";
        ArrayList<QuakeEntry> filtered = filterByPhrase(lists, where, phrase);
        for (QuakeEntry qe : filtered) {
            System.out.println(qe);
        }
        System.out.println("Found " + filtered.size() + " that match " + where + " at " + phrase);
    }
}
