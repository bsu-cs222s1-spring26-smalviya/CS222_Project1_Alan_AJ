import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Main {
    static void main(String[] args) {
        Main main = new Main();
        try {
            main.runProgram();
        } catch (JSONException e) {
            System.err.println("Issue with JSON");
        }
    }

    public void runProgram() throws JSONException {
        WikipediaInputConsole console = new WikipediaInputConsole();
        WikipediaConnection connection = new WikipediaConnection();

        String searchName = console.getSearchNameInput();

        connection.establishConnectionToWikipedia(searchName);

        String revisionJson = new JSONValueFinder(connection.readJsonAsString())
                .nextKey("query").nextKey("pages")
                .nextKey().getJSONValue("revisions").toString();

        JSONArray jsonArray = new JSONArray(revisionJson);

        PageRevision[] revisions = new PageRevision[jsonArray.length()];

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject revisionObject = new JSONObject(jsonArray.get(i).toString());
            String userValue = revisionObject.getString("user");
            String timeStampValue = revisionObject.getString("timestamp");

            revisions[i] = new PageRevision(userValue, timeStampValue);
        }

        WikipediaPage page = new WikipediaPage(revisions);
        System.out.println(page.showAllRevisions());
    }
}
