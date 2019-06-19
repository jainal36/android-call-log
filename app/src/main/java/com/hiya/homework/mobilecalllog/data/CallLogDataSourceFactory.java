package com.hiya.homework.mobilecalllog.data;

import android.app.Application;
import android.arch.paging.DataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hiya.homework.mobilecalllog.data.model.CallLogItem;

public class CallLogDataSourceFactory extends DataSource.Factory<Integer, CallLogItem> {
    private final String TAG = "DataSourceFactory";
    private final Application application;
    public CallLogDataSourceFactory(@NonNull Application application) {
        Log.d(TAG, "CallLogDataSourceFactory-");
        this.application = application;
    }

    @Override
    public DataSource<Integer, CallLogItem> create() {
        Log.d(TAG, "create()");
        return new CallLogDataSource(application);
    }
}
