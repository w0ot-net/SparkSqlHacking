package com.univocity.parsers.common;

import java.util.Map;
import java.util.TreeMap;

public abstract class Format implements Cloneable {
   private static final String systemLineSeparatorString;
   private static final char[] systemLineSeparator;
   private String lineSeparatorString;
   private char[] lineSeparator;
   private char normalizedNewline = '\n';
   private char comment = '#';

   protected Format() {
      this.lineSeparator = (char[])systemLineSeparator.clone();
      this.lineSeparatorString = systemLineSeparatorString;
   }

   public char[] getLineSeparator() {
      return (char[])this.lineSeparator.clone();
   }

   public static char[] getSystemLineSeparator() {
      return (char[])systemLineSeparator.clone();
   }

   public String getLineSeparatorString() {
      return this.lineSeparatorString;
   }

   public void setLineSeparator(String lineSeparator) {
      if (lineSeparator != null && !lineSeparator.isEmpty()) {
         this.setLineSeparator(lineSeparator.toCharArray());
      } else {
         throw new IllegalArgumentException("Line separator cannot be empty");
      }
   }

   public void setLineSeparator(char[] lineSeparator) {
      if (lineSeparator != null && lineSeparator.length != 0) {
         if (lineSeparator.length > 2) {
            throw new IllegalArgumentException("Invalid line separator. Up to 2 characters are expected. Got " + lineSeparator.length + " characters.");
         } else {
            this.lineSeparator = lineSeparator;
            this.lineSeparatorString = new String(lineSeparator);
            if (lineSeparator.length == 1) {
               this.setNormalizedNewline(lineSeparator[0]);
            }

         }
      } else {
         throw new IllegalArgumentException("Invalid line separator. Expected 1 to 2 characters");
      }
   }

   public char getNormalizedNewline() {
      return this.normalizedNewline;
   }

   public void setNormalizedNewline(char normalizedNewline) {
      this.normalizedNewline = normalizedNewline;
   }

   public boolean isNewLine(char ch) {
      return this.normalizedNewline == ch;
   }

   public char getComment() {
      return this.comment;
   }

   public void setComment(char comment) {
      this.comment = comment;
   }

   public boolean isComment(char ch) {
      return this.comment == ch;
   }

   private static String getFormattedValue(Object value) {
      if (value instanceof Character) {
         char ch = (Character)value;
         switch (ch) {
            case '\u0000':
               return "\\0";
            case '\t':
               return "\\t";
            case '\n':
               return "\\n";
            case '\r':
               return "\\r";
            default:
               return value.toString();
         }
      } else {
         if (value instanceof String) {
            String s = (String)value;
            StringBuilder tmp = new StringBuilder();

            for(int i = 0; i < s.length(); ++i) {
               tmp.append(getFormattedValue(s.charAt(i)));
            }

            value = tmp.toString();
         }

         return String.valueOf(value).trim().isEmpty() ? "'" + value + '\'' : String.valueOf(value);
      }
   }

   public final String toString() {
      StringBuilder out = new StringBuilder();
      out.append(this.getClass().getSimpleName()).append(':');
      TreeMap<String, Object> config = this.getConfiguration();
      config.put("Comment character", this.comment);
      config.put("Line separator sequence", this.lineSeparatorString);
      config.put("Line separator (normalized)", this.normalizedNewline);

      for(Map.Entry e : config.entrySet()) {
         out.append("\n\t\t");
         out.append((String)e.getKey()).append('=').append(getFormattedValue(e.getValue()));
      }

      return out.toString();
   }

   protected abstract TreeMap getConfiguration();

   protected Format clone() {
      try {
         return (Format)super.clone();
      } catch (CloneNotSupportedException e) {
         throw new IllegalStateException("Error cloning format object", e);
      }
   }

   static {
      String lineSeparator = System.getProperty("line.separator");
      if (lineSeparator == null) {
         systemLineSeparatorString = "\n";
      } else {
         systemLineSeparatorString = lineSeparator;
      }

      systemLineSeparator = systemLineSeparatorString.toCharArray();
   }
}
