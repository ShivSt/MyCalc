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

    //Variables which helps to decide what operation is to be performed
    int operatorCount=0;
    String operator="";
    StringBuilder firstVal=new StringBuilder("0");
    StringBuilder secondVal=new StringBuilder();
    boolean enter=false;
    float result=0;
    
    //Getting string values in input and answer TextView
    public String getString(TextView tv){
        return tv.getText().toString();
    }


    //Defining the component classes which will perform the actions
    //Operation operate=new Operation();

    //Operation based on operator:
    public float operation(String operator, StringBuilder firstVal, StringBuilder secondVal){
        try{
            switch (operator) {
				//returns evaluation based on the operator button clicked by user
                case "a":
                    return (Float.parseFloat(firstVal.toString()) + Float.parseFloat(secondVal.toString()));
                case "s":
                    return (Float.parseFloat(firstVal.toString()) - Float.parseFloat(secondVal.toString()));
                case "m":
                    return (Float.parseFloat(firstVal.toString()) * Float.parseFloat(secondVal.toString()));
                case "d":
                    return (Float.parseFloat(firstVal.toString()) / Float.parseFloat(secondVal.toString()));
                default:
					//If only firstVal is present then returns firstVal
                    return Float.parseFloat(firstVal.toString());
            }
        }
        catch (Exception e){
			//Even in case of any exceptions returns firstVal as its defaultvalue is 0
            return Float.parseFloat(firstVal.toString());
        }


    }

    //Setter function to set value 0,1,2...based on respective button
    public void setValue(String val){
		//When operator is set, user clicked value is entered in secondVal variable
        if(!operator.equals("")){
			//Handle point
			if((secondVal.indexOf(".")>-1) && val.equals(".")){
			}
			else{
				
				//If variable is equal to zero, replace it by clicked value or concat in it
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
				//Also evaluate result as the user clicks a value and display it
				result=operation(operator,firstVal,secondVal);
				answer.setText("=" + Float.toString(result));
			}
        }
		//Else value is added in firstVal variable
        else {
            if (enter){
				//If enter is clicked set all value to default
                clear.performClick();
            }
			if((firstVal.indexOf(".")>-1) && val.equals(".")){
			}
			else{
				
				if (!firstVal.toString().equals("0")) {
					firstVal.append(val);
					input.setText(getString(input) + val);
				}
				else{
					firstVal.setLength(0);
					firstVal.append(val);
					int length=getString(input).length();
					//Condition to differentiate between first entere value and already having string in input
					if(length>0){
						input.setText(getString(input).substring(0,length-1)+val);
					}
					else{
						input.setText(val);
					}
				}
				//Set variable value on display
				answer.setText("="+firstVal.toString());
				enter = false;
			}
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
            
			if (!getString(input).equals(null)){
				input.setText(getString(input)+sign);
			}
			else{
				input.setText("0"+sign);
			}
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


        //Views and buttons binding to their Ids:

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

        //Open activity with default values of each variables
        clear.performClick();


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
				//If answer contains only two or less charachter
                if (length<3){
					clear.performClick();
				}
				//Or if input endswith +,-,*,/
				else if(getString(input).matches(".*[+\\-*/%]$")){
					input.setText(getString(input).substring(0,length-1) );
				}
				//Else delete value from firstVal or secondVal and compute new result and display on answer
				else{
					input.setText(getString(input).substring(0,length-1) );
                    if(!secondVal.toString().equals("")){

                        secondVal.setLength(secondVal.length()-1);
                        equals.performClick();
                    }
                    else{

                        firstVal.setLength(firstVal.length()-1);
                        equals.performClick();
                    }   
                }
                
            }
        });
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enter){
					//If user already clicks enter and didnot entered anything then do nothing
                }
                else{
					result = operation(operator,firstVal,secondVal);
                    answer.setText("=" + Float.toString(result));
                    if (!getString(input).matches(".*[+\\-*/%]$") ) {
						//If both values anr given with operator in between then reset operartor and set enter
						operator="";
                        enter=true;
                        //result=0;
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
