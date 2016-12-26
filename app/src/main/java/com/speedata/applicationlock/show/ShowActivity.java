package com.speedata.applicationlock.show;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.base.BaseActivity;
import com.speedata.applicationlock.bean.AppChanged;
import com.speedata.applicationlock.bean.AppInfo;
import com.speedata.applicationlock.common.DbCommon;

import org.greenrobot.eventbus.EventBus;

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
 * @author Reginer in  2016/12/15 11:50.
 *         Description:显示应用
 */
public class ShowActivity extends BaseActivity implements View.OnClickListener, CommonRvAdapter.OnItemClickListener {
    private ShowAppAdapter mAdapter;
    private List<AppInfo> mCanShowAppList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
    }

    private void initView() {
        mCanShowAppList = new ArrayList<>();
        mCanShowAppList.addAll(DbCommon.queryAppList(true));
        mAdapter = new ShowAppAdapter(this, R.layout.view_hide_app_item_layout, mCanShowAppList);
        RecyclerView mRvContent = (RecyclerView) findViewById(R.id.rv_content);
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mRvContent.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRvContent.setAdapter(mAdapter);
        Button mBtnSave = (Button) findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                DbCommon.saveAppList(mCanShowAppList);
                EventBus.getDefault().post(new AppChanged(true));
                finish();
                break;
        }
    }


    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
        mCanShowAppList.get(position).setHide(!mCanShowAppList.get(position).isHide());
        mAdapter.notifyDataSetChanged();
    }
}
