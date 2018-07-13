package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.multiplica.cleanarchitecture.mvpapplication.R;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.AttachFragment;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.presenter.DetailPresenterImpl;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.presenter.IDetailPresenter;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.presenter.ListPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends AttachFragment implements IDetailPresenter.View{

    private View rootView;
    private EarthquakeEntity earthquakeEntity;

    private DetailPresenterImpl presenter;

    @BindView(R.id.place_description_text_view)
    TextView placeDescription;

    @BindView(R.id.time_description_text_view)
    TextView timeDescription;

    @BindView(R.id.title_description_text_view)
    TextView titleDescription;

    @BindView(R.id.latitude_description_text_view)
    TextView latitudeDescription;

    @BindView(R.id.longitude_description_text_view)
    TextView longitudeDescription;


    public static DetailFragment newInstance(EarthquakeEntity earthquake) {
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setHasOptionsMenu(true);
        detailFragment.earthquakeEntity = earthquake;

        return detailFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_detail,container,false);
        ButterKnife.bind(this, rootView);

        initResources();
        initPresenter();

        return rootView;
    }

    private void initResources(){

    }

    private void initPresenter(){
        presenter = new DetailPresenterImpl();
        presenter.setView(this);
        presenter.initialize();
    }

    @Override
    public void initView() {
        placeDescription.setText(earthquakeEntity.getPlace());
        timeDescription.setText(String.valueOf(earthquakeEntity.getTime()));
        titleDescription.setText(earthquakeEntity.getTitle());
        latitudeDescription.setText(String.valueOf(earthquakeEntity.getLatitude()));
        longitudeDescription.setText(String.valueOf(earthquakeEntity.getLongitude()));
    }

    @Override
    public void initNotAvailableView() {

    }

    @Override
    public void updateView() {

    }
}
