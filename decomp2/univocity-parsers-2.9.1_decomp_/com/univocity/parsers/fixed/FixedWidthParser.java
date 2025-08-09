package com.univocity.parsers.fixed;

import com.univocity.parsers.common.AbstractParser;
import com.univocity.parsers.common.NormalizedString;
import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.ParsingContextWrapper;
import com.univocity.parsers.common.TextParsingException;
import com.univocity.parsers.common.input.EOFException;
import com.univocity.parsers.common.input.LookaheadCharInputReader;
import com.univocity.parsers.common.record.Record;

public class FixedWidthParser extends AbstractParser {
   private int[] lengths;
   private int[] rootLengths;
   private boolean[] ignore;
   private boolean[] rootIgnore;
   private FieldAlignment[] alignments;
   private FieldAlignment[] rootAlignments;
   private char[] paddings;
   private char[] rootPaddings;
   private Boolean[] keepPaddingFlags;
   private Boolean[] rootKeepPaddingFlags;
   private final Lookup[] lookaheadFormats;
   private final Lookup[] lookbehindFormats;
   private Lookup lookupFormat;
   private Lookup lookbehindFormat;
   private int maxLookupLength;
   private final boolean skipToNewLine;
   private final boolean recordEndsOnNewLine;
   private final boolean skipEmptyLines;
   private final boolean keepPadding;
   private boolean useDefaultPadding;
   private final char defaultPadding;
   private char padding;
   private FieldAlignment alignment;
   private final char newLine;
   private int length;
   private boolean initializeLookaheadInput = false;
   private LookaheadCharInputReader lookaheadInput;
   private final char wildcard;

   public FixedWidthParser(FixedWidthParserSettings settings) {
      super(settings);
      this.skipToNewLine = settings.getSkipTrailingCharsUntilNewline();
      this.recordEndsOnNewLine = settings.getRecordEndsOnNewline();
      this.skipEmptyLines = settings.getSkipEmptyLines();
      this.lengths = settings.getAllLengths();
      this.alignments = settings.getFieldAlignments();
      this.paddings = settings.getFieldPaddings();
      this.ignore = settings.getFieldsToIgnore();
      this.keepPaddingFlags = settings.getKeepPaddingFlags();
      this.keepPadding = settings.getKeepPadding();
      this.lookaheadFormats = settings.getLookaheadFormats();
      this.lookbehindFormats = settings.getLookbehindFormats();
      this.wildcard = ((FixedWidthFormat)settings.getFormat()).getLookupWildcard();
      if (this.lookaheadFormats != null || this.lookbehindFormats != null) {
         this.initializeLookaheadInput = true;
         this.rootLengths = this.lengths;
         this.rootAlignments = this.alignments;
         this.rootPaddings = this.paddings;
         this.rootIgnore = this.ignore;
         this.rootKeepPaddingFlags = this.keepPaddingFlags;
         this.maxLookupLength = Lookup.calculateMaxLookupLength(this.lookaheadFormats, this.lookbehindFormats);
      }

      FixedWidthFormat format = (FixedWidthFormat)settings.getFormat();
      this.padding = format.getPadding();
      this.defaultPadding = this.padding;
      this.newLine = format.getNormalizedNewline();
      this.useDefaultPadding = settings.getUseDefaultPaddingForHeaders() && settings.isHeaderExtractionEnabled();
   }

   protected ParsingContext createParsingContext() {
      ParsingContext context = super.createParsingContext();
      return (ParsingContext)(this.lookaheadFormats == null && this.lookbehindFormats == null ? context : new ParsingContextWrapper(context) {
         public String[] headers() {
            return FixedWidthParser.this.lookupFormat != null ? NormalizedString.toArray(FixedWidthParser.this.lookupFormat.fieldNames) : super.headers();
         }

         public Record toRecord(String[] row) {
            if (FixedWidthParser.this.lookupFormat != null) {
               if (FixedWidthParser.this.lookupFormat.context == null) {
                  FixedWidthParser.this.lookupFormat.initializeLookupContext((ParsingContext)this.context, FixedWidthParser.this.lookupFormat.fieldNames);
               }

               return FixedWidthParser.this.lookupFormat.context.toRecord(row);
            } else {
               return super.toRecord(row);
            }
         }
      });
   }

