package StringsFirstAssignments;
import edu.duke.*;

public class Part4 {
    public static void main(String[] args) {
        URLResource fr = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String line : fr.lines()) {
            String lineLower = line.toLowerCase();
            int pos = lineLower.indexOf("youtube.com");
            if (pos == -1) {
                continue;
            }
            int start = line.lastIndexOf("\"", pos);
            if (start == -1) {
                continue;
            }
            int stop = line.indexOf("\"", pos + 1);
            if (stop == -1) {
                continue;
            }
            String ytb = line.substring(start+1, stop);
            System.out.println(ytb);
        }
    }
}
