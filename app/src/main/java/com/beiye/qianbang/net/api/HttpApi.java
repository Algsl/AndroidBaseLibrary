package com.beiye.qianbang.net.api;



import com.beiye.qianbang.net.bean.LoginBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HttpApi {

    @POST("/index.php/api/student/login.html")
    Observable<ResponseBody> login(@Body LoginBean loginBean);
}
