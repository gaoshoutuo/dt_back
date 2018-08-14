package Resource;

import Bean.JsonBean;
import Util.ParseJson;
import Util.TimeAndDateUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;

public class PDFmake {
    //表头填充
    private static BaseColor bc = new BaseColor(255, 255, 255);
    public static com.itextpdf.text.Font[] fonts;
    public static String xmlDataUpsIns,xmlDataUpsFix,xmlDataUpsTest,xmlDataAirIns,xmlDataService,xmlDataInstall;
    public static String dataUpsIns[],dataUpsFix[],dataUpsTest[],dataAirIns[],dataService[],dataInstall[];

    /**
     * 第一种是 内部类结构  第二种是子类继承结构
     */

   public BaseFont bfChinese;
   public Font chinese24,chinese18,chinese12,chinese16;

    /**
     * 确立字体
     */
    public void initFont(){
        try {
            bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//中文字体
            chinese24=new Font(bfChinese,24,Font.NORMAL);
            chinese18=new Font(bfChinese,18,Font.NORMAL);
            chinese12=new Font(bfChinese,12,Font.NORMAL);
            chinese16=new Font(bfChinese,16,Font.NORMAL);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *  确立pdf 主要组成部分  标题 列表 段落 表 文件 图片等。
     *  chuck cell table parag prhase
     */

    private void mainElement(){

    }

    /**
     * 初始化文档
     */
    private void initDocu(){
        Document document = new Document(PageSize.A4.rotate());//A4大小的文档
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:/ftp/test.pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document.open();
        //document.add();
       // PdfWriter.getInstance(document, os);
    }

    public static String testpdf2(){

        try
        {
            OutputStream os = new FileOutputStream(new File("d:/ftp/test001.pdf"));
           // response.setContentType("application/pdf");
            //Document document = new Document(PageSize.A4.rotate());
            Document document = new Document(PageSize.A4);
           PdfWriter pw= PdfWriter.getInstance(document, os);
            pw.open();
            //设置字体
//            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            BaseFont bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/simhei.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
            com.itextpdf.text.Font FontChinese24 = new com.itextpdf.text.Font(bfChinese, 24, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese, 18, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font FontChinese16 = new com.itextpdf.text.Font(bfChinese, 16, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font FontChinese12 = new com.itextpdf.text.Font(bfChinese, 12, com.itextpdf.text.Font.NORMAL);
            com.itextpdf.text.Font FontChinese11Bold = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font FontChinese11 = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.ITALIC);
            com.itextpdf.text.Font FontChinese11Normal = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.NORMAL);

            document.open();
            //test
            Paragraph pg_bt = new Paragraph("纳税人风险明细数据查询报告",FontChinese18);
            pg_bt.setAlignment(Element.ALIGN_CENTER);
            document.add(pg_bt);

            document.add(new Paragraph("报告使用说明：",FontChinese18));

            document.add(new Chunk("1、报告使用注意保密，不得将报告直接转发给被应对纳税人，以及其他无关单位和人员；", FontChinese11Normal));
            document.add(Chunk.NEWLINE);
            document.add(new Chunk("2、报告中的外部交换数据，以及部分涉及发票、契税等数据，是以纳税名称为条件的查询，可能不完整；", FontChinese11Normal));
            document.add(Chunk.NEWLINE);
            document.add(new Chunk("3、报告中使用的外部数据本身可能存在错误、遗漏；", FontChinese11Normal));
            document.add(Chunk.NEWLINE);
            document.add(new Chunk("4、报告中的税款统计口径使用入库期，如标明为所属期，对跨年税款，按所跨越年度均等拆分。", FontChinese11Normal));
            Paragraph pg_xbt01 = new Paragraph("第一章 企业资产税收风险分析",FontChinese18);
            pg_xbt01.setAlignment(Element.ALIGN_CENTER);
            document.add(pg_xbt01);
            Paragraph pg_xbt02 = new Paragraph("第一节 企业房产、土地税收风险分析",FontChinese18);
            pg_xbt02.setAlignment(Element.ALIGN_CENTER);
            document.add(pg_xbt02);

            document.add(new Paragraph("一、分析项目：本项分析主要涉及纳税人的以下经济行为：",FontChinese18));

            document.add(new Chunk("（一）使用自有房产或无租使用他人房产", FontChinese11Normal));
            document.add(Chunk.NEWLINE);
            document.add(new Chunk("（二）使用自有土地或免税土地产", FontChinese11Normal));
            document.add(Chunk.NEWLINE);
            document.add(new Chunk("（三）自建或委托建设房产", FontChinese11Normal));
            document.add(Chunk.NEWLINE);
            document.add(new Chunk("（四）购置房产", FontChinese11Normal));
            document.add(Chunk.NEWLINE);
            document.add(new Chunk("（五）购置土地", FontChinese11Normal));
            document.add(Chunk.NEWLINE);
            document.add(new Chunk("（六）使用集体土地", FontChinese11Normal));
            document.add(Chunk.NEWLINE);
            document.add(new Chunk("（七）出租房产、土地", FontChinese11Normal));
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("二、数据查询",FontChinese18));
            document.add(new Paragraph("（一）税源登记信息",FontChinese18));
            document.add(new Paragraph("1、房土税源登记表（旧表）",FontChinese18));

            //table7
            PdfPTable pt01 = new PdfPTable(11);
            BaseColor lightGrey01 = new BaseColor(0xCC,0xCC,0xCC);
            int widthpt01[] = {20,20,20,20,20,20,15,20,20,15,15};
            pt01.setWidths(widthpt01);
            PdfPCell cell01 = new PdfPCell(new Paragraph("登记来源",FontChinese11Bold));
            BaseColor bc = new BaseColor(102, 204, 255);
            cell01.setBackgroundColor(bc);
            PdfPCell cell02 = new PdfPCell(new Paragraph("产权证号",FontChinese11Bold));
            cell02.setBackgroundColor(bc);
            PdfPCell cell03 = new PdfPCell(new Paragraph("房屋坐落",FontChinese11Bold));
            cell03.setBackgroundColor(bc);
            PdfPCell cell04 = new PdfPCell(new Paragraph("房产原值/土地面积",FontChinese11Bold));
            cell04.setBackgroundColor(bc);
            cell04.setNoWrap(false);
            PdfPCell  cell05= new PdfPCell(new Paragraph("应税房产原值/应税土地面积",FontChinese11Bold));
            cell05.setBackgroundColor(bc);
            PdfPCell cell06 = new PdfPCell(new Paragraph("应纳税额",FontChinese11Bold));
            cell06.setBackgroundColor(bc);
            PdfPCell cell07 = new PdfPCell(new Paragraph("面积",FontChinese11Bold));
            cell07.setBackgroundColor(bc);
            PdfPCell cell08 = new PdfPCell(new Paragraph("起时间",FontChinese11Bold));
            cell08.setBackgroundColor(bc);
            PdfPCell cell09 = new PdfPCell(new Paragraph("止时间",FontChinese11Bold));
            cell09.setBackgroundColor(bc);
            PdfPCell cell10 = new PdfPCell(new Paragraph("年租金",FontChinese11Bold));
            cell10.setBackgroundColor(bc);
            PdfPCell cell_11 = new PdfPCell(new Paragraph("承租人/出租人",FontChinese11Bold));
            cell_11.setBackgroundColor(bc);


            //表格高度
            //cell01.setFixedHeight(25);

            //水平居中
            cell01.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell02.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell03.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell04.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell05.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell06.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell07.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell08.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell09.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell_11.setHorizontalAlignment(Element.ALIGN_CENTER);
            //垂直居中
            cell01.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell02.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell03.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell04.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell05.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell06.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell07.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell08.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell09.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell10.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell_11.setHorizontalAlignment(Element.ALIGN_MIDDLE);

            //边框颜色
            cell01.setBorderColor(lightGrey01);
            cell02.setBorderColor(lightGrey01);
            cell03.setBorderColor(lightGrey01);
            cell04.setBorderColor(lightGrey01);
            cell05.setBorderColor(lightGrey01);
            cell06.setBorderColor(lightGrey01);
            cell07.setBorderColor(lightGrey01);
            cell08.setBorderColor(lightGrey01);
            cell09.setBorderColor(lightGrey01);
            cell10.setBorderColor(lightGrey01);
            cell_11.setBorderColor(lightGrey01);
            //去掉左右边框
            /**
             cell71.disableBorderSide(8);
             **/
            pt01.addCell(cell01);
            pt01.addCell(cell02);
            pt01.addCell(cell03);
            pt01.addCell(cell04);
            pt01.addCell(cell05);
            pt01.addCell(cell06);
            pt01.addCell(cell07);
            pt01.addCell(cell08);
            pt01.addCell(cell09);
            pt01.addCell(cell10);
            pt01.addCell(cell_11);
            document.add(pt01);

            PdfPTable pt02 = new PdfPTable(11);
            BaseColor lightGrey02 = new BaseColor(0xCC,0xCC,0xCC);
            int widthpt02[] ={20,20,20,20,20,20,15,20,20,20,20};
            pt02.setWidths(widthpt02);
            PdfPCell cell001 = new PdfPCell(new Paragraph("自用房屋登记",FontChinese11Bold));
          //  cell001.setColspan(2);
            PdfPCell cell002 = new PdfPCell(new Paragraph("无产权证",FontChinese11Bold));
            PdfPCell cell003 = new PdfPCell(new Paragraph("秦淮路19号",FontChinese11Bold));
            PdfPCell cell004 = new PdfPCell(new Paragraph("7464583.31",FontChinese11Bold));
            PdfPCell cell005 = new PdfPCell(new Paragraph("7464583.31",FontChinese11Bold));
            PdfPCell cell006 = new PdfPCell(new Paragraph("62702.5",FontChinese11Bold));
            PdfPCell cell007 = new PdfPCell(new Paragraph("1000",FontChinese11Bold));
            PdfPCell cell008 = new PdfPCell(new Paragraph("2005-01-01",FontChinese11Bold));
            PdfPCell cell009 = new PdfPCell(new Paragraph("",FontChinese11Bold));
            PdfPCell cell100 = new PdfPCell(new Paragraph("",FontChinese11Bold));
            PdfPCell cell011 = new PdfPCell(new Paragraph("",FontChinese11Bold));


            //表格高度
            cell001.setFixedHeight(25);
            cell002.setFixedHeight(25);
            cell003.setFixedHeight(25);
            cell004.setFixedHeight(25);
            cell005.setFixedHeight(25);
            cell006.setFixedHeight(25);
            cell007.setFixedHeight(25);
            cell008.setFixedHeight(25);
            cell009.setFixedHeight(25);
            cell100.setFixedHeight(25);
            cell011.setFixedHeight(25);
            //水平居中
            cell001.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell002.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell003.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell004.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell005.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell006.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell007.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell008.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell009.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell100.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell011.setHorizontalAlignment(Element.ALIGN_CENTER);
            //垂直居中
            cell001.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell002.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell003.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell004.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell005.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell006.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell007.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell008.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell009.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell100.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            cell011.setHorizontalAlignment(Element.ALIGN_MIDDLE);

            //边框颜色
            cell001.setBorderColor(lightGrey02);
            cell002.setBorderColor(lightGrey02);
            cell003.setBorderColor(lightGrey02);
            cell004.setBorderColor(lightGrey02);
            cell005.setBorderColor(lightGrey02);
            cell006.setBorderColor(lightGrey02);
            cell007.setBorderColor(lightGrey02);
            cell008.setBorderColor(lightGrey02);
            cell009.setBorderColor(lightGrey02);
            cell100.setBorderColor(lightGrey02);
            cell011.setBorderColor(lightGrey02);
            pt02.addCell(cell001);
            pt02.addCell(cell002);
            pt02.addCell(cell003);
            pt02.addCell(cell004);
            pt02.addCell(cell005);
            pt02.addCell(cell006);
            pt02.addCell(cell007);
            pt02.addCell(cell008);
            pt02.addCell(cell009);
            pt02.addCell(cell100);
            pt02.addCell(cell011);
            document.add(pt02);
            pw.close();
            document.close();
            os.flush();
            os.close();

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * 一些小工具 软件是橡皮擦擦出来的 还是一步到位的
     * 擦出来可能会在中途发现起始缺少前瞻性二不断选择很愚蠢的做法 一步到位 则是需要抓到许许多多的数据情报 然后非常消耗脑力的做出来，非常脆落
     */
    /**
     * 表  边框颜色 单元格大小 背景填充
     */
    public static PdfPTable initTable(int length,int []width){
        PdfPTable pt=new PdfPTable(length);//12  宽16高30
        try {
            pt.setWidths(width);

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return pt;
    }

    /**
     * 表格数据是一行一行加的
     */
    public static PdfPTable initHeadCell(String[]iu,PdfPTable pt){//居中的
        int colspan=12/iu.length;
        for(int i=0;i<iu.length;i++){//此处注意到底有几条数据.setAlignment(Element.ALIGN_CENTER)
            Paragraph paragraph=new Paragraph(iu[i], fonts[3]);
         /*   paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setAlignment(Element.ALIGN_MIDDLE);*/
            PdfPCell cell=new PdfPCell(new Paragraph(iu[i], fonts[3]));
            cell.setUseAscender(true);
            cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE); //垂直居中
            cell.setFixedHeight(30);
            cell.setColspan(colspan);
            pt.addCell(cell);
        }
        return pt;
    }//不是二维数组  一条一条数据添加 然后最后才for循环

    //单层数组
    public static PdfPTable initCell(String[]iu,PdfPTable pt){//普通的
        int colspan=12/iu.length;
        for(int i=0;i<iu.length;i++){//此处注意到底有几条数据.setAlignment(Element.ALIGN_CENTER)
            Paragraph paragraph=new Paragraph(iu[i], fonts[3]);
         /*   paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setAlignment(Element.ALIGN_MIDDLE);*/
            PdfPCell cell=new PdfPCell(new Paragraph(iu[i], fonts[3]));
          /*  cell.setUseAscender(true);
            cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
*/
            cell.setFixedHeight(30);
            cell.setColspan(colspan);
            pt.addCell(cell);
        }
        return pt;
    }//不是二维数组  一条一条数据添加 然后最后才for循环

    public static PdfPTable initCellWithHeight(String[]iu,PdfPTable pt){//高度为80
        int colspan=12/iu.length;
        for(int i=0;i<iu.length;i++){//此处注意到底有几条数据.setAlignment(Element.ALIGN_CENTER)
            Paragraph paragraph=new Paragraph(iu[i], fonts[3]);
            PdfPCell cell=new PdfPCell(new Paragraph(iu[i], fonts[3]));
            cell.setFixedHeight(80);
            cell.setColspan(colspan);
            pt.addCell(cell);
        }
        return pt;
    }//不是二维数组  一条一条数据添加 然后最后才for循环

    public static void jsonMakeArray(JsonBean jsonBean){

    }

    public static void makeImage(String imageName,PdfPTable pt){

        try {
            Image imageCus  = Image.getInstance("d:/ftp/"+imageName+".jpg");
            PdfPCell cellCus=new PdfPCell(imageCus);
            cellCus.setFixedHeight(80);
            cellCus.setColspan(6);
            pt.addCell(cellCus);
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 将pdf元素颗粒化 phrase chuck paragraph title table cell
     * 3 4 夹心  客户名称：xxxxxxx  V/A
     * {"device_work_pattern":"","cus_sign":"123","error_time":"","my_sign":"123","handle_error":"","cost":{"warr_inner":"","materal":"","sum_cost":"","Maintenance":"","transport":"","travel":"","warr_out":"","labor":""},"device_id":"","error_phon":"","fix_time":"","fix_suggest":"","kong":"","device_power":"","device_brand":"","device_t":"","fix_reason":"","phone_number":"","location":"","error_analysis":"","contacts":""}
     */

    //字体
    public static void initFontArray(){
        //设置字体
//            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        fonts=new  com.itextpdf.text.Font[5];
        BaseFont bfChinese = null;
        try {
            bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/simhei.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
            com.itextpdf.text.Font firstTitle24 = new com.itextpdf.text.Font(bfChinese, 24, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font serondTitle18 = new com.itextpdf.text.Font(bfChinese, 18, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font text_cell = new com.itextpdf.text.Font(bfChinese, 14, com.itextpdf.text.Font.NORMAL);
            //com.itextpdf.text.Font text_cell = new com.itextpdf.text.Font(bfChinese, 14, com.itextpdf.text.Font.NORMAL);
            com.itextpdf.text.Font cell_10 = new com.itextpdf.text.Font(bfChinese, 10, com.itextpdf.text.Font.NORMAL);
            com.itextpdf.text.Font cell_12 = new com.itextpdf.text.Font(bfChinese, 12, com.itextpdf.text.Font.NORMAL);

          /*  com.itextpdf.text.Font FontChinese16 = new com.itextpdf.text.Font(bfChinese, 16, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font FontChinese11Bold = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font FontChinese11 = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.ITALIC);
            com.itextpdf.text.Font FontChinese11Normal = new com.itextpdf.text.Font(bfChinese, 11, com.itextpdf.text.Font.NORMAL);*/
            fonts[0]=firstTitle24;
            fonts[1]=serondTitle18;
            fonts[2]=text_cell;
            fonts[3]=cell_10;
            fonts[4]=cell_12;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String makeTestPdf(){
        OutputStream os = null;
        long timestamp=TimeAndDateUtil.makeBootstamp();
        String fileName="d:/ftp/"+timestamp+".pdf";
        try {
            os = new FileOutputStream(new File("d:/ftp/"+timestamp+".pdf"));
            Document document = new Document(PageSize.A4);
            PdfWriter pw= PdfWriter.getInstance(document, os);
            pw.open();
            document.open();
            if(fonts==null)initFontArray();
            //标题
            Paragraph upsTestTitle = new Paragraph("ups测试报告",fonts[1]);
            upsTestTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(upsTestTitle);
            document.add(Chunk.NEWLINE);

            PdfPTable pdfPTable=initTable(12,new int[]{16,16,16,16,16,16,16,16,16,16,16,16});
             /* initCell(new String[]{"你若","安好","便是","晴天"},pdfPTable);
            document.add(pdfPTable); 这样 就会有两行 今天java出bug  可以无限加入的*/

            //
            initCell(new String[]{"今天","java","出了","bug"},pdfPTable);
            initCell(new String[]{"你若","安好","便是","晴天"},pdfPTable);
            initCell(new String[]{"体系架构","网络","数据结构","操作系统"},pdfPTable);
            document.add(pdfPTable);


            document.close();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // response.setContentType("application/pdf");
        //Document document = new Document(PageSize.A4.rotate());
        return fileName;
    }

    public static void sandWish(String []top,String []mid,String []bottom,PdfPTable pdfPTable){//json 虽然也有长度 但是没办法  有些kong 长度 只有三 没法取
        if(mid.length==4)
        initCell(new String[]{top[0]+mid[0]+bottom[0],top[1]+mid[1]+bottom[1],top[2]+mid[2]+bottom[2],top[3]+mid[3]+bottom[3]},pdfPTable);
        else if(mid.length==3)
            initCell(new String[]{top[0]+mid[0]+bottom[0],top[1]+mid[1]+bottom[1],top[2]+mid[2]+bottom[2]},pdfPTable);
    }

    public static void addImage(Document document,String filePath){
        try {
           // PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("d:/ftp/test2.pdf"));
            Image img = Image.getInstance(filePath);
            img.setAbsolutePosition((PageSize.POSTCARD.getWidth() - img.getScaledWidth()) / 2,
                    (PageSize.POSTCARD.getHeight() - img.getScaledHeight()) / 2);
            document.add(img);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    /**
     * 是方法 不是类决定pdf样式
     * 检修 overhaul   维修 fix  安装 install
     * 先做吧 东风么那么快来
     {"feature_test_1":{"inverse":"","record":"","near":"","panel":""},
     "feature_test_2":{"e_inverse":"","emergency":"","warning":"","fix_near":""},
     "electric_1":{"in_cabie":"","conform":"","in_air":"","zero_vol":""},
     "electric_2":{"out_air":"","out_cabie":"","zero_vol":"","conform":""},
     "electric_3":{"idc_clean":"","conform":"","idc_tem":"","idc_hum":""},
     "product_info_1":{"pattern":"","power":"","type":"","brand":""},
     "product_info_2":{"bar_code":"","word_way":"","kong":""},
     "ups_para_1":{"ups_type":"","ups_num":"","ups_brand":"","kong":""},
     "ups_para_2":{"vol":"","cha_vol":"","ups_group_num":"","cha_ec":""},
     "ups_para_3":{"vps_ustime":"","vps_status":"","need_ex":"","vps_id":""},
     "cus_data":{"custom_contacts":"","custom_name":"","custom_location":"","phone_num":""},
     "hard_test":{"hard2":"","hard1":"","hard0":"","hard7":"","hard6":"","hard5":"","hard4":"","hard3":""},
     "run_para_1":{"hz":"","in_vol_a":"","in_vol_b":"","in_vol_c":""},
     "run_para_2":{"hz":"","near_vol_c":"","near_vol_a":"","near_vol_b":""},
     "run_para_3":{"out_vol_c":"","out_vol_b":"","hz":"","out_vol_a":""}}
     "run_para_4":{"in_ec_c":"","in_ec_b":"","in_ec_a":"","kong":""},
     "run_para_5":{"out_ec_c":"","out_ec_b":"","out_ec_a":"","kong":""},
     */

    //ups 巡检
    public static String upsInsPdfMake(JSONObject jsonObject){
        // initpara
        OutputStream os = null;
        long timestamp=TimeAndDateUtil.makeBootstamp();
        String fileName="d:/ftp/"+timestamp+".pdf";

        //init xml arr
        dataUpsIns=  ParseJson.getXmls(ParseJson.getFileFile("vps_inspection_report.xml"));


        try {
            os = new FileOutputStream(new File("d:/ftp/"+timestamp+".pdf"));
            Document document = new Document(PageSize.A4);
            PdfWriter pw= PdfWriter.getInstance(document, os);
            pw.open();
            document.open();
            if(fonts==null)initFontArray();
            //标题
            Paragraph upsTestTitle = new Paragraph("ups检修报告",fonts[1]);
            upsTestTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(upsTestTitle);
            document.add(Chunk.NEWLINE);

            PdfPTable pdfPTable=initTable(12,new int[]{16,16,16,16,16,16,16,16,16,16,16,16});
             /* initCell(new String[]{"你若","安好","便是","晴天"},pdfPTable);
            document.add(pdfPTable); 这样 就会有两行 今天java出bug  可以无限加入的*/

            //
           /* initCell(new String[]{"今天","java","出了","bug"},pdfPTable);
            initCell(new String[]{"你若","安好","便是","晴天"},pdfPTable);
            initCell(new String[]{"体系架构","网络","数据结构","操作系统"},pdfPTable);
            document.add(pdfPTable);*/
            JSONObject cusData=ParseJson.getSubjson(jsonObject,"cus_data");
           //JSONObject cusData=jsonObject.getJSONObject("cus_data");
           System.out.println(cusData.length()+"");
            String []cus_data_arr=ParseJson.jsonobjToArr(cusData,new String[]{"custom_contacts","custom_name","custom_location","phone_num"});
            //客户信息"cus_data":{"custom_contacts":"","custom_name":"","custom_location":"","phone_num":""},
            initHeadCell(new String[]{dataUpsIns[100]},pdfPTable);
            sandWish(new String[]{dataUpsIns[1],dataUpsIns[4],dataUpsIns[2],dataUpsIns[3]},
                    new String[]{cusData.getString("custom_name"),cusData.getString("custom_location"),
                            cusData.getString("custom_contacts"),cusData.getString("phone_num")},
            new String[]{"","","",""},pdfPTable);
            //initCell(new String[]{cus_data_arr[0],cus_data_arr[1],cus_data_arr[2],cus_data_arr[3]},pdfPTable);

            //产品信息 "product_info_1":{"pattern":"","power":"","type":"","brand":""}, "product_info_2":{"bar_code":"","word_way":"","kong":""},
            JSONObject productInfo1=ParseJson.getSubjson(jsonObject,"product_info_1");
            JSONObject productInfo2=ParseJson.getSubjson(jsonObject,"product_info_2");
            initHeadCell(new String[]{dataUpsIns[200]},pdfPTable);
            sandWish(new String[]{dataUpsIns[9],dataUpsIns[10],dataUpsIns[888],dataUpsIns[888]},
                    new String[]{productInfo1.getString("brand"),productInfo1.getString("type"),
                            productInfo1.getString("power"),productInfo1.getString("pattern")},
                    new String[]{"","","",""},pdfPTable);

            sandWish(new String[]{dataUpsIns[5],dataUpsIns[6],dataUpsIns[7],dataUpsIns[8]},
                    new String[]{productInfo2.getString("bar_code"),productInfo2.getString("word_way"),
                            productInfo2.getString("kong"),productInfo2.getString("kong")},
                    new String[]{"","","",""},pdfPTable);

            //电池参数   "ups_para_1":{"ups_type":"","ups_num":"","ups_brand":"","kong":""},
            //     "ups_para_2":{"vol":"","cha_vol":"","ups_group_num":"","cha_ec":""},
            //     "ups_para_3":{"vps_ustime":"","vps_status":"","need_ex":"","vps_id":""},
            JSONObject upspara1=ParseJson.getSubjson(jsonObject,"ups_para_1");
            JSONObject upspara2=ParseJson.getSubjson(jsonObject,"ups_para_2");
            JSONObject upspara3=ParseJson.getSubjson(jsonObject,"ups_para_3");
            initHeadCell(new String[]{dataUpsIns[300]},pdfPTable);
            sandWish(new String[]{dataUpsIns[11],dataUpsIns[12],dataUpsIns[13],dataUpsIns[888]},
                    new String[]{upspara1.getString("ups_brand"),upspara1.getString("ups_type"),
                            upspara1.getString("ups_num"),upspara1.getString("kong")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataUpsIns[14],dataUpsIns[15],dataUpsIns[16],dataUpsIns[17]},
                    new String[]{upspara2.getString("ups_group_num"),upspara2.getString("vol"),
                            upspara2.getString("cha_vol"),upspara2.getString("cha_ec")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataUpsIns[18],dataUpsIns[19],dataUpsIns[20],dataUpsIns[21]},
                    new String[]{upspara3.getString("vps_id"),upspara3.getString("vps_ustime"),
                            upspara3.getString("need_ex"),upspara3.getString("vps_status")},
                    new String[]{"","","",""},pdfPTable);



            //电气环境参数  "electric_1":{"in_cabie":"","conform":"","in_air":"","zero_vol":""},
            //     "electric_2":{"out_air":"","out_cabie":"","zero_vol":"","conform":""},
            //     "electric_3":{"idc_clean":"","conform":"","idc_tem":"","idc_hum":""},
            JSONObject electric1=ParseJson.getSubjson(jsonObject,"electric_1");
            JSONObject electric2=ParseJson.getSubjson(jsonObject,"electric_2");
            JSONObject electric3=ParseJson.getSubjson(jsonObject,"electric_3");
            initHeadCell(new String[]{dataUpsIns[400]},pdfPTable);
            sandWish(new String[]{dataUpsIns[22],dataUpsIns[23],dataUpsIns[24],dataUpsIns[25]},
                    new String[]{electric1.getString("in_air"),electric1.getString("in_cabie"),
                            electric1.getString("zero_vol"),electric1.getString("conform")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataUpsIns[222],dataUpsIns[223],dataUpsIns[24],dataUpsIns[25]},
                    new String[]{electric2.getString("out_air"),electric2.getString("out_cabie"),
                            electric2.getString("zero_vol"),electric2.getString("conform")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataUpsIns[26],dataUpsIns[27],dataUpsIns[28],dataUpsIns[29]},
                    new String[]{electric3.getString("idc_tem"),electric3.getString("idc_hum"),
                            electric3.getString("idc_clean"),electric3.getString("conform")},
                    new String[]{"","","",""},pdfPTable);

            //运行参数 "run_para_1":{"hz":"","in_vol_a":"","in_vol_b":"","in_vol_c":""},
            //     "run_para_2":{"hz":"","near_vol_c":"","near_vol_a":"","near_vol_b":""},
            //     "run_para_3":{"out_vol_c":"","out_vol_b":"","hz":"","out_vol_a":""}}
            //     "run_para_4":{"in_ec_c":"","in_ec_b":"","in_ec_a":"","kong":""},
            //     "run_para_5":{"out_ec_c":"","out_ec_b":"","out_ec_a":"","kong":""},
            JSONObject runPara1=ParseJson.getSubjson(jsonObject,"run_para_1");//黄花菜都凉了
            JSONObject runPara2=ParseJson.getSubjson(jsonObject,"run_para_2");
            JSONObject runPara3=ParseJson.getSubjson(jsonObject,"run_para_3");
            JSONObject runPara4=ParseJson.getSubjson(jsonObject,"run_para_4");
            JSONObject runPara5=ParseJson.getSubjson(jsonObject,"run_para_5");
            initHeadCell(new String[]{dataUpsIns[500]},pdfPTable);
            sandWish(new String[]{dataUpsIns[430],dataUpsIns[431],dataUpsIns[432],dataUpsIns[35]},
                    new String[]{runPara1.getString("in_vol_a"),runPara1.getString("in_vol_b"),
                            runPara1.getString("in_vol_c"),runPara1.getString("hz")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataUpsIns[530],dataUpsIns[531],dataUpsIns[532],dataUpsIns[35]},
                    new String[]{runPara2.getString("near_vol_a"),runPara2.getString("near_vol_b"),
                            runPara2.getString("near_vol_c"),runPara2.getString("hz")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataUpsIns[630],dataUpsIns[631],dataUpsIns[632],dataUpsIns[35]},
                    new String[]{runPara3.getString("out_vol_a"),runPara3.getString("out_vol_b"),
                            runPara3.getString("out_vol_c"),runPara3.getString("hz")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataUpsIns[730],dataUpsIns[731],dataUpsIns[732],dataUpsIns[888]},
                    new String[]{runPara4.getString("in_ec_a"),runPara4.getString("in_ec_b"),
                            runPara4.getString("in_ec_c"),runPara4.getString("kong")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataUpsIns[830],dataUpsIns[831],dataUpsIns[832],dataUpsIns[888]},
                    new String[]{runPara5.getString("out_ec_a"),runPara5.getString("out_ec_b"),
                            runPara5.getString("out_ec_c"),runPara5.getString("kong")},
                    new String[]{"","","",""},pdfPTable);


            //功能测试"feature_test_1":{"inverse":"","record":"","near":"","panel":""},
            //     "feature_test_2":{"e_inverse":"","emergency":"","warning":"","fix_near":""},
            JSONObject featureTest1=ParseJson.getSubjson(jsonObject,"feature_test_1");
            JSONObject featureTest2=ParseJson.getSubjson(jsonObject,"feature_test_2");
            initHeadCell(new String[]{dataUpsIns[600]},pdfPTable);
            sandWish(new String[]{dataUpsIns[36],dataUpsIns[37],dataUpsIns[38],dataUpsIns[39]},
                    new String[]{featureTest1.getString("panel"),featureTest1.getString("record"),
                            featureTest1.getString("inverse"),featureTest1.getString("near")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataUpsIns[40],dataUpsIns[41],dataUpsIns[42],dataUpsIns[43]},
                    new String[]{featureTest2.getString("e_inverse"),featureTest2.getString("emergency"),
                            featureTest2.getString("fix_near"),featureTest2.getString("warning")},
                    new String[]{"","","",""},pdfPTable);

            //硬件检测 "hard_test":{"hard2":"","hard1":"","hard0":"","hard7":"","hard6":"","hard5":"","hard4":"","hard3":""},
            JSONObject hardTest=ParseJson.getSubjson(jsonObject,"hard_test");
            initHeadCell(new String[]{dataUpsIns[600]},pdfPTable);

            initHeadCell(new String[]{dataUpsIns[44]+hardTest.getString("hard0")},pdfPTable);
            initHeadCell(new String[]{dataUpsIns[45]+hardTest.getString("hard1")},pdfPTable);
            initHeadCell(new String[]{dataUpsIns[46]+hardTest.getString("hard2")},pdfPTable);
            initHeadCell(new String[]{dataUpsIns[47]+hardTest.getString("hard3")},pdfPTable);
            initHeadCell(new String[]{dataUpsIns[48]+hardTest.getString("hard4")},pdfPTable);
            initHeadCell(new String[]{dataUpsIns[49]+hardTest.getString("hard5")},pdfPTable);
            initHeadCell(new String[]{dataUpsIns[490]+hardTest.getString("hard6")},pdfPTable);
            initHeadCell(new String[]{dataUpsIns[491]+hardTest.getString("hard7")},pdfPTable);

            //其他  au img suggest opinion  time step way








            document.add(pdfPTable);
            document.close();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // response.setContentType("application/pdf");
        //Document document = new Document(PageSize.A4.rotate());
        return fileName;
    }

    /**
     {"device_work_pattern":"","cus_sign":"123","error_time":"","my_sign":"123","handle_error":"",
     "cost":{"warr_inner":"","materal":"","sum_cost":"","Maintenance":"","transport":"","travel":"","warr_out":"","labor":""},
     "device_id":"","error_phon":"","fix_time":"","fix_suggest":"","kong":"","device_power":"","device_brand":"",
     "device_t":"","fix_location":"","phone_number":"","location":"","error_analysis":"","contacts":"","fix_reason":"","cus_name":"",}


     public void makeBodyJson(){
     initBodyJson(R.id.ups_fix_body1,"error_phon");
     initBodyJson(R.id.ups_fix_body2,"error_analysis");
     initBodyJson(R.id.ups_fix_body3,"handle_error");
     initBodyJson(R.id.ups_fix_body4,"fix_reason");
     singleJson(this.json,"cost",initCostJson(R.id.ups_fix_body5));
     }

     public void makeFootJson(){//除了这些 还应该有签名文件名 时间戳 能不能先生成好在update呀  可以吧
     EditText text= view.findViewById(R.id.ups_foot_sugg);
     String str=getEditData(text);
     singleStr(this.json,"fix_suggest",str);
     singleStr(this.json,"my_sign","123");
     singleStr(this.json,"cus_sign","123");

     * @param jsonObject
     */
    //ups维修
    public static String upsFixPdfMake(JSONObject jsonObject){
        //init
        OutputStream os = null;
        long timestamp=TimeAndDateUtil.makeBootstamp();
        String fileName="d:/ftp/"+timestamp+".pdf";

        //initData
        dataUpsFix=  ParseJson.getXmls(ParseJson.getFileFile("vps_fix_report.xml"));
        try {
            os = new FileOutputStream(new File("d:/ftp/"+timestamp+".pdf"));
            Document document = new Document(PageSize.A4);
            PdfWriter pw= PdfWriter.getInstance(document, os);
            pw.open();
            document.open();
            if(fonts==null)initFontArray();
            //标题
            Paragraph upsTestTitle = new Paragraph("ups修理报告",fonts[1]);
            upsTestTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(upsTestTitle);
            document.add(Chunk.NEWLINE);

            PdfPTable pdfPTable=initTable(12,new int[]{16,16,16,16,16,16,16,16,16,16,16,16});
             /* initCell(new String[]{"你若","安好","便是","晴天"},pdfPTable);
            document.add(pdfPTable); 这样 就会有两行 今天java出bug  可以无限加入的*/

            //
         /*   initCell(new String[]{"今天","java","出了","bug"},pdfPTable);
            initCell(new String[]{"你若","安好","便是","晴天"},pdfPTable);
            initCell(new String[]{"体系架构","网络","数据结构","操作系统"},pdfPTable);*/
            //散列
            initCell(new String[]{dataUpsFix[1]+jsonObject.getString("contacts")},pdfPTable);
            sandWish(new String[]{dataUpsFix[1],dataUpsFix[2],dataUpsFix[3],dataUpsFix[4]},
                    new String[]{jsonObject.getString("cus_name"),jsonObject.getString("contacts"),
                    jsonObject.getString("phone_number"),jsonObject.getString("location")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataUpsFix[5],dataUpsFix[6],dataUpsFix[7],dataUpsFix[8]},
                    new String[]{jsonObject.getString("device_brand"),jsonObject.getString("device_t"),
                            jsonObject.getString("device_power"),jsonObject.getString("device_id")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataUpsFix[9],dataUpsFix[10],dataUpsFix[11],dataUpsFix[12]},
                    new String[]{jsonObject.getString("device_work_pattern"),jsonObject.getString("error_time"),
                            jsonObject.getString("fix_time"),jsonObject.getString("fix_location")},
                    new String[]{"","","",""},pdfPTable);


            //故障
            initCell(new String[]{dataUpsFix[100]},pdfPTable);
            initCellWithHeight(new String[]{dataUpsFix[13]+jsonObject.getString("error_phon")},pdfPTable);
            initCellWithHeight(new String[]{dataUpsFix[14]+jsonObject.getString("error_analysis")},pdfPTable);
            initCellWithHeight(new String[]{dataUpsFix[15]+jsonObject.getString("handle_error")},pdfPTable);
            initCellWithHeight(new String[]{dataUpsFix[16]+jsonObject.getString("fix_reason")},pdfPTable);

            //cost "warr_inner":"","materal":"","sum_cost":"","Maintenance":"","transport":"","travel":"","warr_out":"","labor":""
            JSONObject cost=ParseJson.getSubjson(jsonObject,"cost");
            initCell(new String[]{dataUpsFix[17]},pdfPTable);
            sandWish(new String[]{dataUpsFix[18],dataUpsFix[19],dataUpsFix[20],dataUpsFix[21]},
                    new String[]{cost.getString("Maintenance"),cost.getString("warr_inner"),
                            cost.getString("warr_out"),cost.getString("labor")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataUpsFix[22],dataUpsFix[23],dataUpsFix[24],dataUpsFix[25]},
                    new String[]{cost.getString("materal"),cost.getString("travel"),
                            cost.getString("transport"),cost.getString("sum_cost")},
                    new String[]{"","","",""},pdfPTable);

            //其他

            document.add(pdfPTable);

            document.close();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // response.setContentType("application/pdf");
        //Document document = new Document(PageSize.A4.rotate());
        return fileName;
    }


    /**
     {"cus_sign":"123","ups_ec":"","ups_status":"",
     "my_sign":"123","to_sum_up":"","ups_brand":"",
     "ups_cap":"","ups_type":"","ups_vol":"",
     "ups_t_charging":{"str1":"","str2":"","str0":""},
     "ups_ec_charging":{"str1":"","str2":"","str0":""},
     "ups_t_discha":{"str1":"","str2":"","str0":""},
     "ups_ec_discha":{"str1":"","str2":"","str0":""},
     "array":[{"ups_dischar_vol":{"str1":"","str2":"","str0":""},"ups_char_vol":{"str1":"","str2":"","str0":""},"internal_resist":""},133
     {"ups_dischar_vol":{"str1":"","str2":"","str0":""},"ups_char_vol":{"str1":"","str2":"","str0":""},"internal_resist":""},
     {"ups_dischar_vol":{"str1":"","str2":"","str0":""},"ups_char_vol":{"str1":"","str2":"","str0":""},"internal_resist":""},
     {"ups_dischar_vol":{"str1":"","str2":"","str0":""},"ups_char_vol":{"str1":"","str2":"","str0":""},"internal_resist":""},
     {"ups_dischar_vol":{"str1":"","str2":"","str0":""},"ups_char_vol":{"str1":"","str2":"","str0":""},"internal_resist":""},
     {"ups_dischar_vol":{"str1":"","str2":"","str0":""},"ups_char_vol":{"str1":"","str2":"","str0":""},"internal_resist":""},
     {"ups_dischar_vol":{"str1":"","str2":"","str0":""},"ups_char_vol":{"str1":"","str2":"","str0":""},"internal_resist":""},
     {"ups_dischar_vol":{"str1":"","str2":"","str0":""},"ups_char_vol":{"str1":"","str2":"","str0":""},"internal_resist":""},
     {"ups_dischar_vol":{"str1":"","str2":"","str0":""},"ups_char_vol":{"str1":"","str2":"","str0":""},"internal_resist":""},
     {"ups_dischar_vol":{"str1":"","str2":"","str0":""},"ups_char_vol":{"str1":"","str2":"","str0":""},"internal_resist":""}]
     }
     * @param jsonObject
     */

    //ups测试
    public static String upsTestPdfMake(JSONObject jsonObject){//搁着先
        //init
        OutputStream os = null;
        long timestamp=TimeAndDateUtil.makeBootstamp();
        String fileName="d:/ftp/"+timestamp+".pdf";
        //pdf
        dataUpsTest=  ParseJson.getXmls(ParseJson.getFileFile("vps_test_report.xml"));

        try {
            os = new FileOutputStream(new File("d:/ftp/"+timestamp+".pdf"));
            Document document = new Document(PageSize.A4);
            PdfWriter pw= PdfWriter.getInstance(document, os);
            pw.open();
            document.open();
            if(fonts==null)initFontArray();
            //标题
            Paragraph upsTestTitle = new Paragraph("ups测试报告",fonts[1]);
            upsTestTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(upsTestTitle);
            document.add(Chunk.NEWLINE);

            PdfPTable pdfPTable=initTable(12,new int[]{16,16,16,16,16,16,16,16,16,16,16,16});
             /* initCell(new String[]{"你若","安好","便是","晴天"},pdfPTable);
            document.add(pdfPTable); 这样 就会有两行 今天java出bug  可以无限加入的*/

           // 前面几行"cus_sign":"123","ups_ec":"","ups_status":"",
            //     "my_sign":"123","to_sum_up":"","ups_brand":"",
            //     "ups_cap":"","ups_type":"","ups_vol":"",

            sandWish(new String[]{dataUpsTest[1],dataUpsTest[2],dataUpsTest[3]},
                    new String[]{jsonObject.getString("ups_brand"),jsonObject.getString("ups_type"), jsonObject.getString("ups_cap")},
                    new String[]{"","",""},pdfPTable);
            sandWish(new String[]{dataUpsTest[4],dataUpsTest[5],dataUpsTest[6]},
                    new String[]{jsonObject.getString("ups_vol"),jsonObject.getString("ups_status"), jsonObject.getString("ups_ec")},
                    new String[]{"","",""},pdfPTable);

            // 电池组 133
            initCell(new String[]{dataUpsTest[9]+dataUpsTest[7]+dataUpsTest[8]+dataUpsTest[7]},pdfPTable);

            //JSONObject cost=ParseJson.getSubjson(jsonObject,"cost");
            JSONArray ja=jsonObject.getJSONArray("array");
            for(int i=0;i<ja.length();i++){
                JSONObject numObj=ja.getJSONObject(i);
                // {"ups_dischar_vol":{"str1":"","str2":"","str0":""},"ups_char_vol":{"str1":"","str2":"","str0":""},"internal_resist":""},
                //电池编号及内阻
                initCell(new String[]{dataUpsTest[12]+":"+(i+1),dataUpsTest[13]+":"+numObj.getString("internal_resist")},pdfPTable);
                //充电电压
                JSONObject upsCharVol=numObj.getJSONObject("ups_char_vol");
                sandWish(new String[]{dataUpsTest[9]+dataUpsTest[14]+"1",dataUpsTest[9]+dataUpsTest[14]+"2",dataUpsTest[9]+dataUpsTest[14]+"3"},
                        new String[]{upsCharVol.getString("str0"),upsCharVol.getString("str1"), upsCharVol.getString("str2")},
                        new String[]{"","",""},pdfPTable);
                //放电电压
                JSONObject upsDischarVol=numObj.getJSONObject("ups_dischar_vol");
                sandWish(new String[]{dataUpsTest[8]+dataUpsTest[14]+"1",dataUpsTest[8]+dataUpsTest[14]+"2",dataUpsTest[8]+dataUpsTest[14]+"3"},
                        new String[]{upsDischarVol.getString("str0"),upsDischarVol.getString("str1"), upsDischarVol.getString("str2")},
                        new String[]{"","",""},pdfPTable);
            }
            // 其他 总结 工程师签名客户签名  工程师单位  客户单位
            //总结
            initCellWithHeight(new String[]{dataUpsTest[17]+jsonObject.getString("to_sum_up")},pdfPTable);
            // 签名
            //initCellWithHeight(new String[]{dataUpsTest[17]},pdfPTable);
            Image imageEng = Image.getInstance("d:/ftp/"+jsonObject.getString("my_sign")+".jpg");
         /*   PdfPCell cellEng=new PdfPCell(imageEng);
            cellEng.setFixedHeight(80);
            cellEng.setColspan(6);
            pdfPTable.addCell(cellEng);*/
            makeImage(jsonObject.getString("my_sign"),pdfPTable);

         /*   Image imageCus = Image.getInstance("d:/ftp/"+jsonObject.getString("cus_sign")+".jpg");
            PdfPCell cellCus=new PdfPCell(imageCus);
            cellCus.setFixedHeight(80);
            cellCus.setColspan(6);
            pdfPTable.addCell(cellCus);*/
            makeImage(jsonObject.getString("cus_sign"),pdfPTable);
            //
            sandWish(new String[]{dataUpsTest[8]+dataUpsTest[14]+"1",dataUpsTest[8]+dataUpsTest[14]+"2",dataUpsTest[8]+dataUpsTest[14]+"3",dataUpsTest[8]+dataUpsTest[14]+"3"},
                    new String[]{jsonObject.getString("str0"),jsonObject.getString("str1"),
                            jsonObject.getString("str2"),jsonObject.getString("str2")},
                    new String[]{"","","",""},pdfPTable);



            document.add(pdfPTable);


            document.close();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // response.setContentType("application/pdf");
        //Document document = new Document(PageSize.A4.rotate());
        return fileName;

    }

    /**
     "hum_sys1":{"humidifying_steam_pipe":"","humidification_water":"","kong":""},
     {"hum_sys2":{"drain_solenoid_valve":"","humidification_current_1":"","humidification_current_2":"","humidification_current_3":""},
     "hum_sys3":{"infrared_humidifying_tray":"","infrared_humidifying_lamp_tube":"","wet_the_tank":"","humidifying_tank_electrode":""},
     "exterior":{"door_lock":"","door_plank":"","kong":""},
     "warm_sys1":{"the_heating_switch":"","level_of_current_3":"","level_of_current_1":"","level_of_current_2":""},
     "warm_sys2":{"the_secondary_current_3":"","the_insulation_protection":"","the_secondary_current_1":"","the_secondary_current_2":""},
     "remove_hum_water":{"humidifying_drainage":"","contactor":"","condensate_drain":"","dehumidifying_solenoid_valve_status":""},
     "fan_sys1":{"filter":"","fan_phase_sequence":"","kong":""},
     "fan_sys2":{"fan1_ele_2":"","fan1_ele_3":"","fan_belt":"","fan1_ele_1":""},
     "fan_sys3":{"fan_bearing":"","fan2_ele_1":"","fan2_ele_2":"","fan2_ele_3":""},"
     fan_sys4":{"fan3_ele_2":"","fan3_ele_3":"","fan_contactor":"","fan3_ele_1":""},
     "product_info_1":{"pattern":"","power":"","type":"","brand":""},
     "product_info_2":{"bar_code":"","word_way":"","kong":""},

     "cold_sys1_1":{"low_pressure":"","high_pressure":"","fan_control_type":"","condenser":""},
     "cold_sys1_2":{"expansion_valve":"","compressor_current_1":"","compressor_current_3":"","compressor_current_2":""},
     "cold_sys1_3":{"solenoid_valve":"","external_fan_current_3":"","external_fan_current_2":"","external_fan_current_1":""},
     "cold_sys1_4":{"low_voltage_protection_settings":"","liquid_lens_shows":"","frozen_oil_level":"","high_voltage_protection_settings":""},

     "cold_sys2_1":{"low_pressure":"","high_pressure":"","fan_control_type":"","condenser":""},
     "cold_sys2_2":{"expansion_valve":"","compressor_current_1":"","compressor_current_3":"","compressor_current_2":""},
     "cold_sys2_3":{"solenoid_valve":"","external_fan_current_3":"","external_fan_current_2":"","external_fan_current_1":""},
     "cold_sys2_4":{"low_voltage_protection_settings":"","liquid_lens_shows":"","frozen_oil_level":"","high_voltage_protection_settings":""}}
     "cus_data":{"custom_contacts":"","custom_name":"","custom_location":"","phone_num":""},
     "con_sys":{"indicator_light":"","button_alarm":"","display_record":"","menu_set":""},

    空重叠导致问题了  反正空 就再gei一次 getString("kong");
     * @param jsonObject 空调检修
     */
    public static String airInsMakePdf(JSONObject jsonObject){

        //init
        OutputStream os = null;
        long timestamp=TimeAndDateUtil.makeBootstamp();
        String fileName="d:/ftp/"+timestamp+".pdf";

        //initdata
        dataAirIns=  ParseJson.getXmls(ParseJson.getFileFile("air_condition_inspection.xml"));
        try {
            os = new FileOutputStream(new File("d:/ftp/"+timestamp+".pdf"));
            Document document = new Document(PageSize.A4);
            PdfWriter pw= PdfWriter.getInstance(document, os);
            pw.open();
            document.open();
            if(fonts==null)initFontArray();
            //标题
            Paragraph upsTestTitle = new Paragraph("空调检修报告",fonts[1]);
            upsTestTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(upsTestTitle);
            document.add(Chunk.NEWLINE);

            PdfPTable pdfPTable=initTable(12,new int[]{16,16,16,16,16,16,16,16,16,16,16,16});
             /* initCell(new String[]{"你若","安好","便是","晴天"},pdfPTable);
            document.add(pdfPTable); 这样 就会有两行 今天java出bug  可以无限加入的*/

            //
        /*    initCell(new String[]{"今天","java","出了","bug"},pdfPTable);
            initCell(new String[]{"你若","安好","便是","晴天"},pdfPTable);
            initCell(new String[]{"体系架构","网络","数据结构","操作系统"},pdfPTable);*/

            //客户资料"cus_data":{"custom_contacts":"","custom_name":"","custom_location":"","phone_num":""},
            JSONObject cusData=ParseJson.getSubjson(jsonObject,"cus_data");
            initCell(new String[]{dataAirIns[100]},pdfPTable);
            sandWish(new String[]{dataAirIns[1],dataAirIns[2],dataAirIns[3],dataAirIns[4]},
                    new String[]{cusData.getString("custom_contacts"),cusData.getString("custom_name"),
                            cusData.getString("phone_num"),cusData.getString("custom_location")},
                    new String[]{"","","",""},pdfPTable);



            //产品信息 "product_info_1":{"pattern":"","power":"","type":"","brand":""},
            //     "product_info_2":{"bar_code":"","word_way":"","kong":""},
            JSONObject productInfo1=ParseJson.getSubjson(jsonObject,"product_info_1");
            JSONObject productInfo2=ParseJson.getSubjson(jsonObject,"product_info_2");
            initCell(new String[]{dataAirIns[200]},pdfPTable);
            sandWish(new String[]{dataAirIns[5],dataAirIns[6],dataAirIns[7],dataAirIns[8]},
                    new String[]{productInfo1.getString("brand"),productInfo1.getString("type"),
                            productInfo1.getString("power"),productInfo1.getString("pattern")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataAirIns[9],dataAirIns[99],dataAirIns[888],dataAirIns[888]},
                    new String[]{productInfo2.getString("bar_code"),productInfo2.getString("word_way"),
                            productInfo2.getString("kong"),productInfo2.getString("kong")},
                    new String[]{"","","",""},pdfPTable);

            //检测项目
            initCellWithHeight(new String[]{dataAirIns[10]+dataAirIns[11]},pdfPTable);

            //外观  "exterior":{"door_lock":"","door_plank":"","kong":""},
            JSONObject exterior=ParseJson.getSubjson(jsonObject,"exterior");
            initCell(new String[]{dataAirIns[12]},pdfPTable);
            sandWish(new String[]{dataAirIns[13],dataAirIns[14],dataAirIns[888],dataAirIns[888]},
                    new String[]{exterior.getString("door_plank"),exterior.getString("door_lock"),
                            exterior.getString("kong"),exterior.getString("kong")},
                    new String[]{"","","",""},pdfPTable);

            //控制系统"con_sys":{"indicator_light":"","button_alarm":"","display_record":"","menu_set":""},
            JSONObject conSys=ParseJson.getSubjson(jsonObject,"con_sys");
            initCell(new String[]{dataAirIns[15]},pdfPTable);
            sandWish(new String[]{dataAirIns[16],dataAirIns[17],dataAirIns[18],dataAirIns[19]},
                    new String[]{conSys.getString("button_alarm"),conSys.getString("indicator_light"),
                            conSys.getString("display_record"),conSys.getString("menu_set")},
                    new String[]{"","","",""},pdfPTable);
            //风机系统  "fan_sys1":{"filter":"","fan_phase_sequence":"","kong":""},
            //     "fan_sys2":{"fan1_ele_2":"","fan1_ele_3":"","fan_belt":"","fan1_ele_1":""},
            //     "fan_sys3":{"fan_bearing":"","fan2_ele_1":"","fan2_ele_2":"","fan2_ele_3":""},"
            //     fan_sys4":{"fan3_ele_2":"","fan3_ele_3":"","fan_contactor":"","fan3_ele_1":""},
            JSONObject fanSys1=ParseJson.getSubjson(jsonObject,"fan_sys1");
            JSONObject fanSys2=ParseJson.getSubjson(jsonObject,"fan_sys2");
            JSONObject fanSys3=ParseJson.getSubjson(jsonObject,"fan_sys3");
            JSONObject fanSys4=ParseJson.getSubjson(jsonObject,"fan_sys4");
            initCell(new String[]{dataAirIns[20]},pdfPTable);
            sandWish(new String[]{dataAirIns[21],dataAirIns[22],dataAirIns[888],dataAirIns[888]},
                    new String[]{fanSys1.getString("fan_phase_sequence"),fanSys1.getString("filter"),
                            fanSys1.getString("kong"),fanSys1.getString("kong")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataAirIns[23],dataAirIns[24],dataAirIns[24],dataAirIns[24]},
                    new String[]{fanSys2.getString("fan_belt"),fanSys2.getString("fan1_ele_1"),
                            fanSys2.getString("fan1_ele_2"),fanSys2.getString("fan1_ele_3")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataAirIns[25],dataAirIns[26],dataAirIns[26],dataAirIns[26]},
                    new String[]{fanSys3.getString("fan_bearing"),fanSys3.getString("fan2_ele_1"),
                            fanSys3.getString("fan2_ele_2"),fanSys3.getString("fan2_ele_3")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataAirIns[27],dataAirIns[28],dataAirIns[28],dataAirIns[28]},
                    new String[]{fanSys4.getString("fan_contactor"),fanSys4.getString("fan3_ele_1"),
                            fanSys4.getString("fan3_ele_2"),fanSys4.getString("fan3_ele_3")},
                    new String[]{"","","",""},pdfPTable);

            //制冷系统一    "cold_sys1_1":{"low_pressure":"","high_pressure":"","fan_control_type":"","condenser":""},
            //     "cold_sys1_2":{"expansion_valve":"","compressor_current_1":"","compressor_current_3":"","compressor_current_2":""},
            //     "cold_sys1_3":{"solenoid_valve":"","external_fan_current_3":"","external_fan_current_2":"","external_fan_current_1":""},
            //     "cold_sys1_4":{"low_voltage_protection_settings":"","liquid_lens_shows":"","frozen_oil_level":"","high_voltage_protection_settings":""},
            JSONObject coldSys11=ParseJson.getSubjson(jsonObject,"cold_sys1_1");
            JSONObject coldSys12=ParseJson.getSubjson(jsonObject,"cold_sys1_2");
            JSONObject coldSys13=ParseJson.getSubjson(jsonObject,"cold_sys1_3");
            JSONObject coldSys14=ParseJson.getSubjson(jsonObject,"cold_sys1_4");
            initCell(new String[]{dataAirIns[300]},pdfPTable);
            sandWish(new String[]{dataAirIns[29],dataAirIns[30],dataAirIns[31],dataAirIns[32]},
                    new String[]{coldSys11.getString("fan_control_type"),coldSys11.getString("high_pressure"),
                            coldSys11.getString("condenser"),coldSys11.getString("low_pressure")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataAirIns[33],dataAirIns[34],dataAirIns[34],dataAirIns[34]},
                    new String[]{coldSys12.getString("expansion_valve"),coldSys12.getString("compressor_current_1"),
                            coldSys12.getString("compressor_current_2"),coldSys12.getString("compressor_current_3")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataAirIns[35],dataAirIns[36],dataAirIns[36],dataAirIns[36]},
                    new String[]{coldSys13.getString("solenoid_valve"),coldSys13.getString("external_fan_current_1"),
                            coldSys13.getString("external_fan_current_2"),coldSys13.getString("external_fan_current_3")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataAirIns[37],dataAirIns[38],dataAirIns[39],dataAirIns[40]},
                    new String[]{coldSys14.getString("liquid_lens_shows"),coldSys14.getString("high_voltage_protection_settings"),
                            coldSys14.getString("frozen_oil_level"),coldSys14.getString("low_voltage_protection_settings")},
                    new String[]{"","","",""},pdfPTable);

            //制冷系统二"cold_sys2_1":{"low_pressure":"","high_pressure":"","fan_control_type":"","condenser":""},
            //     "cold_sys2_2":{"expansion_valve":"","compressor_current_1":"","compressor_current_3":"","compressor_current_2":""},
            //     "cold_sys2_3":{"solenoid_valve":"","external_fan_current_3":"","external_fan_current_2":"","external_fan_current_1":""},
            //     "cold_sys2_4":{"low_voltage_protection_settings":"","liquid_lens_shows":"","frozen_oil_level":"","high_voltage_protection_settings":""}}
            JSONObject coldSys21=ParseJson.getSubjson(jsonObject,"cold_sys2_1");
            JSONObject coldSys22=ParseJson.getSubjson(jsonObject,"cold_sys2_2");
            JSONObject coldSys23=ParseJson.getSubjson(jsonObject,"cold_sys2_3");
            JSONObject coldSys24=ParseJson.getSubjson(jsonObject,"cold_sys2_4");
            initCell(new String[]{dataAirIns[400]},pdfPTable);
            sandWish(new String[]{dataAirIns[29],dataAirIns[30],dataAirIns[31],dataAirIns[32]},
                    new String[]{coldSys21.getString("fan_control_type"),coldSys21.getString("high_pressure"),
                            coldSys21.getString("condenser"),coldSys21.getString("low_pressure")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataAirIns[33],dataAirIns[34],dataAirIns[34],dataAirIns[34]},
                    new String[]{coldSys22.getString("expansion_valve"),coldSys22.getString("compressor_current_1"),
                            coldSys22.getString("compressor_current_2"),coldSys22.getString("compressor_current_3")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataAirIns[35],dataAirIns[36],dataAirIns[36],dataAirIns[36]},
                    new String[]{coldSys23.getString("solenoid_valve"),coldSys23.getString("external_fan_current_1"),
                            coldSys23.getString("external_fan_current_2"),coldSys23.getString("external_fan_current_3")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataAirIns[37],dataAirIns[38],dataAirIns[39],dataAirIns[40]},
                    new String[]{coldSys24.getString("liquid_lens_shows"),coldSys24.getString("high_voltage_protection_settings"),
                            coldSys24.getString("frozen_oil_level"),coldSys24.getString("low_voltage_protection_settings")},
                    new String[]{"","","",""},pdfPTable);

            //加湿系统 "hum_sys1":{"humidifying_steam_pipe":"","humidification_water":"","kong":""},
            //     {"hum_sys2":{"drain_solenoid_valve":"","humidification_current_1":"","humidification_current_2":"","humidification_current_3":""},
            //     "hum_sys3":{"infrared_humidifying_tray":"","infrared_humidifying_lamp_tube":"","wet_the_tank":"","humidifying_tank_electrode":""},
            JSONObject humSys1=ParseJson.getSubjson(jsonObject,"hum_sys1");
            JSONObject humSys2=ParseJson.getSubjson(jsonObject,"hum_sys2");
            JSONObject humSys3=ParseJson.getSubjson(jsonObject,"hum_sys3");
            initCell(new String[]{dataAirIns[500]},pdfPTable);
            sandWish(new String[]{dataAirIns[41],dataAirIns[42],dataAirIns[39],dataAirIns[40]},
                    new String[]{humSys1.getString("humidification_water"),humSys1.getString("humidifying_steam_pipe"),
                            humSys1.getString("kong"),humSys1.getString("kong")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataAirIns[43],dataAirIns[44],dataAirIns[44],dataAirIns[44]},
                    new String[]{humSys2.getString("drain_solenoid_valve"),humSys2.getString("humidification_current_1"),
                            humSys2.getString("humidification_current_2"),humSys2.getString("humidification_current_3")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataAirIns[45],dataAirIns[46],dataAirIns[47],dataAirIns[48]},
                    new String[]{humSys3.getString("humidifying_tank_electrode"),humSys3.getString("wet_the_tank"),
                            humSys3.getString("infrared_humidifying_lamp_tube"),humSys3.getString("infrared_humidifying_tray")},
                    new String[]{"","","",""},pdfPTable);

            //加热系统 "warm_sys1":{"the_heating_switch":"","level_of_current_3":"","level_of_current_1":"","level_of_current_2":""},
            //     "warm_sys2":{"the_secondary_current_3":"","the_insulation_protection":"","the_secondary_current_1":"","the_secondary_current_2":""},
            JSONObject warmSys1=ParseJson.getSubjson(jsonObject,"warm_sys1");
            JSONObject warmSys2=ParseJson.getSubjson(jsonObject,"warm_sys2");
            initCell(new String[]{dataAirIns[600]},pdfPTable);
            sandWish(new String[]{dataAirIns[50],dataAirIns[51],dataAirIns[51],dataAirIns[51]},
                    new String[]{warmSys1.getString("the_heating_switch"),warmSys1.getString("level_of_current_1"),
                            warmSys1.getString("level_of_current_2"),warmSys1.getString("level_of_current_3")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataAirIns[52],dataAirIns[53],dataAirIns[53],dataAirIns[53]},
                    new String[]{warmSys2.getString("the_insulation_protection"),warmSys2.getString("the_secondary_current_1"),
                            warmSys2.getString("the_secondary_current_2"),warmSys2.getString("the_secondary_current_3")},
                    new String[]{"","","",""},pdfPTable);

            //除湿排水     "remove_hum_water":{"humidifying_drainage":"","contactor":"","condensate_drain":"","dehumidifying_solenoid_valve_status":""},
            JSONObject removeHumWater=ParseJson.getSubjson(jsonObject,"remove_hum_water");
            initCell(new String[]{dataAirIns[700]},pdfPTable);
            sandWish(new String[]{dataAirIns[54],dataAirIns[55],dataAirIns[56],dataAirIns[57]},
                    new String[]{removeHumWater.getString("dehumidifying_solenoid_valve_status"),removeHumWater.getString("contactor"),
                            removeHumWater.getString("humidifying_drainage"),removeHumWater.getString("condensate_drain")},
                    new String[]{"","","",""},pdfPTable);
            //其他



            document.add(pdfPTable);


            document.close();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // response.setContentType("application/pdf");
        //Document document = new Document(PageSize.A4.rotate());
        return fileName;
    }


    //安装

    /**
     * {"cost":{"warr_inner":"","materal":"","sum_cost":"","Maintenance":"","transport":"","travel":"","warr_out":"","labor":""},
     *
     * "sum":"","cus_data":{"custom_contacts":"","custom_name":"","custom_location":"","phone_num":""},
     * "info":{"install_result":"","material":"","bar_code":"","install_process":""}}
     * @param jsonObject
     */
    public static String installMakePdf(JSONObject jsonObject){
        //init
        OutputStream os = null;
        long timestamp=TimeAndDateUtil.makeBootstamp();
        String fileName="d:/ftp/"+timestamp+".pdf";
        //initData
        if(dataInstall==null)
        dataInstall=  ParseJson.getXmls(ParseJson.getFileFile("site_install.xml"));
        try {
            os = new FileOutputStream(new File("d:/ftp/"+timestamp+".pdf"));
            Document document = new Document(PageSize.A4);
            PdfWriter pw= PdfWriter.getInstance(document, os);
            pw.open();
            document.open();
            if(fonts==null)initFontArray();
            //标题
            Paragraph upsTestTitle = new Paragraph("现场安装报告",fonts[1]);
            upsTestTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(upsTestTitle);
            document.add(Chunk.NEWLINE);

            PdfPTable pdfPTable=initTable(12,new int[]{16,16,16,16,16,16,16,16,16,16,16,16});
             /* initCell(new String[]{"你若","安好","便是","晴天"},pdfPTable);
            document.add(pdfPTable); 这样 就会有两行 今天java出bug  可以无限加入的*/

            //客户信息
            JSONObject cusData=ParseJson.getSubjson(jsonObject,"cus_data");
            initCell(new String[]{dataInstall[100]},pdfPTable);
            sandWish(new String[]{dataInstall[1],dataInstall[2],dataInstall[3],dataInstall[4]},
                    new String[]{cusData.getString("custom_name"),cusData.getString("custom_contacts"),
                            cusData.getString("phone_num"),cusData.getString("custom_location")},
                    new String[]{"","","",""},pdfPTable);

            //设备信息 "product_info":{"para":"","power":"","type":"","brand":""},
            JSONObject productInfo=ParseJson.getSubjson(jsonObject,"product_info");
            initCell(new String[]{dataInstall[200]},pdfPTable);
            sandWish(new String[]{dataInstall[55],dataInstall[5],dataInstall[6],dataInstall[7]},
                    new String[]{productInfo.getString("brand"),productInfo.getString("type"),
                            productInfo.getString("power"),productInfo.getString("para")},
                    new String[]{"","","",""},pdfPTable);

            //安装信息  "info":{"install_result":"","material":"","bar_code":"","install_process":""}}
            JSONObject info=ParseJson.getSubjson(jsonObject,"info");
            initCell(new String[]{dataInstall[400]},pdfPTable);
            sandWish(new String[]{dataInstall[8],dataInstall[9],dataInstall[10],dataInstall[11]},
                    new String[]{info.getString("bar_code"),info.getString("material"),
                            info.getString("install_process"),info.getString("install_result")},
                    new String[]{"","","",""},pdfPTable);

            //维修费用 {"cost":{"warr_inner":"","materal":"","sum_cost":"","Maintenance":"","transport":"","travel":"","warr_out":"","labor":""},
            JSONObject cost=ParseJson.getSubjson(jsonObject,"cost");
            initCell(new String[]{dataInstall[17]},pdfPTable);
            sandWish(new String[]{dataInstall[18],dataInstall[19],dataInstall[20],dataInstall[21]},
                    new String[]{cost.getString("Maintenance"),cost.getString("warr_inner"),
                            cost.getString("warr_out"),cost.getString("labor")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataInstall[22],dataInstall[23],dataInstall[24],dataInstall[25]},
                    new String[]{cost.getString("materal"),cost.getString("travel"),
                            cost.getString("transport"),cost.getString("sum_cost")},
                    new String[]{"","","",""},pdfPTable);

            //其他
            initCell(new String[]{dataInstall[300]},pdfPTable);
            document.add(pdfPTable);


            document.close();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // response.setContentType("application/pdf");
        //Document document = new Document(PageSize.A4.rotate());
        return fileName;
    }

    /**
     {"cost":{"warr_inner":"","materal":"","sum_cost":"","Maintenance":"","transport":"","travel":"","warr_out":"","labor":""},
     "sum":"","cus_data":{"custom_contacts":"","custom_name":"","custom_location":"","phone_num":""},
     "info":{"handle_error":"","service_pro":"","fix_reason":"","error_phon":""}}6月 11, 2018 5:35:55 下午 org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure


     * @param jsonObject
     */

    //服务
    public static String serviceMakePdf(JSONObject jsonObject){
        //init
        OutputStream os = null;
        long timestamp=TimeAndDateUtil.makeBootstamp();
        String fileName="d:/ftp/"+timestamp+".pdf";

        //initdata
        if(dataService==null)
            dataService=  ParseJson.getXmls(ParseJson.getFileFile("site_service.xml"));

        try {
            os = new FileOutputStream(new File("d:/ftp/"+timestamp+".pdf"));
            Document document = new Document(PageSize.A4);
            PdfWriter pw= PdfWriter.getInstance(document, os);
            pw.open();
            document.open();
            if(fonts==null)initFontArray();
            //标题
            Paragraph upsTestTitle = new Paragraph("现场服务报告",fonts[1]);
            upsTestTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(upsTestTitle);
            document.add(Chunk.NEWLINE);

            PdfPTable pdfPTable=initTable(12,new int[]{16,16,16,16,16,16,16,16,16,16,16,16});
             /* initCell(new String[]{"你若","安好","便是","晴天"},pdfPTable);
            document.add(pdfPTable); 这样 就会有两行 今天java出bug  可以无限加入的*/

            //客户信息  "cus_data":{"custom_contacts":"","custom_name":"","custom_location":"","phone_num":""},
            JSONObject cusData=ParseJson.getSubjson(jsonObject,"cus_data");
            initCell(new String[]{dataService[100]},pdfPTable);
            sandWish(new String[]{dataService[1],dataService[2],dataService[3],dataService[4]},
                    new String[]{cusData.getString("custom_name"),cusData.getString("custom_contacts"),
                            cusData.getString("phone_num"),cusData.getString("custom_location")},
                    new String[]{"","","",""},pdfPTable);

            //服务信息   "info":{"handle_error":"","service_pro":"","fix_reason":"","error_phon":""}
            JSONObject info=ParseJson.getSubjson(jsonObject,"info");
            initCell(new String[]{dataService[400]},pdfPTable);
            sandWish(new String[]{dataService[5],dataService[14],dataService[15],dataService[16]},
                    new String[]{info.getString("service_pro"),info.getString("error_phon"),
                            info.getString("handle_error"),info.getString("fix_reason")},
                    new String[]{"","","",""},pdfPTable);

            //维修费用
            JSONObject cost=ParseJson.getSubjson(jsonObject,"cost");
            initCell(new String[]{dataService[17]},pdfPTable);
            sandWish(new String[]{dataService[18],dataService[19],dataService[20],dataService[21]},
                    new String[]{cost.getString("Maintenance"),cost.getString("warr_inner"),
                            cost.getString("warr_out"),cost.getString("labor")},
                    new String[]{"","","",""},pdfPTable);
            sandWish(new String[]{dataService[22],dataService[23],dataService[24],dataService[25]},
                    new String[]{cost.getString("materal"),cost.getString("travel"),
                            cost.getString("transport"),cost.getString("sum_cost")},
                    new String[]{"","","",""},pdfPTable);


            //其他信息
            initCell(new String[]{dataService[300]},pdfPTable);
            document.add(pdfPTable);


            document.close();

            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // response.setContentType("application/pdf");
        //Document document = new Document(PageSize.A4.rotate());
        return fileName;
    }

}
