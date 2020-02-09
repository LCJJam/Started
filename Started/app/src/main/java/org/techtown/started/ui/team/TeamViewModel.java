package org.techtown.started.ui.team;

import android.media.Image;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TeamViewModel extends ViewModel {
    private MutableLiveData<String> mText;


    public TeamViewModel() {
        mText = new MutableLiveData<>();

        mText.setValue( "This is team fragment" );
    }

    public LiveData<String> getText() {
        return mText;
    }
}
