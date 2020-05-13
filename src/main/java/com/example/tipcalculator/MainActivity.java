package com.example.tipcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    private EditText enteredAmount;
    private SeekBar seekBar;
    private Button calculateButton;
    private TextView textViewSeekBar;
    private TextView ResultTextView;
    private TextView totalBillTextView;
   private   int seekBarPercentage; // to store the value of seekBar Progress ;
   // private  float enteredFloat;  // store the value of enteredAmount
    //----------------
    private AlertDialog.Builder alertDialog;
    private Button showExitDialogButton;
//---------------------------
int seekBarValue = 0;  // new
    private  Button clearButton;

      @Override
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         enteredAmount = (EditText) findViewById(R.id.billAmountID);
         seekBar = (SeekBar) findViewById(R.id.percentageSeekBar);
         calculateButton = (Button) findViewById(R.id.calculateButton);
        ResultTextView = (TextView) findViewById(R.id.resultID);
        textViewSeekBar = (TextView) findViewById(R.id.textViewSeekBar);
        totalBillTextView = (TextView) findViewById(R.id.totalBillTextView);
        showExitDialogButton = (Button) findViewById(R.id.ExitButton);
         showExitDialogButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
        // show the actual dialog (alert dialog)
        alertDialog = new AlertDialog.Builder(MainActivity.this);

        //set things up  - set title
        alertDialog.setTitle(R.string.title);
        alertDialog.setIcon(android.R.drawable.btn_star_big_on);

        //  - set message
        alertDialog.setMessage(R.string.message);

        // -  set Cancel label
        alertDialog.setCancelable(false);

        //   - set positive button
        alertDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //exit our window activity
                MainActivity.this.finish();
            }
        });

        //  - set negative button
        alertDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // stay in our window activity
                dialog.cancel();
            }
        });


        // create the actual dialog
        AlertDialog d = alertDialog.create();

        //show the dialog
        d.show();
         }
       });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              //  textViewSeekBar.setText(Integer.toString(seekBar.getProgress())+ "%");
                seekBarValue = progress; //new
                textViewSeekBar.setText(Integer.toString(seekBarValue) + "%"); // new
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            //  seekBarPercentage = seekBar.getProgress();
                seekBarPercentage = seekBarValue; // new
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });


        clearButton =(Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultTextView.setText("");
                totalBillTextView.setText("");
                enteredAmount.setText("");
                seekBar.setProgress(0);
                textViewSeekBar.setText("");



            }
        });

    }

    public void calculate()
    {
        float result = 0.0f;
        if(!enteredAmount.getText().toString().equals(""))
        {
          float enteredFloat =Float.parseFloat(enteredAmount.getText().toString());
            result = enteredFloat*seekBarPercentage/100;
            ResultTextView.setText("Your Tip Will Be  " +  " $" + Float.toString(result));

            totalBillTextView.setText("Total bill Will Be " + " $ " + Float.toString(enteredFloat+result) );
        }
        else
        {
            Toast.makeText(MainActivity.this , "Please Enter The bill ." , Toast.LENGTH_LONG).show();

        }
    }

}
