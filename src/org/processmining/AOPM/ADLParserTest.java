package org.processmining.AOPM;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.processmining.AOPM.models.ActionFormula;
import org.processmining.AOPM.parser.ADLLexer;
import org.processmining.AOPM.parser.ADLListenerImpl;
import org.processmining.AOPM.parser.ADLParser;

@SuppressWarnings("deprecation")
public class ADLParserTest
{

	public static void main(String[] args) throws Exception
	{
	    String content = new Scanner(new File("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/adlExample.adl")).useDelimiter("\\Z").next();
	    System.out.println( "JSON File:\n" + content + "\n\n");

		ANTLRInputStream input = new ANTLRInputStream( content );

		ADLLexer lexer = new ADLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		ADLParser parser = new ADLParser(tokens);

		ParseTree tree = parser.action();

		System.out.println( "ParseTree:\n" + tree.toStringTree( parser ) + "\n");

		Map<String, Map<String, Object>> actionMap = new LinkedHashMap<String, Map<String, Object>>();
//
		ADLListenerImpl listener = new ADLListenerImpl(actionMap);
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(listener, tree);
		Map<String, ActionFormula> cfMap = listener.genafMap(actionMap);
		System.out.println(cfMap);
//		Gson gson = new Gson();
//		String json = gson.toJson(actionMap);
//		System.out.println(json);
	}
}