//Adam Youssef
//104785050
//Part C of lab 7

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class lab7part3 {

    static ArrayList<Integer> list = new ArrayList<Integer>();
    static int comparisons = 0;

    public static void Heapify(int i, int j){
        int k;

        if(2*i+2<=j){
            //checks which child is bigger
            if(list.get(2*i+1)>=list.get(2*i+2)){
                k = 2*i+1;
            }
            else{
                k = 2*i+2;
            }
        }
        else if(2*i+1 <= j){     // A[i] has one child
            k = 2*i+1;  // k is index of the largest (and only) child
        }
        else{   // A[i] has no children
            return;
        }

        //checks if we need to swap with parent
        if(list.get(i) < list.get(k)){
            Collections.swap(list, i, k);
        }
        comparisons++;

        Heapify(k, j);
    }

    public static void BuildHeap(){
        int n = list.size();

        for(int i = n/2 - 1; i >= 0; i--){
            Heapify(i, n-1);
        }
    }

    public static void Heapsort(){
        BuildHeap();
        int n = list.size();
        for(int i = n-1; i >= 1; i--){
            Collections.swap(list, 0, i);
            Heapify(0, i-1);
        }
    }

    public static void arrayFiller(int a[]){
        Random rand = new Random();
        for(int i = 0; i < a.length; i++){
            a[i] = rand.nextInt(499)+1;   //gets new random number from 1 - 500 and adds to a
        }
    }

    public static void main(String[] args) {
        int[]a = new int[100];
        arrayFiller(a);
        for(int l: a){
            list.add(l);
        }
        Heapsort();
        list.forEach(i->System.out.print(i + " "));
        System.out.println();
        System.out.println("Comparisons: " + comparisons);
    }
}