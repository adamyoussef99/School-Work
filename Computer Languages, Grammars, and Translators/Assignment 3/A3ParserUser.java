import java.io.*;
class A3ParserUser {
	public static void main(String[]args) throws Exception {
		File inputFile = new File ("A3.tiny") ;
		A3Parser parser = new A3Parser (new A3Scanner(new FileInputStream (inputFile)));
		Integer result =(Integer) parser.debug_parse().value;
		FileWriter fw =new FileWriter (new File("A3.output"));
		fw.write(" Number of methods : " + result.intValue());
		fw.close();
	}
}