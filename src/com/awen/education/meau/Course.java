package com.awen.education.meau;

/**
 * @Author: awen
 * @Date: 2021/9/2 9:32
 */
public class Course {
    private int number;
    private String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
