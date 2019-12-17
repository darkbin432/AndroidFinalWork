package com.example.weather.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.weather.DatabaseHelper;
import com.example.weather.data.model.LoggedInUser;
import com.example.weather.db.User;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource{

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
//            System.out.println("123");
//            DataSupport.
//            DatabaseHelper databaseHelper = new DatabaseHelper(null, "test.db", null, 1);
//            System.out.println("456");
//            SQLiteDatabase db = databaseHelper.getWritableDatabase();
//            System.out.println("789");
//            //创建游标对象
//            Cursor cursor = db.query("user", new String[]{"id", "username", "name", "phone"}, "username = ? and password = ?", new String[]{username, password}, null, null, "1,1");
//            LoggedInUser user =
//                    new LoggedInUser(
//                            cursor.getInt(cursor.getColumnIndex("id")),
//                            cursor.getString(cursor.getColumnIndex("username")),
//                            cursor.getString(cursor.getColumnIndex("password")),
//                            cursor.getString(cursor.getColumnIndex("name")),
//                            cursor.getString(cursor.getColumnIndex("phone"))
//                            );
//            // 关闭游标，释放资源
//            cursor.close();
            System.out.println("123");
            List<User> userList = DataSupport.select("id", "username", "password","name", "phone")//需要的条目
                    .where("username= ? and password= ?", username, password)//条件
                    .offset(0)//开始查询位置
                    .limit(1)//数据长度
                    .find(User.class);//查询表
            System.out.println("456" + userList.get(0).getName());
            if (userList.size() > 0){
                LoggedInUser user = new LoggedInUser(userList.get(0));
                return new Result.Success<>(user);
            }else{
                return null;
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
