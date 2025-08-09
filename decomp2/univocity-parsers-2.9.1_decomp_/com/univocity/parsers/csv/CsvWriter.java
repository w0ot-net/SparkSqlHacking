package com.univocity.parsers.csv;

import com.univocity.parsers.common.AbstractWriter;
import com.univocity.parsers.common.fields.FieldSelector;
import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CsvWriter extends AbstractWriter {
   private char delimiter;
   private char[] multiDelimiter;
   private char quoteChar;
   private char escapeChar;
   private char escapeEscape;
   private boolean quoteAllFields;
   private boolean escapeUnquoted;
   private boolean inputNotEscaped;
   private char newLine;
   private boolean dontProcessNormalizedNewLines;
   private boolean[] quotationTriggers;
   private char maxTrigger;
   private Set quotedColumns;
   private FieldSelector quotedFieldSelector;
   private boolean quoteNulls;

   public CsvWriter(CsvWriterSettings settings) {
      this((Writer)null, settings);
   }

   public CsvWriter(Writer writer, CsvWriterSettings settings) {
      super((Writer)writer, settings);
   }

   public CsvWriter(File file, CsvWriterSettings settings) {
      super((File)file, settings);
   }

   public CsvWriter(File file, String encoding, CsvWriterSettings settings) {
      super((File)file, (String)encoding, settings);
   }

   public CsvWriter(File file, Charset encoding, CsvWriterSettings settings) {
      super((File)file, (Charset)encoding, settings);
   }

   public CsvWriter(OutputStream output, CsvWriterSettings settings) {
      super((OutputStream)output, settings);
   }

   public CsvWriter(OutputStream output, String encoding, CsvWriterSettings settings) {
      super((OutputStream)output, (String)encoding, settings);
   }

   public CsvWriter(OutputStream output, Charset encoding, CsvWriterSettings settings) {
      super((OutputStream)output, (Charset)encoding, settings);
   }

   protected final void initialize(CsvWriterSettings settings) {
      CsvFormat format = (CsvFormat)settings.getFormat();
      this.multiDelimiter = format.getDelimiterString().toCharArray();
      if (this.multiDelimiter.length == 1) {
         this.delimiter = this.multiDelimiter[0];
         this.multiDelimiter = null;
      }

      this.quoteChar = format.getQuote();
      this.escapeChar = format.getQuoteEscape();
      this.escapeEscape = ((CsvFormat)settings.getFormat()).getCharToEscapeQuoteEscaping();
      this.newLine = format.getNormalizedNewline();
      this.quoteAllFields = settings.getQuoteAllFields();
      this.quoteNulls = settings.getQuoteNulls();
      this.escapeUnquoted = settings.isEscapeUnquotedValues();
      this.inputNotEscaped = !settings.isInputEscaped();
      this.dontProcessNormalizedNewLines = !settings.isNormalizeLineEndingsWithinQuotes();
      this.quotationTriggers = null;
      this.quotedColumns = null;
      this.maxTrigger = 0;
      this.quotedColumns = Collections.emptySet();
      this.quotedFieldSelector = settings.getQuotedFieldSelector();
      char[] sep = format.getLineSeparator();
      int triggerCount = 3 + settings.getQuotationTriggers().length + sep.length;
      int offset = settings.isQuoteEscapingEnabled() ? 1 : 0;
      char[] tmp = Arrays.copyOf(settings.getQuotationTriggers(), triggerCount + offset);
      if (offset == 1) {
         tmp[triggerCount] = this.quoteChar;
      }

      tmp[triggerCount - 1] = '\n';
      tmp[triggerCount - 2] = '\r';
      tmp[triggerCount - 3] = this.newLine;
      tmp[triggerCount - 4] = sep[0];
      if (sep.length > 1) {
         tmp[triggerCount - 5] = sep[1];
      }

      for(int i = 0; i < tmp.length; ++i) {
         if (this.maxTrigger < tmp[i]) {
            this.maxTrigger = tmp[i];
         }
      }

      if (this.maxTrigger != 0) {
         ++this.maxTrigger;
         this.quotationTriggers = new boolean[this.maxTrigger];
         Arrays.fill(this.quotationTriggers, false);

         for(int i = 0; i < tmp.length; ++i) {
            this.quotationTriggers[tmp[i]] = true;
         }
      }

   }

   protected void processRow(Object[] row) {
      if (this.recordCount == 0L && this.quotedFieldSelector != null) {
         int[] quotedIndexes = this.quotedFieldSelector.getFieldIndexes(this.headers);
         if (quotedIndexes.length > 0) {
            this.quotedColumns = new HashSet();

            for(int idx : quotedIndexes) {
               this.quotedColumns.add(idx);
            }
         }
      }

      for(int i = 0; i < row.length; ++i) {
         if (i != 0) {
            if (this.multiDelimiter == null) {
               this.appendToRow(this.delimiter);
            } else {
               this.appendToRow(this.multiDelimiter);
            }
         }

         if (this.dontProcessNormalizedNewLines) {
            this.appender.enableDenormalizedLineEndings(false);
         }

         boolean allowTrim = this.allowTrim(i);
         String nextElement = this.getStringValue(row[i]);
         boolean quoteOn = this.quoteNulls || row[i] != null;
         int originalLength = this.appender.length();
         boolean isElementQuoted = this.append(i, quoteOn && (this.quoteAllFields || this.quotedColumns.contains(i)), allowTrim, nextElement) && quoteOn;
         if (this.appender.length() == originalLength && !this.usingNullOrEmptyValue) {
            if (isElementQuoted) {
               if (nextElement == null) {
                  this.append(i, false, allowTrim, this.nullValue);
               } else {
                  this.append(i, true, allowTrim, this.emptyValue);
               }
            } else if (nextElement == null) {
               this.append(i, false, allowTrim, this.nullValue);
            } else {
               this.append(i, false, allowTrim, this.emptyValue);
            }
         }

         if (isElementQuoted) {
            this.appendToRow(this.quoteChar);
            this.appendValueToRow();
            this.appendToRow(this.quoteChar);
            if (this.dontProcessNormalizedNewLines) {
               this.appender.enableDenormalizedLineEndings(true);
            }
         } else {
            this.appendValueToRow();
         }
      }

   }

   private boolean matchMultiDelimiter(String element, int from) {
      if (from + this.multiDelimiter.length - 2 >= element.length()) {
         return false;
      } else {
         for(int j = 1; j < this.multiDelimiter.length; ++from) {
            if (element.charAt(from) != this.multiDelimiter[j]) {
               return false;
            }

            ++j;
         }

         return true;
      }
   }

   private boolean quoteElement(int start, String element) {
      int length = element.length();
      if (this.multiDelimiter == null) {
         if (this.maxTrigger == 0) {
            for(int i = start; i < length; ++i) {
               char nextChar = element.charAt(i);
               if (nextChar == this.delimiter || nextChar == this.newLine) {
                  return true;
               }
            }
         } else {
            for(int i = start; i < length; ++i) {
               char nextChar = element.charAt(i);
               if (nextChar == this.delimiter || nextChar < this.maxTrigger && this.quotationTriggers[nextChar]) {
                  return true;
               }
            }
         }
      } else if (this.maxTrigger == 0) {
         for(int i = start; i < length; ++i) {
            char nextChar = element.charAt(i);
            if (nextChar == this.multiDelimiter[0] && this.matchMultiDelimiter(element, i + 1) || nextChar == this.newLine) {
               return true;
            }
         }
      } else {
         for(int i = start; i < length; ++i) {
            char nextChar = element.charAt(i);
            if (nextChar == this.multiDelimiter[0] && this.matchMultiDelimiter(element, i + 1) || nextChar < this.maxTrigger && this.quotationTriggers[nextChar]) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean append(int columnIndex, boolean isElementQuoted, boolean allowTrim, String element) {
      if (element == null) {
         if (this.nullValue == null) {
            return isElementQuoted;
         }

         element = this.nullValue;
      }

      int start = 0;
      if (allowTrim && this.ignoreLeading) {
         start = skipLeadingWhitespace(this.whitespaceRangeStart, element);
      }

      int length = element.length();
      if (start < length && (element.charAt(start) == this.quoteChar || columnIndex == 0 && element.charAt(0) == this.comment)) {
         isElementQuoted = true;
      }

      if (isElementQuoted) {
         if (this.usingNullOrEmptyValue && length >= 2) {
            if (element.charAt(0) == this.quoteChar && element.charAt(length - 1) == this.quoteChar) {
               this.appender.append(element);
               return false;
            } else {
               this.appendQuoted(start, allowTrim, element);
               return true;
            }
         } else {
            this.appendQuoted(start, allowTrim, element);
            return true;
         }
      } else {
         int i = start;
         char ch = 0;
         if (this.multiDelimiter != null) {
            for(; i < length; ++i) {
               ch = element.charAt(i);
               if (ch == this.quoteChar || ch == this.multiDelimiter[0] && this.matchMultiDelimiter(element, i + 1) || ch == this.escapeChar || ch < this.maxTrigger && this.quotationTriggers[ch]) {
                  this.appender.append(element, start, i);
                  start = i + 1;
                  if (ch == this.quoteChar || ch == this.escapeChar) {
                     if (this.quoteElement(i, element)) {
                        this.appendQuoted(i, allowTrim, element);
                        return true;
                     }

                     if (this.escapeUnquoted) {
                        this.appendQuoted(i, allowTrim, element);
                     } else {
                        this.appender.append(element, i, length);
                        if (allowTrim && this.ignoreTrailing && element.charAt(length - 1) <= ' ' && this.whitespaceRangeStart < element.charAt(length - 1)) {
                           this.appender.updateWhitespace();
                        }
                     }

                     return isElementQuoted;
                  }

                  if (ch == this.escapeChar && this.inputNotEscaped && this.escapeEscape != 0 && this.escapeUnquoted) {
                     this.appender.append(this.escapeEscape);
                  } else if (ch == this.multiDelimiter[0] && this.matchMultiDelimiter(element, i + 1) || ch < this.maxTrigger && this.quotationTriggers[ch]) {
                     this.appendQuoted(i, allowTrim, element);
                     return true;
                  }

                  this.appender.append(ch);
               }
            }
         } else {
            for(; i < length; ++i) {
               ch = element.charAt(i);
               if (ch == this.quoteChar || ch == this.delimiter || ch == this.escapeChar || ch < this.maxTrigger && this.quotationTriggers[ch]) {
                  this.appender.append(element, start, i);
                  start = i + 1;
                  if (ch == this.quoteChar || ch == this.escapeChar) {
                     if (this.quoteElement(i, element)) {
                        this.appendQuoted(i, allowTrim, element);
                        return true;
                     }

                     if (this.escapeUnquoted) {
                        this.appendQuoted(i, allowTrim, element);
                     } else {
                        this.appender.append(element, i, length);
                        if (allowTrim && this.ignoreTrailing && element.charAt(length - 1) <= ' ' && this.whitespaceRangeStart < element.charAt(length - 1)) {
                           this.appender.updateWhitespace();
                        }
                     }

                     return isElementQuoted;
                  }

                  if (ch == this.escapeChar && this.inputNotEscaped && this.escapeEscape != 0 && this.escapeUnquoted) {
                     this.appender.append(this.escapeEscape);
                  } else if (ch == this.delimiter || ch < this.maxTrigger && this.quotationTriggers[ch]) {
                     this.appendQuoted(i, allowTrim, element);
                     return true;
                  }

                  this.appender.append(ch);
               }
            }
         }

         this.appender.append(element, start, i);
         if (allowTrim && ch <= ' ' && this.ignoreTrailing && this.whitespaceRangeStart < ch) {
            this.appender.updateWhitespace();
         }

         return isElementQuoted;
      }
   }

   private void appendQuoted(int start, boolean allowTrim, String element) {
      int length = element.length();
      int i = start;

      char ch;
      for(ch = 0; i < length; ++i) {
         ch = element.charAt(i);
         if (ch == this.quoteChar || ch == this.newLine || ch == this.escapeChar) {
            this.appender.append(element, start, i);
            start = i + 1;
            if (ch == this.quoteChar && this.inputNotEscaped) {
               this.appender.append(this.escapeChar);
            } else if (ch == this.escapeChar && this.inputNotEscaped && this.escapeEscape != 0) {
               this.appender.append(this.escapeEscape);
            }

            this.appender.append(ch);
         }
      }

      this.appender.append(element, start, i);
      if (allowTrim && ch <= ' ' && this.ignoreTrailing && this.whitespaceRangeStart < ch) {
         this.appender.updateWhitespace();
      }

   }
}
