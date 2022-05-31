import java.io.*;
import java.util.ArrayList;

public class A5 {

	static int pointer = -1;	
	static ArrayList tokens = new ArrayList();

	public static void main(String[] args) throws Exception {
		BufferedWriter bw = new BufferedWriter(new FileWriter("a5.output"));
		A5Scanner scanner = new A5Scanner(new FileInputStream(new File("A5.tiny")));
		Symbol token;

		while((token=scanner.yylex()).sym != A5Sym.EOF){
			tokens.add(token);
		}

		tokens.add(token);
		boolean legal = Program() && nextToken().sym == A5Sym.EOF;
		bw.write((legal)?"legal" : "illegal");
		bw.close();
	}
	
	static Symbol nextToken() {
		if (pointer < tokens.size()-1) {
			pointer++;
			Symbol token = (Symbol) tokens.get(pointer);
			System.out.println ("next Token: " + token.toString());
			return token;
		} else
			return null;
	}
	
	static boolean Program() throws Exception{
			int savePointer = pointer;

			if (Method() && ProgramPrime()) {
				System.out.println("      Program-->Method Programs");
				return true;
			}
			pointer = savePointer;
			return false;
	}

	static boolean ProgramPrime() throws Exception{

			int savePointer = pointer;

			if (Method() && ProgramPrime()) {
				System.out.println("      ProgramPrime-->Method ProgramPrime");
				return true;
			}

			pointer = savePointer;
			System.out.println("      ProgramPrime-->Epsilon");
			return true;

	}

	static boolean Method() throws Exception{

			int savePointer = pointer;

			if (Type() && nextToken().sym == A5Sym.MAIN && nextToken().sym == A5Sym.ID 
				&& nextToken().sym == A5Sym.LPAREN && FormalParams() && nextToken().sym == A5Sym.RPAREN && Block()) {
				System.out.println("      Method-->TYPE MAIN ID LPAREN FormalParams RPAREN Block");
				return true;
			}

			pointer = savePointer;

			if (Type() && nextToken().sym == A5Sym.ID 
				&& nextToken().sym == A5Sym.LPAREN && FormalParams() && nextToken().sym == A5Sym.RPAREN && Block()) {
				System.out.println("      Method-->TYPE ID LPAREN FormalParams RPAREN Block");
				return true;
			}
			pointer = savePointer;
			return false;
	}
	
	static boolean Type() throws Exception{

			int savePointer = pointer;
			
			if (nextToken().sym == A5Sym.INT) {
				System.out.println("      Type-->INT");
				return true;
			}

			pointer = savePointer;

			if (nextToken().sym == A5Sym.STRING) {
				System.out.println("      Type-->STRING");
				return true;
			}
			pointer = savePointer;
			
			if (nextToken().sym == A5Sym.REAL) {
				System.out.println("      Type-->REAL");
				return true;
			}
			pointer = savePointer;
			return false;
	}

	static boolean FormalParams() throws Exception{
			int savePointer = pointer;

			if (ParamList()) {
				System.out.println("      FormalParams--> ParamList");
				return true;
			}
			
			pointer = savePointer;
				
			return true;
	}

	static boolean ParamList() throws Exception{
		int savePointer = pointer;
		
		if (FormalParam() && ParamListPrime()) {
			System.out.println("      ParamList-->FormalParam ParamListPrime");
			return true;
		}
		
		pointer = savePointer;
		return false;
		
	}

	static boolean ParamListPrime() throws Exception{
		int savePointer = pointer;

		if (nextToken().sym == A5Sym.COMMA && FormalParam() && ParamListPrime()) {
			System.out.println("      ParamListPrime-->COMMA FormalParam ParamListPrime");
			return true;
		}
		pointer = savePointer;
		System.out.println("      ParamListPrime-->Epsilon");
		return true;
	}

	static boolean FormalParam() throws Exception{
			int savePointer = pointer;

			if (Type() && nextToken().sym == A5Sym.ID) {
				System.out.println("      FormalParam-->TYPE ID");
				return true;
			}
			
			pointer = savePointer;
			return false;
	}

	static boolean Block() throws Exception{
			int savePointer = pointer;

			if (nextToken().sym == A5Sym.BEGIN && StatementList() && nextToken().sym == A5Sym.END) {
				System.out.println("      Block-->BEGIN StatementList END");
				return true;
			}
			
			pointer = savePointer;
			return false;
	}

	static boolean StatementList() throws Exception{
			int savePointer = pointer;

			if (Statement() && StatementPrime()) {
				System.out.println("      StatementList--> statement statementPrime");
				return true;
			}
			
			pointer = savePointer;
			return false;
	}
	
	static boolean StatementPrime() throws Exception{
			int savePointer = pointer;

			if (Statement() && StatementPrime()) {
				System.out.println("      StatementList--> statement statementPrime");
				return true;
			}
			
			pointer = savePointer;
			return true;
	}

