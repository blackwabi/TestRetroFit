package com.example.martinbegleiter.testretrofit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements Callback<List<Repo>> {

    private GitHubService mGitHubService;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView)findViewById(R.id.repo_list);
        ArrayAdapter<Repo> adapter = new ArrayAdapter<Repo>(getApplicationContext(),
                R.layout.repo_item);
        mListView.setAdapter(adapter);
        mListView.setEmptyView(View.inflate(getApplicationContext(), R.layout.empty_view, null));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<Repo>> repos = mGitHubService.listRepos("octocat");
                repos.enqueue(MainActivity.this);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mGitHubService = retrofit.create(GitHubService.class);
    }

    @Override
    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
        setItemsInList(response.body());
    }

    @Override
    public void onFailure(Call<List<Repo>> call, Throwable t) {
        clearList();
    }

    private void clearList() {
        ArrayAdapter<Repo> adapter = ((ArrayAdapter<Repo>) mListView.getAdapter());
        adapter.clear();
    }

    private void setItemsInList(List<Repo> items) {
        ArrayAdapter<Repo> repoAdapter = ((ArrayAdapter<Repo>) mListView.getAdapter());
        repoAdapter.clear();
        repoAdapter.addAll(items);
        repoAdapter.notifyDataSetChanged();
    }
}
