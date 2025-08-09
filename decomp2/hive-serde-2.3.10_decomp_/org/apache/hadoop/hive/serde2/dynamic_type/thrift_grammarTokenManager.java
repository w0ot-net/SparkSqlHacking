package org.apache.hadoop.hive.serde2.dynamic_type;

import java.io.IOException;
import java.io.PrintStream;

public class thrift_grammarTokenManager implements thrift_grammarConstants {
   public PrintStream debugStream;
   static final long[] jjbitVec0 = new long[]{0L, 0L, -1L, -1L};
   static final int[] jjnextStates = new int[]{5, 29, 30, 17, 22, 1, 2, 4, 18, 19, 21, 25, 27, 9, 10, 12, 13, 33, 34};
   public static final String[] jjstrLiteralImages = new String[]{"", null, null, null, null, null, null, null, "const", "namespace", "cpp_namespace", "cpp_include", "cpp_type", "java_package", "cocoa_prefix", "csharp_namespace", "php_namespace", "py_module", "perl_package", "ruby_namespace", "smalltalk_category", "smalltalk_prefix", "xsd_all", "xsd_optional", "xsd_nillable", "xsd_namespace", "xsd_attrs", "include", "void", "bool", "byte", "i16", "i32", "i64", "double", "string", "slist", "senum", "map", "list", "set", "async", "typedef", "struct", "exception", "extends", "throws", "service", "enum", "required", "optional", "skip", null, null, null, null, null, null, null, ",", ";", "{", "}", "=", "[", "]", ":", "(", ")", "<", ">"};
   public static final String[] lexStateNames = new String[]{"DEFAULT"};
   static final long[] jjtoToken = new long[]{-108086391056892159L, 127L};
   static final long[] jjtoSkip = new long[]{254L, 0L};
   protected SimpleCharStream input_stream;
   private final int[] jjrounds;
   private final int[] jjstateSet;
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

   private final int jjStopStringLiteralDfa_0(int pos, long active0, long active1) {
      switch (pos) {
         case 0:
            if ((active0 & 4503599627370240L) != 0L) {
               this.jjmatchedKind = 54;
               return 35;
            }

            return -1;
         case 1:
            if ((active0 & 4503599627370240L) != 0L) {
               this.jjmatchedKind = 54;
               this.jjmatchedPos = 1;
               return 35;
            }

            return -1;
         case 2:
            if ((active0 & 1389421920256L) != 0L) {
               return 35;
            } else {
               if ((active0 & 4502210205449984L) != 0L) {
                  this.jjmatchedKind = 54;
                  this.jjmatchedPos = 2;
                  return 35;
               }

               return -1;
            }
         case 3:
            if ((active0 & 2533826425257984L) != 0L) {
               return 35;
            } else {
               if ((active0 & 1968383780192000L) != 0L) {
                  this.jjmatchedKind = 54;
                  this.jjmatchedPos = 3;
                  return 35;
               }

               return -1;
            }
         case 4:
            if ((active0 & 2405181686016L) != 0L) {
               return 35;
            } else {
               if ((active0 & 1965978598505984L) != 0L) {
                  this.jjmatchedKind = 54;
                  this.jjmatchedPos = 4;
                  return 35;
               }

               return -1;
            }
         case 5:
            if ((active0 & 79216376807424L) != 0L) {
               return 35;
            } else {
               if ((active0 & 1886762221698560L) != 0L) {
                  this.jjmatchedKind = 54;
                  this.jjmatchedPos = 5;
                  return 35;
               }

               return -1;
            }
         case 6:
            if ((active0 & 1706442176331264L) != 0L) {
               this.jjmatchedKind = 54;
               this.jjmatchedPos = 6;
               return 35;
            } else {
               if ((active0 & 180320045367296L) != 0L) {
                  return 35;
               }

               return -1;
            }
         case 7:
            if ((active0 & 17592316063232L) != 0L) {
               this.jjmatchedKind = 54;
               this.jjmatchedPos = 7;
               return 35;
            } else {
               if ((active0 & 1688849860268032L) != 0L) {
                  return 35;
               }

               return -1;
            }
         case 8:
            if ((active0 & 62778368L) != 0L) {
               this.jjmatchedKind = 54;
               this.jjmatchedPos = 8;
               return 35;
            } else {
               if ((active0 & 17592253284864L) != 0L) {
                  return 35;
               }

               return -1;
            }
         case 9:
            if ((active0 & 62778368L) != 0L) {
               this.jjmatchedKind = 54;
               this.jjmatchedPos = 9;
               return 35;
            }

            return -1;
         case 10:
            if ((active0 & 2048L) != 0L) {
               return 35;
            } else {
               if ((active0 & 62776320L) != 0L) {
                  this.jjmatchedKind = 54;
                  this.jjmatchedPos = 10;
                  return 35;
               }

               return -1;
            }
         case 11:
            if ((active0 & 25452544L) != 0L) {
               return 35;
            } else {
               if ((active0 & 37323776L) != 0L) {
                  this.jjmatchedKind = 54;
                  this.jjmatchedPos = 11;
                  return 35;
               }

               return -1;
            }
         case 12:
            if ((active0 & 33620992L) != 0L) {
               return 35;
            } else {
               if ((active0 & 3702784L) != 0L) {
                  this.jjmatchedKind = 54;
                  this.jjmatchedPos = 12;
                  return 35;
               }

               return -1;
            }
         case 13:
            if ((active0 & 524288L) != 0L) {
               return 35;
            } else {
               if ((active0 & 3178496L) != 0L) {
                  this.jjmatchedKind = 54;
                  this.jjmatchedPos = 13;
                  return 35;
               }

               return -1;
            }
         case 14:
            if ((active0 & 3178496L) != 0L) {
               this.jjmatchedKind = 54;
               this.jjmatchedPos = 14;
               return 35;
            }

            return -1;
         case 15:
            if ((active0 & 2129920L) != 0L) {
               return 35;
            } else {
               if ((active0 & 1048576L) != 0L) {
                  this.jjmatchedKind = 54;
                  this.jjmatchedPos = 15;
                  return 35;
               }

               return -1;
            }
         case 16:
            if ((active0 & 1048576L) != 0L) {
               this.jjmatchedKind = 54;
               this.jjmatchedPos = 16;
               return 35;
            }

            return -1;
         default:
            return -1;
      }
   }