	static boolean Statement() throws Exception {
		int savePointer = pointer;
		int nextTok = nextToken().sym;
		if(nextTok == A5Sym.BEGIN){
			pointer = savePointer;
			if (Block()) {
				System.out.println("      Statement-->Block");
				return true;
			}
		}
		pointer = savePointer;
		if(nextTok == A5Sym.ID){
			pointer = savePointer;	
			if (AssignStmt()) {
				System.out.println("      Statement-->AssignStmt");
				return true;
			}
		}
		pointer = savePointer;
		if(Type()){
			pointer = savePointer;	
			if (LocalVar()) {
				System.out.println("      Statement-->LocalVar");
				return true;
			}
		}
		pointer = savePointer;
		if(nextTok == A5Sym.RETURN){
			pointer = savePointer;	
			if (ReturnStmt()) {
				System.out.println("      Statement-->ReturnStmt");
				return true;
			}
		}
		pointer = savePointer;
		if(nextTok == A5Sym.IF){
			pointer = savePointer;
			if (IfStmt()) {
				System.out.println("      Statement-->IfStmt");
				return true;
			}
		}
		pointer = savePointer;
		if(nextTok == A5Sym.WRITE){
			pointer = savePointer;	
			if (WriteStmt()) {
				System.out.println("      Statement-->WriteStmt");
				return true;
			}
		}
		pointer = savePointer;
		if(nextTok == A5Sym.READ){
			pointer = savePointer;
			if (ReadStmt()) {
				System.out.println("      Statement-->ReadStmt");
				return true;
			}
		}
		pointer = savePointer;
		return false;
	}

	static boolean LocalVar() throws Exception{
			int savePointer = pointer;

			if (Type() && nextToken().sym == A5Sym.ID && nextToken().sym == A5Sym.SEMI) {
				System.out.println("      LocalVar-->TYPE ID SEMI");
				return true;
			}
			
			pointer = savePointer;
			
			if (Type() && AssignStmt()) {
				System.out.println("      LocalVar-->TYPE AssignStmt");
				return true;
			}
			
			pointer = savePointer;
			return false;
	}

	static boolean AssignStmt() throws Exception{
			int savePointer = pointer;
			if (nextToken().sym == A5Sym.ID && nextToken().sym == A5Sym.ASSIGN && Expression() && nextToken().sym == A5Sym.SEMI) {
				System.out.println("      AssignStmt--> ID ASSIGN Expression SEMI");
				return true;
			}
			
			pointer = savePointer;
			
			if (nextToken().sym == A5Sym.ID && nextToken().sym == A5Sym.ASSIGN && nextToken().sym == A5Sym.QUOTES && nextToken().sym == A5Sym.SEMI) {
				System.out.println("      AssignStmt--> ID ASSIGN QUOTES SEMI");
				return true;
			}
			
			pointer = savePointer;
			return false;
	}

	static boolean ReturnStmt() throws Exception{
			int savePointer = pointer;

			if (nextToken().sym == A5Sym.RETURN && Expression() && nextToken().sym == A5Sym.SEMI) {
				System.out.println("      ReturnStmt--> RETURN Expression SEMI");
				return true;
			}
			
			pointer = savePointer;
			return false;
	}

	static boolean  IfStmt() throws Exception{
		int savePointer= pointer;
		if (nextToken().sym==A5Sym.IF && nextToken().sym==A5Sym.LPAREN && BoolExpr() && nextToken().sym==A5Sym.RPAREN && Statement() && nextToken().sym==A5Sym.ELSE && Statement()) {
			System.out.println("      IfStmt-->IF LPAREN BoolExpr RPAREN Statement ELSE Statement");
			return true;
		}
		pointer = savePointer;
		if (nextToken().sym==A5Sym.IF && nextToken().sym==A5Sym.LPAREN && BoolExpr() && nextToken().sym==A5Sym.RPAREN && Statement()) {
			System.out.println("      IfStmt-->IF LPAREN BoolExpr RPAREN Statement");
			return true;
		}
		pointer = savePointer;
		return false;
	}

	static boolean WriteStmt() throws Exception{
			int savePointer = pointer;

			if (nextToken().sym == A5Sym.WRITE && nextToken().sym == A5Sym.LPAREN && Expression() && nextToken().sym == A5Sym.COMMA && nextToken().sym == A5Sym.QUOTES && nextToken().sym == A5Sym.RPAREN && nextToken().sym == A5Sym.SEMI) {
				System.out.println("      WriteStmt--> WRITE LPAREN Expression COMMA QUOTES RPAREN SEMI");
				return true;
			}		
			
			pointer = savePointer;
			return false;
	}

