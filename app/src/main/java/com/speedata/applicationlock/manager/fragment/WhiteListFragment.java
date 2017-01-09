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
import com.speedata.applicationlock.bean.AppInfo;
import com.speedata.applicationlock.common.DbCommon;
import com.speedata.applicationlock.common.utils.ToolToast;
import com.speedata.applicationlock.manager.adapter.WhiteListAdapter;

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
 * <p>
 * 创   建:Reginer in  2017/1/5 16:43.
 * 联系方式:QQ:282921012
 * 功能描述:白名单
 */
public class WhiteListFragment extends BaseFragment implements View.OnClickListener, CommonRvAdapter.OnItemClickListener {

    private WhiteListAdapter mAdapter;
    private List<AppInfo> mWhiteList;

    public WhiteListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_white_list, container, false);
        initView(mView);
        return mView;
    }

    private void initView(View mView) {
        mWhiteList = new ArrayList<>();
        mWhiteList.addAll(SQLite.select().from(AppInfo.class).queryList());

        mAdapter = new WhiteListAdapter(getActivity(), R.layout.view_hide_app_item_layout, mWhiteList);
        RecyclerView mRvContent = (RecyclerView) mView.findViewById(R.id.rv_content);
        mRvContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvContent.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mRvContent.setAdapter(mAdapter);
        Button mBtnSave = (Button) mView.findViewById(R.id.btn_save);
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
        ToolToast.toastShort(getString(R.string.config_success));
    }

    @Override
    public void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position) {
        mWhiteList.get(position).setWhiteList(!mWhiteList.get(position).isWhiteList());
        mAdapter.notifyDataSetChanged();
    }
}
