package fundamentals.class_problems;

/**
 * Problem 2: CSV Student Record Parser
 * 
 * Scenario:
 * The T&P team receives student registration data as CSV lines and needs a
 * quick parser to split each line into fields and print a formatted record.
 * 
 * Task:
 * - Accept a CSV line in the form "Name,RollNumber,Department".
 * - Use split(",") to break it into fields.
 * - Validate that exactly 3 fields are present; if not, print "Invalid Record".
 * - Print a formatted record: "Name: ... | Roll No: ... | Dept: ...".
 */
public class L2_CSVStudentRecordParser {

    /**
     * Parses and prints a CSV line containing student records.
     *
     * @param csvLine CSV formatted string "Name,RollNumber,Department"
     */
    public static void parseStudentRecord(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            System.out.println("Invalid Record");
            return;
        }

        // Use split(",") to separate the fields
        String[] fields = csvLine.split(",");

        // Validate that exactly 3 fields are present
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String name = fields[0].trim();
        String rollNumber = fields[1].trim();
        String department = fields[2].trim();

        if (name.isEmpty() || rollNumber.isEmpty() || department.isEmpty()) {
            System.out.println("Invalid Record");
            return;
        }

        // Print formatted record
        System.out.println("Name: " + name + " | Roll No: " + rollNumber + " | Dept: " + department);
    }

    public static void main(String[] args) {
        // PDF Samples demonstration
        System.out.println("--- PDF Samples ---");
        String sample1 = "Ananya Verma,RA2211003010123,CSE";
        System.out.println("Input: \"" + sample1 + "\"");
        parseStudentRecord(sample1);

        String sample2 = "Ananya Verma,CSE";
        System.out.println("Input: \"" + sample2 + "\"");
        parseStudentRecord(sample2);

        // Edge case demonstrations
        System.out.println("\n--- Edge Cases ---");
        String sample3 = "Rahul Sharma,RA2211003010456,IT,ExtraField";
        System.out.println("Input: \"" + sample3 + "\"");
        parseStudentRecord(sample3);

        String sample4 = "";
        System.out.println("Input: \"" + sample4 + "\"");
        parseStudentRecord(sample4);
    }
}
