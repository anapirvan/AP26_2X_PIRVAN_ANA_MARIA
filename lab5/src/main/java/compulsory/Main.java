package compulsory;

import java.awt.*;
import java.net.URI;

public class Main {
    public static void main(String[] args) {
        Item item1=new Item("knuth67", "The Art of Computer Programming", "d:/books/programming/tacp.ps");
        Item item2=new Item("jvm25", "The Java Virtual Machine Specification", "https://docs.oracle.com/javase/specs/jvms/se25/html/index.html");
        Item item3=new Item("java25", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se25/jls25.pdf");

        Catalog catalog=new Catalog("catalog");
        catalog.add(item1);
        catalog.add(item2);
        catalog.add(item3);

        catalog.display();

        openResource(catalog.getItems().get(2).getLocation());
    }
    public static void openResource(String resource){
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        try{
            desktop.browse(new URI(resource));
        }
        catch (Exception exception){
            System.out.println("Couldn't open: "+resource);
            exception.printStackTrace();
        }
    }
}
