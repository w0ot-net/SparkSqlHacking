package com.univocity.parsers.csv;

import com.univocity.parsers.common.AbstractParser;
import com.univocity.parsers.common.TextParsingException;
import com.univocity.parsers.common.input.DefaultCharAppender;
import com.univocity.parsers.common.input.EOFException;
import com.univocity.parsers.common.input.ExpandingCharAppender;
import com.univocity.parsers.common.input.InputAnalysisProcess;
import com.univocity.parsers.common.input.NoopCharAppender;

public final class CsvParser extends AbstractParser {
   private boolean parseUnescapedQuotes;
   private boolean parseUnescapedQuotesUntilDelimiter;
   private boolean backToDelimiter;
   private final boolean doNotEscapeUnquotedValues;
   private final boolean keepEscape;
   private final boolean keepQuotes;
   private boolean unescaped;
   private char prev;
   private char delimiter;
   private char[] multiDelimiter;
   private char quote;
   private char quoteEscape;
   private char escapeEscape;
   private char newLine;
   private final DefaultCharAppender whitespaceAppender;
   private final boolean normalizeLineEndingsInQuotes;
   private UnescapedQuoteHandling quoteHandling;
   private final String nullValue;
   private final int maxColumnLength;
   private final String emptyValue;
   private final boolean trimQuotedLeading;
   private final boolean trimQuotedTrailing;
   private char[] delimiters;
   private int match = 0;
   private int formatDetectorRowSampleCount;

   public CsvParser(CsvParserSettings settings) {
      super(settings);
      this.parseUnescapedQuotes = settings.isParseUnescapedQuotes();
      this.parseUnescapedQuotesUntilDelimiter = settings.isParseUnescapedQuotesUntilDelimiter();
      this.doNotEscapeUnquotedValues = !settings.isEscapeUnquotedValues();
      this.keepEscape = settings.isKeepEscapeSequences();
      this.keepQuotes = settings.getKeepQuotes();
      this.normalizeLineEndingsInQuotes = settings.isNormalizeLineEndingsWithinQuotes();
      this.nullValue = settings.getNullValue();
      this.emptyValue = settings.getEmptyValue();
      this.maxColumnLength = settings.getMaxCharsPerColumn();
      this.trimQuotedTrailing = settings.getIgnoreTrailingWhitespacesInQuotes();
      this.trimQuotedLeading = settings.getIgnoreLeadingWhitespacesInQuotes();
      this.formatDetectorRowSampleCount = settings.getFormatDetectorRowSampleCount();
      this.updateFormat((CsvFormat)settings.getFormat());
      this.whitespaceAppender = new ExpandingCharAppender(10, "", this.whitespaceRangeStart);
      this.quoteHandling = settings.getUnescapedQuoteHandling();
      if (this.quoteHandling == null) {
         if (this.parseUnescapedQuotes) {
            if (this.parseUnescapedQuotesUntilDelimiter) {
               this.quoteHandling = UnescapedQuoteHandling.STOP_AT_DELIMITER;
            } else {
               this.quoteHandling = UnescapedQuoteHandling.STOP_AT_CLOSING_QUOTE;
            }
         } else {
            this.quoteHandling = UnescapedQuoteHandling.RAISE_ERROR;
         }
      } else {
         this.backToDelimiter = this.quoteHandling == UnescapedQuoteHandling.BACK_TO_DELIMITER;
         this.parseUnescapedQuotesUntilDelimiter = this.quoteHandling == UnescapedQuoteHandling.STOP_AT_DELIMITER || this.quoteHandling == UnescapedQuoteHandling.SKIP_VALUE || this.backToDelimiter;
         this.parseUnescapedQuotes = this.quoteHandling != UnescapedQuoteHandling.RAISE_ERROR;
      }

   }

   protected final void parseRecord() {
      if (this.multiDelimiter == null) {
         this.parseSingleDelimiterRecord();
      } else {
         this.parseMultiDelimiterRecord();
      }

   }

