package com.udacity.stockhawk.ui;

import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ahmed on 31/12/16.
 */

public class DateAxisFormatter implements IValueFormatter {

    String TAG = DateAxisFormatter.class.getSimpleName();

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        Log.v(TAG, "called getFormattedValue " + value + new Gson().toJson(entry));
        Date d = new Date((long) value);
        SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        return ISO8601DATEFORMAT.format(d);
    }
}
