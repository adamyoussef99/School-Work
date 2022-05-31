//Adam Youssef
//104785050

import java.util.*;

public class lab5 {

    public static int insertionSort(int a[], int c){
        int size = a.length;
        int checker;    //the item were moving to the correct spot when sorting
        int placement = 0;  //where we place the checker after moving all items less than it
        //if the array only has 0 or 1 items return the array
        if(size == 0 || size == 1){
            c = 0;
            return c;
        }
        //go through items starting at second postion
        for(int i = 1; i < size; i++){
            checker = a[i];
            //check all items before the current item being checked
            for(int j = i-1; j >= 0; j--){
                //moves items up in array if they're bigger than the checker
                if (checker < a[j]){
                    a[j+1] = a[j];
                    c++;    //increment c as we've done a comparison
                }
                //get spot to add the number we're checking
                else{
                    placement = j;
                    c++;    //increment c as we've done a comparison
                    break;
                }
            }
            a[placement] = checker;
        }
        //return the amount of counts that were done
        return c;
    }

    public static int mergeSort(int a[], int c){
        int n = a.length;
        int count = 0;
        int a1[] = new int[n/2];
        int a2[];

        //if n is an odd number then make the extra number go to second array
        if(n%2 != 0){
            a2 = new int[(n/2)+1];
        }
        else{
            a2 = new int[n/2];
        }
        //as long as there's more than one item in the list to sort
        if(n > 1){
            //loops through and adds a elements to a1 and a2
            for(int i = 0; i < n/2; i++){
                a1[i] = a[i];
            }
            for(int i = n/2; i < n; i++){
                a2[count] = a[i];
                count++;
            }

            //split a1 and a2 again
            mergeSort(a1, c);
            mergeSort(a2, c);
            //merges lists together and sorts them
            c = Merge(a, a1, a2, c);
        }
        else{
            //return number of comparisons
            return c;
        }
        //return number of comparisons
        return c;
    }

    public static int Merge(int a[], int a1[], int a2[], int c){
        int n1 = a1.length;
        int n2 = a2.length;

        int i = 0;
        int j = 0;
        int k = 0;

        //loops through the elements in 'a1' and 'a2'
        while(i < n1 && j < n2){
            //compare elements and add them to 'a' in order
            if(a1[i] < a2[j]){
                a[k] = a1[i];
                i++;
                c++;    //increment c as we've done a comparison
            }
            else{
                a[k] = a2[j];
                j++;
                c++;    //increment c as we've done a comparison
            }
            k++;
        }

        for(int m = i; m < n1; m++){
            a[k] = a1[m];
            k++;
            c++;
        }
        for(int m = j; m < n2; m++){
            a[k] = a2[m];
            k++;
        }
        return c;
    }

    //gets random numbers and fills array
    public static void arrayFiller(int a[]){
        Random rand = new Random();
        for(int i = 0; i < a.length; i++){
            a[i] = rand.nextInt(9)+1;
        }
    }

    //prints out an array
    public static void printArray(int a[]){
        for(int i = 0; i<a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    //This is in the main function, used to get the counts in PlotSomeData
    public static int[] insertCount(){
        int n = 500;
        int cur = 0;
        int insert[] = new int[20];
        int count = 0;
        int a[];

        while(n <= 10000){
            a = new int[n];

            arrayFiller(a);
            insert[cur] = count + insertionSort(a, count);

            count = 0;
            cur++;
            n = n + 500;
        }
        return insert;
    }

    //This is in the main function, used to get the counts in PlotSomeData
    public static int[] mergeCount(){
        int n = 500;
        int cur = 0;
        int merge[] = new int[20];
        int count = 0;
        int a[];

        while(n <= 10000){
            a = new int[n];

            arrayFiller(a);
            merge[cur] = count + mergeSort(a, count);

            count = 0;
            cur++;
            n = n + 500;
        }
        return merge;
    }

    public static void main(String[] args) {
        int n = 500;    //numbers in the list to sort
        int cur = 0;    //position to add both comparison counts to respective lists
        int insertCount[] = new int[20];    //number of comparisons for all insert tests
        int mergeCount[] = new int[20];     //number of comparisons for all merge tests
        int count1 = 0;     //number of comparisons in insertion sort
        int count2 = 0;     //number of comparisons in merge sort
        //arrays of random numbers to be of size 'n'
        int a[];
        int m[];

        //loop until n=10000, incrementing by 500 each time
        while(n <= 10000){
            //make a and m as size 'n'
            a = new int[n];
            m = new int[n];

            //fill array 'a' and copy it to array 'm'
            arrayFiller(a);

            for(int i = 0; i < a.length; i++){
                m[i] = a[i];
            }

            //run insert and merge sort and store comparison counts
            insertCount[cur] = count1 + insertionSort(a, count1);
            mergeCount[cur] = count2 + mergeSort(m, count2);

            //reset variables
            count1 = 0;
            count2 = 0;
            //increment to move to next test
            cur++;
            n = n + 500;
        }
        System.out.print("insert sort comparisons: ");
        printArray(insertCount);
        System.out.print("merge sort comparisons: ");
        printArray(mergeCount);
    }
}
