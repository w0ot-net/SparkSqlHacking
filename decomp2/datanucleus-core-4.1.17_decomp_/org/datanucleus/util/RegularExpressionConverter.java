package org.datanucleus.util;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class RegularExpressionConverter {
   private final char zeroOrMoreChar;
   private final char anyChar;
   private final char escapeChar;

   public RegularExpressionConverter(char zeroOrMoreChar, char anyChar, char escapeChar) {
      this.zeroOrMoreChar = zeroOrMoreChar;
      this.anyChar = anyChar;
      this.escapeChar = escapeChar;
   }

   public String convert(String input) {
      StringBuilder lit = new StringBuilder();

      char c;
      for(CharacterIterator ci = new StringCharacterIterator(input); (c = ci.current()) != '\uffff'; ci.next()) {
         if (c == '\\') {
            char ch = ci.next();
            if (ch == '\uffff') {
               lit.append(this.escapeChar + "\\");
            } else if (ch == '.') {
               lit.append(".");
            } else if (ch == '\\') {
               lit.append(this.escapeChar + "\\" + this.escapeChar + ch);
            } else {
               lit.append(this.escapeChar + "\\" + ch);
            }
         } else if (c == '.') {
            int savedIdx = ci.getIndex();
            if (ci.next() == '*') {
               lit.append(this.zeroOrMoreChar);
            } else {
               ci.setIndex(savedIdx);
               lit.append(this.anyChar);
            }
         } else if (c == this.anyChar) {
            lit.append("" + this.escapeChar + this.anyChar);
         } else if (c == this.zeroOrMoreChar) {
            lit.append("" + this.escapeChar + this.zeroOrMoreChar);
         } else {
            lit.append(c);
         }
      }

      return lit.toString();
   }
}
