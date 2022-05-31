import java_cup.runtime.*;
import java.io.*;
%%

%implements java_cup.runtime.Scanner
%type Symbol
%function next_token
%class A3Scanner
%eofval{ return null;
%eofval}

IDENTIFIER = [a-zA-Z][a-zA-Z0-9]*
NUMBER = [0-9]+
QUOTES = \"[^\"]*\"
%%
"+" { return new Symbol(A3Symbol.PLUS, yytext()); }
"-" { return new Symbol(A3Symbol.MINUS, yytext()); }
"*" { return new Symbol(A3Symbol.TIMES, yytext()); }
"/" { return new Symbol(A3Symbol.DIVIDE, yytext()); }
"=" { return new Symbol(A3Symbol.EQUAL, yytext()); }
"(" { return new Symbol(A3Symbol.LPAREN, yytext()); }
")" { return new Symbol(A3Symbol.RPAREN, yytext()); }
"," { return new Symbol(A3Symbol.COMMA, yytext()); }
{IDENTIFIER} {return new Symbol(A3Symbol.ID, yytext()); }
{NUMBER} { return new Symbol(A3Symbol.NUMBER, new Integer(yytext()));}
\r|\n|\t|" " {}
. {}