package com.hiya.homework.mobilecalllog.ui;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hiya.homework.mobilecalllog.data.model.CallLogItem;
import com.hiya.homework.mobilecalllog.databinding.CallLogItemBinding;

public class CallLogListAdapter extends PagedListAdapter<CallLogItem, RecyclerView.ViewHolder>{
    protected CallLogListAdapter(Context context) {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CallLogItemBinding binding = CallLogItemBinding.inflate(layoutInflater, parent, false);
        return new CallLogItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ((CallLogItemViewHolder)viewHolder).bindView(getItem(position));
    }

    public static class CallLogItemViewHolder extends RecyclerView.ViewHolder {
        CallLogItemBinding binding;
        public CallLogItemViewHolder(CallLogItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(CallLogItem callLogItem) {
            binding.phoneNo.setText(callLogItem.getPhoneNumber());
            binding.dateTime.setText(callLogItem.getCallDate());
            binding.callType.setText(callLogItem.getDirection());
        }
    }

    private static DiffUtil.ItemCallback<CallLogItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<CallLogItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull CallLogItem oldCallLogItem, @NonNull CallLogItem newCallLogItem) {
            return oldCallLogItem.getId() == newCallLogItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull CallLogItem oldCallLogItem, @NonNull CallLogItem newCallLogItem) {
            return oldCallLogItem.equals(newCallLogItem);
        }
    };
}