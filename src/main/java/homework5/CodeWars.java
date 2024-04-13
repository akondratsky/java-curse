package homework5;

public class CodeWars {
    public static void main(String[] args) {
        sayHi(new String[]{"John", "Smith", "Second"}, "Phoenix", "Arizona");
        System.out.println(dduppliccatte("hello"));
    }
    public static void sayHi(String[] name, String city, String state) {
        String fullName = String.join(" ", name);
        System.out.printf("Hello, %s! Welcome to %s, %s!%n", fullName, city, state);
    }

    public static String dduppliccatte(String s) {
        // it's much faster than with StringBuilder, right?
        char[] chars = new char[s.length() * 2];
        for (int i = 0; i < s.length(); i++) {
            chars[i * 2] = s.charAt(i);
            chars[i * 2 + 1] = s.charAt(i);
        }
        return String.valueOf(chars);
    }
}
