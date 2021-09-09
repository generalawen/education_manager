package com.awen.education.meau;

/**
 * @Author: awen
 * @Date: 2021/9/2 9:37
 */
public class Grade {
    private String name;
    private int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
