import java.util.*;

public class Part2b {
    //different tellers customers are dealing with
    static int line1 = 0;
    static int line2 = 0;
    static int line3 = 0;

    //3 separate queues people are waiting
    static circularQueue queue1 = new circularQueue(100);
    static circularQueue queue2 = new circularQueue(100);
    static circularQueue queue3 = new circularQueue(100);

    //checks which queue is the shortest
    public static void queueChecker(int value){
        if(queue1.size()<queue2.size()) {
            if(queue3.size()<queue1.size()) {
                queue3.enqueue(value);
            } else {
                queue1.enqueue(value);
            }
        } else {
            if(queue2.size()<queue3.size()) {
                queue2.enqueue(value);
            } else {
                queue3.enqueue(value);
            }
        }
    }

    public static void main(String[]args){
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
                    //decrement lines if people are with teller
                    line1--;
                    line2--;
                    line3--;
                    //if all queues aren't empty increment sum
                    if(!queue1.isEmpty() && !queue2.isEmpty() && !queue3.isEmpty()){
                        sum++;
                    }
                }
                else{
                    //add person to each teller if last person is done
                    if(line1 == 0){
                        line1 = queue1.dequeue();
                    }
                    if(line2 == 0){
                        line2 = queue2.dequeue();
                    }
                    if(line3 == 0){
                        line3 = queue3.dequeue();
                    }
                }
            }
            //check which line is smallest and add them to that line
            queueChecker(people[i]);
        }

        System.out.println("Avg wait time in queue per person: " + sum/100);
    }
}
