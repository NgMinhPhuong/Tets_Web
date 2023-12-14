package com.codegym.demoservlet;

public class MyObject {
    int id;
    String name;

    public MyObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
