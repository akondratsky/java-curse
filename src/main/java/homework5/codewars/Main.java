package homework5.codewars;

public class Main {
    public static void welcomeUser(String[] name, String city, String state) {
        String fullName = String.join(" ", name);
        System.out.printf("Hello, %s! Welcome to %s, %s!%n", fullName, city, state);
    }

    public static String dduplicateString(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c).append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Task 1:");
        welcomeUser(new String[] {"John", "Smith"}, "Phoenix", "Arizona");

        System.out.println("Task 2:");
        System.out.println(dduplicateString("String"));
        System.out.println(dduplicateString("Hello World"));
        System.out.println(dduplicateString("1234!_ "));
    }
}
