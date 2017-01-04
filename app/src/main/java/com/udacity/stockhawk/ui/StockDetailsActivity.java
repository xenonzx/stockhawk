package com.udacity.stockhawk.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.udacity.stockhawk.R;

/**
 * Created by ahmed on 24/12/16.
 */

public class StockDetailsActivity extends AppCompatActivity {
    public static String PASSED_STOCK_SYMBOL_KEY = "symbol";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_details);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, getStockDetailsFragment()).commit();
    }

    StockDetailsFragment getStockDetailsFragment() {
        StockDetailsFragment reFragment;
        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey(PASSED_STOCK_SYMBOL_KEY)) {
            reFragment = StockDetailsFragment.newInstance(getIntent().getExtras().getString(PASSED_STOCK_SYMBOL_KEY));
        } else {
            reFragment = new StockDetailsFragment();
        }
        return reFragment;
    }
}
