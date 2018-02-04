package artofprogramming.ru.busrout.views;

import artofprogramming.ru.busrout.model.Routes;

/**
 * BusRout. Created by aleksandrstegnin on 28.01.2018.
 */

public interface IMainView {
    void setRoutesListViewData(Routes routes);

    void updateRoutesListView(Routes routes);

    void setEmptyResponseText(String text);

    void showNoConnectionMessage();

}
