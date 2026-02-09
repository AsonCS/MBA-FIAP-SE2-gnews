package com.gnews.fake;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * This class is intentionally filled with code smells and security issues
 * to test SonarQube integration and reporting.
 */
public class SonarIssueSandbox {

    // 1. Blocker: Hardcoded password
    private static final String DB_PASSWORD = "super-secret-password-123";

    // 8. Critical: Static SimpleDateFormat is not thread-safe
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public void processData(String userInput) {
        // 5. Minor: Unused local variable
        int unusedVariable = 42;

        try {
            // 2. Critical: Potential SQL Injection (concatenating user input into query)
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "admin", DB_PASSWORD);
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM users WHERE name = '" + userInput + "'";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("username"));
            }
        } catch (Exception e) {
            // 6. Minor: Empty catch block
        }

        // 3. Major: Use of deprecated API (Date.getYear() is deprecated since Java 1.1)
        Date now = new Date();
        int year = now.getYear();
        System.out.println("Current year: " + year);

        // 4. Major: Potential Null Pointer Exception
        String potentiallyNull = getNullValue();
        System.out.println("Length: " + potentiallyNull.length());

        // 7. Info: TODO comment
        // TODO: This method should be refactored to use a more secure way of handling
        // data

        complexMethod(10, true);
    }

    private String getNullValue() {
        return null;
    }

    // 9. Major: High Cognitive Complexity
    public void complexMethod(int limit, boolean flag) {
        if (limit > 0) {
            for (int i = 0; i < limit; i++) {
                if (flag) {
                    for (int j = 0; j < i; j++) {
                        if (j % 2 == 0) {
                            System.out.println("Even");
                        } else {
                            System.out.println("Odd");
                        }
                    }
                } else {
                    if (i % 2 == 0) {
                        System.out.println("Outer Even");
                    }
                }
            }
        } else if (limit == -1) {
            System.out.println("Special case");
        } else {
            System.out.println("Negative");
        }
    }

    // 10. Minor: Naming convention violation (Method name starting with uppercase
    // or having underscores)
    public void This_Is_A_Bad_Method_Name() {
        System.out.println("Hello world");
    }

    // Unused method to add another smell
    private void uselessMethod() {
        List<String> list = new ArrayList<>();
        list.add("test");
    }
}
