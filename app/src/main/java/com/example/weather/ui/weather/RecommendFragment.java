package com.example.weather.ui.weather;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.amap.api.maps.model.Text;
import com.example.weather.R;

import static com.example.weather.util.QRCode.createQRCodeBitmap;

public class RecommendFragment extends Fragment {

    private ListView listView;

    public RecommendFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_recommend, container, false);


        Resources res = getResources();
        Bitmap logoBitmap= BitmapFactory.decodeResource(res,R.mipmap.ic_launcher);
        Bitmap qrCodeBitmap = createQRCodeBitmap("hello android!", 400, 400,"UTF-8","H", "1", Color.BLACK, Color.WHITE, logoBitmap,0.2F);
        ImageView qrCode = view.findViewById(R.id.qr_code);
        qrCode.setImageBitmap(qrCodeBitmap);

        TextView tv1 = view.findViewById(R.id.tv1);
        tv1.getBackground().setAlpha(100);

        TextView tv2 = view.findViewById(R.id.tv2);
        tv2.getBackground().setAlpha(100);

        return view;

    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);


    }
}
