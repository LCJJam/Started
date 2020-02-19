package org.techtown.started.ui.team;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
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
    private TeamViewModel teamViewModel;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ArrayList<User> arrayList;
    Context ct;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ct = container.getContext();

        recyclerView.findViewById(R.id.team_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(ct);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("User");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    arrayList.add(user); // 담은 데이터를 배열리스트에 넣고 뷰에 넣을 준비
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DashboardViewModel",String.valueOf(databaseError.toException()));
            }
        });


        adapter = new CustomAdapter(arrayList,ct);
        recyclerView.setAdapter(adapter);



        teamViewModel =
                ViewModelProviders.of( this ).get( TeamViewModel.class );
        View root = inflater.inflate( R.layout.fragment_team, container, false );
        //final TextView textView = root.findViewById( R.id.text_team );
        //teamViewModel.getText().observe( this, new Observer<String>() {
          //  @Override
            //public void onChanged(@Nullable String s) { textView.setText( s ); }
        //} );
        return root;


    }
}
