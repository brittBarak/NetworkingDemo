package com.playground.arch.britt.networkingdemo.presentation.VenuesList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.playground.arch.britt.networkingdemo.AppExecutors;
import com.playground.arch.britt.networkingdemo.R;
import com.playground.arch.britt.networkingdemo.data.db.AppDatabase;
import com.playground.arch.britt.networkingdemo.domain.GetVenues;
import com.playground.arch.britt.networkingdemo.presentation.model.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    VenuesAdapter adapter;

    MainActivityViewModel viewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppExecutors.get().diskIO().execute(() -> {
            AppDatabase.getInMemoryDatabase(this).venueDao().deleteAll();
                }
        );

        recyclerView = findViewById(R.id.rv_places);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VenuesAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new MainActivityViewModel(new GetVenues(this), "Nearby Venues");
        setTitle(viewModel.title);

        viewModel.venues.observe(this, list -> adapter.setList(list));


    }

    public void onLocationChanged(double[] newLocation){
        viewModel.location.setValue(newLocation);
    }

}
