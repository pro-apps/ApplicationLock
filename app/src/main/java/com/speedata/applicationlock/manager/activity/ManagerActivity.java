package com.speedata.applicationlock.manager.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.base.BaseActivity;
import com.speedata.applicationlock.manager.adapter.PageAdapter;
import com.speedata.applicationlock.manager.fragment.AutoStartFragment;
import com.speedata.applicationlock.manager.fragment.ShowFragment;
import com.speedata.applicationlock.manager.fragment.WhiteListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * ----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━神兽出没━━━━━━
 * 创   建:Reginer in  2017/1/4 16:58.
 * 联系方式:QQ:282921012
 * 功能描述:应用管理
 */
public class ManagerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        initView();
    }

    private void initView() {
        ShowFragment mShowFragment = new ShowFragment();
        AutoStartFragment mAutoStartFragment = new AutoStartFragment();
        WhiteListFragment mWhiteListFragment = new WhiteListFragment();
        List<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(mShowFragment);
        mFragmentList.add(mAutoStartFragment);
        mFragmentList.add(mWhiteListFragment);
        PageAdapter mPageAdapter = new PageAdapter(getSupportFragmentManager(),mFragmentList,this);


        ViewPager  mViewPager = (ViewPager) findViewById(R.id.vp_fragment);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tab_title);
        mViewPager.setAdapter(mPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        LinearLayout linearLayout = (LinearLayout) mTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.layout_divider_vertical));
    }
}
