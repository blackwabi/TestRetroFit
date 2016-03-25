package com.example.martinbegleiter.githubrestlibrary;

import com.example.martinbegleiter.githubrestlibrary.api.Github;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by martinbegleiter on 29/03/16.
 */
public class GithubFactory {
    public static Github getGitHub() {
        return new GithubImpl();
    }

    /* package */ static GitHubService getGitHubService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(GitHubService.class);
    }
}
