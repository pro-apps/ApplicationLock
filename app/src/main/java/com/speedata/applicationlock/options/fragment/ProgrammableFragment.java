package com.speedata.applicationlock.options.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

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
 * 创   建:Reginer in  2017/1/13 12:24.
 * 联系方式:QQ:282921012
 * 功能描述:可编程键配置Fragment
 */
public class ProgrammableFragment extends BaseFragment implements View.OnClickListener {


    private CheckBox mCbVolumeUp;
    private CheckBox mCbVolumeDown;
    private CheckBox mCbLeftFunction;
    private CheckBox mCbRightFunction;

    public ProgrammableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_programmable, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCbVolumeUp = (CheckBox) view.findViewById(R.id.cb_volume_up);
        mCbVolumeDown = (CheckBox) view.findViewById(R.id.cb_volume_down);
        mCbLeftFunction = (CheckBox) view.findViewById(R.id.cb_left_function);
        mCbRightFunction = (CheckBox) view.findViewById(R.id.cb_right_function);

        view.findViewById(R.id.rl_volume_up).setOnClickListener(this);
        view.findViewById(R.id.rl_volume_down).setOnClickListener(this);
        view.findViewById(R.id.rl_left_function).setOnClickListener(this);
        view.findViewById(R.id.rl_right_function).setOnClickListener(this);


        mCbVolumeUp.setChecked((boolean) SPUtils.get(mContext, GlobalParams.VOLUME_UP,
                false, GlobalParams.APP_CONFIG));
        mCbVolumeDown.setChecked((boolean) SPUtils.get(mContext, GlobalParams.VOLUME_DOWN,
                false, GlobalParams.APP_CONFIG));
        mCbLeftFunction.setChecked((boolean) SPUtils.get(mContext, GlobalParams.LEFT_FUNCTION,
                false, GlobalParams.APP_CONFIG));
        mCbRightFunction.setChecked((boolean) SPUtils.get(mContext, GlobalParams.RIGHT_FUNCTION,
                false, GlobalParams.APP_CONFIG));


    }

    @Override
    public void onStart() {
        super.onStart();
        ((OptionsActivity) getActivity()).mToolbar.setTitle(R.string.programmable_keys);
    }

    @Override
    public void onPause() {
        super.onPause();
        ((OptionsActivity) getActivity()).mToolbar.setTitle(R.string.options);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_volume_up:
                volumeUp();
                break;
            case R.id.rl_volume_down:
                volumeDown();
                break;
            case R.id.rl_left_function:
                leftFunction();
                break;
            case R.id.rl_right_function:
                rightFunction();
                break;

        }
    }

    private void rightFunction() {
        mCbRightFunction.setChecked(!mCbRightFunction.isChecked());
        SPUtils.put(mContext, GlobalParams.RIGHT_FUNCTION, mCbRightFunction.isChecked(),
                GlobalParams.APP_CONFIG);
    }

    private void leftFunction() {
        mCbLeftFunction.setChecked(!mCbLeftFunction.isChecked());
        SPUtils.put(mContext, GlobalParams.LEFT_FUNCTION, mCbLeftFunction.isChecked(),
                GlobalParams.APP_CONFIG);
    }

    private void volumeDown() {
        mCbVolumeDown.setChecked(!mCbVolumeDown.isChecked());
        SPUtils.put(mContext, GlobalParams.VOLUME_DOWN, mCbVolumeDown.isChecked(),
                GlobalParams.APP_CONFIG);
    }

    private void volumeUp() {
        mCbVolumeUp.setChecked(!mCbVolumeUp.isChecked());
        SPUtils.put(mContext, GlobalParams.VOLUME_UP, mCbVolumeUp.isChecked(),
                GlobalParams.APP_CONFIG);
    }
}
