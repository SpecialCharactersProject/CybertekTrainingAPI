package utilities;

import java.util.HashMap;
import java.util.Map;

public class TempStorage {

    private static Map<String, String> data = new HashMap<>();

    private static Map<String, Map<String, String>> dbMap=new HashMap<>();

    public static void addData(String key, String value) {
        data.put(key, value);
    }

    public static String getData (String key) {
        return data.get(key);
    }

    public static void clear() {
        data.clear();
    }

    public static void addMap (String key, Map<String, String> map){ dbMap.put(key, map);  }

    public static Map<String, String> getMap(String key){

        return dbMap.get(key);
    }




}