   private final void parseSingleDelimiterRecord() {
      if (this.ch <= ' ' && this.ignoreLeadingWhitespace && this.whitespaceRangeStart < this.ch) {
         this.ch = this.input.skipWhitespace(this.ch, this.delimiter, this.quote);
      }

      while(this.ch != this.newLine) {
         if (this.ch <= ' ' && this.ignoreLeadingWhitespace && this.whitespaceRangeStart < this.ch) {
            this.ch = this.input.skipWhitespace(this.ch, this.delimiter, this.quote);
         }

         if (this.ch != this.delimiter && this.ch != this.newLine) {
            this.unescaped = false;
            this.prev = 0;
            if (this.ch == this.quote) {
               this.input.enableNormalizeLineEndings(this.normalizeLineEndingsInQuotes);
               int len = this.output.appender.length();
               if (len == 0) {
                  String value = this.input.getQuotedString(this.quote, this.quoteEscape, this.escapeEscape, this.maxColumnLength, this.delimiter, this.newLine, this.keepQuotes, this.keepEscape, this.trimQuotedLeading, this.trimQuotedTrailing);
                  if (value != null) {
                     this.output.valueParsed(value == "" ? this.emptyValue : value);
                     this.input.enableNormalizeLineEndings(true);

                     try {
                        this.ch = this.input.nextChar();
                        if (this.ch == this.delimiter) {
                           try {
                              this.ch = this.input.nextChar();
                              if (this.ch == this.newLine) {
                                 this.output.emptyParsed();
                              }
                           } catch (EOFException var4) {
                              this.output.emptyParsed();
                              return;
                           }
                        }
                        continue;
                     } catch (EOFException var5) {
                        return;
                     }
                  }
               } else if (len == -1 && this.input.skipQuotedString(this.quote, this.quoteEscape, this.delimiter, this.newLine)) {
                  this.output.valueParsed();

                  try {
                     this.ch = this.input.nextChar();
                     if (this.ch == this.delimiter) {
                        try {
                           this.ch = this.input.nextChar();
                           if (this.ch == this.newLine) {
                              this.output.emptyParsed();
                           }
                        } catch (EOFException var6) {
                           this.output.emptyParsed();
                           return;
                        }
                     }
                     continue;
                  } catch (EOFException var7) {
                     return;
                  }
               }

               this.output.trim = this.trimQuotedTrailing;
               this.parseQuotedValue();
               this.input.enableNormalizeLineEndings(true);
               if (!this.unescaped || this.quoteHandling != UnescapedQuoteHandling.BACK_TO_DELIMITER || this.output.appender.length() != 0) {
                  this.output.valueParsed();
               }
            } else if (this.doNotEscapeUnquotedValues) {
               String value = null;
               int len = this.output.appender.length();
               if (len == 0) {
                  value = this.input.getString(this.ch, this.delimiter, this.ignoreTrailingWhitespace, this.nullValue, this.maxColumnLength);
               }

               if (value != null) {
                  this.output.valueParsed(value);
                  this.ch = this.input.getChar();
               } else {
                  if (len != -1) {
                     this.output.trim = this.ignoreTrailingWhitespace;
                     this.ch = this.output.appender.appendUntil(this.ch, this.input, this.delimiter, this.newLine);
                  } else if (this.input.skipString(this.ch, this.delimiter)) {
                     this.ch = this.input.getChar();
                  } else {
                     this.ch = this.output.appender.appendUntil(this.ch, this.input, this.delimiter, this.newLine);
                  }

                  this.output.valueParsed();
               }
            } else {
               this.output.trim = this.ignoreTrailingWhitespace;
               this.parseValueProcessingEscape();
               this.output.valueParsed();
            }
         } else {
            this.output.emptyParsed();
         }

         if (this.ch != this.newLine) {
            this.ch = this.input.nextChar();
            if (this.ch == this.newLine) {
               this.output.emptyParsed();
            }
         }
      }

   }

