package com.univocity.parsers.common;

import java.util.Map;

public interface ParsingContext extends Context {
   String[] headers();

   int[] extractedFieldIndexes();

   boolean columnsReordered();

   long currentLine();

   long currentChar();

   void skipLines(long var1);

   String[] parsedHeaders();

   String currentParsedContent();

   int currentParsedContentLength();

   String fieldContentOnError();

   Map comments();

   String lastComment();

   char[] lineSeparator();
}
