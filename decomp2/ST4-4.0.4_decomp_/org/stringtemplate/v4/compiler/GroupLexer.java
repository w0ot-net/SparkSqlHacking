package org.stringtemplate.v4.compiler;

import java.io.File;
import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.DFA;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.misc.ErrorType;

public class GroupLexer extends Lexer {
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
   protected DFA8 dfa8;
   static final String DFA8_eotS = "\u0001\uffff\u0003\u0010\u0001\uffff\u0001\u0010\u0001\u001b\u0002\uffff\u0001\u0010\f\uffff\u0004\u0010\u0002\uffff\u0001\u0010\u0004\uffff\u0006\u0010\u0001.\u0006\u0010\u0001\uffff\u00015\u0002\u0010\u00018\u0002\u0010\u0001\uffff\u0001;\u0001\u0010\u0001\uffff\u0002\u0010\u0001\uffff\u0002\u0010\u0001A\u0002\u0010\u0001\uffff\u0002\u0010\u0001F\u0001G\u0002\uffff";
   static final String DFA8_eofS = "H\uffff";
   static final String DFA8_minS = "\u0001\t\u0001r\u0001a\u0001m\u0001\uffff\u0001r\u0001:\u0002\uffff\u0001e\b\uffff\u0001%\u0001\uffff\u0001*\u0001\uffff\u0001u\u0001l\u0001p\u0001o\u0002\uffff\u0001f\u0004\uffff\u0001e\u0001s\u0001l\u0001u\u0001i\u0001a\u0001-\u0001e\u0001r\u0001e\u0001p\u0001m\u0001u\u0001\uffff\u0001-\u0001t\u0001m\u0001-\u0001i\u0001l\u0001\uffff\u0001-\u0001e\u0001\uffff\u0002t\u0001\uffff\u0001n\u0001e\u0001-\u0001t\u0001r\u0001\uffff\u0002s\u0002-\u0002\uffff";
   static final String DFA8_maxS = "\u0001{\u0001r\u0001a\u0001m\u0001\uffff\u0001r\u0001:\u0002\uffff\u0001e\b\uffff\u0001<\u0001\uffff\u0001/\u0001\uffff\u0001u\u0001l\u0001p\u0001o\u0002\uffff\u0001l\u0004\uffff\u0001e\u0001s\u0001o\u0001u\u0001i\u0001a\u0001z\u0001e\u0001r\u0001e\u0001p\u0001m\u0001u\u0001\uffff\u0001z\u0001t\u0001m\u0001z\u0001i\u0001l\u0001\uffff\u0001z\u0001e\u0001\uffff\u0002t\u0001\uffff\u0001n\u0001e\u0001z\u0001t\u0001r\u0001\uffff\u0002s\u0002z\u0002\uffff";
   static final String DFA8_acceptS = "\u0004\uffff\u0001\u0004\u0002\uffff\u0001\b\u0001\t\u0001\uffff\u0001\u000b\u0001\f\u0001\r\u0001\u000f\u0001\u0010\u0001\u0011\u0001\u0013\u0001\u0014\u0001\uffff\u0001\u0017\u0001\uffff\u0001\u001a\u0004\uffff\u0001\u000e\u0001\u0006\u0001\uffff\u0001\u0015\u0001\u0016\u0001\u0018\u0001\u0019\r\uffff\u0001\u0001\u0006\uffff\u0001\u0002\u0002\uffff\u0001\u0005\u0002\uffff\u0001\u0003\u0005\uffff\u0001\u0012\u0004\uffff\u0001\u0007\u0001\n";
   static final String DFA8_specialS = "H\uffff}>";
   static final String[] DFA8_transitionS = new String[]{"\u0002\u0015\u0002\uffff\u0001\u0015\u0012\uffff\u0001\u0015\u0001\uffff\u0001\u0011\u0005\uffff\u0001\u000b\u0001\f\u0002\uffff\u0001\u0007\u0001\uffff\u0001\u0004\u0001\u0014\n\uffff\u0001\u0006\u0001\b\u0001\u0012\u0001\r\u0002\uffff\u0001\n\u001a\u0010\u0001\u000e\u0001\uffff\u0001\u000f\u0001\uffff\u0001\u0010\u0001\uffff\u0003\u0010\u0001\t\u0001\u0010\u0001\u0002\u0001\u0005\u0001\u0010\u0001\u0003\n\u0010\u0001\u0001\u0006\u0010\u0001\u0013", "\u0001\u0016", "\u0001\u0017", "\u0001\u0018", "", "\u0001\u0019", "\u0001\u001a", "", "", "\u0001\u001c", "", "", "", "", "", "", "", "", "\u0001\u001d\u0016\uffff\u0001\u001e", "", "\u0001\u001f\u0004\uffff\u0001 ", "", "\u0001!", "\u0001\"", "\u0001#", "\u0001$", "", "", "\u0001&\u0005\uffff\u0001%", "", "", "", "", "\u0001'", "\u0001(", "\u0001*\u0002\uffff\u0001)", "\u0001+", "\u0001,", "\u0001-", "\u0001\u0010\u0002\uffff\n\u0010\u0007\uffff\u001a\u0010\u0004\uffff\u0001\u0010\u0001\uffff\u001a\u0010", "\u0001/", "\u00010", "\u00011", "\u00012", "\u00013", "\u00014", "", "\u0001\u0010\u0002\uffff\n\u0010\u0007\uffff\u001a\u0010\u0004\uffff\u0001\u0010\u0001\uffff\u001a\u0010", "\u00016", "\u00017", "\u0001\u0010\u0002\uffff\n\u0010\u0007\uffff\u001a\u0010\u0004\uffff\u0001\u0010\u0001\uffff\u001a\u0010", "\u00019", "\u0001:", "", "\u0001\u0010\u0002\uffff\n\u0010\u0007\uffff\u001a\u0010\u0004\uffff\u0001\u0010\u0001\uffff\u001a\u0010", "\u0001<", "", "\u0001=", "\u0001>", "", "\u0001?", "\u0001@", "\u0001\u0010\u0002\uffff\n\u0010\u0007\uffff\u001a\u0010\u0004\uffff\u0001\u0010\u0001\uffff\u001a\u0010", "\u0001B", "\u0001C", "", "\u0001D", "\u0001E", "\u0001\u0010\u0002\uffff\n\u0010\u0007\uffff\u001a\u0010\u0004\uffff\u0001\u0010\u0001\uffff\u001a\u0010", "\u0001\u0010\u0002\uffff\n\u0010\u0007\uffff\u001a\u0010\u0004\uffff\u0001\u0010\u0001\uffff\u001a\u0010", "", ""};
   static final short[] DFA8_eot = DFA.unpackEncodedString("\u0001\uffff\u0003\u0010\u0001\uffff\u0001\u0010\u0001\u001b\u0002\uffff\u0001\u0010\f\uffff\u0004\u0010\u0002\uffff\u0001\u0010\u0004\uffff\u0006\u0010\u0001.\u0006\u0010\u0001\uffff\u00015\u0002\u0010\u00018\u0002\u0010\u0001\uffff\u0001;\u0001\u0010\u0001\uffff\u0002\u0010\u0001\uffff\u0002\u0010\u0001A\u0002\u0010\u0001\uffff\u0002\u0010\u0001F\u0001G\u0002\uffff");
   static final short[] DFA8_eof = DFA.unpackEncodedString("H\uffff");
   static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars("\u0001\t\u0001r\u0001a\u0001m\u0001\uffff\u0001r\u0001:\u0002\uffff\u0001e\b\uffff\u0001%\u0001\uffff\u0001*\u0001\uffff\u0001u\u0001l\u0001p\u0001o\u0002\uffff\u0001f\u0004\uffff\u0001e\u0001s\u0001l\u0001u\u0001i\u0001a\u0001-\u0001e\u0001r\u0001e\u0001p\u0001m\u0001u\u0001\uffff\u0001-\u0001t\u0001m\u0001-\u0001i\u0001l\u0001\uffff\u0001-\u0001e\u0001\uffff\u0002t\u0001\uffff\u0001n\u0001e\u0001-\u0001t\u0001r\u0001\uffff\u0002s\u0002-\u0002\uffff");
   static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars("\u0001{\u0001r\u0001a\u0001m\u0001\uffff\u0001r\u0001:\u0002\uffff\u0001e\b\uffff\u0001<\u0001\uffff\u0001/\u0001\uffff\u0001u\u0001l\u0001p\u0001o\u0002\uffff\u0001l\u0004\uffff\u0001e\u0001s\u0001o\u0001u\u0001i\u0001a\u0001z\u0001e\u0001r\u0001e\u0001p\u0001m\u0001u\u0001\uffff\u0001z\u0001t\u0001m\u0001z\u0001i\u0001l\u0001\uffff\u0001z\u0001e\u0001\uffff\u0002t\u0001\uffff\u0001n\u0001e\u0001z\u0001t\u0001r\u0001\uffff\u0002s\u0002z\u0002\uffff");
   static final short[] DFA8_accept = DFA.unpackEncodedString("\u0004\uffff\u0001\u0004\u0002\uffff\u0001\b\u0001\t\u0001\uffff\u0001\u000b\u0001\f\u0001\r\u0001\u000f\u0001\u0010\u0001\u0011\u0001\u0013\u0001\u0014\u0001\uffff\u0001\u0017\u0001\uffff\u0001\u001a\u0004\uffff\u0001\u000e\u0001\u0006\u0001\uffff\u0001\u0015\u0001\u0016\u0001\u0018\u0001\u0019\r\uffff\u0001\u0001\u0006\uffff\u0001\u0002\u0002\uffff\u0001\u0005\u0002\uffff\u0001\u0003\u0005\uffff\u0001\u0012\u0004\uffff\u0001\u0007\u0001\n");
   static final short[] DFA8_special = DFA.unpackEncodedString("H\uffff}>");
   static final short[][] DFA8_transition;

