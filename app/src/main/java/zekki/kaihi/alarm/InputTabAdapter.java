package zekki.kaihi.alarm;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class InputTabAdapter extends FragmentPagerAdapter {
    @StringRes
    private static final int[] TAB_TITLES = new int[] {
            R.string.InputTabLeft,
            R.string.InputTabCenter,
            R.string.InputTabRight
    };
    private final Context mContext;

    public InputTabAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }
    @Override
    public Fragment getItem(int position) {
        // 指定されたページのフラグメントをインスタンス化するためにgetItemが呼び出し
        //return PlaceholderFragment.newInstance(position + 1);

        Fragment fragment = null;
        if (position == 0) fragment = new InputAlarmInfoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}
