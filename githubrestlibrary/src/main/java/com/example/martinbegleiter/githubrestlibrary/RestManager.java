package com.example.martinbegleiter.githubrestlibrary;

import com.example.martinbegleiter.githubrestlibrary.events.ReposEventRequest;
import com.example.martinbegleiter.githubrestlibrary.model.Repo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by martinbegleiter on 29/03/16.
 */
public class RestManager {

    private final Retrofit mRetrofit;
    private ActionManager mActionManager;

    private RestManager() {
        mRetrofit = new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mActionManager = new ActionManager();
    }

    private static RestManager sInstance;

    public static void start() {
        sInstance = new RestManager();
        EventBus.getDefault().register(sInstance);

    }

    public static void stop() {
        EventBus.getDefault().unregister(sInstance);
        sInstance = null;
    }

    public static RestManager getInstance() {
        return sInstance;
    }

    @Subscribe
    public void onGetReposEvent(ReposEventRequest getReposEvent) {
        GitHubService gitHubService = getGitHubService();
        mActionManager.add(new GetReposAction(gitHubService));
    }

    /* package */ GitHubService getGitHubService() {
        return mRetrofit.create(GitHubService.class);
    }
}
