package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.multiplica.cleanarchitecture.mvpapplication.R;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.AttachFragment;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.presenter.IMainPresenter;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.presenter.MainPresenterImpl;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.view.adapter.MainRecycler;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 26/06/18.
 */

public class MainFragment extends AttachFragment implements IMainPresenter.View{

    private View rootView;

    @BindView(R.id.main_recycler_earthquakes)
    RecyclerView recyclerView;

    private MainRecycler adapter;

    private MainPresenterImpl presenter;


    public static MainFragment newInstance() {
        MainFragment mainFragment = new MainFragment();
        mainFragment.setHasOptionsMenu(true);
        return mainFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this, rootView);

        initResources();
        initPresenter();

        return rootView;
    }

    private void initResources(){
        LinearLayoutManager llmGral = new LinearLayoutManager(getActivity());
        llmGral.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llmGral);
    }

    private void initPresenter(){
        presenter = new MainPresenterImpl();
        presenter.setView(this);
        presenter.initialize();
        presenter.onGetEarthquakes();
    }

    @Override
    public void initView(ArrayList<EarthquakeEntity> earthquakes) {
        adapter = new MainRecycler(getActivity(),earthquakes);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initNotAvailableView() {

    }

    @Override
    public void updateView() {

    }
}
