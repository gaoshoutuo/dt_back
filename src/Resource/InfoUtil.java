package Resource;

import DAO.TestInfo;
import Entity.InfoEntity;
import org.hibernate.Session;

import java.util.List;

public class InfoUtil implements TestInfo {
    @Override
    public void add(InfoEntity infoEntity) {
       HibUtil hibUtil= new HibUtil();
       hibUtil.openSession();
        Session session=hibUtil.getSession();
        /* InfoEntity infoEntityl=new InfoEntity();
       infoEntityl.setEmail("dtsj@126.com");
        infoEntityl.setName("dtsj");
        infoEntityl.setPwd("123456");*/
        session.save(infoEntity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public List<InfoEntity> query() {
        return null;
    }
}
