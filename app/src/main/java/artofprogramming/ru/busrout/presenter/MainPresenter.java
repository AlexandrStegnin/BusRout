package artofprogramming.ru.busrout.presenter;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import artofprogramming.ru.busrout.db.DatabaseHelper;
import artofprogramming.ru.busrout.model.Checkpoint;
import artofprogramming.ru.busrout.model.Routes;
import artofprogramming.ru.busrout.service.BusRoutesApi;
import artofprogramming.ru.busrout.views.IMainView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * BusRout. Created by aleksandrstegnin on 28.01.2018.
 */

public class MainPresenter implements IMainPresenter {
    private final BusRoutesApi _busRoutesApi;
    private final IMainView _view;
    DatabaseHelper helper = null;
    Context _context;

    public MainPresenter(IMainView view) {
        _busRoutesApi = new BusRoutesApi();
        _view = view;
    }

    @Override
    public void getRoutesData(boolean isUpdate, Context context) {
        //Get Observable from our Model layer
        _context = context;
        Observable<Routes> dataObservable = _busRoutesApi.getRoutes();

        //Really cool thing in RxAndroid is Schedulers. It helps us execute Network requests in new thread (subscribeOn)
        //and update our widgets in main thread with new data (observeOn)
        dataObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(routesData ->
                {
                    if (routesData == null) _view.setEmptyResponseText("There is no routes");
                    else if (isUpdate) _view.updateRoutesListView(routesData);
                    else _view.setRoutesListViewData(routesData);
                },
                        err -> {
                            Dao<Checkpoint, Integer> checkpointDao = null;
                            List<Checkpoint> checkpoints = new ArrayList<>(0);
                            try {
                                checkpointDao = getHelper().getCheckpointDao();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                if (checkpointDao != null) {
                                    checkpoints = checkpointDao.queryForAll();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            Routes routes = new Routes();
                            routes.setCheckpoints(checkpoints);
                            _view.setRoutesListViewData(routes);
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
