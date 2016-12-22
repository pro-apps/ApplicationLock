package com.speedata.applicationlock.hide;

import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.base.BaseActivity;
import com.speedata.applicationlock.bean.AppsBean;
import com.speedata.applicationlock.common.DbCommon;
import com.speedata.applicationlock.common.ToolsCommon;

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
 * @author Reginer in  2016/12/14 17:28.
 *         Description:隐藏应用
 */
public class HideActivity extends BaseActivity implements View.OnClickListener, CommonRvAdapter.OnItemClickListener {

    private List<AppsBean> mAppsList;
    private HideAppAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide);
        initView();
    }

    private void initView() {
        List<ResolveInfo> mAppList = getIntent().getParcelableArrayListExtra("appList");
        mAppsList = new ArrayList<>();
        mAppsList.addAll(ToolsCommon.getAppsList(mAppList));
        mAdapter = new HideAppAdapter(this, R.layout.view_hide_app_item_layout, mAppsList);
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
                EventBus.getDefault().post(ToolsCommon.getShowAppList(mAppsList));
                DbCommon.saveDb(mAppsList);
                finish();
                break;
        }
    }


    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
        mAppsList.get(position).setCheck(!mAppsList.get(position).isCheck());
        mAdapter.notifyItemChanged(position);
    }
}
