package sergio.retrofit2test.mvp.presenter;

import sergio.retrofit2test.R;
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

    public void onButtonPressed() {
        if (view != null) {
            view.showLoading(view.getActivity().getString(R.string.message_downloading));
            model.startDownload(getRepoName());
        }
    }

    @Override
    public void onDownloadFinished(String username, String repoList, String urlImg){
        if (view != null) {
            view.setUsername(username);
            view.setRepos(repoList);
            view.loadImage(urlImg);
            view.dismissLoading();
        }
    }
    
    @Override
    public void onFailure(String errorMessage) {
        if (view != null) {
            view.dismissLoading();
            view.showDialog(errorMessage);
        }
    }
    
    private String getRepoName() {
        return "autiito";
    }
}
