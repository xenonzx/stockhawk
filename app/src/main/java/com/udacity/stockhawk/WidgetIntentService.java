package com.udacity.stockhawk;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.udacity.stockhawk.ui.MainActivity;

/**
 * Created by ahmed on 04/01/17.
 */

public class WidgetIntentService extends IntentService {
    private static final String TAG = WidgetIntentService.class.getSimpleName();
    int REQUEST_CODE = 0;
    int NO_FLAGS = 0;

    public WidgetIntentService() {
        super(WidgetIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v(TAG, "onHandleIntent");
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, StockWidgetProvider.class));
        for (int i = 0; i < appWidgetIds.length; i++) {
            RemoteViews views = new RemoteViews(getPackageName(), R.layout.layout_widget);
            views.setOnClickPendingIntent(R.id.tv_widget_header, getMainActivityIntent(this));
            appWidgetManager.updateAppWidget(appWidgetIds[i], views);

        }
    }

    PendingIntent getMainActivityIntent(Context cxt) {
        Intent i = new Intent(cxt, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(cxt, REQUEST_CODE, i, NO_FLAGS);
        return pi;
    }
}

