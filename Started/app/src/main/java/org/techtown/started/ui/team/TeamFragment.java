package org.techtown.started.ui.team;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import org.techtown.started.R;

public class TeamFragment extends Fragment {

    RecyclerView recyclerView;
    TeamAdapter adapter;
    Context context;
    OnTabItemSelectedListener listener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        this.context = context;

        if(context instanceof TabLayout.OnTabSelectedListener) {
            listener = (onTabItemSelectedListener) context;

        }
    }

    @Override
    public void onDetach(){
        super.onDetach();

        if(context != null) {
            context = null;
            listener = null;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_team,container,false);

        initUI(rootView);

        return rootView;
    }
    private void initUI(ViewGroup rootView){
        Button teamCreateButton = rootView.findViewById(R.id.teamCreateButton);
        teamCreateButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onTabSelected(1);
                }
            }
        });

        SwitchMultiButton switchButton = rootView.findViewById(R.id.switchButton);
        switchButton.setOnSwitchListener(new SwitchMultiButton.OnSwitchListener(){
            @Override
            public void onSwitch(int position, String tabText){
                Toast.makeText(getContext(),tabText,Toast.LENGTH_SHORT).show();
                adapter.switchLayout(position);
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView = rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TeamAdapter();

        adapter.addItem(new Team("백재윤","4","010-0000-0000","img"));
        adapter.addItem(new Team("백재윤","4","010-0000-0000","img"));
        adapter.addItem(new Team("백재윤","4","010-0000-0000","img"));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnTeamItemClickListener() {
            @Override
            public void onItemClick(TeamAdapter.ViewHolder holder, View view, int position) {
                Team item = adapter.getItem(position);
                Toast.makeText(getContext(),"아이템 선택됨"+ item.getContents(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
