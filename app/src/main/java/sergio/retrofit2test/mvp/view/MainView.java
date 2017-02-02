package sergio.retrofit2test.mvp.view;

import android.app.Activity;

/**
 * @author s.ruiz
 */

public interface MainView {

    Activity getActivity();

    String getUserSearch();
    void setUsername(String text);
    void setRepos(String text);
    void loadImage(String urlImg);
    void onButtonPressed();

    void showDialog(String errorMessage);

    void showLoading(String loadingMessage);
    void dismissLoading();
}
