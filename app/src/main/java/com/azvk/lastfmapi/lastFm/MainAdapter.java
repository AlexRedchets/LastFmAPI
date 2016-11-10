package com.azvk.lastfmapi.lastFm;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        View view = inflater.inflate(R.layout.custom_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Track currentData =  response.get(position);

        holder.songArtist.setText(currentData.getArtist().getName());
        holder.songName.setText(currentData.getName());
        Picasso.with(context).load(currentData.getImage().get(3).getText()).into(holder.image);

        holder.dots.setOnClickListener(v -> showPopupMenu(holder.dots));

    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_song, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(context, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(context, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.song_title)
        TextView songName;
        @BindView(R.id.artist_name)
        TextView songArtist;
        @BindView(R.id.artist_image)
        ImageView image;
        @BindView(R.id.dots)
        ImageView dots;

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
