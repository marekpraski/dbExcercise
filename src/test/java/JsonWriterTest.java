import dbmodel.School;
import dboperator.JsonWriter;
import org.junit.Test;

public class JsonWriterTest {

    @Test
    public void writeToJsonFileTest(){
        School school = new School();
        school.setId(6);
        school.setAddress("test new address");
        school.setName("new set name");

        JsonWriter writer = new JsonWriter();
        writer.schoolToFile(school,"jtest.json");
    }

}
