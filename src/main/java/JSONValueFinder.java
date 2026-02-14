import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class JSONValueFinder {

    private JSONObject jsonObject;

    public JSONValueFinder(String json){
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            System.err.println("Something went wrong reading the json string..");
        }
    }

    public JSONValueFinder nextValue(String nextKey){
        try {
            jsonObject = new JSONObject(jsonObject.get(nextKey).toString());
        } catch (JSONException e) {
            System.err.println("The key provided does not exist..");
        }
        return this;
    }

    public JSONValueFinder nextValue(){
        try {
            String nextKey = jsonObject.keys().next().toString();
            jsonObject = new JSONObject(jsonObject.get(nextKey).toString());
        } catch (JSONException e) {
            System.err.println("There are no further keys in this json path..");
        }
        return this;
    }

    public boolean keyExists(String nextKey) {
        try {
            return getNextKeys().contains(nextKey);
        } catch (Exception e) {
            return false;
        }
    }

    public String getJSONValue(String finalKey){
        try {
            return jsonObject.get(finalKey).toString();
        } catch (JSONException e) {
            return null;
        }
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    @SuppressWarnings("rawtypes")
    private List<String> getNextKeys() {
        List<String> temp = new ArrayList<>();
        Iterator keys = jsonObject.keys();
        while(keys.hasNext()) {
            temp.add(keys.next().toString());
        }
        return temp;
    }
}
