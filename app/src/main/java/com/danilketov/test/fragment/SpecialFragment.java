package com.danilketov.test.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danilketov.test.R;
import com.danilketov.test.adapter.SpecialAdapter;
import com.danilketov.test.model.Specialty;
import com.danilketov.test.network.HttpClient;

import java.io.IOException;
import java.util.ArrayList;

public class SpecialFragment extends Fragment {

    private RecyclerView recyclerView;
    private SpecialAdapter adapter;
    private HttpClient httpClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_special, container, false);

        initRecyclerView(view);

        httpClient = new HttpClient();

        new GetSpecialsAsyncTask().execute();

        return view;
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.special_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Специальности");
        adapter = new SpecialAdapter();
        recyclerView.setAdapter(adapter);
    }

    private class GetSpecialsAsyncTask extends AsyncTask<String, Void, ArrayList<Specialty>> {

        @Override
        protected ArrayList<Specialty> doInBackground(String... queries) {

            try {
                return httpClient.getSpecialtyInfo();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Specialty> result) {

            if(result != null) {
                adapter.addItems(result);
            } else {
                Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
