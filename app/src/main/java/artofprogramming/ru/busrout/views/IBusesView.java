package artofprogramming.ru.busrout.views;

import java.util.List;

import artofprogramming.ru.busrout.model.Object;

/**
 * BusRout. Created by aleksandrstegnin on 03.02.2018.
 */

public interface IBusesView {
    void setBusesListViewData(List<Object> objects);

    void updateBusesListView(List<Object> objects);

    void setEmptyResponseText(String text);

    void showNoConnectionMessage();
}
