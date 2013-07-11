import net.digitalprimates.TypeScriptBaseListener;
import net.digitalprimates.TypeScriptLexer;
import net.digitalprimates.TypeScriptParser;
import net.digitalprimates.translators.actionscript.TypeScriptToAS;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;

public class Main extends TypeScriptBaseListener {

    //NOTE:: This is based off of the ANTLR4 boilerplate in The Definitive ANTLR 4 Reference, p. 42.
    public static void main(String[] args) throws Exception {
        //NOTE:: The hardcoding here is for testing/debugging in IntelliJ only.
        FileReader fileReader = new FileReader( System.getProperty("user.dir") + ".\\src\\ExtJSsmall.d.ts" );
        // create a CharStream that reads from standard input
        ANTLRInputStream input = new ANTLRInputStream( fileReader );

        //TODO:: Make it so that the file reader can accept an input from standard input so we can run from the command line.
//        ANTLRInputStream input = new ANTLRInputStream(System.in);

        // create a lexer that feeds off of input CharStream
        TypeScriptLexer lexer = new TypeScriptLexer(input);

        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // create a parser that feeds off the tokens buffer
        TypeScriptParser parser = new TypeScriptParser(tokens);

        ParseTree tree = parser.program(); // begin parsing at program rule
//        System.out.println( tree.toStringTree(parser)); // print LISP-style tree

        // Create a generic parse tree walker that can trigger callbacks
        ParseTreeWalker walker = new ParseTreeWalker();

        // Walk the tree created during the parse, trigger callbacks
        walker.walk(new TypeScriptToAS(), tree);
    }
}
