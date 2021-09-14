package com.example.webconnectassignment.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.webconnectassignment.model.ActiveItems;
import com.example.webconnectassignment.R;
import com.example.webconnectassignment.adapter.RecyclerviewItemAdapter;
import com.example.webconnectassignment.listener.ClickListener;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActiveFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecyclerviewItemAdapter recyclerviewItemAdapter;
    private List<ActiveItems> itemsList;

    public ActiveFragment() {
        // Required empty public constructor
    }

    public static ActiveFragment newInstance(String param1, String param2) {
        ActiveFragment fragment = new ActiveFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_active, container, false);
        itemsList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.activeRecycler);
        recyclerviewItemAdapter = new RecyclerviewItemAdapter(itemsList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerviewItemAdapter);

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setTitleText("Select A Date");

        MaterialDatePicker materialDatePicker = builder.build();

        recyclerviewItemAdapter.setOnItemClickListener(new ClickListener<ActiveItems>(){
            @Override
            public void onClick(View view, ActiveItems data, int position) {
                //Toast.makeText(getContext(),"Position = "+position+"\n Item = "+data.getName(), Toast.LENGTH_SHORT).show();
                materialDatePicker.show(getParentFragmentManager(),"Demo");
            }


        });
        prepareItems();
        return view;
    }

    private void prepareItems(){
        for(int i = 0; i < 10; i++) {
            ActiveItems items = new ActiveItems("Check my car fluids insolution",20+i);
            itemsList.add(items);
        }
        recyclerviewItemAdapter.notifyDataSetChanged();
    }
}