   protected void parseRecord() {
      if (this.ch != this.newLine || !this.skipEmptyLines) {
         boolean matched = false;
         if (this.lookaheadFormats != null || this.lookbehindFormats != null) {
            if (this.initializeLookaheadInput) {
               this.initializeLookaheadInput = false;
               this.lookaheadInput = new LookaheadCharInputReader(this.input, this.newLine, this.whitespaceRangeStart);
               this.input = this.lookaheadInput;
            }

            this.lookaheadInput.lookahead(this.maxLookupLength);
            if (this.lookaheadFormats == null) {
               for(int i = 0; i < this.lookbehindFormats.length; ++i) {
                  if (this.lookaheadInput.matches(this.ch, this.lookbehindFormats[i].value, this.wildcard)) {
                     this.lookbehindFormat = this.lookbehindFormats[i];
                     matched = true;
                     this.lengths = this.rootLengths;
                     this.ignore = this.rootIgnore;
                     this.keepPaddingFlags = this.rootKeepPaddingFlags;
                     break;
                  }
               }
            } else {
               for(int i = 0; i < this.lookaheadFormats.length; ++i) {
                  if (this.lookaheadInput.matches(this.ch, this.lookaheadFormats[i].value, this.wildcard)) {
                     this.lengths = this.lookaheadFormats[i].lengths;
                     this.alignments = this.lookaheadFormats[i].alignments;
                     this.paddings = this.lookaheadFormats[i].paddings;
                     this.ignore = this.lookaheadFormats[i].ignore;
                     this.keepPaddingFlags = this.lookaheadFormats[i].keepPaddingFlags;
                     this.lookupFormat = this.lookaheadFormats[i];
                     matched = true;
                     break;
                  }
               }

               if (this.lookbehindFormats != null && matched) {
                  this.lookbehindFormat = null;

                  for(int i = 0; i < this.lookbehindFormats.length; ++i) {
                     if (this.lookaheadInput.matches(this.ch, this.lookbehindFormats[i].value, this.wildcard)) {
                        this.lookbehindFormat = this.lookbehindFormats[i];
                        break;
                     }
                  }
               }
            }

            if (!matched) {
               if (this.lookbehindFormat == null) {
                  if (this.rootLengths == null) {
                     throw new TextParsingException(this.context, "Cannot process input with the given configuration. No default field lengths defined and no lookahead/lookbehind value match '" + this.lookaheadInput.getLookahead(this.ch) + '\'');
                  }

                  this.lengths = this.rootLengths;
                  this.alignments = this.rootAlignments;
                  this.paddings = this.rootPaddings;
                  this.ignore = this.rootIgnore;
                  this.keepPaddingFlags = this.rootKeepPaddingFlags;
                  this.lookupFormat = null;
               } else {
                  this.lengths = this.lookbehindFormat.lengths;
                  this.alignments = this.lookbehindFormat.alignments;
                  this.paddings = this.lookbehindFormat.paddings;
                  this.ignore = this.lookbehindFormat.ignore;
                  this.keepPaddingFlags = this.lookbehindFormat.keepPaddingFlags;
                  this.lookupFormat = this.lookbehindFormat;
               }
            }
         }

         for(int i = 0; i < this.lengths.length; ++i) {
            boolean ignorePadding = this.keepPaddingFlags[i] == null ? !this.keepPadding : !this.keepPaddingFlags[i];
            this.length = this.lengths[i];
            if (this.paddings != null) {
               this.padding = this.useDefaultPadding ? this.defaultPadding : this.paddings[i];
            }

            if (this.alignments != null) {
               this.alignment = this.alignments[i];
            }

            boolean lastFieldOfRecord = i + 1 >= this.lengths.length;
            if (ignorePadding) {
               this.skipPadding(lastFieldOfRecord);
            }

            if (this.ignoreLeadingWhitespace) {
               this.skipWhitespace(lastFieldOfRecord, ignorePadding);
            }

            if (this.recordEndsOnNewLine) {
               this.readValueUntilNewLine(ignorePadding);
               if (this.ch == this.newLine) {
                  this.output.valueParsed();
                  this.useDefaultPadding = false;
                  return;
               }
            } else if (this.length > 0) {
               this.readValue(ignorePadding);
               if (!lastFieldOfRecord) {
                  this.ch = this.input.nextChar();
               }
            }

            if (this.ignore[i]) {
               this.output.appender.reset();
            } else {
               this.output.valueParsed();
            }
         }

         if (this.skipToNewLine) {
            this.skipToNewLine();
         }

         this.useDefaultPadding = false;
      }
   }

