package com.speedata.applicationlock.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.base.BaseActivity;
import com.speedata.applicationlock.bean.AdminTag;
import com.speedata.applicationlock.bean.AppChanged;
import com.speedata.applicationlock.bean.AppInfo;
import com.speedata.applicationlock.common.DbCommon;
import com.speedata.applicationlock.common.ToolsCommon;
import com.speedata.applicationlock.common.utils.ToolToast;
import com.speedata.applicationlock.hide.HideActivity;
import com.speedata.applicationlock.show.ShowActivity;

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
 * @author Reginer in  2016/12/30 9:42.
 *         QQ:282921012
 *         Description:主页面
 */
public class AdminActivity extends BaseActivity implements CommonRvAdapter.OnItemClickListener,
        AdapterView.OnItemSelectedListener {

    private List<AppInfo> mAllAppList;
    private AppsAdapter mAdapter;
    private Spinner mSourceSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView();
        loadApps();
    }

    private void loadApps() {
        if (DbCommon.queryAppList(false).isEmpty()) {
            mAllAppList.addAll(ToolsCommon.getAllAppList(this));
            DbCommon.saveAppList(mAllAppList);
        } else {
            mAllAppList.addAll(DbCommon.queryAppList(false));
            mLoadAppThread.start();
        }

        mAdapter.notifyDataSetChanged();

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        View mView = getLayoutInflater().inflate(R.layout.view_options_view, new LinearLayout(this), false);
        toolbar.addView(mView, 0);
        mSourceSpinner = (Spinner) mView.findViewById(R.id.spn_options);
        mSourceSpinner.setOnItemSelectedListener(this);
        RecyclerView mRvContent = (RecyclerView) findViewById(R.id.rv_content);
        mRvContent.setLayoutManager(new GridLayoutManager(this, 4));
        mAllAppList = new ArrayList<>();
        mAdapter = new AppsAdapter(this, R.layout.view_all_app_item_layout, mAllAppList);
        mRvContent.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
        ToolsCommon.startApp(mAllAppList, position, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_admin:
                ToolToast.showPwdDialog(this, R.layout.view_input_pwd_dialog_layout);
                return true;
            case R.id.action_clear:
                return true;
            case R.id.action_about:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getShowSpinner(AdminTag adminTag) {
        if (adminTag.isAdmin()) {
            mSourceSpinner.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAppChanged(AppChanged appChanged) {
        mAllAppList.clear();
        if (appChanged.isHide()) {
            mAllAppList.addAll(DbCommon.queryAppList(false));
        } else {
            mAllAppList.addAll(ToolsCommon.getAppChangedList(this, appChanged.isAdd(), appChanged.getPackageName()));
        }
        mAdapter.notifyDataSetChanged();
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

    /**
     * 开启比对已安装应用和数据库app比对线程
     */
    private Thread mLoadAppThread = new Thread(new Runnable() {
        @Override
        public void run() {
            ToolsCommon.getCompareDbList(AdminActivity.this);
        }
    });

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        switch (position) {
            case 0:
                startActivity(new Intent(this, HideActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, ShowActivity.class));
                break;

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
