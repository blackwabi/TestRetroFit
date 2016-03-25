package com.example.martinbegleiter.testretrofit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.martinbegleiter.githubrestlibrary.GithubFactory;
import com.example.martinbegleiter.githubrestlibrary.model.Repo;
import com.example.martinbegleiter.githubrestlibrary.api.GithubListener;
import com.example.martinbegleiter.githubrestlibrary.api.Github;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GithubListener {

    private ListView mListView;
    private Github mGitHubRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mGitHubRest = GithubFactory.getGitHub();

        mListView = (ListView)findViewById(R.id.repo_list);
        ArrayAdapter<Repo> adapter = new ArrayAdapter<Repo>(getApplicationContext(),
                R.layout.repo_item);
        mListView.setAdapter(adapter);
        mListView.setEmptyView(View.inflate(getApplicationContext(), R.layout.empty_view, null));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGitHubRest.getRepos(MainActivity.this);

            }
        });
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

    @Override
    public void onReposLoaded(List<Repo> reposLoaded) {
        setItemsInList(reposLoaded);
    }

    @Override
    public void onRepoLoadFailure() {
        clearList();
    }
}
