package sergio.retrofit2test.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sergio.retrofit2test.R;
import sergio.retrofit2test.model.Repo;
import sergio.retrofit2test.mvp.presenter.MainPresenter;
import sergio.retrofit2test.mvp.view.MainView;
import sergio.retrofit2test.mvp.view.RepoAdapter;

public class MainActivity extends AppCompatActivity implements MainView {

    private SearchView userSearch;
    private ImageView userAvatar;
    private TextView userName;
    private ListView repoList;

    private MainPresenter presenter;
    private RepoAdapter adapter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter();
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.search);
        userSearch= (SearchView) MenuItemCompat.getActionView(menuItem);
        userSearch.setOnQueryTextListener(this);

        return true;
    }

    /**
     * Se encarga de inicializar las vistas
     */
    private void init() {
        userAvatar = (ImageView) findViewById(R.id.user_avatar);
        userName = (TextView) findViewById(R.id.user_name);
        repoList = (ListView) findViewById(R.id.repo_list);
        repoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Repo repo = adapter.getItem(position);
                presenter.onItemClicked(repo);
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

    @Override
    public void setUsername(String user) {
        if (userName!= null){
            userName.setText(user);
        }
    }

    @Override
    public void setData(List<Repo> repos) {
        if (repoList != null) {
            adapter = new RepoAdapter(repos);
            repoList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadImage(String urlImg) {
        Picasso.with(this)
               .load(urlImg)
               .into(userAvatar);
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

    @Override
    public void openBrowser(String urlString) {
        final Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
        startActivity(urlIntent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenter.onButtonSearchPressed(query);
        userSearch.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
