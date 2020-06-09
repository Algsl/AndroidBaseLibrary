package com.beiye.qianbang.net.api;

import android.util.Log;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class OnResultSub extends DisposableObserver<ResponseBody>  {

    private OnResultListener listener;

    public OnResultSub(OnResultListener listener){
        this.listener=listener;
    }


    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            String result=responseBody.string();

            JSONObject object=new JSONObject(result);
            int code=object.getInt("code");
            if(code==0){
                listener.onSuccess(result);
            }else{
                String msg=object.getString("msg");
                listener.onFail(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e("测试", "onError: "+e.getMessage() );
        if(e instanceof SocketTimeoutException){
            listener.onFail("网络请求超时");
        }else if(e instanceof ConnectException){
            listener.onFail("连接错误");
        }else if(e instanceof SSLHandshakeException){
            listener.onFail("安全证书错误");
        }else if(e instanceof HttpException){
            int code=((HttpException) e).code();
            if(code ==504){
                listener.onFail("网络异常");
            }else if(code == 404){
                listener.onFail("请求地址不存在");
            }else{
                listener.onFail("请求异常");
            }
        }else if(e instanceof UnknownHostException){
            listener.onFail("域名解析错误");
        }else{
            listener.onFail(e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }
}
