package org.stringtemplate.v4.compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.TreeNodeStream;
import org.antlr.runtime.tree.TreeParser;
import org.antlr.runtime.tree.TreeRuleReturnScope;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.misc.ErrorManager;
import org.stringtemplate.v4.misc.ErrorType;
import org.stringtemplate.v4.misc.Misc;

public class CodeGenerator extends TreeParser {
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
   String outermostTemplateName;
   CompiledST outermostImpl;
   Token templateToken;
   String template;
   ErrorManager errMgr;
   protected Stack template_stack;
   public static final BitSet FOLLOW_template_in_templateAndEOF44 = new BitSet(new long[]{0L});
   public static final BitSet FOLLOW_EOF_in_templateAndEOF47 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_chunk_in_template71 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_element_in_chunk86 = new BitSet(new long[]{180144264271888402L});
   public static final BitSet FOLLOW_INDENTED_EXPR_in_element99 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_INDENT_in_element101 = new BitSet(new long[]{36028797018963984L});
   public static final BitSet FOLLOW_compoundElement_in_element103 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_compoundElement_in_element111 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_INDENTED_EXPR_in_element118 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_INDENT_in_element120 = new BitSet(new long[]{180144264271888408L});
   public static final BitSet FOLLOW_singleElement_in_element124 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_singleElement_in_element132 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_exprElement_in_singleElement143 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_TEXT_in_singleElement148 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_NEWLINE_in_singleElement158 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_ifstat_in_compoundElement172 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_region_in_compoundElement178 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_EXPR_in_exprElement197 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_expr_in_exprElement199 = new BitSet(new long[]{549755813896L});
   public static final BitSet FOLLOW_exprOptions_in_exprElement202 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_REGION_in_region240 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_ID_in_region242 = new BitSet(new long[]{180144264271888400L});
   public static final BitSet FOLLOW_template_in_region252 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_SUBTEMPLATE_in_subtemplate285 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_ARGS_in_subtemplate292 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_ID_in_subtemplate295 = new BitSet(new long[]{33554440L});
   public static final BitSet FOLLOW_template_in_subtemplate312 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_SUBTEMPLATE_in_subtemplate328 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_IF_in_ifstat360 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_conditional_in_ifstat362 = new BitSet(new long[]{180144264271888400L});
   public static final BitSet FOLLOW_chunk_in_ifstat372 = new BitSet(new long[]{104L});
   public static final BitSet FOLLOW_ELSEIF_in_ifstat382 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_conditional_in_ifstat396 = new BitSet(new long[]{180144264271888400L});
   public static final BitSet FOLLOW_chunk_in_ifstat408 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_ELSE_in_ifstat431 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_chunk_in_ifstat445 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_OR_in_conditional479 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_conditional_in_conditional481 = new BitSet(new long[]{9006204533605376L});
   public static final BitSet FOLLOW_conditional_in_conditional483 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_AND_in_conditional493 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_conditional_in_conditional495 = new BitSet(new long[]{9006204533605376L});
   public static final BitSet FOLLOW_conditional_in_conditional497 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_BANG_in_conditional507 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_conditional_in_conditional509 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_expr_in_conditional521 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_OPTIONS_in_exprOptions535 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_option_in_exprOptions537 = new BitSet(new long[]{4104L});
   public static final BitSet FOLLOW_EQUALS_in_option549 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_ID_in_option551 = new BitSet(new long[]{9006204533605376L});
   public static final BitSet FOLLOW_expr_in_option553 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_ZIP_in_expr572 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_ELEMENTS_in_expr575 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_expr_in_expr578 = new BitSet(new long[]{9006204533605384L});
   public static final BitSet FOLLOW_mapTemplateRef_in_expr585 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_MAP_in_expr597 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_expr_in_expr599 = new BitSet(new long[]{4516793766903808L});
   public static final BitSet FOLLOW_mapTemplateRef_in_expr602 = new BitSet(new long[]{4516793766903816L});
   public static final BitSet FOLLOW_prop_in_expr617 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_includeExpr_in_expr622 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_PROP_in_prop632 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_expr_in_prop634 = new BitSet(new long[]{33554432L});
   public static final BitSet FOLLOW_ID_in_prop636 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_PROP_IND_in_prop650 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_expr_in_prop652 = new BitSet(new long[]{9006204533605376L});
   public static final BitSet FOLLOW_expr_in_prop654 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_INCLUDE_in_mapTemplateRef674 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_ID_in_mapTemplateRef676 = new BitSet(new long[]{9006204533611528L});
   public static final BitSet FOLLOW_args_in_mapTemplateRef686 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_subtemplate_in_mapTemplateRef699 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_INCLUDE_IND_in_mapTemplateRef711 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_expr_in_mapTemplateRef713 = new BitSet(new long[]{9006204533611528L});
   public static final BitSet FOLLOW_args_in_mapTemplateRef723 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_EXEC_FUNC_in_includeExpr745 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_ID_in_includeExpr747 = new BitSet(new long[]{9006204533605384L});
   public static final BitSet FOLLOW_expr_in_includeExpr749 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_INCLUDE_in_includeExpr760 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_ID_in_includeExpr762 = new BitSet(new long[]{9006204533611528L});
   public static final BitSet FOLLOW_args_in_includeExpr764 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_INCLUDE_SUPER_in_includeExpr775 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_ID_in_includeExpr777 = new BitSet(new long[]{9006204533611528L});
   public static final BitSet FOLLOW_args_in_includeExpr779 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_INCLUDE_REGION_in_includeExpr790 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_ID_in_includeExpr792 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_INCLUDE_SUPER_REGION_in_includeExpr802 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_ID_in_includeExpr804 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_primary_in_includeExpr812 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_ID_in_primary823 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_STRING_in_primary833 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_TRUE_in_primary842 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_FALSE_in_primary851 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_subtemplate_in_primary860 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_list_in_primary887 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_INCLUDE_IND_in_primary894 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_expr_in_primary899 = new BitSet(new long[]{9006204533611528L});
   public static final BitSet FOLLOW_args_in_primary908 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_TO_STR_in_primary928 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_expr_in_primary930 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_expr_in_arg943 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_arg_in_args959 = new BitSet(new long[]{9006204533605378L});
   public static final BitSet FOLLOW_EQUALS_in_args978 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_ID_in_args980 = new BitSet(new long[]{9006204533605376L});
   public static final BitSet FOLLOW_expr_in_args982 = new BitSet(new long[]{8L});
   public static final BitSet FOLLOW_ELLIPSIS_in_args999 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_ELLIPSIS_in_args1014 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_LIST_in_list1034 = new BitSet(new long[]{4L});
   public static final BitSet FOLLOW_listElement_in_list1037 = new BitSet(new long[]{81063798571533320L});
   public static final BitSet FOLLOW_expr_in_listElement1053 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_NULL_in_listElement1057 = new BitSet(new long[]{2L});

   public CodeGenerator(TreeNodeStream input) {
      this(input, new RecognizerSharedState());
   }

   public CodeGenerator(TreeNodeStream input, RecognizerSharedState state) {
      super(input, state);
      this.template_stack = new Stack();
   }

