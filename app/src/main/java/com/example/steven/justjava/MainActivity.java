package com.example.steven.justjava;

import java.text.NumberFormat;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        // get the checkbox for whipped cream and its value
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // get the checkbox for chocolat and its value
        CheckBox chocolatCheckBox = (CheckBox) findViewById(R.id.chocolat);
        boolean hasChocolate = chocolatCheckBox.isChecked();

        // get the text from the name field and its value
        EditText nameEditText = (EditText) findViewById(R.id.edit_text_name);
        String name = nameEditText.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String summary = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        //displayMessage(summary);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * displays the given number on the screen
     * @param number: the number to display on the TextView with the order
     */
    private void displayQuantity(int number){
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number );
    }


//    /**
//     *
//     * @param text to be displayed
//     */
//    private void displayMessage(String text){
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(text);
//    }

    /**
     *
     * @return total price of the order
     */
    private int calculatePrice(boolean whippedCream, boolean chocolate){
        int pricePerCoffee = 5;

        if (whippedCream){
            pricePerCoffee += 1;
        }

        if (chocolate){
            pricePerCoffee += 2;
        }

        return quantity * pricePerCoffee;
    }

    /**
     * Create summary of the order
     * @param price total price of the order
     * @return summary of the order, including name, quantity, price
     */
    private String createOrderSummary(int price, boolean whippedCream, boolean chocolat, String name){
        return "Name: " + name +
                "\nAdd whipped cream? " + whippedCream +
                "\nAdd chocolat? " + chocolat +
                "\nQuantity: " + quantity +
                "\nTotal: " +  NumberFormat.getCurrencyInstance().format(price) +
                "\nThank you!";
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
        if (quantity > 0) {
            quantity--;
        } else {
            // show error message
            Toast.makeText(this,"You cannot order less than 1 coffee!",Toast.LENGTH_SHORT).show();
        }
        displayQuantity(quantity);
    }
}
