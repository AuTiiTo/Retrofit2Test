package sergio.retrofit2test.mvp.model;

import android.text.SpannableString;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sergio.retrofit2test.githubapi.GitHubService;
import sergio.retrofit2test.model.Owner;
import sergio.retrofit2test.model.Repo;

/**
 * @author s.ruiz
 */
public class MainModel {

    private final GitHubDownloadConsumer consumer;

    public MainModel(GitHubDownloadConsumer consumer) {
        this.consumer = consumer;
    }

    public void startDownload(String userSearch) {
        GitHubService service = GitHubService.retrofit.create(GitHubService.class);
        Call<List<Repo>> call = service.listRepo(userSearch);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                List<Repo> repos = response.body();
                Owner owner = repos.get(0).getOwner();
                StringBuilder repositories = new StringBuilder();
                for (Repo repo :
                        repos) {
                    repositories.append(repo.getName())
                                .append('\n')
                                .append(repo.getLanguage())
                                .append('\n')
                                .append(repo.getCreated())
                                .append('\n')
                                .append(new SpannableString(repo.getUrl()))
                                .append('\n');
                }
                consumer.onDownloadFinish(owner.getLogin(), repos, owner.getAvatar());
//                consumer.onDownloadFinished(owner.getLogin(), repositories.toString(), owner.getAvatar());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                consumer.onFailure(t.getMessage());
            }
        });
    }

    public interface GitHubDownloadConsumer {
//        void onDownloadFinished(String username, String repoList, String urlImg);
        void onDownloadFinish(String username, List<Repo> repoList, String urlImg);
        void onFailure(String errorMessage);
    }
}
