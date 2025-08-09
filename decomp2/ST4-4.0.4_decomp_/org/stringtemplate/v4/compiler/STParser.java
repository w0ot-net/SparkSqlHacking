package org.stringtemplate.v4.compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.FailedPredicateException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.RuleReturnScope;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.RewriteEarlyExitException;
import org.antlr.runtime.tree.RewriteRuleSubtreeStream;
import org.antlr.runtime.tree.RewriteRuleTokenStream;
import org.antlr.runtime.tree.TreeAdaptor;
import org.stringtemplate.v4.misc.ErrorManager;
import org.stringtemplate.v4.misc.ErrorType;

public class STParser extends Parser {
   public static final String[] tokenNames = new String[]{"<invalid>", "<EOR>", "<DOWN>", "<UP>", "IF", "ELSE", "ELSEIF", "ENDIF", "SUPER", "SEMI", "BANG", "ELLIPSIS", "EQUALS", "COLON", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "COMMA", "DOT", "LCURLY", "RCURLY", "TEXT", "LDELIM", "RDELIM", "ID", "STRING", "WS", "PIPE", "OR", "AND", "INDENT", "NEWLINE", "AT", "END", "TRUE", "FALSE", "COMMENT", "EXPR", "OPTIONS", "PROP", "PROP_IND", "INCLUDE", "INCLUDE_IND", "EXEC_FUNC", "INCLUDE_SUPER", "INCLUDE_SUPER_REGION", "INCLUDE_REGION", "TO_STR", "LIST", "MAP", "ZIP", "SUBTEMPLATE", "ARGS", "ELEMENTS", "REGION", "NULL", "INDENTED_EXPR"};
   public static final int EOF = -1;
   public static final int RBRACK = 17;
   public static final int LBRACK = 16;
   public static final int ELSE = 5;
   public static final int ELLIPSIS = 11;
   public static final int LCURLY = 20;
   public static final int BANG = 10;
   public static final int EQUALS = 12;
   public static final int TEXT = 22;
   public static final int ID = 25;
   public static final int SEMI = 9;
   public static final int LPAREN = 14;
   public static final int IF = 4;
   public static final int ELSEIF = 6;
   public static final int COLON = 13;
   public static final int RPAREN = 15;
   public static final int WS = 27;
   public static final int COMMA = 18;
   public static final int RCURLY = 21;
   public static final int ENDIF = 7;
   public static final int RDELIM = 24;
   public static final int SUPER = 8;
   public static final int DOT = 19;
   public static final int LDELIM = 23;
   public static final int STRING = 26;
   public static final int PIPE = 28;
   public static final int OR = 29;
   public static final int AND = 30;
   public static final int INDENT = 31;
   public static final int NEWLINE = 32;
   public static final int AT = 33;
   public static final int END = 34;
   public static final int TRUE = 35;
   public static final int FALSE = 36;
   public static final int COMMENT = 37;
   public static final int EXPR = 38;
   public static final int OPTIONS = 39;
   public static final int PROP = 40;
   public static final int PROP_IND = 41;
   public static final int INCLUDE = 42;
   public static final int INCLUDE_IND = 43;
   public static final int EXEC_FUNC = 44;
   public static final int INCLUDE_SUPER = 45;
   public static final int INCLUDE_SUPER_REGION = 46;
   public static final int INCLUDE_REGION = 47;
   public static final int TO_STR = 48;
   public static final int LIST = 49;
   public static final int MAP = 50;
   public static final int ZIP = 51;
   public static final int SUBTEMPLATE = 52;
   public static final int ARGS = 53;
   public static final int ELEMENTS = 54;
   public static final int REGION = 55;
   public static final int NULL = 56;
   public static final int INDENTED_EXPR = 57;
   protected TreeAdaptor adaptor;
   ErrorManager errMgr;
   Token templateToken;
   protected Stack conditional_stack;
   protected DFA3 dfa3;
   protected DFA35 dfa35;
   protected DFA38 dfa38;
   static final String DFA3_eotS = "\u0010\uffff";
   static final String DFA3_eofS = "\u0002\uffff\u0001\u0004\u0002\uffff\u0001\u0007\n\uffff";
   static final String DFA3_minS = "\u0002\u0016\u0001\u0015\u0001\u0004\u0001\uffff\u0001\u0015\u0001\u0004\u0001\uffff\u0001\u0000\u0001\uffff\u0001\b\u0001\u0000\u0001\b\u0001\uffff\u0002\u000e";
   static final String DFA3_maxS = "\u0003%\u0001$\u0001\uffff\u0001%\u0001$\u0001\uffff\u0001\u0000\u0001\uffff\u0001\u0019\u0001\u0000\u0001\u0019\u0001\uffff\u0002\u0018";
   static final String DFA3_acceptS = "\u0004\uffff\u0001\u0003\u0002\uffff\u0001\u0002\u0001\uffff\u0001\u0004\u0003\uffff\u0001\u0001\u0002\uffff";
   static final String DFA3_specialS = "\b\uffff\u0001\u0001\u0002\uffff\u0001\u0000\u0004\uffff}>";
   static final String[] DFA3_transitionS = new String[]{"\u0001\u0004\u0001\u0003\u0007\uffff\u0001\u0001\u0001\u0004\u0004\uffff\u0001\u0002", "\u0001\u0007\u0001\u0006\b\uffff\u0001\u0007\u0004\uffff\u0001\u0005", "\u0003\u0004\u0007\uffff\u0001\u0004\u0001\b\u0004\uffff\u0001\u0004", "\u0001\t\u0003\uffff\u0001\u0004\u0005\uffff\u0001\u0004\u0001\uffff\u0001\u0004\u0003\uffff\u0001\u0004\u0004\uffff\u0002\u0004\u0006\uffff\u0001\n\u0001\uffff\u0002\u0004", "", "\u0003\u0007\u0007\uffff\u0001\u0007\u0001\u000b\u0004\uffff\u0001\u0007", "\u0001\t\u0003\uffff\u0001\u0007\u0005\uffff\u0001\u0007\u0001\uffff\u0001\u0007\u0003\uffff\u0001\u0007\u0004\uffff\u0002\u0007\u0006\uffff\u0001\f\u0001\uffff\u0002\u0007", "", "\u0001\uffff", "", "\u0001\u0004\u0010\uffff\u0001\u000e", "\u0001\uffff", "\u0001\u0007\u0010\uffff\u0001\u000f", "", "\u0001\u0004\t\uffff\u0001\t", "\u0001\u0007\t\uffff\u0001\t"};
   static final short[] DFA3_eot = DFA.unpackEncodedString("\u0010\uffff");
   static final short[] DFA3_eof = DFA.unpackEncodedString("\u0002\uffff\u0001\u0004\u0002\uffff\u0001\u0007\n\uffff");
   static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars("\u0002\u0016\u0001\u0015\u0001\u0004\u0001\uffff\u0001\u0015\u0001\u0004\u0001\uffff\u0001\u0000\u0001\uffff\u0001\b\u0001\u0000\u0001\b\u0001\uffff\u0002\u000e");
   static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars("\u0003%\u0001$\u0001\uffff\u0001%\u0001$\u0001\uffff\u0001\u0000\u0001\uffff\u0001\u0019\u0001\u0000\u0001\u0019\u0001\uffff\u0002\u0018");
   static final short[] DFA3_accept = DFA.unpackEncodedString("\u0004\uffff\u0001\u0003\u0002\uffff\u0001\u0002\u0001\uffff\u0001\u0004\u0003\uffff\u0001\u0001\u0002\uffff");
   static final short[] DFA3_special = DFA.unpackEncodedString("\b\uffff\u0001\u0001\u0002\uffff\u0001\u0000\u0004\uffff}>");
   static final short[][] DFA3_transition;
   static final String DFA35_eotS = "\u0018\uffff";
   static final String DFA35_eofS = "\u0018\uffff";
   static final String DFA35_minS = "\u0001\b\u0001\t\u0001\uffff\u0001\b\u0006\uffff\u0001\u0000\r\uffff";
   static final String DFA35_maxS = "\u0001$\u0001\u001e\u0001\uffff\u0001\u0019\u0006\uffff\u0001\u0000\r\uffff";
   static final String DFA35_acceptS = "\u0002\uffff\u0001\u0002\u0001\uffff\u0001\u0006\u0004\uffff\u0001\u0006\u0001\uffff\u0001\u0006\b\uffff\u0001\u0004\u0001\u0005\u0001\u0001\u0001\u0003";
   static final String DFA35_specialS = "\u0001\u0000\t\uffff\u0001\u0001\r\uffff}>";
   static final String[] DFA35_transitionS;
   static final short[] DFA35_eot;
   static final short[] DFA35_eof;
   static final char[] DFA35_min;
   static final char[] DFA35_max;
   static final short[] DFA35_accept;
   static final short[] DFA35_special;
   static final short[][] DFA35_transition;
   static final String DFA38_eotS = "\n\uffff";
   static final String DFA38_eofS = "\n\uffff";
   static final String DFA38_minS = "\u0001\u000e\u0006\uffff\u0001\u0000\u0002\uffff";
   static final String DFA38_maxS = "\u0001$\u0006\uffff\u0001\u0000\u0002\uffff";
   static final String DFA38_acceptS = "\u0001\uffff\u0001\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\uffff\u0001\u0007\u0001\b";
   static final String DFA38_specialS = "\u0001\u0000\u0006\uffff\u0001\u0001\u0002\uffff}>";
   static final String[] DFA38_transitionS;
   static final short[] DFA38_eot;
   static final short[] DFA38_eof;
   static final char[] DFA38_min;
   static final char[] DFA38_max;
   static final short[] DFA38_accept;
   static final short[] DFA38_special;
   static final short[][] DFA38_transition;
   public static final BitSet FOLLOW_template_in_templateAndEOF133;
   public static final BitSet FOLLOW_EOF_in_templateAndEOF135;
   public static final BitSet FOLLOW_element_in_template149;
   public static final BitSet FOLLOW_INDENT_in_element162;
   public static final BitSet FOLLOW_COMMENT_in_element165;
   public static final BitSet FOLLOW_NEWLINE_in_element167;
   public static final BitSet FOLLOW_INDENT_in_element175;
   public static final BitSet FOLLOW_singleElement_in_element177;
   public static final BitSet FOLLOW_singleElement_in_element194;
   public static final BitSet FOLLOW_compoundElement_in_element199;
   public static final BitSet FOLLOW_exprTag_in_singleElement210;
   public static final BitSet FOLLOW_TEXT_in_singleElement215;
   public static final BitSet FOLLOW_NEWLINE_in_singleElement220;
   public static final BitSet FOLLOW_COMMENT_in_singleElement225;
   public static final BitSet FOLLOW_ifstat_in_compoundElement238;
   public static final BitSet FOLLOW_region_in_compoundElement243;
   public static final BitSet FOLLOW_LDELIM_in_exprTag254;
   public static final BitSet FOLLOW_expr_in_exprTag256;
   public static final BitSet FOLLOW_SEMI_in_exprTag260;
   public static final BitSet FOLLOW_exprOptions_in_exprTag262;
   public static final BitSet FOLLOW_RDELIM_in_exprTag267;
   public static final BitSet FOLLOW_INDENT_in_region299;
   public static final BitSet FOLLOW_LDELIM_in_region304;
   public static final BitSet FOLLOW_AT_in_region306;
   public static final BitSet FOLLOW_ID_in_region308;
   public static final BitSet FOLLOW_RDELIM_in_region310;
   public static final BitSet FOLLOW_template_in_region316;
   public static final BitSet FOLLOW_INDENT_in_region320;
   public static final BitSet FOLLOW_LDELIM_in_region323;
   public static final BitSet FOLLOW_END_in_region325;
   public static final BitSet FOLLOW_RDELIM_in_region327;
   public static final BitSet FOLLOW_NEWLINE_in_region338;
   public static final BitSet FOLLOW_LCURLY_in_subtemplate414;
   public static final BitSet FOLLOW_ID_in_subtemplate420;
   public static final BitSet FOLLOW_COMMA_in_subtemplate424;
   public static final BitSet FOLLOW_ID_in_subtemplate429;
   public static final BitSet FOLLOW_PIPE_in_subtemplate434;
   public static final BitSet FOLLOW_template_in_subtemplate439;
   public static final BitSet FOLLOW_INDENT_in_subtemplate441;
   public static final BitSet FOLLOW_RCURLY_in_subtemplate444;
   public static final BitSet FOLLOW_INDENT_in_ifstat485;
   public static final BitSet FOLLOW_LDELIM_in_ifstat488;
   public static final BitSet FOLLOW_IF_in_ifstat490;
   public static final BitSet FOLLOW_LPAREN_in_ifstat492;
   public static final BitSet FOLLOW_conditional_in_ifstat496;
   public static final BitSet FOLLOW_RPAREN_in_ifstat498;
   public static final BitSet FOLLOW_RDELIM_in_ifstat500;
   public static final BitSet FOLLOW_template_in_ifstat509;
   public static final BitSet FOLLOW_INDENT_in_ifstat516;
   public static final BitSet FOLLOW_LDELIM_in_ifstat519;
   public static final BitSet FOLLOW_ELSEIF_in_ifstat521;
   public static final BitSet FOLLOW_LPAREN_in_ifstat523;
   public static final BitSet FOLLOW_conditional_in_ifstat527;
   public static final BitSet FOLLOW_RPAREN_in_ifstat529;
   public static final BitSet FOLLOW_RDELIM_in_ifstat531;
   public static final BitSet FOLLOW_template_in_ifstat535;
   public static final BitSet FOLLOW_INDENT_in_ifstat545;
   public static final BitSet FOLLOW_LDELIM_in_ifstat548;
   public static final BitSet FOLLOW_ELSE_in_ifstat550;
   public static final BitSet FOLLOW_RDELIM_in_ifstat552;
   public static final BitSet FOLLOW_template_in_ifstat556;
   public static final BitSet FOLLOW_INDENT_in_ifstat564;
   public static final BitSet FOLLOW_LDELIM_in_ifstat570;
   public static final BitSet FOLLOW_ENDIF_in_ifstat572;
   public static final BitSet FOLLOW_RDELIM_in_ifstat576;
   public static final BitSet FOLLOW_NEWLINE_in_ifstat587;
   public static final BitSet FOLLOW_andConditional_in_conditional707;
   public static final BitSet FOLLOW_OR_in_conditional711;
   public static final BitSet FOLLOW_andConditional_in_conditional714;
   public static final BitSet FOLLOW_notConditional_in_andConditional727;
   public static final BitSet FOLLOW_AND_in_andConditional731;
   public static final BitSet FOLLOW_notConditional_in_andConditional734;
   public static final BitSet FOLLOW_BANG_in_notConditional747;
   public static final BitSet FOLLOW_notConditional_in_notConditional750;
   public static final BitSet FOLLOW_memberExpr_in_notConditional755;
   public static final BitSet FOLLOW_ID_in_notConditionalExpr767;
   public static final BitSet FOLLOW_DOT_in_notConditionalExpr778;
   public static final BitSet FOLLOW_ID_in_notConditionalExpr782;
   public static final BitSet FOLLOW_DOT_in_notConditionalExpr808;
   public static final BitSet FOLLOW_LPAREN_in_notConditionalExpr810;
   public static final BitSet FOLLOW_mapExpr_in_notConditionalExpr812;
   public static final BitSet FOLLOW_RPAREN_in_notConditionalExpr814;
   public static final BitSet FOLLOW_option_in_exprOptions844;
   public static final BitSet FOLLOW_COMMA_in_exprOptions848;
   public static final BitSet FOLLOW_option_in_exprOptions850;
   public static final BitSet FOLLOW_ID_in_option877;
   public static final BitSet FOLLOW_EQUALS_in_option887;
   public static final BitSet FOLLOW_exprNoComma_in_option889;
   public static final BitSet FOLLOW_memberExpr_in_exprNoComma996;
   public static final BitSet FOLLOW_COLON_in_exprNoComma1002;
   public static final BitSet FOLLOW_mapTemplateRef_in_exprNoComma1004;
   public static final BitSet FOLLOW_mapExpr_in_expr1049;
   public static final BitSet FOLLOW_memberExpr_in_mapExpr1061;
   public static final BitSet FOLLOW_COMMA_in_mapExpr1070;
   public static final BitSet FOLLOW_memberExpr_in_mapExpr1072;
   public static final BitSet FOLLOW_COLON_in_mapExpr1078;
   public static final BitSet FOLLOW_mapTemplateRef_in_mapExpr1080;
   public static final BitSet FOLLOW_COLON_in_mapExpr1143;
   public static final BitSet FOLLOW_mapTemplateRef_in_mapExpr1147;
   public static final BitSet FOLLOW_COMMA_in_mapExpr1153;
   public static final BitSet FOLLOW_mapTemplateRef_in_mapExpr1157;
   public static final BitSet FOLLOW_ID_in_mapTemplateRef1204;
   public static final BitSet FOLLOW_LPAREN_in_mapTemplateRef1206;
   public static final BitSet FOLLOW_args_in_mapTemplateRef1208;
   public static final BitSet FOLLOW_RPAREN_in_mapTemplateRef1210;
   public static final BitSet FOLLOW_subtemplate_in_mapTemplateRef1232;
   public static final BitSet FOLLOW_LPAREN_in_mapTemplateRef1239;
   public static final BitSet FOLLOW_mapExpr_in_mapTemplateRef1241;
   public static final BitSet FOLLOW_RPAREN_in_mapTemplateRef1245;
   public static final BitSet FOLLOW_LPAREN_in_mapTemplateRef1247;
   public static final BitSet FOLLOW_argExprList_in_mapTemplateRef1249;
   public static final BitSet FOLLOW_RPAREN_in_mapTemplateRef1252;
   public static final BitSet FOLLOW_includeExpr_in_memberExpr1275;
   public static final BitSet FOLLOW_DOT_in_memberExpr1286;
   public static final BitSet FOLLOW_ID_in_memberExpr1288;
   public static final BitSet FOLLOW_DOT_in_memberExpr1314;
   public static final BitSet FOLLOW_LPAREN_in_memberExpr1316;
   public static final BitSet FOLLOW_mapExpr_in_memberExpr1318;
   public static final BitSet FOLLOW_RPAREN_in_memberExpr1320;
   public static final BitSet FOLLOW_ID_in_includeExpr1364;
   public static final BitSet FOLLOW_LPAREN_in_includeExpr1366;
   public static final BitSet FOLLOW_expr_in_includeExpr1368;
   public static final BitSet FOLLOW_RPAREN_in_includeExpr1371;
   public static final BitSet FOLLOW_SUPER_in_includeExpr1392;
   public static final BitSet FOLLOW_DOT_in_includeExpr1394;
   public static final BitSet FOLLOW_ID_in_includeExpr1396;
   public static final BitSet FOLLOW_LPAREN_in_includeExpr1398;
   public static final BitSet FOLLOW_args_in_includeExpr1400;
   public static final BitSet FOLLOW_RPAREN_in_includeExpr1402;
   public static final BitSet FOLLOW_ID_in_includeExpr1421;
   public static final BitSet FOLLOW_LPAREN_in_includeExpr1423;
   public static final BitSet FOLLOW_args_in_includeExpr1425;
   public static final BitSet FOLLOW_RPAREN_in_includeExpr1427;
   public static final BitSet FOLLOW_AT_in_includeExpr1449;
   public static final BitSet FOLLOW_SUPER_in_includeExpr1451;
   public static final BitSet FOLLOW_DOT_in_includeExpr1453;
   public static final BitSet FOLLOW_ID_in_includeExpr1455;
   public static final BitSet FOLLOW_LPAREN_in_includeExpr1457;
   public static final BitSet FOLLOW_RPAREN_in_includeExpr1461;
   public static final BitSet FOLLOW_AT_in_includeExpr1476;
   public static final BitSet FOLLOW_ID_in_includeExpr1478;
   public static final BitSet FOLLOW_LPAREN_in_includeExpr1480;
   public static final BitSet FOLLOW_RPAREN_in_includeExpr1484;
   public static final BitSet FOLLOW_primary_in_includeExpr1502;
   public static final BitSet FOLLOW_ID_in_primary1513;
   public static final BitSet FOLLOW_STRING_in_primary1518;
   public static final BitSet FOLLOW_TRUE_in_primary1523;
   public static final BitSet FOLLOW_FALSE_in_primary1528;
   public static final BitSet FOLLOW_subtemplate_in_primary1533;
   public static final BitSet FOLLOW_list_in_primary1538;
   public static final BitSet FOLLOW_LPAREN_in_primary1547;
   public static final BitSet FOLLOW_conditional_in_primary1550;
   public static final BitSet FOLLOW_RPAREN_in_primary1552;
   public static final BitSet FOLLOW_LPAREN_in_primary1563;
   public static final BitSet FOLLOW_expr_in_primary1565;
   public static final BitSet FOLLOW_RPAREN_in_primary1567;
   public static final BitSet FOLLOW_LPAREN_in_primary1573;
   public static final BitSet FOLLOW_argExprList_in_primary1575;
   public static final BitSet FOLLOW_RPAREN_in_primary1578;
   public static final BitSet FOLLOW_argExprList_in_args1634;
   public static final BitSet FOLLOW_namedArg_in_args1639;
   public static final BitSet FOLLOW_COMMA_in_args1643;
   public static final BitSet FOLLOW_namedArg_in_args1645;
   public static final BitSet FOLLOW_COMMA_in_args1651;
   public static final BitSet FOLLOW_ELLIPSIS_in_args1653;
   public static final BitSet FOLLOW_ELLIPSIS_in_args1673;
   public static final BitSet FOLLOW_arg_in_argExprList1686;
   public static final BitSet FOLLOW_COMMA_in_argExprList1690;
   public static final BitSet FOLLOW_arg_in_argExprList1692;
   public static final BitSet FOLLOW_exprNoComma_in_arg1709;
   public static final BitSet FOLLOW_ID_in_namedArg1718;
   public static final BitSet FOLLOW_EQUALS_in_namedArg1720;
   public static final BitSet FOLLOW_arg_in_namedArg1722;
   public static final BitSet FOLLOW_LBRACK_in_list1747;
   public static final BitSet FOLLOW_RBRACK_in_list1749;
   public static final BitSet FOLLOW_LBRACK_in_list1761;
   public static final BitSet FOLLOW_listElement_in_list1763;
   public static final BitSet FOLLOW_COMMA_in_list1767;
   public static final BitSet FOLLOW_listElement_in_list1769;
   public static final BitSet FOLLOW_RBRACK_in_list1774;
   public static final BitSet FOLLOW_exprNoComma_in_listElement1794;