   public String[] getTokenNames() {
      return tokenNames;
   }

   public String getGrammarFileName() {
      return "org/stringtemplate/v4/compiler/CodeGenerator.g";
   }

   public CodeGenerator(TreeNodeStream input, ErrorManager errMgr, String name, String template, Token templateToken) {
      this(input, new RecognizerSharedState());
      this.errMgr = errMgr;
      this.outermostTemplateName = name;
      this.template = template;
      this.templateToken = templateToken;
   }

   public void emit1(CommonTree opAST, short opcode, int arg) {
      ((template_scope)this.template_stack.peek()).state.emit1(opAST, opcode, arg);
   }

   public void emit1(CommonTree opAST, short opcode, String arg) {
      ((template_scope)this.template_stack.peek()).state.emit1(opAST, opcode, arg);
   }

   public void emit2(CommonTree opAST, short opcode, int arg, int arg2) {
      ((template_scope)this.template_stack.peek()).state.emit2(opAST, opcode, arg, arg2);
   }

   public void emit2(CommonTree opAST, short opcode, String s, int arg2) {
      ((template_scope)this.template_stack.peek()).state.emit2(opAST, opcode, s, arg2);
   }

   public void emit(short opcode) {
      ((template_scope)this.template_stack.peek()).state.emit(opcode);
   }

   public void emit(CommonTree opAST, short opcode) {
      ((template_scope)this.template_stack.peek()).state.emit(opAST, opcode);
   }

   public void insert(int addr, short opcode, String s) {
      ((template_scope)this.template_stack.peek()).state.insert(addr, opcode, s);
   }

   public void setOption(CommonTree id) {
      ((template_scope)this.template_stack.peek()).state.setOption(id);
   }

   public void write(int addr, short value) {
      ((template_scope)this.template_stack.peek()).state.write(addr, value);
   }

   public int address() {
      return ((template_scope)this.template_stack.peek()).state.ip;
   }

   public void func(CommonTree id) {
      ((template_scope)this.template_stack.peek()).state.func(this.templateToken, id);
   }

   public void refAttr(CommonTree id) {
      ((template_scope)this.template_stack.peek()).state.refAttr(this.templateToken, id);
   }

   public int defineString(String s) {
      return ((template_scope)this.template_stack.peek()).state.defineString(s);
   }

