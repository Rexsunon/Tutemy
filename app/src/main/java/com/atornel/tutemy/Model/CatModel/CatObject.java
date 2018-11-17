package com.atornel.tutemy.Model.CatModel;

import java.util.List;

public class CatObject
{
    public List<Item> items;

    public CatObject(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}