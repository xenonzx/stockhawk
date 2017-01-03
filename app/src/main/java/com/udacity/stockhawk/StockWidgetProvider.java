package com.udacity.stockhawk;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * Created by ahmed on 03/01/17.
 */

public class StockWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // once on update is called will make remote views object and notify all widgets with the view through passed id -through using app widget manager-
        for (int i = 0; i < appWidgetIds.length; i++) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
            appWidgetManager.updateAppWidget(appWidgetIds[i], views);

        }
    }
}