   private void skipValue() {
      this.output.appender.reset();
      this.output.appender = NoopCharAppender.getInstance();
      if (this.multiDelimiter == null) {
         this.ch = NoopCharAppender.getInstance().appendUntil(this.ch, this.input, this.delimiter, this.newLine);
      } else {
         for(; this.match < this.multiDelimiter.length && this.ch != this.newLine; this.ch = this.input.nextChar()) {
            if (this.multiDelimiter[this.match] == this.ch) {
               ++this.match;
            } else {
               this.match = 0;
            }
         }
      }

   }

   private void handleValueSkipping(boolean quoted) {
      switch (this.quoteHandling) {
         case SKIP_VALUE:
            this.skipValue();
         default:
            return;
         case RAISE_ERROR:
            throw new TextParsingException(this.context, "Unescaped quote character '" + this.quote + "' inside " + (quoted ? "quoted" : "") + " value of CSV field. To allow unescaped quotes, set 'parseUnescapedQuotes' to 'true' in the CSV parser settings. Cannot parse CSV input.");
      }
   }

   private void handleUnescapedQuoteInValue() {
      switch (this.quoteHandling) {
         case BACK_TO_DELIMITER:
         case STOP_AT_CLOSING_QUOTE:
         case STOP_AT_DELIMITER:
            this.output.appender.append(this.quote);
            this.prev = this.ch;
            this.parseValueProcessingEscape();
            break;
         default:
            this.handleValueSkipping(false);
      }

   }

   private int nextDelimiter() {
      if (this.multiDelimiter == null) {
         return this.output.appender.indexOfAny(this.delimiters, 0);
      } else {
         int lineEnd = this.output.appender.indexOf(this.newLine, 0);
         int delimiter = this.output.appender.indexOf((char[])this.multiDelimiter, 0);
         return lineEnd != -1 && lineEnd < delimiter ? lineEnd : delimiter;
      }
   }

   private boolean handleUnescapedQuote() {
      this.unescaped = true;
      switch (this.quoteHandling) {
         case BACK_TO_DELIMITER:
            int lastPos = 0;

            int pos;
            while((pos = this.nextDelimiter()) != -1) {
               lastPos = pos;
               String value = this.output.appender.substring(0, pos);
               if (this.keepQuotes && this.output.appender.charAt(pos - 1) == this.quote) {
                  value = value + this.quote;
               }

               this.output.valueParsed(value);
               if (this.output.appender.charAt(pos) == this.newLine) {
                  this.output.pendingRecords.add(this.output.rowParsed());
                  this.output.appender.remove(0, pos + 1);
               } else if (this.multiDelimiter == null) {
                  this.output.appender.remove(0, pos + 1);
               } else {
                  this.output.appender.remove(0, pos + this.multiDelimiter.length);
               }
            }

            if (this.keepQuotes && this.input.lastIndexOf(this.quote) > lastPos) {
               this.output.appender.append(this.quote);
            }

            this.output.appender.append(this.ch);
            this.prev = 0;
            if (this.multiDelimiter == null) {
               this.parseQuotedValue();
            } else {
               this.parseQuotedValueMultiDelimiter();
            }

            return true;
         case STOP_AT_CLOSING_QUOTE:
         case STOP_AT_DELIMITER:
            this.output.appender.append(this.quote);
            this.output.appender.append(this.ch);
            this.prev = this.ch;
            if (this.multiDelimiter == null) {
               this.parseQuotedValue();
            } else {
               this.parseQuotedValueMultiDelimiter();
            }

            return true;
         default:
            this.handleValueSkipping(true);
            return false;
      }
   }

