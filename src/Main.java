import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String textUrl = args[0];
        String pattern = ".*http://.*";
        Processing processing = new Processing();

        boolean matches = Pattern.matches(pattern, textUrl);
        if (matches == false) {
            System.out.println("Error : Only accepted HTTP://URL");
        } else {
            if (processing.getUrlStatus(textUrl) != 200) {
                System.out.println("This URL - " + textUrl + "does not contain the HTML content");
            } else {
                Map<String, Integer> sortedMap = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
                sortedMap.putAll(processing.getHtmlContent(textUrl));
                for (String s : sortedMap.keySet()) {
                    System.out.println(s + "/" + sortedMap.get(s));
                }
            }
        }
    }
}
