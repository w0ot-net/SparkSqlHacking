package com.univocity.parsers.tsv;

import com.univocity.parsers.common.Format;
import java.util.TreeMap;

public class TsvFormat extends Format {
   private char escapeChar = '\\';
   private char escapedTabChar = 't';

   public void setEscapeChar(char escapeChar) {
      this.escapeChar = escapeChar;
   }

   public char getEscapeChar() {
      return this.escapeChar;
   }

   public char getEscapedTabChar() {
      return this.escapedTabChar;
   }

   public void setEscapedTabChar(char escapedTabChar) {
      this.escapedTabChar = escapedTabChar;
   }

   public boolean isEscapeChar(char ch) {
      return this.escapeChar == ch;
   }

   protected TreeMap getConfiguration() {
      TreeMap<String, Object> out = new TreeMap();
      out.put("Escape character", this.escapeChar);
      return out;
   }

   public final TsvFormat clone() {
      return (TsvFormat)super.clone();
   }
}
