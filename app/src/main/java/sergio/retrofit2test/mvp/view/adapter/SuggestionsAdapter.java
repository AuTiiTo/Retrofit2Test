package sergio.retrofit2test.mvp.view.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

import sergio.retrofit2test.R;

/**
 * @author s.ruiz
 */

public class SuggestionsAdapter extends CursorAdapter implements Filterable {

    public SuggestionsAdapter(Context context, Cursor c) {
        super(context, c, 2);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.suggestion_item, parent, false);

        SuggestionViewHolder holder = new SuggestionViewHolder();
        holder.suggest = (TextView) view.findViewById(R.id.suggest_text);
        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        SuggestionViewHolder holder = (SuggestionViewHolder) view.getTag();
        holder.suggest.setText(cursor.getString(1));
    }

    private class SuggestionViewHolder {
        private TextView suggest;
    }
}

