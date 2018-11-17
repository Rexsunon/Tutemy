package com.atornel.tutemy.Model.CatModel;

public class Cury {
    public String name;
    public String href;
    public boolean templated;

    public Cury(String name, String href, boolean templated) {
        this.name = name;
        this.href = href;
        this.templated = templated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isTemplated() {
        return templated;
    }

    public void setTemplated(boolean templated) {
        this.templated = templated;
    }
}
