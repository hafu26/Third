package demo.isoft.com.third;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GrideActivity extends AppCompatActivity {
 ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gride);
        viewPager=(ViewPager)findViewById(R.id.viewPager);//初始化
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position==0){
                    return new GuideFragment1();
                }
                if(position==1){
                    return new GuideFragment2();
                }
                if(position==2){
                    return new GuideFragment4();
                }
                if(position==3){
                    return new GuideFragment3();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });//适配器
    }
}
