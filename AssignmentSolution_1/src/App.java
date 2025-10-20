/* 
 * This is a sample Java application that processes customer shopping data from a CSV file.
 * It reads the data, performs various analyses such as counting transactions and unique customers
 * by gender, calculating total sales by gender, identifying the most used payment method,      
 * and finding the day with the highest sales.
 * 
 * The application utilizes classes for reading CSV files and handling customer data records.
 * 
 * To run this application, ensure you have the necessary CSV file in the specified path
 * 
 */
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Determine CSV file path
        String csvPath = (args.length > 0) ? args[0] : "src/customer_shopping_data.csv";

        // 1) Read customer data from CSV
        List<CustomerDataHandler.CustomerRecord> rows = CsvReader.read(csvPath);
        System.out.println("1) Read customer data from CSV");
        System.out.println("   Loaded rows = " + rows.size());

        // 2) Population analysis by gender   
        Map<String, Long> tx = CustomerDataHandler.transactionCountByGender(rows);
        Map<String, Long> uniq = CustomerDataHandler.uniqueCustomersByGender(rows);
        System.out.println("\n2) Population grouped by gender");
        System.out.println("   Transactions: " + tx);
        System.out.println("   Unique customers: " + uniq);

        // 3) Total sales by gender
        Map<String, BigDecimal> salesByGender = CustomerDataHandler.totalSalesByGender(rows);
        System.out.println("\n3) Total sales grouped by gender");
        salesByGender.entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .forEach(e -> System.out.println("   " + e.getKey() + " -> $" + CustomerDataHandler.money(e.getValue())));

        // 4) Most used payment method
        String mostUsed = CustomerDataHandler.mostUsedPaymentMethod(rows);
        System.out.println("\n4) Most used payment method");
        System.out.println("   " + mostUsed);

        // 5) Day with the most sales
        Map.Entry<LocalDate, BigDecimal> top = CustomerDataHandler.dayWithMostSales(rows);
        System.out.println("\n5) Day with the most sales");
        if (top != null) {
            System.out.println("   " + top.getKey() + " -> $" + CustomerDataHandler.money(top.getValue()));
        } else {
            System.out.println("   N/A");
        }
    }
}
