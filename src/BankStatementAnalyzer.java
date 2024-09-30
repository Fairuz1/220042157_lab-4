import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class BankStatementAnalyzer {

    public double calculateTotalProfitOrLoss(List<Transaction> transactions) {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public long countTransactionsInMonth(List<Transaction> transactions, Month month) {
        return transactions.stream().filter(t -> t.getDate().getMonth() == month).count();
    }

    public List<Transaction> getTop10Expenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparingDouble(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public String getMostSpentCategory(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(Transaction::getDescription, Collectors.summingDouble(Transaction::getAmount)))
                .entrySet()
                .stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("No expenses");
    }
}

