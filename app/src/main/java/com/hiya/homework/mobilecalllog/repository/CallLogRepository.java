package com.hiya.homework.mobilecalllog.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.hiya.homework.mobilecalllog.data.CallLogDataSourceFactory;
import com.hiya.homework.mobilecalllog.data.model.CallLogItem;

import java.util.concurrent.Executors;

public class CallLogRepository {
    LiveData<PagedList<CallLogItem>> callLogsLiveData;
    private final int PAGE_SIZE = 50;

    public CallLogRepository(@NonNull Application application) {
        CallLogDataSourceFactory dataSourceFactory = new CallLogDataSourceFactory(application);
        PagedList.Config pConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();
        callLogsLiveData  = (new LivePagedListBuilder(dataSourceFactory, pConfig))
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build();
    }

    public LiveData<PagedList<CallLogItem>> getCallLogsLiveData() {
        return callLogsLiveData;
    }
}
