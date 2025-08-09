package org.apache.jute.compiler.generated;

import java.io.IOException;
import java.io.PrintStream;

public class RccTokenManager implements RccConstants {
   public PrintStream debugStream;
   static final long[] jjbitVec0 = new long[]{0L, 0L, -1L, -1L};
   static final int[] jjnextStates = new int[]{1, 2};
   public static final String[] jjstrLiteralImages = new String[]{"", null, null, null, null, null, null, null, null, null, null, "module", "class", "include", "byte", "boolean", "int", "long", "float", "double", "ustring", "buffer", "vector", "map", "{", "}", "<", ">", ";", ",", ".", null, null};
   public static final String[] lexStateNames = new String[]{"DEFAULT", "WithinOneLineComment", "WithinMultiLineComment"};
   public static final int[] jjnewLexState = new int[]{-1, -1, -1, -1, -1, 1, 0, -1, 2, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
   static final long[] jjtoToken = new long[]{8589932545L};
   static final long[] jjtoSkip = new long[]{894L};
   static final long[] jjtoSpecial = new long[]{864L};
   static final long[] jjtoMore = new long[]{1152L};
   protected SimpleCharStream input_stream;
   private final int[] jjrounds;
   private final int[] jjstateSet;
   private final StringBuilder jjimage;
   private StringBuilder image;
   private int jjimageLen;
   private int lengthOfMatch;
   protected char curChar;
   int curLexState;
   int defaultLexState;
   int jjnewStateCnt;
   int jjround;
   int jjmatchedPos;
   int jjmatchedKind;

   public void setDebugStream(PrintStream ds) {
      this.debugStream = ds;
   }

   private int jjMoveStringLiteralDfa0_1() {
      return this.jjMoveNfa_1(0, 0);
   }

   private int jjMoveNfa_1(int startState, int curPos) {
      int startsAt = 0;
      this.jjnewStateCnt = 3;
      int i = 1;
      this.jjstateSet[0] = startState;
      int kind = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long l = 1L << this.curChar;

            do {
               --i;
               switch (this.jjstateSet[i]) {
                  case 0:
                     if ((9216L & l) != 0L && kind > 6) {
                        kind = 6;
                     }

                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
                     break;
                  case 1:
                     if (this.curChar == '\n' && kind > 6) {
                        kind = 6;
                     }
                     break;
                  case 2:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 1;
                     }
               }
            } while(i != startsAt);
         } else if (this.curChar < 128) {
            long l = 1L << (this.curChar & 63);

            do {
               --i;
               switch (this.jjstateSet[i]) {
               }
            } while(i != startsAt);
         } else {
            int i2 = (this.curChar & 255) >> 6;
            long l2 = 1L << (this.curChar & 63);

            do {
               --i;
               switch (this.jjstateSet[i]) {
               }
            } while(i != startsAt);
         }

         if (kind != Integer.MAX_VALUE) {
            this.jjmatchedKind = kind;
            this.jjmatchedPos = curPos;
            kind = Integer.MAX_VALUE;
         }

