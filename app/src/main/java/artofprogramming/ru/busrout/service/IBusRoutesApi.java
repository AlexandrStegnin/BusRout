package artofprogramming.ru.busrout.service;

import artofprogramming.ru.busrout.model.Routes;
import artofprogramming.ru.busrout.model.SnappedPoints;
import io.reactivex.Observable;

/**
 * BusRout. Created by aleksandrstegnin on 28.01.2018.
 */

public interface IBusRoutesApi {
    Observable<Routes> getRoutes();
    Observable<SnappedPoints> getSnappedPoints(String snappedPoints);
}
