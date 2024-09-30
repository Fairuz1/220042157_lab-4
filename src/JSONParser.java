import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.ParseException;

public static void main(String[] args) {

    

    try {
        Object obj = parser.parse(new FileReader("c:\\file.json"));

        JSONObject jsonObject =  (JSONObject) obj;

        String date = (String) jsonObject.get("date");
        System.out.println(date);

        String amount = (String) jsonObject.get("amount");
        System.out.println(amount);

        String description = (String) jsonObject.get("description");
        System.out.println(description);


    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private static CSVParser parser;
