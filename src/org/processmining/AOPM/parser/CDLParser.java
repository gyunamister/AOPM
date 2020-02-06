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
		RULE_entityName = 4, RULE_relevantEntityName = 5, RULE_acqName = 6, RULE_validatePredicate = 7, 
		RULE_evaluatePredicate = 8, RULE_predicate = 9, RULE_proposition = 10, 
		RULE_unaryPredicate = 11, RULE_unaryPredicateName = 12, RULE_binaryPredicate = 13, 
		RULE_binaryPredicateName = 14, RULE_truthValue = 15, RULE_negation = 16, 
		RULE_relationalPredicate = 17, RULE_setPredicate = 18, RULE_varName = 19, 
		RULE_funcName = 20, RULE_eos = 21;
	public static final String[] ruleNames = {
		"constraint", "acquireQuery", "constraintName", "objectName", "entityName", 
		"relevantEntityName", "acqName", "validatePredicate", "evaluatePredicate", 
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
		public TerminalNode ENTITY(int i) {
			return getToken(CDLParser.ENTITY, i);
		}
		public EvaluatePredicateContext evaluatePredicate(int i) {
			return getRuleContext(EvaluatePredicateContext.class,i);
		}
		public ValidatePredicateContext validatePredicate(int i) {
			return getRuleContext(ValidatePredicateContext.class,i);
		}
		public List<EntityNameContext> entityName() {
			return getRuleContexts(EntityNameContext.class);
		}
		public List<TerminalNode> AS() { return getTokens(CDLParser.AS); }
		public TerminalNode DEFINE(int i) {
			return getToken(CDLParser.DEFINE, i);
		}
		public List<AcquireQueryContext> acquireQuery() {
			return getRuleContexts(AcquireQueryContext.class);
		}
		public List<TerminalNode> ACQUIRE() { return getTokens(CDLParser.ACQUIRE); }
		public TerminalNode VALIDATE(int i) {
			return getToken(CDLParser.VALIDATE, i);
		}
		public List<TerminalNode> EVALUATE() { return getTokens(CDLParser.EVALUATE); }
		public List<TerminalNode> VALIDATE() { return getTokens(CDLParser.VALIDATE); }
		public List<ValidatePredicateContext> validatePredicate() {
			return getRuleContexts(ValidatePredicateContext.class);
		}
		public TerminalNode AS(int i) {
			return getToken(CDLParser.AS, i);
		}
		public List<TerminalNode> ENTITY() { return getTokens(CDLParser.ENTITY); }
		public List<TerminalNode> ASSOCIATE() { return getTokens(CDLParser.ASSOCIATE); }
		public TerminalNode EVALUATE(int i) {
			return getToken(CDLParser.EVALUATE, i);
		}
		public AcquireQueryContext acquireQuery(int i) {
			return getRuleContext(AcquireQueryContext.class,i);
		}
		public List<RelevantEntityNameContext> relevantEntityName() {
			return getRuleContexts(RelevantEntityNameContext.class);
		}
		public RelevantEntityNameContext relevantEntityName(int i) {
			return getRuleContext(RelevantEntityNameContext.class,i);
		}
		public List<TerminalNode> SEP() { return getTokens(CDLParser.SEP); }
		public List<EosContext> eos() {
			return getRuleContexts(EosContext.class);
		}
		public EosContext eos(int i) {
			return getRuleContext(EosContext.class,i);
		}
		public List<ConstraintNameContext> constraintName() {
			return getRuleContexts(ConstraintNameContext.class);
		}
		public EntityNameContext entityName(int i) {
			return getRuleContext(EntityNameContext.class,i);
		}
		public TerminalNode ASSOCIATE(int i) {
			return getToken(CDLParser.ASSOCIATE, i);
		}
		public List<TerminalNode> DEFINE() { return getTokens(CDLParser.DEFINE); }
		public ConstraintNameContext constraintName(int i) {
			return getRuleContext(ConstraintNameContext.class,i);
		}
		public List<EvaluatePredicateContext> evaluatePredicate() {
			return getRuleContexts(EvaluatePredicateContext.class);
		}
		public TerminalNode SEP(int i) {
			return getToken(CDLParser.SEP, i);
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
			setState(44); match(DEFINE);
			setState(45); constraintName();
			setState(46); match(AS);
			setState(47); match(ENTITY);
			setState(48); entityName();
			setState(58);
			_la = _input.LA(1);
			if (_la==ASSOCIATE) {
				{
				setState(49); match(ASSOCIATE);
				setState(50); relevantEntityName();
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEP) {
					{
					{
					setState(51); match(SEP);
					setState(52); relevantEntityName();
					}
					}
					setState(57);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(60); match(VALIDATE);
			setState(61); validatePredicate();
			setState(62); match(EVALUATE);
			setState(63); evaluatePredicate();
			setState(66);
			_la = _input.LA(1);
			if (_la==ACQUIRE) {
				{
				setState(64); match(ACQUIRE);
				setState(65); acquireQuery();
				}
			}

			setState(68); eos();
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DEFINE) {
				{
				{
				setState(69); match(DEFINE);
				setState(70); constraintName();
				setState(71); match(AS);
				setState(72); match(ENTITY);
				setState(73); entityName();
				setState(83);
				_la = _input.LA(1);
				if (_la==ASSOCIATE) {
					{
					setState(74); match(ASSOCIATE);
					setState(75); relevantEntityName();
					setState(80);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SEP) {
						{
						{
						setState(76); match(SEP);
						setState(77); relevantEntityName();
						}
						}
						setState(82);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(85); match(VALIDATE);
				setState(86); validatePredicate();
				setState(87); match(EVALUATE);
				setState(88); evaluatePredicate();
				setState(91);
				_la = _input.LA(1);
				if (_la==ACQUIRE) {
					{
					setState(89); match(ACQUIRE);
					setState(90); acquireQuery();
					}
				}

				setState(93); eos();
				}
				}
				setState(99);
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
			setState(100); acqName();
			setState(101); match(CALCULATE);
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
			setState(106); match(STRING);
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
			setState(108); match(STRING);
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

	public static class RelevantEntityNameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CDLParser.STRING, 0); }
		public RelevantEntityNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relevantEntityName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).enterRelevantEntityName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CDLListener ) ((CDLListener)listener).exitRelevantEntityName(this);
		}
	}

	public final RelevantEntityNameContext relevantEntityName() throws RecognitionException {
		RelevantEntityNameContext _localctx = new RelevantEntityNameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_relevantEntityName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); match(STRING);
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
			setState(112); match(STRING);
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
			setState(114); predicate();
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
			setState(116); predicate();
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
			setState(118); proposition();
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
			setState(126);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(120); unaryPredicate();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(121); binaryPredicate();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(122); setPredicate();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(123); truthValue();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(124); negation();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(125); relationalPredicate();
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
			setState(128); unaryPredicateName();
			setState(129); match(LP);
			setState(130); match(STRING);
			setState(131); match(RP);
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
			setState(133); match(OCCUR);
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
			setState(135); match(STRING);
			setState(136); match(SEP);
			setState(137); binaryPredicateName();
			setState(138); match(SEP);
			setState(139); match(STRING);
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
			setState(141);
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
			setState(143);
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
			setState(145); match(NOT);
			setState(146); proposition();
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
			setState(162);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(148); match(STRING);
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
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(153); match(STRING);
				setState(154); match(SEP);
				setState(155); match(ACQUIRED_BY);
				setState(156); funcName();
				setState(157); match(SEP);
				setState(158);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LTB) | (1L << RTB) | (1L << ASSIGN))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(159); match(SEP);
				setState(160); match(NUMBER);
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
			setState(164); match(STRING);
			setState(165); match(SEP);
			setState(166); match(IN);
			setState(167); match(SEP);
			setState(168); match(LB);
			setState(169); varName();
			setState(170); match(RB);
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
			setState(172); match(VARIABLE_NAME);
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
			setState(174); match(STRING);
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
			setState(176); match(T__0);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\'\u00b5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\7\28\n\2\f\2\16\2;\13\2\5\2=\n\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\5\2E\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2Q\n\2\f\2\16"+
		"\2T\13\2\5\2V\n\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2^\n\2\3\2\3\2\7\2b\n\2\f"+
		"\2\16\2e\13\2\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b"+
		"\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0081\n\f\3\r\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u00a5\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\2\2\30\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,\2\5\3\2%&\3\2\"#\4\2\31\32\35\35\u00ab\2.\3\2"+
		"\2\2\4f\3\2\2\2\6j\3\2\2\2\bl\3\2\2\2\nn\3\2\2\2\fp\3\2\2\2\16r\3\2\2"+
		"\2\20t\3\2\2\2\22v\3\2\2\2\24x\3\2\2\2\26\u0080\3\2\2\2\30\u0082\3\2\2"+
		"\2\32\u0087\3\2\2\2\34\u0089\3\2\2\2\36\u008f\3\2\2\2 \u0091\3\2\2\2\""+
		"\u0093\3\2\2\2$\u00a4\3\2\2\2&\u00a6\3\2\2\2(\u00ae\3\2\2\2*\u00b0\3\2"+
		"\2\2,\u00b2\3\2\2\2./\7\7\2\2/\60\5\6\4\2\60\61\7\b\2\2\61\62\7\r\2\2"+
		"\62<\5\n\6\2\63\64\7\f\2\2\649\5\f\7\2\65\66\7\34\2\2\668\5\f\7\2\67\65"+
		"\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:=\3\2\2\2;9\3\2\2\2<\63\3\2\2"+
		"\2<=\3\2\2\2=>\3\2\2\2>?\7\16\2\2?@\5\20\t\2@A\7\n\2\2AD\5\22\n\2BC\7"+
		"\13\2\2CE\5\4\3\2DB\3\2\2\2DE\3\2\2\2EF\3\2\2\2Fc\5,\27\2GH\7\7\2\2HI"+
		"\5\6\4\2IJ\7\b\2\2JK\7\r\2\2KU\5\n\6\2LM\7\f\2\2MR\5\f\7\2NO\7\34\2\2"+
		"OQ\5\f\7\2PN\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2SV\3\2\2\2TR\3\2\2\2"+
		"UL\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\7\16\2\2XY\5\20\t\2YZ\7\n\2\2Z]\5\22"+
		"\n\2[\\\7\13\2\2\\^\5\4\3\2][\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\5,\27\2`b"+
		"\3\2\2\2aG\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2d\3\3\2\2\2ec\3\2\2\2"+
		"fg\5\16\b\2gh\7\20\2\2hi\7\5\2\2i\5\3\2\2\2jk\7\5\2\2k\7\3\2\2\2lm\7\5"+
		"\2\2m\t\3\2\2\2no\7\5\2\2o\13\3\2\2\2pq\7\5\2\2q\r\3\2\2\2rs\7\5\2\2s"+
		"\17\3\2\2\2tu\5\24\13\2u\21\3\2\2\2vw\5\24\13\2w\23\3\2\2\2xy\5\26\f\2"+
		"y\25\3\2\2\2z\u0081\5\30\r\2{\u0081\5\34\17\2|\u0081\5&\24\2}\u0081\5"+
		" \21\2~\u0081\5\"\22\2\177\u0081\5$\23\2\u0080z\3\2\2\2\u0080{\3\2\2\2"+
		"\u0080|\3\2\2\2\u0080}\3\2\2\2\u0080~\3\2\2\2\u0080\177\3\2\2\2\u0081"+
		"\27\3\2\2\2\u0082\u0083\5\32\16\2\u0083\u0084\7\23\2\2\u0084\u0085\7\5"+
		"\2\2\u0085\u0086\7\24\2\2\u0086\31\3\2\2\2\u0087\u0088\7$\2\2\u0088\33"+
		"\3\2\2\2\u0089\u008a\7\5\2\2\u008a\u008b\7\34\2\2\u008b\u008c\5\36\20"+
		"\2\u008c\u008d\7\34\2\2\u008d\u008e\7\5\2\2\u008e\35\3\2\2\2\u008f\u0090"+
		"\t\2\2\2\u0090\37\3\2\2\2\u0091\u0092\t\3\2\2\u0092!\3\2\2\2\u0093\u0094"+
		"\7\37\2\2\u0094\u0095\5\26\f\2\u0095#\3\2\2\2\u0096\u0097\7\5\2\2\u0097"+
		"\u0098\7\34\2\2\u0098\u0099\t\4\2\2\u0099\u009a\7\34\2\2\u009a\u00a5\7"+
		"\6\2\2\u009b\u009c\7\5\2\2\u009c\u009d\7\34\2\2\u009d\u009e\7\21\2\2\u009e"+
		"\u009f\5*\26\2\u009f\u00a0\7\34\2\2\u00a0\u00a1\t\4\2\2\u00a1\u00a2\7"+
		"\34\2\2\u00a2\u00a3\7\6\2\2\u00a3\u00a5\3\2\2\2\u00a4\u0096\3\2\2\2\u00a4"+
		"\u009b\3\2\2\2\u00a5%\3\2\2\2\u00a6\u00a7\7\5\2\2\u00a7\u00a8\7\34\2\2"+
		"\u00a8\u00a9\7\22\2\2\u00a9\u00aa\7\34\2\2\u00aa\u00ab\7\25\2\2\u00ab"+
		"\u00ac\5(\25\2\u00ac\u00ad\7\26\2\2\u00ad\'\3\2\2\2\u00ae\u00af\7\4\2"+
		"\2\u00af)\3\2\2\2\u00b0\u00b1\7\5\2\2\u00b1+\3\2\2\2\u00b2\u00b3\7\3\2"+
		"\2\u00b3-\3\2\2\2\139<DRU]c\u0080\u00a4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}