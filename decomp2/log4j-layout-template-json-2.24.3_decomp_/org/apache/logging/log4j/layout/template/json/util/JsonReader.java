package org.apache.logging.log4j.layout.template.json.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class JsonReader {
   private CharacterIterator it;
   private int readCharIndex = -1;
   private char readChar;
   private int readTokenStartIndex = -1;
   private Object readToken;
   private final StringBuilder buffer = new StringBuilder();

   private JsonReader() {
   }

   public static Object read(final String json) {
      Objects.requireNonNull(json, "json");
      JsonReader reader = new JsonReader();
      return reader.read((CharacterIterator)(new StringCharacterIterator(json)));
   }

   private Object read(final CharacterIterator ci) {
      this.it = ci;
      this.readCharIndex = 0;
      this.readChar = this.it.first();
      Object token = this.readToken();
      if (token instanceof Delimiter) {
         String message = String.format("was not expecting %s at index %d", this.readToken, this.readTokenStartIndex);
         throw new IllegalArgumentException(message);
      } else {
         this.skipWhiteSpace();
         if (this.it.getIndex() != this.it.getEndIndex()) {
            String message = String.format("was not expecting input at index %d: %c", this.readCharIndex, this.readChar);
            throw new IllegalArgumentException(message);
         } else {
            return token;
         }
      }
   }

   private Object readToken() {
      this.skipWhiteSpace();
      this.readTokenStartIndex = this.readCharIndex;
      char prevChar = this.readChar;
      this.readChar();
      switch (prevChar) {
         case '"':
            this.readToken = this.readString();
            break;
         case ',':
            this.readToken = JsonReader.Delimiter.COMMA;
            break;
         case ':':
            this.readToken = JsonReader.Delimiter.COLON;
            break;
         case '[':
            this.readToken = this.readArray();
            break;
         case ']':
            this.readToken = JsonReader.Delimiter.ARRAY_END;
            break;
         case 'f':
            this.readToken = this.readFalse();
            break;
         case 'n':
            this.readToken = this.readNull();
            break;
         case 't':
            this.readToken = this.readTrue();
            break;
         case '{':
            this.readToken = this.readObject();
            break;
         case '}':
            this.readToken = JsonReader.Delimiter.OBJECT_END;
            break;
         default:
            this.unreadChar();
            if (!Character.isDigit(this.readChar) && this.readChar != '-') {
               String message = String.format("invalid character at index %d: %c", this.readCharIndex, this.readChar);
               throw new IllegalArgumentException(message);
            }

            this.readToken = this.readNumber();
      }

      return this.readToken;
   }

   private void skipWhiteSpace() {
      while(Character.isWhitespace(this.readChar) && this.readChar() != '\uffff') {
      }

   }

   private char readChar() {
      if (this.it.getIndex() == this.it.getEndIndex()) {
         throw new IllegalArgumentException("premature end of input");
      } else {
         this.readChar = this.it.next();
         this.readCharIndex = this.it.getIndex();
         return this.readChar;
      }
   }

   private void unreadChar() {
      this.readChar = this.it.previous();
      this.readCharIndex = this.it.getIndex();
   }

   private String readString() {
      this.buffer.setLength(0);

      while(this.readChar != '"') {
         if (this.readChar == '\\') {
            this.readChar();
            if (this.readChar == 'u') {
               char unicodeChar = this.readUnicodeChar();
               this.bufferChar(unicodeChar);
            } else {
               switch (this.readChar) {
                  case '"':
                  case '\\':
                     this.bufferReadChar();
                     break;
                  case 'b':
                     this.bufferChar('\b');
                     break;
                  case 'f':
                     this.bufferChar('\f');
                     break;
                  case 'n':
                     this.bufferChar('\n');
                     break;
                  case 'r':
                     this.bufferChar('\r');
                     break;
                  case 't':
                     this.bufferChar('\t');
                     break;
                  default:
                     String message = String.format("was expecting an escape character at index %d: %c", this.readCharIndex, this.readChar);
                     throw new IllegalArgumentException(message);
               }
            }
         } else {
            this.bufferReadChar();
         }
      }

      this.readChar();
      return this.buffer.toString();
   }

   private void bufferReadChar() {
      this.bufferChar(this.readChar);
   }

   private void bufferChar(final char c) {
      this.buffer.append(c);
      this.readChar();
   }

   private char readUnicodeChar() {
      int value = 0;

      for(int i = 0; i < 4; ++i) {
         this.readChar();
         if (this.readChar >= '0' && this.readChar <= '9') {
            value = (value << 4) + this.readChar - 48;
         } else if (this.readChar >= 'a' && this.readChar <= 'f') {
            value = (value << 4) + (this.readChar - 97) + 10;
         } else {
            if (this.readChar < 'A' || this.readChar > 'F') {
               String message = String.format("was expecting a unicode character at index %d: %c", this.readCharIndex, this.readChar);
               throw new IllegalArgumentException(message);
            }

            value = (value << 4) + (this.readChar - 65) + 10;
         }
      }

      return (char)value;
   }

   private Map readObject() {
      Map<String, Object> object = new LinkedHashMap();
      String key = this.readObjectKey();

      while(this.readToken != JsonReader.Delimiter.OBJECT_END) {
         this.expectDelimiter(JsonReader.Delimiter.COLON, this.readToken());
         if (this.readToken != JsonReader.Delimiter.OBJECT_END) {
            Object value = this.readToken();
            object.put(key, value);
            if (this.readToken() == JsonReader.Delimiter.COMMA) {
               key = this.readObjectKey();
               if (key == null || JsonReader.Delimiter.exists(key)) {
                  String message = String.format("was expecting an object key at index %d: %s", this.readTokenStartIndex, this.readToken);
                  throw new IllegalArgumentException(message);
               }

               if (object.containsKey(key)) {
                  String message = String.format("found duplicate object key at index %d: %s", this.readTokenStartIndex, key);
                  throw new IllegalArgumentException(message);
               }
            } else {
               this.expectDelimiter(JsonReader.Delimiter.OBJECT_END, this.readToken);
            }
         }
      }

      return object;
   }

   private List readArray() {
      List<Object> array = new LinkedList();
      this.readToken();

      while(this.readToken != JsonReader.Delimiter.ARRAY_END) {
         if (this.readToken instanceof Delimiter) {
            String message = String.format("was expecting an array element at index %d: %s", this.readTokenStartIndex, this.readToken);
            throw new IllegalArgumentException(message);
         }

         array.add(this.readToken);
         if (this.readToken() == JsonReader.Delimiter.COMMA) {
            if (this.readToken() == JsonReader.Delimiter.ARRAY_END) {
               String message = String.format("was expecting an array element at index %d: %s", this.readTokenStartIndex, this.readToken);
               throw new IllegalArgumentException(message);
            }
         } else {
            this.expectDelimiter(JsonReader.Delimiter.ARRAY_END, this.readToken);
         }
      }

      return array;
   }

   private String readObjectKey() {
      this.readToken();
      if (this.readToken == JsonReader.Delimiter.OBJECT_END) {
         return null;
      } else if (this.readToken instanceof String) {
         return (String)this.readToken;
      } else {
         String message = String.format("was expecting an object key at index %d: %s", this.readTokenStartIndex, this.readToken);
         throw new IllegalArgumentException(message);
      }
   }

   private void expectDelimiter(final Delimiter expectedDelimiter, final Object actualToken) {
      if (!expectedDelimiter.equals(actualToken)) {
         String message = String.format("was expecting %s at index %d: %s", expectedDelimiter, this.readTokenStartIndex, actualToken);
         throw new IllegalArgumentException(message);
      }
   }

   private boolean readTrue() {
      if (this.readChar == 'r' && this.readChar() == 'u' && this.readChar() == 'e') {
         this.readChar();
         return true;
      } else {
         String message = String.format("was expecting keyword 'true' at index %d: %s", this.readCharIndex, this.readChar);
         throw new IllegalArgumentException(message);
      }
   }

   private boolean readFalse() {
      if (this.readChar == 'a' && this.readChar() == 'l' && this.readChar() == 's' && this.readChar() == 'e') {
         this.readChar();
         return false;
      } else {
         String message = String.format("was expecting keyword 'false' at index %d: %s", this.readCharIndex, this.readChar);
         throw new IllegalArgumentException(message);
      }
   }

   private Object readNull() {
      if (this.readChar == 'u' && this.readChar() == 'l' && this.readChar() == 'l') {
         this.readChar();
         return null;
      } else {
         String message = String.format("was expecting keyword 'null' at index %d: %s", this.readCharIndex, this.readChar);
         throw new IllegalArgumentException(message);
      }
   }

   private Number readNumber() {
      this.buffer.setLength(0);
      if (this.readChar == '-') {
         this.bufferReadChar();
      }

      boolean floatingPoint = false;
      this.bufferDigits();
      if (this.readChar == '.') {
         this.bufferReadChar();
         this.bufferDigits();
         floatingPoint = true;
      }

      if (this.readChar == 'e' || this.readChar == 'E') {
         floatingPoint = true;
         this.bufferReadChar();
         if (this.readChar == '+' || this.readChar == '-') {
            this.bufferReadChar();
         }

         this.bufferDigits();
      }

      String string = this.buffer.toString();
      if (floatingPoint) {
         return new BigDecimal(string);
      } else {
         BigInteger bigInteger = new BigInteger(string);

         try {
            return bigInteger.intValueExact();
         } catch (ArithmeticException var7) {
            try {
               return bigInteger.longValueExact();
            } catch (ArithmeticException var6) {
               return bigInteger;
            }
         }
      }
   }

   private void bufferDigits() {
      boolean found = false;

      while(Character.isDigit(this.readChar)) {
         found = true;
         this.bufferReadChar();
      }

      if (!found) {
         String message = String.format("was expecting a digit at index %d: %c", this.readCharIndex, this.readChar);
         throw new IllegalArgumentException(message);
      }
   }

   private static enum Delimiter {
      OBJECT_START("{"),
      OBJECT_END("}"),
      ARRAY_START("["),
      ARRAY_END("]"),
      COLON(":"),
      COMMA(",");

      private final String string;

      private Delimiter(final String string) {
         this.string = string;
      }

      private static boolean exists(final Object token) {
         for(Delimiter delimiter : values()) {
            if (delimiter.string.equals(token)) {
               return true;
            }
         }

         return false;
      }

      // $FF: synthetic method
      private static Delimiter[] $values() {
         return new Delimiter[]{OBJECT_START, OBJECT_END, ARRAY_START, ARRAY_END, COLON, COMMA};
      }
   }
}
