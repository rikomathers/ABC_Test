package com.example.abc_test.framework.presentation.strategy_list;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.abc_test.business.data.DataFactory;
import com.example.abc_test.business.data.SharedPreferences;
import com.example.abc_test.business.domain.model.DataItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListViewModel extends ViewModel {

    private MutableLiveData<List<DataItem>> objectList = new MutableLiveData<>();
    private SharedPreferences sharedPreferences = SharedPreferences.Companion.getInstance(null);

    ListViewModel() {
        Set<String> favIds = sharedPreferences.getFavoriteIds();

        ArrayList<DataItem> arrayList = new ArrayList(DataFactory.INSTANCE.getList());

        for (DataItem item: arrayList) {
            if (favIds.contains(item.getId())) {
                item.setFavorite(true);
            }
        }
        objectList.setValue(arrayList);
    }

    public MutableLiveData<List<DataItem>> getObjectList() {
        return objectList;
    }

    public void addToFavorites(DataItem data) {
        sharedPreferences.addFavorite(data.getId());
        List<DataItem> updatedList = new ArrayList<>(objectList.getValue());
        for (DataItem item: updatedList) {
            if (item.getId().equals(data.getId())) {
                item.setFavorite(true);
            }
        }
        objectList.setValue(updatedList);
    }

    public void removeFromFavorites(DataItem data) {
        sharedPreferences.removeFavorite(data.getId());
        List<DataItem> updatedList = new ArrayList<>(objectList.getValue());
        for (DataItem item: updatedList) {
            if (item.getId().equals(data.getId())) {
                item.setFavorite(false);
            }
        }
        objectList.setValue(updatedList);
    }
}
