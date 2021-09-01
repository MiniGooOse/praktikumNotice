package de.uni.marburg.plt.calculator.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BasicPRNCalculatorImpl implements BasicRPNCalculator, BasicRPNConverter{
    @Override
    public double evaluate(String expression) {
        return 0;
    }


    /**
     * Evaluates an arithmetic expression in reverse Polish notation and returns the result.
     * The expression may contain variables, which have to be set via the given map.
     * @param expression the RPN expression.
     * @param variableAssignments the variable mappings.
     * @return the result of the arithmetic expression.
     */

    /**
     * Operator range :      + - * / % ^
     */
    @Override
    public double evaluate(String expression, Map<String, Double> variableAssignments){
        String[] inputList = expression.split(" ");
        String[] tempList = inputList;
        ArrayList<Integer> opIndex = new ArrayList<>();
        for(int i = 0; i < inputList.length; i++){
            String exp = inputList[i];
            if(isOperator(exp)){
                opIndex.add(i);
            }
            if(!isNumeric(exp) && !isOperator(exp)){
                if (variableAssignments.get(exp) == null){
                    throw new IllegalArgumentException("variable not initialised");
                }
            }
        }

        if(inputList.length == 1 && isNumeric(inputList[0])){
            return Double.parseDouble(inputList[0]);
        } else if(inputList.length == 1){
            return getValue(inputList[0], variableAssignments);
        }

        if(opIndex.size() == 0 || opIndex.get(opIndex.size() - 1) != inputList.length -1
                || opIndex.get(0) < 2){
            throw new IllegalArgumentException("illegal operation");
        }

        for(int i : opIndex){
            evalOneExp(i, tempList,variableAssignments);
            tempList = inputList;
        }

        if(tempList.length == 1 && isNumeric(inputList[0])){
            return Double.parseDouble(tempList[0]);
        } else if(tempList.length == 1){
            return getValue(inputList[0], variableAssignments);
        } else {
            throw new IllegalArgumentException("illegal operation");
        }
    }

    /**
     * helps method
     * @param str
     * @return true if the given string can be parsed as a double
     */
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public boolean isOperator(String str) {
        ArrayList<String> opList = new ArrayList<String>(Arrays.asList("+", "-", "*", "/", "%", "^"));
        return opList.contains(str);
    }

    public double getValue(String str, Map<String, Double> variableAssignments) {
        if (isNumeric(str)){
            return  Double.parseDouble(str);
        } else if (variableAssignments.get(str) != null){
            return variableAssignments.get(str);
        } else {
            throw new IllegalArgumentException("illigal operation");
        }
    }

    public String[] evalOneExp(int opIndex, String[] givenList, Map<String, Double> variableAssignments){
        int index1 = opIndex - 2;
        int index2 = opIndex - 1;
        String op = givenList[opIndex];
        double operand1 = getValue(givenList[index1], variableAssignments);
        double operand2 = getValue(givenList[index2], variableAssignments);
        double result = 0;
        switch (op){
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
            case "%":
                result = operand1 % operand2;
                break;
            case "^":
                result = Math.pow(operand1, operand2);
                break;
        }
        String[] newList = new String[givenList.length - 2];
        for(int i = 0; i < index1; i++){
            newList[i] = givenList[i];
        }
        newList[index1] = String.valueOf(result);
        for(int i = opIndex + 1; i < givenList.length; i++){
            newList[i - 2] = givenList[i];
        }
        return newList;
    }

    @Override
    public String convertRPNToInfix(String expression) {
        return null;
    }
}
