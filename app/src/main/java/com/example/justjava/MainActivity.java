package com.example.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameOfUser = (EditText) findViewById(R.id.name);
        String gotUserName = nameOfUser.getText().toString();
        CheckBox whipedDecision = (CheckBox) findViewById(R.id.whippied);
        boolean WhipedInTrue = whipedDecision.isChecked();
        CheckBox choclateStore = (CheckBox) findViewById(R.id.choclate);
        boolean choclatetrue = choclateStore.isChecked();
        Button afterPressed = (Button) findViewById(R.id.afterPressed);
        afterPressed.setText("Hurry Order Placed");
        String addresses = "udacity@gmail.com";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL,addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Coffe " + nameOfUser);
        intent.putExtra(Intent.EXTRA_TEXT,createOrderSummary(gotUserName,WhipedInTrue,choclatetrue));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }



    }
    /**
     * defining createORderSummery mthod
     */
    public String createOrderSummary(String name, boolean Whipped, boolean choclate) {

        return "Name: " + name +
                "\n Quantity: " + quantity +
                "\n Whippied Required: " + Whipped +
                "\n Choclate Required: " + choclate +
                "\n Total: " + calcualtePrice(Whipped,choclate) +
                "\n Thank you";
    }
    /**
     * calculate pric emethod
     */
    public int calcualtePrice(boolean whippedPrice, boolean chochlatePrice) {
            int whipPrice = 4;
            int chocPrice  = 2;
            int price  = 5 ;
        if (whippedPrice){
             price = price + whipPrice;
        } if (chochlatePrice){
             price = price+chocPrice;
        }
         price = quantity * price;

        return price;
    }

//    /**
//     * This method displays the given text on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_smmary_text);
//        orderSummaryTextView.setText(message);
//    }

    /**
     * this methods increment
     */
    public void increment(View view) {
        quantity = quantity + 1;
        if (quantity <= 100) {
            display(quantity);
        }else {
            TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
            quantityTextView.setText("100" );
            Toast.makeText(this,"Maximum 100",  Toast.LENGTH_LONG).show();
            return;

        }
    }

    /**
     * this decrement the quanityt
     */

    public void decrement(View view) {
        quantity = quantity - 1;
        if ( quantity > 1) {
            display(quantity);
        }else {
            Toast.makeText(this,"Minimum 1",  Toast.LENGTH_LONG).show();
            return;


        }
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}