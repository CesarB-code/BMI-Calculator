package com.Health.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    // Class variables also called feilds
     private TextView resultText;
   private Button calculate;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText age;
    private EditText feet;
    private EditText inches;
    private EditText weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        setupButtonClickListener();


    }



    private void  findViews(){
        
        resultText =  findViewById(R.id.text_view_result);
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        age = findViewById(R.id.edit_text_age);
        feet = findViewById(R.id.edit_text_feet);
        inches = findViewById(R.id.edit_text_inches);
        weight = findViewById(R.id.edit_text_weight);
         calculate = findViewById(R.id.button_calculate);

    }


    private void setupButtonClickListener() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ageText =  age.getText().toString();
                int age = Integer.parseInt(ageText);
                double bmiResult = calculateBMI();
                if(age>=18){
                    displayResult(bmiResult);
                }
                else {
                    displayGuidance(bmiResult);
                }

            }
        });
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFromatter = new DecimalFormat("0.00");
        String BMITextResult= myDecimalFromatter.format(bmi);

        String fullResultString;
        if (maleButton.isChecked()){
            // display boy guidance
            fullResultString = BMITextResult + "- As you are 18 , please consult with your doctor for the healthy range for boys";
        }
        else if (femaleButton.isChecked()){
            // Display girl guidance
            fullResultString = BMITextResult + "- As you are 18 , please consult with your doctor for the healthy range for girls";

        }
        else{
            // Display general guidance
            fullResultString = BMITextResult + "- As you are 18 , please consult with your doctor for the healthy range ";

        }
        resultText.setText(fullResultString);
    }

    private double calculateBMI() {

      String feetText =  feet.getText().toString();
      String inchesText =  inches.getText().toString();
      String weightText =  weight.getText().toString();

      // Coverting number strings to int variables

      int feet = Integer.parseInt(feetText);
      int inches = Integer.parseInt(inchesText);
      int weight = Integer.parseInt(weightText);

      int totalInches = (feet *12) + inches;

      // Height in meters is the inches multiplied by 0.0254
      double heightInMeters = totalInches * 0.0254;



        return  weight / (heightInMeters * heightInMeters);

    }

    private void displayResult(double BMI){
        DecimalFormat myDecimalFromatter = new DecimalFormat("0.00");
        String BMITextResult= myDecimalFromatter.format(BMI);

        String fullResultString;

        if(BMI < 18.5){
            // Display underweight
            fullResultString = BMITextResult + "- You are underweight";
        } else if (BMI>25) {
            //Display overweight
            fullResultString = BMITextResult +" - You are overweight";
        }
        else {
            //Display healthy
            fullResultString = BMITextResult + " - You are healthy weight";
        }

        resultText.setText(fullResultString);
    }



}