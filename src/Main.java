

import Resource.PDFmake;
import Socket.SocketIOClient;
import Socket.Ss;
import Util.ParseJson;
import org.json.JSONObject;


import java.io.IOException;

public class Main {
public static final String JSON="{\"au\":\"add\",\"age\":\"88\",\"name\":\"hetao\"}";
public static String jsonUpsIns=" {\"feature_test_1\":{\"inverse\":\"\",\"record\":\"\",\"near\":\"\",\"panel\":\"\"},\n" +
        "     \"feature_test_2\":{\"e_inverse\":\"\",\"emergency\":\"\",\"warning\":\"\",\"fix_near\":\"\"},\n" +
        "     \"electric_1\":{\"in_cabie\":\"\",\"conform\":\"\",\"in_air\":\"\",\"zero_vol\":\"\"},\n" +
        "     \"electric_3\":{\"idc_clean\":\"\",\"conform\":\"\",\"idc_tem\":\"\",\"idc_hum\":\"\"},\n" +
        "     \"run_para_2\":{\"hz\":\"\",\"near_vol_c\":\"\",\"near_vol_a\":\"\",\"near_vol_b\":\"\"},\n" +
        "     \"electric_2\":{\"out_air\":\"\",\"out_cabie\":\"\",\"zero_vol\":\"\",\"conform\":\"\"},\n" +
        "     \"run_para_1\":{\"hz\":\"\",\"in_vol_a\":\"\",\"in_vol_b\":\"\",\"in_vol_c\":\"\"},\n" +
        "     \"product_info_1\":{\"pattern\":\"\",\"power\":\"\",\"type\":\"\",\"brand\":\"\"},\n" +
        "     \"ups_para_3\":{\"vps_ustime\":\"\",\"vps_status\":\"\",\"need_ex\":\"\",\"vps_id\":\"\"},\n" +
        "     \"product_info_2\":{\"bar_code\":\"\",\"word_way\":\"\",\"kong\":\"\"},\n" +
        "     \"ups_para_2\":{\"vol\":\"\",\"cha_vol\":\"\",\"ups_group_num\":\"\",\"cha_ec\":\"\"},\n" +
        "     \"ups_para_1\":{\"ups_type\":\"\",\"ups_num\":\"\",\"ups_brand\":\"\",\"kong\":\"\"},\n" +
        "     \"cus_data\":{\"custom_contacts\":\"\",\"custom_name\":\"\",\"custom_location\":\"\",\"phone_num\":\"\"},\n" +
        "     \"run_para_5\":{\"out_ec_c\":\"\",\"out_ec_b\":\"\",\"out_ec_a\":\"\",\"kong\":\"\"},\n" +
        "     \"hard_test\":{\"hard2\":\"\",\"hard1\":\"\",\"hard0\":\"\",\"hard7\":\"\",\"hard6\":\"\",\"hard5\":\"\",\"hard4\":\"\",\"hard3\":\"\"},\n" +
        "     \"run_para_4\":{\"in_ec_c\":\"\",\"in_ec_b\":\"\",\"in_ec_a\":\"\",\"kong\":\"\"},\n" +
        "     \"run_para_3\":{\"out_vol_c\":\"\",\"out_vol_b\":\"\",\"hz\":\"\",\"out_vol_a\":\"\"}}";
    // JSONObject jsonObject=new JSONObject(JSON);
    // System.out.println(jsonObject.getString("au"));http://218.108.146.98:88/
    public static void main(String[] args) {
	// write your code here
         //   Ss.sendMessage();
          /*  try {
                while (true)
                    Ss.sendMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }*/

        //PDFmake.makeTestPdf();

        //PDFmake.upsInsPdfMake(new JSONObject(jsonUpsIns));

      String data[]=  ParseJson.getXmls(ParseJson.getFileFile("site_service.xml"));//site_service
      for(int i=0;i<data.length;i++){
          if(i%100==99)System.out.println();
          if(data[i]!=null)
          System.out.print(i+"..."+data[i]+" ");
      }
       // SocketIOClient.sendMessage("{\"au\":\"add\",\"age\":\"88\",\"name\":\"hetao\"}");

       /* JSONObject jsonObject=new JSONObject(JSON);
        System.out.println(jsonObject.length()+""+jsonObject.get);*/

                //这说明了json 也是一种数组型hashmap kv容器
       // PDFmake.testpdf2();
    }
}
