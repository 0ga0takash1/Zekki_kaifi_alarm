package zekki.kaihi.alarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
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

        // Tab layout
        InputTabAdapter InputTabAdapter = new InputTabAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.input_vp);
        viewPager.setAdapter(InputTabAdapter);

        TabLayout tab = findViewById(R.id.input_tab);
        tab.setupWithViewPager(viewPager);
    }
}