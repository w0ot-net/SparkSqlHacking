package org.apache.derby.impl.tools.ij;

import java.io.IOException;
import java.io.PrintStream;

class ijTokenManager implements ijConstants {
   int commentNestingDepth;
   public PrintStream debugStream;
   static final long[] jjbitVec0 = new long[]{-2L, -1L, -1L, -1L};
   static final long[] jjbitVec2 = new long[]{0L, 0L, -1L, -1L};
   static final long[] jjbitVec3 = new long[]{0L, -16384L, -17590038560769L, 1297036692691091455L};
   static final long[] jjbitVec4 = new long[]{0L, 0L, 297241973452963840L, -36028797027352577L};
   static final long[] jjbitVec5 = new long[]{-1L, -1L, -1L, -270215977642229761L};
   static final long[] jjbitVec6 = new long[]{16777215L, -65536L, -432624840181022721L, 133144182787L};
   static final long[] jjbitVec7 = new long[]{0L, 288230376151711744L, -17179879616L, 4503599568617471L};
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
   static final long[] jjbitVec24 = new long[]{274877906943L, 0L, 0L, 0L};
   static final long[] jjbitVec25 = new long[]{17451448556060704L, -2L, -6574571521L, 8646911284551352319L};
   static final long[] jjbitVec26 = new long[]{-527765581332512L, -1L, 32767L, 0L};
   static final long[] jjbitVec27 = new long[]{-1L, -1L, 274877906943L, 0L};
   static final long[] jjbitVec28 = new long[]{-1L, -1L, 68719476735L, 0L};
   static final long[] jjbitVec29 = new long[]{70368744177663L, 0L, 0L, 0L};
   static final long[] jjbitVec30 = new long[]{6881498029467631743L, -37L, 1125899906842623L, -524288L};
   static final long[] jjbitVec31 = new long[]{4611686018427387903L, -65536L, -196609L, 1152640029630136575L};
   static final long[] jjbitVec32 = new long[]{0L, -11540474045136896L, -1L, 2305843009213693951L};
   static final long[] jjbitVec33 = new long[]{576460743713488896L, -274743689218L, Long.MAX_VALUE, 486341884L};
   static final long[] jjbitVec34 = new long[]{576460743713488896L, -558556201875457L, 9007199254740991999L, 287949313494974463L};
   static final long[] jjbitVec35 = new long[]{2594073385365405664L, 281217261895680L, 271902628478820320L, 1125640866627584L};
   static final long[] jjbitVec36 = new long[]{247132830528276448L, 8162501023760384L, 2589004636761075680L, 281204393771008L};
   static final long[] jjbitVec37 = new long[]{2579997437506199520L, 281215936495616L, 270153412153034720L, 280925220896768L};
   static final long[] jjbitVec38 = new long[]{283724577500946400L, 281212983705600L, 283724577500946400L, 281214057447424L};
   static final long[] jjbitVec39 = new long[]{288228177128316896L, 281212983705600L, 0L, 0L};
   static final long[] jjbitVec40 = new long[]{3799912185593854L, 67043455L, 2309621682768192918L, 872349791L};
   static final long[] jjbitVec41 = new long[]{4393751543808L, 4398046510847L, 0L, 0L};
   static final long[] jjbitVec42 = new long[]{576460743780532224L, -274743689218L, Long.MAX_VALUE, 486341884L};
   static final int[] jjnextStates = new int[]{11, 12, 13, 1, 2, 4};
   public static final String[] jjstrLiteralImages = new String[]{"", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "!", null, null, null, null, null, null, null, null, null, null, null, null, null, null, "=", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, ".", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "@", ",", "(", ")", "\"", "#", "-", "+", null, null, null, null, null};
   public static final String[] lexStateNames = new String[]{"DEFAULT", "IN_BRACKETED_COMMENT", "IN_NESTED_BRACKETED_COMMENT"};
   public static final int[] jjnewLexState = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, 1, 2, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
   static final long[] jjtoToken = new long[]{-16383L, -1L, 207L};
   static final long[] jjtoSkip = new long[]{1278L, 0L, 0L};
   static final long[] jjtoSpecial = new long[]{128L, 0L, 0L};
   static final long[] jjtoMore = new long[]{15104L, 0L, 0L};
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

   public void setDebugStream(PrintStream var1) {
      this.debugStream = var1;
   }

   private final int jjStopAtPos(int var1, int var2) {
      this.jjmatchedKind = var2;
      this.jjmatchedPos = var1;
      return var1 + 1;
   }

   private final int jjMoveStringLiteralDfa0_0() {
      switch (this.curChar) {
         case '\t':
            this.jjmatchedKind = 2;
            return this.jjMoveNfa_0(5, 0);
         case '\n':
            this.jjmatchedKind = 4;
            return this.jjMoveNfa_0(5, 0);
         case '\u000b':
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
         case '%':
         case '&':
         case '\'':
         case '*':
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
         case '>':
         case '?':
         case 'J':
         case 'K':
         case 'Y':
         case 'Z':
         case '[':
         case '\\':
         case ']':
         case '^':
         case '_':
         case '`':
         case 'j':
         case 'k':
         default:
            return this.jjMoveNfa_0(5, 0);
         case '\f':
            this.jjmatchedKind = 6;
            return this.jjMoveNfa_0(5, 0);
         case '\r':
            this.jjmatchedKind = 5;
            return this.jjMoveStringLiteralDfa1_0(8L, 0L);
         case ' ':
            this.jjmatchedKind = 1;
            return this.jjMoveNfa_0(5, 0);
         case '!':
            this.jjmatchedKind = 22;
            return this.jjMoveNfa_0(5, 0);
         case '"':
            this.jjmatchedKind = 127;
            return this.jjMoveNfa_0(5, 0);
         case '#':
            this.jjmatchedKind = 128;
            return this.jjMoveNfa_0(5, 0);
         case '(':
            this.jjmatchedKind = 125;
            return this.jjMoveNfa_0(5, 0);
         case ')':
            this.jjmatchedKind = 126;
            return this.jjMoveNfa_0(5, 0);
         case '+':
            this.jjmatchedKind = 130;
            return this.jjMoveNfa_0(5, 0);
         case ',':
            this.jjmatchedKind = 124;
            return this.jjMoveNfa_0(5, 0);
         case '-':
            this.jjmatchedKind = 129;
            return this.jjMoveNfa_0(5, 0);
         case '.':
            this.jjmatchedKind = 64;
            return this.jjMoveNfa_0(5, 0);
         case '/':
            return this.jjMoveStringLiteralDfa1_0(256L, 0L);
         case '=':
            this.jjmatchedKind = 37;
            return this.jjMoveNfa_0(5, 0);
         case '@':
            this.jjmatchedKind = 123;
            return this.jjMoveNfa_0(5, 0);
         case 'A':
         case 'a':
            return this.jjMoveStringLiteralDfa1_0(4177920L, 0L);
         case 'B':
         case 'b':
            return this.jjMoveStringLiteralDfa1_0(8388608L, 0L);
         case 'C':
         case 'c':
            return this.jjMoveStringLiteralDfa1_0(2130706432L, 270215977642229760L);
         case 'D':
         case 'd':
            return this.jjMoveStringLiteralDfa1_0(15032385536L, 9007199254740992L);
         case 'E':
         case 'e':
            return this.jjMoveStringLiteralDfa1_0(944892805120L, 0L);
         case 'F':
         case 'f':
            return this.jjMoveStringLiteralDfa1_0(34084860461056L, 0L);
         case 'G':
         case 'g':
            return this.jjMoveStringLiteralDfa1_0(105553116266496L, 0L);
         case 'H':
         case 'h':
            return this.jjMoveStringLiteralDfa1_0(985162418487296L, 0L);
         case 'I':
         case 'i':
            return this.jjMoveStringLiteralDfa1_0(16888498602639360L, 0L);
         case 'L':
         case 'l':
            return this.jjMoveStringLiteralDfa1_0(54043195528445952L, 0L);
         case 'M':
         case 'm':
            return this.jjMoveStringLiteralDfa1_0(72057594037927936L, 0L);
         case 'N':
         case 'n':
            return this.jjMoveStringLiteralDfa1_0(2161727821137838080L, 0L);
         case 'O':
         case 'o':
            return this.jjMoveStringLiteralDfa1_0(6917529027641081856L, 0L);
         case 'P':
         case 'p':
            return this.jjMoveStringLiteralDfa1_0(Long.MIN_VALUE, 126L);
         case 'Q':
         case 'q':
            return this.jjMoveStringLiteralDfa1_0(0L, 128L);
         case 'R':
         case 'r':
            return this.jjMoveStringLiteralDfa1_0(0L, 32512L);
         case 'S':
         case 's':
            return this.jjMoveStringLiteralDfa1_0(0L, 67043328L);
         case 'T':
         case 't':
            return this.jjMoveStringLiteralDfa1_0(0L, 67141632L);
         case 'U':
         case 'u':
            return this.jjMoveStringLiteralDfa1_0(0L, 402653184L);
         case 'V':
         case 'v':
            return this.jjMoveStringLiteralDfa1_0(0L, 536870912L);
         case 'W':
         case 'w':
            return this.jjMoveStringLiteralDfa1_0(0L, 288230379372937216L);
         case 'X':
         case 'x':
            return this.jjMoveStringLiteralDfa1_0(0L, 9007194959773696L);
      }
   }

