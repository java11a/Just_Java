package com.yasam.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
     * Decrement (minus) quantity button click event handler
     * @param view Decrement (minus) mQuantity button ref.
     */
    public void decrement(View view) {
        if(mQuantity==QUANTITY_MIN)
            Toast.makeText(this, getString(R.string.err_msg_decrement, getResources().getQuantityString(R.plurals.numOfCoffeeCups, QUANTITY_MIN, QUANTITY_MIN)), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, getString(R.string.err_msg_increment, getResources().getQuantityString(R.plurals.numOfCoffeeCups, QUANTITY_MAX, QUANTITY_MAX)), Toast.LENGTH_SHORT).show();
        else
            mQuantity++;

        display(mQuantity);
    }

    /**
     * Order button click event handler
     * @param view Order button ref.
     */
    public void submitOrder(View view) {

        String name = null;
        boolean hasWhippedCream = false;
        boolean hasChocolate =false;

        //
        EditText txtEd_name = (EditText) findViewById(R.id.txtEd_name);
        if(txtEd_name!=null)
            name = txtEd_name.getText().toString();

        //Figure out if the user wants whipped cream topping
        CheckBox chkBx_whipedCream = (CheckBox) findViewById(R.id.chkBx_whippedCream);
        if(chkBx_whipedCream!=null)
            hasWhippedCream = chkBx_whipedCream.isChecked();

        //Figure out if the user wants chocolate topping
        CheckBox chkBx_chocolate = (CheckBox) findViewById(R.id.chkBx_chocolate);
        if(chkBx_chocolate!=null)
            hasChocolate = chkBx_chocolate.isChecked();

        int price = calculatePrice(mQuantity, hasWhippedCream, hasChocolate);

        String orderSummaryMsg = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent orderSummaryIntent = new Intent(Intent.ACTION_SENDTO);
        orderSummaryIntent.setData(Uri.parse("mailto:"));
        orderSummaryIntent.putExtra(Intent.EXTRA_SUBJECT
                ,getString(R.string.order_summary_email_subject, getApplicationInfo().loadLabel(getPackageManager()).toString(), name));
        orderSummaryIntent.putExtra(Intent.EXTRA_TEXT, orderSummaryMsg);

        if(orderSummaryIntent.resolveActivity(getPackageManager()) != null){
            startActivity(orderSummaryIntent);
        }
    }

    /**
     * Calculates total price of the order
     *
     * @param quantity of the order
     * @param addWhippedCream to add the whipped cream
     * @param addChocolate to add the chocolate
     * @return text summary
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
     * @param name customer name
     * @param price of the order
     * @param addWhippedCream to add the whipped cream
     * @param addChocolate to add the chocolate
     * @return text summary
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        StringBuilder strBldr = new StringBuilder();

        if(name!=null)
            strBldr.append(getString(R.string.order_summary_name, name));
        
        strBldr.append(getString(R.string.order_summary_whipped_cream, getString(addWhippedCream ? R.string.answer_yes : R.string.answer_no)));
        strBldr.append(getString(R.string.order_summary_chocolate, getString(addChocolate ? R.string.answer_yes : R.string.answer_no)));
        strBldr.append(getString(R.string.order_summary_quantity, getResources().getQuantityString(R.plurals.numOfCoffeeCups, mQuantity, mQuantity)));
        strBldr.append(getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(price)));
        strBldr.append(getString(R.string.thank_you));
        return strBldr.toString();
    }
}
