//Adam Youssef
//104785050

import java.util.Scanner;

public class part3 {
    //maximum amount of storage in stack allowed
    final int MAX = 100;

    //Array to store items
    int[] a;

    //top of the stack
    int t;

    //Constructor
    public part3() {
        a = new int[MAX];
        t = -1;
    }

    //returns size of the array the stack is made in
    public int size(){
        return t;
    }

    //returns value at the top of the stack, returns 0 if empty
    public int top(){
        if(isEmpty()){
            System.out.println("Stack is empty.");
            return 0;
        }
        return a[t];
    }

    //checks if stack is full with max amount allowed
    public boolean isFull(){
        if(t == MAX){
            return true;
        }
        else {
            return false;
        }
    }

    //checks if stack is empty by checking if t < 0
    public boolean isEmpty(){
        if(t == -1){
            return true;
        }
        else {
            return false;
        }
    }

    //pushes object to top of stack, doesn't add if stack is full
    public void push(int i){
        if(isFull()){
            System.out.println("Stack is full.");
            return;
        }
        t ++;
        a[t] = i;
    }

    //pops item off the top of stack and returns it, doesn't pop if stack is empty
    public int pop(){
        if(isEmpty()){
            System.out.println("Stack is empty.");
            return 0;
        }
        else {
            int popped = a[t];
            t--;
            return popped;
        }
    }

    public static void main(String[]args){
        //create stack
        part3 a = new part3();
        //numbers for calculations
        int result = 0;
        int num1 = 0;
        int num2 = 0;

        //get first user input value
        Scanner input = new Scanner(System.in);
        System.out.println("Enter sequence: ");
        char current = input.next().charAt(0);

        //while user doesn't input '='
        while(current != '='){
            //reset result and nums to 0
            result = 0;
            num1 = 0;
            num2 = 0;
            //checks if current input is a number
            boolean checker = Character.isDigit(current);
            if(checker){
                a.push(Character.getNumericValue(current));
            }
            //if it's not a number checks which operator it is
            else{
                //checks to see if there's enough operands
                if(a.size() < 1){
                    System.out.println("Invalid input, not enough operands.");
                }
                else {
                    //pops off two top numbers and does inputted calculation
                    num1 = a.pop();
                    num2 = a.pop();
                    if (current == '+') {
                        result = num1 + num2;
                    }
                    if (current == '-') {
                        result = num1 - num2;
                    }
                    if (current == '*') {
                        result = num1 * num2;
                    }
                    //printf result of calculation and pushes it onto stack
                    System.out.println("Result: " + result);
                    a.push(result);
                }
            }
            //gets next user input
            current = input.next().charAt(0);
        }

        //pops off top value of stack, if the stack isn't empty, there were too many operands
        int end = a.pop();
        if(a.isEmpty()){
            System.out.println("Final result: " + end);
        }
        else{
            System.out.println("Too many operands.");
        }
    }
}
