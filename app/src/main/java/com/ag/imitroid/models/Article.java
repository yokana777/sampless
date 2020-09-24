package com.ag.imitroid.models;

import java.util.Date;

public class Article {

    private Integer id;
    private String title;
    private String body;
    private Date created_at;
    private Date updated_at;

    public Integer getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getBody(){
        return body;
    }

    public void setBody(String body){
        this.body = body;
    }

    public Date getCreated_at(){
        return created_at;
    }

    public void setCreated_at(Date created_at){
        this.created_at = created_at;
    }

    public Date getUpdated_at(){
        return updated_at;
    }

    public void setUpdated_at(Date updated_at){
        this.updated_at = updated_at;
    }
}
