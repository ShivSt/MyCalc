package com.example.mycalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView input, answer;
    Button add, subtract, multiply, divide,percent;
    Button clear, delete,equals;
    Button zero, one, two, three, four, five, six, seven, eight, nine, point, more;

    //to decide what operation is to be performed
    int operatorCount=0;
    String operator="";
    String firstVal="0";
    String secondVal="";
    boolean enter=false;
    float result=0;

    //Operation based on operator:
    public float operation(String operator, String firstVal, String secondVal){
        try{
            if (operator.equals("a")){
                return (Float.parseFloat(firstVal)+Float.parseFloat(secondVal));
            }
            if (operator.equals("s")){
                return (Float.parseFloat(firstVal)-Float.parseFloat(secondVal));
            }
            if (operator.equals("m")){
                return (Float.parseFloat(firstVal)*Float.parseFloat(secondVal));
            }
            if (operator.equals("d")){
                return (Float.parseFloat(firstVal)/Float.parseFloat(secondVal));
            }
            else {
                return Float.parseFloat(firstVal);
            }
        }
        catch (Exception e){
            return Float.parseFloat(firstVal);
        }


    }

    //It helps in handling decimal values
    //DecimalFormat df=new DecimalFormat("#.##");
    //1-
    //2+3+
    //4+23*
    //5/
    //6%

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

                if(input.getText().toString().matches(".*[\\u002b\\u2212\\u00d7\\u00f7]$")) {

                    int length=input.getText().toString().length();
                    input.setText(input.getText().toString().substring(0,length-1) + "\u002b");

                }
                else {
                    input.setText(input.getText().toString() + "\u002b");
                    secondVal="";
                    if (operatorCount!=0){
                        firstVal = Float.toString(result);
                    }
                    operatorCount+=1;
                }
            }
        });
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                operator="s";
                if(input.getText().toString().matches(".*[\\u002b\\u2212\\u00d7\\u00f7]$")) {
                    int length=input.getText().toString().length();
                    input.setText(input.getText().toString().substring(0,length-1) + "\u2212");
                }
                else {
                    input.setText(input.getText().toString() + "\u2212");
                    secondVal="";
                    if (operatorCount!=0){
                        firstVal = Float.toString(result);
                    }
                    operatorCount+=1;
                }

            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator="m";
                if(input.getText().toString().matches(".*[\\u002b\\u2212\\u00d7\\u00f7]$")) {
                    int length=input.getText().toString().length();
                    input.setText(input.getText().toString().substring(0,length-1) + "\u00d7");
                }
                else {
                    input.setText(input.getText().toString() + "\u00d7");
                    secondVal="";
                    if (operatorCount!=0){
                        firstVal = Float.toString(result);
                    }
                    operatorCount+=1;
                }

            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator="d";
                if(input.getText().toString().matches(".*[\\u002b\\u2212\\u00d7\\u00f7]$")) {
                    int length=input.getText().toString().length();
                    input.setText(input.getText().toString().substring(0,length-1) + "\u00f7");
                }
                else {
                    input.setText(input.getText().toString() + "\u00f7");
                    secondVal="";
                    if (operatorCount!=0){
                        firstVal = Float.toString(result);
                    }
                    operatorCount+=1;
                }

            }
        });
        /*
        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator="p";
                if(input.getText().toString().endsWith("+")) {
                }
                else {
                    if (secondVal != "") {
                        result = Float.parseFloat(firstVal) % Float.parseFloat(secondVal);
                        answer.setText("=" + Float.toString(result));
                        input.setText(input.getText().toString() + "%");
                        firstVal = Float.toString(result);
                        secondVal = "";
                    }
                    else {
                        input.setText(input.getText().toString() + "%");

                    }
                }

            }
        }); */
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input.setText(null);
                answer.setText("0");
                firstVal="0";
                secondVal="";
                operator="";
                operatorCount=0;
                enter=false;
                result=0;
            }
        });
        /*
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float result;
                answer=Float.parseFloat(answer.getEditableText())
                if (operator.equals("a"){
                    answer=answer+
                }

                answer.setText(answer.getEditableText()+"/");


            }
        }); */
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enter){

                }
                else{
                    if (!input.getText().toString().matches(".*[+\\-*/%]$") ) {

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


                if(!operator.equals("")){
                    if (!secondVal.equals("0")) {
                        secondVal += "0";
                        input.setText(input.getText().toString()+"0");
                    }
                    else{
                        secondVal="0";
                        int length=input.getText().toString().length();
                        input.setText(input.getText().toString().substring(0,length-1)+"0");
                    }

                    result=operation(operator,firstVal,secondVal);
                    answer.setText("=" + Float.toString(result));
                }
                else {
                    if (enter){
                        clear.performClick();
                    }
                    if (!firstVal.equals("0")) {
                        firstVal += "0";
                        input.setText(input.getText().toString() + "0");
                    }
                    else{
                        firstVal="0";
                        int length=input.getText().toString().length();
                        input.setText(input.getText().toString().substring(0,length-1)+"0");
                    }
                    answer.setText(firstVal);
                    enter = false;
                }

            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!operator.equals("")){
                    secondVal+="1";
                    input.setText(input.getText().toString()+"1");

                    result = operation(operator,firstVal,secondVal);
                    answer.setText("=" + Float.toString(result));
                }
                else {
                    if (enter){
                        clear.performClick();
                    }
                    if (!firstVal.equals("0")) {
                        firstVal += "1";
                    }
                    else{
                        firstVal="1";
                    }
                    answer.setText(firstVal);
                    input.setText(input.getText().toString() + "1");
                    enter = false;
                }

            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!operator.equals("")){
                    secondVal+="2";
                    input.setText(input.getText().toString()+"2");

                    result=operation(operator,firstVal,secondVal);
                    answer.setText("=" + Float.toString(result));
                }
                else {
                    if (enter){
                        clear.performClick();
                    }
                    if (!firstVal.equals("0")) {
                        firstVal += "2";
                    }
                    else{
                        firstVal="2";
                    }
                    answer.setText(firstVal);
                    input.setText(input.getText().toString() + "2");
                    enter = false;
                }

            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!operator.equals("")){
                    secondVal+="3";
                    input.setText(input.getText().toString()+"3");

                    result=operation(operator,firstVal,secondVal);
                    answer.setText("=" + Float.toString(result));
                }
                else {
                    if (enter){
                        clear.performClick();
                    }
                    if (!firstVal.equals("0")) {
                        firstVal += "3";
                    }
                    else{
                        firstVal="3";
                    }
                    answer.setText(firstVal);
                    input.setText(input.getText().toString() + "3");
                    enter = false;
                }

            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!operator.equals("")){
                    secondVal+="4";
                    input.setText(input.getText().toString()+"4");

                    result=operation(operator,firstVal,secondVal);
                    answer.setText("=" + Float.toString(result));
                }
                else {
                    if (enter){
                        clear.performClick();
                    }
                    if (!firstVal.equals("0")) {
                        firstVal += "4";
                    }
                    else{
                        firstVal="4";
                    }
                    answer.setText(firstVal);
                    input.setText(input.getText().toString() + "4");
                    enter = false;
                }

            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!operator.equals("")){
                    secondVal+="5";
                    input.setText(input.getText().toString()+"5");

                    result=operation(operator,firstVal,secondVal);
                    answer.setText("=" + Float.toString(result));
                }
                else {
                    if (enter){
                        clear.performClick();
                    }
                    if (!firstVal.equals("0")) {
                        firstVal += "5";
                    }
                    else{
                        firstVal="5";
                    }
                    answer.setText(firstVal);
                    input.setText(input.getText().toString() + "5");
                    enter = false;
                }

            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!operator.equals("")){
                    secondVal+="6";
                    input.setText(input.getText().toString()+"6");

                    result=operation(operator,firstVal,secondVal);
                    answer.setText("=" + Float.toString(result));
                }
                else {
                    if (enter){
                        clear.performClick();
                    }
                    if (!firstVal.equals("0")) {
                        firstVal += "6";
                    }
                    else{
                        firstVal="6";
                    }
                    answer.setText(firstVal);
                    input.setText(input.getText().toString() + "6");
                    enter = false;
                }

            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!operator.equals("")){
                    secondVal+="7";
                    input.setText(input.getText().toString()+"7");

                    result=operation(operator,firstVal,secondVal);
                    answer.setText("=" + Float.toString(result));
                }
                else {
                    if (enter){
                        clear.performClick();
                    }
                    if (!firstVal.equals("0")) {
                        firstVal += "7";
                    }
                    else{
                        firstVal="7";
                    }
                    answer.setText(firstVal);
                    input.setText(input.getText().toString()+"7");
                    enter=false;
                }

            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!operator.equals("")){
                    secondVal+="8";
                    input.setText(input.getText().toString()+"8");

                    result=operation(operator,firstVal,secondVal);
                    answer.setText("=" + Float.toString(result));
                }
                else {
                    if (enter){
                        clear.performClick();
                    }
                    if (!firstVal.equals("0")) {
                        firstVal += "8";
                    }
                    else{
                        firstVal="8";
                    }
                    answer.setText(firstVal);
                    input.setText(input.getText().toString()+"8");
                    enter=false;
                }

            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!operator.equals("")){
                    secondVal+="9";
                    input.setText(input.getText().toString()+"9");

                    result=operation(operator,firstVal,secondVal);
                    answer.setText("=" + Float.toString(result));
                }
                else {
                    if (enter){
                        clear.performClick();
                    }
                    if (!firstVal.equals("0")) {
                        firstVal += "9";
                    }
                    else{
                        firstVal="9";
                    }
                    answer.setText(firstVal);
                    input.setText(input.getText().toString()+"9");
                    enter=false;
                }

            }
        });
        /*
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!operator.equals("")){
                    secondVal+=".";
                    input.setText(input.getText().toString()+".");
                }
                else {
                    if (enter){
                        clear.performClick();
                    }
                    firstVal+=".";
                    answer.setText(firstVal);
                    input.setText(input.getText().toString()+".");
                    enter=false;
                }

            }
        });  */

    }
}
