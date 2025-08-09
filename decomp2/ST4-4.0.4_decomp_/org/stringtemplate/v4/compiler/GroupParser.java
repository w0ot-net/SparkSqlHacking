package org.stringtemplate.v4.compiler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.FailedPredicateException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.misc.ErrorType;
import org.stringtemplate.v4.misc.Misc;

public class GroupParser extends Parser {
   public static final String[] tokenNames = new String[]{"<invalid>", "<EOR>", "<DOWN>", "<UP>", "TRUE", "FALSE", "STRING", "ID", "BIGSTRING", "BIGSTRING_NO_NL", "ANONYMOUS_TEMPLATE", "COMMENT", "LINE_COMMENT", "WS", "'import'", "'.'", "'group'", "':'", "'implements'", "','", "';'", "'delimiters'", "'@'", "'('", "')'", "'::='", "'='", "'['", "']'", "'default'"};
   public static final int EOF = -1;
   public static final int T__14 = 14;
   public static final int T__15 = 15;
   public static final int T__16 = 16;
   public static final int T__17 = 17;
   public static final int T__18 = 18;
   public static final int T__19 = 19;
   public static final int T__20 = 20;
   public static final int T__21 = 21;
   public static final int T__22 = 22;
   public static final int T__23 = 23;
   public static final int T__24 = 24;
   public static final int T__25 = 25;
   public static final int T__26 = 26;
   public static final int T__27 = 27;
   public static final int T__28 = 28;
   public static final int T__29 = 29;
   public static final int TRUE = 4;
   public static final int FALSE = 5;
   public static final int STRING = 6;
   public static final int ID = 7;
   public static final int BIGSTRING = 8;
   public static final int BIGSTRING_NO_NL = 9;
   public static final int ANONYMOUS_TEMPLATE = 10;
   public static final int COMMENT = 11;
   public static final int LINE_COMMENT = 12;
   public static final int WS = 13;
   public STGroup group;
   protected Stack formalArgs_stack;
   public static final BitSet FOLLOW_oldStyleHeader_in_group65 = new BitSet(new long[]{6307968L});
   public static final BitSet FOLLOW_delimiters_in_group70 = new BitSet(new long[]{6307968L});
   public static final BitSet FOLLOW_14_in_group80 = new BitSet(new long[]{64L});
   public static final BitSet FOLLOW_STRING_in_group82 = new BitSet(new long[]{6307968L});
   public static final BitSet FOLLOW_14_in_group90 = new BitSet(new long[]{128L});
   public static final BitSet FOLLOW_ID_in_group101 = new BitSet(new long[]{6340736L});
   public static final BitSet FOLLOW_15_in_group104 = new BitSet(new long[]{128L});
   public static final BitSet FOLLOW_ID_in_group106 = new BitSet(new long[]{6340736L});
   public static final BitSet FOLLOW_def_in_group124 = new BitSet(new long[]{6307970L});
   public static final BitSet FOLLOW_16_in_oldStyleHeader146 = new BitSet(new long[]{128L});
   public static final BitSet FOLLOW_ID_in_oldStyleHeader148 = new BitSet(new long[]{1441792L});
   public static final BitSet FOLLOW_17_in_oldStyleHeader152 = new BitSet(new long[]{128L});
   public static final BitSet FOLLOW_ID_in_oldStyleHeader154 = new BitSet(new long[]{1310720L});
   public static final BitSet FOLLOW_18_in_oldStyleHeader166 = new BitSet(new long[]{128L});
   public static final BitSet FOLLOW_ID_in_oldStyleHeader168 = new BitSet(new long[]{1572864L});
   public static final BitSet FOLLOW_19_in_oldStyleHeader171 = new BitSet(new long[]{128L});
   public static final BitSet FOLLOW_ID_in_oldStyleHeader173 = new BitSet(new long[]{1572864L});
   public static final BitSet FOLLOW_20_in_oldStyleHeader185 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_ID_in_groupName207 = new BitSet(new long[]{32770L});
   public static final BitSet FOLLOW_15_in_groupName212 = new BitSet(new long[]{128L});
   public static final BitSet FOLLOW_ID_in_groupName216 = new BitSet(new long[]{32770L});
   public static final BitSet FOLLOW_21_in_delimiters234 = new BitSet(new long[]{64L});
   public static final BitSet FOLLOW_STRING_in_delimiters238 = new BitSet(new long[]{524288L});
   public static final BitSet FOLLOW_19_in_delimiters240 = new BitSet(new long[]{64L});
   public static final BitSet FOLLOW_STRING_in_delimiters244 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_templateDef_in_def268 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_dictDef_in_def273 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_22_in_templateDef297 = new BitSet(new long[]{128L});
   public static final BitSet FOLLOW_ID_in_templateDef301 = new BitSet(new long[]{32768L});
   public static final BitSet FOLLOW_15_in_templateDef303 = new BitSet(new long[]{128L});
   public static final BitSet FOLLOW_ID_in_templateDef307 = new BitSet(new long[]{8388608L});
   public static final BitSet FOLLOW_23_in_templateDef309 = new BitSet(new long[]{16777216L});
   public static final BitSet FOLLOW_24_in_templateDef311 = new BitSet(new long[]{33554432L});
   public static final BitSet FOLLOW_ID_in_templateDef319 = new BitSet(new long[]{8388608L});
   public static final BitSet FOLLOW_23_in_templateDef321 = new BitSet(new long[]{16777344L});
   public static final BitSet FOLLOW_formalArgs_in_templateDef323 = new BitSet(new long[]{16777216L});
   public static final BitSet FOLLOW_24_in_templateDef325 = new BitSet(new long[]{33554432L});
   public static final BitSet FOLLOW_25_in_templateDef336 = new BitSet(new long[]{834L});
   public static final BitSet FOLLOW_STRING_in_templateDef352 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_BIGSTRING_in_templateDef367 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_BIGSTRING_NO_NL_in_templateDef379 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_ID_in_templateDef414 = new BitSet(new long[]{33554432L});
   public static final BitSet FOLLOW_25_in_templateDef416 = new BitSet(new long[]{128L});
   public static final BitSet FOLLOW_ID_in_templateDef420 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_formalArg_in_formalArgs446 = new BitSet(new long[]{524290L});
   public static final BitSet FOLLOW_19_in_formalArgs450 = new BitSet(new long[]{128L});
   public static final BitSet FOLLOW_formalArg_in_formalArgs452 = new BitSet(new long[]{524290L});
   public static final BitSet FOLLOW_ID_in_formalArg470 = new BitSet(new long[]{67108866L});
   public static final BitSet FOLLOW_26_in_formalArg476 = new BitSet(new long[]{1136L});
   public static final BitSet FOLLOW_set_in_formalArg480 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_ID_in_dictDef521 = new BitSet(new long[]{33554432L});
   public static final BitSet FOLLOW_25_in_dictDef523 = new BitSet(new long[]{134217728L});
   public static final BitSet FOLLOW_dict_in_dictDef525 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_27_in_dict557 = new BitSet(new long[]{536870976L});
   public static final BitSet FOLLOW_dictPairs_in_dict559 = new BitSet(new long[]{268435456L});
   public static final BitSet FOLLOW_28_in_dict562 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_keyValuePair_in_dictPairs577 = new BitSet(new long[]{524290L});
   public static final BitSet FOLLOW_19_in_dictPairs586 = new BitSet(new long[]{64L});
   public static final BitSet FOLLOW_keyValuePair_in_dictPairs588 = new BitSet(new long[]{524290L});
   public static final BitSet FOLLOW_19_in_dictPairs594 = new BitSet(new long[]{536870976L});
   public static final BitSet FOLLOW_defaultValuePair_in_dictPairs596 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_defaultValuePair_in_dictPairs607 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_29_in_defaultValuePair630 = new BitSet(new long[]{131072L});
   public static final BitSet FOLLOW_17_in_defaultValuePair632 = new BitSet(new long[]{2032L});
   public static final BitSet FOLLOW_keyValue_in_defaultValuePair634 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_STRING_in_keyValuePair648 = new BitSet(new long[]{131072L});
   public static final BitSet FOLLOW_17_in_keyValuePair650 = new BitSet(new long[]{2032L});
   public static final BitSet FOLLOW_keyValue_in_keyValuePair652 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_BIGSTRING_in_keyValue669 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_BIGSTRING_NO_NL_in_keyValue678 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_ANONYMOUS_TEMPLATE_in_keyValue686 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_STRING_in_keyValue693 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_TRUE_in_keyValue703 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_FALSE_in_keyValue713 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_ID_in_keyValue726 = new BitSet(new long[]{2L});

