package org.processmining.AOPM;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.processmining.AOPM.models.ConstraintFormula;
import org.processmining.AOPM.parser.CDLLexer;
import org.processmining.AOPM.parser.CDLListenerImpl;
import org.processmining.AOPM.parser.CDLParser;

@SuppressWarnings("deprecation")
public class CDLParserTest
{
	

	public static void main(String[] args) throws Exception
	{
	    String content = new Scanner(new File("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/cdlExample.cdl")).useDelimiter("\\Z").next();
	    System.out.println( "JSON File:\n" + content + "\n\n");

		ANTLRInputStream input = new ANTLRInputStream( content );

		CDLLexer lexer = new CDLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		CDLParser parser = new CDLParser(tokens);

		ParseTree tree = parser.constraint();

		System.out.println( "ParseTree:\n" + tree.toStringTree( parser ) + "\n");

		Map<String, Map<String, Object>> constraintMap = new LinkedHashMap<String, Map<String, Object>>();

		CDLListenerImpl listener = new CDLListenerImpl(constraintMap);
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(listener, tree);
		System.out.println(constraintMap);
		Map<String, ConstraintFormula> cfMap = listener.gencfMap(constraintMap);
		System.out.println(cfMap);
//		Gson gson = new Gson();
//		String json = gson.toJson(constraintMap);
//		System.out.println(json);
	}
}