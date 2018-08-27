package net.lezouave.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.halt;

public class HelloFreemarkerSparkStyle {

    public static void main(String[] args) {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(HelloFreemarkerStyle.class, "/");

        get("/", (req, res) -> {
            StringWriter sw = new StringWriter();

            try {
                Template helloTemplate = configuration.getTemplate("hello.ftl");

                Map<String, Object> helloMap = new HashMap<String, Object>();
                helloMap.put("name", "John DOE");

                helloTemplate.process(helloMap, sw);

                System.out.println(sw);

            } catch (Exception e) {
                halt(500);
                e.printStackTrace();
            }

            return sw;
        });
    }

}
