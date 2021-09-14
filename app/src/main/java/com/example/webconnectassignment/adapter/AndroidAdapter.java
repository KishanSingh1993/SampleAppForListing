package com.example.webconnectassignment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webconnectassignment.R;
import com.example.webconnectassignment.listener.RemoveClickListener;
import com.example.webconnectassignment.model.AndroidUserModel;

import java.util.ArrayList;

public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.ViewHolder> {

    private ArrayList<AndroidUserModel> courseModalArrayList;
    private Context context;
    private RemoveClickListener removeClickListener;
    // creating a constructor for our variables.
    public AndroidAdapter(ArrayList<AndroidUserModel> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AndroidAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_android, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AndroidAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // setting data to our views of recycler view.
        AndroidUserModel modal = courseModalArrayList.get(position);
        holder.courseNameTV.setText(modal.getUserEmail());

        holder.txtRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeClickListener.onClick(view,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return courseModalArrayList.size();
    }

    public void setOnRemoveItemClickListener(RemoveClickListener clickListener) {
        this.removeClickListener = clickListener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views.
        private TextView courseNameTV,txtRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            txtRemove = itemView.findViewById(R.id.remove);

        }
    }
}
