package com.saint.blackrussia.other;

import com.saint.blackrussia.model.News;
import com.saint.blackrussia.model.Servers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface {

    @GET("http://wh3606.web3.maze-host.ru/zakazi/brp/servers.json")
    Call<List<Servers>> getServers();
    @GET("http://wh3606.web3.maze-host.ru/zakazi/brp/news.json")
    Call<List<News>> getNews();

}


//http://wh3606.web3.maze-host.ru/zakazi/204/servers.json
//http://wh3606.web3.maze-host.ru/zakazi/204/news.json