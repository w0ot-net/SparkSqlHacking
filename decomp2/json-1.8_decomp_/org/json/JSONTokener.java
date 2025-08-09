package org.json;

import java.io.IOException;
import java.io.Reader;
import java.util.Collection;

public class JSONTokener {
   private final String in;
   private int pos;

   public JSONTokener(String in) {
      if (in != null && in.startsWith("\ufeff")) {
         in = in.substring(1);
      }

      this.in = in;
   }

   public JSONTokener(Reader input) throws IOException {
      StringBuilder s = new StringBuilder();
      char[] readBuf = new char[102400];

      for(int n = input.read(readBuf); n >= 0; n = input.read(readBuf)) {
         s.append(readBuf, 0, n);
      }

      this.in = s.toString();
      this.pos = 0;
   }

   public Object nextValue() throws JSONException {
      int c = this.nextCleanInternal();
      switch (c) {
         case -1:
            throw this.syntaxError("End of input");
         case 34:
         case 39:
            return this.nextString((char)c);
         case 91:
            return this.readArray();
         case 123:
            return this.readObject();
         default:
            --this.pos;
            return this.readLiteral();
      }
   }

   private int nextCleanInternal() throws JSONException {
      while(this.pos < this.in.length()) {
         int c = this.in.charAt(this.pos++);
         switch (c) {
            case 9:
            case 10:
            case 13:
            case 32:
               break;
            case 35:
               this.skipToEndOfLine();
               break;
            case 47:
               if (this.pos == this.in.length()) {
                  return c;
               }

               char peek = this.in.charAt(this.pos);
               switch (peek) {
                  case '*':
                     ++this.pos;
                     int commentEnd = this.in.indexOf("*/", this.pos);
                     if (commentEnd == -1) {
                        throw this.syntaxError("Unterminated comment");
                     }

                     this.pos = commentEnd + 2;
                     continue;
                  case '/':
                     ++this.pos;
                     this.skipToEndOfLine();
                     continue;
                  default:
                     return c;
               }
            default:
               return c;
         }
      }

      return -1;
   }

   private void skipToEndOfLine() {
      while(true) {
         if (this.pos < this.in.length()) {
            char c = this.in.charAt(this.pos);
            if (c != '\r' && c != '\n') {
               ++this.pos;
               continue;
            }

            ++this.pos;
         }

         return;
      }
   }

   public String nextString(char quote) throws JSONException {
      StringBuilder builder = null;
      int start = this.pos;

      while(this.pos < this.in.length()) {
         int c = this.in.charAt(this.pos++);
         if (c == quote) {
            if (builder == null) {
               return new String(this.in.substring(start, this.pos - 1));
            }

            builder.append(this.in, start, this.pos - 1);
            return builder.toString();
         }

         if (c == 92) {
            if (this.pos == this.in.length()) {
               throw this.syntaxError("Unterminated escape sequence");
            }

            if (builder == null) {
               builder = new StringBuilder();
            }

            builder.append(this.in, start, this.pos - 1);
            builder.append(this.readEscapeCharacter());
            start = this.pos;
         }
      }

      throw this.syntaxError("Unterminated string");
   }

   private char readEscapeCharacter() throws JSONException {
      char escaped = this.in.charAt(this.pos++);
      switch (escaped) {
         case '"':
         case '\'':
         case '\\':
         default:
            return escaped;
         case 'b':
            return '\b';
         case 'f':
            return '\f';
         case 'n':
            return '\n';
         case 'r':
            return '\r';
         case 't':
            return '\t';
         case 'u':
            if (this.pos + 4 > this.in.length()) {
               throw this.syntaxError("Unterminated escape sequence");
            } else {
               String hex = this.in.substring(this.pos, this.pos + 4);
               this.pos += 4;

               try {
                  return (char)Integer.parseInt(hex, 16);
               } catch (NumberFormatException var4) {
                  throw this.syntaxError("Invalid escape sequence: " + hex);
               }
            }
      }
   }

   private Object readLiteral() throws JSONException {
      String literal = this.nextToInternal("{}[]/\\:,=;# \t\f");
      if (literal.length() == 0) {
         throw this.syntaxError("Expected literal value");
      } else if ("null".equalsIgnoreCase(literal)) {
         return JSONObject.NULL;
      } else if ("true".equalsIgnoreCase(literal)) {
         return Boolean.TRUE;
      } else if ("false".equalsIgnoreCase(literal)) {
         return Boolean.FALSE;
      } else {
         if (literal.indexOf(46) == -1) {
            int base = 10;
            String number = literal;
            if (!literal.startsWith("0x") && !literal.startsWith("0X")) {
               if (literal.startsWith("0") && literal.length() > 1) {
                  number = literal.substring(1);
                  base = 8;
               }
            } else {
               number = literal.substring(2);
               base = 16;
            }

            try {
               long longValue = Long.parseLong(number, base);
               if (longValue <= 2147483647L && longValue >= -2147483648L) {
                  return (int)longValue;
               }

               return longValue;
            } catch (NumberFormatException var7) {
            }
         }

         try {
            return Double.valueOf(literal);
         } catch (NumberFormatException var6) {
            return new String(literal);
         }
      }
   }