   private void processQuoteEscape() {
      if (this.ch == this.quoteEscape && this.prev == this.escapeEscape && this.escapeEscape != 0) {
         if (this.keepEscape) {
            this.output.appender.append(this.escapeEscape);
         }

         this.output.appender.append(this.quoteEscape);
         this.ch = 0;
      } else if (this.prev == this.quoteEscape) {
         if (this.ch == this.quote) {
            if (this.keepEscape) {
               this.output.appender.append(this.quoteEscape);
            }

            this.output.appender.append(this.quote);
            this.ch = 0;
         } else {
            this.output.appender.append(this.prev);
         }
      } else if (this.ch == this.quote && this.prev == this.quote) {
         this.output.appender.append(this.quote);
      } else if (this.prev == this.quote) {
         this.handleUnescapedQuoteInValue();
      }

   }

   private void parseValueProcessingEscape() {
      while(this.ch != this.delimiter && this.ch != this.newLine) {
         if (this.ch != this.quote && this.ch != this.quoteEscape) {
            if (this.prev == this.quote) {
               this.handleUnescapedQuoteInValue();
               return;
            }

            this.output.appender.append(this.ch);
         } else {
            this.processQuoteEscape();
         }

         this.prev = this.ch;
         this.ch = this.input.nextChar();
      }

   }

   private void parseQuotedValue() {
      if (this.prev != 0 && this.parseUnescapedQuotesUntilDelimiter) {
         if (this.quoteHandling == UnescapedQuoteHandling.SKIP_VALUE) {
            this.skipValue();
            return;
         }

         if (!this.keepQuotes) {
            this.output.appender.prepend(this.quote);
         }

         this.ch = this.input.nextChar();
         this.output.trim = this.ignoreTrailingWhitespace;
         this.ch = this.output.appender.appendUntil(this.ch, this.input, this.delimiter, this.newLine);
      } else {
         if (this.keepQuotes && this.prev == 0) {
            this.output.appender.append(this.quote);
         }

         this.ch = this.input.nextChar();
         if (this.trimQuotedLeading && this.ch <= ' ' && this.output.appender.length() == 0) {
            while((this.ch = this.input.nextChar()) <= ' ') {
            }
         }

         while(this.prev != this.quote || (this.ch > ' ' || this.whitespaceRangeStart >= this.ch) && this.ch != this.delimiter && this.ch != this.newLine) {
            if (this.ch != this.quote && this.ch != this.quoteEscape) {
               if (this.prev != this.quote) {
                  if (this.prev == this.quoteEscape && this.quoteEscape != 0) {
                     this.output.appender.append(this.quoteEscape);
                  }

                  this.ch = this.output.appender.appendUntil(this.ch, this.input, this.quote, this.quoteEscape, this.escapeEscape);
                  this.prev = this.ch;
                  this.ch = this.input.nextChar();
               } else {
                  if (!this.handleUnescapedQuote()) {
                     return;
                  }

                  if (this.quoteHandling != UnescapedQuoteHandling.SKIP_VALUE) {
                     return;
                  }
                  break;
               }
            } else {
               this.processQuoteEscape();
               this.prev = this.ch;
               this.ch = this.input.nextChar();
               if (this.unescaped && (this.ch == this.delimiter || this.ch == this.newLine)) {
                  return;
               }
            }
         }

         if (this.ch != this.delimiter && this.ch != this.newLine && this.ch <= ' ' && this.whitespaceRangeStart < this.ch) {
            this.whitespaceAppender.reset();

            do {
               this.whitespaceAppender.append(this.ch);
               this.ch = this.input.nextChar();
               if (this.ch == this.newLine) {
                  if (this.keepQuotes) {
                     this.output.appender.append(this.quote);
                  }

                  return;
               }
            } while(this.ch <= ' ' && this.whitespaceRangeStart < this.ch && this.ch != this.delimiter);

            if (this.ch != this.delimiter && this.parseUnescapedQuotes) {
               if (this.output.appender instanceof DefaultCharAppender) {
                  this.output.appender.append(this.quote);
                  ((DefaultCharAppender)this.output.appender).append(this.whitespaceAppender);
               }

               if (this.parseUnescapedQuotesUntilDelimiter || this.ch != this.quote && this.ch != this.quoteEscape) {
                  this.output.appender.append(this.ch);
               }

               this.prev = this.ch;
               this.parseQuotedValue();
            } else if (this.keepQuotes) {
               this.output.appender.append(this.quote);
            }
         } else if (this.keepQuotes) {
            this.output.appender.append(this.quote);
         }

         if (this.ch != this.delimiter && this.ch != this.newLine) {
            throw new TextParsingException(this.context, "Unexpected character '" + this.ch + "' following quoted value of CSV field. Expecting '" + this.delimiter + "'. Cannot parse CSV input.");
         }
      }

   }

