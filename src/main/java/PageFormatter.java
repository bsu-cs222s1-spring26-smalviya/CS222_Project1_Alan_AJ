import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PageFormatter {

    public String formatPageTitle(String json){
        String format = "";
        try {
            String jsonKey = new JSONValueFinder(json).nextValue("query").getJSONValue("redirects");
            JSONObject redirectTo = new JSONObject(new JSONArray(jsonKey).get(0).toString());
            format = "Redirecting to " + redirectTo.get("to").toString();
            format = new JSONValueFinder(json).nextValue("query").nextValue("pages").nextValue().getJSONValue("title");
        } catch (JSONException | NullPointerException e) {

        }
        return format;
    }

    public PageRevision[] formatPageRevisions(String json) {
        PageRevision[] revisions = null;
        try {
            String revisionJson = new JSONValueFinder(json)
                    .nextValue("query").nextValue("pages").nextValue().getJSONValue("revisions");
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

    public boolean isValidPage(String json) {
        try {
            boolean keyExists =  new JSONValueFinder(json).nextValue("query").nextValue("pages").keyExists("-1");
            return !keyExists;
        } catch(NullPointerException e) {
            return false;
        }
    }
}