   private String nextToInternal(String excluded) {
      int start;
      for(start = this.pos; this.pos < this.in.length(); ++this.pos) {
         char c = this.in.charAt(this.pos);
         if (c == '\r' || c == '\n' || excluded.indexOf(c) != -1) {
            return this.in.substring(start, this.pos);
         }
      }

      return this.in.substring(start);
   }

   private JSONObject readObject() throws JSONException {
      JSONObject result = new JSONObject();
      int first = this.nextCleanInternal();
      if (first == 125) {
         return result;
      } else {
         if (first != -1) {
            --this.pos;
         }

         while(true) {
            Object name = this.nextValue();
            if (!(name instanceof String)) {
               if (name == null) {
                  throw this.syntaxError("Names cannot be null");
               }

               throw this.syntaxError("Names must be strings, but " + name + " is of type " + name.getClass().getName());
            }

            int separator = this.nextCleanInternal();
            if (separator != 58 && separator != 61) {
               throw this.syntaxError("Expected ':' after " + name);
            }

            if (this.pos < this.in.length() && this.in.charAt(this.pos) == '>') {
               ++this.pos;
            }

            result.put((String)name, this.nextValue());
            switch (this.nextCleanInternal()) {
               case 44:
               case 59:
                  break;
               case 125:
                  return result;
               default:
                  throw this.syntaxError("Unterminated object");
            }
         }
      }
   }

   private JSONArray readArray() throws JSONException {
      JSONArray result = new JSONArray();
      boolean hasTrailingSeparator = false;

      while(true) {
         switch (this.nextCleanInternal()) {
            case -1:
               throw this.syntaxError("Unterminated array");
            case 44:
            case 59:
               result.put((Collection)null);
               hasTrailingSeparator = true;
               break;
            case 93:
               if (hasTrailingSeparator) {
                  result.put((Collection)null);
               }

               return result;
            default:
               --this.pos;
               result.put(this.nextValue());
               switch (this.nextCleanInternal()) {
                  case 44:
                  case 59:
                     hasTrailingSeparator = true;
                     break;
                  case 93:
                     return result;
                  default:
                     throw this.syntaxError("Unterminated array");
               }
         }
      }
   }

   public JSONException syntaxError(String message) {
      return new JSONException(message + this);
   }

   public String toString() {
      return " at character " + this.pos + " of " + this.in;
   }

   public boolean more() {
      return this.pos < this.in.length();
   }

   public char next() {
      return this.pos < this.in.length() ? this.in.charAt(this.pos++) : '\u0000';
   }

   public char next(char c) throws JSONException {
      char result = this.next();
      if (result != c) {
         throw this.syntaxError("Expected " + c + " but was " + result);
      } else {
         return result;
      }
   }

   public char nextClean() throws JSONException {
      int nextCleanInt = this.nextCleanInternal();
      return nextCleanInt == -1 ? '\u0000' : (char)nextCleanInt;
   }

   public String next(int length) throws JSONException {
      if (this.pos + length > this.in.length()) {
         throw this.syntaxError(length + " is out of bounds");
      } else {
         String result = this.in.substring(this.pos, this.pos + length);
         this.pos += length;
         return result;
      }
   }

   public String nextTo(String excluded) {
      if (excluded == null) {
         throw new NullPointerException("excluded == null");
      } else {
         return this.nextToInternal(excluded).trim();
      }
   }

   public String nextTo(char excluded) {
      return this.nextToInternal(String.valueOf(excluded)).trim();
   }

   public void skipPast(String thru) {
      int thruStart = this.in.indexOf(thru, this.pos);
      this.pos = thruStart == -1 ? this.in.length() : thruStart + thru.length();
   }

   public char skipTo(char to) {
      int index = this.in.indexOf(to, this.pos);
      if (index != -1) {
         this.pos = index;
         return to;
      } else {
         return '\u0000';
      }
   }

   public void back() {
      if (--this.pos == -1) {
         this.pos = 0;
      }

   }

   public static int dehexchar(char hex) {
      if (hex >= '0' && hex <= '9') {
         return hex - 48;
      } else if (hex >= 'A' && hex <= 'F') {
         return hex - 65 + 10;
      } else {
         return hex >= 'a' && hex <= 'f' ? hex - 97 + 10 : -1;
      }
   }
}
