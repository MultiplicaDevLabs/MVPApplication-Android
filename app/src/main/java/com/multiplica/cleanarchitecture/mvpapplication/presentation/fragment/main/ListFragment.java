package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DebugUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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

    @BindView(R.id.layoutRecycler)
    View layoutRecycler;
    @BindView(R.id.main_recycler_earthquakes)
    RecyclerView recyclerView;

    @BindView(R.id.layoutProgressBar)
    View layoutProgessBar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    private ListRecycler adapter;

    private ListPresenterImpl presenter;

    private boolean downLoadData;
    public static final String DOWNLOAD_DATA = "download_data";

    private String startDate;
    public static final String START_DATE = "start_date";

    private String endDate;
    public static final String END_DATE = "end_date";

    public static ListFragment newInstance(boolean downloadData, String startDate, String endDate) {
        ListFragment listFragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(DOWNLOAD_DATA,downloadData);
        bundle.putString(START_DATE,startDate);
        bundle.putString(END_DATE,endDate);
        listFragment.setArguments(bundle);
        listFragment.setHasOptionsMenu(true);
        return listFragment;
    }

    public static ListFragment newInstance(boolean downloadData) {
        ListFragment listFragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(DOWNLOAD_DATA,downloadData);
        listFragment.setArguments(bundle);
        listFragment.setHasOptionsMenu(true);
        return listFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downLoadData = getArguments() != null ? getArguments().getBoolean(DOWNLOAD_DATA):false;
        startDate = getArguments() != null ? getArguments().getString(START_DATE):"";
        endDate = getArguments() != null ? getArguments().getString(END_DATE):"";

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

        if(downLoadData){
            presenter.onGetEarthquakes(startDate,endDate);
        }else{
            presenter.onGetLocalEarthquakes();
        }


    }

    @Override
    public void initView(ArrayList<EarthquakeEntity> earthquakes) {
        layoutRecycler.setVisibility(View.VISIBLE);
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
    public void showProgressBar() {
        layoutProgessBar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        layoutProgessBar.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemRecyclerClick(View view, int position, EarthquakeEntity item) {
            callback.onFragmentChanged(DetailFragment.newInstance(item),R.id.container);
    }
}
