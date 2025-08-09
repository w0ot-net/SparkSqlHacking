package com.univocity.parsers.common;

import com.univocity.parsers.common.record.Record;
import java.util.Map;

public class ParsingContextWrapper extends ContextWrapper implements ParsingContext {
   public ParsingContextWrapper(ParsingContext context) {
      super(context);
   }

   public long currentLine() {
      return ((ParsingContext)this.context).currentLine();
   }

   public long currentChar() {
      return ((ParsingContext)this.context).currentChar();
   }

   public void skipLines(long lines) {
      ((ParsingContext)this.context).skipLines(lines);
   }

   public String currentParsedContent() {
      return ((ParsingContext)this.context).currentParsedContent();
   }

   public int currentParsedContentLength() {
      return ((ParsingContext)this.context).currentParsedContentLength();
   }

   public Map comments() {
      return ((ParsingContext)this.context).comments();
   }

   public String lastComment() {
      return ((ParsingContext)this.context).lastComment();
   }

   public String[] parsedHeaders() {
      return ((ParsingContext)this.context).parsedHeaders();
   }

   public char[] lineSeparator() {
      return ((ParsingContext)this.context).lineSeparator();
   }

   public String fieldContentOnError() {
      return ((ParsingContext)this.context).fieldContentOnError();
   }

   public String[] selectedHeaders() {
      return ((ParsingContext)this.context).selectedHeaders();
   }

   public Record toRecord(String[] row) {
      return ((ParsingContext)this.context).toRecord(row);
   }
}
