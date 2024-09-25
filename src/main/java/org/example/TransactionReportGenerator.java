package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {

    public static void printAllTransactions(List<Transaction> transactions) // --
    {
        for (Transaction transaction : transactions)
        {
            System.out.println(transaction);
        }
        System.out.println();
    }
    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance+"\n");
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count+"\n");
    }
    public static void printTopExpensesReport(List<Transaction> topExpenses)
    {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printExpensesByCategory(List<Transaction> transactions) //--
    {
        Map<String, Double> expensesByCategory = new HashMap<>();

        for (Transaction transaction : transactions)
        {
            if (transaction.getAmount() < 0)
            {
                String category = transaction.getDescription();
                double amount = transaction.getAmount();
                expensesByCategory.put(category, expensesByCategory.getOrDefault(category, 0.0) + amount);
            }
        }
        System.out.println("\nВитрати за категоріями:\n");
        printExpensesByKey(expensesByCategory);
    }

    public static void printExpensesByMonth(List<Transaction> transactions) //--
    {
        Map<String, Double> expensesByMonth = new HashMap<>();
        for (Transaction transaction : transactions)
        {
            if (transaction.getAmount() < 0)
            {
                LocalDate date = LocalDate.parse(transaction.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                String month = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
                double amount = transaction.getAmount();
                expensesByMonth.put(month, expensesByMonth.getOrDefault(month, 0.0) + amount);
            }
        }
        System.out.println("\nВитрати за місяцем:\n");
        printExpensesByKey(expensesByMonth);
    }

    private static void printExpensesByKey(Map<String, Double> expenses)//--
    {
        double totalAmount = 0;
        for (String key : expenses.keySet())
        {
            totalAmount = expenses.get(key);
            System.out.println(key + ": " + totalAmount + "\nВізуалізація: " + getVisualization(totalAmount, 1000));
        }

    }
    private static String getVisualization(double totalAmount, double scale)//--
    {
        int numberOfStars = (int) (totalAmount / scale) *-1;

        String stars = "";
        for(int i = 0; i < numberOfStars; i++)
        {
            stars += "*";
        }

        return stars;
    }

}
