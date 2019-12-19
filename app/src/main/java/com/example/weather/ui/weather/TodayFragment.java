package com.example.weather.ui.weather;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.weather.MainActivity;
import com.example.weather.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TodayFragment extends Fragment {

    public String date;
    public ListView lv;

    View view;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:

                    lv = view.findViewById(R.id.lv);
                    Weather weather= (Weather) msg.obj;
                    String str = "ok";
                    if(String.valueOf(weather.getHeWeather6().get(0).getStatus()).equals(str)) {
                        String[] data = String.valueOf("" + weather.getHeWeather6().get(0).getBasic().getLocation() +
                                "  时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(weather.getHeWeather6().get(0).getUpdate().getLoc()) +
                                "  天气：" + weather.getHeWeather6().get(0).getNow().getCond_txt() +
                                "  气温：" + weather.getHeWeather6().get(0).getNow().getTmp() + " ℃" +
                                "  风向：" + weather.getHeWeather6().get(0).getNow().getWind_dir()
                        ).split("  ");
                        Log.i("test", weather.getHeWeather6().get(0).getUpdate().getLoc().toString());
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, data);
                        lv.setAdapter(adapter);
                    }else{

                    }
                    break;
            }

        }
    };

    public TodayFragment() {
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_today, container, false);
        Thread thread = new Thread(){
            public void run(){
                String url = "https://free-api.heweather.com/s6/weather/now?key=e1984444a03d4a52a1f6cc545cce9245&location=" + MainActivity.city;
                new MyAsyncTask().execute(url);
            }
        };
        thread.start();

        return view;

    }

    class MyAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            String url = strings[0];
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10,TimeUnit.SECONDS)
                    .writeTimeout(10,TimeUnit.SECONDS)
                    .build();
            final Request request = new Request.Builder().url(url).build();
            Call call  = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("EX15",e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    date = response.body().string();
                    Message msg = new Message();
                    msg.what = 1;
//                    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                    Gson gson = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd hh:mm")
                            .create();
                    Weather weather1 = gson.fromJson(date,Weather.class);
                    msg.obj=weather1;
                    handler.sendMessage(msg);

                }
            });
            return null;

        }
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

    }
}
