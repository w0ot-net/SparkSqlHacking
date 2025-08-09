package com.univocity.parsers.fixed;

import com.univocity.parsers.common.AbstractWriter;
import com.univocity.parsers.common.TextWritingException;
import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;

public class FixedWidthWriter extends AbstractWriter {
   private int[] fieldLengths;
   private FieldAlignment[] fieldAlignments;
   private char[] fieldPaddings;
   private char padding;
   private char defaultPadding;
   private int length;
   private FieldAlignment alignment;
   private Lookup[] lookaheadFormats;
   private Lookup[] lookbehindFormats;
   private char[] lookupChars;
   private Lookup lookbehindFormat;
   private int[] rootLengths;
   private FieldAlignment[] rootAlignments;
   private boolean[] ignore;
   private boolean[] rootIgnore;
   private int ignoreCount;
   private char[] rootPaddings;
   private boolean defaultHeaderPadding;
   private FieldAlignment defaultHeaderAlignment;

   public FixedWidthWriter(FixedWidthWriterSettings settings) {
      this((Writer)null, settings);
   }

   public FixedWidthWriter(Writer writer, FixedWidthWriterSettings settings) {
      super((Writer)writer, settings);
   }

   public FixedWidthWriter(File file, FixedWidthWriterSettings settings) {
      super((File)file, settings);
   }

   public FixedWidthWriter(File file, String encoding, FixedWidthWriterSettings settings) {
      super((File)file, (String)encoding, settings);
   }

   public FixedWidthWriter(File file, Charset encoding, FixedWidthWriterSettings settings) {
      super((File)file, (Charset)encoding, settings);
   }

   public FixedWidthWriter(OutputStream output, FixedWidthWriterSettings settings) {
      super((OutputStream)output, settings);
   }

   public FixedWidthWriter(OutputStream output, String encoding, FixedWidthWriterSettings settings) {
      super((OutputStream)output, (String)encoding, settings);
   }

   public FixedWidthWriter(OutputStream output, Charset encoding, FixedWidthWriterSettings settings) {
      super((OutputStream)output, (Charset)encoding, settings);
   }

   protected final void initialize(FixedWidthWriterSettings settings) {
      FixedWidthFormat format = (FixedWidthFormat)settings.getFormat();
      this.padding = format.getPadding();
      this.defaultPadding = this.padding;
      this.fieldLengths = settings.getAllLengths();
      this.fieldAlignments = settings.getFieldAlignments();
      this.fieldPaddings = settings.getFieldPaddings();
      this.ignore = settings.getFieldsToIgnore();
      if (this.ignore != null) {
         for(int i = 0; i < this.ignore.length; ++i) {
            if (this.ignore[i]) {
               ++this.ignoreCount;
            }
         }
      }

      this.lookaheadFormats = settings.getLookaheadFormats();
      this.lookbehindFormats = settings.getLookbehindFormats();
      this.defaultHeaderPadding = settings.getUseDefaultPaddingForHeaders();
      this.defaultHeaderAlignment = settings.getDefaultAlignmentForHeaders();
      super.enableNewlineAfterRecord(settings.getWriteLineSeparatorAfterRecord());
      if (this.lookaheadFormats == null && this.lookbehindFormats == null) {
         this.lookupChars = null;
         this.rootLengths = null;
         this.rootAlignments = null;
         this.rootPaddings = null;
         this.rootIgnore = null;
      } else {
         this.lookupChars = new char[Lookup.calculateMaxLookupLength(this.lookaheadFormats, this.lookbehindFormats)];
         this.rootLengths = this.fieldLengths;
         this.rootAlignments = this.fieldAlignments;
         this.rootPaddings = this.fieldPaddings;
         this.rootIgnore = this.ignore;
      }

   }

