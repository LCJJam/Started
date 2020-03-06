package org.techtown.started.ui.home;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.techtown.started.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    private ArrayList<Notice> notice_items = new ArrayList<Notice>();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Board");


    public NoticeAdapter(ArrayList<Notice> notice_items) {
        this.notice_items = notice_items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notice_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NoticeAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.Title.setText(notice_items.get(position).getTitle());
        viewHolder.Date.setText(notice_items.get(position).getDate().substring(0,10));
        //viewHolder.Contents.setText(notice_items.get(position).getContents());
        viewHolder.Number.setText( ""+(notice_items.size()-position) );
        viewHolder.itemView.setTag(position);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                final View view = LayoutInflater.from( v.getContext())
                        .inflate(R.layout.notice_board, null, false);
                builder.setView(view);

                final TextView BoardTitle = (TextView) view.findViewById(R.id.TextView_title2);
                final TextView BoardContents = (TextView) view.findViewById(R.id.TextView_Contents);
                final Button ButtonEdit = (Button) view.findViewById(R.id.button_Notice_Edit);
                final Button ButtonDelete = (Button) view.findViewById(R.id.button_Notice_Delete);

                final AlertDialog dialog = builder.create();

                BoardTitle.setText(viewHolder.Title.getText().toString());
                BoardContents.setText(notice_items.get( position ).getContents());

                ButtonDelete.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myRef.child("Boarding_"+notice_items.get( position ).getDate()).removeValue();
                        Toast.makeText(v.getContext(),"삭제 완료",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                } );

                ButtonEdit.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                        View view = LayoutInflater.from( v.getContext())
                                .inflate(R.layout.notice_editbox, null, false);
                        builder.setView(view);

                        final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                        final EditText editTitle = (EditText) view.findViewById(R.id.EditText_title);
                        final EditText editContents = (EditText) view.findViewById(R.id.EditText_Contents);

                        ButtonSubmit.setText("변경");

                        final AlertDialog dialog = builder.create();
                        // 3. 다이얼로그에 있는 변경 버튼을 클릭하면
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {

                                myRef.child("Boarding_"+notice_items.get( position ).getDate()).removeValue();
                                // 4. 사용자가 입력한 내용을 가져와서
                                String Title = editTitle.getText().toString();
                                String Contents = editContents.getText().toString();
                                String Date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                                String Date_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                                // 5. ArrayList에 추가하고

                                Notice dict = new Notice(Title,Contents,Date_time,notice_items.size());
                                notice_items.add(0, dict); //첫번째 줄에 삽입됨
                                //mArrayList.add(dict); //마지막 줄에 삽입됨


                                // 6. 어댑터에서 RecyclerView에 반영하도록 합니다.
                                myRef.child( "Boarding_"+Date_time ).setValue(dict);
                                //mAdapter.notifyDataSetChanged();

                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }
                } );

                dialog.show();
                //String curName = viewHolder.Title.getText().toString();
                //Toast.makeText(v.getContext(),curName,Toast.LENGTH_SHORT).show();
            }

        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(viewHolder.getAdapterPosition());
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return (null != notice_items ? notice_items.size() : 0);
    }

    public void remove(int position){
        try {
            notice_items.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }
    public void addItem(Notice notice_item){
        notice_items.add( notice_item );
        notifyItemInserted( notice_items.size()-1 );
    }
    public void setItems(ArrayList<Notice> notice_items){
        this.notice_items = notice_items;
    }
    public Notice getItem(int position){
        return notice_items.get(position);
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView Title;
        TextView Date;
        //TextView Contents;
        TextView Number;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            Title = itemView.findViewById( R.id.Write_Title );
            Number = itemView.findViewById( R.id.Write_Number );
            Date = itemView.findViewById( R.id.Write_Date );
            //Contents = itemView.findViewById( R.id.Write_Contents );
        }
    }
}
