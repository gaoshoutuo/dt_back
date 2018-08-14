package Resource;

import Bean.JsonBean;
import Entity.FixTestInsHistoryEntity;
import org.json.JSONObject;

import java.sql.Timestamp;

public class Utils {


    public static String[] cutJson(String o){

        int length=o.length();
        System.out.println(length);
        String a=o.substring(0,length/2);
        String b=o.substring(length/2 ,length);//左闭右开
        return new String[]{a,b};
    }
    /**
     *  ano.put("cus_id",mapService.get("h_custom_id"));
     *                     ano.put("timestamp",System.currentTimeMillis()+"");
     *                     ano.put("reason",mapService.get("h_reason"));
     *                     ano.put("business", HandlerFinal.BUSINESS_STR[7]);
     *                     ano.put("eng_id",HandlerFinal.userId);
     *                     ano.put("eng_name",HandlerFinal.userName);  固定拿着六个的 another
     */
    public static String[] parseAnother(JSONObject jsonObject){
        JSONObject ano=jsonObject.getJSONObject("another");
        String cusId=ano.getString("cus_id");
        String timestamp=ano.getString("timestamp");
        String reason=ano.getString("reason");
        String business=ano.getString("business");
        String eng_id=ano.getString("eng_id");
        String eng_name=ano.getString("eng_name");
        return new String[]{cusId,timestamp,reason,business,eng_id,eng_name};
    }

    public static String[] parseJsonArray(JSONObject jsonObject){

        return null;
    }
    public static void makeSixKV(String[]k ,FixTestInsHistoryEntity fie){
        fie.setUserId(k[0]);
        fie.setDateHistory(new Timestamp(System.currentTimeMillis()));
        fie.setTextHistory(k[2]);
        fie.setBusiness(k[3]);
        fie.setEngId(k[4]);
        fie.setHuman(k[5]);
    }
    public static JSONObject JsonObjFac(String[]key,String[]value){
        JSONObject jsonObject=new JSONObject();
        for (int i=0;i<key.length;i++){
            jsonObject.put(key[i],value[i]);
        }
        return jsonObject;
    }

    //  这里来给主scoket 分担一些负担

    /**
     * 解析资产分给所有的子模块
     * @param object
     */
    public static void parseSub(JSONObject object){

    }


}
