import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class XMLParser implements TransactionParser {
    @Override
    public List<Transaction> parse(String filePath) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(filePath));
            NodeList nodeList = doc.getElementsByTagName("transaction");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    LocalDate date = LocalDate.parse(element.getElementsByTagName("date").item(0).getTextContent(), formatter);
                    double amount = Double.parseDouble(element.getElementsByTagName("amount").item(0).getTextContent());
                    String description = element.getElementsByTagName("description").item(0).getTextContent();
                    transactions.add(new Transaction(date, amount, description));
                }
            }
        } catch (Exception e) {
            throw new IOException("Error parsing XML", e);
        }

        return transactions;
    }
}
