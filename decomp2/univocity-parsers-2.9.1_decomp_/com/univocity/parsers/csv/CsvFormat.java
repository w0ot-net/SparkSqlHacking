package com.univocity.parsers.csv;

import com.univocity.parsers.common.Format;
import java.util.TreeMap;

public class CsvFormat extends Format {
   private char quote = '"';
   private char quoteEscape = '"';
   private String delimiter = ",";
   private Character charToEscapeQuoteEscaping = null;

   public char getQuote() {
      return this.quote;
   }

   public void setQuote(char quote) {
      this.quote = quote;
   }

   public boolean isQuote(char ch) {
      return this.quote == ch;
   }

   public char getQuoteEscape() {
      return this.quoteEscape;
   }

   public void setQuoteEscape(char quoteEscape) {
      this.quoteEscape = quoteEscape;
   }

   public boolean isQuoteEscape(char ch) {
      return this.quoteEscape == ch;
   }

   public char getDelimiter() {
      if (this.delimiter.length() > 1) {
         throw new UnsupportedOperationException("Delimiter '" + this.delimiter + "' has more than one character. Use method getDelimiterString()");
      } else {
         return this.delimiter.charAt(0);
      }
   }

   public String getDelimiterString() {
      return this.delimiter;
   }

   public void setDelimiter(char delimiter) {
      this.delimiter = String.valueOf(delimiter);
   }

   public void setDelimiter(String delimiter) {
      if (delimiter == null) {
         throw new IllegalArgumentException("Delimiter cannot be null");
      } else if (delimiter.isEmpty()) {
         throw new IllegalArgumentException("Delimiter cannot be empty");
      } else {
         this.delimiter = delimiter;
      }
   }

   public boolean isDelimiter(char ch) {
      if (this.delimiter.length() > 1) {
         throw new UnsupportedOperationException("Delimiter '" + this.delimiter + "' has more than one character. Use method isDelimiter(String)");
      } else {
         return this.delimiter.charAt(0) == ch;
      }
   }

   public boolean isDelimiter(String sequence) {
      return this.delimiter.equals(sequence);
   }

   public final char getCharToEscapeQuoteEscaping() {
      if (this.charToEscapeQuoteEscaping == null) {
         return this.quote == this.quoteEscape ? '\u0000' : this.quoteEscape;
      } else {
         return this.charToEscapeQuoteEscaping;
      }
   }

   public final void setCharToEscapeQuoteEscaping(char charToEscapeQuoteEscaping) {
      this.charToEscapeQuoteEscaping = charToEscapeQuoteEscaping;
   }

   public final boolean isCharToEscapeQuoteEscaping(char ch) {
      char current = this.getCharToEscapeQuoteEscaping();
      return current != 0 && current == ch;
   }

   protected TreeMap getConfiguration() {
      TreeMap<String, Object> out = new TreeMap();
      out.put("Quote character", this.quote);
      out.put("Quote escape character", this.quoteEscape);
      out.put("Quote escape escape character", this.charToEscapeQuoteEscaping);
      out.put("Field delimiter", this.delimiter);
      return out;
   }

   public final CsvFormat clone() {
      return (CsvFormat)super.clone();
   }
}
