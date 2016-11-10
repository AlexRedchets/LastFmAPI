package com.azvk.lastfmapi.lastFm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.azvk.lastfmapi.R;
import com.azvk.lastfmapi.model.Track;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private List<Track> response;
    private Context context;
    private String TAG = MainAdapter.class.getSimpleName();
    private ClickListener clickListener;

    public MainAdapter(Context context, ClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void updateAdapter(List<Track> response){
        this.response = response;
        notifyDataSetChanged();
        Log.i(TAG, "Adapter is updated");
    }

    @Override
    public int getItemCount() {
        return response != null ? response.size() : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Track currentData =  response.get(position);

        holder.songArtist.setText(currentData.getArtist().getName());
        holder.songName.setText(currentData.getName());
        Picasso.with(context).load(currentData.getImage().get(3).getText()).into(holder.image);

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.song_name)
        TextView songName;
        @BindView(R.id.song_artist)
        TextView songArtist;
        @BindView(R.id.image)
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(response.get(getAdapterPosition()));
        }
    }

    public interface ClickListener {

        void onClick(Track track);
    }
}
