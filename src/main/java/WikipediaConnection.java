import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.Connection;

public class WikipediaConnection {

    static void main(String[] args) {
        WikipediaConnection newConnection = new WikipediaConnection();
        System.out.println(newConnection.readJsonAsString());
    }

    private URLConnection connection;

    public void establishConnectionToWikipedia(String keyWord) {
        try {
            String encodedUrlString = ProgramSettings.WIKILINK +
                    URLEncoder.encode(keyWord, Charset.defaultCharset()) + "&rvprop=timestamp" +
                    URLEncoder.encode("|",Charset.defaultCharset()) + "user&rvlimit=4&redirects";
            URI uri = new URI(encodedUrlString);
            connection = uri.toURL().openConnection();
            connection.setRequestProperty("User-Agent",
                    "FirstProject/0.1 (academic use; https://example.com)");
            connection.connect();
        } catch (IOException | URISyntaxException e) {
            System.out.println("Error");
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