/*
 * This file contains the CustomerDataHandler class which provides methods to analyze customer shopping data.
 * It includes functionalities to count transactions and unique customers by gender
 * , calculate total sales by gender, identify the most used payment method,
 * and find the day with the highest sales. 
 * The class also defines a nested CustomerRecord class to represent individual customer records.
 * The methods utilize Java Streams for efficient data processing.
 * To use this class, call the static methods with a list of CustomerRecord objects.
 * The CustomerRecord class contains fields such as invoice number
 * , customer ID, gender                    
 * , age, category, quantity, price, payment method, invoice date, and shopping mall.
 * The sales method in CustomerRecord calculates the total sales amount for that record.
 * Additionally, a utility method for formatting monetary values is provided.
 */
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CustomerDataHandler {

    public static class CustomerRecord {
        public final String invoiceNo;
        public final String customerId;
        public final String gender;
        public final int age;
        public final String category;
        public final int quantity;
        public final BigDecimal price;
        public final String paymentMethod;
        public final LocalDate invoiceDate;
        public final String shoppingMall;

        public CustomerRecord(String invoiceNo, String customerId, String gender, int age,
                              String category, int quantity, BigDecimal price,
                              String paymentMethod, LocalDate invoiceDate, String shoppingMall) {
            this.invoiceNo = invoiceNo;
            this.customerId = customerId;
            this.gender = gender;
            this.age = age;
            this.category = category;
            this.quantity = quantity;
            this.price = price;
            this.paymentMethod = paymentMethod;
            this.invoiceDate = invoiceDate;
            this.shoppingMall = shoppingMall;
        }

        public BigDecimal sales() {
            return price.multiply(BigDecimal.valueOf(quantity));
        }
    }

    // 2) population 
    public static Map<String, Long> transactionCountByGender(List<CustomerRecord> rows) {
        return rows.stream().collect(Collectors.groupingBy(r -> r.gender, Collectors.counting()));
    }

    // 2) unique customers
    public static Map<String, Long> uniqueCustomersByGender(List<CustomerRecord> rows) {
        Map<String, Integer> tmp = rows.stream().collect(Collectors.groupingBy(
                r -> r.gender,
                Collectors.mapping(r -> r.customerId, Collectors.collectingAndThen(Collectors.toSet(), Set::size))
        ));
        return tmp.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().longValue()));
    }

    // 3) total sales
    public static Map<String, BigDecimal> totalSalesByGender(List<CustomerRecord> rows) {
        return rows.stream().collect(Collectors.groupingBy(
                r -> r.gender,
                Collectors.reducing(BigDecimal.ZERO, CustomerRecord::sales, BigDecimal::add)
        ));
    }

    // 4) most used payment method
    public static String mostUsedPaymentMethod(List<CustomerRecord> rows) {
        return rows.stream()
                .collect(Collectors.groupingBy(r -> r.paymentMethod, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }

    // 5) day with most sales
    public static Map.Entry<LocalDate, BigDecimal> dayWithMostSales(List<CustomerRecord> rows) {
        Map<LocalDate, BigDecimal> byDay = rows.stream().collect(Collectors.groupingBy(
                r -> r.invoiceDate,
                Collectors.reducing(BigDecimal.ZERO, CustomerRecord::sales, BigDecimal::add)
        ));
        return byDay.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
    }

    // monetary formatting
    public static String money(BigDecimal v) {
        return NumberFormat.getNumberInstance(Locale.US).format(v);
    }
}
