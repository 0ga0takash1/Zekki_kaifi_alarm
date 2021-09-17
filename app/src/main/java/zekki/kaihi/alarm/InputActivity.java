package zekki.kaihi.alarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

public class InputActivity extends AppCompatActivity {
    private int reqCode = -1;
    private static int MENU_DELETE_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input2);

        ImageView back = findViewById(R.id.back_button);
        TextView save = findViewById(R.id.save_button);
        EditText alarm_name = findViewById(R.id.alarm_name_edit);
        TimePicker tp = findViewById(R.id.time_picker);

        DatabaseHelper helper = DatabaseHelper.getInstance(InputActivity.this);

        // x button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // save button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                reqCode = intent.getIntExtra(getString(R.string.request_code),-1);
                int alarmID = -1;
                int hour = tp.getHour(), minute = tp.getMinute();

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                // アラーム名の設定
                String alarmName = alarm_name.getText().toString();
                String alarmTime = String.format("%02d", hour) + ":"+ String.format("%02d", minute);
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