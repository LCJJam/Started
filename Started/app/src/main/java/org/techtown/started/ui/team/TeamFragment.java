package org.techtown.started.ui.team;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import org.techtown.started.R;
import org.techtown.started.ThankDTo;

import java.util.ArrayList;

public class TeamFragment extends Fragment {

    //이메일 비밀번호 로그인 모듈 변수
    private FirebaseAuth mAuth;
    //현재 로그인 된 유저 정보를 담을 변수
    private FirebaseUser currentUser;
    //데이터 베이스 저장
    private static FirebaseDatabase database;

    //감사리스트 데이터 담을 리스트
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> contents = new ArrayList<String>();
    ArrayList<String> dates = new ArrayList<String>();
    ArrayList<String> thankPK = new ArrayList<String>();
    ArrayList<String> userKey = new ArrayList<String>();

    CustomAdapter2 ca; //커스텀 어뎁터


    public TeamFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Activity root = getActivity(); //이 클래스가 프레그먼트이기 때문에 액티비티 정보를 얻는다.
        Log.d("myTag1", "onCreateView-1");
        Log.d("myTag2", "onCreateView-2");


        View view = inflater.inflate(R.layout.fragment_team, container, false);

        ListView listView = (ListView) view.findViewById(R.id.listView);
        ca = new CustomAdapter2();
        listView.setAdapter(ca);

        return view;
    }
    class CustomAdapter2 extends BaseAdapter {
        @Override
        public int getCount() {
            return names.size();
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Activity root = getActivity(); //이 클래스가 프레그먼트이기 때문에 액티비티 정보를 얻는다.
            Toast.makeText(root,"getView" , Toast.LENGTH_SHORT).show();

            //커스텀뷰에 있는 객체들 가져오기
            convertView = getLayoutInflater().inflate(R.layout.custom_listview_layout,null);
            TextView tName = (TextView)convertView.findViewById(R.id.textView_name);
            TextView tDate = (TextView)convertView.findViewById(R.id.textView_date);
            TextView tContent = (TextView)convertView.findViewById(R.id.textView_content);

            tName.setText(names.get(position));
            tDate.setText(dates.get(position));
            tContent.setText(contents.get(position));
            return convertView;
        }
    }
    public void displayThanks(){
        database.getReference("thanks/")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        //개수 만큼 돈다

                        //Activity root = getActivity(); //이 클래스가 프레그먼트이기 때문에 액티비티 정보를 얻는다.
                        //Toast.makeText(root,"onChildAdded-2" , Toast.LENGTH_SHORT).show();
                        Log.d("myTag3", "onChildAdded-2");


                        ThankDTo thankDTo = dataSnapshot.getValue(ThankDTo.class);
                        //키값이랑 같이 넣어주기
                        thankDTo.setThankPK(dataSnapshot.getKey());

                        //Activity root = getActivity(); //이 클래스가 프레그먼트이기 때문에 액티비티 정보를 얻는다.
                        //Toast.makeText(root, thankDTo.getName()+"/"+ thankDTo.getComment(), Toast.LENGTH_SHORT).show();

                        names.add(thankDTo.getName());
                        dates.add(thankDTo.getCreatetime());
                        contents.add(thankDTo.getComment());
                        thankPK.add(thankDTo.getThankPK());
                        userKey.add(thankDTo.getUserkey());

                        int a = names.size();
                        String aa = String.valueOf(a);
                        Log.d("myTagrsult", aa);
                        //이거를 해줘야지 adapter getView가 호출이 된다!!!!중요!!!
                        ca.notifyDataSetChanged();
                        //Toast.makeText(root, aa , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }



        /*

        myTeamList = (RecyclerView) TeamView.findViewById(R.id.team_list);
        myTeamList.setLayoutManager(new LinearLayoutManager(getContext()));

        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();

        UserRef = FirebaseDatabase.getInstance().getReference().child("User_01");//.child(currentUserID);
        UserRef = FirebaseDatabase.getInstance().getReference();//.child("Users");


        return TeamView;
        listview = (ListView) root.findViewById(R.id.listview1);

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
        */

        /*
        teamViewModel =
               ViewModelProviders.of( this ).get( TeamViewModel.class );
        final TextView textView = root.findViewById( R.id.team_BackNumber );
        teamViewModel.getText().observe( this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { textView.setText( s ); }
        } );

        */






    ////////////
    /*
    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(UserRef, User.class)
                .build();

        FirebaseRecyclerAdapter<User, TeamViewHolder> adapter
                = new FirebaseRecyclerAdapter<User, TeamViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final TeamViewHolder teamViewHolder, int i, @NonNull User user) {
                //final String userIDs = getRef(i).getKey();

                UserRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("id")){
                            String profileName = dataSnapshot.child("UserName").getValue().toString();
                            String profilePhoneNumber = dataSnapshot.child("id").getValue().toString();
                            String profileBackNumber = dataSnapshot.child("pw").getValue().toString();

                            teamViewHolder.userName.setText(profileName);
                            teamViewHolder.PhoneNumber.setText(profilePhoneNumber);
                            teamViewHolder.BackNumber.setText(profileBackNumber);
                            //Picasso.get().load(profileImage).placeholder(R.drawable.ic_launcher_background).into(teamViewHolder.profileImage);
                        } else {
                            String profileName = dataSnapshot.child("UserName").getValue().toString();
                            String profilePhoneNumber = dataSnapshot.child("id").getValue().toString();
                            String profileBackNumber = dataSnapshot.child("pw").getValue().toString();

                            teamViewHolder.userName.setText(profileName);
                            teamViewHolder.PhoneNumber.setText(profilePhoneNumber);
                            teamViewHolder.BackNumber.setText(profileBackNumber);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @NonNull
            @Override
            public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_team, parent ,false);
                TeamViewHolder viewHolder = new TeamViewHolder(view);
                return viewHolder;
            }
        };

        myTeamList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder{

        TextView userName, BackNumber, PhoneNumber;
        //ImageView profileImage;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.team_Name);
            BackNumber = itemView.findViewById(R.id.team_BackNumber);
            PhoneNumber = itemView.findViewById(R.id.team_PhoneNumber);
            //profileImage = itemView.findViewById(R.id.team_ProfileImage);
        }
    }
    */
}
