java JLex.Main A3.lex
java java_cup.Main -parser A3Parser -symbols A3Symbol < A3.cup 
javac A3.lex.java A3Parser.java A3Symbol.java A3ParserUser.java
java A3ParserUser