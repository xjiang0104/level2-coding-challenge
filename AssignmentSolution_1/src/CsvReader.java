/*
 * This file contains a CSV reader utility to read customer shopping data from a CSV file.
 * It handles parsing of various data types including dates with inconsistent formats.
 * The reader returns a list of CustomerRecord objects representing each row in the CSV.
 * To use this utility, call the read method with the path to the CSV file.
 * The CSV file is expected to have a header row which will be skipped during reading.
 * The date formats supported are M/d/uuuu, MM/dd/uuuu, d/M/uuuu, and dd/MM/uuuu.
 * Each line in the CSV should contain the following fields:
 * invoice_no, customer_id, gender, age, category, quantity, price, payment_method, invoice_date, shopping_mall 
 * The parsed CustomerRecord objects can then be used for further analysis.
 */
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;

public class CsvReader {
    // date data format inconsistent
    private static final DateTimeFormatter[] DATE_FORMATS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("M/d/uuuu").withResolverStyle(ResolverStyle.SMART),
            DateTimeFormatter.ofPattern("MM/dd/uuuu").withResolverStyle(ResolverStyle.SMART),
            DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.SMART),
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.SMART)
    };

    public static List<CustomerDataHandler.CustomerRecord> read(String csvPath) throws IOException {
        return Files.lines(Path.of(csvPath))
                .skip(1) // skip first line (field names)
                .filter(line -> !line.isBlank())
                .map(CsvReader::parse)
                .toList();
    }

    private static CustomerDataHandler.CustomerRecord parse(String line) {
        // invoice_no, customer_id, gender, age, category,
        // quantity, price, payment_method, invoice_date, shopping_mall
        String[] c = line.split(",", -1);
        if (c.length < 10) throw new IllegalArgumentException("Bad CSV line: " + line);

        String invoiceNo     = c[0].trim();
        String customerId    = c[1].trim();
        String gender        = c[2].trim();
        int    age           = Integer.parseInt(c[3].trim());
        String category      = c[4].trim();
        int    quantity      = Integer.parseInt(c[5].trim());
        BigDecimal price     = new BigDecimal(c[6].trim());
        String paymentMethod = c[7].trim();
        LocalDate invoiceDate= parseFlexibleDate(c[8].trim());
        String shoppingMall  = c[9].trim();

        return new CustomerDataHandler.CustomerRecord(
                invoiceNo, customerId, gender, age, category,
                quantity, price, paymentMethod, invoiceDate, shoppingMall
        );
    }

    private static LocalDate parseFlexibleDate(String s) {
        // parse format one by one
        for (DateTimeFormatter f : DATE_FORMATS) {
            try { return LocalDate.parse(s, f); }
            catch (Exception ignore) { }
        }
        // If not format matched, throw exception
        throw new IllegalArgumentException("Unrecognized date: " + s);
    }
}
