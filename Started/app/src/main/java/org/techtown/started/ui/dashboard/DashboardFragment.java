package org.techtown.started.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.techtown.started.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private DashboardViewModel2 dashboardViewModel2;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.fragment_dashboard, container, false );

        dashboardViewModel =
                ViewModelProviders.of( this ).get( DashboardViewModel.class );

        //View root = inflater.inflate( R.layout.fragment_dashboard, container, false );
        final TextView textView = root.findViewById( R.id.text_dashboard );
        dashboardViewModel.getText().observe( this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText( s );

            }
        } );

        dashboardViewModel2 =
                ViewModelProviders.of( this ).get( DashboardViewModel2.class );
        final TextView textView2 = root.findViewById( R.id.text_dashboard2 );
        dashboardViewModel2.getText().observe( this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView2.setText( s );

            }
        } );


        return root;
    }
}