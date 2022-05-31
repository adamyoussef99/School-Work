//Adam Youssef
//104785050

public class Main {

    static Node TreeInsert(Node x, int k) throws Exception {
        //if tree is empty or this is where a new node is added, create the root/branch
        if(x==null){
            x = new Node(k);
        }
        //adds node to left of tree
        else if(k < x.key){
            x.left = TreeInsert(x.left, k);     //call tree insert to left until new node is added
            x.left.parent = x;      //sets new node's parent
        }
        //adds node to right of tree
        else if(k > x.key){
            x.right = TreeInsert(x.right, k);       //call tree insert to left until new node is added
            x.right.parent = x;         //sets new node's parent
        }
        else{
            throw new Exception("Existing key!");       //throws exception if node is already in tree
        }
        return x;       //return newly created node
    }

    static Node TreeSearch(Node x, int k){
        //if tree is empty or the key is found, return it
        if(x==null || k==x.key){
            return x;
        }
        //if we need to go to the left, search left branch
        else if(k < x.key){
            return TreeSearch(x.left, k);
        }
        //else search the right branch
        else{
            return TreeSearch(x.right, k);
        }
    }

    static Node Successor(Node x){
        Node p;     //final successor to be returned
        if(x.right != null){
            //returns smallest key to the right of x
            return TreeMin(x.right);
        }
        else{
            p = x.parent;
            while(p != null && x == p.right){
                x = p;
                p = x.parent;
            }
            return p;
        }
    }

    //gets smallest key from Node 'x'
    static Node TreeMin(Node x){
        while(x.left != null){
            x = x.left;
        }
        return x;
    }

    //copied from my previous assignment but used nodes instead of array
    static void printInorder(Node x) {
        if(x.left != null){
            printInorder(x.left);
        }
        System.out.print(x.key + " ");
        if(x.right != null){
            printInorder(x.right);
        }
    }

    public static void main(String[] args) throws Exception{

        //Part A output
        Node x = null;

        x = TreeInsert(x, 16);
        TreeInsert(x, 5);
        TreeInsert(x, 18);
        TreeInsert(x, 2);
        TreeInsert(x, 15);
        TreeInsert(x, 17);
        TreeInsert(x, 19);
        TreeInsert(x, 1);
        TreeInsert(x, 3);
        TreeInsert(x, 13);
        TreeInsert(x, 12);
        TreeInsert(x, 14);

        printInorder(x);
        System.out.println();

        //Part B output
        Node searcher = null;
        for(int i = 1; i < 21; i++){
            searcher = TreeSearch(x, i);
            if(searcher == null){
                System.out.println(i + ": False");
            }
            else{
                if(searcher.parent != null) {
                    System.out.println("Parent of "+ i+ ": " + searcher.parent.key);
                    System.out.println(i + ": True");
                }
                else{
                    System.out.println("This is the root so... False?");
                }
            }
        }

        //Part C output
        Node success;
        for(int i = 1; i < 21; i++) {
            if (TreeSearch(x, i) == null || Successor(TreeSearch(x, i)) == null) {
                System.out.println("Successor of " + i + ": " + null);
            } else {
                success = Successor(TreeSearch(x, i));
                System.out.println("Successor of " + i + ": " + success.key);
            }
        }
    }
}

//Node class with left and right child, as well as parent node
class Node {
    int key;
    Node left;
    Node right;
    Node parent;

    Node(int key) {
        this.key = key;
        right = null;
        left = null;
        parent = null;
    }
}

