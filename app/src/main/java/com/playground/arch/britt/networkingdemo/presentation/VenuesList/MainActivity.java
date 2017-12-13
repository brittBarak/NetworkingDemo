package com.playground.arch.britt.networkingdemo.presentation.VenuesList;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.playground.arch.britt.networkingdemo.R;
import com.playground.arch.britt.networkingdemo.domain.GetVenues;
import com.playground.arch.britt.networkingdemo.presentation.model.VenueViewModel;
import com.playground.arch.britt.networkingdemo.presentation.utils.MutableListLiveData.ListInfo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Observer<ListInfo<VenueViewModel>> {

    RecyclerView recyclerView;
    VenuesAdapter adapter;
    GetVenues getVenues;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(this);

        recyclerView = findViewById(R.id.rv_places);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VenuesAdapter(null);
        recyclerView.setAdapter(adapter);

        getVenues = new GetVenues();
    }


    @Override
    public void onClick(View v) {
        getVenues.execute(this)
                .observe(this, this);

        v.setClickable(false);
    }

    @Override
    public void onChanged(ListInfo<VenueViewModel> listInfo) {
        if (listInfo.getIndexChanged() >= 0) {
            adapter.setItem(listInfo.getIndexChanged(), listInfo.getItems());
        } else {
            adapter.setItems(listInfo.getItems());
        }
    }
}
