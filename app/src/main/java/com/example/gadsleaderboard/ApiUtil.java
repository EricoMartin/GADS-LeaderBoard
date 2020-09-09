package com.example.gadsleaderboard;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtil {
    private ApiUtil(){}

    public static final String BASE_API_URL = " https://gadsapi.herokuapp.com/";
//    public static final String TOP_LEARNERS_API_URL = " https://gadsapi.herokuapp.com/api/hours";
//    public static final String SKILL_IQ_API_URL = " https://gadsapi.herokuapp.com/api/skilliq";



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

    public static LearnerService getLearnerService(){
        LearnerService learnerService = getRetrofit().create(LearnerService.class);
        return learnerService;
    }

    public static SkillService getSkillService(){
        SkillService skillService = getRetrofit().create(SkillService.class);
        return skillService;
    }
}
