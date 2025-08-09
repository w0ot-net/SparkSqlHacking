package com.univocity.parsers.common.input;

import java.io.Reader;
import java.util.Arrays;

public class LookaheadCharInputReader implements CharInputReader {
   private final CharInputReader reader;
   private char[] lookahead = new char[0];
   private int length = 0;
   private int start = 0;
   private final char newLine;
   private char delimiter;
   private final int whitespaceRangeStart;

   public LookaheadCharInputReader(CharInputReader reader, char newLine, int whitespaceRangeStart) {
      this.reader = reader;
      this.newLine = newLine;
      this.whitespaceRangeStart = whitespaceRangeStart;
   }

   public boolean matches(char current, char[] sequence, char wildcard) {
      if (sequence.length > this.length - this.start) {
         return false;
      } else if (sequence[0] != current && sequence[0] != wildcard) {
         return false;
      } else {
         for(int i = 1; i < sequence.length; ++i) {
            char ch = sequence[i];
            if (ch != wildcard && ch != this.lookahead[i - 1 + this.start]) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean matches(char[] sequence, char wildcard) {
      if (sequence.length > this.length - this.start) {
         return false;
      } else {
         for(int i = 0; i < sequence.length; ++i) {
            char ch = sequence[i];
            if (ch != wildcard && sequence[i] != this.lookahead[i + this.start]) {
               return false;
            }
         }

         return true;
      }
   }

   public String getLookahead() {
      return this.start >= this.length ? "" : new String(this.lookahead, this.start, this.length);
   }

   public String getLookahead(char current) {
      return this.start >= this.length ? String.valueOf(current) : current + new String(this.lookahead, this.start, this.length - 1);
   }

   public void lookahead(int numberOfCharacters) {
      numberOfCharacters += this.length - this.start;
      if (this.lookahead.length < numberOfCharacters) {
         this.lookahead = Arrays.copyOf(this.lookahead, numberOfCharacters);
      }

      if (this.start >= this.length) {
         this.start = 0;
         this.length = 0;
      }

      try {
         for(int var5 = numberOfCharacters - this.length; var5-- > 0; ++this.length) {
            this.lookahead[this.length] = this.reader.nextChar();
         }
      } catch (EOFException var3) {
      }

   }

   public void start(Reader reader) {
      this.reader.start(reader);
   }

   public void stop() {
      this.reader.stop();
   }

   public char nextChar() {
      return this.start >= this.length ? this.reader.nextChar() : this.lookahead[this.start++];
   }

   public long charCount() {
      return this.reader.charCount();
   }

   public long lineCount() {
      return this.reader.lineCount();
   }

   public void skipLines(long lineCount) {
      this.reader.skipLines(lineCount);
   }

   public void enableNormalizeLineEndings(boolean escaping) {
      this.reader.enableNormalizeLineEndings(escaping);
   }

   public String readComment() {
      return this.reader.readComment();
   }

   public char[] getLineSeparator() {
      return this.reader.getLineSeparator();
   }

   public final char getChar() {
      return this.start != 0 && this.start >= this.length ? this.reader.getChar() : this.lookahead[this.start - 1];
   }

   public char skipWhitespace(char ch, char stopChar1, char stopChar2) {
      while(this.start < this.length && ch <= ' ' && ch != stopChar1 && ch != this.newLine && ch != stopChar2 && this.whitespaceRangeStart < ch) {
         ch = this.lookahead[this.start++];
      }

      return this.reader.skipWhitespace(ch, stopChar1, stopChar2);
   }

   public String currentParsedContent() {
      return this.reader.currentParsedContent();
   }

   public void markRecordStart() {
      this.reader.markRecordStart();
   }

   public String getString(char ch, char stop, boolean trim, String nullValue, int maxLength) {
      return this.reader.getString(ch, stop, trim, nullValue, maxLength);
   }

   public String getQuotedString(char quote, char escape, char escapeEscape, int maxLength, char stop1, char stop2, boolean keepQuotes, boolean keepEscape, boolean trimLeading, boolean trimTrailing) {
      return this.reader.getQuotedString(quote, escape, escapeEscape, maxLength, stop1, stop2, keepQuotes, keepEscape, trimLeading, trimTrailing);
   }

   public int currentParsedContentLength() {
      return this.reader.currentParsedContentLength();
   }

   public boolean skipString(char ch, char stop) {
      return this.reader.skipString(ch, stop);
   }

   public boolean skipQuotedString(char quote, char escape, char stop1, char stop2) {
      return this.reader.skipQuotedString(quote, escape, stop1, stop2);
   }

   public int lastIndexOf(char ch) {
      return this.reader.lastIndexOf(ch);
   }

   public void reloadBuffer() {
      if (this.reader instanceof DefaultCharInputReader) {
         ((DefaultCharInputReader)this.reader).reloadBuffer();
      }

   }
}