	static boolean ReadStmt() throws Exception{
			int savePointer = pointer;

			if (nextToken().sym == A5Sym.READ && nextToken().sym == A5Sym.LPAREN && nextToken().sym == A5Sym.ID && nextToken().sym == A5Sym.COMMA && nextToken().sym == A5Sym.QUOTES && nextToken().sym == A5Sym.RPAREN && nextToken().sym == A5Sym.SEMI) {
				System.out.println("      ReadStmt--> READ LPAREN ID COMMA QUOTES RPAREN SEMI");
				return true;
			}		
			
			pointer = savePointer;
			return false;
	}

	static boolean Expression() throws Exception{
			int savePointer = pointer;

			if (MultiplicativeExpr() && _Expression()) {
				System.out.println("      Expression--> MultiplicativeExpr _Expression");
				return true;
			}		
			
			pointer = savePointer;
			return false;
	}

	static boolean _Expression() throws Exception{
			int savePointer = pointer;

			if (nextToken().sym == A5Sym.PLUS && MultiplicativeExpr() && _Expression()) {
				System.out.println("      _Expression--> PLUS MultiplicativeExpr _Expression");
				return true;
			}		
			
			pointer = savePointer;
			
			if (nextToken().sym == A5Sym.MINUS && MultiplicativeExpr() && _Expression()) {
				System.out.println("      _Expression--> MINUS MultiplicativeExpr _Expression");
				return true;
			}

			pointer = savePointer;
			
			if (nextToken().sym == A5Sym.EQUAL && BoolExpr()) {
				System.out.println("      _Expression--> EQUAL BoolExpr");
				return true;
			}
			
			pointer = savePointer;
			
			if (nextToken().sym == A5Sym.NOTEQUAL && BoolExpr()) {
				System.out.println("      _Expression--> NOTEQUAL BoolExpr");
				return true;
			}
			
			pointer = savePointer;
			return true;
	}

	static boolean MultiplicativeExpr() throws Exception{
			int savePointer = pointer;

			if (PrimaryExpr() && _PrimaryExpr()) {
				System.out.println("      MultiplicativeExpr--> PrimaryExpr _PrimaryExpr");
				return true;
			}		
			
			pointer = savePointer;
			return false;
	}

	static boolean PrimaryExpr() throws Exception {
	    int savedPointer=pointer;
	    if (nextToken().sym == A5Sym.ID && nextToken().sym == A5Sym.LPAREN && ActualParams() && nextToken().sym == A5Sym.RPAREN){ 
	    	System.out.println("      PrimaryExpr-->ID LPAREN ActualParams RPAREN");
	    	return true;
	    }
	    pointer = savedPointer;
	    if (nextToken().sym == A5Sym.LPAREN && Expression() && nextToken().sym == A5Sym.RPAREN){ 
	    	System.out.println("      PrimaryExpr-->LPAREN Expr RPAREN");
	    	return true;
	    }
	    pointer = savedPointer;
	    int sym=nextToken().sym;
		if (sym == A5Sym.NUMBER || sym == A5Sym.ID || sym == A5Sym.TRUE || sym == A5Sym.FALSE) {
			System.out.println("      PrimaryExpr-->NUMBER | ID | TRUE | FALSE");
			return true;
		}
		pointer = savedPointer;
		return false;
	}

	static boolean _PrimaryExpr() throws Exception{
			int savePointer = pointer;

			if (nextToken().sym == A5Sym.TIMES && PrimaryExpr() && _PrimaryExpr()) {
				System.out.println("      MultiplicativeExpr--> TIMES PrimaryExpr _PrimaryExpr");
				return true;
			}		
			
			pointer = savePointer;
			
			if (nextToken().sym == A5Sym.DIVIDE && PrimaryExpr() && _PrimaryExpr()) {
				System.out.println("      MultiplicativeExpr--> DIVIDE PrimaryExpr _PrimaryExpr");
				return true;
			}		
			
			pointer = savePointer;
			return true;
	}

	static boolean BoolExpr() throws Exception{
			int savePointer = pointer;

			if (Expression()) {
				System.out.println("      BoolExpr--> Expression");
				return true;
			}		
						
			pointer = savePointer;
			return false;
	}

	static boolean ActualParams() throws Exception{
			int savePointer = pointer;

			if (_ActualParams()) {
				System.out.println("      ActualParams-->_ActualParams");
				return true;
			}		
						
			pointer = savePointer;
			return true;
	}

	static boolean _ActualParams() throws Exception {
		int savePointer = pointer;
		if (Expression() && nextToken().sym==A5Sym.COMMA  && _ActualParams()){
			System.out.println("      ActualParamsPrime--> Expression COMMA ActualParamsPrime");
			return true;
		}
		pointer = savePointer;
		if (Expression()){
			System.out.println("      ActualParamsPrime--> Expression");
			return true;	
		}
		pointer = savePointer;
		return false;
	}
}

