package com.example.webconnectassignment.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.webconnectassignment.model.ActiveItems;
import com.example.webconnectassignment.R;
import com.example.webconnectassignment.adapter.RecyclerviewItemAdapter;
import com.example.webconnectassignment.listener.ClickListener;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.savvi.rangedatepicker.CalendarPickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
                //materialDatePicker.show(getParentFragmentManager(),"Demo");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    showCustomDialog(view);
                }
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showCustomDialog(View v) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = v.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.custom_dialog, viewGroup, false);
        AppCompatButton okButton = dialogView.findViewById(R.id.buttonOk);
        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 10);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.YEAR, - 10);

        CalendarPickerView calendar = dialogView.findViewById(R.id.datepicker);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);

        calendar.deactivateDates(list);
        ArrayList<Date> arrayList = new ArrayList<>();
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

            String strdate = "22-4-2019";
            String strdate2 = "26-4-2019";

            Date newdate = dateformat.parse(strdate);
            Date newdate2 = dateformat.parse(strdate2);
            arrayList.add(newdate);
            arrayList.add(newdate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.init(lastYear.getTime(), nextYear.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDate(new Date())
                // deactivates given dates, non selectable
                .withDeactivateDates(list)
                // highlight dates in red color, mean they are aleady used.
                //.withHighlightedDates(arrayList)
                // add subtitles to the dates pass a arrayList of SubTitle objects with date and text
                ;

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "list " + calendar.getSelectedDates().toString(), Toast.LENGTH_LONG).show();
                alertDialog.dismiss();
            }
        });
    }
}