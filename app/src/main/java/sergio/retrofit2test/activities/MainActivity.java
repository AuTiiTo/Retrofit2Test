package sergio.retrofit2test.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import sergio.retrofit2test.R;
import sergio.retrofit2test.mvp.presenter.MainPresenter;
import sergio.retrofit2test.mvp.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView {

    private EditText userSearch;
    private ImageView userAvatar;
    private TextView userName;
    private TextView userRepos;
    private Button mainButton;

    private MainPresenter presenter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter();
        init();
    }


    /**
     * Se encarga de inicializar las vistas
     */
    private void init() {
        userSearch = (EditText) findViewById(R.id.user_serch);
        userAvatar = (ImageView) findViewById(R.id.user_avatar);
        userName = (TextView) findViewById(R.id.user_name);
        userRepos =  (TextView) findViewById(R.id.user_repos);
        mainButton = (Button) findViewById(R.id.main_button);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.setView(null);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    public String getUserSearch() {
        if (userSearch.length() > 0) {
            return userSearch.getText().toString();
        } else {
            return null;
        }
    }

    @Override
    public void setUsername(String user) {
        if (userName!= null){
            userName.setText(user);
        }
    }

    @Override
    public void setRepos(String repos) {
        if (userRepos!= null){
            userRepos.setText(repos);
        }
    }

    @Override
    public void loadImage(String urlImg) {
        Picasso.with(this)
               .load(urlImg)
               .into(userAvatar);
    }

    @Override
    public void onButtonPressed() {
        presenter.onButtonPressed();
    }

    @Override
    public void showDialog(String errorMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.error_title);
        builder.setMessage(errorMessage);
        builder.setPositiveButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }

    @Override
    public void showLoading(String loadingMessage) {
        if (dialog == null) {
            dialog = ProgressDialog.show(MainActivity.this, null, loadingMessage);
        } else {
            dialog.setMessage(loadingMessage);
        }
    }

    @Override
    public void dismissLoading() {
        try{
            if (dialog != null && dialog.isShowing()){
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
