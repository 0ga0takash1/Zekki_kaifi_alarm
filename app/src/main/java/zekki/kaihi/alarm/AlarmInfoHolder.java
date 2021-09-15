package zekki.kaihi.alarm;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

public class AlarmInfoHolder extends RecyclerView.ViewHolder {
    TextView alarmName;
    TextView time;
    TextView timing;
    TextView subAlarm;
    SwitchCompat alarmSwitch;

    AlarmInfoHolder(View itemView){
        super(itemView);
        this.alarmName = itemView.findViewById(R.id.alarm_name);
        this.time = itemView.findViewById(R.id.alarm_time);
        this.timing = itemView.findViewById(R.id.alarm_timing);
        this.subAlarm = itemView.findViewById(R.id.sub_alarm);
        this.alarmSwitch = itemView.findViewById(R.id.alarm_switch);
    }
}
