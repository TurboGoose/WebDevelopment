package templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class PageGenerator {
    private static final String HTML_DIR = "template";
    private static PageGenerator generator;
    private final Configuration config;

    private PageGenerator() {
        this.config = new Configuration();
    }

    public static PageGenerator getInstance() {
        if (generator == null) {
            generator = new PageGenerator();
        }
        return generator;
    }

    public String getPage(String filename, Map<String, Object> data) {
        StringWriter writer = new StringWriter();
        try {
            Template template = config.getTemplate(HTML_DIR + File.separator + filename);
            template.process(data, writer);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}
