package Util;

import Bean.JsonBean;
import Para.TestAction;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 解析json类  build 构建模式
 */
public class ParseJson {

    private static void setMap(HashMap map,String key,Object value){
        map.put(key,value);
    }

    /**
     * {"device_work_pattern":"","cus_sign":"123","error_time":"","my_sign":"123","handle_error":"",
     * "cost":{"warr_inner":"","materal":"","sum_cost":"","Maintenance":"","transport":"","travel":"","warr_out":"","labor":""},
     * "device_id":"","error_phon":"","fix_time":"","fix_suggest":"","kong":"","device_power":"","device_brand":"","device_t":"","fix_reason":"","phone_number":"","location":"","error_analysis":"","contacts":""}
     {"device_work_pattern":"","cus_sign":"123","error_time":"","my_sign":"123","handle_error":"","cost":{"warr_inner":"","materal":"","sum_cost":"","Maintenance":"","transport":"","travel":"","warr_out":"","labor":""},"device_id":"","error_phon":"","fix_time":"","fix_suggest":"","kong":"","device_power":"","device_brand":"","device_t":"","fix_reason":"","phone_number":"","location":"","error_analysis":"","contacts":""}

     */

    public JsonBean jsonobjToObj(JSONObject object,String[]array){
        JsonBean jsonBean=new JsonBean();
        for(int i=0;i<array.length;i++){
           jsonBean.arr[i]= object.getString(array[i]);
        }
        return jsonBean;
    }//不需要这个 直接插入jsonobj 到o       仍然是jsonobject好用

    public static String[] jsonobjToArr(JSONObject object,String[]array){
       String []arr=new String[array.length];
        for(int i=0;i<array.length;i++){
            arr[i]= object.getString(array[i]);
        }
        return arr;

    }//不需要这个 直接插入jsonobj 到o       仍然是jsonobject好用



    public static JSONObject getSubjson(JSONObject jsonObject,String subData){
        return jsonObject.getJSONObject(subData);

    }



    /*public static HashMap<String,Object> fixUpsParse(String jsonData){
        HashMap<String,Object> map=new HashMap<>();
        JSONObject json=new JSONObject(jsonData);
        setMap(map,"device_work_pattern",json.getString("device_work_pattern"));

        return map;

    }*/

    public static JSONObject fixUpsParse(String jsonData){
        JSONObject json=new JSONObject(jsonData);
        return json;
    }




    /*public static String[] parseAndArray2(String xmlData){

        String []in=new String[999];
        try {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser=factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventtype=xmlPullParser.getEventType();
            int i=0;
            StringBuilder sb=new StringBuilder();
            while (eventtype!=xmlPullParser.END_DOCUMENT){
                String nodename=xmlPullParser.getName();
                //Log.e("西溪湿地",nodename+"1");
                if ("name".equals(nodename)){
                    in[Integer.parseInt(xmlPullParser.getAttributeValue(null,"class"))]=xmlPullParser.nextText();//类似哈希表
                }
                if ("root".equals(nodename)&&i>2){
                    return in;//类似哈希表
                }
                xmlPullParser.next();
                i++;

            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }*/

    public static String[] getXmls(File file){//10033class33100
        String []data=new String[999];
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);
            Element bookstore = document.getRootElement();
            Iterator storeit = bookstore.elementIterator();// 写复杂xml json 的万能解析

           // bookList = new ArrayList<Book>();//System.out.println(attribute.getData().toString()+"33"+attribute.getName()+"33"+attribute.getValue());10033class33100
            while(storeit.hasNext()){

                //book = new Book();
                Element element = (Element) storeit.next();
                //遍历bookElement的属性
               // Attribute attribute = element.attribute("class");
                Attribute attribute = element.attribute("class");
                if(attribute.getName().equals("class")){
                  //System.out.println( element.getStringValue());
                    data[Integer.parseInt(attribute.getValue())]=element.getStringValue()+":";
                }
              /*  for(Attribute attribute : attributes){
                    //System.out.println(attribute.getData().toString()+"33"+attribute.getName()+"33"+attribute.getValue());10033class33100
                    //System.out.println(attribute.getText());100
                    System.out.println();
                    if(attribute.getName().equals("class")){
                        Element child = (Element) bookit.next();
                        data[Integer.parseInt(attribute.getValue())] = bookit.next().get;//System.out.println(id);
                       // book.setId(Integer.parseInt(id));
                    }
                }*/



           /*     while(bookit.hasNext()){
                    Element child = (Element) bookit.next();
                    String nodeName = child.getName();
                    if(nodeName.equals("name")){
                        //System.out.println(child.getStringValue());
                        String name = child.getStringValue();
                        book.setName(name);
                    }
                }
*/
            }
        } catch (DocumentException e) {

            e.printStackTrace();
        }


        return data;

    }

    public static String getFileStr()  {
        FileInputStream is= null;
        StringBuilder sb=new StringBuilder();
        try {
            String relativelyPath=System.getProperty("user.dir");
            System.out.println(relativelyPath);
            is = new FileInputStream(new File(relativelyPath+"\\src\\Para\\"+"air_condition_inspection.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte b[]=new byte[1024];int len=0;
        try {
            while ((len=is.read(b))!=-1){
                sb.append(new String(b,0,len));

            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return sb.toString();

    }

    public static File getFileFile(String filename)  {
        String relativelyPath=System.getProperty("user.dir");
        return new File(relativelyPath+"\\src\\Para\\"+filename);
    }






}
