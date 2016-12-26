package com.udacity.stockhawk.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;
import com.udacity.stockhawk.data.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 24/12/16.
 */

public class StockDetailsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int QUOTE_LOADER = 0;
    private static final String ARG_STOCK_SYMBOL = "symbol";
    String mStockSymbol;
    @BindView(R.id.tv_stock_symbol)
    TextView symbol;

    @BindView(R.id.tv_stock_price)
    TextView price;

    @BindView(R.id.tv_stock_absolute_change)
    TextView change;

    @BindView(R.id.tv_history)
    TextView history;

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
            symbol.setText(mStockSymbol);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(QUOTE_LOADER, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                Contract.Quote.makeUriForStock(mStockSymbol),
                Contract.Quote.QUOTE_COLUMNS,
                null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        updateUI(data);

    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    void updateUI(Cursor cursor) {
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            symbol.setText(cursor.getString(Contract.Quote.POSITION_SYMBOL));
            price.setText(new FormatHelper().getDollarPrice(getActivity(), cursor.getFloat(Contract.Quote.POSITION_PRICE)));
            history.setText(cursor.getString(Contract.Quote.POSITION_HISTORY));

            float rawAbsoluteChange = cursor.getFloat(Contract.Quote.POSITION_ABSOLUTE_CHANGE);
            float percentageChange = cursor.getFloat(Contract.Quote.POSITION_PERCENTAGE_CHANGE);

            if (rawAbsoluteChange > 0) {
                change.setBackgroundResource(R.drawable.percent_change_pill_green);
            } else {
                change.setBackgroundResource(R.drawable.percent_change_pill_red);
            }

            String changeString = new FormatHelper().getChange(getActivity(), rawAbsoluteChange);
            String percentageString = new FormatHelper().getPercent(getActivity(), percentageChange);
            if (PrefUtils.getDisplayMode(getActivity()).equals(getActivity().getString(R.string.pref_display_mode_absolute_key))) {
                change.setText(changeString);
            } else {
                change.setText(percentageString);
            }
        }

    }

}
