package com.wangrui.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.wangrui.android.Shopping.Fragment.ShoppingFragment;
import com.wangrui.android.base.BaseFragment;
import com.wangrui.android.home.Fragment.HomeFragment;
import com.wangrui.android.type.Fragment.typeFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.frame_Layout)
    FrameLayout frameLayout;
    @InjectView(R.id.Rig_man)
    RadioGroup RigMan;

    private ArrayList<BaseFragment> fragments;

    private int position;

    private Fragment tempFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initFragment();

        initListener();
    }

    private void initListener() {
        RigMan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int chechedId) {
                switch (chechedId) {
                    case R.id.kj_btn1:
                        position = 0;
                        break;
                    case R.id.kj_btn2:
                        position = 1;
                        break;
                    case R.id.kj_btn3:
                        position = 2;

                        break;


                }
                Fragment currentFragment = fragments.get(position);
                switchFragment(currentFragment);
            }
        });
        RigMan.check(R.id.kj_btn1);
    }

    private void switchFragment(Fragment currentFragment) {
        if (tempFragment != currentFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (!currentFragment.isAdded()) {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.add(R.id.frame_Layout, currentFragment);
            } else {
                if (tempFragment != null) {

                    ft.hide(tempFragment);

                }
                ft.show(currentFragment);

            }
            ft.commit();

            tempFragment = currentFragment;
        }
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ShoppingFragment());
        fragments.add(new typeFragment());
    }


}
