package org.techtown.started.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

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
    private DatabaseReference myBoard = database.getReference("Board");
    private DatabaseReference mySchedule = database.getReference("Schedule");
    private ValueEventListener ScheduleValueEventListener;

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
        ValueEventListener nValueEventListener;
        nValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Notice_items.clear();
                for (DataSnapshot noticeSnapshot : dataSnapshot.getChildren()) {
                    String key = noticeSnapshot.getKey();
                    Notice info_eac = noticeSnapshot.getValue( Notice.class );
                    Notice_items.add( info_eac );
                }
                Comparator<Notice> Notice_sort = new Comparator<Notice>() {
                    @Override
                    public int compare(Notice item1, Notice item2) {
                        return (item2.getDate().compareTo(item1.getDate())) ;
                    }
                } ;

                Collections.sort(Notice_items, Notice_sort) ;
                notice_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        //사용
        myBoard.addValueEventListener(nValueEventListener);
        notice_adapter = new NoticeAdapter(Notice_items);
        recyclerView1.setAdapter(notice_adapter);
        // 팀원 만들기 버튼 추가

        Button NoticeAddButton = rootView.findViewById(R.id.Notice_add);
        NoticeAddButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                View view = LayoutInflater.from( getContext())
                        .inflate(R.layout.notice_editbox, null, false);
                builder.setView(view);



                final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                final EditText editTitle = (EditText) view.findViewById(R.id.EditText_title);
                final EditText editContents = (EditText) view.findViewById(R.id.EditText_Contents);

                ButtonSubmit.setText("삽입");

                final AlertDialog dialog = builder.create();


                // 3. 다이얼로그에 있는 삽입 버튼을 클릭하면

                ButtonSubmit.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        // 4. 사용자가 입력한 내용을 가져와서
                        String Title = editTitle.getText().toString();
                        String Contents = editContents.getText().toString();
                        String Date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                        String Date_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        // 5. ArrayList에 추가하고

                        Notice dict = new Notice(Title,Contents,Date_time,Notice_items.size());
                        Notice_items.add(0, dict); //첫번째 줄에 삽입됨
                        //mArrayList.add(dict); //마지막 줄에 삽입됨


                        // 6. 어댑터에서 RecyclerView에 반영하도록 합니다.
                        myBoard.child( "Boarding_"+Date_time ).setValue(dict);
                        notice_adapter.notifyItemInserted(0);
                        //mAdapter.notifyDataSetChanged();

                        dialog.dismiss();
                    }
                });
                dialog.show();

                //Schedule ScheduleData = new Schedule("고색 솔대 공원","2020-03-01");
                //Schedule_items.add(ScheduleData);
                //schedule_adapter.notifyDataSetChanged();
                cnt++;
            }
        });

        recyclerView2 = rootView.findViewById(R.id.home_Schedule);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(layoutManager1);
        Schedule_items = new ArrayList<>();
        schedule_adapter = new ScheduleAdapter(Schedule_items);
        recyclerView2.setAdapter(schedule_adapter);

        ScheduleValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Schedule_items.clear();
                for (DataSnapshot scheduleSnapshot : dataSnapshot.getChildren()) {
                    String key = scheduleSnapshot.getKey();
                    Schedule info_schedule = scheduleSnapshot.getValue( Schedule.class );
                    Schedule_items.add( info_schedule );
                }
                Comparator<Schedule> Schedule_sort = new Comparator<Schedule>() {
                    @Override
                    public int compare(Schedule item1, Schedule item2) {
                        return (item2.getMatch_date().compareTo(item1.getMatch_date())) ;
                    }
                } ;

                Collections.sort(Schedule_items, Schedule_sort );
                schedule_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        //사용
        mySchedule.addValueEventListener(ScheduleValueEventListener);
        schedule_adapter = new ScheduleAdapter(Schedule_items);
        recyclerView2.setAdapter(schedule_adapter);

        Button ScheduleAddButton = rootView.findViewById(R.id.Schedule_add);
        ScheduleAddButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());

                View view = LayoutInflater.from( getContext())
                        .inflate(R.layout.schedule_addbox, null, false);
                builder1.setView(view);

                final Button ButtonScheduleSubmit = (Button) view.findViewById(R.id.button_schedule_submit);
                final EditText Match_Date = (EditText) view.findViewById(R.id.Match_Date);
                final EditText Match_Spot = (EditText) view.findViewById(R.id.Match_Spot);

                ButtonScheduleSubmit.setText("추가");

                final AlertDialog dialog = builder1.create();

                // 3. 다이얼로그에 있는 삽입 버튼을 클릭하면

                ButtonScheduleSubmit.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        // 4. 사용자가 입력한 내용을 가져와서
                        String Match_date = Match_Date.getText().toString();
                        String Match_spot = Match_Spot.getText().toString();
                        // 5. ArrayList에 추가하고

                        Schedule Sche = new Schedule(Match_spot,Match_date);
                        Schedule_items.add(0, Sche); //첫번째 줄에 삽입됨
                        //mArrayList.add(dict); //마지막 줄에 삽입됨


                        // 6. 어댑터에서 RecyclerView에 반영하도록 합니다.
                        mySchedule.child( "Schedule_"+Match_date ).setValue(Sche);
                        schedule_adapter.notifyItemInserted(0);
                        //mAdapter.notifyDataSetChanged();

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        } );

    }
}