   public final void templateAndEOF() throws RecognitionException {
      try {
         try {
            this.pushFollow(FOLLOW_template_in_templateAndEOF44);
            this.template((String)null, (List)null);
            --this.state._fsp;
            this.match(this.input, -1, FOLLOW_EOF_in_templateAndEOF47);
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final CompiledST template(String name, List args) throws RecognitionException {
      this.template_stack.push(new template_scope());
      CompiledST impl = null;
      ((template_scope)this.template_stack.peek()).state = new CompilationState(this.errMgr, name, this.input.getTokenStream());
      impl = ((template_scope)this.template_stack.peek()).state.impl;
      if (this.template_stack.size() == 1) {
         this.outermostImpl = impl;
      }

      impl.defineFormalArgs(args);
      if (name != null && name.startsWith("_sub")) {
         impl.addArg(new FormalArgument("i"));
         impl.addArg(new FormalArgument("i0"));
      }

      impl.template = this.template;

      try {
         this.pushFollow(FOLLOW_chunk_in_template71);
         this.chunk();
         --this.state._fsp;
         if (((template_scope)this.template_stack.peek()).state.stringtable != null) {
            impl.strings = ((template_scope)this.template_stack.peek()).state.stringtable.toArray();
         }

         impl.codeSize = ((template_scope)this.template_stack.peek()).state.ip;
      } catch (RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
      } finally {
         this.template_stack.pop();
      }

      return impl;
   }

   public final void chunk() throws RecognitionException {
      try {
         while(true) {
            try {
               int alt1 = 2;
               switch (this.input.LA(1)) {
                  case 4:
                  case 22:
                  case 32:
                  case 38:
                  case 55:
                  case 57:
                     alt1 = 1;
                  default:
                     switch (alt1) {
                        case 1:
                           this.pushFollow(FOLLOW_element_in_chunk86);
                           this.element();
                           --this.state._fsp;
                           continue;
                     }
               }
            } catch (RecognitionException re) {
               this.reportError(re);
               this.recover(this.input, re);
            }

            return;
         }
      } finally {
         ;
      }
   }

   public final void element() throws RecognitionException {
      CommonTree INDENT1 = null;
      CommonTree INDENT2 = null;

      try {
         try {
            int alt2;
            alt2 = 4;
            label77:
            switch (this.input.LA(1)) {
               case 4:
               case 55:
                  alt2 = 2;
                  break;
               case 22:
               case 32:
               case 38:
                  alt2 = 4;
                  break;
               case 57:
                  switch (this.input.LA(2)) {
                     case 2:
                        switch (this.input.LA(3)) {
                           case 31:
                              switch (this.input.LA(4)) {
                                 case 4:
                                 case 55:
                                    alt2 = 1;
                                    break label77;
                                 case 22:
                                 case 32:
                                 case 38:
                                    alt2 = 3;
                                    break label77;
                                 default:
                                    NoViableAltException nvae = new NoViableAltException("", 2, 5, this.input);
                                    throw nvae;
                              }
                           default:
                              NoViableAltException nvae = new NoViableAltException("", 2, 4, this.input);
                              throw nvae;
                        }
                     default:
                        NoViableAltException nvae = new NoViableAltException("", 2, 1, this.input);
                        throw nvae;
                  }
               default:
                  NoViableAltException nvae = new NoViableAltException("", 2, 0, this.input);
                  throw nvae;
            }

            switch (alt2) {
               case 1:
                  this.match(this.input, 57, FOLLOW_INDENTED_EXPR_in_element99);
                  this.match(this.input, 2, (BitSet)null);
                  INDENT1 = (CommonTree)this.match(this.input, 31, FOLLOW_INDENT_in_element101);
                  this.pushFollow(FOLLOW_compoundElement_in_element103);
                  this.compoundElement(INDENT1);
                  --this.state._fsp;
                  this.match(this.input, 3, (BitSet)null);
                  break;
               case 2:
                  this.pushFollow(FOLLOW_compoundElement_in_element111);
                  this.compoundElement((CommonTree)null);
                  --this.state._fsp;
                  break;
               case 3:
                  this.match(this.input, 57, FOLLOW_INDENTED_EXPR_in_element118);
                  this.match(this.input, 2, (BitSet)null);
                  INDENT2 = (CommonTree)this.match(this.input, 31, FOLLOW_INDENT_in_element120);
                  ((template_scope)this.template_stack.peek()).state.indent(INDENT2);
                  this.pushFollow(FOLLOW_singleElement_in_element124);
                  this.singleElement();
                  --this.state._fsp;
                  ((template_scope)this.template_stack.peek()).state.emit((short)40);
                  this.match(this.input, 3, (BitSet)null);
                  break;
               case 4:
                  this.pushFollow(FOLLOW_singleElement_in_element132);
                  this.singleElement();
                  --this.state._fsp;
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final void singleElement() throws RecognitionException {
      CommonTree TEXT3 = null;

      try {
         try {
            int alt3 = 3;
            switch (this.input.LA(1)) {
               case 22:
                  alt3 = 2;
                  break;
               case 32:
                  alt3 = 3;
                  break;
               case 38:
                  alt3 = 1;
                  break;
               default:
                  NoViableAltException nvae = new NoViableAltException("", 3, 0, this.input);
                  throw nvae;
            }

            switch (alt3) {
               case 1:
                  this.pushFollow(FOLLOW_exprElement_in_singleElement143);
                  this.exprElement();
                  --this.state._fsp;
                  break;
               case 2:
                  TEXT3 = (CommonTree)this.match(this.input, 22, FOLLOW_TEXT_in_singleElement148);
                  if ((TEXT3 != null ? TEXT3.getText() : null).length() > 0) {
                     this.emit1(TEXT3, (short)47, TEXT3 != null ? TEXT3.getText() : null);
                  }
                  break;
               case 3:
                  this.match(this.input, 32, FOLLOW_NEWLINE_in_singleElement158);
                  this.emit((short)41);
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final void compoundElement(CommonTree indent) throws RecognitionException {
      try {
         try {
            int alt4 = 2;
            switch (this.input.LA(1)) {
               case 4:
                  alt4 = 1;
                  break;
               case 55:
                  alt4 = 2;
                  break;
               default:
                  NoViableAltException nvae = new NoViableAltException("", 4, 0, this.input);
                  throw nvae;
            }

            switch (alt4) {
               case 1:
                  this.pushFollow(FOLLOW_ifstat_in_compoundElement172);
                  this.ifstat(indent);
                  --this.state._fsp;
                  break;
               case 2:
                  this.pushFollow(FOLLOW_region_in_compoundElement178);
                  this.region(indent);
                  --this.state._fsp;
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final void exprElement() throws RecognitionException {
      CommonTree EXPR4 = null;
      short op = 13;

      try {
         try {
            EXPR4 = (CommonTree)this.match(this.input, 38, FOLLOW_EXPR_in_exprElement197);
            this.match(this.input, 2, (BitSet)null);
            this.pushFollow(FOLLOW_expr_in_exprElement199);
            this.expr();
            --this.state._fsp;
            int alt5 = 2;
            switch (this.input.LA(1)) {
               case 39:
                  alt5 = 1;
               default:
                  switch (alt5) {
                     case 1:
                        this.pushFollow(FOLLOW_exprOptions_in_exprElement202);
                        this.exprOptions();
                        --this.state._fsp;
                        op = 14;
                     default:
                        this.match(this.input, 3, (BitSet)null);
                        this.emit(EXPR4, op);
                  }
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final region_return region(CommonTree indent) throws RecognitionException {
      region_return retval = new region_return();
      retval.start = this.input.LT(1);
      CommonTree ID5 = null;
      CompiledST template6 = null;
      if (indent != null) {
         ((template_scope)this.template_stack.peek()).state.indent(indent);
      }

      try {
         try {
            this.match(this.input, 55, FOLLOW_REGION_in_region240);
            this.match(this.input, 2, (BitSet)null);
            ID5 = (CommonTree)this.match(this.input, 25, FOLLOW_ID_in_region242);
            retval.name = STGroup.getMangledRegionName(this.outermostTemplateName, ID5 != null ? ID5.getText() : null);
            this.pushFollow(FOLLOW_template_in_region252);
            template6 = this.template(retval.name, (List)null);
            --this.state._fsp;
            template6.isRegion = true;
            template6.regionDefType = ST.RegionType.EMBEDDED;
            template6.templateDefStartToken = ID5.token;
            this.outermostImpl.addImplicitlyDefinedTemplate(template6);
            this.emit2((CommonTree)retval.start, (short)8, retval.name, 0);
            this.emit((CommonTree)retval.start, (short)13);
            this.match(this.input, 3, (BitSet)null);
            if (indent != null) {
               ((template_scope)this.template_stack.peek()).state.emit((short)40);
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

         return retval;
      } finally {
         ;
      }
   }

   public final subtemplate_return subtemplate() throws RecognitionException {
      subtemplate_return retval = new subtemplate_return();
      retval.start = this.input.LT(1);
      CommonTree ID7 = null;
      CommonTree SUBTEMPLATE9 = null;
      CommonTree SUBTEMPLATE10 = null;
      CompiledST template8 = null;
      retval.name = Compiler.getNewSubtemplateName();
      List<FormalArgument> args = new ArrayList();

      try {
         int alt8 = 2;
         switch (this.input.LA(1)) {
            case 52:
               switch (this.input.LA(2)) {
                  case 2:
                     alt8 = 1;
                     break;
                  case 3:
                  case 4:
                  case 5:
                  case 6:
                  case 10:
                  case 11:
                  case 12:
                  case 22:
                  case 25:
                  case 26:
                  case 29:
                  case 30:
                  case 32:
                  case 35:
                  case 36:
                  case 38:
                  case 39:
                  case 40:
                  case 41:
                  case 42:
                  case 43:
                  case 44:
                  case 45:
                  case 46:
                  case 47:
                  case 48:
                  case 49:
                  case 50:
                  case 51:
                  case 52:
                  case 55:
                  case 56:
                  case 57:
                     alt8 = 2;
                     break;
                  case 7:
                  case 8:
                  case 9:
                  case 13:
                  case 14:
                  case 15:
                  case 16:
                  case 17:
                  case 18:
                  case 19:
                  case 20:
                  case 21:
                  case 23:
                  case 24:
                  case 27:
                  case 28:
                  case 31:
                  case 33:
                  case 34:
                  case 37:
                  case 53:
                  case 54:
                  default:
                     NoViableAltException nvae = new NoViableAltException("", 8, 1, this.input);
                     throw nvae;
               }

               switch (alt8) {
                  case 1:
                     SUBTEMPLATE9 = (CommonTree)this.match(this.input, 52, FOLLOW_SUBTEMPLATE_in_subtemplate285);
                     if (this.input.LA(1) == 2) {
                        this.match(this.input, 2, (BitSet)null);

                        label140:
                        while(true) {
                           int alt7 = 2;
                           switch (this.input.LA(1)) {
                              case 53:
                                 alt7 = 1;
                           }

                           switch (alt7) {
                              case 1:
                                 this.match(this.input, 53, FOLLOW_ARGS_in_subtemplate292);
                                 this.match(this.input, 2, (BitSet)null);
                                 int cnt6 = 0;

                                 while(true) {
                                    int alt6 = 2;
                                    switch (this.input.LA(1)) {
                                       case 25:
                                          alt6 = 1;
                                    }

                                    switch (alt6) {
                                       case 1:
                                          ID7 = (CommonTree)this.match(this.input, 25, FOLLOW_ID_in_subtemplate295);
                                          args.add(new FormalArgument(ID7 != null ? ID7.getText() : null));
                                          ++cnt6;
                                          break;
                                       default:
                                          if (cnt6 < 1) {
                                             EarlyExitException eee = new EarlyExitException(6, this.input);
                                             throw eee;
                                          }

                                          this.match(this.input, 3, (BitSet)null);
                                          continue label140;
                                    }
                                 }
                              default:
                                 retval.nargs = args.size();
                                 this.pushFollow(FOLLOW_template_in_subtemplate312);
                                 template8 = this.template(retval.name, args);
                                 --this.state._fsp;
                                 template8.isAnonSubtemplate = true;
                                 template8.templateDefStartToken = SUBTEMPLATE9.token;
                                 template8.ast = SUBTEMPLATE9;
                                 template8.ast.setUnknownTokenBoundaries();
                                 template8.tokens = this.input.getTokenStream();
                                 this.outermostImpl.addImplicitlyDefinedTemplate(template8);
                                 this.match(this.input, 3, (BitSet)null);
                                 return retval;
                           }
                        }
                     }

                     return retval;
                  case 2:
                     SUBTEMPLATE10 = (CommonTree)this.match(this.input, 52, FOLLOW_SUBTEMPLATE_in_subtemplate328);
                     CompiledST sub = new CompiledST();
                     sub.name = retval.name;
                     sub.template = "";
                     sub.addArg(new FormalArgument("i"));
                     sub.addArg(new FormalArgument("i0"));
                     sub.isAnonSubtemplate = true;
                     sub.templateDefStartToken = SUBTEMPLATE10.token;
                     sub.ast = SUBTEMPLATE10;
                     sub.ast.setUnknownTokenBoundaries();
                     sub.tokens = this.input.getTokenStream();
                     this.outermostImpl.addImplicitlyDefinedTemplate(sub);
                     return retval;
                  default:
                     return retval;
               }
            default:
               NoViableAltException nvae = new NoViableAltException("", 8, 0, this.input);
               throw nvae;
         }
      } catch (RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         return retval;
      } finally {
         ;
      }
   }

   public final void ifstat(CommonTree indent) throws RecognitionException {
      CommonTree i = null;
      CommonTree eif = null;
      CommonTree el = null;
      conditional_return ec = null;
      int prevBranchOperand = -1;
      List<Integer> endRefs = new ArrayList();
      if (indent != null) {
         ((template_scope)this.template_stack.peek()).state.indent(indent);
      }

      try {
         i = (CommonTree)this.match(this.input, 4, FOLLOW_IF_in_ifstat360);
         this.match(this.input, 2, (BitSet)null);
         this.pushFollow(FOLLOW_conditional_in_ifstat362);
         this.conditional();
         --this.state._fsp;
         prevBranchOperand = this.address() + 1;
         this.emit1(i, (short)19, -1);
         this.pushFollow(FOLLOW_chunk_in_ifstat372);
         this.chunk();
         --this.state._fsp;

         while(true) {
            int alt9 = 2;
            switch (this.input.LA(1)) {
               case 6:
                  alt9 = 1;
            }

            switch (alt9) {
               case 1:
                  eif = (CommonTree)this.match(this.input, 6, FOLLOW_ELSEIF_in_ifstat382);
                  endRefs.add(this.address() + 1);
                  this.emit1(eif, (short)18, -1);
                  this.write(prevBranchOperand, (short)this.address());
                  prevBranchOperand = -1;
                  this.match(this.input, 2, (BitSet)null);
                  this.pushFollow(FOLLOW_conditional_in_ifstat396);
                  ec = this.conditional();
                  --this.state._fsp;
                  prevBranchOperand = this.address() + 1;
                  this.emit1(ec != null ? (CommonTree)ec.start : null, (short)19, -1);
                  this.pushFollow(FOLLOW_chunk_in_ifstat408);
                  this.chunk();
                  --this.state._fsp;
                  this.match(this.input, 3, (BitSet)null);
                  break;
               default:
                  alt9 = 2;
                  switch (this.input.LA(1)) {
                     case 5:
                        alt9 = 1;
                     default:
                        switch (alt9) {
                           case 1:
                              el = (CommonTree)this.match(this.input, 5, FOLLOW_ELSE_in_ifstat431);
                              endRefs.add(this.address() + 1);
                              this.emit1(el, (short)18, -1);
                              this.write(prevBranchOperand, (short)this.address());
                              prevBranchOperand = -1;
                              if (this.input.LA(1) == 2) {
                                 this.match(this.input, 2, (BitSet)null);
                                 this.pushFollow(FOLLOW_chunk_in_ifstat445);
                                 this.chunk();
                                 --this.state._fsp;
                                 this.match(this.input, 3, (BitSet)null);
                              }
                           default:
                              this.match(this.input, 3, (BitSet)null);
                              if (prevBranchOperand >= 0) {
                                 this.write(prevBranchOperand, (short)this.address());
                              }

                              for(int opnd : endRefs) {
                                 this.write(opnd, (short)this.address());
                              }

                              if (indent != null) {
                                 ((template_scope)this.template_stack.peek()).state.emit((short)40);
                              }

                              return;
                        }
                  }
            }
         }
      } catch (RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
      } finally {
         ;
      }
   }

   public final conditional_return conditional() throws RecognitionException {
      conditional_return retval = new conditional_return();
      retval.start = this.input.LT(1);

      try {
         try {
            int alt11 = 4;
            switch (this.input.LA(1)) {
               case 10:
                  alt11 = 3;
                  break;
               case 11:
               case 12:
               case 13:
               case 14:
               case 15:
               case 16:
               case 17:
               case 18:
               case 19:
               case 20:
               case 21:
               case 22:
               case 23:
               case 24:
               case 27:
               case 28:
               case 31:
               case 32:
               case 33:
               case 34:
               case 37:
               case 38:
               case 39:
               default:
                  NoViableAltException nvae = new NoViableAltException("", 11, 0, this.input);
                  throw nvae;
               case 25:
               case 26:
               case 35:
               case 36:
               case 40:
               case 41:
               case 42:
               case 43:
               case 44:
               case 45:
               case 46:
               case 47:
               case 48:
               case 49:
               case 50:
               case 51:
               case 52:
                  alt11 = 4;
                  break;
               case 29:
                  alt11 = 1;
                  break;
               case 30:
                  alt11 = 2;
            }

            switch (alt11) {
               case 1:
                  this.match(this.input, 29, FOLLOW_OR_in_conditional479);
                  this.match(this.input, 2, (BitSet)null);
                  this.pushFollow(FOLLOW_conditional_in_conditional481);
                  this.conditional();
                  --this.state._fsp;
                  this.pushFollow(FOLLOW_conditional_in_conditional483);
                  this.conditional();
                  --this.state._fsp;
                  this.match(this.input, 3, (BitSet)null);
                  this.emit((short)37);
                  break;
               case 2:
                  this.match(this.input, 30, FOLLOW_AND_in_conditional493);
                  this.match(this.input, 2, (BitSet)null);
                  this.pushFollow(FOLLOW_conditional_in_conditional495);
                  this.conditional();
                  --this.state._fsp;
                  this.pushFollow(FOLLOW_conditional_in_conditional497);
                  this.conditional();
                  --this.state._fsp;
                  this.match(this.input, 3, (BitSet)null);
                  this.emit((short)38);
                  break;
               case 3:
                  this.match(this.input, 10, FOLLOW_BANG_in_conditional507);
                  this.match(this.input, 2, (BitSet)null);
                  this.pushFollow(FOLLOW_conditional_in_conditional509);
                  this.conditional();
                  --this.state._fsp;
                  this.match(this.input, 3, (BitSet)null);
                  this.emit((short)36);
                  break;
               case 4:
                  this.pushFollow(FOLLOW_expr_in_conditional521);
                  this.expr();
                  --this.state._fsp;
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

         return retval;
      } finally {
         ;
      }
   }

   public final void exprOptions() throws RecognitionException {
      try {
         try {
            this.emit((short)20);
            this.match(this.input, 39, FOLLOW_OPTIONS_in_exprOptions535);
            if (this.input.LA(1) == 2) {
               this.match(this.input, 2, (BitSet)null);

               while(true) {
                  int alt12 = 2;
                  switch (this.input.LA(1)) {
                     case 12:
                        alt12 = 1;
                  }

                  switch (alt12) {
                     case 1:
                        this.pushFollow(FOLLOW_option_in_exprOptions537);
                        this.option();
                        --this.state._fsp;
                        break;
                     default:
                        this.match(this.input, 3, (BitSet)null);
                        return;
                  }
               }
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final void option() throws RecognitionException {
      CommonTree ID11 = null;

      try {
         try {
            this.match(this.input, 12, FOLLOW_EQUALS_in_option549);
            this.match(this.input, 2, (BitSet)null);
            ID11 = (CommonTree)this.match(this.input, 25, FOLLOW_ID_in_option551);
            this.pushFollow(FOLLOW_expr_in_option553);
            this.expr();
            --this.state._fsp;
            this.match(this.input, 3, (BitSet)null);
            this.setOption(ID11);
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final void expr() throws RecognitionException {
      CommonTree ZIP12 = null;
      CommonTree MAP13 = null;
      int nt = 0;
      int ne = 0;

      try {
         try {
            int alt15 = 4;
            switch (this.input.LA(1)) {
               case 25:
               case 26:
               case 35:
               case 36:
               case 42:
               case 43:
               case 44:
               case 45:
               case 46:
               case 47:
               case 48:
               case 49:
               case 52:
                  alt15 = 4;
                  break;
               case 27:
               case 28:
               case 29:
               case 30:
               case 31:
               case 32:
               case 33:
               case 34:
               case 37:
               case 38:
               case 39:
               default:
                  NoViableAltException nvae = new NoViableAltException("", 15, 0, this.input);
                  throw nvae;
               case 40:
               case 41:
                  alt15 = 3;
                  break;
               case 50:
                  alt15 = 2;
                  break;
               case 51:
                  alt15 = 1;
            }

            switch (alt15) {
               case 1:
                  ZIP12 = (CommonTree)this.match(this.input, 51, FOLLOW_ZIP_in_expr572);
                  this.match(this.input, 2, (BitSet)null);
                  this.match(this.input, 54, FOLLOW_ELEMENTS_in_expr575);
                  this.match(this.input, 2, (BitSet)null);
                  int cnt13 = 0;

                  while(true) {
                     int alt13 = 2;
                     switch (this.input.LA(1)) {
                        case 25:
                        case 26:
                        case 35:
                        case 36:
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                        case 44:
                        case 45:
                        case 46:
                        case 47:
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                           alt13 = 1;
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 37:
                        case 38:
                        case 39:
                     }

                     switch (alt13) {
                        case 1:
                           this.pushFollow(FOLLOW_expr_in_expr578);
                           this.expr();
                           --this.state._fsp;
                           ++ne;
                           ++cnt13;
                           break;
                        default:
                           if (cnt13 < 1) {
                              EarlyExitException eee = new EarlyExitException(13, this.input);
                              throw eee;
                           }

                           this.match(this.input, 3, (BitSet)null);
                           this.pushFollow(FOLLOW_mapTemplateRef_in_expr585);
                           this.mapTemplateRef(ne);
                           --this.state._fsp;
                           this.match(this.input, 3, (BitSet)null);
                           this.emit1(ZIP12, (short)17, ne);
                           return;
                     }
                  }
               case 2:
                  MAP13 = (CommonTree)this.match(this.input, 50, FOLLOW_MAP_in_expr597);
                  this.match(this.input, 2, (BitSet)null);
                  this.pushFollow(FOLLOW_expr_in_expr599);
                  this.expr();
                  --this.state._fsp;
                  int cnt14 = 0;

                  while(true) {
                     int alt14 = 2;
                     switch (this.input.LA(1)) {
                        case 42:
                        case 43:
                        case 52:
                           alt14 = 1;
                     }

                     switch (alt14) {
                        case 1:
                           this.pushFollow(FOLLOW_mapTemplateRef_in_expr602);
                           this.mapTemplateRef(1);
                           --this.state._fsp;
                           ++nt;
                           ++cnt14;
                           break;
                        default:
                           if (cnt14 < 1) {
                              EarlyExitException eee = new EarlyExitException(14, this.input);
                              throw eee;
                           }

                           this.match(this.input, 3, (BitSet)null);
                           if (nt > 1) {
                              this.emit1(MAP13, (short)(nt > 1 ? 16 : 15), nt);
                           } else {
                              this.emit(MAP13, (short)15);
                           }

                           return;
                     }
                  }
               case 3:
                  this.pushFollow(FOLLOW_prop_in_expr617);
                  this.prop();
                  --this.state._fsp;
                  break;
               case 4:
                  this.pushFollow(FOLLOW_includeExpr_in_expr622);
                  this.includeExpr();
                  --this.state._fsp;
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final void prop() throws RecognitionException {
      CommonTree PROP14 = null;
      CommonTree ID15 = null;
      CommonTree PROP_IND16 = null;

      try {
         try {
            int alt16 = 2;
            switch (this.input.LA(1)) {
               case 40:
                  alt16 = 1;
                  break;
               case 41:
                  alt16 = 2;
                  break;
               default:
                  NoViableAltException nvae = new NoViableAltException("", 16, 0, this.input);
                  throw nvae;
            }

            switch (alt16) {
               case 1:
                  PROP14 = (CommonTree)this.match(this.input, 40, FOLLOW_PROP_in_prop632);
                  this.match(this.input, 2, (BitSet)null);
                  this.pushFollow(FOLLOW_expr_in_prop634);
                  this.expr();
                  --this.state._fsp;
                  ID15 = (CommonTree)this.match(this.input, 25, FOLLOW_ID_in_prop636);
                  this.match(this.input, 3, (BitSet)null);
                  this.emit1(PROP14, (short)4, ID15 != null ? ID15.getText() : null);
                  break;
               case 2:
                  PROP_IND16 = (CommonTree)this.match(this.input, 41, FOLLOW_PROP_IND_in_prop650);
                  this.match(this.input, 2, (BitSet)null);
                  this.pushFollow(FOLLOW_expr_in_prop652);
                  this.expr();
                  --this.state._fsp;
                  this.pushFollow(FOLLOW_expr_in_prop654);
                  this.expr();
                  --this.state._fsp;
                  this.match(this.input, 3, (BitSet)null);
                  this.emit(PROP_IND16, (short)5);
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final mapTemplateRef_return mapTemplateRef(int num_exprs) throws RecognitionException {
      mapTemplateRef_return retval = new mapTemplateRef_return();
      retval.start = this.input.LT(1);
      CommonTree INCLUDE17 = null;
      CommonTree ID19 = null;
      CommonTree INCLUDE_IND21 = null;
      args_return args18 = null;
      subtemplate_return subtemplate20 = null;
      args_return args22 = null;

      try {
         try {
            int alt17 = 3;
            switch (this.input.LA(1)) {
               case 42:
                  alt17 = 1;
                  break;
               case 43:
                  alt17 = 3;
                  break;
               case 52:
                  alt17 = 2;
                  break;
               default:
                  NoViableAltException nvae = new NoViableAltException("", 17, 0, this.input);
                  throw nvae;
            }

            switch (alt17) {
               case 1:
                  INCLUDE17 = (CommonTree)this.match(this.input, 42, FOLLOW_INCLUDE_in_mapTemplateRef674);
                  this.match(this.input, 2, (BitSet)null);
                  ID19 = (CommonTree)this.match(this.input, 25, FOLLOW_ID_in_mapTemplateRef676);

                  for(int i = 1; i <= num_exprs; ++i) {
                     this.emit(INCLUDE17, (short)44);
                  }

                  this.pushFollow(FOLLOW_args_in_mapTemplateRef686);
                  args18 = this.args();
                  --this.state._fsp;
                  this.match(this.input, 3, (BitSet)null);
                  if (args18 != null && args18.passThru) {
                     this.emit1((CommonTree)retval.start, (short)22, ID19 != null ? ID19.getText() : null);
                  }

                  if (args18 != null && args18.namedArgs) {
                     this.emit1(INCLUDE17, (short)10, ID19 != null ? ID19.getText() : null);
                  } else {
                     this.emit2(INCLUDE17, (short)8, ID19 != null ? ID19.getText() : null, (args18 != null ? args18.n : 0) + num_exprs);
                  }
                  break;
               case 2:
                  this.pushFollow(FOLLOW_subtemplate_in_mapTemplateRef699);
                  subtemplate20 = this.subtemplate();
                  --this.state._fsp;
                  if ((subtemplate20 != null ? subtemplate20.nargs : 0) != num_exprs) {
                     this.errMgr.compileTimeError(ErrorType.ANON_ARGUMENT_MISMATCH, this.templateToken, (subtemplate20 != null ? (CommonTree)subtemplate20.start : null).token, subtemplate20 != null ? subtemplate20.nargs : 0, num_exprs);
                  }

                  for(int i = 1; i <= num_exprs; ++i) {
                     this.emit(subtemplate20 != null ? (CommonTree)subtemplate20.start : null, (short)44);
                  }

                  this.emit2(subtemplate20 != null ? (CommonTree)subtemplate20.start : null, (short)8, subtemplate20 != null ? subtemplate20.name : null, num_exprs);
                  break;
               case 3:
                  INCLUDE_IND21 = (CommonTree)this.match(this.input, 43, FOLLOW_INCLUDE_IND_in_mapTemplateRef711);
                  this.match(this.input, 2, (BitSet)null);
                  this.pushFollow(FOLLOW_expr_in_mapTemplateRef713);
                  this.expr();
                  --this.state._fsp;
                  this.emit(INCLUDE_IND21, (short)26);

                  for(int i = 1; i <= num_exprs; ++i) {
                     this.emit(INCLUDE_IND21, (short)44);
                  }

                  this.pushFollow(FOLLOW_args_in_mapTemplateRef723);
                  args22 = this.args();
                  --this.state._fsp;
                  this.emit1(INCLUDE_IND21, (short)9, (args22 != null ? args22.n : 0) + num_exprs);
                  this.match(this.input, 3, (BitSet)null);
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

         return retval;
      } finally {
         ;
      }
   }

   public final includeExpr_return includeExpr() throws RecognitionException {
      includeExpr_return retval = new includeExpr_return();
      retval.start = this.input.LT(1);
      CommonTree ID23 = null;
      CommonTree ID25 = null;
      CommonTree INCLUDE26 = null;
      CommonTree ID28 = null;
      CommonTree INCLUDE_SUPER29 = null;
      CommonTree ID30 = null;
      CommonTree INCLUDE_REGION31 = null;
      CommonTree ID32 = null;
      CommonTree INCLUDE_SUPER_REGION33 = null;
      args_return args24 = null;
      args_return args27 = null;

      try {
         try {
            int alt19 = 6;
            switch (this.input.LA(1)) {
               case 25:
               case 26:
               case 35:
               case 36:
               case 43:
               case 48:
               case 49:
               case 52:
                  alt19 = 6;
                  break;
               case 27:
               case 28:
               case 29:
               case 30:
               case 31:
               case 32:
               case 33:
               case 34:
               case 37:
               case 38:
               case 39:
               case 40:
               case 41:
               case 50:
               case 51:
               default:
                  NoViableAltException nvae = new NoViableAltException("", 19, 0, this.input);
                  throw nvae;
               case 42:
                  alt19 = 2;
                  break;
               case 44:
                  alt19 = 1;
                  break;
               case 45:
                  alt19 = 3;
                  break;
               case 46:
                  alt19 = 5;
                  break;
               case 47:
                  alt19 = 4;
            }

            switch (alt19) {
               case 1:
                  this.match(this.input, 44, FOLLOW_EXEC_FUNC_in_includeExpr745);
                  this.match(this.input, 2, (BitSet)null);
                  ID23 = (CommonTree)this.match(this.input, 25, FOLLOW_ID_in_includeExpr747);
                  int alt18 = 2;
                  switch (this.input.LA(1)) {
                     case 25:
                     case 26:
                     case 35:
                     case 36:
                     case 40:
                     case 41:
                     case 42:
                     case 43:
                     case 44:
                     case 45:
                     case 46:
                     case 47:
                     case 48:
                     case 49:
                     case 50:
                     case 51:
                     case 52:
                        alt18 = 1;
                     case 27:
                     case 28:
                     case 29:
                     case 30:
                     case 31:
                     case 32:
                     case 33:
                     case 34:
                     case 37:
                     case 38:
                     case 39:
                     default:
                        switch (alt18) {
                           case 1:
                              this.pushFollow(FOLLOW_expr_in_includeExpr749);
                              this.expr();
                              --this.state._fsp;
                           default:
                              this.match(this.input, 3, (BitSet)null);
                              this.func(ID23);
                              return retval;
                        }
                  }
               case 2:
                  INCLUDE26 = (CommonTree)this.match(this.input, 42, FOLLOW_INCLUDE_in_includeExpr760);
                  this.match(this.input, 2, (BitSet)null);
                  ID25 = (CommonTree)this.match(this.input, 25, FOLLOW_ID_in_includeExpr762);
                  this.pushFollow(FOLLOW_args_in_includeExpr764);
                  args24 = this.args();
                  --this.state._fsp;
                  this.match(this.input, 3, (BitSet)null);
                  if (args24 != null && args24.passThru) {
                     this.emit1((CommonTree)retval.start, (short)22, ID25 != null ? ID25.getText() : null);
                  }

                  if (args24 != null && args24.namedArgs) {
                     this.emit1(INCLUDE26, (short)10, ID25 != null ? ID25.getText() : null);
                  } else {
                     this.emit2(INCLUDE26, (short)8, ID25 != null ? ID25.getText() : null, args24 != null ? args24.n : 0);
                  }
                  break;
               case 3:
                  INCLUDE_SUPER29 = (CommonTree)this.match(this.input, 45, FOLLOW_INCLUDE_SUPER_in_includeExpr775);
                  this.match(this.input, 2, (BitSet)null);
                  ID28 = (CommonTree)this.match(this.input, 25, FOLLOW_ID_in_includeExpr777);
                  this.pushFollow(FOLLOW_args_in_includeExpr779);
                  args27 = this.args();
                  --this.state._fsp;
                  this.match(this.input, 3, (BitSet)null);
                  if (args27 != null && args27.passThru) {
                     this.emit1((CommonTree)retval.start, (short)22, ID28 != null ? ID28.getText() : null);
                  }

                  if (args27 != null && args27.namedArgs) {
                     this.emit1(INCLUDE_SUPER29, (short)12, ID28 != null ? ID28.getText() : null);
                  } else {
                     this.emit2(INCLUDE_SUPER29, (short)11, ID28 != null ? ID28.getText() : null, args27 != null ? args27.n : 0);
                  }
                  break;
               case 4:
                  INCLUDE_REGION31 = (CommonTree)this.match(this.input, 47, FOLLOW_INCLUDE_REGION_in_includeExpr790);
                  this.match(this.input, 2, (BitSet)null);
                  ID30 = (CommonTree)this.match(this.input, 25, FOLLOW_ID_in_includeExpr792);
                  this.match(this.input, 3, (BitSet)null);
                  CompiledST impl = Compiler.defineBlankRegion(this.outermostImpl, ID30.token);
                  this.emit2(INCLUDE_REGION31, (short)8, impl.name, 0);
                  break;
               case 5:
                  INCLUDE_SUPER_REGION33 = (CommonTree)this.match(this.input, 46, FOLLOW_INCLUDE_SUPER_REGION_in_includeExpr802);
                  this.match(this.input, 2, (BitSet)null);
                  ID32 = (CommonTree)this.match(this.input, 25, FOLLOW_ID_in_includeExpr804);
                  this.match(this.input, 3, (BitSet)null);
                  String mangled = STGroup.getMangledRegionName(this.outermostImpl.name, ID32 != null ? ID32.getText() : null);
                  this.emit2(INCLUDE_SUPER_REGION33, (short)11, mangled, 0);
                  break;
               case 6:
                  this.pushFollow(FOLLOW_primary_in_includeExpr812);
                  this.primary();
                  --this.state._fsp;
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

         return retval;
      } finally {
         ;
      }
   }

   public final primary_return primary() throws RecognitionException {
      primary_return retval = new primary_return();
      retval.start = this.input.LT(1);
      CommonTree ID34 = null;
      CommonTree STRING35 = null;
      CommonTree TRUE36 = null;
      CommonTree FALSE37 = null;
      CommonTree INCLUDE_IND39 = null;
      CommonTree TO_STR41 = null;
      subtemplate_return subtemplate38 = null;
      args_return args40 = null;

      try {
         try {
            int alt20 = 8;
            switch (this.input.LA(1)) {
               case 25:
                  alt20 = 1;
                  break;
               case 26:
                  alt20 = 2;
                  break;
               case 27:
               case 28:
               case 29:
               case 30:
               case 31:
               case 32:
               case 33:
               case 34:
               case 37:
               case 38:
               case 39:
               case 40:
               case 41:
               case 42:
               case 44:
               case 45:
               case 46:
               case 47:
               case 50:
               case 51:
               default:
                  NoViableAltException nvae = new NoViableAltException("", 20, 0, this.input);
                  throw nvae;
               case 35:
                  alt20 = 3;
                  break;
               case 36:
                  alt20 = 4;
                  break;
               case 43:
                  alt20 = 7;
                  break;
               case 48:
                  alt20 = 8;
                  break;
               case 49:
                  alt20 = 6;
                  break;
               case 52:
                  alt20 = 5;
            }

            switch (alt20) {
               case 1:
                  ID34 = (CommonTree)this.match(this.input, 25, FOLLOW_ID_in_primary823);
                  this.refAttr(ID34);
                  break;
               case 2:
                  STRING35 = (CommonTree)this.match(this.input, 26, FOLLOW_STRING_in_primary833);
                  this.emit1(STRING35, (short)1, Misc.strip(STRING35 != null ? STRING35.getText() : null, 1));
                  break;
               case 3:
                  TRUE36 = (CommonTree)this.match(this.input, 35, FOLLOW_TRUE_in_primary842);
                  this.emit(TRUE36, (short)45);
                  break;
               case 4:
                  FALSE37 = (CommonTree)this.match(this.input, 36, FOLLOW_FALSE_in_primary851);
                  this.emit(FALSE37, (short)46);
                  break;
               case 5:
                  this.pushFollow(FOLLOW_subtemplate_in_primary860);
                  subtemplate38 = this.subtemplate();
                  --this.state._fsp;
                  this.emit2((CommonTree)retval.start, (short)8, subtemplate38 != null ? subtemplate38.name : null, 0);
                  break;
               case 6:
                  this.pushFollow(FOLLOW_list_in_primary887);
                  this.list();
                  --this.state._fsp;
                  break;
               case 7:
                  INCLUDE_IND39 = (CommonTree)this.match(this.input, 43, FOLLOW_INCLUDE_IND_in_primary894);
                  this.match(this.input, 2, (BitSet)null);
                  this.pushFollow(FOLLOW_expr_in_primary899);
                  this.expr();
                  --this.state._fsp;
                  this.emit(INCLUDE_IND39, (short)26);
                  this.pushFollow(FOLLOW_args_in_primary908);
                  args40 = this.args();
                  --this.state._fsp;
                  this.emit1(INCLUDE_IND39, (short)9, args40 != null ? args40.n : 0);
                  this.match(this.input, 3, (BitSet)null);
                  break;
               case 8:
                  TO_STR41 = (CommonTree)this.match(this.input, 48, FOLLOW_TO_STR_in_primary928);
                  this.match(this.input, 2, (BitSet)null);
                  this.pushFollow(FOLLOW_expr_in_primary930);
                  this.expr();
                  --this.state._fsp;
                  this.match(this.input, 3, (BitSet)null);
                  this.emit(TO_STR41, (short)26);
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

         return retval;
      } finally {
         ;
      }
   }

   public final void arg() throws RecognitionException {
      try {
         try {
            this.pushFollow(FOLLOW_expr_in_arg943);
            this.expr();
            --this.state._fsp;
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final args_return args() throws RecognitionException {
      args_return retval = new args_return();
      retval.start = this.input.LT(1);
      CommonTree eq = null;
      CommonTree ID42 = null;

      try {
         try {
            int alt24 = 4;
            switch (this.input.LA(1)) {
               case 3:
                  alt24 = 4;
                  break;
               case 4:
               case 5:
               case 6:
               case 7:
               case 8:
               case 9:
               case 10:
               case 13:
               case 14:
               case 15:
               case 16:
               case 17:
               case 18:
               case 19:
               case 20:
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
               case 33:
               case 34:
               case 37:
               case 38:
               case 39:
               default:
                  NoViableAltException nvae = new NoViableAltException("", 24, 0, this.input);
                  throw nvae;
               case 11:
                  alt24 = 3;
                  break;
               case 12:
                  alt24 = 2;
                  break;
               case 25:
               case 26:
               case 35:
               case 36:
               case 40:
               case 41:
               case 42:
               case 43:
               case 44:
               case 45:
               case 46:
               case 47:
               case 48:
               case 49:
               case 50:
               case 51:
               case 52:
                  alt24 = 1;
            }

            switch (alt24) {
               case 1:
                  int cnt21 = 0;

                  while(true) {
                     int alt21 = 2;
                     switch (this.input.LA(1)) {
                        case 25:
                        case 26:
                        case 35:
                        case 36:
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                        case 44:
                        case 45:
                        case 46:
                        case 47:
                        case 48:
                        case 49:
                        case 50:
                        case 51:
                        case 52:
                           alt21 = 1;
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 37:
                        case 38:
                        case 39:
                     }

                     switch (alt21) {
                        case 1:
                           this.pushFollow(FOLLOW_arg_in_args959);
                           this.arg();
                           --this.state._fsp;
                           ++retval.n;
                           ++cnt21;
                           break;
                        default:
                           if (cnt21 < 1) {
                              EarlyExitException eee = new EarlyExitException(21, this.input);
                              throw eee;
                           }

                           return retval;
                     }
                  }
               case 2:
                  this.emit((CommonTree)retval.start, (short)21);
                  retval.namedArgs = true;
                  int cnt22 = 0;

                  while(true) {
                     int alt22 = 2;
                     switch (this.input.LA(1)) {
                        case 12:
                           alt22 = 1;
                     }

                     switch (alt22) {
                        case 1:
                           eq = (CommonTree)this.match(this.input, 12, FOLLOW_EQUALS_in_args978);
                           this.match(this.input, 2, (BitSet)null);
                           ID42 = (CommonTree)this.match(this.input, 25, FOLLOW_ID_in_args980);
                           this.pushFollow(FOLLOW_expr_in_args982);
                           this.expr();
                           --this.state._fsp;
                           this.match(this.input, 3, (BitSet)null);
                           ++retval.n;
                           this.emit1(eq, (short)7, this.defineString(ID42 != null ? ID42.getText() : null));
                           ++cnt22;
                           break;
                        default:
                           if (cnt22 < 1) {
                              EarlyExitException eee = new EarlyExitException(22, this.input);
                              throw eee;
                           }

                           alt22 = 2;
                           switch (this.input.LA(1)) {
                              case 11:
                                 alt22 = 1;
                              default:
                                 switch (alt22) {
                                    case 1:
                                       this.match(this.input, 11, FOLLOW_ELLIPSIS_in_args999);
                                       retval.passThru = true;
                                       return retval;
                                    default:
                                       return retval;
                                 }
                           }
                     }
                  }
               case 3:
                  this.match(this.input, 11, FOLLOW_ELLIPSIS_in_args1014);
                  retval.passThru = true;
                  this.emit((CommonTree)retval.start, (short)21);
                  retval.namedArgs = true;
               case 4:
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

         return retval;
      } finally {
         ;
      }
   }

   public final void list() throws RecognitionException {
      listElement_return listElement43 = null;

      try {
         try {
            this.emit((short)24);
            this.match(this.input, 49, FOLLOW_LIST_in_list1034);
            if (this.input.LA(1) == 2) {
               this.match(this.input, 2, (BitSet)null);

               while(true) {
                  int alt25 = 2;
                  switch (this.input.LA(1)) {
                     case 25:
                     case 26:
                     case 35:
                     case 36:
                     case 40:
                     case 41:
                     case 42:
                     case 43:
                     case 44:
                     case 45:
                     case 46:
                     case 47:
                     case 48:
                     case 49:
                     case 50:
                     case 51:
                     case 52:
                     case 56:
                        alt25 = 1;
                     case 27:
                     case 28:
                     case 29:
                     case 30:
                     case 31:
                     case 32:
                     case 33:
                     case 34:
                     case 37:
                     case 38:
                     case 39:
                     case 53:
                     case 54:
                     case 55:
                  }

                  switch (alt25) {
                     case 1:
                        this.pushFollow(FOLLOW_listElement_in_list1037);
                        listElement43 = this.listElement();
                        --this.state._fsp;
                        this.emit(listElement43 != null ? (CommonTree)listElement43.start : null, (short)25);
                        break;
                     default:
                        this.match(this.input, 3, (BitSet)null);
                        return;
                  }
               }
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final listElement_return listElement() throws RecognitionException {
      listElement_return retval = new listElement_return();
      retval.start = this.input.LT(1);
      CommonTree NULL44 = null;

      try {
         try {
            int alt26 = 2;
            switch (this.input.LA(1)) {
               case 25:
               case 26:
               case 35:
               case 36:
               case 40:
               case 41:
               case 42:
               case 43:
               case 44:
               case 45:
               case 46:
               case 47:
               case 48:
               case 49:
               case 50:
               case 51:
               case 52:
                  alt26 = 1;
                  break;
               case 27:
               case 28:
               case 29:
               case 30:
               case 31:
               case 32:
               case 33:
               case 34:
               case 37:
               case 38:
               case 39:
               case 53:
               case 54:
               case 55:
               default:
                  NoViableAltException nvae = new NoViableAltException("", 26, 0, this.input);
                  throw nvae;
               case 56:
                  alt26 = 2;
            }

            switch (alt26) {
               case 1:
                  this.pushFollow(FOLLOW_expr_in_listElement1053);
                  this.expr();
                  --this.state._fsp;
                  break;
               case 2:
                  NULL44 = (CommonTree)this.match(this.input, 56, FOLLOW_NULL_in_listElement1057);
                  this.emit(NULL44, (short)44);
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

         return retval;
      } finally {
         ;
      }
   }

   protected static class template_scope {
      CompilationState state;
   }

   public static class region_return extends TreeRuleReturnScope {
      public String name;
   }

   public static class subtemplate_return extends TreeRuleReturnScope {
      public String name;
      public int nargs;
   }

   public static class conditional_return extends TreeRuleReturnScope {
   }

   public static class mapTemplateRef_return extends TreeRuleReturnScope {
   }

   public static class includeExpr_return extends TreeRuleReturnScope {
   }

   public static class primary_return extends TreeRuleReturnScope {
   }

   public static class args_return extends TreeRuleReturnScope {
      public int n = 0;
      public boolean namedArgs = false;
      public boolean passThru;
   }

   public static class listElement_return extends TreeRuleReturnScope {
   }
}
