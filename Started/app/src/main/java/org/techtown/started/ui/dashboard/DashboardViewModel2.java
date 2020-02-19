package org.techtown.started.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel2 extends ViewModel {

    private MutableLiveData<String> mText;

    public DashboardViewModel2() {
        mText = new MutableLiveData<>();
        mText.setValue( "고색 솔대 공원 : 2시간");
    }

    public LiveData<String> getText() {
        return mText;
    }
}