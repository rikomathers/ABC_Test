package com.example.abc_test.framework.presentation.main;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.abc_test.R;
import com.example.abc_test.business.data.SharedPreferences;
import com.example.abc_test.business.domain.model.DataItem;
import com.example.abc_test.framework.presentation.strategy_list.ListFragmentDirections;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences.Companion.getInstance(getApplicationContext());
        setContentView(R.layout.activity_main);
        setupNavGraph();
    }


    private void setupNavGraph() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentView);
        navController = navHostFragment.getNavController();

    }

    public void goToDetails(DataItem dataItem) {
        Log.d("MainActivity", "id " + navController.getCurrentDestination().getId());
        Log.d("MainActivity", "getNavigatorName " + navController.getCurrentDestination().getNavigatorName());
        Log.d("MainActivity", "getLabel " + navController.getCurrentDestination().getLabel());
        ListFragmentDirections.ActionListToDetails action = ListFragmentDirections.actionListToDetails(dataItem);
        if (navController.getCurrentDestination().getId() == R.id.listFragment) {
            navController.navigate(action);
        }
    }

    private void goToFavorites() {
        if (navController.getCurrentDestination().getId() != R.id.favoriteListFragment) {
            navController.navigate(R.id.favoriteListFragment);
        }
    }

    public void goBackToList() {
        if (navController.getCurrentDestination().getId() == R.id.detailsFragment) {
            navController.popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorites: {
                goToFavorites();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
