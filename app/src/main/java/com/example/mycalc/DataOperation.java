package com.example.mycalc;

import org.jetbrains.annotations.NotNull;

import java.util.Stack;

enum operator{ADD, SUBTRACT, MULTIPLY, DIVIDE, BLANK }
class DataOperation {

    //Variables which helps to decide what operation is to be performed
    private int operatorCount=0;

    private StringBuilder firstVal=new StringBuilder("0");
    private StringBuilder secondVal=new StringBuilder();
    private boolean enter=false;
    StringBuilder input=new StringBuilder();
    StringBuilder answer=new StringBuilder();
    private float result=0;


    //Getting string values in input and answer TextView
    private String getStringFromView(@NotNull StringBuilder viewString){
        return viewString.toString();
    }

    //Setting String values from input and answer from MainActivity
    void setStringFromView(String inputString, String answerString){
        input.replace(0,input.length(),inputString);
        answer.replace(0,answer.length(),answerString);
    }

    //Evaluate input string according to BODMAS
    private float evaluate(String input){
        try{
            Stack<Float> numberStack = new Stack<Float>();
            Stack<operator> operatorStack = new Stack<operator>();
            for(int i = 0; i < input.length(); i++){
                try{
                    int number = parseNumber(input, i);
                    numberStack.push((float)number);
                    i += Integer.toString(number).length();
                    if(i >= input.length()){
                        break;
                    }

                    operator op = parseOperator(input, i);
                    collapseTop(numberStack, operatorStack, op);
                    operatorStack.push(op);
                } catch(NumberFormatException ex){
                    return Integer.MIN_VALUE;
                }
            }
            collapseTop(numberStack, operatorStack, operator.BLANK);
            if(numberStack.size() == 1 && operatorStack.size() == 0){
                return numberStack.pop();
            }
            return 0;
        }
        catch (Exception e){
            //Even in case of any exceptions returns firstVal as its default value is 0
            return Float.parseFloat(firstVal.toString());
        }

    }

    private void collapseTop(Stack<Float> numberStack, Stack<operator> operatorStack, operator futureTop){
        while(numberStack.size() >= 2 && operatorStack.size() >= 1){
            if(priorityOfOperator(futureTop) <= priorityOfOperator(operatorStack.peek())){
                float second = numberStack.pop();
                float first = numberStack.pop();
                operator op = operatorStack.pop();
                float result = applyOp(first, op, second);
                numberStack.push(result);
            } else{
                break;
            }
        }
    }

    private float applyOp(float left, operator op, float right){
        switch (op){
            case ADD: return left + right;
            case SUBTRACT: return left - right;
            case MULTIPLY: return left * right;
            case DIVIDE: return left / right;
            default: return right;
        }

    }

    private int priorityOfOperator(operator op){
        switch (op){
            case ADD: return 1;
            case SUBTRACT: return 1;
            case MULTIPLY: return 2;
            case DIVIDE: return 2;
            case BLANK: return 0;
        }
        return 0;
    }

    private int parseNumber(String sequence, int offset){
        StringBuilder sb = new StringBuilder();
        while(offset < sequence.length() && Character.isDigit(sequence.charAt(offset))){
            sb.append(sequence.charAt(offset));
            offset++;
        }
        return Integer.parseInt(sb.toString());
    }

    private operator parseOperator(String sequence, int offset){
        if(offset < sequence.length()){
            char op = sequence.charAt(offset);
            switch (op){
                case '\u002b': return operator.ADD;
                case '\u2212': return operator.SUBTRACT;
                case '\u00d7': return operator.MULTIPLY;
                case '\u00f7': return operator.DIVIDE;
            }
        }
        return operator.BLANK;
    }

    void arithmeticOperator(@org.jetbrains.annotations.NotNull operator op){
        String sign ;
        switch (op) {
            case ADD:
                sign = "\u002b";
                break;
            case SUBTRACT:
                sign = "\u2212";
                break;
            case MULTIPLY:
                sign = "\u00d7";
                break;
            case DIVIDE:
                sign = "\u00f7";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + op);
        }


