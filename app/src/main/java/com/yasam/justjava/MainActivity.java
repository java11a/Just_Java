package com.yasam.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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
     * This method displays the order summary.
     */
    private void displayMessage(String orderSummaryMsg) {
        TextView txtVw_orderSummary = (TextView) findViewById(
                R.id.txtVw_orderSummary);
        if (txtVw_orderSummary != null) {
            txtVw_orderSummary.setText(orderSummaryMsg);
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

        CheckBox chkBx_whipedCream = (CheckBox) findViewById(R.id.chkBx_whippedCream);
        boolean hasWhippedCream = chkBx_whipedCream.isChecked();
        Log.v(this.getClass().getSimpleName(), "Has whipped cream: " + hasWhippedCream);

        CheckBox chkBx_chocolate = (CheckBox) findViewById(R.id.chkBx_chocolate);
        boolean hasChocolate = chkBx_chocolate.isChecked();
        Log.v(this.getClass().getSimpleName(), "Has chocolate: " + hasChocolate);

        int price = calculatePrice(mQuantity, hasWhippedCream, hasChocolate);

        String orderSummaryMsg = createOrderSummary(price, hasWhippedCream, hasChocolate);
        displayMessage(orderSummaryMsg);
    }

    /**
     * Calculates total price of the order
     *
     * @param quantity of the order
     * @param addWhippedCream
     * @param addChocolate  @return text summary
     */
    private int calculatePrice(int quantity, boolean addWhippedCream, boolean addChocolate) {
        final int PRICE_COFFEE_CUP = 5;
        final int PRICE_CHOCOLATE = 2;
        final int PRICE_WHIPPER_CREAM = 1;

        int price = PRICE_COFFEE_CUP;

        //Add if the user wants whipper cream
        if(addWhippedCream)
            price += PRICE_WHIPPER_CREAM;

        //Add if the user wants chocolate
        if(addChocolate)
            price += PRICE_CHOCOLATE;

        return price * quantity;
    }

    /**
     * Creates summary of the order
     *
     * @param price of the order
     * @param addWhippedCream
     * @param addChocolate  @return text summary
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate) {
        String orderSummaryMsg = "Name: Yuri Granovsky";
        orderSummaryMsg += "\nAdd whipped cream? " + addWhippedCream;
        orderSummaryMsg += "\nAdd chocolate? " + addChocolate;
        orderSummaryMsg += "\nQuantity: " + mQuantity;
        orderSummaryMsg += "\nTotal: " + NumberFormat.getCurrencyInstance().format(price);
        orderSummaryMsg += "\nThank you!";
        return orderSummaryMsg;
    }
}
