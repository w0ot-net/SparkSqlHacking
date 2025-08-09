package org.apache.derby.impl.sql.compile;

import java.io.IOException;
import java.io.PrintStream;

class SQLParserTokenManager implements SQLParserConstants {
   int commentNestingDepth;
   public PrintStream debugStream;
   static final long[] jjbitVec0 = new long[]{-2L, -1L, -1L, -1L};
   static final long[] jjbitVec2 = new long[]{0L, 0L, -1L, -1L};
   static final long[] jjbitVec3 = new long[]{0L, -16384L, -17590038560769L, 1297036692691091455L};
   static final long[] jjbitVec4 = new long[]{0L, 0L, 297241973452963840L, -36028797027352577L};
   static final long[] jjbitVec5 = new long[]{-1L, -1L, -1L, -270215977642229761L};
   static final long[] jjbitVec6 = new long[]{16777215L, -65536L, -432624840181022721L, 133144182787L};
   static final long[] jjbitVec7 = new long[]{0L, 288230376151711744L, -17179879616L, 4503588160110591L};
   static final long[] jjbitVec8 = new long[]{-8194L, -536936449L, -65533L, 234134404065073567L};
   static final long[] jjbitVec9 = new long[]{-562949953421312L, -8547991553L, 255L, 1979120929931264L};
   static final long[] jjbitVec10 = new long[]{576460743713488896L, -562949953419265L, 9007199254740991999L, 412319973375L};
   static final long[] jjbitVec11 = new long[]{2594073385365405664L, 17163091968L, 271902628478820320L, 844440767823872L};
   static final long[] jjbitVec12 = new long[]{247132830528276448L, 7881300924956672L, 2589004636761075680L, 4294967296L};
   static final long[] jjbitVec13 = new long[]{2579997437506199520L, 15837691904L, 270153412153034720L, 0L};
   static final long[] jjbitVec14 = new long[]{283724577500946400L, 12884901888L, 283724577500946400L, 13958643712L};
   static final long[] jjbitVec15 = new long[]{288228177128316896L, 12884901888L, 0L, 0L};
   static final long[] jjbitVec16 = new long[]{3799912185593854L, 127L, 2309621682768192918L, 805306463L};
   static final long[] jjbitVec17 = new long[]{0L, 4398046510847L, 0L, 0L};
   static final long[] jjbitVec18 = new long[]{0L, 0L, -4294967296L, 36028797018898495L};
   static final long[] jjbitVec19 = new long[]{-1L, -2080374785L, -1065151889409L, 288230376151711743L};
   static final long[] jjbitVec20 = new long[]{-1L, -1L, -4026531841L, 288230376151711743L};
   static final long[] jjbitVec21 = new long[]{-3233808385L, 4611686017001275199L, 6908521828386340863L, 2295745090394464220L};
   static final long[] jjbitVec22 = new long[]{0L, Long.MIN_VALUE, 0L, 0L};
   static final long[] jjbitVec23 = new long[]{142986334291623044L, 0L, 0L, 0L};
   static final long[] jjbitVec24 = new long[]{17451448556060704L, -2L, -6574571521L, 8646911284551352319L};
   static final long[] jjbitVec25 = new long[]{-527765581332512L, -1L, 32767L, 0L};
   static final long[] jjbitVec26 = new long[]{-1L, -1L, 274877906943L, 0L};
   static final long[] jjbitVec27 = new long[]{-1L, -1L, 68719476735L, 0L};
   static final long[] jjbitVec28 = new long[]{70368744177663L, 0L, 0L, 0L};
   static final long[] jjbitVec29 = new long[]{6881498029467631743L, -37L, 1125899906842623L, -524288L};
   static final long[] jjbitVec30 = new long[]{4611686018427387903L, -65536L, -196609L, 1152640029630136575L};
   static final long[] jjbitVec31 = new long[]{0L, -11540474045136896L, -1L, 2305843009213693951L};
   static final long[] jjbitVec32 = new long[]{576460743713488896L, -274743689218L, Long.MAX_VALUE, 486341884L};
   static final long[] jjbitVec33 = new long[]{576460743713488896L, -558556201875457L, 9007199254740991999L, 287949313494974463L};
   static final long[] jjbitVec34 = new long[]{2594073385365405664L, 281217261895680L, 271902628478820320L, 1125640866627584L};
   static final long[] jjbitVec35 = new long[]{247132830528276448L, 8162501023760384L, 2589004636761075680L, 281204393771008L};
   static final long[] jjbitVec36 = new long[]{2579997437506199520L, 281215936495616L, 270153412153034720L, 280925220896768L};
   static final long[] jjbitVec37 = new long[]{283724577500946400L, 281212983705600L, 283724577500946400L, 281214057447424L};
   static final long[] jjbitVec38 = new long[]{288228177128316896L, 281212983705600L, 0L, 0L};
   static final long[] jjbitVec39 = new long[]{3799912185593854L, 67043455L, 2309621682768192918L, 872349791L};
   static final long[] jjbitVec40 = new long[]{4393751543808L, 4398046510847L, 0L, 0L};
   static final long[] jjbitVec41 = new long[]{576460743780532224L, -274743689218L, Long.MAX_VALUE, 486341884L};
   static final int[] jjnextStates = new int[]{0, 1, 3, 122, 123, 125, 126, 127, 128, 129, 131, 9, 10, 11, 4, 5, 6, 21, 22, 23, 89, 90, 98, 99, 101, 102, 106, 107, 24, 113, 25, 60, 65, 70, 89, 90, 24, 91, 92, 24, 93, 94, 24, 95, 96, 24, 98, 99, 24, 101, 102, 24, 103, 104, 24, 106, 107, 24, 108, 109, 24, 110, 111, 24, 23, 24, 113, 128, 129, 131, 14, 15, 31, 59, 40, 43, 46, 47, 51, 56, 62, 64, 67, 69, 74, 79, 83, 88, 132, 133};
   public static final String[] jjstrLiteralImages = new String[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
   public static final String[] lexStateNames = new String[]{"DEFAULT", "IN_BRACKETED_COMMENT", "IN_NESTED_BRACKETED_COMMENT", "IN_COMMENT", "LOOKFOR_DE", "LOOKFOR_DER", "LOOKFOR_DERB", "LOOKFOR_DERBY", "LOOKFOR_DERBYDASH", "LOOKFOR_DERBYDASHP", "LOOKFOR_DERBYDASHPR", "LOOKFOR_DERBYDASHPRO", "LOOKFOR_DERBYDASHPROP", "LOOKFOR_DERBYDASHPROPE", "LOOKFOR_DERBYDASHPROPER", "LOOKFOR_DERBYDASHPROPERT", "LOOKFOR_DERBYDASHPROPERTI", "LOOKFOR_DERBYDASHPROPERTIE", "LOOKFOR_DERBYDASHPROPERTIES", "IT_IS_NOT_DERBYPROPERTIES_COMMENT", "PROPERTIES_LIST"};
   public static final int[] jjnewLexState = new int[]{-1, -1, -1, -1, -1, 1, 2, 0, -1, -1, -1, 3, -1, -1, 4, 0, 19, 5, 0, 19, 6, 0, 19, 7, 0, 19, 8, 0, 19, 9, 0, 19, 10, 0, 19, 11, 0, 19, 12, 0, 19, 13, 0, 19, 14, 0, 19, 15, 0, 19, 16, 0, 19, 17, 0, 19, 18, 0, 19, -1, 20, 0, 19, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
   static final long[] jjtoToken = new long[]{1729382256910270465L, -1L, -1L, -1L, -1L, -1L, -1L, 70162585747455L};
   static final long[] jjtoSkip = new long[]{-2305843009213679458L, 0L, 0L, 0L, 0L, 0L, 0L, 0L};
   static final long[] jjtoMore = new long[]{576460752303408992L, 0L, 0L, 0L, 0L, 0L, 0L, 0L};
   protected CharStream input_stream;
   private final int[] jjrounds;
   private final int[] jjstateSet;
   StringBuffer image;
   int jjimageLen;
   int lengthOfMatch;
   protected char curChar;
   int curLexState;
   int defaultLexState;
   int jjnewStateCnt;
   int jjround;
   int jjmatchedPos;
   int jjmatchedKind;

   void CommonTokenAction(Token var1) {
      var1.beginOffset = this.input_stream.getBeginOffset();
      var1.endOffset = this.input_stream.getEndOffset();
   }

   public void setDebugStream(PrintStream var1) {
      this.debugStream = var1;
   }

   private final int jjStopAtPos(int var1, int var2) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;
      return var1 + 1;
   }

   private final int jjMoveStringLiteralDfa0_1() {
      switch (this.curChar) {
         case '*' -> {
            return this.jjMoveStringLiteralDfa1_1(128L);
         }
         case '/' -> {
            return this.jjMoveStringLiteralDfa1_1(64L);
         }
         default -> {
            return 1;
         }
      }
   }

   private final int jjMoveStringLiteralDfa1_1(long var1) {
      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var4) {
         return 1;
      }

      switch (this.curChar) {
         case '*':
            if ((var1 & 64L) != 0L) {
               return this.jjStopAtPos(1, 6);
            }
            break;
         case '/':
            if ((var1 & 128L) != 0L) {
               return this.jjStopAtPos(1, 7);
            }
            break;
         default:
            return 2;
      }

      return 2;
   }

   private final int jjStopStringLiteralDfa_17(int var1, long var2) {
      switch (var1) {
         default -> {
            return -1;
         }
      }
   }

   private final int jjStartNfa_17(int var1, long var2) {
      return this.jjMoveNfa_17(this.jjStopStringLiteralDfa_17(var1, var2), var1 + 1);
   }

