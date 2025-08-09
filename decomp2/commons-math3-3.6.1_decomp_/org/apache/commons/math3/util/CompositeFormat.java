package org.apache.commons.math3.util;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

public class CompositeFormat {
   private CompositeFormat() {
   }

   public static NumberFormat getDefaultNumberFormat() {
      return getDefaultNumberFormat(Locale.getDefault());
   }

   public static NumberFormat getDefaultNumberFormat(Locale locale) {
      NumberFormat nf = NumberFormat.getInstance(locale);
      nf.setMaximumFractionDigits(10);
      return nf;
   }

   public static void parseAndIgnoreWhitespace(String source, ParsePosition pos) {
      parseNextCharacter(source, pos);
      pos.setIndex(pos.getIndex() - 1);
   }

   public static char parseNextCharacter(String source, ParsePosition pos) {
      int index = pos.getIndex();
      int n = source.length();
      char ret = 0;
      if (index < n) {
         char c;
         do {
            c = source.charAt(index++);
         } while(Character.isWhitespace(c) && index < n);

         pos.setIndex(index);
         if (index < n) {
            ret = c;
         }
      }

      return ret;
   }

   private static Number parseNumber(String source, double value, ParsePosition pos) {
      Number ret = null;
      StringBuilder sb = new StringBuilder();
      sb.append('(');
      sb.append(value);
      sb.append(')');
      int n = sb.length();
      int startIndex = pos.getIndex();
      int endIndex = startIndex + n;
      if (endIndex < source.length() && source.substring(startIndex, endIndex).compareTo(sb.toString()) == 0) {
         ret = value;
         pos.setIndex(endIndex);
      }

      return ret;
   }

   public static Number parseNumber(String source, NumberFormat format, ParsePosition pos) {
      int startIndex = pos.getIndex();
      Number number = format.parse(source, pos);
      int endIndex = pos.getIndex();
      if (startIndex == endIndex) {
         double[] special = new double[]{Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};

         for(int i = 0; i < special.length; ++i) {
            number = parseNumber(source, special[i], pos);
            if (number != null) {
               break;
            }
         }
      }

      return number;
   }

   public static boolean parseFixedstring(String source, String expected, ParsePosition pos) {
      int startIndex = pos.getIndex();
      int endIndex = startIndex + expected.length();
      if (startIndex < source.length() && endIndex <= source.length() && source.substring(startIndex, endIndex).compareTo(expected) == 0) {
         pos.setIndex(endIndex);
         return true;
      } else {
         pos.setIndex(startIndex);
         pos.setErrorIndex(startIndex);
         return false;
      }
   }

   public static StringBuffer formatDouble(double value, NumberFormat format, StringBuffer toAppendTo, FieldPosition pos) {
      if (!Double.isNaN(value) && !Double.isInfinite(value)) {
         format.format(value, toAppendTo, pos);
      } else {
         toAppendTo.append('(');
         toAppendTo.append(value);
         toAppendTo.append(')');
      }

      return toAppendTo;
   }
}
