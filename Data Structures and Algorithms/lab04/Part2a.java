import java.util.*;

public class Part2a {

    //different tellers customers are dealing with
    static int line1 = 0;
    static int line2 = 0;
    static int line3 = 0;

    //checks if a teller is occupied to decrement it
    public static void lineChecker(boolean l1, boolean l2, boolean l3){
        if(l1 && line1>0){
            line1--;
        }
        if(l2 && line2>0){
            line2--;
        }
        if(l3 && line3>0){
            line3--;
        }
    }

    public static void main(String[]args){
        circularQueue queue = new circularQueue(100);
        //random people coming into the bank
        int[] people = new int [100];
        //total number of time spent waiting in line
        double sum = 0;
        Random rand = new Random();

        //gets 100 random people and how long they take at teller
        for(int i = 0; i< 100; i++){
            people[i] = rand.nextInt(4)+1;
        }

        //amount of time until next person arrives
        int time;

        //for each of the 100 people
        for(int i = 0; i< 100; i++){
            //time until next person arrives
            time = rand.nextInt(4)+1;
            for(int k = 0; k < time; k++){
                //if all tellers are busy, decrement time at teller
                if(line1 > 0 && line2 > 0 && line3 > 0){
                    lineChecker(true, true, true);
                    //if people are waiting in line increment sum
                    if(!queue.isEmpty()){
                        sum++;
                    }
                }
                else{
                    //adds next person in line to an empty teller and decrements line if person is at the teller
                    if(line1 == 0){
                        line1 = queue.dequeue();
                        lineChecker(false, true, true);
                    }
                    if(line2 == 0){
                        line2 = queue.dequeue();
                        lineChecker(true, false, true);
                    }
                    if(line3 == 0){
                        line3 = queue.dequeue();
                        lineChecker(true, true, false);
                    }
                }
            }
            //adds person to line
            queue.enqueue(people[i]);
        }

        System.out.println("Avg wait time in queue per person: " + sum/100);
    }
}
