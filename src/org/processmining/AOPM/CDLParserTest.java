package org.processmining.AOPM;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.processmining.AOPM.parser.CDLLexer;
import org.processmining.AOPM.parser.CDLListenerImpl;
import org.processmining.AOPM.parser.CDLParser;

import com.google.gson.Gson;
 
@SuppressWarnings("deprecation")
public class CDLParserTest
{
 
	public static void main(String[] args) throws Exception 
	{
	    String content = new Scanner(new File("/Users/GYUNAM/Documents/Workshop/src/org/processmining/AOPM/cdlExample.cdl")).useDelimiter("\\Z").next();
	    System.out.println( "JSON File:\n" + content + "\n\n");
	    
		ANTLRInputStream input = new ANTLRInputStream( content );
		
		CDLLexer lexer = new CDLLexer(input);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		CDLParser parser = new CDLParser(tokens);
		
		ParseTree tree = parser.constraint();
		
		System.out.println( "ParseTree:\n" + tree.toStringTree( parser ) + "\n"); 
		
		Map<String, Map<String, Map<String,List<String>>>> constraintMap = new LinkedHashMap<String, Map<String, Map<String,List<String>>>>();
		
		CDLListenerImpl listener = new CDLListenerImpl(constraintMap);
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(listener, tree);
		System.out.println(constraintMap);
		Gson gson = new Gson(); 
		String json = gson.toJson(constraintMap); 
		System.out.println(json);
	}
}