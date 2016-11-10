package com.azvk.lastfmapi.lastFm.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.azvk.lastfmapi.App;
import com.azvk.lastfmapi.R;
import com.azvk.lastfmapi.lastFm.MainAdapter;
import com.azvk.lastfmapi.lastFm.MainPresenter;
import com.azvk.lastfmapi.model.Track;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements MainInterface.View, MainAdapter.ClickListener{

    @Inject
    MainPresenter presenter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MainAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resolveDependencies();
    }

    private void resolveDependencies() {
        ((App)getActivity().getApplicationContext()).getMainComponent(this).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MainAdapter(getContext(), this) ;
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onComplete(List<Track> data) {
        adapter.updateAdapter(data);
    }

    @Override
    public void onError(String message) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((App)getActivity().getApplicationContext()).releaseMainComponent();
    }

    @Override
    public void onClick(Track track) {
        Toast.makeText(getContext(), track.getName(), Toast.LENGTH_SHORT).show();
    }
}
