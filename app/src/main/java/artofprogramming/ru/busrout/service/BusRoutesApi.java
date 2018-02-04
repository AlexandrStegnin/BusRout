package artofprogramming.ru.busrout.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import artofprogramming.ru.busrout.model.Buses;
import artofprogramming.ru.busrout.model.Routes;
import artofprogramming.ru.busrout.model.SnappedPoints;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * BusRout. Created by aleksandrstegnin on 28.01.2018.
 */

public class BusRoutesApi implements IBusRoutesApi {

    public Observable<Routes> getRoutes() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://av.admtyumen.ru/api/public/portal_routes_api/")
                .client(client)
                .build();

        IBusRoutesService routesService = retrofit.create(IBusRoutesService.class);

        return routesService.getRouting(1);
    }

    public Observable<SnappedPoints> getSnappedPoints(String snappedPoints) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://roads.googleapis.com/v1/")
                .client(client)
                .build();

        IBusRoutesService routesService = retrofit.create(IBusRoutesService.class);
        return routesService.getSnappedPoint(snappedPoints, true, "AIzaSyBD6poVDzl4DkjAj3k2ZMePacf4gCVzMWE");
    }

    public Observable<Buses> getBuses() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://av.admtyumen.ru/api/public/")
                .client(client)
                .build();

        IBusRoutesService routesService = retrofit.create(IBusRoutesService.class);
        return routesService.getBuses(1338, 1);
    }
}
