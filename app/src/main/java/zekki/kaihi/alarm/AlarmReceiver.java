package zekki.kaihi.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = AlarmReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String requestCode = intent.getData().toString();
        DatabaseHelper helper = DatabaseHelper.getInstance(context);
        AlarmInfo item = Util.getAlarmsByID(Integer.parseInt(requestCode), helper);

        Util.setAlarm(context, item);

        Intent startActivityIntent = new Intent(context, WhenAlarmRingActivity.class);
        startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startActivityIntent);
    }
}
