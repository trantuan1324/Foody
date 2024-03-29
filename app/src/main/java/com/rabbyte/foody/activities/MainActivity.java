package com.rabbyte.foody.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rabbyte.foody.R;
import com.rabbyte.foody.adapters.BestFoodAdapter;
import com.rabbyte.foody.adapters.CategoryAdapter;
import com.rabbyte.foody.databinding.ActivityMainBinding;
import com.rabbyte.foody.domains.Category;
import com.rabbyte.foody.domains.Food;
import com.rabbyte.foody.domains.Location;
import com.rabbyte.foody.domains.Price;
import com.rabbyte.foody.domains.Time;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(mBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initLocation();
        initTime();
        initPrice();
        initBestFood();
        initCategory();
    }

    private void initCategory() {
        DatabaseReference myRef = mDatabase.getReference("Category");
        mBinding.progressBarCategory.setVisibility(View.VISIBLE);
        ArrayList<Category> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(Category.class));
                    }
                    for (Category c : list) {
                        Log.e("itemTN", c.toString());
                    }
                    if (!list.isEmpty()) {
                        mBinding.categoryView.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
                        RecyclerView.Adapter<CategoryAdapter.ViewHolder> adapter = new CategoryAdapter(list);
                        mBinding.categoryView.setAdapter(adapter);
                    }
                    mBinding.progressBarCategory.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initBestFood() {
        DatabaseReference myRef = mDatabase.getReference("Foods");
        mBinding.progressBarBestFood.setVisibility(View.VISIBLE);

        ArrayList<Food> list = new ArrayList<>();
        Query query = myRef.orderByChild("BestFood").equalTo(true);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue :
                            snapshot.getChildren()) {
                        list.add(issue.getValue(Food.class));
                    }
                    if (!list.isEmpty()) {
                        mBinding.bestFoodView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        RecyclerView.Adapter<BestFoodAdapter.ViewHolder> adapter = new BestFoodAdapter(list);
                        mBinding.bestFoodView.setAdapter(adapter);
                    }
                    mBinding.progressBarBestFood.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initTime() {
        DatabaseReference myRef = mDatabase.getReference("Time");
        ArrayList<Time> timeList = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren())
                        timeList.add(issue.getValue(Time.class));

                    ArrayAdapter<Time> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item, timeList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mBinding.timeSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void initLocation() {
        DatabaseReference myRef = mDatabase.getReference("Location");
        ArrayList<Location> locationsList = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren())
                        locationsList.add(issue.getValue(Location.class));

                    ArrayAdapter<Location> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item, locationsList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mBinding.locationSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void initPrice() {
        DatabaseReference myRef = mDatabase.getReference("Price");
        ArrayList<Price> pricesList = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren())
                        pricesList.add(issue.getValue(Price.class));

                    ArrayAdapter<Price> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item, pricesList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mBinding.priceSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}