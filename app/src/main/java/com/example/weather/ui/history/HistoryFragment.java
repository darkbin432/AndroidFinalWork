package com.example.weather.ui.history;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.weather.MainActivity;
import com.example.weather.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.formatter.AxisValueFormatter;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

import static com.example.weather.util.QRCode.createQRCodeBitmap;

public class HistoryFragment extends Fragment {

    private HistoryViewModel historyViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        historyViewModel =
                ViewModelProviders.of(this).get(HistoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);

        ArrayList<PointValue> mPointValues = new ArrayList<>();
        List<AxisValue> mAxisValues = new ArrayList<>();

        mPointValues.add(new PointValue(1, 10));
        mAxisValues.add(new AxisValue(1).setLabel("1")); //为每个对应的i设置相应的label(显示在X轴)
        mPointValues.add(new PointValue(2, 13));
        mAxisValues.add(new AxisValue(2).setLabel("2")); //为每个对应的i设置相应的label(显示在X轴)
        mPointValues.add(new PointValue(3, 11));
        mAxisValues.add(new AxisValue(3).setLabel("3")); //为每个对应的i设置相应的label(显示在X轴)
        mPointValues.add(new PointValue(4, 11));
        mAxisValues.add(new AxisValue(4).setLabel("4")); //为每个对应的i设置相应的label(显示在X轴)
        mPointValues.add(new PointValue(5, 12));
        mAxisValues.add(new AxisValue(5).setLabel("5")); //为每个对应的i设置相应的label(显示在X轴)
        mPointValues.add(new PointValue(6, 11));
        mAxisValues.add(new AxisValue(6).setLabel("6")); //为每个对应的i设置相应的label(显示在X轴)
        mPointValues.add(new PointValue(7, 9));
        mAxisValues.add(new AxisValue(7).setLabel("7")); //为每个对应的i设置相应的label(显示在X轴)

        Line line = new Line(mPointValues).setColor(Color.YELLOW).setCubic(false);

        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        mPointValues.clear();

        mPointValues.add(new PointValue(1, 8));
        mPointValues.add(new PointValue(2, 9));
        mPointValues.add(new PointValue(3, 8));
        mPointValues.add(new PointValue(4, 7));
        mPointValues.add(new PointValue(5, 7));
        mPointValues.add(new PointValue(6, 4));
        mPointValues.add(new PointValue(7, 3));

        line = new Line(mPointValues).setColor(Color.BLUE).setCubic(false);

        lines.add(line);
        LineChartData data = new LineChartData(lines);
//坐标轴 
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);
        axisX.setTextColor(Color.WHITE);
        axisX.setName("历史气温变化");
        axisX.setMaxLabelChars(MainActivity.historyDays);
        axisX.setValues(mAxisValues);
        data.setAxisXBottom(axisX);
        Axis axisY = new Axis(); //Y轴
        axisY.setMaxLabelChars(3); //默认是3，只能看最后三个数字
        data.setAxisYLeft(axisY);
//设置行为属性，支持缩放、滑动以及平移 
        LineChartView mLineChartView = root.findViewById(R.id.chart);
        mLineChartView.setInteractive(true);
        mLineChartView.setZoomType(ZoomType.HORIZONTAL);
        mLineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        mLineChartView.setLineChartData(data);
        mLineChartView.setVisibility(View.VISIBLE);

        return root;
    }
    

}