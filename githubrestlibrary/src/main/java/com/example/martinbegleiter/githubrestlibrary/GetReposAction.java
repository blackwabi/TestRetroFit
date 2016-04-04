package com.example.martinbegleiter.githubrestlibrary;

import com.example.martinbegleiter.githubrestlibrary.events.ReposEventResponse;
import com.example.martinbegleiter.githubrestlibrary.model.Repo;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by martinbegleiter on 04/04/16.
 */
public class GetReposAction implements Action, Callback<List<Repo>> {
    private GitHubService mService;

    public GetReposAction(GitHubService service) {
        mService = service;
    }

    @Override
    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
        if (response.isSuccessful()) {
            EventBus.getDefault().post(new ReposEventResponse(response.body(), null));
        } else {
            // TODO: Handle server error here
        }
    }

    @Override
    public void onFailure(Call<List<Repo>> call, Throwable t) {
        EventBus.getDefault().post(new ReposEventResponse(null, t));
    }

    @Override
    public void startAction() {
        Call<List<Repo>> repos = mService.listRepos("octocat");
        repos.enqueue(this);
    }
}
