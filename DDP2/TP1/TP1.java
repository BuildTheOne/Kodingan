package DDP2.TP1;

import java.util.Scanner;

public class TP1 {
    public static String extractNPM(long npm) {
        // This method is to change NPM data type from long to string so it could be
        // converted easily
        String npmInput = Long.toString(npm);
        return npmInput;
    }

    public static String yearExtractor(long npm) {
        // This method is used to get the entry year
        String npmInput = "20" + extractNPM(npm).substring(0, 2);
        return npmInput;
    }

    public static boolean yearValidation(long npm) {
        // This method is used to validate the student's age;
        // if it's more than 15 return true, else false
        int npmYear = Integer.parseInt(extractNPM(npm).substring(0, 2)); // Entry year
        int npmBirthYear = Integer.parseInt(extractNPM(npm).substring(10, 12)); // Birth Year
        // This conditional is used to subtract the year to get the age
        if ((npmYear - npmBirthYear) > 15) {
            return true;
        }
        return false;
    }

    public static String majorExtractor(long npm) {
        // This method is to get the student's major using switch case
        // If the digit is not registered, it would return blank string and enter the
        // majorValidation
        String major = "";
        String npmInput = extractNPM(npm).substring(2, 4);
        switch (npmInput) {
        case "01":
            major = "Ilmu Komputer";
            break;
        case "02":
            major = "Sistem Informasi";
            break;
        case "03":
            major = "Teknologi Informasi";
            break;
        case "11":
            major = "Teknik Telekomunikasi";
            break;
        case "12":
            major = "Teknik Elektro";
            break;
        default:
            break;
        }
        return major;
    }

    public static boolean majorValidation(long npm) {
        // This method is used to determined if the major existed
        // If the majorExtracted return blank string, return false, else true
        String major = majorExtractor(npm);
        if (!major.equals("")) {
            return true;
        }
        return false;
    }

    public static String dateExtractor(long npm) {
        // This method is used to get the birth date
        // Because the input is would always true, there is no need for date validation
        // method
        String npmInput = extractNPM(npm).substring(4, 12);
        npmInput = npmInput.substring(0, 2) + "-" + npmInput.substring(2, 4) + "-" + npmInput.substring(4);
        return npmInput;
    }

    public static boolean validateNPM(long npm) {
        // This method is validate NPM digits
        // NPM validated by using for loop and while loop and compared with last digit
        String npmInput = extractNPM(npm); // Change the NPM to string
        int npmLastDigit = Integer.parseInt(extractNPM(npm).substring(13)); // Take the NPM Last digit
        // For loop below is for get the sum of NPM digit according the rules;
        // Product of 1 and 13, 2 and 12, 3 and 11, 4 and 10, 5 and 9, and 6 and 8 and
        // sum the all the result
        int sum = 0;
        for (int i = 0; i < 7; i++) {
            int a = Character.getNumericValue(npmInput.charAt(i)); // Get the number at specific index, i.e. 1, 2, etc
            int b = Character.getNumericValue(npmInput.charAt((12 - i))); // Get the number at specific index but the
                                                                          // opposite, i.e. 12 (12-0), 11 (12-1), etc.
            sum += a * b; // multiply the integer and sum it to the previous iteration
        }
        sum += Character.getNumericValue(npmInput.charAt(6)); // Then sum is added with seventh digit
        // This while loop is used if the sum is still not only 1 digit
        while (sum >= 10) {
            sum = sum / 10 + sum % 10;
        }
        // This conditional below is to compared the sum with the last digit, if it's
        // equals return true, else false
        if (sum == npmLastDigit) {
            return true;
        }
        return false;
    }

    public static boolean validate(long npm) {
        // This method is to validate all the validation method above
        String npmInput = extractNPM(npm);
        // 1. If the digit is not 14, return false
        if (npmInput.length() != 14) {
            return false;
        }
        // If the year is not valid return false
        if (!yearValidation(npm)) {
            return false;
        }
        // If the major is not valid return false
        if (!majorValidation(npm)) {
            return false;
        }
        // If the NPM digit calculation is not valid return false
        if (!validateNPM(npm)) {
            return false;
        }
        // If the conditionals above is not fulfilled, return true
        return true;
    }

    public static String extract(long npm) {
        // This method is to display the output based on extractor methods above
        return ("Tahun masuk: " + yearExtractor(npm) + "\n" + "Jurusan: " + majorExtractor(npm) + "\n"
                + "Tanggal Lahir: " + dateExtractor(npm));
    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        // While loop for unlimited input, already on template
        boolean exitFlag = false;
        while (!exitFlag) {
            long npm = input.nextLong();
            if (npm <= 0) {
                exitFlag = true;
                break;
            }
            // Conditional for validate the NPM method
            // If the NPM is valid, execute extract method, else return error message
            if (validate(npm)) {
                extract(npm);
            } else {
                System.out.println("NPM tidak valid!");
            }
        }

        input.close();
    }
}