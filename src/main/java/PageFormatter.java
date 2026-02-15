import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PageFormatter {

    public String formatPageTitle(String json){
        String format = "";
        try {
            boolean hasRedirect = new JSONValueFinder(json).nextValue("query").keyExists("redirects");
            if(hasRedirect) {
                String redirectKey = new JSONValueFinder(json).nextValue("query").getJSONValue("redirects");
                JSONObject redirectTo = new JSONObject(new JSONArray(redirectKey).get(0).toString());
                format = "Redirected to " + redirectTo.get("to").toString();
            }
            else {
                format = new JSONValueFinder(json).nextValue("query").nextValue("pages").nextValue().getJSONValue("title");
            }
        } catch (JSONException | NullPointerException e) {
            System.err.println("Unable to read the title..");
            System.exit(0);
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
