package com.example.webconnectassignment.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webconnectassignment.model.ActiveItems;
import com.example.webconnectassignment.R;
import com.example.webconnectassignment.listener.ClickListener;

import java.util.List;

public class RecyclerviewItemAdapter extends RecyclerView.Adapter<RecyclerviewItemAdapter.MyViewHolder>{

    private List<ActiveItems> itemsList;
    private ClickListener clickListener;

    public RecyclerviewItemAdapter(List<ActiveItems> itemsList) {
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public RecyclerviewItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.active_card_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewItemAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final ActiveItems item = itemsList.get(position);
        holder.name.setText(item.getName());
        //holder.price.setText(String.valueOf(item.getPrice()));

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(v,item,position);
            }
        });
    }

    @Override
    public int getItemCount() {

        return itemsList.size();
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView name,price;
        private CardView itemLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            itemLayout =  itemView.findViewById(R.id.card_active);
        }
    }
}
