import java.util.TreeMap;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.io.*;

public class DFA {
    public static String [] alphabet;
    public String startState;
    public Set<String> finalStates;

    public static TreeMap<String, String> transitions=new TreeMap<String, String>();

    /** Construct a DFA from a text file
     */
    public DFA(String filename) throws Exception{
        BufferedReader br  = new BufferedReader(new FileReader(filename));
        alphabet=br.readLine().trim().split(" ");
        String[] states=br.readLine().split(" ");
        startState=br.readLine().trim();
        String[] finals=br.readLine().trim().split(" ");
        finalStates= new HashSet<>(Arrays.asList(finals));
        String line="";
        while ((line=br.readLine())!=null) {
            String[] transition=line.trim().split(" ");
            transitions.put(transition[0]+"_"+transition[1], transition[2]);
        }
    }

    public static void main(String[] args) throws Exception{
        DFA dfa = new DFA(args[0]);
        String input = new BufferedReader(new FileReader(args[1])).readLine().strip();
        boolean result=Simulator.run(dfa,input);
        BufferedWriter bw=new BufferedWriter(new FileWriter(args[2]));
        bw.write(result+"");
        bw.close();
        System.out.println(input+"\t"+result);
    }
}