   protected final InputAnalysisProcess getInputAnalysisProcess() {
      return !((CsvParserSettings)this.settings).isDelimiterDetectionEnabled() && !((CsvParserSettings)this.settings).isQuoteDetectionEnabled() ? null : new CsvFormatDetector(this.formatDetectorRowSampleCount, (CsvParserSettings)this.settings, this.whitespaceRangeStart) {
         protected void apply(char delimiter, char quote, char quoteEscape) {
            if (((CsvParserSettings)CsvParser.this.settings).isDelimiterDetectionEnabled()) {
               CsvParser.this.delimiter = delimiter;
               CsvParser.this.delimiters[0] = delimiter;
            }

            if (((CsvParserSettings)CsvParser.this.settings).isQuoteDetectionEnabled()) {
               CsvParser.this.quote = quote;
               CsvParser.this.quoteEscape = quoteEscape;
            }

         }
      };
   }

   public final CsvFormat getDetectedFormat() {
      CsvFormat out = null;
      if (((CsvParserSettings)this.settings).isDelimiterDetectionEnabled()) {
         out = ((CsvFormat)((CsvParserSettings)this.settings).getFormat()).clone();
         out.setDelimiter(this.delimiter);
      }

      if (((CsvParserSettings)this.settings).isQuoteDetectionEnabled()) {
         out = out == null ? ((CsvFormat)((CsvParserSettings)this.settings).getFormat()).clone() : out;
         out.setQuote(this.quote);
         out.setQuoteEscape(this.quoteEscape);
      }

      if (((CsvParserSettings)this.settings).isLineSeparatorDetectionEnabled()) {
         out = out == null ? ((CsvFormat)((CsvParserSettings)this.settings).getFormat()).clone() : out;
         out.setLineSeparator(this.input.getLineSeparator());
      }

      return out;
   }

   protected final boolean consumeValueOnEOF() {
      if (this.ch == this.quote) {
         if (this.prev == this.quote) {
            if (this.keepQuotes) {
               this.output.appender.append(this.quote);
            }

            return true;
         }

         if (!this.unescaped) {
            this.output.appender.append(this.quote);
         }
      }

      boolean out = this.prev != 0 && this.ch != this.delimiter && this.ch != this.newLine && this.ch != this.comment;
      this.ch = this.prev = 0;
      if (this.match > 0) {
         this.saveMatchingCharacters();
         return true;
      } else {
         return out;
      }
   }

   public final void updateFormat(CsvFormat format) {
      this.newLine = format.getNormalizedNewline();
      this.multiDelimiter = format.getDelimiterString().toCharArray();
      if (this.multiDelimiter.length == 1) {
         this.multiDelimiter = null;
         this.delimiter = format.getDelimiter();
         this.delimiters = new char[]{this.delimiter, this.newLine};
      } else {
         this.delimiters = new char[]{this.multiDelimiter[0], this.newLine};
      }

      this.quote = format.getQuote();
      this.quoteEscape = format.getQuoteEscape();
      this.escapeEscape = format.getCharToEscapeQuoteEscaping();
   }

