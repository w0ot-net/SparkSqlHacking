package com.univocity.parsers.common;

import com.univocity.parsers.common.input.CharInputReader;
import java.util.Map;

public class DefaultParsingContext extends DefaultContext implements ParsingContext {
   private final CharInputReader input;
   private final AbstractParser parser;

   public DefaultParsingContext(AbstractParser parser, int errorContentLength) {
      super(parser == null ? null : parser.output, errorContentLength);
      this.parser = parser;
      this.input = parser == null ? null : parser.input;
   }

   public long currentLine() {
      return this.input.lineCount();
   }

   public long currentChar() {
      return this.input.charCount();
   }

   public void skipLines(long lines) {
      this.input.skipLines(lines);
   }

   public String fieldContentOnError() {
      char[] chars = this.output.appender.getChars();
      if (chars != null) {
         int length = this.output.appender.length();
         if (length > chars.length) {
            length = chars.length;
         }

         if (length > 0) {
            return new String(chars, 0, length);
         }
      }

      return null;
   }

   public String currentParsedContent() {
      return this.input != null ? this.input.currentParsedContent() : null;
   }

   public int currentParsedContentLength() {
      return this.input != null ? this.input.currentParsedContentLength() : 0;
   }

   public Map comments() {
      return this.parser.getComments();
   }

   public String lastComment() {
      return this.parser.getLastComment();
   }

   public String[] parsedHeaders() {
      return this.parser.getParsedHeaders();
   }

   public char[] lineSeparator() {
      return this.input.getLineSeparator();
   }
}
