package zekki.kaihi.alarm;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper helper = null;
    final static public int NEW_REQ_CODE = 1;
    final static public int EDIT_REQ_CODE = 2;
    RecyclerView rv = null;
    RecyclerView.Adapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // アラームのデータを取得
        ArrayList<AlarmInfo> data = this.loadAlarms();

        // RecyclerViewに設定
        this.setRV(data);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), InputActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(getApplication(), SettingsActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_delete) {
            Intent intent = new Intent(getApplication(), DeleteActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.action_license) {
            CreateLicence();
        }

        return super.onOptionsItemSelected(item);
    }


    private ArrayList<AlarmInfo> loadAlarms(){
        helper = DatabaseHelper.getInstance(this);

        ArrayList<AlarmInfo> data = new ArrayList<>();

        try(SQLiteDatabase db = helper.getReadableDatabase()) {

            String[] cols ={"alarmid","name","alarttime"};

            Cursor cs = db.query("alarms",cols,null,null,
                    null,null,"alarmid",null);
            boolean eol = cs.moveToFirst();
            while (eol){
                AlarmInfo item = new AlarmInfo();
                item.setAlarmID(cs.getInt(0));
                item.setAlarmName(cs.getString(1));
                item.setTime(cs.getString(2));
                data.add(item);
                eol = cs.moveToNext();
            }
        }
        return data;
    }

    private void setRV(ArrayList<AlarmInfo> data){
        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(manager);
        adapter = new ListAdapter(data);
        rv.setAdapter(adapter);
    }

    private void updateRV(ArrayList<AlarmInfo> data){
        adapter = new ListAdapter(data);
        rv.setAdapter(adapter);
        // rv.swapAdapter(adapter,false);
    }

    private void CreateLicence() {
        // PopupWindow内に表示するFrameLayoutを作成する。
        FrameLayout contentView = new FrameLayout(this);
        contentView.setBackgroundColor(Color.BLACK);

        // PopupWindowのインスタンスを作成する
        PopupWindow popupWindow = new PopupWindow();
        // PopupWindow内に表示するViewを設定する。
        popupWindow.setContentView(contentView);
        // PopupWindowの幅を800に設定
        popupWindow.setWidth(10);
        // PopupWindowの高さを800に設定
        popupWindow.setHeight(10);
        // PopupWindowを消すボタンを作成
        Button dismissPopupWindow = new Button(this);
        dismissPopupWindow.setText("PopupWindowを消す");
        dismissPopupWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // PopupWindow#dismissでPopupWindowを消す
                popupWindow.dismiss();
            }
        });

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.addView(dismissPopupWindow);

        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.addView(linearLayout);
        setContentView(frameLayout);
    }
}