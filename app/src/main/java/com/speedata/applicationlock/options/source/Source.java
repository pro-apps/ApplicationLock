package com.speedata.applicationlock.options.source;

import com.speedata.applicationlock.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

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
 * 创   建:Reginer in  2017/1/12 15:47.
 * 联系方式:QQ:282921012
 * 功能描述:
 */
public class Source {
  public static List<ItemBean> getOptionsList(){
      List<ItemBean> mList = new ArrayList<>();
      mList.add(new ItemBean("通知栏","屏幕顶部栏"));
      mList.add(new ItemBean("显示","配置显示"));
      mList.add(new ItemBean("安全","安全配置"));
      mList.add(new ItemBean("可编程键","可编程键配置"));
      return mList;
  }
}
