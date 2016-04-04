package com.example.martinbegleiter.githubrestlibrary.events;

import com.example.martinbegleiter.githubrestlibrary.model.Repo;

import java.util.List;

/**
 * Created by martinbegleiter on 04/04/16.
 */
public class ReposEventResponse {
    public final List<Repo> mRepoList;
    public final Throwable mThrowable;

    public ReposEventResponse(List<Repo> repoList, Throwable throwable) {
        this.mRepoList = repoList;
        this.mThrowable = throwable;
    }
}
