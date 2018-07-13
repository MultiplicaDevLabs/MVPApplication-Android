package com.multiplica.cleanarchitecture.mvpapplication.presentation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.multiplica.cleanarchitecture.mvpapplication.R;
import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseHolder;
import com.multiplica.cleanarchitecture.mvpapplication.domain.callback.IOnItemRecyclerClickListener;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 27/06/18.
 */

public class ListRecycler extends RecyclerView.Adapter<BaseHolder>{

    private Context context;
    private ArrayList<EarthquakeEntity> data;
    private IOnItemRecyclerClickListener listener;

    public ListRecycler(Context context, ArrayList<EarthquakeEntity> data) {

        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_main_item, parent, false);

        return new ListRecycler.ContentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {

        ContentHolder contentHolder = (ContentHolder) holder;

        contentHolder.itemTitle.setText(data.get(position).getTitle());
        contentHolder.itemDescription.setText(data.get(position).getPlace());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemRecyclerClickListener(
            IOnItemRecyclerClickListener<EarthquakeEntity> listener) {
        this.listener = listener;
    }

    protected class ContentHolder extends BaseHolder{

        @BindView(R.id.recycler_main_item_title)
        TextView itemTitle;
        @BindView(R.id.recycler_main_item_description)
        TextView itemDescription;

        public ContentHolder(View view) {

            super(view);

            ButterKnife.bind(this, view);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.earthquake_view_item:
                    if (listener != null) {
                        EarthquakeEntity entity = data.get(getAdapterPosition());
                        listener.onItemRecyclerClick(v, getAdapterPosition(), entity);
                    }
                default:
            }
        }
    }
}
