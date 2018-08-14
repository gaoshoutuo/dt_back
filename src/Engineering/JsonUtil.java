package Engineering;

import org.json.JSONObject;

public class JsonUtil {

    public static JSONObject addSingleFieldJson(JSONObject jsonObject,String key,String value){
        jsonObject.put(key,value);
        return jsonObject;
    }
    public static JSONObject makeSingleFieldJson(String key,String value){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(key,value);
        return jsonObject;
    }

}
