package com.example.gadsleaderboard;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtil {
    private ApiUtil(){}

    public static final String BASE_API_URL = " https://gadsapi.herokuapp.com/";
    public static final String GOOGLE_API_URL = "https://docs.google.com/forms/d/e/";




    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return  retrofit;
    }
    private static Retrofit postRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(GOOGLE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return  retrofit;
    }

    public static LearnerService getLearnerService(){
        LearnerService learnerService = getRetrofit().create(LearnerService.class);
        return learnerService;
    }

    public static LearnerService postService(){
        LearnerService submitService = postRetrofit().create(LearnerService.class);
        return submitService;
    }
    public static Call<Void> submitProject(String fName, String lName, String email, String link) {
        return postService().submitForm(fName, lName, email, link);
    }
}