         ++curPos;
         if ((i = this.jjnewStateCnt) == (startsAt = 3 - (this.jjnewStateCnt = startsAt))) {
            return curPos;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var9) {
            return curPos;
         }
      }
   }

   private final int jjStopStringLiteralDfa_0(int pos, long active0) {
      switch (pos) {
         case 0:
            if ((active0 & 16775168L) != 0L) {
               this.jjmatchedKind = 32;
               return 4;
            }

            return -1;
         case 1:
            if ((active0 & 16775168L) != 0L) {
               this.jjmatchedKind = 32;
               this.jjmatchedPos = 1;
               return 4;
            }

            return -1;
         case 2:
            if ((active0 & 8321024L) != 0L) {
               this.jjmatchedKind = 32;
               this.jjmatchedPos = 2;
               return 4;
            } else {
               if ((active0 & 8454144L) != 0L) {
                  return 4;
               }

               return -1;
            }
         case 3:
            if ((active0 & 147456L) != 0L) {
               return 4;
            } else {
               if ((active0 & 8173568L) != 0L) {
                  this.jjmatchedKind = 32;
                  this.jjmatchedPos = 3;
                  return 4;
               }

               return -1;
            }
         case 4:
            if ((active0 & 266240L) != 0L) {
               return 4;
            } else {
               if ((active0 & 7907328L) != 0L) {
                  this.jjmatchedKind = 32;
                  this.jjmatchedPos = 4;
                  return 4;
               }

               return -1;
            }
         case 5:
            if ((active0 & 6817792L) != 0L) {
               return 4;
            } else {
               if ((active0 & 1089536L) != 0L) {
                  this.jjmatchedKind = 32;
                  this.jjmatchedPos = 5;
                  return 4;
               }

               return -1;
            }
         default:
            return -1;
      }
   }

   private final int jjStartNfa_0(int pos, long active0) {
      return this.jjMoveNfa_0(this.jjStopStringLiteralDfa_0(pos, active0), pos + 1);
   }

   private int jjStopAtPos(int pos, int kind) {
      this.jjmatchedKind = kind;
      this.jjmatchedPos = pos;
      return pos + 1;
   }

   private int jjMoveStringLiteralDfa0_0() {
      switch (this.curChar) {
         case ',':
            return this.jjStopAtPos(0, 29);
         case '.':
            return this.jjStopAtPos(0, 30);
         case '/':
            return this.jjMoveStringLiteralDfa1_0(288L);
         case ';':
            return this.jjStopAtPos(0, 28);
         case '<':
            return this.jjStopAtPos(0, 26);
         case '>':
            return this.jjStopAtPos(0, 27);
         case 'b':
            return this.jjMoveStringLiteralDfa1_0(2146304L);
         case 'c':
            return this.jjMoveStringLiteralDfa1_0(4096L);
         case 'd':
            return this.jjMoveStringLiteralDfa1_0(524288L);
         case 'f':
            return this.jjMoveStringLiteralDfa1_0(262144L);
         case 'i':
            return this.jjMoveStringLiteralDfa1_0(73728L);
         case 'l':
            return this.jjMoveStringLiteralDfa1_0(131072L);
         case 'm':
            return this.jjMoveStringLiteralDfa1_0(8390656L);
         case 'u':
            return this.jjMoveStringLiteralDfa1_0(1048576L);
         case 'v':
            return this.jjMoveStringLiteralDfa1_0(4194304L);
         case '{':
            return this.jjStopAtPos(0, 24);
         case '}':
            return this.jjStopAtPos(0, 25);
         default:
            return this.jjMoveNfa_0(0, 0);
      }
   }

   private int jjMoveStringLiteralDfa1_0(long active0) {
      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var4) {
         this.jjStopStringLiteralDfa_0(0, active0);
         return 1;
      }

      switch (this.curChar) {
         case '*':
            if ((active0 & 256L) != 0L) {
               return this.jjStopAtPos(1, 8);
            }
            break;
         case '/':
            if ((active0 & 32L) != 0L) {
               return this.jjStopAtPos(1, 5);
            }
            break;
         case 'a':
            return this.jjMoveStringLiteralDfa2_0(active0, 8388608L);
         case 'e':
            return this.jjMoveStringLiteralDfa2_0(active0, 4194304L);
         case 'l':
            return this.jjMoveStringLiteralDfa2_0(active0, 266240L);
         case 'n':
            return this.jjMoveStringLiteralDfa2_0(active0, 73728L);
         case 'o':
            return this.jjMoveStringLiteralDfa2_0(active0, 690176L);
         case 's':
            return this.jjMoveStringLiteralDfa2_0(active0, 1048576L);
         case 'u':
            return this.jjMoveStringLiteralDfa2_0(active0, 2097152L);
         case 'y':
            return this.jjMoveStringLiteralDfa2_0(active0, 16384L);
      }

      return this.jjStartNfa_0(0, active0);
   }

   private int jjMoveStringLiteralDfa2_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(0, old0);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(1, active0);
            return 2;
         }

         switch (this.curChar) {
            case 'a':
               return this.jjMoveStringLiteralDfa3_0(active0, 4096L);
            case 'c':
               return this.jjMoveStringLiteralDfa3_0(active0, 4202496L);
            case 'd':
               return this.jjMoveStringLiteralDfa3_0(active0, 2048L);
            case 'f':
               return this.jjMoveStringLiteralDfa3_0(active0, 2097152L);
            case 'n':
               return this.jjMoveStringLiteralDfa3_0(active0, 131072L);
            case 'o':
               return this.jjMoveStringLiteralDfa3_0(active0, 294912L);
            case 'p':
               if ((active0 & 8388608L) != 0L) {
                  return this.jjStartNfaWithStates_0(2, 23, 4);
               }
            case 'b':
            case 'e':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'q':
            case 'r':
            case 's':
            default:
               return this.jjStartNfa_0(1, active0);
            case 't':
               if ((active0 & 65536L) != 0L) {
                  return this.jjStartNfaWithStates_0(2, 16, 4);
               }

               return this.jjMoveStringLiteralDfa3_0(active0, 1064960L);
            case 'u':
               return this.jjMoveStringLiteralDfa3_0(active0, 524288L);
         }
      }
   }

   private int jjMoveStringLiteralDfa3_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(1, old0);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(2, active0);
            return 3;
         }

         switch (this.curChar) {
            case 'a':
               return this.jjMoveStringLiteralDfa4_0(active0, 262144L);
            case 'b':
               return this.jjMoveStringLiteralDfa4_0(active0, 524288L);
            case 'c':
            case 'd':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            default:
               break;
            case 'e':
               if ((active0 & 16384L) != 0L) {
                  return this.jjStartNfaWithStates_0(3, 14, 4);
               }
               break;
            case 'f':
               return this.jjMoveStringLiteralDfa4_0(active0, 2097152L);
            case 'g':
               if ((active0 & 131072L) != 0L) {
                  return this.jjStartNfaWithStates_0(3, 17, 4);
               }
               break;
            case 'l':
               return this.jjMoveStringLiteralDfa4_0(active0, 40960L);
            case 'r':
               return this.jjMoveStringLiteralDfa4_0(active0, 1048576L);
            case 's':
               return this.jjMoveStringLiteralDfa4_0(active0, 4096L);
            case 't':
               return this.jjMoveStringLiteralDfa4_0(active0, 4194304L);
            case 'u':
               return this.jjMoveStringLiteralDfa4_0(active0, 2048L);
         }

         return this.jjStartNfa_0(2, active0);
      }
   }

   private int jjMoveStringLiteralDfa4_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(2, old0);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(3, active0);
            return 4;
         }

         switch (this.curChar) {
            case 'e':
               return this.jjMoveStringLiteralDfa5_0(active0, 2129920L);
            case 'f':
            case 'g':
            case 'h':
            case 'j':
            case 'k':
            case 'm':
            case 'n':
            case 'p':
            case 'q':
            case 'r':
            default:
               break;
            case 'i':
               return this.jjMoveStringLiteralDfa5_0(active0, 1048576L);
            case 'l':
               return this.jjMoveStringLiteralDfa5_0(active0, 526336L);
            case 'o':
               return this.jjMoveStringLiteralDfa5_0(active0, 4194304L);
            case 's':
               if ((active0 & 4096L) != 0L) {
                  return this.jjStartNfaWithStates_0(4, 12, 4);
               }
               break;
            case 't':
               if ((active0 & 262144L) != 0L) {
                  return this.jjStartNfaWithStates_0(4, 18, 4);
               }
               break;
            case 'u':
               return this.jjMoveStringLiteralDfa5_0(active0, 8192L);
         }

         return this.jjStartNfa_0(3, active0);
      }
   }

   private int jjMoveStringLiteralDfa5_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(3, old0);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(4, active0);
            return 5;
         }

         switch (this.curChar) {
            case 'a':
               return this.jjMoveStringLiteralDfa6_0(active0, 32768L);
            case 'd':
               return this.jjMoveStringLiteralDfa6_0(active0, 8192L);
            case 'e':
               if ((active0 & 2048L) != 0L) {
                  return this.jjStartNfaWithStates_0(5, 11, 4);
               }

               if ((active0 & 524288L) != 0L) {
                  return this.jjStartNfaWithStates_0(5, 19, 4);
               }
               break;
            case 'n':
               return this.jjMoveStringLiteralDfa6_0(active0, 1048576L);
            case 'r':
               if ((active0 & 2097152L) != 0L) {
                  return this.jjStartNfaWithStates_0(5, 21, 4);
               }

               if ((active0 & 4194304L) != 0L) {
                  return this.jjStartNfaWithStates_0(5, 22, 4);
               }
         }

         return this.jjStartNfa_0(4, active0);
      }
   }

   private int jjMoveStringLiteralDfa6_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(4, old0);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(5, active0);
            return 6;
         }

         switch (this.curChar) {
            case 'e':
               if ((active0 & 8192L) != 0L) {
                  return this.jjStartNfaWithStates_0(6, 13, 4);
               }
               break;
            case 'g':
               if ((active0 & 1048576L) != 0L) {
                  return this.jjStartNfaWithStates_0(6, 20, 4);
               }
               break;
            case 'n':
               if ((active0 & 32768L) != 0L) {
                  return this.jjStartNfaWithStates_0(6, 15, 4);
               }
         }

         return this.jjStartNfa_0(5, active0);
      }
   }

   private int jjStartNfaWithStates_0(int pos, int kind, int state) {
      this.jjmatchedKind = kind;
      this.jjmatchedPos = pos;

      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var5) {
         return pos + 1;
      }

      return this.jjMoveNfa_0(state, pos + 1);
   }

   private int jjMoveNfa_0(int startState, int curPos) {
      int startsAt = 0;
      this.jjnewStateCnt = 5;
      int i = 1;
      this.jjstateSet[0] = startState;
      int kind = Integer.MAX_VALUE;

      while(true) {
         if (++this.jjround == Integer.MAX_VALUE) {
            this.ReInitRounds();
         }

         if (this.curChar < '@') {
            long l = 1L << this.curChar;

            do {
               --i;
               switch (this.jjstateSet[i]) {
                  case 0:
                     if (this.curChar == '"') {
                        this.jjCheckNAdd(1);
                     }
                     break;
                  case 1:
                     if ((-17179869185L & l) != 0L) {
                        this.jjCheckNAddTwoStates(1, 2);
                     }
                     break;
                  case 2:
                     if (this.curChar == '"' && kind > 31) {
                        kind = 31;
                     }
                  case 3:
                  default:
                     break;
                  case 4:
                     if ((287948901175001088L & l) != 0L) {
                        if (kind > 32) {
                           kind = 32;
                        }

                        this.jjstateSet[this.jjnewStateCnt++] = 4;
                     }
               }
            } while(i != startsAt);
         } else if (this.curChar < 128) {
            long l = 1L << (this.curChar & 63);

            do {
               --i;
               switch (this.jjstateSet[i]) {
                  case 0:
                     if ((576460743847706622L & l) != 0L) {
                        if (kind > 32) {
                           kind = 32;
                        }

                        this.jjCheckNAdd(4);
                     }
                     break;
                  case 1:
                     this.jjAddStates(0, 1);
                  case 2:
                  case 3:
                  default:
                     break;
                  case 4:
                     if ((576460745995190270L & l) != 0L) {
                        if (kind > 32) {
                           kind = 32;
                        }

                        this.jjCheckNAdd(4);
                     }
               }
            } while(i != startsAt);
         } else {
            int i2 = (this.curChar & 255) >> 6;
            long l2 = 1L << (this.curChar & 63);

            do {
               --i;
               switch (this.jjstateSet[i]) {
                  case 1:
                     if ((jjbitVec0[i2] & l2) != 0L) {
                        this.jjAddStates(0, 1);
                     }
               }
            } while(i != startsAt);
         }

         if (kind != Integer.MAX_VALUE) {
            this.jjmatchedKind = kind;
            this.jjmatchedPos = curPos;
            kind = Integer.MAX_VALUE;
         }

         ++curPos;
         if ((i = this.jjnewStateCnt) == (startsAt = 5 - (this.jjnewStateCnt = startsAt))) {
            return curPos;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var9) {
            return curPos;
         }
      }
   }

   private int jjMoveStringLiteralDfa0_2() {
      switch (this.curChar) {
         case '*':
            return this.jjMoveStringLiteralDfa1_2(512L);
         default:
            return 1;
      }
   }

   private int jjMoveStringLiteralDfa1_2(long active0) {
      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var4) {
         return 1;
      }

      switch (this.curChar) {
         case '/':
            if ((active0 & 512L) != 0L) {
               return this.jjStopAtPos(1, 9);
            }

            return 2;
         default:
            return 2;
      }
   }

   public RccTokenManager(SimpleCharStream stream) {
      this.debugStream = System.out;
      this.jjrounds = new int[5];
      this.jjstateSet = new int[10];
      this.jjimage = new StringBuilder();
      this.image = this.jjimage;
      this.curLexState = 0;
      this.defaultLexState = 0;
      this.input_stream = stream;
   }

   public RccTokenManager(SimpleCharStream stream, int lexState) {
      this(stream);
      this.SwitchTo(lexState);
   }

   public void ReInit(SimpleCharStream stream) {
      this.jjmatchedPos = this.jjnewStateCnt = 0;
      this.curLexState = this.defaultLexState;
      this.input_stream = stream;
      this.ReInitRounds();
   }

   private void ReInitRounds() {
      this.jjround = -2147483647;

      for(int i = 5; i-- > 0; this.jjrounds[i] = Integer.MIN_VALUE) {
      }

   }

   public void ReInit(SimpleCharStream stream, int lexState) {
      this.ReInit(stream);
      this.SwitchTo(lexState);
   }

   public void SwitchTo(int lexState) {
      if (lexState < 3 && lexState >= 0) {
         this.curLexState = lexState;
      } else {
         throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", 2);
      }
   }

   protected Token jjFillToken() {
      String im = jjstrLiteralImages[this.jjmatchedKind];
      String curTokenImage = im == null ? this.input_stream.GetImage() : im;
      int beginLine = this.input_stream.getBeginLine();
      int beginColumn = this.input_stream.getBeginColumn();
      int endLine = this.input_stream.getEndLine();
      int endColumn = this.input_stream.getEndColumn();
      Token t = Token.newToken(this.jjmatchedKind, curTokenImage);
      t.beginLine = beginLine;
      t.endLine = endLine;
      t.beginColumn = beginColumn;
      t.endColumn = endColumn;
      return t;
   }

   public Token getNextToken() {
      Token specialToken = null;
      int curPos = 0;

      label123:
      while(true) {
         try {
            this.curChar = this.input_stream.BeginToken();
         } catch (IOException var9) {
            this.jjmatchedKind = 0;
            Token matchedToken = this.jjFillToken();
            matchedToken.specialToken = specialToken;
            return matchedToken;
         }

         this.image = this.jjimage;
         this.image.setLength(0);
         this.jjimageLen = 0;

         while(true) {
            switch (this.curLexState) {
               case 0:
                  try {
                     this.input_stream.backup(0);

                     while(this.curChar <= ' ' && (4294977024L & 1L << this.curChar) != 0L) {
                        this.curChar = this.input_stream.BeginToken();
                     }
                  } catch (IOException var12) {
                     continue label123;
                  }

                  this.jjmatchedKind = Integer.MAX_VALUE;
                  this.jjmatchedPos = 0;
                  curPos = this.jjMoveStringLiteralDfa0_0();
                  break;
               case 1:
                  this.jjmatchedKind = Integer.MAX_VALUE;
                  this.jjmatchedPos = 0;
                  curPos = this.jjMoveStringLiteralDfa0_1();
                  if (this.jjmatchedPos == 0 && this.jjmatchedKind > 7) {
                     this.jjmatchedKind = 7;
                  }
                  break;
               case 2:
                  this.jjmatchedKind = Integer.MAX_VALUE;
                  this.jjmatchedPos = 0;
                  curPos = this.jjMoveStringLiteralDfa0_2();
                  if (this.jjmatchedPos == 0 && this.jjmatchedKind > 10) {
                     this.jjmatchedKind = 10;
                  }
            }

            if (this.jjmatchedKind == Integer.MAX_VALUE) {
               break label123;
            }

            if (this.jjmatchedPos + 1 < curPos) {
               this.input_stream.backup(curPos - this.jjmatchedPos - 1);
            }

            if ((jjtoToken[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 63)) != 0L) {
               Token matchedToken = this.jjFillToken();
               matchedToken.specialToken = specialToken;
               if (jjnewLexState[this.jjmatchedKind] != -1) {
                  this.curLexState = jjnewLexState[this.jjmatchedKind];
               }

               return matchedToken;
            }

            if ((jjtoSkip[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 63)) != 0L) {
               if ((jjtoSpecial[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 63)) != 0L) {
                  Token matchedToken = this.jjFillToken();
                  if (specialToken == null) {
                     specialToken = matchedToken;
                  } else {
                     matchedToken.specialToken = specialToken;
                     specialToken = specialToken.next = matchedToken;
                  }

                  this.SkipLexicalActions(matchedToken);
               } else {
                  this.SkipLexicalActions((Token)null);
               }

               if (jjnewLexState[this.jjmatchedKind] != -1) {
                  this.curLexState = jjnewLexState[this.jjmatchedKind];
               }
               break;
            }

            this.jjimageLen += this.jjmatchedPos + 1;
            if (jjnewLexState[this.jjmatchedKind] != -1) {
               this.curLexState = jjnewLexState[this.jjmatchedKind];
            }

            curPos = 0;
            this.jjmatchedKind = Integer.MAX_VALUE;

            try {
               this.curChar = this.input_stream.readChar();
            } catch (IOException var11) {
               break label123;
            }
         }
      }

      int error_line = this.input_stream.getEndLine();
      int error_column = this.input_stream.getEndColumn();
      String error_after = null;
      boolean EOFSeen = false;

      try {
         this.input_stream.readChar();
         this.input_stream.backup(1);
      } catch (IOException var10) {
         EOFSeen = true;
         error_after = curPos <= 1 ? "" : this.input_stream.GetImage();
         if (this.curChar != '\n' && this.curChar != '\r') {
            ++error_column;
         } else {
            ++error_line;
            error_column = 0;
         }
      }

      if (!EOFSeen) {
         this.input_stream.backup(1);
         error_after = curPos <= 1 ? "" : this.input_stream.GetImage();
      }

      throw new TokenMgrError(EOFSeen, this.curLexState, error_line, error_column, error_after, this.curChar, 0);
   }

   void SkipLexicalActions(Token matchedToken) {
      switch (this.jjmatchedKind) {
         default:
      }
   }

   private void jjCheckNAdd(int state) {
      if (this.jjrounds[state] != this.jjround) {
         this.jjstateSet[this.jjnewStateCnt++] = state;
         this.jjrounds[state] = this.jjround;
      }

   }

   private void jjAddStates(int start, int end) {
      do {
         this.jjstateSet[this.jjnewStateCnt++] = jjnextStates[start];
      } while(start++ != end);

   }

   private void jjCheckNAddTwoStates(int state1, int state2) {
      this.jjCheckNAdd(state1);
      this.jjCheckNAdd(state2);
   }
}
