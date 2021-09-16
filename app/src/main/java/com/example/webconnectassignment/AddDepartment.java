package com.example.webconnectassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.webconnectassignment.adapter.AndroidAdapter;
import com.example.webconnectassignment.adapter.IosAdapter;
import com.example.webconnectassignment.listener.RemoveClickListener;
import com.example.webconnectassignment.model.AndroidUserModel;
import com.example.webconnectassignment.model.Employee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddDepartment extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener, View.OnClickListener {

    String[] department = {"Please Select Department", "Android", "IOS", "Web"};
    private EditText etDepartment, etDepartmentIos;
    private EditText etUser,etUserIos;
    private LinearLayout ll2,ll4;
    // variable for our adapter class and array list
    private RecyclerView courseRV,iosRV;
    private AndroidAdapter adapter;
    private IosAdapter adapter1;
    private IosAdapter adapter2;
    private ArrayList<AndroidUserModel> courseModalArrayList;
    private ArrayList<AndroidUserModel> courseModalArrayList1;
    private ArrayList<AndroidUserModel> courseModalArrayList2;
    private String androidJson, iosJson, webJson;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);
        init();
    }

    @SuppressLint("SetTextI18n")
    private void init(){

        etDepartment = findViewById(R.id.etDepartment);
        etDepartmentIos = findViewById(R.id.etDepartmentIos);
        etUser = findViewById(R.id.etEmail);
        etUserIos = findViewById(R.id.etEmailIos);
        gson = new Gson();
        ll2 = findViewById(R.id.ll2);
        ll4 = findViewById(R.id.ll4);
        ll2.setVisibility(View.GONE);
        ll4.setVisibility(View.GONE);

        courseRV = findViewById(R.id.rv1);
        iosRV = findViewById(R.id.rv2);
        adapter = new AndroidAdapter(courseModalArrayList, AddDepartment.this);

        Spinner spin = findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,department);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        AppCompatButton btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        AppCompatButton addUser = findViewById(R.id.addUser);
        addUser.setOnClickListener(this);

        AppCompatButton addUserIos = findViewById(R.id.addUserIos);
        addUserIos.setOnClickListener(this);

        AppCompatButton btnSaveIos = findViewById(R.id.btnSaveIos);
        btnSaveIos.setOnClickListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (i==1){
            etDepartment.setText(department[i]);
            courseModalArrayList1 = new ArrayList<>();
            courseModalArrayList1.clear();
            loadData();
            buildRecyclerView();
        }
        else if (i==2){
            etDepartment.setText(department[i]);
            courseModalArrayList = new ArrayList<>();
            courseModalArrayList.clear();
            loadDataIos();
            buildRecyclerViewIos();
        }
        else if (i==3){
            etDepartmentIos.setText(department[i]);

            loadDataWeb();
            buildRecyclerViewWeb();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void saveData(String email) {
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for editor to
        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson = new Gson();

        // getting data from gson and storing it in a string.
        androidJson = gson.toJson(courseModalArrayList);

        Log.d("Json Data",androidJson);


        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("androidUser", androidJson);

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply();
        etUser.setText("");

        // after saving data we are displaying a toast message.
        //Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
    }

    private void buildRecyclerView() {
        // initializing our adapter class.
        adapter = new AndroidAdapter(courseModalArrayList, AddDepartment.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        courseRV.setHasFixedSize(true);

        // setting layout manager to our recycler view.
        courseRV.setLayoutManager(manager);

        // setting adapter to our recycler view.
        courseRV.setAdapter(adapter);
    }

    private void loadData() {
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("androidUser", null);

//        if (json!=null){
//            etDepartment.setText("Android");
//        }

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<AndroidUserModel>>() {}.getType();

        // in below line we are getting data from gson
        // and saving it to our array list
        courseModalArrayList = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (courseModalArrayList == null) {
            // if the array list is empty
            // creating a new array list.
            courseModalArrayList = new ArrayList<>();
        }

    }

    private void saveDataIos() {
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for editor to
        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson = new Gson();

        // getting data from gson and storing it in a string.
        iosJson = gson.toJson(courseModalArrayList1);

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("iosUser", iosJson);

        // below line is to apply changes
        // and save data in shared prefs.
         editor.apply();
         etUser.setText("");
        // after saving data we are displaying a toast message.
        //Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
    }

    private void buildRecyclerViewIos() {
        // initializing our adapter class.
        adapter1 = new IosAdapter(courseModalArrayList1, AddDepartment.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        courseRV.setHasFixedSize(true);

        // setting layout manager to our recycler view.
        courseRV.setLayoutManager(manager);

        // setting adapter to our recycler view.
        courseRV.setAdapter(adapter1);
    }

    @SuppressLint("SetTextI18n")
    private void loadDataIos() {
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("iosUser", null);

//        if (json != null){
//            etDepartmentIos.setText("IOS");
//        }

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<AndroidUserModel>>() {}.getType();

        // in below line we are getting data from gson
        // and saving it to our array list
        courseModalArrayList1 = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (courseModalArrayList1 == null) {
            // if the array list is empty
            // creating a new array list.
            courseModalArrayList1 = new ArrayList<>();
        }
    }

    private void saveDataWeb() {
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for editor to
        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson = new Gson();

        // getting data from gson and storing it in a string.
        webJson = gson.toJson(courseModalArrayList2);

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("webUser", webJson);

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply();
        etUserIos.setText("");
        // after saving data we are displaying a toast message.
        //Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
    }

    private void buildRecyclerViewWeb() {
        // initializing our adapter class.
        adapter2 = new IosAdapter(courseModalArrayList2, AddDepartment.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        iosRV.setHasFixedSize(true);

        // setting layout manager to our recycler view.
        iosRV.setLayoutManager(manager);

        // setting adapter to our recycler view.
        iosRV.setAdapter(adapter2);
    }

    @SuppressLint("SetTextI18n")
    private void loadDataWeb() {
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("webUser", null);

//        if (json != null){
//            etDepartmentIos.setText("IOS");
//        }

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<AndroidUserModel>>() {}.getType();

        // in below line we are getting data from gson
        // and saving it to our array list
        courseModalArrayList2 = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (courseModalArrayList2 == null) {
            // if the array list is empty
            // creating a new array list.
            courseModalArrayList2 = new ArrayList<>();
        }
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case  R.id.btnSave:
                if (etDepartment.getText().toString().equals("Android")){
                    courseModalArrayList1 = new ArrayList<>();
                    courseModalArrayList1.clear();
                    addAndroidUser();
                }
                else if(etDepartment.getText().toString().equals("IOS")){
                    courseModalArrayList = new ArrayList<>();
                    courseModalArrayList.clear();
                    addIosUser();
                }

                break;

            case R.id.btnSaveIos:
                   addWebUser();
                break;
            case R.id.addUser:
                ll2.setVisibility(View.VISIBLE);
                break;

            case R.id.addUserIos:
                ll4.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void addAndroidUser(){
        if (!etUser.getText().toString().equals("") && !etDepartment.getText().toString().equals("")) {

            // getting data from gson and storing it in a string.
            androidJson = gson.toJson(courseModalArrayList);
            iosJson = gson.toJson(courseModalArrayList1);
            if (androidJson.contains(etUser.getText().toString()) || iosJson.contains(etUser.getText().toString())){
                Toast.makeText(getApplicationContext(),"User Already Exist",Toast.LENGTH_SHORT).show();
            }
            else {
                // below line is use to add data to array list.
                courseModalArrayList.add(new AndroidUserModel(etUser.getText().toString(),etDepartment.getText().toString()));
                // notifying adapter when new data added.
                adapter.notifyItemInserted(courseModalArrayList.size());
                saveData(etUser.getText().toString());
            }


        }
        else {
            Toast.makeText(this, "All Fields are Required", Toast.LENGTH_SHORT).show();

        }
    }

    private void addIosUser(){
        if (!etUser.getText().toString().equals("") && !etDepartment.getText().toString().equals("")) {

            // getting data from gson and storing it in a string.
            androidJson = gson.toJson(courseModalArrayList);
            iosJson = gson.toJson(courseModalArrayList1);
            if (androidJson.contains(etUser.getText().toString()) || iosJson.contains(etUser.getText().toString())){
                Toast.makeText(getApplicationContext(),"User Already Exist",Toast.LENGTH_SHORT).show();
            }
            else {
                // below line is use to add data to array list.
                courseModalArrayList1.add(new AndroidUserModel(etUser.getText().toString(),etDepartment.getText().toString()));
                // notifying adapter when new data added.
                adapter1.notifyItemInserted(courseModalArrayList1.size());

                saveDataIos();
            }

        }
        else {
            Toast.makeText(this, "All Fields are Required", Toast.LENGTH_SHORT).show();

        }
    }

    private void addWebUser(){
        if (!etUserIos.getText().toString().equals("") && !etDepartmentIos.getText().toString().equals("")) {

            // getting data from gson and storing it in a string.
            androidJson = gson.toJson(courseModalArrayList);
            iosJson = gson.toJson(courseModalArrayList1);
            webJson = gson.toJson(courseModalArrayList2);
            if (androidJson.contains(etUserIos.getText().toString()) || iosJson.contains(etDepartmentIos.getText().toString()) || webJson.contains(etUserIos.getText().toString())){
                Toast.makeText(getApplicationContext(),"User Already Exist",Toast.LENGTH_SHORT).show();
            }
            else {
                // below line is use to add data to array list.
                courseModalArrayList2.add(new AndroidUserModel(etUserIos.getText().toString(),etDepartmentIos.getText().toString()));
                // notifying adapter when new data added.
                adapter2.notifyItemInserted(courseModalArrayList2.size());

                saveDataWeb();
            }

        }
        else {
            Toast.makeText(this, "All Fields are Required", Toast.LENGTH_SHORT).show();

        }
    }

}