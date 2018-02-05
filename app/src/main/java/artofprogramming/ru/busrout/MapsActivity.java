package artofprogramming.ru.busrout;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import artofprogramming.ru.busrout.db.DatabaseHelper;
import artofprogramming.ru.busrout.model.Checkpoint;
import artofprogramming.ru.busrout.model.Object;
import artofprogramming.ru.busrout.model.SnappedPoints;
import artofprogramming.ru.busrout.presenter.BusesPresenter;
import artofprogramming.ru.busrout.presenter.IBusesPresenter;
import artofprogramming.ru.busrout.views.IBusesView;
import artofprogramming.ru.busrout.views.IMapsView;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, IMapsView, IBusesView {

    private GoogleMap mMap;
    private SnappedPoints _snappedPoints;
    DatabaseHelper helper = null;
    private List<Object> _objects;
    private HashMap<String, Marker> mMarkerHashMap;

    private IBusesPresenter _busesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        PolylineOptions rectOptions = new PolylineOptions();

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

        LatLng point;

        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_place_white_18dp);
        Checkpoint firstCheckpoint = checkpoints.get(0);
        Checkpoint lastCheckpoint = checkpoints.get(checkpoints.size() - 1);
        for (Checkpoint ch : checkpoints) {
            point = new LatLng(ch.getLatitude(), ch.getLongitude());
            rectOptions.add(point).width(18).color(Color.BLUE).geodesic(false);

            googleMap.addMarker(new MarkerOptions()
                    .position(point)
                    .title(ch.getName())
                    .icon(icon));
        }

        point = new LatLng(firstCheckpoint.getLatitude(), firstCheckpoint.getLongitude());
        rectOptions.add(point).width(18).color(Color.BLUE).geodesic(false);
        point = new LatLng(lastCheckpoint.getLatitude(), lastCheckpoint.getLongitude());
        rectOptions.add(point).width(18).color(Color.BLUE).geodesic(false);

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


        for (Object o : objects) {
            if (o.getLatitude() != null && o.getLongitude() != null) {
                if (mMarkerHashMap != null && !mMarkerHashMap.isEmpty()) {
                    Marker m = mMarkerHashMap.get(o.getCarNumber());
                    if (m != null) {
                        m.remove();
                        mMarkerHashMap.remove(o.getCarNumber());
                    }
                } else {
                    mMarkerHashMap = new HashMap<>(0);
                }
                point = new LatLng(o.getLatitude(), o.getLongitude());
                Marker marker = googleMap.addMarker(new MarkerOptions()
                        .position(point)
                        .title(o.getCarNumber())
                        .icon(icon));
                mMarkerHashMap.put(o.getCarNumber(), marker);
            }
        }

        Polyline polyline = mMap.addPolyline(rectOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 12));
        mMap.getUiSettings().setZoomControlsEnabled(true);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr != null ? connMgr.getActiveNetworkInfo() : null;

        if (networkInfo != null && networkInfo.isConnected()) {
            if (_busesPresenter == null) _busesPresenter = new BusesPresenter(this);
            _busesPresenter.updateBusLocation(this);
        } else {
            showNoConnectionMessage();
        }

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
    }

    @Override
    public void setBusesListViewData(List<Object> objects) {
        _objects = objects;
    }

    @Override
    public void updateBusesListView(List<Object> objects) {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_directions_bus_black_18dp);
        LatLng point;
        for (Object o : objects) {
            if (o.getLatitude() != null && o.getLongitude() != null) {
                if (mMarkerHashMap != null && !mMarkerHashMap.isEmpty()) {
                    Marker m = mMarkerHashMap.get(o.getCarNumber());
                    if (m != null) {
                        m.remove();
                        mMarkerHashMap.remove(o.getCarNumber());
                    }
                } else {
                    mMarkerHashMap = new HashMap<>(0);
                }
                point = new LatLng(o.getLatitude(), o.getLongitude());
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(point)
                        .title(o.getCarNumber())
                        .icon(icon));
                mMarkerHashMap.put(o.getCarNumber(), marker);
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

}
