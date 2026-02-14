import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;

public class WikipediaConnection {

    private URLConnection connection;

    public boolean establishConnectionToWikipedia(String keyWord, int numberOfRedirects) {
        try {
            String encodedUrlString = ProgramSettings.WIKILINK +
                    URLEncoder.encode(keyWord, Charset.defaultCharset()) + "&rvprop=timestamp" +
                    URLEncoder.encode("|",Charset.defaultCharset()) + "user&rvlimit=" + numberOfRedirects + "&redirects";

            URI uri = new URI(encodedUrlString);
            connection = uri.toURL().openConnection();
            connection.setRequestProperty("User-Agent", "FirstProject/0.1 (academic use; https://example.com)");
            connection.connect();
            return true;
        } catch (IOException | URISyntaxException e) {
            System.out.println("Error");
            return false;
        }
    }

    public String readJsonAsString() {
        try {
            return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
        } catch(IOException e) {
            return "Error Retrieving json";
        }
    }
}