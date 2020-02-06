package org.processmining.AOPM.parser;
// Generated from CDL.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CDLParser}.
 */
public interface CDLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CDLParser#negation}.
	 * @param ctx the parse tree
	 */
	void enterNegation(@NotNull CDLParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#negation}.
	 * @param ctx the parse tree
	 */
	void exitNegation(@NotNull CDLParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#varName}.
	 * @param ctx the parse tree
	 */
	void enterVarName(@NotNull CDLParser.VarNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#varName}.
	 * @param ctx the parse tree
	 */
	void exitVarName(@NotNull CDLParser.VarNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#evaluatePredicate}.
	 * @param ctx the parse tree
	 */
	void enterEvaluatePredicate(@NotNull CDLParser.EvaluatePredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#evaluatePredicate}.
	 * @param ctx the parse tree
	 */
	void exitEvaluatePredicate(@NotNull CDLParser.EvaluatePredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#unaryPredicate}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPredicate(@NotNull CDLParser.UnaryPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#unaryPredicate}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPredicate(@NotNull CDLParser.UnaryPredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#eos}.
	 * @param ctx the parse tree
	 */
	void enterEos(@NotNull CDLParser.EosContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#eos}.
	 * @param ctx the parse tree
	 */
	void exitEos(@NotNull CDLParser.EosContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#validatePredicate}.
	 * @param ctx the parse tree
	 */
	void enterValidatePredicate(@NotNull CDLParser.ValidatePredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#validatePredicate}.
	 * @param ctx the parse tree
	 */
	void exitValidatePredicate(@NotNull CDLParser.ValidatePredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#unaryPredicateName}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPredicateName(@NotNull CDLParser.UnaryPredicateNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#unaryPredicateName}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPredicateName(@NotNull CDLParser.UnaryPredicateNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#acquireQuery}.
	 * @param ctx the parse tree
	 */
	void enterAcquireQuery(@NotNull CDLParser.AcquireQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#acquireQuery}.
	 * @param ctx the parse tree
	 */
	void exitAcquireQuery(@NotNull CDLParser.AcquireQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#constraintName}.
	 * @param ctx the parse tree
	 */
	void enterConstraintName(@NotNull CDLParser.ConstraintNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#constraintName}.
	 * @param ctx the parse tree
	 */
	void exitConstraintName(@NotNull CDLParser.ConstraintNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#binaryPredicate}.
	 * @param ctx the parse tree
	 */
	void enterBinaryPredicate(@NotNull CDLParser.BinaryPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#binaryPredicate}.
	 * @param ctx the parse tree
	 */
	void exitBinaryPredicate(@NotNull CDLParser.BinaryPredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(@NotNull CDLParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(@NotNull CDLParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#relationalPredicate}.
	 * @param ctx the parse tree
	 */
	void enterRelationalPredicate(@NotNull CDLParser.RelationalPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#relationalPredicate}.
	 * @param ctx the parse tree
	 */
	void exitRelationalPredicate(@NotNull CDLParser.RelationalPredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#funcName}.
	 * @param ctx the parse tree
	 */
	void enterFuncName(@NotNull CDLParser.FuncNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#funcName}.
	 * @param ctx the parse tree
	 */
	void exitFuncName(@NotNull CDLParser.FuncNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#setPredicate}.
	 * @param ctx the parse tree
	 */
	void enterSetPredicate(@NotNull CDLParser.SetPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#setPredicate}.
	 * @param ctx the parse tree
	 */
	void exitSetPredicate(@NotNull CDLParser.SetPredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#acqName}.
	 * @param ctx the parse tree
	 */
	void enterAcqName(@NotNull CDLParser.AcqNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#acqName}.
	 * @param ctx the parse tree
	 */
	void exitAcqName(@NotNull CDLParser.AcqNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#entityName}.
	 * @param ctx the parse tree
	 */
	void enterEntityName(@NotNull CDLParser.EntityNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#entityName}.
	 * @param ctx the parse tree
	 */
	void exitEntityName(@NotNull CDLParser.EntityNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#proposition}.
	 * @param ctx the parse tree
	 */
	void enterProposition(@NotNull CDLParser.PropositionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#proposition}.
	 * @param ctx the parse tree
	 */
	void exitProposition(@NotNull CDLParser.PropositionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#truthValue}.
	 * @param ctx the parse tree
	 */
	void enterTruthValue(@NotNull CDLParser.TruthValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#truthValue}.
	 * @param ctx the parse tree
	 */
	void exitTruthValue(@NotNull CDLParser.TruthValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#objectName}.
	 * @param ctx the parse tree
	 */
	void enterObjectName(@NotNull CDLParser.ObjectNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#objectName}.
	 * @param ctx the parse tree
	 */
	void exitObjectName(@NotNull CDLParser.ObjectNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#constraint}.
	 * @param ctx the parse tree
	 */
	void enterConstraint(@NotNull CDLParser.ConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#constraint}.
	 * @param ctx the parse tree
	 */
	void exitConstraint(@NotNull CDLParser.ConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#relevantEntityName}.
	 * @param ctx the parse tree
	 */
	void enterRelevantEntityName(@NotNull CDLParser.RelevantEntityNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#relevantEntityName}.
	 * @param ctx the parse tree
	 */
	void exitRelevantEntityName(@NotNull CDLParser.RelevantEntityNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CDLParser#binaryPredicateName}.
	 * @param ctx the parse tree
	 */
	void enterBinaryPredicateName(@NotNull CDLParser.BinaryPredicateNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CDLParser#binaryPredicateName}.
	 * @param ctx the parse tree
	 */
	void exitBinaryPredicateName(@NotNull CDLParser.BinaryPredicateNameContext ctx);
}