package com.multiplica.cleanarchitecture.mvpapplication.presentation.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.multiplica.cleanarchitecture.mvpapplication.R;
import com.multiplica.cleanarchitecture.mvpapplication.data.network.IWebServices;
import com.multiplica.cleanarchitecture.mvpapplication.data.network.RetrofitClient;
import com.multiplica.cleanarchitecture.mvpapplication.data.network.response.ResponseQuery;
import com.multiplica.cleanarchitecture.mvpapplication.domain.callback.IOnFragmentChangedListener;
import com.multiplica.cleanarchitecture.mvpapplication.presentation.fragment.main.MainFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements IOnFragmentChangedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
    }

    @Override
    public void onFragmentChanged(Fragment fragment, int container) {

    }

    private void initFragment(){

        Fragment fragment = MainFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.container, fragment)
                .commit();
    }
}
