package com.myapps.yara.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapps.yara.R;
import com.myapps.yara.service.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieRecyclerViewAdapter extends
        RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = MovieRecyclerViewAdapter.class.getSimpleName();

    private Context context;
    private List<Movie> list;
    private OnItemClickListener onItemClickListener;

    public MovieRecyclerViewAdapter(Context context, List<Movie> list,
                                    OnItemClickListener onItemClickListener) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Todo Butterknife bindings
        @BindView(R.id.movieImageView)
        ImageView movieImageView;
        @BindView(R.id.movieTitleTextView)
        TextView movieTitleTextView;
        @BindView(R.id.movieDesTextView)
        TextView movieDesTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bind(final Movie model,
                         final OnItemClickListener listener) {
            itemView.findViewById(R.id.btnShowDetail).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getLayoutPosition(), model.getImdbID());
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.layout_movie, parent, false);
        ButterKnife.bind(this, view);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie item = list.get(position);
        holder.movieTitleTextView.setText(item.getTitle());
        holder.movieDesTextView.setText(item.getYear());
        String posterUrl = item.getPoster();
        Picasso.get().load(posterUrl).into(holder.movieImageView);
        holder.bind(item, onItemClickListener);
    }

    public void setList(List<Movie> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position, String imdbId);
    }

}