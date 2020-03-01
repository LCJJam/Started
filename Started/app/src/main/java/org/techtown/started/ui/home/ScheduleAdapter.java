package org.techtown.started.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.started.R;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private ArrayList<Schedule> schedule_items = new ArrayList<Schedule>();

    public ScheduleAdapter(ArrayList<Schedule> schedule_items) {
        this.schedule_items = schedule_items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.team_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder viewHolder, int position) {
        viewHolder.Spot.setText(schedule_items.get(position).getSpot());
        viewHolder.Match_Date.setText(schedule_items.get(position).getMatch_date());
    }

    @Override
    public int getItemCount() {
        return (null != schedule_items ? schedule_items.size() : 0);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView Spot;
        TextView Match_Date;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            Spot = itemView.findViewById( R.id.Schedule_spot );
            Match_Date = itemView.findViewById( R.id.Schedule_date );
        }
    }
}
