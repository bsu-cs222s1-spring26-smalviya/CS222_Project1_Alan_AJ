import org.json.JSONException;
import org.json.JSONObject;

public class JSONValueFinder {

    private JSONObject jsonObject;

    public JSONValueFinder(String json) throws JSONException {
        jsonObject = new JSONObject(json);
    }

    public JSONValueFinder nextKey(String nextKey) {
        try {
            jsonObject = new JSONObject(jsonObject.get(nextKey).toString());
        } catch(JSONException e) {
            System.err.println("A path in the JSON does not exist");
        }
        return this;
    }

    public JSONValueFinder nextKey() {
        try {
            String nextKey = jsonObject.keys().next().toString();
            jsonObject = new JSONObject(jsonObject.get(nextKey).toString());
        } catch(JSONException e) {
            System.err.println("A path in the JSON does not exist");
        }
        return this;
    }

    public Object getJSONValue(String finalKey) {
        Object object = null;

        try {
            object = jsonObject.get(finalKey);
        } catch(JSONException e) {
            System.err.println("A path in the JSON does not exist");
        }

        return object;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
