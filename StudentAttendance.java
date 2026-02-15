import java.nio.channels.Pipe.SourceChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentAttendance {
    public static void main(String[] args) {
        // --- Class Attendance Matrix ---
        // Columns: studentId, attendancePercentage, name
        String[][] classAttendance = {
                { "S101", "92", "Alice" },
                { "S102", "35", "Bob" },
                { "S103", "78", "Charlie" },
                { "S104", "54", "Diana" },
                { "S106", "95", "Ethan" }
        };

        // --- Library Attendance Matrix ---
        // Columns: studentId, noOfHoursInLibrary
        String[][] libraryAttendance = {
                { "S102", "6" },
                { "S106", "7" },
                { "S101", "10" },
                { "S104", "9" },
                { "S110", "11" }
        };

        // --- Ideal Library Hours ---
        int idealHours = 8;

        // --- Display Data ---
        System.out.println("=== CLASS ATTENDANCE MATRIX ===");
        System.out.printf("%-15s %-25s %-20s%n", "Student ID", "Attendance %", "Name");
        for (String[] row : classAttendance) {
            System.out.printf("%-15s %-25s %-20s%n", row[0], row[1], row[2]);
        }

        System.out.println("\n=== LIBRARY ATTENDANCE MATRIX ===");
        System.out.printf("%-15s %-20s%n", "Student ID", "Library Hours");
        for (String[] row : libraryAttendance) {
            System.out.printf("%-15s %-20s%n", row[0], row[1]);
        }

        System.out.println("\nIdeal Library Hours: " + idealHours);
        solve(classAttendance.length, libraryAttendance.length, classAttendance, libraryAttendance, idealHours);
    }

    private static void solve(int x, int y, String[][] classAttendance, String[][] libraryAttendance, int idealHours) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < x; i++) {
            if (Integer.parseInt(classAttendance[i][1]) >= 75) {
                map.put(classAttendance[i][0], classAttendance[i][2]);
            }
        }

        // List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int j = 0; j < y; j++) {
            int curHour = Integer.parseInt(libraryAttendance[j][1]);
            double perc = ((double) curHour / (double) idealHours) * 100;

            if (map.containsKey(libraryAttendance[j][0])) {
                if (perc >= 75) {
                    // String name = map.get(libraryAttendance[j][0]);
                    // ans.add(name);
                    set.add(libraryAttendance[j][0]);
                }
            }
        }

        /*
         * for (int k = 0; k < ans.size(); k++) {
         * System.out.print(ans.get(k));
         * if (k < ans.size() - 1) {
         * System.out.print("-");
         * }
         * }
         */

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> mpp : map.entrySet()) {
            String key = String.valueOf(mpp.getKey());
            String val = String.valueOf(mpp.getValue());
            if (set.contains(key)) {
                sb.append(val);
                sb.append("-");
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());

    }
}
