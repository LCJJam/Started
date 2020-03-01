package org.techtown.started.ui.home;

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

public class HomeFragment extends Fragment { // 공지사항 및 경기일정

    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private NoticeAdapter notice_adapter;
    private ScheduleAdapter schedule_adapter;
    private Context context;
    private ArrayList<Notice> Notice_items;
    private ArrayList<Schedule> Schedule_items;
    private int cnt = 0;
    private static final String TAG = "Activity";

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Board");

    /*
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        this.context = context;

        if(context instanceof TabLayout.OnTabSelectedListener) {
            //listener = (onTabItemSelectedListener) context;

        }
    }

    @Override
    public void onDetach(){
        super.onDetach();

        if(context != null) {
            context = null;
            //listener = null;
        }
    }
    */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home,container,false);
        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView){
        recyclerView1 = rootView.findViewById(R.id.home_Notice);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(layoutManager);
        Notice_items = new ArrayList<Notice>();
        // 어뎁터에 파이어베이스에 있는 데이터 등록
        ValueEventListener mValueEventListener;
        mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Notice_items.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    Notice info_eac = postSnapshot.getValue( Notice.class );
                    Notice_items.add( info_eac );
                }
                notice_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        //사용
        myRef.addValueEventListener(mValueEventListener);
        notice_adapter = new NoticeAdapter(Notice_items);
        recyclerView1.setAdapter(notice_adapter);

        /*
        recyclerView2 = rootView.findViewById(R.id.home_Schedule);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(layoutManager1);
        Schedule_items = new ArrayList<>();
        schedule_adapter = new ScheduleAdapter(Schedule_items);
        recyclerView2.setAdapter(schedule_adapter);
        */

        // 팀원 만들기 버튼 추가
        Button NoticeAddButton = rootView.findViewById(R.id.Notice_add);
        NoticeAddButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Notice NoticeData = new Notice(1,"공지사항","첫번째 공지","2020-02-27");
                myRef.child( "Boarding" ).setValue( NoticeData );

                //Schedule ScheduleData = new Schedule("고색 솔대 공원","2020-03-01");
                //Schedule_items.add(ScheduleData);
                //schedule_adapter.notifyDataSetChanged();

            }
        });
    }
}