   public STParser(TokenStream input) {
      this(input, new RecognizerSharedState());
   }

   public STParser(TokenStream input, RecognizerSharedState state) {
      super(input, state);
      this.adaptor = new CommonTreeAdaptor();
      this.conditional_stack = new Stack();
      this.dfa3 = new DFA3(this);
      this.dfa35 = new DFA35(this);
      this.dfa38 = new DFA38(this);
   }

   public void setTreeAdaptor(TreeAdaptor adaptor) {
      this.adaptor = adaptor;
   }

   public TreeAdaptor getTreeAdaptor() {
      return this.adaptor;
   }

   public String[] getTokenNames() {
      return tokenNames;
   }

   public String getGrammarFileName() {
      return "org/stringtemplate/v4/compiler/STParser.g";
   }

   public STParser(TokenStream input, ErrorManager errMgr, Token templateToken) {
      this(input);
      this.errMgr = errMgr;
      this.templateToken = templateToken;
   }

   protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow) throws RecognitionException {
      throw new MismatchedTokenException(ttype, input);
   }

   public final templateAndEOF_return templateAndEOF() throws RecognitionException {
      templateAndEOF_return retval = new templateAndEOF_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken EOF2 = null;
      template_return template1 = null;
      CommonTree EOF2_tree = null;
      RewriteRuleTokenStream stream_EOF = new RewriteRuleTokenStream(this.adaptor, "token EOF");
      RewriteRuleSubtreeStream stream_template = new RewriteRuleSubtreeStream(this.adaptor, "rule template");

      try {
         this.pushFollow(FOLLOW_template_in_templateAndEOF133);
         template1 = this.template();
         --this.state._fsp;
         stream_template.add(template1.getTree());
         EOF2 = (CommonToken)this.match(this.input, -1, FOLLOW_EOF_in_templateAndEOF135);
         stream_EOF.add(EOF2);
         retval.tree = root_0;
         new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
         root_0 = (CommonTree)this.adaptor.nil();
         if (stream_template.hasNext()) {
            this.adaptor.addChild(root_0, stream_template.nextTree());
         }

         stream_template.reset();
         retval.tree = root_0;
         retval.stop = this.input.LT(-1);
         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         return retval;
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final template_return template() throws RecognitionException {
      template_return retval = new template_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      element_return element3 = null;

      try {
         root_0 = (CommonTree)this.adaptor.nil();

         while(true) {
            int alt1;
            alt1 = 2;
            label78:
            switch (this.input.LA(1)) {
               case 22:
               case 32:
               case 37:
                  alt1 = 1;
                  break;
               case 23:
                  switch (this.input.LA(2)) {
                     case 4:
                     case 8:
                     case 14:
                     case 16:
                     case 20:
                     case 25:
                     case 26:
                     case 33:
                     case 35:
                     case 36:
                        alt1 = 1;
                     case 5:
                     case 6:
                     case 7:
                     case 9:
                     case 10:
                     case 11:
                     case 12:
                     case 13:
                     case 15:
                     case 17:
                     case 18:
                     case 19:
                     case 21:
                     case 22:
                     case 23:
                     case 24:
                     case 27:
                     case 28:
                     case 29:
                     case 30:
                     case 31:
                     case 32:
                     case 34:
                     default:
                        break label78;
                  }
               case 31:
                  switch (this.input.LA(2)) {
                     case 22:
                     case 32:
                     case 37:
                        alt1 = 1;
                        break;
                     case 23:
                        switch (this.input.LA(3)) {
                           case 4:
                           case 8:
                           case 14:
                           case 16:
                           case 20:
                           case 25:
                           case 26:
                           case 33:
                           case 35:
                           case 36:
                              alt1 = 1;
                           case 5:
                           case 6:
                           case 7:
                           case 9:
                           case 10:
                           case 11:
                           case 12:
                           case 13:
                           case 15:
                           case 17:
                           case 18:
                           case 19:
                           case 21:
                           case 22:
                           case 23:
                           case 24:
                           case 27:
                           case 28:
                           case 29:
                           case 30:
                           case 31:
                           case 32:
                           case 34:
                        }
                  }
            }

            switch (alt1) {
               case 1:
                  this.pushFollow(FOLLOW_element_in_template149);
                  element3 = this.element();
                  --this.state._fsp;
                  this.adaptor.addChild(root_0, element3.getTree());
                  break;
               default:
                  retval.stop = this.input.LT(-1);
                  retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                  this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                  return retval;
            }
         }
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final element_return element() throws RecognitionException {
      element_return retval = new element_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken INDENT4 = null;
      CommonToken COMMENT5 = null;
      CommonToken NEWLINE6 = null;
      CommonToken INDENT7 = null;
      singleElement_return singleElement8 = null;
      singleElement_return singleElement9 = null;
      compoundElement_return compoundElement10 = null;
      CommonTree INDENT4_tree = null;
      CommonTree COMMENT5_tree = null;
      CommonTree NEWLINE6_tree = null;
      CommonTree INDENT7_tree = null;
      RewriteRuleTokenStream stream_NEWLINE = new RewriteRuleTokenStream(this.adaptor, "token NEWLINE");
      RewriteRuleTokenStream stream_COMMENT = new RewriteRuleTokenStream(this.adaptor, "token COMMENT");
      RewriteRuleTokenStream stream_INDENT = new RewriteRuleTokenStream(this.adaptor, "token INDENT");
      RewriteRuleSubtreeStream stream_singleElement = new RewriteRuleSubtreeStream(this.adaptor, "rule singleElement");

      try {
         int alt3 = 4;
         alt3 = this.dfa3.predict(this.input);
         label88:
         switch (alt3) {
            case 1:
               if (this.input.LT(1).getCharPositionInLine() != 0) {
                  throw new FailedPredicateException(this.input, "element", "input.LT(1).getCharPositionInLine()==0");
               }

               int alt2 = 2;
               switch (this.input.LA(1)) {
                  case 31:
                     alt2 = 1;
                  default:
                     switch (alt2) {
                        case 1:
                           INDENT4 = (CommonToken)this.match(this.input, 31, FOLLOW_INDENT_in_element162);
                           stream_INDENT.add(INDENT4);
                        default:
                           COMMENT5 = (CommonToken)this.match(this.input, 37, FOLLOW_COMMENT_in_element165);
                           stream_COMMENT.add(COMMENT5);
                           NEWLINE6 = (CommonToken)this.match(this.input, 32, FOLLOW_NEWLINE_in_element167);
                           stream_NEWLINE.add(NEWLINE6);
                           retval.tree = root_0;
                           new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                           CommonTree var26 = (CommonTree)this.adaptor.nil();
                           root_0 = null;
                           retval.tree = root_0;
                           break label88;
                     }
               }
            case 2:
               INDENT7 = (CommonToken)this.match(this.input, 31, FOLLOW_INDENT_in_element175);
               stream_INDENT.add(INDENT7);
               this.pushFollow(FOLLOW_singleElement_in_element177);
               singleElement8 = this.singleElement();
               --this.state._fsp;
               stream_singleElement.add(singleElement8.getTree());
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(57, "INDENTED_EXPR"), root_1);
               this.adaptor.addChild(root_1, stream_INDENT.nextNode());
               if (stream_singleElement.hasNext()) {
                  this.adaptor.addChild(root_1, stream_singleElement.nextTree());
               }

               stream_singleElement.reset();
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
               break;
            case 3:
               root_0 = (CommonTree)this.adaptor.nil();
               this.pushFollow(FOLLOW_singleElement_in_element194);
               singleElement9 = this.singleElement();
               --this.state._fsp;
               this.adaptor.addChild(root_0, singleElement9.getTree());
               break;
            case 4:
               root_0 = (CommonTree)this.adaptor.nil();
               this.pushFollow(FOLLOW_compoundElement_in_element199);
               compoundElement10 = this.compoundElement();
               --this.state._fsp;
               this.adaptor.addChild(root_0, compoundElement10.getTree());
         }

         retval.stop = this.input.LT(-1);
         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         return retval;
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final singleElement_return singleElement() throws RecognitionException {
      singleElement_return retval = new singleElement_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken TEXT12 = null;
      CommonToken NEWLINE13 = null;
      CommonToken COMMENT14 = null;
      exprTag_return exprTag11 = null;
      CommonTree TEXT12_tree = null;
      CommonTree NEWLINE13_tree = null;
      CommonTree COMMENT14_tree = null;

      try {
         int alt4 = 4;
         switch (this.input.LA(1)) {
            case 22:
               alt4 = 2;
               break;
            case 23:
               alt4 = 1;
               break;
            case 32:
               alt4 = 3;
               break;
            case 37:
               alt4 = 4;
               break;
            default:
               NoViableAltException nvae = new NoViableAltException("", 4, 0, this.input);
               throw nvae;
         }

         switch (alt4) {
            case 1:
               root_0 = (CommonTree)this.adaptor.nil();
               this.pushFollow(FOLLOW_exprTag_in_singleElement210);
               exprTag11 = this.exprTag();
               --this.state._fsp;
               this.adaptor.addChild(root_0, exprTag11.getTree());
               break;
            case 2:
               root_0 = (CommonTree)this.adaptor.nil();
               TEXT12 = (CommonToken)this.match(this.input, 22, FOLLOW_TEXT_in_singleElement215);
               TEXT12_tree = (CommonTree)this.adaptor.create(TEXT12);
               this.adaptor.addChild(root_0, TEXT12_tree);
               break;
            case 3:
               root_0 = (CommonTree)this.adaptor.nil();
               NEWLINE13 = (CommonToken)this.match(this.input, 32, FOLLOW_NEWLINE_in_singleElement220);
               NEWLINE13_tree = (CommonTree)this.adaptor.create(NEWLINE13);
               this.adaptor.addChild(root_0, NEWLINE13_tree);
               break;
            case 4:
               root_0 = (CommonTree)this.adaptor.nil();
               COMMENT14 = (CommonToken)this.match(this.input, 37, FOLLOW_COMMENT_in_singleElement225);
         }

         retval.stop = this.input.LT(-1);
         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         return retval;
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final compoundElement_return compoundElement() throws RecognitionException {
      compoundElement_return retval = new compoundElement_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      ifstat_return ifstat15 = null;
      region_return region16 = null;

      try {
         int alt5;
         alt5 = 2;
         label75:
         switch (this.input.LA(1)) {
            case 23:
               switch (this.input.LA(2)) {
                  case 4:
                     alt5 = 1;
                     break label75;
                  case 33:
                     alt5 = 2;
                     break label75;
                  default:
                     NoViableAltException nvae = new NoViableAltException("", 5, 2, this.input);
                     throw nvae;
               }
            case 31:
               switch (this.input.LA(2)) {
                  case 23:
                     switch (this.input.LA(3)) {
                        case 4:
                           alt5 = 1;
                           break label75;
                        case 33:
                           alt5 = 2;
                           break label75;
                        default:
                           NoViableAltException nvae = new NoViableAltException("", 5, 2, this.input);
                           throw nvae;
                     }
                  default:
                     NoViableAltException nvae = new NoViableAltException("", 5, 1, this.input);
                     throw nvae;
               }
            default:
               NoViableAltException nvae = new NoViableAltException("", 5, 0, this.input);
               throw nvae;
         }

         switch (alt5) {
            case 1:
               root_0 = (CommonTree)this.adaptor.nil();
               this.pushFollow(FOLLOW_ifstat_in_compoundElement238);
               ifstat15 = this.ifstat();
               --this.state._fsp;
               this.adaptor.addChild(root_0, ifstat15.getTree());
               break;
            case 2:
               root_0 = (CommonTree)this.adaptor.nil();
               this.pushFollow(FOLLOW_region_in_compoundElement243);
               region16 = this.region();
               --this.state._fsp;
               this.adaptor.addChild(root_0, region16.getTree());
         }

         retval.stop = this.input.LT(-1);
         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         return retval;
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final exprTag_return exprTag() throws RecognitionException {
      exprTag_return retval = new exprTag_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken LDELIM17 = null;
      CommonToken char_literal19 = null;
      CommonToken RDELIM21 = null;
      expr_return expr18 = null;
      exprOptions_return exprOptions20 = null;
      CommonTree LDELIM17_tree = null;
      CommonTree char_literal19_tree = null;
      CommonTree RDELIM21_tree = null;
      RewriteRuleTokenStream stream_RDELIM = new RewriteRuleTokenStream(this.adaptor, "token RDELIM");
      RewriteRuleTokenStream stream_LDELIM = new RewriteRuleTokenStream(this.adaptor, "token LDELIM");
      RewriteRuleTokenStream stream_SEMI = new RewriteRuleTokenStream(this.adaptor, "token SEMI");
      RewriteRuleSubtreeStream stream_exprOptions = new RewriteRuleSubtreeStream(this.adaptor, "rule exprOptions");
      RewriteRuleSubtreeStream stream_expr = new RewriteRuleSubtreeStream(this.adaptor, "rule expr");

      try {
         LDELIM17 = (CommonToken)this.match(this.input, 23, FOLLOW_LDELIM_in_exprTag254);
         stream_LDELIM.add(LDELIM17);
         this.pushFollow(FOLLOW_expr_in_exprTag256);
         expr18 = this.expr();
         --this.state._fsp;
         stream_expr.add(expr18.getTree());
         int alt6 = 2;
         switch (this.input.LA(1)) {
            case 9:
               alt6 = 1;
            default:
               switch (alt6) {
                  case 1:
                     char_literal19 = (CommonToken)this.match(this.input, 9, FOLLOW_SEMI_in_exprTag260);
                     stream_SEMI.add(char_literal19);
                     this.pushFollow(FOLLOW_exprOptions_in_exprTag262);
                     exprOptions20 = this.exprOptions();
                     --this.state._fsp;
                     stream_exprOptions.add(exprOptions20.getTree());
                  default:
                     RDELIM21 = (CommonToken)this.match(this.input, 24, FOLLOW_RDELIM_in_exprTag267);
                     stream_RDELIM.add(RDELIM21);
                     retval.tree = root_0;
                     new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                     root_0 = (CommonTree)this.adaptor.nil();
                     CommonTree root_1 = (CommonTree)this.adaptor.nil();
                     root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(38, LDELIM17, "EXPR"), root_1);
                     this.adaptor.addChild(root_1, stream_expr.nextTree());
                     if (stream_exprOptions.hasNext()) {
                        this.adaptor.addChild(root_1, stream_exprOptions.nextTree());
                     }

                     stream_exprOptions.reset();
                     this.adaptor.addChild(root_0, root_1);
                     retval.tree = root_0;
                     retval.stop = this.input.LT(-1);
                     retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                     this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                     return retval;
               }
         }
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final region_return region() throws RecognitionException {
      region_return retval = new region_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken i = null;
      CommonToken x = null;
      CommonToken char_literal22 = null;
      CommonToken ID23 = null;
      CommonToken RDELIM24 = null;
      CommonToken INDENT26 = null;
      CommonToken LDELIM27 = null;
      CommonToken string_literal28 = null;
      CommonToken RDELIM29 = null;
      CommonToken NEWLINE30 = null;
      template_return template25 = null;
      CommonTree i_tree = null;
      CommonTree x_tree = null;
      CommonTree char_literal22_tree = null;
      CommonTree ID23_tree = null;
      CommonTree RDELIM24_tree = null;
      CommonTree INDENT26_tree = null;
      CommonTree LDELIM27_tree = null;
      CommonTree string_literal28_tree = null;
      CommonTree RDELIM29_tree = null;
      CommonTree NEWLINE30_tree = null;
      RewriteRuleTokenStream stream_AT = new RewriteRuleTokenStream(this.adaptor, "token AT");
      RewriteRuleTokenStream stream_RDELIM = new RewriteRuleTokenStream(this.adaptor, "token RDELIM");
      RewriteRuleTokenStream stream_NEWLINE = new RewriteRuleTokenStream(this.adaptor, "token NEWLINE");
      RewriteRuleTokenStream stream_ID = new RewriteRuleTokenStream(this.adaptor, "token ID");
      RewriteRuleTokenStream stream_END = new RewriteRuleTokenStream(this.adaptor, "token END");
      RewriteRuleTokenStream stream_LDELIM = new RewriteRuleTokenStream(this.adaptor, "token LDELIM");
      RewriteRuleTokenStream stream_INDENT = new RewriteRuleTokenStream(this.adaptor, "token INDENT");
      RewriteRuleSubtreeStream stream_template = new RewriteRuleSubtreeStream(this.adaptor, "rule template");
      Token indent = null;

      try {
         int alt7 = 2;
         switch (this.input.LA(1)) {
            case 31:
               alt7 = 1;
            default:
               switch (alt7) {
                  case 1:
                     i = (CommonToken)this.match(this.input, 31, FOLLOW_INDENT_in_region299);
                     stream_INDENT.add(i);
                  default:
                     x = (CommonToken)this.match(this.input, 23, FOLLOW_LDELIM_in_region304);
                     stream_LDELIM.add(x);
                     char_literal22 = (CommonToken)this.match(this.input, 33, FOLLOW_AT_in_region306);
                     stream_AT.add(char_literal22);
                     ID23 = (CommonToken)this.match(this.input, 25, FOLLOW_ID_in_region308);
                     stream_ID.add(ID23);
                     RDELIM24 = (CommonToken)this.match(this.input, 24, FOLLOW_RDELIM_in_region310);
                     stream_RDELIM.add(RDELIM24);
                     if (this.input.LA(1) != 32) {
                        indent = i;
                     }

                     this.pushFollow(FOLLOW_template_in_region316);
                     template25 = this.template();
                     --this.state._fsp;
                     stream_template.add(template25.getTree());
                     int alt8 = 2;
                     switch (this.input.LA(1)) {
                        case 31:
                           alt8 = 1;
                        default:
                           switch (alt8) {
                              case 1:
                                 INDENT26 = (CommonToken)this.match(this.input, 31, FOLLOW_INDENT_in_region320);
                                 stream_INDENT.add(INDENT26);
                              default:
                                 LDELIM27 = (CommonToken)this.match(this.input, 23, FOLLOW_LDELIM_in_region323);
                                 stream_LDELIM.add(LDELIM27);
                                 string_literal28 = (CommonToken)this.match(this.input, 34, FOLLOW_END_in_region325);
                                 stream_END.add(string_literal28);
                                 RDELIM29 = (CommonToken)this.match(this.input, 24, FOLLOW_RDELIM_in_region327);
                                 stream_RDELIM.add(RDELIM29);
                                 int alt9 = 2;
                                 switch (this.input.LA(1)) {
                                    case 32:
                                       int LA9_1 = this.input.LA(2);
                                       if (((CommonToken)retval.start).getLine() != this.input.LT(1).getLine()) {
                                          alt9 = 1;
                                       }
                                    default:
                                       switch (alt9) {
                                          case 1:
                                             if (((CommonToken)retval.start).getLine() == this.input.LT(1).getLine()) {
                                                throw new FailedPredicateException(this.input, "region", "$region.start.getLine()!=input.LT(1).getLine()");
                                             } else {
                                                NEWLINE30 = (CommonToken)this.match(this.input, 32, FOLLOW_NEWLINE_in_region338);
                                                stream_NEWLINE.add(NEWLINE30);
                                             }
                                          default:
                                             retval.tree = root_0;
                                             RewriteRuleTokenStream stream_i = new RewriteRuleTokenStream(this.adaptor, "token i", i);
                                             new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                                             root_0 = (CommonTree)this.adaptor.nil();
                                             if (indent != null) {
                                                CommonTree root_1 = (CommonTree)this.adaptor.nil();
                                                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(57, "INDENTED_EXPR"), root_1);
                                                this.adaptor.addChild(root_1, stream_i.nextNode());
                                                CommonTree root_2 = (CommonTree)this.adaptor.nil();
                                                root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(55, x), root_2);
                                                this.adaptor.addChild(root_2, stream_ID.nextNode());
                                                if (stream_template.hasNext()) {
                                                   this.adaptor.addChild(root_2, stream_template.nextTree());
                                                }

                                                stream_template.reset();
                                                this.adaptor.addChild(root_1, root_2);
                                                this.adaptor.addChild(root_0, root_1);
                                             } else {
                                                CommonTree root_1 = (CommonTree)this.adaptor.nil();
                                                root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(55, x), root_1);
                                                this.adaptor.addChild(root_1, stream_ID.nextNode());
                                                if (stream_template.hasNext()) {
                                                   this.adaptor.addChild(root_1, stream_template.nextTree());
                                                }

                                                stream_template.reset();
                                                this.adaptor.addChild(root_0, root_1);
                                             }

                                             retval.tree = root_0;
                                             retval.stop = this.input.LT(-1);
                                             retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                                             this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                                             return retval;
                                       }
                                 }
                           }
                     }
               }
         }
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final subtemplate_return subtemplate() throws RecognitionException {
      subtemplate_return retval = new subtemplate_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken lc = null;
      CommonToken char_literal31 = null;
      CommonToken char_literal32 = null;
      CommonToken INDENT34 = null;
      CommonToken char_literal35 = null;
      CommonToken ids = null;
      List list_ids = null;
      template_return template33 = null;
      CommonTree lc_tree = null;
      CommonTree char_literal31_tree = null;
      CommonTree char_literal32_tree = null;
      CommonTree INDENT34_tree = null;
      CommonTree char_literal35_tree = null;
      CommonTree ids_tree = null;
      RewriteRuleTokenStream stream_LCURLY = new RewriteRuleTokenStream(this.adaptor, "token LCURLY");
      RewriteRuleTokenStream stream_PIPE = new RewriteRuleTokenStream(this.adaptor, "token PIPE");
      RewriteRuleTokenStream stream_ID = new RewriteRuleTokenStream(this.adaptor, "token ID");
      RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
      RewriteRuleTokenStream stream_INDENT = new RewriteRuleTokenStream(this.adaptor, "token INDENT");
      RewriteRuleTokenStream stream_RCURLY = new RewriteRuleTokenStream(this.adaptor, "token RCURLY");
      RewriteRuleSubtreeStream stream_template = new RewriteRuleSubtreeStream(this.adaptor, "rule template");

      try {
         lc = (CommonToken)this.match(this.input, 20, FOLLOW_LCURLY_in_subtemplate414);
         stream_LCURLY.add(lc);
         int alt11 = 2;
         switch (this.input.LA(1)) {
            case 25:
               alt11 = 1;
         }

         switch (alt11) {
            case 1:
               ids = (CommonToken)this.match(this.input, 25, FOLLOW_ID_in_subtemplate420);
               stream_ID.add(ids);
               if (list_ids == null) {
                  list_ids = new ArrayList();
               }

               list_ids.add(ids);

               label138:
               while(true) {
                  int alt10 = 2;
                  switch (this.input.LA(1)) {
                     case 18:
                        alt10 = 1;
                  }

                  switch (alt10) {
                     case 1:
                        char_literal31 = (CommonToken)this.match(this.input, 18, FOLLOW_COMMA_in_subtemplate424);
                        stream_COMMA.add(char_literal31);
                        ids = (CommonToken)this.match(this.input, 25, FOLLOW_ID_in_subtemplate429);
                        stream_ID.add(ids);
                        if (list_ids == null) {
                           list_ids = new ArrayList();
                        }

                        list_ids.add(ids);
                        break;
                     default:
                        char_literal32 = (CommonToken)this.match(this.input, 28, FOLLOW_PIPE_in_subtemplate434);
                        stream_PIPE.add(char_literal32);
                        break label138;
                  }
               }
            default:
               this.pushFollow(FOLLOW_template_in_subtemplate439);
               template33 = this.template();
               --this.state._fsp;
               stream_template.add(template33.getTree());
               int alt12 = 2;
               switch (this.input.LA(1)) {
                  case 31:
                     alt12 = 1;
                  default:
                     switch (alt12) {
                        case 1:
                           INDENT34 = (CommonToken)this.match(this.input, 31, FOLLOW_INDENT_in_subtemplate441);
                           stream_INDENT.add(INDENT34);
                        default:
                           char_literal35 = (CommonToken)this.match(this.input, 21, FOLLOW_RCURLY_in_subtemplate444);
                           stream_RCURLY.add(char_literal35);
                           retval.tree = root_0;
                           RewriteRuleTokenStream stream_ids = new RewriteRuleTokenStream(this.adaptor, "token ids", list_ids);
                           new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                           root_0 = (CommonTree)this.adaptor.nil();
                           CommonTree root_1 = (CommonTree)this.adaptor.nil();
                           root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(52, lc, "SUBTEMPLATE"), root_1);

                           while(stream_ids.hasNext()) {
                              CommonTree root_2 = (CommonTree)this.adaptor.nil();
                              root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(53, "ARGS"), root_2);
                              this.adaptor.addChild(root_2, stream_ids.nextNode());
                              this.adaptor.addChild(root_1, root_2);
                           }

                           stream_ids.reset();
                           if (stream_template.hasNext()) {
                              this.adaptor.addChild(root_1, stream_template.nextTree());
                           }

                           stream_template.reset();
                           this.adaptor.addChild(root_0, root_1);
                           retval.tree = root_0;
                           retval.stop = this.input.LT(-1);
                           retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                           this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                           return retval;
                     }
               }
         }
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final ifstat_return ifstat() throws RecognitionException {
      ifstat_return retval = new ifstat_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken i = null;
      CommonToken endif = null;
      CommonToken LDELIM36 = null;
      CommonToken string_literal37 = null;
      CommonToken char_literal38 = null;
      CommonToken char_literal39 = null;
      CommonToken RDELIM40 = null;
      CommonToken INDENT41 = null;
      CommonToken LDELIM42 = null;
      CommonToken string_literal43 = null;
      CommonToken char_literal44 = null;
      CommonToken char_literal45 = null;
      CommonToken RDELIM46 = null;
      CommonToken INDENT47 = null;
      CommonToken LDELIM48 = null;
      CommonToken string_literal49 = null;
      CommonToken RDELIM50 = null;
      CommonToken INDENT51 = null;
      CommonToken string_literal52 = null;
      CommonToken RDELIM53 = null;
      CommonToken NEWLINE54 = null;
      List list_c2 = null;
      List list_t2 = null;
      conditional_return c1 = null;
      template_return t1 = null;
      template_return t3 = null;
      RuleReturnScope c2 = null;
      RuleReturnScope t2 = null;
      CommonTree i_tree = null;
      CommonTree endif_tree = null;
      CommonTree LDELIM36_tree = null;
      CommonTree string_literal37_tree = null;
      CommonTree char_literal38_tree = null;
      CommonTree char_literal39_tree = null;
      CommonTree RDELIM40_tree = null;
      CommonTree INDENT41_tree = null;
      CommonTree LDELIM42_tree = null;
      CommonTree string_literal43_tree = null;
      CommonTree char_literal44_tree = null;
      CommonTree char_literal45_tree = null;
      CommonTree RDELIM46_tree = null;
      CommonTree INDENT47_tree = null;
      CommonTree LDELIM48_tree = null;
      CommonTree string_literal49_tree = null;
      CommonTree RDELIM50_tree = null;
      CommonTree INDENT51_tree = null;
      CommonTree string_literal52_tree = null;
      CommonTree RDELIM53_tree = null;
      CommonTree NEWLINE54_tree = null;
      RewriteRuleTokenStream stream_ENDIF = new RewriteRuleTokenStream(this.adaptor, "token ENDIF");
      RewriteRuleTokenStream stream_RDELIM = new RewriteRuleTokenStream(this.adaptor, "token RDELIM");
      RewriteRuleTokenStream stream_RPAREN = new RewriteRuleTokenStream(this.adaptor, "token RPAREN");
      RewriteRuleTokenStream stream_NEWLINE = new RewriteRuleTokenStream(this.adaptor, "token NEWLINE");
      RewriteRuleTokenStream stream_LDELIM = new RewriteRuleTokenStream(this.adaptor, "token LDELIM");
      RewriteRuleTokenStream stream_INDENT = new RewriteRuleTokenStream(this.adaptor, "token INDENT");
      RewriteRuleTokenStream stream_LPAREN = new RewriteRuleTokenStream(this.adaptor, "token LPAREN");
      RewriteRuleTokenStream stream_IF = new RewriteRuleTokenStream(this.adaptor, "token IF");
      RewriteRuleTokenStream stream_ELSE = new RewriteRuleTokenStream(this.adaptor, "token ELSE");
      RewriteRuleTokenStream stream_ELSEIF = new RewriteRuleTokenStream(this.adaptor, "token ELSEIF");
      RewriteRuleSubtreeStream stream_template = new RewriteRuleSubtreeStream(this.adaptor, "rule template");
      RewriteRuleSubtreeStream stream_conditional = new RewriteRuleSubtreeStream(this.adaptor, "rule conditional");
      Token indent = null;

      try {
         int alt13 = 2;
         switch (this.input.LA(1)) {
            case 31:
               alt13 = 1;
            default:
               switch (alt13) {
                  case 1:
                     i = (CommonToken)this.match(this.input, 31, FOLLOW_INDENT_in_ifstat485);
                     stream_INDENT.add(i);
                  default:
                     LDELIM36 = (CommonToken)this.match(this.input, 23, FOLLOW_LDELIM_in_ifstat488);
                     stream_LDELIM.add(LDELIM36);
                     string_literal37 = (CommonToken)this.match(this.input, 4, FOLLOW_IF_in_ifstat490);
                     stream_IF.add(string_literal37);
                     char_literal38 = (CommonToken)this.match(this.input, 14, FOLLOW_LPAREN_in_ifstat492);
                     stream_LPAREN.add(char_literal38);
                     this.pushFollow(FOLLOW_conditional_in_ifstat496);
                     c1 = this.conditional();
                     --this.state._fsp;
                     stream_conditional.add(c1.getTree());
                     char_literal39 = (CommonToken)this.match(this.input, 15, FOLLOW_RPAREN_in_ifstat498);
                     stream_RPAREN.add(char_literal39);
                     RDELIM40 = (CommonToken)this.match(this.input, 24, FOLLOW_RDELIM_in_ifstat500);
                     stream_RDELIM.add(RDELIM40);
                     if (this.input.LA(1) != 32) {
                        indent = i;
                     }

                     this.pushFollow(FOLLOW_template_in_ifstat509);
                     t1 = this.template();
                     --this.state._fsp;
                     stream_template.add(t1.getTree());

                     while(true) {
                        int alt15;
                        alt15 = 2;
                        label355:
                        switch (this.input.LA(1)) {
                           case 23:
                              switch (this.input.LA(2)) {
                                 case 6:
                                    alt15 = 1;
                                 default:
                                    break label355;
                              }
                           case 31:
                              switch (this.input.LA(2)) {
                                 case 23:
                                    switch (this.input.LA(3)) {
                                       case 6:
                                          alt15 = 1;
                                    }
                              }
                        }

                        switch (alt15) {
                           case 1:
                              int alt14 = 2;
                              switch (this.input.LA(1)) {
                                 case 31:
                                    alt14 = 1;
                                 default:
                                    switch (alt14) {
                                       case 1:
                                          INDENT41 = (CommonToken)this.match(this.input, 31, FOLLOW_INDENT_in_ifstat516);
                                          stream_INDENT.add(INDENT41);
                                       default:
                                          LDELIM42 = (CommonToken)this.match(this.input, 23, FOLLOW_LDELIM_in_ifstat519);
                                          stream_LDELIM.add(LDELIM42);
                                          string_literal43 = (CommonToken)this.match(this.input, 6, FOLLOW_ELSEIF_in_ifstat521);
                                          stream_ELSEIF.add(string_literal43);
                                          char_literal44 = (CommonToken)this.match(this.input, 14, FOLLOW_LPAREN_in_ifstat523);
                                          stream_LPAREN.add(char_literal44);
                                          this.pushFollow(FOLLOW_conditional_in_ifstat527);
                                          RuleReturnScope var107 = this.conditional();
                                          --this.state._fsp;
                                          stream_conditional.add(((RuleReturnScope)var107).getTree());
                                          if (list_c2 == null) {
                                             list_c2 = new ArrayList();
                                          }

                                          list_c2.add(((RuleReturnScope)var107).getTree());
                                          char_literal45 = (CommonToken)this.match(this.input, 15, FOLLOW_RPAREN_in_ifstat529);
                                          stream_RPAREN.add(char_literal45);
                                          RDELIM46 = (CommonToken)this.match(this.input, 24, FOLLOW_RDELIM_in_ifstat531);
                                          stream_RDELIM.add(RDELIM46);
                                          this.pushFollow(FOLLOW_template_in_ifstat535);
                                          RuleReturnScope var108 = this.template();
                                          --this.state._fsp;
                                          stream_template.add(((RuleReturnScope)var108).getTree());
                                          if (list_t2 == null) {
                                             list_t2 = new ArrayList();
                                          }

                                          list_t2.add(((RuleReturnScope)var108).getTree());
                                          continue;
                                    }
                              }
                           default:
                              alt15 = 2;
                              label343:
                              switch (this.input.LA(1)) {
                                 case 23:
                                    switch (this.input.LA(2)) {
                                       case 5:
                                          alt15 = 1;
                                       default:
                                          break label343;
                                    }
                                 case 31:
                                    switch (this.input.LA(2)) {
                                       case 23:
                                          switch (this.input.LA(3)) {
                                             case 5:
                                                alt15 = 1;
                                          }
                                    }
                              }

                              switch (alt15) {
                                 case 1:
                                    int alt16 = 2;
                                    switch (this.input.LA(1)) {
                                       case 31:
                                          alt16 = 1;
                                       default:
                                          switch (alt16) {
                                             case 1:
                                                INDENT47 = (CommonToken)this.match(this.input, 31, FOLLOW_INDENT_in_ifstat545);
                                                stream_INDENT.add(INDENT47);
                                             default:
                                                LDELIM48 = (CommonToken)this.match(this.input, 23, FOLLOW_LDELIM_in_ifstat548);
                                                stream_LDELIM.add(LDELIM48);
                                                string_literal49 = (CommonToken)this.match(this.input, 5, FOLLOW_ELSE_in_ifstat550);
                                                stream_ELSE.add(string_literal49);
                                                RDELIM50 = (CommonToken)this.match(this.input, 24, FOLLOW_RDELIM_in_ifstat552);
                                                stream_RDELIM.add(RDELIM50);
                                                this.pushFollow(FOLLOW_template_in_ifstat556);
                                                t3 = this.template();
                                                --this.state._fsp;
                                                stream_template.add(t3.getTree());
                                          }
                                    }
                                 default:
                                    int alt18 = 2;
                                    switch (this.input.LA(1)) {
                                       case 31:
                                          alt18 = 1;
                                    }

                                    switch (alt18) {
                                       case 1:
                                          INDENT51 = (CommonToken)this.match(this.input, 31, FOLLOW_INDENT_in_ifstat564);
                                          stream_INDENT.add(INDENT51);
                                       default:
                                          endif = (CommonToken)this.match(this.input, 23, FOLLOW_LDELIM_in_ifstat570);
                                          stream_LDELIM.add(endif);
                                          string_literal52 = (CommonToken)this.match(this.input, 7, FOLLOW_ENDIF_in_ifstat572);
                                          stream_ENDIF.add(string_literal52);
                                          RDELIM53 = (CommonToken)this.match(this.input, 24, FOLLOW_RDELIM_in_ifstat576);
                                          stream_RDELIM.add(RDELIM53);
                                          int alt19 = 2;
                                          switch (this.input.LA(1)) {
                                             case 32:
                                                int LA19_1 = this.input.LA(2);
                                                if (((CommonToken)retval.start).getLine() != this.input.LT(1).getLine()) {
                                                   alt19 = 1;
                                                }
                                          }

                                          switch (alt19) {
                                             case 1:
                                                if (((CommonToken)retval.start).getLine() == this.input.LT(1).getLine()) {
                                                   throw new FailedPredicateException(this.input, "ifstat", "$ifstat.start.getLine()!=input.LT(1).getLine()");
                                                }

                                                NEWLINE54 = (CommonToken)this.match(this.input, 32, FOLLOW_NEWLINE_in_ifstat587);
                                                stream_NEWLINE.add(NEWLINE54);
                                             default:
                                                retval.tree = root_0;
                                                RewriteRuleTokenStream stream_i = new RewriteRuleTokenStream(this.adaptor, "token i", i);
                                                RewriteRuleSubtreeStream stream_t3 = new RewriteRuleSubtreeStream(this.adaptor, "rule t3", t3 != null ? t3.tree : null);
                                                new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                                                RewriteRuleSubtreeStream stream_t1 = new RewriteRuleSubtreeStream(this.adaptor, "rule t1", t1 != null ? t1.tree : null);
                                                RewriteRuleSubtreeStream stream_c1 = new RewriteRuleSubtreeStream(this.adaptor, "rule c1", c1 != null ? c1.tree : null);
                                                RewriteRuleSubtreeStream stream_t2 = new RewriteRuleSubtreeStream(this.adaptor, "token t2", list_t2);
                                                RewriteRuleSubtreeStream stream_c2 = new RewriteRuleSubtreeStream(this.adaptor, "token c2", list_c2);
                                                root_0 = (CommonTree)this.adaptor.nil();
                                                if (indent != null) {
                                                   CommonTree root_1 = (CommonTree)this.adaptor.nil();
                                                   root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(57, "INDENTED_EXPR"), root_1);
                                                   this.adaptor.addChild(root_1, stream_i.nextNode());
                                                   CommonTree root_2 = (CommonTree)this.adaptor.nil();
                                                   root_2 = (CommonTree)this.adaptor.becomeRoot(stream_IF.nextNode(), root_2);
                                                   this.adaptor.addChild(root_2, stream_c1.nextTree());
                                                   if (stream_t1.hasNext()) {
                                                      this.adaptor.addChild(root_2, stream_t1.nextTree());
                                                   }

                                                   stream_t1.reset();

                                                   while(stream_t2.hasNext() || stream_ELSEIF.hasNext() || stream_c2.hasNext()) {
                                                      CommonTree root_3 = (CommonTree)this.adaptor.nil();
                                                      root_3 = (CommonTree)this.adaptor.becomeRoot(stream_ELSEIF.nextNode(), root_3);
                                                      this.adaptor.addChild(root_3, stream_c2.nextTree());
                                                      this.adaptor.addChild(root_3, stream_t2.nextTree());
                                                      this.adaptor.addChild(root_2, root_3);
                                                   }

                                                   stream_t2.reset();
                                                   stream_ELSEIF.reset();
                                                   stream_c2.reset();
                                                   if (stream_t3.hasNext() || stream_ELSE.hasNext()) {
                                                      CommonTree root_3 = (CommonTree)this.adaptor.nil();
                                                      root_3 = (CommonTree)this.adaptor.becomeRoot(stream_ELSE.nextNode(), root_3);
                                                      if (stream_t3.hasNext()) {
                                                         this.adaptor.addChild(root_3, stream_t3.nextTree());
                                                      }

                                                      stream_t3.reset();
                                                      this.adaptor.addChild(root_2, root_3);
                                                   }

                                                   stream_t3.reset();
                                                   stream_ELSE.reset();
                                                   this.adaptor.addChild(root_1, root_2);
                                                   this.adaptor.addChild(root_0, root_1);
                                                } else {
                                                   CommonTree root_1 = (CommonTree)this.adaptor.nil();
                                                   root_1 = (CommonTree)this.adaptor.becomeRoot(stream_IF.nextNode(), root_1);
                                                   this.adaptor.addChild(root_1, stream_c1.nextTree());
                                                   if (stream_t1.hasNext()) {
                                                      this.adaptor.addChild(root_1, stream_t1.nextTree());
                                                   }

                                                   stream_t1.reset();

                                                   while(stream_c2.hasNext() || stream_t2.hasNext() || stream_ELSEIF.hasNext()) {
                                                      CommonTree root_2 = (CommonTree)this.adaptor.nil();
                                                      root_2 = (CommonTree)this.adaptor.becomeRoot(stream_ELSEIF.nextNode(), root_2);
                                                      this.adaptor.addChild(root_2, stream_c2.nextTree());
                                                      this.adaptor.addChild(root_2, stream_t2.nextTree());
                                                      this.adaptor.addChild(root_1, root_2);
                                                   }

                                                   stream_c2.reset();
                                                   stream_t2.reset();
                                                   stream_ELSEIF.reset();
                                                   if (stream_ELSE.hasNext() || stream_t3.hasNext()) {
                                                      CommonTree root_2 = (CommonTree)this.adaptor.nil();
                                                      root_2 = (CommonTree)this.adaptor.becomeRoot(stream_ELSE.nextNode(), root_2);
                                                      if (stream_t3.hasNext()) {
                                                         this.adaptor.addChild(root_2, stream_t3.nextTree());
                                                      }

                                                      stream_t3.reset();
                                                      this.adaptor.addChild(root_1, root_2);
                                                   }

                                                   stream_ELSE.reset();
                                                   stream_t3.reset();
                                                   this.adaptor.addChild(root_0, root_1);
                                                }

                                                retval.tree = root_0;
                                                retval.stop = this.input.LT(-1);
                                                retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                                                this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                                                return retval;
                                          }
                                    }
                              }
                        }
                     }
               }
         }
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final conditional_return conditional() throws RecognitionException {
      this.conditional_stack.push(new conditional_scope());
      conditional_return retval = new conditional_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken string_literal56 = null;
      andConditional_return andConditional55 = null;
      andConditional_return andConditional57 = null;
      CommonTree string_literal56_tree = null;

      try {
         root_0 = (CommonTree)this.adaptor.nil();
         this.pushFollow(FOLLOW_andConditional_in_conditional707);
         andConditional55 = this.andConditional();
         --this.state._fsp;
         this.adaptor.addChild(root_0, andConditional55.getTree());

         while(true) {
            int alt20 = 2;
            switch (this.input.LA(1)) {
               case 29:
                  alt20 = 1;
            }

            switch (alt20) {
               case 1:
                  string_literal56 = (CommonToken)this.match(this.input, 29, FOLLOW_OR_in_conditional711);
                  string_literal56_tree = (CommonTree)this.adaptor.create(string_literal56);
                  root_0 = (CommonTree)this.adaptor.becomeRoot(string_literal56_tree, root_0);
                  this.pushFollow(FOLLOW_andConditional_in_conditional714);
                  andConditional57 = this.andConditional();
                  --this.state._fsp;
                  this.adaptor.addChild(root_0, andConditional57.getTree());
                  break;
               default:
                  retval.stop = this.input.LT(-1);
                  retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                  this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                  return retval;
            }
         }
      } catch (RecognitionException re) {
         throw re;
      } finally {
         this.conditional_stack.pop();
      }
   }

   public final andConditional_return andConditional() throws RecognitionException {
      andConditional_return retval = new andConditional_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken string_literal59 = null;
      notConditional_return notConditional58 = null;
      notConditional_return notConditional60 = null;
      CommonTree string_literal59_tree = null;

      try {
         root_0 = (CommonTree)this.adaptor.nil();
         this.pushFollow(FOLLOW_notConditional_in_andConditional727);
         notConditional58 = this.notConditional();
         --this.state._fsp;
         this.adaptor.addChild(root_0, notConditional58.getTree());

         while(true) {
            int alt21 = 2;
            switch (this.input.LA(1)) {
               case 30:
                  alt21 = 1;
            }

            switch (alt21) {
               case 1:
                  string_literal59 = (CommonToken)this.match(this.input, 30, FOLLOW_AND_in_andConditional731);
                  string_literal59_tree = (CommonTree)this.adaptor.create(string_literal59);
                  root_0 = (CommonTree)this.adaptor.becomeRoot(string_literal59_tree, root_0);
                  this.pushFollow(FOLLOW_notConditional_in_andConditional734);
                  notConditional60 = this.notConditional();
                  --this.state._fsp;
                  this.adaptor.addChild(root_0, notConditional60.getTree());
                  break;
               default:
                  retval.stop = this.input.LT(-1);
                  retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                  this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                  return retval;
            }
         }
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final notConditional_return notConditional() throws RecognitionException {
      notConditional_return retval = new notConditional_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken char_literal61 = null;
      notConditional_return notConditional62 = null;
      memberExpr_return memberExpr63 = null;
      CommonTree char_literal61_tree = null;

      try {
         int alt22 = 2;
         int LA22_0 = this.input.LA(1);
         if (LA22_0 == 10) {
            alt22 = 1;
         } else if (LA22_0 != 8 && LA22_0 != 16 && LA22_0 != 20 && (LA22_0 < 25 || LA22_0 > 26) && LA22_0 != 33 && (LA22_0 < 35 || LA22_0 > 36)) {
            if (LA22_0 != 14 || this.conditional_stack.size() != 0 && this.conditional_stack.size() <= 0) {
               NoViableAltException nvae = new NoViableAltException("", 22, 0, this.input);
               throw nvae;
            }

            alt22 = 2;
         } else {
            alt22 = 2;
         }

         switch (alt22) {
            case 1:
               CommonTree var15 = (CommonTree)this.adaptor.nil();
               char_literal61 = (CommonToken)this.match(this.input, 10, FOLLOW_BANG_in_notConditional747);
               char_literal61_tree = (CommonTree)this.adaptor.create(char_literal61);
               root_0 = (CommonTree)this.adaptor.becomeRoot(char_literal61_tree, var15);
               this.pushFollow(FOLLOW_notConditional_in_notConditional750);
               notConditional62 = this.notConditional();
               --this.state._fsp;
               this.adaptor.addChild(root_0, notConditional62.getTree());
               break;
            case 2:
               root_0 = (CommonTree)this.adaptor.nil();
               this.pushFollow(FOLLOW_memberExpr_in_notConditional755);
               memberExpr63 = this.memberExpr();
               --this.state._fsp;
               this.adaptor.addChild(root_0, memberExpr63.getTree());
         }

         retval.stop = this.input.LT(-1);
         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         return retval;
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final notConditionalExpr_return notConditionalExpr() throws RecognitionException {
      notConditionalExpr_return retval = new notConditionalExpr_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken p = null;
      CommonToken prop = null;
      CommonToken ID64 = null;
      CommonToken char_literal65 = null;
      CommonToken char_literal67 = null;
      mapExpr_return mapExpr66 = null;
      CommonTree p_tree = null;
      CommonTree prop_tree = null;
      CommonTree ID64_tree = null;
      CommonTree char_literal65_tree = null;
      CommonTree char_literal67_tree = null;
      RewriteRuleTokenStream stream_RPAREN = new RewriteRuleTokenStream(this.adaptor, "token RPAREN");
      RewriteRuleTokenStream stream_DOT = new RewriteRuleTokenStream(this.adaptor, "token DOT");
      RewriteRuleTokenStream stream_ID = new RewriteRuleTokenStream(this.adaptor, "token ID");
      RewriteRuleTokenStream stream_LPAREN = new RewriteRuleTokenStream(this.adaptor, "token LPAREN");
      RewriteRuleSubtreeStream stream_mapExpr = new RewriteRuleSubtreeStream(this.adaptor, "rule mapExpr");

      try {
         ID64 = (CommonToken)this.match(this.input, 25, FOLLOW_ID_in_notConditionalExpr767);
         stream_ID.add(ID64);
         retval.tree = root_0;
         new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
         root_0 = (CommonTree)this.adaptor.nil();
         this.adaptor.addChild(root_0, stream_ID.nextNode());
         retval.tree = root_0;

         while(true) {
            int alt23 = 3;
            switch (this.input.LA(1)) {
               case 19:
                  switch (this.input.LA(2)) {
                     case 14:
                        alt23 = 2;
                        break;
                     case 25:
                        alt23 = 1;
                  }
            }

            switch (alt23) {
               case 1:
                  p = (CommonToken)this.match(this.input, 19, FOLLOW_DOT_in_notConditionalExpr778);
                  stream_DOT.add(p);
                  prop = (CommonToken)this.match(this.input, 25, FOLLOW_ID_in_notConditionalExpr782);
                  stream_ID.add(prop);
                  retval.tree = root_0;
                  RewriteRuleTokenStream stream_prop = new RewriteRuleTokenStream(this.adaptor, "token prop", prop);
                  RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                  root_0 = (CommonTree)this.adaptor.nil();
                  CommonTree root_1 = (CommonTree)this.adaptor.nil();
                  root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(40, p, "PROP"), root_1);
                  this.adaptor.addChild(root_1, stream_retval.nextTree());
                  this.adaptor.addChild(root_1, stream_prop.nextNode());
                  this.adaptor.addChild(root_0, root_1);
                  retval.tree = root_0;
                  break;
               case 2:
                  p = (CommonToken)this.match(this.input, 19, FOLLOW_DOT_in_notConditionalExpr808);
                  stream_DOT.add(p);
                  char_literal65 = (CommonToken)this.match(this.input, 14, FOLLOW_LPAREN_in_notConditionalExpr810);
                  stream_LPAREN.add(char_literal65);
                  this.pushFollow(FOLLOW_mapExpr_in_notConditionalExpr812);
                  mapExpr66 = this.mapExpr();
                  --this.state._fsp;
                  stream_mapExpr.add(mapExpr66.getTree());
                  char_literal67 = (CommonToken)this.match(this.input, 15, FOLLOW_RPAREN_in_notConditionalExpr814);
                  stream_RPAREN.add(char_literal67);
                  retval.tree = root_0;
                  RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                  root_0 = (CommonTree)this.adaptor.nil();
                  CommonTree root_1 = (CommonTree)this.adaptor.nil();
                  root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(41, p, "PROP_IND"), root_1);
                  this.adaptor.addChild(root_1, stream_retval.nextTree());
                  this.adaptor.addChild(root_1, stream_mapExpr.nextTree());
                  this.adaptor.addChild(root_0, root_1);
                  retval.tree = root_0;
                  break;
               default:
                  retval.stop = this.input.LT(-1);
                  retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                  this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                  return retval;
            }
         }
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final exprOptions_return exprOptions() throws RecognitionException {
      exprOptions_return retval = new exprOptions_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken char_literal69 = null;
      option_return option68 = null;
      option_return option70 = null;
      CommonTree char_literal69_tree = null;
      RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
      RewriteRuleSubtreeStream stream_option = new RewriteRuleSubtreeStream(this.adaptor, "rule option");

      try {
         this.pushFollow(FOLLOW_option_in_exprOptions844);
         option68 = this.option();
         --this.state._fsp;
         stream_option.add(option68.getTree());

         while(true) {
            int alt24 = 2;
            switch (this.input.LA(1)) {
               case 18:
                  alt24 = 1;
            }

            switch (alt24) {
               case 1:
                  char_literal69 = (CommonToken)this.match(this.input, 18, FOLLOW_COMMA_in_exprOptions848);
                  stream_COMMA.add(char_literal69);
                  this.pushFollow(FOLLOW_option_in_exprOptions850);
                  option70 = this.option();
                  --this.state._fsp;
                  stream_option.add(option70.getTree());
                  break;
               default:
                  retval.tree = root_0;
                  new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                  root_0 = (CommonTree)this.adaptor.nil();
                  CommonTree root_1 = (CommonTree)this.adaptor.nil();
                  root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(39, "OPTIONS"), root_1);

                  while(stream_option.hasNext()) {
                     this.adaptor.addChild(root_1, stream_option.nextTree());
                  }

                  stream_option.reset();
                  this.adaptor.addChild(root_0, root_1);
                  retval.tree = root_0;
                  retval.stop = this.input.LT(-1);
                  retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                  this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                  return retval;
            }
         }
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final option_return option() throws RecognitionException {
      option_return retval = new option_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken ID71 = null;
      CommonToken char_literal72 = null;
      exprNoComma_return exprNoComma73 = null;
      CommonTree ID71_tree = null;
      CommonTree char_literal72_tree = null;
      RewriteRuleTokenStream stream_EQUALS = new RewriteRuleTokenStream(this.adaptor, "token EQUALS");
      RewriteRuleTokenStream stream_ID = new RewriteRuleTokenStream(this.adaptor, "token ID");
      RewriteRuleSubtreeStream stream_exprNoComma = new RewriteRuleSubtreeStream(this.adaptor, "rule exprNoComma");
      String id = this.input.LT(1).getText();
      String defVal = (String)Compiler.defaultOptionValues.get(id);
      boolean validOption = Compiler.supportedOptions.get(id) != null;

      try {
         ID71 = (CommonToken)this.match(this.input, 25, FOLLOW_ID_in_option877);
         stream_ID.add(ID71);
         if (!validOption) {
            this.errMgr.compileTimeError(ErrorType.NO_SUCH_OPTION, this.templateToken, ID71, ID71 != null ? ID71.getText() : null);
         }

         int alt25 = 2;
         switch (this.input.LA(1)) {
            case 12:
               alt25 = 1;
               break;
            case 18:
            case 24:
               alt25 = 2;
               break;
            default:
               NoViableAltException nvae = new NoViableAltException("", 25, 0, this.input);
               throw nvae;
         }

         switch (alt25) {
            case 1:
               char_literal72 = (CommonToken)this.match(this.input, 12, FOLLOW_EQUALS_in_option887);
               stream_EQUALS.add(char_literal72);
               this.pushFollow(FOLLOW_exprNoComma_in_option889);
               exprNoComma73 = this.exprNoComma();
               --this.state._fsp;
               stream_exprNoComma.add(exprNoComma73.getTree());
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               if (validOption) {
                  CommonTree root_1 = (CommonTree)this.adaptor.nil();
                  root_1 = (CommonTree)this.adaptor.becomeRoot(stream_EQUALS.nextNode(), root_1);
                  this.adaptor.addChild(root_1, stream_ID.nextNode());
                  this.adaptor.addChild(root_1, stream_exprNoComma.nextTree());
                  this.adaptor.addChild(root_0, root_1);
               } else {
                  root_0 = null;
               }

               retval.tree = root_0;
               break;
            case 2:
               if (defVal == null) {
                  this.errMgr.compileTimeError(ErrorType.NO_DEFAULT_VALUE, this.templateToken, ID71);
               }

               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               if (validOption && defVal != null) {
                  CommonTree root_1 = (CommonTree)this.adaptor.nil();
                  root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(12, "="), root_1);
                  this.adaptor.addChild(root_1, stream_ID.nextNode());
                  this.adaptor.addChild(root_1, (CommonTree)this.adaptor.create(26, ID71, '"' + defVal + '"'));
                  this.adaptor.addChild(root_0, root_1);
               } else {
                  root_0 = null;
               }

               retval.tree = root_0;
         }

         retval.stop = this.input.LT(-1);
         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         return retval;
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final exprNoComma_return exprNoComma() throws RecognitionException {
      exprNoComma_return retval = new exprNoComma_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken char_literal75 = null;
      memberExpr_return memberExpr74 = null;
      mapTemplateRef_return mapTemplateRef76 = null;
      CommonTree char_literal75_tree = null;
      RewriteRuleTokenStream stream_COLON = new RewriteRuleTokenStream(this.adaptor, "token COLON");
      RewriteRuleSubtreeStream stream_memberExpr = new RewriteRuleSubtreeStream(this.adaptor, "rule memberExpr");
      RewriteRuleSubtreeStream stream_mapTemplateRef = new RewriteRuleSubtreeStream(this.adaptor, "rule mapTemplateRef");

      try {
         this.pushFollow(FOLLOW_memberExpr_in_exprNoComma996);
         memberExpr74 = this.memberExpr();
         --this.state._fsp;
         stream_memberExpr.add(memberExpr74.getTree());
         int alt26 = 2;
         switch (this.input.LA(1)) {
            case 13:
               alt26 = 1;
               break;
            case 14:
            case 16:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            default:
               NoViableAltException nvae = new NoViableAltException("", 26, 0, this.input);
               throw nvae;
            case 15:
            case 17:
            case 18:
            case 24:
               alt26 = 2;
         }

         switch (alt26) {
            case 1:
               char_literal75 = (CommonToken)this.match(this.input, 13, FOLLOW_COLON_in_exprNoComma1002);
               stream_COLON.add(char_literal75);
               this.pushFollow(FOLLOW_mapTemplateRef_in_exprNoComma1004);
               mapTemplateRef76 = this.mapTemplateRef();
               --this.state._fsp;
               stream_mapTemplateRef.add(mapTemplateRef76.getTree());
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(50, "MAP"), root_1);
               this.adaptor.addChild(root_1, stream_memberExpr.nextTree());
               this.adaptor.addChild(root_1, stream_mapTemplateRef.nextTree());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
               break;
            case 2:
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               this.adaptor.addChild(root_0, stream_memberExpr.nextTree());
               retval.tree = root_0;
         }

         retval.stop = this.input.LT(-1);
         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         return retval;
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final expr_return expr() throws RecognitionException {
      expr_return retval = new expr_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      mapExpr_return mapExpr77 = null;

      try {
         root_0 = (CommonTree)this.adaptor.nil();
         this.pushFollow(FOLLOW_mapExpr_in_expr1049);
         mapExpr77 = this.mapExpr();
         --this.state._fsp;
         this.adaptor.addChild(root_0, mapExpr77.getTree());
         retval.stop = this.input.LT(-1);
         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         return retval;
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final mapExpr_return mapExpr() throws RecognitionException {
      mapExpr_return retval = new mapExpr_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken c = null;
      CommonToken col = null;
      CommonToken char_literal81 = null;
      List list_x = null;
      memberExpr_return memberExpr78 = null;
      memberExpr_return memberExpr79 = null;
      mapTemplateRef_return mapTemplateRef80 = null;
      RuleReturnScope x = null;
      CommonTree c_tree = null;
      CommonTree col_tree = null;
      CommonTree char_literal81_tree = null;
      RewriteRuleTokenStream stream_COLON = new RewriteRuleTokenStream(this.adaptor, "token COLON");
      RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
      RewriteRuleSubtreeStream stream_memberExpr = new RewriteRuleSubtreeStream(this.adaptor, "rule memberExpr");
      RewriteRuleSubtreeStream stream_mapTemplateRef = new RewriteRuleSubtreeStream(this.adaptor, "rule mapTemplateRef");

      try {
         this.pushFollow(FOLLOW_memberExpr_in_mapExpr1061);
         memberExpr78 = this.memberExpr();
         --this.state._fsp;
         stream_memberExpr.add(memberExpr78.getTree());
         int alt28 = 2;
         switch (this.input.LA(1)) {
            case 9:
            case 13:
            case 15:
            case 24:
               alt28 = 2;
               break;
            case 18:
               alt28 = 1;
               break;
            default:
               NoViableAltException nvae = new NoViableAltException("", 28, 0, this.input);
               throw nvae;
         }

         label255:
         switch (alt28) {
            case 1:
               int cnt27 = 0;

               while(true) {
                  int alt27 = 2;
                  switch (this.input.LA(1)) {
                     case 18:
                        alt27 = 1;
                  }

                  switch (alt27) {
                     case 1:
                        c = (CommonToken)this.match(this.input, 18, FOLLOW_COMMA_in_mapExpr1070);
                        stream_COMMA.add(c);
                        this.pushFollow(FOLLOW_memberExpr_in_mapExpr1072);
                        memberExpr79 = this.memberExpr();
                        --this.state._fsp;
                        stream_memberExpr.add(memberExpr79.getTree());
                        ++cnt27;
                        break;
                     default:
                        if (cnt27 < 1) {
                           EarlyExitException eee = new EarlyExitException(27, this.input);
                           throw eee;
                        }

                        col = (CommonToken)this.match(this.input, 13, FOLLOW_COLON_in_mapExpr1078);
                        stream_COLON.add(col);
                        this.pushFollow(FOLLOW_mapTemplateRef_in_mapExpr1080);
                        mapTemplateRef80 = this.mapTemplateRef();
                        --this.state._fsp;
                        stream_mapTemplateRef.add(mapTemplateRef80.getTree());
                        retval.tree = root_0;
                        new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                        root_0 = (CommonTree)this.adaptor.nil();
                        CommonTree root_1 = (CommonTree)this.adaptor.nil();
                        root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(51, col), root_1);
                        CommonTree root_2 = (CommonTree)this.adaptor.nil();
                        root_2 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(54, "ELEMENTS"), root_2);
                        if (!stream_memberExpr.hasNext()) {
                           throw new RewriteEarlyExitException();
                        }

                        while(stream_memberExpr.hasNext()) {
                           this.adaptor.addChild(root_2, stream_memberExpr.nextTree());
                        }

                        stream_memberExpr.reset();
                        this.adaptor.addChild(root_1, root_2);
                        this.adaptor.addChild(root_1, stream_mapTemplateRef.nextTree());
                        this.adaptor.addChild(root_0, root_1);
                        retval.tree = root_0;
                        break label255;
                  }
               }
            case 2:
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               this.adaptor.addChild(root_0, stream_memberExpr.nextTree());
               retval.tree = root_0;
         }

         label240:
         while(true) {
            int alt30 = 2;
            switch (this.input.LA(1)) {
               case 13:
                  alt30 = 1;
               default:
                  switch (alt30) {
                     case 1:
                        if (list_x != null) {
                           list_x.clear();
                        }

                        col = (CommonToken)this.match(this.input, 13, FOLLOW_COLON_in_mapExpr1143);
                        stream_COLON.add(col);
                        this.pushFollow(FOLLOW_mapTemplateRef_in_mapExpr1147);
                        RuleReturnScope var34 = this.mapTemplateRef();
                        --this.state._fsp;
                        stream_mapTemplateRef.add(((RuleReturnScope)var34).getTree());
                        if (list_x == null) {
                           list_x = new ArrayList();
                        }

                        list_x.add(((RuleReturnScope)var34).getTree());

                        while(true) {
                           int alt29 = 2;
                           int LA29_0 = this.input.LA(1);
                           if (LA29_0 == 18 && c == null) {
                              alt29 = 1;
                           }

                           switch (alt29) {
                              case 1:
                                 if (c != null) {
                                    throw new FailedPredicateException(this.input, "mapExpr", "$c==null");
                                 }

                                 char_literal81 = (CommonToken)this.match(this.input, 18, FOLLOW_COMMA_in_mapExpr1153);
                                 stream_COMMA.add(char_literal81);
                                 this.pushFollow(FOLLOW_mapTemplateRef_in_mapExpr1157);
                                 var34 = this.mapTemplateRef();
                                 --this.state._fsp;
                                 stream_mapTemplateRef.add(((RuleReturnScope)var34).getTree());
                                 if (list_x == null) {
                                    list_x = new ArrayList();
                                 }

                                 list_x.add(((RuleReturnScope)var34).getTree());
                                 break;
                              default:
                                 retval.tree = root_0;
                                 RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                                 RewriteRuleSubtreeStream stream_x = new RewriteRuleSubtreeStream(this.adaptor, "token x", list_x);
                                 root_0 = (CommonTree)this.adaptor.nil();
                                 CommonTree root_1 = (CommonTree)this.adaptor.nil();
                                 root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(50, col), root_1);
                                 this.adaptor.addChild(root_1, stream_retval.nextTree());
                                 if (!stream_x.hasNext()) {
                                    throw new RewriteEarlyExitException();
                                 }

                                 while(stream_x.hasNext()) {
                                    this.adaptor.addChild(root_1, stream_x.nextTree());
                                 }

                                 stream_x.reset();
                                 this.adaptor.addChild(root_0, root_1);
                                 retval.tree = root_0;
                                 continue label240;
                           }
                        }
                     default:
                        retval.stop = this.input.LT(-1);
                        retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                        this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                        return retval;
                  }
            }
         }
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final mapTemplateRef_return mapTemplateRef() throws RecognitionException {
      mapTemplateRef_return retval = new mapTemplateRef_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken lp = null;
      CommonToken rp = null;
      CommonToken ID82 = null;
      CommonToken char_literal83 = null;
      CommonToken char_literal85 = null;
      CommonToken char_literal88 = null;
      CommonToken char_literal90 = null;
      args_return args84 = null;
      subtemplate_return subtemplate86 = null;
      mapExpr_return mapExpr87 = null;
      argExprList_return argExprList89 = null;
      CommonTree lp_tree = null;
      CommonTree rp_tree = null;
      CommonTree ID82_tree = null;
      CommonTree char_literal83_tree = null;
      CommonTree char_literal85_tree = null;
      CommonTree char_literal88_tree = null;
      CommonTree char_literal90_tree = null;
      RewriteRuleTokenStream stream_RPAREN = new RewriteRuleTokenStream(this.adaptor, "token RPAREN");
      RewriteRuleTokenStream stream_ID = new RewriteRuleTokenStream(this.adaptor, "token ID");
      RewriteRuleTokenStream stream_LPAREN = new RewriteRuleTokenStream(this.adaptor, "token LPAREN");
      RewriteRuleSubtreeStream stream_argExprList = new RewriteRuleSubtreeStream(this.adaptor, "rule argExprList");
      RewriteRuleSubtreeStream stream_args = new RewriteRuleSubtreeStream(this.adaptor, "rule args");
      RewriteRuleSubtreeStream stream_mapExpr = new RewriteRuleSubtreeStream(this.adaptor, "rule mapExpr");

      try {
         int alt32 = 3;
         switch (this.input.LA(1)) {
            case 14:
               alt32 = 3;
               break;
            case 20:
               alt32 = 2;
               break;
            case 25:
               alt32 = 1;
               break;
            default:
               NoViableAltException nvae = new NoViableAltException("", 32, 0, this.input);
               throw nvae;
         }

         switch (alt32) {
            case 1:
               ID82 = (CommonToken)this.match(this.input, 25, FOLLOW_ID_in_mapTemplateRef1204);
               stream_ID.add(ID82);
               char_literal83 = (CommonToken)this.match(this.input, 14, FOLLOW_LPAREN_in_mapTemplateRef1206);
               stream_LPAREN.add(char_literal83);
               this.pushFollow(FOLLOW_args_in_mapTemplateRef1208);
               args84 = this.args();
               --this.state._fsp;
               stream_args.add(args84.getTree());
               char_literal85 = (CommonToken)this.match(this.input, 15, FOLLOW_RPAREN_in_mapTemplateRef1210);
               stream_RPAREN.add(char_literal85);
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(42, "INCLUDE"), root_1);
               this.adaptor.addChild(root_1, stream_ID.nextNode());
               if (stream_args.hasNext()) {
                  this.adaptor.addChild(root_1, stream_args.nextTree());
               }

               stream_args.reset();
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
               break;
            case 2:
               root_0 = (CommonTree)this.adaptor.nil();
               this.pushFollow(FOLLOW_subtemplate_in_mapTemplateRef1232);
               subtemplate86 = this.subtemplate();
               --this.state._fsp;
               this.adaptor.addChild(root_0, subtemplate86.getTree());
               break;
            case 3:
               lp = (CommonToken)this.match(this.input, 14, FOLLOW_LPAREN_in_mapTemplateRef1239);
               stream_LPAREN.add(lp);
               this.pushFollow(FOLLOW_mapExpr_in_mapTemplateRef1241);
               mapExpr87 = this.mapExpr();
               --this.state._fsp;
               stream_mapExpr.add(mapExpr87.getTree());
               rp = (CommonToken)this.match(this.input, 15, FOLLOW_RPAREN_in_mapTemplateRef1245);
               stream_RPAREN.add(rp);
               char_literal88 = (CommonToken)this.match(this.input, 14, FOLLOW_LPAREN_in_mapTemplateRef1247);
               stream_LPAREN.add(char_literal88);
               int alt31 = 2;
               int LA31_0 = this.input.LA(1);
               if (LA31_0 != 8 && LA31_0 != 16 && LA31_0 != 20 && (LA31_0 < 25 || LA31_0 > 26) && LA31_0 != 33 && (LA31_0 < 35 || LA31_0 > 36)) {
                  if (LA31_0 == 14 && (this.conditional_stack.size() == 0 || this.conditional_stack.size() > 0)) {
                     alt31 = 1;
                  }
               } else {
                  alt31 = 1;
               }

               switch (alt31) {
                  case 1:
                     this.pushFollow(FOLLOW_argExprList_in_mapTemplateRef1249);
                     argExprList89 = this.argExprList();
                     --this.state._fsp;
                     stream_argExprList.add(argExprList89.getTree());
                  default:
                     char_literal90 = (CommonToken)this.match(this.input, 15, FOLLOW_RPAREN_in_mapTemplateRef1252);
                     stream_RPAREN.add(char_literal90);
                     retval.tree = root_0;
                     new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                     root_0 = (CommonTree)this.adaptor.nil();
                     CommonTree root_1 = (CommonTree)this.adaptor.nil();
                     root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(43, "INCLUDE_IND"), root_1);
                     this.adaptor.addChild(root_1, stream_mapExpr.nextTree());
                     if (stream_argExprList.hasNext()) {
                        this.adaptor.addChild(root_1, stream_argExprList.nextTree());
                     }

                     stream_argExprList.reset();
                     this.adaptor.addChild(root_0, root_1);
                     retval.tree = root_0;
               }
         }

         retval.stop = this.input.LT(-1);
         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         return retval;
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final memberExpr_return memberExpr() throws RecognitionException {
      memberExpr_return retval = new memberExpr_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken p = null;
      CommonToken ID92 = null;
      CommonToken char_literal93 = null;
      CommonToken char_literal95 = null;
      includeExpr_return includeExpr91 = null;
      mapExpr_return mapExpr94 = null;
      CommonTree p_tree = null;
      CommonTree ID92_tree = null;
      CommonTree char_literal93_tree = null;
      CommonTree char_literal95_tree = null;
      RewriteRuleTokenStream stream_RPAREN = new RewriteRuleTokenStream(this.adaptor, "token RPAREN");
      RewriteRuleTokenStream stream_DOT = new RewriteRuleTokenStream(this.adaptor, "token DOT");
      RewriteRuleTokenStream stream_ID = new RewriteRuleTokenStream(this.adaptor, "token ID");
      RewriteRuleTokenStream stream_LPAREN = new RewriteRuleTokenStream(this.adaptor, "token LPAREN");
      RewriteRuleSubtreeStream stream_includeExpr = new RewriteRuleSubtreeStream(this.adaptor, "rule includeExpr");
      RewriteRuleSubtreeStream stream_mapExpr = new RewriteRuleSubtreeStream(this.adaptor, "rule mapExpr");

      try {
         this.pushFollow(FOLLOW_includeExpr_in_memberExpr1275);
         includeExpr91 = this.includeExpr();
         --this.state._fsp;
         stream_includeExpr.add(includeExpr91.getTree());
         retval.tree = root_0;
         new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
         root_0 = (CommonTree)this.adaptor.nil();
         this.adaptor.addChild(root_0, stream_includeExpr.nextTree());
         retval.tree = root_0;

         while(true) {
            int alt33 = 3;
            switch (this.input.LA(1)) {
               case 19:
                  switch (this.input.LA(2)) {
                     case 14:
                        alt33 = 2;
                        break;
                     case 25:
                        alt33 = 1;
                  }
            }

            switch (alt33) {
               case 1:
                  p = (CommonToken)this.match(this.input, 19, FOLLOW_DOT_in_memberExpr1286);
                  stream_DOT.add(p);
                  ID92 = (CommonToken)this.match(this.input, 25, FOLLOW_ID_in_memberExpr1288);
                  stream_ID.add(ID92);
                  retval.tree = root_0;
                  RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                  root_0 = (CommonTree)this.adaptor.nil();
                  CommonTree root_1 = (CommonTree)this.adaptor.nil();
                  root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(40, p, "PROP"), root_1);
                  this.adaptor.addChild(root_1, stream_retval.nextTree());
                  this.adaptor.addChild(root_1, stream_ID.nextNode());
                  this.adaptor.addChild(root_0, root_1);
                  retval.tree = root_0;
                  break;
               case 2:
                  p = (CommonToken)this.match(this.input, 19, FOLLOW_DOT_in_memberExpr1314);
                  stream_DOT.add(p);
                  char_literal93 = (CommonToken)this.match(this.input, 14, FOLLOW_LPAREN_in_memberExpr1316);
                  stream_LPAREN.add(char_literal93);
                  this.pushFollow(FOLLOW_mapExpr_in_memberExpr1318);
                  mapExpr94 = this.mapExpr();
                  --this.state._fsp;
                  stream_mapExpr.add(mapExpr94.getTree());
                  char_literal95 = (CommonToken)this.match(this.input, 15, FOLLOW_RPAREN_in_memberExpr1320);
                  stream_RPAREN.add(char_literal95);
                  retval.tree = root_0;
                  RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                  root_0 = (CommonTree)this.adaptor.nil();
                  CommonTree root_1 = (CommonTree)this.adaptor.nil();
                  root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(41, p, "PROP_IND"), root_1);
                  this.adaptor.addChild(root_1, stream_retval.nextTree());
                  this.adaptor.addChild(root_1, stream_mapExpr.nextTree());
                  this.adaptor.addChild(root_0, root_1);
                  retval.tree = root_0;
                  break;
               default:
                  retval.stop = this.input.LT(-1);
                  retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                  this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                  return retval;
            }
         }
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final includeExpr_return includeExpr() throws RecognitionException {
      includeExpr_return retval = new includeExpr_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken rp = null;
      CommonToken ID96 = null;
      CommonToken char_literal97 = null;
      CommonToken char_literal99 = null;
      CommonToken string_literal100 = null;
      CommonToken char_literal101 = null;
      CommonToken ID102 = null;
      CommonToken char_literal103 = null;
      CommonToken char_literal105 = null;
      CommonToken ID106 = null;
      CommonToken char_literal107 = null;
      CommonToken char_literal109 = null;
      CommonToken char_literal110 = null;
      CommonToken string_literal111 = null;
      CommonToken char_literal112 = null;
      CommonToken ID113 = null;
      CommonToken char_literal114 = null;
      CommonToken char_literal115 = null;
      CommonToken ID116 = null;
      CommonToken char_literal117 = null;
      expr_return expr98 = null;
      args_return args104 = null;
      args_return args108 = null;
      primary_return primary118 = null;
      CommonTree rp_tree = null;
      CommonTree ID96_tree = null;
      CommonTree char_literal97_tree = null;
      CommonTree char_literal99_tree = null;
      CommonTree string_literal100_tree = null;
      CommonTree char_literal101_tree = null;
      CommonTree ID102_tree = null;
      CommonTree char_literal103_tree = null;
      CommonTree char_literal105_tree = null;
      CommonTree ID106_tree = null;
      CommonTree char_literal107_tree = null;
      CommonTree char_literal109_tree = null;
      CommonTree char_literal110_tree = null;
      CommonTree string_literal111_tree = null;
      CommonTree char_literal112_tree = null;
      CommonTree ID113_tree = null;
      CommonTree char_literal114_tree = null;
      CommonTree char_literal115_tree = null;
      CommonTree ID116_tree = null;
      CommonTree char_literal117_tree = null;
      RewriteRuleTokenStream stream_AT = new RewriteRuleTokenStream(this.adaptor, "token AT");
      RewriteRuleTokenStream stream_RPAREN = new RewriteRuleTokenStream(this.adaptor, "token RPAREN");
      RewriteRuleTokenStream stream_SUPER = new RewriteRuleTokenStream(this.adaptor, "token SUPER");
      RewriteRuleTokenStream stream_DOT = new RewriteRuleTokenStream(this.adaptor, "token DOT");
      RewriteRuleTokenStream stream_ID = new RewriteRuleTokenStream(this.adaptor, "token ID");
      RewriteRuleTokenStream stream_LPAREN = new RewriteRuleTokenStream(this.adaptor, "token LPAREN");
      RewriteRuleSubtreeStream stream_args = new RewriteRuleSubtreeStream(this.adaptor, "rule args");
      RewriteRuleSubtreeStream stream_expr = new RewriteRuleSubtreeStream(this.adaptor, "rule expr");

      try {
         int alt35 = 6;
         alt35 = this.dfa35.predict(this.input);
         label208:
         switch (alt35) {
            case 1:
               if (!Compiler.funcs.containsKey(this.input.LT(1).getText())) {
                  throw new FailedPredicateException(this.input, "includeExpr", "Compiler.funcs.containsKey(input.LT(1).getText())");
               }

               ID96 = (CommonToken)this.match(this.input, 25, FOLLOW_ID_in_includeExpr1364);
               stream_ID.add(ID96);
               char_literal97 = (CommonToken)this.match(this.input, 14, FOLLOW_LPAREN_in_includeExpr1366);
               stream_LPAREN.add(char_literal97);
               int alt34 = 2;
               int LA34_0 = this.input.LA(1);
               if (LA34_0 != 8 && LA34_0 != 16 && LA34_0 != 20 && (LA34_0 < 25 || LA34_0 > 26) && LA34_0 != 33 && (LA34_0 < 35 || LA34_0 > 36)) {
                  if (LA34_0 == 14 && (this.conditional_stack.size() == 0 || this.conditional_stack.size() > 0)) {
                     alt34 = 1;
                  }
               } else {
                  alt34 = 1;
               }

               switch (alt34) {
                  case 1:
                     this.pushFollow(FOLLOW_expr_in_includeExpr1368);
                     expr98 = this.expr();
                     --this.state._fsp;
                     stream_expr.add(expr98.getTree());
                  default:
                     char_literal99 = (CommonToken)this.match(this.input, 15, FOLLOW_RPAREN_in_includeExpr1371);
                     stream_RPAREN.add(char_literal99);
                     retval.tree = root_0;
                     new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                     root_0 = (CommonTree)this.adaptor.nil();
                     CommonTree root_1 = (CommonTree)this.adaptor.nil();
                     root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(44, "EXEC_FUNC"), root_1);
                     this.adaptor.addChild(root_1, stream_ID.nextNode());
                     if (stream_expr.hasNext()) {
                        this.adaptor.addChild(root_1, stream_expr.nextTree());
                     }

                     stream_expr.reset();
                     this.adaptor.addChild(root_0, root_1);
                     retval.tree = root_0;
                     break label208;
               }
            case 2:
               string_literal100 = (CommonToken)this.match(this.input, 8, FOLLOW_SUPER_in_includeExpr1392);
               stream_SUPER.add(string_literal100);
               char_literal101 = (CommonToken)this.match(this.input, 19, FOLLOW_DOT_in_includeExpr1394);
               stream_DOT.add(char_literal101);
               ID102 = (CommonToken)this.match(this.input, 25, FOLLOW_ID_in_includeExpr1396);
               stream_ID.add(ID102);
               char_literal103 = (CommonToken)this.match(this.input, 14, FOLLOW_LPAREN_in_includeExpr1398);
               stream_LPAREN.add(char_literal103);
               this.pushFollow(FOLLOW_args_in_includeExpr1400);
               args104 = this.args();
               --this.state._fsp;
               stream_args.add(args104.getTree());
               char_literal105 = (CommonToken)this.match(this.input, 15, FOLLOW_RPAREN_in_includeExpr1402);
               stream_RPAREN.add(char_literal105);
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(45, "INCLUDE_SUPER"), root_1);
               this.adaptor.addChild(root_1, stream_ID.nextNode());
               if (stream_args.hasNext()) {
                  this.adaptor.addChild(root_1, stream_args.nextTree());
               }

               stream_args.reset();
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
               break;
            case 3:
               ID106 = (CommonToken)this.match(this.input, 25, FOLLOW_ID_in_includeExpr1421);
               stream_ID.add(ID106);
               char_literal107 = (CommonToken)this.match(this.input, 14, FOLLOW_LPAREN_in_includeExpr1423);
               stream_LPAREN.add(char_literal107);
               this.pushFollow(FOLLOW_args_in_includeExpr1425);
               args108 = this.args();
               --this.state._fsp;
               stream_args.add(args108.getTree());
               char_literal109 = (CommonToken)this.match(this.input, 15, FOLLOW_RPAREN_in_includeExpr1427);
               stream_RPAREN.add(char_literal109);
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(42, "INCLUDE"), root_1);
               this.adaptor.addChild(root_1, stream_ID.nextNode());
               if (stream_args.hasNext()) {
                  this.adaptor.addChild(root_1, stream_args.nextTree());
               }

               stream_args.reset();
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
               break;
            case 4:
               char_literal110 = (CommonToken)this.match(this.input, 33, FOLLOW_AT_in_includeExpr1449);
               stream_AT.add(char_literal110);
               string_literal111 = (CommonToken)this.match(this.input, 8, FOLLOW_SUPER_in_includeExpr1451);
               stream_SUPER.add(string_literal111);
               char_literal112 = (CommonToken)this.match(this.input, 19, FOLLOW_DOT_in_includeExpr1453);
               stream_DOT.add(char_literal112);
               ID113 = (CommonToken)this.match(this.input, 25, FOLLOW_ID_in_includeExpr1455);
               stream_ID.add(ID113);
               char_literal114 = (CommonToken)this.match(this.input, 14, FOLLOW_LPAREN_in_includeExpr1457);
               stream_LPAREN.add(char_literal114);
               rp = (CommonToken)this.match(this.input, 15, FOLLOW_RPAREN_in_includeExpr1461);
               stream_RPAREN.add(rp);
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(46, "INCLUDE_SUPER_REGION"), root_1);
               this.adaptor.addChild(root_1, stream_ID.nextNode());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
               break;
            case 5:
               char_literal115 = (CommonToken)this.match(this.input, 33, FOLLOW_AT_in_includeExpr1476);
               stream_AT.add(char_literal115);
               ID116 = (CommonToken)this.match(this.input, 25, FOLLOW_ID_in_includeExpr1478);
               stream_ID.add(ID116);
               char_literal117 = (CommonToken)this.match(this.input, 14, FOLLOW_LPAREN_in_includeExpr1480);
               stream_LPAREN.add(char_literal117);
               rp = (CommonToken)this.match(this.input, 15, FOLLOW_RPAREN_in_includeExpr1484);
               stream_RPAREN.add(rp);
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               CommonTree root_1 = (CommonTree)this.adaptor.nil();
               root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(47, "INCLUDE_REGION"), root_1);
               this.adaptor.addChild(root_1, stream_ID.nextNode());
               this.adaptor.addChild(root_0, root_1);
               retval.tree = root_0;
               break;
            case 6:
               root_0 = (CommonTree)this.adaptor.nil();
               this.pushFollow(FOLLOW_primary_in_includeExpr1502);
               primary118 = this.primary();
               --this.state._fsp;
               this.adaptor.addChild(root_0, primary118.getTree());
         }

         retval.stop = this.input.LT(-1);
         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         return retval;
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final primary_return primary() throws RecognitionException {
      primary_return retval = new primary_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken lp = null;
      CommonToken ID119 = null;
      CommonToken STRING120 = null;
      CommonToken TRUE121 = null;
      CommonToken FALSE122 = null;
      CommonToken char_literal125 = null;
      CommonToken char_literal127 = null;
      CommonToken char_literal129 = null;
      CommonToken char_literal130 = null;
      CommonToken char_literal132 = null;
      subtemplate_return subtemplate123 = null;
      list_return list124 = null;
      conditional_return conditional126 = null;
      expr_return expr128 = null;
      argExprList_return argExprList131 = null;
      CommonTree lp_tree = null;
      CommonTree ID119_tree = null;
      CommonTree STRING120_tree = null;
      CommonTree TRUE121_tree = null;
      CommonTree FALSE122_tree = null;
      CommonTree char_literal125_tree = null;
      CommonTree char_literal127_tree = null;
      CommonTree char_literal129_tree = null;
      CommonTree char_literal130_tree = null;
      CommonTree char_literal132_tree = null;
      RewriteRuleTokenStream stream_RPAREN = new RewriteRuleTokenStream(this.adaptor, "token RPAREN");
      RewriteRuleTokenStream stream_LPAREN = new RewriteRuleTokenStream(this.adaptor, "token LPAREN");
      RewriteRuleSubtreeStream stream_argExprList = new RewriteRuleSubtreeStream(this.adaptor, "rule argExprList");
      RewriteRuleSubtreeStream stream_expr = new RewriteRuleSubtreeStream(this.adaptor, "rule expr");

      try {
         int alt38 = 8;
         alt38 = this.dfa38.predict(this.input);
         switch (alt38) {
            case 1:
               root_0 = (CommonTree)this.adaptor.nil();
               ID119 = (CommonToken)this.match(this.input, 25, FOLLOW_ID_in_primary1513);
               ID119_tree = (CommonTree)this.adaptor.create(ID119);
               this.adaptor.addChild(root_0, ID119_tree);
               break;
            case 2:
               root_0 = (CommonTree)this.adaptor.nil();
               STRING120 = (CommonToken)this.match(this.input, 26, FOLLOW_STRING_in_primary1518);
               STRING120_tree = (CommonTree)this.adaptor.create(STRING120);
               this.adaptor.addChild(root_0, STRING120_tree);
               break;
            case 3:
               root_0 = (CommonTree)this.adaptor.nil();
               TRUE121 = (CommonToken)this.match(this.input, 35, FOLLOW_TRUE_in_primary1523);
               TRUE121_tree = (CommonTree)this.adaptor.create(TRUE121);
               this.adaptor.addChild(root_0, TRUE121_tree);
               break;
            case 4:
               root_0 = (CommonTree)this.adaptor.nil();
               FALSE122 = (CommonToken)this.match(this.input, 36, FOLLOW_FALSE_in_primary1528);
               FALSE122_tree = (CommonTree)this.adaptor.create(FALSE122);
               this.adaptor.addChild(root_0, FALSE122_tree);
               break;
            case 5:
               root_0 = (CommonTree)this.adaptor.nil();
               this.pushFollow(FOLLOW_subtemplate_in_primary1533);
               subtemplate123 = this.subtemplate();
               --this.state._fsp;
               this.adaptor.addChild(root_0, subtemplate123.getTree());
               break;
            case 6:
               root_0 = (CommonTree)this.adaptor.nil();
               this.pushFollow(FOLLOW_list_in_primary1538);
               list124 = this.list();
               --this.state._fsp;
               this.adaptor.addChild(root_0, list124.getTree());
               break;
            case 7:
               root_0 = (CommonTree)this.adaptor.nil();
               if (this.conditional_stack.size() <= 0) {
                  throw new FailedPredicateException(this.input, "primary", "$conditional.size()>0");
               }

               char_literal125 = (CommonToken)this.match(this.input, 14, FOLLOW_LPAREN_in_primary1547);
               this.pushFollow(FOLLOW_conditional_in_primary1550);
               conditional126 = this.conditional();
               --this.state._fsp;
               this.adaptor.addChild(root_0, conditional126.getTree());
               char_literal127 = (CommonToken)this.match(this.input, 15, FOLLOW_RPAREN_in_primary1552);
               break;
            case 8:
               if (this.conditional_stack.size() != 0) {
                  throw new FailedPredicateException(this.input, "primary", "$conditional.size()==0");
               }

               lp = (CommonToken)this.match(this.input, 14, FOLLOW_LPAREN_in_primary1563);
               stream_LPAREN.add(lp);
               this.pushFollow(FOLLOW_expr_in_primary1565);
               expr128 = this.expr();
               --this.state._fsp;
               stream_expr.add(expr128.getTree());
               char_literal129 = (CommonToken)this.match(this.input, 15, FOLLOW_RPAREN_in_primary1567);
               stream_RPAREN.add(char_literal129);
               int alt37 = 2;
               switch (this.input.LA(1)) {
                  case 9:
                  case 13:
                  case 15:
                  case 17:
                  case 18:
                  case 19:
                  case 24:
                  case 29:
                  case 30:
                     alt37 = 2;
                     break;
                  case 10:
                  case 11:
                  case 12:
                  case 16:
                  case 20:
                  case 21:
                  case 22:
                  case 23:
                  case 25:
                  case 26:
                  case 27:
                  case 28:
                  default:
                     NoViableAltException nvae = new NoViableAltException("", 37, 0, this.input);
                     throw nvae;
                  case 14:
                     alt37 = 1;
               }

               label188:
               switch (alt37) {
                  case 1:
                     char_literal130 = (CommonToken)this.match(this.input, 14, FOLLOW_LPAREN_in_primary1573);
                     stream_LPAREN.add(char_literal130);
                     int alt36 = 2;
                     int LA36_0 = this.input.LA(1);
                     if (LA36_0 != 8 && LA36_0 != 16 && LA36_0 != 20 && (LA36_0 < 25 || LA36_0 > 26) && LA36_0 != 33 && (LA36_0 < 35 || LA36_0 > 36)) {
                        if (LA36_0 == 14 && (this.conditional_stack.size() == 0 || this.conditional_stack.size() > 0)) {
                           alt36 = 1;
                        }
                     } else {
                        alt36 = 1;
                     }

                     switch (alt36) {
                        case 1:
                           this.pushFollow(FOLLOW_argExprList_in_primary1575);
                           argExprList131 = this.argExprList();
                           --this.state._fsp;
                           stream_argExprList.add(argExprList131.getTree());
                        default:
                           char_literal132 = (CommonToken)this.match(this.input, 15, FOLLOW_RPAREN_in_primary1578);
                           stream_RPAREN.add(char_literal132);
                           retval.tree = root_0;
                           new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                           root_0 = (CommonTree)this.adaptor.nil();
                           CommonTree root_1 = (CommonTree)this.adaptor.nil();
                           root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(43, lp), root_1);
                           this.adaptor.addChild(root_1, stream_expr.nextTree());
                           if (stream_argExprList.hasNext()) {
                              this.adaptor.addChild(root_1, stream_argExprList.nextTree());
                           }

                           stream_argExprList.reset();
                           this.adaptor.addChild(root_0, root_1);
                           retval.tree = root_0;
                           break label188;
                     }
                  case 2:
                     retval.tree = root_0;
                     new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                     root_0 = (CommonTree)this.adaptor.nil();
                     CommonTree root_1 = (CommonTree)this.adaptor.nil();
                     root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(48, lp), root_1);
                     this.adaptor.addChild(root_1, stream_expr.nextTree());
                     this.adaptor.addChild(root_0, root_1);
                     retval.tree = root_0;
               }
         }

         retval.stop = this.input.LT(-1);
         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         return retval;
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final args_return args() throws RecognitionException {
      args_return retval = new args_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken char_literal135 = null;
      CommonToken char_literal137 = null;
      CommonToken string_literal138 = null;
      CommonToken string_literal139 = null;
      argExprList_return argExprList133 = null;
      namedArg_return namedArg134 = null;
      namedArg_return namedArg136 = null;
      CommonTree char_literal135_tree = null;
      CommonTree char_literal137_tree = null;
      CommonTree string_literal138_tree = null;
      CommonTree string_literal139_tree = null;
      RewriteRuleTokenStream stream_ELLIPSIS = new RewriteRuleTokenStream(this.adaptor, "token ELLIPSIS");
      RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
      RewriteRuleSubtreeStream stream_namedArg = new RewriteRuleSubtreeStream(this.adaptor, "rule namedArg");

      try {
         int alt41 = 4;
         int LA41_0 = this.input.LA(1);
         if (LA41_0 == 25) {
            switch (this.input.LA(2)) {
               case 12:
                  alt41 = 2;
                  break;
               case 13:
               case 14:
               case 15:
               case 18:
               case 19:
                  alt41 = 1;
                  break;
               case 16:
               case 17:
               default:
                  NoViableAltException nvae = new NoViableAltException("", 41, 1, this.input);
                  throw nvae;
            }
         } else if (LA41_0 != 8 && LA41_0 != 16 && LA41_0 != 20 && LA41_0 != 26 && LA41_0 != 33 && (LA41_0 < 35 || LA41_0 > 36)) {
            if (LA41_0 != 14 || this.conditional_stack.size() != 0 && this.conditional_stack.size() <= 0) {
               if (LA41_0 == 11) {
                  alt41 = 3;
               } else {
                  if (LA41_0 != 15) {
                     NoViableAltException nvae = new NoViableAltException("", 41, 0, this.input);
                     throw nvae;
                  }

                  alt41 = 4;
               }
            } else {
               alt41 = 1;
            }
         } else {
            alt41 = 1;
         }

         label215:
         switch (alt41) {
            case 1:
               root_0 = (CommonTree)this.adaptor.nil();
               this.pushFollow(FOLLOW_argExprList_in_args1634);
               argExprList133 = this.argExprList();
               --this.state._fsp;
               this.adaptor.addChild(root_0, argExprList133.getTree());
               break;
            case 2:
               this.pushFollow(FOLLOW_namedArg_in_args1639);
               namedArg134 = this.namedArg();
               --this.state._fsp;
               stream_namedArg.add(namedArg134.getTree());

               while(true) {
                  int alt39 = 2;
                  switch (this.input.LA(1)) {
                     case 18:
                        switch (this.input.LA(2)) {
                           case 25:
                              alt39 = 1;
                        }
                  }

                  switch (alt39) {
                     case 1:
                        char_literal135 = (CommonToken)this.match(this.input, 18, FOLLOW_COMMA_in_args1643);
                        stream_COMMA.add(char_literal135);
                        this.pushFollow(FOLLOW_namedArg_in_args1645);
                        namedArg136 = this.namedArg();
                        --this.state._fsp;
                        stream_namedArg.add(namedArg136.getTree());
                        break;
                     default:
                        alt39 = 2;
                        switch (this.input.LA(1)) {
                           case 18:
                              alt39 = 1;
                           default:
                              switch (alt39) {
                                 case 1:
                                    char_literal137 = (CommonToken)this.match(this.input, 18, FOLLOW_COMMA_in_args1651);
                                    stream_COMMA.add(char_literal137);
                                    string_literal138 = (CommonToken)this.match(this.input, 11, FOLLOW_ELLIPSIS_in_args1653);
                                    stream_ELLIPSIS.add(string_literal138);
                                 default:
                                    retval.tree = root_0;
                                    new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                                    root_0 = (CommonTree)this.adaptor.nil();
                                    if (!stream_namedArg.hasNext()) {
                                       throw new RewriteEarlyExitException();
                                    }
                              }
                        }

                        while(stream_namedArg.hasNext()) {
                           this.adaptor.addChild(root_0, stream_namedArg.nextTree());
                        }

                        stream_namedArg.reset();
                        if (stream_ELLIPSIS.hasNext()) {
                           this.adaptor.addChild(root_0, stream_ELLIPSIS.nextNode());
                        }

                        stream_ELLIPSIS.reset();
                        retval.tree = root_0;
                        break label215;
                  }
               }
            case 3:
               root_0 = (CommonTree)this.adaptor.nil();
               string_literal139 = (CommonToken)this.match(this.input, 11, FOLLOW_ELLIPSIS_in_args1673);
               string_literal139_tree = (CommonTree)this.adaptor.create(string_literal139);
               this.adaptor.addChild(root_0, string_literal139_tree);
               break;
            case 4:
               root_0 = (CommonTree)this.adaptor.nil();
         }

         retval.stop = this.input.LT(-1);
         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         return retval;
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final argExprList_return argExprList() throws RecognitionException {
      argExprList_return retval = new argExprList_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken char_literal141 = null;
      arg_return arg140 = null;
      arg_return arg142 = null;
      CommonTree char_literal141_tree = null;
      RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
      RewriteRuleSubtreeStream stream_arg = new RewriteRuleSubtreeStream(this.adaptor, "rule arg");

      try {
         this.pushFollow(FOLLOW_arg_in_argExprList1686);
         arg140 = this.arg();
         --this.state._fsp;
         stream_arg.add(arg140.getTree());

         while(true) {
            int alt42 = 2;
            switch (this.input.LA(1)) {
               case 18:
                  alt42 = 1;
            }

            switch (alt42) {
               case 1:
                  char_literal141 = (CommonToken)this.match(this.input, 18, FOLLOW_COMMA_in_argExprList1690);
                  stream_COMMA.add(char_literal141);
                  this.pushFollow(FOLLOW_arg_in_argExprList1692);
                  arg142 = this.arg();
                  --this.state._fsp;
                  stream_arg.add(arg142.getTree());
                  break;
               default:
                  retval.tree = root_0;
                  new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                  root_0 = (CommonTree)this.adaptor.nil();
                  if (!stream_arg.hasNext()) {
                     throw new RewriteEarlyExitException();
                  }

                  while(stream_arg.hasNext()) {
                     this.adaptor.addChild(root_0, stream_arg.nextTree());
                  }

                  stream_arg.reset();
                  retval.tree = root_0;
                  retval.stop = this.input.LT(-1);
                  retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
                  this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
                  return retval;
            }
         }
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final arg_return arg() throws RecognitionException {
      arg_return retval = new arg_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      exprNoComma_return exprNoComma143 = null;

      try {
         root_0 = (CommonTree)this.adaptor.nil();
         this.pushFollow(FOLLOW_exprNoComma_in_arg1709);
         exprNoComma143 = this.exprNoComma();
         --this.state._fsp;
         this.adaptor.addChild(root_0, exprNoComma143.getTree());
         retval.stop = this.input.LT(-1);
         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         return retval;
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final namedArg_return namedArg() throws RecognitionException {
      namedArg_return retval = new namedArg_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken ID144 = null;
      CommonToken char_literal145 = null;
      arg_return arg146 = null;
      CommonTree ID144_tree = null;
      CommonTree char_literal145_tree = null;
      RewriteRuleTokenStream stream_EQUALS = new RewriteRuleTokenStream(this.adaptor, "token EQUALS");
      RewriteRuleTokenStream stream_ID = new RewriteRuleTokenStream(this.adaptor, "token ID");
      RewriteRuleSubtreeStream stream_arg = new RewriteRuleSubtreeStream(this.adaptor, "rule arg");

      try {
         ID144 = (CommonToken)this.match(this.input, 25, FOLLOW_ID_in_namedArg1718);
         stream_ID.add(ID144);
         char_literal145 = (CommonToken)this.match(this.input, 12, FOLLOW_EQUALS_in_namedArg1720);
         stream_EQUALS.add(char_literal145);
         this.pushFollow(FOLLOW_arg_in_namedArg1722);
         arg146 = this.arg();
         --this.state._fsp;
         stream_arg.add(arg146.getTree());
         retval.tree = root_0;
         new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
         root_0 = (CommonTree)this.adaptor.nil();
         CommonTree root_1 = (CommonTree)this.adaptor.nil();
         root_1 = (CommonTree)this.adaptor.becomeRoot(stream_EQUALS.nextNode(), root_1);
         this.adaptor.addChild(root_1, stream_ID.nextNode());
         this.adaptor.addChild(root_1, stream_arg.nextTree());
         this.adaptor.addChild(root_0, root_1);
         retval.tree = root_0;
         retval.stop = this.input.LT(-1);
         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         return retval;
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final list_return list() throws RecognitionException {
      list_return retval = new list_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      CommonToken lb = null;
      CommonToken char_literal147 = null;
      CommonToken char_literal149 = null;
      CommonToken char_literal151 = null;
      listElement_return listElement148 = null;
      listElement_return listElement150 = null;
      CommonTree lb_tree = null;
      CommonTree char_literal147_tree = null;
      CommonTree char_literal149_tree = null;
      CommonTree char_literal151_tree = null;
      RewriteRuleTokenStream stream_RBRACK = new RewriteRuleTokenStream(this.adaptor, "token RBRACK");
      RewriteRuleTokenStream stream_LBRACK = new RewriteRuleTokenStream(this.adaptor, "token LBRACK");
      RewriteRuleTokenStream stream_COMMA = new RewriteRuleTokenStream(this.adaptor, "token COMMA");
      RewriteRuleSubtreeStream stream_listElement = new RewriteRuleSubtreeStream(this.adaptor, "rule listElement");

      try {
         int alt44 = 2;
         switch (this.input.LA(1)) {
            case 16:
               switch (this.input.LA(2)) {
                  case 8:
                  case 14:
                  case 16:
                  case 18:
                  case 20:
                  case 25:
                  case 26:
                  case 33:
                  case 35:
                  case 36:
                     alt44 = 2;
                     break;
                  case 9:
                  case 10:
                  case 11:
                  case 12:
                  case 13:
                  case 15:
                  case 19:
                  case 21:
                  case 22:
                  case 23:
                  case 24:
                  case 27:
                  case 28:
                  case 29:
                  case 30:
                  case 31:
                  case 32:
                  case 34:
                  default:
                     NoViableAltException nvae = new NoViableAltException("", 44, 1, this.input);
                     throw nvae;
                  case 17:
                     int LA44_2 = this.input.LA(3);
                     if (this.input.LA(2) == 17) {
                        alt44 = 1;
                     } else {
                        alt44 = 2;
                     }
               }

               switch (alt44) {
                  case 1:
                     if (this.input.LA(2) != 17) {
                        throw new FailedPredicateException(this.input, "list", "input.LA(2)==RBRACK");
                     }

                     lb = (CommonToken)this.match(this.input, 16, FOLLOW_LBRACK_in_list1747);
                     stream_LBRACK.add(lb);
                     char_literal147 = (CommonToken)this.match(this.input, 17, FOLLOW_RBRACK_in_list1749);
                     stream_RBRACK.add(char_literal147);
                     retval.tree = root_0;
                     new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                     root_0 = (CommonTree)this.adaptor.nil();
                     this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(49, lb));
                     retval.tree = root_0;
                     break;
                  case 2:
                     lb = (CommonToken)this.match(this.input, 16, FOLLOW_LBRACK_in_list1761);
                     stream_LBRACK.add(lb);
                     this.pushFollow(FOLLOW_listElement_in_list1763);
                     listElement148 = this.listElement();
                     --this.state._fsp;
                     stream_listElement.add(listElement148.getTree());

                     label139:
                     while(true) {
                        int alt43 = 2;
                        switch (this.input.LA(1)) {
                           case 18:
                              alt43 = 1;
                        }

                        switch (alt43) {
                           case 1:
                              char_literal149 = (CommonToken)this.match(this.input, 18, FOLLOW_COMMA_in_list1767);
                              stream_COMMA.add(char_literal149);
                              this.pushFollow(FOLLOW_listElement_in_list1769);
                              listElement150 = this.listElement();
                              --this.state._fsp;
                              stream_listElement.add(listElement150.getTree());
                              break;
                           default:
                              char_literal151 = (CommonToken)this.match(this.input, 17, FOLLOW_RBRACK_in_list1774);
                              stream_RBRACK.add(char_literal151);
                              retval.tree = root_0;
                              new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
                              root_0 = (CommonTree)this.adaptor.nil();
                              CommonTree root_1 = (CommonTree)this.adaptor.nil();
                              root_1 = (CommonTree)this.adaptor.becomeRoot((CommonTree)this.adaptor.create(49, lb), root_1);

                              while(stream_listElement.hasNext()) {
                                 this.adaptor.addChild(root_1, stream_listElement.nextTree());
                              }

                              stream_listElement.reset();
                              this.adaptor.addChild(root_0, root_1);
                              retval.tree = root_0;
                              break label139;
                        }
                     }
               }

               retval.stop = this.input.LT(-1);
               retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
               this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
               return retval;
            default:
               NoViableAltException nvae = new NoViableAltException("", 44, 0, this.input);
               throw nvae;
         }
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   public final listElement_return listElement() throws RecognitionException {
      listElement_return retval = new listElement_return();
      retval.start = this.input.LT(1);
      CommonTree root_0 = null;
      exprNoComma_return exprNoComma152 = null;

      try {
         int alt45 = 2;
         int LA45_0 = this.input.LA(1);
         if (LA45_0 != 8 && LA45_0 != 16 && LA45_0 != 20 && (LA45_0 < 25 || LA45_0 > 26) && LA45_0 != 33 && (LA45_0 < 35 || LA45_0 > 36)) {
            if (LA45_0 != 14 || this.conditional_stack.size() != 0 && this.conditional_stack.size() <= 0) {
               if (LA45_0 < 17 || LA45_0 > 18) {
                  NoViableAltException nvae = new NoViableAltException("", 45, 0, this.input);
                  throw nvae;
               }

               alt45 = 2;
            } else {
               alt45 = 1;
            }
         } else {
            alt45 = 1;
         }

         switch (alt45) {
            case 1:
               root_0 = (CommonTree)this.adaptor.nil();
               this.pushFollow(FOLLOW_exprNoComma_in_listElement1794);
               exprNoComma152 = this.exprNoComma();
               --this.state._fsp;
               this.adaptor.addChild(root_0, exprNoComma152.getTree());
               break;
            case 2:
               retval.tree = root_0;
               new RewriteRuleSubtreeStream(this.adaptor, "rule retval", retval != null ? retval.tree : null);
               root_0 = (CommonTree)this.adaptor.nil();
               this.adaptor.addChild(root_0, (CommonTree)this.adaptor.create(56, "NULL"));
               retval.tree = root_0;
         }

         retval.stop = this.input.LT(-1);
         retval.tree = (CommonTree)this.adaptor.rulePostProcessing(root_0);
         this.adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
         return retval;
      } catch (RecognitionException re) {
         throw re;
      } finally {
         ;
      }
   }

   static {
      int numStates = DFA3_transitionS.length;
      DFA3_transition = new short[numStates][];

      for(int i = 0; i < numStates; ++i) {
         DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
      }

      DFA35_transitionS = new String[]{"\u0001\u0002\u0005\uffff\u0001\t\u0001\uffff\u0001\u0004\u0003\uffff\u0001\u0004\u0004\uffff\u0001\u0001\u0001\u0004\u0006\uffff\u0001\u0003\u0001\uffff\u0002\u0004", "\u0001\u000b\u0003\uffff\u0001\u000b\u0001\n\u0001\u000b\u0001\uffff\u0003\u000b\u0004\uffff\u0001\u000b\u0004\uffff\u0002\u000b", "", "\u0001\u0014\u0010\uffff\u0001\u0015", "", "", "", "", "", "", "\u0001\uffff", "", "", "", "", "", "", "", "", "", "", "", "", ""};
      DFA35_eot = DFA.unpackEncodedString("\u0018\uffff");
      DFA35_eof = DFA.unpackEncodedString("\u0018\uffff");
      DFA35_min = DFA.unpackEncodedStringToUnsignedChars("\u0001\b\u0001\t\u0001\uffff\u0001\b\u0006\uffff\u0001\u0000\r\uffff");
      DFA35_max = DFA.unpackEncodedStringToUnsignedChars("\u0001$\u0001\u001e\u0001\uffff\u0001\u0019\u0006\uffff\u0001\u0000\r\uffff");
      DFA35_accept = DFA.unpackEncodedString("\u0002\uffff\u0001\u0002\u0001\uffff\u0001\u0006\u0004\uffff\u0001\u0006\u0001\uffff\u0001\u0006\b\uffff\u0001\u0004\u0001\u0005\u0001\u0001\u0001\u0003");
      DFA35_special = DFA.unpackEncodedString("\u0001\u0000\t\uffff\u0001\u0001\r\uffff}>");
      numStates = DFA35_transitionS.length;
      DFA35_transition = new short[numStates][];

      for(int i = 0; i < numStates; ++i) {
         DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
      }

      DFA38_transitionS = new String[]{"\u0001\u0007\u0001\uffff\u0001\u0006\u0003\uffff\u0001\u0005\u0004\uffff\u0001\u0001\u0001\u0002\b\uffff\u0001\u0003\u0001\u0004", "", "", "", "", "", "", "\u0001\uffff", "", ""};
      DFA38_eot = DFA.unpackEncodedString("\n\uffff");
      DFA38_eof = DFA.unpackEncodedString("\n\uffff");
      DFA38_min = DFA.unpackEncodedStringToUnsignedChars("\u0001\u000e\u0006\uffff\u0001\u0000\u0002\uffff");
      DFA38_max = DFA.unpackEncodedStringToUnsignedChars("\u0001$\u0006\uffff\u0001\u0000\u0002\uffff");
      DFA38_accept = DFA.unpackEncodedString("\u0001\uffff\u0001\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0001\u0005\u0001\u0006\u0001\uffff\u0001\u0007\u0001\b");
      DFA38_special = DFA.unpackEncodedString("\u0001\u0000\u0006\uffff\u0001\u0001\u0002\uffff}>");
      numStates = DFA38_transitionS.length;
      DFA38_transition = new short[numStates][];

      for(int i = 0; i < numStates; ++i) {
         DFA38_transition[i] = DFA.unpackEncodedString(DFA38_transitionS[i]);
      }

      FOLLOW_template_in_templateAndEOF133 = new BitSet(new long[]{0L});
      FOLLOW_EOF_in_templateAndEOF135 = new BitSet(new long[]{2L});
      FOLLOW_element_in_template149 = new BitSet(new long[]{143893987330L});
      FOLLOW_INDENT_in_element162 = new BitSet(new long[]{137438953472L});
      FOLLOW_COMMENT_in_element165 = new BitSet(new long[]{4294967296L});
      FOLLOW_NEWLINE_in_element167 = new BitSet(new long[]{2L});
      FOLLOW_INDENT_in_element175 = new BitSet(new long[]{141746503680L});
      FOLLOW_singleElement_in_element177 = new BitSet(new long[]{2L});
      FOLLOW_singleElement_in_element194 = new BitSet(new long[]{2L});
      FOLLOW_compoundElement_in_element199 = new BitSet(new long[]{2L});
      FOLLOW_exprTag_in_singleElement210 = new BitSet(new long[]{2L});
      FOLLOW_TEXT_in_singleElement215 = new BitSet(new long[]{2L});
      FOLLOW_NEWLINE_in_singleElement220 = new BitSet(new long[]{2L});
      FOLLOW_COMMENT_in_singleElement225 = new BitSet(new long[]{2L});
      FOLLOW_ifstat_in_compoundElement238 = new BitSet(new long[]{2L});
      FOLLOW_region_in_compoundElement243 = new BitSet(new long[]{2L});
      FOLLOW_LDELIM_in_exprTag254 = new BitSet(new long[]{111770943744L});
      FOLLOW_expr_in_exprTag256 = new BitSet(new long[]{16777728L});
      FOLLOW_SEMI_in_exprTag260 = new BitSet(new long[]{33554432L});
      FOLLOW_exprOptions_in_exprTag262 = new BitSet(new long[]{16777216L});
      FOLLOW_RDELIM_in_exprTag267 = new BitSet(new long[]{2L});
      FOLLOW_INDENT_in_region299 = new BitSet(new long[]{8388608L});
      FOLLOW_LDELIM_in_region304 = new BitSet(new long[]{8589934592L});
      FOLLOW_AT_in_region306 = new BitSet(new long[]{33554432L});
      FOLLOW_ID_in_region308 = new BitSet(new long[]{16777216L});
      FOLLOW_RDELIM_in_region310 = new BitSet(new long[]{143893987328L});
      FOLLOW_template_in_region316 = new BitSet(new long[]{2155872256L});
      FOLLOW_INDENT_in_region320 = new BitSet(new long[]{8388608L});
      FOLLOW_LDELIM_in_region323 = new BitSet(new long[]{17179869184L});
      FOLLOW_END_in_region325 = new BitSet(new long[]{16777216L});
      FOLLOW_RDELIM_in_region327 = new BitSet(new long[]{4294967298L});
      FOLLOW_NEWLINE_in_region338 = new BitSet(new long[]{2L});
      FOLLOW_LCURLY_in_subtemplate414 = new BitSet(new long[]{143927541760L});
      FOLLOW_ID_in_subtemplate420 = new BitSet(new long[]{268697600L});
      FOLLOW_COMMA_in_subtemplate424 = new BitSet(new long[]{33554432L});
      FOLLOW_ID_in_subtemplate429 = new BitSet(new long[]{268697600L});
      FOLLOW_PIPE_in_subtemplate434 = new BitSet(new long[]{143893987328L});
      FOLLOW_template_in_subtemplate439 = new BitSet(new long[]{2149580800L});
      FOLLOW_INDENT_in_subtemplate441 = new BitSet(new long[]{2097152L});
      FOLLOW_RCURLY_in_subtemplate444 = new BitSet(new long[]{2L});
      FOLLOW_INDENT_in_ifstat485 = new BitSet(new long[]{8388608L});
      FOLLOW_LDELIM_in_ifstat488 = new BitSet(new long[]{16L});
      FOLLOW_IF_in_ifstat490 = new BitSet(new long[]{16384L});
      FOLLOW_LPAREN_in_ifstat492 = new BitSet(new long[]{111770944768L});
      FOLLOW_conditional_in_ifstat496 = new BitSet(new long[]{32768L});
      FOLLOW_RPAREN_in_ifstat498 = new BitSet(new long[]{16777216L});
      FOLLOW_RDELIM_in_ifstat500 = new BitSet(new long[]{143893987328L});
      FOLLOW_template_in_ifstat509 = new BitSet(new long[]{2155872256L});
      FOLLOW_INDENT_in_ifstat516 = new BitSet(new long[]{8388608L});
      FOLLOW_LDELIM_in_ifstat519 = new BitSet(new long[]{64L});
      FOLLOW_ELSEIF_in_ifstat521 = new BitSet(new long[]{16384L});
      FOLLOW_LPAREN_in_ifstat523 = new BitSet(new long[]{111770944768L});
      FOLLOW_conditional_in_ifstat527 = new BitSet(new long[]{32768L});
      FOLLOW_RPAREN_in_ifstat529 = new BitSet(new long[]{16777216L});
      FOLLOW_RDELIM_in_ifstat531 = new BitSet(new long[]{143893987328L});
      FOLLOW_template_in_ifstat535 = new BitSet(new long[]{2155872256L});
      FOLLOW_INDENT_in_ifstat545 = new BitSet(new long[]{8388608L});
      FOLLOW_LDELIM_in_ifstat548 = new BitSet(new long[]{32L});
      FOLLOW_ELSE_in_ifstat550 = new BitSet(new long[]{16777216L});
      FOLLOW_RDELIM_in_ifstat552 = new BitSet(new long[]{143893987328L});
      FOLLOW_template_in_ifstat556 = new BitSet(new long[]{2155872256L});
      FOLLOW_INDENT_in_ifstat564 = new BitSet(new long[]{8388608L});
      FOLLOW_LDELIM_in_ifstat570 = new BitSet(new long[]{128L});
      FOLLOW_ENDIF_in_ifstat572 = new BitSet(new long[]{16777216L});
      FOLLOW_RDELIM_in_ifstat576 = new BitSet(new long[]{4294967298L});
      FOLLOW_NEWLINE_in_ifstat587 = new BitSet(new long[]{2L});
      FOLLOW_andConditional_in_conditional707 = new BitSet(new long[]{536870914L});
      FOLLOW_OR_in_conditional711 = new BitSet(new long[]{111770944768L});
      FOLLOW_andConditional_in_conditional714 = new BitSet(new long[]{536870914L});
      FOLLOW_notConditional_in_andConditional727 = new BitSet(new long[]{1073741826L});
      FOLLOW_AND_in_andConditional731 = new BitSet(new long[]{111770944768L});
      FOLLOW_notConditional_in_andConditional734 = new BitSet(new long[]{1073741826L});
      FOLLOW_BANG_in_notConditional747 = new BitSet(new long[]{111770944768L});
      FOLLOW_notConditional_in_notConditional750 = new BitSet(new long[]{2L});
      FOLLOW_memberExpr_in_notConditional755 = new BitSet(new long[]{2L});
      FOLLOW_ID_in_notConditionalExpr767 = new BitSet(new long[]{524290L});
      FOLLOW_DOT_in_notConditionalExpr778 = new BitSet(new long[]{33554432L});
      FOLLOW_ID_in_notConditionalExpr782 = new BitSet(new long[]{524290L});
      FOLLOW_DOT_in_notConditionalExpr808 = new BitSet(new long[]{16384L});
      FOLLOW_LPAREN_in_notConditionalExpr810 = new BitSet(new long[]{111770943744L});
      FOLLOW_mapExpr_in_notConditionalExpr812 = new BitSet(new long[]{32768L});
      FOLLOW_RPAREN_in_notConditionalExpr814 = new BitSet(new long[]{524290L});
      FOLLOW_option_in_exprOptions844 = new BitSet(new long[]{262146L});
      FOLLOW_COMMA_in_exprOptions848 = new BitSet(new long[]{33554432L});
      FOLLOW_option_in_exprOptions850 = new BitSet(new long[]{262146L});
      FOLLOW_ID_in_option877 = new BitSet(new long[]{4098L});
      FOLLOW_EQUALS_in_option887 = new BitSet(new long[]{111770943744L});
      FOLLOW_exprNoComma_in_option889 = new BitSet(new long[]{2L});
      FOLLOW_memberExpr_in_exprNoComma996 = new BitSet(new long[]{8194L});
      FOLLOW_COLON_in_exprNoComma1002 = new BitSet(new long[]{34619392L});
      FOLLOW_mapTemplateRef_in_exprNoComma1004 = new BitSet(new long[]{2L});
      FOLLOW_mapExpr_in_expr1049 = new BitSet(new long[]{2L});
      FOLLOW_memberExpr_in_mapExpr1061 = new BitSet(new long[]{270338L});
      FOLLOW_COMMA_in_mapExpr1070 = new BitSet(new long[]{111770943744L});
      FOLLOW_memberExpr_in_mapExpr1072 = new BitSet(new long[]{270336L});
      FOLLOW_COLON_in_mapExpr1078 = new BitSet(new long[]{34619392L});
      FOLLOW_mapTemplateRef_in_mapExpr1080 = new BitSet(new long[]{8194L});
      FOLLOW_COLON_in_mapExpr1143 = new BitSet(new long[]{34619392L});
      FOLLOW_mapTemplateRef_in_mapExpr1147 = new BitSet(new long[]{270338L});
      FOLLOW_COMMA_in_mapExpr1153 = new BitSet(new long[]{34619392L});
      FOLLOW_mapTemplateRef_in_mapExpr1157 = new BitSet(new long[]{270338L});
      FOLLOW_ID_in_mapTemplateRef1204 = new BitSet(new long[]{16384L});
      FOLLOW_LPAREN_in_mapTemplateRef1206 = new BitSet(new long[]{111770978560L});
      FOLLOW_args_in_mapTemplateRef1208 = new BitSet(new long[]{32768L});
      FOLLOW_RPAREN_in_mapTemplateRef1210 = new BitSet(new long[]{2L});
      FOLLOW_subtemplate_in_mapTemplateRef1232 = new BitSet(new long[]{2L});
      FOLLOW_LPAREN_in_mapTemplateRef1239 = new BitSet(new long[]{111770943744L});
      FOLLOW_mapExpr_in_mapTemplateRef1241 = new BitSet(new long[]{32768L});
      FOLLOW_RPAREN_in_mapTemplateRef1245 = new BitSet(new long[]{16384L});
      FOLLOW_LPAREN_in_mapTemplateRef1247 = new BitSet(new long[]{111770976512L});
      FOLLOW_argExprList_in_mapTemplateRef1249 = new BitSet(new long[]{32768L});
      FOLLOW_RPAREN_in_mapTemplateRef1252 = new BitSet(new long[]{2L});
      FOLLOW_includeExpr_in_memberExpr1275 = new BitSet(new long[]{524290L});
      FOLLOW_DOT_in_memberExpr1286 = new BitSet(new long[]{33554432L});
      FOLLOW_ID_in_memberExpr1288 = new BitSet(new long[]{524290L});
      FOLLOW_DOT_in_memberExpr1314 = new BitSet(new long[]{16384L});
      FOLLOW_LPAREN_in_memberExpr1316 = new BitSet(new long[]{111770943744L});
      FOLLOW_mapExpr_in_memberExpr1318 = new BitSet(new long[]{32768L});
      FOLLOW_RPAREN_in_memberExpr1320 = new BitSet(new long[]{524290L});
      FOLLOW_ID_in_includeExpr1364 = new BitSet(new long[]{16384L});
      FOLLOW_LPAREN_in_includeExpr1366 = new BitSet(new long[]{111770976512L});
      FOLLOW_expr_in_includeExpr1368 = new BitSet(new long[]{32768L});
      FOLLOW_RPAREN_in_includeExpr1371 = new BitSet(new long[]{2L});
      FOLLOW_SUPER_in_includeExpr1392 = new BitSet(new long[]{524288L});
      FOLLOW_DOT_in_includeExpr1394 = new BitSet(new long[]{33554432L});
      FOLLOW_ID_in_includeExpr1396 = new BitSet(new long[]{16384L});
      FOLLOW_LPAREN_in_includeExpr1398 = new BitSet(new long[]{111770978560L});
      FOLLOW_args_in_includeExpr1400 = new BitSet(new long[]{32768L});
      FOLLOW_RPAREN_in_includeExpr1402 = new BitSet(new long[]{2L});
      FOLLOW_ID_in_includeExpr1421 = new BitSet(new long[]{16384L});
      FOLLOW_LPAREN_in_includeExpr1423 = new BitSet(new long[]{111770978560L});
      FOLLOW_args_in_includeExpr1425 = new BitSet(new long[]{32768L});
      FOLLOW_RPAREN_in_includeExpr1427 = new BitSet(new long[]{2L});
      FOLLOW_AT_in_includeExpr1449 = new BitSet(new long[]{256L});
      FOLLOW_SUPER_in_includeExpr1451 = new BitSet(new long[]{524288L});
      FOLLOW_DOT_in_includeExpr1453 = new BitSet(new long[]{33554432L});
      FOLLOW_ID_in_includeExpr1455 = new BitSet(new long[]{16384L});
      FOLLOW_LPAREN_in_includeExpr1457 = new BitSet(new long[]{32768L});
      FOLLOW_RPAREN_in_includeExpr1461 = new BitSet(new long[]{2L});
      FOLLOW_AT_in_includeExpr1476 = new BitSet(new long[]{33554432L});
      FOLLOW_ID_in_includeExpr1478 = new BitSet(new long[]{16384L});
      FOLLOW_LPAREN_in_includeExpr1480 = new BitSet(new long[]{32768L});
      FOLLOW_RPAREN_in_includeExpr1484 = new BitSet(new long[]{2L});
      FOLLOW_primary_in_includeExpr1502 = new BitSet(new long[]{2L});
      FOLLOW_ID_in_primary1513 = new BitSet(new long[]{2L});
      FOLLOW_STRING_in_primary1518 = new BitSet(new long[]{2L});
      FOLLOW_TRUE_in_primary1523 = new BitSet(new long[]{2L});
      FOLLOW_FALSE_in_primary1528 = new BitSet(new long[]{2L});
      FOLLOW_subtemplate_in_primary1533 = new BitSet(new long[]{2L});
      FOLLOW_list_in_primary1538 = new BitSet(new long[]{2L});
      FOLLOW_LPAREN_in_primary1547 = new BitSet(new long[]{111770944768L});
      FOLLOW_conditional_in_primary1550 = new BitSet(new long[]{32768L});
      FOLLOW_RPAREN_in_primary1552 = new BitSet(new long[]{2L});
      FOLLOW_LPAREN_in_primary1563 = new BitSet(new long[]{111770943744L});
      FOLLOW_expr_in_primary1565 = new BitSet(new long[]{32768L});
      FOLLOW_RPAREN_in_primary1567 = new BitSet(new long[]{16386L});
      FOLLOW_LPAREN_in_primary1573 = new BitSet(new long[]{111770976512L});
      FOLLOW_argExprList_in_primary1575 = new BitSet(new long[]{32768L});
      FOLLOW_RPAREN_in_primary1578 = new BitSet(new long[]{2L});
      FOLLOW_argExprList_in_args1634 = new BitSet(new long[]{2L});
      FOLLOW_namedArg_in_args1639 = new BitSet(new long[]{262146L});
      FOLLOW_COMMA_in_args1643 = new BitSet(new long[]{33554432L});
      FOLLOW_namedArg_in_args1645 = new BitSet(new long[]{262146L});
      FOLLOW_COMMA_in_args1651 = new BitSet(new long[]{2048L});
      FOLLOW_ELLIPSIS_in_args1653 = new BitSet(new long[]{2L});
      FOLLOW_ELLIPSIS_in_args1673 = new BitSet(new long[]{2L});
      FOLLOW_arg_in_argExprList1686 = new BitSet(new long[]{262146L});
      FOLLOW_COMMA_in_argExprList1690 = new BitSet(new long[]{111770943744L});
      FOLLOW_arg_in_argExprList1692 = new BitSet(new long[]{262146L});
      FOLLOW_exprNoComma_in_arg1709 = new BitSet(new long[]{2L});
      FOLLOW_ID_in_namedArg1718 = new BitSet(new long[]{4096L});
      FOLLOW_EQUALS_in_namedArg1720 = new BitSet(new long[]{111770943744L});
      FOLLOW_arg_in_namedArg1722 = new BitSet(new long[]{2L});
      FOLLOW_LBRACK_in_list1747 = new BitSet(new long[]{131072L});
      FOLLOW_RBRACK_in_list1749 = new BitSet(new long[]{2L});
      FOLLOW_LBRACK_in_list1761 = new BitSet(new long[]{111771336960L});
      FOLLOW_listElement_in_list1763 = new BitSet(new long[]{393216L});
      FOLLOW_COMMA_in_list1767 = new BitSet(new long[]{111771336960L});
      FOLLOW_listElement_in_list1769 = new BitSet(new long[]{393216L});
      FOLLOW_RBRACK_in_list1774 = new BitSet(new long[]{2L});
      FOLLOW_exprNoComma_in_listElement1794 = new BitSet(new long[]{2L});
   }

   public static class templateAndEOF_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class template_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class element_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class singleElement_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class compoundElement_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class exprTag_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class region_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class subtemplate_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class ifstat_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   protected static class conditional_scope {
      boolean inside;
   }

   public static class conditional_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class andConditional_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class notConditional_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class notConditionalExpr_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class exprOptions_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class option_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class exprNoComma_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class expr_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class mapExpr_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class mapTemplateRef_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class memberExpr_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class includeExpr_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class primary_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class args_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class argExprList_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class arg_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class namedArg_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class list_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   public static class listElement_return extends ParserRuleReturnScope {
      CommonTree tree;

      public Object getTree() {
         return this.tree;
      }
   }

   class DFA3 extends DFA {
      public DFA3(BaseRecognizer recognizer) {
         this.recognizer = recognizer;
         this.decisionNumber = 3;
         this.eot = STParser.DFA3_eot;
         this.eof = STParser.DFA3_eof;
         this.min = STParser.DFA3_min;
         this.max = STParser.DFA3_max;
         this.accept = STParser.DFA3_accept;
         this.special = STParser.DFA3_special;
         this.transition = STParser.DFA3_transition;
      }

      public String getDescription() {
         return "74:1: element : ({...}? ( INDENT )? COMMENT NEWLINE -> | INDENT singleElement -> ^( INDENTED_EXPR INDENT ( singleElement )? ) | singleElement | compoundElement );";
      }

      public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
         TokenStream input = (TokenStream)_input;
         int _s = s;
         switch (s) {
            case 0:
               int LA3_11 = input.LA(1);
               int index3_11 = input.index();
               input.rewind();
               s = -1;
               if (input.LT(1).getCharPositionInLine() == 0) {
                  s = 13;
               } else {
                  s = 7;
               }

               input.seek(index3_11);
               if (s >= 0) {
                  return s;
               }
               break;
            case 1:
               int LA3_8 = input.LA(1);
               int index3_8 = input.index();
               input.rewind();
               s = -1;
               if (input.LT(1).getCharPositionInLine() == 0) {
                  s = 13;
               } else {
                  s = 4;
               }

               input.seek(index3_8);
               if (s >= 0) {
                  return s;
               }
         }

         NoViableAltException nvae = new NoViableAltException(this.getDescription(), 3, _s, input);
         this.error(nvae);
         throw nvae;
      }
   }

   class DFA35 extends DFA {
      public DFA35(BaseRecognizer recognizer) {
         this.recognizer = recognizer;
         this.decisionNumber = 35;
         this.eot = STParser.DFA35_eot;
         this.eof = STParser.DFA35_eof;
         this.min = STParser.DFA35_min;
         this.max = STParser.DFA35_max;
         this.accept = STParser.DFA35_accept;
         this.special = STParser.DFA35_special;
         this.transition = STParser.DFA35_transition;
      }

      public String getDescription() {
         return "220:1: includeExpr options {k=2; } : ({...}? ID '(' ( expr )? ')' -> ^( EXEC_FUNC ID ( expr )? ) | 'super' '.' ID '(' args ')' -> ^( INCLUDE_SUPER ID ( args )? ) | ID '(' args ')' -> ^( INCLUDE ID ( args )? ) | '@' 'super' '.' ID '(' rp= ')' -> ^( INCLUDE_SUPER_REGION ID ) | '@' ID '(' rp= ')' -> ^( INCLUDE_REGION ID ) | primary );";
      }

      public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
         TokenStream input = (TokenStream)_input;
         int _s = s;
         switch (s) {
            case 0:
               int LA35_0 = input.LA(1);
               int index35_0 = input.index();
               input.rewind();
               s = -1;
               if (LA35_0 == 25) {
                  s = 1;
               } else if (LA35_0 == 8) {
                  s = 2;
               } else if (LA35_0 == 33) {
                  s = 3;
               } else if (LA35_0 != 16 && LA35_0 != 20 && LA35_0 != 26 && (LA35_0 < 35 || LA35_0 > 36)) {
                  if (LA35_0 == 14 && (STParser.this.conditional_stack.size() == 0 || STParser.this.conditional_stack.size() > 0)) {
                     s = 9;
                  }
               } else {
                  s = 4;
               }

               input.seek(index35_0);
               if (s >= 0) {
                  return s;
               }
               break;
            case 1:
               int LA35_10 = input.LA(1);
               int index35_10 = input.index();
               input.rewind();
               s = -1;
               if (Compiler.funcs.containsKey(input.LT(1).getText())) {
                  s = 22;
               } else {
                  s = 23;
               }

               input.seek(index35_10);
               if (s >= 0) {
                  return s;
               }
         }

         NoViableAltException nvae = new NoViableAltException(this.getDescription(), 35, _s, input);
         this.error(nvae);
         throw nvae;
      }
   }

   class DFA38 extends DFA {
      public DFA38(BaseRecognizer recognizer) {
         this.recognizer = recognizer;
         this.decisionNumber = 38;
         this.eot = STParser.DFA38_eot;
         this.eof = STParser.DFA38_eof;
         this.min = STParser.DFA38_min;
         this.max = STParser.DFA38_max;
         this.accept = STParser.DFA38_accept;
         this.special = STParser.DFA38_special;
         this.transition = STParser.DFA38_transition;
      }

      public String getDescription() {
         return "231:1: primary : ( ID | STRING | TRUE | FALSE | subtemplate | list | {...}? => '(' conditional ')' | {...}? =>lp= '(' expr ')' ( '(' ( argExprList )? ')' -> ^( INCLUDE_IND[$lp] expr ( argExprList )? ) | -> ^( TO_STR[$lp] expr ) ) );";
      }

      public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
         TokenStream input = (TokenStream)_input;
         int _s = s;
         switch (s) {
            case 0:
               int LA38_0 = input.LA(1);
               int index38_0 = input.index();
               input.rewind();
               s = -1;
               if (LA38_0 == 25) {
                  s = 1;
               } else if (LA38_0 == 26) {
                  s = 2;
               } else if (LA38_0 == 35) {
                  s = 3;
               } else if (LA38_0 == 36) {
                  s = 4;
               } else if (LA38_0 == 20) {
                  s = 5;
               } else if (LA38_0 == 16) {
                  s = 6;
               } else if (LA38_0 == 14 && (STParser.this.conditional_stack.size() == 0 || STParser.this.conditional_stack.size() > 0)) {
                  s = 7;
               }

               input.seek(index38_0);
               if (s >= 0) {
                  return s;
               }
               break;
            case 1:
               int LA38_7 = input.LA(1);
               int index38_7 = input.index();
               input.rewind();
               s = -1;
               if (STParser.this.conditional_stack.size() > 0) {
                  s = 8;
               } else if (STParser.this.conditional_stack.size() == 0) {
                  s = 9;
               }

               input.seek(index38_7);
               if (s >= 0) {
                  return s;
               }
         }

         NoViableAltException nvae = new NoViableAltException(this.getDescription(), 38, _s, input);
         this.error(nvae);
         throw nvae;
      }
   }
}
