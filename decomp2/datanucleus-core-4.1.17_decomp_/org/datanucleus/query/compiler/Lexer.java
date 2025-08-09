package org.datanucleus.query.compiler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import org.datanucleus.exceptions.NucleusUserException;

public class Lexer {
   private final String parameterPrefixes;
   private final String input;
   protected final CharacterIterator ci;
   private final boolean parseEscapedChars;

   public Lexer(String input, String paramPrefixes, boolean parseEscapedChars) {
      this.input = input;
      this.parameterPrefixes = paramPrefixes;
      this.parseEscapedChars = parseEscapedChars;
      this.ci = new StringCharacterIterator(input);
   }

   public String getInput() {
      return this.input;
   }

   public int getIndex() {
      return this.ci.getIndex();
   }

   public int skipWS() {
      int startIdx = this.ci.getIndex();

      for(char c = this.ci.current(); Character.isWhitespace(c) || c == '\t' || c == '\f' || c == '\n' || c == '\r' || c == '\t' || c == '\f' || c == ' ' || c == '\t' || c == '\n' || c == '\f' || c == '\r' || c == ' '; c = this.ci.next()) {
      }

      return startIdx;
   }

   public boolean parseEOS() {
      this.skipWS();
      return this.ci.current() == '\uffff';
   }

   public boolean parseChar(char c) {
      this.skipWS();
      if (this.ci.current() == c) {
         this.ci.next();
         return true;
      } else {
         return false;
      }
   }

   public boolean parseChar(char c, char unlessFollowedBy) {
      int savedIdx = this.skipWS();
      if (this.ci.current() == c && this.ci.next() != unlessFollowedBy) {
         return true;
      } else {
         this.ci.setIndex(savedIdx);
         return false;
      }
   }

   public boolean parseString(String s) {
      int savedIdx = this.skipWS();
      int len = s.length();
      char c = this.ci.current();

      for(int i = 0; i < len; ++i) {
         if (c != s.charAt(i)) {
            this.ci.setIndex(savedIdx);
            return false;
         }

         c = this.ci.next();
      }

      return true;
   }

   public boolean parseStringIgnoreCase(String s) {
      String lowerCasedString = s.toLowerCase();
      int savedIdx = this.skipWS();
      int len = lowerCasedString.length();
      char c = this.ci.current();

      for(int i = 0; i < len; ++i) {
         if (Character.toLowerCase(c) != lowerCasedString.charAt(i)) {
            this.ci.setIndex(savedIdx);
            return false;
         }

         c = this.ci.next();
      }

      return true;
   }

   public boolean peekString(String s) {
      int savedIdx = this.skipWS();
      int len = s.length();
      char c = this.ci.current();

      for(int i = 0; i < len; ++i) {
         if (c != s.charAt(i)) {
            this.ci.setIndex(savedIdx);
            return false;
         }

         c = this.ci.next();
      }

      this.ci.setIndex(savedIdx);
      return true;
   }

   public boolean peekStringIgnoreCase(String s) {
      String lowerCasedString = s.toLowerCase();
      int savedIdx = this.skipWS();
      int len = lowerCasedString.length();
      char c = this.ci.current();

      for(int i = 0; i < len; ++i) {
         if (Character.toLowerCase(c) != lowerCasedString.charAt(i)) {
            this.ci.setIndex(savedIdx);
            return false;
         }

         c = this.ci.next();
      }

      this.ci.setIndex(savedIdx);
      return true;
   }

   public String parseIdentifier() {
      this.skipWS();
      char c = this.ci.current();
      if (!Character.isJavaIdentifierStart(c) && this.parameterPrefixes.indexOf(c) < 0) {
         return null;
      } else {
         StringBuilder id = new StringBuilder();
         id.append(c);

         while(Character.isJavaIdentifierPart(c = this.ci.next())) {
            id.append(c);
         }

         return id.toString();
      }
   }

