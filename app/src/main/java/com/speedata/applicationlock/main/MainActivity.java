package com.speedata.applicationlock.main;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.base.BaseActivity;
import com.speedata.applicationlock.bean.AppInfo;
import com.speedata.applicationlock.bean.InstallBroadcast;
import com.speedata.applicationlock.common.ToolsCommon;
import com.speedata.applicationlock.common.utils.ToolToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import xyz.reginer.baseadapter.CommonRvAdapter;

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
 *
 * @author Reginer in  2016/12/14 14:03.
 *         Description:显示全部应用
 */
public class MainActivity extends BaseActivity implements CommonRvAdapter.OnItemClickListener {

    private List<AppInfo> mAllShowList;
    private AppsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView();
        loadApps();
    }

    private void loadApps() {
        Intent mIntent = new Intent(Intent.ACTION_MAIN, null);
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> mAllAppList = getPackageManager().queryIntentActivities(mIntent, 0);
        for (int i = 0; i < mAllAppList.size(); i++) {
            mAllShowList.add(new AppInfo(mAllAppList.get(i).activityInfo.name,
                    mAllAppList.get(i).activityInfo.packageName,
                    mAllAppList.get(i).activityInfo.loadLabel(getPackageManager()).toString(), false));
        }
        mAdapter.notifyDataSetChanged();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView mRvContent = (RecyclerView) findViewById(R.id.rv_content);
        mRvContent.setLayoutManager(new GridLayoutManager(this, 4));
        mAllShowList = new ArrayList<>();
        mAdapter = new AppsAdapter(this, R.layout.view_all_app_item_layout, mAllShowList);
        mRvContent.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
        ToolsCommon.startApp(mAllShowList, position, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_hide:
                ToolToast.showPwdDialog(this, R.layout.view_input_pwd_dialog_layout, true);
                return true;
            case R.id.action_show:
                ToolToast.showPwdDialog(this, R.layout.view_input_pwd_dialog_layout, false);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getShowAppList(List<AppInfo> appShowList) {
        mAllShowList.clear();
        mAllShowList.addAll(appShowList);
        mAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getInstallApp(InstallBroadcast installBroadcast) {
        if (installBroadcast.isChanged()) {
            mAllShowList.clear();
            loadApps();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode != KeyEvent.KEYCODE_BACK && super.onKeyDown(keyCode, event);
    }
}
