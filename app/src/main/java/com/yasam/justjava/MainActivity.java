package com.yasam.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView txtVw_quantity = (TextView) findViewById(
                R.id.txtVw_quantity);
        if (txtVw_quantity != null) {
            txtVw_quantity.setText(String.format(Locale.getDefault(), "%d", number));
        }
    }

    /**
     * This method displays on the screen the total price value for the quantity value.
     */
    private void displayPrice(int price) {
        TextView txtVw_price = (TextView) findViewById(
                R.id.txtVw_price);
        if (txtVw_price != null) {
            txtVw_price.setText(NumberFormat.getCurrencyInstance().format(price));
        }
    }

    public void submitOrder(View view) {
        int quantity = 3;
        display(quantity);

        displayPrice(quantity * 5);
    }
}
