package com.example.abc_test.framework.presentation.common.clicklisteners;

import android.view.View;

import com.example.abc_test.business.domain.model.DataItem;

public interface ListFragmentClickListener {
    public void onClickFavorites(View view, DataItem dataItem);
    public void onClickGoToDetails(View view, DataItem dataItem);
}