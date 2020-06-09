package com.android.baselibrary.netutils;

import com.android.baselibrary.BuildConfig;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitFactory {

    public static String BASE_URL="";

    private static final int CONNECT_TIMEOUT=30;
    private static final int WRITE_TIMEOUT=30;
    private static final int READ_TIMEOUT=30;

    private Retrofit retrofit;
    //失败重连次数
    private int RETRY_COUNT=0;
    private OkHttpClient.Builder okHttpBuilder;

    //私有构造器
    private RetrofitFactory(){
        okHttpBuilder=new OkHttpClient.Builder();
        //设置请求头
        Interceptor headerInterceptor=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originRequest=chain.request();
                Request.Builder requestBuilder = originRequest.newBuilder()
                        .addHeader("Accept-Encoding", "gzip")
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json;charset=UTF-8")
                        .method(originRequest.method(), originRequest.body());
                Request request=requestBuilder.build();
                return chain.proceed(request);
            }
        };
        okHttpBuilder.addInterceptor(headerInterceptor);

        okHttpBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);

        okHttpBuilder.retryOnConnectionFailure(true);//错误重连

        if (BuildConfig.DEBUG) {
            //打印网络请求日志
            LoggingInterceptor httpLoggingInterceptor = new LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request("请求")
                    .response("响应")
                    .build();
            okHttpBuilder.addInterceptor(httpLoggingInterceptor);
        }

        retrofit=new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    //创建RetrofitFactory单例
    private static class SingleHolder{
        private static final  RetrofitFactory INSTANCE=new RetrofitFactory();
    }
    //获取单例
    public static RetrofitFactory getInstance(String url){
        BASE_URL=url;
        return SingleHolder.INSTANCE;
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }


    public <T> void toSubscribe(Observable<T> o, DisposableObserver<T> s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())//网络请求取消切换
                .observeOn(AndroidSchedulers.mainThread())
                .retry(RETRY_COUNT)
                .subscribe(s);
    }
}
