package com.univocity.parsers.common;

import java.util.Collections;
import java.util.Map;

public class ParsingContextSnapshot extends ParsingContextWrapper {
   private final long currentLine;
   private final long currentChar;
   private final Map comments;
   private final String lastComment;
   private final int currentColumn;
   private final String currentParsedContent;
   private final long currentRecord;

   public ParsingContextSnapshot(ParsingContext context) {
      super(context);
      this.currentLine = context.currentLine();
      this.currentChar = context.currentChar();
      this.comments = context.comments() == Collections.EMPTY_MAP ? Collections.emptyMap() : Collections.unmodifiableMap(context.comments());
      this.lastComment = context.lastComment();
      this.currentColumn = context.currentColumn();
      this.currentParsedContent = context.currentParsedContent();
      this.currentRecord = context.currentRecord();
   }

   public long currentLine() {
      return this.currentLine;
   }

   public long currentChar() {
      return this.currentChar;
   }

   public Map comments() {
      return this.comments;
   }

   public String lastComment() {
      return this.lastComment;
   }

   public int currentColumn() {
      return this.currentColumn;
   }

   public String currentParsedContent() {
      return this.currentParsedContent;
   }

   public long currentRecord() {
      return this.currentRecord;
   }
}