   private void skipToNewLine() {
      try {
         while(this.ch != this.newLine) {
            this.ch = this.input.nextChar();
         }
      } catch (EOFException var2) {
      }

   }

   private void skipPadding(boolean lastFieldOfRecord) {
      while(this.ch == this.padding && this.length-- > 0) {
         if (!lastFieldOfRecord || this.length > 0) {
            this.ch = this.input.nextChar();
         }
      }

   }

   private void skipWhitespace(boolean lastFieldOfRecord, boolean ignorePadding) {
      while(this.ch <= ' ' && this.whitespaceRangeStart < this.ch || this.ch == this.padding) {
         if (!ignorePadding && this.ch == this.padding || this.length-- <= 0) {
            break;
         }

         if (!lastFieldOfRecord || this.length > 0) {
            this.ch = this.input.nextChar();
         }
      }

   }

   private void readValueUntilNewLine(boolean ignorePadding) {
      if (this.ignoreTrailingWhitespace) {
         if (this.alignment == FieldAlignment.RIGHT) {
            while(this.length-- > 0 && this.ch != this.newLine) {
               this.output.appender.appendIgnoringWhitespace(this.ch);
               this.ch = this.input.nextChar();
            }
         } else if (ignorePadding) {
            while(this.length-- > 0 && this.ch != this.newLine) {
               this.output.appender.appendIgnoringWhitespaceAndPadding(this.ch, this.padding);
               this.ch = this.input.nextChar();
            }
         } else {
            while(this.length-- > 0 && this.ch != this.newLine) {
               this.output.appender.append(this.ch);
               this.ch = this.input.nextChar();
            }
         }
      } else if (this.alignment == FieldAlignment.RIGHT) {
         while(this.length-- > 0 && this.ch != this.newLine) {
            this.output.appender.append(this.ch);
            this.ch = this.input.nextChar();
         }
      } else if (ignorePadding) {
         while(this.length-- > 0 && this.ch != this.newLine) {
            this.output.appender.appendIgnoringPadding(this.ch, this.padding);
            this.ch = this.input.nextChar();
         }
      } else {
         while(this.length-- > 0 && this.ch != this.newLine) {
            this.output.appender.append(this.ch);
            this.ch = this.input.nextChar();
         }
      }

   }

   private void readValue(boolean ignorePadding) {
      --this.length;
      if (this.ignoreTrailingWhitespace) {
         if (this.alignment == FieldAlignment.RIGHT) {
            this.output.appender.appendIgnoringWhitespace(this.ch);

            while(this.length-- > 0) {
               this.output.appender.appendIgnoringWhitespace(this.ch = this.input.nextChar());
            }
         } else if (ignorePadding) {
            this.output.appender.appendIgnoringWhitespaceAndPadding(this.ch, this.padding);

            while(this.length-- > 0) {
               this.output.appender.appendIgnoringWhitespaceAndPadding(this.ch = this.input.nextChar(), this.padding);
            }
         } else {
            this.output.appender.append(this.ch);

            while(this.length-- > 0) {
               this.output.appender.append(this.ch = this.input.nextChar());
            }
         }
      } else if (this.alignment == FieldAlignment.RIGHT) {
         this.output.appender.append(this.ch);

         while(this.length-- > 0) {
            this.output.appender.append(this.ch = this.input.nextChar());
         }
      } else if (ignorePadding) {
         this.output.appender.appendIgnoringPadding(this.ch, this.padding);

         while(this.length-- > 0) {
            this.output.appender.appendIgnoringPadding(this.ch = this.input.nextChar(), this.padding);
         }
      } else {
         this.output.appender.append(this.ch);

         while(this.length-- > 0) {
            this.output.appender.append(this.ch = this.input.nextChar());
         }
      }

   }
}
