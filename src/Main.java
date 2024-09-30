import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.time.Month;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the file path: ");
        String filePath = scanner.nextLine();

        // Determine file type based on the extension
        String fileType = filePath.substring(filePath.lastIndexOf('.') + 1).toLowerCase();

        try {
            // Get the appropriate parser using the factory
            TransactionParser parser = (TransactionParser) ParserFactory.getParser(fileType);

            // Parse the transactions from the file
            List<Transaction> transactions = parser.parse(filePath);

            // Create an instance of BankStatementAnalyzer to perform the analysis
            BankStatementAnalyzer analyzer = new BankStatementAnalyzer();

            // Analyze the total profit or loss
            double totalProfitOrLoss = analyzer.calculateTotalProfitOrLoss(transactions);
            System.out.println("Total profit or loss: " + totalProfitOrLoss);
            System.out.println(totalProfitOrLoss >= 0 ? "Profit" : "Loss");

            // Count transactions in a specific month (for example, January)
            System.out.println("Enter a month to count transactions (1 for January, 2 for February, etc.): ");
            int monthInput = scanner.nextInt();
            Month month = Month.of(monthInput);
            long countInMonth = analyzer.countTransactionsInMonth(transactions, month);
            System.out.println("Number of transactions in " + month + ": " + countInMonth);

            // Get the top 10 expenses
            List<Transaction> topExpenses = analyzer.getTop10Expenses(transactions);
            System.out.println("Top 10 Expenses: ");
            for (Transaction expense : topExpenses) {
                System.out.println(expense.getDescription() + ": " + expense.getAmount());
            }

            // Get the category with the most spending
            String mostSpentCategory = analyzer.getMostSpentCategory(transactions);
            System.out.println("Category with the most spending: " + mostSpentCategory);

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
