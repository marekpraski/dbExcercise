package dboperator;

import dbmodel.School;
import org.json.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class JsonReader {
    private String readJsonFile(String fileName) {
        // The name of the file to open

        // This will reference one line at a time
        String line = null;
        String output = "";

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

// alternatively create a File object first
//            File file = new File(fileName);
//            FileReader fileReader =new FileReader(file);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                output+=line;
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        return output;
    }
    public School schoolFromJson(String fileName) {
        String jsonText = readJsonFile(fileName);
        JSONObject obj = new JSONObject(jsonText);
        School school = new School();
        String id = obj.getJSONObject("school_details").getString("id");
        String name = obj.getJSONObject("school_details").getString("name");
        String address = obj.getJSONObject("school_details").getString("address");
        school.setName(name);
        school.setAddress(address);
        school.setId(Integer.parseInt(id));
        return school;
    }

    public void readJsonArray(String jsonText) {
        JSONObject obj = new JSONObject(jsonText);
        JSONArray arr = obj.getJSONArray("menuitem");
        for (int i=0; i<arr.length();i++) {
            System.out.println(arr.getJSONObject(i).getString("value"));
        }
    }
}


