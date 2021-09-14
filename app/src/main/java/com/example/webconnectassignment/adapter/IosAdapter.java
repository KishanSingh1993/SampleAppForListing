package com.example.webconnectassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webconnectassignment.R;
import com.example.webconnectassignment.model.AndroidUserModel;

import java.util.ArrayList;

public class IosAdapter extends RecyclerView.Adapter<IosAdapter.ViewHolder> {

    private ArrayList<AndroidUserModel> courseModalArrayList1;
    private Context context;

    // creating a constructor for our variables.
    public IosAdapter(ArrayList<AndroidUserModel> courseModalArrayList, Context context) {
        this.courseModalArrayList1 = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public IosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_android, parent, false);
        return new IosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IosAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        AndroidUserModel modal = courseModalArrayList1.get(position);
        holder.courseNameTV.setText(modal.getUserEmail());

    }

    @Override
    public int getItemCount() {
        return courseModalArrayList1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views.
        private TextView courseNameTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);

        }
    }
}

