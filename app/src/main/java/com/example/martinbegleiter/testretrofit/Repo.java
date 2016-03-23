package com.example.martinbegleiter.testretrofit;

/**
 * Created by martinbegleiter on 22/03/16.
 */
public class Repo {
    String id;
    String name;
    String full_name;
    String html_url;
    String description;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", html_url='" + html_url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
