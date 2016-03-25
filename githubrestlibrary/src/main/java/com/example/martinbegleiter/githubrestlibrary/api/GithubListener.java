package com.example.martinbegleiter.githubrestlibrary.api;


import com.example.martinbegleiter.githubrestlibrary.model.Repo;

import java.util.List;

/**
 * Created by martinbegleiter on 25/03/16.
 */
public interface GithubListener {
    void onReposLoaded(List<Repo> reposLoaded);
    void onRepoLoadFailure();
}
