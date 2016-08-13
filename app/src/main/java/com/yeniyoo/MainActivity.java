package com.yeniyoo;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yeniyoo.Core.CustomDrawerActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.kaelaela.verticalviewpager.VerticalViewPager;
import me.kaelaela.verticalviewpager.transforms.DefaultTransformer;

//메인 화면 설정

public class MainActivity extends CustomDrawerActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.vertical_viewpager)
    VerticalViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeToolbar(toolbar);
        viewPager.setAdapter(new RoundFragmentAdapter.Holder(getSupportFragmentManager())
                .add(RoundFragment.newInstance("속초를 가야할까 속초를 가야할까 속초를 가야할까", "http://www.google.co.kr/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwid5t7qsL7OAhVEl5QKHTqPCfMQjRwIBw&url=http%3A%2F%2Fstackoverflow.com%2Fquestions%2F35179106%2Fandroid-navigation-drawer-center-menu-vertically-in-navigationview&psig=AFQjCNF4ptgOZYsMtc2zwAaBF12KXrbKxw&ust=1471177345622768"))
                .add(RoundFragment.newInstance("속초를 가야할까까까까까가야할까까까까까가야할까까까까까가야할까까까까까가야할까까까까까", "http://www.google.co.kr/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwid5t7qsL7OAhVEl5QKHTqPCfMQjRwIBw&url=http%3A%2F%2Fstackoverflow.com%2Fquestions%2F35179106%2Fandroid-navigation-drawer-center-menu-vertically-in-navigationview&psig=AFQjCNF4ptgOZYsMtc2zwAaBF12KXrbKxw&ust=1471177345622768"))
                .set());
        //If you setting other scroll mode, the scrolled fade is shown from either side of display.
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        viewPager.setPageTransformer(false, new DefaultTransformer());
    }
}
