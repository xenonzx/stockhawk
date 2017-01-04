package com.udacity.stockhawk.ui;

import android.content.Context;

import com.udacity.stockhawk.R;

import timber.log.Timber;

/**
 * Created by ahmed on 26/12/16.
 */

public class FormatHelper {

    public String getChange(Context context, float rawAbsoluteChange) {
        String change = null;
        if (rawAbsoluteChange > 0) {
            change = String.format(context.getString(R.string.dollar_with_plus), rawAbsoluteChange);
        } else if (rawAbsoluteChange < 0) {
            change = String.format(context.getString(R.string.dollar_with_minus), Math.abs(rawAbsoluteChange));
        } else {
            change = String.format(context.getString(R.string.dollar), rawAbsoluteChange);
        }
        return change;
    }

    String getPercent(Context context, float percentageChange) {
        Timber.d("percent change " + percentageChange);

        String percentageString = null;

        if (percentageChange > 0) {
            percentageString = String.format(context.getString(R.string.percent_with_plus), percentageChange);
        } else if (percentageChange < 0) {
            percentageString = String.format(context.getString(R.string.percent_with_minus), Math.abs(percentageChange));
        } else {
            percentageString = String.format(context.getString(R.string.percent), percentageChange);
        }
        return percentageString;
    }

    public String getDollarPrice(Context context, float price) {
        Timber.d("price " + price);
        return String.format(context.getString(R.string.dollar_price), price);
    }
}
