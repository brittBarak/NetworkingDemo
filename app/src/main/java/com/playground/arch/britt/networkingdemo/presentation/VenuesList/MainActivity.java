package com.playground.arch.britt.networkingdemo.presentation.VenuesList;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.playground.arch.britt.networkingdemo.AppExecutors;
import com.playground.arch.britt.networkingdemo.R;
import com.playground.arch.britt.networkingdemo.data.db.AppDatabase;
import com.playground.arch.britt.networkingdemo.domain.GetVenues;
import com.playground.arch.britt.networkingdemo.presentation.model.VenueViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    VenuesAdapter adapter;

    MainActivityViewModel viewModel;

    GetVenues getVenues;

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

        getVenues = new GetVenues(this);
        viewModel = new MainActivityViewModel(getVenues, "Nearby Venues");

        viewModel.venues.observe(this, list -> adapter.setList(list));

        setTitle(viewModel.title);
    }

    public class MainActivityViewModel extends ViewModel {

        public LiveData<List<VenueViewModel>> venues;
        public String title;


        public MainActivityViewModel(GetVenues getVenues, String title) {
            this.venues = getVenues.getLiveDataForView();
            this.title = title;
        }
    }
}
