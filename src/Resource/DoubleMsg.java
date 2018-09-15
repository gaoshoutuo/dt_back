package Resource;

import Entity.FixMessageEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class DoubleMsg {//感觉写代码不用面向对象呢


    public JSONObject isHaveOfflineMsg(JSONObject jsonObject){//给登录用
        JSONObject json=new JSONObject();
        String type=jsonObject.getString("type");
        String userId=jsonObject.getString("cus_id");
        List<FixMessageEntity> list= FixMessageUtil.getInstance().query2(userId,type);
        if (list.size()==0){
            json.put("isnull",0);
        }else{
            json.put("isnull",1);
        }
        return json;
    }

    public JSONObject getDoubleMsg(JSONObject jsonObject){//
        JSONObject json=new JSONObject();
        JSONArray array=new JSONArray();
        String type=jsonObject.getString("type");
        List<FixMessageEntity> list=null;
        if (type.equals("0")){//查询未完成
            //我要未完成的文件名
            String userId=jsonObject.getString("cus_id");
            list= FixMessageUtil.getInstance().query2(userId,"0");
            for (int i=0;i<list.size();i++){
                //json.put("timerecord",123);
                FixMessageEntity fme=list.get(i);
                String preJson=fme.getJson1()+fme.getJson2();
                JSONObject preJsonObj=new JSONObject(preJson);
                String firstFileName=preJsonObj.getString("first_filename");
                JSONObject subJson=new JSONObject();
                subJson.put("timerecord",fme.getTimeRecord());
                subJson.put("idcname",fme.getIdcName());
                subJson.put("business",fme.getBusinessType());
                subJson.put("engname",fme.getEngName());
                subJson.put("filename",firstFileName);
                array.put(subJson);
            }
            json.put("array",array);
            json.put("reply","double_msg_offline_reply");
        }else if (type.equals("3")){//查询工程师历史
            String userId=jsonObject.getString("eng_id");
            list= FixMessageUtil.getInstance().query3(userId,"2");
            for (int i=0;i<list.size();i++){
                //json.put("timerecord",123);
                FixMessageEntity fme=list.get(i);
                String lastFileName=fme.getBlank3();
                JSONObject subJson=new JSONObject();
                subJson.put("timerecord",fme.getTimeRecord());
                subJson.put("idcname",fme.getIdcName());
                subJson.put("business",fme.getBusinessType());
                subJson.put("engname",fme.getEngName());
                subJson.put("last_filename",lastFileName);
                array.put(subJson);
            }
            json.put("array",array);
            json.put("reply","double_msg_history_reply");
            json.put("type","eng");
        }else if (type.equals("2")) {//查询客户历史

            String userId=jsonObject.getString("cus_id");
            list= FixMessageUtil.getInstance().query2(userId,"2");
            for (int i=0;i<list.size();i++){
                //json.put("timerecord",123);
                FixMessageEntity fme=list.get(i);
                String lastFileName=fme.getBlank3();
                JSONObject subJson=new JSONObject();
                subJson.put("timerecord",fme.getTimeRecord());
                subJson.put("idcname",fme.getIdcName());
                subJson.put("business",fme.getBusinessType());
                subJson.put("engname",fme.getEngName());
                subJson.put("last_filename",lastFileName);
                array.put(subJson);
            }
            json.put("array",array);
            json.put("reply","double_msg_history_reply");
            json.put("type","cus");
        }

        if (list.size()==0){
            return null;
        }
        return json;
    }

    public JSONObject getEngHistory(JSONObject jsonObject) {//
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        String type = jsonObject.getString("type");
        String cusId = jsonObject.getString("eng_id");
        List<FixMessageEntity> list= FixMessageUtil.getInstance().query3(cusId,type);
        for (int i=0;i<list.size();i++){
            FixMessageEntity fme=list.get(i);
            String lastFileName=fme.getBlank3();
            JSONObject subJson=new JSONObject();
            subJson.put("timerecord",fme.getTimeRecord());
            subJson.put("idcname",fme.getIdcName());
            subJson.put("business",fme.getBusinessType());
            subJson.put("engname",fme.getEngName());
            subJson.put("last_filename",lastFileName);
            array.put(subJson);
        }
        json.put("array",array);
        json.put("reply","double_msg_history_reply");
        return json;
    }

    public void dmSendMsg(JSONObject jsonObject, Socket socket){
        DoubleMsg dm=new DoubleMsg();
        JSONObject dmJson=dm.getDoubleMsg(jsonObject);
        System.out.println(dmJson.toString()+"1223");
        if(dmJson==null){
            System.out.println(dmJson.toString()+"1223");
        }else{
            try {

                socket.getOutputStream().write(dmJson.toString().getBytes("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getJson12(JSONObject jsonObject,Socket socket){
        FixMessageEntity queryTs= FixMessageUtil.getInstance().query1(jsonObject.getString("timestamp"));
        JSONObject obj=new JSONObject();
        obj.put("reply","json_1_2_reply");
        obj.put("json",queryTs.getJson1()+queryTs.getJson2());
        obj.put("buss_type",queryTs.getBusinessType());
        try {
            socket.getOutputStream().write(obj.toString().getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
