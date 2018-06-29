package com.example.moniyue.view.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.moniyue.R;
import com.example.moniyue.view.fragment.Fragmentcar;
import com.example.moniyue.view.fragment.Fragmentfenlei;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends AppCompatActivity {

    private BottomTabBar bottombar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottombar = findViewById(R.id.bottombar);

        bottombar.init(getSupportFragmentManager())
            .setImgSize(100,100)
            .setFontSize(0)
            .setChangeColor(Color.RED,Color.DKGRAY)
            .addTabItem( "分类", R.drawable.find,R.drawable.find,Fragmentfenlei.class)
            .addTabItem( "购物车", R.drawable.car, R.drawable.car,Fragmentcar.class)
            .isShowDivider(false);

    }
}
