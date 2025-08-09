package org.codehaus.janino.util;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.codehaus.commons.nullanalysis.Nullable;

public class AutoIndentWriter extends FilterWriter {
   public static final char TABULATOR = '\uffff';
   public static final char CLEAR_TABULATORS = '\ufffe';
   public static final char INDENT = '�';
   public static final char UNINDENT = '￼';
   private final StringBuilder lineBuffer = new StringBuilder();
   private int indentation;
   @Nullable
   private List tabulatorBuffer;
   private int tabulatorIndentation;

   public AutoIndentWriter(Writer out) {
      super(out);
   }

   public void write(@Nullable char[] cbuf, int off, int len) throws IOException {
      assert cbuf != null;

      while(len > 0) {
         this.write(cbuf[off++]);
         --len;
      }

   }

   public void write(@Nullable String str, int off, int len) throws IOException {
      assert str != null;

      while(len > 0) {
         this.write(str.charAt(off++));
         --len;
      }

   }

   public void write(int c) throws IOException {
      if (c == 10) {
         this.lineBuffer.append('\n');
         this.line(this.lineBuffer.toString());
         this.lineBuffer.setLength(0);
      } else if (this.lineBuffer.length() > 0 && this.lineBuffer.charAt(this.lineBuffer.length() - 1) == '\r') {
         this.line(this.lineBuffer.toString());
         this.lineBuffer.setCharAt(0, (char)c);
         this.lineBuffer.setLength(1);
      } else {
         this.lineBuffer.append((char)c);
      }
   }

   private void line(String line) throws IOException {
      if (this.tabulatorBuffer != null) {
         this.tabulatorBuffer.add((new StringBuilder(line.length())).append(line));
         if (line.charAt(0) == '�') {
            ++this.indentation;
            line = line.substring(1);
         }

         if (line.charAt(0) == '￼' && --this.indentation < this.tabulatorIndentation) {
            this.flushTabulatorBuffer();
         }
      } else if (line.indexOf(65535) != -1) {
         if (line.charAt(0) == '�') {
            ++this.indentation;
            line = line.substring(1);
         }

         if (line.charAt(0) == '￼') {
            --this.indentation;
            line = line.substring(1);
         }

         this.tabulatorBuffer = new ArrayList();
         this.tabulatorBuffer.add((new StringBuilder(line.length())).append(line));
         this.tabulatorIndentation = this.indentation;
      } else {
         if (line.charAt(0) == '\ufffe') {
            line = line.substring(1);
         }

         if (line.charAt(0) == '�') {
            ++this.indentation;
            line = line.substring(1);
         }

         if (line.charAt(0) == '￼') {
            --this.indentation;
            line = line.substring(1);
         }

         if ("\r\n".indexOf(line.charAt(0)) == -1) {
            for(int i = 0; i < this.indentation; ++i) {
               this.out.write("    ");
            }
         }

         this.out.write(line);
      }

   }

   private void flushTabulatorBuffer() throws IOException {
      List<List<StringBuilder>> lineGroups = new ArrayList();
      lineGroups.add(new ArrayList());
      List<StringBuilder> tb = this.tabulatorBuffer;

      assert tb != null;

      for(StringBuilder line : tb) {
         int idx = 0;
         if (line.charAt(0) == '�') {
            lineGroups.add(new ArrayList());
            ++idx;
         }

         if (line.charAt(idx) == '￼') {
            resolveTabs((List)lineGroups.remove(lineGroups.size() - 1));
            ++idx;
         }

         if (line.charAt(idx) == '\ufffe') {
            List<StringBuilder> lg = (List)lineGroups.get(lineGroups.size() - 1);
            resolveTabs(lg);
            lg.clear();
            line.deleteCharAt(idx);
         }

         for(int i = 0; i < line.length(); ++i) {
            if (line.charAt(i) == '\uffff') {
               ((List)lineGroups.get(lineGroups.size() - 1)).add(line);
            }
         }
      }

      for(List lg : lineGroups) {
         resolveTabs(lg);
      }

      int ind = this.tabulatorIndentation;

      for(StringBuilder sb : tb) {
         String line = sb.toString();
         if (line.charAt(0) == '�') {
            ++ind;
            line = line.substring(1);
         }

         if (line.charAt(0) == '￼') {
            --ind;
            line = line.substring(1);
         }

         if ("\r\n".indexOf(line.charAt(0)) == -1) {
            for(int i = 0; i < ind; ++i) {
               this.out.write("    ");
            }
         }

         this.out.write(line.toString());
      }

      this.tabulatorBuffer = null;
   }

   private static void resolveTabs(List lineGroup) {
      List<Integer> tabulatorOffsets = new ArrayList();

      for(StringBuilder line : lineGroup) {
         int previousTab = 0;
         if (line.charAt(previousTab) == '�') {
            ++previousTab;
         }

         if (line.charAt(previousTab) == '￼') {
            ++previousTab;
         }

         int tabCount = 0;

         for(int i = previousTab; i < line.length(); ++i) {
            if (line.charAt(i) == '\uffff') {
               int tabOffset = i - previousTab;
               previousTab = i;
               if (tabCount >= tabulatorOffsets.size()) {
                  tabulatorOffsets.add(tabOffset);
               } else if (tabOffset > (Integer)tabulatorOffsets.get(tabCount)) {
                  tabulatorOffsets.set(tabCount, tabOffset);
               }

               ++tabCount;
            }
         }
      }

      for(StringBuilder line : lineGroup) {
         int tabCount = 0;
         int previousTab = 0;
         if (line.charAt(previousTab) == '�') {
            ++previousTab;
         }

         if (line.charAt(previousTab) == '￼') {
            ++previousTab;
         }

         for(int i = previousTab; i < line.length(); ++i) {
            if (line.charAt(i) == '\uffff') {
               int tabOffset = i - previousTab;
               int n = (Integer)tabulatorOffsets.get(tabCount++) - tabOffset;
               line.replace(i, i + 1, spaces(n));
               i += n - 1;
               previousTab = i;
            }
         }
      }

   }

   private static String spaces(int n) {
      if (n < 30) {
         return "                              ".substring(0, n);
      } else {
         char[] data = new char[n];
         Arrays.fill(data, ' ');
         return String.valueOf(data);
      }
   }

   public void close() throws IOException {
      if (this.tabulatorBuffer != null) {
         this.flushTabulatorBuffer();
      }

      if (this.lineBuffer.length() > 0) {
         this.line(this.lineBuffer.toString());
      }

      this.out.close();
   }

   public void flush() throws IOException {
      if (this.tabulatorBuffer != null) {
         this.flushTabulatorBuffer();
      }

      if (this.lineBuffer.length() > 0) {
         this.line(this.lineBuffer.toString());
         this.lineBuffer.setLength(0);
      }

      this.out.flush();
   }
}