   private void skipWhitespace() {
      while(this.ch <= ' ' && this.match < this.multiDelimiter.length && this.ch != this.newLine && this.ch != this.quote && this.whitespaceRangeStart < this.ch) {
         this.ch = this.input.nextChar();
         if (this.multiDelimiter[this.match] == this.ch && this.matchDelimiter()) {
            this.output.emptyParsed();
            this.ch = this.input.nextChar();
         }
      }

      this.saveMatchingCharacters();
   }

   private void saveMatchingCharacters() {
      if (this.match > 0) {
         if (this.match < this.multiDelimiter.length) {
            this.output.appender.append((char[])this.multiDelimiter, 0, this.match);
         }

         this.match = 0;
      }

   }

   private boolean matchDelimiter() {
      while(true) {
         if (this.ch == this.multiDelimiter[this.match]) {
            ++this.match;
            if (this.match != this.multiDelimiter.length) {
               this.ch = this.input.nextChar();
               continue;
            }
         }

         if (this.multiDelimiter.length == this.match) {
            this.match = 0;
            return true;
         }

         if (this.match > 0) {
            this.saveMatchingCharacters();
         }

         return false;
      }
   }

   private boolean matchDelimiterAfterQuote() {
      while(true) {
         if (this.ch == this.multiDelimiter[this.match]) {
            ++this.match;
            if (this.match != this.multiDelimiter.length) {
               this.ch = this.input.nextChar();
               continue;
            }
         }

         if (this.multiDelimiter.length == this.match) {
            this.match = 0;
            return true;
         }

         return false;
      }
   }

   private void parseMultiDelimiterRecord() {
      if (this.ch <= ' ' && this.ignoreLeadingWhitespace && this.whitespaceRangeStart < this.ch) {
         this.skipWhitespace();
      }

      while(this.ch != this.newLine) {
         if (this.ch <= ' ' && this.ignoreLeadingWhitespace && this.whitespaceRangeStart < this.ch) {
            this.skipWhitespace();
         }

         if (this.ch != this.newLine && !this.matchDelimiter()) {
            this.unescaped = false;
            this.prev = 0;
            if (this.ch == this.quote && this.output.appender.length() == 0) {
               this.input.enableNormalizeLineEndings(this.normalizeLineEndingsInQuotes);
               this.output.trim = this.trimQuotedTrailing;
               this.parseQuotedValueMultiDelimiter();
               this.input.enableNormalizeLineEndings(true);
               if (!this.unescaped || this.quoteHandling != UnescapedQuoteHandling.BACK_TO_DELIMITER || this.output.appender.length() != 0) {
                  this.output.valueParsed();
               }
            } else if (this.doNotEscapeUnquotedValues) {
               this.appendUntilMultiDelimiter();
               if (this.ignoreTrailingWhitespace) {
                  this.output.appender.updateWhitespace();
               }

               this.output.valueParsed();
            } else {
               this.output.trim = this.ignoreTrailingWhitespace;
               this.parseValueProcessingEscapeMultiDelimiter();
               this.output.valueParsed();
            }
         } else {
            this.output.emptyParsed();
         }

         if (this.ch != this.newLine) {
            this.ch = this.input.nextChar();
            if (this.ch == this.newLine) {
               this.output.emptyParsed();
            }
         }
      }

   }

   private void appendUntilMultiDelimiter() {
      while(true) {
         while(true) {
            if (this.match < this.multiDelimiter.length && this.ch != this.newLine) {
               if (this.multiDelimiter[this.match] != this.ch) {
                  if (this.match > 0) {
                     this.saveMatchingCharacters();
                     continue;
                  }

                  this.output.appender.append(this.ch);
                  break;
               }

               ++this.match;
               if (this.match != this.multiDelimiter.length) {
                  break;
               }
            }

            this.saveMatchingCharacters();
            return;
         }

         this.ch = this.input.nextChar();
      }
   }

