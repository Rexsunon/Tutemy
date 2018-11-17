package com.atornel.tutemy.Model.CatModel;

import java.util.List;

public class Links {
    public List<Self> self;
    public List<Collection> collection;
    public List<About> about;
    public List<WpPostType> __invalid_name__wp;
    public List<Cury> curies;


    public Links(List<Self> self, List<Collection> collection, List<About> about, List<WpPostType> __invalid_name__wp, List<Cury> curies) {
        this.self = self;
        this.collection = collection;
        this.about = about;
        this.__invalid_name__wp = __invalid_name__wp;
        this.curies = curies;
    }

    public List<Self> getSelf() {
        return self;
    }

    public void setSelf(List<Self> self) {
        this.self = self;
    }

    public List<Collection> getCollection() {
        return collection;
    }

    public void setCollection(List<Collection> collection) {
        this.collection = collection;
    }

    public List<About> getAbout() {
        return about;
    }

    public void setAbout(List<About> about) {
        this.about = about;
    }

    public List<WpPostType> get__invalid_name__wp() {
        return __invalid_name__wp;
    }

    public void set__invalid_name__wp(List<WpPostType> __invalid_name__wp) {
        this.__invalid_name__wp = __invalid_name__wp;
    }

    public List<Cury> getCuries() {
        return curies;
    }

    public void setCuries(List<Cury> curies) {
        this.curies = curies;
    }
}

