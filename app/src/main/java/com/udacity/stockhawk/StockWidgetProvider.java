package com.udacity.stockhawk;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.udacity.stockhawk.ui.MainActivity;

/**
 * Created by ahmed on 03/01/17.
 */

public class StockWidgetProvider extends AppWidgetProvider {
    int REQUEST_CODE = 0;
    int NO_FLAGS = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // once on update is called will make remote views object and notify all widgets with the view through passed id -through using app widget manager-
        for (int i = 0; i < appWidgetIds.length; i++) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
            views.setOnClickPendingIntent(R.id.tv_widget_header, getMainActivityIntent(context));
            appWidgetManager.updateAppWidget(appWidgetIds[i], views);

        }
    }

    PendingIntent getMainActivityIntent(Context cxt) {
        Intent i = new Intent(cxt, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(cxt, REQUEST_CODE, i, NO_FLAGS);
        return pi;
    }
}
