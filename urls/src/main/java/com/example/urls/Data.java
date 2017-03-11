package com.example.urls;

import java.util.List;

/**
 * Created by maodi on 2017/3/10.
 */

public class Data {

    private int id;
    private String title;
    private int type;
    private List<Children> children;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }
    public List<Children> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", children=" + children +
                '}';
    }
}
