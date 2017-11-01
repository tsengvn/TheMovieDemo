package me.hienngo.themoviedemo.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.hienngo.themoviedemo.R;
import me.hienngo.themoviedemo.model.viewmodel.MovieViewModel;

/**
 * @author hienngo
 * @since 10/30/17
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final Context context;
    private final List<MovieViewModel> dataList;
    private LoadMoreListener loadMoreListener;
    private OnItemClickListener clickListener;

    public MovieAdapter(Context context) {
        this.context = context;
        this.dataList = new ArrayList<>();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        final MovieViewModel movieViewModel = dataList.get(position);
        holder.titleText.setText(movieViewModel.getName());
        holder.voteText.setText(context.getString(R.string.rating, movieViewModel.getVote()));
        holder.releaseText.setText(context.getString(R.string.release_date) + movieViewModel.getReleaseDate());
        
        Picasso.with(context).load(movieViewModel.getPosterUrl())
                .fit()
                .centerInside()
                .into(holder.posterImage);

        if (position == dataList.size()-1 && loadMoreListener != null) {
            loadMoreListener.onLoadMore();
        }

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onItemClicked(movieViewModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setDataList(List<MovieViewModel> dataList) {
        this.dataList.addAll(dataList);
        this.notifyDataSetChanged();
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void clear() {
        this.dataList.clear();
        this.notifyDataSetChanged();
    }

    public interface LoadMoreListener {
        void onLoadMore();
    }

    public interface OnItemClickListener {
        void onItemClicked(MovieViewModel model);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, voteText, releaseText;
        ImageView posterImage;
        public MovieViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.tvTitle);
            voteText = itemView.findViewById(R.id.tvRate);
            releaseText = itemView.findViewById(R.id.tvReleaseDate);
            posterImage = itemView.findViewById(R.id.ivPoster);
        }
    }
}
