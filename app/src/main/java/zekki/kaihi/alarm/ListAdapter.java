package zekki.kaihi.alarm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<AlarmInfoHolder> {
    private ArrayList<AlarmInfo> data;

    public ListAdapter(ArrayList<AlarmInfo> data){
        this.data = data;
    }

    @NonNull
    @Override
    public AlarmInfoHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_list_layout,parent,false);


        final AlarmInfoHolder holder = new AlarmInfoHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = parent.getContext();
                int position = holder.getAdapterPosition();
//                Intent i = new Intent(context, InputActivity.class);
//                i.putExtra(context.getString(R.string.request_code),MainActivity.EDIT_REQ_CODE);
//                i.putExtra(context.getString(R.string.alarm_id),data.get(position).getAlarmID());
//                ((Activity) context).startActivityForResult(i,MainActivity.EDIT_REQ_CODE);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmInfoHolder holder, int position) {
        holder.alarmName.setText(this.data.get(position).getAlarmName());
        holder.time.setText(this.data.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
