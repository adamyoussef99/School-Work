public class Simulator {
    public static boolean run(DFA dfa, String input) {
        String s = dfa.startState;
        for(char c: input.toCharArray()){
            s = move(s, c);
        }
        return dfa.finalStates.contains(s);
    }

    public static String move(String current_state, char next_char){
        return DFA.transitions.get(current_state+"_"+next_char);
    }
}