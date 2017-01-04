package com.udacity.stockhawk;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by ahmed on 04/01/17.
 */

public class WidgetIntentService extends IntentService {

    public WidgetIntentService() {
        super(WidgetIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
