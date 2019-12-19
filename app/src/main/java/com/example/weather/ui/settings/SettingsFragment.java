package com.example.weather.ui.settings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.weather.MainActivity;
import com.example.weather.R;
import com.example.weather.db.User;
import com.example.weather.ui.login.LoginActivity;

import org.litepal.crud.DataSupport;

import java.util.List;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_settings, container, false);

        TextView usernameText = root.findViewById(R.id.username_text);
        usernameText.setText(MainActivity.username);

        EditText city = root.findViewById(R.id.city_edit);
        if (MainActivity.city.equals("hangzhou")){
            city.setText("杭州");
        }else if (MainActivity.city.equals("beijing")){
            city.setText("北京");
        }


        //修改密码
        Button changePassword = root.findViewById(R.id.change_password);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInf=LayoutInflater.from(getContext());
                View view=layoutInf.inflate(R.layout.change_pass, null);

                //一定要通过loginView.findViewById()来取得控件
                final EditText etPwd = view.findViewById(R.id.et_password);
                final EditText etPwdConfirm = view.findViewById(R.id.et_password_confirm);

                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("修改密码");

                //对dialog添加视图
                dialog.setView(view);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = "密码修改成功";
                        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                });

                //创建并显示
                dialog.create().show();
            }
        });

        //修改城市

        Button cityConfirm = root.findViewById(R.id.city_confirm);

        cityConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText city = root.findViewById(R.id.city_edit);

                if (city.getText().toString().equals("杭州")){
                    MainActivity.city = "hangzhou";
                }else if (city.getText().toString().equals("北京")){
                    MainActivity.city = "beijing";
                }

                Toast.makeText(getContext(), "切换默认城市成功", Toast.LENGTH_SHORT).show();
            }
        });

        //自动登录
        Switch autoLogin = root.findViewById(R.id.auto_login);
        if (MainActivity.autoLogin.equals("0")){
            autoLogin.setChecked(false);
        }else{
            autoLogin.setChecked(true);
        }

        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                User user = new User();
                if (b){
                    user.setAutoLogin("1");
                }else{
                    user.setToDefault("autoLogin");
                }
                System.out.println("set" + user.getAutoLogin());
                System.out.println("boolean" + b);
                user.updateAll("username = ?", "admin");
                MainActivity.autoLogin = b ? "1" : "0";
                List<User> userList = DataSupport.where("username = ?", "admin").find(User.class);
                System.out.println("then" + userList.get(0).getAutoLogin());
                for (User u : userList){
                    System.out.println(u.getId() + " " + u.getUsername() + u.getAutoLogin());
                }
            }
        });

        return root;
    }

}