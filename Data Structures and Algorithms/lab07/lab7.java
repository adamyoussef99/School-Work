//Adam Youssef
//104785050
//Part A of lab 7

import java.util.*;

public class lab7 {

    static ArrayList<Integer> list = new ArrayList<Integer>();
    static int comparisons = 0;

    public static void Heapify(int i, int j){
        int k;

        if(2*i+2 <= j){     // A[i] has two children
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

    public static void main(String[] args) {
        int[]a = {4, 2, 1, 8, 6, 5, 10, 9, 11, 16};
        for(int l: a){
            list.add(l);
        }
        Heapify(0, 9);
        list.forEach(i->System.out.print(i + " "));
        System.out.println();
        System.out.println("Comparisons: " + comparisons);
    }
}
