package artofprogramming.ru.busrout.presenter;

import android.content.Context;

/**
 * BusRout. Created by aleksandrstegnin on 03.02.2018.
 */

public interface IBusesPresenter {
    void getBusesData(Context context);
    void updateBusLocation(Context context);
}
