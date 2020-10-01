package com.ag.imitroid;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.ag.imitroid.models.Article;
import com.ag.imitroid.models.Articles;
import com.ag.imitroid.rest.api;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class main extends AppCompatActivity {

    public static final String ROOT_URL = "https://develophase.000webhostapp.com/";
    private ListView listView;
    private View content;
    private List<Article> articleList;
    private string mmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        content = (View) findViewById(R.id.content);
        listView =(ListView) content.findViewById(R.id.listView);
        getArticles();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void getArticles() {
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please Wait..",false,false);
        //Logging Interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //set Level Log
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())//GsonConverter untuk parsing json
                .client(httpClient.build())
                .build();

        api service = retrofit.create(api.class);

        Call<Articles> call = service.loadArticles();
        call.enqueue(new Callback<Articles>() {
            @Override
            public void onResponse(Call<Articles> call, Response<Articles> response) {
                loading.dismiss();
                List<Article> _articleList = response.body().getArticleList();
                articleList = _articleList;
                showList();
            }

            @Override
            public void onFailure(Call<Articles> call, Throwable t) {

            }
        });

    }

    private void showList() {
        String[] items = new String[articleList.size()];

        for(int i = 0; i < articleList.size(); i++){
            items[i] = articleList.get(i).getTitle();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, items);

        listView.setAdapter(adapter);
    }
}
