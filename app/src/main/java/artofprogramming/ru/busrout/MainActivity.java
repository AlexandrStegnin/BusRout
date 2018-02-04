package artofprogramming.ru.busrout;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import artofprogramming.ru.busrout.adapter.RoutAdapter;
import artofprogramming.ru.busrout.db.DatabaseHelper;
import artofprogramming.ru.busrout.model.Checkpoint;
import artofprogramming.ru.busrout.model.Location;
import artofprogramming.ru.busrout.model.Object;
import artofprogramming.ru.busrout.model.Routes;
import artofprogramming.ru.busrout.model.SnappedPoint;
import artofprogramming.ru.busrout.model.SnappedPoints;
import artofprogramming.ru.busrout.presenter.BusesPresenter;
import artofprogramming.ru.busrout.presenter.IBusesPresenter;
import artofprogramming.ru.busrout.presenter.IMainPresenter;
import artofprogramming.ru.busrout.presenter.MainPresenter;
import artofprogramming.ru.busrout.service.OnItemClickListener;
import artofprogramming.ru.busrout.views.IBusesView;
import artofprogramming.ru.busrout.views.IMainView;
import artofprogramming.ru.busrout.views.IMapsView;

public class MainActivity extends AppCompatActivity implements IMainView, IMapsView, IBusesView {
    DatabaseHelper helper = null;
    private RecyclerView _recyclerView;
    private RecyclerView.LayoutManager _layoutManager;
    private RoutAdapter _adapter;
    public OnItemClickListener _listener;
    private IMainPresenter _presenter;

    private SnappedPoints _snappedPoints;

    private Routes _routes;

    private List<Object> _objects;

    private IBusesPresenter _busesPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _recyclerView = findViewById(R.id.my_recycler_view);
        _recyclerView.setHasFixedSize(true);
        _layoutManager = new LinearLayoutManager(this);
        _recyclerView.setLayoutManager(_layoutManager);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr != null ? connMgr.getActiveNetworkInfo() : null;

        if (networkInfo != null && networkInfo.isConnected()) {

            if (_presenter == null) _presenter = new MainPresenter(this);

            _presenter.getRoutesData(false, this);

            _adapter = new RoutAdapter(MainActivity.this, _routes, _listener); //Инициализируем наш адаптер
            _recyclerView.setAdapter(_adapter);   // Устанавливаем адаптер

            if(_busesPresenter == null) _busesPresenter = new BusesPresenter(this);
            _busesPresenter.getBusesData(false, this);
        } else {
            showNoConnectionMessage();
        }
    }

    @Override
    public void setRoutesListViewData(Routes routes) {
        _routes = routes;
        _adapter = new RoutAdapter(MainActivity.this, _routes, _listener);
        _recyclerView.setAdapter(_adapter);

        if (_routes != null) {
            Dao<Checkpoint, Integer> checkpointDao = null;
            try {
                checkpointDao = getHelper().getCheckpointDao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<Checkpoint> checkpoints = _routes.getCheckpoints();
            createOrUpdateCheckpoints(checkpoints, checkpointDao);
        }
    }

    @Override
    public void updateRoutesListView(Routes routes) {
        _routes = routes;
        _adapter.notifyDataSetChanged();

    }

    @Override
    public void setEmptyResponseText(String text) {

    }

    @Override
    public void showNoConnectionMessage() {

    }

    @Override
    public void setSnappedPointsViewData(SnappedPoints points) {
        _snappedPoints = points;
        if (_snappedPoints != null) {
            Dao<Location, Integer> locationDao = null;
            DeleteBuilder<Location, Integer> deleteBuilder;
            try {
                deleteBuilder = getHelper().getLocationDao().deleteBuilder();
                deleteBuilder.delete();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                locationDao = getHelper().getLocationDao();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            List<Location> locations = new ArrayList<>(0);
            List<SnappedPoint> snappedPoints = _snappedPoints.getSnappedPoints();
            for (int i = 0; i < snappedPoints.size() - 1; i++) {
                Location location = snappedPoints.get(i).getLocation();
                locations.add(location);
            }
            for (Location loc :
                    locations) {
                try {
                    if (locationDao != null) {
                        locationDao.createOrUpdate(loc);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String createDataForSnappedPoints(List<Checkpoint> checkpoints) {
        final String[] path = {""};

        for (Checkpoint ch : checkpoints) {
            path[0] += ch.getLatitude() + "," + ch.getLongitude() + "|";
        }
        path[0] = path[0].substring(0, path[0].length() - 1);
        return path[0];
    }

    private DatabaseHelper getHelper() {
        if (helper == null) {
            helper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return helper;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (helper != null) {
            OpenHelperManager.releaseHelper();
            helper = null;
        }
    }

    @Override
    public void setBusesListViewData(List<Object> objects) {
        _objects = objects;
        Dao<Object, Integer> objectsDao = null;
        DeleteBuilder<Object, Integer> deleteBuilder;
        try {
            deleteBuilder = getHelper().getObjectDao().deleteBuilder();
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            objectsDao = getHelper().getObjectDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Object o :
                objects) {
            try {
                if (objectsDao != null) {
                    objectsDao.createOrUpdate(o);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void updateBusesListView(List<Object> objects){
    }

    private void createOrUpdateCheckpoints(List<Checkpoint> checkpoints, Dao<Checkpoint, Integer> checkpointDao) {
        for (Checkpoint ch :
                checkpoints) {
            try {
                if (checkpointDao != null) {
                    checkpointDao.createOrUpdate(ch);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void createOrUpdateLocations(List<Checkpoint> checkpoints, Dao<Checkpoint, Integer> checkpointDao) {
        for (Checkpoint ch :
                checkpoints) {
            try {
                if (checkpointDao != null) {
                    checkpointDao.createOrUpdate(ch);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
