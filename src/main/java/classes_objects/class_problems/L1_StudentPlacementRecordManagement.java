package classes_objects.class_problems;

import java.util.Locale;

/**
 * Problem M1: Student Placement Record Management
 *
 * OOP Concepts Tested:
 * - Class definition
 * - Instance fields
 * - Constructor initialization
 * - Instance methods
 * - Replacing parallel arrays with an array of objects (PlacementRecord[])
 *
 * Description:
 * The Training & Placement (T&P) cell tracks student placements.
 * Instead of maintaining 3 separate parallel arrays (names, companies, packages)
 * which easily go out of sync, we encapsulate these attributes into a PlacementRecord
 * class and manage an array of PlacementRecord objects.
 */
class PlacementRecord {
    String studentName;
    String company;
    double packageLpa;

    /**
     * Constructor initializing all three placement record fields.
     *
     * @param studentName Name of the placed student
     * @param company     Name of the recruiting company
     * @param packageLpa  Salary package in LPA
     */
    public PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    /**
     * Instance method that prints the placement record in the standard format:
     * <studentName> -> <company> @ <packageLpa> LPA
     */
    public void printRecord() {
        System.out.printf(Locale.US, "%s -> %s @ %.1f LPA%n", studentName, company, packageLpa);
    }
}

public class L1_StudentPlacementRecordManagement {

    public static void main(String[] args) {
        System.out.println("--- Problem M1: Student Placement Record Management ---");
        System.out.println("--- PDF Sample Test Cases ---");

        // Create three PlacementRecord objects and store them in an array
        PlacementRecord[] records = {
            new PlacementRecord("Ravi", "TCS", 4.5),
            new PlacementRecord("Anitha", "Zoho", 6.2),
            new PlacementRecord("Karthik", "Infosys", 4.0)
        };

        // Print each record using a loop
        for (PlacementRecord record : records) {
            record.printRecord();
        }

        System.out.println();
        System.out.println("--- Additional Edge Cases ---");
        // Edge Case: Array with single record, high package, and round values
        PlacementRecord[] additionalRecords = {
            new PlacementRecord("Sneha", "Google", 32.5),
            new PlacementRecord("Vikas", "Microsoft", 28.0)
        };
        for (PlacementRecord record : additionalRecords) {
            record.printRecord();
        }
    }
}
