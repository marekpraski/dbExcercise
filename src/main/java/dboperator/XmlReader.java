package dboperator;

import dbmodel.School;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class XmlReader {
    public XmlReader() {

    }
    private Map<String,String> xmlData;

    private void readXml(String path) {
        xmlData=new HashMap();
        try {
            File file = new File(path);
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.loadFromXML(fileInput);
            fileInput.close();

            Set<Object> keys = properties.keySet();
            for (Object key : keys) {
                String keyStr = (String) key;
                String value = properties.getProperty(keyStr);
                xmlData.put(keyStr,value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public School readSchoolData(String path) {
        School school = new School();
        readXml(path);
        school.setAddress(xmlData.get("address"));
        school.setName(xmlData.get("name"));

        return school;
    }
}
