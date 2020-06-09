package com.beiye.qianbang.net.netsubscribe.test;


import com.android.baselibrary.netutils.RetrofitFactory;
import com.beiye.qianbang.net.api.HttpApi;
import com.beiye.qianbang.net.api.URLConst;
import com.beiye.qianbang.net.bean.LoginBean;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by Android Studio.
 * User: STYL
 * Date: 2020/6/9
 * Time: 15:39
 */
public class Testsub {
    /**
     * 学生登录
     * @param number
     * @param pwd
     * @param subscribe
     */
    public static void login(String number, String pwd, DisposableObserver<ResponseBody> subscribe){
        LoginBean bean=new LoginBean();
        bean.setNumber(number);
        bean.setPassword(pwd);
        Observable<ResponseBody> observable= RetrofitFactory.getInstance(URLConst.BASE_URL).getRetrofit().create(HttpApi.class).login(bean);
        RetrofitFactory.getInstance(URLConst.BASE_URL).toSubscribe(observable,subscribe);
    }
}
