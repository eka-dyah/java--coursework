package EarthquakeData;

public class PhraseFilter implements Filter {
    private final String type;
    private final String title;

    public PhraseFilter(String types, String titles) {
        type = types;
        title = titles;
    }

    public boolean satisfies(QuakeEntry qe) {
        String prhs = qe.getInfo();
        switch (type) {
            case "start": {
                int index = prhs.indexOf(title, 0);
                if (index == 0) {
                    return true;
                }
                break;
            }
            case "end": {
                int index = prhs.indexOf(title, prhs.length() - title.length());
                if (index != -1) {
                    return true;
                }
                break;
            }
            case "any": {
                int index = prhs.indexOf(title, 0);
                if (index != -1) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public String getName() {
        return "Phrase";
    }
}
