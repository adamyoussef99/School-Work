public class circularQueue {

    int max;
    int start, end;
    int queue[];

    //constructor
    circularQueue(){
        start = -1;
        end = -1;
        max = 5;
        queue = new int[max];
    }

    //constructor where you can set the max size of queue
    circularQueue(int size){
        start = -1;
        end = -1;
        max = size;
        queue = new int[max];
    }

    //checks if queue is empty
    public boolean isEmpty(){
        if(start == end){
            return true;
        }
        return false;
    }

    //checks if queue is full
    public boolean isFull(){
        if((end+1)%max == start){
            return true;
        }
        return false;
    }

    //returns amount of items in queue
    public int size(){
        return ((end-start+max)%max);
    }

    //returns value at the front of the circle
    public int front(){ return queue[start]; }

    //adds value to queue
    public void enqueue(int value){
        if(isFull()){
            System.out.println("Queue is full.");
        }
        else{
            if(isEmpty()){
                start = 0;
            }
            end = (end + 1) % max;
            queue[end] = value;
        }
    }

    //removes value from queue
    public int dequeue(){
        int value = 0;
        if(isEmpty()){
            System.out.println("Queue is empty.");
        }
        else{
            value = queue[start];
            start = (start + 1) % max;
        }
        return value;
    }

}