   protected void processRow(Object[] row) {
      if (row.length > 0 && this.lookaheadFormats != null || this.lookbehindFormats != null) {
         int dstBegin = 0;

         for(int i = 0; i < row.length && dstBegin < this.lookupChars.length; ++i) {
            String value = String.valueOf(row[i]);
            int len = value.length();
            if (dstBegin + len > this.lookupChars.length) {
               len = this.lookupChars.length - dstBegin;
            }

            value.getChars(0, len, this.lookupChars, dstBegin);
            dstBegin += len;
         }

         for(int i = this.lookupChars.length - 1; i > dstBegin; --i) {
            this.lookupChars[i] = 0;
         }

         boolean matched = false;
         if (this.lookaheadFormats == null) {
            for(int i = 0; i < this.lookbehindFormats.length; ++i) {
               if (this.lookbehindFormats[i].matches(this.lookupChars)) {
                  this.lookbehindFormat = this.lookbehindFormats[i];
                  matched = true;
                  this.fieldLengths = this.rootLengths;
                  this.fieldAlignments = this.rootAlignments;
                  this.fieldPaddings = this.rootPaddings;
                  this.ignore = this.rootIgnore;
                  break;
               }
            }
         } else {
            for(int i = 0; i < this.lookaheadFormats.length; ++i) {
               if (this.lookaheadFormats[i].matches(this.lookupChars)) {
                  this.fieldLengths = this.lookaheadFormats[i].lengths;
                  this.fieldAlignments = this.lookaheadFormats[i].alignments;
                  this.fieldPaddings = this.lookaheadFormats[i].paddings;
                  this.ignore = this.lookaheadFormats[i].ignore;
                  matched = true;
                  break;
               }
            }

            if (this.lookbehindFormats != null && matched) {
               this.lookbehindFormat = null;

               for(int i = 0; i < this.lookbehindFormats.length; ++i) {
                  if (this.lookbehindFormats[i].matches(this.lookupChars)) {
                     this.lookbehindFormat = this.lookbehindFormats[i];
                     break;
                  }
               }
            }
         }

         if (!matched) {
            if (this.lookbehindFormat == null) {
               if (this.rootLengths == null) {
                  throw new TextWritingException("Cannot write with the given configuration. No default field lengths defined and no lookahead/lookbehind value match '" + new String(this.lookupChars) + '\'', this.getRecordCount(), row);
               }

               this.fieldLengths = this.rootLengths;
               this.fieldAlignments = this.rootAlignments;
               this.fieldPaddings = this.rootPaddings;
               this.ignore = this.rootIgnore;
            } else {
               this.fieldLengths = this.lookbehindFormat.lengths;
               this.fieldAlignments = this.lookbehindFormat.alignments;
               this.fieldPaddings = this.lookbehindFormat.paddings;
               this.ignore = this.lookbehindFormat.ignore;
            }
         }
      }

      if (this.expandRows) {
         row = this.expand(row, this.fieldLengths.length - this.ignoreCount, (Integer)null);
      }

      int lastIndex = this.fieldLengths.length < row.length ? this.fieldLengths.length : row.length;
      int off = 0;

      for(int i = 0; i < lastIndex + off; ++i) {
         this.length = this.fieldLengths[i];
         if (this.ignore[i]) {
            ++off;
            this.appender.fill(' ', this.length);
         } else {
            this.alignment = this.fieldAlignments[i];
            this.padding = this.fieldPaddings[i];
            if (this.writingHeaders) {
               if (this.defaultHeaderPadding) {
                  this.padding = this.defaultPadding;
               }

               if (this.defaultHeaderAlignment != null) {
                  this.alignment = this.defaultHeaderAlignment;
               }
            }

            String nextElement = this.getStringValue(row[i - off]);
            boolean allowTrim = this.allowTrim(i);
            this.processElement(nextElement, allowTrim);
            this.appendValueToRow();
         }
      }

   }

   private void append(String element, boolean allowTrim) {
      int start = 0;
      if (allowTrim && this.ignoreLeading) {
         start = skipLeadingWhitespace(this.whitespaceRangeStart, element);
      }

      int padCount = this.alignment.calculatePadding(this.length, element.length() - start);
      this.length -= padCount;
      this.appender.fill(this.padding, padCount);
      if (allowTrim && this.ignoreTrailing) {
         int i = start;

         while(i < element.length() && this.length > 0) {
            while(i < element.length() && this.length-- > 0) {
               char nextChar = element.charAt(i);
               this.appender.appendIgnoringWhitespace(nextChar);
               ++i;
            }

            if (this.length == -1 && this.appender.whitespaceCount() > 0) {
               for(int j = i; j < element.length(); ++j) {
                  if (element.charAt(j) > ' ') {
                     this.appender.resetWhitespaceCount();
                     break;
                  }
               }

               if (this.appender.whitespaceCount() > 0) {
                  this.length = 0;
               }
            }

            this.length += this.appender.whitespaceCount();
            this.appendValueToRow();
         }
      } else {
         for(int i = start; i < element.length() && this.length-- > 0; ++i) {
            char nextChar = element.charAt(i);
            this.appender.append(nextChar);
         }
      }

   }

   private void processElement(String element, boolean allowTrim) {
      if (element != null) {
         this.append(element, allowTrim);
      }

      this.appender.fill(this.padding, this.length);
   }
}
