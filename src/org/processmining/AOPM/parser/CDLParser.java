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
		T__0=1, VARIABLE_NAME=2, STRING=3, NUMBER=4, DEFINE=5, AS=6, FILTER=7, 
		EVALUATE=8, ACQUIRE=9, ASSOCIATE=10, ENTITY=11, VALIDATE=12, CONDITION=13, 
		CALCULATE=14, ACQUIRED_BY=15, IN=16, LP=17, RP=18, LB=19, RB=20, LSB=21, 
		RSB=22, LTB=23, RTB=24, DQ=25, SEP=26, ASSIGN=27, TILDE=28, NOT=29, AND=30, 
		OR=31, TRUE=32, FALSE=33, OCCUR=34, NEXT=35, EVENTUALLY=36, WS=37;
	public static final String[] tokenNames = {
		"<INVALID>", "';'", "VARIABLE_NAME", "STRING", "NUMBER", "'DEFINE'", "'AS'", 
		"'FILTER'", "'EVALUATE'", "'ACQUIRE'", "'ASSOCIATE'", "'ENTITY'", "'VALIDATE'", 
		"'CONDITION'", "'CALCULATE'", "'ACQUIRED BY'", "'IN'", "'('", "')'", "'{'", 
		"'}'", "'['", "']'", "'<'", "'>'", "'\"'", "','", "'='", "'~'", "'NOT'", 
		"'AND'", "'OR'", "'TRUE'", "'FALSE'", "'OCCUR'", "'NEXT'", "'EVENTUALLY'", 
		"WS"
	};
	public static final int
		RULE_constraint = 0, RULE_acquireQuery = 1, RULE_constraintName = 2, RULE_objectName = 3, 
		RULE_entityName = 4, RULE_filterName = 5, RULE_acqName = 6, RULE_validatePredicate = 7, 
		RULE_evaluatePredicate = 8, RULE_predicate = 9, RULE_proposition = 10, 
		RULE_unaryPredicate = 11, RULE_unaryPredicateName = 12, RULE_binaryPredicate = 13, 
		RULE_binaryPredicateName = 14, RULE_truthValue = 15, RULE_negation = 16, 
		RULE_relationalPredicate = 17, RULE_setPredicate = 18, RULE_varName = 19, 
		RULE_funcName = 20, RULE_eos = 21;
	public static final String[] ruleNames = {
		"constraint", "acquireQuery", "constraintName", "objectName", "entityName", 
		"filterName", "acqName", "validatePredicate", "evaluatePredicate", "predicate", 
		"proposition", "unaryPredicate", "unaryPredicateName", "binaryPredicate", 
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
		public TerminalNode FILTER(int i) {
			return getToken(CDLParser.FILTER, i);
		}
		public TerminalNode AS(int i) {
			return getToken(CDLParser.AS, i);
		}
		public TerminalNode ENTITY(int i) {
			return getToken(CDLParser.ENTITY, i);
		}
		public List<TerminalNode> ENTITY() { return getTokens(CDLParser.ENTITY); }
		public TerminalNode EVALUATE(int i) {
			return getToken(CDLParser.EVALUATE, i);
		}
		public EvaluatePredicateContext evaluatePredicate(int i) {
			return getRuleContext(EvaluatePredicateContext.class,i);
		}
		public List<EosContext> eos() {
			return getRuleContexts(EosContext.class);
		}
		public EosContext eos(int i) {
			return getRuleContext(EosContext.class,i);
		}
		public List<EntityNameContext> entityName() {
			return getRuleContexts(EntityNameContext.class);
		}
		public List<ConstraintNameContext> constraintName() {
			return getRuleContexts(ConstraintNameContext.class);
		}
		public List<TerminalNode> AS() { return getTokens(CDLParser.AS); }
		public EntityNameContext entityName(int i) {
			return getRuleContext(EntityNameContext.class,i);
		}
		public TerminalNode DEFINE(int i) {
			return getToken(CDLParser.DEFINE, i);
		}
		public List<TerminalNode> DEFINE() { return getTokens(CDLParser.DEFINE); }
		public FilterNameContext filterName(int i) {
			return getRuleContext(FilterNameContext.class,i);
		}
		public ConstraintNameContext constraintName(int i) {
			return getRuleContext(ConstraintNameContext.class,i);
		}
		public List<TerminalNode> EVALUATE() { return getTokens(CDLParser.EVALUATE); }
		public List<EvaluatePredicateContext> evaluatePredicate() {
			return getRuleContexts(EvaluatePredicateContext.class);
		}
		public List<FilterNameContext> filterName() {
			return getRuleContexts(FilterNameContext.class);
		}
		public List<TerminalNode> FILTER() { return getTokens(CDLParser.FILTER); }
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
			setState(44); match(DEFINE);
			setState(45); constraintName();
			setState(46); match(AS);
			setState(47); match(ENTITY);
			setState(48); entityName();
			setState(49); match(FILTER);
			setState(50); filterName();
			setState(51); match(EVALUATE);
			setState(52); evaluatePredicate();
			setState(53); eos();
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DEFINE) {
				{
				{
				setState(54); match(DEFINE);
				setState(55); constraintName();
				setState(56); match(AS);
				setState(57); match(ENTITY);
				setState(58); entityName();
				setState(59); match(FILTER);
				setState(60); filterName();
				setState(61); match(EVALUATE);
				setState(62); evaluatePredicate();
				setState(63); eos();
				}
				}
				setState(69);
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
		enterRule(_localctx, 2, RULE_acquireQuery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70); acqName();
			setState(71); match(CALCULATE);
			setState(72); match(STRING);
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
		enterRule(_localctx, 4, RULE_constraintName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74); match(STRING);
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
		enterRule(_localctx, 6, RULE_objectName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); match(STRING);
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
		enterRule(_localctx, 8, RULE_entityName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78); match(STRING);
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

	public static class FilterNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public FilterNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filterName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterFilterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitFilterName(this);
		}
	}

	public final FilterNameContext filterName() throws RecognitionException {
		FilterNameContext _localctx = new FilterNameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_filterName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); match(STRING);
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
		enterRule(_localctx, 12, RULE_acqName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); match(STRING);
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

	public static class ValidatePredicateContext extends ParserRuleContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public ValidatePredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_validatePredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterValidatePredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitValidatePredicate(this);
		}
	}

	public final ValidatePredicateContext validatePredicate() throws RecognitionException {
		ValidatePredicateContext _localctx = new ValidatePredicateContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_validatePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); predicate();
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
		enterRule(_localctx, 16, RULE_evaluatePredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86); predicate();
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
		enterRule(_localctx, 18, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88); proposition();
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
		enterRule(_localctx, 20, RULE_proposition);
		try {
			setState(96);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(90); unaryPredicate();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(91); binaryPredicate();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(92); setPredicate();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(93); truthValue();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(94); negation();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(95); relationalPredicate();
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
		enterRule(_localctx, 22, RULE_unaryPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98); unaryPredicateName();
			setState(99); match(LP);
			setState(100); match(STRING);
			setState(101); match(RP);
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
		enterRule(_localctx, 24, RULE_unaryPredicateName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); match(OCCUR);
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
		enterRule(_localctx, 26, RULE_binaryPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105); match(STRING);
			setState(106); match(SEP);
			setState(107); binaryPredicateName();
			setState(108); match(SEP);
			setState(109); match(STRING);
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
		enterRule(_localctx, 28, RULE_binaryPredicateName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
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
		enterRule(_localctx, 30, RULE_truthValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
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
		enterRule(_localctx, 32, RULE_negation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); match(NOT);
			setState(116); proposition();
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
		enterRule(_localctx, 34, RULE_relationalPredicate);
		int _la;
		try {
			setState(132);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(118); match(STRING);
				setState(119); match(SEP);
				setState(120);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LTB) | (1L << RTB) | (1L << ASSIGN))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(121); match(SEP);
				setState(122); match(NUMBER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(123); match(STRING);
				setState(124); match(SEP);
				setState(125); match(ACQUIRED_BY);
				setState(126); funcName();
				setState(127); match(SEP);
				setState(128);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LTB) | (1L << RTB) | (1L << ASSIGN))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(129); match(SEP);
				setState(130); match(NUMBER);
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
		enterRule(_localctx, 36, RULE_setPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134); match(STRING);
			setState(135); match(SEP);
			setState(136); match(IN);
			setState(137); match(SEP);
			setState(138); match(LB);
			setState(139); varName();
			setState(140); match(RB);
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
		enterRule(_localctx, 38, RULE_varName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142); match(VARIABLE_NAME);
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
		enterRule(_localctx, 40, RULE_funcName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144); match(STRING);
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
		enterRule(_localctx, 42, RULE_eos);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146); match(T__0);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\'\u0097\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2D"+
		"\n\2\f\2\16\2G\13\2\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3"+
		"\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\5\fc\n\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u0087\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\2\2\30\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,\2\5\3\2%&\3\2\"#\4\2\31\32\35\35\u0087\2.\3\2"+
		"\2\2\4H\3\2\2\2\6L\3\2\2\2\bN\3\2\2\2\nP\3\2\2\2\fR\3\2\2\2\16T\3\2\2"+
		"\2\20V\3\2\2\2\22X\3\2\2\2\24Z\3\2\2\2\26b\3\2\2\2\30d\3\2\2\2\32i\3\2"+
		"\2\2\34k\3\2\2\2\36q\3\2\2\2 s\3\2\2\2\"u\3\2\2\2$\u0086\3\2\2\2&\u0088"+
		"\3\2\2\2(\u0090\3\2\2\2*\u0092\3\2\2\2,\u0094\3\2\2\2./\7\7\2\2/\60\5"+
		"\6\4\2\60\61\7\b\2\2\61\62\7\r\2\2\62\63\5\n\6\2\63\64\7\t\2\2\64\65\5"+
		"\f\7\2\65\66\7\n\2\2\66\67\5\22\n\2\67E\5,\27\289\7\7\2\29:\5\6\4\2:;"+
		"\7\b\2\2;<\7\r\2\2<=\5\n\6\2=>\7\t\2\2>?\5\f\7\2?@\7\n\2\2@A\5\22\n\2"+
		"AB\5,\27\2BD\3\2\2\2C8\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\3\3\2\2"+
		"\2GE\3\2\2\2HI\5\16\b\2IJ\7\20\2\2JK\7\5\2\2K\5\3\2\2\2LM\7\5\2\2M\7\3"+
		"\2\2\2NO\7\5\2\2O\t\3\2\2\2PQ\7\5\2\2Q\13\3\2\2\2RS\7\5\2\2S\r\3\2\2\2"+
		"TU\7\5\2\2U\17\3\2\2\2VW\5\24\13\2W\21\3\2\2\2XY\5\24\13\2Y\23\3\2\2\2"+
		"Z[\5\26\f\2[\25\3\2\2\2\\c\5\30\r\2]c\5\34\17\2^c\5&\24\2_c\5 \21\2`c"+
		"\5\"\22\2ac\5$\23\2b\\\3\2\2\2b]\3\2\2\2b^\3\2\2\2b_\3\2\2\2b`\3\2\2\2"+
		"ba\3\2\2\2c\27\3\2\2\2de\5\32\16\2ef\7\23\2\2fg\7\5\2\2gh\7\24\2\2h\31"+
		"\3\2\2\2ij\7$\2\2j\33\3\2\2\2kl\7\5\2\2lm\7\34\2\2mn\5\36\20\2no\7\34"+
		"\2\2op\7\5\2\2p\35\3\2\2\2qr\t\2\2\2r\37\3\2\2\2st\t\3\2\2t!\3\2\2\2u"+
		"v\7\37\2\2vw\5\26\f\2w#\3\2\2\2xy\7\5\2\2yz\7\34\2\2z{\t\4\2\2{|\7\34"+
		"\2\2|\u0087\7\6\2\2}~\7\5\2\2~\177\7\34\2\2\177\u0080\7\21\2\2\u0080\u0081"+
		"\5*\26\2\u0081\u0082\7\34\2\2\u0082\u0083\t\4\2\2\u0083\u0084\7\34\2\2"+
		"\u0084\u0085\7\6\2\2\u0085\u0087\3\2\2\2\u0086x\3\2\2\2\u0086}\3\2\2\2"+
		"\u0087%\3\2\2\2\u0088\u0089\7\5\2\2\u0089\u008a\7\34\2\2\u008a\u008b\7"+
		"\22\2\2\u008b\u008c\7\34\2\2\u008c\u008d\7\25\2\2\u008d\u008e\5(\25\2"+
		"\u008e\u008f\7\26\2\2\u008f\'\3\2\2\2\u0090\u0091\7\4\2\2\u0091)\3\2\2"+
		"\2\u0092\u0093\7\5\2\2\u0093+\3\2\2\2\u0094\u0095\7\3\2\2\u0095-\3\2\2"+
		"\2\5Eb\u0086";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}