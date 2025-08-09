package org.codehaus.janino.util.charstream;

import java.io.EOFException;

public class StringCharStream implements CharStream {
   private final String in;
   private int pos;

   public StringCharStream(String in) {
      this.in = in;
   }

   public int peek() {
      return this.pos == this.in.length() ? -1 : this.in.charAt(this.pos);
   }

   public boolean peek(char c) {
      return this.pos < this.in.length() && this.in.charAt(this.pos) == c;
   }

   public int peek(String chars) {
      return this.pos == this.in.length() ? -1 : chars.indexOf(this.in.charAt(this.pos));
   }

   public char read() throws EOFException {
      if (this.pos == this.in.length()) {
         throw new EOFException("Unexpected end-of-input");
      } else {
         return this.in.charAt(this.pos++);
      }
   }

   public void read(char c) throws EOFException, UnexpectedCharacterException {
      if (this.pos == this.in.length()) {
         throw new EOFException("Expected '" + c + "' instead of end-of-input");
      } else if (this.in.charAt(this.pos) != c) {
         throw new UnexpectedCharacterException("'" + c + "' expected instead of '" + this.in.substring(this.pos) + "'");
      } else {
         ++this.pos;
      }
   }

   public int read(String chars) throws EOFException, UnexpectedCharacterException {
      if (this.pos == this.in.length()) {
         throw new EOFException("Expected one of '" + chars + "' instead of end-of-input");
      } else {
         int res = chars.indexOf(this.in.charAt(this.pos));
         if (res == -1) {
            throw new UnexpectedCharacterException("One of '" + chars + "' expected instead of '" + this.in.charAt(this.pos) + "'");
         } else {
            ++this.pos;
            return res;
         }
      }
   }

   public boolean peekRead(char c) {
      if (this.pos >= this.in.length()) {
         return false;
      } else if (this.in.charAt(this.pos) == c) {
         ++this.pos;
         return true;
      } else {
         return false;
      }
   }

   public int peekRead(String chars) {
      if (this.pos >= this.in.length()) {
         return -1;
      } else {
         int res = chars.indexOf(this.in.charAt(this.pos));
         if (res != -1) {
            ++this.pos;
         }

         return res;
      }
   }

   public void eoi() throws UnexpectedCharacterException {
      if (this.pos < this.in.length()) {
         throw new UnexpectedCharacterException("Unexpected trailing characters '" + this.in.substring(this.pos) + "'");
      }
   }

   public boolean atEoi() {
      return this.pos >= this.in.length();
   }

   public String toString() {
      return "'" + this.in + "' at offset " + this.pos;
   }
}
