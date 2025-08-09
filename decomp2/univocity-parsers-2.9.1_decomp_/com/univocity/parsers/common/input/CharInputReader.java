package com.univocity.parsers.common.input;

import java.io.Reader;

public interface CharInputReader extends CharInput {
   void start(Reader var1);

   void stop();

   char nextChar();

   char getChar();

   long charCount();

   long lineCount();

   void skipLines(long var1);

   String readComment();

   void enableNormalizeLineEndings(boolean var1);

   char[] getLineSeparator();

   char skipWhitespace(char var1, char var2, char var3);

   int currentParsedContentLength();

   String currentParsedContent();

   int lastIndexOf(char var1);

   void markRecordStart();

   String getString(char var1, char var2, boolean var3, String var4, int var5);

   boolean skipString(char var1, char var2);

   String getQuotedString(char var1, char var2, char var3, int var4, char var5, char var6, boolean var7, boolean var8, boolean var9, boolean var10);

   boolean skipQuotedString(char var1, char var2, char var3, char var4);
}
