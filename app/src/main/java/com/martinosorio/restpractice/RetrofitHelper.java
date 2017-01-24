package com.martinosorio.restpractice;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.schedulers.Schedulers;

public class RetrofitHelper {

    public static final String BASE_URL = "https://api.github.com";

    public static class Factory{

        public static Retrofit create(){
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();
        }

        public static Observable<List<ResultAPI>> create(String user){
            Retrofit retrofit = create();
            GitHubService service = retrofit.create(GitHubService.class);
            return service.getRepos(user);
        }
    }

    public interface GitHubService{

        @GET("/users/{user}/repos")
        Observable<List<ResultAPI>> getRepos(@Path("user") String user);
        //Call<List<ResultAPI>> getRepos(@Path("user") String user);
    }
}
