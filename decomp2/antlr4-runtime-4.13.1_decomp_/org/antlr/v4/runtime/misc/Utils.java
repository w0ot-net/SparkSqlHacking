package org.antlr.v4.runtime.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Utils {
   public static String join(Iterator iter, String separator) {
      StringBuilder buf = new StringBuilder();

      while(iter.hasNext()) {
         buf.append(iter.next());
         if (iter.hasNext()) {
            buf.append(separator);
         }
      }

      return buf.toString();
   }

   public static String join(Object[] array, String separator) {
      StringBuilder builder = new StringBuilder();

      for(int i = 0; i < array.length; ++i) {
         builder.append(array[i]);
         if (i < array.length - 1) {
            builder.append(separator);
         }
      }

      return builder.toString();
   }

   public static int numNonnull(Object[] data) {
      int n = 0;
      if (data == null) {
         return n;
      } else {
         for(Object o : data) {
            if (o != null) {
               ++n;
            }
         }

         return n;
      }
   }

   public static void removeAllElements(Collection data, Object value) {
      if (data != null) {
         while(data.contains(value)) {
            data.remove(value);
         }

      }
   }

   public static String escapeWhitespace(String s, boolean escapeSpaces) {
      StringBuilder buf = new StringBuilder();

      for(char c : s.toCharArray()) {
         if (c == ' ' && escapeSpaces) {
            buf.append('·');
         } else if (c == '\t') {
            buf.append("\\t");
         } else if (c == '\n') {
            buf.append("\\n");
         } else if (c == '\r') {
            buf.append("\\r");
         } else {
            buf.append(c);
         }
      }

      return buf.toString();
   }

   public static void writeFile(String fileName, String content) throws IOException {
      writeFile(fileName, content, (String)null);
   }

   public static void writeFile(String fileName, String content, String encoding) throws IOException {
      File f = new File(fileName);
      FileOutputStream fos = new FileOutputStream(f);
      OutputStreamWriter osw;
      if (encoding != null) {
         osw = new OutputStreamWriter(fos, encoding);
      } else {
         osw = new OutputStreamWriter(fos);
      }

      try {
         osw.write(content);
      } finally {
         osw.close();
      }

   }

   public static char[] readFile(String fileName) throws IOException {
      return readFile(fileName, (String)null);
   }

   public static char[] readFile(String fileName, String encoding) throws IOException {
      File f = new File(fileName);
      int size = (int)f.length();
      FileInputStream fis = new FileInputStream(fileName);
      InputStreamReader isr;
      if (encoding != null) {
         isr = new InputStreamReader(fis, encoding);
      } else {
         isr = new InputStreamReader(fis);
      }

      char[] data = null;

      try {
         data = new char[size];
         int n = isr.read(data);
         if (n < data.length) {
            data = Arrays.copyOf(data, n);
         }
      } finally {
         isr.close();
      }

      return data;
   }

   public static Map toMap(String[] keys) {
      Map<String, Integer> m = new HashMap();

      for(int i = 0; i < keys.length; ++i) {
         m.put(keys[i], i);
      }

      return m;
   }

   public static char[] toCharArray(IntegerList data) {
      return data == null ? null : data.toCharArray();
   }

   public static IntervalSet toSet(BitSet bits) {
      IntervalSet s = new IntervalSet(new int[0]);

      for(int i = bits.nextSetBit(0); i >= 0; i = bits.nextSetBit(i + 1)) {
         s.add(i);
      }

      return s;
   }

   public static String expandTabs(String s, int tabSize) {
      if (s == null) {
         return null;
      } else {
         StringBuilder buf = new StringBuilder();
         int col = 0;

         for(int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            switch (c) {
               case '\t':
                  int n = tabSize - col % tabSize;
                  col += n;
                  buf.append(spaces(n));
                  break;
               case '\n':
                  col = 0;
                  buf.append(c);
                  break;
               default:
                  ++col;
                  buf.append(c);
            }
         }

         return buf.toString();
      }
   }

   public static String spaces(int n) {
      return sequence(n, " ");
   }

   public static String newlines(int n) {
      return sequence(n, "\n");
   }

   public static String sequence(int n, String s) {
      StringBuilder buf = new StringBuilder();

      for(int sp = 1; sp <= n; ++sp) {
         buf.append(s);
      }

      return buf.toString();
   }

   public static int count(String s, char x) {
      int n = 0;

      for(int i = 0; i < s.length(); ++i) {
         if (s.charAt(i) == x) {
            ++n;
         }
      }

      return n;
   }
}
