package artofprogramming.ru.busrout.presenter;

import artofprogramming.ru.busrout.model.SnappedPoints;
import artofprogramming.ru.busrout.service.BusRoutesApi;
import artofprogramming.ru.busrout.views.IMapsView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * BusRout. Created by aleksandrstegnin on 31.01.2018.
 */

public class MapsPresenter implements IMapsPresenter {

    private final BusRoutesApi _busRoutesApi;
    private final IMapsView _view;

    public MapsPresenter(IMapsView view) {
        _busRoutesApi = new BusRoutesApi();
        _view = view;
    }

    @Override
    public void getSnappedPointsData(String snappedPoints) {

        Observable<SnappedPoints> dataObservable = _busRoutesApi.getSnappedPoints(snappedPoints);
        dataObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        snappedPoint -> {
                            if (snappedPoint == null)
                                _view.setEmptyResponseText("There is no points");
                            else _view.setSnappedPointsViewData(snappedPoint);
                        }, Throwable::printStackTrace);
    }
}
