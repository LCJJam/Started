package org.techtown.started.ui.team;

import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.techtown.started.R;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> implements OnTeamItemClickListener {

    private ArrayList<Team> items = new ArrayList<Team>();
    public TeamAdapter(ArrayList<Team> items) {
        this.items = items;
    }
    private SparseBooleanArray mSelectedItems = new SparseBooleanArray(0);

    OnTeamItemClickListener listener;

    int layoutType = 0;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        //View itemView = inflater.inflate( R.layout.team_item,viewGroup,false);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.team_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TeamAdapter.ViewHolder viewHolder, final int position) {
        Glide.with(viewHolder.profileImage)
                .load(items.get(position).getProfileImage())
                .into(viewHolder.profileImage);
        viewHolder.Name.setText(items.get(position).getName());
        viewHolder.PhoneNumber.setText(items.get(position).getPhoneNumber());
        viewHolder.BackNumber.setText(items.get(position).getBackNumber());
        viewHolder.itemView.setTag(position);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String curName = viewHolder.Name.getText().toString();
                Toast.makeText(v.getContext(),curName,Toast.LENGTH_SHORT).show();

                if ( mSelectedItems.get(position, false) ){
                    mSelectedItems.put(position, false);
                    v.setBackgroundColor(Color.WHITE);
                } else {
                    mSelectedItems.put(position, true);
                    v.setBackgroundColor(Color.BLUE);
                }
            }

        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                remove(viewHolder.getAdapterPosition());
                return true;
            }
        });
        //viewHolder.setLayoutType(layoutType);
    }

    @Override
    public int getItemCount() {
        return (null != items ? items.size() : 0);
    }

    public void remove(int position){
        try {
            items.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }
    public void addItem(Team item){
        items.add( item );
        notifyItemInserted( items.size()-1 );
    }
    public void setItems(ArrayList<Team> items){
        this.items = items;
    }
    public Team getItem(int position){
        return items.get(position);
    }
    public void setOnItemClickListener(OnTeamItemClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onItemClick(ViewHolder holder,android.view.View view,int position){
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profileImage;

        TextView Name;
        TextView BackNumber;
        TextView PhoneNumber;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            profileImage = itemView.findViewById( R.id.team_ProfileImage );
            Name = itemView.findViewById( R.id.team_Name );
            BackNumber = itemView.findViewById( R.id.team_BackNumber );
            PhoneNumber = itemView.findViewById( R.id.team_PhoneNumber );
        }
    }
    /*
    public void setItem(Team item){
        String name = item.getName();
        String phoneNumber = item.getPhoneNumber();
        String backNumber = item.getBackNumber();
        int profileImage = item.getProfileImage();
    }
    */
}
