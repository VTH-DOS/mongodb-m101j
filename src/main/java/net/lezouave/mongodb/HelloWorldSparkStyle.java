package net.lezouave.mongodb;

import static spark.Spark.*;


public class HelloWorldSparkStyle {

    public static void main(String[] args) {
        get("/", (req, res) -> {
            return "Hello World from Spark";
        });

        get("/test", (req, res) -> {
            return "This is a test page";
        });

        get("/echo/:thing", (req, res) -> {
            return req.params(":thing");
        });

    }
}