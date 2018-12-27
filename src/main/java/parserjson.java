import com.codesnippets4all.json.parsers.JsonParserFactory;
import com.codesnippets4all.json.parsers.JSONParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class parserjson {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Use two arguments: first is for file, which will be parsed and second for output data");
            return;
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("File of that path " + args[0] + " doesn't exist;");
            return;
        }


        String strOfFile = readFile(args[0], Charset.defaultCharset());


        JsonParserFactory factory = JsonParserFactory.getInstance();


        JSONParser parser = factory.newJsonParser();


        Map jsonData = parser.parseJson(strOfFile);

        List persons = (List) jsonData.get("persons");

        WriteToFile(args[1],persons);
    }

    private static void WriteToFile(String fileOutputPath, List persons) throws IOException {
        FileWriter writer = new FileWriter(fileOutputPath, false);
        String buf = "";
        for (int i = 0; i < persons.size(); i++) {
            Map person = (Map)persons.get(i);
            buf += person.get("name") + " " +  person.get("age") + " " + person.get("city") + "\r\n";
        }
        writer.write(buf);
        System.out.println(buf);
        writer.flush();
    }

    private static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
