package artofprogramming.ru.busrout.views;

import artofprogramming.ru.busrout.model.SnappedPoints;

/**
 * BusRout. Created by aleksandrstegnin on 31.01.2018.
 */

public interface IMapsView {
    void setSnappedPointsViewData(SnappedPoints points);

    void setEmptyResponseText(String text);

    void showNoConnectionMessage();
}
