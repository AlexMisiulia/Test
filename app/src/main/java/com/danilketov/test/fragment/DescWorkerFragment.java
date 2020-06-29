package com.danilketov.test.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.danilketov.test.R;

public class DescWorkerFragment extends Fragment {

    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView ageTextView;
    private TextView birthdayTextView;
    private TextView specialtyTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_desc_worker, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Описание работника");

        initView(view);

        getData();

        return view;
    }

    private void getData() {
        Bundle args = getArguments();
        if (args != null) {
            String lastName = args.getString("lName");
            String firstName = args.getString("FName");

            lastNameTextView.setText(lastName);
            firstNameTextView.setText(firstName);
        } else {
            Toast.makeText(getActivity(), "Fragment args null", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView(View view) {
        firstNameTextView = view.findViewById(R.id.value_first_name_text_view);
        lastNameTextView = view.findViewById(R.id.value_last_name_text_view);
        ageTextView = view.findViewById(R.id.value_age_text_view);
        birthdayTextView = view.findViewById(R.id.value_birthday_text_view);
        specialtyTextView = view.findViewById(R.id.value_special_text_view);
    }

    public static Fragment newInstance(String lastName, String firstName) {
        Bundle args = new Bundle();
        args.putString("lName", lastName);
        args.putString("FName", firstName);
        DescWorkerFragment fragment = new DescWorkerFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
