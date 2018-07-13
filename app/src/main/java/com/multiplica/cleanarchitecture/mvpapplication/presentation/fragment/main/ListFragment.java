package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DebugUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.multiplica.cleanarchitecture.mvpapplication.R;
import com.multiplica.cleanarchitecture.mvpapplication.domain.callback.IOnItemRecyclerClickListener;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.AttachFragment;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.presenter.IListPresenter;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.presenter.ListPresenterImpl;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.view.adapter.ListRecycler;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends AttachFragment implements IListPresenter.View,
        IOnItemRecyclerClickListener<EarthquakeEntity> {

    private View rootView;

    @BindView(R.id.main_recycler_earthquakes)
    RecyclerView recyclerView;

    private ListRecycler adapter;

    private ListPresenterImpl presenter;

    public static ListFragment newInstance() {
        ListFragment listFragment = new ListFragment();
        listFragment.setHasOptionsMenu(true);
        return listFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list,container,false);
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
        presenter = new ListPresenterImpl();
        presenter.setView(this);
        presenter.initialize();
        presenter.onGetEarthquakes();
    }

    @Override
    public void initView(ArrayList<EarthquakeEntity> earthquakes) {
        adapter = new ListRecycler(getActivity(),earthquakes);
        adapter.setOnItemRecyclerClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initNotAvailableView() {

    }

    @Override
    public void updateView() {

    }

    @Override
    public void onItemRecyclerClick(View view, int position, EarthquakeEntity item) {
            callback.onFragmentChanged(DetailFragment.newInstance(item),R.id.container);
    }
}