   private final int jjMoveStringLiteralDfa1_0(long var1, long var3) {
      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var6) {
         return this.jjMoveNfa_0(5, 0);
      }

      switch (this.curChar) {
         case '\n':
            if ((var1 & 8L) != 0L) {
               this.jjmatchedKind = 3;
               this.jjmatchedPos = 1;
            }
         case '\u000b':
         case '\f':
         case '\r':
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
         case ' ':
         case '!':
         case '"':
         case '#':
         case '$':
         case '%':
         case '&':
         case '\'':
         case '(':
         case ')':
         case '+':
         case ',':
         case '-':
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
         case 'D':
         case 'G':
         case 'J':
         case 'K':
         case 'M':
         case 'Q':
         case 'V':
         case 'W':
         case 'Z':
         case '[':
         case '\\':
         case ']':
         case '^':
         case '_':
         case '`':
         case 'd':
         case 'g':
         case 'j':
         case 'k':
         case 'm':
         case 'q':
         case 'v':
         case 'w':
         default:
            break;
         case '*':
            if ((var1 & 256L) != 0L) {
               this.jjmatchedKind = 8;
               this.jjmatchedPos = 1;
            }
            break;
         case 'A':
         case 'a':
            return this.jjMoveStringLiteralDfa2_0(var1, -8989183756719882240L, var3, 18014395355430912L);
         case 'B':
         case 'b':
            return this.jjMoveStringLiteralDfa2_0(var1, 16384L, var3, 0L);
         case 'C':
         case 'c':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 393216L);
         case 'E':
         case 'e':
            return this.jjMoveStringLiteralDfa2_0(var1, 288898881377271808L, var3, 3673856L);
         case 'F':
         case 'f':
            return this.jjMoveStringLiteralDfa2_0(var1, 2305843009213726720L, var3, 0L);
         case 'H':
         case 'h':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 12582912L);
         case 'I':
         case 'i':
            return this.jjMoveStringLiteralDfa2_0(var1, 2203318222848L, var3, 2684354560L);
         case 'L':
         case 'l':
            return this.jjMoveStringLiteralDfa2_0(var1, 17196843008L, var3, 0L);
         case 'N':
         case 'n':
            if ((var1 & 1125899906842624L) != 0L) {
               this.jjmatchedKind = 50;
               this.jjmatchedPos = 1;
            } else if ((var1 & 4611686018427387904L) != 0L) {
               this.jjmatchedKind = 62;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 15762701775011840L, var3, 0L);
         case 'O':
         case 'o':
            if ((var3 & 32768L) != 0L) {
               this.jjmatchedKind = 79;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 1765837664944128000L, var3, 288230376151724032L);
         case 'P':
         case 'p':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 270215977642229760L);
         case 'R':
         case 'r':
            return this.jjMoveStringLiteralDfa2_0(var1, 8804682956800L, var3, 126L);
         case 'S':
         case 's':
            if ((var1 & 262144L) != 0L) {
               this.jjmatchedKind = 18;
               this.jjmatchedPos = 1;
            }

            return this.jjMoveStringLiteralDfa2_0(var1, 524288L, var3, 402653184L);
         case 'T':
         case 't':
            return this.jjMoveStringLiteralDfa2_0(var1, 1048576L, var3, 16777216L);
         case 'U':
         case 'u':
            return this.jjMoveStringLiteralDfa2_0(var1, 17593798754304L, var3, 16512L);
         case 'X':
         case 'x':
            return this.jjMoveStringLiteralDfa2_0(var1, 824633720832L, var3, 0L);
         case 'Y':
         case 'y':
            return this.jjMoveStringLiteralDfa2_0(var1, 0L, var3, 33554432L);
      }

      return this.jjMoveNfa_0(5, 1);
   }

   private final int jjMoveStringLiteralDfa2_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(5, 1);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(5, 1);
         }

         switch (this.curChar) {
            case 'A':
            case 'a':
               return this.jjMoveStringLiteralDfa3_0(var3, 51539607552L, var7, 16777472L);
            case 'B':
            case 'b':
               return this.jjMoveStringLiteralDfa3_0(var3, 0L, var7, 67108864L);
            case 'C':
            case 'c':
               return this.jjMoveStringLiteralDfa3_0(var3, 36028797018963968L, var7, 0L);
            case 'D':
            case 'd':
               if ((var3 & 68719476736L) != 0L) {
                  this.jjmatchedKind = 36;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var3, 2251799813685248L, var7, 0L);
            case 'E':
            case 'e':
               return this.jjMoveStringLiteralDfa3_0(var3, 274877906944L, var7, 671088646L);
            case 'F':
            case 'f':
               if ((var3 & 2305843009213693952L) != 0L) {
                  this.jjmatchedKind = 61;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var3, 8388608L, var7, 0L);
            case 'G':
            case 'J':
            case 'K':
            case 'P':
            case 'Q':
            case 'W':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '`':
            case 'g':
            case 'j':
            case 'k':
            case 'p':
            case 'q':
            case 'w':
            default:
               return this.jjMoveNfa_0(5, 2);
            case 'H':
            case 'h':
               return this.jjMoveStringLiteralDfa3_0(var3, 1729382256910270464L, var7, 131072L);
            case 'I':
            case 'i':
               return this.jjMoveStringLiteralDfa3_0(var3, 1657857441792L, var7, 1342177408L);
            case 'L':
            case 'l':
               if ((var3 & 131072L) != 0L) {
                  this.jjmatchedKind = 17;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var3, 985162418487296L, var7, 12800L);
            case 'M':
            case 'm':
               return this.jjMoveStringLiteralDfa3_0(var3, 144115188109410304L, var7, 1024L);
            case 'N':
            case 'n':
               if ((var7 & 16384L) != 0L) {
                  this.jjmatchedKind = 78;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var3, 17592655806464L, var7, 34078720L);
            case 'O':
            case 'o':
               return this.jjMoveStringLiteralDfa3_0(var3, 8796109799424L, var7, 4194424L);
            case 'R':
            case 'r':
               if ((var3 & 4398046511104L) != 0L) {
                  this.jjmatchedKind = 42;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var3, 2200633868288L, var7, 288230376151973888L);
            case 'S':
            case 's':
               return this.jjMoveStringLiteralDfa3_0(var3, -9200854032275456000L, var7, 2048L);
            case 'T':
            case 't':
               if ((var3 & 35184372088832L) != 0L) {
                  this.jjmatchedKind = 45;
                  this.jjmatchedPos = 2;
               } else if ((var7 & 1048576L) != 0L) {
                  this.jjmatchedKind = 84;
                  this.jjmatchedPos = 2;
               }

               return this.jjMoveStringLiteralDfa3_0(var3, 9077568002097152L, var7, 9007201404321792L);
            case 'U':
            case 'u':
               return this.jjMoveStringLiteralDfa3_0(var3, 0L, var7, 8388608L);
            case 'V':
            case 'v':
               return this.jjMoveStringLiteralDfa3_0(var3, 0L, var7, 65536L);
            case 'X':
            case 'x':
               return this.jjMoveStringLiteralDfa3_0(var3, 360287970189639680L, var7, 0L);
            case 'Y':
            case 'y':
               return this.jjMoveStringLiteralDfa3_0(var3, 524288L, var7, 0L);
            case '_':
               return this.jjMoveStringLiteralDfa3_0(var3, 0L, var7, 279223172602003456L);
         }
      }
   }

   private final int jjMoveStringLiteralDfa3_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(5, 2);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(5, 2);
         }

         switch (this.curChar) {
            case '1':
               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 4294967296L);
            case '2':
               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 8589934592L);
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
            case 'Q':
            case 'U':
            case 'X':
            case 'Y':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'q':
            case 'u':
            default:
               break;
            case 'A':
            case 'a':
               return this.jjMoveStringLiteralDfa4_0(var3, 36028797019029504L, var7, 9007199254741504L);
            case 'B':
            case 'b':
               return this.jjMoveStringLiteralDfa4_0(var3, 34359738368L, var7, 0L);
            case 'C':
            case 'c':
               return this.jjMoveStringLiteralDfa4_0(var3, 88242250579968L, var7, 36028900098179096L);
            case 'D':
            case 'd':
               if ((var3 & 140737488355328L) != 0L) {
                  this.jjmatchedKind = 47;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 281474976710656L, var7, 162129741204160768L);
            case 'E':
            case 'e':
               if ((var3 & 144115188075855872L) != 0L) {
                  this.jjmatchedKind = 57;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 6755399441088512L, var7, 824633921536L);
            case 'F':
            case 'f':
               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 3298534883328L);
            case 'G':
            case 'g':
               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 72061992084439040L);
            case 'H':
            case 'h':
               if ((var7 & 2147483648L) != 0L) {
                  this.jjmatchedKind = 95;
                  this.jjmatchedPos = 3;
               }
               break;
            case 'I':
            case 'i':
               return this.jjMoveStringLiteralDfa4_0(var3, 72057594037927936L, var7, 0L);
            case 'J':
            case 'j':
               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 8796093022208L);
            case 'K':
            case 'k':
               if ((var7 & 288230376151711744L) != 0L) {
                  this.jjmatchedKind = 122;
                  this.jjmatchedPos = 3;
               }
               break;
            case 'L':
            case 'l':
               if ((var3 & 1099511627776L) != 0L) {
                  this.jjmatchedKind = 40;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 67117056L);
            case 'M':
            case 'm':
               if ((var3 & 8796093022208L) != 0L) {
                  this.jjmatchedKind = 43;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 33554432L, var7, 0L);
            case 'N':
            case 'n':
               return this.jjMoveStringLiteralDfa4_0(var3, 470286336L, var7, 17592454479872L);
            case 'O':
            case 'o':
               if ((var3 & 9007199254740992L) != 0L) {
                  this.jjmatchedKind = 53;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 1729382256920772608L, var7, 33819648L);
            case 'P':
            case 'p':
               if ((var3 & 562949953421312L) != 0L) {
                  this.jjmatchedKind = 49;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 17179869184L, var7, 35184372088866L);
            case 'R':
            case 'r':
               if ((var7 & 134217728L) != 0L) {
                  this.jjmatchedKind = 91;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 537919488L, var7, 492581209243648L);
            case 'S':
            case 's':
               return this.jjMoveStringLiteralDfa4_0(var3, -9223369836741001216L, var7, 8444249301843968L);
            case 'T':
            case 't':
               if ((var3 & 549755813888L) != 0L) {
                  this.jjmatchedKind = 39;
                  this.jjmatchedPos = 3;
               } else if ((var3 & 18014398509481984L) != 0L) {
                  this.jjmatchedKind = 54;
                  this.jjmatchedPos = 3;
               } else if ((var3 & 288230376151711744L) != 0L) {
                  this.jjmatchedKind = 58;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 128L) != 0L) {
                  this.jjmatchedKind = 71;
                  this.jjmatchedPos = 3;
               } else if ((var7 & 1073741824L) != 0L) {
                  this.jjmatchedKind = 94;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 27263040L);
            case 'V':
            case 'v':
               return this.jjMoveStringLiteralDfa4_0(var3, 8589934592L, var7, 4L);
            case 'W':
            case 'w':
               if ((var7 & 4194304L) != 0L) {
                  this.jjmatchedKind = 86;
                  this.jjmatchedPos = 3;
               }

               return this.jjMoveStringLiteralDfa4_0(var3, 0L, var7, 536870912L);
         }

         return this.jjMoveNfa_0(5, 3);
      }
   }

   private final int jjMoveStringLiteralDfa4_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(5, 3);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(5, 3);
         }

         switch (this.curChar) {
            case 'A':
            case 'a':
               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 18015515203076098L);
            case 'B':
            case 'b':
               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 8192L);
            case 'C':
            case 'c':
               if ((var3 & 524288L) != 0L) {
                  this.jjmatchedKind = 19;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 2097152L, var7, 0L);
            case 'D':
            case 'd':
               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 8388608L);
            case 'E':
            case 'e':
               if ((var3 & 16777216L) != 0L) {
                  this.jjmatchedKind = 24;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 9596567552L, var7, 72273098400858168L);
            case 'F':
            case 'f':
               return this.jjMoveStringLiteralDfa5_0(var3, 281474976710656L, var7, 0L);
            case 'G':
            case 'g':
               if ((var7 & 268435456L) != 0L) {
                  this.jjmatchedKind = 92;
                  this.jjmatchedPos = 4;
               }
            case 'H':
            case 'J':
            case 'K':
            case 'Q':
            case 'Y':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'h':
            case 'j':
            case 'k':
            case 'q':
            default:
               return this.jjMoveNfa_0(5, 4);
            case 'I':
            case 'i':
               return this.jjMoveStringLiteralDfa5_0(var3, 34603008L, var7, 144115325515333636L);
            case 'L':
            case 'l':
               return this.jjMoveStringLiteralDfa5_0(var3, 1765411088288989184L, var7, 262144L);
            case 'M':
            case 'm':
               return this.jjMoveStringLiteralDfa5_0(var3, 72057594037927936L, var7, 131072L);
            case 'N':
            case 'n':
               return this.jjMoveStringLiteralDfa5_0(var3, 4503599627370496L, var7, 824667275264L);
            case 'O':
            case 'o':
               return this.jjMoveStringLiteralDfa5_0(var3, 5368709120L, var7, 36338962377212224L);
            case 'P':
            case 'p':
               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 12884967424L);
            case 'R':
            case 'r':
               if ((var3 & 32768L) != 0L) {
                  this.jjmatchedKind = 15;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 2155872256L, var7, 35184372088832L);
            case 'S':
            case 's':
               if ((var7 & 4096L) != 0L) {
                  this.jjmatchedKind = 76;
                  this.jjmatchedPos = 4;
               } else if ((var7 & 536870912L) != 0L) {
                  this.jjmatchedKind = 93;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 17179934720L, var7, 9007199254740992L);
            case 'T':
            case 't':
               if ((var3 & 2199023255552L) != 0L) {
                  this.jjmatchedKind = 41;
                  this.jjmatchedPos = 4;
               }

               return this.jjMoveStringLiteralDfa5_0(var3, 17592186044416L, var7, 1688849860264448L);
            case 'U':
            case 'u':
               return this.jjMoveStringLiteralDfa5_0(var3, 70643622084608L, var7, 6755399441057792L);
            case 'V':
            case 'v':
               return this.jjMoveStringLiteralDfa5_0(var3, 0L, var7, 1024L);
            case 'W':
            case 'w':
               return this.jjMoveStringLiteralDfa5_0(var3, Long.MIN_VALUE, var7, 0L);
            case 'X':
            case 'x':
               return this.jjMoveStringLiteralDfa5_0(var3, 2251799813685248L, var7, 0L);
         }
      }
   }

   private final int jjMoveStringLiteralDfa5_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(5, 4);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(5, 4);
         }

         switch (this.curChar) {
            case 'A':
            case 'a':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 1688849860403200L);
            case 'B':
            case 'b':
               return this.jjMoveStringLiteralDfa6_0(var3, 1048576L, var7, 2097152L);
            case 'C':
            case 'c':
               return this.jjMoveStringLiteralDfa6_0(var3, 469762048L, var7, 2322168557862976L);
            case 'D':
            case 'd':
               if ((var3 & 576460752303423488L) != 0L) {
                  this.jjmatchedKind = 59;
                  this.jjmatchedPos = 5;
               } else if ((var7 & 274877906944L) != 0L) {
                  this.jjmatchedKind = 102;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 1152921504606846976L, var7, 549755813912L);
            case 'E':
            case 'e':
               if ((var3 & 8388608L) != 0L) {
                  this.jjmatchedKind = 23;
                  this.jjmatchedPos = 5;
               } else if ((var7 & 1024L) != 0L) {
                  this.jjmatchedKind = 74;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 2251851353358336L, var7, 35184372088832L);
            case 'F':
            case 'f':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 17592186044416L);
            case 'G':
            case 'J':
            case 'K':
            case 'P':
            case 'Q':
            case 'V':
            case 'W':
            case 'X':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'g':
            case 'j':
            case 'k':
            case 'p':
            case 'q':
            case 'v':
            case 'w':
            case 'x':
            default:
               return this.jjMoveNfa_0(5, 5);
            case 'H':
            case 'h':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 12884901888L);
            case 'I':
            case 'i':
               return this.jjMoveStringLiteralDfa6_0(var3, 36046391352492032L, var7, 9895604650496L);
            case 'L':
            case 'l':
               if ((var7 & 262144L) != 0L) {
                  this.jjmatchedKind = 82;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 281474976710656L);
            case 'M':
            case 'm':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 68736253952L);
            case 'N':
            case 'n':
               return this.jjMoveStringLiteralDfa6_0(var3, 4831838208L, var7, 36028831378702592L);
            case 'O':
            case 'o':
               return this.jjMoveStringLiteralDfa6_0(var3, -9223090561875968000L, var7, 9007199263195140L);
            case 'R':
            case 'r':
               if ((var3 & 1073741824L) != 0L) {
                  this.jjmatchedKind = 30;
                  this.jjmatchedPos = 5;
               } else if ((var3 & 8589934592L) != 0L) {
                  this.jjmatchedKind = 33;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 70368744177664L, var7, 2199023257634L);
            case 'S':
            case 's':
               if ((var7 & 67108864L) != 0L) {
                  this.jjmatchedKind = 90;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 4503599627370496L, var7, 148759662630535168L);
            case 'T':
            case 't':
               if ((var3 & 33554432L) != 0L) {
                  this.jjmatchedKind = 25;
                  this.jjmatchedPos = 5;
               }

               return this.jjMoveStringLiteralDfa6_0(var3, 274877906944L, var7, 90076407774314496L);
            case 'U':
            case 'u':
               return this.jjMoveStringLiteralDfa6_0(var3, 72057594037944320L, var7, 0L);
            case 'Y':
            case 'y':
               return this.jjMoveStringLiteralDfa6_0(var3, 0L, var7, 33554432L);
         }
      }
   }

   private final int jjMoveStringLiteralDfa6_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(5, 5);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(5, 5);
         }

         switch (this.curChar) {
            case 'A':
            case 'a':
               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 18014428574253056L);
            case 'B':
            case 'b':
               return this.jjMoveStringLiteralDfa7_0(var3, 2147483648L, var7, 0L);
            case 'C':
            case 'c':
               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 218429117412943872L);
            case 'D':
            case 'd':
               return this.jjMoveStringLiteralDfa7_0(var3, 51539607552L, var7, 0L);
            case 'E':
            case 'e':
               if ((var3 & 274877906944L) != 0L) {
                  this.jjmatchedKind = 38;
                  this.jjmatchedPos = 6;
               } else if ((var7 & 2L) != 0L) {
                  this.jjmatchedKind = 65;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 16777216L);
            case 'F':
            case 'f':
               return this.jjMoveStringLiteralDfa7_0(var3, 1152921504606846976L, var7, 0L);
            case 'G':
            case 'g':
               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 2199023255552L);
            case 'I':
            case 'i':
               return this.jjMoveStringLiteralDfa7_0(var3, 4503599627370496L, var7, 589824L);
            case 'L':
            case 'l':
               if ((var7 & 1099511627776L) != 0L) {
                  this.jjmatchedKind = 104;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 299067164852480L);
            case 'M':
            case 'm':
               return this.jjMoveStringLiteralDfa7_0(var3, 72057594040025088L, var7, 68753031168L);
            case 'N':
            case 'n':
               if ((var7 & 8796093022208L) != 0L) {
                  this.jjmatchedKind = 107;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 4294967296L, var7, 36028831378702336L);
            case 'O':
            case 'o':
               return this.jjMoveStringLiteralDfa7_0(var3, 17592186044416L, var7, 70368744177728L);
            case 'P':
            case 'p':
               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 4538783999459328L);
            case 'R':
            case 'r':
               return this.jjMoveStringLiteralDfa7_0(var3, -9223020193133887488L, var7, 1689399616077824L);
            case 'S':
            case 's':
               if ((var3 & 65536L) != 0L) {
                  this.jjmatchedKind = 16;
                  this.jjmatchedPos = 6;
               } else if ((var3 & 2251799813685248L) != 0L) {
                  this.jjmatchedKind = 51;
                  this.jjmatchedPos = 6;
               } else if ((var7 & 131072L) != 0L) {
                  this.jjmatchedKind = 81;
                  this.jjmatchedPos = 6;
               }
            case 'H':
            case 'J':
            case 'K':
            case 'Q':
            case 'X':
            case 'Y':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '_':
            case '`':
            case 'h':
            case 'j':
            case 'k':
            case 'q':
            case 'x':
            case 'y':
            default:
               return this.jjMoveNfa_0(5, 6);
            case 'T':
            case 't':
               if ((var3 & 67108864L) != 0L) {
                  this.jjmatchedKind = 26;
                  this.jjmatchedPos = 6;
               } else if ((var3 & 536870912L) != 0L) {
                  this.jjmatchedKind = 29;
                  this.jjmatchedPos = 6;
               }

               return this.jjMoveStringLiteralDfa7_0(var3, 402669568L, var7, 32L);
            case 'U':
            case 'u':
               return this.jjMoveStringLiteralDfa7_0(var3, 1048576L, var7, 9147936743096348L);
            case 'V':
            case 'v':
               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 512L);
            case 'W':
            case 'w':
               return this.jjMoveStringLiteralDfa7_0(var3, 0L, var7, 8388608L);
            case 'Z':
            case 'z':
               return this.jjMoveStringLiteralDfa7_0(var3, 36028797018963968L, var7, 0L);
         }
      }
   }

   private final int jjMoveStringLiteralDfa7_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(5, 6);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(5, 6);
         }

         switch (this.curChar) {
            case 'A':
            case 'a':
               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 52776558133248L);
            case 'B':
            case 'b':
               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 281474976710656L);
            case 'C':
            case 'c':
               return this.jjMoveStringLiteralDfa8_0(var3, 281474976710656L, var7, 0L);
            case 'D':
            case 'd':
               if ((var3 & Long.MIN_VALUE) != 0L) {
                  this.jjmatchedKind = 63;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 72057594037927936L, var7, 0L);
            case 'E':
            case 'e':
               if ((var3 & 16384L) != 0L) {
                  this.jjmatchedKind = 14;
                  this.jjmatchedPos = 7;
               } else if ((var3 & 2147483648L) != 0L) {
                  this.jjmatchedKind = 31;
                  this.jjmatchedPos = 7;
               } else if ((var7 & 512L) != 0L) {
                  this.jjmatchedKind = 73;
                  this.jjmatchedPos = 7;
               } else if ((var7 & 2048L) != 0L) {
                  this.jjmatchedKind = 75;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 36099170058108928L, var7, 42786429845110784L);
            case 'F':
            case 'G':
            case 'H':
            case 'J':
            case 'P':
            case 'Q':
            case 'U':
            case 'W':
            case 'X':
            case 'Z':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '`':
            case 'f':
            case 'g':
            case 'h':
            case 'j':
            case 'p':
            case 'q':
            case 'u':
            case 'w':
            case 'x':
            default:
               break;
            case 'I':
            case 'i':
               return this.jjMoveStringLiteralDfa8_0(var3, 402653184L, var7, 68719476768L);
            case 'K':
            case 'k':
               if ((var7 & 8192L) != 0L) {
                  this.jjmatchedKind = 77;
                  this.jjmatchedPos = 7;
               }
               break;
            case 'L':
            case 'l':
               if ((var7 & 64L) != 0L) {
                  this.jjmatchedKind = 70;
                  this.jjmatchedPos = 7;
               }
               break;
            case 'M':
            case 'm':
               return this.jjMoveStringLiteralDfa8_0(var3, 2097152L, var7, 140737488355328L);
            case 'N':
            case 'n':
               if ((var7 & 8388608L) != 0L) {
                  this.jjmatchedKind = 87;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 17592186044416L, var7, 16842752L);
            case 'O':
            case 'o':
               return this.jjMoveStringLiteralDfa8_0(var3, 1152921504606846976L, var7, 216177317599248384L);
            case 'R':
            case 'r':
               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 9007199254741016L);
            case 'S':
            case 's':
               if ((var7 & 4L) != 0L) {
                  this.jjmatchedKind = 66;
                  this.jjmatchedPos = 7;
               } else if ((var7 & 33554432L) != 0L) {
                  this.jjmatchedKind = 89;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 18014978330066944L);
            case 'T':
            case 't':
               if ((var7 & 562949953421312L) != 0L) {
                  this.jjmatchedKind = 113;
                  this.jjmatchedPos = 7;
               }

               return this.jjMoveStringLiteralDfa8_0(var3, 4503616808288256L, var7, 1125899906842624L);
            case 'V':
            case 'v':
               return this.jjMoveStringLiteralDfa8_0(var3, 0L, var7, 70368744701952L);
            case 'Y':
            case 'y':
               if ((var7 & 256L) != 0L) {
                  this.jjmatchedKind = 72;
                  this.jjmatchedPos = 7;
               }
               break;
            case '_':
               return this.jjMoveStringLiteralDfa8_0(var3, 34359738368L, var7, 0L);
         }

         return this.jjMoveNfa_0(5, 7);
      }
   }

   private final int jjMoveStringLiteralDfa8_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(5, 7);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(5, 7);
         }

         switch (this.curChar) {
            case 'A':
            case 'a':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 281474976710656L);
            case 'C':
            case 'c':
               return this.jjMoveStringLiteralDfa9_0(var3, 4294967296L, var7, 45036580389257216L);
            case 'D':
            case 'd':
               return this.jjMoveStringLiteralDfa9_0(var3, 36028797018963968L, var7, 0L);
            case 'E':
            case 'e':
               if ((var7 & 8L) != 0L) {
                  this.jjmatchedKind = 67;
                  this.jjmatchedPos = 8;
               } else if ((var7 & 524288L) != 0L) {
                  this.jjmatchedKind = 83;
                  this.jjmatchedPos = 8;
               } else if ((var7 & 4294967296L) != 0L) {
                  this.jjmatchedKind = 96;
                  this.jjmatchedPos = 8;
               } else if ((var7 & 8589934592L) != 0L) {
                  this.jjmatchedKind = 97;
                  this.jjmatchedPos = 8;
               } else if ((var7 & 140737488355328L) != 0L) {
                  this.jjmatchedKind = 111;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 1048576L, var7, 70368744177712L);
            case 'G':
            case 'g':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 17592186044416L);
            case 'I':
            case 'i':
               return this.jjMoveStringLiteralDfa9_0(var3, 76561210847264768L, var7, 0L);
            case 'N':
            case 'n':
               return this.jjMoveStringLiteralDfa9_0(var3, 70368744177664L, var7, 220680917226618880L);
            case 'O':
            case 'o':
               return this.jjMoveStringLiteralDfa9_0(var3, 281475379363840L, var7, 18014415689351168L);
            case 'R':
            case 'r':
               return this.jjMoveStringLiteralDfa9_0(var3, 1152921538966585344L, var7, 1161084278931456L);
            case 'S':
            case 's':
               if ((var3 & 17592186044416L) != 0L) {
                  this.jjmatchedKind = 44;
                  this.jjmatchedPos = 8;
               }

               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 2251799813685248L);
            case 'T':
            case 't':
               if ((var7 & 65536L) != 0L) {
                  this.jjmatchedKind = 80;
                  this.jjmatchedPos = 8;
               } else if ((var7 & 16777216L) != 0L) {
                  this.jjmatchedKind = 88;
                  this.jjmatchedPos = 8;
               } else if ((var7 & 68719476736L) != 0L) {
                  this.jjmatchedKind = 100;
                  this.jjmatchedPos = 8;
               } else if ((var7 & 2199023255552L) != 0L) {
                  this.jjmatchedKind = 105;
                  this.jjmatchedPos = 8;
               }
            case 'B':
            case 'F':
            case 'H':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'P':
            case 'Q':
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
            case '`':
            case 'b':
            case 'f':
            case 'h':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'p':
            case 'q':
            default:
               return this.jjMoveNfa_0(5, 8);
            case '_':
               return this.jjMoveStringLiteralDfa9_0(var3, 0L, var7, 2097152L);
         }
      }
   }

   private final int jjMoveStringLiteralDfa9_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(5, 8);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(5, 8);
         }

         switch (this.curChar) {
            case 'A':
            case 'a':
               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 549755813888L);
            case 'C':
            case 'c':
               return this.jjMoveStringLiteralDfa10_0(var3, 1152921504606846976L, var7, 281474976710656L);
            case 'D':
            case 'd':
               if ((var7 & 4503599627370496L) != 0L) {
                  this.jjmatchedKind = 116;
                  this.jjmatchedPos = 9;
               }

               return this.jjMoveStringLiteralDfa10_0(var3, 36028797018963968L, var7, 0L);
            case 'E':
            case 'e':
               if ((var7 & 35184372088832L) != 0L) {
                  this.jjmatchedKind = 109;
                  this.jjmatchedPos = 9;
               } else if ((var7 & 9007199254740992L) != 0L) {
                  this.jjmatchedKind = 117;
                  this.jjmatchedPos = 9;
               }
            case 'B':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
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
            case '_':
            case '`':
            case 'b':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'p':
            case 'q':
            default:
               return this.jjMoveNfa_0(5, 9);
            case 'M':
            case 'm':
               return this.jjMoveStringLiteralDfa10_0(var3, 17179869184L, var7, 0L);
            case 'N':
            case 'n':
               if ((var3 & 134217728L) != 0L) {
                  this.jjmatchedKind = 27;
                  this.jjmatchedPos = 9;
               }

               return this.jjMoveStringLiteralDfa10_0(var3, 281475245146112L, var7, 216177317599248384L);
            case 'O':
            case 'o':
               return this.jjMoveStringLiteralDfa10_0(var3, 34359738368L, var7, 0L);
            case 'R':
            case 'r':
               if ((var7 & 70368744177664L) != 0L) {
                  this.jjmatchedKind = 110;
                  this.jjmatchedPos = 9;
               }

               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 2097152L);
            case 'S':
            case 's':
               if ((var3 & 1048576L) != 0L) {
                  this.jjmatchedKind = 20;
                  this.jjmatchedPos = 9;
               } else if ((var7 & 16L) != 0L) {
                  this.jjmatchedKind = 68;
                  this.jjmatchedPos = 9;
               } else if ((var7 & 32L) != 0L) {
                  this.jjmatchedKind = 69;
                  this.jjmatchedPos = 9;
               } else if ((var7 & 17592186044416L) != 0L) {
                  this.jjmatchedKind = 108;
                  this.jjmatchedPos = 9;
               } else if ((var7 & 2251799813685248L) != 0L) {
                  this.jjmatchedKind = 115;
                  this.jjmatchedPos = 9;
               }

               return this.jjMoveStringLiteralDfa10_0(var3, 72057594037927936L, var7, 1125899906842624L);
            case 'T':
            case 't':
               if ((var3 & 2097152L) != 0L) {
                  this.jjmatchedKind = 21;
                  this.jjmatchedPos = 9;
               } else if ((var3 & 4294967296L) != 0L) {
                  this.jjmatchedKind = 32;
                  this.jjmatchedPos = 9;
               } else if ((var7 & 34359738368L) != 0L) {
                  this.jjmatchedKind = 99;
                  this.jjmatchedPos = 9;
               } else if ((var7 & 36028797018963968L) != 0L) {
                  this.jjmatchedKind = 119;
                  this.jjmatchedPos = 9;
               }

               return this.jjMoveStringLiteralDfa10_0(var3, 70368744177664L, var7, 0L);
            case 'U':
            case 'u':
               return this.jjMoveStringLiteralDfa10_0(var3, 0L, var7, 18014415689351168L);
            case 'V':
            case 'v':
               return this.jjMoveStringLiteralDfa10_0(var3, 4503599627370496L, var7, 0L);
         }
      }
   }

   private final int jjMoveStringLiteralDfa10_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(5, 9);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(5, 9);
         }

         switch (this.curChar) {
            case 'C':
            case 'c':
               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 1125899906842624L);
            case 'D':
            case 'F':
            case 'G':
            case 'H':
            case 'J':
            case 'M':
            case 'Q':
            case 'T':
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
            case 'd':
            case 'f':
            case 'g':
            case 'h':
            case 'j':
            case 'm':
            case 'q':
            default:
               break;
            case 'E':
            case 'e':
               if ((var3 & 17179869184L) != 0L) {
                  this.jjmatchedKind = 34;
                  this.jjmatchedPos = 10;
               } else if ((var3 & 4503599627370496L) != 0L) {
                  this.jjmatchedKind = 52;
                  this.jjmatchedPos = 10;
               }

               return this.jjMoveStringLiteralDfa11_0(var3, 0L, var7, 216177317599248384L);
            case 'I':
            case 'i':
               return this.jjMoveStringLiteralDfa11_0(var3, 36028797018963968L, var7, 0L);
            case 'K':
            case 'k':
               if ((var7 & 281474976710656L) != 0L) {
                  this.jjmatchedKind = 112;
                  this.jjmatchedPos = 10;
               }
               break;
            case 'L':
            case 'l':
               return this.jjMoveStringLiteralDfa11_0(var3, 34359738368L, var7, 0L);
            case 'N':
            case 'n':
               if ((var7 & 549755813888L) != 0L) {
                  this.jjmatchedKind = 103;
                  this.jjmatchedPos = 10;
               }

               return this.jjMoveStringLiteralDfa11_0(var3, 281474976710656L, var7, 0L);
            case 'O':
            case 'o':
               return this.jjMoveStringLiteralDfa11_0(var3, 1152921504606846976L, var7, 2097152L);
            case 'P':
            case 'p':
               return this.jjMoveStringLiteralDfa11_0(var3, 72057594037927936L, var7, 0L);
            case 'R':
            case 'r':
               return this.jjMoveStringLiteralDfa11_0(var3, 70368744177664L, var7, 18014415689351168L);
            case 'S':
            case 's':
               if ((var3 & 268435456L) != 0L) {
                  this.jjmatchedKind = 28;
                  this.jjmatchedPos = 10;
               }
         }

         return this.jjMoveNfa_0(5, 10);
      }
   }

   private final int jjMoveStringLiteralDfa11_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(5, 10);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(5, 10);
         }

         switch (this.curChar) {
            case 'A':
            case 'a':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var7, 1125899906842624L);
            case 'B':
            case 'D':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'M':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
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
            case 'b':
            case 'd':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'm':
            case 'p':
            case 'q':
            case 'r':
            default:
               return this.jjMoveNfa_0(5, 11);
            case 'C':
            case 'c':
               return this.jjMoveStringLiteralDfa12_0(var3, 0L, var7, 234191733288599552L);
            case 'E':
            case 'e':
               return this.jjMoveStringLiteralDfa12_0(var3, 281509336449024L, var7, 0L);
            case 'L':
            case 'l':
               return this.jjMoveStringLiteralDfa12_0(var3, 72057594037927936L, var7, 2097152L);
            case 'N':
            case 'n':
               return this.jjMoveStringLiteralDfa12_0(var3, 1152921504606846976L, var7, 0L);
            case 'O':
            case 'o':
               return this.jjMoveStringLiteralDfa12_0(var3, 70368744177664L, var7, 0L);
            case 'S':
            case 's':
               return this.jjMoveStringLiteralDfa12_0(var3, 36028797018963968L, var7, 0L);
         }
      }
   }

   private final int jjMoveStringLiteralDfa12_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(5, 11);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(5, 11);
         }

         switch (this.curChar) {
            case 'A':
            case 'a':
               return this.jjMoveStringLiteralDfa13_0(var3, 72057594037927936L, var7, 0L);
            case 'C':
            case 'c':
               return this.jjMoveStringLiteralDfa13_0(var3, 281474976710656L, var7, 0L);
            case 'E':
            case 'e':
               if ((var7 & 17179869184L) != 0L) {
                  this.jjmatchedKind = 98;
                  this.jjmatchedPos = 12;
               } else if ((var7 & 18014398509481984L) != 0L) {
                  this.jjmatchedKind = 118;
                  this.jjmatchedPos = 12;
               }

               return this.jjMoveStringLiteralDfa13_0(var3, 0L, var7, 2097152L);
            case 'N':
            case 'n':
               if ((var7 & 1125899906842624L) != 0L) {
                  this.jjmatchedKind = 114;
                  this.jjmatchedPos = 12;
               }

               return this.jjMoveStringLiteralDfa13_0(var3, 1152921504606846976L, var7, 0L);
            case 'P':
            case 'p':
               return this.jjMoveStringLiteralDfa13_0(var3, 36028797018963968L, var7, 0L);
            case 'S':
            case 's':
               if ((var3 & 34359738368L) != 0L) {
                  this.jjmatchedKind = 35;
                  this.jjmatchedPos = 12;
               }
            case 'B':
            case 'D':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'O':
            case 'Q':
            case 'R':
            case 'U':
            case 'V':
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
            case 'd':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'o':
            case 'q':
            case 'r':
            case 'u':
            case 'v':
            default:
               return this.jjMoveNfa_0(5, 12);
            case 'T':
            case 't':
               if ((var7 & 137438953472L) != 0L) {
                  this.jjmatchedKind = 101;
                  this.jjmatchedPos = 12;
               } else if ((var7 & 144115188075855872L) != 0L) {
                  this.jjmatchedKind = 121;
                  this.jjmatchedPos = 12;
               }

               return this.jjMoveStringLiteralDfa13_0(var3, 0L, var7, 72061992084439040L);
            case 'W':
            case 'w':
               return this.jjMoveStringLiteralDfa13_0(var3, 70368744177664L, var7, 0L);
         }
      }
   }

   private final int jjMoveStringLiteralDfa13_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(5, 12);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(5, 12);
         }

         switch (this.curChar) {
            case 'E':
            case 'e':
               return this.jjMoveStringLiteralDfa14_0(var3, 1152921504606846976L, var7, 0L);
            case 'I':
            case 'i':
               return this.jjMoveStringLiteralDfa14_0(var3, 0L, var7, 72061992084439040L);
            case 'L':
            case 'l':
               return this.jjMoveStringLiteralDfa14_0(var3, 36028797018963968L, var7, 0L);
            case 'N':
            case 'n':
               return this.jjMoveStringLiteralDfa14_0(var3, 70368744177664L, var7, 0L);
            case 'S':
            case 's':
               if ((var7 & 2097152L) != 0L) {
                  this.jjmatchedKind = 85;
                  this.jjmatchedPos = 13;
               }
            case 'F':
            case 'G':
            case 'H':
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
            case 'd':
            case 'f':
            case 'g':
            case 'h':
            case 'j':
            case 'k':
            case 'm':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            default:
               return this.jjMoveNfa_0(5, 13);
            case 'T':
            case 't':
               return this.jjMoveStringLiteralDfa14_0(var3, 281474976710656L, var7, 0L);
            case 'Y':
            case 'y':
               return this.jjMoveStringLiteralDfa14_0(var3, 72057594037927936L, var7, 0L);
         }
      }
   }

   private final int jjMoveStringLiteralDfa14_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(5, 13);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(5, 13);
         }

         switch (this.curChar) {
            case 'A':
            case 'a':
               return this.jjMoveStringLiteralDfa15_0(var3, 36028797018963968L, var7, 0L);
            case 'C':
            case 'c':
               return this.jjMoveStringLiteralDfa15_0(var3, 1152921504606846976L, var7, 0L);
            case 'I':
            case 'i':
               return this.jjMoveStringLiteralDfa15_0(var3, 281474976710656L, var7, 0L);
            case 'O':
            case 'o':
               return this.jjMoveStringLiteralDfa15_0(var3, 0L, var7, 72061992084439040L);
            case 'U':
            case 'u':
               return this.jjMoveStringLiteralDfa15_0(var3, 70368744177664L, var7, 0L);
            case 'W':
            case 'w':
               return this.jjMoveStringLiteralDfa15_0(var3, 72057594037927936L, var7, 0L);
            default:
               return this.jjMoveNfa_0(5, 14);
         }
      }
   }

   private final int jjMoveStringLiteralDfa15_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | (var7 = var7 & var5)) == 0L) {
         return this.jjMoveNfa_0(5, 14);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(5, 14);
         }

         switch (this.curChar) {
            case 'I':
            case 'i':
               return this.jjMoveStringLiteralDfa16_0(var3, 72057594037927936L, var7, 0L);
            case 'J':
            case 'K':
            case 'L':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
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
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
            case 'j':
            case 'k':
            case 'l':
            case 'p':
            case 'q':
            case 'r':
            case 's':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            default:
               break;
            case 'M':
            case 'm':
               return this.jjMoveStringLiteralDfa16_0(var3, 70368744177664L, var7, 0L);
            case 'N':
            case 'n':
               if ((var7 & 4398046511104L) != 0L) {
                  this.jjmatchedKind = 106;
                  this.jjmatchedPos = 15;
               } else if ((var7 & 72057594037927936L) != 0L) {
                  this.jjmatchedKind = 120;
                  this.jjmatchedPos = 15;
               }
               break;
            case 'O':
            case 'o':
               return this.jjMoveStringLiteralDfa16_0(var3, 281474976710656L, var7, 0L);
            case 'T':
            case 't':
               return this.jjMoveStringLiteralDfa16_0(var3, 1152921504606846976L, var7, 0L);
            case 'Y':
            case 'y':
               if ((var3 & 36028797018963968L) != 0L) {
                  this.jjmatchedKind = 55;
                  this.jjmatchedPos = 15;
               }
         }

         return this.jjMoveNfa_0(5, 15);
      }
   }

   private final int jjMoveStringLiteralDfa16_0(long var1, long var3, long var5, long var7) {
      if (((var3 = var3 & var1) | var7 & var5) == 0L) {
         return this.jjMoveNfa_0(5, 15);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var10) {
            return this.jjMoveNfa_0(5, 15);
         }

         switch (this.curChar) {
            case 'B':
            case 'b':
               return this.jjMoveStringLiteralDfa17_0(var3, 70368744177664L);
            case 'D':
            case 'd':
               return this.jjMoveStringLiteralDfa17_0(var3, 72057594037927936L);
            case 'I':
            case 'i':
               return this.jjMoveStringLiteralDfa17_0(var3, 1152921504606846976L);
            case 'N':
            case 'n':
               if ((var3 & 281474976710656L) != 0L) {
                  this.jjmatchedKind = 48;
                  this.jjmatchedPos = 16;
               }
            default:
               return this.jjMoveNfa_0(5, 16);
         }
      }
   }

   private final int jjMoveStringLiteralDfa17_0(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjMoveNfa_0(5, 16);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            return this.jjMoveNfa_0(5, 16);
         }

         switch (this.curChar) {
            case 'E':
            case 'e':
               return this.jjMoveStringLiteralDfa18_0(var3, 70368744177664L);
            case 'O':
            case 'o':
               return this.jjMoveStringLiteralDfa18_0(var3, 1152921504606846976L);
            case 'T':
            case 't':
               return this.jjMoveStringLiteralDfa18_0(var3, 72057594037927936L);
            default:
               return this.jjMoveNfa_0(5, 17);
         }
      }
   }

   private final int jjMoveStringLiteralDfa18_0(long var1, long var3) {
      if ((var3 = var3 & var1) == 0L) {
         return this.jjMoveNfa_0(5, 17);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            return this.jjMoveNfa_0(5, 17);
         }

         switch (this.curChar) {
            case 'H':
            case 'h':
               if ((var3 & 72057594037927936L) != 0L) {
                  this.jjmatchedKind = 56;
                  this.jjmatchedPos = 18;
               }
               break;
            case 'N':
            case 'n':
               if ((var3 & 1152921504606846976L) != 0L) {
                  this.jjmatchedKind = 60;
                  this.jjmatchedPos = 18;
               }
               break;
            case 'R':
            case 'r':
               if ((var3 & 70368744177664L) != 0L) {
                  this.jjmatchedKind = 46;
                  this.jjmatchedPos = 18;
               }
         }

         return this.jjMoveNfa_0(5, 18);
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
      this.jjnewStateCnt = 14;
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
                     if (this.curChar == '-') {
                        this.jjCheckNAddStates(3, 5);
                     }
                     break;
                  case 1:
                     if ((-9217L & var24) != 0L) {
                        this.jjCheckNAddStates(3, 5);
                     }
                     break;
                  case 2:
                     if ((9216L & var24) != 0L && var10 > 7) {
                        var10 = 7;
                     }
                     break;
                  case 3:
                     if (this.curChar == '\n' && var10 > 7) {
                        var10 = 7;
                     }
                     break;
                  case 4:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 3;
                     }
                     break;
                  case 5:
                     if ((287948901175001088L & var24) != 0L) {
                        if (var10 > 134) {
                           var10 = 134;
                        }

                        this.jjCheckNAdd(8);
                     } else if (this.curChar == '\'') {
                        this.jjCheckNAddStates(0, 2);
                     } else if (this.curChar == '-') {
                        this.jjstateSet[this.jjnewStateCnt++] = 0;
                     }
                  case 6:
                  default:
                     break;
                  case 7:
                     if ((287948901175001088L & var24) != 0L) {
                        if (var10 > 131) {
                           var10 = 131;
                        }

                        this.jjstateSet[this.jjnewStateCnt++] = 7;
                     }
                     break;
                  case 8:
                     if ((287948901175001088L & var24) != 0L) {
                        if (var10 > 134) {
                           var10 = 134;
                        }

                        this.jjCheckNAdd(8);
                     }
                     break;
                  case 9:
                  case 10:
                     if (this.curChar == '\'') {
                        this.jjCheckNAddStates(0, 2);
                     }
                     break;
                  case 11:
                     if (this.curChar == '\'') {
                        this.jjstateSet[this.jjnewStateCnt++] = 10;
                     }
                     break;
                  case 12:
                     if ((-549755813889L & var24) != 0L) {
                        this.jjCheckNAddStates(0, 2);
                     }
                     break;
                  case 13:
                     if (this.curChar == '\'' && var10 > 135) {
                        var10 = 135;
                     }
               }
            } while(var8 != var7);
         } else if (this.curChar < 128) {
            long var23 = 1L << (this.curChar & 63);

            do {
               --var8;
               switch (this.jjstateSet[var8]) {
                  case 1:
                     this.jjAddStates(3, 5);
                     break;
                  case 5:
                  case 7:
                     if ((576460745995190270L & var23) != 0L) {
                        if (var10 > 131) {
                           var10 = 131;
                        }

                        this.jjCheckNAdd(7);
                     }
                     break;
                  case 12:
                     this.jjAddStates(0, 2);
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
                  case 1:
                     if (jjCanMove_0(var11, var12, var15, var13, var16)) {
                        this.jjAddStates(3, 5);
                     }
                     break;
                  case 5:
                     if (jjCanMove_1(var11, var12, var15, var13, var16)) {
                        if (var10 > 131) {
                           var10 = 131;
                        }

                        this.jjCheckNAdd(7);
                     }
                     break;
                  case 7:
                     if (jjCanMove_2(var11, var12, var15, var13, var16)) {
                        if (var10 > 131) {
                           var10 = 131;
                        }

                        this.jjCheckNAdd(7);
                     }
                     break;
                  case 12:
                     if (jjCanMove_0(var11, var12, var15, var13, var16)) {
                        this.jjAddStates(0, 2);
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
         if ((var8 = this.jjnewStateCnt) == (var7 = 14 - (this.jjnewStateCnt = var7))) {
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

   private final int jjMoveStringLiteralDfa0_2() {
      switch (this.curChar) {
         case '*' -> {
            return this.jjMoveStringLiteralDfa1_2(4096L);
         }
         case '/' -> {
            return this.jjMoveStringLiteralDfa1_2(2048L);
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
            if ((var1 & 2048L) != 0L) {
               return this.jjStopAtPos(1, 11);
            }
            break;
         case '/':
            if ((var1 & 4096L) != 0L) {
               return this.jjStopAtPos(1, 12);
            }
            break;
         default:
            return 2;
      }

      return 2;
   }

   private final int jjMoveStringLiteralDfa0_1() {
      switch (this.curChar) {
         case '*' -> {
            return this.jjMoveStringLiteralDfa1_1(1024L);
         }
         case '/' -> {
            return this.jjMoveStringLiteralDfa1_1(512L);
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
            if ((var1 & 512L) != 0L) {
               return this.jjStopAtPos(1, 9);
            }
            break;
         case '/':
            if ((var1 & 1024L) != 0L) {
               return this.jjStopAtPos(1, 10);
            }
            break;
         default:
            return 2;
      }

      return 2;
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
         case 45 -> {
            return (jjbitVec24[var2] & var5) != 0L;
         }
         case 48 -> {
            return (jjbitVec25[var2] & var5) != 0L;
         }
         case 49 -> {
            return (jjbitVec26[var2] & var5) != 0L;
         }
         case 159 -> {
            return (jjbitVec27[var2] & var5) != 0L;
         }
         case 215 -> {
            return (jjbitVec28[var2] & var5) != 0L;
         }
         case 250 -> {
            return (jjbitVec29[var2] & var5) != 0L;
         }
         case 251 -> {
            return (jjbitVec30[var2] & var5) != 0L;
         }
         case 253 -> {
            return (jjbitVec31[var2] & var5) != 0L;
         }
         case 254 -> {
            return (jjbitVec32[var2] & var5) != 0L;
         }
         case 255 -> {
            return (jjbitVec33[var2] & var5) != 0L;
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
            return (jjbitVec34[var2] & var5) != 0L;
         }
         case 9 -> {
            return (jjbitVec35[var2] & var5) != 0L;
         }
         case 10 -> {
            return (jjbitVec36[var2] & var5) != 0L;
         }
         case 11 -> {
            return (jjbitVec37[var2] & var5) != 0L;
         }
         case 12 -> {
            return (jjbitVec38[var2] & var5) != 0L;
         }
         case 13 -> {
            return (jjbitVec39[var2] & var5) != 0L;
         }
         case 14 -> {
            return (jjbitVec40[var2] & var5) != 0L;
         }
         case 15 -> {
            return (jjbitVec41[var2] & var5) != 0L;
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
         case 45 -> {
            return (jjbitVec24[var2] & var5) != 0L;
         }
         case 48 -> {
            return (jjbitVec25[var2] & var5) != 0L;
         }
         case 49 -> {
            return (jjbitVec26[var2] & var5) != 0L;
         }
         case 159 -> {
            return (jjbitVec27[var2] & var5) != 0L;
         }
         case 215 -> {
            return (jjbitVec28[var2] & var5) != 0L;
         }
         case 250 -> {
            return (jjbitVec29[var2] & var5) != 0L;
         }
         case 251 -> {
            return (jjbitVec30[var2] & var5) != 0L;
         }
         case 253 -> {
            return (jjbitVec31[var2] & var5) != 0L;
         }
         case 254 -> {
            return (jjbitVec32[var2] & var5) != 0L;
         }
         case 255 -> {
            return (jjbitVec42[var2] & var5) != 0L;
         }
         default -> {
            return (jjbitVec3[var1] & var3) != 0L;
         }
      }
   }

   public ijTokenManager(CharStream var1) {
      this.commentNestingDepth = 0;
      this.debugStream = System.out;
      this.jjrounds = new int[14];
      this.jjstateSet = new int[28];
      this.curLexState = 0;
      this.defaultLexState = 0;
      this.input_stream = var1;
   }

   public ijTokenManager(CharStream var1, int var2) {
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

      for(int var1 = 14; var1-- > 0; this.jjrounds[var1] = Integer.MIN_VALUE) {
      }

   }

   public void ReInit(CharStream var1, int var2) {
      this.ReInit(var1);
      this.SwitchTo(var2);
   }

   public void SwitchTo(int var1) {
      if (var1 < 3 && var1 >= 0) {
         this.curLexState = var1;
      } else {
         throw new TokenMgrError("Error: Ignoring invalid lexical state : " + var1 + ". State unchanged.", 2);
      }
   }

   protected Token jjFillToken() {
      Token var1 = Token.newToken(this.jjmatchedKind);
      var1.kind = this.jjmatchedKind;
      String var2 = jjstrLiteralImages[this.jjmatchedKind];
      var1.image = var2 == null ? this.input_stream.GetImage() : var2;
      var1.beginLine = this.input_stream.getBeginLine();
      var1.beginColumn = this.input_stream.getBeginColumn();
      var1.endLine = this.input_stream.getEndLine();
      var1.endColumn = this.input_stream.getEndColumn();
      return var1;
   }

   public Token getNextToken() {
      Token var2 = null;
      int var4 = 0;

      label103:
      while(true) {
         try {
            this.curChar = this.input_stream.BeginToken();
         } catch (IOException var10) {
            this.jjmatchedKind = 0;
            Token var3 = this.jjFillToken();
            var3.specialToken = var2;
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
                  if (this.jjmatchedPos == 0 && this.jjmatchedKind > 13) {
                     this.jjmatchedKind = 13;
                  }
                  break;
               case 2:
                  this.jjmatchedKind = Integer.MAX_VALUE;
                  this.jjmatchedPos = 0;
                  var4 = this.jjMoveStringLiteralDfa0_2();
                  if (this.jjmatchedPos == 0 && this.jjmatchedKind > 13) {
                     this.jjmatchedKind = 13;
                  }
            }

            if (this.jjmatchedKind == Integer.MAX_VALUE) {
               break label103;
            }

            if (this.jjmatchedPos + 1 < var4) {
               this.input_stream.backup(var4 - this.jjmatchedPos - 1);
            }

            if ((jjtoToken[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 63)) != 0L) {
               Token var14 = this.jjFillToken();
               var14.specialToken = var2;
               if (jjnewLexState[this.jjmatchedKind] != -1) {
                  this.curLexState = jjnewLexState[this.jjmatchedKind];
               }

               return var14;
            }

            if ((jjtoSkip[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 63)) != 0L) {
               if ((jjtoSpecial[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 63)) != 0L) {
                  Token var13 = this.jjFillToken();
                  if (var2 == null) {
                     var2 = var13;
                  } else {
                     var13.specialToken = var2;
                     var2 = var2.next = var13;
                  }
               }

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
               break label103;
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

   void MoreLexicalActions() {
      this.jjimageLen += this.lengthOfMatch = this.jjmatchedPos + 1;
      switch (this.jjmatchedKind) {
         case 9:
            if (this.image == null) {
               this.image = new StringBuffer();
            }

            this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
            this.jjimageLen = 0;
            this.commentNestingDepth = 1;
         case 10:
         default:
            break;
         case 11:
            if (this.image == null) {
               this.image = new StringBuffer();
            }

            this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
            this.jjimageLen = 0;
            ++this.commentNestingDepth;
            break;
         case 12:
            if (this.image == null) {
               this.image = new StringBuffer();
            }

            this.image.append(this.input_stream.GetSuffix(this.jjimageLen));
            this.jjimageLen = 0;
            --this.commentNestingDepth;
            this.SwitchTo(this.commentNestingDepth == 0 ? 1 : 2);
      }

   }
}
