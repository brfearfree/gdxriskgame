package lv.dium.riskclient;

import java.util.ArrayList;

public class GameArea {
    public String x;
    public String y;
    public String str;
    public String id;
    public String color;
    public ArrayList<Integer> links = new ArrayList<Integer>();

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<Integer> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Integer> links) {
        this.links = links;
    }
}
