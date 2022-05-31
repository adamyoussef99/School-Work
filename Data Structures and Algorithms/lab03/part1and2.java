//Adam Youssef
//104785050

import java.util.Scanner;

public class part1and2<T>{

    //maximum amount of storage in stack allowed
    final int MAX = 50;

    //Array to store items
    char[] a;

    //top of the stack
    int t;

    //Constructor
    public part1and2() {
        a = new char[MAX];
        t = -1;
    }

    //returns size of the array the stack is made in
    public int size(){
        return t;
    }

    //returns value at the top of the stack, returns 0 if empty
    public char top(){
        if(isEmpty()){
            System.out.println("Stack is empty.");
            return ' ';
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
    public void push(char c){
        if(isFull()){
            System.out.println("Stack is full.");
            return;
        }
        t ++;
        a[t] = c;
    }

    //pops item off the top of stack and returns it, doesn't pop if stack is empty
    public char pop(char popped){
        if(isEmpty()){
            System.out.println("Stack is empty.");
            return popped;
        }
        else {
            popped = a[t];
            t--;
            return popped;
        }
    }

    public static void main(String[]args){
        //create stack
        part1and2 a = new part1and2();

        //gets user input string
        Scanner input = new Scanner(System.in);
        System.out.println("Enter string: ");
        String in = input.nextLine();
        //keeps positions of last open bracket
        int pos = 0;

        for(int i = 0; i < in.length(); i++){
            //if the char is an open bracket, push it onto stack and save position
            if(in.charAt(i) == '('){
                a.push(in.charAt(i));
                pos = i;
            }
            //if the char is a closed bracket, push it onto stack, print position, and break
            else if(in.charAt(i) == ')' && a.isEmpty()){
                a.push(in.charAt(i));
                System.out.println("Mismatched bracket at position " + i);
                break;
            }
            //if it's a closed bracket and stack isn't empty, pop off the top open bracket and lower position to next open bracket spot
            else{
                a.pop(in.charAt(i-1));
                pos--;
            }
        }

        //if stack is empty, string is balanced
        if(a.isEmpty()){
            System.out.println("String is balanced");
        }
        //otherwise, if what's left is an open bracket, print position and that string is unbalanced
        else{
            if(a.pop('(') == '('){
                System.out.println("Mismatched bracket at position " + pos);
            }
            System.out.println("String is unbalanced");
        }
    }
}
