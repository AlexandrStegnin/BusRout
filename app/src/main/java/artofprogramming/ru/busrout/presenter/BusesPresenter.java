package artofprogramming.ru.busrout.presenter;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import artofprogramming.ru.busrout.db.DatabaseHelper;
import artofprogramming.ru.busrout.model.Buses;
import artofprogramming.ru.busrout.model.Object;
import artofprogramming.ru.busrout.service.BusRoutesApi;
import artofprogramming.ru.busrout.views.IBusesView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * BusRout. Created by aleksandrstegnin on 03.02.2018.
 */

public class BusesPresenter implements IBusesPresenter {
    private final BusRoutesApi _busRoutesApi;
    private final IBusesView _view;
    DatabaseHelper helper = null;
    Context _context;

    public BusesPresenter(IBusesView view) {
        _busRoutesApi = new BusRoutesApi();
        _view = view;
    }

    @Override
    public void getBusesData(boolean isUpdate, Context context) {
        //Get Observable from our Model layer
        _context = context;
        Observable<Buses> dataObservable = _busRoutesApi.getBuses();

        //Really cool thing in RxAndroid is Schedulers. It helps us execute Network requests in new thread (subscribeOn)
        //and update our widgets in main thread with new data (observeOn)
        dataObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatWhen(completed -> completed.delay(5, TimeUnit.SECONDS))
                .subscribe(buses ->
                        {
                            if (buses == null) _view.setEmptyResponseText("There is no buses");
                            else if(isUpdate) {
                                List<Object> objects = buses.getObjects();
                                _view.updateBusesListView(objects);
                            }
                            else {
                                List<Object> objects = buses.getObjects();
                                _view.setBusesListViewData(objects);
                            }
                        },
                        err -> {
                            Dao<Object, Integer> objectDao = null;
                            List<Object> objects = new ArrayList<>(0);
                            try {
                                objectDao = getHelper().getObjectDao();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                if (objectDao != null) {
                                    objects = objectDao.queryForAll();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            _view.setBusesListViewData(objects);
                        }
                );
    }

    private DatabaseHelper getHelper() {
        if (helper == null) {
            helper = OpenHelperManager.getHelper(_context, DatabaseHelper.class);
        }
        return helper;
    }
}
