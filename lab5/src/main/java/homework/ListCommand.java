package homework;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class ListCommand implements Command {
    private Catalog catalog;

    @Override
    public void execute() {
        catalog.display();
    }
}
