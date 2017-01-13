package com.speedata.applicationlock.options.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

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
public class DisplayFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {


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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TextView mTvTitle = (TextView) view.findViewById(R.id.tv_title_no_checkbox);
        mTvTitle.setText(R.string.wallpaper);
        TextView mTvSummary = (TextView) view.findViewById(R.id.tv_summary_no_checkbox);
        mTvSummary.setText(R.string.wallpaper_config);
        Switch mSwIsShows = (Switch) view.findViewById(R.id.sw_is_show);
        mSwIsShows.setOnCheckedChangeListener(this);
        view.findViewById(R.id.ll_wallpaper).setOnClickListener(this);
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_wallpaper:
                Intent intent = new Intent(Intent.ACTION_SET_WALLPAPER);
                Intent chooser = Intent.createChooser(intent, getString(R.string.wallpaper));
                startActivity(chooser);
                break;

            default:
                break;
        }
    }
}
