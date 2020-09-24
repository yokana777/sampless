package com.ag.imitroid.models;

import java.util.ArrayList;
import java.util.List;

public class Articles {
    private List<Article> articleList = new ArrayList<Article>();

    public List<Article> getArticleList(){
        return articleList;
    }

    public void setArticleList(List<Article> articleList){
        this.articleList = articleList;
    }
}
