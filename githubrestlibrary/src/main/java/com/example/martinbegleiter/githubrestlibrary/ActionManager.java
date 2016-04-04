package com.example.martinbegleiter.githubrestlibrary;

/**
 * Created by martinbegleiter on 04/04/16.
 */
public class ActionManager {
    public void add(Action action) {
        // TODO: Add queue handling, persistence etc
        action.startAction();
    }
}
