package com.udacity.stockhawk.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.stockhawk.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 24/12/16.
 */

public class StockDetailsFragment extends Fragment {
    private static final int QUOTE_LOADER = 0;
    private static final String ARG_STOCK_SYMBOL = "symbol";
    String mStockSymbol;

    @BindView(R.id.tv_stock_symbol)
    TextView stockSymbol;

    public static StockDetailsFragment newInstance(String param1) {
        StockDetailsFragment fragment = new StockDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STOCK_SYMBOL, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARG_STOCK_SYMBOL)) {
            mStockSymbol = getArguments().getString(ARG_STOCK_SYMBOL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stock_details, container, false);
        ButterKnife.bind(this, view);
        if (mStockSymbol != null) {
            stockSymbol.setText(mStockSymbol);
        }
        return view;
    }
}
