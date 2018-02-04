package artofprogramming.ru.busrout.presenter;

import android.content.Context;

/**
 * BusRout. Created by aleksandrstegnin on 28.01.2018.
 */

public interface IMainPresenter {
    void getRoutesData(boolean isUpdate, Context context);
}
