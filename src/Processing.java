import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Processing {

    public int getUrlStatus(String textUrl) {
        URL url = null;
        try {
            url = new URL(textUrl);
            HttpURLConnection http = null;
            try {
                http = (HttpURLConnection) url.openConnection();
                int status = http.getResponseCode();
                if (status == 200) {
                    return status;
                }
            } catch (IOException e) {
                //e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public HashMap<String, Integer> getHtmlContent(String textUrl) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;
        ArrayList<String> myArrayList = new ArrayList<String>();
        ArrayList<String> myArray = new ArrayList<String>();

        try {
            url = new URL(textUrl);
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                String textHtml[] = line.split("<[^<>]*>|,|:|;|-|\\.| ");

                for (int i = 0; i < textHtml.length; i++) {
                    if (textHtml[i].length() == 0) {
                    } else {
                        myArrayList.add(textHtml[i]);
                    }
                }
            }

            for (String s : myArrayList) {
                if (!s.matches("^[^0-9|\\p{Punct}]*$")) {
                } else {
                    myArray.add(s);
                }
            }

            List<String> list = myArray;
            HashMap<String, Integer> hm = new HashMap<String, Integer>();
            Integer am;
            int index = 0;

            for (String i : list) {
                index++;
                am = hm.get(i);
                hm.put(i, am == null ? 1 : am + 1);
            }
            System.out.println("The total number of words: " + index);
            return hm;

        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
            }
        }
        return null;
    }
}
