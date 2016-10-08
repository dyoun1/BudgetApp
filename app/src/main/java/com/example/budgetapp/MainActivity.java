package com.example.budgetapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private PieChart mChart;
    private float[] yValue = {5,10,15,30,40};
    private String[] xValue = {"area1", "area2", "area3", "area4", "area5"};

    // colors for different sections in pieChart
    public static  final int[] MY_COLORS = {
            Color.rgb(84,124,101), Color.rgb(64,64,64), Color.rgb(153,19,0),
            Color.rgb(38,40,53), Color.rgb(215,60,55)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mChart = (PieChart) findViewById(R.id.chart1);

        mChart.setDescription("");
        mChart.setRotationEnabled(true);
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if(e==null)
                    return;

                Toast.makeText(MainActivity.this,
                        xValue[e.getXIndex()] +" is "+e.getVal() + "",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        setDataForPieChart();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void setDataForPieChart(){
        ArrayList<Entry> yVal = new ArrayList<Entry>();

        for(int i=0; i<yValue.length; i++)
            yVal.add(new Entry(yValue[i],i));

        ArrayList<String> xVal = new ArrayList<String>();
        for (int i = 0; i < xValue.length; i++)
            xVal.add(xValue[i]);

        //create pieDataSet
        PieDataSet dataSet = new PieDataSet(yVal, "");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for(int c: MY_COLORS)
            colors.add(c);

        dataSet.setColors(colors);

        //  create pie data object and set xValues and yValues and set it to the pieChart
        PieData data = new PieData(xVal, dataSet);
        //   data.setValueFormatter(new DefaultValueFormatter());
        //   data.setValueFormatter(new PercentFormatter());

        data.setValueFormatter(new MyValueFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // refresh/update pie chart
        mChart.invalidate();

        // animate pie chart
        mChart.animateXY(1400, 1400);


        // Legends to show on bottom of the graph
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }

    public class MyValueFormatter implements ValueFormatter{
        private DecimalFormat mFormat;

        public MyValueFormatter(){
            mFormat = new DecimalFormat("###,###,##0");
        }
        @Override
        public String getFormattedValue(float value, Entry e, int dataSetIndex,
                                        ViewPortHandler viewPortHandler){
            return mFormat.format(value) +"";
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RelativeLayout main_view = (RelativeLayout) findViewById(R.id.content_main);

        switch(item.getItemId()){
            case R.id.menu_home:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                return true;
            case R.id.menu_report:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                return true;
            case R.id.menu_add:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                return true;
            case R.id.menu_extra1:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                return true;
            case R.id.menu_extra2:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
