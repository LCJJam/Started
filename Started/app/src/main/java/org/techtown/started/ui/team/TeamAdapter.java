package org.techtown.started.ui.team;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.techtown.started.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> implements OnTeamItemClickListener {

    ArrayList<Team> items = new ArrayList<Team>();

    OnTeamItemClickListener listener;

    int layoutType = 0;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate( R.layout.team_item,viewGroup,false);
        return new ViewHolder( itemView,this,layoutType );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Team item = items.get(position);
        viewHolder.setItem(item);
        viewHolder.setLayoutType(layoutType);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public  void addIte(Team item){
        items.add( item );
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
        LinearLayout layout1;
        LinearLayout layout2;

        ImageView profileImage;

        TextView Name;
        TextView BackNumber;
        TextView PhoneNumber;

        public ViewHolder(@NonNull android.view.View itemView,final OnTeamItemClickListener listener,int layoutType) {
            super( itemView );

            layout1 = itemView.findViewById( R.id.layout1 );
            layout2 = itemView.findViewById( R.id.layout2 );

            profileImage = itemView.findViewById( R.id.team_ProfileImage );
            Name = itemView.findViewById( R.id.team_Name );
            BackNumber = itemView.findViewById( R.id.team_BackNumber );
            PhoneNumber = itemView.findViewById( R.id.team_PhoneNumber );

            itemView.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    int position = getAdapterPosition();

                    if(listener != null){
                        listener.onItemClick(ViewHolder.this,view,position);
                    }
                }
            } );
            setLayoutType(layoutType);
        }

    }
}
