package com.example.mycalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView input; 
    TextView answer;
    float result;

    /*
    Button add, subtract, multiply, divide,percent;
    Button clear, delete,equals;
    Button zero, one, two, three, four, five, six, seven, eight, nine, point, more;
    */

    
    //Getting string values in input and answer TextView
    public String getStringFromView(@NotNull TextView tv){
        return tv.getText().toString();
    }


    //Defining the component classes which will perform the actions
    DataOperation dp=new DataOperation();
    static operator op=operator.BLANK;
    public static operator getOperator(){        return op;    }
    //enum operator{ADD, SUBTRACT, MULTIPLY, DIVIDE, BLANK};


    //It helps in handling decimal values
    //DecimalFormat df=new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide the toolbar
        getSupportActionBar().hide();

        //Views and buttons binding to their Ids:
        input = findViewById(R.id.tVInput);
        answer = findViewById(R.id.tVAnswer);
    }

    public void operators(@NotNull View view) {
        dp.setStringFromView(getStringFromView(input),getStringFromView(answer));
        switch (view.getId()) {
            case R.id.btPlus:
                op=operator.ADD;
                dp.arithmeticOperator(op);
                break;
            case R.id.btSubtract:
                op= operator.SUBTRACT;
                dp.arithmeticOperator(op);
                break;
            case R.id.btMultiply:
                op= operator.MULTIPLY;
                dp.arithmeticOperator(op);
                break;
            case R.id.btDivide:
                op= operator.DIVIDE;
                dp.arithmeticOperator(op);
            case R.id.btPercent:
                op= operator.PERCENT;
                dp.arithmeticOperator(op);
            default:
                op= operator.BLANK;
                break;

        }
        input.setText(dp.input.toString());
    }

    public void clear(View view) {
        dp.clear();
        input.setText(null);
        answer.setText("0");
        result=0;
    }

    public void delete(View view) {
        dp.setStringFromView(getStringFromView(input),getStringFromView(answer));
        dp.delete();
        input.setText(dp.input.toString());
        answer.setText(dp.answer.toString());
    }

    public void percent(View view){

    }

    //Setter function to set value 0,1,2...based on respective button
    public void setValue(View view){
        dp.setStringFromView(getStringFromView(input),getStringFromView(answer));
        String val=((Button)view).getText().toString();
        dp.setValue(val);
        input.setText(dp.input.toString());
        answer.setText(dp.answer.toString());
    }

    public void equals(View view){
        dp.setStringFromView(getStringFromView(input),getStringFromView(answer));
        dp.equals();
        input.setText(dp.input.toString());
        answer.setText(dp.answer.toString());
    }
}
