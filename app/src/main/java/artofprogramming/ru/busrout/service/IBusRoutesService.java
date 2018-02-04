package artofprogramming.ru.busrout.service;

import artofprogramming.ru.busrout.model.Buses;
import artofprogramming.ru.busrout.model.Routes;
import artofprogramming.ru.busrout.model.SnappedPoints;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * BusRout. Created by aleksandrstegnin on 28.01.2018.
 */

public interface IBusRoutesService {
    @GET("1338")
    Observable<Routes> getRouting(@Query("city_id") int city_id);

    @GET("snapToRoads")
    Observable<SnappedPoints> getSnappedPoint(@Query("path") String path, @Query("interpolate") boolean interpolate, @Query("key") String apiKey);

    @GET("portal_tracking")
    Observable<Buses> getBuses(@Query("route_id") int route_id, @Query("show_route") int show_route);

}
