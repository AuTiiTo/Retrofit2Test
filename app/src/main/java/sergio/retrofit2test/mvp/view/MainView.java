package sergio.retrofit2test.mvp.view;

import android.app.Activity;
import android.support.v7.widget.SearchView;

import java.util.List;

import sergio.retrofit2test.model.Repo;

/**
 * @author s.ruiz
 */

public interface MainView extends SearchView.OnQueryTextListener {

    Activity getActivity();

    void setUsername(String text);
    void setData(List<Repo> repos);
    void loadImage(String urlImg);

    void showDialog(String errorMessage);

    void showLoading(String loadingMessage);
    void dismissLoading();

    void openBrowser(String urlString);
}
