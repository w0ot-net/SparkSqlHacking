package org.sparkproject.jetty.http;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.sparkproject.jetty.util.QuotedStringTokenizer;

public class QuotedCSV extends QuotedCSVParser implements Iterable {
   public static final String ABNF_REQUIRED_QUOTING = "\"'\\\n\r\t\f\b%+ ;=,";
   protected final List _values;

   public static String join(List values) {
      if (values == null) {
         return null;
      } else {
         int size = values.size();
         if (size <= 0) {
            return "";
         } else if (size == 1) {
            return (String)values.get(0);
         } else {
            StringBuilder ret = new StringBuilder();
            join(ret, values);
            return ret.toString();
         }
      }
   }

   public static String join(String... values) {
      if (values == null) {
         return null;
      } else if (values.length <= 0) {
         return "";
      } else if (values.length == 1) {
         return values[0];
      } else {
         StringBuilder ret = new StringBuilder();
         join(ret, Arrays.asList(values));
         return ret.toString();
      }
   }

   public static void join(StringBuilder builder, List values) {
      if (values != null && !values.isEmpty()) {
         boolean needsDelim = false;

         for(String value : values) {
            if (needsDelim) {
               builder.append(", ");
            } else {
               needsDelim = true;
            }

            QuotedStringTokenizer.quoteIfNeeded(builder, value, "\"'\\\n\r\t\f\b%+ ;=,");
         }

      }
   }

   public QuotedCSV(String... values) {
      this(true, values);
   }

   public QuotedCSV(boolean keepQuotes, String... values) {
      super(keepQuotes);
      this._values = new ArrayList();

      for(String v : values) {
         this.addValue(v);
      }

   }

   protected void parsedValueAndParams(StringBuffer buffer) {
      this._values.add(buffer.toString());
   }

   public int size() {
      return this._values.size();
   }

   public boolean isEmpty() {
      return this._values.isEmpty();
   }

   public List getValues() {
      return this._values;
   }

   public Iterator iterator() {
      return this._values.iterator();
   }

   public String toString() {
      List<String> list = new ArrayList();

      for(String s : this) {
         list.add(s);
      }

      return list.toString();
   }
}
