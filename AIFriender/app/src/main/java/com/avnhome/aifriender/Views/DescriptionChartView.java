package com.avnhome.aifriender.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.avnhome.aifriender.R;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DescriptionChartView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private TypedArray typedArray;

    public final static List<String> personalityLabels = Arrays.asList("Openness", "Agreeableness", "Neuroticism", "Conscientiousness", "Extraversion");

    private LinkedHashMap<String, Double> percents = new LinkedHashMap<>();

    public DescriptionChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.DescriptionChartView);
        defaultPer();
    }

    public void setPercents(List<Double> personalies) {
        this.percents.clear();
        for (int i = 0; i < 5; i++){
            percents.put(personalityLabels.get(i), personalies.get(i));
        }
    }

    private void defaultPer(){

        List<Double> per = Arrays.asList(-1d,-1d,-1d,-1d,-1d);
        for (int i = 0; i < 5; i++){
            percents.put(personalityLabels.get(i), per.get(i));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float pointRow1 = canvas.getWidth()/3;
        float align = pointRow1/2;
        float pointRow2 = canvas.getWidth()/2;
        float align2 = pointRow2/2;

        boolean heighAlignCenter = typedArray.getBoolean(R.styleable.DescriptionChartView_layout_alignCenter, false);
        float heightAlignRow1 = 100;
        float heightAlignRow2 = 300;
        if (heighAlignCenter) {
            heightAlignRow1 = canvas.getHeight() / 3;
            heightAlignRow2 = heightAlignRow1 * 2;
        }


        Iterator<Map.Entry<String, Double>> iterator = percents.entrySet().iterator();

        int opennessColor = typedArray.getColor(R.styleable.DescriptionChartView_openness_color,Color.RED);
        int conscientiousnessColor = typedArray.getColor(R.styleable.DescriptionChartView_conscientiousness_color,Color.BLUE);
        int extraversionColor = typedArray.getColor(R.styleable.DescriptionChartView_extraversion_color,Color.GREEN);
        int agreeablenessColor = typedArray.getColor(R.styleable.DescriptionChartView_agreeableness_color,Color.BLACK);
        int emotionalRangeColor = typedArray.getColor(R.styleable.DescriptionChartView_emotional_range_color,Color.MAGENTA);

        drawCircle(canvas, pointRow1 - align, heightAlignRow1, opennessColor, iterator.next());
        drawCircle(canvas, pointRow1 * 2 - align, heightAlignRow1, agreeablenessColor, iterator.next());
        drawCircle(canvas, pointRow1 * 3 - align, heightAlignRow1, emotionalRangeColor, iterator.next());

        drawCircle(canvas, pointRow2 - align2, heightAlignRow2, conscientiousnessColor, iterator.next());
        drawCircle(canvas, pointRow2 * 2 - align2, heightAlignRow2, extraversionColor, iterator.next());

    }

    public void drawCircle(Canvas canvas, float x, float y, int color, Map.Entry<String, Double> percent){

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        canvas.drawCircle(x,y,50, paint);

        invalidate();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawCircle(x ,y,45,paint);

        invalidate();

        int textColor = typedArray.getColor(R.styleable.DescriptionChartView_text_color, Color.BLACK);
        paint.setColor(textColor);
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.CENTER);
        Rect bound = new Rect();
        String percentString = String.valueOf(Math.round(percent.getValue()));
        paint.getTextBounds( percentString, 0, percentString.length(),bound);
        float heightText = bound.height();
        canvas.drawText(percentString,x, y + heightText/2, paint);

        invalidate();

        String personalLabel = percent.getKey();
        paint.setTextSize(40);
        paint.getTextBounds(personalLabel, 0, percentString.length(), bound);
        canvas.drawText(personalLabel,x,y + 60 + bound.height(), paint);

        invalidate();
    }
}
