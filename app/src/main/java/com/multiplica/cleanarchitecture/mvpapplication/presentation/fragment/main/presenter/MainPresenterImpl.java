package com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.presenter;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BasePresenter;
import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseResponseList;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;
import com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.main.IMainInteractor;
import com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.main.MainInteractorImpl;
import com.multiplica.cleanarchitecture.mvpapplication.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.multiplica.cleanarchitecture.mvpapplication.utils.DateUtils.DATE_USA;

/**
 * Created by user on 27/06/18.
 */

public class MainPresenterImpl extends BasePresenter<IMainPresenter.View> implements IMainPresenter,
        DatePickerDialog.OnDateSetListener{

    private IMainInteractor interactor;

    private DatePickerDialog.OnDateSetListener dateListener = this;
    private Calendar calendar = Calendar.getInstance();


    @Override
    public void initialize() {
        super.initialize();
        interactor = new MainInteractorImpl();
        onGetEarthquakes();
    }

    @Override
    public void onGetEarthquakes() {
        interactor.getEarthquakeList(new BaseResponseList<EarthquakeEntity>() {
            @Override
            public void onSuccess(ArrayList<EarthquakeEntity> objList) {
                if(objList.size()>0){
                    getView().initView(objList);
                }else{
                    getView().initNotAvailableView();
                }
            }

        });
    }

    @Override
    public void onSetCalendarDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                dateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        long firstDate = (new GregorianCalendar(year,month, dayOfMonth).getTimeInMillis())-86400000;

        long secondDate = new GregorianCalendar(year, month, dayOfMonth).getTimeInMillis();
        calendar.setTimeInMillis(secondDate);

        String startDate = DateUtils.formatter(firstDate,DATE_USA);
        String endDate = DateUtils.formatter(secondDate,DATE_USA);

        getView().setDateView(startDate,endDate);
    }
}
