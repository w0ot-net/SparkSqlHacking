package com.univocity.parsers.tsv;

import com.univocity.parsers.common.AbstractWriter;
import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;

public class TsvWriter extends AbstractWriter {
   private boolean joinLines;
   private char escapeChar;
   private char escapedTabChar;
   private char newLine;

   public TsvWriter(TsvWriterSettings settings) {
      this((Writer)null, settings);
   }

   public TsvWriter(Writer writer, TsvWriterSettings settings) {
      super((Writer)writer, settings);
   }

   public TsvWriter(File file, TsvWriterSettings settings) {
      super((File)file, settings);
   }

   public TsvWriter(File file, String encoding, TsvWriterSettings settings) {
      super((File)file, (String)encoding, settings);
   }

   public TsvWriter(File file, Charset encoding, TsvWriterSettings settings) {
      super((File)file, (Charset)encoding, settings);
   }

   public TsvWriter(OutputStream output, TsvWriterSettings settings) {
      super((OutputStream)output, settings);
   }

   public TsvWriter(OutputStream output, String encoding, TsvWriterSettings settings) {
      super((OutputStream)output, (String)encoding, settings);
   }

   public TsvWriter(OutputStream output, Charset encoding, TsvWriterSettings settings) {
      super((OutputStream)output, (Charset)encoding, settings);
   }

   protected final void initialize(TsvWriterSettings settings) {
      this.escapeChar = ((TsvFormat)settings.getFormat()).getEscapeChar();
      this.escapedTabChar = ((TsvFormat)settings.getFormat()).getEscapedTabChar();
      this.joinLines = settings.isLineJoiningEnabled();
      this.newLine = ((TsvFormat)settings.getFormat()).getNormalizedNewline();
   }

   protected void processRow(Object[] row) {
      for(int i = 0; i < row.length; ++i) {
         if (i != 0) {
            this.appendToRow('\t');
         }

         String nextElement = this.getStringValue(row[i]);
         boolean allowTrim = this.allowTrim(i);
         int originalLength = this.appender.length();
         this.append(nextElement, allowTrim);
         if (this.appender.length() == originalLength && this.nullValue != null && !this.nullValue.isEmpty()) {
            this.append(this.nullValue, allowTrim);
         }

         this.appendValueToRow();
      }

   }

   private void append(String element, boolean allowTrim) {
      if (element == null) {
         element = this.nullValue;
      }

      if (element != null) {
         int start = 0;
         if (allowTrim && this.ignoreLeading) {
            start = skipLeadingWhitespace(this.whitespaceRangeStart, element);
         }

         int length = element.length();
         int i = start;

         char ch;
         for(ch = 0; i < length; ++i) {
            ch = element.charAt(i);
            if (ch == '\t' || ch == '\n' || ch == '\r' || ch == '\\') {
               this.appender.append(element, start, i);
               start = i + 1;
               this.appender.append(this.escapeChar);
               if (ch == '\t') {
                  this.appender.append(this.escapedTabChar);
               } else if (ch == '\n') {
                  this.appender.append(this.joinLines ? this.newLine : 'n');
               } else if (ch == '\\') {
                  this.appender.append('\\');
               } else {
                  this.appender.append(this.joinLines ? this.newLine : 'r');
               }
            }
         }

         this.appender.append(element, start, i);
         if (allowTrim && ch <= ' ' && this.ignoreTrailing && this.whitespaceRangeStart < ch) {
            this.appender.updateWhitespace();
         }

      }
   }
}
