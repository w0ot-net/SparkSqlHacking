package org.apache.hadoop.hive.metastore.parser;

import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public class FilterParser extends Parser {
   public static final String[] tokenNames = new String[]{"<invalid>", "<EOR>", "<DOWN>", "<UP>", "BETWEEN", "COMMA", "DateLiteral", "DateString", "Digit", "EQUAL", "GREATERTHAN", "GREATERTHANOREQUALTO", "IN", "Identifier", "IntegralLiteral", "KW_AND", "KW_CONST", "KW_DATE", "KW_LIKE", "KW_NOT", "KW_OR", "KW_STRUCT", "LESSTHAN", "LESSTHANOREQUALTO", "LPAREN", "Letter", "NOTEQUAL", "RPAREN", "StringLiteral", "WS"};
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
   public ExpressionTree tree;
   public static final BitSet FOLLOW_orExpression_in_filter84 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_andExpression_in_orExpression106 = new BitSet(new long[]{1048578L});
   public static final BitSet FOLLOW_KW_OR_in_orExpression109 = new BitSet(new long[]{285237312L});
   public static final BitSet FOLLOW_andExpression_in_orExpression111 = new BitSet(new long[]{1048578L});
   public static final BitSet FOLLOW_expression_in_andExpression137 = new BitSet(new long[]{32770L});
   public static final BitSet FOLLOW_KW_AND_in_andExpression140 = new BitSet(new long[]{285237312L});
   public static final BitSet FOLLOW_expression_in_andExpression142 = new BitSet(new long[]{32770L});
   public static final BitSet FOLLOW_LPAREN_in_expression169 = new BitSet(new long[]{285237312L});
   public static final BitSet FOLLOW_orExpression_in_expression171 = new BitSet(new long[]{134217728L});
   public static final BitSet FOLLOW_RPAREN_in_expression173 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_operatorExpression_in_expression185 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_betweenExpression_in_operatorExpression206 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_inExpression_in_operatorExpression218 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_multiColInExpression_in_operatorExpression230 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_binOpExpression_in_operatorExpression242 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_Identifier_in_binOpExpression293 = new BitSet(new long[]{79957504L});
   public static final BitSet FOLLOW_operator_in_binOpExpression299 = new BitSet(new long[]{64L});
   public static final BitSet FOLLOW_DateLiteral_in_binOpExpression306 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_DateLiteral_in_binOpExpression334 = new BitSet(new long[]{79957504L});
   public static final BitSet FOLLOW_operator_in_binOpExpression341 = new BitSet(new long[]{8192L});
   public static final BitSet FOLLOW_Identifier_in_binOpExpression347 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_Identifier_in_binOpExpression395 = new BitSet(new long[]{79957504L});
   public static final BitSet FOLLOW_operator_in_binOpExpression401 = new BitSet(new long[]{268435456L});
   public static final BitSet FOLLOW_StringLiteral_in_binOpExpression408 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_StringLiteral_in_binOpExpression436 = new BitSet(new long[]{79957504L});
   public static final BitSet FOLLOW_operator_in_binOpExpression443 = new BitSet(new long[]{8192L});
   public static final BitSet FOLLOW_Identifier_in_binOpExpression449 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_Identifier_in_binOpExpression497 = new BitSet(new long[]{79957504L});
   public static final BitSet FOLLOW_operator_in_binOpExpression503 = new BitSet(new long[]{16384L});
   public static final BitSet FOLLOW_IntegralLiteral_in_binOpExpression509 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_IntegralLiteral_in_binOpExpression537 = new BitSet(new long[]{79957504L});
   public static final BitSet FOLLOW_operator_in_binOpExpression543 = new BitSet(new long[]{8192L});
   public static final BitSet FOLLOW_Identifier_in_binOpExpression549 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_set_in_operator597 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_Identifier_in_betweenExpression662 = new BitSet(new long[]{524304L});
   public static final BitSet FOLLOW_KW_NOT_in_betweenExpression665 = new BitSet(new long[]{16L});
   public static final BitSet FOLLOW_BETWEEN_in_betweenExpression672 = new BitSet(new long[]{268451904L});
   public static final BitSet FOLLOW_DateLiteral_in_betweenExpression697 = new BitSet(new long[]{32768L});
   public static final BitSet FOLLOW_KW_AND_in_betweenExpression699 = new BitSet(new long[]{64L});
   public static final BitSet FOLLOW_DateLiteral_in_betweenExpression705 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_StringLiteral_in_betweenExpression735 = new BitSet(new long[]{32768L});
   public static final BitSet FOLLOW_KW_AND_in_betweenExpression737 = new BitSet(new long[]{268435456L});
   public static final BitSet FOLLOW_StringLiteral_in_betweenExpression743 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_IntegralLiteral_in_betweenExpression773 = new BitSet(new long[]{32768L});
   public static final BitSet FOLLOW_KW_AND_in_betweenExpression775 = new BitSet(new long[]{16384L});
   public static final BitSet FOLLOW_IntegralLiteral_in_betweenExpression781 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_LPAREN_in_inExpression836 = new BitSet(new long[]{8192L});
   public static final BitSet FOLLOW_Identifier_in_inExpression842 = new BitSet(new long[]{134217728L});
   public static final BitSet FOLLOW_RPAREN_in_inExpression844 = new BitSet(new long[]{528384L});
   public static final BitSet FOLLOW_KW_NOT_in_inExpression848 = new BitSet(new long[]{4096L});
   public static final BitSet FOLLOW_IN_in_inExpression855 = new BitSet(new long[]{16777216L});
   public static final BitSet FOLLOW_LPAREN_in_inExpression857 = new BitSet(new long[]{268451904L});
   public static final BitSet FOLLOW_DateLiteral_in_inExpression903 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_COMMA_in_inExpression961 = new BitSet(new long[]{64L});
   public static final BitSet FOLLOW_DateLiteral_in_inExpression967 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_StringLiteral_in_inExpression1072 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_COMMA_in_inExpression1130 = new BitSet(new long[]{268435456L});
   public static final BitSet FOLLOW_StringLiteral_in_inExpression1136 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_IntegralLiteral_in_inExpression1241 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_COMMA_in_inExpression1299 = new BitSet(new long[]{16384L});
   public static final BitSet FOLLOW_IntegralLiteral_in_inExpression1305 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_RPAREN_in_inExpression1372 = new BitSet(new long[]{2L});
   public static final BitSet FOLLOW_LPAREN_in_multiColInExpression1415 = new BitSet(new long[]{2097152L});
   public static final BitSet FOLLOW_KW_STRUCT_in_multiColInExpression1439 = new BitSet(new long[]{16777216L});
   public static final BitSet FOLLOW_LPAREN_in_multiColInExpression1441 = new BitSet(new long[]{8192L});
   public static final BitSet FOLLOW_Identifier_in_multiColInExpression1447 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_COMMA_in_multiColInExpression1493 = new BitSet(new long[]{8192L});
   public static final BitSet FOLLOW_Identifier_in_multiColInExpression1499 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_RPAREN_in_multiColInExpression1534 = new BitSet(new long[]{134217728L});
   public static final BitSet FOLLOW_RPAREN_in_multiColInExpression1546 = new BitSet(new long[]{528384L});
   public static final BitSet FOLLOW_KW_NOT_in_multiColInExpression1550 = new BitSet(new long[]{4096L});
   public static final BitSet FOLLOW_IN_in_multiColInExpression1557 = new BitSet(new long[]{16777216L});
   public static final BitSet FOLLOW_LPAREN_in_multiColInExpression1559 = new BitSet(new long[]{65536L});
   public static final BitSet FOLLOW_KW_CONST_in_multiColInExpression1561 = new BitSet(new long[]{2097152L});
   public static final BitSet FOLLOW_KW_STRUCT_in_multiColInExpression1563 = new BitSet(new long[]{16777216L});
   public static final BitSet FOLLOW_LPAREN_in_multiColInExpression1565 = new BitSet(new long[]{268451904L});
   public static final BitSet FOLLOW_DateLiteral_in_multiColInExpression1603 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_StringLiteral_in_multiColInExpression1637 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_IntegralLiteral_in_multiColInExpression1671 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_COMMA_in_multiColInExpression1719 = new BitSet(new long[]{268451904L});
   public static final BitSet FOLLOW_DateLiteral_in_multiColInExpression1755 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_StringLiteral_in_multiColInExpression1797 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_IntegralLiteral_in_multiColInExpression1839 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_RPAREN_in_multiColInExpression1902 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_COMMA_in_multiColInExpression1926 = new BitSet(new long[]{65536L});
   public static final BitSet FOLLOW_KW_CONST_in_multiColInExpression1928 = new BitSet(new long[]{2097152L});
   public static final BitSet FOLLOW_KW_STRUCT_in_multiColInExpression1930 = new BitSet(new long[]{16777216L});
   public static final BitSet FOLLOW_LPAREN_in_multiColInExpression1932 = new BitSet(new long[]{268451904L});
   public static final BitSet FOLLOW_DateLiteral_in_multiColInExpression1982 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_StringLiteral_in_multiColInExpression2024 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_IntegralLiteral_in_multiColInExpression2066 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_COMMA_in_multiColInExpression2130 = new BitSet(new long[]{268451904L});
   public static final BitSet FOLLOW_DateLiteral_in_multiColInExpression2174 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_StringLiteral_in_multiColInExpression2224 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_IntegralLiteral_in_multiColInExpression2274 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_RPAREN_in_multiColInExpression2357 = new BitSet(new long[]{134217760L});
   public static final BitSet FOLLOW_RPAREN_in_multiColInExpression2370 = new BitSet(new long[]{2L});

   public Parser[] getDelegates() {
      return new Parser[0];
   }

   public FilterParser(TokenStream input) {
      this(input, new RecognizerSharedState());
   }

   public FilterParser(TokenStream input, RecognizerSharedState state) {
      super(input, state);
      this.tree = new ExpressionTree();
   }

   public String[] getTokenNames() {
      return tokenNames;
   }

   public String getGrammarFileName() {
      return "org/apache/hadoop/hive/metastore/parser/Filter.g";
   }

   public static String TrimQuotes(String input) {
      return input.length() <= 1 || (input.charAt(0) != '"' || input.charAt(input.length() - 1) != '"') && (input.charAt(0) != '\'' || input.charAt(input.length() - 1) != '\'') ? input : input.substring(1, input.length() - 1);
   }

   public final void filter() throws RecognitionException {
      try {
         this.pushFollow(FOLLOW_orExpression_in_filter84);
         this.orExpression();
         --this.state._fsp;
      } catch (RecognitionException e) {
         throw e;
      } finally {
         ;
      }
   }

   public final void orExpression() throws RecognitionException {
      try {
         this.pushFollow(FOLLOW_andExpression_in_orExpression106);
         this.andExpression();
         --this.state._fsp;

         while(true) {
            int alt1 = 2;
            int LA1_0 = this.input.LA(1);
            if (LA1_0 == 20) {
               alt1 = 1;
            }

            switch (alt1) {
               case 1:
                  this.match(this.input, 20, FOLLOW_KW_OR_in_orExpression109);
                  this.pushFollow(FOLLOW_andExpression_in_orExpression111);
                  this.andExpression();
                  --this.state._fsp;
                  this.tree.addIntermediateNode(ExpressionTree.LogicalOperator.OR);
                  break;
               default:
                  return;
            }
         }
      } catch (RecognitionException e) {
         throw e;
      } finally {
         ;
      }
   }

   public final void andExpression() throws RecognitionException {
      try {
         this.pushFollow(FOLLOW_expression_in_andExpression137);
         this.expression();
         --this.state._fsp;

         while(true) {
            int alt2 = 2;
            int LA2_0 = this.input.LA(1);
            if (LA2_0 == 15) {
               alt2 = 1;
            }

            switch (alt2) {
               case 1:
                  this.match(this.input, 15, FOLLOW_KW_AND_in_andExpression140);
                  this.pushFollow(FOLLOW_expression_in_andExpression142);
                  this.expression();
                  --this.state._fsp;
                  this.tree.addIntermediateNode(ExpressionTree.LogicalOperator.AND);
                  break;
               default:
                  return;
            }
         }
      } catch (RecognitionException e) {
         throw e;
      } finally {
         ;
      }
   }

   public final void expression() throws RecognitionException {
      try {
         int alt3 = 2;
         int LA3_0 = this.input.LA(1);
         if (LA3_0 == 24) {
            switch (this.input.LA(2)) {
               case 6:
               case 14:
               case 24:
               case 28:
                  alt3 = 1;
                  break;
               case 13:
                  int LA3_6 = this.input.LA(3);
                  if (LA3_6 == 27) {
                     alt3 = 2;
                  } else {
                     if (LA3_6 != 4 && (LA3_6 < 9 || LA3_6 > 11) && (LA3_6 < 18 || LA3_6 > 19) && (LA3_6 < 22 || LA3_6 > 23) && LA3_6 != 26) {
                        int nvaeMark = this.input.mark();

                        try {
                           for(int nvaeConsume = 0; nvaeConsume < 2; ++nvaeConsume) {
                              this.input.consume();
                           }

                           NoViableAltException nvae = new NoViableAltException("", 3, 6, this.input);
                           throw nvae;
                        } finally {
                           this.input.rewind(nvaeMark);
                        }
                     }

                     alt3 = 1;
                  }
                  break;
               case 21:
                  alt3 = 2;
                  break;
               default:
                  int nvaeMark = this.input.mark();

                  try {
                     this.input.consume();
                     NoViableAltException nvae = new NoViableAltException("", 3, 1, this.input);
                     throw nvae;
                  } finally {
                     this.input.rewind(nvaeMark);
                  }
            }
         } else {
            if (LA3_0 != 6 && (LA3_0 < 13 || LA3_0 > 14) && LA3_0 != 28) {
               NoViableAltException nvae = new NoViableAltException("", 3, 0, this.input);
               throw nvae;
            }

            alt3 = 2;
         }

         switch (alt3) {
            case 1:
               this.match(this.input, 24, FOLLOW_LPAREN_in_expression169);
               this.pushFollow(FOLLOW_orExpression_in_expression171);
               this.orExpression();
               --this.state._fsp;
               this.match(this.input, 27, FOLLOW_RPAREN_in_expression173);
               break;
            case 2:
               this.pushFollow(FOLLOW_operatorExpression_in_expression185);
               this.operatorExpression();
               --this.state._fsp;
         }

      } catch (RecognitionException e) {
         throw e;
      } finally {
         ;
      }
   }

   public final void operatorExpression() throws RecognitionException {
      try {
         int alt4 = 4;
         switch (this.input.LA(1)) {
            case 6:
            case 14:
            case 28:
               alt4 = 4;
               break;
            case 13:
               int LA4_1 = this.input.LA(2);
               if (LA4_1 != 4 && LA4_1 != 19) {
                  if ((LA4_1 < 9 || LA4_1 > 11) && LA4_1 != 18 && (LA4_1 < 22 || LA4_1 > 23) && LA4_1 != 26) {
                     int nvaeMark = this.input.mark();

                     try {
                        this.input.consume();
                        NoViableAltException nvae = new NoViableAltException("", 4, 1, this.input);
                        throw nvae;
                     } finally {
                        this.input.rewind(nvaeMark);
                     }
                  }

                  alt4 = 4;
               } else {
                  alt4 = 1;
               }
               break;
            case 24:
               int LA4_2 = this.input.LA(2);
               if (LA4_2 == 13) {
                  alt4 = 2;
               } else {
                  if (LA4_2 != 21) {
                     int nvaeMark = this.input.mark();

                     try {
                        this.input.consume();
                        NoViableAltException nvae = new NoViableAltException("", 4, 2, this.input);
                        throw nvae;
                     } finally {
                        this.input.rewind(nvaeMark);
                     }
                  }

                  alt4 = 3;
               }
               break;
            default:
               NoViableAltException nvae = new NoViableAltException("", 4, 0, this.input);
               throw nvae;
         }

         switch (alt4) {
            case 1:
               this.pushFollow(FOLLOW_betweenExpression_in_operatorExpression206);
               this.betweenExpression();
               --this.state._fsp;
               break;
            case 2:
               this.pushFollow(FOLLOW_inExpression_in_operatorExpression218);
               this.inExpression();
               --this.state._fsp;
               break;
            case 3:
               this.pushFollow(FOLLOW_multiColInExpression_in_operatorExpression230);
               this.multiColInExpression();
               --this.state._fsp;
               break;
            case 4:
               this.pushFollow(FOLLOW_binOpExpression_in_operatorExpression242);
               this.binOpExpression();
               --this.state._fsp;
         }

      } catch (RecognitionException e) {
         throw e;
      } finally {
         ;
      }
   }

   public final void binOpExpression() throws RecognitionException {
      Token key = null;
      Token value = null;
      ExpressionTree.Operator op = null;
      boolean isReverseOrder = false;
      Object val = null;

      try {
         int alt8;
         alt8 = 3;
         label483:
         switch (this.input.LA(1)) {
            case 6:
               alt8 = 1;
               break;
            case 13:
               int LA8_1 = this.input.LA(2);
               if ((LA8_1 < 9 || LA8_1 > 11) && LA8_1 != 18 && (LA8_1 < 22 || LA8_1 > 23) && LA8_1 != 26) {
                  int nvaeMark = this.input.mark();

                  try {
                     this.input.consume();
                     NoViableAltException nvae = new NoViableAltException("", 8, 1, this.input);
                     throw nvae;
                  } finally {
                     this.input.rewind(nvaeMark);
                  }
               }

               switch (this.input.LA(3)) {
                  case 6:
                     alt8 = 1;
                     break label483;
                  case 14:
                     alt8 = 3;
                     break label483;
                  case 28:
                     alt8 = 2;
                     break label483;
                  default:
                     int nvaeMark = this.input.mark();

                     try {
                        for(int nvaeConsume = 0; nvaeConsume < 2; ++nvaeConsume) {
                           this.input.consume();
                        }

                        NoViableAltException nvae = new NoViableAltException("", 8, 5, this.input);
                        throw nvae;
                     } finally {
                        this.input.rewind(nvaeMark);
                     }
               }
            case 14:
               alt8 = 3;
               break;
            case 28:
               alt8 = 2;
               break;
            default:
               NoViableAltException nvae = new NoViableAltException("", 8, 0, this.input);
               throw nvae;
         }

         switch (alt8) {
            case 1:
               int alt5 = 2;
               int LA5_0 = this.input.LA(1);
               if (LA5_0 == 13) {
                  alt5 = 1;
               } else {
                  if (LA5_0 != 6) {
                     NoViableAltException nvae = new NoViableAltException("", 5, 0, this.input);
                     throw nvae;
                  }

                  alt5 = 2;
               }

               switch (alt5) {
                  case 1:
                     key = (Token)this.match(this.input, 13, FOLLOW_Identifier_in_binOpExpression293);
                     this.pushFollow(FOLLOW_operator_in_binOpExpression299);
                     op = this.operator();
                     --this.state._fsp;
                     value = (Token)this.match(this.input, 6, FOLLOW_DateLiteral_in_binOpExpression306);
                     break;
                  case 2:
                     value = (Token)this.match(this.input, 6, FOLLOW_DateLiteral_in_binOpExpression334);
                     this.pushFollow(FOLLOW_operator_in_binOpExpression341);
                     op = this.operator();
                     --this.state._fsp;
                     key = (Token)this.match(this.input, 13, FOLLOW_Identifier_in_binOpExpression347);
                     isReverseOrder = true;
               }

               val = FilterLexer.ExtractDate(value.getText());
               break;
            case 2:
               int alt6 = 2;
               int LA6_0 = this.input.LA(1);
               if (LA6_0 == 13) {
                  alt6 = 1;
               } else {
                  if (LA6_0 != 28) {
                     NoViableAltException nvae = new NoViableAltException("", 6, 0, this.input);
                     throw nvae;
                  }

                  alt6 = 2;
               }

               switch (alt6) {
                  case 1:
                     key = (Token)this.match(this.input, 13, FOLLOW_Identifier_in_binOpExpression395);
                     this.pushFollow(FOLLOW_operator_in_binOpExpression401);
                     op = this.operator();
                     --this.state._fsp;
                     value = (Token)this.match(this.input, 28, FOLLOW_StringLiteral_in_binOpExpression408);
                     break;
                  case 2:
                     value = (Token)this.match(this.input, 28, FOLLOW_StringLiteral_in_binOpExpression436);
                     this.pushFollow(FOLLOW_operator_in_binOpExpression443);
                     op = this.operator();
                     --this.state._fsp;
                     key = (Token)this.match(this.input, 13, FOLLOW_Identifier_in_binOpExpression449);
                     isReverseOrder = true;
               }

               val = TrimQuotes(value.getText());
               break;
            case 3:
               int alt7 = 2;
               int LA7_0 = this.input.LA(1);
               if (LA7_0 == 13) {
                  alt7 = 1;
               } else {
                  if (LA7_0 != 14) {
                     NoViableAltException nvae = new NoViableAltException("", 7, 0, this.input);
                     throw nvae;
                  }

                  alt7 = 2;
               }

               switch (alt7) {
                  case 1:
                     key = (Token)this.match(this.input, 13, FOLLOW_Identifier_in_binOpExpression497);
                     this.pushFollow(FOLLOW_operator_in_binOpExpression503);
                     op = this.operator();
                     --this.state._fsp;
                     value = (Token)this.match(this.input, 14, FOLLOW_IntegralLiteral_in_binOpExpression509);
                     break;
                  case 2:
                     value = (Token)this.match(this.input, 14, FOLLOW_IntegralLiteral_in_binOpExpression537);
                     this.pushFollow(FOLLOW_operator_in_binOpExpression543);
                     op = this.operator();
                     --this.state._fsp;
                     key = (Token)this.match(this.input, 13, FOLLOW_Identifier_in_binOpExpression549);
                     isReverseOrder = true;
               }

               val = Long.parseLong(value.getText());
         }

         ExpressionTree.LeafNode node = new ExpressionTree.LeafNode();
         node.keyName = key.getText();
         node.value = val;
         node.operator = op;
         node.isReverseOrder = isReverseOrder;
         this.tree.addLeafNode(node);
      } catch (RecognitionException e) {
         throw e;
      } finally {
         ;
      }
   }

   public final ExpressionTree.Operator operator() throws RecognitionException {
      ExpressionTree.Operator op = null;
      Token t = null;

      try {
         t = this.input.LT(1);
         if ((this.input.LA(1) < 9 || this.input.LA(1) > 11) && this.input.LA(1) != 18 && (this.input.LA(1) < 22 || this.input.LA(1) > 23) && this.input.LA(1) != 26) {
            MismatchedSetException mse = new MismatchedSetException((BitSet)null, this.input);
            throw mse;
         } else {
            this.input.consume();
            this.state.errorRecovery = false;
            op = ExpressionTree.Operator.fromString(t.getText().toUpperCase());
            return op;
         }
      } catch (RecognitionException e) {
         throw e;
      } finally {
         ;
      }
   }

   public final void betweenExpression() throws RecognitionException {
      Token key = null;
      Token left = null;
      Token right = null;
      Object leftV = null;
      Object rightV = null;
      boolean isPositive = true;

      try {
         key = (Token)this.match(this.input, 13, FOLLOW_Identifier_in_betweenExpression662);
         int alt9 = 2;
         int LA9_0 = this.input.LA(1);
         if (LA9_0 == 19) {
            alt9 = 1;
         }

         switch (alt9) {
            case 1:
               this.match(this.input, 19, FOLLOW_KW_NOT_in_betweenExpression665);
               isPositive = false;
            default:
               this.match(this.input, 4, FOLLOW_BETWEEN_in_betweenExpression672);
               int alt10 = 3;
               switch (this.input.LA(1)) {
                  case 6:
                     alt10 = 1;
                     break;
                  case 14:
                     alt10 = 3;
                     break;
                  case 28:
                     alt10 = 2;
                     break;
                  default:
                     NoViableAltException nvae = new NoViableAltException("", 10, 0, this.input);
                     throw nvae;
               }

               switch (alt10) {
                  case 1:
                     left = (Token)this.match(this.input, 6, FOLLOW_DateLiteral_in_betweenExpression697);
                     this.match(this.input, 15, FOLLOW_KW_AND_in_betweenExpression699);
                     right = (Token)this.match(this.input, 6, FOLLOW_DateLiteral_in_betweenExpression705);
                     leftV = FilterLexer.ExtractDate(left.getText());
                     rightV = FilterLexer.ExtractDate(right.getText());
                     break;
                  case 2:
                     left = (Token)this.match(this.input, 28, FOLLOW_StringLiteral_in_betweenExpression735);
                     this.match(this.input, 15, FOLLOW_KW_AND_in_betweenExpression737);
                     right = (Token)this.match(this.input, 28, FOLLOW_StringLiteral_in_betweenExpression743);
                     leftV = TrimQuotes(left.getText());
                     rightV = TrimQuotes(right.getText());
                     break;
                  case 3:
                     left = (Token)this.match(this.input, 14, FOLLOW_IntegralLiteral_in_betweenExpression773);
                     this.match(this.input, 15, FOLLOW_KW_AND_in_betweenExpression775);
                     right = (Token)this.match(this.input, 14, FOLLOW_IntegralLiteral_in_betweenExpression781);
                     leftV = Long.parseLong(left.getText());
                     rightV = Long.parseLong(right.getText());
               }

               ExpressionTree.LeafNode leftNode = new ExpressionTree.LeafNode();
               ExpressionTree.LeafNode rightNode = new ExpressionTree.LeafNode();
               leftNode.keyName = rightNode.keyName = key.getText();
               leftNode.value = leftV;
               rightNode.value = rightV;
               leftNode.operator = isPositive ? ExpressionTree.Operator.GREATERTHANOREQUALTO : ExpressionTree.Operator.LESSTHAN;
               rightNode.operator = isPositive ? ExpressionTree.Operator.LESSTHANOREQUALTO : ExpressionTree.Operator.GREATERTHAN;
               this.tree.addLeafNode(leftNode);
               this.tree.addLeafNode(rightNode);
               this.tree.addIntermediateNode(isPositive ? ExpressionTree.LogicalOperator.AND : ExpressionTree.LogicalOperator.OR);
         }
      } catch (RecognitionException e) {
         throw e;
      } finally {
         ;
      }
   }

   public final void inExpression() throws RecognitionException {
      Token key = null;
      Token constant = null;
      List constants = new ArrayList();
      Object constantV = null;
      boolean isPositive = true;

      try {
         this.match(this.input, 24, FOLLOW_LPAREN_in_inExpression836);
         key = (Token)this.match(this.input, 13, FOLLOW_Identifier_in_inExpression842);
         this.match(this.input, 27, FOLLOW_RPAREN_in_inExpression844);
         int alt11 = 2;
         int LA11_0 = this.input.LA(1);
         if (LA11_0 == 19) {
            alt11 = 1;
         }

         switch (alt11) {
            case 1:
               this.match(this.input, 19, FOLLOW_KW_NOT_in_inExpression848);
               isPositive = false;
            default:
               this.match(this.input, 12, FOLLOW_IN_in_inExpression855);
               this.match(this.input, 24, FOLLOW_LPAREN_in_inExpression857);
               int alt15 = 3;
               switch (this.input.LA(1)) {
                  case 6:
                     alt15 = 1;
                     break;
                  case 14:
                     alt15 = 3;
                     break;
                  case 28:
                     alt15 = 2;
                     break;
                  default:
                     NoViableAltException nvae = new NoViableAltException("", 15, 0, this.input);
                     throw nvae;
               }

               label183:
               switch (alt15) {
                  case 1:
                     constant = (Token)this.match(this.input, 6, FOLLOW_DateLiteral_in_inExpression903);
                     Object var27 = FilterLexer.ExtractDate(constant.getText());
                     constants.add(var27);

                     while(true) {
                        int alt12 = 2;
                        int LA12_0 = this.input.LA(1);
                        if (LA12_0 == 5) {
                           alt12 = 1;
                        }

                        switch (alt12) {
                           case 1:
                              this.match(this.input, 5, FOLLOW_COMMA_in_inExpression961);
                              constant = (Token)this.match(this.input, 6, FOLLOW_DateLiteral_in_inExpression967);
                              var27 = FilterLexer.ExtractDate(constant.getText());
                              constants.add(var27);
                              break;
                           default:
                              break label183;
                        }
                     }
                  case 2:
                     constant = (Token)this.match(this.input, 28, FOLLOW_StringLiteral_in_inExpression1072);
                     Object var25 = TrimQuotes(constant.getText());
                     constants.add(var25);

                     while(true) {
                        int alt13 = 2;
                        int LA13_0 = this.input.LA(1);
                        if (LA13_0 == 5) {
                           alt13 = 1;
                        }

                        switch (alt13) {
                           case 1:
                              this.match(this.input, 5, FOLLOW_COMMA_in_inExpression1130);
                              constant = (Token)this.match(this.input, 28, FOLLOW_StringLiteral_in_inExpression1136);
                              var25 = TrimQuotes(constant.getText());
                              constants.add(var25);
                              break;
                           default:
                              break label183;
                        }
                     }
                  case 3:
                     constant = (Token)this.match(this.input, 14, FOLLOW_IntegralLiteral_in_inExpression1241);
                     Object var23 = Long.parseLong(constant.getText());
                     constants.add(var23);

                     label167:
                     while(true) {
                        int alt14 = 2;
                        int LA14_0 = this.input.LA(1);
                        if (LA14_0 == 5) {
                           alt14 = 1;
                        }

                        switch (alt14) {
                           case 1:
                              this.match(this.input, 5, FOLLOW_COMMA_in_inExpression1299);
                              constant = (Token)this.match(this.input, 14, FOLLOW_IntegralLiteral_in_inExpression1305);
                              var23 = Long.parseLong(constant.getText());
                              constants.add(var23);
                              break;
                           default:
                              break label167;
                        }
                     }
               }

               this.match(this.input, 27, FOLLOW_RPAREN_in_inExpression1372);

               for(int i = 0; i < constants.size(); ++i) {
                  Object value = constants.get(i);
                  ExpressionTree.LeafNode leaf = new ExpressionTree.LeafNode();
                  leaf.keyName = key.getText();
                  leaf.value = value;
                  leaf.operator = isPositive ? ExpressionTree.Operator.EQUALS : ExpressionTree.Operator.NOTEQUALS2;
                  this.tree.addLeafNode(leaf);
                  if (i != 0) {
                     this.tree.addIntermediateNode(isPositive ? ExpressionTree.LogicalOperator.OR : ExpressionTree.LogicalOperator.AND);
                  }
               }

         }
      } catch (RecognitionException e) {
         throw e;
      } finally {
         ;
      }
   }

   public final void multiColInExpression() throws RecognitionException {
      Token key = null;
      Token constant = null;
      List<String> keyNames = new ArrayList();
      List constants = new ArrayList();
      String keyV = null;
      Object constantV = null;
      boolean isPositive = true;

      try {
         this.match(this.input, 24, FOLLOW_LPAREN_in_multiColInExpression1415);
         this.match(this.input, 21, FOLLOW_KW_STRUCT_in_multiColInExpression1439);
         this.match(this.input, 24, FOLLOW_LPAREN_in_multiColInExpression1441);
         key = (Token)this.match(this.input, 13, FOLLOW_Identifier_in_multiColInExpression1447);
         keyV = key.getText();
         keyNames.add(keyV);

         while(true) {
            int alt16 = 2;
            int LA16_0 = this.input.LA(1);
            if (LA16_0 == 5) {
               alt16 = 1;
            }

            switch (alt16) {
               case 1:
                  this.match(this.input, 5, FOLLOW_COMMA_in_multiColInExpression1493);
                  key = (Token)this.match(this.input, 13, FOLLOW_Identifier_in_multiColInExpression1499);
                  keyV = key.getText();
                  keyNames.add(keyV);
                  break;
               default:
                  this.match(this.input, 27, FOLLOW_RPAREN_in_multiColInExpression1534);
                  this.match(this.input, 27, FOLLOW_RPAREN_in_multiColInExpression1546);
                  alt16 = 2;
                  LA16_0 = this.input.LA(1);
                  if (LA16_0 == 19) {
                     alt16 = 1;
                  }

                  List partialConstants;
                  switch (alt16) {
                     case 1:
                        this.match(this.input, 19, FOLLOW_KW_NOT_in_multiColInExpression1550);
                        isPositive = false;
                     default:
                        this.match(this.input, 12, FOLLOW_IN_in_multiColInExpression1557);
                        this.match(this.input, 24, FOLLOW_LPAREN_in_multiColInExpression1559);
                        this.match(this.input, 16, FOLLOW_KW_CONST_in_multiColInExpression1561);
                        this.match(this.input, 21, FOLLOW_KW_STRUCT_in_multiColInExpression1563);
                        this.match(this.input, 24, FOLLOW_LPAREN_in_multiColInExpression1565);
                        partialConstants = new ArrayList();
                        int alt18 = 3;
                        switch (this.input.LA(1)) {
                           case 6:
                              alt18 = 1;
                              break;
                           case 14:
                              alt18 = 3;
                              break;
                           case 28:
                              alt18 = 2;
                              break;
                           default:
                              NoViableAltException nvae = new NoViableAltException("", 18, 0, this.input);
                              throw nvae;
                        }

                        switch (alt18) {
                           case 1:
                              constant = (Token)this.match(this.input, 6, FOLLOW_DateLiteral_in_multiColInExpression1603);
                              Object var43 = FilterLexer.ExtractDate(constant.getText());
                              partialConstants.add(var43);
                              break;
                           case 2:
                              constant = (Token)this.match(this.input, 28, FOLLOW_StringLiteral_in_multiColInExpression1637);
                              Object var42 = TrimQuotes(constant.getText());
                              partialConstants.add(var42);
                              break;
                           case 3:
                              constant = (Token)this.match(this.input, 14, FOLLOW_IntegralLiteral_in_multiColInExpression1671);
                              Object var41 = Long.parseLong(constant.getText());
                              partialConstants.add(var41);
                        }
                  }

                  while(true) {
                     int alt20 = 2;
                     int LA20_0 = this.input.LA(1);
                     if (LA20_0 == 5) {
                        alt20 = 1;
                     }

                     switch (alt20) {
                        case 1:
                           this.match(this.input, 5, FOLLOW_COMMA_in_multiColInExpression1719);
                           int alt19 = 3;
                           switch (this.input.LA(1)) {
                              case 6:
                                 alt19 = 1;
                                 break;
                              case 14:
                                 alt19 = 3;
                                 break;
                              case 28:
                                 alt19 = 2;
                                 break;
                              default:
                                 NoViableAltException nvae = new NoViableAltException("", 19, 0, this.input);
                                 throw nvae;
                           }

                           switch (alt19) {
                              case 1:
                                 constant = (Token)this.match(this.input, 6, FOLLOW_DateLiteral_in_multiColInExpression1755);
                                 Object var46 = FilterLexer.ExtractDate(constant.getText());
                                 partialConstants.add(var46);
                                 continue;
                              case 2:
                                 constant = (Token)this.match(this.input, 28, FOLLOW_StringLiteral_in_multiColInExpression1797);
                                 Object var45 = TrimQuotes(constant.getText());
                                 partialConstants.add(var45);
                                 continue;
                              case 3:
                                 constant = (Token)this.match(this.input, 14, FOLLOW_IntegralLiteral_in_multiColInExpression1839);
                                 Object var44 = Long.parseLong(constant.getText());
                                 partialConstants.add(var44);
                              default:
                                 continue;
                           }
                        default:
                           constants.add(partialConstants);
                           this.match(this.input, 27, FOLLOW_RPAREN_in_multiColInExpression1902);

                           label324:
                           while(true) {
                              alt20 = 2;
                              LA20_0 = this.input.LA(1);
                              if (LA20_0 == 5) {
                                 alt20 = 1;
                              }

                              label314:
                              switch (alt20) {
                                 case 1:
                                    this.match(this.input, 5, FOLLOW_COMMA_in_multiColInExpression1926);
                                    this.match(this.input, 16, FOLLOW_KW_CONST_in_multiColInExpression1928);
                                    this.match(this.input, 21, FOLLOW_KW_STRUCT_in_multiColInExpression1930);
                                    this.match(this.input, 24, FOLLOW_LPAREN_in_multiColInExpression1932);
                                    partialConstants = new ArrayList();
                                    int alt21 = 3;
                                    switch (this.input.LA(1)) {
                                       case 6:
                                          alt21 = 1;
                                          break;
                                       case 14:
                                          alt21 = 3;
                                          break;
                                       case 28:
                                          alt21 = 2;
                                          break;
                                       default:
                                          NoViableAltException nvae = new NoViableAltException("", 21, 0, this.input);
                                          throw nvae;
                                    }

                                    switch (alt21) {
                                       case 1:
                                          constant = (Token)this.match(this.input, 6, FOLLOW_DateLiteral_in_multiColInExpression1982);
                                          Object var49 = FilterLexer.ExtractDate(constant.getText());
                                          partialConstants.add(var49);
                                          break label314;
                                       case 2:
                                          constant = (Token)this.match(this.input, 28, FOLLOW_StringLiteral_in_multiColInExpression2024);
                                          Object var48 = TrimQuotes(constant.getText());
                                          partialConstants.add(var48);
                                          break label314;
                                       case 3:
                                          constant = (Token)this.match(this.input, 14, FOLLOW_IntegralLiteral_in_multiColInExpression2066);
                                          Object var47 = Long.parseLong(constant.getText());
                                          partialConstants.add(var47);
                                       default:
                                          break label314;
                                    }
                                 default:
                                    this.match(this.input, 27, FOLLOW_RPAREN_in_multiColInExpression2370);

                                    for(int i = 0; i < constants.size(); ++i) {
                                       List list = (List)constants.get(i);

                                       assert keyNames.size() == list.size();

                                       for(int j = 0; j < list.size(); ++j) {
                                          String keyName = (String)keyNames.get(j);
                                          Object value = list.get(j);
                                          ExpressionTree.LeafNode leaf = new ExpressionTree.LeafNode();
                                          leaf.keyName = keyName;
                                          leaf.value = value;
                                          leaf.operator = isPositive ? ExpressionTree.Operator.EQUALS : ExpressionTree.Operator.NOTEQUALS2;
                                          this.tree.addLeafNode(leaf);
                                          if (j != 0) {
                                             this.tree.addIntermediateNode(isPositive ? ExpressionTree.LogicalOperator.AND : ExpressionTree.LogicalOperator.OR);
                                          }
                                       }

                                       if (i != 0) {
                                          this.tree.addIntermediateNode(isPositive ? ExpressionTree.LogicalOperator.OR : ExpressionTree.LogicalOperator.AND);
                                       }
                                    }

                                    return;
                              }

                              while(true) {
                                 int alt23 = 2;
                                 int LA23_0 = this.input.LA(1);
                                 if (LA23_0 == 5) {
                                    alt23 = 1;
                                 }

                                 switch (alt23) {
                                    case 1:
                                       this.match(this.input, 5, FOLLOW_COMMA_in_multiColInExpression2130);
                                       int alt22 = 3;
                                       switch (this.input.LA(1)) {
                                          case 6:
                                             alt22 = 1;
                                             break;
                                          case 14:
                                             alt22 = 3;
                                             break;
                                          case 28:
                                             alt22 = 2;
                                             break;
                                          default:
                                             NoViableAltException nvae = new NoViableAltException("", 22, 0, this.input);
                                             throw nvae;
                                       }

                                       switch (alt22) {
                                          case 1:
                                             constant = (Token)this.match(this.input, 6, FOLLOW_DateLiteral_in_multiColInExpression2174);
                                             Object var52 = FilterLexer.ExtractDate(constant.getText());
                                             partialConstants.add(var52);
                                             continue;
                                          case 2:
                                             constant = (Token)this.match(this.input, 28, FOLLOW_StringLiteral_in_multiColInExpression2224);
                                             Object var51 = TrimQuotes(constant.getText());
                                             partialConstants.add(var51);
                                             continue;
                                          case 3:
                                             constant = (Token)this.match(this.input, 14, FOLLOW_IntegralLiteral_in_multiColInExpression2274);
                                             Object var50 = Long.parseLong(constant.getText());
                                             partialConstants.add(var50);
                                          default:
                                             continue;
                                       }
                                    default:
                                       constants.add(partialConstants);
                                       this.match(this.input, 27, FOLLOW_RPAREN_in_multiColInExpression2357);
                                       continue label324;
                                 }
                              }
                           }
                     }
                  }
            }
         }
      } catch (RecognitionException e) {
         throw e;
      } finally {
         ;
      }
   }
}
