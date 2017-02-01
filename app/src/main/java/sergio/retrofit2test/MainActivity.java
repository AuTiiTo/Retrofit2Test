package sergio.retrofit2test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sergio.retrofit2test.githubapi.GitHubService;
import sergio.retrofit2test.model.Owner;
import sergio.retrofit2test.model.Repo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView userAvatar = (ImageView) findViewById(R.id.user_avatar);
        final TextView userName = (TextView) findViewById(R.id.user_name);
        final TextView userRepos =  (TextView) findViewById(R.id.user_repos);
        final Button mainButton = (Button) findViewById(R.id.main_button);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GitHubService service = GitHubService.retrofit.create(GitHubService.class);
                Call<List<Repo>> call = service.listRepo("autiito");
                call.enqueue(new Callback<List<Repo>>() {
                    @Override
                    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                        List<Repo> repos = response.body();
                        Owner owner = repos.get(0).getOwner();
                        userName.setText(owner.getLogin());
                        Picasso.with(getBaseContext()).load(owner.getAvatar()).into(userAvatar);
                        userRepos.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<List<Repo>> call, Throwable t) {
                        userRepos.setText("Error: " + t.getMessage());
                    }
                });
            }
        });
    }
}