   public String parseMethod() {
      int savedIdx = this.ci.getIndex();
      String id;
      if ((id = this.parseIdentifier()) == null) {
         this.ci.setIndex(savedIdx);
         return null;
      } else {
         this.skipWS();
         if (!this.parseChar('(')) {
            this.ci.setIndex(savedIdx);
            return null;
         } else {
            this.ci.setIndex(this.ci.getIndex() - 1);
            return id;
         }
      }
   }

   public String parseName() {
      int savedIdx = this.skipWS();
      String id;
      if ((id = this.parseIdentifier()) == null) {
         return null;
      } else {
         StringBuilder qn = new StringBuilder(id);

         while(this.parseChar('.')) {
            if ((id = this.parseIdentifier()) == null) {
               this.ci.setIndex(savedIdx);
               return null;
            }

            qn.append('.').append(id);
         }

         return qn.toString();
      }
   }

   public String parseCast() {
      int savedIdx = this.skipWS();
      String typeName;
      if (this.parseChar('(') && (typeName = this.parseName()) != null && this.parseChar(')')) {
         return typeName;
      } else {
         this.ci.setIndex(savedIdx);
         return null;
      }
   }

   private static final boolean isDecDigit(char c) {
      return c >= '0' && c <= '9';
   }

   private static final boolean isOctDigit(char c) {
      return c >= '0' && c <= '7';
   }

   private static final boolean isHexDigit(char c) {
      return c >= '0' && c <= '9' || c >= 'a' && c <= 'f' || c >= 'A' && c <= 'F';
   }

   public BigInteger parseIntegerLiteral() {
      int savedIdx = this.skipWS();
      StringBuilder digits = new StringBuilder();
      char c = this.ci.current();
      boolean negate = false;
      if (c == '-') {
         negate = true;
         c = this.ci.next();
      }

      int radix;
      if (c == '0') {
         c = this.ci.next();
         if (c != 'x' && c != 'X') {
            if (isOctDigit(c)) {
               radix = 8;

               do {
                  digits.append(c);
                  c = this.ci.next();
               } while(isOctDigit(c));
            } else {
               radix = 10;
               digits.append('0');
            }
         } else {
            radix = 16;

            for(c = this.ci.next(); isHexDigit(c); c = this.ci.next()) {
               digits.append(c);
            }
         }
      } else {
         for(radix = 10; isDecDigit(c); c = this.ci.next()) {
            digits.append(c);
         }
      }

      if (digits.length() == 0) {
         this.ci.setIndex(savedIdx);
         return null;
      } else {
         if (c == 'l' || c == 'L') {
            this.ci.next();
         }

         return negate ? (new BigInteger(digits.toString(), radix)).negate() : new BigInteger(digits.toString(), radix);
      }
   }

   public BigDecimal parseFloatingPointLiteral() {
      int savedIdx = this.skipWS();
      StringBuilder val = new StringBuilder();
      boolean dotSeen = false;
      boolean expSeen = false;
      boolean sfxSeen = false;
      char c = this.ci.current();
      boolean negate = false;
      if (c == '-') {
         negate = true;
         c = this.ci.next();
      }

      while(isDecDigit(c)) {
         val.append(c);
         c = this.ci.next();
      }

      if (c == '.') {
         dotSeen = true;
         val.append(c);

         for(c = this.ci.next(); isDecDigit(c); c = this.ci.next()) {
            val.append(c);
         }
      }

      if (val.length() < (dotSeen ? 2 : 1)) {
         this.ci.setIndex(savedIdx);
         return null;
      } else {
         if (c == 'e' || c == 'E') {
            expSeen = true;
            val.append(c);
            c = this.ci.next();
            if (c != '+' && c != '-' && !isDecDigit(c)) {
               this.ci.setIndex(savedIdx);
               return null;
            }

            do {
               val.append(c);
               c = this.ci.next();
            } while(isDecDigit(c));
         }

         if (c == 'f' || c == 'F' || c == 'd' || c == 'D') {
            sfxSeen = true;
            this.ci.next();
         }

         if (!dotSeen && !expSeen && !sfxSeen) {
            this.ci.setIndex(savedIdx);
            return null;
         } else {
            return negate ? (new BigDecimal(val.toString())).negate() : new BigDecimal(val.toString());
         }
      }
   }

