package demo.isoft.com.third;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
ViewPager viewPager;
    MainFragment1 mainFragment1;
    MainFragment2 mainFragment2;
    MainFragment3 mainFragment3;
    MainFragment4 mainFragment4;
    RadioGroup radioGroup;
    public void init(){
        viewPager = (ViewPager) findViewById(R.id.mainViewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
               if(position==0){
                   if(mainFragment1==null){
                       mainFragment1=new MainFragment1();
                   }
                   return mainFragment1;
               }
                if(position==1){
                    if(mainFragment2==null){
                        mainFragment2=new MainFragment2();
                    }
                    return mainFragment2;
                }
                if(position==2){
                    if(mainFragment3==null){
                        mainFragment3=new MainFragment3();
                    }
                    return mainFragment3;
                }
                if(position==3){
                    if(mainFragment4==null){
                        mainFragment4=new MainFragment4();
                    }
                    return mainFragment4;
                }
                return null;
            }
            @Override
            public int getCount() {
                return 4;
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("航旅纵横");
        toolbar.setLogo(R.mipmap.logo);
        toolbar.setSubtitle("首页");
        toolbar.setBackground( getDrawable(R.mipmap.guid_show1));
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.mainViewPager);
        radioGroup= (RadioGroup) findViewById(R.id.bottomHav);
        init();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                Toast.makeText(MainActivity.this,"aa"+checkedId,Toast.LENGTH_SHORT).show();
                if(checkedId==R.id.first){
                    viewPager.setCurrentItem(0);
                }
                if(checkedId==R.id.second){
                    viewPager.setCurrentItem(1);
                }
                if(checkedId==R.id.third){
                    viewPager.setCurrentItem(2);
                }
                if(checkedId==R.id.forth){
                    viewPager.setCurrentItem(3);
                }
            }
        });
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
