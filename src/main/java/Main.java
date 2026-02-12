import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Main {
    static void main(String[] args) {
        Main main = new Main();
        try {
            main.runProgram();
        }  catch (JSONException e) {
            System.err.println("Issue with JSON: " + e);
        }
    }

    public void runProgram() throws JSONException {
        WikipediaInputConsole console = new WikipediaInputConsole();
        WikipediaConnection connection = new WikipediaConnection();

        String searchName = console.getSearchNameInput();
        connection.establishConnectionToWikipedia(searchName);

        String connectedJson = connection.readJsonAsString();
        String title = formatPageTitle(connectedJson);
        PageRevision[] revisions = formatPageRevisions(connectedJson);

        WikipediaPage page = new WikipediaPage(title, revisions);
        System.out.println(page.printPageInformation());
    }

    public String formatPageTitle(String json) throws JSONException {
        String format = "";
        try {
            String jsonKey = new JSONValueFinder(json).nextKey("query").getJSONValue("redirects").toString();
            JSONObject redirectTo = new JSONObject(new JSONArray(jsonKey).get(0).toString());
            format = "Redirecting to " + redirectTo.get("to").toString();
        } catch (JSONException | NullPointerException e) {
            format = new JSONValueFinder(json)
                    .nextKey("query").nextKey("pages").nextKey().getJSONValue("title").toString();
        }
        return format;
    }

    public PageRevision[] formatPageRevisions(String json) throws JSONException {
        String revisionJson = new JSONValueFinder(json)
                .nextKey("query").nextKey("pages").nextKey().getJSONValue("revisions").toString();

        PageRevision[] revisions = null;
        try {
            JSONArray jsonArray = new JSONArray(revisionJson);
            revisions = new PageRevision[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject revisionObject = new JSONObject(jsonArray.get(i).toString());
                String userValue = revisionObject.getString("user");
                String timeStampValue = revisionObject.getString("timestamp");

                revisions[i] = new PageRevision(userValue, timeStampValue);
            }
        } catch(JSONException e) {
            System.err.println("Unable to read revisions..");
            System.exit(0);
        }
        return revisions;
    }
}
