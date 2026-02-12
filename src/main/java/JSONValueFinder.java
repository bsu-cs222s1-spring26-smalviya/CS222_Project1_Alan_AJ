import org.json.JSONException;
import org.json.JSONObject;

public class JSONValueFinder {

    private JSONObject jsonObject;

    public JSONValueFinder(String json) throws JSONException {
        jsonObject = new JSONObject(json);
    }

    public JSONValueFinder nextKey(String nextKey) throws JSONException {
        jsonObject = new JSONObject(jsonObject.get(nextKey).toString());
        return this;
    }

    public JSONValueFinder nextKey() throws JSONException {
        String nextKey = jsonObject.keys().next().toString();
        jsonObject = new JSONObject(jsonObject.get(nextKey).toString());
        return this;
    }

    public Object getJSONValue(String finalKey) throws JSONException {
        return jsonObject.get(finalKey);
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
