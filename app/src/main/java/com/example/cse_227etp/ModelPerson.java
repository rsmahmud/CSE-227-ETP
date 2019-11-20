package com.example.cse_227etp;

public class ModelPerson {
    String name;
    String reg;

    ModelPerson() {
    }

    ModelPerson(String name, String reg) {
        this.name = name;
        this.reg = reg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }
}
