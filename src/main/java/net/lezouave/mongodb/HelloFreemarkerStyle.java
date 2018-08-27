package net.lezouave.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HelloFreemarkerStyle {
    public static void main(String[] args) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(HelloFreemarkerStyle.class, "/");

        try {
            Template helloTemplate = configuration.getTemplate("hello.ftl");

            StringWriter sw = new StringWriter();

            Map<String, Object> helloMap = new HashMap<String, Object>();
            helloMap.put("name", "John DOE");

            helloTemplate.process(helloMap, sw);

            System.out.println(sw);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
