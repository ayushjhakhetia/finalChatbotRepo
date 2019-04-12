package com.impetus.rpacoordinator.model;

public class Basic {
    String name = " IMPETUS " ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Basic(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Basic [name=" + name + "]";
    }
    
}
