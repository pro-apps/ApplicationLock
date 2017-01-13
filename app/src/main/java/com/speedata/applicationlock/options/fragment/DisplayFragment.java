package com.speedata.applicationlock.options.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.speedata.applicationlock.R;
import com.speedata.applicationlock.base.BaseFragment;
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
 * 创   建:Reginer in  2017/1/13 11:20.
 * 联系方式:QQ:282921012
 * 功能描述:显示Fragment
 */
public class DisplayFragment extends BaseFragment {


    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((OptionsActivity) getActivity()).mToolbar.setTitle(R.string.display);
    }

    @Override
    public void onPause() {
        super.onPause();
        ((OptionsActivity) getActivity()).mToolbar.setTitle(R.string.options);
    }
}
