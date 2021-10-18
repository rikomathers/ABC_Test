package com.example.abc_test.framework.presentation.common.adapter;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abc_test.R;
import com.example.abc_test.business.domain.model.DataItem;
import com.example.abc_test.framework.base.view.StarView;
import com.example.abc_test.framework.presentation.common.clicklisteners.ListFragmentClickListener;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView description;
    StarView addToFavorites;
    ListFragmentClickListener clickListener;
    DataItem dataItem;

    public boolean isSwipeable = false;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(view -> {
            clickListener.onClickGoToDetails(view, dataItem);
        });
        title = itemView.findViewById(R.id.source_name);
        description = itemView.findViewById(R.id.description);
        addToFavorites = itemView.findViewById(R.id.addToFavorites);
        addToFavorites.setOnClickListener(view -> {
            clickListener.onClickFavorites(view, dataItem);
        });
    }

    public void onBind(DataItem s, ListFragmentClickListener clickListener) {
        this.dataItem = s;
        isSwipeable = dataItem.getIsFavorite();
        this.clickListener = clickListener;
        title.setText(s.getId());
        description.setText(s.getName());
        addToFavorites.setFill(s.getIsFavorite());
    }
}
