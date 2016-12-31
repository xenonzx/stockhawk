package com.udacity.stockhawk.ui;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ahmed on 31/12/16.
 */

public class DateAxisFormatter implements IAxisValueFormatter {

    String TAG = DateAxisFormatter.class.getSimpleName();


    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        Log.v(TAG, "called getFormattedValue " + value);
        Date d = new Date((long) value);
        SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
        return ISO8601DATEFORMAT.format(d);
    }
}
