package org.techtown.started.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.started.R;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {

    private ArrayList<Notice> notice_items = new ArrayList<Notice>();

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
        viewHolder.Number.setText(Integer.toString(notice_items.get(position).getNumber()));
        viewHolder.Date.setText(notice_items.get(position).getDate());
        viewHolder.itemView.setTag(position);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String curName = viewHolder.Title.getText().toString();
                Toast.makeText(v.getContext(),curName,Toast.LENGTH_SHORT).show();
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
    public void remove(int position){
        try {
            notice_items.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (null != notice_items ? notice_items.size() : 0);
    }
    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView Title;
        TextView Number;
        TextView Date;


        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            Title = itemView.findViewById( R.id.Write_Title );
            Number = itemView.findViewById( R.id.Write_Number );
            Date = itemView.findViewById( R.id.Write_Date );
        }
    }
}
