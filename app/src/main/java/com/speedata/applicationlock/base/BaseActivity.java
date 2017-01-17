package com.speedata.applicationlock.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.speedata.applicationlock.common.GlobalParams;
import com.speedata.applicationlock.common.utils.SPUtils;

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
 * @author Reginer on  2016/12/14 14:03.
 *         Description:Activity基类
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean isShowNotification = (boolean) SPUtils.get
                (this, GlobalParams.IS_SHOW_NOTIFICATION_KEY, false, GlobalParams.APP_CONFIG);
        if (isShowNotification)
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        else
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }




//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP &&
//                (boolean) SPUtils.get(this, GlobalParams.VOLUME_UP, false, GlobalParams.APP_CONFIG))
//            return true;
//        else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN &&
//                (boolean) SPUtils.get(this, GlobalParams.VOLUME_UP, false, GlobalParams.APP_CONFIG))
//            return true;
//
//        return super.onKeyDown(keyCode, event);
//    }
}