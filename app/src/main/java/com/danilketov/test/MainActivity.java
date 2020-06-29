package com.danilketov.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.danilketov.test.adapter.SpecialAdapter;
import com.danilketov.test.adapter.WorkerAdapter;
import com.danilketov.test.fragment.SpecialFragment;
import com.danilketov.test.fragment.WorkerFragment;
import com.danilketov.test.model.Worker;
import com.danilketov.test.network.HttpClient;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private WorkerAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Фрагмент главного экрана
        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new SpecialFragment())
                    .addToBackStack(null)
                    .commit();
        }

        setToolbar();
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
