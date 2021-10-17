package com.example.abc_test.framework.presentation.strategy_details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.abc_test.databinding.FragmentDetailsBinding;

import org.jetbrains.annotations.Nullable;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding fragmentDetailsBinding;

    private DetailsFragmentArgs args;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false);
        return fragmentDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        args = DetailsFragmentArgs.fromBundle(getArguments());
        fragmentDetailsBinding.sourceName.setText(args.getDataItem().getId());
        fragmentDetailsBinding.description.setText(args.getDataItem().getName());
        fragmentDetailsBinding.addToFavorites.setFill(args.getDataItem().getIsFavorite());
    }
}
