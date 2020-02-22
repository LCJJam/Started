package org.techtown.started.ui.team;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.techtown.started.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<User> arrayList;
    private Context context;


    public CustomAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_team,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfileImage())
                .into(holder.team_ProfileImage);

        holder.team_PhoneNumber.setText(arrayList.get(position).getPhoneNumber());
        holder.team_Name.setText(arrayList.get(position).getName());
        holder.team_BackNumber.setText(String.valueOf(arrayList.get(position).getBackNumber()));
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView team_ProfileImage;
        TextView team_Name;
        TextView team_BackNumber;
        TextView team_PhoneNumber;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.team_ProfileImage = itemView.findViewById(R.id.team_ProfileImage);
            this.team_Name = itemView.findViewById(R.id.team_Name);
            this.team_BackNumber = itemView.findViewById(R.id.team_BackNumber);
            this.team_PhoneNumber = itemView.findViewById(R.id.team_PhoneNumber);
        }
    }
}
