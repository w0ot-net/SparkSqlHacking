package org.apache.hadoop.hive.metastore.parser;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.FailedPredicateException;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

public class FilterLexer extends Lexer {
   public static final int EOF = -1;
   public static final int BETWEEN = 4;
   public static final int COMMA = 5;
   public static final int DateLiteral = 6;
   public static final int DateString = 7;
   public static final int Digit = 8;
   public static final int EQUAL = 9;
   public static final int GREATERTHAN = 10;
   public static final int GREATERTHANOREQUALTO = 11;
   public static final int IN = 12;
   public static final int Identifier = 13;
   public static final int IntegralLiteral = 14;
   public static final int KW_AND = 15;
   public static final int KW_CONST = 16;
   public static final int KW_DATE = 17;
   public static final int KW_LIKE = 18;
   public static final int KW_NOT = 19;
   public static final int KW_OR = 20;
   public static final int KW_STRUCT = 21;
   public static final int LESSTHAN = 22;
   public static final int LESSTHANOREQUALTO = 23;
   public static final int LPAREN = 24;
   public static final int Letter = 25;
   public static final int NOTEQUAL = 26;
   public static final int RPAREN = 27;
   public static final int StringLiteral = 28;
   public static final int WS = 29;
   public String errorMsg;
   private static final Pattern datePattern = Pattern.compile(".*(\\d\\d\\d\\d-\\d\\d-\\d\\d).*");
   private static final ThreadLocal dateFormat = new ThreadLocal() {
      protected SimpleDateFormat initialValue() {
         SimpleDateFormat val = new SimpleDateFormat("yyyy-MM-dd");
         val.setLenient(false);
         return val;
      }
   };
   protected DFA10 dfa10;
   static final String DFA10_eotS = "\u0001\uffff\u0007\u0014\u0004\uffff\u0001\u001e\u0001\uffff\u0001 \u0002\u0014\u0001\u0013\u0004\uffff\u0002\u0014\u0001&\u0004\u0014\u0004\uffff\u0001\u0014\u0001,\u0001\u0013\u0001.\u0001/\u0001\uffff\u0005\u0014\u0001\uffff\u0001\u0013\u0002\uffff\u00016\u00017\u0003\u0014\u0001\u0013\u0002\uffff\u0001\u0014\u0001?\u0002\u0014\u0001\uffff\u0001\u0013\u0001\u0014\u0001\uffff\u0001C\u0002\u0014\u0001\uffff\u0001F\u0001\u0014\u0001\uffff";
   static final String DFA10_eofS = "G\uffff";
   static final String DFA10_minS = "\u0001\t\u0001O\u0001N\u0001R\u0001I\u0001a\u0001O\u0001T\u0004\uffff\u0001=\u0001\uffff\u0001=\u0001E\u0001N\u00010\u0004\uffff\u0001T\u0001D\u00010\u0001K\u0001t\u0001N\u0001R\u0004\uffff\u0001T\u00040\u0001\uffff\u0001E\u0001e\u0001S\u0001U\u0001W\u0001\uffff\u00010\u0002\uffff\u00020\u0001T\u0001C\u0001E\u0001-\u0002\uffff\u00020\u0001T\u0001E\u0001\uffff\u00020\u0001\uffff\u00010\u0001N\u00010\u0001\uffff\u00010\u0001-\u0001\uffff";
   static final String DFA10_maxS = "\u0001z\u0001O\u0001N\u0001R\u0001I\u0001a\u0001O\u0001T\u0004\uffff\u0001>\u0001\uffff\u0001=\u0001E\u0001N\u0001z\u0004\uffff\u0001T\u0001D\u0001z\u0001K\u0001t\u0001N\u0001R\u0004\uffff\u0001T\u0004z\u0001\uffff\u0001E\u0001e\u0001S\u0001U\u0001W\u0001\uffff\u0001z\u0002\uffff\u0002z\u0001T\u0001C\u0001E\u0001z\u0002\uffff\u00019\u0001z\u0001T\u0001E\u0001\uffff\u0001z\u00019\u0001\uffff\u0001z\u0001N\u00019\u0001\uffff\u0001z\u0001-\u0001\uffff";
   static final String DFA10_acceptS = "\b\uffff\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\uffff\u0001\f\u0004\uffff\u0001\u0014\u0001\u0015\u0001\u0016\u0001\u0017\u0007\uffff\u0001\r\u0001\u000e\u0001\u000f\u0001\u0010\u0005\uffff\u0001\u0003\u0005\uffff\u0001\u0012\u0001\uffff\u0001\u0001\u0001\u0002\u0006\uffff\u0001\u0004\u0001\u0005\u0004\uffff\u0001\u0013\u0002\uffff\u0001\u0006\u0003\uffff\u0001\u0007\u0002\uffff\u0001\u0011";
   static final String DFA10_specialS = "G\uffff}>";
   static final String[] DFA10_transitionS = new String[]{"\u0002\u0015\u0002\uffff\u0001\u0015\u0012\uffff\u0001\u0015\u0001\r\u0001\u0012\u0004\uffff\u0001\u0012\u0001\b\u0001\t\u0002\uffff\u0001\n\u0001\u0013\u0002\uffff\n\u0011\u0002\uffff\u0001\f\u0001\u000b\u0001\u000e\u0002\uffff\u0001\u0002\u0001\u000f\u0001\u0006\u0005\u0014\u0001\u0010\u0002\u0014\u0001\u0004\u0001\u0014\u0001\u0001\u0001\u0003\u0003\u0014\u0001\u0007\u0007\u0014\u0006\uffff\u0003\u0014\u0001\u0005\u0016\u0014", "\u0001\u0016", "\u0001\u0017", "\u0001\u0018", "\u0001\u0019", "\u0001\u001a", "\u0001\u001b", "\u0001\u001c", "", "", "", "", "\u0001\u001d\u0001\r", "", "\u0001\u001f", "\u0001!", "\u0001\"", "\n#\u0007\uffff\u001a\u0014\u0004\uffff\u0001\u0014\u0001\uffff\u001a\u0014", "", "", "", "", "\u0001$", "\u0001%", "\n\u0014\u0007\uffff\u001a\u0014\u0004\uffff\u0001\u0014\u0001\uffff\u001a\u0014", "\u0001'", "\u0001(", "\u0001)", "\u0001*", "", "", "", "", "\u0001+", "\n\u0014\u0007\uffff\u001a\u0014\u0004\uffff\u0001\u0014\u0001\uffff\u001a\u0014", "\n-\u0007\uffff\u001a\u0014\u0004\uffff\u0001\u0014\u0001\uffff\u001a\u0014", "\n\u0014\u0007\uffff\u001a\u0014\u0004\uffff\u0001\u0014\u0001\uffff\u001a\u0014", "\n\u0014\u0007\uffff\u001a\u0014\u0004\uffff\u0001\u0014\u0001\uffff\u001a\u0014", "", "\u00010", "\u00011", "\u00012", "\u00013", "\u00014", "", "\n5\u0007\uffff\u001a\u0014\u0004\uffff\u0001\u0014\u0001\uffff\u001a\u0014", "", "", "\n\u0014\u0007\uffff\u001a\u0014\u0004\uffff\u0001\u0014\u0001\uffff\u001a\u0014", "\n8\u0007\uffff\u001a\u0014\u0004\uffff\u0001\u0014\u0001\uffff\u001a\u0014", "\u00019", "\u0001:", "\u0001;", "\u0001<\u0002\uffff\n=\u0007\uffff\u001a\u0014\u0004\uffff\u0001\u0014\u0001\uffff\u001a\u0014", "", "", "\n>", "\n\u0014\u0007\uffff\u001a\u0014\u0004\uffff\u0001\u0014\u0001\uffff\u001a\u0014", "\u0001@", "\u0001A", "", "\n=\u0007\uffff\u001a\u0014\u0004\uffff\u0001\u0014\u0001\uffff\u001a\u0014", "\nB", "", "\n\u0014\u0007\uffff\u001a\u0014\u0004\uffff\u0001\u0014\u0001\uffff\u001a\u0014", "\u0001D", "\nE", "", "\n\u0014\u0007\uffff\u001a\u0014\u0004\uffff\u0001\u0014\u0001\uffff\u001a\u0014", "\u0001<", ""};
   static final short[] DFA10_eot = DFA.unpackEncodedString("\u0001\uffff\u0007\u0014\u0004\uffff\u0001\u001e\u0001\uffff\u0001 \u0002\u0014\u0001\u0013\u0004\uffff\u0002\u0014\u0001&\u0004\u0014\u0004\uffff\u0001\u0014\u0001,\u0001\u0013\u0001.\u0001/\u0001\uffff\u0005\u0014\u0001\uffff\u0001\u0013\u0002\uffff\u00016\u00017\u0003\u0014\u0001\u0013\u0002\uffff\u0001\u0014\u0001?\u0002\u0014\u0001\uffff\u0001\u0013\u0001\u0014\u0001\uffff\u0001C\u0002\u0014\u0001\uffff\u0001F\u0001\u0014\u0001\uffff");
   static final short[] DFA10_eof = DFA.unpackEncodedString("G\uffff");
   static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars("\u0001\t\u0001O\u0001N\u0001R\u0001I\u0001a\u0001O\u0001T\u0004\uffff\u0001=\u0001\uffff\u0001=\u0001E\u0001N\u00010\u0004\uffff\u0001T\u0001D\u00010\u0001K\u0001t\u0001N\u0001R\u0004\uffff\u0001T\u00040\u0001\uffff\u0001E\u0001e\u0001S\u0001U\u0001W\u0001\uffff\u00010\u0002\uffff\u00020\u0001T\u0001C\u0001E\u0001-\u0002\uffff\u00020\u0001T\u0001E\u0001\uffff\u00020\u0001\uffff\u00010\u0001N\u00010\u0001\uffff\u00010\u0001-\u0001\uffff");
   static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars("\u0001z\u0001O\u0001N\u0001R\u0001I\u0001a\u0001O\u0001T\u0004\uffff\u0001>\u0001\uffff\u0001=\u0001E\u0001N\u0001z\u0004\uffff\u0001T\u0001D\u0001z\u0001K\u0001t\u0001N\u0001R\u0004\uffff\u0001T\u0004z\u0001\uffff\u0001E\u0001e\u0001S\u0001U\u0001W\u0001\uffff\u0001z\u0002\uffff\u0002z\u0001T\u0001C\u0001E\u0001z\u0002\uffff\u00019\u0001z\u0001T\u0001E\u0001\uffff\u0001z\u00019\u0001\uffff\u0001z\u0001N\u00019\u0001\uffff\u0001z\u0001-\u0001\uffff");
   static final short[] DFA10_accept = DFA.unpackEncodedString("\b\uffff\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\uffff\u0001\f\u0004\uffff\u0001\u0014\u0001\u0015\u0001\u0016\u0001\u0017\u0007\uffff\u0001\r\u0001\u000e\u0001\u000f\u0001\u0010\u0005\uffff\u0001\u0003\u0005\uffff\u0001\u0012\u0001\uffff\u0001\u0001\u0001\u0002\u0006\uffff\u0001\u0004\u0001\u0005\u0004\uffff\u0001\u0013\u0002\uffff\u0001\u0006\u0003\uffff\u0001\u0007\u0002\uffff\u0001\u0011");
   static final short[] DFA10_special = DFA.unpackEncodedString("G\uffff}>");
   static final short[][] DFA10_transition;

