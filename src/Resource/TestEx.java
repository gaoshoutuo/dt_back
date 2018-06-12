package Resource;



import java.util.ArrayList;
import java.util.Scanner;

public class TestEx {

    public static void main(String []args){
       /* Scanner scanner=new Scanner(System.in);
       int x= scanner.nextInt();

       pow(x,3);
       System.out.println( pow(x,2));
        System.out.println( pow2(x,2));
        System.out.println( pow3(x,2));*/
        ArrayList<String>list=new ArrayList<>();
        //list.add(2,"爱国");
      /*  list.set(3,"敬业");
        list.set(4,"奉献");
        list.set(5,"民主");
        list.set(2,"核心价值观");*/
       for(String str:list){
           System.out.println(str);
       }
       String []data=new String[]{"12","34","45","78","90"};
       int []cac={5,4,3,2,1};
       for(int i=0;i<3;i++){
           data[i++]+="||";//34|| 34 45 78 90
          // data[i]+="||";
       }

      /*  for(int i=0;i<3;i++){
            cac[i++]+=2;//32545
            // data[i]+="||";
        }*/
      int i=2;//没错呀;
        cac[i++]+=2;//12365  54341

       /* for(int i=0;i<=2;i++){
            data[i++]=data[i++]+"II";
        }*/
        for(i=0;i<data.length;i++){
           System.out.println("str"+data[i]+"........"+cac[i]);
        }
    }
    public static long pow(long x,int n){
        if(n==0)
            return 1;
        if(n%2==0)
          return   pow(x*x,n/2);
            //return pow(pow(x,2),n/2);
        else
          return   pow(x*x,n/2)*x;
    }
    public static long pow2(long x,int n){
        if(n==0)
            return 1;
        if (n==2)
        return x*x;
        if(n%2==0)
            return pow2(pow2(x,2),n/2);
        else
            return   pow2(x*x,n/2)*x;
    }

    public static long pow3(long x,int n){
        if(n==0)
            return 1;
        if(n==2)
            return x*x;
        if(n%2==0)

            return pow3(pow3(x,n/2),2);
        else
            return   pow3(x*x,n/2)*x;
    }

 /*   public void add(Ups表测试Entity infoEntity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();

        session.save(infoEntity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    public static void main(String[]args){
        Ups表测试Entity avv=new Ups表测试Entity();
        avv.set名字("名字");
        avv.set密码("9527");
        avv.set用户名("124578");
        avv.set身份("大老板");
        //果然 不能用中文做表头
        new TestEx().add(avv);
    }*/

}
