package com.hiya.homework.mobilecalllog.data;

import android.app.Application;
import android.arch.paging.PositionalDataSource;
import android.database.Cursor;
import android.database.SQLException;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.telephony.PhoneNumberUtils;
import android.util.Log;

import com.hiya.homework.mobilecalllog.data.model.CallLogItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CallLogDataSource extends PositionalDataSource<CallLogItem> {
    private final Application application;
    private static final String TAG = "CallLogDataSource";
    public CallLogDataSource(Application application) {
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<CallLogItem> callback) {
        Log.d(TAG, "loadInitial()");
        callback.onResult(getContacts(params.requestedLoadSize, params.requestedStartPosition), 0);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<CallLogItem> callback) {
        Log.d(TAG, "loadRange()");
        callback.onResult(getContacts(params.loadSize, params.startPosition));
    }

    private List<CallLogItem> getContacts(int limit, int offset)  {
        Log.d(TAG, "getContacts()");
        List<CallLogItem> calllogs = new ArrayList<>();
        //try with resource
        try(Cursor cursor = application.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null,
                null,
                null,
                CallLog.Calls.DATE + " DESC LIMIT " + limit + " OFFSET " + offset)
        ) {
            //calllogs.
            cursor.moveToFirst();
            Log.d(TAG, "cursor count = "+cursor.getCount());
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(CallLog.Calls._ID));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                String type = cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE));
                String date = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
                String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));
                String dir = null;
                int dircode = Integer.parseInt(type);
                switch (dircode) {
                    case CallLog.Calls.OUTGOING_TYPE:
                        dir = "OUTGOING";
                        break;
                    case CallLog.Calls.INCOMING_TYPE:
                        dir = "INCOMING";
                        break;

                    case CallLog.Calls.MISSED_TYPE:
                        dir = "MISSED";
                        break;
                }

                ///////
                Date callDayTime = new Date(Long.valueOf(date));
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                date = df.format(callDayTime);
                //////////

                calllogs.add(new CallLogItem(id, phoneNumber, duration, dir, date));
                Log.d("CallLogDataSource", ""+calllogs.get(calllogs.size() - 1));
                cursor.moveToNext();
            }
        } catch (SQLException e) {
            Log.d(TAG, ""+e);
        }
        //calllogs.add(new CallLogItem(1, "425-445-6160", "00:20", "Incoming", "3:05 PM"));
        return calllogs;
    }
}
