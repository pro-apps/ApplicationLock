package com.speedata.applicationlock.white;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.speedata.applicationlock.R;
import com.speedata.applicationlock.base.BaseActivity;
import com.speedata.applicationlock.bean.AppInfo;
import com.speedata.applicationlock.common.DbCommon;

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
 * @author Reginer in  2017/1/4 12:18.
 *         QQ:282921012
 *         Description:文件清理白名单配置界面
 */
public class WhiteListActivity extends BaseActivity implements View.OnClickListener,
        CommonRvAdapter.OnItemClickListener {
    private WhiteListAdapter mAdapter;
    private List<AppInfo> mWhiteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white_list);

        initView();
    }

    private void initView() {
        mWhiteList = new ArrayList<>();
        mWhiteList.addAll(SQLite.select().from(AppInfo.class).queryList());

        mAdapter = new WhiteListAdapter(this, R.layout.view_hide_app_item_layout, mWhiteList);
        RecyclerView mRvContent = (RecyclerView) findViewById(R.id.rv_content);
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mRvContent.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRvContent.setAdapter(mAdapter);
        Button mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                save();
                break;
        }
    }

    private void save() {
        DbCommon.saveAppList(mWhiteList);
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
        mWhiteList.get(position).setHide(!mWhiteList.get(position).isWhiteList());
        mAdapter.notifyDataSetChanged();
    }
}
