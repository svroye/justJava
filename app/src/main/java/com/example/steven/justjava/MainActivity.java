package com.example.steven.justjava;

import java.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *  called when the order button is clicked
     * @param view: the view to update
     */
    public void submitOrder(View view){
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
        boolean whippedCream = whippedCreamCheckBox.isChecked();
        int price = calculatePrice();
        String summary = createOrderSummary(price, whippedCream);
        displayMessage(summary);
    }

    /**
     * displays the given number on the screen
     * @param number: the number to display on the TextView with the order
     */
    private void displayQuantity(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number );
    }


    /**
     *
     * @param text to be displayed
     */
    private void displayMessage(String text){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(text);
    }

    /**
     *
     * @return total price of the order
     */
    private int calculatePrice(){
        int price = quantity * 5;
        return price;
    }

    /**
     * Create summary of the order
     * @param price total price of the order
     * @return summary of the order, including name, quantity, price
     */
    private String createOrderSummary(int price, boolean whippedCream){
        String summary = "Name: Steven Van Roye" +
                "\nAdd whipped cream? " + whippedCream +
                "\nQuantity: " + quantity +
                "\nTotal: " +  NumberFormat.getCurrencyInstance().format(price) +
                "\nThank you!";
        return summary;
    }

    /**
     * increments the number of coffees when the + button is clicked
     * @param v
     */
    public void increment(View v){
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * decrements the number of coffees when the - button is pressed
     * @param v
     */
    public void decrement(View v){
        quantity--;
        displayQuantity(quantity);
    }
}
