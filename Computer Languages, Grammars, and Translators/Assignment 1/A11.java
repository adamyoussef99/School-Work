import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Set;
import java.util.HashSet;

public class A11 {
    //isIdentifier whether the char is a letter
    static boolean isLetter(int character) {
        return (character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z');
    }

    // isIdentifier whether the char is a letter or digit
    static boolean isLetterOrDigit(int character) {
        return isLetter(character) || (character >= '0' && character <= '9');
    }

    public static Set<String> getIdentifiers(String filename) throws Exception{

        String[] keywordsArray = { "IF", "WRITE", "READ", "RETURN", "BEGIN",
                "END", "MAIN", "INT", "REAL" };
        Set<String> keywords = new HashSet();
        Set<String> identifiers = new HashSet();

        boolean isIdentifier = true;
        Set<String> quoted = new HashSet();

        for (String s : keywordsArray) {;
            keywords.add(s);
        }

        String state="INIT"; // Initially it is in the INIT state.

        StringBuilder code = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            code=code.append(line+"\n");
        } // read the text line by line.
        code =code.append('$'); //add a special symbol to indicate the end of file.

        int len=code.length();

        String token="";

        for (int i=0; i<len; i++) { //look at the characters one by one
            char next_char=code.charAt(i);

            if (state.contentEquals("INIT")){

                if(next_char=='"'){
                    state = "QUOTE";
                }

                if (isLetter(next_char)){
                    state="ID";  // go to the ID state
                    token=token+next_char;
                } //ignore everything if it is not a letter

            }else if (state.equals("ID")) {
                if (isLetterOrDigit(next_char)) { //take letter or digit if it is in ID state
                    token=token+next_char;
                } else{ // end of ID state
                    for(String k:keywords){
                        if(k.equals(token)){
                            isIdentifier = false;
                        }
                    }
                    if(isIdentifier==true){
                        identifiers.add(token);
                    }
                    token="";
                    state="INIT";
                    isIdentifier = true;
                }
            }else if (state.equals("QUOTE")){
                if(next_char == '"'){
                    state="INIT";
                }
            }
        }

        return identifiers;
    }

    public static void main(String[] args) throws Exception{
        Set<String> ids=getIdentifiers("A1.tiny");
        for (String id:ids) System.out.println(id);
    }
}