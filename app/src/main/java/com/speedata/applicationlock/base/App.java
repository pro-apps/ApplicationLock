package com.speedata.applicationlock.base;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
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
 * @author Reginer on  2016/12/15 12:14.
 *         Description:app
 */
public class App extends Application {
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        mInstance = this;
        FlowManager.init(new FlowConfig.Builder(this).build());
        if (!SPUtils.contains(this, GlobalParams.IS_SHOW_NOTIFICATION_KEY, GlobalParams.APP_CONFIG))
            SPUtils.put(this, GlobalParams.IS_SHOW_NOTIFICATION_KEY, true,
                    GlobalParams.APP_CONFIG);

    }


    public static App getInstance() {
        return mInstance;
    }
}