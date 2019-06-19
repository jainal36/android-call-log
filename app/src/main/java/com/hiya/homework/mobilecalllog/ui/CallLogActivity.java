package com.hiya.homework.mobilecalllog.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.hiya.homework.mobilecalllog.R;
import com.hiya.homework.mobilecalllog.databinding.CallLogActivityBinding;


public class CallLogActivity extends AppCompatActivity {
    CallLogViewModel callLogViewModel;
    CallLogActivityBinding binding;
    CallLogListAdapter callLogListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //using data binding, setup layout for the activity
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //initialize view model
        callLogViewModel = ViewModelProviders.of(this).get(CallLogViewModel.class);

        //setup adapter class for the recylerview
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        callLogListAdapter = new CallLogListAdapter(getApplicationContext());
        binding.recyclerview.setAdapter(callLogListAdapter);

        //when new data available, call submit list method of the PagedListAdapter
        callLogViewModel.getCalllogList().observe(this, pagedList -> {
            callLogListAdapter.submitList(pagedList);
        });
    }
}