   private final int jjStartNfa_0(int pos, long active0, long active1) {
      return this.jjMoveNfa_0(this.jjStopStringLiteralDfa_0(pos, active0, active1), pos + 1);
   }

   private int jjStopAtPos(int pos, int kind) {
      this.jjmatchedKind = kind;
      this.jjmatchedPos = pos;
      return pos + 1;
   }

   private int jjMoveStringLiteralDfa0_0() {
      switch (this.curChar) {
         case '(':
            return this.jjStopAtPos(0, 67);
         case ')':
            return this.jjStopAtPos(0, 68);
         case '*':
         case '+':
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
         case '?':
         case '@':
         case 'A':
         case 'B':
         case 'C':
         case 'D':
         case 'E':
         case 'F':
         case 'G':
         case 'H':
         case 'I':
         case 'J':
         case 'K':
         case 'L':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'S':
         case 'T':
         case 'U':
         case 'V':
         case 'W':
         case 'X':
         case 'Y':
         case 'Z':
         case '\\':
         case '^':
         case '_':
         case '`':
         case 'f':
         case 'g':
         case 'h':
         case 'k':
         case 'q':
         case 'u':
         case 'w':
         case 'y':
         case 'z':
         case '|':
         default:
            return this.jjMoveNfa_0(0, 0);
         case ',':
            return this.jjStopAtPos(0, 59);
         case ':':
            return this.jjStopAtPos(0, 66);
         case ';':
            return this.jjStopAtPos(0, 60);
         case '<':
            return this.jjStopAtPos(0, 69);
         case '=':
            return this.jjStopAtPos(0, 63);
         case '>':
            return this.jjStopAtPos(0, 70);
         case '[':
            return this.jjStopAtPos(0, 64);
         case ']':
            return this.jjStopAtPos(0, 65);
         case 'a':
            return this.jjMoveStringLiteralDfa1_0(2199023255552L);
         case 'b':
            return this.jjMoveStringLiteralDfa1_0(1610612736L);
         case 'c':
            return this.jjMoveStringLiteralDfa1_0(56576L);
         case 'd':
            return this.jjMoveStringLiteralDfa1_0(17179869184L);
         case 'e':
            return this.jjMoveStringLiteralDfa1_0(334251534843904L);
         case 'i':
            return this.jjMoveStringLiteralDfa1_0(15166603264L);
         case 'j':
            return this.jjMoveStringLiteralDfa1_0(8192L);
         case 'l':
            return this.jjMoveStringLiteralDfa1_0(549755813888L);
         case 'm':
            return this.jjMoveStringLiteralDfa1_0(274877906944L);
         case 'n':
            return this.jjMoveStringLiteralDfa1_0(512L);
         case 'o':
            return this.jjMoveStringLiteralDfa1_0(1125899906842624L);
         case 'p':
            return this.jjMoveStringLiteralDfa1_0(458752L);
         case 'r':
            return this.jjMoveStringLiteralDfa1_0(562949953945600L);
         case 's':
            return this.jjMoveStringLiteralDfa1_0(2402673428004864L);
         case 't':
            return this.jjMoveStringLiteralDfa1_0(74766790688768L);
         case 'v':
            return this.jjMoveStringLiteralDfa1_0(268435456L);
         case 'x':
            return this.jjMoveStringLiteralDfa1_0(130023424L);
         case '{':
            return this.jjStopAtPos(0, 61);
         case '}':
            return this.jjStopAtPos(0, 62);
      }
   }

   private int jjMoveStringLiteralDfa1_0(long active0) {
      try {
         this.curChar = this.input_stream.readChar();
      } catch (IOException var4) {
         this.jjStopStringLiteralDfa_0(0, active0, 0L);
         return 1;
      }

      switch (this.curChar) {
         case '1':
            return this.jjMoveStringLiteralDfa2_0(active0, 2147483648L);
         case '2':
         case '4':
         case '5':
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
         case 'A':
         case 'B':
         case 'C':
         case 'D':
         case 'E':
         case 'F':
         case 'G':
         case 'H':
         case 'I':
         case 'J':
         case 'K':
         case 'L':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'S':
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
         case 'c':
         case 'd':
         case 'f':
         case 'g':
         case 'j':
         case 'q':
         case 'r':
         case 'v':
         case 'w':
         default:
            return this.jjStartNfa_0(0, active0, 0L);
         case '3':
            return this.jjMoveStringLiteralDfa2_0(active0, 4294967296L);
         case '6':
            return this.jjMoveStringLiteralDfa2_0(active0, 8589934592L);
         case 'a':
            return this.jjMoveStringLiteralDfa2_0(active0, 274877915648L);
         case 'e':
            return this.jjMoveStringLiteralDfa2_0(active0, 704924392620032L);
         case 'h':
            return this.jjMoveStringLiteralDfa2_0(active0, 70368744243200L);
         case 'i':
            return this.jjMoveStringLiteralDfa2_0(active0, 549755813888L);
         case 'k':
            return this.jjMoveStringLiteralDfa2_0(active0, 2251799813685248L);
         case 'l':
            return this.jjMoveStringLiteralDfa2_0(active0, 68719476736L);
         case 'm':
            return this.jjMoveStringLiteralDfa2_0(active0, 3145728L);
         case 'n':
            return this.jjMoveStringLiteralDfa2_0(active0, 281475110928384L);
         case 'o':
            return this.jjMoveStringLiteralDfa2_0(active0, 17985192192L);
         case 'p':
            return this.jjMoveStringLiteralDfa2_0(active0, 1125899906849792L);
         case 's':
            return this.jjMoveStringLiteralDfa2_0(active0, 2199153311744L);
         case 't':
            return this.jjMoveStringLiteralDfa2_0(active0, 8830452760576L);
         case 'u':
            return this.jjMoveStringLiteralDfa2_0(active0, 524288L);
         case 'x':
            return this.jjMoveStringLiteralDfa2_0(active0, 52776558133248L);
         case 'y':
            return this.jjMoveStringLiteralDfa2_0(active0, 4399120384000L);
      }
   }

