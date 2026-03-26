package homework;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command {
    private final Catalog catalog;
    private final String outputPath;

    public ReportCommand(Catalog catalog, String outputPath) {
        this.catalog = catalog;
        this.outputPath = outputPath;
    }

    @Override
    public void execute() {
        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_33);
            configuration.setClassForTemplateLoading(ReportCommand.class, "/");

            Template template = configuration.getTemplate("catalog.ftl");

            Map<String, Object> data = new HashMap<>();
            data.put("items", catalog.getItems());

            File output = new File(outputPath);
            try (Writer writer = new FileWriter(output)) {
                template.process(data, writer);
            }

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(output);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}