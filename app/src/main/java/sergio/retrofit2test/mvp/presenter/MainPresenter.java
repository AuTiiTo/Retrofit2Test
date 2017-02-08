package sergio.retrofit2test.mvp.presenter;

import java.util.List;

import sergio.retrofit2test.R;
import sergio.retrofit2test.model.Repo;
import sergio.retrofit2test.mvp.model.MainModel;
import sergio.retrofit2test.mvp.view.MainView;

/**
 * @author s.ruiz
 */
public class MainPresenter implements MainModel.GitHubDownloadConsumer{

    private MainView view;
    private MainModel model;
    
    public MainPresenter() {
        model = new MainModel(this);
    }

    public void setView(MainView view) {
        this.view = view;
    }

    public void onButtonSearchPressed(String userToSearch) {
        if (view != null) {
            view.showLoading(view.getActivity().getString(R.string.message_downloading));
            model.startDownload(userToSearch);
        }
    }

    @Override
    public void onDownloadFinish(String username, List<Repo> repoList, String urlImg) {
        if (view != null) {
            view.setUsername(username);
            view.loadImage(urlImg);
            view.dismissLoading();
            view.setData(repoList);
        }
    }

    @Override
    public void onFailure(String errorMessage) {
        if (view != null) {
            view.dismissLoading();
            view.showDialog(errorMessage);
        }
    }

    public void onItemClicked(Repo repo) {
        if (view != null) {
            view.openBrowser(repo.getUrl());
        }
    }
}
