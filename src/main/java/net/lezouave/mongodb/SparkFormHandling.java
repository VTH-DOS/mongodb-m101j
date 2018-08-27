package net.lezouave.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import net.lezouave.test.Animal;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

public class SparkFormHandling {

    public static void main(String[] args) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

        get("/", (req, res) -> {
            StringWriter sw = new StringWriter();

            try {
                Template fruitTemplate = configuration.getTemplate("fruitPicker.ftl");

                Map<String, Object> fruitsMap = new HashMap<String, Object>();
                fruitsMap.put("fruits", Arrays.asList("Pomme", "Orange", "Raisin", "Fraise"));

                fruitTemplate.process(fruitsMap, sw);

                return sw;

            } catch (Exception e) {
                halt(500);
                e.printStackTrace();
                return null;
            }
        });

        post("/favorite_fruit", (req, res) -> {
            StringWriter sw = new StringWriter();
            boolean hasResult = false;
            try {
                Template fruitTemplate = configuration.getTemplate("favoriteFruit.ftl");

                final String fruit = req.queryParams("fruit");

                if (fruit == null) {
                    hasResult = false;
                } else {
                    hasResult = true;
                }

                Map<String, Object> favoriteFruitMap = new HashMap<String, Object>();
                favoriteFruitMap.put("favoriteFruit", fruit);
                favoriteFruitMap.put("hasResult", hasResult);
                favoriteFruitMap.put("backLink", "/");
                favoriteFruitMap.put("objectsLink", "/objects");

                fruitTemplate.process(favoriteFruitMap, sw);

                return sw;
            } catch(Exception e) {
                halt(500);
                e.printStackTrace();
                return null;
            }
        });

        get("/objects", (req, res) -> {
            StringWriter sw = new StringWriter();
            Animal monAnimal = new Animal("Ibbo", 5, "chien");


            try {
                Template fruitTemplate = configuration.getTemplate("objects.ftl");

                Map<String, Object> fruitsMap = new HashMap<String, Object>();
                fruitsMap.put("myPet", monAnimal);
                fruitsMap.put("backLink", "/");

                fruitTemplate.process(fruitsMap, sw);

                return sw;

            } catch (Exception e) {
                halt(500);
                e.printStackTrace();
                return null;
            }
        });

    }

}
