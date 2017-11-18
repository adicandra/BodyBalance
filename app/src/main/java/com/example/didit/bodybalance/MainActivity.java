package com.example.didit.bodybalance;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import java.math.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;




public class MainActivity extends AppCompatActivity {
    public EditText heightValue;
    public EditText weightValue;
    public EditText ageValue;
    public String genderValue;
    public TextView bmiValueText;
    public Button calculate;
    public  RadioButton radioMenValue;
    public  RadioGroup groupAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightValue = (EditText) findViewById(R.id.inputHeight);
        weightValue = (EditText) findViewById(R.id.inputWeight);
        ageValue    = (EditText) findViewById(R.id.inputAge);
        bmiValueText = (TextView) findViewById(R.id.textViewResult);
        calculate = (Button) findViewById(R.id.calculate);
        groupAge = (RadioGroup) findViewById(R.id.radioGroup1);





        calculate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try{
                    final float weight = Float.valueOf(weightValue.getText().toString());
                    final float height = Float.valueOf(heightValue.getText().toString());
                    final int age = Integer.valueOf(ageValue.getText().toString());
                    float bmiValue = calculateBMI(height, weight);
                    bmiValue = Math.round(bmiValue);
                    int selectedGender = groupAge.getCheckedRadioButtonId();

                    radioMenValue = (RadioButton) findViewById(selectedGender);
                    genderValue = radioMenValue.getText().toString();

                    int result = resultBMI(bmiValue,genderValue, age);
                    bmiValueText.setText(bmiValue + "kg/m2 " + getResources().getString(result));
                    Toast.makeText(MainActivity.this,genderValue,Toast.LENGTH_SHORT).show();
                }catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });


    }


    //Hitung BMI
    private float calculateBMI (float height, float weight) {
        height = height / 100;
        return (weight / (height * height));
    }

//    //hitung lemak
//    private float calculateFat(float weight, float height){
//        float db;
//        db = 1.0913 - 0.00116;
//        return (4.97/db - 4.52) * 100;
//    }

    //Representasi BMI
    private int resultBMI(float bmiValue, String genderValue, int age){
        //Laki-laki
        if (genderValue.equals("Laki-laki") && age<=60){
            if (bmiValue < 16) {
                return R.string.bmiSUnder;
            } else if (bmiValue < 18) {
                return R.string.bmiUnder;
            } else if (bmiValue <= 25) {
                return R.string.bmiNormal;
            } else if (bmiValue <= 27) {
                return R.string.bmiOver;
            } else {
                return R.string.bmiObese;
            }
        }
        //perempuan
        else{
            if (bmiValue < 16 && age<=60)  {
                return R.string.bmiSUnder;
            } else if (bmiValue < 17) {
                return R.string.bmiUnder;
            } else if (bmiValue <= 23) {
                return R.string.bmiNormal;
            } else if (bmiValue < 27) {
                return R.string.bmiOver;
            } else {
                return R.string.bmiObese;
            }
        }

    }
}
