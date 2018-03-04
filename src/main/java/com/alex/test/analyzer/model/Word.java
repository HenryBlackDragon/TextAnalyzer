package com.alex.test.analyzer.model;

public class Word {

    private String name;
    private Long count;

    public Word(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Word{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