   private void parseQuotedValueMultiDelimiter() {
      if (this.prev != 0 && this.parseUnescapedQuotesUntilDelimiter) {
         if (this.quoteHandling == UnescapedQuoteHandling.SKIP_VALUE) {
            this.skipValue();
            return;
         }

         if (!this.keepQuotes) {
            this.output.appender.prepend(this.quote);
         }

         this.ch = this.input.nextChar();
         this.output.trim = this.ignoreTrailingWhitespace;
         this.appendUntilMultiDelimiter();
      } else {
         if (this.keepQuotes && this.prev == 0) {
            this.output.appender.append(this.quote);
         }

         this.ch = this.input.nextChar();
         if (this.trimQuotedLeading && this.ch <= ' ' && this.output.appender.length() == 0) {
            while((this.ch = this.input.nextChar()) <= ' ') {
            }
         }

         while(this.prev != this.quote || (this.ch > ' ' || this.whitespaceRangeStart >= this.ch) && this.ch != this.newLine) {
            if (this.prev == this.quote && this.matchDelimiter()) {
               if (this.keepQuotes) {
                  this.output.appender.append(this.quote);
               }

               return;
            }

            if (this.ch != this.quote && this.ch != this.quoteEscape) {
               if (this.prev == this.quote) {
                  if (!this.handleUnescapedQuote()) {
                     return;
                  }

                  if (this.quoteHandling != UnescapedQuoteHandling.SKIP_VALUE) {
                     return;
                  }
                  break;
               }

               if (this.prev == this.quoteEscape && this.quoteEscape != 0) {
                  this.output.appender.append(this.quoteEscape);
               }

               this.ch = this.output.appender.appendUntil(this.ch, this.input, this.quote, this.quoteEscape, this.escapeEscape);
               this.prev = this.ch;
               this.ch = this.input.nextChar();
            } else {
               this.processQuoteEscape();
               this.prev = this.ch;
               this.ch = this.input.nextChar();
               if (this.unescaped && (this.ch == this.newLine || this.matchDelimiter())) {
                  return;
               }
            }
         }
      }

      if (this.ch != this.newLine && this.ch <= ' ' && this.whitespaceRangeStart < this.ch && !this.matchDelimiterAfterQuote()) {
         this.whitespaceAppender.reset();

         do {
            this.whitespaceAppender.append(this.ch);
            this.ch = this.input.nextChar();
            if (this.ch == this.newLine) {
               if (this.keepQuotes) {
                  this.output.appender.append(this.quote);
               }

               return;
            }

            if (this.matchDelimiterAfterQuote()) {
               return;
            }
         } while(this.ch <= ' ' && this.whitespaceRangeStart < this.ch);

         if (this.parseUnescapedQuotes && !this.matchDelimiterAfterQuote()) {
            if (this.output.appender instanceof DefaultCharAppender) {
               this.output.appender.append(this.quote);
               ((DefaultCharAppender)this.output.appender).append(this.whitespaceAppender);
            }

            if (this.parseUnescapedQuotesUntilDelimiter || this.ch != this.quote && this.ch != this.quoteEscape) {
               this.output.appender.append(this.ch);
            }

            this.prev = this.ch;
            this.parseQuotedValue();
         } else if (this.keepQuotes) {
            this.output.appender.append(this.quote);
         }
      } else if (this.keepQuotes && (!this.unescaped || this.quoteHandling == UnescapedQuoteHandling.STOP_AT_CLOSING_QUOTE)) {
         this.output.appender.append(this.quote);
      }

   }

   private void parseValueProcessingEscapeMultiDelimiter() {
      while(this.ch != this.newLine && !this.matchDelimiter()) {
         if (this.ch != this.quote && this.ch != this.quoteEscape) {
            if (this.prev == this.quote) {
               this.handleUnescapedQuoteInValue();
               return;
            }

            this.output.appender.append(this.ch);
         } else {
            this.processQuoteEscape();
         }

         this.prev = this.ch;
         this.ch = this.input.nextChar();
      }

   }
}