        if(getStringFromView(input).matches(".*[\\u002b\\u2212\\u00d7\\u00f7]$")) {

            int length=getStringFromView(input).length();
            input.replace(0,input.length(),String.format("%s%s", getStringFromView(input).substring(0, length - 1),sign));

        }
        else {

            if (!getStringFromView(input).equals("")){
                input.replace(0,input.length(),String.format("%s%s", getStringFromView(input), sign));
            }
            else{
                input.replace(0,input.length(),String.format("0%s", sign));
            }
            secondVal.setLength(0);
            if (operatorCount!=0){
                firstVal.setLength(0);
                firstVal.append(result);
            }
            operatorCount+=1;
        }
    }

    void clear() {
        input.setLength(0);
        answer.setLength(0);answer.append("0");
        MainActivity.op=operator.BLANK;
        firstVal.setLength(0);firstVal.append("0");
        secondVal.setLength(0);
        operatorCount=0;
        enter=false;
        result=0;
    }

    void delete() {

        String str = getStringFromView(input); //take input value from TextView
        //if length of input greater than 1 than its go in if condition
        if (str.length() >1 ) {
            str = str.substring(0, str.length() - 1); // remove last 1 element from str
            input = new StringBuilder(str);                   //and set it into input
            // Log.i(str + "     ","");
            if(!secondVal.toString().equals("")){       //secondValue is not null
                String value = secondVal.toString();
                value = value.substring(0, value.length() - 1); // remove last 1 element from second value
                secondVal = new StringBuilder(value);
            }else if(!MainActivity.op.equals(operator.BLANK)){    // operator is not null
                MainActivity.op = operator.BLANK;
            }else {
                String value = firstVal.toString();
                value = value.substring(0, value.length() - 1);  // remove last 1 element from first value
                firstVal = new StringBuilder(value);
            }
                result=evaluate(input.toString()); // evaluate result
                answer = new StringBuilder("=" + Float.toString(result));     // set result in anwer TextView
            }
        else if (str.length() <=1 ) {
            input = new StringBuilder("0");
            answer = new StringBuilder("0");
        }
    }

    void equals(){
        if (enter){
            //If user already clicks enter and did not entered anything then do nothing
        }
        else{
            result = evaluate(input.toString());
            answer.setLength(0);
            answer.append(String.format("=%s", result));
            if (!getStringFromView(input).matches(".*[+\\-*/%]$") ) {
                //If both values anr given with operator in between then reset operartor and set enter
                MainActivity.op=operator.BLANK;
                enter=true;
                //result=0;
            }


        }
    }

    //Setter function to set value 0,1,2...based on respective button
    void setValue(String val){


        //When operator is set, user clicked value is entered in secondVal variable
        operator op=MainActivity.getOperator();
        if(op!= operator.BLANK){
            //Handle point
            if((secondVal.indexOf(".")>-1) && val.equals(".")) {

            }
            else{

                //If variable is equal to zero, replace it by clicked value or concat in it
                if (!secondVal.toString().equals("0")) {
                    secondVal.append(val);
                    input.replace(0,input.length(),String.format("%s%s", getStringFromView(input), val));
                }
                else{
                    secondVal.setLength(0);
                    secondVal.append(val);
                    int length=getStringFromView(input).length();
                    input.replace(0,input.length(),String.format("%s%s", getStringFromView(input).substring(0, length - 1), val));
                }
                //Also evaluate result as the user clicks a value and display it
                result = evaluate(input.toString());
                answer.replace(0,answer.length(),String.format("=%s", result));
            }
        }
        //Else value is added in firstVal variable
        else {
            if (enter){
                //If enter is clicked set all value to default
                clear();
            }
            if((firstVal.indexOf(".")>-1) && val.equals(".")){
            }
            else{

                if (!firstVal.toString().equals("0")) {
                    firstVal.append(val);
                    input.replace(0,input.length(),String.format("%s%s", getStringFromView(input), val));
                }
                else{
                    firstVal.setLength(0);
                    firstVal.append(val);
                    int length=getStringFromView(input).length();
                    //Condition to differentiate between first entere value and already having string in input
                    if(length>0){
                        input.replace(0,input.length(),String.format("%s%s", getStringFromView(input).substring(0, length - 1), val));
                    }
                    else{
                        input.replace(0,input.length(),val);
                    }
                }
                //Set variable value on display
                answer.replace(0,answer.length(),String.format("=%s", firstVal.toString()));
                enter = false;
            }
        }
    }

    private float returnValueFromStack(StringBuilder firstVal,StringBuilder secondVal){
        return 0f;
    }
}