   private final int jjStartNfaWithStates_17(int var1, int var2, int var3) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return var1 + 1;
      }

      return this.jjMoveNfa_17(var3, var1 + 1);
   }

   private final int jjMoveStringLiteralDfa0_17() {
      switch (this.curChar) {
         case 'E' -> {
            return this.jjStopAtPos(0, 56);
         }
         case 'e' -> {
            return this.jjStopAtPos(0, 56);
         }
         default -> {
            return this.jjMoveNfa_17(0, 0);
         }
      }
   }

   private final void jjCheckNAdd(int var1) {
      if (this.jjrounds[var1] != this.jjround) {
         this.jjstateSet[this.jjnewStateCnt++] = var1;
         this.jjrounds[var1] = this.jjround;
      }

   }

   private final void jjAddStates(int var1, int var2) {
      do {
         this.jjstateSet[this.jjnewStateCnt++] = jjnextStates[var1];
      } while(var1++ != var2);

   }

   private final void jjCheckNAddTwoStates(int var1, int var2) {
      this.jjCheckNAdd(var1);
      this.jjCheckNAdd(var2);
   }

   private final void jjCheckNAddStates(int var1, int var2) {
      do {
         this.jjCheckNAdd(jjnextStates[var1]);
      } while(var1++ != var2);

   }

   private final void jjCheckNAddStates(int var1) {
      this.jjCheckNAdd(jjnextStates[var1]);
      this.jjCheckNAdd(jjnextStates[var1 + 1]);
   }

   private final int jjMoveNfa_17(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (var7 > 58) {
                        var7 = 58;
                     }

                     if ((9216L & var17) != 0L && var7 > 57) {
                        var7 = 57;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var7 > 57) {
                        var7 = 57;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if (var7 > 58) {
                        var7 = 58;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-137438953505L & var16) != 0L) {
                        var7 = 58;
                     }
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (jjCanMove_0(var8, var9, var12, var10, var13) && var7 > 58) {
                        var7 = 58;
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjStopStringLiteralDfa_16(int var1, long var2) {
      switch (var1) {
         default -> {
            return -1;
         }
      }
   }

   private final int jjStartNfa_16(int var1, long var2) {
      return this.jjMoveNfa_16(this.jjStopStringLiteralDfa_16(var1, var2), var1 + 1);
   }

   private final int jjStartNfaWithStates_16(int var1, int var2, int var3) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return var1 + 1;
      }

      return this.jjMoveNfa_16(var3, var1 + 1);
   }

   private final int jjMoveStringLiteralDfa0_16() {
      switch (this.curChar) {
         case 'I' -> {
            return this.jjStopAtPos(0, 53);
         }
         case 'i' -> {
            return this.jjStopAtPos(0, 53);
         }
         default -> {
            return this.jjMoveNfa_16(0, 0);
         }
      }
   }

   private final int jjMoveNfa_16(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (var7 > 55) {
                        var7 = 55;
                     }

                     if ((9216L & var17) != 0L && var7 > 54) {
                        var7 = 54;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var7 > 54) {
                        var7 = 54;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if (var7 > 55) {
                        var7 = 55;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-2199023256065L & var16) != 0L) {
                        var7 = 55;
                     }
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (jjCanMove_0(var8, var9, var12, var10, var13) && var7 > 55) {
                        var7 = 55;
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjMoveStringLiteralDfa0_19() {
      return this.jjMoveNfa_19(4, 0);
   }

   private final int jjMoveNfa_19(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-9217L & var17) != 0L) {
                        var7 = 63;
                        this.jjCheckNAddStates(0, 2);
                     }
                     break;
                  case 1:
                     if ((9216L & var17) != 0L && var7 > 63) {
                        var7 = 63;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\n' && var7 > 63) {
                        var7 = 63;
                     }
                     break;
                  case 3:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 2;
                     }
                     break;
                  case 4:
                     if ((-9217L & var17) != 0L) {
                        if (var7 > 63) {
                           var7 = 63;
                        }

                        this.jjCheckNAddStates(0, 2);
                     } else if ((9216L & var17) != 0L && var7 > 63) {
                        var7 = 63;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 2;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                  case 4:
                     var7 = 63;
                     this.jjCheckNAddStates(0, 2);
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                  case 4:
                     if (jjCanMove_0(var8, var9, var12, var10, var13)) {
                        if (var7 > 63) {
                           var7 = 63;
                        }

                        this.jjCheckNAddStates(0, 2);
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjStopStringLiteralDfa_12(int var1, long var2) {
      switch (var1) {
         default -> {
            return -1;
         }
      }
   }

   private final int jjStartNfa_12(int var1, long var2) {
      return this.jjMoveNfa_12(this.jjStopStringLiteralDfa_12(var1, var2), var1 + 1);
   }

   private final int jjStartNfaWithStates_12(int var1, int var2, int var3) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return var1 + 1;
      }

      return this.jjMoveNfa_12(var3, var1 + 1);
   }

   private final int jjMoveStringLiteralDfa0_12() {
      switch (this.curChar) {
         case 'P' -> {
            return this.jjStopAtPos(0, 41);
         }
         case 'p' -> {
            return this.jjStopAtPos(0, 41);
         }
         default -> {
            return this.jjMoveNfa_12(0, 0);
         }
      }
   }

   private final int jjMoveNfa_12(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (var7 > 43) {
                        var7 = 43;
                     }

                     if ((9216L & var17) != 0L && var7 > 42) {
                        var7 = 42;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var7 > 42) {
                        var7 = 42;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if (var7 > 43) {
                        var7 = 43;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-281474976776193L & var16) != 0L) {
                        var7 = 43;
                     }
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (jjCanMove_0(var8, var9, var12, var10, var13) && var7 > 43) {
                        var7 = 43;
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjStopStringLiteralDfa_6(int var1, long var2) {
      switch (var1) {
         default -> {
            return -1;
         }
      }
   }

   private final int jjStartNfa_6(int var1, long var2) {
      return this.jjMoveNfa_6(this.jjStopStringLiteralDfa_6(var1, var2), var1 + 1);
   }

   private final int jjStartNfaWithStates_6(int var1, int var2, int var3) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return var1 + 1;
      }

      return this.jjMoveNfa_6(var3, var1 + 1);
   }

   private final int jjMoveStringLiteralDfa0_6() {
      switch (this.curChar) {
         case 'B' -> {
            return this.jjStopAtPos(0, 23);
         }
         case 'b' -> {
            return this.jjStopAtPos(0, 23);
         }
         default -> {
            return this.jjMoveNfa_6(0, 0);
         }
      }
   }

   private final int jjMoveNfa_6(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (var7 > 25) {
                        var7 = 25;
                     }

                     if ((9216L & var17) != 0L && var7 > 24) {
                        var7 = 24;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var7 > 24) {
                        var7 = 24;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if (var7 > 25) {
                        var7 = 25;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-17179869189L & var16) != 0L) {
                        var7 = 25;
                     }
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (jjCanMove_0(var8, var9, var12, var10, var13) && var7 > 25) {
                        var7 = 25;
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjMoveStringLiteralDfa0_2() {
      switch (this.curChar) {
         case '*' -> {
            return this.jjMoveStringLiteralDfa1_2(512L);
         }
         case '/' -> {
            return this.jjMoveStringLiteralDfa1_2(256L);
         }
         default -> {
            return 1;
         }
      }
   }

   private final int jjMoveStringLiteralDfa1_2(long var1) {
      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var4) {
         return 1;
      }

      switch (this.curChar) {
         case '*':
            if ((var1 & 256L) != 0L) {
               return this.jjStopAtPos(1, 8);
            }
            break;
         case '/':
            if ((var1 & 512L) != 0L) {
               return this.jjStopAtPos(1, 9);
            }
            break;
         default:
            return 2;
      }

      return 2;
   }

   private final int jjStopStringLiteralDfa_15(int var1, long var2) {
      switch (var1) {
         default -> {
            return -1;
         }
      }
   }

   private final int jjStartNfa_15(int var1, long var2) {
      return this.jjMoveNfa_15(this.jjStopStringLiteralDfa_15(var1, var2), var1 + 1);
   }

   private final int jjStartNfaWithStates_15(int var1, int var2, int var3) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return var1 + 1;
      }

      return this.jjMoveNfa_15(var3, var1 + 1);
   }

   private final int jjMoveStringLiteralDfa0_15() {
      switch (this.curChar) {
         case 'T' -> {
            return this.jjStopAtPos(0, 50);
         }
         case 't' -> {
            return this.jjStopAtPos(0, 50);
         }
         default -> {
            return this.jjMoveNfa_15(0, 0);
         }
      }
   }

   private final int jjMoveNfa_15(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (var7 > 52) {
                        var7 = 52;
                     }

                     if ((9216L & var17) != 0L && var7 > 51) {
                        var7 = 51;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var7 > 51) {
                        var7 = 51;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if (var7 > 52) {
                        var7 = 52;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-4503599628419073L & var16) != 0L) {
                        var7 = 52;
                     }
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (jjCanMove_0(var8, var9, var12, var10, var13) && var7 > 52) {
                        var7 = 52;
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjStopStringLiteralDfa_13(int var1, long var2) {
      switch (var1) {
         default -> {
            return -1;
         }
      }
   }

   private final int jjStartNfa_13(int var1, long var2) {
      return this.jjMoveNfa_13(this.jjStopStringLiteralDfa_13(var1, var2), var1 + 1);
   }

   private final int jjStartNfaWithStates_13(int var1, int var2, int var3) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return var1 + 1;
      }

      return this.jjMoveNfa_13(var3, var1 + 1);
   }

   private final int jjMoveStringLiteralDfa0_13() {
      switch (this.curChar) {
         case 'E' -> {
            return this.jjStopAtPos(0, 44);
         }
         case 'e' -> {
            return this.jjStopAtPos(0, 44);
         }
         default -> {
            return this.jjMoveNfa_13(0, 0);
         }
      }
   }

   private final int jjMoveNfa_13(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (var7 > 46) {
                        var7 = 46;
                     }

                     if ((9216L & var17) != 0L && var7 > 45) {
                        var7 = 45;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var7 > 45) {
                        var7 = 45;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if (var7 > 46) {
                        var7 = 46;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-137438953505L & var16) != 0L) {
                        var7 = 46;
                     }
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (jjCanMove_0(var8, var9, var12, var10, var13) && var7 > 46) {
                        var7 = 46;
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjStopStringLiteralDfa_7(int var1, long var2) {
      switch (var1) {
         default -> {
            return -1;
         }
      }
   }

   private final int jjStartNfa_7(int var1, long var2) {
      return this.jjMoveNfa_7(this.jjStopStringLiteralDfa_7(var1, var2), var1 + 1);
   }

   private final int jjStartNfaWithStates_7(int var1, int var2, int var3) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return var1 + 1;
      }

      return this.jjMoveNfa_7(var3, var1 + 1);
   }

   private final int jjMoveStringLiteralDfa0_7() {
      switch (this.curChar) {
         case 'Y' -> {
            return this.jjStopAtPos(0, 26);
         }
         case 'y' -> {
            return this.jjStopAtPos(0, 26);
         }
         default -> {
            return this.jjMoveNfa_7(0, 0);
         }
      }
   }

   private final int jjMoveNfa_7(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (var7 > 28) {
                        var7 = 28;
                     }

                     if ((9216L & var17) != 0L && var7 > 27) {
                        var7 = 27;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var7 > 27) {
                        var7 = 27;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if (var7 > 28) {
                        var7 = 28;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-144115188109410305L & var16) != 0L) {
                        var7 = 28;
                     }
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (jjCanMove_0(var8, var9, var12, var10, var13) && var7 > 28) {
                        var7 = 28;
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjStopStringLiteralDfa_11(int var1, long var2) {
      switch (var1) {
         default -> {
            return -1;
         }
      }
   }

   private final int jjStartNfa_11(int var1, long var2) {
      return this.jjMoveNfa_11(this.jjStopStringLiteralDfa_11(var1, var2), var1 + 1);
   }

   private final int jjStartNfaWithStates_11(int var1, int var2, int var3) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return var1 + 1;
      }

      return this.jjMoveNfa_11(var3, var1 + 1);
   }

   private final int jjMoveStringLiteralDfa0_11() {
      switch (this.curChar) {
         case 'O' -> {
            return this.jjStopAtPos(0, 38);
         }
         case 'o' -> {
            return this.jjStopAtPos(0, 38);
         }
         default -> {
            return this.jjMoveNfa_11(0, 0);
         }
      }
   }

   private final int jjMoveNfa_11(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (var7 > 40) {
                        var7 = 40;
                     }

                     if ((9216L & var17) != 0L && var7 > 39) {
                        var7 = 39;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var7 > 39) {
                        var7 = 39;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if (var7 > 40) {
                        var7 = 40;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-140737488388097L & var16) != 0L) {
                        var7 = 40;
                     }
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (jjCanMove_0(var8, var9, var12, var10, var13) && var7 > 40) {
                        var7 = 40;
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjMoveStringLiteralDfa0_3() {
      switch (this.curChar) {
         case '\t':
            this.jjmatchedKind = 13;
            this.jjmatchedPos = 0;
            return this.jjMoveNfa_3(0, 0);
         case ' ':
            this.jjmatchedKind = 12;
            this.jjmatchedPos = 0;
            return this.jjMoveNfa_3(0, 0);
         case 'D':
            this.jjmatchedKind = 14;
            this.jjmatchedPos = 0;
            return this.jjMoveNfa_3(0, 0);
         case 'd':
            this.jjmatchedKind = 14;
            this.jjmatchedPos = 0;
            return this.jjMoveNfa_3(0, 0);
         default:
            return this.jjMoveNfa_3(0, 0);
      }
   }

   private final int jjMoveNfa_3(int var1, int var2) {
      int var3 = this.jjmatchedKind;
      int var4 = this.jjmatchedPos;
      int var5;
      this.input_stream.backup(var5 = var2 + 1);

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var19) {
         throw new Error("Internal Error");
      }

      var2 = 0;
      int var7 = 0;
      this.jjnewStateCnt = 4;
      int var8 = 1;
      this.jjstateSet[0] = var1;
      int var10 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var24 = 1L << this.curChar;

            do {
               --var8;
               switch (this.jjstateSet[var8]) {
                  case 0:
                     if (var10 > 16) {
                        var10 = 16;
                     }

                     if ((9216L & var24) != 0L && var10 > 15) {
                        var10 = 15;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var10 > 15) {
                        var10 = 15;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if (var10 > 16) {
                        var10 = 16;
                     }
               }
            } while(var8 != var7);
         } else if (this.curChar < 128) {
            long var23 = 1L << (this.curChar & 63);

            do {
               --var8;
               switch (this.jjstateSet[var8]) {
                  case 0:
                     if ((-68719476753L & var23) != 0L) {
                        var10 = 16;
                     }
               }
            } while(var8 != var7);
         } else {
            int var11 = this.curChar >> 8;
            int var12 = var11 >> 6;
            long var13 = 1L << (var11 & 63);
            int var15 = (this.curChar & 255) >> 6;
            long var16 = 1L << (this.curChar & 63);

            do {
               --var8;
               switch (this.jjstateSet[var8]) {
                  case 0:
                     if (jjCanMove_0(var11, var12, var15, var13, var16) && var10 > 16) {
                        var10 = 16;
                     }
               }
            } while(var8 != var7);
         }

         if (var10 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var10;
            this.jjmatchedPos = var2;
            var10 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var8 = this.jjnewStateCnt) == (var7 = 4 - (this.jjnewStateCnt = var7))) {
            break;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var20) {
            break;
         }
      }

      if (this.jjmatchedPos > var4) {
         return var2;
      } else {
         int var25 = Math.max(var2, var5);
         if (var2 < var25) {
            var8 = var25 - Math.min(var2, var5);

            while(var8-- > 0) {
               try {
                  this.curChar = this.input_stream.readChar();
               } catch (IOException var18) {
                  throw new Error("Internal Error : Please send a bug report.");
               }
            }
         }

         if (this.jjmatchedPos < var4) {
            this.jjmatchedKind = var3;
            this.jjmatchedPos = var4;
         } else if (this.jjmatchedPos == var4 && this.jjmatchedKind > var3) {
            this.jjmatchedKind = var3;
         }

         return var25;
      }
   }

   private final int jjMoveStringLiteralDfa0_20() {
      return this.jjMoveNfa_20(4, 0);
   }

   private final int jjMoveNfa_20(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-9217L & var17) != 0L) {
                        var7 = 64;
                        this.jjCheckNAddStates(0, 2);
                     }
                     break;
                  case 1:
                     if ((9216L & var17) != 0L && var7 > 64) {
                        var7 = 64;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\n' && var7 > 64) {
                        var7 = 64;
                     }
                     break;
                  case 3:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 2;
                     }
                     break;
                  case 4:
                     if ((-9217L & var17) != 0L) {
                        if (var7 > 64) {
                           var7 = 64;
                        }

                        this.jjCheckNAddStates(0, 2);
                     } else if ((9216L & var17) != 0L && var7 > 64) {
                        var7 = 64;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 2;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                  case 4:
                     var7 = 64;
                     this.jjCheckNAddStates(0, 2);
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                  case 4:
                     if (jjCanMove_0(var8, var9, var12, var10, var13)) {
                        if (var7 > 64) {
                           var7 = 64;
                        }

                        this.jjCheckNAddStates(0, 2);
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjStopStringLiteralDfa_5(int var1, long var2) {
      switch (var1) {
         default -> {
            return -1;
         }
      }
   }

   private final int jjStartNfa_5(int var1, long var2) {
      return this.jjMoveNfa_5(this.jjStopStringLiteralDfa_5(var1, var2), var1 + 1);
   }

   private final int jjStartNfaWithStates_5(int var1, int var2, int var3) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return var1 + 1;
      }

      return this.jjMoveNfa_5(var3, var1 + 1);
   }

   private final int jjMoveStringLiteralDfa0_5() {
      switch (this.curChar) {
         case 'R' -> {
            return this.jjStopAtPos(0, 20);
         }
         case 'r' -> {
            return this.jjStopAtPos(0, 20);
         }
         default -> {
            return this.jjMoveNfa_5(0, 0);
         }
      }
   }

   private final int jjMoveNfa_5(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (var7 > 22) {
                        var7 = 22;
                     }

                     if ((9216L & var17) != 0L && var7 > 21) {
                        var7 = 21;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var7 > 21) {
                        var7 = 21;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if (var7 > 22) {
                        var7 = 22;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-1125899907104769L & var16) != 0L) {
                        var7 = 22;
                     }
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (jjCanMove_0(var8, var9, var12, var10, var13) && var7 > 22) {
                        var7 = 22;
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjStopStringLiteralDfa_4(int var1, long var2) {
      switch (var1) {
         default -> {
            return -1;
         }
      }
   }

   private final int jjStartNfa_4(int var1, long var2) {
      return this.jjMoveNfa_4(this.jjStopStringLiteralDfa_4(var1, var2), var1 + 1);
   }

   private final int jjStartNfaWithStates_4(int var1, int var2, int var3) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return var1 + 1;
      }

      return this.jjMoveNfa_4(var3, var1 + 1);
   }

   private final int jjMoveStringLiteralDfa0_4() {
      switch (this.curChar) {
         case 'E' -> {
            return this.jjStopAtPos(0, 17);
         }
         case 'e' -> {
            return this.jjStopAtPos(0, 17);
         }
         default -> {
            return this.jjMoveNfa_4(0, 0);
         }
      }
   }

   private final int jjMoveNfa_4(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (var7 > 19) {
                        var7 = 19;
                     }

                     if ((9216L & var17) != 0L && var7 > 18) {
                        var7 = 18;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var7 > 18) {
                        var7 = 18;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if (var7 > 19) {
                        var7 = 19;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-137438953505L & var16) != 0L) {
                        var7 = 19;
                     }
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (jjCanMove_0(var8, var9, var12, var10, var13) && var7 > 19) {
                        var7 = 19;
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjStopStringLiteralDfa_8(int var1, long var2) {
      switch (var1) {
         default -> {
            return -1;
         }
      }
   }

   private final int jjStartNfa_8(int var1, long var2) {
      return this.jjMoveNfa_8(this.jjStopStringLiteralDfa_8(var1, var2), var1 + 1);
   }

   private final int jjStartNfaWithStates_8(int var1, int var2, int var3) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return var1 + 1;
      }

      return this.jjMoveNfa_8(var3, var1 + 1);
   }

   private final int jjMoveStringLiteralDfa0_8() {
      switch (this.curChar) {
         case '-' -> {
            return this.jjStopAtPos(0, 29);
         }
         default -> {
            return this.jjMoveNfa_8(0, 0);
         }
      }
   }

   private final int jjMoveNfa_8(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-35184372088833L & var17) != 0L && var7 > 31) {
                        var7 = 31;
                     }

                     if ((9216L & var17) != 0L && var7 > 30) {
                        var7 = 30;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var7 > 30) {
                        var7 = 30;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if ((-35184372088833L & var17) != 0L && var7 > 31) {
                        var7 = 31;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     var7 = 31;
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (jjCanMove_0(var8, var9, var12, var10, var13) && var7 > 31) {
                        var7 = 31;
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjStopStringLiteralDfa_10(int var1, long var2) {
      switch (var1) {
         default -> {
            return -1;
         }
      }
   }

   private final int jjStartNfa_10(int var1, long var2) {
      return this.jjMoveNfa_10(this.jjStopStringLiteralDfa_10(var1, var2), var1 + 1);
   }

   private final int jjStartNfaWithStates_10(int var1, int var2, int var3) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return var1 + 1;
      }

      return this.jjMoveNfa_10(var3, var1 + 1);
   }

   private final int jjMoveStringLiteralDfa0_10() {
      switch (this.curChar) {
         case 'R' -> {
            return this.jjStopAtPos(0, 35);
         }
         case 'r' -> {
            return this.jjStopAtPos(0, 35);
         }
         default -> {
            return this.jjMoveNfa_10(0, 0);
         }
      }
   }

   private final int jjMoveNfa_10(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (var7 > 37) {
                        var7 = 37;
                     }

                     if ((9216L & var17) != 0L && var7 > 36) {
                        var7 = 36;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var7 > 36) {
                        var7 = 36;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if (var7 > 37) {
                        var7 = 37;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-1125899907104769L & var16) != 0L) {
                        var7 = 37;
                     }
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (jjCanMove_0(var8, var9, var12, var10, var13) && var7 > 37) {
                        var7 = 37;
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjMoveStringLiteralDfa0_0() {
      switch (this.curChar) {
         case '\t':
            this.jjmatchedKind = 2;
            return this.jjMoveNfa_0(0, 0);
         case '\n':
            this.jjmatchedKind = 3;
            return this.jjMoveNfa_0(0, 0);
         case '\u000b':
         case '\f':
         case '\u000e':
         case '\u000f':
         case '\u0010':
         case '\u0011':
         case '\u0012':
         case '\u0013':
         case '\u0014':
         case '\u0015':
         case '\u0016':
         case '\u0017':
         case '\u0018':
         case '\u0019':
         case '\u001a':
         case '\u001b':
         case '\u001c':
         case '\u001d':
         case '\u001e':
         case '\u001f':
         case '$':
         case '0':
         case '1':
         case '2':
         case '3':
         case '4':
         case '5':
         case '6':
         case '7':
         case '8':
         case '9':
         case '@':
         case 'Q':
         case 'Z':
         case '\\':
         case '^':
         case '`':
         case 'q':
         case 'z':
         default:
            return this.jjMoveNfa_0(0, 0);
         case '\r':
            this.jjmatchedKind = 4;
            return this.jjMoveNfa_0(0, 0);
         case ' ':
            this.jjmatchedKind = 1;
            return this.jjMoveNfa_0(0, 0);
         case '!':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 2097152L);
         case '"':
            this.jjmatchedKind = 447;
            return this.jjMoveNfa_0(0, 0);
         case '#':
            this.jjmatchedKind = 456;
            return this.jjMoveNfa_0(0, 0);
         case '%':
            this.jjmatchedKind = 448;
            return this.jjMoveNfa_0(0, 0);
         case '&':
            this.jjmatchedKind = 449;
            return this.jjMoveNfa_0(0, 0);
         case '\'':
            this.jjmatchedKind = 450;
            return this.jjMoveNfa_0(0, 0);
         case '(':
            this.jjmatchedKind = 453;
            return this.jjMoveNfa_0(0, 0);
         case ')':
            this.jjmatchedKind = 454;
            return this.jjMoveNfa_0(0, 0);
         case '*':
            this.jjmatchedKind = 455;
            return this.jjMoveNfa_0(0, 0);
         case '+':
            this.jjmatchedKind = 457;
            return this.jjMoveNfa_0(0, 0);
         case ',':
            this.jjmatchedKind = 458;
            return this.jjMoveNfa_0(0, 0);
         case '-':
            this.jjmatchedKind = 459;
            return this.jjMoveStringLiteralDfa1_0(2048L, 0L, 0L, 0L, 0L, 549755813888L, 0L, 1073741824L);
         case '.':
            this.jjmatchedKind = 460;
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 2147483648L);
         case '/':
            this.jjmatchedKind = 461;
            return this.jjMoveStringLiteralDfa1_0(32L, 0L, 0L, 0L, 0L, 0L, 0L, 0L);
         case ':':
            this.jjmatchedKind = 462;
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 32768L);
         case ';':
            this.jjmatchedKind = 464;
            return this.jjMoveNfa_0(0, 0);
         case '<':
            this.jjmatchedKind = 465;
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 1310720L);
         case '=':
            this.jjmatchedKind = 467;
            return this.jjMoveNfa_0(0, 0);
         case '>':
            this.jjmatchedKind = 470;
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 8388608L);
         case '?':
            this.jjmatchedKind = 472;
            return this.jjMoveNfa_0(0, 0);
         case 'A':
            return this.jjMoveStringLiteralDfa1_0(0L, 16382L, 0L, 0L, 491520L, -4611686018427387904L, 0L, 0L);
         case 'B':
            return this.jjMoveStringLiteralDfa1_0(0L, 1032192L, 0L, 0L, 524288L, 282574488338432L, 1L, 0L);
         case 'C':
            this.jjmatchedKind = 276;
            return this.jjMoveStringLiteralDfa1_0(0L, 1125899905794048L, 0L, 0L, 266338304L, 32985348833280L, 62L, 0L);
         case 'D':
            this.jjmatchedKind = 114;
            return this.jjMoveStringLiteralDfa1_0(0L, -2251799813685248L, 3L, 0L, 16911433728L, 35184372088832L, 4032L, 0L);
         case 'E':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 4092L, 0L, 17179869184L, 140737488355328L, 28672L, 0L);
         case 'F':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 4190208L, 0L, 34359738368L, 0L, 32768L, 0L);
         case 'G':
            this.jjmatchedKind = 483;
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 264241152L, 0L, 68719476736L, 70368744177664L, 0L, 0L);
         case 'H':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 805306368L, 0L, 0L, 0L, 0L, 0L);
         case 'I':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 35183298347008L, 0L, 8658654068736L, 0L, 196608L, 0L);
         case 'J':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 35184372088832L, 0L, 0L, 0L, 262144L, 0L);
         case 'K':
            this.jjmatchedKind = 481;
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 70368744177664L, 0L, 0L, 0L, 0L, 0L);
         case 'L':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 4362862139015168L, 0L, 2243003720663040L, 1688849860263936L, 3670016L, 0L);
         case 'M':
            this.jjmatchedKind = 482;
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 139611588448485376L, 0L, 2303591209400008704L, 0L, 29360128L, 0L);
         case 'N':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, -144115188075855872L, 3L, -2305843009213693952L, 2251799813685251L, 234881024L, 0L);
         case 'O':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 4092L, 0L, 4503599627370508L, 8321499136L, 0L);
         case 'P':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 2093056L, 0L, 112L, 128849018880L, 0L);
         case 'R':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 1071644672L, 0L, 63050394783195008L, 140600049401856L, 0L);
         case 'S':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 70367670435840L, 0L, 72057594574790656L, 288089638663356416L, 0L);
         case 'T':
            this.jjmatchedKind = 238;
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 576320014815068160L, 0L, 68182605824L, 864691128455135232L, 0L);
         case 'U':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, -576460752303423488L, 3L, 206158430208L, 3458764513820540928L, 0L);
         case 'V':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 0L, 252L, 0L, 0L, 0L);
         case 'W':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 0L, 16128L, 274877906944L, 4611686018427387904L, 0L);
         case 'X':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 0L, 0L, 4467570830351532032L, 0L, 0L);
         case 'Y':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 0L, 16384L, 0L, 0L, 0L);
         case '[':
            this.jjmatchedKind = 475;
            return this.jjMoveNfa_0(0, 0);
         case ']':
            this.jjmatchedKind = 476;
            return this.jjMoveNfa_0(0, 0);
         case '_':
            this.jjmatchedKind = 473;
            return this.jjMoveNfa_0(0, 0);
         case 'a':
            return this.jjMoveStringLiteralDfa1_0(0L, 16382L, 0L, 0L, 491520L, -4611686018427387904L, 0L, 0L);
         case 'b':
            return this.jjMoveStringLiteralDfa1_0(0L, 1032192L, 0L, 0L, 524288L, 282574488338432L, 1L, 0L);
         case 'c':
            this.jjmatchedKind = 276;
            return this.jjMoveStringLiteralDfa1_0(0L, 1125899905794048L, 0L, 0L, 266338304L, 32985348833280L, 62L, 0L);
         case 'd':
            this.jjmatchedKind = 114;
            return this.jjMoveStringLiteralDfa1_0(0L, -2251799813685248L, 3L, 0L, 16911433728L, 35184372088832L, 4032L, 0L);
         case 'e':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 4092L, 0L, 17179869184L, 140737488355328L, 28672L, 0L);
         case 'f':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 4190208L, 0L, 34359738368L, 0L, 32768L, 0L);
         case 'g':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 264241152L, 0L, 68719476736L, 70368744177664L, 0L, 0L);
         case 'h':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 805306368L, 0L, 0L, 0L, 0L, 0L);
         case 'i':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 35183298347008L, 0L, 8658654068736L, 0L, 196608L, 0L);
         case 'j':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 35184372088832L, 0L, 0L, 0L, 262144L, 0L);
         case 'k':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 70368744177664L, 0L, 0L, 0L, 0L, 0L);
         case 'l':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 4362862139015168L, 0L, 2243003720663040L, 1688849860263936L, 3670016L, 0L);
         case 'm':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 139611588448485376L, 0L, 2303591209400008704L, 0L, 29360128L, 0L);
         case 'n':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, -144115188075855872L, 3L, -2305843009213693952L, 2251799813685251L, 234881024L, 0L);
         case 'o':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 4092L, 0L, 4503599627370508L, 8321499136L, 0L);
         case 'p':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 2093056L, 0L, 112L, 128849018880L, 0L);
         case 'r':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 1071644672L, 0L, 63050394783195008L, 140600049401856L, 0L);
         case 's':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 70367670435840L, 0L, 72057594574790656L, 288089638663356416L, 0L);
         case 't':
            this.jjmatchedKind = 238;
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 576320014815068160L, 0L, 68182605824L, 864691128455135232L, 0L);
         case 'u':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, -576460752303423488L, 3L, 206158430208L, 3458764513820540928L, 0L);
         case 'v':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 0L, 252L, 0L, 0L, 0L);
         case 'w':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 0L, 16128L, 274877906944L, 4611686018427387904L, 0L);
         case 'x':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 0L, 0L, 4467570830351532032L, 0L, 0L);
         case 'y':
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 0L, 16384L, 0L, 0L, 0L);
         case '{':
            this.jjmatchedKind = 451;
            return this.jjMoveNfa_0(0, 0);
         case '|':
            this.jjmatchedKind = 474;
            return this.jjMoveStringLiteralDfa1_0(0L, 0L, 0L, 0L, 0L, 0L, 0L, 536870912L);
         case '}':
            this.jjmatchedKind = 452;
            return this.jjMoveNfa_0(0, 0);
      }
   }

   private final int jjMoveStringLiteralDfa1_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15) {
      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var18) {
         return this.jjMoveNfa_0(0, 0);
      }

      switch (this.curChar) {
         case '*':
            if ((var1 & 32L) != 0L) {
               this.jjmatchedKind = 5;
               this.jjmatchedPos = 1;
            }
         case '+':
         case ',':
         case '/':
         case '0':
         case '1':
         case '2':
         case '3':
         case '4':
         case '5':
         case '6':
         case '7':
         case '8':
         case '9':
         case ';':
         case '<':
         case '?':
         case '@':
         case 'K':
         case 'W':
         case 'Z':
         case '[':
         case '\\':
         case ']':
         case '^':
         case '_':
         case '`':
         case 'k':
         case 'w':
         case 'z':
         case '{':
         default:
            break;
         case '-':
            if ((var1 & 2048L) != 0L) {
               this.jjmatchedKind = 11;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, 0L, var9, 0L, var11, 549755813888L, var13, 0L, var15, 0L);
         case '.':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, 0L, var9, 0L, var11, 0L, var13, 0L, var15, 2147483648L);
         case ':':
            if ((var15 & 32768L) != 0L) {
               this.jjmatchedKind = 463;
               this.jjmatchedPos = 1;
            }
            break;
         case '=':
            if ((var15 & 262144L) != 0L) {
               this.jjmatchedKind = 466;
               this.jjmatchedPos = 1;
            } else if ((var15 & 2097152L) != 0L) {
               this.jjmatchedKind = 469;
               this.jjmatchedPos = 1;
            } else if ((var15 & 8388608L) != 0L) {
               this.jjmatchedKind = 471;
               this.jjmatchedPos = 1;
            }
            break;
         case '>':
            if ((var15 & 1048576L) != 0L) {
               this.jjmatchedKind = 468;
               this.jjmatchedPos = 1;
            } else if ((var15 & 1073741824L) != 0L) {
               this.jjmatchedKind = 478;
               this.jjmatchedPos = 1;
            }
            break;
         case 'A':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 15728640L, var5, 445997100866473984L, var7, 140737488367616L, var9, 2312624798814961788L, var11, 37383395352592L, var13, 25770070016L, var15, 0L);
         case 'B':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, 0L, var9, 98304L, var11, 4L, var13, 64L, var15, 0L);
         case 'C':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 576460752303423488L, var7, 3221225472L, var9, 4611686018427518976L, var11, 16384L, var13, 1152921504607371264L, var15, 0L);
         case 'D':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 2L, var5, 1073741824L, var7, 0L, var9, 137438953472L, var11, 0L, var13, 0L, var15, 0L);
         case 'E':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 2303591209400057856L, var5, 1153836298285359104L, var7, 281539533340672L, var9, 9112827532951552L, var11, 70368744277888L, var13, 2005371883357057L, var15, 0L);
         case 'F':
            if ((var7 & 4L) != 0L) {
               this.jjmatchedKind = 194;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, 0L, var9, 0L, var11, 4611686018427387912L, var13, 536870912L, var15, 0L);
         case 'G':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, 0L, var9, 0L, var11, Long.MIN_VALUE, var13, 0L, var15, 0L);
         case 'H':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 251658240L, var5, 0L, var7, 0L, var9, 768L, var11, 275414777856L, var13, 4613937818241073152L, var15, 0L);
         case 'I':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, -2305843009213497344L, var5, 55169095435304960L, var7, 1688849994481664L, var9, 18155135997840512L, var11, 281491082838016L, var13, 1024L, var15, 0L);
         case 'J':
            if ((var13 & 268435456L) != 0L) {
               this.jjmatchedKind = 412;
               this.jjmatchedPos = 1;
            }
            break;
         case 'L':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 268435484L, var5, 8421380L, var7, 0L, var9, 4980736L, var11, 32L, var13, 3221225474L, var15, 0L);
         case 'M':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 2147483648L, var7, 68719476736L, var9, 0L, var11, 4467570830351532032L, var13, 8192L, var15, 0L);
         case 'N':
            if ((var5 & 4294967296L) != 0L) {
               this.jjmatchedKind = 160;
               this.jjmatchedPos = 1;
            } else if ((var7 & 8L) != 0L) {
               this.jjmatchedKind = 195;
               this.jjmatchedPos = 1;
            } else if ((var13 & 32768L) != 0L) {
               this.jjmatchedKind = 399;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 96L, var5, 8787503087640L, var7, 4035225266123964432L, var9, 8538394984448L, var11, 68719476736L, var13, 196608L, var15, 0L);
         case 'O':
            if ((var5 & 16777216L) != 0L) {
               this.jjmatchedKind = 152;
               this.jjmatchedPos = 1;
            } else if ((var5 & 2305843009213693952L) != 0L) {
               this.jjmatchedKind = 189;
               this.jjmatchedPos = 1;
            } else if ((var7 & 2251799813685248L) != 0L) {
               this.jjmatchedKind = 243;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 4397509902336L, var5, 4686030597221974017L, var7, 138244259840L, var9, 1118863066910429184L, var11, 29837447042963456L, var13, 19925004L, var15, 0L);
         case 'P':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, -4611685743549480864L, var9, 0L, var11, 0L, var13, 9007199254740992L, var15, 0L);
         case 'Q':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, 8246337208320L, var9, 0L, var11, 66977792L, var13, 22517998136852480L, var15, 0L);
         case 'R':
            if ((var7 & 128L) != 0L) {
               this.jjmatchedKind = 199;
               this.jjmatchedPos = 1;
            } else if ((var13 & 35184372088832L) != 0L) {
               this.jjmatchedKind = 429;
               this.jjmatchedPos = 1;
            } else if ((var13 & 2305843009213693952L) != 0L) {
               this.jjmatchedKind = 445;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 13194139533440L, var5, 201850882L, var7, 283726776525373696L, var9, 8192L, var11, 17179869248L, var13, 864691162814873600L, var15, 0L);
         case 'S':
            if ((var3 & 256L) != 0L) {
               this.jjmatchedKind = 72;
               this.jjmatchedPos = 1;
            } else if ((var5 & 8796093022208L) != 0L) {
               this.jjmatchedKind = 171;
               this.jjmatchedPos = 1;
            } else if ((var7 & 288230376151711744L) != 0L) {
               this.jjmatchedKind = 250;
               this.jjmatchedPos = 1;
            } else if ((var13 & 16L) != 0L) {
               this.jjmatchedKind = 388;
               this.jjmatchedPos = 1;
            } else if ((var13 & 70368744177664L) != 0L) {
               this.jjmatchedKind = 430;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 1536L, var5, 17592186044448L, var7, 0L, var9, 3L, var11, 137438953472L, var13, 0L, var15, 0L);
         case 'T':
            if ((var3 & 2048L) != 0L) {
               this.jjmatchedKind = 75;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, 0L, var9, 0L, var11, 37154697127133184L, var13, 252342316621103104L, var15, 0L);
         case 'U':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 1108307720802304L, var5, -9223372036851630080L, var7, 26388280116739L, var9, -8070450532247928832L, var11, 72088380363505667L, var13, 73014444064L, var15, 0L);
         case 'V':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 8192L, var5, 0L, var7, 2048L, var9, 0L, var11, 4503599627370496L, var13, 134217728L, var15, 0L);
         case 'X':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 4032L, var7, 0L, var9, 0L, var11, 140737488355328L, var13, 16384L, var15, 0L);
         case 'Y':
            if ((var3 & 524288L) != 0L) {
               this.jjmatchedKind = 83;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, 35184372088832L, var9, 8724152320L, var11, 34628173824L, var13, 0L, var15, 0L);
         case 'a':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 15728640L, var5, 445997100866473984L, var7, 140737488367616L, var9, 2312624798814961788L, var11, 37383395352592L, var13, 25770070016L, var15, 0L);
         case 'b':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, 0L, var9, 98304L, var11, 4L, var13, 64L, var15, 0L);
         case 'c':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 576460752303423488L, var7, 3221225472L, var9, 4611686018427518976L, var11, 16384L, var13, 1152921504607371264L, var15, 0L);
         case 'd':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 2L, var5, 1073741824L, var7, 0L, var9, 137438953472L, var11, 0L, var13, 0L, var15, 0L);
         case 'e':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 2303591209400057856L, var5, 1153836298285359104L, var7, 281539533340672L, var9, 9112827532951552L, var11, 70368744277888L, var13, 2005371883357057L, var15, 0L);
         case 'f':
            if ((var7 & 4L) != 0L) {
               this.jjmatchedKind = 194;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, 0L, var9, 0L, var11, 4611686018427387912L, var13, 536870912L, var15, 0L);
         case 'g':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, 0L, var9, 0L, var11, Long.MIN_VALUE, var13, 0L, var15, 0L);
         case 'h':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 251658240L, var5, 0L, var7, 0L, var9, 768L, var11, 275414777856L, var13, 4613937818241073152L, var15, 0L);
         case 'i':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, -2305843009213497344L, var5, 55169095435304960L, var7, 1688849994481664L, var9, 18155135997840512L, var11, 281491082838016L, var13, 1024L, var15, 0L);
         case 'j':
            if ((var13 & 268435456L) != 0L) {
               this.jjmatchedKind = 412;
               this.jjmatchedPos = 1;
            }
            break;
         case 'l':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 268435484L, var5, 8421380L, var7, 0L, var9, 4980736L, var11, 32L, var13, 3221225474L, var15, 0L);
         case 'm':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 2147483648L, var7, 68719476736L, var9, 0L, var11, 4467570830351532032L, var13, 8192L, var15, 0L);
         case 'n':
            if ((var5 & 4294967296L) != 0L) {
               this.jjmatchedKind = 160;
               this.jjmatchedPos = 1;
            } else if ((var7 & 8L) != 0L) {
               this.jjmatchedKind = 195;
               this.jjmatchedPos = 1;
            } else if ((var13 & 32768L) != 0L) {
               this.jjmatchedKind = 399;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 96L, var5, 8787503087640L, var7, 4035225266123964432L, var9, 8538394984448L, var11, 68719476736L, var13, 196608L, var15, 0L);
         case 'o':
            if ((var5 & 16777216L) != 0L) {
               this.jjmatchedKind = 152;
               this.jjmatchedPos = 1;
            } else if ((var5 & 2305843009213693952L) != 0L) {
               this.jjmatchedKind = 189;
               this.jjmatchedPos = 1;
            } else if ((var7 & 2251799813685248L) != 0L) {
               this.jjmatchedKind = 243;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 4397509902336L, var5, 4686030597221974017L, var7, 138244259840L, var9, 1118863066910429184L, var11, 29837447042963456L, var13, 19925004L, var15, 0L);
         case 'p':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, -4611685743549480864L, var9, 0L, var11, 0L, var13, 9007199254740992L, var15, 0L);
         case 'q':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, 8246337208320L, var9, 0L, var11, 66977792L, var13, 22517998136852480L, var15, 0L);
         case 'r':
            if ((var7 & 128L) != 0L) {
               this.jjmatchedKind = 199;
               this.jjmatchedPos = 1;
            } else if ((var13 & 35184372088832L) != 0L) {
               this.jjmatchedKind = 429;
               this.jjmatchedPos = 1;
            } else if ((var13 & 2305843009213693952L) != 0L) {
               this.jjmatchedKind = 445;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 13194139533440L, var5, 201850882L, var7, 283726776525373696L, var9, 8192L, var11, 17179869248L, var13, 864691162814873600L, var15, 0L);
         case 's':
            if ((var3 & 256L) != 0L) {
               this.jjmatchedKind = 72;
               this.jjmatchedPos = 1;
            } else if ((var5 & 8796093022208L) != 0L) {
               this.jjmatchedKind = 171;
               this.jjmatchedPos = 1;
            } else if ((var7 & 288230376151711744L) != 0L) {
               this.jjmatchedKind = 250;
               this.jjmatchedPos = 1;
            } else if ((var13 & 16L) != 0L) {
               this.jjmatchedKind = 388;
               this.jjmatchedPos = 1;
            } else if ((var13 & 70368744177664L) != 0L) {
               this.jjmatchedKind = 430;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 1536L, var5, 17592186044448L, var7, 0L, var9, 3L, var11, 137438953472L, var13, 0L, var15, 0L);
         case 't':
            if ((var3 & 2048L) != 0L) {
               this.jjmatchedKind = 75;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, 0L, var9, 0L, var11, 37154697127133184L, var13, 252342316621103104L, var15, 0L);
         case 'u':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 1108307720802304L, var5, -9223372036851630080L, var7, 26388280116739L, var9, -8070450532247928832L, var11, 72088380363505667L, var13, 73014444064L, var15, 0L);
         case 'v':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 8192L, var5, 0L, var7, 2048L, var9, 0L, var11, 4503599627370496L, var13, 134217728L, var15, 0L);
         case 'x':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 4032L, var7, 0L, var9, 0L, var11, 140737488355328L, var13, 16384L, var15, 0L);
         case 'y':
            if ((var3 & 524288L) != 0L) {
               this.jjmatchedKind = 83;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 0L, var5, 0L, var7, 35184372088832L, var9, 8724152320L, var11, 34628173824L, var13, 0L, var15, 0L);
         case '|':
            if ((var15 & 536870912L) != 0L) {
               this.jjmatchedKind = 477;
               this.jjmatchedPos = 1;
            }
      }

      return this.jjMoveNfa_0(0, 1);
   }

   private final int jjMoveStringLiteralDfa2_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15, long var17, long var19, long var21, long var23, long var25, long var27, long var29, long var31) {
      if ((var3 & var1 | (var7 = var7 & var5) | (var11 = var11 & var9) | (var15 = var15 & var13) | (var19 = var19 & var17) | (var23 = var23 & var21) | (var27 = var27 & var25) | (var31 = var31 & var29)) == 0L) {
         return this.jjMoveNfa_0(0, 1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var34) {
            return this.jjMoveNfa_0(0, 1);
         }

         switch (this.curChar) {
            case '.':
               if ((var31 & 2147483648L) != 0L) {
                  this.jjmatchedKind = 479;
                  this.jjmatchedPos = 2;
               }
            case '/':
            case '0':
            case '1':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            default:
               return this.jjMoveNfa_0(0, 2);
            case '2':
               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 0L, var27, 64L, var31, 0L);
            case 'A':
               return this.jjMoveStringLiteralDfa3_0(var7, 2308094809681690624L, var11, 281475043819520L, var15, 67554338014232576L, var19, 16384L, var23, 137640296448L, var27, 1191342976501547010L, var31, 0L);
            case 'B':
               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 0L, var15, 149533582426112L, var19, 8388608L, var23, 72057594037927936L, var27, 0L, var31, 0L);
            case 'C':
               if ((var7 & 512L) != 0L) {
                  this.jjmatchedKind = 73;
                  this.jjmatchedPos = 2;
               } else if ((var7 & 4503599627370496L) != 0L) {
                  this.jjmatchedKind = 116;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 27021597764222976L, var11, 224L, var15, 4294967296L, var19, 844699942256640L, var23, 68719509504L, var27, 3168256L, var31, 0L);
            case 'D':
               if ((var7 & 2L) != 0L) {
                  this.jjmatchedKind = 65;
                  this.jjmatchedPos = 2;
               } else if ((var7 & 32L) != 0L) {
                  this.jjmatchedKind = 69;
                  this.jjmatchedPos = 2;
               } else if ((var11 & 8L) != 0L) {
                  this.jjmatchedKind = 131;
                  this.jjmatchedPos = 2;
               } else if ((var15 & 4096L) != 0L) {
                  this.jjmatchedKind = 204;
                  this.jjmatchedPos = 2;
               } else if ((var19 & 36028797018963968L) != 0L) {
                  this.jjmatchedKind = 311;
                  this.jjmatchedPos = 2;
               } else if ((var27 & 1073741824L) != 0L) {
                  this.jjmatchedKind = 414;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 72057602627862544L, var15, 4611686018427388160L, var19, 216172782113783808L, var23, 549755813888L, var27, 2164326400L, var31, 0L);
            case 'E':
               if ((var7 & 128L) != 0L) {
                  this.jjmatchedKind = 71;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 4398180728832L, var11, 1073742592L, var15, 51232L, var19, 137438954369L, var23, 4503875042148416L, var27, 9007199254740992L, var31, 0L);
            case 'F':
               if ((var27 & 536870912L) != 0L) {
                  this.jjmatchedKind = 413;
                  this.jjmatchedPos = 2;
               } else if ((var27 & 274877906944L) != 0L) {
                  this.jjmatchedKind = 422;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 252201579132747776L, var11, 562949953421312L, var15, 8388608L, var19, 19327352832L, var23, 8L, var27, 549755814401L, var31, 0L);
            case 'G':
               if ((var7 & 8192L) != 0L) {
                  this.jjmatchedKind = 77;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 16384L, var11, 0L, var15, 134217728L, var19, 1125899906842624L, var23, -9223090561878065152L, var27, 0L, var31, 0L);
            case 'H':
               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 576460752303423488L, var15, 1073741824L, var19, 0L, var23, 0L, var27, 0L, var31, 0L);
            case 'I':
               if ((var23 & 32L) != 0L) {
                  this.jjmatchedKind = 325;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 35201551959040L, var15, 1801439850948657152L, var19, 549755822082L, var23, 0L, var27, 4899916394579099648L, var31, 0L);
            case 'J':
               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 4L, var27, 0L, var31, 0L);
            case 'K':
               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 1125899906842624L, var15, 2305843009213693952L, var19, 0L, var23, 0L, var27, 0L, var31, 0L);
            case 'L':
               if ((var7 & 4L) != 0L) {
                  this.jjmatchedKind = 66;
                  this.jjmatchedPos = 2;
               } else if ((var15 & 549755813888L) != 0L) {
                  this.jjmatchedKind = 231;
                  this.jjmatchedPos = 2;
               } else if ((var23 & 144115188075855872L) != 0L) {
                  this.jjmatchedKind = 377;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 288230383667904520L, var11, -9223372036853723136L, var15, 7705456541713L, var19, -4611686018425290740L, var23, 4332465040620652673L, var27, 4503599627370496L, var31, 0L);
            case 'M':
               if ((var15 & 17592186044416L) != 0L) {
                  this.jjmatchedKind = 236;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 8589934592L, var11, 2147483648L, var15, 1970462275928066L, var19, 3458905251325673472L, var23, 16106127362L, var27, 4L, var31, 0L);
            case 'N':
               if ((var11 & 18014398509481984L) != 0L) {
                  this.jjmatchedKind = 182;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 1082331824128L, var11, 36028831380799488L, var15, 0L, var19, 306288832536380416L, var23, 2814750035542016L, var27, 1099511627784L, var31, 0L);
            case 'O':
               return this.jjMoveStringLiteralDfa3_0(var7, 8796361457664L, var11, 17592329207810L, var15, 524288L, var19, 1099516346368L, var23, 1099511627776L, var27, 34359738368L, var31, 0L);
            case 'P':
               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 68719476736L, var15, Long.MIN_VALUE, var19, 0L, var23, 140771848093952L, var27, 139264L, var31, 0L);
            case 'Q':
               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 0L, var27, 844424930131968L, var31, 0L);
            case 'R':
               if ((var11 & 65536L) != 0L) {
                  this.jjmatchedKind = 144;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 1109407232425984L, var11, 147456L, var15, 2147491840L, var19, 585485578103951472L, var23, 37185483251449856L, var27, 90072069856822688L, var31, 0L);
            case 'S':
               if ((var19 & 32768L) != 0L) {
                  this.jjmatchedKind = 271;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, -2882303761501387776L, var11, 141149805215748L, var15, 35201585512448L, var19, 65536L, var23, 528L, var27, 6614253830144L, var31, 0L);
            case 'T':
               if ((var7 & 131072L) != 0L) {
                  this.jjmatchedKind = 81;
                  this.jjmatchedPos = 2;
               } else if ((var11 & 4194304L) != 0L) {
                  this.jjmatchedKind = 150;
                  this.jjmatchedPos = 2;
               } else if ((var11 & 549755813888L) != 0L) {
                  this.jjmatchedKind = 167;
                  this.jjmatchedPos = 2;
               } else if ((var11 & 4611686018427387904L) != 0L) {
                  this.jjmatchedKind = 190;
                  this.jjmatchedPos = 2;
               } else if ((var15 & 34359738368L) != 0L) {
                  this.jjmatchedKind = 227;
                  this.jjmatchedPos = 2;
               } else if ((var27 & 4294967296L) != 0L) {
                  this.jjmatchedKind = 416;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 299024L, var11, 436856860469897216L, var15, 1600L, var19, 2254003937347584L, var23, 4611791571543655424L, var27, 1152288194297856L, var31, 0L);
            case 'U':
               return this.jjMoveStringLiteralDfa3_0(var7, 2199023255552L, var11, 537133057L, var15, 144115188075855872L, var19, 0L, var23, 17179869184L, var27, 576460752303423488L, var31, 0L);
            case 'V':
               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 268435456L, var15, 67108864L, var19, 74766790688768L, var23, 8192L, var27, 262144L, var31, 0L);
            case 'W':
               if ((var23 & 4096L) != 0L) {
                  this.jjmatchedKind = 332;
                  this.jjmatchedPos = 2;
               } else if ((var27 & 33554432L) != 0L) {
                  this.jjmatchedKind = 409;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 2251799813685248L, var15, 536870912L, var19, 262144L, var23, 18014398509481984L, var27, 67108864L, var31, 0L);
            case 'X':
               if ((var11 & 9007199254740992L) != 0L) {
                  this.jjmatchedKind = 181;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 1152921504606846976L, var15, 0L, var19, 4503599627370496L, var23, 0L, var27, 0L, var31, 0L);
            case 'Y':
               if ((var7 & 64L) != 0L) {
                  this.jjmatchedKind = 70;
                  this.jjmatchedPos = 2;
               } else if ((var11 & 70368744177664L) != 0L) {
                  this.jjmatchedKind = 174;
                  this.jjmatchedPos = 2;
               } else if ((var19 & 1073741824L) != 0L) {
                  this.jjmatchedKind = 286;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 0L, var27, 144115188075855872L, var31, 0L);
            case 'a':
               return this.jjMoveStringLiteralDfa3_0(var7, 2308094809681690624L, var11, 281475043819520L, var15, 67554338014232576L, var19, 16384L, var23, 137640296448L, var27, 1191342976501547010L, var31, 0L);
            case 'b':
               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 0L, var15, 149533582426112L, var19, 8388608L, var23, 72057594037927936L, var27, 0L, var31, 0L);
            case 'c':
               if ((var7 & 512L) != 0L) {
                  this.jjmatchedKind = 73;
                  this.jjmatchedPos = 2;
               } else if ((var7 & 4503599627370496L) != 0L) {
                  this.jjmatchedKind = 116;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 27021597764222976L, var11, 224L, var15, 4294967296L, var19, 844699942256640L, var23, 68719509504L, var27, 3168256L, var31, 0L);
            case 'd':
               if ((var7 & 2L) != 0L) {
                  this.jjmatchedKind = 65;
                  this.jjmatchedPos = 2;
               } else if ((var7 & 32L) != 0L) {
                  this.jjmatchedKind = 69;
                  this.jjmatchedPos = 2;
               } else if ((var11 & 8L) != 0L) {
                  this.jjmatchedKind = 131;
                  this.jjmatchedPos = 2;
               } else if ((var15 & 4096L) != 0L) {
                  this.jjmatchedKind = 204;
                  this.jjmatchedPos = 2;
               } else if ((var19 & 36028797018963968L) != 0L) {
                  this.jjmatchedKind = 311;
                  this.jjmatchedPos = 2;
               } else if ((var27 & 1073741824L) != 0L) {
                  this.jjmatchedKind = 414;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 72057602627862544L, var15, 4611686018427388160L, var19, 216172782113783808L, var23, 549755813888L, var27, 2164326400L, var31, 0L);
            case 'e':
               if ((var7 & 128L) != 0L) {
                  this.jjmatchedKind = 71;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 4398180728832L, var11, 1073742592L, var15, 51232L, var19, 137438954369L, var23, 4503875042148416L, var27, 9007199254740992L, var31, 0L);
            case 'f':
               if ((var27 & 536870912L) != 0L) {
                  this.jjmatchedKind = 413;
                  this.jjmatchedPos = 2;
               } else if ((var27 & 274877906944L) != 0L) {
                  this.jjmatchedKind = 422;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 252201579132747776L, var11, 562949953421312L, var15, 8388608L, var19, 19327352832L, var23, 8L, var27, 549755814401L, var31, 0L);
            case 'g':
               if ((var7 & 8192L) != 0L) {
                  this.jjmatchedKind = 77;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 16384L, var11, 0L, var15, 134217728L, var19, 1125899906842624L, var23, -9223090561878065152L, var27, 0L, var31, 0L);
            case 'h':
               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 576460752303423488L, var15, 1073741824L, var19, 0L, var23, 0L, var27, 0L, var31, 0L);
            case 'i':
               if ((var23 & 32L) != 0L) {
                  this.jjmatchedKind = 325;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 35201551959040L, var15, 1801439850948657152L, var19, 549755822082L, var23, 0L, var27, 4899916394579099648L, var31, 0L);
            case 'j':
               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 4L, var27, 0L, var31, 0L);
            case 'k':
               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 1125899906842624L, var15, 2305843009213693952L, var19, 0L, var23, 0L, var27, 0L, var31, 0L);
            case 'l':
               if ((var7 & 4L) != 0L) {
                  this.jjmatchedKind = 66;
                  this.jjmatchedPos = 2;
               } else if ((var15 & 549755813888L) != 0L) {
                  this.jjmatchedKind = 231;
                  this.jjmatchedPos = 2;
               } else if ((var23 & 144115188075855872L) != 0L) {
                  this.jjmatchedKind = 377;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 288230383667904520L, var11, -9223372036853723136L, var15, 7705456541713L, var19, -4611686018425290740L, var23, 4332465040620652673L, var27, 4503599627370496L, var31, 0L);
            case 'm':
               if ((var15 & 17592186044416L) != 0L) {
                  this.jjmatchedKind = 236;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 8589934592L, var11, 2147483648L, var15, 1970462275928066L, var19, 3458905251325673472L, var23, 16106127362L, var27, 4L, var31, 0L);
            case 'n':
               if ((var11 & 18014398509481984L) != 0L) {
                  this.jjmatchedKind = 182;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 1082331824128L, var11, 36028831380799488L, var15, 0L, var19, 306288832536380416L, var23, 2814750035542016L, var27, 1099511627784L, var31, 0L);
            case 'o':
               return this.jjMoveStringLiteralDfa3_0(var7, 8796361457664L, var11, 17592329207810L, var15, 524288L, var19, 1099516346368L, var23, 1099511627776L, var27, 34359738368L, var31, 0L);
            case 'p':
               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 68719476736L, var15, Long.MIN_VALUE, var19, 0L, var23, 140771848093952L, var27, 139264L, var31, 0L);
            case 'q':
               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 0L, var27, 844424930131968L, var31, 0L);
            case 'r':
               if ((var11 & 65536L) != 0L) {
                  this.jjmatchedKind = 144;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 1109407232425984L, var11, 147456L, var15, 2147491840L, var19, 585485578103951472L, var23, 37185483251449856L, var27, 90072069856822688L, var31, 0L);
            case 's':
               if ((var19 & 32768L) != 0L) {
                  this.jjmatchedKind = 271;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, -2882303761501387776L, var11, 141149805215748L, var15, 35201585512448L, var19, 65536L, var23, 528L, var27, 6614253830144L, var31, 0L);
            case 't':
               if ((var7 & 131072L) != 0L) {
                  this.jjmatchedKind = 81;
                  this.jjmatchedPos = 2;
               } else if ((var11 & 4194304L) != 0L) {
                  this.jjmatchedKind = 150;
                  this.jjmatchedPos = 2;
               } else if ((var11 & 549755813888L) != 0L) {
                  this.jjmatchedKind = 167;
                  this.jjmatchedPos = 2;
               } else if ((var11 & 4611686018427387904L) != 0L) {
                  this.jjmatchedKind = 190;
                  this.jjmatchedPos = 2;
               } else if ((var15 & 34359738368L) != 0L) {
                  this.jjmatchedKind = 227;
                  this.jjmatchedPos = 2;
               } else if ((var27 & 4294967296L) != 0L) {
                  this.jjmatchedKind = 416;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 299024L, var11, 436856860469897216L, var15, 1600L, var19, 2254003937347584L, var23, 4611791571543655424L, var27, 1152288194297856L, var31, 0L);
            case 'u':
               return this.jjMoveStringLiteralDfa3_0(var7, 2199023255552L, var11, 537133057L, var15, 144115188075855872L, var19, 0L, var23, 17179869184L, var27, 576460752303423488L, var31, 0L);
            case 'v':
               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 268435456L, var15, 67108864L, var19, 74766790688768L, var23, 8192L, var27, 262144L, var31, 0L);
            case 'w':
               if ((var23 & 4096L) != 0L) {
                  this.jjmatchedKind = 332;
                  this.jjmatchedPos = 2;
               } else if ((var27 & 33554432L) != 0L) {
                  this.jjmatchedKind = 409;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 2251799813685248L, var15, 536870912L, var19, 262144L, var23, 18014398509481984L, var27, 67108864L, var31, 0L);
            case 'x':
               if ((var11 & 9007199254740992L) != 0L) {
                  this.jjmatchedKind = 181;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 1152921504606846976L, var15, 0L, var19, 4503599627370496L, var23, 0L, var27, 0L, var31, 0L);
            case 'y':
               if ((var7 & 64L) != 0L) {
                  this.jjmatchedKind = 70;
                  this.jjmatchedPos = 2;
               } else if ((var11 & 70368744177664L) != 0L) {
                  this.jjmatchedKind = 174;
                  this.jjmatchedPos = 2;
               } else if ((var19 & 1073741824L) != 0L) {
                  this.jjmatchedKind = 286;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 0L, var27, 144115188075855872L, var31, 0L);
         }
      }
   }

   private final int jjMoveStringLiteralDfa3_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15, long var17, long var19, long var21, long var23, long var25, long var27) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5) | (var11 = var11 & var9) | (var15 = var15 & var13) | (var19 = var19 & var17) | (var23 = var23 & var21) | var27 & var25) == 0L) {
         return this.jjMoveNfa_0(0, 2);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var30) {
            return this.jjMoveNfa_0(0, 2);
         }

         switch (this.curChar) {
            case '-':
               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 16L, var11, 0L, var15, 0L, var19, 0L, var23, 0L);
            case '.':
            case '/':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
            case 'F':
            case 'J':
            case 'X':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '`':
            case 'f':
            case 'j':
            case 'x':
            default:
               return this.jjMoveNfa_0(0, 3);
            case 'A':
               if ((var15 & 268435456L) != 0L) {
                  this.jjmatchedKind = 284;
                  this.jjmatchedPos = 3;
               } else if ((var23 & 262144L) != 0L) {
                  this.jjmatchedKind = 402;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 36033195065540608L, var7, 576460752303456288L, var11, 4611686018444165120L, var15, 8590196736L, var19, 35184372088832L, var23, 9904195633152L);
            case 'B':
               if ((var15 & 524288L) != 0L) {
                  this.jjmatchedKind = 275;
                  this.jjmatchedPos = 3;
               } else if ((var15 & 4194304L) != 0L) {
                  this.jjmatchedKind = 278;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 8388609L, var11, 0L, var15, 16L, var19, 2L, var23, 36028797018964352L);
            case 'C':
               if ((var3 & 576460752303423488L) != 0L) {
                  this.jjmatchedKind = 123;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 256L) != 0L) {
                  this.jjmatchedKind = 136;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 5764607523171598336L, var7, 4503599629476352L, var11, 1374390059008L, var15, 2251799847239712L, var19, 70368744177744L, var23, 9007199254740992L);
            case 'D':
               if ((var11 & 2097152L) != 0L) {
                  this.jjmatchedKind = 213;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 281474976710656L, var11, 0L, var15, 1024L, var19, 4398046511104L, var23, 137438953472L);
            case 'E':
               if ((var3 & 4194304L) != 0L) {
                  this.jjmatchedKind = 86;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 4L) != 0L) {
                  this.jjmatchedKind = 130;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 1125899906842624L) != 0L) {
                  this.jjmatchedKind = 178;
                  this.jjmatchedPos = 3;
               } else if ((var11 & 137438953472L) != 0L) {
                  this.jjmatchedKind = 229;
                  this.jjmatchedPos = 3;
               } else if ((var11 & 144115188075855872L) != 0L) {
                  this.jjmatchedKind = 249;
                  this.jjmatchedPos = 3;
               } else if ((var15 & 536870912L) != 0L) {
                  this.jjmatchedKind = 285;
                  this.jjmatchedPos = 3;
               } else if ((var15 & 576460752303423488L) != 0L) {
                  this.jjmatchedKind = 315;
                  this.jjmatchedPos = 3;
               } else if ((var15 & 2305843009213693952L) != 0L) {
                  this.jjmatchedKind = 317;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 1073741824L) != 0L) {
                  this.jjmatchedKind = 350;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 34359738368L) != 0L) {
                  this.jjmatchedKind = 355;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 2251799813685248L) != 0L) {
                  this.jjmatchedKind = 371;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 9007199254740992L) != 0L) {
                  this.jjmatchedKind = 373;
                  this.jjmatchedPos = 3;
               } else if ((var23 & 16777216L) != 0L) {
                  this.jjmatchedKind = 408;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 504403158265496592L, var7, 2255547172784320L, var11, -9221680978299190526L, var15, 72640781877248L, var19, 4899916959367307652L, var23, 2748779134976L);
            case 'G':
               if ((var19 & 562949953421312L) != 0L) {
                  this.jjmatchedKind = 369;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 2305843009213693952L, var7, 0L, var11, 0L, var15, 10194671812739072L, var19, 137438953472L, var23, 288230444871188480L);
            case 'H':
               if ((var3 & 262144L) != 0L) {
                  this.jjmatchedKind = 82;
                  this.jjmatchedPos = 3;
               } else if ((var15 & 2048L) != 0L) {
                  this.jjmatchedKind = 267;
                  this.jjmatchedPos = 3;
               } else if ((var23 & 4096L) != 0L) {
                  this.jjmatchedKind = 396;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 4096L, var7, 0L, var11, 134217728L, var15, 0L, var19, 0L, var23, 8388608L);
            case 'I':
               return this.jjMoveStringLiteralDfa4_0(var3, 9007199254757376L, var7, 144115196934225920L, var11, 36028797018964032L, var15, 216313521749753856L, var19, 37436171902582784L, var23, 76561193665298432L);
            case 'K':
               if ((var15 & 4096L) != 0L) {
                  this.jjmatchedKind = 268;
                  this.jjmatchedPos = 3;
               } else if ((var23 & 2097152L) != 0L) {
                  this.jjmatchedKind = 405;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 0L, var11, 0L, var15, 844424930131968L, var19, 0L, var23, 0L);
            case 'L':
               if ((var7 & 1048576L) != 0L) {
                  this.jjmatchedKind = 148;
                  this.jjmatchedPos = 3;
               } else if ((var7 & Long.MIN_VALUE) != 0L) {
                  this.jjmatchedKind = 191;
                  this.jjmatchedPos = 3;
               } else if ((var11 & 4194304L) != 0L) {
                  this.jjmatchedKind = 214;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 2199023255552L) != 0L) {
                  this.jjmatchedKind = 361;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 20266202081263616L, var7, 17592186044416L, var11, 140806477316097L, var15, -9223372036718460928L, var19, 141837000001537L, var23, 144115188076003328L);
            case 'M':
               if ((var7 & 524288L) != 0L) {
                  this.jjmatchedKind = 147;
                  this.jjmatchedPos = 3;
               } else if ((var11 & 72057594037927936L) != 0L) {
                  this.jjmatchedKind = 248;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 8589934592L, var7, 0L, var11, 65536L, var15, 16777216L, var19, 0L, var23, 0L);
            case 'N':
               if ((var7 & 35184372088832L) != 0L) {
                  this.jjmatchedKind = 173;
                  this.jjmatchedPos = 3;
               } else if ((var11 & 32L) != 0L) {
                  this.jjmatchedKind = 197;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 536870912L) != 0L) {
                  this.jjmatchedKind = 349;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 274877906944L) != 0L) {
                  this.jjmatchedKind = 358;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 2250562863104L, var7, 1141112832L, var11, 2337368206605287424L, var15, 137438953730L, var19, 17179869184L, var23, 576460752303423488L);
            case 'O':
               if ((var7 & 33554432L) != 0L) {
                  this.jjmatchedKind = 153;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 4398046511104L) != 0L) {
                  this.jjmatchedKind = 170;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 8L, var7, 0L, var11, 576460758813114368L, var15, 4611690433662156800L, var19, 68987912192L, var23, 1L);
            case 'P':
               if ((var7 & 2L) != 0L) {
                  this.jjmatchedKind = 129;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 0L, var11, 281474976728064L, var15, 1152921504606846976L, var19, 576460752303423488L, var23, 34359738372L);
            case 'Q':
               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 0L, var11, 1152921504606846976L, var15, 0L, var19, 1152921504606846976L, var23, 0L);
            case 'R':
               if ((var3 & 16777216L) != 0L) {
                  this.jjmatchedKind = 88;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 536870912L) != 0L) {
                  this.jjmatchedKind = 157;
                  this.jjmatchedPos = 3;
               } else if ((var15 & 1L) != 0L) {
                  this.jjmatchedKind = 256;
                  this.jjmatchedPos = 3;
               } else if ((var15 & 16384L) != 0L) {
                  this.jjmatchedKind = 270;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 4503599627370496L) != 0L) {
                  this.jjmatchedKind = 372;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 546457379667968L, var7, 0L, var11, 2048L, var15, 274877907456L, var19, -9223363240694644736L, var23, 2251799947903488L);
            case 'S':
               if ((var11 & 536870912L) != 0L) {
                  this.jjmatchedKind = 221;
                  this.jjmatchedPos = 3;
               } else if ((var23 & 1125899906842624L) != 0L) {
                  this.jjmatchedKind = 434;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 571952473309184L, var7, 21504L, var11, 13211319435264L, var15, 0L, var19, 2377900603251621896L, var23, 1152921521791434850L);
            case 'T':
               if ((var3 & 8388608L) != 0L) {
                  this.jjmatchedKind = 87;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 140737488355328L) != 0L) {
                  this.jjmatchedKind = 175;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 562949953421312L) != 0L) {
                  this.jjmatchedKind = 177;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 1152921504606846976L) != 0L) {
                  this.jjmatchedKind = 188;
                  this.jjmatchedPos = 3;
               } else if ((var23 & 18014398509481984L) != 0L) {
                  this.jjmatchedKind = 438;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, -9223371761976868864L, var7, 17179869184L, var11, 35184405651456L, var15, 288230960334381056L, var19, 17592320262656L, var23, 4611826755915752456L);
            case 'U':
               return this.jjMoveStringLiteralDfa4_0(var3, 4294967296L, var7, 396316836062298112L, var11, 0L, var15, 1099511627788L, var19, 33792L, var23, 866415162689536L);
            case 'V':
               return this.jjMoveStringLiteralDfa4_0(var3, 549755813888L, var7, 0L, var11, 262144L, var15, 22517998136918016L, var19, 0L, var23, 0L);
            case 'W':
               if ((var15 & 128L) != 0L) {
                  this.jjmatchedKind = 263;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 32768L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 0L);
            case 'Y':
               if ((var11 & 16L) != 0L) {
                  this.jjmatchedKind = 196;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 0L, var11, 0L, var15, 64L, var19, 0L, var23, 0L);
            case '_':
               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 18014398576459776L, var23, 2214592512L);
            case 'a':
               if ((var15 & 268435456L) != 0L) {
                  this.jjmatchedKind = 284;
                  this.jjmatchedPos = 3;
               } else if ((var23 & 262144L) != 0L) {
                  this.jjmatchedKind = 402;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 36033195065540608L, var7, 576460752303456288L, var11, 4611686018444165120L, var15, 8590196736L, var19, 35184372088832L, var23, 9904195633152L);
            case 'b':
               if ((var15 & 524288L) != 0L) {
                  this.jjmatchedKind = 275;
                  this.jjmatchedPos = 3;
               } else if ((var15 & 4194304L) != 0L) {
                  this.jjmatchedKind = 278;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 8388609L, var11, 0L, var15, 16L, var19, 2L, var23, 36028797018964352L);
            case 'c':
               if ((var3 & 576460752303423488L) != 0L) {
                  this.jjmatchedKind = 123;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 256L) != 0L) {
                  this.jjmatchedKind = 136;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 5764607523171598336L, var7, 4503599629476352L, var11, 1374390059008L, var15, 2251799847239712L, var19, 70368744177744L, var23, 9007199254740992L);
            case 'd':
               if ((var11 & 2097152L) != 0L) {
                  this.jjmatchedKind = 213;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 281474976710656L, var11, 0L, var15, 1024L, var19, 4398046511104L, var23, 137438953472L);
            case 'e':
               if ((var3 & 4194304L) != 0L) {
                  this.jjmatchedKind = 86;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 4L) != 0L) {
                  this.jjmatchedKind = 130;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 1125899906842624L) != 0L) {
                  this.jjmatchedKind = 178;
                  this.jjmatchedPos = 3;
               } else if ((var11 & 137438953472L) != 0L) {
                  this.jjmatchedKind = 229;
                  this.jjmatchedPos = 3;
               } else if ((var11 & 144115188075855872L) != 0L) {
                  this.jjmatchedKind = 249;
                  this.jjmatchedPos = 3;
               } else if ((var15 & 536870912L) != 0L) {
                  this.jjmatchedKind = 285;
                  this.jjmatchedPos = 3;
               } else if ((var15 & 576460752303423488L) != 0L) {
                  this.jjmatchedKind = 315;
                  this.jjmatchedPos = 3;
               } else if ((var15 & 2305843009213693952L) != 0L) {
                  this.jjmatchedKind = 317;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 1073741824L) != 0L) {
                  this.jjmatchedKind = 350;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 34359738368L) != 0L) {
                  this.jjmatchedKind = 355;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 2251799813685248L) != 0L) {
                  this.jjmatchedKind = 371;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 9007199254740992L) != 0L) {
                  this.jjmatchedKind = 373;
                  this.jjmatchedPos = 3;
               } else if ((var23 & 16777216L) != 0L) {
                  this.jjmatchedKind = 408;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 504403158265496592L, var7, 2255547172784320L, var11, -9221680978299190526L, var15, 72640781877248L, var19, 4899916959367307652L, var23, 2748779134976L);
            case 'g':
               if ((var19 & 562949953421312L) != 0L) {
                  this.jjmatchedKind = 369;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 2305843009213693952L, var7, 0L, var11, 0L, var15, 10194671812739072L, var19, 137438953472L, var23, 288230444871188480L);
            case 'h':
               if ((var3 & 262144L) != 0L) {
                  this.jjmatchedKind = 82;
                  this.jjmatchedPos = 3;
               } else if ((var15 & 2048L) != 0L) {
                  this.jjmatchedKind = 267;
                  this.jjmatchedPos = 3;
               } else if ((var23 & 4096L) != 0L) {
                  this.jjmatchedKind = 396;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 4096L, var7, 0L, var11, 134217728L, var15, 0L, var19, 0L, var23, 8388608L);
            case 'i':
               return this.jjMoveStringLiteralDfa4_0(var3, 9007199254757376L, var7, 144115196934225920L, var11, 36028797018964032L, var15, 216313521749753856L, var19, 37436171902582784L, var23, 76561193665298432L);
            case 'k':
               if ((var15 & 4096L) != 0L) {
                  this.jjmatchedKind = 268;
                  this.jjmatchedPos = 3;
               } else if ((var23 & 2097152L) != 0L) {
                  this.jjmatchedKind = 405;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 0L, var11, 0L, var15, 844424930131968L, var19, 0L, var23, 0L);
            case 'l':
               if ((var7 & 1048576L) != 0L) {
                  this.jjmatchedKind = 148;
                  this.jjmatchedPos = 3;
               } else if ((var7 & Long.MIN_VALUE) != 0L) {
                  this.jjmatchedKind = 191;
                  this.jjmatchedPos = 3;
               } else if ((var11 & 4194304L) != 0L) {
                  this.jjmatchedKind = 214;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 2199023255552L) != 0L) {
                  this.jjmatchedKind = 361;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 20266202081263616L, var7, 17592186044416L, var11, 140806477316097L, var15, -9223372036718460928L, var19, 141837000001537L, var23, 144115188076003328L);
            case 'm':
               if ((var7 & 524288L) != 0L) {
                  this.jjmatchedKind = 147;
                  this.jjmatchedPos = 3;
               } else if ((var11 & 72057594037927936L) != 0L) {
                  this.jjmatchedKind = 248;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 8589934592L, var7, 0L, var11, 65536L, var15, 16777216L, var19, 0L, var23, 0L);
            case 'n':
               if ((var7 & 35184372088832L) != 0L) {
                  this.jjmatchedKind = 173;
                  this.jjmatchedPos = 3;
               } else if ((var11 & 32L) != 0L) {
                  this.jjmatchedKind = 197;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 536870912L) != 0L) {
                  this.jjmatchedKind = 349;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 274877906944L) != 0L) {
                  this.jjmatchedKind = 358;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 2250562863104L, var7, 1141112832L, var11, 2337368206605287424L, var15, 137438953730L, var19, 17179869184L, var23, 576460752303423488L);
            case 'o':
               if ((var7 & 33554432L) != 0L) {
                  this.jjmatchedKind = 153;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 4398046511104L) != 0L) {
                  this.jjmatchedKind = 170;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 8L, var7, 0L, var11, 576460758813114368L, var15, 4611690433662156800L, var19, 68987912192L, var23, 1L);
            case 'p':
               if ((var7 & 2L) != 0L) {
                  this.jjmatchedKind = 129;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 0L, var11, 281474976728064L, var15, 1152921504606846976L, var19, 576460752303423488L, var23, 34359738372L);
            case 'q':
               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 0L, var11, 1152921504606846976L, var15, 0L, var19, 1152921504606846976L, var23, 0L);
            case 'r':
               if ((var3 & 16777216L) != 0L) {
                  this.jjmatchedKind = 88;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 536870912L) != 0L) {
                  this.jjmatchedKind = 157;
                  this.jjmatchedPos = 3;
               } else if ((var15 & 1L) != 0L) {
                  this.jjmatchedKind = 256;
                  this.jjmatchedPos = 3;
               } else if ((var15 & 16384L) != 0L) {
                  this.jjmatchedKind = 270;
                  this.jjmatchedPos = 3;
               } else if ((var19 & 4503599627370496L) != 0L) {
                  this.jjmatchedKind = 372;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 546457379667968L, var7, 0L, var11, 2048L, var15, 274877907456L, var19, -9223363240694644736L, var23, 2251799947903488L);
            case 's':
               if ((var11 & 536870912L) != 0L) {
                  this.jjmatchedKind = 221;
                  this.jjmatchedPos = 3;
               } else if ((var23 & 1125899906842624L) != 0L) {
                  this.jjmatchedKind = 434;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 571952473309184L, var7, 21504L, var11, 13211319435264L, var15, 0L, var19, 2377900603251621896L, var23, 1152921521791434850L);
            case 't':
               if ((var3 & 8388608L) != 0L) {
                  this.jjmatchedKind = 87;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 140737488355328L) != 0L) {
                  this.jjmatchedKind = 175;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 562949953421312L) != 0L) {
                  this.jjmatchedKind = 177;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 1152921504606846976L) != 0L) {
                  this.jjmatchedKind = 188;
                  this.jjmatchedPos = 3;
               } else if ((var23 & 18014398509481984L) != 0L) {
                  this.jjmatchedKind = 438;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, -9223371761976868864L, var7, 17179869184L, var11, 35184405651456L, var15, 288230960334381056L, var19, 17592320262656L, var23, 4611826755915752456L);
            case 'u':
               return this.jjMoveStringLiteralDfa4_0(var3, 4294967296L, var7, 396316836062298112L, var11, 0L, var15, 1099511627788L, var19, 33792L, var23, 866415162689536L);
            case 'v':
               return this.jjMoveStringLiteralDfa4_0(var3, 549755813888L, var7, 0L, var11, 262144L, var15, 22517998136918016L, var19, 0L, var23, 0L);
            case 'w':
               if ((var15 & 128L) != 0L) {
                  this.jjmatchedKind = 263;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 32768L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 0L);
            case 'y':
               if ((var11 & 16L) != 0L) {
                  this.jjmatchedKind = 196;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 0L, var11, 0L, var15, 64L, var19, 0L, var23, 0L);
         }
      }
   }

   private final int jjMoveStringLiteralDfa4_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15, long var17, long var19, long var21, long var23) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5) | (var11 = var11 & var9) | (var15 = var15 & var13) | (var19 = var19 & var17) | (var23 = var23 & var21)) == 0L) {
         return this.jjMoveNfa_0(0, 3);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var26) {
            return this.jjMoveNfa_0(0, 3);
         }

         switch (this.curChar) {
            case 'A':
               return this.jjMoveStringLiteralDfa5_0(var3, 18014401834516480L, var7, 17592194433024L, var11, 81920L, var15, -9200854038617194496L, var19, 576605887838356368L, var23, 4325888L);
            case 'B':
               if ((var15 & 4611686018427387904L) != 0L) {
                  this.jjmatchedKind = 318;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 268435456L, var15, 0L, var19, 35184372088832L, var23, 0L);
            case 'C':
               return this.jjMoveStringLiteralDfa5_0(var3, 8L, var7, 8589934592L, var11, 8589934592L, var15, 0L, var19, 17179869188L, var23, 576460752437641216L);
            case 'D':
               if ((var7 & 262144L) != 0L) {
                  this.jjmatchedKind = 146;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 4503599627370496L) != 0L) {
                  this.jjmatchedKind = 436;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 2147483648L, var11, 0L, var15, 0L, var19, 0L, var23, 0L);
            case 'E':
               if ((var3 & 268435456L) != 0L) {
                  this.jjmatchedKind = 92;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 4096L) != 0L) {
                  this.jjmatchedKind = 140;
                  this.jjmatchedPos = 4;
               } else if ((var11 & 274877906944L) != 0L) {
                  this.jjmatchedKind = 230;
                  this.jjmatchedPos = 4;
               } else if ((var11 & 140737488355328L) != 0L) {
                  this.jjmatchedKind = 239;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 4L) != 0L) {
                  this.jjmatchedKind = 258;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 512L) != 0L) {
                  this.jjmatchedKind = 265;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 8192L) != 0L) {
                  this.jjmatchedKind = 269;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 134217728L) != 0L) {
                  this.jjmatchedKind = 283;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 17592186044416L) != 0L) {
                  this.jjmatchedKind = 300;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 9007199254740992L) != 0L) {
                  this.jjmatchedKind = 309;
                  this.jjmatchedPos = 4;
               } else if ((var19 & 16384L) != 0L) {
                  this.jjmatchedKind = 334;
                  this.jjmatchedPos = 4;
               } else if ((var19 & 137438953472L) != 0L) {
                  this.jjmatchedKind = 357;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 524288L) != 0L) {
                  this.jjmatchedKind = 403;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 68719476736L) != 0L) {
                  this.jjmatchedKind = 420;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 2251799813685248L) != 0L) {
                  this.jjmatchedKind = 435;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 144115188075855872L) != 0L) {
                  this.jjmatchedKind = 441;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 1152921504606846976L) != 0L) {
                  this.jjmatchedKind = 444;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 547059111329792L, var7, 16L, var11, 35184372645888L, var15, 1126174786846984L, var19, -6917519131902214134L, var23, 4612530477717258248L);
            case 'F':
               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 0L, var15, 216172782113783808L, var19, 0L, var23, 0L);
            case 'G':
               if ((var15 & 2L) != 0L) {
                  this.jjmatchedKind = 257;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 1099511627776L, var11, 0L, var15, 0L, var19, 0L, var23, 288230376151711744L);
            case 'H':
               if ((var7 & 8192L) != 0L) {
                  this.jjmatchedKind = 141;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 4503599627370496L) != 0L) {
                  this.jjmatchedKind = 180;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 288230376151711744L) != 0L) {
                  this.jjmatchedKind = 314;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 0L, var15, 2251799813685280L, var19, 0L, var23, 0L);
            case 'I':
               return this.jjMoveStringLiteralDfa5_0(var3, -9223371753386934272L, var7, 281492156710912L, var11, 17181188097L, var15, 549772591184L, var19, 17592186044480L, var23, 45185547034951680L);
            case 'J':
            case 'V':
            case 'W':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'j':
            case 'v':
            case 'w':
            default:
               return this.jjMoveNfa_0(0, 4);
            case 'K':
               if ((var3 & 134217728L) != 0L) {
                  this.jjmatchedKind = 91;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 67108864L, var15, 4398046511104L, var19, 0L, var23, 0L);
            case 'L':
               if ((var15 & 8388608L) != 0L) {
                  this.jjmatchedKind = 279;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 70368744177664L) != 0L) {
                  this.jjmatchedKind = 302;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 2251799813685248L, var7, 72057594037927937L, var11, 36028867885926400L, var15, 0L, var19, 0L, var23, 4398046511104L);
            case 'M':
               if ((var19 & 1125899906842624L) != 0L) {
                  this.jjmatchedKind = 370;
                  this.jjmatchedPos = 4;
               } else if ((var19 & 36028797018963968L) != 0L) {
                  this.jjmatchedKind = 375;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 9007203549708288L, var7, 0L, var11, 1073741824L, var15, 8589934592L, var19, 68719476736L, var23, 1108101564416L);
            case 'N':
               if ((var3 & 16384L) != 0L) {
                  this.jjmatchedKind = 78;
                  this.jjmatchedPos = 4;
               } else if ((var11 & 576460752303423488L) != 0L) {
                  this.jjmatchedKind = 251;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 2305843009213693952L, var7, 137707388928L, var11, 4294967296L, var15, 2147483648L, var19, 18295873754628096L, var23, 0L);
            case 'O':
               return this.jjMoveStringLiteralDfa5_0(var3, 4612248968380813312L, var7, 144115188075855872L, var11, 2306125583702032448L, var15, 132096L, var19, 0L, var23, 8388640L);
            case 'P':
               if ((var7 & 134217728L) != 0L) {
                  this.jjmatchedKind = 155;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 72057594037927936L) != 0L) {
                  this.jjmatchedKind = 440;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 224L, var11, 0L, var15, 0L, var19, 8192L, var23, 0L);
            case 'Q':
               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 64L);
            case 'R':
               if ((var3 & 16L) != 0L) {
                  this.jjmatchedKind = 68;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 34359738368L) != 0L) {
                  this.jjmatchedKind = 163;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 2251799813685248L) != 0L) {
                  this.jjmatchedKind = 179;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 576460752303423488L) != 0L) {
                  this.jjmatchedKind = 187;
                  this.jjmatchedPos = 4;
               } else if ((var11 & 256L) != 0L) {
                  this.jjmatchedKind = 200;
                  this.jjmatchedPos = 4;
               } else if ((var11 & 512L) != 0L) {
                  this.jjmatchedKind = 201;
                  this.jjmatchedPos = 4;
               } else if ((var11 & 131072L) != 0L) {
                  this.jjmatchedKind = 209;
                  this.jjmatchedPos = 4;
               } else if ((var11 & Long.MIN_VALUE) != 0L) {
                  this.jjmatchedKind = 255;
                  this.jjmatchedPos = 4;
               } else if ((var19 & 4611686018427387904L) != 0L) {
                  this.jjmatchedKind = 382;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 1369094286720697344L, var7, 288232850052876288L, var11, 2199065198594L, var15, 2323577307136L, var19, 549755847680L, var23, 18141941858309L);
            case 'S':
               if ((var3 & 8796093022208L) != 0L) {
                  this.jjmatchedKind = 107;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 281474976710656L) != 0L) {
                  this.jjmatchedKind = 304;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 1152921504606846976L) != 0L) {
                  this.jjmatchedKind = 316;
                  this.jjmatchedPos = 4;
               } else if ((var19 & 1L) != 0L) {
                  this.jjmatchedKind = 320;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 2L) != 0L) {
                  this.jjmatchedKind = 385;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 137438953472L) != 0L) {
                  this.jjmatchedKind = 421;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 31525197391593472L, var15, 562949953421312L, var19, 15032385536L, var23, 0L);
            case 'T':
               if ((var3 & 2199023255552L) != 0L) {
                  this.jjmatchedKind = 105;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 16384L) != 0L) {
                  this.jjmatchedKind = 142;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 32768L) != 0L) {
                  this.jjmatchedKind = 143;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 67108864L) != 0L) {
                  this.jjmatchedKind = 154;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 68719476736L) != 0L) {
                  this.jjmatchedKind = 164;
                  this.jjmatchedPos = 4;
               } else if ((var11 & 134217728L) != 0L) {
                  this.jjmatchedKind = 219;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 1099511627776L) != 0L) {
                  this.jjmatchedKind = 296;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 140737488355328L) != 0L) {
                  this.jjmatchedKind = 303;
                  this.jjmatchedPos = 4;
               } else if ((var19 & 67108864L) != 0L) {
                  this.jjmatchedKind = 346;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 2199023255552L) != 0L) {
                  this.jjmatchedKind = 425;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 288234980356653056L, var7, 36028798094803968L, var11, 4611699212583698432L, var15, 35321811042304L, var19, 72057594104905728L, var23, 2215641088L);
            case 'U':
               return this.jjMoveStringLiteralDfa5_0(var3, 36028797018963968L, var7, 512L, var11, 1152921504606848000L, var15, 8796093022208L, var19, 1152991873351026688L, var23, 16384L);
            case 'X':
               if ((var23 & 65536L) != 0L) {
                  this.jjmatchedKind = 400;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 288230376151711744L, var23, 0L);
            case 'Y':
               if ((var23 & 128L) != 0L) {
                  this.jjmatchedKind = 391;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 1024L) != 0L) {
                  this.jjmatchedKind = 394;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 8192L) != 0L) {
                  this.jjmatchedKind = 397;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 0L, var15, 262144L, var19, 0L, var23, 256L);
            case 'Z':
               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 1688849860263936L, var15, 0L, var19, 0L, var23, 0L);
            case 'a':
               return this.jjMoveStringLiteralDfa5_0(var3, 18014401834516480L, var7, 17592194433024L, var11, 81920L, var15, -9200854038617194496L, var19, 576605887838356368L, var23, 4325888L);
            case 'b':
               if ((var15 & 4611686018427387904L) != 0L) {
                  this.jjmatchedKind = 318;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 268435456L, var15, 0L, var19, 35184372088832L, var23, 0L);
            case 'c':
               return this.jjMoveStringLiteralDfa5_0(var3, 8L, var7, 8589934592L, var11, 8589934592L, var15, 0L, var19, 17179869188L, var23, 576460752437641216L);
            case 'd':
               if ((var7 & 262144L) != 0L) {
                  this.jjmatchedKind = 146;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 4503599627370496L) != 0L) {
                  this.jjmatchedKind = 436;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 2147483648L, var11, 0L, var15, 0L, var19, 0L, var23, 0L);
            case 'e':
               if ((var3 & 268435456L) != 0L) {
                  this.jjmatchedKind = 92;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 4096L) != 0L) {
                  this.jjmatchedKind = 140;
                  this.jjmatchedPos = 4;
               } else if ((var11 & 274877906944L) != 0L) {
                  this.jjmatchedKind = 230;
                  this.jjmatchedPos = 4;
               } else if ((var11 & 140737488355328L) != 0L) {
                  this.jjmatchedKind = 239;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 4L) != 0L) {
                  this.jjmatchedKind = 258;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 512L) != 0L) {
                  this.jjmatchedKind = 265;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 8192L) != 0L) {
                  this.jjmatchedKind = 269;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 134217728L) != 0L) {
                  this.jjmatchedKind = 283;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 17592186044416L) != 0L) {
                  this.jjmatchedKind = 300;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 9007199254740992L) != 0L) {
                  this.jjmatchedKind = 309;
                  this.jjmatchedPos = 4;
               } else if ((var19 & 16384L) != 0L) {
                  this.jjmatchedKind = 334;
                  this.jjmatchedPos = 4;
               } else if ((var19 & 137438953472L) != 0L) {
                  this.jjmatchedKind = 357;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 524288L) != 0L) {
                  this.jjmatchedKind = 403;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 68719476736L) != 0L) {
                  this.jjmatchedKind = 420;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 2251799813685248L) != 0L) {
                  this.jjmatchedKind = 435;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 144115188075855872L) != 0L) {
                  this.jjmatchedKind = 441;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 1152921504606846976L) != 0L) {
                  this.jjmatchedKind = 444;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 547059111329792L, var7, 16L, var11, 35184372645888L, var15, 1126174786846984L, var19, -6917519131902214134L, var23, 4612530477717258248L);
            case 'f':
               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 0L, var15, 216172782113783808L, var19, 0L, var23, 0L);
            case 'g':
               if ((var15 & 2L) != 0L) {
                  this.jjmatchedKind = 257;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 1099511627776L, var11, 0L, var15, 0L, var19, 0L, var23, 288230376151711744L);
            case 'h':
               if ((var7 & 8192L) != 0L) {
                  this.jjmatchedKind = 141;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 4503599627370496L) != 0L) {
                  this.jjmatchedKind = 180;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 288230376151711744L) != 0L) {
                  this.jjmatchedKind = 314;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 0L, var15, 2251799813685280L, var19, 0L, var23, 0L);
            case 'i':
               return this.jjMoveStringLiteralDfa5_0(var3, -9223371753386934272L, var7, 281492156710912L, var11, 17181188097L, var15, 549772591184L, var19, 17592186044480L, var23, 45185547034951680L);
            case 'k':
               if ((var3 & 134217728L) != 0L) {
                  this.jjmatchedKind = 91;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 67108864L, var15, 4398046511104L, var19, 0L, var23, 0L);
            case 'l':
               if ((var15 & 8388608L) != 0L) {
                  this.jjmatchedKind = 279;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 70368744177664L) != 0L) {
                  this.jjmatchedKind = 302;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 2251799813685248L, var7, 72057594037927937L, var11, 36028867885926400L, var15, 0L, var19, 0L, var23, 4398046511104L);
            case 'm':
               if ((var19 & 1125899906842624L) != 0L) {
                  this.jjmatchedKind = 370;
                  this.jjmatchedPos = 4;
               } else if ((var19 & 36028797018963968L) != 0L) {
                  this.jjmatchedKind = 375;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 9007203549708288L, var7, 0L, var11, 1073741824L, var15, 8589934592L, var19, 68719476736L, var23, 1108101564416L);
            case 'n':
               if ((var3 & 16384L) != 0L) {
                  this.jjmatchedKind = 78;
                  this.jjmatchedPos = 4;
               } else if ((var11 & 576460752303423488L) != 0L) {
                  this.jjmatchedKind = 251;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 2305843009213693952L, var7, 137707388928L, var11, 4294967296L, var15, 2147483648L, var19, 18295873754628096L, var23, 0L);
            case 'o':
               return this.jjMoveStringLiteralDfa5_0(var3, 4612248968380813312L, var7, 144115188075855872L, var11, 2306125583702032448L, var15, 132096L, var19, 0L, var23, 8388640L);
            case 'p':
               if ((var7 & 134217728L) != 0L) {
                  this.jjmatchedKind = 155;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 72057594037927936L) != 0L) {
                  this.jjmatchedKind = 440;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 224L, var11, 0L, var15, 0L, var19, 8192L, var23, 0L);
            case 'q':
               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 64L);
            case 'r':
               if ((var3 & 16L) != 0L) {
                  this.jjmatchedKind = 68;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 34359738368L) != 0L) {
                  this.jjmatchedKind = 163;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 2251799813685248L) != 0L) {
                  this.jjmatchedKind = 179;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 576460752303423488L) != 0L) {
                  this.jjmatchedKind = 187;
                  this.jjmatchedPos = 4;
               } else if ((var11 & 256L) != 0L) {
                  this.jjmatchedKind = 200;
                  this.jjmatchedPos = 4;
               } else if ((var11 & 512L) != 0L) {
                  this.jjmatchedKind = 201;
                  this.jjmatchedPos = 4;
               } else if ((var11 & 131072L) != 0L) {
                  this.jjmatchedKind = 209;
                  this.jjmatchedPos = 4;
               } else if ((var11 & Long.MIN_VALUE) != 0L) {
                  this.jjmatchedKind = 255;
                  this.jjmatchedPos = 4;
               } else if ((var19 & 4611686018427387904L) != 0L) {
                  this.jjmatchedKind = 382;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 1369094286720697344L, var7, 288232850052876288L, var11, 2199065198594L, var15, 2323577307136L, var19, 549755847680L, var23, 18141941858309L);
            case 's':
               if ((var3 & 8796093022208L) != 0L) {
                  this.jjmatchedKind = 107;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 281474976710656L) != 0L) {
                  this.jjmatchedKind = 304;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 1152921504606846976L) != 0L) {
                  this.jjmatchedKind = 316;
                  this.jjmatchedPos = 4;
               } else if ((var19 & 1L) != 0L) {
                  this.jjmatchedKind = 320;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 2L) != 0L) {
                  this.jjmatchedKind = 385;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 137438953472L) != 0L) {
                  this.jjmatchedKind = 421;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 31525197391593472L, var15, 562949953421312L, var19, 15032385536L, var23, 0L);
            case 't':
               if ((var3 & 2199023255552L) != 0L) {
                  this.jjmatchedKind = 105;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 16384L) != 0L) {
                  this.jjmatchedKind = 142;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 32768L) != 0L) {
                  this.jjmatchedKind = 143;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 67108864L) != 0L) {
                  this.jjmatchedKind = 154;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 68719476736L) != 0L) {
                  this.jjmatchedKind = 164;
                  this.jjmatchedPos = 4;
               } else if ((var11 & 134217728L) != 0L) {
                  this.jjmatchedKind = 219;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 1099511627776L) != 0L) {
                  this.jjmatchedKind = 296;
                  this.jjmatchedPos = 4;
               } else if ((var15 & 140737488355328L) != 0L) {
                  this.jjmatchedKind = 303;
                  this.jjmatchedPos = 4;
               } else if ((var19 & 67108864L) != 0L) {
                  this.jjmatchedKind = 346;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 2199023255552L) != 0L) {
                  this.jjmatchedKind = 425;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 288234980356653056L, var7, 36028798094803968L, var11, 4611699212583698432L, var15, 35321811042304L, var19, 72057594104905728L, var23, 2215641088L);
            case 'u':
               return this.jjMoveStringLiteralDfa5_0(var3, 36028797018963968L, var7, 512L, var11, 1152921504606848000L, var15, 8796093022208L, var19, 1152991873351026688L, var23, 16384L);
            case 'x':
               if ((var23 & 65536L) != 0L) {
                  this.jjmatchedKind = 400;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 288230376151711744L, var23, 0L);
            case 'y':
               if ((var23 & 128L) != 0L) {
                  this.jjmatchedKind = 391;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 1024L) != 0L) {
                  this.jjmatchedKind = 394;
                  this.jjmatchedPos = 4;
               } else if ((var23 & 8192L) != 0L) {
                  this.jjmatchedKind = 397;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 0L, var15, 262144L, var19, 0L, var23, 256L);
            case 'z':
               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 0L, var11, 1688849860263936L, var15, 0L, var19, 0L, var23, 0L);
         }
      }
   }

   private final int jjMoveStringLiteralDfa5_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15, long var17, long var19, long var21, long var23) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5) | (var11 = var11 & var9) | (var15 = var15 & var13) | (var19 = var19 & var17) | (var23 = var23 & var21)) == 0L) {
         return this.jjMoveNfa_0(0, 4);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var26) {
            return this.jjMoveNfa_0(0, 4);
         }

         switch (this.curChar) {
            case 'A':
               if ((var11 & 1073741824L) != 0L) {
                  this.jjmatchedKind = 222;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 9007199254741000L, var7, 288230401921515520L, var11, 4507997942327296L, var15, 9448928051232L, var19, 36301063585792L, var23, 576460754518016000L);
            case 'B':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 0L, var15, Long.MIN_VALUE, var19, 549755813888L, var23, 0L);
            case 'C':
               if ((var11 & 1048576L) != 0L) {
                  this.jjmatchedKind = 212;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 51640270848L, var7, 0L, var11, 0L, var15, 17179869184L, var19, 0L, var23, 131072L);
            case 'D':
               if ((var11 & 4294967296L) != 0L) {
                  this.jjmatchedKind = 224;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 2097152L) != 0L) {
                  this.jjmatchedKind = 277;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 1125899906842624L) != 0L) {
                  this.jjmatchedKind = 306;
                  this.jjmatchedPos = 5;
               } else if ((var23 & 8388608L) != 0L) {
                  this.jjmatchedKind = 407;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 3145728L, var7, 0L, var11, 1099512152064L, var15, 0L, var19, 0L, var23, 0L);
            case 'E':
               if ((var3 & 4398046511104L) != 0L) {
                  this.jjmatchedKind = 106;
                  this.jjmatchedPos = 5;
               } else if ((var3 & 288230376151711744L) != 0L) {
                  this.jjmatchedKind = 122;
                  this.jjmatchedPos = 5;
               } else if ((var7 & 1L) != 0L) {
                  this.jjmatchedKind = 128;
                  this.jjmatchedPos = 5;
               } else if ((var7 & 32L) != 0L) {
                  this.jjmatchedKind = 133;
                  this.jjmatchedPos = 5;
               } else if ((var7 & 36028797018963968L) != 0L) {
                  this.jjmatchedKind = 183;
                  this.jjmatchedPos = 5;
               } else if ((var7 & 72057594037927936L) != 0L) {
                  this.jjmatchedKind = 184;
                  this.jjmatchedPos = 5;
               } else if ((var11 & 67108864L) != 0L) {
                  this.jjmatchedKind = 218;
                  this.jjmatchedPos = 5;
               } else if ((var11 & 1152921504606846976L) != 0L) {
                  this.jjmatchedKind = 252;
                  this.jjmatchedPos = 5;
               } else if ((var11 & 4611686018427387904L) != 0L) {
                  this.jjmatchedKind = 254;
                  this.jjmatchedPos = 5;
               } else if ((var23 & 1L) != 0L) {
                  this.jjmatchedKind = 384;
                  this.jjmatchedPos = 5;
               } else if ((var23 & 1048576L) != 0L) {
                  this.jjmatchedKind = 404;
                  this.jjmatchedPos = 5;
               } else if ((var23 & 1099511627776L) != 0L) {
                  this.jjmatchedKind = 424;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 32768L, var7, 1099511627776L, var11, 8388608L, var15, 2256200007680000L, var19, 1152921504606846976L, var23, 288230934497462276L);
            case 'F':
               if ((var11 & 1L) != 0L) {
                  this.jjmatchedKind = 192;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 9007199254740992L);
            case 'G':
               if ((var7 & 268435456L) != 0L) {
                  this.jjmatchedKind = 156;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 131072L, var11, 0L, var15, 0L, var19, Long.MIN_VALUE, var23, 4194816L);
            case 'H':
               if ((var15 & 35184372088832L) != 0L) {
                  this.jjmatchedKind = 301;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 134217728L);
            case 'I':
               return this.jjMoveStringLiteralDfa6_0(var3, 1152921504606846976L, var7, 3223322624L, var11, 36028865788772354L, var15, 72620690087346176L, var19, 288371113640099840L, var23, 0L);
            case 'J':
            case 'K':
            case 'Q':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '`':
            case 'j':
            case 'k':
            case 'q':
            default:
               break;
            case 'L':
               if ((var7 & 8388608L) != 0L) {
                  this.jjmatchedKind = 151;
                  this.jjmatchedPos = 5;
               } else if ((var11 & 2147483648L) != 0L) {
                  this.jjmatchedKind = 223;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 65536L) != 0L) {
                  this.jjmatchedKind = 272;
                  this.jjmatchedPos = 5;
               } else if ((var19 & 16L) != 0L) {
                  this.jjmatchedKind = 324;
                  this.jjmatchedPos = 5;
               } else if ((var23 & 64L) != 0L) {
                  this.jjmatchedKind = 390;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 36028797018963968L, var7, 0L, var11, 27021597764485120L, var15, 22517998136852480L, var19, 65536L, var23, 36028797018963968L);
            case 'M':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 35184372088832L, var15, 279172874240L, var19, 17661039738880L, var23, 0L);
            case 'N':
               if ((var3 & 4294967296L) != 0L) {
                  this.jjmatchedKind = 96;
                  this.jjmatchedPos = 5;
               } else if ((var11 & 64L) != 0L) {
                  this.jjmatchedKind = 198;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 131072L) != 0L) {
                  this.jjmatchedKind = 273;
                  this.jjmatchedPos = 5;
               } else if ((var23 & 8796093022208L) != 0L) {
                  this.jjmatchedKind = 427;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, -4611140385782104064L, var7, 144396663052568576L, var11, 0L, var15, 80L, var19, 8796093023232L, var23, 862034296045576L);
            case 'O':
               return this.jjMoveStringLiteralDfa6_0(var3, 2308094809027379200L, var7, 0L, var11, 1688867040133120L, var15, 0L, var19, 8192L, var23, 0L);
            case 'P':
               if ((var19 & 2048L) != 0L) {
                  this.jjmatchedKind = 331;
                  this.jjmatchedPos = 5;
               }
               break;
            case 'R':
               if ((var3 & 562949953421312L) != 0L) {
                  this.jjmatchedKind = 113;
                  this.jjmatchedPos = 5;
               } else if ((var19 & 2L) != 0L) {
                  this.jjmatchedKind = 321;
                  this.jjmatchedPos = 5;
               } else if ((var19 & 72057594037927936L) != 0L) {
                  this.jjmatchedKind = 376;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 234187936537513984L, var7, 0L, var11, 292470093103104L, var15, 0L, var19, 2882374130261295616L, var23, 34359738400L);
            case 'S':
               if ((var7 & 1024L) != 0L) {
                  this.jjmatchedKind = 138;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 8L) != 0L) {
                  this.jjmatchedKind = 259;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 262144L) != 0L) {
                  this.jjmatchedKind = 274;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 1100048498688L, var7, 2336462209024L, var11, 0L, var15, 0L, var19, 66977984L, var23, 4611826755915759616L);
            case 'T':
               if ((var3 & 8589934592L) != 0L) {
                  this.jjmatchedKind = 97;
                  this.jjmatchedPos = 5;
               } else if ((var7 & 64L) != 0L) {
                  this.jjmatchedKind = 134;
                  this.jjmatchedPos = 5;
               } else if ((var7 & 274877906944L) != 0L) {
                  this.jjmatchedKind = 166;
                  this.jjmatchedPos = 5;
               } else if ((var11 & 1024L) != 0L) {
                  this.jjmatchedKind = 202;
                  this.jjmatchedPos = 5;
               } else if ((var11 & 8589934592L) != 0L) {
                  this.jjmatchedKind = 225;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 33554432L) != 0L) {
                  this.jjmatchedKind = 281;
                  this.jjmatchedPos = 5;
               } else if ((var19 & 4L) != 0L) {
                  this.jjmatchedKind = 322;
                  this.jjmatchedPos = 5;
               } else if ((var19 & 8L) != 0L) {
                  this.jjmatchedKind = 323;
                  this.jjmatchedPos = 5;
               } else if ((var19 & 281474976710656L) != 0L) {
                  this.jjmatchedKind = 368;
                  this.jjmatchedPos = 5;
               } else if ((var23 & 4398046511104L) != 0L) {
                  this.jjmatchedKind = 426;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 3221226496L, var7, 17592186045056L, var11, 0L, var15, 16777216L, var19, 4413078896896L, var23, 0L);
            case 'U':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 18014398509481984L, var23, 0L);
            case 'V':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 0L, var15, 2199023255808L, var19, 0L, var23, 0L);
            case 'W':
               if ((var15 & 1024L) != 0L) {
                  this.jjmatchedKind = 266;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 2305843009213693952L, var15, 0L, var19, 0L, var23, 0L);
            case 'X':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 16L, var11, 0L, var15, 0L, var19, 0L, var23, 0L);
            case 'Y':
               if ((var3 & 65536L) != 0L) {
                  this.jjmatchedKind = 80;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 144115188075855872L) != 0L) {
                  this.jjmatchedKind = 313;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 268435456L, var23, 0L);
            case '_':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 256L);
            case 'a':
               if ((var11 & 1073741824L) != 0L) {
                  this.jjmatchedKind = 222;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 9007199254741000L, var7, 288230401921515520L, var11, 4507997942327296L, var15, 9448928051232L, var19, 36301063585792L, var23, 576460754518016000L);
            case 'b':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 0L, var15, Long.MIN_VALUE, var19, 549755813888L, var23, 0L);
            case 'c':
               if ((var11 & 1048576L) != 0L) {
                  this.jjmatchedKind = 212;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 51640270848L, var7, 0L, var11, 0L, var15, 17179869184L, var19, 0L, var23, 131072L);
            case 'd':
               if ((var11 & 4294967296L) != 0L) {
                  this.jjmatchedKind = 224;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 2097152L) != 0L) {
                  this.jjmatchedKind = 277;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 1125899906842624L) != 0L) {
                  this.jjmatchedKind = 306;
                  this.jjmatchedPos = 5;
               } else if ((var23 & 8388608L) != 0L) {
                  this.jjmatchedKind = 407;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 3145728L, var7, 0L, var11, 1099512152064L, var15, 0L, var19, 0L, var23, 0L);
            case 'e':
               if ((var3 & 4398046511104L) != 0L) {
                  this.jjmatchedKind = 106;
                  this.jjmatchedPos = 5;
               } else if ((var3 & 288230376151711744L) != 0L) {
                  this.jjmatchedKind = 122;
                  this.jjmatchedPos = 5;
               } else if ((var7 & 1L) != 0L) {
                  this.jjmatchedKind = 128;
                  this.jjmatchedPos = 5;
               } else if ((var7 & 32L) != 0L) {
                  this.jjmatchedKind = 133;
                  this.jjmatchedPos = 5;
               } else if ((var7 & 36028797018963968L) != 0L) {
                  this.jjmatchedKind = 183;
                  this.jjmatchedPos = 5;
               } else if ((var7 & 72057594037927936L) != 0L) {
                  this.jjmatchedKind = 184;
                  this.jjmatchedPos = 5;
               } else if ((var11 & 67108864L) != 0L) {
                  this.jjmatchedKind = 218;
                  this.jjmatchedPos = 5;
               } else if ((var11 & 1152921504606846976L) != 0L) {
                  this.jjmatchedKind = 252;
                  this.jjmatchedPos = 5;
               } else if ((var11 & 4611686018427387904L) != 0L) {
                  this.jjmatchedKind = 254;
                  this.jjmatchedPos = 5;
               } else if ((var23 & 1L) != 0L) {
                  this.jjmatchedKind = 384;
                  this.jjmatchedPos = 5;
               } else if ((var23 & 1048576L) != 0L) {
                  this.jjmatchedKind = 404;
                  this.jjmatchedPos = 5;
               } else if ((var23 & 1099511627776L) != 0L) {
                  this.jjmatchedKind = 424;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 32768L, var7, 1099511627776L, var11, 8388608L, var15, 2256200007680000L, var19, 1152921504606846976L, var23, 288230934497462276L);
            case 'f':
               if ((var11 & 1L) != 0L) {
                  this.jjmatchedKind = 192;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 9007199254740992L);
            case 'g':
               if ((var7 & 268435456L) != 0L) {
                  this.jjmatchedKind = 156;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 131072L, var11, 0L, var15, 0L, var19, Long.MIN_VALUE, var23, 4194816L);
            case 'h':
               if ((var15 & 35184372088832L) != 0L) {
                  this.jjmatchedKind = 301;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 134217728L);
            case 'i':
               return this.jjMoveStringLiteralDfa6_0(var3, 1152921504606846976L, var7, 3223322624L, var11, 36028865788772354L, var15, 72620690087346176L, var19, 288371113640099840L, var23, 0L);
            case 'l':
               if ((var7 & 8388608L) != 0L) {
                  this.jjmatchedKind = 151;
                  this.jjmatchedPos = 5;
               } else if ((var11 & 2147483648L) != 0L) {
                  this.jjmatchedKind = 223;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 65536L) != 0L) {
                  this.jjmatchedKind = 272;
                  this.jjmatchedPos = 5;
               } else if ((var19 & 16L) != 0L) {
                  this.jjmatchedKind = 324;
                  this.jjmatchedPos = 5;
               } else if ((var23 & 64L) != 0L) {
                  this.jjmatchedKind = 390;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 36028797018963968L, var7, 0L, var11, 27021597764485120L, var15, 22517998136852480L, var19, 65536L, var23, 36028797018963968L);
            case 'm':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 35184372088832L, var15, 279172874240L, var19, 17661039738880L, var23, 0L);
            case 'n':
               if ((var3 & 4294967296L) != 0L) {
                  this.jjmatchedKind = 96;
                  this.jjmatchedPos = 5;
               } else if ((var11 & 64L) != 0L) {
                  this.jjmatchedKind = 198;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 131072L) != 0L) {
                  this.jjmatchedKind = 273;
                  this.jjmatchedPos = 5;
               } else if ((var23 & 8796093022208L) != 0L) {
                  this.jjmatchedKind = 427;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, -4611140385782104064L, var7, 144396663052568576L, var11, 0L, var15, 80L, var19, 8796093023232L, var23, 862034296045576L);
            case 'o':
               return this.jjMoveStringLiteralDfa6_0(var3, 2308094809027379200L, var7, 0L, var11, 1688867040133120L, var15, 0L, var19, 8192L, var23, 0L);
            case 'p':
               if ((var19 & 2048L) != 0L) {
                  this.jjmatchedKind = 331;
                  this.jjmatchedPos = 5;
               }
               break;
            case 'r':
               if ((var3 & 562949953421312L) != 0L) {
                  this.jjmatchedKind = 113;
                  this.jjmatchedPos = 5;
               } else if ((var19 & 2L) != 0L) {
                  this.jjmatchedKind = 321;
                  this.jjmatchedPos = 5;
               } else if ((var19 & 72057594037927936L) != 0L) {
                  this.jjmatchedKind = 376;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 234187936537513984L, var7, 0L, var11, 292470093103104L, var15, 0L, var19, 2882374130261295616L, var23, 34359738400L);
            case 's':
               if ((var7 & 1024L) != 0L) {
                  this.jjmatchedKind = 138;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 8L) != 0L) {
                  this.jjmatchedKind = 259;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 262144L) != 0L) {
                  this.jjmatchedKind = 274;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 1100048498688L, var7, 2336462209024L, var11, 0L, var15, 0L, var19, 66977984L, var23, 4611826755915759616L);
            case 't':
               if ((var3 & 8589934592L) != 0L) {
                  this.jjmatchedKind = 97;
                  this.jjmatchedPos = 5;
               } else if ((var7 & 64L) != 0L) {
                  this.jjmatchedKind = 134;
                  this.jjmatchedPos = 5;
               } else if ((var7 & 274877906944L) != 0L) {
                  this.jjmatchedKind = 166;
                  this.jjmatchedPos = 5;
               } else if ((var11 & 1024L) != 0L) {
                  this.jjmatchedKind = 202;
                  this.jjmatchedPos = 5;
               } else if ((var11 & 8589934592L) != 0L) {
                  this.jjmatchedKind = 225;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 33554432L) != 0L) {
                  this.jjmatchedKind = 281;
                  this.jjmatchedPos = 5;
               } else if ((var19 & 4L) != 0L) {
                  this.jjmatchedKind = 322;
                  this.jjmatchedPos = 5;
               } else if ((var19 & 8L) != 0L) {
                  this.jjmatchedKind = 323;
                  this.jjmatchedPos = 5;
               } else if ((var19 & 281474976710656L) != 0L) {
                  this.jjmatchedKind = 368;
                  this.jjmatchedPos = 5;
               } else if ((var23 & 4398046511104L) != 0L) {
                  this.jjmatchedKind = 426;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 3221226496L, var7, 17592186045056L, var11, 0L, var15, 16777216L, var19, 4413078896896L, var23, 0L);
            case 'u':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 18014398509481984L, var23, 0L);
            case 'v':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 0L, var15, 2199023255808L, var19, 0L, var23, 0L);
            case 'w':
               if ((var15 & 1024L) != 0L) {
                  this.jjmatchedKind = 266;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 2305843009213693952L, var15, 0L, var19, 0L, var23, 0L);
            case 'x':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 16L, var11, 0L, var15, 0L, var19, 0L, var23, 0L);
            case 'y':
               if ((var3 & 65536L) != 0L) {
                  this.jjmatchedKind = 80;
                  this.jjmatchedPos = 5;
               } else if ((var15 & 144115188075855872L) != 0L) {
                  this.jjmatchedKind = 313;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 268435456L, var23, 0L);
         }

         return this.jjMoveNfa_0(0, 5);
      }
   }

   private final int jjMoveStringLiteralDfa6_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15, long var17, long var19, long var21, long var23) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5) | (var11 = var11 & var9) | (var15 = var15 & var13) | (var19 = var19 & var17) | (var23 = var23 & var21)) == 0L) {
         return this.jjMoveNfa_0(0, 5);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var26) {
            return this.jjMoveNfa_0(0, 5);
         }

         switch (this.curChar) {
            case 'A':
               return this.jjMoveStringLiteralDfa7_0(var3, 72057800196358144L, var7, 144115190223341568L, var11, 27303072740933632L, var15, 2199023255568L, var19, -9223372021822390016L, var23, 134217728L);
            case 'B':
               return this.jjMoveStringLiteralDfa7_0(var3, 1152921504606846976L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 2214592512L);
            case 'C':
               if ((var11 & 2L) != 0L) {
                  this.jjmatchedKind = 193;
                  this.jjmatchedPos = 6;
               } else if ((var15 & 8589934592L) != 0L) {
                  this.jjmatchedKind = 289;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, -9221120236504219648L, var7, 0L, var11, 4503599929360384L, var15, 0L, var19, 0L, var23, 281474976710656L);
            case 'D':
               if ((var15 & 2251799813685248L) != 0L) {
                  this.jjmatchedKind = 307;
                  this.jjmatchedPos = 6;
               }
               break;
            case 'E':
               if ((var3 & 1048576L) != 0L) {
                  this.jjmatchedKind = 84;
                  this.jjmatchedPos = 6;
               } else if ((var3 & 1073741824L) != 0L) {
                  this.jjmatchedKind = 94;
                  this.jjmatchedPos = 6;
               } else if ((var3 & 18014398509481984L) != 0L) {
                  this.jjmatchedKind = 118;
                  this.jjmatchedPos = 6;
               } else if ((var7 & 512L) != 0L) {
                  this.jjmatchedKind = 137;
                  this.jjmatchedPos = 6;
               } else if ((var11 & 16384L) != 0L) {
                  this.jjmatchedKind = 206;
                  this.jjmatchedPos = 6;
               } else if ((var11 & 1099511627776L) != 0L) {
                  this.jjmatchedKind = 232;
                  this.jjmatchedPos = 6;
               } else if ((var19 & 128L) != 0L) {
                  this.jjmatchedKind = 327;
                  this.jjmatchedPos = 6;
               } else if ((var19 & 4398046511104L) != 0L) {
                  this.jjmatchedKind = 362;
                  this.jjmatchedPos = 6;
               } else if ((var19 & 17592186044416L) != 0L) {
                  this.jjmatchedKind = 364;
                  this.jjmatchedPos = 6;
               } else if ((var23 & 131072L) != 0L) {
                  this.jjmatchedKind = 401;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 144115188077953024L, var7, 2199023255568L, var11, 262144L, var15, 72057886095704320L, var19, 134217728L, var23, 4194304L);
            case 'F':
            case 'H':
            case 'K':
            case 'Q':
            case 'W':
            case 'X':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '`':
            case 'f':
            case 'h':
            case 'k':
            case 'q':
            case 'w':
            case 'x':
            default:
               break;
            case 'G':
               if ((var7 & 281474976710656L) != 0L) {
                  this.jjmatchedKind = 176;
                  this.jjmatchedPos = 6;
               } else if ((var15 & 64L) != 0L) {
                  this.jjmatchedKind = 262;
                  this.jjmatchedPos = 6;
               } else if ((var23 & 17179869184L) != 0L) {
                  this.jjmatchedKind = 418;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 0L, var11, 0L, var15, 8796093022208L, var19, 0L, var23, 0L);
            case 'I':
               return this.jjMoveStringLiteralDfa7_0(var3, 2147488768L, var7, 17729624998016L, var11, 8796093022208L, var15, 4294967296L, var19, 2305843078000222272L, var23, 45053588459765760L);
            case 'J':
               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 256L);
            case 'L':
               if ((var3 & 9007199254740992L) != 0L) {
                  this.jjmatchedKind = 117;
                  this.jjmatchedPos = 6;
               } else if ((var7 & 288230376151711744L) != 0L) {
                  this.jjmatchedKind = 186;
                  this.jjmatchedPos = 6;
               } else if ((var11 & 8192L) != 0L) {
                  this.jjmatchedKind = 205;
                  this.jjmatchedPos = 6;
               } else if ((var15 & 549755813888L) != 0L) {
                  this.jjmatchedKind = 295;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 17179869184L, var11, 0L, var15, Long.MIN_VALUE, var19, 0L, var23, 0L);
            case 'M':
               if ((var19 & 268435456L) != 0L) {
                  this.jjmatchedKind = 348;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 18014398509481984L, var23, 512L);
            case 'N':
               if ((var3 & 32768L) != 0L) {
                  this.jjmatchedKind = 79;
                  this.jjmatchedPos = 6;
               } else if ((var7 & 131072L) != 0L) {
                  this.jjmatchedKind = 145;
                  this.jjmatchedPos = 6;
               } else if ((var11 & 2305843009213693952L) != 0L) {
                  this.jjmatchedKind = 253;
                  this.jjmatchedPos = 6;
               } else if ((var15 & 34359738368L) != 0L) {
                  this.jjmatchedKind = 291;
                  this.jjmatchedPos = 6;
               } else if ((var19 & 1099511627776L) != 0L) {
                  this.jjmatchedKind = 360;
                  this.jjmatchedPos = 6;
               } else if ((var19 & 140737488355328L) != 0L) {
                  this.jjmatchedKind = 367;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 4611686018427387904L, var7, 0L, var11, 37717732786962432L, var15, 67108864L, var19, 0L, var23, 549755815936L);
            case 'O':
               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 2097152L, var11, 2199023255552L, var15, 0L, var19, 0L, var23, 0L);
            case 'P':
               return this.jjMoveStringLiteralDfa7_0(var3, 1099511627776L, var7, 0L, var11, 2048L, var15, 0L, var19, 0L, var23, 4611686018427387904L);
            case 'R':
               if ((var7 & 1099511627776L) != 0L) {
                  this.jjmatchedKind = 168;
                  this.jjmatchedPos = 6;
               } else if ((var15 & 32L) != 0L) {
                  this.jjmatchedKind = 261;
                  this.jjmatchedPos = 6;
               } else if ((var15 & 2147483648L) != 0L) {
                  this.jjmatchedKind = 287;
                  this.jjmatchedPos = 6;
               } else if ((var15 & 4398046511104L) != 0L) {
                  this.jjmatchedKind = 298;
                  this.jjmatchedPos = 6;
               } else if ((var23 & 288230376151711744L) != 0L) {
                  this.jjmatchedKind = 442;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 1152991873351024640L, var23, 0L);
            case 'S':
               if ((var19 & 1024L) != 0L) {
                  this.jjmatchedKind = 330;
                  this.jjmatchedPos = 6;
               } else if ((var23 & 32L) != 0L) {
                  this.jjmatchedKind = 389;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 2305843009213693952L, var7, 0L, var11, 0L, var15, 0L, var19, 864726312827224064L, var23, 4L);
            case 'T':
               if ((var3 & 17179869184L) != 0L) {
                  this.jjmatchedKind = 98;
                  this.jjmatchedPos = 6;
               } else if ((var3 & 549755813888L) != 0L) {
                  this.jjmatchedKind = 103;
                  this.jjmatchedPos = 6;
               } else if ((var3 & 17592186044416L) != 0L) {
                  this.jjmatchedKind = 108;
                  this.jjmatchedPos = 6;
               } else if ((var3 & 36028797018963968L) != 0L) {
                  this.jjmatchedKind = 119;
                  this.jjmatchedPos = 6;
               } else if ((var19 & 512L) != 0L) {
                  this.jjmatchedKind = 329;
                  this.jjmatchedPos = 6;
               } else if ((var23 & 8L) != 0L) {
                  this.jjmatchedKind = 387;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 527800041734152L, var7, 9663676416L, var11, 4398046511104L, var15, 206175207424L, var19, 8813272924160L, var23, 577164482694873088L);
            case 'U':
               return this.jjMoveStringLiteralDfa7_0(var3, 274877906944L, var7, 0L, var11, 524288L, var15, 22517998136852480L, var19, 0L, var23, 0L);
            case 'V':
               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 0L, var11, 16809984L, var15, 0L, var19, 0L, var23, 0L);
            case 'Y':
               if ((var11 & 65536L) != 0L) {
                  this.jjmatchedKind = 208;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 549755813888L, var23, 0L);
            case 'Z':
               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 0L, var11, 0L, var15, 562949953421312L, var19, 0L, var23, 0L);
            case '_':
               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 0L, var11, 35184372088832L, var15, 0L, var19, 0L, var23, 0L);
            case 'a':
               return this.jjMoveStringLiteralDfa7_0(var3, 72057800196358144L, var7, 144115190223341568L, var11, 27303072740933632L, var15, 2199023255568L, var19, -9223372021822390016L, var23, 134217728L);
            case 'b':
               return this.jjMoveStringLiteralDfa7_0(var3, 1152921504606846976L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 2214592512L);
            case 'c':
               if ((var11 & 2L) != 0L) {
                  this.jjmatchedKind = 193;
                  this.jjmatchedPos = 6;
               } else if ((var15 & 8589934592L) != 0L) {
                  this.jjmatchedKind = 289;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, -9221120236504219648L, var7, 0L, var11, 4503599929360384L, var15, 0L, var19, 0L, var23, 281474976710656L);
            case 'd':
               if ((var15 & 2251799813685248L) != 0L) {
                  this.jjmatchedKind = 307;
                  this.jjmatchedPos = 6;
               }
               break;
            case 'e':
               if ((var3 & 1048576L) != 0L) {
                  this.jjmatchedKind = 84;
                  this.jjmatchedPos = 6;
               } else if ((var3 & 1073741824L) != 0L) {
                  this.jjmatchedKind = 94;
                  this.jjmatchedPos = 6;
               } else if ((var3 & 18014398509481984L) != 0L) {
                  this.jjmatchedKind = 118;
                  this.jjmatchedPos = 6;
               } else if ((var7 & 512L) != 0L) {
                  this.jjmatchedKind = 137;
                  this.jjmatchedPos = 6;
               } else if ((var11 & 16384L) != 0L) {
                  this.jjmatchedKind = 206;
                  this.jjmatchedPos = 6;
               } else if ((var11 & 1099511627776L) != 0L) {
                  this.jjmatchedKind = 232;
                  this.jjmatchedPos = 6;
               } else if ((var19 & 128L) != 0L) {
                  this.jjmatchedKind = 327;
                  this.jjmatchedPos = 6;
               } else if ((var19 & 4398046511104L) != 0L) {
                  this.jjmatchedKind = 362;
                  this.jjmatchedPos = 6;
               } else if ((var19 & 17592186044416L) != 0L) {
                  this.jjmatchedKind = 364;
                  this.jjmatchedPos = 6;
               } else if ((var23 & 131072L) != 0L) {
                  this.jjmatchedKind = 401;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 144115188077953024L, var7, 2199023255568L, var11, 262144L, var15, 72057886095704320L, var19, 134217728L, var23, 4194304L);
            case 'g':
               if ((var7 & 281474976710656L) != 0L) {
                  this.jjmatchedKind = 176;
                  this.jjmatchedPos = 6;
               } else if ((var15 & 64L) != 0L) {
                  this.jjmatchedKind = 262;
                  this.jjmatchedPos = 6;
               } else if ((var23 & 17179869184L) != 0L) {
                  this.jjmatchedKind = 418;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 0L, var11, 0L, var15, 8796093022208L, var19, 0L, var23, 0L);
            case 'i':
               return this.jjMoveStringLiteralDfa7_0(var3, 2147488768L, var7, 17729624998016L, var11, 8796093022208L, var15, 4294967296L, var19, 2305843078000222272L, var23, 45053588459765760L);
            case 'j':
               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 256L);
            case 'l':
               if ((var3 & 9007199254740992L) != 0L) {
                  this.jjmatchedKind = 117;
                  this.jjmatchedPos = 6;
               } else if ((var7 & 288230376151711744L) != 0L) {
                  this.jjmatchedKind = 186;
                  this.jjmatchedPos = 6;
               } else if ((var11 & 8192L) != 0L) {
                  this.jjmatchedKind = 205;
                  this.jjmatchedPos = 6;
               } else if ((var15 & 549755813888L) != 0L) {
                  this.jjmatchedKind = 295;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 17179869184L, var11, 0L, var15, Long.MIN_VALUE, var19, 0L, var23, 0L);
            case 'm':
               if ((var19 & 268435456L) != 0L) {
                  this.jjmatchedKind = 348;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 18014398509481984L, var23, 512L);
            case 'n':
               if ((var3 & 32768L) != 0L) {
                  this.jjmatchedKind = 79;
                  this.jjmatchedPos = 6;
               } else if ((var7 & 131072L) != 0L) {
                  this.jjmatchedKind = 145;
                  this.jjmatchedPos = 6;
               } else if ((var11 & 2305843009213693952L) != 0L) {
                  this.jjmatchedKind = 253;
                  this.jjmatchedPos = 6;
               } else if ((var15 & 34359738368L) != 0L) {
                  this.jjmatchedKind = 291;
                  this.jjmatchedPos = 6;
               } else if ((var19 & 1099511627776L) != 0L) {
                  this.jjmatchedKind = 360;
                  this.jjmatchedPos = 6;
               } else if ((var19 & 140737488355328L) != 0L) {
                  this.jjmatchedKind = 367;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 4611686018427387904L, var7, 0L, var11, 37717732786962432L, var15, 67108864L, var19, 0L, var23, 549755815936L);
            case 'o':
               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 2097152L, var11, 2199023255552L, var15, 0L, var19, 0L, var23, 0L);
            case 'p':
               return this.jjMoveStringLiteralDfa7_0(var3, 1099511627776L, var7, 0L, var11, 2048L, var15, 0L, var19, 0L, var23, 4611686018427387904L);
            case 'r':
               if ((var7 & 1099511627776L) != 0L) {
                  this.jjmatchedKind = 168;
                  this.jjmatchedPos = 6;
               } else if ((var15 & 32L) != 0L) {
                  this.jjmatchedKind = 261;
                  this.jjmatchedPos = 6;
               } else if ((var15 & 2147483648L) != 0L) {
                  this.jjmatchedKind = 287;
                  this.jjmatchedPos = 6;
               } else if ((var15 & 4398046511104L) != 0L) {
                  this.jjmatchedKind = 298;
                  this.jjmatchedPos = 6;
               } else if ((var23 & 288230376151711744L) != 0L) {
                  this.jjmatchedKind = 442;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 1152991873351024640L, var23, 0L);
            case 's':
               if ((var19 & 1024L) != 0L) {
                  this.jjmatchedKind = 330;
                  this.jjmatchedPos = 6;
               } else if ((var23 & 32L) != 0L) {
                  this.jjmatchedKind = 389;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 2305843009213693952L, var7, 0L, var11, 0L, var15, 0L, var19, 864726312827224064L, var23, 4L);
            case 't':
               if ((var3 & 17179869184L) != 0L) {
                  this.jjmatchedKind = 98;
                  this.jjmatchedPos = 6;
               } else if ((var3 & 549755813888L) != 0L) {
                  this.jjmatchedKind = 103;
                  this.jjmatchedPos = 6;
               } else if ((var3 & 17592186044416L) != 0L) {
                  this.jjmatchedKind = 108;
                  this.jjmatchedPos = 6;
               } else if ((var3 & 36028797018963968L) != 0L) {
                  this.jjmatchedKind = 119;
                  this.jjmatchedPos = 6;
               } else if ((var19 & 512L) != 0L) {
                  this.jjmatchedKind = 329;
                  this.jjmatchedPos = 6;
               } else if ((var23 & 8L) != 0L) {
                  this.jjmatchedKind = 387;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 527800041734152L, var7, 9663676416L, var11, 4398046511104L, var15, 206175207424L, var19, 8813272924160L, var23, 577164482694873088L);
            case 'u':
               return this.jjMoveStringLiteralDfa7_0(var3, 274877906944L, var7, 0L, var11, 524288L, var15, 22517998136852480L, var19, 0L, var23, 0L);
            case 'v':
               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 0L, var11, 16809984L, var15, 0L, var19, 0L, var23, 0L);
            case 'y':
               if ((var11 & 65536L) != 0L) {
                  this.jjmatchedKind = 208;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 549755813888L, var23, 0L);
            case 'z':
               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 0L, var11, 0L, var15, 562949953421312L, var19, 0L, var23, 0L);
         }

         return this.jjMoveNfa_0(0, 6);
      }
   }

   private final int jjMoveStringLiteralDfa7_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15, long var17, long var19, long var21, long var23) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5) | (var11 = var11 & var9) | (var15 = var15 & var13) | (var19 = var19 & var17) | (var23 = var23 & var21)) == 0L) {
         return this.jjMoveNfa_0(0, 6);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var26) {
            return this.jjMoveNfa_0(0, 6);
         }

         switch (this.curChar) {
            case 'A':
               return this.jjMoveStringLiteralDfa8_0(var3, 2251799813685248L, var7, 0L, var11, 0L, var15, 0L, var19, 2305843009213693952L, var23, 4611686018427387904L);
            case 'B':
               return this.jjMoveStringLiteralDfa8_0(var3, 72057594037927936L, var7, 0L, var11, 0L, var15, 0L, var19, 18014398509482240L, var23, 0L);
            case 'C':
               if ((var7 & 16L) != 0L) {
                  this.jjmatchedKind = 132;
                  this.jjmatchedPos = 7;
               } else if ((var23 & 9007199254740992L) != 0L) {
                  this.jjmatchedKind = 437;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 2199023255552L, var11, 8388608L, var15, 0L, var19, 0L, var23, 549755813888L);
            case 'D':
               if ((var3 & 2097152L) != 0L) {
                  this.jjmatchedKind = 85;
                  this.jjmatchedPos = 7;
               } else if ((var3 & 144115188075855872L) != 0L) {
                  this.jjmatchedKind = 121;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 17179869184L) != 0L) {
                  this.jjmatchedKind = 290;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 256L);
            case 'E':
               if ((var3 & 8L) != 0L) {
                  this.jjmatchedKind = 67;
                  this.jjmatchedPos = 7;
               } else if ((var3 & 536870912L) != 0L) {
                  this.jjmatchedKind = 93;
                  this.jjmatchedPos = 7;
               } else if ((var3 & 274877906944L) != 0L) {
                  this.jjmatchedKind = 102;
                  this.jjmatchedPos = 7;
               } else if ((var3 & 1152921504606846976L) != 0L) {
                  this.jjmatchedKind = 124;
                  this.jjmatchedPos = 7;
               } else if ((var11 & 32768L) != 0L) {
                  this.jjmatchedKind = 207;
                  this.jjmatchedPos = 7;
               } else if ((var11 & 16777216L) != 0L) {
                  this.jjmatchedKind = 216;
                  this.jjmatchedPos = 7;
               } else if ((var11 & 4398046511104L) != 0L) {
                  this.jjmatchedKind = 234;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 8796093022208L) != 0L) {
                  this.jjmatchedKind = 299;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 562949953421312L) != 0L) {
                  this.jjmatchedKind = 305;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 4503599627370496L) != 0L) {
                  this.jjmatchedKind = 308;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 18014398509481984L) != 0L) {
                  this.jjmatchedKind = 310;
                  this.jjmatchedPos = 7;
               } else if ((var15 & Long.MIN_VALUE) != 0L) {
                  this.jjmatchedKind = 319;
                  this.jjmatchedPos = 7;
               } else if ((var19 & 17179869184L) != 0L) {
                  this.jjmatchedKind = 354;
                  this.jjmatchedPos = 7;
               } else if ((var19 & 35184372088832L) != 0L) {
                  this.jjmatchedKind = 365;
                  this.jjmatchedPos = 7;
               } else if ((var19 & 576460752303423488L) != 0L) {
                  this.jjmatchedKind = 379;
                  this.jjmatchedPos = 7;
               } else if ((var23 & 281474976710656L) != 0L) {
                  this.jjmatchedKind = 432;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 4611686018528051200L, var7, 0L, var11, 1688849860263936L, var15, 68736253952L, var19, 70368744177664L, var23, 576460760893358592L);
            case 'F':
            case 'H':
            case 'J':
            case 'Q':
            case 'W':
            case 'X':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '`':
            case 'f':
            case 'h':
            case 'j':
            case 'q':
            case 'w':
            case 'x':
            default:
               break;
            case 'G':
               if ((var11 & 36028797018963968L) != 0L) {
                  this.jjmatchedKind = 247;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 262144L, var15, 0L, var19, 0L, var23, 0L);
            case 'I':
               return this.jjMoveStringLiteralDfa8_0(var3, 240518168576L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 703721801515008L);
            case 'K':
               if ((var11 & 268435456L) != 0L) {
                  this.jjmatchedKind = 220;
                  this.jjmatchedPos = 7;
               }
               break;
            case 'L':
               if ((var7 & 2048L) != 0L) {
                  this.jjmatchedKind = 139;
                  this.jjmatchedPos = 7;
               } else if ((var7 & 144115188075855872L) != 0L) {
                  this.jjmatchedKind = 185;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 2199023255552L) != 0L) {
                  this.jjmatchedKind = 297;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 17179869184L, var11, 0L, var15, 0L, var19, 0L, var23, 2214592512L);
            case 'M':
               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 15032385536L, var23, 0L);
            case 'N':
               if ((var7 & 2097152L) != 0L) {
                  this.jjmatchedKind = 149;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 8796093022208L, var15, 279172874240L, var19, 134225920L, var23, 17592186044416L);
            case 'O':
               return this.jjMoveStringLiteralDfa8_0(var3, 1101659112448L, var7, 17600775979136L, var11, 0L, var15, 0L, var19, 64L, var23, 0L);
            case 'P':
               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 549755813888L, var23, 0L);
            case 'R':
               if ((var11 & 2199023255552L) != 0L) {
                  this.jjmatchedKind = 233;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 256L) != 0L) {
                  this.jjmatchedKind = 264;
                  this.jjmatchedPos = 7;
               } else if ((var23 & 134217728L) != 0L) {
                  this.jjmatchedKind = 411;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 281474977234944L, var15, 16L, var19, 0L, var23, 0L);
            case 'S':
               if ((var11 & 2048L) != 0L) {
                  this.jjmatchedKind = 203;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 67108864L) != 0L) {
                  this.jjmatchedKind = 282;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 72057594037927936L) != 0L) {
                  this.jjmatchedKind = 312;
                  this.jjmatchedPos = 7;
               } else if ((var23 & 4L) != 0L) {
                  this.jjmatchedKind = 386;
                  this.jjmatchedPos = 7;
               }
               break;
            case 'T':
               if ((var3 & Long.MIN_VALUE) != 0L) {
                  this.jjmatchedKind = 127;
                  this.jjmatchedPos = 7;
               } else if ((var11 & 33554432L) != 0L) {
                  this.jjmatchedKind = 217;
                  this.jjmatchedPos = 7;
               } else if ((var11 & 68719476736L) != 0L) {
                  this.jjmatchedKind = 228;
                  this.jjmatchedPos = 7;
               } else if ((var23 & 2048L) != 0L) {
                  this.jjmatchedKind = 395;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 2305843009213693952L, var7, 139586437120L, var11, 31525197391593472L, var15, 0L, var19, -8935141591983587328L, var23, 36028797018963968L);
            case 'U':
               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 35184372088832L, var15, 0L, var19, 0L, var23, 0L);
            case 'V':
               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 16384L);
            case 'Y':
               if ((var7 & 1073741824L) != 0L) {
                  this.jjmatchedKind = 158;
                  this.jjmatchedPos = 7;
               } else if ((var19 & 32768L) != 0L) {
                  this.jjmatchedKind = 335;
                  this.jjmatchedPos = 7;
               } else if ((var19 & 1152921504606846976L) != 0L) {
                  this.jjmatchedKind = 380;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 0L, var15, 137438953472L, var19, 0L, var23, 0L);
            case 'Z':
               return this.jjMoveStringLiteralDfa8_0(var3, 4096L, var7, 0L, var11, 0L, var15, 0L, var19, 65536L, var23, 0L);
            case '_':
               return this.jjMoveStringLiteralDfa8_0(var3, 527765581332480L, var7, 0L, var11, 17179869184L, var15, 0L, var19, 8796160000000L, var23, 4194304L);
            case 'a':
               return this.jjMoveStringLiteralDfa8_0(var3, 2251799813685248L, var7, 0L, var11, 0L, var15, 0L, var19, 2305843009213693952L, var23, 4611686018427387904L);
            case 'b':
               return this.jjMoveStringLiteralDfa8_0(var3, 72057594037927936L, var7, 0L, var11, 0L, var15, 0L, var19, 18014398509482240L, var23, 0L);
            case 'c':
               if ((var7 & 16L) != 0L) {
                  this.jjmatchedKind = 132;
                  this.jjmatchedPos = 7;
               } else if ((var23 & 9007199254740992L) != 0L) {
                  this.jjmatchedKind = 437;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 2199023255552L, var11, 8388608L, var15, 0L, var19, 0L, var23, 549755813888L);
            case 'd':
               if ((var3 & 2097152L) != 0L) {
                  this.jjmatchedKind = 85;
                  this.jjmatchedPos = 7;
               } else if ((var3 & 144115188075855872L) != 0L) {
                  this.jjmatchedKind = 121;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 17179869184L) != 0L) {
                  this.jjmatchedKind = 290;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 256L);
            case 'e':
               if ((var3 & 8L) != 0L) {
                  this.jjmatchedKind = 67;
                  this.jjmatchedPos = 7;
               } else if ((var3 & 536870912L) != 0L) {
                  this.jjmatchedKind = 93;
                  this.jjmatchedPos = 7;
               } else if ((var3 & 274877906944L) != 0L) {
                  this.jjmatchedKind = 102;
                  this.jjmatchedPos = 7;
               } else if ((var3 & 1152921504606846976L) != 0L) {
                  this.jjmatchedKind = 124;
                  this.jjmatchedPos = 7;
               } else if ((var11 & 32768L) != 0L) {
                  this.jjmatchedKind = 207;
                  this.jjmatchedPos = 7;
               } else if ((var11 & 16777216L) != 0L) {
                  this.jjmatchedKind = 216;
                  this.jjmatchedPos = 7;
               } else if ((var11 & 4398046511104L) != 0L) {
                  this.jjmatchedKind = 234;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 8796093022208L) != 0L) {
                  this.jjmatchedKind = 299;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 562949953421312L) != 0L) {
                  this.jjmatchedKind = 305;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 4503599627370496L) != 0L) {
                  this.jjmatchedKind = 308;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 18014398509481984L) != 0L) {
                  this.jjmatchedKind = 310;
                  this.jjmatchedPos = 7;
               } else if ((var15 & Long.MIN_VALUE) != 0L) {
                  this.jjmatchedKind = 319;
                  this.jjmatchedPos = 7;
               } else if ((var19 & 17179869184L) != 0L) {
                  this.jjmatchedKind = 354;
                  this.jjmatchedPos = 7;
               } else if ((var19 & 35184372088832L) != 0L) {
                  this.jjmatchedKind = 365;
                  this.jjmatchedPos = 7;
               } else if ((var19 & 576460752303423488L) != 0L) {
                  this.jjmatchedKind = 379;
                  this.jjmatchedPos = 7;
               } else if ((var23 & 281474976710656L) != 0L) {
                  this.jjmatchedKind = 432;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 4611686018528051200L, var7, 0L, var11, 1688849860263936L, var15, 68736253952L, var19, 70368744177664L, var23, 576460760893358592L);
            case 'g':
               if ((var11 & 36028797018963968L) != 0L) {
                  this.jjmatchedKind = 247;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 262144L, var15, 0L, var19, 0L, var23, 0L);
            case 'i':
               return this.jjMoveStringLiteralDfa8_0(var3, 240518168576L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 703721801515008L);
            case 'k':
               if ((var11 & 268435456L) != 0L) {
                  this.jjmatchedKind = 220;
                  this.jjmatchedPos = 7;
               }
               break;
            case 'l':
               if ((var7 & 2048L) != 0L) {
                  this.jjmatchedKind = 139;
                  this.jjmatchedPos = 7;
               } else if ((var7 & 144115188075855872L) != 0L) {
                  this.jjmatchedKind = 185;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 2199023255552L) != 0L) {
                  this.jjmatchedKind = 297;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 17179869184L, var11, 0L, var15, 0L, var19, 0L, var23, 2214592512L);
            case 'm':
               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 15032385536L, var23, 0L);
            case 'n':
               if ((var7 & 2097152L) != 0L) {
                  this.jjmatchedKind = 149;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 8796093022208L, var15, 279172874240L, var19, 134225920L, var23, 17592186044416L);
            case 'o':
               return this.jjMoveStringLiteralDfa8_0(var3, 1101659112448L, var7, 17600775979136L, var11, 0L, var15, 0L, var19, 64L, var23, 0L);
            case 'p':
               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 549755813888L, var23, 0L);
            case 'r':
               if ((var11 & 2199023255552L) != 0L) {
                  this.jjmatchedKind = 233;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 256L) != 0L) {
                  this.jjmatchedKind = 264;
                  this.jjmatchedPos = 7;
               } else if ((var23 & 134217728L) != 0L) {
                  this.jjmatchedKind = 411;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 281474977234944L, var15, 16L, var19, 0L, var23, 0L);
            case 's':
               if ((var11 & 2048L) != 0L) {
                  this.jjmatchedKind = 203;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 67108864L) != 0L) {
                  this.jjmatchedKind = 282;
                  this.jjmatchedPos = 7;
               } else if ((var15 & 72057594037927936L) != 0L) {
                  this.jjmatchedKind = 312;
                  this.jjmatchedPos = 7;
               } else if ((var23 & 4L) != 0L) {
                  this.jjmatchedKind = 386;
                  this.jjmatchedPos = 7;
               }
               break;
            case 't':
               if ((var3 & Long.MIN_VALUE) != 0L) {
                  this.jjmatchedKind = 127;
                  this.jjmatchedPos = 7;
               } else if ((var11 & 33554432L) != 0L) {
                  this.jjmatchedKind = 217;
                  this.jjmatchedPos = 7;
               } else if ((var11 & 68719476736L) != 0L) {
                  this.jjmatchedKind = 228;
                  this.jjmatchedPos = 7;
               } else if ((var23 & 2048L) != 0L) {
                  this.jjmatchedKind = 395;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 2305843009213693952L, var7, 139586437120L, var11, 31525197391593472L, var15, 0L, var19, -8935141591983587328L, var23, 36028797018963968L);
            case 'u':
               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 35184372088832L, var15, 0L, var19, 0L, var23, 0L);
            case 'v':
               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 16384L);
            case 'y':
               if ((var7 & 1073741824L) != 0L) {
                  this.jjmatchedKind = 158;
                  this.jjmatchedPos = 7;
               } else if ((var19 & 32768L) != 0L) {
                  this.jjmatchedKind = 335;
                  this.jjmatchedPos = 7;
               } else if ((var19 & 1152921504606846976L) != 0L) {
                  this.jjmatchedKind = 380;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 0L, var11, 0L, var15, 137438953472L, var19, 0L, var23, 0L);
            case 'z':
               return this.jjMoveStringLiteralDfa8_0(var3, 4096L, var7, 0L, var11, 0L, var15, 0L, var19, 65536L, var23, 0L);
         }

         return this.jjMoveNfa_0(0, 7);
      }
   }

   private final int jjMoveStringLiteralDfa8_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15, long var17, long var19, long var21, long var23) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5) | (var11 = var11 & var9) | (var15 = var15 & var13) | (var19 = var19 & var17) | (var23 = var23 & var21)) == 0L) {
         return this.jjMoveNfa_0(0, 7);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var26) {
            return this.jjMoveNfa_0(0, 7);
         }

         switch (this.curChar) {
            case 'A':
               return this.jjMoveStringLiteralDfa9_0(var3, 4096L, var7, 0L, var11, 0L, var15, 0L, var19, 65536L, var23, 562949953421312L);
            case 'B':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 256L);
            case 'C':
               return this.jjMoveStringLiteralDfa9_0(var3, 4611686018427387904L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 4611826755915743232L);
            case 'D':
               if ((var15 & 16777216L) != 0L) {
                  this.jjmatchedKind = 280;
                  this.jjmatchedPos = 8;
               } else if ((var15 & 68719476736L) != 0L) {
                  this.jjmatchedKind = 292;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 35184372088832L, var7, 0L, var11, 0L, var15, 0L, var19, 2097152L, var23, 0L);
            case 'E':
               if ((var7 & 2147483648L) != 0L) {
                  this.jjmatchedKind = 159;
                  this.jjmatchedPos = 8;
               } else if ((var11 & 524288L) != 0L) {
                  this.jjmatchedKind = 211;
                  this.jjmatchedPos = 8;
               } else if ((var11 & 9007199254740992L) != 0L) {
                  this.jjmatchedKind = 245;
                  this.jjmatchedPos = 8;
               } else if ((var19 & Long.MIN_VALUE) != 0L) {
                  this.jjmatchedKind = 383;
                  this.jjmatchedPos = 8;
               } else if ((var23 & 16384L) != 0L) {
                  this.jjmatchedKind = 398;
                  this.jjmatchedPos = 8;
               } else if ((var23 & 67108864L) != 0L) {
                  this.jjmatchedKind = 410;
                  this.jjmatchedPos = 8;
               } else if ((var23 & 2147483648L) != 0L) {
                  this.jjmatchedKind = 415;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 8650752L, var15, 0L, var19, 18014398509481984L, var23, 34359738368L);
            case 'F':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 131072L, var23, 0L);
            case 'G':
               if ((var11 & 8796093022208L) != 0L) {
                  this.jjmatchedKind = 235;
                  this.jjmatchedPos = 8;
               } else if ((var23 & 17592186044416L) != 0L) {
                  this.jjmatchedKind = 428;
                  this.jjmatchedPos = 8;
               }
               break;
            case 'H':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 1048576L, var23, 0L);
            case 'I':
               return this.jjMoveStringLiteralDfa9_0(var3, 2305843009213693952L, var7, 137438953472L, var11, 22517998136852480L, var15, 4294967296L, var19, 0L, var23, 549755813888L);
            case 'J':
            case 'K':
            case 'V':
            case 'X':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '`':
            case 'j':
            case 'k':
            case 'v':
            case 'x':
            default:
               break;
            case 'L':
               return this.jjMoveStringLiteralDfa9_0(var3, 72057594037927936L, var7, 0L, var11, 0L, var15, 0L, var19, 2305843558969508096L, var23, 4194304L);
            case 'M':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 8912896L, var23, 0L);
            case 'N':
               if ((var3 & 1024L) != 0L) {
                  this.jjmatchedKind = 74;
                  this.jjmatchedPos = 8;
               } else if ((var3 & 2147483648L) != 0L) {
                  this.jjmatchedKind = 95;
                  this.jjmatchedPos = 8;
               } else if ((var7 & 128L) != 0L) {
                  this.jjmatchedKind = 135;
                  this.jjmatchedPos = 8;
               } else if ((var7 & 17592186044416L) != 0L) {
                  this.jjmatchedKind = 172;
                  this.jjmatchedPos = 8;
               } else if ((var19 & 64L) != 0L) {
                  this.jjmatchedKind = 326;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 1305670057984L, var7, 0L, var11, 0L, var15, 0L, var19, 70368744177664L, var23, 512L);
            case 'O':
               return this.jjMoveStringLiteralDfa9_0(var3, 34359738368L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 0L);
            case 'P':
               if ((var19 & 2147483648L) != 0L) {
                  this.jjmatchedKind = 351;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 12884901888L, var23, 0L);
            case 'Q':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 16777216L, var23, 0L);
            case 'R':
               if ((var3 & 33554432L) != 0L) {
                  this.jjmatchedKind = 89;
                  this.jjmatchedPos = 8;
               } else if ((var7 & 8589934592L) != 0L) {
                  this.jjmatchedKind = 161;
                  this.jjmatchedPos = 8;
               } else if ((var23 & 8589934592L) != 0L) {
                  this.jjmatchedKind = 417;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 67108864L, var7, 0L, var11, 0L, var15, 0L, var19, 8796093022208L, var23, 0L);
            case 'S':
               if ((var19 & 288230376151711744L) != 0L) {
                  this.jjmatchedKind = 378;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 35184372088832L, var15, 0L, var19, 262144L, var23, 0L);
            case 'T':
               if ((var7 & 2199023255552L) != 0L) {
                  this.jjmatchedKind = 169;
                  this.jjmatchedPos = 8;
               } else if ((var15 & 274877906944L) != 0L) {
                  this.jjmatchedKind = 294;
                  this.jjmatchedPos = 8;
               } else if ((var19 & 8192L) != 0L) {
                  this.jjmatchedKind = 333;
                  this.jjmatchedPos = 8;
               } else if ((var19 & 134217728L) != 0L) {
                  this.jjmatchedKind = 347;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 2462906046218240L, var7, 0L, var11, 0L, var15, 0L, var19, 68719476736L, var23, 0L);
            case 'U':
               return this.jjMoveStringLiteralDfa9_0(var3, 281474976710656L, var7, 0L, var11, 17179869184L, var15, 0L, var19, 0L, var23, 0L);
            case 'W':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 4194304L, var23, 0L);
            case 'Y':
               if ((var7 & 17179869184L) != 0L) {
                  this.jjmatchedKind = 162;
                  this.jjmatchedPos = 8;
               } else if ((var11 & 281474976710656L) != 0L) {
                  this.jjmatchedKind = 240;
                  this.jjmatchedPos = 8;
               } else if ((var15 & 16L) != 0L) {
                  this.jjmatchedKind = 260;
                  this.jjmatchedPos = 8;
               } else if ((var23 & 36028797018963968L) != 0L) {
                  this.jjmatchedKind = 439;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 33554432L, var23, 0L);
            case '_':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 1688849860263936L, var15, 137438953472L, var19, 0L, var23, 576460752303423488L);
            case 'a':
               return this.jjMoveStringLiteralDfa9_0(var3, 4096L, var7, 0L, var11, 0L, var15, 0L, var19, 65536L, var23, 562949953421312L);
            case 'b':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 256L);
            case 'c':
               return this.jjMoveStringLiteralDfa9_0(var3, 4611686018427387904L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 4611826755915743232L);
            case 'd':
               if ((var15 & 16777216L) != 0L) {
                  this.jjmatchedKind = 280;
                  this.jjmatchedPos = 8;
               } else if ((var15 & 68719476736L) != 0L) {
                  this.jjmatchedKind = 292;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 35184372088832L, var7, 0L, var11, 0L, var15, 0L, var19, 2097152L, var23, 0L);
            case 'e':
               if ((var7 & 2147483648L) != 0L) {
                  this.jjmatchedKind = 159;
                  this.jjmatchedPos = 8;
               } else if ((var11 & 524288L) != 0L) {
                  this.jjmatchedKind = 211;
                  this.jjmatchedPos = 8;
               } else if ((var11 & 9007199254740992L) != 0L) {
                  this.jjmatchedKind = 245;
                  this.jjmatchedPos = 8;
               } else if ((var19 & Long.MIN_VALUE) != 0L) {
                  this.jjmatchedKind = 383;
                  this.jjmatchedPos = 8;
               } else if ((var23 & 16384L) != 0L) {
                  this.jjmatchedKind = 398;
                  this.jjmatchedPos = 8;
               } else if ((var23 & 67108864L) != 0L) {
                  this.jjmatchedKind = 410;
                  this.jjmatchedPos = 8;
               } else if ((var23 & 2147483648L) != 0L) {
                  this.jjmatchedKind = 415;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 8650752L, var15, 0L, var19, 18014398509481984L, var23, 34359738368L);
            case 'f':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 131072L, var23, 0L);
            case 'g':
               if ((var11 & 8796093022208L) != 0L) {
                  this.jjmatchedKind = 235;
                  this.jjmatchedPos = 8;
               } else if ((var23 & 17592186044416L) != 0L) {
                  this.jjmatchedKind = 428;
                  this.jjmatchedPos = 8;
               }
               break;
            case 'h':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 1048576L, var23, 0L);
            case 'i':
               return this.jjMoveStringLiteralDfa9_0(var3, 2305843009213693952L, var7, 137438953472L, var11, 22517998136852480L, var15, 4294967296L, var19, 0L, var23, 549755813888L);
            case 'l':
               return this.jjMoveStringLiteralDfa9_0(var3, 72057594037927936L, var7, 0L, var11, 0L, var15, 0L, var19, 2305843558969508096L, var23, 4194304L);
            case 'm':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 8912896L, var23, 0L);
            case 'n':
               if ((var3 & 1024L) != 0L) {
                  this.jjmatchedKind = 74;
                  this.jjmatchedPos = 8;
               } else if ((var3 & 2147483648L) != 0L) {
                  this.jjmatchedKind = 95;
                  this.jjmatchedPos = 8;
               } else if ((var7 & 128L) != 0L) {
                  this.jjmatchedKind = 135;
                  this.jjmatchedPos = 8;
               } else if ((var7 & 17592186044416L) != 0L) {
                  this.jjmatchedKind = 172;
                  this.jjmatchedPos = 8;
               } else if ((var19 & 64L) != 0L) {
                  this.jjmatchedKind = 326;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 1305670057984L, var7, 0L, var11, 0L, var15, 0L, var19, 70368744177664L, var23, 512L);
            case 'o':
               return this.jjMoveStringLiteralDfa9_0(var3, 34359738368L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 0L);
            case 'p':
               if ((var19 & 2147483648L) != 0L) {
                  this.jjmatchedKind = 351;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 12884901888L, var23, 0L);
            case 'q':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 16777216L, var23, 0L);
            case 'r':
               if ((var3 & 33554432L) != 0L) {
                  this.jjmatchedKind = 89;
                  this.jjmatchedPos = 8;
               } else if ((var7 & 8589934592L) != 0L) {
                  this.jjmatchedKind = 161;
                  this.jjmatchedPos = 8;
               } else if ((var23 & 8589934592L) != 0L) {
                  this.jjmatchedKind = 417;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 67108864L, var7, 0L, var11, 0L, var15, 0L, var19, 8796093022208L, var23, 0L);
            case 's':
               if ((var19 & 288230376151711744L) != 0L) {
                  this.jjmatchedKind = 378;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 35184372088832L, var15, 0L, var19, 262144L, var23, 0L);
            case 't':
               if ((var7 & 2199023255552L) != 0L) {
                  this.jjmatchedKind = 169;
                  this.jjmatchedPos = 8;
               } else if ((var15 & 274877906944L) != 0L) {
                  this.jjmatchedKind = 294;
                  this.jjmatchedPos = 8;
               } else if ((var19 & 8192L) != 0L) {
                  this.jjmatchedKind = 333;
                  this.jjmatchedPos = 8;
               } else if ((var19 & 134217728L) != 0L) {
                  this.jjmatchedKind = 347;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 2462906046218240L, var7, 0L, var11, 0L, var15, 0L, var19, 68719476736L, var23, 0L);
            case 'u':
               return this.jjMoveStringLiteralDfa9_0(var3, 281474976710656L, var7, 0L, var11, 17179869184L, var15, 0L, var19, 0L, var23, 0L);
            case 'w':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 4194304L, var23, 0L);
            case 'y':
               if ((var7 & 17179869184L) != 0L) {
                  this.jjmatchedKind = 162;
                  this.jjmatchedPos = 8;
               } else if ((var11 & 281474976710656L) != 0L) {
                  this.jjmatchedKind = 240;
                  this.jjmatchedPos = 8;
               } else if ((var15 & 16L) != 0L) {
                  this.jjmatchedKind = 260;
                  this.jjmatchedPos = 8;
               } else if ((var23 & 36028797018963968L) != 0L) {
                  this.jjmatchedKind = 439;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 33554432L, var23, 0L);
         }

         return this.jjMoveNfa_0(0, 8);
      }
   }

   private final int jjMoveStringLiteralDfa9_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15, long var17, long var19, long var21, long var23) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5) | (var11 = var11 & var9) | (var15 = var15 & var13) | (var19 = var19 & var17) | (var23 = var23 & var21)) == 0L) {
         return this.jjMoveNfa_0(0, 8);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var26) {
            return this.jjMoveNfa_0(0, 8);
         }

         switch (this.curChar) {
            case 'A':
               return this.jjMoveStringLiteralDfa10_0(var3, 35184372088832L, var7, 0L, var11, 0L, var15, 0L, var19, 554052878336L, var23, 0L);
            case 'B':
               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 65536L, var23, 0L);
            case 'C':
               return this.jjMoveStringLiteralDfa10_0(var3, 2305843009213693952L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 256L);
            case 'D':
               return this.jjMoveStringLiteralDfa10_0(var3, 1099511627776L, var7, 0L, var11, 0L, var15, 0L, var19, 8589934592L, var23, 0L);
            case 'E':
               if ((var3 & 2251799813685248L) != 0L) {
                  this.jjmatchedKind = 115;
                  this.jjmatchedPos = 9;
               } else if ((var3 & 72057594037927936L) != 0L) {
                  this.jjmatchedKind = 120;
                  this.jjmatchedPos = 9;
               } else if ((var19 & 256L) != 0L) {
                  this.jjmatchedKind = 328;
                  this.jjmatchedPos = 9;
               } else if ((var23 & 4611686018427387904L) != 0L) {
                  this.jjmatchedKind = 446;
                  this.jjmatchedPos = 9;
               }

               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 35184372088832L, var15, 0L, var19, 68757487616L, var23, 576460752303423488L);
            case 'F':
            case 'G':
            case 'J':
            case 'K':
            case 'P':
            case 'Q':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '`':
            case 'f':
            case 'g':
            case 'j':
            case 'k':
            case 'p':
            case 'q':
            default:
               break;
            case 'H':
               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 562949953421312L, var15, 0L, var19, 0L, var23, 0L);
            case 'I':
               return this.jjMoveStringLiteralDfa10_0(var3, 211106232532992L, var7, 0L, var11, 0L, var15, 0L, var19, 2305843009214218240L, var23, 0L);
            case 'L':
               if ((var23 & 562949953421312L) != 0L) {
                  this.jjmatchedKind = 433;
                  this.jjmatchedPos = 9;
               }
               break;
            case 'M':
               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 1125899906842624L, var15, 0L, var19, 0L, var23, 0L);
            case 'N':
               if ((var3 & 34359738368L) != 0L) {
                  this.jjmatchedKind = 99;
                  this.jjmatchedPos = 9;
               }

               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 549755813888L);
            case 'O':
               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 22517998136852480L, var15, 0L, var19, 8796102459392L, var23, 4194304L);
            case 'R':
               if ((var19 & 18014398509481984L) != 0L) {
                  this.jjmatchedKind = 374;
                  this.jjmatchedPos = 9;
               }

               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 131072L, var23, 0L);
            case 'S':
               if ((var11 & 262144L) != 0L) {
                  this.jjmatchedKind = 210;
                  this.jjmatchedPos = 9;
               } else if ((var11 & 8388608L) != 0L) {
                  this.jjmatchedKind = 215;
                  this.jjmatchedPos = 9;
               } else if ((var23 & 34359738368L) != 0L) {
                  this.jjmatchedKind = 419;
                  this.jjmatchedPos = 9;
               } else if ((var23 & 140737488355328L) != 0L) {
                  this.jjmatchedKind = 431;
                  this.jjmatchedPos = 9;
               }

               return this.jjMoveStringLiteralDfa10_0(var3, 281474976710656L, var7, 0L, var11, 17179869184L, var15, 4294967296L, var19, 0L, var23, 0L);
            case 'T':
               if ((var3 & 68719476736L) != 0L) {
                  this.jjmatchedKind = 100;
                  this.jjmatchedPos = 9;
               } else if ((var3 & 4611686018427387904L) != 0L) {
                  this.jjmatchedKind = 126;
                  this.jjmatchedPos = 9;
               } else if ((var23 & 512L) != 0L) {
                  this.jjmatchedKind = 393;
                  this.jjmatchedPos = 9;
               }

               return this.jjMoveStringLiteralDfa10_0(var3, 137438957568L, var7, 0L, var11, 0L, var15, 0L, var19, 70368744177664L, var23, 0L);
            case 'U':
               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 16777216L, var23, 0L);
            case 'V':
               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 137438953472L, var11, 0L, var15, 137438953472L, var19, 0L, var23, 0L);
            case '_':
               return this.jjMoveStringLiteralDfa10_0(var3, 67108864L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 0L);
            case 'a':
               return this.jjMoveStringLiteralDfa10_0(var3, 35184372088832L, var7, 0L, var11, 0L, var15, 0L, var19, 554052878336L, var23, 0L);
            case 'b':
               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 65536L, var23, 0L);
            case 'c':
               return this.jjMoveStringLiteralDfa10_0(var3, 2305843009213693952L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 256L);
            case 'd':
               return this.jjMoveStringLiteralDfa10_0(var3, 1099511627776L, var7, 0L, var11, 0L, var15, 0L, var19, 8589934592L, var23, 0L);
            case 'e':
               if ((var3 & 2251799813685248L) != 0L) {
                  this.jjmatchedKind = 115;
                  this.jjmatchedPos = 9;
               } else if ((var3 & 72057594037927936L) != 0L) {
                  this.jjmatchedKind = 120;
                  this.jjmatchedPos = 9;
               } else if ((var19 & 256L) != 0L) {
                  this.jjmatchedKind = 328;
                  this.jjmatchedPos = 9;
               } else if ((var23 & 4611686018427387904L) != 0L) {
                  this.jjmatchedKind = 446;
                  this.jjmatchedPos = 9;
               }

               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 35184372088832L, var15, 0L, var19, 68757487616L, var23, 576460752303423488L);
            case 'h':
               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 562949953421312L, var15, 0L, var19, 0L, var23, 0L);
            case 'i':
               return this.jjMoveStringLiteralDfa10_0(var3, 211106232532992L, var7, 0L, var11, 0L, var15, 0L, var19, 2305843009214218240L, var23, 0L);
            case 'l':
               if ((var23 & 562949953421312L) != 0L) {
                  this.jjmatchedKind = 433;
                  this.jjmatchedPos = 9;
               }
               break;
            case 'm':
               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 1125899906842624L, var15, 0L, var19, 0L, var23, 0L);
            case 'n':
               if ((var3 & 34359738368L) != 0L) {
                  this.jjmatchedKind = 99;
                  this.jjmatchedPos = 9;
               }

               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 549755813888L);
            case 'o':
               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 22517998136852480L, var15, 0L, var19, 8796102459392L, var23, 4194304L);
            case 'r':
               if ((var19 & 18014398509481984L) != 0L) {
                  this.jjmatchedKind = 374;
                  this.jjmatchedPos = 9;
               }

               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 131072L, var23, 0L);
            case 's':
               if ((var11 & 262144L) != 0L) {
                  this.jjmatchedKind = 210;
                  this.jjmatchedPos = 9;
               } else if ((var11 & 8388608L) != 0L) {
                  this.jjmatchedKind = 215;
                  this.jjmatchedPos = 9;
               } else if ((var23 & 34359738368L) != 0L) {
                  this.jjmatchedKind = 419;
                  this.jjmatchedPos = 9;
               } else if ((var23 & 140737488355328L) != 0L) {
                  this.jjmatchedKind = 431;
                  this.jjmatchedPos = 9;
               }

               return this.jjMoveStringLiteralDfa10_0(var3, 281474976710656L, var7, 0L, var11, 17179869184L, var15, 4294967296L, var19, 0L, var23, 0L);
            case 't':
               if ((var3 & 68719476736L) != 0L) {
                  this.jjmatchedKind = 100;
                  this.jjmatchedPos = 9;
               } else if ((var3 & 4611686018427387904L) != 0L) {
                  this.jjmatchedKind = 126;
                  this.jjmatchedPos = 9;
               } else if ((var23 & 512L) != 0L) {
                  this.jjmatchedKind = 393;
                  this.jjmatchedPos = 9;
               }

               return this.jjMoveStringLiteralDfa10_0(var3, 137438957568L, var7, 0L, var11, 0L, var15, 0L, var19, 70368744177664L, var23, 0L);
            case 'u':
               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 16777216L, var23, 0L);
            case 'v':
               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 137438953472L, var11, 0L, var15, 137438953472L, var19, 0L, var23, 0L);
         }

         return this.jjMoveNfa_0(0, 9);
      }
   }

   private final int jjMoveStringLiteralDfa10_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15, long var17, long var19, long var21, long var23) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5) | (var11 = var11 & var9) | (var15 = var15 & var13) | (var19 = var19 & var17) | (var23 = var23 & var21)) == 0L) {
         return this.jjMoveNfa_0(0, 9);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var26) {
            return this.jjMoveNfa_0(0, 9);
         }

         switch (this.curChar) {
            case 'A':
               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 0L, var11, 0L, var15, 137438953472L, var19, 50462720L, var23, 0L);
            case 'B':
            case 'F':
            case 'H':
            case 'J':
            case 'K':
            case 'P':
            case 'Q':
            case 'V':
            case 'W':
            case 'X':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '`':
            case 'b':
            case 'f':
            case 'h':
            case 'j':
            case 'k':
            case 'p':
            case 'q':
            case 'v':
            case 'w':
            case 'x':
            default:
               break;
            case 'C':
               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 70368744439808L, var23, 4194304L);
            case 'D':
               if ((var19 & 68719476736L) != 0L) {
                  this.jjmatchedKind = 356;
                  this.jjmatchedPos = 10;
               }

               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 4294967296L, var23, 0L);
            case 'E':
               if ((var7 & 137438953472L) != 0L) {
                  this.jjmatchedKind = 165;
                  this.jjmatchedPos = 10;
               }

               return this.jjMoveStringLiteralDfa11_0(var3, 281474976710656L, var7, 0L, var11, 17179869184L, var15, 0L, var19, 4194304L, var23, 0L);
            case 'G':
               if ((var23 & 549755813888L) != 0L) {
                  this.jjmatchedKind = 423;
                  this.jjmatchedPos = 10;
               }
               break;
            case 'I':
               return this.jjMoveStringLiteralDfa11_0(var3, 1099511631872L, var7, 0L, var11, 1125899906842624L, var15, 0L, var19, 8589934592L, var23, 0L);
            case 'L':
               return this.jjMoveStringLiteralDfa11_0(var3, 67108864L, var7, 0L, var11, 0L, var15, 0L, var19, 8796093087744L, var23, 0L);
            case 'M':
               return this.jjMoveStringLiteralDfa11_0(var3, 211106232532992L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 0L);
            case 'N':
               if ((var11 & 4503599627370496L) != 0L) {
                  this.jjmatchedKind = 244;
                  this.jjmatchedPos = 10;
               } else if ((var11 & 18014398509481984L) != 0L) {
                  this.jjmatchedKind = 246;
                  this.jjmatchedPos = 10;
               } else if ((var19 & 549755813888L) != 0L) {
                  this.jjmatchedKind = 359;
                  this.jjmatchedPos = 10;
               }

               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 8912896L, var23, 576460752303423488L);
            case 'O':
               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 0L, var11, 562949953421312L, var15, 0L, var19, 0L, var23, 0L);
            case 'R':
               if ((var11 & 35184372088832L) != 0L) {
                  this.jjmatchedKind = 237;
                  this.jjmatchedPos = 10;
               }
               break;
            case 'S':
               if ((var3 & 137438953472L) != 0L) {
                  this.jjmatchedKind = 101;
                  this.jjmatchedPos = 10;
               } else if ((var3 & 2305843009213693952L) != 0L) {
                  this.jjmatchedKind = 125;
                  this.jjmatchedPos = 10;
               }
               break;
            case 'T':
               return this.jjMoveStringLiteralDfa11_0(var3, 35184372088832L, var7, 0L, var11, 0L, var15, 4294967296L, var19, 0L, var23, 0L);
            case 'U':
               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 1048576L, var23, 0L);
            case 'Y':
               if ((var19 & 2097152L) != 0L) {
                  this.jjmatchedKind = 341;
                  this.jjmatchedPos = 10;
               }
               break;
            case 'Z':
               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 2305843009213693952L, var23, 0L);
            case '_':
               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 256L);
            case 'a':
               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 0L, var11, 0L, var15, 137438953472L, var19, 50462720L, var23, 0L);
            case 'c':
               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 70368744439808L, var23, 4194304L);
            case 'd':
               if ((var19 & 68719476736L) != 0L) {
                  this.jjmatchedKind = 356;
                  this.jjmatchedPos = 10;
               }

               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 4294967296L, var23, 0L);
            case 'e':
               if ((var7 & 137438953472L) != 0L) {
                  this.jjmatchedKind = 165;
                  this.jjmatchedPos = 10;
               }

               return this.jjMoveStringLiteralDfa11_0(var3, 281474976710656L, var7, 0L, var11, 17179869184L, var15, 0L, var19, 4194304L, var23, 0L);
            case 'g':
               if ((var23 & 549755813888L) != 0L) {
                  this.jjmatchedKind = 423;
                  this.jjmatchedPos = 10;
               }
               break;
            case 'i':
               return this.jjMoveStringLiteralDfa11_0(var3, 1099511631872L, var7, 0L, var11, 1125899906842624L, var15, 0L, var19, 8589934592L, var23, 0L);
            case 'l':
               return this.jjMoveStringLiteralDfa11_0(var3, 67108864L, var7, 0L, var11, 0L, var15, 0L, var19, 8796093087744L, var23, 0L);
            case 'm':
               return this.jjMoveStringLiteralDfa11_0(var3, 211106232532992L, var7, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 0L);
            case 'n':
               if ((var11 & 4503599627370496L) != 0L) {
                  this.jjmatchedKind = 244;
                  this.jjmatchedPos = 10;
               } else if ((var11 & 18014398509481984L) != 0L) {
                  this.jjmatchedKind = 246;
                  this.jjmatchedPos = 10;
               } else if ((var19 & 549755813888L) != 0L) {
                  this.jjmatchedKind = 359;
                  this.jjmatchedPos = 10;
               }

               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 8912896L, var23, 576460752303423488L);
            case 'o':
               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 0L, var11, 562949953421312L, var15, 0L, var19, 0L, var23, 0L);
            case 'r':
               if ((var11 & 35184372088832L) != 0L) {
                  this.jjmatchedKind = 237;
                  this.jjmatchedPos = 10;
               }
               break;
            case 's':
               if ((var3 & 137438953472L) != 0L) {
                  this.jjmatchedKind = 101;
                  this.jjmatchedPos = 10;
               } else if ((var3 & 2305843009213693952L) != 0L) {
                  this.jjmatchedKind = 125;
                  this.jjmatchedPos = 10;
               }
               break;
            case 't':
               return this.jjMoveStringLiteralDfa11_0(var3, 35184372088832L, var7, 0L, var11, 0L, var15, 4294967296L, var19, 0L, var23, 0L);
            case 'u':
               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 1048576L, var23, 0L);
            case 'y':
               if ((var19 & 2097152L) != 0L) {
                  this.jjmatchedKind = 341;
                  this.jjmatchedPos = 10;
               }
               break;
            case 'z':
               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 2305843009213693952L, var23, 0L);
         }

         return this.jjMoveNfa_0(0, 10);
      }
   }

   private final int jjMoveStringLiteralDfa11_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15, long var17, long var19, long var21, long var23) {
      if (((var3 = var3 & var1) | var7 & var5 | (var11 = var11 & var9) | (var15 = var15 & var13) | (var19 = var19 & var17) | (var23 = var23 & var21)) == 0L) {
         return this.jjMoveNfa_0(0, 10);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var26) {
            return this.jjMoveNfa_0(0, 10);
         }

         switch (this.curChar) {
            case 'A':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 4194304L);
            case 'B':
            case 'G':
            case 'H':
            case 'J':
            case 'M':
            case 'P':
            case 'Q':
            case 'S':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'b':
            case 'g':
            case 'h':
            case 'j':
            case 'm':
            case 'p':
            case 'q':
            case 's':
            default:
               break;
            case 'C':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 0L, var15, 0L, var19, 131072L, var23, 0L);
            case 'D':
               if ((var19 & 4294967296L) != 0L) {
                  this.jjmatchedKind = 352;
                  this.jjmatchedPos = 11;
               } else if ((var23 & 576460752303423488L) != 0L) {
                  this.jjmatchedKind = 443;
                  this.jjmatchedPos = 11;
               }
               break;
            case 'E':
               if ((var3 & 35184372088832L) != 0L) {
                  this.jjmatchedKind = 109;
                  this.jjmatchedPos = 11;
               } else if ((var3 & 70368744177664L) != 0L) {
                  this.jjmatchedKind = 110;
                  this.jjmatchedPos = 11;
               } else if ((var19 & 65536L) != 0L) {
                  this.jjmatchedKind = 336;
                  this.jjmatchedPos = 11;
               } else if ((var19 & 8796093022208L) != 0L) {
                  this.jjmatchedKind = 363;
                  this.jjmatchedPos = 11;
               } else if ((var19 & 2305843009213693952L) != 0L) {
                  this.jjmatchedKind = 381;
                  this.jjmatchedPos = 11;
               }

               return this.jjMoveStringLiteralDfa12_0(var3, 140737555464192L, var11, 0L, var15, 0L, var19, 0L, var23, 0L);
            case 'F':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 0L, var15, 0L, var19, 8589934592L, var23, 0L);
            case 'I':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 0L, var15, 4294967296L, var19, 0L, var23, 0L);
            case 'K':
               if ((var19 & 4194304L) != 0L) {
                  this.jjmatchedKind = 342;
                  this.jjmatchedPos = 11;
               }
               break;
            case 'L':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 0L, var15, 137438953472L, var19, 0L, var23, 0L);
            case 'N':
               return this.jjMoveStringLiteralDfa12_0(var3, 1099511627776L, var11, 1125899906842624L, var15, 0L, var19, 0L, var23, 0L);
            case 'O':
               return this.jjMoveStringLiteralDfa12_0(var3, 4096L, var11, 0L, var15, 0L, var19, 70368744439808L, var23, 0L);
            case 'R':
               if ((var3 & 281474976710656L) != 0L) {
                  this.jjmatchedKind = 112;
                  this.jjmatchedPos = 11;
               } else if ((var11 & 17179869184L) != 0L) {
                  this.jjmatchedKind = 226;
                  this.jjmatchedPos = 11;
               } else if ((var19 & 1048576L) != 0L) {
                  this.jjmatchedKind = 340;
                  this.jjmatchedPos = 11;
               } else if ((var19 & 33554432L) != 0L) {
                  this.jjmatchedKind = 345;
                  this.jjmatchedPos = 11;
               }

               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 0L, var15, 0L, var19, 16777216L, var23, 256L);
            case 'T':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 0L, var15, 0L, var19, 8388608L, var23, 0L);
            case 'U':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 562949953421312L, var15, 0L, var19, 524288L, var23, 0L);
            case 'a':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 0L, var15, 0L, var19, 0L, var23, 4194304L);
            case 'c':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 0L, var15, 0L, var19, 131072L, var23, 0L);
            case 'd':
               if ((var19 & 4294967296L) != 0L) {
                  this.jjmatchedKind = 352;
                  this.jjmatchedPos = 11;
               } else if ((var23 & 576460752303423488L) != 0L) {
                  this.jjmatchedKind = 443;
                  this.jjmatchedPos = 11;
               }
               break;
            case 'e':
               if ((var3 & 35184372088832L) != 0L) {
                  this.jjmatchedKind = 109;
                  this.jjmatchedPos = 11;
               } else if ((var3 & 70368744177664L) != 0L) {
                  this.jjmatchedKind = 110;
                  this.jjmatchedPos = 11;
               } else if ((var19 & 65536L) != 0L) {
                  this.jjmatchedKind = 336;
                  this.jjmatchedPos = 11;
               } else if ((var19 & 8796093022208L) != 0L) {
                  this.jjmatchedKind = 363;
                  this.jjmatchedPos = 11;
               } else if ((var19 & 2305843009213693952L) != 0L) {
                  this.jjmatchedKind = 381;
                  this.jjmatchedPos = 11;
               }

               return this.jjMoveStringLiteralDfa12_0(var3, 140737555464192L, var11, 0L, var15, 0L, var19, 0L, var23, 0L);
            case 'f':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 0L, var15, 0L, var19, 8589934592L, var23, 0L);
            case 'i':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 0L, var15, 4294967296L, var19, 0L, var23, 0L);
            case 'k':
               if ((var19 & 4194304L) != 0L) {
                  this.jjmatchedKind = 342;
                  this.jjmatchedPos = 11;
               }
               break;
            case 'l':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 0L, var15, 137438953472L, var19, 0L, var23, 0L);
            case 'n':
               return this.jjMoveStringLiteralDfa12_0(var3, 1099511627776L, var11, 1125899906842624L, var15, 0L, var19, 0L, var23, 0L);
            case 'o':
               return this.jjMoveStringLiteralDfa12_0(var3, 4096L, var11, 0L, var15, 0L, var19, 70368744439808L, var23, 0L);
            case 'r':
               if ((var3 & 281474976710656L) != 0L) {
                  this.jjmatchedKind = 112;
                  this.jjmatchedPos = 11;
               } else if ((var11 & 17179869184L) != 0L) {
                  this.jjmatchedKind = 226;
                  this.jjmatchedPos = 11;
               } else if ((var19 & 1048576L) != 0L) {
                  this.jjmatchedKind = 340;
                  this.jjmatchedPos = 11;
               } else if ((var19 & 33554432L) != 0L) {
                  this.jjmatchedKind = 345;
                  this.jjmatchedPos = 11;
               }

               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 0L, var15, 0L, var19, 16777216L, var23, 256L);
            case 't':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 0L, var15, 0L, var19, 8388608L, var23, 0L);
            case 'u':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var11, 562949953421312L, var15, 0L, var19, 524288L, var23, 0L);
         }

         return this.jjMoveNfa_0(0, 11);
      }
   }

   private final int jjMoveStringLiteralDfa12_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15, long var17, long var19) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5) | (var11 = var11 & var9) | (var15 = var15 & var13) | (var19 = var19 & var17)) == 0L) {
         return this.jjMoveNfa_0(0, 11);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var22) {
            return this.jjMoveNfa_0(0, 11);
         }

         switch (this.curChar) {
            case 'C':
               if ((var11 & 4294967296L) != 0L) {
                  this.jjmatchedKind = 288;
                  this.jjmatchedPos = 12;
               }
            case 'D':
            case 'I':
            case 'J':
            case 'K':
            case 'M':
            case 'O':
            case 'P':
            case 'Q':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '`':
            case 'a':
            case 'b':
            case 'd':
            case 'i':
            case 'j':
            case 'k':
            case 'm':
            case 'o':
            case 'p':
            case 'q':
            default:
               break;
            case 'E':
               return this.jjMoveStringLiteralDfa13_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 256L);
            case 'F':
               if ((var15 & 8589934592L) != 0L) {
                  this.jjmatchedKind = 353;
                  this.jjmatchedPos = 12;
               }
               break;
            case 'G':
               if ((var3 & 1099511627776L) != 0L) {
                  this.jjmatchedKind = 104;
                  this.jjmatchedPos = 12;
               }
               break;
            case 'H':
               if ((var15 & 8388608L) != 0L) {
                  this.jjmatchedKind = 343;
                  this.jjmatchedPos = 12;
               }
               break;
            case 'L':
               return this.jjMoveStringLiteralDfa13_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 4194304L);
            case 'N':
               if ((var3 & 4096L) != 0L) {
                  this.jjmatchedKind = 76;
                  this.jjmatchedPos = 12;
               }

               return this.jjMoveStringLiteralDfa13_0(var3, 67108864L, var7, 0L, var11, 0L, var15, 70368744439808L, var19, 0L);
            case 'R':
               if ((var7 & 562949953421312L) != 0L) {
                  this.jjmatchedKind = 241;
                  this.jjmatchedPos = 12;
               }
               break;
            case 'S':
               return this.jjMoveStringLiteralDfa13_0(var3, 140737488355328L, var7, 0L, var11, 0L, var15, 0L, var19, 0L);
            case 'T':
               return this.jjMoveStringLiteralDfa13_0(var3, 0L, var7, 0L, var11, 0L, var15, 17301504L, var19, 0L);
            case 'U':
               return this.jjMoveStringLiteralDfa13_0(var3, 0L, var7, 1125899906842624L, var11, 0L, var15, 0L, var19, 0L);
            case '_':
               return this.jjMoveStringLiteralDfa13_0(var3, 0L, var7, 0L, var11, 137438953472L, var15, 131072L, var19, 0L);
            case 'c':
               if ((var11 & 4294967296L) != 0L) {
                  this.jjmatchedKind = 288;
                  this.jjmatchedPos = 12;
               }
               break;
            case 'e':
               return this.jjMoveStringLiteralDfa13_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 256L);
            case 'f':
               if ((var15 & 8589934592L) != 0L) {
                  this.jjmatchedKind = 353;
                  this.jjmatchedPos = 12;
               }
               break;
            case 'g':
               if ((var3 & 1099511627776L) != 0L) {
                  this.jjmatchedKind = 104;
                  this.jjmatchedPos = 12;
               }
               break;
            case 'h':
               if ((var15 & 8388608L) != 0L) {
                  this.jjmatchedKind = 343;
                  this.jjmatchedPos = 12;
               }
               break;
            case 'l':
               return this.jjMoveStringLiteralDfa13_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 4194304L);
            case 'n':
               if ((var3 & 4096L) != 0L) {
                  this.jjmatchedKind = 76;
                  this.jjmatchedPos = 12;
               }

               return this.jjMoveStringLiteralDfa13_0(var3, 67108864L, var7, 0L, var11, 0L, var15, 70368744439808L, var19, 0L);
            case 'r':
               if ((var7 & 562949953421312L) != 0L) {
                  this.jjmatchedKind = 241;
                  this.jjmatchedPos = 12;
               }
               break;
            case 's':
               return this.jjMoveStringLiteralDfa13_0(var3, 140737488355328L, var7, 0L, var11, 0L, var15, 0L, var19, 0L);
            case 't':
               return this.jjMoveStringLiteralDfa13_0(var3, 0L, var7, 0L, var11, 0L, var15, 17301504L, var19, 0L);
            case 'u':
               return this.jjMoveStringLiteralDfa13_0(var3, 0L, var7, 1125899906842624L, var11, 0L, var15, 0L, var19, 0L);
         }

         return this.jjMoveNfa_0(0, 12);
      }
   }

   private final int jjMoveStringLiteralDfa13_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15, long var17, long var19) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5) | (var11 = var11 & var9) | (var15 = var15 & var13) | (var19 = var19 & var17)) == 0L) {
         return this.jjMoveNfa_0(0, 12);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var22) {
            return this.jjMoveNfa_0(0, 12);
         }

         switch (this.curChar) {
            case 'D':
               if ((var15 & 262144L) != 0L) {
                  this.jjmatchedKind = 338;
                  this.jjmatchedPos = 13;
               }
               break;
            case 'E':
               if ((var15 & 524288L) != 0L) {
                  this.jjmatchedKind = 339;
                  this.jjmatchedPos = 13;
               } else if ((var19 & 4194304L) != 0L) {
                  this.jjmatchedKind = 406;
                  this.jjmatchedPos = 13;
               }

               return this.jjMoveStringLiteralDfa14_0(var3, 0L, var7, 0L, var11, 0L, var15, 16777216L, var19, 0L);
            case 'F':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'M':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'a':
            case 'b':
            case 'c':
            case 'f':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'm':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            default:
               break;
            case 'G':
               return this.jjMoveStringLiteralDfa14_0(var3, 67108864L, var7, 0L, var11, 0L, var15, 0L, var19, 0L);
            case 'L':
               return this.jjMoveStringLiteralDfa14_0(var3, 0L, var7, 0L, var11, 137438953472L, var15, 0L, var19, 0L);
            case 'N':
               return this.jjMoveStringLiteralDfa14_0(var3, 0L, var7, 0L, var11, 0L, var15, 70368744177664L, var19, 0L);
            case 'S':
               return this.jjMoveStringLiteralDfa14_0(var3, 0L, var7, 0L, var11, 0L, var15, 131072L, var19, 256L);
            case 'T':
               return this.jjMoveStringLiteralDfa14_0(var3, 140737488355328L, var7, 1125899906842624L, var11, 0L, var15, 0L, var19, 0L);
            case 'd':
               if ((var15 & 262144L) != 0L) {
                  this.jjmatchedKind = 338;
                  this.jjmatchedPos = 13;
               }
               break;
            case 'e':
               if ((var15 & 524288L) != 0L) {
                  this.jjmatchedKind = 339;
                  this.jjmatchedPos = 13;
               } else if ((var19 & 4194304L) != 0L) {
                  this.jjmatchedKind = 406;
                  this.jjmatchedPos = 13;
               }

               return this.jjMoveStringLiteralDfa14_0(var3, 0L, var7, 0L, var11, 0L, var15, 16777216L, var19, 0L);
            case 'g':
               return this.jjMoveStringLiteralDfa14_0(var3, 67108864L, var7, 0L, var11, 0L, var15, 0L, var19, 0L);
            case 'l':
               return this.jjMoveStringLiteralDfa14_0(var3, 0L, var7, 0L, var11, 137438953472L, var15, 0L, var19, 0L);
            case 'n':
               return this.jjMoveStringLiteralDfa14_0(var3, 0L, var7, 0L, var11, 0L, var15, 70368744177664L, var19, 0L);
            case 's':
               return this.jjMoveStringLiteralDfa14_0(var3, 0L, var7, 0L, var11, 0L, var15, 131072L, var19, 256L);
            case 't':
               return this.jjMoveStringLiteralDfa14_0(var3, 140737488355328L, var7, 1125899906842624L, var11, 0L, var15, 0L, var19, 0L);
         }

         return this.jjMoveNfa_0(0, 13);
      }
   }

   private final int jjMoveStringLiteralDfa14_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15, long var17, long var19) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5) | (var11 = var11 & var9) | (var15 = var15 & var13) | (var19 = var19 & var17)) == 0L) {
         return this.jjMoveNfa_0(0, 13);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var22) {
            return this.jjMoveNfa_0(0, 13);
         }

         switch (this.curChar) {
            case 'A':
               return this.jjMoveStringLiteralDfa15_0(var3, 140737488355328L, var7, 0L, var11, 0L, var15, 0L, var19, 0L);
            case 'E':
               if ((var7 & 1125899906842624L) != 0L) {
                  this.jjmatchedKind = 242;
                  this.jjmatchedPos = 14;
               }

               return this.jjMoveStringLiteralDfa15_0(var3, 0L, var7, 0L, var11, 0L, var15, 70368744308736L, var19, 0L);
            case 'O':
               return this.jjMoveStringLiteralDfa15_0(var3, 0L, var7, 0L, var11, 137438953472L, var15, 0L, var19, 0L);
            case 'R':
               if ((var15 & 16777216L) != 0L) {
                  this.jjmatchedKind = 344;
                  this.jjmatchedPos = 14;
               }
               break;
            case 'T':
               return this.jjMoveStringLiteralDfa15_0(var3, 67108864L, var7, 0L, var11, 0L, var15, 0L, var19, 0L);
            case 'U':
               return this.jjMoveStringLiteralDfa15_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 256L);
            case 'a':
               return this.jjMoveStringLiteralDfa15_0(var3, 140737488355328L, var7, 0L, var11, 0L, var15, 0L, var19, 0L);
            case 'e':
               if ((var7 & 1125899906842624L) != 0L) {
                  this.jjmatchedKind = 242;
                  this.jjmatchedPos = 14;
               }

               return this.jjMoveStringLiteralDfa15_0(var3, 0L, var7, 0L, var11, 0L, var15, 70368744308736L, var19, 0L);
            case 'o':
               return this.jjMoveStringLiteralDfa15_0(var3, 0L, var7, 0L, var11, 137438953472L, var15, 0L, var19, 0L);
            case 'r':
               if ((var15 & 16777216L) != 0L) {
                  this.jjmatchedKind = 344;
                  this.jjmatchedPos = 14;
               }
               break;
            case 't':
               return this.jjMoveStringLiteralDfa15_0(var3, 67108864L, var7, 0L, var11, 0L, var15, 0L, var19, 0L);
            case 'u':
               return this.jjMoveStringLiteralDfa15_0(var3, 0L, var7, 0L, var11, 0L, var15, 0L, var19, 256L);
         }

         return this.jjMoveNfa_0(0, 14);
      }
   }

   private final int jjMoveStringLiteralDfa15_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15, long var17, long var19) {
      if (((var3 = var3 & var1) | var7 & var5 | (var11 = var11 & var9) | (var15 = var15 & var13) | (var19 = var19 & var17)) == 0L) {
         return this.jjMoveNfa_0(0, 14);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var22) {
            return this.jjMoveNfa_0(0, 14);
         }

         switch (this.curChar) {
            case 'C':
               return this.jjMoveStringLiteralDfa16_0(var3, 0L, var11, 137438953472L, var15, 70368744308736L, var19, 0L);
            case 'H':
               if ((var3 & 67108864L) != 0L) {
                  this.jjmatchedKind = 90;
                  this.jjmatchedPos = 15;
               }
               break;
            case 'L':
               return this.jjMoveStringLiteralDfa16_0(var3, 0L, var11, 0L, var15, 0L, var19, 256L);
            case 'M':
               return this.jjMoveStringLiteralDfa16_0(var3, 140737488355328L, var11, 0L, var15, 0L, var19, 0L);
            case 'c':
               return this.jjMoveStringLiteralDfa16_0(var3, 0L, var11, 137438953472L, var15, 70368744308736L, var19, 0L);
            case 'h':
               if ((var3 & 67108864L) != 0L) {
                  this.jjmatchedKind = 90;
                  this.jjmatchedPos = 15;
               }
               break;
            case 'l':
               return this.jjMoveStringLiteralDfa16_0(var3, 0L, var11, 0L, var15, 0L, var19, 256L);
            case 'm':
               return this.jjMoveStringLiteralDfa16_0(var3, 140737488355328L, var11, 0L, var15, 0L, var19, 0L);
         }

         return this.jjMoveNfa_0(0, 15);
      }
   }

   private final int jjMoveStringLiteralDfa16_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5) | (var11 = var11 & var9) | (var15 = var15 & var13)) == 0L) {
         return this.jjMoveNfa_0(0, 15);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var18) {
            return this.jjMoveNfa_0(0, 15);
         }

         switch (this.curChar) {
            case 'A':
               return this.jjMoveStringLiteralDfa17_0(var3, 0L, var7, 137438953472L, var11, 0L, var15, 0L);
            case 'O':
               return this.jjMoveStringLiteralDfa17_0(var3, 0L, var7, 0L, var11, 131072L, var15, 0L);
            case 'P':
               if ((var3 & 140737488355328L) != 0L) {
                  this.jjmatchedKind = 111;
                  this.jjmatchedPos = 16;
               }
               break;
            case 'T':
               return this.jjMoveStringLiteralDfa17_0(var3, 0L, var7, 0L, var11, 70368744177664L, var15, 256L);
            case 'a':
               return this.jjMoveStringLiteralDfa17_0(var3, 0L, var7, 137438953472L, var11, 0L, var15, 0L);
            case 'o':
               return this.jjMoveStringLiteralDfa17_0(var3, 0L, var7, 0L, var11, 131072L, var15, 0L);
            case 'p':
               if ((var3 & 140737488355328L) != 0L) {
                  this.jjmatchedKind = 111;
                  this.jjmatchedPos = 16;
               }
               break;
            case 't':
               return this.jjMoveStringLiteralDfa17_0(var3, 0L, var7, 0L, var11, 70368744177664L, var15, 256L);
         }

         return this.jjMoveNfa_0(0, 16);
      }
   }

   private final int jjMoveStringLiteralDfa17_0(long var1, long var3, long var5, long var7, long var9, long var11, long var13, long var15) {
      if ((var3 & var1 | (var7 = var7 & var5) | (var11 = var11 & var9) | (var15 = var15 & var13)) == 0L) {
         return this.jjMoveNfa_0(0, 16);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var18) {
            return this.jjMoveNfa_0(0, 16);
         }

         switch (this.curChar) {
            case 'I':
               return this.jjMoveStringLiteralDfa18_0(var7, 0L, var11, 70368744177664L, var15, 0L);
            case 'L':
               if ((var7 & 137438953472L) != 0L) {
                  this.jjmatchedKind = 293;
                  this.jjmatchedPos = 17;
               }
               break;
            case 'N':
               return this.jjMoveStringLiteralDfa18_0(var7, 0L, var11, 131072L, var15, 0L);
            case '_':
               return this.jjMoveStringLiteralDfa18_0(var7, 0L, var11, 0L, var15, 256L);
            case 'i':
               return this.jjMoveStringLiteralDfa18_0(var7, 0L, var11, 70368744177664L, var15, 0L);
            case 'l':
               if ((var7 & 137438953472L) != 0L) {
                  this.jjmatchedKind = 293;
                  this.jjmatchedPos = 17;
               }
               break;
            case 'n':
               return this.jjMoveStringLiteralDfa18_0(var7, 0L, var11, 131072L, var15, 0L);
         }

         return this.jjMoveNfa_0(0, 17);
      }
   }

   private final int jjMoveStringLiteralDfa18_0(long var1, long var3, long var5, long var7, long var9, long var11) {
      if ((var3 & var1 | (var7 = var7 & var5) | (var11 = var11 & var9)) == 0L) {
         return this.jjMoveNfa_0(0, 17);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var14) {
            return this.jjMoveNfa_0(0, 17);
         }

         switch (this.curChar) {
            case 'D':
               if ((var7 & 131072L) != 0L) {
                  this.jjmatchedKind = 337;
                  this.jjmatchedPos = 18;
               }
               break;
            case 'O':
               return this.jjMoveStringLiteralDfa19_0(var7, 70368744177664L, var11, 0L);
            case 'S':
               return this.jjMoveStringLiteralDfa19_0(var7, 0L, var11, 256L);
            case 'd':
               if ((var7 & 131072L) != 0L) {
                  this.jjmatchedKind = 337;
                  this.jjmatchedPos = 18;
               }
               break;
            case 'o':
               return this.jjMoveStringLiteralDfa19_0(var7, 70368744177664L, var11, 0L);
            case 's':
               return this.jjMoveStringLiteralDfa19_0(var7, 0L, var11, 256L);
         }

         return this.jjMoveNfa_0(0, 18);
      }
   }

   private final int jjMoveStringLiteralDfa19_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(0, 18);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(0, 18);
         }

         switch (this.curChar) {
            case 'E':
               return this.jjMoveStringLiteralDfa20_0(var3, 0L, var7, 256L);
            case 'N':
               if ((var3 & 70368744177664L) != 0L) {
                  this.jjmatchedKind = 366;
                  this.jjmatchedPos = 19;
               }
               break;
            case 'e':
               return this.jjMoveStringLiteralDfa20_0(var3, 0L, var7, 256L);
            case 'n':
               if ((var3 & 70368744177664L) != 0L) {
                  this.jjmatchedKind = 366;
                  this.jjmatchedPos = 19;
               }
         }

         return this.jjMoveNfa_0(0, 19);
      }
   }

   private final int jjMoveStringLiteralDfa20_0(long var1, long var3, long var5, long var7) {
      if ((var3 & var1 | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(0, 19);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(0, 19);
         }

         switch (this.curChar) {
            case 'T':
               if ((var7 & 256L) != 0L) {
                  this.jjmatchedKind = 392;
                  this.jjmatchedPos = 20;
               }
               break;
            case 't':
               if ((var7 & 256L) != 0L) {
                  this.jjmatchedKind = 392;
                  this.jjmatchedPos = 20;
               }
         }

         return this.jjMoveNfa_0(0, 20);
      }
   }

   private final int jjMoveNfa_0(int var1, int var2) {
      int var3 = this.jjmatchedKind;
      int var4 = this.jjmatchedPos;
      int var5;
      this.input_stream.backup(var5 = var2 + 1);

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var19) {
         throw new Error("Internal Error");
      }

      var2 = 0;
      int var7 = 0;
      this.jjnewStateCnt = 137;
      int var8 = 1;
      this.jjstateSet[0] = var1;
      int var10 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var24 = 1L << this.curChar;

            do {
               --var8;
               switch (this.jjstateSet[var8]) {
                  case 0:
                     if ((287948901175001088L & var24) != 0L) {
                        if (var10 > 487) {
                           var10 = 487;
                        }

                        this.jjCheckNAddStates(3, 10);
                     } else if (this.curChar == '.') {
                        this.jjCheckNAddTwoStates(135, 136);
                     } else if (this.curChar == '\'') {
                        this.jjCheckNAddStates(11, 13);
                     } else if (this.curChar == '"') {
                        this.jjCheckNAddTwoStates(4, 5);
                     }
                     break;
                  case 1:
                     if ((287948901175001088L & var24) != 0L) {
                        if (var10 > 480) {
                           var10 = 480;
                        }

                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 2:
                     if (this.curChar == '"') {
                        this.jjCheckNAddTwoStates(4, 5);
                     }
                     break;
                  case 3:
                     if (this.curChar == '"') {
                        this.jjCheckNAddStates(14, 16);
                     }
                     break;
                  case 4:
                     if (this.curChar == '"') {
                        this.jjstateSet[this.jjnewStateCnt++] = 3;
                     }
                     break;
                  case 5:
                     if ((-17179869185L & var24) != 0L) {
                        this.jjCheckNAddStates(14, 16);
                     }
                     break;
                  case 6:
                     if (this.curChar == '"' && var10 > 486) {
                        var10 = 486;
                     }
                     break;
                  case 7:
                  case 8:
                     if (this.curChar == '\'') {
                        this.jjCheckNAddStates(11, 13);
                     }
                     break;
                  case 9:
                     if (this.curChar == '\'') {
                        this.jjstateSet[this.jjnewStateCnt++] = 8;
                     }
                     break;
                  case 10:
                     if ((-549755813889L & var24) != 0L) {
                        this.jjCheckNAddStates(11, 13);
                     }
                     break;
                  case 11:
                     if (this.curChar == '\'' && var10 > 490) {
                        var10 = 490;
                     }
                  case 12:
                  case 16:
                  case 25:
                  case 26:
                  case 30:
                  case 31:
                  case 32:
                  case 36:
                  case 37:
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
                  case 53:
                  case 54:
                  case 55:
                  case 56:
                  case 57:
                  case 58:
                  case 59:
                  case 60:
                  case 61:
                  case 62:
                  case 63:
                  case 64:
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
                  case 114:
                  case 115:
                  case 116:
                  case 117:
                  case 118:
                  case 119:
                  case 120:
                  case 127:
                  case 131:
                  default:
                     break;
                  case 13:
                     if (this.curChar == '\'') {
                        this.jjCheckNAddTwoStates(14, 15);
                     }
                     break;
                  case 14:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddTwoStates(14, 15);
                     }
                     break;
                  case 15:
                     if (this.curChar == '\'' && var10 > 491) {
                        var10 = 491;
                     }
                     break;
                  case 17:
                     if (this.curChar == '\'') {
                        this.jjCheckNAddTwoStates(18, 19);
                     }
                     break;
                  case 18:
                     if ((43980465111040L & var24) != 0L) {
                        this.jjCheckNAdd(19);
                     }
                     break;
                  case 19:
                     if (this.curChar == '\'') {
                        this.jjstateSet[this.jjnewStateCnt++] = 20;
                     }
                     break;
                  case 20:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddStates(17, 29);
                     }
                     break;
                  case 21:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddTwoStates(21, 22);
                     }
                     break;
                  case 22:
                     if (this.curChar == '-') {
                        this.jjCheckNAdd(23);
                     }
                     break;
                  case 23:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddTwoStates(23, 24);
                     }
                     break;
                  case 24:
                     if (this.curChar == '\'') {
                        this.jjAddStates(30, 33);
                     }
                     break;
                  case 27:
                     if (this.curChar == '(') {
                        this.jjCheckNAdd(28);
                     }
                     break;
                  case 28:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddTwoStates(28, 29);
                     }
                     break;
                  case 29:
                     if (this.curChar == ')' && var10 > 493) {
                        var10 = 493;
                     }
                     break;
                  case 33:
                     if (this.curChar == '(') {
                        this.jjCheckNAdd(34);
                     }
                     break;
                  case 34:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddTwoStates(34, 35);
                     }
                     break;
                  case 35:
                     if (this.curChar == ')') {
                        this.jjstateSet[this.jjnewStateCnt++] = 57;
                     }
                     break;
                  case 89:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddStates(34, 36);
                     }
                     break;
                  case 90:
                     if (this.curChar == ' ') {
                        this.jjCheckNAdd(91);
                     }
                     break;
                  case 91:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddStates(37, 39);
                     }
                     break;
                  case 92:
                     if (this.curChar == ':') {
                        this.jjCheckNAdd(93);
                     }
                     break;
                  case 93:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddStates(40, 42);
                     }
                     break;
                  case 94:
                     if (this.curChar == ':') {
                        this.jjCheckNAdd(95);
                     }
                     break;
                  case 95:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddStates(43, 45);
                     }
                     break;
                  case 96:
                     if (this.curChar == '.') {
                        this.jjCheckNAddTwoStates(97, 24);
                     }
                     break;
                  case 97:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddTwoStates(97, 24);
                     }
                     break;
                  case 98:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddStates(46, 48);
                     }
                     break;
                  case 99:
                     if (this.curChar == '.') {
                        this.jjCheckNAddTwoStates(100, 24);
                     }
                     break;
                  case 100:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddTwoStates(100, 24);
                     }
                     break;
                  case 101:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddStates(49, 51);
                     }
                     break;
                  case 102:
                     if (this.curChar == ':') {
                        this.jjCheckNAdd(103);
                     }
                     break;
                  case 103:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddStates(52, 54);
                     }
                     break;
                  case 104:
                     if (this.curChar == '.') {
                        this.jjCheckNAddTwoStates(105, 24);
                     }
                     break;
                  case 105:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddTwoStates(105, 24);
                     }
                     break;
                  case 106:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddStates(55, 57);
                     }
                     break;
                  case 107:
                     if (this.curChar == ':') {
                        this.jjCheckNAdd(108);
                     }
                     break;
                  case 108:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddStates(58, 60);
                     }
                     break;
                  case 109:
                     if (this.curChar == ':') {
                        this.jjCheckNAdd(110);
                     }
                     break;
                  case 110:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddStates(61, 63);
                     }
                     break;
                  case 111:
                     if (this.curChar == '.') {
                        this.jjCheckNAddTwoStates(112, 24);
                     }
                     break;
                  case 112:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddTwoStates(112, 24);
                     }
                     break;
                  case 113:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddStates(64, 66);
                     }
                     break;
                  case 121:
                     if ((287948901175001088L & var24) != 0L) {
                        if (var10 > 487) {
                           var10 = 487;
                        }

                        this.jjCheckNAddStates(3, 10);
                     }
                     break;
                  case 122:
                     if ((287948901175001088L & var24) != 0L) {
                        if (var10 > 487) {
                           var10 = 487;
                        }

                        this.jjCheckNAddTwoStates(122, 123);
                     }
                     break;
                  case 123:
                     if (this.curChar == '.') {
                        if (var10 > 487) {
                           var10 = 487;
                        }

                        this.jjCheckNAdd(124);
                     }
                     break;
                  case 124:
                     if ((287948901175001088L & var24) != 0L) {
                        if (var10 > 487) {
                           var10 = 487;
                        }

                        this.jjCheckNAdd(124);
                     }
                     break;
                  case 125:
                     if ((287948901175001088L & var24) != 0L) {
                        if (var10 > 488) {
                           var10 = 488;
                        }

                        this.jjCheckNAdd(125);
                     }
                     break;
                  case 126:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddTwoStates(126, 127);
                     }
                     break;
                  case 128:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddStates(67, 69);
                     }
                     break;
                  case 129:
                     if (this.curChar == '.') {
                        this.jjCheckNAddTwoStates(130, 131);
                     }
                     break;
                  case 130:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddTwoStates(130, 131);
                     }
                     break;
                  case 132:
                     if ((43980465111040L & var24) != 0L) {
                        this.jjCheckNAdd(133);
                     }
                     break;
                  case 133:
                     if ((287948901175001088L & var24) != 0L) {
                        if (var10 > 492) {
                           var10 = 492;
                        }

                        this.jjCheckNAdd(133);
                     }
                     break;
                  case 134:
                     if (this.curChar == '.') {
                        this.jjCheckNAddTwoStates(135, 136);
                     }
                     break;
                  case 135:
                     if ((287948901175001088L & var24) != 0L) {
                        if (var10 > 487) {
                           var10 = 487;
                        }

                        this.jjCheckNAdd(135);
                     }
                     break;
                  case 136:
                     if ((287948901175001088L & var24) != 0L) {
                        this.jjCheckNAddTwoStates(136, 131);
                     }
               }
            } while(var8 != var7);
         } else if (this.curChar < 128) {
            long var23 = 1L << (this.curChar & 63);

            do {
               --var8;
               switch (this.jjstateSet[var8]) {
                  case 0:
                     if ((576460743847706622L & var23) != 0L) {
                        if (var10 > 480) {
                           var10 = 480;
                        }

                        this.jjCheckNAdd(1);
                     }

                     if ((72057594054705152L & var23) != 0L) {
                        this.jjstateSet[this.jjnewStateCnt++] = 13;
                     } else if (this.curChar == 'I') {
                        this.jjstateSet[this.jjnewStateCnt++] = 119;
                     }
                     break;
                  case 1:
                     if ((576460745995190270L & var23) != 0L) {
                        if (var10 > 480) {
                           var10 = 480;
                        }

                        this.jjCheckNAdd(1);
                     }
                  case 2:
                  case 3:
                  case 4:
                  case 6:
                  case 7:
                  case 8:
                  case 9:
                  case 11:
                  case 13:
                  case 15:
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
                  case 33:
                  case 34:
                  case 35:
                  case 89:
                  case 90:
                  case 91:
                  case 92:
                  case 93:
                  case 94:
                  case 95:
                  case 96:
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
                  case 121:
                  case 122:
                  case 123:
                  case 124:
                  case 125:
                  case 126:
                  case 128:
                  case 129:
                  case 130:
                  default:
                     break;
                  case 5:
                     this.jjAddStates(14, 16);
                     break;
                  case 10:
                     this.jjAddStates(11, 13);
                     break;
                  case 12:
                     if ((72057594054705152L & var23) != 0L) {
                        this.jjstateSet[this.jjnewStateCnt++] = 13;
                     }
                     break;
                  case 14:
                     if ((541165879422L & var23) != 0L) {
                        this.jjAddStates(70, 71);
                     }
                     break;
                  case 16:
                     if (this.curChar == 'L') {
                        this.jjstateSet[this.jjnewStateCnt++] = 17;
                     }
                     break;
                  case 25:
                     if (this.curChar == 'y') {
                        this.jjAddStates(72, 73);
                     }
                     break;
                  case 26:
                     if (this.curChar == 'r') {
                        if (var10 > 493) {
                           var10 = 493;
                        }

                        this.jjCheckNAdd(27);
                     }
                     break;
                  case 30:
                     if (this.curChar == 'a') {
                        this.jjCheckNAdd(26);
                     }
                     break;
                  case 31:
                     if (this.curChar == 'e') {
                        this.jjstateSet[this.jjnewStateCnt++] = 30;
                     }
                     break;
                  case 32:
                     if (this.curChar == 'r') {
                        this.jjCheckNAddTwoStates(33, 57);
                     }
                     break;
                  case 36:
                     if (this.curChar == 'o') {
                        this.jjAddStates(74, 77);
                     }
                     break;
                  case 37:
                     if (this.curChar == 'r' && var10 > 493) {
                        var10 = 493;
                     }
                     break;
                  case 38:
                     if (this.curChar == 'a') {
                        this.jjCheckNAdd(37);
                     }
                     break;
                  case 39:
                     if (this.curChar == 'e') {
                        this.jjstateSet[this.jjnewStateCnt++] = 38;
                     }
                     break;
                  case 40:
                     if (this.curChar == 'y') {
                        this.jjstateSet[this.jjnewStateCnt++] = 39;
                     }
                     break;
                  case 41:
                     if (this.curChar == 'y' && var10 > 493) {
                        var10 = 493;
                     }
                     break;
                  case 42:
                     if (this.curChar == 'a') {
                        this.jjstateSet[this.jjnewStateCnt++] = 41;
                     }
                     break;
                  case 43:
                     if (this.curChar == 'd') {
                        this.jjstateSet[this.jjnewStateCnt++] = 42;
                     }
                     break;
                  case 44:
                     if (this.curChar == 'u') {
                        this.jjCheckNAdd(37);
                     }
                     break;
                  case 45:
                     if (this.curChar == 'o') {
                        this.jjstateSet[this.jjnewStateCnt++] = 44;
                     }
                     break;
                  case 46:
                     if (this.curChar == 'h') {
                        this.jjstateSet[this.jjnewStateCnt++] = 45;
                     }
                     break;
                  case 47:
                     if (this.curChar == 'm') {
                        this.jjAddStates(78, 79);
                     }
                     break;
                  case 48:
                     if (this.curChar == 'h' && var10 > 493) {
                        var10 = 493;
                     }
                     break;
                  case 49:
                     if (this.curChar == 't') {
                        this.jjstateSet[this.jjnewStateCnt++] = 48;
                     }
                     break;
                  case 50:
                     if (this.curChar == 'n') {
                        this.jjstateSet[this.jjnewStateCnt++] = 49;
                     }
                     break;
                  case 51:
                     if (this.curChar == 'o') {
                        this.jjstateSet[this.jjnewStateCnt++] = 50;
                     }
                     break;
                  case 52:
                     if (this.curChar == 'e' && var10 > 493) {
                        var10 = 493;
                     }
                     break;
                  case 53:
                     if (this.curChar == 't') {
                        this.jjstateSet[this.jjnewStateCnt++] = 52;
                     }
                     break;
                  case 54:
                     if (this.curChar == 'u') {
                        this.jjstateSet[this.jjnewStateCnt++] = 53;
                     }
                     break;
                  case 55:
                     if (this.curChar == 'n') {
                        this.jjstateSet[this.jjnewStateCnt++] = 54;
                     }
                     break;
                  case 56:
                     if (this.curChar == 'i') {
                        this.jjstateSet[this.jjnewStateCnt++] = 55;
                     }
                     break;
                  case 57:
                     if (this.curChar == 't') {
                        this.jjstateSet[this.jjnewStateCnt++] = 36;
                     }
                     break;
                  case 58:
                     if (this.curChar == 'a') {
                        this.jjCheckNAdd(32);
                     }
                     break;
                  case 59:
                     if (this.curChar == 'e') {
                        this.jjstateSet[this.jjnewStateCnt++] = 58;
                     }
                     break;
                  case 60:
                     if (this.curChar == 'd') {
                        this.jjAddStates(80, 81);
                     }
                     break;
                  case 61:
                     if (this.curChar == 'y') {
                        if (var10 > 493) {
                           var10 = 493;
                        }

                        this.jjCheckNAdd(27);
                     }
                     break;
                  case 62:
                     if (this.curChar == 'a') {
                        this.jjstateSet[this.jjnewStateCnt++] = 61;
                     }
                     break;
                  case 63:
                     if (this.curChar == 'y') {
                        this.jjCheckNAddTwoStates(33, 57);
                     }
                     break;
                  case 64:
                     if (this.curChar == 'a') {
                        this.jjstateSet[this.jjnewStateCnt++] = 63;
                     }
                     break;
                  case 65:
                     if (this.curChar == 'h') {
                        this.jjAddStates(82, 83);
                     }
                     break;
                  case 66:
                     if (this.curChar == 'u') {
                        this.jjCheckNAdd(26);
                     }
                     break;
                  case 67:
                     if (this.curChar == 'o') {
                        this.jjstateSet[this.jjnewStateCnt++] = 66;
                     }
                     break;
                  case 68:
                     if (this.curChar == 'u') {
                        this.jjCheckNAdd(32);
                     }
                     break;
                  case 69:
                     if (this.curChar == 'o') {
                        this.jjstateSet[this.jjnewStateCnt++] = 68;
                     }
                     break;
                  case 70:
                     if (this.curChar == 'm') {
                        this.jjAddStates(84, 87);
                     }
                     break;
                  case 71:
                     if (this.curChar == 'h') {
                        if (var10 > 493) {
                           var10 = 493;
                        }

                        this.jjCheckNAdd(27);
                     }
                     break;
                  case 72:
                     if (this.curChar == 't') {
                        this.jjstateSet[this.jjnewStateCnt++] = 71;
                     }
                     break;
                  case 73:
                     if (this.curChar == 'n') {
                        this.jjstateSet[this.jjnewStateCnt++] = 72;
                     }
                     break;
                  case 74:
                     if (this.curChar == 'o') {
                        this.jjstateSet[this.jjnewStateCnt++] = 73;
                     }
                     break;
                  case 75:
                     if (this.curChar == 'e') {
                        if (var10 > 493) {
                           var10 = 493;
                        }

                        this.jjCheckNAdd(27);
                     }
                     break;
                  case 76:
                     if (this.curChar == 't') {
                        this.jjstateSet[this.jjnewStateCnt++] = 75;
                     }
                     break;
                  case 77:
                     if (this.curChar == 'u') {
                        this.jjstateSet[this.jjnewStateCnt++] = 76;
                     }
                     break;
                  case 78:
                     if (this.curChar == 'n') {
                        this.jjstateSet[this.jjnewStateCnt++] = 77;
                     }
                     break;
                  case 79:
                     if (this.curChar == 'i') {
                        this.jjstateSet[this.jjnewStateCnt++] = 78;
                     }
                     break;
                  case 80:
                     if (this.curChar == 'h') {
                        this.jjCheckNAddTwoStates(33, 57);
                     }
                     break;
                  case 81:
                     if (this.curChar == 't') {
                        this.jjstateSet[this.jjnewStateCnt++] = 80;
                     }
                     break;
                  case 82:
                     if (this.curChar == 'n') {
                        this.jjstateSet[this.jjnewStateCnt++] = 81;
                     }
                     break;
                  case 83:
                     if (this.curChar == 'o') {
                        this.jjstateSet[this.jjnewStateCnt++] = 82;
                     }
                     break;
                  case 84:
                     if (this.curChar == 'e') {
                        this.jjCheckNAddTwoStates(33, 57);
                     }
                     break;
                  case 85:
                     if (this.curChar == 't') {
                        this.jjstateSet[this.jjnewStateCnt++] = 84;
                     }
                     break;
                  case 86:
                     if (this.curChar == 'u') {
                        this.jjstateSet[this.jjnewStateCnt++] = 85;
                     }
                     break;
                  case 87:
                     if (this.curChar == 'n') {
                        this.jjstateSet[this.jjnewStateCnt++] = 86;
                     }
                     break;
                  case 88:
                     if (this.curChar == 'i') {
                        this.jjstateSet[this.jjnewStateCnt++] = 87;
                     }
                     break;
                  case 114:
                     if (this.curChar == 'A') {
                        this.jjstateSet[this.jjnewStateCnt++] = 16;
                     }
                     break;
                  case 115:
                     if (this.curChar == 'V') {
                        this.jjstateSet[this.jjnewStateCnt++] = 114;
                     }
                     break;
                  case 116:
                     if (this.curChar == 'R') {
                        this.jjstateSet[this.jjnewStateCnt++] = 115;
                     }
                     break;
                  case 117:
                     if (this.curChar == 'E') {
                        this.jjstateSet[this.jjnewStateCnt++] = 116;
                     }
                     break;
                  case 118:
                     if (this.curChar == 'T') {
                        this.jjstateSet[this.jjnewStateCnt++] = 117;
                     }
                     break;
                  case 119:
                     if (this.curChar == 'N') {
                        this.jjstateSet[this.jjnewStateCnt++] = 118;
                     }
                     break;
                  case 120:
                     if (this.curChar == 'I') {
                        this.jjstateSet[this.jjnewStateCnt++] = 119;
                     }
                     break;
                  case 127:
                     if ((44530220935296L & var23) != 0L && var10 > 489) {
                        var10 = 489;
                     }
                     break;
                  case 131:
                     if ((137438953504L & var23) != 0L) {
                        this.jjAddStates(88, 89);
                     }
               }
            } while(var8 != var7);
         } else {
            int var11 = this.curChar >> 8;
            int var12 = var11 >> 6;
            long var13 = 1L << (var11 & 63);
            int var15 = (this.curChar & 255) >> 6;
            long var16 = 1L << (this.curChar & 63);

            do {
               --var8;
               switch (this.jjstateSet[var8]) {
                  case 0:
                     if (jjCanMove_1(var11, var12, var15, var13, var16)) {
                        if (var10 > 480) {
                           var10 = 480;
                        }

                        this.jjCheckNAdd(1);
                     }
                     break;
                  case 1:
                     if (jjCanMove_2(var11, var12, var15, var13, var16)) {
                        if (var10 > 480) {
                           var10 = 480;
                        }

                        this.jjCheckNAdd(1);
                     }
                     break;
                  case 5:
                     if (jjCanMove_0(var11, var12, var15, var13, var16)) {
                        this.jjAddStates(14, 16);
                     }
                     break;
                  case 10:
                     if (jjCanMove_0(var11, var12, var15, var13, var16)) {
                        this.jjAddStates(11, 13);
                     }
               }
            } while(var8 != var7);
         }

         if (var10 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var10;
            this.jjmatchedPos = var2;
            var10 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var8 = this.jjnewStateCnt) == (var7 = 137 - (this.jjnewStateCnt = var7))) {
            break;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var20) {
            break;
         }
      }

      if (this.jjmatchedPos > var4) {
         return var2;
      } else {
         int var25 = Math.max(var2, var5);
         if (var2 < var25) {
            var8 = var25 - Math.min(var2, var5);

            while(var8-- > 0) {
               try {
                  this.curChar = this.input_stream.readChar();
               } catch (IOException var18) {
                  throw new Error("Internal Error : Please send a bug report.");
               }
            }
         }

         if (this.jjmatchedPos < var4) {
            this.jjmatchedKind = var3;
            this.jjmatchedPos = var4;
         } else if (this.jjmatchedPos == var4 && this.jjmatchedKind > var3) {
            this.jjmatchedKind = var3;
         }

         return var25;
      }
   }

   private final int jjStopStringLiteralDfa_18(int var1, long var2) {
      switch (var1) {
         case 0:
            if ((var2 & 576460752303423488L) != 0L) {
               this.jjmatchedKind = 62;
               this.jjmatchedPos = 0;
               return -1;
            }

            return -1;
         case 1:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 2:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 3:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 4:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 5:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 6:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 7:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 8:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 9:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 10:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 11:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 12:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 13:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 14:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 15:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 16:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         case 17:
            if ((var2 & 576460752303423488L) != 0L) {
               if (this.jjmatchedPos == 0) {
                  this.jjmatchedKind = 62;
                  this.jjmatchedPos = 0;
               }

               return -1;
            }

            return -1;
         default:
            return -1;
      }
   }

   private final int jjStartNfa_18(int var1, long var2) {
      return this.jjMoveNfa_18(this.jjStopStringLiteralDfa_18(var1, var2), var1 + 1);
   }

   private final int jjStartNfaWithStates_18(int var1, int var2, int var3) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return var1 + 1;
      }

      return this.jjMoveNfa_18(var3, var1 + 1);
   }

   private final int jjMoveStringLiteralDfa0_18() {
      switch (this.curChar) {
         case 'D' -> {
            return this.jjMoveStringLiteralDfa1_18(576460752303423488L);
         }
         case 'S' -> {
            return this.jjStopAtPos(0, 60);
         }
         case 'd' -> {
            return this.jjMoveStringLiteralDfa1_18(576460752303423488L);
         }
         case 's' -> {
            return this.jjStopAtPos(0, 60);
         }
         default -> {
            return this.jjMoveNfa_18(0, 0);
         }
      }
   }

   private final int jjMoveStringLiteralDfa1_18(long var1) {
      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var4) {
         this.jjStopStringLiteralDfa_18(0, var1);
         return 1;
      }

      switch (this.curChar) {
         case 'E' -> {
            return this.jjMoveStringLiteralDfa2_18(var1, 576460752303423488L);
         }
         case 'e' -> {
            return this.jjMoveStringLiteralDfa2_18(var1, 576460752303423488L);
         }
         default -> {
            return this.jjStartNfa_18(0, var1);
         }
      }
   }

   private final int jjMoveStringLiteralDfa2_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(0, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(1, var3);
            return 2;
         }

         switch (this.curChar) {
            case 'R' -> {
               return this.jjMoveStringLiteralDfa3_18(var3, 576460752303423488L);
            }
            case 'r' -> {
               return this.jjMoveStringLiteralDfa3_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(1, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa3_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(1, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(2, var3);
            return 3;
         }

         switch (this.curChar) {
            case 'B' -> {
               return this.jjMoveStringLiteralDfa4_18(var3, 576460752303423488L);
            }
            case 'b' -> {
               return this.jjMoveStringLiteralDfa4_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(2, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa4_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(2, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(3, var3);
            return 4;
         }

         switch (this.curChar) {
            case 'Y' -> {
               return this.jjMoveStringLiteralDfa5_18(var3, 576460752303423488L);
            }
            case 'y' -> {
               return this.jjMoveStringLiteralDfa5_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(3, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa5_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(3, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(4, var3);
            return 5;
         }

         switch (this.curChar) {
            case 'D' -> {
               return this.jjMoveStringLiteralDfa6_18(var3, 576460752303423488L);
            }
            case 'd' -> {
               return this.jjMoveStringLiteralDfa6_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(4, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa6_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(4, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(5, var3);
            return 6;
         }

         switch (this.curChar) {
            case 'A' -> {
               return this.jjMoveStringLiteralDfa7_18(var3, 576460752303423488L);
            }
            case 'a' -> {
               return this.jjMoveStringLiteralDfa7_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(5, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa7_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(5, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(6, var3);
            return 7;
         }

         switch (this.curChar) {
            case 'S' -> {
               return this.jjMoveStringLiteralDfa8_18(var3, 576460752303423488L);
            }
            case 's' -> {
               return this.jjMoveStringLiteralDfa8_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(6, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa8_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(6, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(7, var3);
            return 8;
         }

         switch (this.curChar) {
            case 'H' -> {
               return this.jjMoveStringLiteralDfa9_18(var3, 576460752303423488L);
            }
            case 'h' -> {
               return this.jjMoveStringLiteralDfa9_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(7, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa9_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(7, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(8, var3);
            return 9;
         }

         switch (this.curChar) {
            case 'P' -> {
               return this.jjMoveStringLiteralDfa10_18(var3, 576460752303423488L);
            }
            case 'p' -> {
               return this.jjMoveStringLiteralDfa10_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(8, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa10_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(8, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(9, var3);
            return 10;
         }

         switch (this.curChar) {
            case 'R' -> {
               return this.jjMoveStringLiteralDfa11_18(var3, 576460752303423488L);
            }
            case 'r' -> {
               return this.jjMoveStringLiteralDfa11_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(9, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa11_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(9, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(10, var3);
            return 11;
         }

         switch (this.curChar) {
            case 'O' -> {
               return this.jjMoveStringLiteralDfa12_18(var3, 576460752303423488L);
            }
            case 'o' -> {
               return this.jjMoveStringLiteralDfa12_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(10, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa12_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(10, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(11, var3);
            return 12;
         }

         switch (this.curChar) {
            case 'P' -> {
               return this.jjMoveStringLiteralDfa13_18(var3, 576460752303423488L);
            }
            case 'p' -> {
               return this.jjMoveStringLiteralDfa13_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(11, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa13_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(11, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(12, var3);
            return 13;
         }

         switch (this.curChar) {
            case 'E' -> {
               return this.jjMoveStringLiteralDfa14_18(var3, 576460752303423488L);
            }
            case 'e' -> {
               return this.jjMoveStringLiteralDfa14_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(12, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa14_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(12, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(13, var3);
            return 14;
         }

         switch (this.curChar) {
            case 'R' -> {
               return this.jjMoveStringLiteralDfa15_18(var3, 576460752303423488L);
            }
            case 'r' -> {
               return this.jjMoveStringLiteralDfa15_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(13, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa15_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(13, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(14, var3);
            return 15;
         }

         switch (this.curChar) {
            case 'T' -> {
               return this.jjMoveStringLiteralDfa16_18(var3, 576460752303423488L);
            }
            case 't' -> {
               return this.jjMoveStringLiteralDfa16_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(14, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa16_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(14, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(15, var3);
            return 16;
         }

         switch (this.curChar) {
            case 'I' -> {
               return this.jjMoveStringLiteralDfa17_18(var3, 576460752303423488L);
            }
            case 'i' -> {
               return this.jjMoveStringLiteralDfa17_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(15, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa17_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(15, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(16, var3);
            return 17;
         }

         switch (this.curChar) {
            case 'E' -> {
               return this.jjMoveStringLiteralDfa18_18(var3, 576460752303423488L);
            }
            case 'e' -> {
               return this.jjMoveStringLiteralDfa18_18(var3, 576460752303423488L);
            }
            default -> {
               return this.jjStartNfa_18(16, var3);
            }
         }
      }
   }

   private final int jjMoveStringLiteralDfa18_18(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjStartNfa_18(16, var1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_18(17, var3);
            return 18;
         }

         switch (this.curChar) {
            case 'S':
               if ((var3 & 576460752303423488L) != 0L) {
                  return this.jjStopAtPos(18, 59);
               }
               break;
            case 's':
               if ((var3 & 576460752303423488L) != 0L) {
                  return this.jjStopAtPos(18, 59);
               }
         }

         return this.jjStartNfa_18(17, var3);
      }
   }

   private final int jjMoveNfa_18(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (var7 > 62) {
                        var7 = 62;
                     }

                     if ((9216L & var17) != 0L && var7 > 61) {
                        var7 = 61;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var7 > 61) {
                        var7 = 61;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if (var7 > 62) {
                        var7 = 62;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-2251799814209537L & var16) != 0L) {
                        var7 = 62;
                     }
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (jjCanMove_0(var8, var9, var12, var10, var13) && var7 > 62) {
                        var7 = 62;
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjStopStringLiteralDfa_14(int var1, long var2) {
      switch (var1) {
         default -> {
            return -1;
         }
      }
   }

   private final int jjStartNfa_14(int var1, long var2) {
      return this.jjMoveNfa_14(this.jjStopStringLiteralDfa_14(var1, var2), var1 + 1);
   }

   private final int jjStartNfaWithStates_14(int var1, int var2, int var3) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return var1 + 1;
      }

      return this.jjMoveNfa_14(var3, var1 + 1);
   }

   private final int jjMoveStringLiteralDfa0_14() {
      switch (this.curChar) {
         case 'R' -> {
            return this.jjStopAtPos(0, 47);
         }
         case 'r' -> {
            return this.jjStopAtPos(0, 47);
         }
         default -> {
            return this.jjMoveNfa_14(0, 0);
         }
      }
   }

   private final int jjMoveNfa_14(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (var7 > 49) {
                        var7 = 49;
                     }

                     if ((9216L & var17) != 0L && var7 > 48) {
                        var7 = 48;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var7 > 48) {
                        var7 = 48;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if (var7 > 49) {
                        var7 = 49;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-1125899907104769L & var16) != 0L) {
                        var7 = 49;
                     }
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (jjCanMove_0(var8, var9, var12, var10, var13) && var7 > 49) {
                        var7 = 49;
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private final int jjStopStringLiteralDfa_9(int var1, long var2) {
      switch (var1) {
         default -> {
            return -1;
         }
      }
   }

   private final int jjStartNfa_9(int var1, long var2) {
      return this.jjMoveNfa_9(this.jjStopStringLiteralDfa_9(var1, var2), var1 + 1);
   }

   private final int jjStartNfaWithStates_9(int var1, int var2, int var3) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return var1 + 1;
      }

      return this.jjMoveNfa_9(var3, var1 + 1);
   }

   private final int jjMoveStringLiteralDfa0_9() {
      switch (this.curChar) {
         case 'P' -> {
            return this.jjStopAtPos(0, 32);
         }
         case 'p' -> {
            return this.jjStopAtPos(0, 32);
         }
         default -> {
            return this.jjMoveNfa_9(0, 0);
         }
      }
   }

   private final int jjMoveNfa_9(int var1, int var2) {
      int var4 = 0;
      this.jjnewStateCnt = 4;
      int var5 = 1;
      this.jjstateSet[0] = var1;
      int var7 = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long var17 = 1L << this.curChar;

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (var7 > 34) {
                        var7 = 34;
                     }

                     if ((9216L & var17) != 0L && var7 > 33) {
                        var7 = 33;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && var7 > 33) {
                        var7 = 33;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 3:
                     if (var7 > 34) {
                        var7 = 34;
                     }
               }
            } while(var5 != var4);
         } else if (this.curChar < 128) {
            long var16 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if ((-281474976776193L & var16) != 0L) {
                        var7 = 34;
                     }
               }
            } while(var5 != var4);
         } else {
            int var8 = this.curChar >> 8;
            int var9 = var8 >> 6;
            long var10 = 1L << (var8 & 63);
            int var12 = (this.curChar & 255) >> 6;
            long var13 = 1L << (this.curChar & 63);

            do {
               --var5;
               switch (this.jjstateSet[var5]) {
                  case 0:
                     if (jjCanMove_0(var8, var9, var12, var10, var13) && var7 > 34) {
                        var7 = 34;
                     }
               }
            } while(var5 != var4);
         }

         if (var7 != Integer.MAX_VALUE) {
            this.jjmatchedKind = var7;
            this.jjmatchedPos = var2;
            var7 = Integer.MAX_VALUE;
         }

         ++var2;
         if ((var5 = this.jjnewStateCnt) == (var4 = 4 - (this.jjnewStateCnt = var4))) {
            return var2;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var15) {
            return var2;
         }
      }
   }

   private static final boolean jjCanMove_0(int var0, int var1, int var2, long var3, long var5) {
      switch (var0) {
         case 0 -> {
            return (jjbitVec2[var2] & var5) != 0L;
         }
         default -> {
            return (jjbitVec0[var1] & var3) != 0L;
         }
      }
   }

   private static final boolean jjCanMove_1(int var0, int var1, int var2, long var3, long var5) {
      switch (var0) {
         case 0 -> {
            return (jjbitVec4[var2] & var5) != 0L;
         }
         case 1 -> {
            return (jjbitVec5[var2] & var5) != 0L;
         }
         case 2 -> {
            return (jjbitVec6[var2] & var5) != 0L;
         }
         case 3 -> {
            return (jjbitVec7[var2] & var5) != 0L;
         }
         case 4 -> {
            return (jjbitVec8[var2] & var5) != 0L;
         }
         case 5 -> {
            return (jjbitVec9[var2] & var5) != 0L;
         }
         case 6 -> {
            return (jjbitVec10[var2] & var5) != 0L;
         }
         case 9 -> {
            return (jjbitVec11[var2] & var5) != 0L;
         }
         case 10 -> {
            return (jjbitVec12[var2] & var5) != 0L;
         }
         case 11 -> {
            return (jjbitVec13[var2] & var5) != 0L;
         }
         case 12 -> {
            return (jjbitVec14[var2] & var5) != 0L;
         }
         case 13 -> {
            return (jjbitVec15[var2] & var5) != 0L;
         }
         case 14 -> {
            return (jjbitVec16[var2] & var5) != 0L;
         }
         case 15 -> {
            return (jjbitVec17[var2] & var5) != 0L;
         }
         case 16 -> {
            return (jjbitVec18[var2] & var5) != 0L;
         }
         case 17 -> {
            return (jjbitVec19[var2] & var5) != 0L;
         }
         case 30 -> {
            return (jjbitVec20[var2] & var5) != 0L;
         }
         case 31 -> {
            return (jjbitVec21[var2] & var5) != 0L;
         }
         case 32 -> {
            return (jjbitVec22[var2] & var5) != 0L;
         }
         case 33 -> {
            return (jjbitVec23[var2] & var5) != 0L;
         }
         case 48 -> {
            return (jjbitVec24[var2] & var5) != 0L;
         }
         case 49 -> {
            return (jjbitVec25[var2] & var5) != 0L;
         }
         case 159 -> {
            return (jjbitVec26[var2] & var5) != 0L;
         }
         case 215 -> {
            return (jjbitVec27[var2] & var5) != 0L;
         }
         case 250 -> {
            return (jjbitVec28[var2] & var5) != 0L;
         }
         case 251 -> {
            return (jjbitVec29[var2] & var5) != 0L;
         }
         case 253 -> {
            return (jjbitVec30[var2] & var5) != 0L;
         }
         case 254 -> {
            return (jjbitVec31[var2] & var5) != 0L;
         }
         case 255 -> {
            return (jjbitVec32[var2] & var5) != 0L;
         }
         default -> {
            return (jjbitVec3[var1] & var3) != 0L;
         }
      }
   }

   private static final boolean jjCanMove_2(int var0, int var1, int var2, long var3, long var5) {
      switch (var0) {
         case 0 -> {
            return (jjbitVec4[var2] & var5) != 0L;
         }
         case 1 -> {
            return (jjbitVec5[var2] & var5) != 0L;
         }
         case 2 -> {
            return (jjbitVec6[var2] & var5) != 0L;
         }
         case 3 -> {
            return (jjbitVec7[var2] & var5) != 0L;
         }
         case 4 -> {
            return (jjbitVec8[var2] & var5) != 0L;
         }
         case 5 -> {
            return (jjbitVec9[var2] & var5) != 0L;
         }
         case 6 -> {
            return (jjbitVec33[var2] & var5) != 0L;
         }
         case 9 -> {
            return (jjbitVec34[var2] & var5) != 0L;
         }
         case 10 -> {
            return (jjbitVec35[var2] & var5) != 0L;
         }
         case 11 -> {
            return (jjbitVec36[var2] & var5) != 0L;
         }
         case 12 -> {
            return (jjbitVec37[var2] & var5) != 0L;
         }
         case 13 -> {
            return (jjbitVec38[var2] & var5) != 0L;
         }
         case 14 -> {
            return (jjbitVec39[var2] & var5) != 0L;
         }
         case 15 -> {
            return (jjbitVec40[var2] & var5) != 0L;
         }
         case 16 -> {
            return (jjbitVec18[var2] & var5) != 0L;
         }
         case 17 -> {
            return (jjbitVec19[var2] & var5) != 0L;
         }
         case 30 -> {
            return (jjbitVec20[var2] & var5) != 0L;
         }
         case 31 -> {
            return (jjbitVec21[var2] & var5) != 0L;
         }
         case 32 -> {
            return (jjbitVec22[var2] & var5) != 0L;
         }
         case 33 -> {
            return (jjbitVec23[var2] & var5) != 0L;
         }
         case 48 -> {
            return (jjbitVec24[var2] & var5) != 0L;
         }
         case 49 -> {
            return (jjbitVec25[var2] & var5) != 0L;
         }
         case 159 -> {
            return (jjbitVec26[var2] & var5) != 0L;
         }
         case 215 -> {
            return (jjbitVec27[var2] & var5) != 0L;
         }
         case 250 -> {
            return (jjbitVec28[var2] & var5) != 0L;
         }
         case 251 -> {
            return (jjbitVec29[var2] & var5) != 0L;
         }
         case 253 -> {
            return (jjbitVec30[var2] & var5) != 0L;
         }
         case 254 -> {
            return (jjbitVec31[var2] & var5) != 0L;
         }
         case 255 -> {
            return (jjbitVec41[var2] & var5) != 0L;
         }
         default -> {
            return (jjbitVec3[var1] & var3) != 0L;
         }
      }
   }

   public SQLParserTokenManager(CharStream var1) {
      this.commentNestingDepth = 0;
      this.debugStream = System.out;
      this.jjrounds = new int[137];
      this.jjstateSet = new int[274];
      this.curLexState = 0;
      this.defaultLexState = 0;
      this.input_stream = var1;
   }

   public SQLParserTokenManager(CharStream var1, int var2) {
      this(var1);
      this.SwitchTo(var2);
   }

   public void ReInit(CharStream var1) {
      this.jjmatchedPos = this.jjnewStateCnt = 0;
      this.curLexState = this.defaultLexState;
      this.input_stream = var1;
      this.ReInitRounds();
   }

   private final void ReInitRounds() {
      this.jjround = -2147483647;

      for(int var1 = 137; var1-- > 0; this.jjrounds[var1] = Integer.MIN_VALUE) {
      }

   }

   public void ReInit(CharStream var1, int var2) {
      this.ReInit(var1);
      this.SwitchTo(var2);
   }

   public void SwitchTo(int var1) {
      if (var1 < 21 && var1 >= 0) {
         this.curLexState = var1;
      } else {
         throw new TokenMgrError("Error: Ignoring invalid lexical state : " + var1 + ". State unchanged.", 2);
      }
   }

   protected Token jjFillToken() {
      Token var1 = Token.newToken(this.jjmatchedKind);
      var1.kind = this.jjmatchedKind;
      if (this.jjmatchedPos < 0) {
         if (this.image == null) {
            var1.image = "";
         } else {
            var1.image = this.image.toString();
         }

         var1.beginLine = var1.endLine = this.input_stream.getBeginLine();
         var1.beginColumn = var1.endColumn = this.input_stream.getBeginColumn();
      } else {
         String var2 = jjstrLiteralImages[this.jjmatchedKind];
         var1.image = var2 == null ? this.input_stream.GetImage() : var2;
         var1.beginLine = this.input_stream.getBeginLine();
         var1.beginColumn = this.input_stream.getBeginColumn();
         var1.endLine = this.input_stream.getEndLine();
         var1.endColumn = this.input_stream.getEndColumn();
      }

      return var1;
   }

   public Token getNextToken() {
      Object var2 = null;
      int var4 = 0;

      label114:
      while(true) {
         try {
            this.curChar = this.input_stream.BeginToken();
         } catch (IOException var10) {
            this.jjmatchedKind = 0;
            Token var3 = this.jjFillToken();
            this.CommonTokenAction(var3);
            return var3;
         }

         this.image = null;
         this.jjimageLen = 0;

         while(true) {
            switch (this.curLexState) {
               case 0:
                  this.jjmatchedKind = Integer.MAX_VALUE;
                  this.jjmatchedPos = 0;
                  var4 = this.jjMoveStringLiteralDfa0_0();
                  break;
               case 1:
                  this.jjmatchedKind = Integer.MAX_VALUE;
                  this.jjmatchedPos = 0;
                  var4 = this.jjMoveStringLiteralDfa0_1();
                  if (this.jjmatchedPos == 0 && this.jjmatchedKind > 10) {
                     this.jjmatchedKind = 10;
                  }
                  break;
               case 2:
                  this.jjmatchedKind = Integer.MAX_VALUE;
                  this.jjmatchedPos = 0;
                  var4 = this.jjMoveStringLiteralDfa0_2();
                  if (this.jjmatchedPos == 0 && this.jjmatchedKind > 10) {
                     this.jjmatchedKind = 10;
                  }
                  break;
               case 3:
                  this.jjmatchedKind = 15;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_3();
                  break;
               case 4:
                  this.jjmatchedKind = 18;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_4();
                  break;
               case 5:
                  this.jjmatchedKind = 21;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_5();
                  break;
               case 6:
                  this.jjmatchedKind = 24;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_6();
                  break;
               case 7:
                  this.jjmatchedKind = 27;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_7();
                  break;
               case 8:
                  this.jjmatchedKind = 30;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_8();
                  break;
               case 9:
                  this.jjmatchedKind = 33;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_9();
                  break;
               case 10:
                  this.jjmatchedKind = 36;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_10();
                  break;
               case 11:
                  this.jjmatchedKind = 39;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_11();
                  break;
               case 12:
                  this.jjmatchedKind = 42;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_12();
                  break;
               case 13:
                  this.jjmatchedKind = 45;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_13();
                  break;
               case 14:
                  this.jjmatchedKind = 48;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_14();
                  break;
               case 15:
                  this.jjmatchedKind = 51;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_15();
                  break;
               case 16:
                  this.jjmatchedKind = 54;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_16();
                  break;
               case 17:
                  this.jjmatchedKind = 57;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_17();
                  break;
               case 18:
                  this.jjmatchedKind = 61;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_18();
                  break;
               case 19:
                  this.jjmatchedKind = 63;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_19();
                  break;
               case 20:
                  this.jjmatchedKind = 64;
                  this.jjmatchedPos = -1;
                  var4 = 0;
                  var4 = this.jjMoveStringLiteralDfa0_20();
            }

            if (this.jjmatchedKind == Integer.MAX_VALUE) {
               break label114;
            }

            if (this.jjmatchedPos + 1 < var4) {
               this.input_stream.backup(var4 - this.jjmatchedPos - 1);
            }

            if ((jjtoToken[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 63)) != 0L) {
               Token var13 = this.jjFillToken();
               this.TokenLexicalActions(var13);
               if (jjnewLexState[this.jjmatchedKind] != -1) {
                  this.curLexState = jjnewLexState[this.jjmatchedKind];
               }

               this.CommonTokenAction(var13);
               return var13;
            }

            if ((jjtoSkip[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 63)) != 0L) {
               this.SkipLexicalActions((Token)null);
               if (jjnewLexState[this.jjmatchedKind] != -1) {
                  this.curLexState = jjnewLexState[this.jjmatchedKind];
               }
               break;
            }

            this.MoreLexicalActions();
            if (jjnewLexState[this.jjmatchedKind] != -1) {
               this.curLexState = jjnewLexState[this.jjmatchedKind];
            }

            var4 = 0;
            this.jjmatchedKind = Integer.MAX_VALUE;

            try {
               this.curChar = this.input_stream.readChar();
            } catch (IOException var12) {
               break label114;
            }
         }
      }

      int var5 = this.input_stream.getEndLine();
      int var6 = this.input_stream.getEndColumn();
      String var7 = null;
      boolean var8 = false;

      try {
         this.input_stream.readChar();
         this.input_stream.backup(1);
      } catch (IOException var11) {
         var8 = true;
         var7 = var4 <= 1 ? "" : this.input_stream.GetImage();
         if (this.curChar != '\n' && this.curChar != '\r') {
            ++var6;
         } else {
            ++var5;
            var6 = 0;
         }
      }

      if (!var8) {
         this.input_stream.backup(1);
         var7 = var4 <= 1 ? "" : this.input_stream.GetImage();
      }

      throw new TokenMgrError(var8, this.curLexState, var5, var6, var7, this.curChar, 0);
   }

   void SkipLexicalActions(Token var1) {
      switch (this.jjmatchedKind) {
         default ->       }
   }

   void MoreLexicalActions() {
      this.jjimageLen += this.lengthOfMatch = this.jjmatchedPos + 1;
      switch (this.jjmatchedKind) {
         case 6:
            if (this.image == null) {
               this.image = new StringBuffer();
            }

            this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
            this.jjimageLen = 0;
            this.commentNestingDepth = 1;
         case 7:
         default:
            break;
         case 8:
            if (this.image == null) {
               this.image = new StringBuffer();
            }

            this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
            this.jjimageLen = 0;
            ++this.commentNestingDepth;
            break;
         case 9:
            if (this.image == null) {
               this.image = new StringBuffer();
            }

            this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
            this.jjimageLen = 0;
            --this.commentNestingDepth;
            this.SwitchTo(this.commentNestingDepth == 0 ? 1 : 2);
      }

   }

   void TokenLexicalActions(Token var1) {
      switch (this.jjmatchedKind) {
         case 60:
            if (this.image == null) {
               this.image = new StringBuffer();
            }

            this.image.append(this.input_stream.GetSuffix(this.jjimageLen + (this.lengthOfMatch = this.jjmatchedPos + 1)));
            var1.kind = 59;
         default:
      }
   }
}
