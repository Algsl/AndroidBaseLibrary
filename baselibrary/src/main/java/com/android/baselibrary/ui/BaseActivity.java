package com.android.baselibrary.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.EditText;

import com.android.baselibrary.utils.SharedPreferenceUtil;
import com.android.baselibrary.utils.StatusBarUtil;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucentStatus(BaseActivity.this);
        BaseApplication.getApp().addActivity(BaseActivity.this);

    }

    public void openActivity(Class<?> pClass,Bundle bundle){
        Intent intent=new Intent(this,pClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    public String getString(EditText et){
        return et.getText().toString().trim();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();

        showLanguage(language);
    }

    protected void showLanguage(String language) {
        //设置应用语言类型
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        if (language.equals("zh")) {
            config.locale = Locale.SIMPLIFIED_CHINESE;
        } else {
            config.locale = Locale.ENGLISH;
        }
        resources.updateConfiguration(config, dm);
        SharedPreferenceUtil.putString(this,"language",language);
    }
}
