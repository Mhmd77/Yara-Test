package com.myapps.yara.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapps.yara.R;
import com.myapps.yara.service.model.DetailMovie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailMoviesAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = DetailMoviesAdapter.class.getSimpleName();


    private Context context;
    private List<Pair<String, String>> list;
    private DetailMovie detailMovie;

    public DetailMoviesAdapter(Context context, List<Pair<String, String>> list, DetailMovie detailMovie) {
        this.detailMovie = detailMovie;
        this.context = context;
        this.list = list;
    }


    public static class TopViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtDirector)
        TextView txtDirector;
        @BindView(R.id.txtDes)
        TextView txtDes;

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public static class UsualViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtKey)
        TextView txtKey;
        @BindView(R.id.txtValue)
        TextView txtValue;

        public UsualViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                view = inflater.inflate(R.layout.layout_top_detail, parent, false);
                holder = new TopViewHolder(view);
                break;
            case 1:
                view = inflater.inflate(R.layout.layout_usual_detail, parent, false);
                holder = new UsualViewHolder(view);
                break;
        }

        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Pair<String, String> item = null;
        if (position > 0)
            item = list.get(position - 1);
        switch (holder.getItemViewType()) {
            case 0:
                TopViewHolder h1 = (TopViewHolder) holder;
                h1.txtTitle.setText(detailMovie.getTitle());
                h1.txtDirector.setText("Director: " + detailMovie.getDirector());
                h1.txtDes.setText("Released: " + detailMovie.getReleased() + "\n" + "Runtime: " + detailMovie.getRuntime() +
                        '\n' + detailMovie.getGenre());
                break;
            case 1:
                UsualViewHolder h2 = (UsualViewHolder) holder;
                h2.txtKey.setText(item.first);
                h2.txtValue.setText(item.second);
                break;

        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return 0;
        return 1;
    }
}