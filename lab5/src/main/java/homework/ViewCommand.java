package homework;

import lombok.AllArgsConstructor;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@AllArgsConstructor

public class ViewCommand implements Command {
    private Catalog catalog;
    private String id;

    @Override
    public void execute() throws InvalidCatalogException {
        Item item = catalog.findById(id);

        if (item == null) {
            System.out.println("Item not found: " + id);
            return;
        }

        if (!Desktop.isDesktopSupported()) {
            System.out.println("Desktop not supported!");
            return;
        }

        try {
            String location = item.getLocation();
            if (location.startsWith("http://") || location.startsWith("https://")) {
                Desktop.getDesktop().browse(new URI(location));
            } else {
                Desktop.getDesktop().open(new File(location));
            }

        }
        catch (IOException | URISyntaxException e) {
            throw new InvalidCatalogException(e.getMessage());
        }
    }
}