   public static Date ExtractDate(String input) {
      Matcher m = datePattern.matcher(input);
      if (!m.matches()) {
         return null;
      } else {
         try {
            return new Date(((SimpleDateFormat)dateFormat.get()).parse(m.group(1)).getTime());
         } catch (ParseException var3) {
            return null;
         }
      }
   }

   public void emitErrorMessage(String msg) {
      this.errorMsg = msg;
   }

   public Lexer[] getDelegates() {
      return new Lexer[0];
   }

   public FilterLexer() {
      this.dfa10 = new DFA10(this);
   }

   public FilterLexer(CharStream input) {
      this(input, new RecognizerSharedState());
   }

   public FilterLexer(CharStream input, RecognizerSharedState state) {
      super(input, state);
      this.dfa10 = new DFA10(this);
   }

   public String getGrammarFileName() {
      return "org/apache/hadoop/hive/metastore/parser/Filter.g";
   }

   public final void mKW_NOT() throws RecognitionException {
      int _type = 19;
      int _channel = 0;
      this.match("NOT");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mKW_AND() throws RecognitionException {
      int _type = 15;
      int _channel = 0;
      this.match("AND");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mKW_OR() throws RecognitionException {
      int _type = 20;
      int _channel = 0;
      this.match("OR");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mKW_LIKE() throws RecognitionException {
      int _type = 18;
      int _channel = 0;
      this.match("LIKE");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mKW_DATE() throws RecognitionException {
      int _type = 17;
      int _channel = 0;
      this.match("date");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mKW_CONST() throws RecognitionException {
      int _type = 16;
      int _channel = 0;
      this.match("CONST");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mKW_STRUCT() throws RecognitionException {
      int _type = 21;
      int _channel = 0;
      this.match("STRUCT");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mLPAREN() throws RecognitionException {
      int _type = 24;
      int _channel = 0;
      this.match(40);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mRPAREN() throws RecognitionException {
      int _type = 27;
      int _channel = 0;
      this.match(41);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mCOMMA() throws RecognitionException {
      int _type = 5;
      int _channel = 0;
      this.match(44);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mEQUAL() throws RecognitionException {
      int _type = 9;
      int _channel = 0;
      this.match(61);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mNOTEQUAL() throws RecognitionException {
      int _type = 26;
      int _channel = 0;
      int alt1 = 2;
      int LA1_0 = this.input.LA(1);
      if (LA1_0 == 60) {
         alt1 = 1;
      } else {
         if (LA1_0 != 33) {
            NoViableAltException nvae = new NoViableAltException("", 1, 0, this.input);
            throw nvae;
         }

         alt1 = 2;
      }

      switch (alt1) {
         case 1:
            this.match("<>");
            break;
         case 2:
            this.match("!=");
      }

      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mLESSTHANOREQUALTO() throws RecognitionException {
      int _type = 23;
      int _channel = 0;
      this.match("<=");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mLESSTHAN() throws RecognitionException {
      int _type = 22;
      int _channel = 0;
      this.match(60);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mGREATERTHANOREQUALTO() throws RecognitionException {
      int _type = 11;
      int _channel = 0;
      this.match(">=");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mGREATERTHAN() throws RecognitionException {
      int _type = 10;
      int _channel = 0;
      this.match(62);
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mBETWEEN() throws RecognitionException {
      int _type = 4;
      int _channel = 0;
      this.match("BETWEEN");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mIN() throws RecognitionException {
      int _type = 12;
      int _channel = 0;
      this.match("IN");
      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mLetter() throws RecognitionException {
      if ((this.input.LA(1) < 65 || this.input.LA(1) > 90) && (this.input.LA(1) < 97 || this.input.LA(1) > 122)) {
         MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
         this.recover(mse);
         throw mse;
      } else {
         this.input.consume();
      }
   }

   public final void mDigit() throws RecognitionException {
      if (this.input.LA(1) >= 48 && this.input.LA(1) <= 57) {
         this.input.consume();
      } else {
         MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
         this.recover(mse);
         throw mse;
      }
   }

   public final void mDateString() throws RecognitionException {
      if (this.input.LA(1) >= 48 && this.input.LA(1) <= 57) {
         this.input.consume();
         if (this.input.LA(1) >= 48 && this.input.LA(1) <= 57) {
            this.input.consume();
            if (this.input.LA(1) >= 48 && this.input.LA(1) <= 57) {
               this.input.consume();
               if (this.input.LA(1) >= 48 && this.input.LA(1) <= 57) {
                  this.input.consume();
                  this.match(45);
                  if (this.input.LA(1) >= 48 && this.input.LA(1) <= 57) {
                     this.input.consume();
                     if (this.input.LA(1) >= 48 && this.input.LA(1) <= 57) {
                        this.input.consume();
                        this.match(45);
                        if (this.input.LA(1) >= 48 && this.input.LA(1) <= 57) {
                           this.input.consume();
                           if (this.input.LA(1) >= 48 && this.input.LA(1) <= 57) {
                              this.input.consume();
                           } else {
                              MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                              this.recover(mse);
                              throw mse;
                           }
                        } else {
                           MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                           this.recover(mse);
                           throw mse;
                        }
                     } else {
                        MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                        this.recover(mse);
                        throw mse;
                     }
                  } else {
                     MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                     this.recover(mse);
                     throw mse;
                  }
               } else {
                  MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                  this.recover(mse);
                  throw mse;
               }
            } else {
               MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
               this.recover(mse);
               throw mse;
            }
         } else {
            MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
            this.recover(mse);
            throw mse;
         }
      } else {
         MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
         this.recover(mse);
         throw mse;
      }
   }

   public final void mDateLiteral() throws RecognitionException {
      int _type = 6;
      int _channel = 0;
      int alt2 = 2;
      int LA2_0 = this.input.LA(1);
      if (LA2_0 == 100) {
         alt2 = 1;
      }

      switch (alt2) {
         case 1:
            this.mKW_DATE();
         default:
            this.mDateString();
            if (ExtractDate(this.getText()) == null) {
               throw new FailedPredicateException(this.input, "DateLiteral", " ExtractDate(getText()) != null ");
            } else {
               this.state.type = _type;
               this.state.channel = _channel;
            }
      }
   }

   public final void mStringLiteral() throws RecognitionException {
      int _type = 28;
      int _channel = 0;
      int alt5 = 2;
      int LA5_0 = this.input.LA(1);
      if (LA5_0 == 39) {
         alt5 = 1;
      } else {
         if (LA5_0 != 34) {
            NoViableAltException nvae = new NoViableAltException("", 5, 0, this.input);
            throw nvae;
         }

         alt5 = 2;
      }

      label101:
      switch (alt5) {
         case 1:
            this.match(39);

            while(true) {
               int alt3 = 3;
               int LA3_0 = this.input.LA(1);
               if ((LA3_0 < 0 || LA3_0 > 38) && (LA3_0 < 40 || LA3_0 > 91) && (LA3_0 < 93 || LA3_0 > 65535)) {
                  if (LA3_0 == 92) {
                     alt3 = 2;
                  }
               } else {
                  alt3 = 1;
               }

               switch (alt3) {
                  case 1:
                     if ((this.input.LA(1) < 0 || this.input.LA(1) > 38) && (this.input.LA(1) < 40 || this.input.LA(1) > 91) && (this.input.LA(1) < 93 || this.input.LA(1) > 65535)) {
                        MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                        this.recover(mse);
                        throw mse;
                     }

                     this.input.consume();
                     break;
                  case 2:
                     this.match(92);
                     this.matchAny();
                     break;
                  default:
                     this.match(39);
                     break label101;
               }
            }
         case 2:
            this.match(34);

            label99:
            while(true) {
               int alt4 = 3;
               int LA4_0 = this.input.LA(1);
               if ((LA4_0 < 0 || LA4_0 > 33) && (LA4_0 < 35 || LA4_0 > 91) && (LA4_0 < 93 || LA4_0 > 65535)) {
                  if (LA4_0 == 92) {
                     alt4 = 2;
                  }
               } else {
                  alt4 = 1;
               }

               switch (alt4) {
                  case 1:
                     if ((this.input.LA(1) < 0 || this.input.LA(1) > 33) && (this.input.LA(1) < 35 || this.input.LA(1) > 91) && (this.input.LA(1) < 93 || this.input.LA(1) > 65535)) {
                        MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                        this.recover(mse);
                        throw mse;
                     }

                     this.input.consume();
                     break;
                  case 2:
                     this.match(92);
                     this.matchAny();
                     break;
                  default:
                     this.match(34);
                     break label99;
               }
            }
      }

      this.state.type = _type;
      this.state.channel = _channel;
   }

   public final void mIntegralLiteral() throws RecognitionException {
      int _type = 14;
      int _channel = 0;
      int alt6 = 2;
      int LA6_0 = this.input.LA(1);
      if (LA6_0 == 45) {
         alt6 = 1;
      }

      switch (alt6) {
         case 1:
            this.match(45);
         default:
            int cnt7 = 0;

            while(true) {
               int alt7 = 2;
               int LA7_0 = this.input.LA(1);
               if (LA7_0 >= 48 && LA7_0 <= 57) {
                  alt7 = 1;
               }

               switch (alt7) {
                  case 1:
                     if (this.input.LA(1) < 48 || this.input.LA(1) > 57) {
                        MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                        this.recover(mse);
                        throw mse;
                     }

                     this.input.consume();
                     ++cnt7;
                     break;
                  default:
                     if (cnt7 >= 1) {
                        this.state.type = _type;
                        this.state.channel = _channel;
                        return;
                     }

                     EarlyExitException eee = new EarlyExitException(7, this.input);
                     throw eee;
               }
            }
      }
   }

   public final void mIdentifier() throws RecognitionException {
      int _type = 13;
      int _channel = 0;
      if (this.input.LA(1) >= 48 && this.input.LA(1) <= 57 || this.input.LA(1) >= 65 && this.input.LA(1) <= 90 || this.input.LA(1) >= 97 && this.input.LA(1) <= 122) {
         this.input.consume();

         while(true) {
            int alt8 = 2;
            int LA8_0 = this.input.LA(1);
            if (LA8_0 >= 48 && LA8_0 <= 57 || LA8_0 >= 65 && LA8_0 <= 90 || LA8_0 == 95 || LA8_0 >= 97 && LA8_0 <= 122) {
               alt8 = 1;
            }

            switch (alt8) {
               case 1:
                  if ((this.input.LA(1) < 48 || this.input.LA(1) > 57) && (this.input.LA(1) < 65 || this.input.LA(1) > 90) && this.input.LA(1) != 95 && (this.input.LA(1) < 97 || this.input.LA(1) > 122)) {
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
   }

   public final void mWS() throws RecognitionException {
      int _type = 29;
      int _channel = 0;
      int cnt9 = 0;

      while(true) {
         int alt9 = 2;
         int LA9_0 = this.input.LA(1);
         if (LA9_0 >= 9 && LA9_0 <= 10 || LA9_0 == 13 || LA9_0 == 32) {
            alt9 = 1;
         }

         switch (alt9) {
            case 1:
               if ((this.input.LA(1) < 9 || this.input.LA(1) > 10) && this.input.LA(1) != 13 && this.input.LA(1) != 32) {
                  MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
                  this.recover(mse);
                  throw mse;
               }

               this.input.consume();
               ++cnt9;
               break;
            default:
               if (cnt9 >= 1) {
                  this.skip();
                  this.state.type = _type;
                  this.state.channel = _channel;
                  return;
               }

               EarlyExitException eee = new EarlyExitException(9, this.input);
               throw eee;
         }
      }
   }

   public void mTokens() throws RecognitionException {
      int alt10 = 23;
      alt10 = this.dfa10.predict(this.input);
      switch (alt10) {
         case 1:
            this.mKW_NOT();
            break;
         case 2:
            this.mKW_AND();
            break;
         case 3:
            this.mKW_OR();
            break;
         case 4:
            this.mKW_LIKE();
            break;
         case 5:
            this.mKW_DATE();
            break;
         case 6:
            this.mKW_CONST();
            break;
         case 7:
            this.mKW_STRUCT();
            break;
         case 8:
            this.mLPAREN();
            break;
         case 9:
            this.mRPAREN();
            break;
         case 10:
            this.mCOMMA();
            break;
         case 11:
            this.mEQUAL();
            break;
         case 12:
            this.mNOTEQUAL();
            break;
         case 13:
            this.mLESSTHANOREQUALTO();
            break;
         case 14:
            this.mLESSTHAN();
            break;
         case 15:
            this.mGREATERTHANOREQUALTO();
            break;
         case 16:
            this.mGREATERTHAN();
            break;
         case 17:
            this.mBETWEEN();
            break;
         case 18:
            this.mIN();
            break;
         case 19:
            this.mDateLiteral();
            break;
         case 20:
            this.mStringLiteral();
            break;
         case 21:
            this.mIntegralLiteral();
            break;
         case 22:
            this.mIdentifier();
            break;
         case 23:
            this.mWS();
      }

   }

   static {
      int numStates = DFA10_transitionS.length;
      DFA10_transition = new short[numStates][];

      for(int i = 0; i < numStates; ++i) {
         DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
      }

   }

   protected class DFA10 extends DFA {
      public DFA10(BaseRecognizer recognizer) {
         this.recognizer = recognizer;
         this.decisionNumber = 10;
         this.eot = FilterLexer.DFA10_eot;
         this.eof = FilterLexer.DFA10_eof;
         this.min = FilterLexer.DFA10_min;
         this.max = FilterLexer.DFA10_max;
         this.accept = FilterLexer.DFA10_accept;
         this.special = FilterLexer.DFA10_special;
         this.transition = FilterLexer.DFA10_transition;
      }

      public String getDescription() {
         return "1:1: Tokens : ( KW_NOT | KW_AND | KW_OR | KW_LIKE | KW_DATE | KW_CONST | KW_STRUCT | LPAREN | RPAREN | COMMA | EQUAL | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN | BETWEEN | IN | DateLiteral | StringLiteral | IntegralLiteral | Identifier | WS );";
      }
   }
}
