package homework;

public class Main {
    public static void main(String[] args) {
        Item item1 = new Item("knuth67", "The Art of Computer Programming", "C:\\Users\\User-PC\\Downloads\\class-diagram.png");
        Item item2 = new Item("jvm25", "The Java Virtual Machine Specification", "https://docs.oracle.com/javase/specs/jvms/se25/html/index.html");
        Item item3 = new Item("java25", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se25/jls25.pdf");

        Catalog catalog = new Catalog("catalog");

        new AddCommand(catalog,item1).execute();
        new AddCommand(catalog,item2).execute();
        new AddCommand(catalog,item3).execute();

        new ListCommand(catalog).execute();

       /* try {
            new ViewCommand(catalog, "knuth67").execute();
        } catch (InvalidCatalogException e) {
            System.out.println("Error: " + e.getMessage());
        }*/

        new ReportCommand(catalog, "catalog_report.html").execute();
    }

}

