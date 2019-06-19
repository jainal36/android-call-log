package com.hiya.homework.mobilecalllog.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.hiya.homework.mobilecalllog.data.model.CallLogItem;
import com.hiya.homework.mobilecalllog.repository.CallLogRepository;

public class CallLogViewModel extends AndroidViewModel {
    private LiveData<PagedList<CallLogItem>> calllogList;
    private CallLogRepository callLogRepository;

    public CallLogViewModel(@NonNull Application application) {
        super(application);
        callLogRepository = new CallLogRepository(application);
        calllogList = callLogRepository.getCallLogsLiveData();
    }

    public LiveData<PagedList<CallLogItem>> getCalllogList() {
        return calllogList;
    }
}
