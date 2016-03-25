package com.example.martinbegleiter.githubrestlibrary;

import com.example.martinbegleiter.githubrestlibrary.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by martinbegleiter on 22/03/16.
 */
/* package */ interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
