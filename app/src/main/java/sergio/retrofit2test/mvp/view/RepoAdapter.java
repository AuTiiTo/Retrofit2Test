package sergio.retrofit2test.mvp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import sergio.retrofit2test.R;
import sergio.retrofit2test.model.Repo;

/**
 * @author s.ruiz
 */

public class RepoAdapter extends BaseAdapter {

    private List<Repo> repoItems;

    public RepoAdapter(List<Repo> repoList) {
        this.repoItems = repoList;
    }

    @Override
    public int getCount() {
        return repoItems.size();
    }

    @Override
    public Repo getItem(int position) {
        return repoItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RepoViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, null);

            holder = new RepoViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.repo_name);
            holder.url = (TextView) convertView.findViewById(R.id.repo_url);
            holder.language = (TextView) convertView.findViewById(R.id.repo_lang);
            holder.created = (TextView) convertView.findViewById(R.id.repo_date);
            holder.stars_count = (TextView) convertView.findViewById(R.id.repo_stars_count);

            convertView.setTag(holder);
        } else {
            holder = (RepoViewHolder) convertView.getTag();
        }

        Repo repoItem = getItem(position);
        if (repoItem != null) {
            holder.name.setText(repoItem.getName());
            holder.language.setText(repoItem.getLanguage());
            holder.url.setText(repoItem.getUrl());
            holder.created.setText(String.valueOf(repoItem.getCreated()));
            holder.stars_count.setText(String.valueOf(repoItem.getStars()));
        }

        return convertView;
    }

    class RepoViewHolder {
        private TextView name;
        private TextView url;
        private TextView language;
        private TextView created;
        private TextView stars_count;
    }
}