   public void reportError(RecognitionException e) {
      String msg = null;
      if (e instanceof NoViableAltException) {
         msg = "invalid character '" + (char)this.input.LA(1) + "'";
      } else if (e instanceof MismatchedTokenException && ((MismatchedTokenException)e).expecting == 34) {
         msg = "unterminated string";
      } else {
         msg = this.getErrorMessage(e, this.getTokenNames());
      }

      this.group.errMgr.groupSyntaxError(ErrorType.SYNTAX_ERROR, this.getSourceName(), e, msg);
   }

   public String getSourceName() {
      String fullFileName = super.getSourceName();
      File f = new File(fullFileName);
      return f.getName();
   }

   public GroupLexer() {
      this.dfa8 = new DFA8(this);
   }

   public GroupLexer(CharStream input) {
      this(input, new RecognizerSharedState());
   }

   public GroupLexer(CharStream input, RecognizerSharedState state) {
      super(input, state);
      this.dfa8 = new DFA8(this);
   }

   public String getGrammarFileName() {
      return "org/stringtemplate/v4/compiler/Group.g";
   }

   public final void mTRUE() throws RecognitionException {
      try {
         int _type = 4;
         int _channel = 0;
         this.match("true");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mFALSE() throws RecognitionException {
      try {
         int _type = 5;
         int _channel = 0;
         this.match("false");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__14() throws RecognitionException {
      try {
         int _type = 14;
         int _channel = 0;
         this.match("import");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__15() throws RecognitionException {
      try {
         int _type = 15;
         int _channel = 0;
         this.match(46);
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__16() throws RecognitionException {
      try {
         int _type = 16;
         int _channel = 0;
         this.match("group");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__17() throws RecognitionException {
      try {
         int _type = 17;
         int _channel = 0;
         this.match(58);
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__18() throws RecognitionException {
      try {
         int _type = 18;
         int _channel = 0;
         this.match("implements");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__19() throws RecognitionException {
      try {
         int _type = 19;
         int _channel = 0;
         this.match(44);
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__20() throws RecognitionException {
      try {
         int _type = 20;
         int _channel = 0;
         this.match(59);
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__21() throws RecognitionException {
      try {
         int _type = 21;
         int _channel = 0;
         this.match("delimiters");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__22() throws RecognitionException {
      try {
         int _type = 22;
         int _channel = 0;
         this.match(64);
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__23() throws RecognitionException {
      try {
         int _type = 23;
         int _channel = 0;
         this.match(40);
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__24() throws RecognitionException {
      try {
         int _type = 24;
         int _channel = 0;
         this.match(41);
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__25() throws RecognitionException {
      try {
         int _type = 25;
         int _channel = 0;
         this.match("::=");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__26() throws RecognitionException {
      try {
         int _type = 26;
         int _channel = 0;
         this.match(61);
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__27() throws RecognitionException {
      try {
         int _type = 27;
         int _channel = 0;
         this.match(91);
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__28() throws RecognitionException {
      try {
         int _type = 28;
         int _channel = 0;
         this.match(93);
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mT__29() throws RecognitionException {
      try {
         int _type = 29;
         int _channel = 0;
         this.match("default");
         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mID() throws RecognitionException {
      try {
         int _type = 7;
         int _channel = 0;
         if (this.input.LA(1) >= 65 && this.input.LA(1) <= 90 || this.input.LA(1) == 95 || this.input.LA(1) >= 97 && this.input.LA(1) <= 122) {
            this.input.consume();

            while(true) {
               int alt1 = 2;
               switch (this.input.LA(1)) {
                  case 45:
                  case 48:
                  case 49:
                  case 50:
                  case 51:
                  case 52:
                  case 53:
                  case 54:
                  case 55:
                  case 56:
                  case 57:
                  case 65:
                  case 66:
                  case 67:
                  case 68:
                  case 69:
                  case 70:
                  case 71:
                  case 72:
                  case 73:
                  case 74:
                  case 75:
                  case 76:
                  case 77:
                  case 78:
                  case 79:
                  case 80:
                  case 81:
                  case 82:
                  case 83:
                  case 84:
                  case 85:
                  case 86:
                  case 87:
                  case 88:
                  case 89:
                  case 90:
                  case 95:
                  case 97:
                  case 98:
                  case 99:
                  case 100:
                  case 101:
                  case 102:
                  case 103:
                  case 104:
                  case 105:
                  case 106:
                  case 107:
                  case 108:
                  case 109:
                  case 110:
                  case 111:
                  case 112:
                  case 113:
                  case 114:
                  case 115:
                  case 116:
                  case 117:
                  case 118:
                  case 119:
                  case 120:
                  case 121:
                  case 122:
                     alt1 = 1;
                  case 46:
                  case 47:
                  case 58:
                  case 59:
                  case 60:
                  case 61:
                  case 62:
                  case 63:
                  case 64:
                  case 91:
                  case 92:
                  case 93:
                  case 94:
                  case 96:
               }

               switch (alt1) {
                  case 1:
                     if (this.input.LA(1) != 45 && (this.input.LA(1) < 48 || this.input.LA(1) > 57) && (this.input.LA(1) < 65 || this.input.LA(1) > 90) && this.input.LA(1) != 95 && (this.input.LA(1) < 97 || this.input.LA(1) > 122)) {
                        MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                        this.recover(mse);
                        throw mse;
                     }

                     this.input.consume();
                     break;
                  default:
                     this.state.type = _type;
                     this.state.channel = _channel;
                     return;
               }
            }
         } else {
            MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
            this.recover(mse);
            throw mse;
         }
      } finally {
         ;
      }
   }

   public final void mSTRING() throws RecognitionException {
      try {
         int _type = 6;
         int _channel = 0;
         this.match(34);

         while(true) {
            int alt2 = 5;
            int LA2_0 = this.input.LA(1);
            if (LA2_0 == 92) {
               int LA2_2 = this.input.LA(2);
               if (LA2_2 == 34) {
                  alt2 = 1;
               } else if (LA2_2 >= 0 && LA2_2 <= 33 || LA2_2 >= 35 && LA2_2 <= 65535) {
                  alt2 = 2;
               }
            } else if (LA2_0 == 10) {
               alt2 = 3;
            } else if (LA2_0 >= 0 && LA2_0 <= 9 || LA2_0 >= 11 && LA2_0 <= 33 || LA2_0 >= 35 && LA2_0 <= 91 || LA2_0 >= 93 && LA2_0 <= 65535) {
               alt2 = 4;
            }

            switch (alt2) {
               case 1:
                  this.match(92);
                  this.match(34);
                  break;
               case 2:
                  this.match(92);
                  if ((this.input.LA(1) < 0 || this.input.LA(1) > 33) && (this.input.LA(1) < 35 || this.input.LA(1) > 65535)) {
                     MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                     this.recover(mse);
                     throw mse;
                  }

                  this.input.consume();
                  break;
               case 3:
                  String msg = "\\n in string";
                  NoViableAltException e = new NoViableAltException("", 0, 0, this.input);
                  this.group.errMgr.groupLexerError(ErrorType.SYNTAX_ERROR, this.getSourceName(), e, msg);
                  this.match(10);
                  break;
               case 4:
                  if ((this.input.LA(1) < 0 || this.input.LA(1) > 9) && (this.input.LA(1) < 11 || this.input.LA(1) > 33) && (this.input.LA(1) < 35 || this.input.LA(1) > 91) && (this.input.LA(1) < 93 || this.input.LA(1) > 65535)) {
                     MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                     this.recover(mse);
                     throw mse;
                  }

                  this.input.consume();
                  break;
               default:
                  this.match(34);
                  String txt = this.getText().replaceAll("\\\\\"", "\"");
                  this.setText(txt);
                  this.state.type = _type;
                  this.state.channel = _channel;
                  return;
            }
         }
      } finally {
         ;
      }
   }

   public final void mBIGSTRING_NO_NL() throws RecognitionException {
      try {
         int _type = 9;
         int _channel = 0;
         this.match("<%");

         while(true) {
            int alt3 = 2;
            int LA3_0 = this.input.LA(1);
            if (LA3_0 == 37) {
               int LA3_1 = this.input.LA(2);
               if (LA3_1 == 62) {
                  alt3 = 2;
               } else if (LA3_1 >= 0 && LA3_1 <= 61 || LA3_1 >= 63 && LA3_1 <= 65535) {
                  alt3 = 1;
               }
            } else if (LA3_0 >= 0 && LA3_0 <= 36 || LA3_0 >= 38 && LA3_0 <= 65535) {
               alt3 = 1;
            }

            switch (alt3) {
               case 1:
                  this.matchAny();
                  break;
               default:
                  this.match("%>");
                  this.state.type = _type;
                  this.state.channel = _channel;
                  return;
            }
         }
      } finally {
         ;
      }
   }

   public final void mBIGSTRING() throws RecognitionException {
      try {
         int _type = 8;
         int _channel = 0;
         this.match("<<");

         while(true) {
            int alt4 = 4;
            int LA4_0 = this.input.LA(1);
            if (LA4_0 == 62) {
               int LA4_1 = this.input.LA(2);
               if (LA4_1 == 62) {
                  alt4 = 4;
               } else if (LA4_1 >= 0 && LA4_1 <= 61 || LA4_1 >= 63 && LA4_1 <= 65535) {
                  alt4 = 3;
               }
            } else if (LA4_0 == 92) {
               int LA4_2 = this.input.LA(2);
               if (LA4_2 == 62) {
                  alt4 = 1;
               } else if (LA4_2 >= 0 && LA4_2 <= 61 || LA4_2 >= 63 && LA4_2 <= 65535) {
                  alt4 = 2;
               }
            } else if (LA4_0 >= 0 && LA4_0 <= 61 || LA4_0 >= 63 && LA4_0 <= 91 || LA4_0 >= 93 && LA4_0 <= 65535) {
               alt4 = 3;
            }

            switch (alt4) {
               case 1:
                  this.match(92);
                  this.match(62);
                  break;
               case 2:
                  this.match(92);
                  if ((this.input.LA(1) < 0 || this.input.LA(1) > 61) && (this.input.LA(1) < 63 || this.input.LA(1) > 65535)) {
                     MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                     this.recover(mse);
                     throw mse;
                  }

                  this.input.consume();
                  break;
               case 3:
                  if ((this.input.LA(1) < 0 || this.input.LA(1) > 91) && (this.input.LA(1) < 93 || this.input.LA(1) > 65535)) {
                     MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                     this.recover(mse);
                     throw mse;
                  }

                  this.input.consume();
                  break;
               default:
                  this.match(">>");
                  String txt = this.getText().replaceAll("\\\\>", ">");
                  this.setText(txt);
                  this.state.type = _type;
                  this.state.channel = _channel;
                  return;
            }
         }
      } finally {
         ;
      }
   }

   public final void mANONYMOUS_TEMPLATE() throws RecognitionException {
      try {
         int _type = 10;
         int _channel = 0;
         this.match(123);
         Token templateToken = new CommonToken(this.input, 10, 0, this.getCharIndex(), this.getCharIndex());
         STLexer lexer = new STLexer(this.group.errMgr, this.input, templateToken, this.group.delimiterStartChar, this.group.delimiterStopChar);
         lexer.subtemplateDepth = 1;

         for(Token t = lexer.nextToken(); lexer.subtemplateDepth >= 1 || t.getType() != 21; t = lexer.nextToken()) {
            if (t.getType() == -1) {
               MismatchedTokenException e = new MismatchedTokenException(125, this.input);
               String msg = "missing final '}' in {...} anonymous template";
               this.group.errMgr.groupLexerError(ErrorType.SYNTAX_ERROR, this.getSourceName(), e, msg);
               break;
            }
         }

         this.state.type = _type;
         this.state.channel = _channel;
      } finally {
         ;
      }
   }

   public final void mCOMMENT() throws RecognitionException {
      try {
         int _type = 11;
         int _channel = 0;
         this.match("/*");

         while(true) {
            int alt5 = 2;
            int LA5_0 = this.input.LA(1);
            if (LA5_0 == 42) {
               int LA5_1 = this.input.LA(2);
               if (LA5_1 == 47) {
                  alt5 = 2;
               } else if (LA5_1 >= 0 && LA5_1 <= 46 || LA5_1 >= 48 && LA5_1 <= 65535) {
                  alt5 = 1;
               }
            } else if (LA5_0 >= 0 && LA5_0 <= 41 || LA5_0 >= 43 && LA5_0 <= 65535) {
               alt5 = 1;
            }

            switch (alt5) {
               case 1:
                  this.matchAny();
                  break;
               default:
                  this.match("*/");
                  this.skip();
                  this.state.type = _type;
                  this.state.channel = _channel;
                  return;
            }
         }
      } finally {
         ;
      }
   }

   public final void mLINE_COMMENT() throws RecognitionException {
      try {
         int _type = 12;
         int _channel = 0;
         this.match("//");

         while(true) {
            int alt6 = 2;
            int LA6_0 = this.input.LA(1);
            if (LA6_0 >= 0 && LA6_0 <= 9 || LA6_0 >= 11 && LA6_0 <= 12 || LA6_0 >= 14 && LA6_0 <= 65535) {
               alt6 = 1;
            }

            switch (alt6) {
               case 1:
                  if ((this.input.LA(1) < 0 || this.input.LA(1) > 9) && (this.input.LA(1) < 11 || this.input.LA(1) > 12) && (this.input.LA(1) < 14 || this.input.LA(1) > 65535)) {
                     MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                     this.recover(mse);
                     throw mse;
                  }

                  this.input.consume();
                  break;
               default:
                  alt6 = 2;
                  switch (this.input.LA(1)) {
                     case 13:
                        alt6 = 1;
                     default:
                        switch (alt6) {
                           case 1:
                              this.match(13);
                           default:
                              this.match(10);
                              this.skip();
                              this.state.type = _type;
                              this.state.channel = _channel;
                              return;
                        }
                  }
            }
         }
      } finally {
         ;
      }
   }

   public final void mWS() throws RecognitionException {
      try {
         int _type = 13;
         int _channel = 0;
         if ((this.input.LA(1) < 9 || this.input.LA(1) > 10) && this.input.LA(1) != 13 && this.input.LA(1) != 32) {
            MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
            this.recover(mse);
            throw mse;
         } else {
            this.input.consume();
            this.skip();
            this.state.type = _type;
            this.state.channel = _channel;
         }
      } finally {
         ;
      }
   }

   public void mTokens() throws RecognitionException {
      int alt8 = 26;
      alt8 = this.dfa8.predict(this.input);
      switch (alt8) {
         case 1:
            this.mTRUE();
            break;
         case 2:
            this.mFALSE();
            break;
         case 3:
            this.mT__14();
            break;
         case 4:
            this.mT__15();
            break;
         case 5:
            this.mT__16();
            break;
         case 6:
            this.mT__17();
            break;
         case 7:
            this.mT__18();
            break;
         case 8:
            this.mT__19();
            break;
         case 9:
            this.mT__20();
            break;
         case 10:
            this.mT__21();
            break;
         case 11:
            this.mT__22();
            break;
         case 12:
            this.mT__23();
            break;
         case 13:
            this.mT__24();
            break;
         case 14:
            this.mT__25();
            break;
         case 15:
            this.mT__26();
            break;
         case 16:
            this.mT__27();
            break;
         case 17:
            this.mT__28();
            break;
         case 18:
            this.mT__29();
            break;
         case 19:
            this.mID();
            break;
         case 20:
            this.mSTRING();
            break;
         case 21:
            this.mBIGSTRING_NO_NL();
            break;
         case 22:
            this.mBIGSTRING();
            break;
         case 23:
            this.mANONYMOUS_TEMPLATE();
            break;
         case 24:
            this.mCOMMENT();
            break;
         case 25:
            this.mLINE_COMMENT();
            break;
         case 26:
            this.mWS();
      }

   }

   static {
      int numStates = DFA8_transitionS.length;
      DFA8_transition = new short[numStates][];

      for(int i = 0; i < numStates; ++i) {
         DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
      }

   }

   class DFA8 extends DFA {
      public DFA8(BaseRecognizer recognizer) {
         this.recognizer = recognizer;
         this.decisionNumber = 8;
         this.eot = GroupLexer.DFA8_eot;
         this.eof = GroupLexer.DFA8_eof;
         this.min = GroupLexer.DFA8_min;
         this.max = GroupLexer.DFA8_max;
         this.accept = GroupLexer.DFA8_accept;
         this.special = GroupLexer.DFA8_special;
         this.transition = GroupLexer.DFA8_transition;
      }

      public String getDescription() {
         return "1:1: Tokens : ( TRUE | FALSE | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | ID | STRING | BIGSTRING_NO_NL | BIGSTRING | ANONYMOUS_TEMPLATE | COMMENT | LINE_COMMENT | WS );";
      }
   }
}
