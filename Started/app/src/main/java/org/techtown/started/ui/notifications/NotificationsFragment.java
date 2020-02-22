package org.techtown.started.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.techtown.started.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NotificationsFragment extends Fragment {

    //private NotificationsViewModel notificationsViewModel;
    ListView listview ;
    ListViewAdapter adapter;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    ArrayList<ListViewItem> itemList = new ArrayList<ListViewItem>() ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate( R.layout.fragment_notifications, container, false );


        Button Attend = (Button) root.findViewById(R.id.Attend) ;
        Attend.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.push().setValue("Hello, World!");

            }
        });


        /*
        notificationsViewModel =

                ViewModelProviders.of( this ).get( NotificationsViewModel.class );

        final TextView textView = root.findViewById( R.id.text_notifications );
        notificationsViewModel.getText().observe( this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText( s );
            }
        } );
        */

        adapter = new ListViewAdapter(itemList) ;

        adapter.addItem(4, "백재윤") ;
        adapter.addItem(6, "권도형") ;
        adapter.addItem(7, "김용환") ;
        adapter.addItem(9, "손영운") ;
        adapter.addItem(10, "형민기") ;
        adapter.addItem(10, "황기호") ;
        adapter.addItem(11, "강성인") ;
        adapter.addItem(13, "김민배") ;
        adapter.addItem(14, "이우진") ;
        adapter.addItem(15, "김석준") ;
        adapter.addItem(16, "강민석") ;
        adapter.addItem(18, "김영훈") ;
        adapter.addItem(19, "권명진") ;
        adapter.addItem(20, "이창") ;
        adapter.addItem(21, "원철희") ;
        adapter.addItem(22, "정명훈") ;
        adapter.addItem(23, "이정호") ;
        adapter.addItem(24, "정현준") ;
        adapter.addItem(27, "전대성") ;
        adapter.addItem(28, "정준혁") ;
        adapter.addItem(29, "박우람") ;
        adapter.addItem(45, "곽태은") ;
        adapter.addItem(47, "한승헌") ;
        adapter.addItem(68, "문홍성") ;
        adapter.addItem(77, "박윤하") ;
        adapter.addItem(88, "이경준") ;
        adapter.addItem(91, "윤영찬") ;
        adapter.addItem(96, "조영호") ;
        adapter.addItem(99, "박범진") ;



        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) root.findViewById(R.id.listview1);
        listview.setAdapter(adapter);


        Button buttonNoAsc = (Button) root.findViewById(R.id.buttonNoAsc) ;
        buttonNoAsc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<ListViewItem> noAsc = new Comparator<ListViewItem>() {
                    @Override
                    public int compare(ListViewItem item1, ListViewItem item2) {
                        int ret ;

                        if (item1.getBackNumber() < item2.getBackNumber())
                            ret = -1 ;
                        else if (item1.getBackNumber() == item2.getBackNumber())
                            ret = 0 ;
                        else
                            ret = 1 ;

                        return ret ;

                        // 위의 코드를 간단히 만드는 방법.
                        // return (item1.getNo() - item2.getNo()) ;
                    }
                } ;

                Collections.sort(itemList, noAsc) ;
                adapter.notifyDataSetChanged() ;
            }
        });

        Button buttonNoDesc = (Button) root.findViewById(R.id.buttonNoDesc) ;
        buttonNoDesc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<ListViewItem> noDesc = new Comparator<ListViewItem>() {
                    @Override
                    public int compare(ListViewItem item1, ListViewItem item2) {
                        int ret ;

                        if (item1.getBackNumber() < item2.getBackNumber())
                            ret = 1 ;
                        else if (item1.getBackNumber() == item2.getBackNumber())
                            ret = 0 ;
                        else
                            ret = -1 ;

                        return ret ;

                        // 위의 코드를 간단히 만드는 방법.
                        // return (item1.getNo() - item2.getNo()) ;
                    }
                } ;

                Collections.sort(itemList, noDesc) ;
                adapter.notifyDataSetChanged() ;
            }
        });

        Button buttonTextAsc = (Button) root.findViewById(R.id.buttonTextAsc) ;
        buttonTextAsc.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comparator<ListViewItem> textAsc = new Comparator<ListViewItem>() {
                    @Override
                    public int compare(ListViewItem item1, ListViewItem item2) {
                        int ret ;

                        if (item1.getName().compareTo(item2.getName()) > 0)
                            ret = 1 ;
                        else if (item1.getName().compareTo(item2.getName()) < 0)
                            ret = -1 ;
                        else
                            ret = 0 ;

                        return ret ;

                        // 위의 코드를 간단히 만드는 방법.
                        // return (item1.getNo() - item2.getNo()) ;
                    }
                } ;

                Collections.sort(itemList, textAsc) ;
                adapter.notifyDataSetChanged() ;
            }
        });

        return root;
    }


}

