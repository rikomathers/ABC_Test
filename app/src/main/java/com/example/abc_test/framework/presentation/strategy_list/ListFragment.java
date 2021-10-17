package com.example.abc_test.framework.presentation.strategy_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.abc_test.framework.presentation.main.MainActivity;
import com.example.abc_test.business.domain.model.DataItem;
import com.example.abc_test.databinding.FragmentListBinding;
import com.example.abc_test.framework.presentation.common.adapter.MyAdapter;
import com.example.abc_test.framework.presentation.common.clicklisteners.ListFragmentClickListener;

import org.jetbrains.annotations.Nullable;

public class ListFragment extends Fragment {

    private FragmentListBinding fragmentListBinding;

    private MyAdapter myAdapter;
    private ListViewModel viewModel = new ListViewModel();

    private ListFragmentClickListener addToFavoritesClickListener = new ListFragmentClickListener() {
        @Override
        public void onClickFavorites(View view, DataItem dataItem) {
            if (dataItem.isFavorite()) {
                viewModel.removeFromFavorites(dataItem);
            } else {
                viewModel.addToFavorites(dataItem);
            }
        }

        @Override
        public void onClickGoToDetails(View view, DataItem dataItem) {
            ((MainActivity) getActivity()).goToDetails(dataItem);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentListBinding = FragmentListBinding.inflate(inflater, container, false);

        return fragmentListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myAdapter = new MyAdapter();
        myAdapter.setClickListener(addToFavoritesClickListener);
        fragmentListBinding.recyclerView.setAdapter(myAdapter);

        viewModel.getObjectList().observe(getViewLifecycleOwner(), dataItems -> {
            myAdapter.updateItems(dataItems);
        });

    }
}
