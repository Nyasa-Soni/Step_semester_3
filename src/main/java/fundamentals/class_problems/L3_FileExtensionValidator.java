package fundamentals.class_problems;

/**
 * Problem 3: File Extension Validator
 * 
 * Scenario:
 * An assignment-upload portal must check whether an uploaded filename has an accepted
 * extension (pdf, docx, zip) regardless of case, before accepting the submission.
 * 
 * Task:
 * - Accept a filename string.
 * - Find the last '.' using lastIndexOf('.') and extract the extension with substring().
 * - Compare the extension case-insensitively against the accepted list: pdf, docx, zip.
 * - Return "Accepted" or "Rejected — invalid file type".
 */
public class L3_FileExtensionValidator {

    /**
     * Validates if the file has an accepted extension (pdf, docx, zip).
     *
     * @param filename name of the file to validate
     * @return "Accepted" or "Rejected — invalid file type"
     */
    public static String validateFileExtension(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            return "Rejected \u2014 invalid file type";
        }

        // Find the last '.' in the filename
        int lastDotIndex = filename.lastIndexOf('.');

        // If no dot is found or dot is at the end of the filename
        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return "Rejected \u2014 invalid file type";
        }

        // Extract the extension
        String extension = filename.substring(lastDotIndex + 1);

        // Case-insensitive comparison against accepted extensions
        if (extension.equalsIgnoreCase("pdf") ||
            extension.equalsIgnoreCase("docx") ||
            extension.equalsIgnoreCase("zip")) {
            return "Accepted";
        }

        return "Rejected \u2014 invalid file type";
    }

    public static void main(String[] args) {
        // PDF Samples demonstration
        System.out.println("--- PDF Samples ---");
        String sample1 = "Assignment1.PDF";
        System.out.println("Input: \"" + sample1 + "\" -> " + validateFileExtension(sample1));

        String sample2 = "notes.txt";
        System.out.println("Input: \"" + sample2 + "\" -> " + validateFileExtension(sample2));

        // Edge case demonstrations
        System.out.println("\n--- Edge Cases ---");
        String sample3 = "project_archive.zip";
        System.out.println("Input: \"" + sample3 + "\" -> " + validateFileExtension(sample3));

        String sample4 = "thesis.DOCX";
        System.out.println("Input: \"" + sample4 + "\" -> " + validateFileExtension(sample4));

        String sample5 = "no_extension";
        System.out.println("Input: \"" + sample5 + "\" -> " + validateFileExtension(sample5));

        String sample6 = "trailing_dot.";
        System.out.println("Input: \"" + sample6 + "\" -> " + validateFileExtension(sample6));

        String sample7 = "multi.dot.archive.tar.gz";
        System.out.println("Input: \"" + sample7 + "\" -> " + validateFileExtension(sample7));
    }
}
