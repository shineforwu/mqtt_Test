package tool;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class IniRead {

    public static  String filePath= new File("").getAbsolutePath() + File.separator  + "config" + File.separator+"myini.ini";
    private static HashMap<String, String> itemsMap = new HashMap<String, String>();
    private static String currentSection = "";//节名称

    public static String read1(String file) throws Exception {
        Properties pro = new Properties();
        StringBuffer sb = new StringBuffer();
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        pro.load(in);
        Set keyValue = pro.keySet();
        for (Iterator it = keyValue.iterator(); it.hasNext();) {
            String key = (String) it.next();
            sb.append(key + ":" + pro.get(key) + "\n");
            // System.out.println(key + " "+ pro.get(key));
        }
        return sb.toString();

    }
    public static String read2(String file) throws Exception {
        StringBuffer sb = new StringBuffer();
        BufferedReader bf = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), "GB2312"));
        String line = null;
        while ((line = bf.readLine()) != null) {
            line = line.trim();

            if ("".equals(line))
                continue;
            if (line.startsWith("[") && line.endsWith("]")) {
                currentSection = line.substring(1, line.length() - 1);
                sb.append(currentSection+"\r\n");
            }else if(line.startsWith(";")){
                String str = "#"+line.substring(1);
                sb.append(str+"\r\n");
            }else {
                int index = line.indexOf("=");
                if (index != -1) {
                    String key = currentSection + "_"
                            + line.substring(0, index);
                    String value = line.substring(index + 1, line.length());
                    itemsMap.put(key, value);
                    sb.append(key+"="+value+"\r\n");
                }
            }
        }
        return sb.toString();

    }
    public static String read(String file,String key) throws Exception {
        Properties pro = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        pro.load(in);
        Set keyValue = pro.keySet();
        for (Iterator it = keyValue.iterator(); it.hasNext();) {
            if(key.equals((String) it.next()))
            {
                return pro.get(key).toString();
            }

        }
        return "";

    }


}
