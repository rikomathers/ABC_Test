package com.example.abc_test.framework.presentation.strategy_favorite;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.abc_test.business.data.DataFactory;
import com.example.abc_test.business.domain.model.DataItem;
import com.example.abc_test.business.data.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FavoriteListViewModel extends ViewModel {

    private MutableLiveData<List<DataItem>> objectList = new MutableLiveData<>();
    private SharedPreferences sharedPreferences = SharedPreferences.Companion.getInstance(null);

    FavoriteListViewModel() {

        Set<String> favIds = sharedPreferences.getFavoriteIds();

        ArrayList arrayList = new ArrayList();

        for (DataItem item: DataFactory.INSTANCE.getList()) {
            if (favIds.contains(item.getId())) {
                item.setFavorite(true);
                arrayList.add(item);
            }
        }

        objectList.setValue(arrayList);
    }

    public MutableLiveData<List<DataItem>> getObjectList() {
        return objectList;
    }

    public void removeFromFavorites(DataItem data) {
        sharedPreferences.removeFavorite(data.getId());
        List<DataItem> updatedList = new ArrayList<>();
        for (DataItem item: objectList.getValue()) {
            if (!item.getId().equals(data.getId())) {
                updatedList.add(item);
            }
        }
        objectList.setValue(updatedList);
    }
}
