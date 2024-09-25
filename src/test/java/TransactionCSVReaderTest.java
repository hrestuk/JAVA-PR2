import org.example.Transaction;
import org.example.TransactionAnalyzer;
import org.example.TransactionCSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TransactionCSVReaderTest
{
    @Test
    public void testReadTransactions()
    {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        Assertions.assertNotNull(transactions, "Транзакції не були завантажені");

        Transaction firstTransaction = transactions.get(0);

        Assertions.assertEquals("05-12-2023", firstTransaction.getDate(), "Дата першої транзакції неправильна");
        Assertions.assertEquals("Сільпо", firstTransaction.getDescription(), "Категорія першої транзакції неправильна");
        Assertions.assertEquals(-7850.0, firstTransaction.getAmount(), "Сума першої транзакції неправильна");

    }

    @Test
    public void  findTopExpensesReport()
    {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        double[] expectedExpenses = {
                -9100.0,
                -8800.0,
                -7850.0,
                -7500.0,
                -6000.0,
                -5500.0,
                -4500.0,
                -3300.0,
                -3200.0,
                -3200.0
        };
        for (int i = 0; i < 10; i++) {

            Assertions.assertEquals(expectedExpenses[i], topExpenses.get(i).getAmount(), "Сума транзакції неправильна");
        }
    }
}