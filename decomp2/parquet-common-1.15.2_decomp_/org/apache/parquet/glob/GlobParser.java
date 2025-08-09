package org.apache.parquet.glob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class GlobParser {
   private GlobParser() {
   }

   public static GlobNode.GlobNodeSequence parse(String pattern) {
      if (!pattern.isEmpty() && !pattern.equals("{}")) {
         List<GlobNode> children = new ArrayList();
         int unmatchedBraces = 0;
         int firstBrace = 0;
         int anchor = 0;

         for(int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            switch (c) {
               case ',':
                  if (unmatchedBraces == 0) {
                     throw new GlobParseException("Unexpected comma outside of a {} group:\n" + annotateMessage(pattern, i));
                  }
                  break;
               case '{':
                  if (unmatchedBraces == 0) {
                     firstBrace = i;
                  }

                  ++unmatchedBraces;
                  break;
               case '}':
                  --unmatchedBraces;
                  if (unmatchedBraces < 0) {
                     throw new GlobParseException("Unexpected closing }:\n" + annotateMessage(pattern, i));
                  }

                  if (unmatchedBraces == 0) {
                     if (anchor != firstBrace) {
                        children.add(new GlobNode.Atom(pattern.substring(anchor, firstBrace)));
                     }

                     children.add(parseOneOf(pattern.substring(firstBrace + 1, i)));
                     anchor = i + 1;
                  }
            }
         }

         if (unmatchedBraces > 0) {
            throw new GlobParseException("Not enough close braces in: " + pattern);
         } else {
            if (anchor != pattern.length()) {
               children.add(new GlobNode.Atom(pattern.substring(anchor, pattern.length())));
            }

            return new GlobNode.GlobNodeSequence(children);
         }
      } else {
         return new GlobNode.GlobNodeSequence(Collections.singletonList(new GlobNode.Atom("")));
      }
   }

   private static GlobNode.OneOf parseOneOf(String pattern) {
      List<GlobNode> children = new ArrayList();
      int unmatchedBraces = 0;
      int anchor = 0;

      for(int i = 0; i < pattern.length(); ++i) {
         char c = pattern.charAt(i);
         switch (c) {
            case ',':
               if (unmatchedBraces == 0) {
                  children.add(parse(pattern.substring(anchor, i)));
                  anchor = i + 1;
               }
               break;
            case '{':
               ++unmatchedBraces;
               break;
            case '}':
               --unmatchedBraces;
               if (unmatchedBraces < 0) {
                  throw new GlobParseException("Unexpected closing }:\n" + annotateMessage(pattern, i));
               }
         }
      }

      if (unmatchedBraces > 0) {
         throw new GlobParseException("Not enough close braces in: " + pattern);
      } else {
         if (anchor != pattern.length()) {
            children.add(parse(pattern.substring(anchor, pattern.length())));
         }

         if (pattern.length() > 0 && pattern.charAt(pattern.length() - 1) == ',') {
            children.add(parse(""));
         }

         return new GlobNode.OneOf(children);
      }
   }

   private static String annotateMessage(String message, int pos) {
      StringBuilder sb = new StringBuilder(message);
      sb.append('\n');

      for(int i = 0; i < pos; ++i) {
         sb.append('-');
      }

      sb.append('^');
      return sb.toString();
   }

   public static class GlobParseException extends RuntimeException {
      public GlobParseException() {
      }

      public GlobParseException(String message) {
         super(message);
      }

      public GlobParseException(String message, Throwable cause) {
         super(message, cause);
      }
   }
}
