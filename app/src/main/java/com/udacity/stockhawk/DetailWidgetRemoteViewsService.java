package com.udacity.stockhawk;

import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.stockhawk.data.Contract;
import com.udacity.stockhawk.data.PrefUtils;
import com.udacity.stockhawk.ui.FormatHelper;

/**
 * Created by ahmed on 04/01/17.
 */

public class DetailWidgetRemoteViewsService extends RemoteViewsService {
    String TAG = DetailWidgetRemoteViewsService.class.getSimpleName();

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewsFactory() {
            private Cursor data = null;

            @Override
            public void onCreate() {
                Log.v(TAG, "onCreate");
            }

            @Override
            public void onDataSetChanged() {
                Log.v(TAG, "onDataSetChanged");
                if (data != null) {
                    data.close();
                }
                //AS FOUND IN THE Advanced_Android_Development REPO
                // This method is called by the app hosting the widget (e.g., the launcher)
                // However, our ContentProvider is not exported so it doesn't have access to the
                // data. Therefore we need to clear (and finally restore) the calling identity so
                // that calls use our process and permission
                final long identityToken = Binder.clearCallingIdentity();
                data = getContentResolver().query(Contract.Quote.URI,
                        Contract.Quote.QUOTE_COLUMNS,
                        null, null, Contract.Quote.COLUMN_SYMBOL);
                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                if (data != null) {
                    data.close();
                    data = null;
                }
            }

            @Override
            public int getCount() {


                return data == null ? 0 : data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                Log.v(TAG, "position");

                if (position == AdapterView.INVALID_POSITION ||
                        data == null || !data.moveToPosition(position)) {
                    return null;
                }
                String stockSymbol = data.getString(Contract.Quote.POSITION_SYMBOL);
                float price = data.getFloat(Contract.Quote.POSITION_PRICE);
                float rawAbsoluteChange = data.getFloat(Contract.Quote.POSITION_ABSOLUTE_CHANGE);
                float percentageChange = data.getFloat(Contract.Quote.POSITION_PERCENTAGE_CHANGE);

                RemoteViews views = new RemoteViews(getPackageName(), R.layout.list_item_quote);
                views.setTextViewText(R.id.symbol, stockSymbol);
                views.setTextViewText(R.id.price, new FormatHelper().getDollarPrice(DetailWidgetRemoteViewsService.this, price));
                if (rawAbsoluteChange > 0) {
                    views.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_green);

                } else {
                    views.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_red);
                }
                if (PrefUtils.getDisplayMode(DetailWidgetRemoteViewsService.this).equals(getString(R.string.pref_display_mode_absolute_key))) {
                    views.setTextViewText(R.id.change, new FormatHelper().getChange(DetailWidgetRemoteViewsService.this, rawAbsoluteChange));
                } else {
                    views.setTextViewText(R.id.change, new FormatHelper().getChange(DetailWidgetRemoteViewsService.this, percentageChange));
                }
                return views;
            }


            @Override
            public RemoteViews getLoadingView() {
                return null;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (data.moveToPosition(position))
                    return data.getLong(Contract.Quote.POSITION_ID);
                return position;
            }

            @Override
            public boolean hasStableIds() {
                //TODO
                return false;
            }
        };
    }
}
