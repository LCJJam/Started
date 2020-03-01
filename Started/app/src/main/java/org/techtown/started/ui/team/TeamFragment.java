package org.techtown.started.ui.team;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.techtown.started.R;

import java.util.ArrayList;

public class TeamFragment extends Fragment {

    private RecyclerView recyclerView;
    private TeamAdapter adapter;
    private Context context;
    private ArrayList<Team> items;
    //OnTabItemSelectedListener listener;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Team");
    int cnt = 0;
    private static final String TAG = "Activity";

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;

        if (context instanceof TabLayout.OnTabSelectedListener) {
            //listener = (onTabItemSelectedListener) context;

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (context != null) {
            context = null;
            //listener = null;
        }
    }
    */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_team, container, false);
        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        recyclerView = rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager(layoutManager);
        items = new ArrayList<Team>();

        // 어뎁터에 파이어베이스에 있는 데이터 등록
        ValueEventListener mValueEventListener;
        mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                items.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    Team info_each = postSnapshot.getValue( Team.class );
                    items.add( info_each );
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        //사용
        myRef.addValueEventListener(mValueEventListener);
        adapter = new TeamAdapter( items );
        recyclerView.setAdapter(adapter);
        //삭제
        //myRef.removeEventListener(mValueEventListener);

        // 팀원 만들기 버튼 추가
        Button teamCreateButton = rootView.findViewById(R.id.teamCreateButton);
        teamCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Team team = new Team("백재윤","4","010-1111-1111","https://firebasestorage.googleapis.com/v0/b/project-smb-8a7d3.appspot.com/o/WIN_20180923_23_58_05_Pro.jpg?alt=media&token=3d4f81ea-e704-4f0e-8d98-7ba0ad946ea5");
                myRef.child( "User_"+cnt ).setValue( team );
                /*
                myRef.child("User_" + cnt).child("Name").setValue("백재윤");
                myRef.child("User_" + cnt).child("BackNumber").setValue("4");
                myRef.child("User_" + cnt).child("PhoneNumber").setValue("010-1111-1111");
                myRef.child("User_" + cnt).child("ProfileImage").setValue("https://firebasestorage.googleapis.com/v0/b/project-smb-8a7d3.appspot.com/o/WIN_20180923_23_58_05_Pro.jpg?alt=media&token=3d4f81ea-e704-4f0e-8d98-7ba0ad946ea5");
                */
                cnt++;

            }
        });

        // 팀원 삭제 버튼 추가
        Button teamDeleteButton = rootView.findViewById(R.id.teamDeleteButton);
        teamDeleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myRef.removeValue();
                recyclerView.setAdapter(adapter);
            }
        });

    }
}
