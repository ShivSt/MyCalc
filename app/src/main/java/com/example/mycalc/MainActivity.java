package com.example.mycalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView input; //=findViewById(R.id.tVInput);
    TextView answer; //=findViewById(R.id.tVAnswer);
    Button add, subtract, multiply, divide,percent;
    Button clear, delete,equals;
    Button zero, one, two, three, four, five, six, seven, eight, nine, point, more;

    //to decide what operation is to be performed
    int operatorCount=0;
    String operator="";
    StringBuilder firstVal=new StringBuilder("0");
    StringBuilder secondVal=new StringBuilder();
    boolean enter=false;
    float result=0;
    
    //Getting string values from input and answer TextView
    public String getString(TextView tv){
        return tv.getText().toString();
    }


    //Defining the component classes which will perform the actions
    //Operation operate=new Operation();

    //Operation based on operator:
    public float operation(String operator, StringBuilder firstVal, StringBuilder secondVal){
        try{
            switch (operator) {
                case "a":
                    return (Float.parseFloat(firstVal.toString()) + Float.parseFloat(secondVal.toString()));
                case "s":
                    return (Float.parseFloat(firstVal.toString()) - Float.parseFloat(secondVal.toString()));
                case "m":
                    return (Float.parseFloat(firstVal.toString()) * Float.parseFloat(secondVal.toString()));
                case "d":
                    return (Float.parseFloat(firstVal.toString()) / Float.parseFloat(secondVal.toString()));
                default:
                    return Float.parseFloat(firstVal.toString());
            }
        }
        catch (Exception e){
            return Float.parseFloat(firstVal.toString());
        }


    }

    //Setter function to set value 0,1,2...based on respective button
    public void setValue(String val){

        if(!operator.equals("")){
            if (!secondVal.toString().equals("0")) {
                secondVal.append(val);
                input.setText(getString(input)+val);
            }
            else{
                secondVal.setLength(0);
                secondVal.append(val);
                int length=getString(input).length();
                input.setText(getString(input).substring(0,length-1)+val);
            }

            result=operation(operator,firstVal,secondVal);
            answer.setText("=" + Float.toString(result));
        }
        else {
            if (enter){
                clear.performClick();
            }
            if (!firstVal.toString().equals("0")) {
                firstVal.append(val);
                input.setText(getString(input) + val);
            }
            else{
                firstVal.setLength(0);
                firstVal.append(val);
                int length=getString(input).length();
                if(length>0){
                    input.setText(getString(input).substring(0,length-1)+val);
                }
                else{
                    input.setText(val);
                }
            }
            answer.setText("="+firstVal.toString());
            enter = false;
        }
    }

    public void arithmeticOperator(String op){
        String sign = null;
        switch (op) {
            case "a":
                sign = "\u002b";
                break;
            case "s":
                sign = "\u2212";
                break;
            case "m":
                sign = "\u00d7";
                break;
            case "d":
                sign = "\u00f7";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + op);
        }


        if(getString(input).matches(".*[\\u002b\\u2212\\u00d7\\u00f7]$")) {

            int length=getString(input).length();
            input.setText(getString(input).substring(0,length-1) + sign);

        }
        else {
            input.setText(getString(input) + sign);
            secondVal.setLength(0);
            if (operatorCount!=0){
                firstVal.setLength(0);
                firstVal.append(Float.toString(result));
            }
            operatorCount+=1;
        }
    }

    //It helps in handling decimal values
    //DecimalFormat df=new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide the toolbar
        getSupportActionBar().hide();


        //Views binding to their Ids:

        input =findViewById(R.id.tVInput);
        answer =findViewById(R.id.tVAnswer);
        clear =findViewById(R.id.btClear);
        delete =findViewById(R.id.btDelete);
        percent =findViewById(R.id.btPercent);
        divide= findViewById(R.id.btDivide);
        seven= findViewById(R.id.btSeven);
        eight= findViewById(R.id.btEight);
        nine= findViewById(R.id.btNine);
        multiply= findViewById(R.id.btMultiply);
        four= findViewById(R.id.btFour);
        five= findViewById(R.id.btFive);
        six= findViewById(R.id.btSix);
        subtract= findViewById(R.id.btSubtract);
        one= findViewById(R.id.btOne);
        two= findViewById(R.id.btTwo);
        three= findViewById(R.id.btThree);
        add= findViewById(R.id.btPlus);
        more= findViewById(R.id.btMore);
        zero= findViewById(R.id.btZero);
        point= findViewById(R.id.btPoint);
        equals= findViewById(R.id.btEquals);


        //Defining methods for all buttons
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator="a";
                arithmeticOperator(operator);

            }
        });
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                operator="s";
                arithmeticOperator(operator);

            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator="m";
                arithmeticOperator(operator);

            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator="d";
                arithmeticOperator(operator);

            }
        });
        /*
        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator="p";

            }
        }); */
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input.setText(null);
                answer.setText("0");
                firstVal.setLength(0);firstVal.append("0");
                secondVal.setLength(0);
                operator="";
                operatorCount=0;
                enter=false;
                result=0;
            }
        });

        //Backspace button
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int length=getString(answer).length();
                if (length>2){

                    if(!secondVal.toString().equals("")){

                        secondVal.setLength(secondVal.length()-1);
                        equals.performClick();
                    }
                    else if(!firstVal.toString().equals("")){

                        firstVal.setLength(firstVal.length()-1);
                        equals.performClick();
                    }
				    else{
                        clear.performClick();
                    }
                }
                else{
                    clear.performClick();
                }

            }
        });
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enter){

                }
                else{
                    if (!getString(input).matches(".*[+\\-*/%]$") ) {

                        result = operation(operator,firstVal,secondVal);
                        answer.setText("=" + Float.toString(result));
                        operator="";
                        enter=true;
                        //result=0;
                    }
                    else {
                        result = operation(operator,firstVal,secondVal);
                        answer.setText("=" + Float.toString(result));

                    }

                }

            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setValue("0");
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            setValue("1");

            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setValue("2");

            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setValue("3");

            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setValue("4");

            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setValue("5");

            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setValue("6");

            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setValue("7");

            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setValue("8");

            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setValue("9");

            }
        });

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setValue(".");

            }
        });

    }
}
