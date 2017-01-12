package com.speedata.applicationlock.options;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.base.BaseActivity;
import com.speedata.applicationlock.options.source.Source;

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
 * <p>
 * 创   建:Reginer in  2017/1/9 15:00.
 * 联系方式:QQ:282921012
 * 功能描述:选项页面
 */

public class OptionsActivity extends BaseActivity implements
        CommonRvAdapter.OnItemClickListener, View.OnClickListener {

    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        initView();
    }

    private void initView() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.options);
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        OptionsAdapter mAdapter = new OptionsAdapter
                (this, R.layout.view_options_item_layout, Source.getOptionsList());
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rv_content);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);


        RelativeLayout mRelativeLayout = (RelativeLayout) findViewById(R.id.rl_allow_usb);
        mRelativeLayout.setOnClickListener(this);

        mCheckBox = (CheckBox) findViewById(R.id.cb_is_allow);
        mCheckBox.setVisibility(View.VISIBLE);
        mCheckBox.setChecked(Settings.Global.getInt(
                getContentResolver(), Settings.Global.ADB_ENABLED, 0) != 0);
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_allow_usb:
                mCheckBox.setChecked(!mCheckBox.isChecked());
                allow(mCheckBox.isChecked());
                break;

        }
    }

    /**
     * 允许usb调试
     * @param checked checked
     */
    private void allow(boolean checked) {
        Settings.Global.putInt(getContentResolver(), Settings.Global.ADB_ENABLED, checked ? 1 : 0);
    }
}
