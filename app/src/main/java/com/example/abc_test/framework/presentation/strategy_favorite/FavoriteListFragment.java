package com.example.abc_test.framework.presentation.strategy_favorite;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abc_test.business.domain.model.DataItem;
import com.example.abc_test.framework.presentation.common.clicklisteners.ListFragmentClickListener;
import com.example.abc_test.framework.presentation.main.MainActivity;
import com.example.abc_test.framework.presentation.common.adapter.MyAdapter;
import com.example.abc_test.framework.presentation.common.SwipeToDeleteCallback;
import com.example.abc_test.databinding.FragmentListBinding;

import org.jetbrains.annotations.Nullable;

public class FavoriteListFragment extends Fragment {

    private FragmentListBinding fragmentListBinding;

    private MyAdapter myAdapter;
    private FavoriteListViewModel viewModel = new FavoriteListViewModel();

    private ListFragmentClickListener addToFavoritesClickListener = new ListFragmentClickListener() {
        @Override
        public void onClickFavorites(View view, DataItem dataItem) {
//            viewModel.addToFavorites(dataItem);
        }

        @Override
        public void onClickGoToDetails(View view, DataItem dataItem) {
            ((MainActivity) getActivity()).goToDetails(dataItem);
        }
    };

    private void setSwipe(FragmentListBinding binding) {

        SwipeToDeleteCallback swipeHandler = new SwipeToDeleteCallback(binding.getRoot().getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                try {
                    viewModel.removeFromFavorites(viewModel.getObjectList().getValue().get(viewHolder.getAdapterPosition()));
                } catch (Exception e) {
                    Log.w("FavListFragment", e);
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeHandler);
        itemTouchHelper.attachToRecyclerView(fragmentListBinding.recyclerView);
    }

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
        setSwipe(fragmentListBinding);
        viewModel.getObjectList().observe(getViewLifecycleOwner(), dataItems -> {
            myAdapter.updateItems(dataItems);
        });

    }

}
