import org.json.simple.parser.JSONParser;

public class ParserFactory {
    public static Object getParser(String fileType) {
        switch (fileType.toLowerCase()) {
            case "csv":
                return new CSVParser();
            case "json":
                return new JSONParser();
            case "xml":
                return new XMLParser();
            default:
                throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }
    }
}