   private int jjMoveStringLiteralDfa2_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(0, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(1, active0, 0L);
            return 2;
         }

         switch (this.curChar) {
            case '2':
               if ((active0 & 4294967296L) != 0L) {
                  return this.jjStartNfaWithStates_0(2, 32, 35);
               }
            case '3':
            case '5':
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
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
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
            case '`':
            case 'e':
            case 'f':
            case 'g':
            case 'j':
            case 'k':
            case 'l':
            case 'w':
            case 'x':
            default:
               break;
            case '4':
               if ((active0 & 8589934592L) != 0L) {
                  return this.jjStartNfaWithStates_0(2, 33, 35);
               }
               break;
            case '6':
               if ((active0 & 2147483648L) != 0L) {
                  return this.jjStartNfaWithStates_0(2, 31, 35);
               }
               break;
            case '_':
               return this.jjMoveStringLiteralDfa3_0(active0, 131072L);
            case 'a':
               return this.jjMoveStringLiteralDfa3_0(active0, 3145728L);
            case 'b':
               return this.jjMoveStringLiteralDfa3_0(active0, 524288L);
            case 'c':
               return this.jjMoveStringLiteralDfa3_0(active0, 17592320278528L);
            case 'd':
               return this.jjMoveStringLiteralDfa3_0(active0, 130023424L);
            case 'h':
               return this.jjMoveStringLiteralDfa3_0(active0, 32768L);
            case 'i':
               return this.jjMoveStringLiteralDfa3_0(active0, 2251868801597440L);
            case 'm':
               return this.jjMoveStringLiteralDfa3_0(active0, 512L);
            case 'n':
               return this.jjMoveStringLiteralDfa3_0(active0, 137438953728L);
            case 'o':
               return this.jjMoveStringLiteralDfa3_0(active0, 536870912L);
            case 'p':
               if ((active0 & 274877906944L) != 0L) {
                  return this.jjStartNfaWithStates_0(2, 38, 35);
               }

               return this.jjMoveStringLiteralDfa3_0(active0, 4398046583808L);
            case 'q':
               return this.jjMoveStringLiteralDfa3_0(active0, 562949953421312L);
            case 'r':
               return this.jjMoveStringLiteralDfa3_0(active0, 219936685555712L);
            case 's':
               return this.jjMoveStringLiteralDfa3_0(active0, 549755813888L);
            case 't':
               if ((active0 & 1099511627776L) != 0L) {
                  return this.jjStartNfaWithStates_0(2, 40, 35);
               }

               return this.jjMoveStringLiteralDfa3_0(active0, 1161085352673280L);
            case 'u':
               return this.jjMoveStringLiteralDfa3_0(active0, 281492156579840L);
            case 'v':
               return this.jjMoveStringLiteralDfa3_0(active0, 8192L);
            case 'y':
               return this.jjMoveStringLiteralDfa3_0(active0, 2199023255552L);
         }

