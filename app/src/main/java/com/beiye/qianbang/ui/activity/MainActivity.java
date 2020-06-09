package com.beiye.qianbang.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.android.baselibrary.ui.BaseActivity;
import com.beiye.qianbang.R;
import com.beiye.qianbang.ui.fragment.HomeFragment;
import com.beiye.qianbang.ui.fragment.MineFragment;
import com.beiye.qianbang.ui.fragment.NotifyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.ac_main_frame)
    FrameLayout acMainFrame;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    private int mIndex = 0;
    private Fragment[] mFragments;
    private Fragment homeFragment, notifyFragment, mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragment();

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_home:
                        selectIndex(0);
                        break;
                    case R.id.item_notify:
                        selectIndex(1);
                        break;
                    case R.id.item_mine:
                        selectIndex(2);
                        break;
                }
                return true;
            }
        });
    }

    //碎片初始化
    private void initFragment() {
        homeFragment = new HomeFragment();
        notifyFragment = new NotifyFragment();
        mineFragment = new MineFragment();

        mFragments = new Fragment[]{homeFragment, notifyFragment, mineFragment};

        //开启事务
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.ac_main_frame, mFragments[0]).commit();
    }



    public void selectIndex(int index) {
        //判断用户是否重复选择已选中的控件
        if (mIndex == index) {
            return;
        }

        //获取碎片管理，并开启事务
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        //隐藏上次选中的碎片
        ft.hide(mFragments[mIndex]);

        for(int i=0;i<mFragments.length;i++){
            Log.e("测试", "selectIndex: "+mFragments[i].isAdded() );
        }

        //判断是否添加至碎片管理器中，
        if (!mFragments[index].isAdded()) { //未加入activity
            fragmentManager.beginTransaction().add(R.id.ac_main_frame, mFragments[index]).commit();
        } else {//已加入activity
            ft.show(mFragments[index]);
        }
        ft.commit();
        //再次赋值
        mIndex = index;
    }


}
