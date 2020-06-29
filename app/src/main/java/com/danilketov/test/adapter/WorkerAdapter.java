package com.danilketov.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danilketov.test.R;
import com.danilketov.test.fragment.WorkerFragment;
import com.danilketov.test.model.Worker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder> {

    private List<Worker> workers = new ArrayList<>();

    private OnInfoWorkerClickListener onInfoWorkerClickListener;

    public WorkerAdapter(OnInfoWorkerClickListener onInfoWorkerClickListener) {
        this.onInfoWorkerClickListener = onInfoWorkerClickListener;
    }

    public interface OnInfoWorkerClickListener {
        void onInfoWorkerClick (Worker worker);
    }

    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_view_worker, parent, false);

        return new WorkerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, int position) {
        Worker worker = workers.get(position);
        holder.bind(worker);
    }

    @Override
    public int getItemCount() {
        return workers.size();
    }

    public void addItems(List<Worker> items) {
        workers.addAll(items);
        notifyDataSetChanged();
    }

    class WorkerViewHolder extends RecyclerView.ViewHolder {

        private TextView firstNameTextView;
        private TextView lastNameTextView;
        private TextView ageTextView;


        public WorkerViewHolder(@NonNull View itemView) {
            super(itemView);

            firstNameTextView = itemView.findViewById(R.id.first_name_text_view);
            lastNameTextView = itemView.findViewById(R.id.last_name_text_view);
            ageTextView = itemView.findViewById(R.id.value_age_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Worker worker = workers.get(getLayoutPosition());
                    onInfoWorkerClickListener.onInfoWorkerClick(worker);
                }
            });
        }

        void bind(Worker worker) {
            firstNameTextView.setText(worker.getFirstName());
            lastNameTextView.setText(worker.getLastName());
            ageTextView.setText(worker.getBirthday());
        }
    }

//    private String getFormattedDate(String dateString) {
//        SimpleDateFormat githubFormat = new SimpleDateFormat("yyyy-mm-dd");
//        try {
//            Date date = githubFormat.parse(dateString);
//
//            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-mm-yyyy");
//            return outputFormat.format(date);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
