package com.example.martinbegleiter.testretrofit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.martinbegleiter.githubrestlibrary.RestManager;
import com.example.martinbegleiter.githubrestlibrary.events.ReposEventRequest;
import com.example.martinbegleiter.githubrestlibrary.events.ReposEventResponse;
import com.example.martinbegleiter.githubrestlibrary.model.Repo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RestManager.start();

        mListView = (ListView)findViewById(R.id.repo_list);
        ArrayAdapter<Repo> adapter = new ArrayAdapter<Repo>(getApplicationContext(),
                R.layout.repo_item);
        mListView.setAdapter(adapter);
        mListView.setEmptyView(View.inflate(getApplicationContext(), R.layout.empty_view, null));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new ReposEventRequest());

            }
        });
        EventBus.getDefault().register(this);
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
    protected void onDestroy() {
        super.onDestroy();
        RestManager.stop();
    }

    @Subscribe
    public void onReposReceived(ReposEventResponse reposEventResponse) {
        Throwable throwable = reposEventResponse.mThrowable;
        if (throwable != null) {
            Log.e("MARTIN", "Error when retrieving repos");
        } else {
            setItemsInList(reposEventResponse.mRepoList);
        }
    }
}
