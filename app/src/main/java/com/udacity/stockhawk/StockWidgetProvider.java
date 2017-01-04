package com.udacity.stockhawk;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.udacity.stockhawk.sync.QuoteSyncJob;

/**
 * Created by ahmed on 03/01/17.
 */

public class StockWidgetProvider extends AppWidgetProvider {

    String TAG = StockWidgetProvider.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.v(TAG, "onReceive" + intent.getAction());
        if (intent.getAction().equals(QuoteSyncJob.ACTION_DATA_UPDATED)) {
            startWidgetIntentService(context);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.v(TAG, "onUpdate");
        // once on update is called will make remote views object and notify all widgets with the view through passed id -through using app widget manager-

    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.v(TAG, "onEnabled");
        startWidgetIntentService(context);
    }

    void startWidgetIntentService(Context context) {
        context.startService(new Intent(context, WidgetIntentService.class));
    }
}


