package com.speedata.applicationlock.options.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.base.BaseFragment;
import com.speedata.applicationlock.common.utils.SPUtils;
import com.speedata.applicationlock.common.utils.ToolToast;
import com.speedata.applicationlock.options.OptionsActivity;

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
 * 创   建:Reginer in  2017/1/13 11:19.
 * 联系方式:QQ:282921012
 * 功能描述:安全Fragment
 */
public class SecurityFragment extends BaseFragment implements View.OnClickListener {


    private CheckBox mCbIsAllow;

    public SecurityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_security, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView mLoginTitle = (TextView) view.findViewById(R.id.tv_title_no_checkbox);
        TextView mLoginSummary = (TextView) view.findViewById(R.id.tv_summary_no_checkbox);
        TextView mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView mTvSummary = (TextView) view.findViewById(R.id.tv_summary);
        mCbIsAllow = (CheckBox) view.findViewById(R.id.cb_is_allow);
        mCbIsAllow.setVisibility(View.VISIBLE);
        mCbIsAllow.setChecked((boolean)SPUtils.get(getActivity(), "is_Show_pwd", false, "pwd_file"));
        mLoginTitle.setText(R.string.set_login_pwd);
        mLoginSummary.setText(R.string.set_login_pwd_tab);
        mTvTitle.setText(R.string.show_pwd);
        mTvSummary.setText(R.string.if_you_need_show_pwd);

        view.findViewById(R.id.ll_login_set).setOnClickListener(this);
        view.findViewById(R.id.rl_login_set).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((OptionsActivity) getActivity()).mToolbar.setTitle(R.string.security);
    }

    @Override
    public void onPause() {
        super.onPause();
        ((OptionsActivity) getActivity()).mToolbar.setTitle(R.string.options);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_login_set:
                ToolToast.showPwdDialog(getActivity(), R.layout.view_input_pwd_dialog_layout,true);
                break;

            case R.id.rl_login_set:
                mCbIsAllow.setChecked(!mCbIsAllow.isChecked());
                SPUtils.put(getActivity(), "is_Show_pwd", mCbIsAllow.isChecked(), "pwd_file");
                break;
        }
    }
}