         return this.jjStartNfa_0(1, active0, 0L);
      }
   }

   private int jjMoveStringLiteralDfa3_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(1, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(2, active0, 0L);
            return 3;
         }

         switch (this.curChar) {
            case '_':
               return this.jjMoveStringLiteralDfa4_0(active0, 130096128L);
            case '`':
            case 'c':
            case 'f':
            case 'g':
            case 'h':
            case 'j':
            case 'k':
            case 'q':
            case 'r':
            case 'w':
            case 'x':
            default:
               break;
            case 'a':
               return this.jjMoveStringLiteralDfa4_0(active0, 40960L);
            case 'b':
               return this.jjMoveStringLiteralDfa4_0(active0, 17179869184L);
            case 'd':
               if ((active0 & 268435456L) != 0L) {
                  return this.jjStartNfaWithStates_0(3, 28, 35);
               }
               break;
            case 'e':
               if ((active0 & 1073741824L) != 0L) {
                  return this.jjStartNfaWithStates_0(3, 30, 35);
               }

               return this.jjMoveStringLiteralDfa4_0(active0, 57174604644864L);
            case 'i':
               return this.jjMoveStringLiteralDfa4_0(active0, 1125934266580992L);
            case 'l':
               if ((active0 & 536870912L) != 0L) {
                  return this.jjStartNfaWithStates_0(3, 29, 35);
               }

               return this.jjMoveStringLiteralDfa4_0(active0, 137625600L);
            case 'm':
               if ((active0 & 281474976710656L) != 0L) {
                  return this.jjStartNfaWithStates_0(3, 48, 35);
               }

               return this.jjMoveStringLiteralDfa4_0(active0, 131072L);
            case 'n':
               return this.jjMoveStringLiteralDfa4_0(active0, 2199023255552L);
            case 'o':
               return this.jjMoveStringLiteralDfa4_0(active0, 70368744194048L);
            case 'p':
               if ((active0 & 2251799813685248L) != 0L) {
                  return this.jjStartNfaWithStates_0(3, 51, 35);
               }
               break;
            case 's':
               return this.jjMoveStringLiteralDfa4_0(active0, 68719476992L);
            case 't':
               if ((active0 & 549755813888L) != 0L) {
                  return this.jjStartNfaWithStates_0(3, 39, 35);
               }
               break;
            case 'u':
               return this.jjMoveStringLiteralDfa4_0(active0, 571883485396992L);
            case 'v':
               return this.jjMoveStringLiteralDfa4_0(active0, 140737488355328L);
            case 'y':
               return this.jjMoveStringLiteralDfa4_0(active0, 524288L);
         }

         return this.jjStartNfa_0(2, active0, 0L);
      }
   }

   private int jjMoveStringLiteralDfa4_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(2, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(3, active0, 0L);
            return 4;
         }

         switch (this.curChar) {
            case '_':
               return this.jjMoveStringLiteralDfa5_0(active0, 794624L);
            case 'a':
               return this.jjMoveStringLiteralDfa5_0(active0, 71319552L);
            case 'c':
               if ((active0 & 2199023255552L) != 0L) {
                  return this.jjStartNfaWithStates_0(4, 41, 35);
               }

               return this.jjMoveStringLiteralDfa5_0(active0, 8796093022208L);
            case 'd':
               return this.jjMoveStringLiteralDfa5_0(active0, 4398046511104L);
            case 'i':
               return this.jjMoveStringLiteralDfa5_0(active0, 703687441778688L);
            case 'l':
               return this.jjMoveStringLiteralDfa5_0(active0, 17183014912L);
            case 'm':
               if ((active0 & 137438953472L) != 0L) {
                  return this.jjStartNfaWithStates_0(4, 37, 35);
               }
            case '`':
            case 'b':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
            case 'j':
            case 'k':
            case 'q':
            case 'v':
            default:
               return this.jjStartNfa_0(3, active0, 0L);
            case 'n':
               return this.jjMoveStringLiteralDfa5_0(active0, 35218782225408L);
            case 'o':
               return this.jjMoveStringLiteralDfa5_0(active0, 1125899915362304L);
            case 'p':
               return this.jjMoveStringLiteralDfa5_0(active0, 17592186044416L);
            case 'r':
               return this.jjMoveStringLiteralDfa5_0(active0, 32768L);
            case 's':
               return this.jjMoveStringLiteralDfa5_0(active0, 512L);
            case 't':
               if ((active0 & 256L) != 0L) {
                  return this.jjStartNfaWithStates_0(4, 8, 35);
               } else {
                  if ((active0 & 68719476736L) != 0L) {
                     return this.jjStartNfaWithStates_0(4, 36, 35);
                  }

                  return this.jjMoveStringLiteralDfa5_0(active0, 4096L);
               }
            case 'u':
               return this.jjMoveStringLiteralDfa5_0(active0, 134217728L);
            case 'w':
               return this.jjMoveStringLiteralDfa5_0(active0, 70368744177664L);
         }
      }
   }

   private int jjMoveStringLiteralDfa5_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(3, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(4, active0, 0L);
            return 5;
         }

         switch (this.curChar) {
            case '_':
               return this.jjMoveStringLiteralDfa6_0(active0, 16384L);
            case '`':
            case 'b':
            case 'f':
            case 'h':
            case 'j':
            case 'k':
            case 'm':
            case 'o':
            case 'q':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            default:
               break;
            case 'a':
               return this.jjMoveStringLiteralDfa6_0(active0, 33620992L);
            case 'c':
               return this.jjMoveStringLiteralDfa6_0(active0, 140737488355328L);
            case 'd':
               return this.jjMoveStringLiteralDfa6_0(active0, 35184506437632L);
            case 'e':
               if ((active0 & 17179869184L) != 0L) {
                  return this.jjStartNfaWithStates_0(5, 34, 35);
               }

               return this.jjMoveStringLiteralDfa6_0(active0, 4398046511104L);
            case 'g':
               if ((active0 & 34359738368L) != 0L) {
                  return this.jjStartNfaWithStates_0(5, 35, 35);
               }
               break;
            case 'i':
               return this.jjMoveStringLiteralDfa6_0(active0, 16777216L);
            case 'l':
               return this.jjMoveStringLiteralDfa6_0(active0, 4194304L);
            case 'n':
               return this.jjMoveStringLiteralDfa6_0(active0, 1125899907368960L);
            case 'p':
               return this.jjMoveStringLiteralDfa6_0(active0, 8692224L);
            case 'r':
               return this.jjMoveStringLiteralDfa6_0(active0, 562949953421312L);
            case 's':
               if ((active0 & 70368744177664L) != 0L) {
                  return this.jjStartNfaWithStates_0(5, 46, 35);
               }
               break;
            case 't':
               if ((active0 & 8796093022208L) != 0L) {
                  return this.jjStartNfaWithStates_0(5, 43, 35);
               }

               return this.jjMoveStringLiteralDfa6_0(active0, 17592256299008L);
            case 'y':
               return this.jjMoveStringLiteralDfa6_0(active0, 4096L);
         }

         return this.jjStartNfa_0(4, active0, 0L);
      }
   }

   private int jjMoveStringLiteralDfa6_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(4, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(5, active0, 0L);
            return 6;
         }

         switch (this.curChar) {
            case '_':
               return this.jjMoveStringLiteralDfa7_0(active0, 32768L);
            case '`':
            case 'b':
            case 'd':
            case 'g':
            case 'h':
            case 'j':
            case 'k':
            case 'n':
            case 'o':
            case 'q':
            case 'r':
            default:
               break;
            case 'a':
               return this.jjMoveStringLiteralDfa7_0(active0, 1125899910783488L);
            case 'c':
               return this.jjMoveStringLiteralDfa7_0(active0, 2048L);
            case 'e':
               if ((active0 & 134217728L) != 0L) {
                  return this.jjStartNfaWithStates_0(6, 27, 35);
               }

               if ((active0 & 140737488355328L) != 0L) {
                  return this.jjStartNfaWithStates_0(6, 47, 35);
               }

               return this.jjMoveStringLiteralDfa7_0(active0, 562949953421312L);
            case 'f':
               if ((active0 & 4398046511104L) != 0L) {
                  return this.jjStartNfaWithStates_0(6, 42, 35);
               }
               break;
            case 'i':
               return this.jjMoveStringLiteralDfa7_0(active0, 17592186044416L);
            case 'l':
               if ((active0 & 4194304L) != 0L) {
                  return this.jjStartNfaWithStates_0(6, 22, 35);
               }

               return this.jjMoveStringLiteralDfa7_0(active0, 16777216L);
            case 'm':
               return this.jjMoveStringLiteralDfa7_0(active0, 33620992L);
            case 'p':
               return this.jjMoveStringLiteralDfa7_0(active0, 20480L);
            case 's':
               if ((active0 & 35184372088832L) != 0L) {
                  return this.jjStartNfaWithStates_0(6, 45, 35);
               }
               break;
            case 't':
               return this.jjMoveStringLiteralDfa7_0(active0, 75497472L);
            case 'u':
               return this.jjMoveStringLiteralDfa7_0(active0, 131072L);
         }

         return this.jjStartNfa_0(5, active0, 0L);
      }
   }

   private int jjMoveStringLiteralDfa7_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(5, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(6, active0, 0L);
            return 7;
         }

         switch (this.curChar) {
            case 'c':
               return this.jjMoveStringLiteralDfa8_0(active0, 270848L);
            case 'd':
               if ((active0 & 562949953421312L) != 0L) {
                  return this.jjStartNfaWithStates_0(7, 49, 35);
               }
            case 'f':
            case 'g':
            case 'h':
            case 'j':
            case 'k':
            case 'p':
            case 'q':
            default:
               return this.jjStartNfa_0(6, active0, 0L);
            case 'e':
               if ((active0 & 4096L) != 0L) {
                  return this.jjStartNfaWithStates_0(7, 12, 35);
               }

               return this.jjMoveStringLiteralDfa8_0(active0, 33620992L);
            case 'i':
               return this.jjMoveStringLiteralDfa8_0(active0, 8388608L);
            case 'l':
               if ((active0 & 1125899906842624L) != 0L) {
                  return this.jjStartNfaWithStates_0(7, 50, 35);
               }

               return this.jjMoveStringLiteralDfa8_0(active0, 20056064L);
            case 'm':
               return this.jjMoveStringLiteralDfa8_0(active0, 524288L);
            case 'n':
               return this.jjMoveStringLiteralDfa8_0(active0, 32768L);
            case 'o':
               return this.jjMoveStringLiteralDfa8_0(active0, 17592186044416L);
            case 'r':
               return this.jjMoveStringLiteralDfa8_0(active0, 67125248L);
         }
      }
   }

   private int jjMoveStringLiteralDfa8_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(6, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(7, active0, 0L);
            return 8;
         }

         switch (this.curChar) {
            case 'a':
               return this.jjMoveStringLiteralDfa9_0(active0, 16809984L);
            case 'e':
               if ((active0 & 512L) != 0L) {
                  return this.jjStartNfaWithStates_0(8, 9, 35);
               } else {
                  if ((active0 & 131072L) != 0L) {
                     return this.jjStartNfaWithStates_0(8, 17, 35);
                  }

                  return this.jjMoveStringLiteralDfa9_0(active0, 540672L);
               }
            case 'k':
               return this.jjMoveStringLiteralDfa9_0(active0, 3416064L);
            case 'n':
               if ((active0 & 17592186044416L) != 0L) {
                  return this.jjStartNfaWithStates_0(8, 44, 35);
               }
            case 'b':
            case 'c':
            case 'd':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'l':
            case 'm':
            case 'p':
            case 'q':
            case 'r':
            case 't':
            default:
               return this.jjStartNfa_0(7, active0, 0L);
            case 'o':
               return this.jjMoveStringLiteralDfa9_0(active0, 8388608L);
            case 's':
               if ((active0 & 67108864L) != 0L) {
                  return this.jjStartNfaWithStates_0(8, 26, 35);
               }

               return this.jjMoveStringLiteralDfa9_0(active0, 33620992L);
            case 'u':
               return this.jjMoveStringLiteralDfa9_0(active0, 2048L);
         }
      }
   }

   private int jjMoveStringLiteralDfa9_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(7, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(8, active0, 0L);
            return 9;
         }

         switch (this.curChar) {
            case '_':
               return this.jjMoveStringLiteralDfa10_0(active0, 3145728L);
            case '`':
            case 'c':
            case 'e':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'o':
            case 'q':
            case 'r':
            default:
               return this.jjStartNfa_0(8, active0, 0L);
            case 'a':
               return this.jjMoveStringLiteralDfa10_0(active0, 270336L);
            case 'b':
               return this.jjMoveStringLiteralDfa10_0(active0, 16777216L);
            case 'd':
               return this.jjMoveStringLiteralDfa10_0(active0, 2048L);
            case 'f':
               return this.jjMoveStringLiteralDfa10_0(active0, 16384L);
            case 'm':
               return this.jjMoveStringLiteralDfa10_0(active0, 32768L);
            case 'n':
               return this.jjMoveStringLiteralDfa10_0(active0, 8388608L);
            case 'p':
               return this.jjMoveStringLiteralDfa10_0(active0, 33620992L);
            case 's':
               return this.jjMoveStringLiteralDfa10_0(active0, 524288L);
         }
      }
   }

   private int jjMoveStringLiteralDfa10_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(8, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(9, active0, 0L);
            return 10;
         }

         switch (this.curChar) {
            case 'a':
               return this.jjMoveStringLiteralDfa11_0(active0, 42009600L);
            case 'b':
            case 'd':
            case 'f':
            case 'h':
            case 'j':
            case 'k':
            case 'm':
            case 'n':
            case 'o':
            default:
               return this.jjStartNfa_0(9, active0, 0L);
            case 'c':
               return this.jjMoveStringLiteralDfa11_0(active0, 1048576L);
            case 'e':
               if ((active0 & 2048L) != 0L) {
                  return this.jjStartNfaWithStates_0(10, 11, 35);
               }

               return this.jjMoveStringLiteralDfa11_0(active0, 32768L);
            case 'g':
               return this.jjMoveStringLiteralDfa11_0(active0, 270336L);
            case 'i':
               return this.jjMoveStringLiteralDfa11_0(active0, 16384L);
            case 'l':
               return this.jjMoveStringLiteralDfa11_0(active0, 16777216L);
            case 'p':
               return this.jjMoveStringLiteralDfa11_0(active0, 2621440L);
         }
      }
   }

   private int jjMoveStringLiteralDfa11_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(9, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(10, active0, 0L);
            return 11;
         }

         switch (this.curChar) {
            case 'a':
               return this.jjMoveStringLiteralDfa12_0(active0, 1572864L);
            case 'b':
            case 'd':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 't':
            case 'u':
            case 'v':
            case 'w':
            default:
               break;
            case 'c':
               return this.jjMoveStringLiteralDfa12_0(active0, 33620992L);
            case 'e':
               if ((active0 & 8192L) != 0L) {
                  return this.jjStartNfaWithStates_0(11, 13, 35);
               }

               if ((active0 & 262144L) != 0L) {
                  return this.jjStartNfaWithStates_0(11, 18, 35);
               }

               if ((active0 & 16777216L) != 0L) {
                  return this.jjStartNfaWithStates_0(11, 24, 35);
               }
               break;
            case 'l':
               if ((active0 & 8388608L) != 0L) {
                  return this.jjStartNfaWithStates_0(11, 23, 35);
               }
               break;
            case 'r':
               return this.jjMoveStringLiteralDfa12_0(active0, 2097152L);
            case 's':
               return this.jjMoveStringLiteralDfa12_0(active0, 32768L);
            case 'x':
               if ((active0 & 16384L) != 0L) {
                  return this.jjStartNfaWithStates_0(11, 14, 35);
               }
         }

         return this.jjStartNfa_0(10, active0, 0L);
      }
   }

   private int jjMoveStringLiteralDfa12_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(10, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(11, active0, 0L);
            return 12;
         }

         switch (this.curChar) {
            case 'c':
               return this.jjMoveStringLiteralDfa13_0(active0, 524288L);
            case 'e':
               if ((active0 & 1024L) != 0L) {
                  return this.jjStartNfaWithStates_0(12, 10, 35);
               } else if ((active0 & 65536L) != 0L) {
                  return this.jjStartNfaWithStates_0(12, 16, 35);
               } else {
                  if ((active0 & 33554432L) != 0L) {
                     return this.jjStartNfaWithStates_0(12, 25, 35);
                  }

                  return this.jjMoveStringLiteralDfa13_0(active0, 2097152L);
               }
            case 'p':
               return this.jjMoveStringLiteralDfa13_0(active0, 32768L);
            case 't':
               return this.jjMoveStringLiteralDfa13_0(active0, 1048576L);
            default:
               return this.jjStartNfa_0(11, active0, 0L);
         }
      }
   }

   private int jjMoveStringLiteralDfa13_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(11, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(12, active0, 0L);
            return 13;
         }

         switch (this.curChar) {
            case 'a':
               return this.jjMoveStringLiteralDfa14_0(active0, 32768L);
            case 'e':
               if ((active0 & 524288L) != 0L) {
                  return this.jjStartNfaWithStates_0(13, 19, 35);
               }

               return this.jjMoveStringLiteralDfa14_0(active0, 1048576L);
            case 'f':
               return this.jjMoveStringLiteralDfa14_0(active0, 2097152L);
            default:
               return this.jjStartNfa_0(12, active0, 0L);
         }
      }
   }

   private int jjMoveStringLiteralDfa14_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(12, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(13, active0, 0L);
            return 14;
         }

         switch (this.curChar) {
            case 'c':
               return this.jjMoveStringLiteralDfa15_0(active0, 32768L);
            case 'g':
               return this.jjMoveStringLiteralDfa15_0(active0, 1048576L);
            case 'i':
               return this.jjMoveStringLiteralDfa15_0(active0, 2097152L);
            default:
               return this.jjStartNfa_0(13, active0, 0L);
         }
      }
   }

   private int jjMoveStringLiteralDfa15_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(13, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(14, active0, 0L);
            return 15;
         }

         switch (this.curChar) {
            case 'e':
               if ((active0 & 32768L) != 0L) {
                  return this.jjStartNfaWithStates_0(15, 15, 35);
               }
               break;
            case 'o':
               return this.jjMoveStringLiteralDfa16_0(active0, 1048576L);
            case 'x':
               if ((active0 & 2097152L) != 0L) {
                  return this.jjStartNfaWithStates_0(15, 21, 35);
               }
         }

         return this.jjStartNfa_0(14, active0, 0L);
      }
   }

   private int jjMoveStringLiteralDfa16_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(14, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(15, active0, 0L);
            return 16;
         }

         switch (this.curChar) {
            case 'r':
               return this.jjMoveStringLiteralDfa17_0(active0, 1048576L);
            default:
               return this.jjStartNfa_0(15, active0, 0L);
         }
      }
   }

   private int jjMoveStringLiteralDfa17_0(long old0, long active0) {
      if ((active0 = active0 & old0) == 0L) {
         return this.jjStartNfa_0(15, old0, 0L);
      } else {
         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var6) {
            this.jjStopStringLiteralDfa_0(16, active0, 0L);
            return 17;
         }

         switch (this.curChar) {
            case 'y':
               if ((active0 & 1048576L) != 0L) {
                  return this.jjStartNfaWithStates_0(17, 20, 35);
               }
            default:
               return this.jjStartNfa_0(16, active0, 0L);
         }
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
      this.jjnewStateCnt = 35;
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
                     if ((287948901175001088L & l) != 0L) {
                        if (kind > 54) {
                           kind = 54;
                        }

                        this.jjCheckNAdd(7);
                     } else if ((43980465111040L & l) != 0L) {
                        this.jjCheckNAddStates(0, 2);
                     } else if (this.curChar == '/') {
                        this.jjAddStates(3, 4);
                     } else if (this.curChar == '\'') {
                        this.jjCheckNAddTwoStates(12, 13);
                     } else if (this.curChar == '"') {
                        this.jjCheckNAddTwoStates(9, 10);
                     } else if (this.curChar == '#') {
                        this.jjCheckNAddStates(5, 7);
                     }

                     if ((287948901175001088L & l) != 0L) {
                        if (kind > 52) {
                           kind = 52;
                        }

                        this.jjCheckNAdd(5);
                     } else if (this.curChar == '-') {
                        if (kind > 58) {
                           kind = 58;
                        }

                        this.jjCheckNAdd(15);
                     }
                     break;
                  case 1:
                     if ((-1025L & l) != 0L) {
                        this.jjCheckNAddStates(5, 7);
                     }
                     break;
                  case 2:
                     if ((9216L & l) != 0L && kind > 5) {
                        kind = 5;
                     }
                     break;
                  case 3:
                     if (this.curChar == '\n' && kind > 5) {
                        kind = 5;
                     }
                     break;
                  case 4:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 3;
                     }
                     break;
                  case 5:
                     if ((287948901175001088L & l) != 0L) {
                        if (kind > 52) {
                           kind = 52;
                        }

                        this.jjCheckNAdd(5);
                     }
                     break;
                  case 6:
                     if ((287948901175001088L & l) != 0L) {
                        if (kind > 54) {
                           kind = 54;
                        }

                        this.jjCheckNAdd(7);
                     }
                     break;
                  case 7:
                     if ((288019269919178752L & l) != 0L) {
                        if (kind > 54) {
                           kind = 54;
                        }

                        this.jjCheckNAdd(7);
                     }
                     break;
                  case 8:
                     if (this.curChar == '"') {
                        this.jjCheckNAddTwoStates(9, 10);
                     }
                     break;
                  case 9:
                     if ((-17179869185L & l) != 0L) {
                        this.jjCheckNAddTwoStates(9, 10);
                     }
                     break;
                  case 10:
                     if (this.curChar == '"' && kind > 57) {
                        kind = 57;
                     }
                     break;
                  case 11:
                     if (this.curChar == '\'') {
                        this.jjCheckNAddTwoStates(12, 13);
                     }
                     break;
                  case 12:
                     if ((-549755813889L & l) != 0L) {
                        this.jjCheckNAddTwoStates(12, 13);
                     }
                     break;
                  case 13:
                     if (this.curChar == '\'' && kind > 57) {
                        kind = 57;
                     }
                     break;
                  case 14:
                     if (this.curChar == '-') {
                        if (kind > 58) {
                           kind = 58;
                        }

                        this.jjCheckNAdd(15);
                     }
                     break;
                  case 15:
                     if ((288054454291267584L & l) != 0L) {
                        if (kind > 58) {
                           kind = 58;
                        }

                        this.jjCheckNAdd(15);
                     }
                     break;
                  case 16:
                     if (this.curChar == '/') {
                        this.jjAddStates(3, 4);
                     }
                     break;
                  case 17:
                     if (this.curChar == '/') {
                        this.jjCheckNAddStates(8, 10);
                     }
                     break;
                  case 18:
                     if ((-9217L & l) != 0L) {
                        this.jjCheckNAddStates(8, 10);
                     }
                     break;
                  case 19:
                     if ((9216L & l) != 0L && kind > 6) {
                        kind = 6;
                     }
                     break;
                  case 20:
                     if (this.curChar == '\n' && kind > 6) {
                        kind = 6;
                     }
                     break;
                  case 21:
                     if (this.curChar == '\r') {
                        this.jjstateSet[this.jjnewStateCnt++] = 20;
                     }
                     break;
                  case 22:
                     if (this.curChar == '*') {
                        this.jjCheckNAddTwoStates(23, 24);
                     }
                     break;
                  case 23:
                     if ((-4398046511105L & l) != 0L) {
                        this.jjCheckNAddTwoStates(23, 24);
                     }
                     break;
                  case 24:
                     if (this.curChar == '*') {
                        this.jjAddStates(11, 12);
                     }
                     break;
                  case 25:
                     if ((-140737488355329L & l) != 0L) {
                        this.jjCheckNAddTwoStates(26, 24);
                     }
                     break;
                  case 26:
                     if ((-4398046511105L & l) != 0L) {
                        this.jjCheckNAddTwoStates(26, 24);
                     }
                     break;
                  case 27:
                     if (this.curChar == '/' && kind > 7) {
                        kind = 7;
                     }
                     break;
                  case 28:
                     if ((43980465111040L & l) != 0L) {
                        this.jjCheckNAddStates(0, 2);
                     }
                     break;
                  case 29:
                     if ((287948901175001088L & l) != 0L) {
                        this.jjCheckNAddTwoStates(29, 30);
                     }
                     break;
                  case 30:
                     if (this.curChar == '.') {
                        this.jjCheckNAdd(31);
                     }
                     break;
                  case 31:
                     if ((287948901175001088L & l) != 0L) {
                        if (kind > 53) {
                           kind = 53;
                        }

                        this.jjCheckNAddTwoStates(31, 32);
                     }
                  case 32:
                  default:
                     break;
                  case 33:
                     if ((43980465111040L & l) != 0L) {
                        this.jjCheckNAdd(34);
                     }
                     break;
                  case 34:
                     if ((287948901175001088L & l) != 0L) {
                        if (kind > 53) {
                           kind = 53;
                        }

                        this.jjCheckNAdd(34);
                     }
                     break;
                  case 35:
                     if ((288054454291267584L & l) != 0L) {
                        if (kind > 58) {
                           kind = 58;
                        }

                        this.jjCheckNAdd(15);
                     }

                     if ((288019269919178752L & l) != 0L) {
                        if (kind > 54) {
                           kind = 54;
                        }

                        this.jjCheckNAdd(7);
                     }
               }
            } while(i != startsAt);
         } else if (this.curChar < 128) {
            long l = 1L << (this.curChar & 63);

            do {
               --i;
               switch (this.jjstateSet[i]) {
                  case 0:
                     if ((576460745995190270L & l) != 0L) {
                        if (kind > 54) {
                           kind = 54;
                        }

                        this.jjCheckNAdd(7);
                     }

                     if ((576460743847706622L & l) != 0L) {
                        if (kind > 58) {
                           kind = 58;
                        }

                        this.jjCheckNAdd(15);
                     }
                     break;
                  case 1:
                     this.jjAddStates(5, 7);
                  case 2:
                  case 3:
                  case 4:
                  case 5:
                  case 8:
                  case 10:
                  case 11:
                  case 13:
                  case 16:
                  case 17:
                  case 19:
                  case 20:
                  case 21:
                  case 22:
                  case 24:
                  case 27:
                  case 28:
                  case 29:
                  case 30:
                  case 31:
                  case 33:
                  case 34:
                  default:
                     break;
                  case 6:
                     if ((576460745995190270L & l) != 0L) {
                        if (kind > 54) {
                           kind = 54;
                        }

                        this.jjCheckNAdd(7);
                     }
                     break;
                  case 7:
                     if ((576460745995190270L & l) != 0L) {
                        if (kind > 54) {
                           kind = 54;
                        }

                        this.jjCheckNAdd(7);
                     }
                     break;
                  case 9:
                     this.jjAddStates(13, 14);
                     break;
                  case 12:
                     this.jjAddStates(15, 16);
                     break;
                  case 14:
                     if ((576460743847706622L & l) != 0L) {
                        if (kind > 58) {
                           kind = 58;
                        }

                        this.jjCheckNAdd(15);
                     }
                     break;
                  case 15:
                     if ((576460745995190270L & l) != 0L) {
                        if (kind > 58) {
                           kind = 58;
                        }

                        this.jjCheckNAdd(15);
                     }
                     break;
                  case 18:
                     this.jjAddStates(8, 10);
                     break;
                  case 23:
                     this.jjCheckNAddTwoStates(23, 24);
                     break;
                  case 25:
                  case 26:
                     this.jjCheckNAddTwoStates(26, 24);
                     break;
                  case 32:
                     if ((137438953504L & l) != 0L) {
                        this.jjAddStates(17, 18);
                     }
                     break;
                  case 35:
                     if ((576460745995190270L & l) != 0L) {
                        if (kind > 58) {
                           kind = 58;
                        }

                        this.jjCheckNAdd(15);
                     }

                     if ((576460745995190270L & l) != 0L) {
                        if (kind > 54) {
                           kind = 54;
                        }

                        this.jjCheckNAdd(7);
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
                        this.jjAddStates(5, 7);
                     }
                     break;
                  case 9:
                     if ((jjbitVec0[i2] & l2) != 0L) {
                        this.jjAddStates(13, 14);
                     }
                     break;
                  case 12:
                     if ((jjbitVec0[i2] & l2) != 0L) {
                        this.jjAddStates(15, 16);
                     }
                     break;
                  case 18:
                     if ((jjbitVec0[i2] & l2) != 0L) {
                        this.jjAddStates(8, 10);
                     }
                     break;
                  case 23:
                     if ((jjbitVec0[i2] & l2) != 0L) {
                        this.jjCheckNAddTwoStates(23, 24);
                     }
                     break;
                  case 25:
                  case 26:
                     if ((jjbitVec0[i2] & l2) != 0L) {
                        this.jjCheckNAddTwoStates(26, 24);
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
         if ((i = this.jjnewStateCnt) == (startsAt = 35 - (this.jjnewStateCnt = startsAt))) {
            return curPos;
         }

         try {
            this.curChar = this.input_stream.readChar();
         } catch (IOException var9) {
            return curPos;
         }
      }
   }

   public thrift_grammarTokenManager(SimpleCharStream stream) {
      this.debugStream = System.out;
      this.jjrounds = new int[35];
      this.jjstateSet = new int[70];
      this.curLexState = 0;
      this.defaultLexState = 0;
      this.input_stream = stream;
   }

   public thrift_grammarTokenManager(SimpleCharStream stream, int lexState) {
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

      for(int i = 35; i-- > 0; this.jjrounds[i] = Integer.MIN_VALUE) {
      }

   }

   public void ReInit(SimpleCharStream stream, int lexState) {
      this.ReInit(stream);
      this.SwitchTo(lexState);
   }

   public void SwitchTo(int lexState) {
      if (lexState < 1 && lexState >= 0) {
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
      Token t = Token.newToken(this.jjmatchedKind);
      t.kind = this.jjmatchedKind;
      t.image = curTokenImage;
      t.beginLine = beginLine;
      t.endLine = endLine;
      t.beginColumn = beginColumn;
      t.endColumn = endColumn;
      return t;
   }

   public Token getNextToken() {
      int curPos = 0;

      while(true) {
         try {
            this.curChar = this.input_stream.BeginToken();
         } catch (IOException var8) {
            this.jjmatchedKind = 0;
            Token matchedToken = this.jjFillToken();
            return matchedToken;
         }

         try {
            this.input_stream.backup(0);

            while(this.curChar <= ' ' && (4294977024L & 1L << this.curChar) != 0L) {
               this.curChar = this.input_stream.BeginToken();
            }
         } catch (IOException var10) {
            continue;
         }

         this.jjmatchedKind = Integer.MAX_VALUE;
         this.jjmatchedPos = 0;
         curPos = this.jjMoveStringLiteralDfa0_0();
         if (this.jjmatchedKind == Integer.MAX_VALUE) {
            int error_line = this.input_stream.getEndLine();
            int error_column = this.input_stream.getEndColumn();
            String error_after = null;
            boolean EOFSeen = false;

            try {
               this.input_stream.readChar();
               this.input_stream.backup(1);
            } catch (IOException var9) {
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

         if (this.jjmatchedPos + 1 < curPos) {
            this.input_stream.backup(curPos - this.jjmatchedPos - 1);
         }

         if ((jjtoToken[this.jjmatchedKind >> 6] & 1L << (this.jjmatchedKind & 63)) != 0L) {
            Token matchedToken = this.jjFillToken();
            return matchedToken;
         }
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

   private void jjCheckNAddStates(int start, int end) {
      do {
         this.jjCheckNAdd(jjnextStates[start]);
      } while(start++ != end);

   }
}
