package homework;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class Runner {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String folderPath = scanner.nextLine().trim();
        scanner.close();

        File folder = new File(folderPath);
        File[] classFiles = folder.listFiles((dir, name) -> name.endsWith(".class"));

        URLClassLoader loader = new URLClassLoader(new URL[]{folder.toURI().toURL()});

        List<Class<?>> classes = new ArrayList<>();
        Set<Class<?>> annotations = new HashSet<>();

        for (File f : classFiles) {
            Class<?> c = loader.loadClass(f.getName().replace(".class", ""));
            classes.add(c);
            if (c.isAnnotation()) annotations.add(c);
        }

        System.out.println("Annotations: " + annotations);

        //clase publice care nu sunt adnotari si afisam informatii
        for (Class<?> c : classes) {
            if (!Modifier.isPublic(c.getModifiers()) || c.isAnnotation()) continue;

            System.out.println("\n" + c);
            for (Field f : c.getDeclaredFields())
                System.out.println("  field:  " + f);
            for (Constructor<?> ct : c.getDeclaredConstructors())
                System.out.println("  constructor:   " + ct);
            for (Method m : c.getDeclaredMethods())
                System.out.println("  method: " + m);


            Object instance = c.getDeclaredConstructor().newInstance();
            for (Method m : c.getDeclaredMethods()) {
                //verifica daca contine o adnotare dintre cele gasite mai sus
                boolean isAnnotated = Arrays.stream(m.getDeclaredAnnotations())
                        .anyMatch(a -> annotations.contains(a.annotationType()));
                if (!isAnnotated) continue;

                m.setAccessible(true);
                Parameter[] params = m.getParameters();

                if (params.length == 0) {
                    System.out.println(m.getName() + "() => " + m.invoke(instance));
                } else if (params.length == 1 && params[0].getType() == int.class) {
                    System.out.println(m.getName() + "(42) => " + m.invoke(instance, 42));}
            }
        }
    }
}