   public Boolean parseBooleanLiteral() {
      int savedIdx = this.skipWS();
      String id;
      if ((id = this.parseIdentifier()) == null) {
         return null;
      } else if (id.equals("true")) {
         return Boolean.TRUE;
      } else if (id.equals("false")) {
         return Boolean.FALSE;
      } else {
         this.ci.setIndex(savedIdx);
         return null;
      }
   }

   public Boolean parseBooleanLiteralIgnoreCase() {
      int savedIdx = this.skipWS();
      String id;
      if ((id = this.parseIdentifier()) == null) {
         return null;
      } else if (id.equalsIgnoreCase("true")) {
         return Boolean.TRUE;
      } else if (id.equalsIgnoreCase("false")) {
         return Boolean.FALSE;
      } else {
         this.ci.setIndex(savedIdx);
         return null;
      }
   }

   public boolean nextIsSingleQuote() {
      this.skipWS();
      return this.ci.current() == '\'';
   }

   public boolean nextIsDot() {
      return this.ci.current() == '.';
   }

   public Character parseCharacterLiteral() {
      this.skipWS();
      if (this.ci.current() != '\'') {
         return null;
      } else {
         char c = this.ci.next();
         if (c == '\uffff') {
            throw new NucleusUserException("Invalid character literal: " + this.input);
         } else {
            if (this.parseEscapedChars && c == '\\') {
               c = this.parseEscapedCharacter();
            }

            if (this.ci.next() != '\'') {
               throw new NucleusUserException("Invalid character literal: " + this.input);
            } else {
               this.ci.next();
               return c;
            }
         }
      }
   }

   public String parseStringLiteral() {
      this.skipWS();
      char quote = this.ci.current();
      if (quote != '"' && quote != '\'') {
         return null;
      } else {
         StringBuilder lit;
         char c;
         for(lit = new StringBuilder(); (c = this.ci.next()) != quote; lit.append(c)) {
            if (c == '\uffff') {
               throw new NucleusUserException("Invalid string literal (End of stream): " + this.input);
            }

            if (this.parseEscapedChars && c == '\\') {
               c = this.parseEscapedCharacter();
            }
         }

         this.ci.next();
         return lit.toString();
      }
   }

   private char parseEscapedCharacter() {
      char c;
      if (isOctDigit(c = this.ci.next())) {
         int i = c - 48;
         if (isOctDigit(c = this.ci.next())) {
            i = i * 8 + (c - 48);
            if (isOctDigit(c = this.ci.next())) {
               i = i * 8 + (c - 48);
            } else {
               this.ci.previous();
            }
         } else {
            this.ci.previous();
         }

         if (i > 255) {
            throw new NucleusUserException("Invalid character escape: '\\" + Integer.toOctalString(i) + "'");
         } else {
            return (char)i;
         }
      } else {
         switch (c) {
            case '"':
               return '"';
            case '\'':
               return '\'';
            case '\\':
               return '\\';
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
            default:
               throw new NucleusUserException("Invalid character escape: '\\" + c + "'");
         }
      }
   }

   public boolean parseNullLiteral() {
      int savedIdx = this.skipWS();
      String id;
      if ((id = this.parseIdentifier()) == null) {
         return false;
      } else if (id.equals("null")) {
         return true;
      } else {
         this.ci.setIndex(savedIdx);
         return false;
      }
   }

   public boolean parseNullLiteralIgnoreCase() {
      int savedIdx = this.skipWS();
      String id;
      if ((id = this.parseIdentifier()) == null) {
         return false;
      } else if (id.equalsIgnoreCase("null")) {
         return true;
      } else {
         this.ci.setIndex(savedIdx);
         return false;
      }
   }

   public String remaining() {
      int position = this.ci.getIndex();
      StringBuilder sb = new StringBuilder();

      for(char c = this.ci.current(); c != '\uffff'; c = this.ci.next()) {
         sb.append(c);
      }

      this.ci.setIndex(position);
      return sb.toString();
   }

   public String toString() {
      return this.input;
   }
}
