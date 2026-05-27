package compulsory;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String className = scanner.nextLine().trim();

        try {
            Class<?> clasa = Class.forName(className);
            Method method = clasa.getDeclaredMethod("run");
            method.setAccessible(true);
            Object object = clasa.getDeclaredConstructor().newInstance();

            method.invoke(object);
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        scanner.close();
    }
}
