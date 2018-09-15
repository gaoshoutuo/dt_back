

import Resource.Utils;
import Socket.SsMix;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceDtsj {
public static final String JSON="{\"au\":\"service\",\"other_eng_id\":\"18768349255\",\"filename\":\"1530172244175.png\",\"timestamp\":\"1530172244175\",\"cus_data\":{\"custom_name\":\"小灰灰fhhh\",\"custom_location\":\"帆布包\",\"custom_contacts\":\"他还会\",\"phone_num\":\"cvb\"},\"product_info\":{\"para\":\"dgb\",\"brand\":\"xv\",\"type\":\"vb\",\"power\":\"cbb\"},\"info\":{\"service_pro\":\"吃vv个\",\"error_phon\":\"等一会\",\"handle_error\":\"喝咖啡\",\"fix_reason\":\"都很好\"},\"cost\":{\"Maintenance\":\"哈哈\",\"warr_inner\":\"\",\"warr_out\":\"哈哈哈\",\"labor\":\"哈哈哈\",\"materal\":\"\",\"travel\":\"打广告\",\"transport\":\"好纠结\",\"sum_cost\":\"\"},\"sum\":\"\",\"other_location\":\"杭州\",\"h_custom_id\":\"18768349255\",\"another\":{\"cus_id\":\"18768349255\",\"timestamp\":\"1530172249625\",\"reason\":\"都很好\",\"business\":\"site_service\",\"eng_id\":\"18768349255\",\"eng_name\":\"tuo\"}}\n";
public static String jsonUpsIns=" {\"au\":\"ups_ins\",\"cus_data\":{\"custom_name\":\"成本价\",\"custom_location\":\"\",\"custom_contacts\":\"\",\"phone_num\":\"\"},\"product_info_1\":{\"brand\":\"\",\"type\":\"\",\"power\":\"\",\"pattern\":\"xeh\"},\"product_info_2\":{\"bar_code\":\"\",\"word_way\":\"fhh\",\"kong\":\"\"},\"ups_para_1\":{\"ups_brand\":\"\",\"ups_type\":\" b\",\"ups_num\":\"dhb\",\"kong\":\"cn\"},\"ups_para_2\":{\"ups_group_num\":\"\",\"vol\":\"\",\"cha_vol\":\"顶吃\",\"cha_ec\":\"\"},\"ups_para_3\":{\"vps_id\":\"\",\"vps_ustime\":\"\",\"need_ex\":\"\",\"vps_status\":\"\"},\"electric_1\":{\"in_air\":\"\",\"in_cabie\":\"\",\"zero_vol\":\"dff\",\"conform\":\"\"},\"electric_2\":{\"out_air\":\"\",\"out_cabie\":\"dcv\",\"zero_vol\":\"chh\",\"conform\":\"fbb\"},\"electric_3\":{\"idc_tem\":\"fvb\",\"idc_hum\":\"rgg\",\"idc_clean\":\"\",\"conform\":\"\"},\"run_para_1\":{\"in_vol_a\":\"\",\"in_vol_b\":\"\",\"in_vol_c\":\"\",\"hz\":\"\"},\"run_para_2\":{\"near_vol_a\":\"\",\"near_vol_b\":\"fgg\",\"near_vol_c\":\"cbh\",\"hz\":\"\"},\"run_para_3\":{\"out_vol_a\":\"xgh\",\"out_vol_b\":\"\",\"out_vol_c\":\"\",\"hz\":\"cbg\"},\"run_para_4\":{\"in_ec_a\":\"\",\"in_ec_b\":\"cvvcc\",\"in_ec_c\":\"dgh\",\"kong\":\"\"},\"run_para_5\":{\"out_ec_a\":\"\",\"out_ec_b\":\"\",\"out_ec_c\":\"\",\"kong\":\"\"},\"feature_test_1\":{\"panel\":\"\",\"record\":\"\",\"inverse\":\"先款后货\",\"near\":\"\"},\"feature_test_2\":{\"e_inverse\":\"\",\"emergency\":\"\",\"fix_near\":\"\",\"warning\":\"\"},\"other_eng_id\":\"18768349255\",\"filename\":\"1530171282559.png\",\"timestamp\":\"1530171282559\",\"hard_test\":{\"hard0\":\"ch\",\"hard1\":\"\",\"hard2\":\"bn\",\"hard3\":\"\",\"hard4\":\"cbn\",\"hard5\":\"\",\"hard6\":\"拜拜\",\"hard7\":\"\"},\"other_location\":\"杭州\",\"h_custom_id\":\"18768349255\",\"another\":{\"cus_id\":\"18768349255\",\"timestamp\":\"1530171290451\",\"reason\":\"58\",\"business\":\"ins_ups\",\"eng_id\":\"18768349255\",\"eng_name\":\"tuo\"}}";
    // JSONObject jsonObject=new JSONObject(JSON);
    // System.out.println(jsonObject.getString("au"));http://218.108.146.98:88/
    public static ExecutorService threadPool= Executors.newFixedThreadPool(20);//线程池

    public static void main(String[] args) {
	// write your code here
         //   Ss.sendMessage();
            try {
                while (true){
                   SsMix.sendMessage();
                   // new Thread(runnable).start();
                 /*   if (SsMix.countdownSwitch){//其他类获取不到入口类的对象
                        Utils.countdown();
                        SsMix.countdownSwitch=false;
                    }*/
                }
                //怎么把web 与 socket 结合呢  就是把 这句话放到  主入口的servlet 里面去
            } catch (Exception e) {
                e.printStackTrace();
            }
        //PDFmake.serviceMakePdf(new JSONObject(JSON));

        //PDFmake.upsInsPdfMake(new JSONObject(jsonUpsIns));

    /*  String data[]=  ParseJson.getXmls(ParseJson.getFileFile("vps_test_report.xml"));//site_service
      for(int i=0;i<data.length;i++){
          if(i%100==99)System.out.println();
          if(data[i]!=null)
          System.out.print(i+"..."+data[i]+" ");
      }*/
       // SocketIOClient.sendMessage("{\"au\":\"add\",\"age\":\"88\",\"name\":\"hetao\"}");

       /* JSONObject jsonObject=new JSONObject(JSON);
        System.out.println(jsonObject.length()+""+jsonObject.get);*/

                //这说明了json 也是一种数组型hashmap kv容器
       // PDFmake.testpdf2();
    }
}
