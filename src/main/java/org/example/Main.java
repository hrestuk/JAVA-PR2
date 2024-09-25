package org.example;

import java.util.List;


public class Main
{
    public static void main(String[] args)
    {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        TransactionReportGenerator.printAllTransactions(transactions); // --

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(monthYear, transactions);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        // --
        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        String startDate = "05-12-2023";
        String endDate = "30-01-2024";
        Transaction[] minMaxExpenses = TransactionAnalyzer.findMinMaxExpensesInPeriod(transactions,startDate, endDate);
        Transaction minExpense = minMaxExpenses[0];
        Transaction maxExpense = minMaxExpenses[1];

        System.out.println("\nНайменщі та найбільші витрати за період " + startDate + " - " + endDate);
        System.out.println("Найменша витрата: " + minExpense.getAmount() + " (" + minExpense.getDate() + ")");
        System.out.println("Найбільша витрата: " + maxExpense.getAmount() + " (" + maxExpense.getDate() + ")");

        //TransactionReportGenerator.printExpensesByCategory(transactions);
        //TransactionReportGenerator.printExpensesByMonth(transactions);
    }
}