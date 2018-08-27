package net.lezouave.test;

public class Animal {
    private String name;
    private int age;
    private String type;

    public Animal() {
        this.name = "";
        this.age = 0;
        this.type = "undefined";
    }

    public Animal(String p_name, int p_age, String p_type){
        this.name = p_name;
        this.age = p_age;
        this.type = p_type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
