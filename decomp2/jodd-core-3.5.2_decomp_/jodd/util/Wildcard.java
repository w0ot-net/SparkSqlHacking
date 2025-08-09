package jodd.util;

public class Wildcard {
   protected static final String PATH_MATCH = "**";
   protected static final String PATH_SEPARATORS = "/\\";

   public static boolean match(String string, String pattern) {
      return match(string, pattern, 0, 0);
   }

   public static boolean equalsOrMatch(String string, String pattern) {
      return string.equals(pattern) ? true : match(string, pattern, 0, 0);
   }

   private static boolean match(String string, String pattern, int sNdx, int pNdx) {
      int pLen = pattern.length();
      if (pLen == 1 && pattern.charAt(0) == '*') {
         return true;
      } else {
         int sLen = string.length();
         boolean nextIsNotWildcard = false;

         while(sNdx < sLen) {
            if (pNdx >= pLen) {
               return false;
            }

            char p = pattern.charAt(pNdx);
            if (!nextIsNotWildcard) {
               if (p == '\\') {
                  ++pNdx;
                  nextIsNotWildcard = true;
                  continue;
               }

               if (p == '?') {
                  ++sNdx;
                  ++pNdx;
                  continue;
               }

               if (p == '*') {
                  char pNext = 0;
                  if (pNdx + 1 < pLen) {
                     pNext = pattern.charAt(pNdx + 1);
                  }

                  if (pNext != '*') {
                     ++pNdx;

                     for(int i = string.length(); i >= sNdx; --i) {
                        if (match(string, pattern, i, pNdx)) {
                           return true;
                        }
                     }

                     return false;
                  }

                  ++pNdx;
                  continue;
               }
            } else {
               nextIsNotWildcard = false;
            }

            if (p != string.charAt(sNdx)) {
               return false;
            }

            ++sNdx;
            ++pNdx;
         }

         while(pNdx < pLen && pattern.charAt(pNdx) == '*') {
            ++pNdx;
         }

         return pNdx >= pLen;
      }
   }

   public static int matchOne(String src, String[] patterns) {
      for(int i = 0; i < patterns.length; ++i) {
         if (match(src, patterns[i])) {
            return i;
         }
      }

      return -1;
   }

   public static int matchPathOne(String path, String[] patterns) {
      for(int i = 0; i < patterns.length; ++i) {
         if (matchPath(path, patterns[i])) {
            return i;
         }
      }

      return -1;
   }

   public static boolean matchPath(String path, String pattern) {
      String[] pathElements = StringUtil.splitc(path, "/\\");
      String[] patternElements = StringUtil.splitc(pattern, "/\\");
      return matchTokens(pathElements, patternElements);
   }

   protected static boolean matchTokens(String[] tokens, String[] patterns) {
      int patNdxStart = 0;
      int patNdxEnd = patterns.length - 1;
      int tokNdxStart = 0;

      int tokNdxEnd;
      for(tokNdxEnd = tokens.length - 1; patNdxStart <= patNdxEnd && tokNdxStart <= tokNdxEnd; ++tokNdxStart) {
         String patDir = patterns[patNdxStart];
         if (patDir.equals("**")) {
            break;
         }

         if (!match(tokens[tokNdxStart], patDir)) {
            return false;
         }

         ++patNdxStart;
      }

      if (tokNdxStart > tokNdxEnd) {
         for(int i = patNdxStart; i <= patNdxEnd; ++i) {
            if (!patterns[i].equals("**")) {
               return false;
            }
         }

         return true;
      } else if (patNdxStart > patNdxEnd) {
         return false;
      } else {
         while(patNdxStart <= patNdxEnd && tokNdxStart <= tokNdxEnd) {
            String patDir = patterns[patNdxEnd];
            if (patDir.equals("**")) {
               break;
            }

            if (!match(tokens[tokNdxEnd], patDir)) {
               return false;
            }

            --patNdxEnd;
            --tokNdxEnd;
         }

         if (tokNdxStart > tokNdxEnd) {
            for(int i = patNdxStart; i <= patNdxEnd; ++i) {
               if (!patterns[i].equals("**")) {
                  return false;
               }
            }

            return true;
         } else {
            while(patNdxStart != patNdxEnd && tokNdxStart <= tokNdxEnd) {
               int patIdxTmp = -1;

               for(int i = patNdxStart + 1; i <= patNdxEnd; ++i) {
                  if (patterns[i].equals("**")) {
                     patIdxTmp = i;
                     break;
                  }
               }

               if (patIdxTmp == patNdxStart + 1) {
                  ++patNdxStart;
               } else {
                  int patLength = patIdxTmp - patNdxStart - 1;
                  int strLength = tokNdxEnd - tokNdxStart + 1;
                  int ndx = -1;
                  int i = 0;

                  label106:
                  while(i <= strLength - patLength) {
                     for(int j = 0; j < patLength; ++j) {
                        String subPat = patterns[patNdxStart + j + 1];
                        String subStr = tokens[tokNdxStart + i + j];
                        if (!match(subStr, subPat)) {
                           ++i;
                           continue label106;
                        }
                     }

                     ndx = tokNdxStart + i;
                     break;
                  }

                  if (ndx == -1) {
                     return false;
                  }

                  patNdxStart = patIdxTmp;
                  tokNdxStart = ndx + patLength;
               }
            }

            for(int i = patNdxStart; i <= patNdxEnd; ++i) {
               if (!patterns[i].equals("**")) {
                  return false;
               }
            }

            return true;
         }
      }
   }
}
