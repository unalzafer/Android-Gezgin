package com.a.gezginapp.Model;

public class StoryModel {

    String name;
    String photo;
    String date;
    String like;
    String title;

    public StoryModel() {
    }

    public StoryModel(String name, String photo, String date, String like, String title) {
        this.name = name;
        this.photo = photo;
        this.date = date;
        this.like = like;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
