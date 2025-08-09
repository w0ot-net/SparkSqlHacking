package com.univocity.parsers.common.input;

public interface CharAppender extends CharSequence {
   void appendIgnoringWhitespace(char var1);

   void appendIgnoringPadding(char var1, char var2);

   void appendIgnoringWhitespaceAndPadding(char var1, char var2);

   void append(char var1);

   int indexOf(char var1, int var2);

   int indexOf(char[] var1, int var2);

   int indexOf(CharSequence var1, int var2);

   int indexOfAny(char[] var1, int var2);

   String substring(int var1, int var2);

   void remove(int var1, int var2);

   void append(int var1);

   void append(Object var1);

   int length();

   int whitespaceCount();

   void resetWhitespaceCount();

   String getAndReset();

   void reset();

   char[] getCharsAndReset();

   char[] getChars();

   void fill(char var1, int var2);

   void prepend(char var1);

   void prepend(char var1, char var2);

   void prepend(char[] var1);

   void updateWhitespace();

   char appendUntil(char var1, CharInput var2, char var3);

   char appendUntil(char var1, CharInput var2, char var3, char var4);

   char appendUntil(char var1, CharInput var2, char var3, char var4, char var5);

   void append(char[] var1, int var2, int var3);

   void append(char[] var1);

   void append(int[] var1);

   void append(String var1);

   void append(String var1, int var2, int var3);

   void ignore(int var1);

   void delete(int var1);

   boolean isEmpty();

   int lastIndexOf(char var1);
}
