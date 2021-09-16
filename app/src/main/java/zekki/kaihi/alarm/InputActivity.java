package zekki.kaihi.alarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.QuickContactBadge;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

public class InputActivity extends AppCompatActivity {
    private static int MENU_DELETE_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input2);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_input);
//        toolbar.setNavigationIcon(R.drawable.ic_baseline_close_24);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent();
//                setResult(RESULT_CANCELED, i);
//                finish();
//            }
//        });

//        toolbar.inflateMenu(R.menu.menu_edit);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }

//        InputTabAdapter InputTabAdapter = new InputTabAdapter(this, getSupportFragmentManager());

//        ViewPager viewPager = findViewById(R.id.input_vp);
//        viewPager.setAdapter(InputTabAdapter);

//        TabLayout tabLayout = (TabLayout) findViewById(R.id.input_tab);
//        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();

        return super.onSupportNavigateUp();
    }
}