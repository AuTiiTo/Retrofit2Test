package sergio.retrofit2test.githubapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sergio.retrofit2test.model.Repo;

/**
 * @author s.ruiz
 */

public interface GitHubService {
    @GET("/users/{user}/repos")
    Call<List<Repo>> listRepo(
            @Path("user") String user
    );

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
