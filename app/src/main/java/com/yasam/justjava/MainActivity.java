package com.yasam.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    int mQuantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method displays the given mQuantity value on the screen.
     */
    private void display(int number) {
        TextView txtVw_quantity = (TextView) findViewById(
                R.id.txtVw_quantity);
        if (txtVw_quantity != null) {
            txtVw_quantity.setText(String.format(Locale.getDefault(), "%d", number));
        }
    }

    /**
     * This method displays the total price value for the quantity value.
     */
    private void displayPrice(int price) {
        TextView txtVw_price = (TextView) findViewById(
                R.id.txtVw_price);
        if (txtVw_price != null) {
            txtVw_price.setText(NumberFormat.getCurrencyInstance().format(price));
        }
    }

    /**
     * This method displays the total price value for the quantity value.
     */
    private void displayMessage(String priceMessage) {
        TextView txtVw_price = (TextView) findViewById(
                R.id.txtVw_price);
        if (txtVw_price != null) {
            txtVw_price.setText(priceMessage);
        }
    }

    /**
     * Decrement (minus) quantity button click event handler
     * @param view Decrement (minus) mQuantity button ref.
     */
    public void decrement(View view) {
        mQuantity--;
        display(mQuantity);
    }

    /**
     * Increment (plus) quantity button click event handler
     * @param view Increment (plus) mQuantity button ref.
     */
    public void increment(View view) {
        mQuantity++;
        display(mQuantity);
    }

    /**
     * Order button click event handler
     * @param view Order button ref.
     */
    public void submitOrder(View view) {
        int price = mQuantity * 5;
        String priceMessage = "Total: " + NumberFormat.getCurrencyInstance().format(price);
        priceMessage += "\nThank you!";

        displayMessage(priceMessage);
    }
}
