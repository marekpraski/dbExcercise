package dboperator;

import dbmodel.School;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonWriter {

    JSONObject jsonObject;

    private JsonWriter() {
        jsonObject = new JSONObject();
        ;
    }

    /**
     * converts School object to json object
     *
     * @param school
     */
    private void schoolToJson(School school) {
        jsonObject.put("name", school.getName());
        jsonObject.put("address", school.getAddress());
        jsonObject.put("id", school.getId());
    }

    public void schoolToFile(School school, String fileName) {
        schoolToJson(school);
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            String jsonText = jsonObject.toString();
            fileWriter.write(jsonText);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String text) {
        try {
            FileWriter fileWriter = new FileWriter("test11.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(text);
            printWriter.close();
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
