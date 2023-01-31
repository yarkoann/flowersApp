package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    ListView mRecyclerView;

    List<Flower> mFlowers;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);
        mFlowers = new ArrayList<>();
        mRecyclerView = (ListView) findViewById(R.id.recyclerView);
        mProgressBar.setVisibility(View.VISIBLE);





        FlowersAPI flowersAPI = FlowersAPI.retrofit.create(FlowersAPI.class);
        final Call<List<Flower>> call = flowersAPI.getData();

        call.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                if (response.isSuccessful()){
                    mFlowers.addAll(response.body());
                    FlowerAdapter adapter = new FlowerAdapter(MainActivity.this, mFlowers);
                    mRecyclerView.setAdapter(adapter);
                    mProgressBar.setVisibility(View.INVISIBLE);
                } else {
                    // Обрабатываем ошибку
                    ResponseBody errorBody = response.errorBody();
                    try {
                        Toast.makeText(MainActivity.this, errorBody.string(),
                                Toast.LENGTH_SHORT).show();
                        mProgressBar.setVisibility(View.INVISIBLE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable throwable) {
                Log.d("GIT", ""+throwable.getMessage());
                Toast.makeText(MainActivity.this, "Что-то пошло не так. ",
                        Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.INVISIBLE);

            }
        });

    }
}

