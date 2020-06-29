package com.danilketov.test.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danilketov.test.R;
import com.danilketov.test.adapter.SpecialAdapter;
import com.danilketov.test.adapter.WorkerAdapter;
import com.danilketov.test.model.Worker;
import com.danilketov.test.network.HttpClient;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class WorkerFragment extends Fragment {

    private RecyclerView recyclerView;
    private WorkerAdapter adapter;
    private HttpClient httpClient;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_worker, container, false);

        initRecyclerView(view);
        progressBar = view.findViewById(R.id.progress_bar);

        httpClient = new HttpClient();

        new GetWorkersAsyncTask().execute();

        return view;
    }

    private class GetWorkersAsyncTask extends AsyncTask<String, Void, ArrayList<Worker>> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Worker> doInBackground(String... queries) {
            try {
                return httpClient.getWorkersInfo();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Worker> result) {
            progressBar.setVisibility(View.GONE);

            if(result != null) {
                adapter.addItems(result);
            } else {
                Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.worker_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Название специальности");
        WorkerAdapter.OnInfoWorkerClickListener listener = new WorkerAdapter.OnInfoWorkerClickListener() {
            @Override
            public void onInfoWorkerClick(Worker worker) {
                String lastName = worker.getLastName();
                String firstName = worker.getFirstName();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new DescWorkerFragment().newInstance(lastName, firstName))
                        .addToBackStack(null)
                        .commit();

            }
        };
        adapter = new WorkerAdapter(listener);
        recyclerView.setAdapter(adapter);
    }
}
