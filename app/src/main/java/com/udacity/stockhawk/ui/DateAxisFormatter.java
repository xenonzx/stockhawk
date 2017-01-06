package com.udacity.stockhawk.ui;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ahmed on 31/12/16.
 */

public class DateAxisFormatter implements IAxisValueFormatter, IValueFormatter {

    String TAG = DateAxisFormatter.class.getSimpleName();
    //the long of date of 2015 used to be subtracted from values too big  to be converted as float and this should be the solution to errors of converting float to long
    public static long REFRENCE_VALUE = getRefrenceValue();
    public static final int YEARS_OF_HISTORY = 3;

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
        Log.v(TAG, "called getFormattedValue " + value);
        Date d = new Date(((long) value * 1000L)+ REFRENCE_VALUE);
        return ISO8601DATEFORMAT.format(d);
    }

    static long getRefrenceValue() {

        Calendar now = Calendar.getInstance();

        now.add(Calendar.YEAR, -YEARS_OF_HISTORY);

        return now.getTimeInMillis();
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
        Log.v(TAG, "called getFormattedValue " + value);
        Date d = new Date(((long) value * 1000L)+ REFRENCE_VALUE);
        return ISO8601DATEFORMAT.format(d);
    }
}
