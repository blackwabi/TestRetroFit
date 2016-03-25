package com.example.martinbegleiter.githubrestlibrary;

import com.example.martinbegleiter.githubrestlibrary.model.Repo;
import com.example.martinbegleiter.githubrestlibrary.api.GithubListener;
import com.example.martinbegleiter.githubrestlibrary.api.Github;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by martinbegleiter on 25/03/16.
 */
/* package */ class GithubImpl implements Callback<List<Repo>>, Github {

    private GithubListener githubListener;

    @Override
    public void getRepos(GithubListener githubListener) {
        this.githubListener = githubListener;
        GitHubService gitHubService = GithubFactory.getGitHubService();
        Call<List<Repo>> repos = gitHubService.listRepos("octocat");
        repos.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
        githubListener.onReposLoaded(response.body());
    }

    @Override
    public void onFailure(Call<List<Repo>> call, Throwable t) {
        githubListener.onRepoLoadFailure();
    }
}
