package com.yasam.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    final int QUANTITY_MIN = 1;
    final int QUANTITY_MAX = 100;
    int mQuantity = QUANTITY_MIN;

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
        if(mQuantity==QUANTITY_MIN)
            Toast.makeText(this, "You cannot order less than " + QUANTITY_MIN + " coffee cup", Toast.LENGTH_SHORT).show();
        else
            mQuantity--;

        display(mQuantity);
    }

    /**
     * Increment (plus) quantity button click event handler
     * @param view Increment (plus) mQuantity button ref.
     */
    public void increment(View view) {
        if(mQuantity==QUANTITY_MAX)
            Toast.makeText(this, "You cannot order more than " + QUANTITY_MAX + " coffees cups", Toast.LENGTH_SHORT).show();
        else
            mQuantity++;

        display(mQuantity);
    }

    /**
     * Order button click event handler
     * @param view Order button ref.
     */
    public void submitOrder(View view) {
        EditText txtEd_name = (EditText) findViewById(R.id.txtEd_name);
        String name = txtEd_name.getText().toString();
        Log.v(getClass().getSimpleName(), "Name: " + name);

        //Figure out if the user wants whipped cream topping
        CheckBox chkBx_whipedCream = (CheckBox) findViewById(R.id.chkBx_whippedCream);
        boolean hasWhippedCream = chkBx_whipedCream.isChecked();
        Log.v(getClass().getSimpleName(), "Has whipped cream: " + hasWhippedCream);

        //Figure out if the user wants chocolate topping
        CheckBox chkBx_chocolate = (CheckBox) findViewById(R.id.chkBx_chocolate);
        boolean hasChocolate = chkBx_chocolate.isChecked();
        Log.v(getClass().getSimpleName(), "Has chocolate: " + hasChocolate);

        int price = calculatePrice(mQuantity, hasWhippedCream, hasChocolate);

        String orderSummaryMsg = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
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
     * @param name
     * @param price of the order
     * @param addWhippedCream
     * @param addChocolate  @return text summary
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String orderSummaryMsg = "Name: " + name;
        orderSummaryMsg += "\nAdd whipped cream? " + addWhippedCream;
        orderSummaryMsg += "\nAdd chocolate? " + addChocolate;
        orderSummaryMsg += "\nQuantity: " + mQuantity;
        orderSummaryMsg += "\nTotal: " + NumberFormat.getCurrencyInstance().format(price);
        orderSummaryMsg += "\nThank you!";
        return orderSummaryMsg;
    }
}
