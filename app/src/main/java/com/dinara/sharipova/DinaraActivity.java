package com.dinara.sharipova;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DinaraActivity extends AppCompatActivity {
    private StockViewModel mViewModel;
    TextView txtStockName, txtStockQuote;
    String stock_selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(getResources().getText(R.string.title_activity_main));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinara);
        mViewModel = new ViewModelProvider(this).get(StockViewModel.class);
        txtStockName = findViewById(R.id.txtStockName);
        txtStockQuote = findViewById(R.id.txtStockQuote);
    }

    public void insertStocksClick(View view) {
        StockInfo stock1 = new StockInfo("AAPL", "Apple Inc", 123.51);
        mViewModel.insertStock(stock1);
        StockInfo stock2 = new StockInfo("MSFT", "Microsoft Corporation", 211.98);
        mViewModel.insertStock(stock2);
        StockInfo stock3 = new StockInfo("TSLA", "Tesla Inc", 621.87);
        mViewModel.insertStock(stock3);
        Toast.makeText(this, "Stocks are inserted",Toast.LENGTH_SHORT).show();

    }

    public void display_click(View view) {
        final RadioGroup group= (RadioGroup) findViewById(R.id.stocks_group);

        int id_radio = group.getCheckedRadioButtonId();

        switch (id_radio) {
            case R.id.AAPL_button:
                stock_selected="AAPL";
                break;
            case R.id.MSFT_button:
                stock_selected="MSFT";
                break;
            case R.id.TSLA_button:
                stock_selected="TSLA";
                break;
            default: stock_selected="AAPL";
            break;

        }
        mViewModel.getStockBySymbol(stock_selected).observe(this,
                new Observer<List<StockInfo>>() {
                    @Override
                    public void onChanged(@Nullable final List<StockInfo> stocks) {

                        for (StockInfo stock : stocks) {

                            txtStockName.setText("Company Name: " + String.valueOf(stock.getCompanyName()));
                            txtStockQuote.setText("Stock Quote: " + String.valueOf(stock.getStockQuote()));
                            String StockName = String.valueOf(stock.getCompanyName());
                            String StockQuote = String.valueOf(stock.getStockQuote());
                            Intent intent = new Intent(DinaraActivity.this, StockBroadcastReceiver.class);
                            intent.putExtra("STOCK_INFO", "Stock Info received:\nCompany name: "+StockName+"\nStock Quote: "+StockQuote);
                            sendBroadcast(intent);
                        }
                        
                      
                    }
                });

    }
}
