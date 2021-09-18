package zekki.kaihi.alarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

public class InputActivity extends AppCompatActivity {
    private int reqCode = -1;
    private static int MENU_DELETE_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input2);

        EditText alarm_name = findViewById(R.id.alarm_name_edit);
        TimePicker tp = findViewById(R.id.time_picker);

        DatabaseHelper helper = DatabaseHelper.getInstance(InputActivity.this);

        Intent intent = getIntent();
        reqCode = intent.getIntExtra(getString(R.string.request_code),-1);
        int alarmID = -1;
        if(reqCode == MainActivity.EDIT_REQ_CODE){
            // 編集前のデータを取得
            alarmID = intent.getIntExtra(getString(R.string.alarm_id),-1);
            AlarmInfo item = Util.getAlarmsByID(alarmID, helper);
            alarm_name.setText(item.getAlarmName());

            tp.setHour(Integer.parseInt(item.getHour()));
            tp.setMinute(Integer.parseInt(item.getMin()));
        }
        final int alarmIDForMenu = alarmID;

        /* -- button action --*/
        // x button
        ImageView back = findViewById(R.id.x_button_input);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // save button
        TextView save = findViewById(R.id.save_button_input);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = tp.getHour(), minute = tp.getMinute();

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                // about alarm setting
                String alarmName = alarm_name.getText().toString();
                String alarmTime = String.format("%02d", hour) + ":"+ String.format("%02d", minute);
                int requestCode = -1;
                if ( reqCode == MainActivity.NEW_REQ_CODE ) {
                    // new data regist
                    try(SQLiteDatabase db = helper.getWritableDatabase()){
                        ContentValues cv = new ContentValues();
                        cv.put("name",alarmName);
                        cv.put("alarttime", alarmTime);
                        requestCode = (int) db.insert("alarms", null, cv);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                } else {
                    // existing data update
                    requestCode = alarmIDForMenu;
                    try(SQLiteDatabase db = helper.getWritableDatabase()){
                        ContentValues cv = new ContentValues();
                        cv.put("name",alarmName);
                        cv.put("alarttime", alarmTime);
                        String[] params = {String.valueOf(requestCode)};
                        db.update("alarms",cv,"alarmid = ?",params);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                AlarmInfo info = new AlarmInfo();
                info.setAlarmID(requestCode);
                info.setAlarmName(alarmName);
                info.setTime(alarmTime);
                Util.setAlarm(InputActivity.this, info);

                Toast.makeText(InputActivity.this, R.string.alarm_save_done, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // Tab layout
        InputTabAdapter InputTabAdapter = new InputTabAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.input_vp);
        viewPager.setAdapter(InputTabAdapter);

        TabLayout tab = findViewById(R.id.input_tab);
        tab.setupWithViewPager(viewPager);
    }
}