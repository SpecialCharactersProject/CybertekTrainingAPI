package apiModels;

import javafx.scene.effect.SepiaTone;
import org.junit.Assert;
import utilities.DBUtility;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestClass {

    public static void main(String[] args) throws SQLException {

        Map<Object, Object> dbMap=new HashMap<>();

        DBUtility.createConnection();

        List<Map<Object, Object>> teacherDataList = DBUtility.executeQuery("select * from teacher");
        dbMap= teacherDataList.get(0);

        System.out.println(dbMap.get("TEACHER_ID"));


//        Set<Object> keys=dbMap.keySet();
//        for(Object key:keys){
//        System.out.println("Keys " + key);
//        }



        DBUtility.close();
    }

}
