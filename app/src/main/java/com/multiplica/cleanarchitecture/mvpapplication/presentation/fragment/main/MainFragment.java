package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.multiplica.cleanarchitecture.mvpapplication.R;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.AttachFragment;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.presenter.IMainPresenter;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.presenter.MainPresenterImpl;
import com.multiplica.cleanarchitecture.mvpapplication.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 26/06/18.
 */

public class MainFragment extends AttachFragment implements IMainPresenter.View, View.OnClickListener{

    private View rootView;

    @BindView(R.id.selected_date_text_view)
    TextView selectedDateTextView;

    @BindView(R.id.select_date_button)
    Button selectDateButton;

    @BindView(R.id.download_data_button)
    Button downloadDataButton;

    @BindView(R.id.load_data_button)
    Button loadDataButton;

    private MainPresenterImpl presenter;

    private String startDate;
    private String endDate;


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
        selectDateButton.setOnClickListener(this);
        downloadDataButton.setOnClickListener(this);
        loadDataButton.setOnClickListener(this);
    }

    private void initPresenter(){
        presenter = new MainPresenterImpl();
        presenter.initialize();
        presenter.setView(this);
        presenter.setActivity(getActivity());

    }

    @Override
    public void initView(ArrayList<EarthquakeEntity> earthquakes) {

    }

    @Override
    public void initNotAvailableView() {

    }

    @Override
    public void updateView() {

    }

    @Override
    public void setDateView(String startDate, String endDate) {

        this.startDate = startDate;
        this.endDate = endDate;

        selectedDateTextView.setText(endDate);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.select_date_button:

                presenter.onSetCalendarDate();

                break;

            case R.id.download_data_button:
                callback.onFragmentChanged(ListFragment.newInstance(true, startDate, endDate),R.id.container);
                break;

            case R.id.load_data_button:
                callback.onFragmentChanged(ListFragment.newInstance(false),R.id.container);
                break;
        }


    }

}
