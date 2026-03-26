package homework;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class AddCommand implements Command {
    private Catalog catalog;
    private Item item;

    @Override
    public void execute() {
        catalog.add(item);
    }
}
