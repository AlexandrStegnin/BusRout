package artofprogramming.ru.busrout.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import artofprogramming.ru.busrout.model.Checkpoint;
import artofprogramming.ru.busrout.model.Location;
import artofprogramming.ru.busrout.model.Object;

/**
 * BusRout. Created by aleksandrstegnin on 01.02.2018.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "BusRoutes.sqlite";
    private static final int DATABASE_VERSION = 2;

    //private Dao<Routes, Integer> mRoutesDao = null;
    private Dao<Checkpoint, Integer> mCheckpointDao = null;
    private Dao<Object, Integer> mObjectsDao = null;
    //private Dao<Buses, Integer> mBusesDao = null;
    private Dao<Location, Integer> mLocationDao = null;
    //private RuntimeExceptionDao<Routes, ?> m;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Object.class);
            TableUtils.createTable(connectionSource, Checkpoint.class);
            //TableUtils.createTable(connectionSource, SnappedPoints.class);
            //TableUtils.createTable(connectionSource, SnappedPoint.class);
            TableUtils.createTable(connectionSource, Location.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Object.class, true);
            TableUtils.dropTable(connectionSource, Checkpoint.class, true);
            //TableUtils.dropTable(connectionSource, Buses.class, true);
            //TableUtils.dropTable(connectionSource, SnappedPoint.class, true);
            TableUtils.dropTable(connectionSource, Location.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* Routes

    public Dao<Routes, Integer> getRoutesDao() throws SQLException {
        if (mRoutesDao == null) {
            mRoutesDao = getDao(Routes.class);
        }
        m = getRuntimeExceptionDao(Routes.class);
        return mRoutesDao;
    }
    */
    /* Checkpoint */

    public Dao<Checkpoint, Integer> getCheckpointDao() throws SQLException {
        if (mCheckpointDao == null) {
            mCheckpointDao = getDao(Checkpoint.class);
        }

        return mCheckpointDao;
    }

    /* Buses

    public Dao<Buses, Integer> getBusesDao() throws SQLException {
        if (mBusesDao == null) {
            mBusesDao = getDao(Buses.class);
        }

        return mBusesDao;
    }
    */
    /* Objects */

    public Dao<Object, Integer> getObjectDao() throws SQLException {
        if (mObjectsDao == null) {
            mObjectsDao = getDao(Object.class);
        }

        return mObjectsDao;
    }

    /* Location */

    public Dao<Location, Integer> getLocationDao() throws SQLException {
        if (mLocationDao == null) {
            mLocationDao = getDao(Location.class);
        }

        return mLocationDao;
    }

    @Override
    public void close() {
        //mBusesDao = null;
        mCheckpointDao = null;
        //mSnappedPointsDao = null;
        mObjectsDao = null;
        mLocationDao = null;

        super.close();
    }

    public void deleteAll(ConnectionSource connectionSource){
        try {
            TableUtils.clearTable(connectionSource, Location.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper sDatabaseHelper;

    public static DatabaseHelper getInstance() {
        return sDatabaseHelper;
    }

}
