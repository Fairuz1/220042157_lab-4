import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVParser implements TransactionParser {
    public List<Transaction> parse(FileReader filePath) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(String.valueOf(filePath)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (String line : lines) {
            String[] tokens = line.split(",");
            LocalDate date = LocalDate.parse(tokens[0], formatter);
            double amount = Double.parseDouble(tokens[1]);
            String description = tokens[2];
            transactions.add(new Transaction(date, amount, description));
        }

        return transactions;
    }

    @Override
    public List<Transaction> parse(String filePath) throws IOException {
        return List.of();
    }
}
