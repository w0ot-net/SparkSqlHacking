package com.univocity.parsers.common.input;

public class NoopCharAppender implements CharAppender {
   private static final NoopCharAppender instance = new NoopCharAppender();

   public static CharAppender getInstance() {
      return instance;
   }

   private NoopCharAppender() {
   }

   public int length() {
      return -1;
   }

   public String getAndReset() {
      return null;
   }

   public void appendIgnoringWhitespace(char ch) {
   }

   public void append(char ch) {
   }

   public char[] getCharsAndReset() {
      return null;
   }

   public int whitespaceCount() {
      return 0;
   }

   public void reset() {
   }

   public void resetWhitespaceCount() {
   }

   public char[] getChars() {
      return null;
   }

   public void fill(char ch, int length) {
   }

   public void appendIgnoringPadding(char ch, char padding) {
   }

   public void appendIgnoringWhitespaceAndPadding(char ch, char padding) {
   }

   public void prepend(char ch) {
   }

   public void updateWhitespace() {
   }

   public char appendUntil(char ch, CharInput input, char stop) {
      while(ch != stop) {
         ch = input.nextChar();
      }

      return ch;
   }

   public final char appendUntil(char ch, CharInput input, char stop1, char stop2) {
      while(ch != stop1 && ch != stop2) {
         ch = input.nextChar();
      }

      return ch;
   }

   public final char appendUntil(char ch, CharInput input, char stop1, char stop2, char stop3) {
      while(ch != stop1 && ch != stop2 && ch != stop3) {
         ch = input.nextChar();
      }

      return ch;
   }

   public void append(char[] ch, int from, int length) {
   }

   public void prepend(char ch1, char ch2) {
   }

   public void prepend(char[] chars) {
   }

   public void append(char[] ch) {
   }

   public void append(String string) {
   }

   public void append(String string, int from, int to) {
   }

   public char charAt(int i) {
      return '\u0000';
   }

   public CharSequence subSequence(int i, int i1) {
      return null;
   }

   public void append(int ch) {
   }

   public void append(int[] ch) {
   }

   public void append(Object obj) {
   }

   public void ignore(int count) {
   }

   public int indexOf(char ch, int from) {
      return -1;
   }

   public String substring(int from, int length) {
      return null;
   }

   public void remove(int from, int length) {
   }

   public void delete(int count) {
   }

   public int indexOfAny(char[] chars, int from) {
      return -1;
   }

   public int indexOf(char[] charSequence, int from) {
      return -1;
   }

   public int indexOf(CharSequence charSequence, int from) {
      return -1;
   }

   public boolean isEmpty() {
      return true;
   }

   public int lastIndexOf(char ch) {
      return -1;
   }
}
