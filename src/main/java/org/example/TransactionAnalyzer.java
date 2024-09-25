package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;


public abstract class TransactionAnalyzer {

    // Метод для розрахунку загального балансу
    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public static int countTransactionsByMonth(String monthYear, List<Transaction> transactions) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0) // Вибірка лише витрат (від'ємні значення)
                .sorted(Comparator.comparing(Transaction::getAmount)) // Сортування за сумою
                .limit(10) // Обмеження результату першими 10 записами
                .collect(Collectors.toList()); // Збір результату в список
    }

    public static Transaction[] findMinMaxExpensesInPeriod(List<Transaction> transactions, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        Transaction maxExpense = null;
        Transaction minExpense = null;

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) { // Перевірка на витрати
                LocalDate transactionDate = LocalDate.parse(transaction.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                if (!transactionDate.isBefore(start) && !transactionDate.isAfter(end)) {
                    // Визначаємо максимальну і мінімальну витрату
                    if (minExpense == null || transaction.getAmount() > minExpense.getAmount()) {
                        minExpense = transaction;
                    }
                    if (maxExpense == null || transaction.getAmount() < maxExpense.getAmount()) {
                        maxExpense = transaction;
                    }
                }
            }
        }

        return new Transaction[]{minExpense, maxExpense};
    }
}