   public GroupParser(TokenStream input) {
      this(input, new RecognizerSharedState());
   }

   public GroupParser(TokenStream input, RecognizerSharedState state) {
      super(input, state);
      this.formalArgs_stack = new Stack();
   }

   public String[] getTokenNames() {
      return tokenNames;
   }

   public String getGrammarFileName() {
      return "org/stringtemplate/v4/compiler/Group.g";
   }

   public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
      String msg = this.getErrorMessage(e, tokenNames);
      this.group.errMgr.groupSyntaxError(ErrorType.SYNTAX_ERROR, this.getSourceName(), e, msg);
   }

   public String getSourceName() {
      String fullFileName = super.getSourceName();
      File f = new File(fullFileName);
      return f.getName();
   }

   public void error(String msg) {
      NoViableAltException e = new NoViableAltException("", 0, 0, this.input);
      this.group.errMgr.groupSyntaxError(ErrorType.SYNTAX_ERROR, this.getSourceName(), e, msg);
      this.recover(this.input, (RecognitionException)null);
   }

   public final void group(STGroup group, String prefix) throws RecognitionException {
      Token STRING1 = null;
      GroupLexer lexer = (GroupLexer)this.input.getTokenSource();
      this.group = lexer.group = group;

      try {
         int alt1 = 2;
         switch (this.input.LA(1)) {
            case 16:
               alt1 = 1;
            default:
               switch (alt1) {
                  case 1:
                     this.pushFollow(FOLLOW_oldStyleHeader_in_group65);
                     this.oldStyleHeader();
                     --this.state._fsp;
                  default:
                     int alt2 = 2;
                     switch (this.input.LA(1)) {
                        case 21:
                           alt2 = 1;
                        default:
                           switch (alt2) {
                              case 1:
                                 this.pushFollow(FOLLOW_delimiters_in_group70);
                                 this.delimiters();
                                 --this.state._fsp;
                           }
                     }
               }
         }

         label178:
         while(true) {
            int alt4 = 3;
            switch (this.input.LA(1)) {
               case 14:
                  switch (this.input.LA(2)) {
                     case 6:
                        alt4 = 1;
                        break;
                     case 7:
                        alt4 = 2;
                  }
            }

            switch (alt4) {
               case 1:
                  this.match(this.input, 14, FOLLOW_14_in_group80);
                  STRING1 = (Token)this.match(this.input, 6, FOLLOW_STRING_in_group82);
                  group.importTemplates(STRING1);
                  break;
               case 2:
                  this.match(this.input, 14, FOLLOW_14_in_group90);
                  MismatchedTokenException e = new MismatchedTokenException(6, this.input);
                  this.reportError(e);
                  this.match(this.input, 7, FOLLOW_ID_in_group101);

                  while(true) {
                     int alt3 = 2;
                     switch (this.input.LA(1)) {
                        case 15:
                           alt3 = 1;
                     }

                     switch (alt3) {
                        case 1:
                           this.match(this.input, 15, FOLLOW_15_in_group104);
                           this.match(this.input, 7, FOLLOW_ID_in_group106);
                           break;
                        default:
                           continue label178;
                     }
                  }
               default:
                  alt4 = 0;

                  while(true) {
                     int alt5 = 2;
                     switch (this.input.LA(1)) {
                        case 7:
                        case 22:
                           alt5 = 1;
                     }

                     switch (alt5) {
                        case 1:
                           this.pushFollow(FOLLOW_def_in_group124);
                           this.def(prefix);
                           --this.state._fsp;
                           ++alt4;
                           break;
                        default:
                           if (alt4 < 1) {
                              EarlyExitException eee = new EarlyExitException(5, this.input);
                              throw eee;
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

   public final void oldStyleHeader() throws RecognitionException {
      try {
         try {
            this.match(this.input, 16, FOLLOW_16_in_oldStyleHeader146);
            this.match(this.input, 7, FOLLOW_ID_in_oldStyleHeader148);
            int alt6 = 2;
            switch (this.input.LA(1)) {
               case 17:
                  alt6 = 1;
            }

            switch (alt6) {
               case 1:
                  this.match(this.input, 17, FOLLOW_17_in_oldStyleHeader152);
                  this.match(this.input, 7, FOLLOW_ID_in_oldStyleHeader154);
               default:
                  int alt8 = 2;
                  switch (this.input.LA(1)) {
                     case 18:
                        alt8 = 1;
                  }

                  switch (alt8) {
                     case 1:
                        this.match(this.input, 18, FOLLOW_18_in_oldStyleHeader166);
                        this.match(this.input, 7, FOLLOW_ID_in_oldStyleHeader168);

                        label89:
                        while(true) {
                           int alt7 = 2;
                           switch (this.input.LA(1)) {
                              case 19:
                                 alt7 = 1;
                           }

                           switch (alt7) {
                              case 1:
                                 this.match(this.input, 19, FOLLOW_19_in_oldStyleHeader171);
                                 this.match(this.input, 7, FOLLOW_ID_in_oldStyleHeader173);
                                 break;
                              default:
                                 break label89;
                           }
                        }
                  }

                  this.match(this.input, 20, FOLLOW_20_in_oldStyleHeader185);
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final String groupName() throws RecognitionException {
      String name = null;
      Token a = null;
      StringBuilder buf = new StringBuilder();

      try {
         a = (Token)this.match(this.input, 7, FOLLOW_ID_in_groupName207);
         buf.append(a != null ? a.getText() : null);

         while(true) {
            int alt9 = 2;
            switch (this.input.LA(1)) {
               case 15:
                  alt9 = 1;
            }

            switch (alt9) {
               case 1:
                  this.match(this.input, 15, FOLLOW_15_in_groupName212);
                  a = (Token)this.match(this.input, 7, FOLLOW_ID_in_groupName216);
                  buf.append(a != null ? a.getText() : null);
                  break;
               default:
                  return name;
            }
         }
      } catch (RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
         return name;
      } finally {
         ;
      }
   }

   public final void delimiters() throws RecognitionException {
      Token a = null;
      Token b = null;

      try {
         try {
            this.match(this.input, 21, FOLLOW_21_in_delimiters234);
            a = (Token)this.match(this.input, 6, FOLLOW_STRING_in_delimiters238);
            this.match(this.input, 19, FOLLOW_19_in_delimiters240);
            b = (Token)this.match(this.input, 6, FOLLOW_STRING_in_delimiters244);
            this.group.delimiterStartChar = a.getText().charAt(1);
            this.group.delimiterStopChar = b.getText().charAt(1);
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final void def(String prefix) throws RecognitionException {
      try {
         try {
            int alt10;
            alt10 = 2;
            label63:
            switch (this.input.LA(1)) {
               case 7:
                  switch (this.input.LA(2)) {
                     case 23:
                        alt10 = 1;
                        break label63;
                     case 25:
                        switch (this.input.LA(3)) {
                           case 7:
                              alt10 = 1;
                              break label63;
                           case 27:
                              alt10 = 2;
                              break label63;
                           default:
                              NoViableAltException nvae = new NoViableAltException("", 10, 3, this.input);
                              throw nvae;
                        }
                     default:
                        NoViableAltException nvae = new NoViableAltException("", 10, 2, this.input);
                        throw nvae;
                  }
               case 22:
                  alt10 = 1;
                  break;
               default:
                  NoViableAltException nvae = new NoViableAltException("", 10, 0, this.input);
                  throw nvae;
            }

            switch (alt10) {
               case 1:
                  this.pushFollow(FOLLOW_templateDef_in_def268);
                  this.templateDef(prefix);
                  --this.state._fsp;
                  break;
               case 2:
                  this.pushFollow(FOLLOW_dictDef_in_def273);
                  this.dictDef();
                  --this.state._fsp;
            }
         } catch (RecognitionException var7) {
            this.state.lastErrorIndex = this.input.index();
            this.error("garbled template definition starting at '" + this.input.LT(1).getText() + "'");
         }

      } finally {
         ;
      }
   }

   public final void templateDef(String prefix) throws RecognitionException {
      Token enclosing = null;
      Token name = null;
      Token alias = null;
      Token target = null;
      Token STRING2 = null;
      Token BIGSTRING3 = null;
      Token BIGSTRING_NO_NL4 = null;
      List<FormalArgument> formalArgs5 = null;
      String template = null;
      int n = 0;

      try {
         try {
            int alt13;
            alt13 = 2;
            label202:
            switch (this.input.LA(1)) {
               case 7:
                  switch (this.input.LA(2)) {
                     case 23:
                        alt13 = 1;
                        break label202;
                     case 25:
                        alt13 = 2;
                        break label202;
                     default:
                        NoViableAltException nvae = new NoViableAltException("", 13, 2, this.input);
                        throw nvae;
                  }
               case 22:
                  alt13 = 1;
                  break;
               default:
                  NoViableAltException nvae = new NoViableAltException("", 13, 0, this.input);
                  throw nvae;
            }

            switch (alt13) {
               case 1:
                  int alt11 = 2;
                  switch (this.input.LA(1)) {
                     case 7:
                        alt11 = 2;
                        break;
                     case 22:
                        alt11 = 1;
                        break;
                     default:
                        NoViableAltException nvae = new NoViableAltException("", 11, 0, this.input);
                        throw nvae;
                  }

                  switch (alt11) {
                     case 1:
                        this.match(this.input, 22, FOLLOW_22_in_templateDef297);
                        enclosing = (Token)this.match(this.input, 7, FOLLOW_ID_in_templateDef301);
                        this.match(this.input, 15, FOLLOW_15_in_templateDef303);
                        name = (Token)this.match(this.input, 7, FOLLOW_ID_in_templateDef307);
                        this.match(this.input, 23, FOLLOW_23_in_templateDef309);
                        this.match(this.input, 24, FOLLOW_24_in_templateDef311);
                        break;
                     case 2:
                        name = (Token)this.match(this.input, 7, FOLLOW_ID_in_templateDef319);
                        this.match(this.input, 23, FOLLOW_23_in_templateDef321);
                        this.pushFollow(FOLLOW_formalArgs_in_templateDef323);
                        formalArgs5 = this.formalArgs();
                        --this.state._fsp;
                        this.match(this.input, 24, FOLLOW_24_in_templateDef325);
                  }

                  this.match(this.input, 25, FOLLOW_25_in_templateDef336);
                  Token templateToken = this.input.LT(1);
                  int alt12 = 4;
                  switch (this.input.LA(1)) {
                     case -1:
                     case 7:
                     case 22:
                        alt12 = 4;
                        break;
                     case 6:
                        alt12 = 1;
                        break;
                     case 8:
                        alt12 = 2;
                        break;
                     case 9:
                        alt12 = 3;
                        break;
                     default:
                        NoViableAltException nvae = new NoViableAltException("", 12, 0, this.input);
                        throw nvae;
                  }

                  switch (alt12) {
                     case 1:
                        STRING2 = (Token)this.match(this.input, 6, FOLLOW_STRING_in_templateDef352);
                        template = STRING2 != null ? STRING2.getText() : null;
                        n = 1;
                        break;
                     case 2:
                        BIGSTRING3 = (Token)this.match(this.input, 8, FOLLOW_BIGSTRING_in_templateDef367);
                        template = BIGSTRING3 != null ? BIGSTRING3.getText() : null;
                        n = 2;
                        break;
                     case 3:
                        BIGSTRING_NO_NL4 = (Token)this.match(this.input, 9, FOLLOW_BIGSTRING_NO_NL_in_templateDef379);
                        template = BIGSTRING_NO_NL4 != null ? BIGSTRING_NO_NL4.getText() : null;
                        n = 2;
                        break;
                     case 4:
                        template = "";
                        String msg = "missing template at '" + this.input.LT(1).getText() + "'";
                        NoViableAltException e = new NoViableAltException("", 0, 0, this.input);
                        this.group.errMgr.groupSyntaxError(ErrorType.SYNTAX_ERROR, this.getSourceName(), e, msg);
                  }

                  if ((name != null ? name.getTokenIndex() : 0) >= 0) {
                     template = Misc.strip(template, n);
                     String templateName = name != null ? name.getText() : null;
                     if (prefix.length() > 0) {
                        templateName = prefix + (name != null ? name.getText() : null);
                     }

                     this.group.defineTemplateOrRegion(templateName, enclosing != null ? enclosing.getText() : null, templateToken, template, name, formalArgs5);
                  }
                  break;
               case 2:
                  alias = (Token)this.match(this.input, 7, FOLLOW_ID_in_templateDef414);
                  this.match(this.input, 25, FOLLOW_25_in_templateDef416);
                  target = (Token)this.match(this.input, 7, FOLLOW_ID_in_templateDef420);
                  this.group.defineTemplateAlias(alias, target);
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final List formalArgs() throws RecognitionException {
      this.formalArgs_stack.push(new formalArgs_scope());
      List<FormalArgument> args = new ArrayList();
      ((formalArgs_scope)this.formalArgs_stack.peek()).hasOptionalParameter = false;

      try {
         int alt15 = 2;
         switch (this.input.LA(1)) {
            case 7:
               alt15 = 1;
               break;
            case 24:
               alt15 = 2;
               break;
            default:
               NoViableAltException nvae = new NoViableAltException("", 15, 0, this.input);
               throw nvae;
         }

         switch (alt15) {
            case 1:
               this.pushFollow(FOLLOW_formalArg_in_formalArgs446);
               this.formalArg(args);
               --this.state._fsp;

               while(true) {
                  int alt14 = 2;
                  switch (this.input.LA(1)) {
                     case 19:
                        alt14 = 1;
                  }

                  switch (alt14) {
                     case 1:
                        this.match(this.input, 19, FOLLOW_19_in_formalArgs450);
                        this.pushFollow(FOLLOW_formalArg_in_formalArgs452);
                        this.formalArg(args);
                        --this.state._fsp;
                        break;
                     default:
                        return args;
                  }
               }
            case 2:
         }
      } catch (RecognitionException re) {
         this.reportError(re);
         this.recover(this.input, re);
      } finally {
         this.formalArgs_stack.pop();
      }

      return args;
   }

   public final void formalArg(List args) throws RecognitionException {
      Token a = null;
      Token ID6 = null;

      try {
         try {
            ID6 = (Token)this.match(this.input, 7, FOLLOW_ID_in_formalArg470);
            int alt16 = 2;
            switch (this.input.LA(1)) {
               case 19:
               case 24:
                  alt16 = 2;
                  break;
               case 26:
                  alt16 = 1;
                  break;
               default:
                  NoViableAltException nvae = new NoViableAltException("", 16, 0, this.input);
                  throw nvae;
            }

            switch (alt16) {
               case 1:
                  this.match(this.input, 26, FOLLOW_26_in_formalArg476);
                  a = this.input.LT(1);
                  if ((this.input.LA(1) < 4 || this.input.LA(1) > 6) && this.input.LA(1) != 10) {
                     MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                     throw mse;
                  }

                  this.input.consume();
                  this.state.errorRecovery = false;
                  ((formalArgs_scope)this.formalArgs_stack.peek()).hasOptionalParameter = true;
                  break;
               case 2:
                  if (((formalArgs_scope)this.formalArgs_stack.peek()).hasOptionalParameter) {
                     this.group.errMgr.compileTimeError(ErrorType.REQUIRED_PARAMETER_AFTER_OPTIONAL, (Token)null, ID6);
                  }
            }

            args.add(new FormalArgument(ID6 != null ? ID6.getText() : null, a));
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final void dictDef() throws RecognitionException {
      Token ID7 = null;
      Map<String, Object> dict8 = null;

      try {
         try {
            ID7 = (Token)this.match(this.input, 7, FOLLOW_ID_in_dictDef521);
            this.match(this.input, 25, FOLLOW_25_in_dictDef523);
            this.pushFollow(FOLLOW_dict_in_dictDef525);
            dict8 = this.dict();
            --this.state._fsp;
            if (this.group.rawGetDictionary(ID7 != null ? ID7.getText() : null) != null) {
               this.group.errMgr.compileTimeError(ErrorType.MAP_REDEFINITION, (Token)null, ID7);
            } else if (this.group.rawGetTemplate(ID7 != null ? ID7.getText() : null) != null) {
               this.group.errMgr.compileTimeError(ErrorType.TEMPLATE_REDEFINITION_AS_MAP, (Token)null, ID7);
            } else {
               this.group.defineDictionary(ID7 != null ? ID7.getText() : null, dict8);
            }
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final Map dict() throws RecognitionException {
      Map<String, Object> mapping = null;
      mapping = new HashMap();

      try {
         try {
            this.match(this.input, 27, FOLLOW_27_in_dict557);
            this.pushFollow(FOLLOW_dictPairs_in_dict559);
            this.dictPairs(mapping);
            --this.state._fsp;
            this.match(this.input, 28, FOLLOW_28_in_dict562);
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

         return mapping;
      } finally {
         ;
      }
   }

   public final void dictPairs(Map mapping) throws RecognitionException {
      try {
         try {
            int alt19 = 2;
            switch (this.input.LA(1)) {
               case 6:
                  alt19 = 1;
                  break;
               case 29:
                  alt19 = 2;
                  break;
               default:
                  NoViableAltException nvae = new NoViableAltException("", 19, 0, this.input);
                  throw nvae;
            }

            switch (alt19) {
               case 1:
                  this.pushFollow(FOLLOW_keyValuePair_in_dictPairs577);
                  this.keyValuePair(mapping);
                  --this.state._fsp;

                  while(true) {
                     int alt17 = 2;
                     switch (this.input.LA(1)) {
                        case 19:
                           switch (this.input.LA(2)) {
                              case 6:
                                 alt17 = 1;
                           }
                     }

                     switch (alt17) {
                        case 1:
                           this.match(this.input, 19, FOLLOW_19_in_dictPairs586);
                           this.pushFollow(FOLLOW_keyValuePair_in_dictPairs588);
                           this.keyValuePair(mapping);
                           --this.state._fsp;
                           break;
                        default:
                           alt17 = 2;
                           switch (this.input.LA(1)) {
                              case 19:
                                 alt17 = 1;
                              default:
                                 switch (alt17) {
                                    case 1:
                                       this.match(this.input, 19, FOLLOW_19_in_dictPairs594);
                                       this.pushFollow(FOLLOW_defaultValuePair_in_dictPairs596);
                                       this.defaultValuePair(mapping);
                                       --this.state._fsp;
                                       return;
                                    default:
                                       return;
                                 }
                           }
                     }
                  }
               case 2:
                  this.pushFollow(FOLLOW_defaultValuePair_in_dictPairs607);
                  this.defaultValuePair(mapping);
                  --this.state._fsp;
            }
         } catch (RecognitionException var7) {
            this.error("missing dictionary entry at '" + this.input.LT(1).getText() + "'");
         }

      } finally {
         ;
      }
   }

   public final void defaultValuePair(Map mapping) throws RecognitionException {
      Object keyValue9 = null;

      try {
         try {
            this.match(this.input, 29, FOLLOW_29_in_defaultValuePair630);
            this.match(this.input, 17, FOLLOW_17_in_defaultValuePair632);
            this.pushFollow(FOLLOW_keyValue_in_defaultValuePair634);
            keyValue9 = this.keyValue();
            --this.state._fsp;
            mapping.put("default", keyValue9);
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final void keyValuePair(Map mapping) throws RecognitionException {
      Token STRING10 = null;
      Object keyValue11 = null;

      try {
         try {
            STRING10 = (Token)this.match(this.input, 6, FOLLOW_STRING_in_keyValuePair648);
            this.match(this.input, 17, FOLLOW_17_in_keyValuePair650);
            this.pushFollow(FOLLOW_keyValue_in_keyValuePair652);
            keyValue11 = this.keyValue();
            --this.state._fsp;
            mapping.put(Misc.replaceEscapes(Misc.strip(STRING10 != null ? STRING10.getText() : null, 1)), keyValue11);
         } catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
         }

      } finally {
         ;
      }
   }

   public final Object keyValue() throws RecognitionException {
      Object value = null;
      Token BIGSTRING12 = null;
      Token BIGSTRING_NO_NL13 = null;
      Token ANONYMOUS_TEMPLATE14 = null;
      Token STRING15 = null;

      try {
         try {
            int alt20 = 7;
            int LA20_0 = this.input.LA(1);
            if (LA20_0 == 8) {
               alt20 = 1;
            } else if (LA20_0 == 9) {
               alt20 = 2;
            } else if (LA20_0 == 10) {
               alt20 = 3;
            } else if (LA20_0 == 6) {
               alt20 = 4;
            } else if (LA20_0 == 4) {
               alt20 = 5;
            } else if (LA20_0 == 5) {
               alt20 = 6;
            } else {
               if (LA20_0 != 7 || !this.input.LT(1).getText().equals("key")) {
                  NoViableAltException nvae = new NoViableAltException("", 20, 0, this.input);
                  throw nvae;
               }

               alt20 = 7;
            }

            switch (alt20) {
               case 1:
                  BIGSTRING12 = (Token)this.match(this.input, 8, FOLLOW_BIGSTRING_in_keyValue669);
                  value = this.group.createSingleton(BIGSTRING12);
                  break;
               case 2:
                  BIGSTRING_NO_NL13 = (Token)this.match(this.input, 9, FOLLOW_BIGSTRING_NO_NL_in_keyValue678);
                  value = this.group.createSingleton(BIGSTRING_NO_NL13);
                  break;
               case 3:
                  ANONYMOUS_TEMPLATE14 = (Token)this.match(this.input, 10, FOLLOW_ANONYMOUS_TEMPLATE_in_keyValue686);
                  value = this.group.createSingleton(ANONYMOUS_TEMPLATE14);
                  break;
               case 4:
                  STRING15 = (Token)this.match(this.input, 6, FOLLOW_STRING_in_keyValue693);
                  value = Misc.replaceEscapes(Misc.strip(STRING15 != null ? STRING15.getText() : null, 1));
                  break;
               case 5:
                  this.match(this.input, 4, FOLLOW_TRUE_in_keyValue703);
                  value = true;
                  break;
               case 6:
                  this.match(this.input, 5, FOLLOW_FALSE_in_keyValue713);
                  value = false;
                  break;
               case 7:
                  if (!this.input.LT(1).getText().equals("key")) {
                     throw new FailedPredicateException(this.input, "keyValue", "input.LT(1).getText().equals(\"key\")");
                  }

                  this.match(this.input, 7, FOLLOW_ID_in_keyValue726);
                  value = "key";
            }
         } catch (RecognitionException var12) {
            this.error("missing value for key at '" + this.input.LT(1).getText() + "'");
         }

         return value;
      } finally {
         ;
      }
   }

   protected static class formalArgs_scope {
      boolean hasOptionalParameter;
   }
}
