package com.speedata.applicationlock.manager.fragment;


import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.speedata.applicationlock.R;
import com.speedata.applicationlock.base.BaseFragment;
import com.speedata.applicationlock.bean.AppChanged;
import com.speedata.applicationlock.bean.AppInfo;
import com.speedata.applicationlock.common.DbCommon;
import com.speedata.applicationlock.common.utils.ToolToast;
import com.speedata.applicationlock.manager.adapter.ShowAppAdapter;

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
 * 创   建:Reginer in  2017/1/6 11:04.
 * 联系方式:QQ:282921012
 * 功能描述:显示应用
 */
public class ShowFragment extends BaseFragment implements View.OnClickListener, CommonRvAdapter.OnItemClickListener {

    private ShowAppAdapter mAdapter;
    private List<AppInfo> mAppList;

    public ShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View mView = inflater.inflate(R.layout.fragment_show, container, false);
        initView(mView);
        return mView;
    }

    private void initView(View mView) {
        mAppList = new ArrayList<>();
        mAppList.addAll(SQLite.select().from(AppInfo.class).queryList());
        mAdapter = new ShowAppAdapter(getActivity(), R.layout.view_hide_app_item_layout, mAppList);
        RecyclerView mRvContent = (RecyclerView) mView.findViewById(R.id.rv_content);
        mRvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvContent.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRvContent.setAdapter(mAdapter);
        Button mBtnSave = (Button) mView.findViewById(R.id.btn_save);
        mBtnSave.setOnClickListener(this);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                save();
                break;
        }
    }

    private void save() {
        DbCommon.saveAppList(mAppList);
        EventBus.getDefault().post(new AppChanged(true));
        ToolToast.toastShort(getString(R.string.config_success));
    }


    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
        mAppList.get(position).setHide(!mAppList.get(position).isHide());
        mAdapter.notifyDataSetChanged();
    }
}
