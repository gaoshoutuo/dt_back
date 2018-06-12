package Resource;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class FtpUtil {
    //安置文件 并且将文件路径返回给数据库

    public static void main(String []args){
       FtpUtil ftpUtil=new FtpUtil();
     /*  ftpUtil.reader();
        ftpUtil.createfile();*/
     ftpUtil.writer();
    }
    public void reader(){
        //读取pdf文件
        PdfReader pdfReader = null;
        try {
            pdfReader = new PdfReader("d:/ftp/test.pdf");

           /* BaseFont bf = BaseFont.createFont("STSong-Light",
                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font font=new Font(baseFont);*/
            //修改器
            PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("d:/ftp/test2.pdf"));
            Image image = Image.getInstance("d:/ftp/1525829289815.jpg");
            image.scaleAbsolute(50, 50);
            image.setAbsolutePosition(0, 700);
            for(int i=1; i<= pdfReader.getNumberOfPages(); i++)
            {
                PdfContentByte content = pdfStamper.getUnderContent(i);
                content.addImage(image);
            }
            pdfStamper.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (BadElementException e1) {
            e1.printStackTrace();
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }
    }
    private void writer(){
                Document document = new Document(PageSize.A4, 80, 79, 20, 45);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:/ftp/test.pdf"));
            // BaseFont bf = BaseFont.createFont("STSong-Light",
            //                    "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); 无效
            BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font FontChinese = new Font(bfChinese);
            document.open();
            document.add(new Paragraph("解决中文问题了！",FontChinese));
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createfile(){
        File file=new File("d:/ftp/test3.pdf");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
