package com.example.abc_test.framework.presentation.common.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abc_test.R;
import com.example.abc_test.business.domain.model.DataItem;
import com.example.abc_test.framework.presentation.common.clicklisteners.ListFragmentClickListener;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<DataItem> items = new ArrayList<>();
    private ListFragmentClickListener clickListener = null;

    public void setClickListener(ListFragmentClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void updateItems(List<DataItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_strategy_overview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(items.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
