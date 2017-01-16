package com.speedata.applicationlock.options.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.base.BaseFragment;
import com.speedata.applicationlock.common.GlobalParams;
import com.speedata.applicationlock.common.utils.SPUtils;
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
 * 创   建:Reginer in  2017/1/13 12:23.
 * 联系方式:QQ:282921012
 * 功能描述:通知栏配置Fragment
 */
public class NotificationFragment extends BaseFragment implements View.OnClickListener {

    private CheckBox mShowBox;
    private CheckBox mEnableBox;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mShowBox = (CheckBox) view.findViewById(R.id.cb_is_allow);
        mEnableBox = (CheckBox) view.findViewById(R.id.cb_is_enable);
        TextView mShowNotificationTitle = (TextView) view.findViewById(R.id.tv_title);
        TextView mShowNotificationSummary = (TextView) view.findViewById(R.id.tv_summary);
        mShowNotificationTitle.setText(R.string.show_notification);
        mShowNotificationSummary.setText(R.string.to_show_notification);
        mShowBox.setVisibility(View.VISIBLE);
        mShowBox.setChecked((boolean) SPUtils.get(mContext, GlobalParams.IS_SHOW_NOTIFICATION_KEY,
                false, GlobalParams.APP_CONFIG));

        view.findViewById(R.id.rl_show_notification).setOnClickListener(this);
        view.findViewById(R.id.rl_enable_notification).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((OptionsActivity) getActivity()).mToolbar.setTitle(R.string.notification_bar);
    }

    @Override
    public void onPause() {
        super.onPause();
        ((OptionsActivity) getActivity()).mToolbar.setTitle(R.string.options);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_show_notification:
                showNotification();
                break;
            case R.id.rl_enable_notification:
                enableNotification();
                break;

        }
    }

    private void enableNotification() {
        mEnableBox.setChecked(!mEnableBox.isChecked());
    }

    private void showNotification() {
        mShowBox.setChecked(!mShowBox.isChecked());
        if (mShowBox.isChecked())
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        else
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        SPUtils.put(mContext, GlobalParams.IS_SHOW_NOTIFICATION_KEY, mShowBox.isChecked(),
                GlobalParams.APP_CONFIG);

    }
}
