package demo.isoft.com.third;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LoginActivity extends AppCompatActivity {

    EditText uname;
    EditText upwd;
    CheckBox rememberpwd;
    CheckBox showpwd;
    Button login;
    Button register;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setActionBar();
        init();
    }
    public void init( ) {
        uname =(EditText)findViewById(R.id.uname);
        upwd = (EditText)findViewById(R.id.upwd);
        rememberpwd=(CheckBox)findViewById(R.id.checkBox);
        showpwd=(CheckBox)findViewById(R.id.checkBox2);
        login=(Button)findViewById(R.id.button);
        register=(Button)findViewById(R.id.button2);
        SharedPreferences loginStatus = getSharedPreferences("loginStatus",MODE_PRIVATE);
        String temp_uname = loginStatus.getString("uname","");
        String temp_upwd = loginStatus.getString("upwd","");
        int temp_status = loginStatus.getInt("status",0);
        uname.setText(temp_uname);
        upwd.setText(temp_upwd);
        if(temp_status==1){
            rememberpwd.setChecked(true);
        }else {
            rememberpwd.setChecked(false);
        }
        showpwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        //           是否记住密码
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    upwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else
                {
                    upwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_uname=uname.getText().toString();
                String str_upwd=upwd.getText().toString();
                if(str_uname.length()==0){
                    Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                }
                if(str_upwd.length()==0){
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }
                if(str_upwd.length()<6){
                    Toast.makeText(LoginActivity.this, "密码不能小于6位", Toast.LENGTH_SHORT).show();
                }
                if(str_uname.equals("admin")&&str_upwd.equals("123456")){
                    SharedPreferences loginStatus = getSharedPreferences("loginStatus",MODE_PRIVATE);
                    SharedPreferences.Editor editor = loginStatus.edit();
                    if(rememberpwd.isChecked()){
                        editor.putString("uname",str_uname);
                        editor.putString("upwd",str_upwd);
                        editor.putInt("status",1);
                    }else {
                        editor.clear();
                    }
                    editor.commit();
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                }
//                else {
//                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setActionBar() {
        ActionBar actionBar =getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle("航班查询系统");
            actionBar.setSubtitle("登录");
            actionBar.setDisplayHomeAsUpEnabled(true);//显示返回的箭头
            actionBar.setDisplayShowHomeEnabled(true);//用与导航栏的最前面的图标是否显示
            actionBar.setDisplayUseLogoEnabled(true);
          actionBar.setBackgroundDrawable( getDrawable(R.mipmap.guid_show1));//设置导航栏背景
           actionBar.setIcon(getDrawable(R.mipmap.logo));//导航栏图标
        }
    }
    //    实现菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(menu!=null){
            if(menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")){
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible",Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu,true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        getMenuInflater().inflate(R.menu.login_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        后续完成
        if(item.getItemId()==R.id.menu_exit){
            AlertDialog.Builder builder =new  AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("是否真的退出系统？");
            builder.setCancelable(true);
            builder.setIcon(R.mipmap.logo);
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }
        if(item.getItemId()==R.id.menu_register){
            EditText text =new EditText(this);
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setTitle("注册");
            builder.setView(text);
            builder.show();
        }
        if(item.getItemId()==R.id.menu_share){
            Toast.makeText(this,"尚未实现",Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()== android.R.id.home){
            Toast.makeText(this,"返回按钮",Toast.LENGTH_SHORT).show();
            System.exit(0);
        }
        return true;
    }
}

