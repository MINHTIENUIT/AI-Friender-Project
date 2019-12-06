package com.avnhome.aifriender.Views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RadarChartView extends RadarChart {

    private List<User> users = new ArrayList<>();

    public RadarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setUserForChart(User mainUser, User subUser) throws Throwable {
        if (mainUser != null)
            this.users.add(mainUser);

        if (subUser != null)
            this.users.add(subUser);

        setData();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        buildUI();
    }

    private void buildUI(){
        this.animateXY(1400, 1400, Easing.EaseInOutQuad);

        this.setBackgroundColor(getContext().getColor(R.color.background));

        this.getDescription().setEnabled(false);

        this.setWebLineWidth(1f);
        this.setWebColor(Color.LTGRAY);
        this.setWebLineWidthInner(1f);
        this.setWebColorInner(Color.LTGRAY);
        this.setWebAlpha(100);
        this.setTouchEnabled(false);


        XAxis xAxis = this.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
//        xAxis.setValueFormatter(new ValueFormatter() {
//
//            private final String[] mActivities = new String[]{"Openness", "Agreeableness", "Neuroticism", "Conscientiousness", "Extraversion"};
//
//            @Override
//            public String getFormattedValue(float value) {
//                return mActivities[(int) value % mActivities.length];
//            }
//        });
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = this.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = this.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.WHITE);
    }

    private void setData(){
        ArrayList<RadarEntry> entries1 = new ArrayList<>();
        ArrayList<RadarEntry> entries2 = new ArrayList<>();
        ArrayList<IRadarDataSet> sets = new ArrayList<>();

        if (users.size() > 2) return;


        for (Double per: users.get(0).getPersonalities()){
            entries1.add(new RadarEntry(new BigDecimal(per).floatValue()));
        }

        RadarDataSet set1 = new RadarDataSet(entries1, users.get(0).getTwitterId());
        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        sets.add(set1);

        if (users.size() == 2){
            for (Double per: users.get(1).getPersonalities()){
                entries2.add(new RadarEntry(new BigDecimal(per).floatValue()));
            }
            RadarDataSet set2 = new RadarDataSet(entries2, users.get(1).getTwitterId());
            set2.setColor(Color.rgb(121, 162, 175));
            set2.setFillColor(Color.rgb(121, 162, 175));
            set2.setDrawFilled(true);
            set2.setFillAlpha(180);
            set2.setLineWidth(2f);
            set2.setDrawHighlightCircleEnabled(true);
            set2.setDrawHighlightIndicators(false);

            sets.add(set2);
        }

        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        this.setData(data);
        this.invalidate();
    }
}
