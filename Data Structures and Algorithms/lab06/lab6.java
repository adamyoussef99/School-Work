import java.util.*;

public class lab6 {

    static int ycount = 0;
    static int xcount = 0;

    //Prints list in preorder
    public static void Preorder(int a[], int root){

        //stores values of a into nodes
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        for(int i: a){
            nodes.add(i);
        }

        //print root (current number as we're traversing)
        System.out.print(root + " ");
        //if left child isn't empty, call preorder to that child
        if(nodes.contains(root*2+1)) {
            Preorder(a, root*2+1);
        }
        //if right child isn't empty, call preorder to that child
        if(nodes.contains(root*2+2)){
            Preorder(a, root*2+2);
        }

    }

    //Prints list in order
    public static void Inorder(int a[], int root, int axis[][]){

        //stores values of a into nodes
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        for(int i: a){
            nodes.add(i);
        }

        //if left child isn't empty, call inorder to that child
        if(nodes.contains(root*2+1)) {
            ycount++;
            Inorder(a, root*2+1, axis);
        }
        xcount++;
        axis[root][0] = ycount;
        axis[root][1] = xcount-1;
        //print root (current number as we're traversing)
        System.out.print(root + " ");
        //if right child isn't empty, call inorder to that child
        if(nodes.contains(root*2+2)){
            ycount++;
            Inorder(a, root*2+2, axis);
        }
        ycount--;
    }

    //Prints list in order
    public static void Postorder(int a[], int root){

        //stores values of a into nodes
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        for(int i: a){
            nodes.add(i);
        }

        //if left child isn't empty, call postorder to that child
        if(nodes.contains(root*2+1)) {
            Postorder(a, root*2+1);
        }
        //if right child isn't empty, call postorder to that child
        if(nodes.contains(root*2+2)){
            Postorder(a, root*2+2);
        }
        //print root (current number as we're traversing)
        System.out.print(root + " ");
    }

    public static void main(String[] args) {

        int a[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int axis[][] = new int[10][2];

        Preorder(a, a[0]);
        System.out.println();
        Inorder(a, a[0], axis);
        System.out.println();
        Postorder(a, a[0]);
        System.out.println();

        System.out.print("coordinates: ");
        for(int i = 0; i < 10; i++){
            System.out.print("(" + axis[i][1]+ ", " + axis[i][0] + ") ");
        }
        System.out.println();
    }
}
