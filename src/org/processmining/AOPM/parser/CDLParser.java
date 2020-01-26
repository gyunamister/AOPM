package org.processmining.AOPM.parser;
// Generated from CDL.g4 by ANTLR 4.4
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CDLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, VARIABLE_NAME=2, STRING=3, NUMBER=4, DEFINE=5, AS=6, CONTEXTUALIZE=7, 
		EVALUATE=8, ACQUIRE=9, OBJECT=10, ENTITY=11, CONTEXT=12, CONDITION=13, 
		CALCULATE=14, ACQUIRED_BY=15, IN=16, LP=17, RP=18, LB=19, RB=20, LSB=21, 
		RSB=22, LTB=23, RTB=24, DQ=25, SEP=26, ASSIGN=27, TILDE=28, NOT=29, AND=30, 
		OR=31, TRUE=32, FALSE=33, OCCUR=34, NEXT=35, EVENTUALLY=36, WS=37;
	public static final String[] tokenNames = {
		"<INVALID>", "';'", "VARIABLE_NAME", "STRING", "NUMBER", "'DEFINE'", "'AS'", 
		"'CONTEXTUALIZE'", "'EVALUATE'", "'ACQUIRE'", "'OBJECT'", "'ENTITY'", 
		"'CONTEXT'", "'CONDITION'", "'CALCULATE'", "'ACQUIRED BY'", "'IN'", "'('", 
		"')'", "'{'", "'}'", "'['", "']'", "'<'", "'>'", "'\"'", "','", "'='", 
		"'~'", "'NOT'", "'AND'", "'OR'", "'TRUE'", "'FALSE'", "'OCCUR'", "'NEXT'", 
		"'EVENTUALLY'", "WS"
	};
	public static final int
		RULE_constraint = 0, RULE_contextualizeQuery = 1, RULE_evaluateQuery = 2, 
		RULE_acquireQuery = 3, RULE_constraintName = 4, RULE_objectName = 5, RULE_entityName = 6, 
		RULE_acqName = 7, RULE_contextPredicate = 8, RULE_evaluatePredicate = 9, 
		RULE_predicate = 10, RULE_proposition = 11, RULE_unaryPredicate = 12, 
		RULE_unaryPredicateName = 13, RULE_binaryPredicate = 14, RULE_binaryPredicateName = 15, 
		RULE_truthValue = 16, RULE_negation = 17, RULE_relationalPredicate = 18, 
		RULE_setPredicate = 19, RULE_varName = 20, RULE_funcName = 21, RULE_eos = 22;
	public static final String[] ruleNames = {
		"constraint", "contextualizeQuery", "evaluateQuery", "acquireQuery", "constraintName", 
		"objectName", "entityName", "acqName", "contextPredicate", "evaluatePredicate", 
		"predicate", "proposition", "unaryPredicate", "unaryPredicateName", "binaryPredicate", 
		"binaryPredicateName", "truthValue", "negation", "relationalPredicate", 
		"setPredicate", "varName", "funcName", "eos"
	};

	@Override
	public String getGrammarFileName() { return "CDL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CDLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ConstraintContext extends ParserRuleContext {
		public TerminalNode ACQUIRE(int i) {
			return getToken(CDLParser.ACQUIRE, i);
		}
		public TerminalNode AS(int i) {
			return getToken(CDLParser.AS, i);
		}
		public TerminalNode EVALUATE(int i) {
			return getToken(CDLParser.EVALUATE, i);
		}
		public AcquireQueryContext acquireQuery(int i) {
			return getRuleContext(AcquireQueryContext.class,i);
		}
		public TerminalNode CONTEXTUALIZE(int i) {
			return getToken(CDLParser.CONTEXTUALIZE, i);
		}
		public List<EosContext> eos() {
			return getRuleContexts(EosContext.class);
		}
		public EosContext eos(int i) {
			return getRuleContext(EosContext.class,i);
		}
		public List<ConstraintNameContext> constraintName() {
			return getRuleContexts(ConstraintNameContext.class);
		}
		public List<TerminalNode> AS() { return getTokens(CDLParser.AS); }
		public ContextualizeQueryContext contextualizeQuery(int i) {
			return getRuleContext(ContextualizeQueryContext.class,i);
		}
		public TerminalNode DEFINE(int i) {
			return getToken(CDLParser.DEFINE, i);
		}
		public List<TerminalNode> CONTEXTUALIZE() { return getTokens(CDLParser.CONTEXTUALIZE); }
		public List<AcquireQueryContext> acquireQuery() {
			return getRuleContexts(AcquireQueryContext.class);
		}
		public EvaluateQueryContext evaluateQuery(int i) {
			return getRuleContext(EvaluateQueryContext.class,i);
		}
		public List<TerminalNode> DEFINE() { return getTokens(CDLParser.DEFINE); }
		public List<EvaluateQueryContext> evaluateQuery() {
			return getRuleContexts(EvaluateQueryContext.class);
		}
		public List<TerminalNode> ACQUIRE() { return getTokens(CDLParser.ACQUIRE); }
		public ConstraintNameContext constraintName(int i) {
			return getRuleContext(ConstraintNameContext.class,i);
		}
		public List<TerminalNode> EVALUATE() { return getTokens(CDLParser.EVALUATE); }
		public List<ContextualizeQueryContext> contextualizeQuery() {
			return getRuleContexts(ContextualizeQueryContext.class);
		}
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitConstraint(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46); match(DEFINE);
			setState(47); constraintName();
			setState(48); match(AS);
			setState(49); match(CONTEXTUALIZE);
			setState(50); contextualizeQuery();
			setState(51); match(EVALUATE);
			setState(52); evaluateQuery();
			setState(55);
			_la = _input.LA(1);
			if (_la==ACQUIRE) {
				{
				setState(53); match(ACQUIRE);
				setState(54); acquireQuery();
				}
			}

			setState(57); eos();
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DEFINE) {
				{
				{
				setState(58); match(DEFINE);
				setState(59); constraintName();
				setState(60); match(AS);
				setState(61); match(CONTEXTUALIZE);
				setState(62); contextualizeQuery();
				setState(63); match(EVALUATE);
				setState(64); evaluateQuery();
				setState(67);
				_la = _input.LA(1);
				if (_la==ACQUIRE) {
					{
					setState(65); match(ACQUIRE);
					setState(66); acquireQuery();
					}
				}

				setState(69); eos();
				}
				}
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContextualizeQueryContext extends ParserRuleContext {
		public TerminalNode ENTITY() { return getToken(CDLParser.ENTITY, 0); }
		public TerminalNode OBJECT() { return getToken(CDLParser.OBJECT, 0); }
		public List<ObjectNameContext> objectName() {
			return getRuleContexts(ObjectNameContext.class);
		}
		public List<TerminalNode> SEP() { return getTokens(CDLParser.SEP); }
		public ContextPredicateContext contextPredicate() {
			return getRuleContext(ContextPredicateContext.class,0);
		}
		public EntityNameContext entityName() {
			return getRuleContext(EntityNameContext.class,0);
		}
		public TerminalNode SEP(int i) {
			return getToken(CDLParser.SEP, i);
		}
		public TerminalNode CONTEXT() { return getToken(CDLParser.CONTEXT, 0); }
		public ObjectNameContext objectName(int i) {
			return getRuleContext(ObjectNameContext.class,i);
		}
		public ContextualizeQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contextualizeQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterContextualizeQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitContextualizeQuery(this);
		}
	}

	public final ContextualizeQueryContext contextualizeQuery() throws RecognitionException {
		ContextualizeQueryContext _localctx = new ContextualizeQueryContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_contextualizeQuery);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); match(ENTITY);
			setState(77); entityName();
			setState(78); match(OBJECT);
			setState(79); objectName();
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEP) {
				{
				{
				setState(80); match(SEP);
				setState(81); objectName();
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(89);
			_la = _input.LA(1);
			if (_la==CONTEXT) {
				{
				setState(87); match(CONTEXT);
				setState(88); contextPredicate();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EvaluateQueryContext extends ParserRuleContext {
		public EvaluatePredicateContext evaluatePredicate() {
			return getRuleContext(EvaluatePredicateContext.class,0);
		}
		public TerminalNode CONDITION() { return getToken(CDLParser.CONDITION, 0); }
		public EvaluateQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_evaluateQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterEvaluateQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitEvaluateQuery(this);
		}
	}

	public final EvaluateQueryContext evaluateQuery() throws RecognitionException {
		EvaluateQueryContext _localctx = new EvaluateQueryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_evaluateQuery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); match(CONDITION);
			setState(92); evaluatePredicate();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AcquireQueryContext extends ParserRuleContext {
		public TerminalNode CALCULATE() { return getToken(CDLParser.CALCULATE, 0); }
		public AcqNameContext acqName() {
			return getRuleContext(AcqNameContext.class,0);
		}
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public AcquireQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acquireQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterAcquireQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitAcquireQuery(this);
		}
	}

	public final AcquireQueryContext acquireQuery() throws RecognitionException {
		AcquireQueryContext _localctx = new AcquireQueryContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_acquireQuery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94); acqName();
			setState(95); match(CALCULATE);
			setState(96); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public ConstraintNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraintName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterConstraintName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitConstraintName(this);
		}
	}

	public final ConstraintNameContext constraintName() throws RecognitionException {
		ConstraintNameContext _localctx = new ConstraintNameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_constraintName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public ObjectNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterObjectName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitObjectName(this);
		}
	}

	public final ObjectNameContext objectName() throws RecognitionException {
		ObjectNameContext _localctx = new ObjectNameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_objectName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EntityNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public EntityNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entityName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterEntityName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitEntityName(this);
		}
	}

	public final EntityNameContext entityName() throws RecognitionException {
		EntityNameContext _localctx = new EntityNameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_entityName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AcqNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public AcqNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acqName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterAcqName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitAcqName(this);
		}
	}

	public final AcqNameContext acqName() throws RecognitionException {
		AcqNameContext _localctx = new AcqNameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_acqName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContextPredicateContext extends ParserRuleContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public ContextPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contextPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterContextPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitContextPredicate(this);
		}
	}

	public final ContextPredicateContext contextPredicate() throws RecognitionException {
		ContextPredicateContext _localctx = new ContextPredicateContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_contextPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106); predicate();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EvaluatePredicateContext extends ParserRuleContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public EvaluatePredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_evaluatePredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterEvaluatePredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitEvaluatePredicate(this);
		}
	}

	public final EvaluatePredicateContext evaluatePredicate() throws RecognitionException {
		EvaluatePredicateContext _localctx = new EvaluatePredicateContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_evaluatePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108); predicate();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitPredicate(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); proposition();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropositionContext extends ParserRuleContext {
		public BinaryPredicateContext binaryPredicate() {
			return getRuleContext(BinaryPredicateContext.class,0);
		}
		public TruthValueContext truthValue() {
			return getRuleContext(TruthValueContext.class,0);
		}
		public NegationContext negation() {
			return getRuleContext(NegationContext.class,0);
		}
		public RelationalPredicateContext relationalPredicate() {
			return getRuleContext(RelationalPredicateContext.class,0);
		}
		public UnaryPredicateContext unaryPredicate() {
			return getRuleContext(UnaryPredicateContext.class,0);
		}
		public SetPredicateContext setPredicate() {
			return getRuleContext(SetPredicateContext.class,0);
		}
		public PropositionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_proposition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterProposition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitProposition(this);
		}
	}

	public final PropositionContext proposition() throws RecognitionException {
		PropositionContext _localctx = new PropositionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_proposition);
		try {
			setState(118);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(112); unaryPredicate();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(113); binaryPredicate();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(114); setPredicate();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(115); truthValue();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(116); negation();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(117); relationalPredicate();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryPredicateContext extends ParserRuleContext {
		public TerminalNode RP() { return getToken(CDLParser.RP, 0); }
		public UnaryPredicateNameContext unaryPredicateName() {
			return getRuleContext(UnaryPredicateNameContext.class,0);
		}
		public TerminalNode LP() { return getToken(CDLParser.LP, 0); }
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public UnaryPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterUnaryPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitUnaryPredicate(this);
		}
	}

	public final UnaryPredicateContext unaryPredicate() throws RecognitionException {
		UnaryPredicateContext _localctx = new UnaryPredicateContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_unaryPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120); unaryPredicateName();
			setState(121); match(LP);
			setState(122); match(STRING);
			setState(123); match(RP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryPredicateNameContext extends ParserRuleContext {
		public TerminalNode OCCUR() { return getToken(CDLParser.OCCUR, 0); }
		public UnaryPredicateNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryPredicateName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterUnaryPredicateName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitUnaryPredicateName(this);
		}
	}

	public final UnaryPredicateNameContext unaryPredicateName() throws RecognitionException {
		UnaryPredicateNameContext _localctx = new UnaryPredicateNameContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_unaryPredicateName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125); match(OCCUR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryPredicateContext extends ParserRuleContext {
		public TerminalNode STRING(int i) {
			return getToken(CDLParser.STRING, i);
		}
		public BinaryPredicateNameContext binaryPredicateName() {
			return getRuleContext(BinaryPredicateNameContext.class,0);
		}
		public List<TerminalNode> SEP() { return getTokens(CDLParser.SEP); }
		public List<TerminalNode> STRING() { return getTokens(CDLParser.STRING); }
		public TerminalNode SEP(int i) {
			return getToken(CDLParser.SEP, i);
		}
		public BinaryPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterBinaryPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitBinaryPredicate(this);
		}
	}

	public final BinaryPredicateContext binaryPredicate() throws RecognitionException {
		BinaryPredicateContext _localctx = new BinaryPredicateContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_binaryPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127); match(STRING);
			setState(128); match(SEP);
			setState(129); binaryPredicateName();
			setState(130); match(SEP);
			setState(131); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryPredicateNameContext extends ParserRuleContext {
		public TerminalNode NEXT() { return getToken(CDLParser.NEXT, 0); }
		public TerminalNode EVENTUALLY() { return getToken(CDLParser.EVENTUALLY, 0); }
		public BinaryPredicateNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryPredicateName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterBinaryPredicateName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitBinaryPredicateName(this);
		}
	}

	public final BinaryPredicateNameContext binaryPredicateName() throws RecognitionException {
		BinaryPredicateNameContext _localctx = new BinaryPredicateNameContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_binaryPredicateName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			_la = _input.LA(1);
			if ( !(_la==NEXT || _la==EVENTUALLY) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TruthValueContext extends ParserRuleContext {
		public TerminalNode FALSE() { return getToken(CDLParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(CDLParser.TRUE, 0); }
		public TruthValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_truthValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterTruthValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitTruthValue(this);
		}
	}

	public final TruthValueContext truthValue() throws RecognitionException {
		TruthValueContext _localctx = new TruthValueContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_truthValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NegationContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(CDLParser.NOT, 0); }
		public PropositionContext proposition() {
			return getRuleContext(PropositionContext.class,0);
		}
		public NegationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_negation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitNegation(this);
		}
	}

	public final NegationContext negation() throws RecognitionException {
		NegationContext _localctx = new NegationContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_negation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137); match(NOT);
			setState(138); proposition();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationalPredicateContext extends ParserRuleContext {
		public FuncNameContext funcName() {
			return getRuleContext(FuncNameContext.class,0);
		}
		public List<TerminalNode> SEP() { return getTokens(CDLParser.SEP); }
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(CDLParser.NUMBER, 0); }
		public TerminalNode SEP(int i) {
			return getToken(CDLParser.SEP, i);
		}
		public TerminalNode ACQUIRED_BY() { return getToken(CDLParser.ACQUIRED_BY, 0); }
		public RelationalPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterRelationalPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitRelationalPredicate(this);
		}
	}

	public final RelationalPredicateContext relationalPredicate() throws RecognitionException {
		RelationalPredicateContext _localctx = new RelationalPredicateContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_relationalPredicate);
		int _la;
		try {
			setState(154);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(140); match(STRING);
				setState(141); match(SEP);
				setState(142);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LTB) | (1L << RTB) | (1L << ASSIGN))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(143); match(SEP);
				setState(144); match(NUMBER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(145); match(STRING);
				setState(146); match(SEP);
				setState(147); match(ACQUIRED_BY);
				setState(148); funcName();
				setState(149); match(SEP);
				setState(150);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LTB) | (1L << RTB) | (1L << ASSIGN))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(151); match(SEP);
				setState(152); match(NUMBER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetPredicateContext extends ParserRuleContext {
		public VarNameContext varName() {
			return getRuleContext(VarNameContext.class,0);
		}
		public List<TerminalNode> SEP() { return getTokens(CDLParser.SEP); }
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public TerminalNode RB() { return getToken(CDLParser.RB, 0); }
		public TerminalNode SEP(int i) {
			return getToken(CDLParser.SEP, i);
		}
		public TerminalNode LB() { return getToken(CDLParser.LB, 0); }
		public TerminalNode IN() { return getToken(CDLParser.IN, 0); }
		public SetPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterSetPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitSetPredicate(this);
		}
	}

	public final SetPredicateContext setPredicate() throws RecognitionException {
		SetPredicateContext _localctx = new SetPredicateContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_setPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156); match(STRING);
			setState(157); match(SEP);
			setState(158); match(IN);
			setState(159); match(SEP);
			setState(160); match(LB);
			setState(161); varName();
			setState(162); match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarNameContext extends ParserRuleContext {
		public TerminalNode VARIABLE_NAME() { return getToken(CDLParser.VARIABLE_NAME, 0); }
		public VarNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterVarName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitVarName(this);
		}
	}

	public final VarNameContext varName() throws RecognitionException {
		VarNameContext _localctx = new VarNameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_varName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164); match(VARIABLE_NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public FuncNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterFuncName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitFuncName(this);
		}
	}

	public final FuncNameContext funcName() throws RecognitionException {
		FuncNameContext _localctx = new FuncNameContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_funcName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166); match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EosContext extends ParserRuleContext {
		public EosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterEos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitEos(this);
		}
	}

	public final EosContext eos() throws RecognitionException {
		EosContext _localctx = new EosContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_eos);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168); match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\'\u00ad\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2:\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\5\2F\n\2\3\2\3\2\7\2J\n\2\f\2\16\2M\13\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\7\3U\n\3\f\3\16\3X\13\3\3\3\3\3\5\3\\\n\3\3\4\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\5\ry\n\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u009d\n\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30"+
		"\3\30\2\2\31\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\2\5\3\2%"+
		"&\3\2\"#\4\2\31\32\35\35\u00a0\2\60\3\2\2\2\4N\3\2\2\2\6]\3\2\2\2\b`\3"+
		"\2\2\2\nd\3\2\2\2\ff\3\2\2\2\16h\3\2\2\2\20j\3\2\2\2\22l\3\2\2\2\24n\3"+
		"\2\2\2\26p\3\2\2\2\30x\3\2\2\2\32z\3\2\2\2\34\177\3\2\2\2\36\u0081\3\2"+
		"\2\2 \u0087\3\2\2\2\"\u0089\3\2\2\2$\u008b\3\2\2\2&\u009c\3\2\2\2(\u009e"+
		"\3\2\2\2*\u00a6\3\2\2\2,\u00a8\3\2\2\2.\u00aa\3\2\2\2\60\61\7\7\2\2\61"+
		"\62\5\n\6\2\62\63\7\b\2\2\63\64\7\t\2\2\64\65\5\4\3\2\65\66\7\n\2\2\66"+
		"9\5\6\4\2\678\7\13\2\28:\5\b\5\29\67\3\2\2\29:\3\2\2\2:;\3\2\2\2;K\5."+
		"\30\2<=\7\7\2\2=>\5\n\6\2>?\7\b\2\2?@\7\t\2\2@A\5\4\3\2AB\7\n\2\2BE\5"+
		"\6\4\2CD\7\13\2\2DF\5\b\5\2EC\3\2\2\2EF\3\2\2\2FG\3\2\2\2GH\5.\30\2HJ"+
		"\3\2\2\2I<\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\3\3\2\2\2MK\3\2\2\2"+
		"NO\7\r\2\2OP\5\16\b\2PQ\7\f\2\2QV\5\f\7\2RS\7\34\2\2SU\5\f\7\2TR\3\2\2"+
		"\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2W[\3\2\2\2XV\3\2\2\2YZ\7\16\2\2Z\\\5\22"+
		"\n\2[Y\3\2\2\2[\\\3\2\2\2\\\5\3\2\2\2]^\7\17\2\2^_\5\24\13\2_\7\3\2\2"+
		"\2`a\5\20\t\2ab\7\20\2\2bc\7\5\2\2c\t\3\2\2\2de\7\5\2\2e\13\3\2\2\2fg"+
		"\7\5\2\2g\r\3\2\2\2hi\7\5\2\2i\17\3\2\2\2jk\7\5\2\2k\21\3\2\2\2lm\5\26"+
		"\f\2m\23\3\2\2\2no\5\26\f\2o\25\3\2\2\2pq\5\30\r\2q\27\3\2\2\2ry\5\32"+
		"\16\2sy\5\36\20\2ty\5(\25\2uy\5\"\22\2vy\5$\23\2wy\5&\24\2xr\3\2\2\2x"+
		"s\3\2\2\2xt\3\2\2\2xu\3\2\2\2xv\3\2\2\2xw\3\2\2\2y\31\3\2\2\2z{\5\34\17"+
		"\2{|\7\23\2\2|}\7\5\2\2}~\7\24\2\2~\33\3\2\2\2\177\u0080\7$\2\2\u0080"+
		"\35\3\2\2\2\u0081\u0082\7\5\2\2\u0082\u0083\7\34\2\2\u0083\u0084\5 \21"+
		"\2\u0084\u0085\7\34\2\2\u0085\u0086\7\5\2\2\u0086\37\3\2\2\2\u0087\u0088"+
		"\t\2\2\2\u0088!\3\2\2\2\u0089\u008a\t\3\2\2\u008a#\3\2\2\2\u008b\u008c"+
		"\7\37\2\2\u008c\u008d\5\30\r\2\u008d%\3\2\2\2\u008e\u008f\7\5\2\2\u008f"+
		"\u0090\7\34\2\2\u0090\u0091\t\4\2\2\u0091\u0092\7\34\2\2\u0092\u009d\7"+
		"\6\2\2\u0093\u0094\7\5\2\2\u0094\u0095\7\34\2\2\u0095\u0096\7\21\2\2\u0096"+
		"\u0097\5,\27\2\u0097\u0098\7\34\2\2\u0098\u0099\t\4\2\2\u0099\u009a\7"+
		"\34\2\2\u009a\u009b\7\6\2\2\u009b\u009d\3\2\2\2\u009c\u008e\3\2\2\2\u009c"+
		"\u0093\3\2\2\2\u009d\'\3\2\2\2\u009e\u009f\7\5\2\2\u009f\u00a0\7\34\2"+
		"\2\u00a0\u00a1\7\22\2\2\u00a1\u00a2\7\34\2\2\u00a2\u00a3\7\25\2\2\u00a3"+
		"\u00a4\5*\26\2\u00a4\u00a5\7\26\2\2\u00a5)\3\2\2\2\u00a6\u00a7\7\4\2\2"+
		"\u00a7+\3\2\2\2\u00a8\u00a9\7\5\2\2\u00a9-\3\2\2\2\u00aa\u00ab\7\3\2\2"+
		"\u00ab/\3\2\2\2\t9EKV[x\u009c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}