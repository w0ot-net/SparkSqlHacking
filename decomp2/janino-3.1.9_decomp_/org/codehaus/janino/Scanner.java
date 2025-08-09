package org.codehaus.janino;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.compiler.io.Readers;
import org.codehaus.commons.nullanalysis.Nullable;

public class Scanner {
   public static final String SYSTEM_PROPERTY_SOURCE_DEBUGGING_ENABLE = "org.codehaus.janino.source_debugging.enable";
   public static final String SYSTEM_PROPERTY_SOURCE_DEBUGGING_DIR = "org.codehaus.janino.source_debugging.dir";
   public static final String SYSTEM_PROPERTY_SOURCE_DEBUGGING_KEEP = "org.codehaus.janino.source_debugging.keep";
   private final StringBuilder sb;
   @Nullable
   private final String fileName;
   private final Reader in;
   private boolean ignoreWhiteSpace;
   private int nextChar;
   private int nextButOneChar;
   private boolean crLfPending;
   private int nextCharLineNumber;
   private int nextCharColumnNumber;
   private int tokenLineNumber;
   private int tokenColumnNumber;
   private static final Set JAVA_KEYWORDS = new HashSet(Arrays.asList("abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while"));
   private static final Set JAVA_OPERATORS = new HashSet(Arrays.asList("(", ")", "{", "}", "[", "]", ";", ",", ".", "@", "::", "=", ">", "<", "!", "~", "?", ":", "->", "==", "<=", ">=", "!=", "&&", "||", "++", "--", "+", "-", "*", "/", "&", "|", "^", "%", "<<", ">>", ">>>", "+=", "-=", "*=", "/=", "&=", "|=", "^=", "%=", "<<=", ">>=", ">>>="));

   /** @deprecated */
   @Deprecated
   public Scanner(String fileName) throws IOException {
      this((String)fileName, (InputStream)(new FileInputStream(fileName)));
   }

   /** @deprecated */
   @Deprecated
   public Scanner(String fileName, String encoding) throws IOException {
      this(fileName, new FileInputStream(fileName), encoding);
   }

   /** @deprecated */
   @Deprecated
   public Scanner(File file) throws IOException {
      this(file.getAbsolutePath(), new FileInputStream(file), (String)null);
   }

   /** @deprecated */
   @Deprecated
   public Scanner(File file, @Nullable String encoding) throws IOException {
      this(file.getAbsolutePath(), new FileInputStream(file), encoding);
   }

   public Scanner(@Nullable String fileName, InputStream is) throws IOException {
      this(fileName, new InputStreamReader(is), 1, 0);
   }

   public Scanner(@Nullable String fileName, InputStream is, @Nullable String encoding) throws IOException {
      this(fileName, encoding == null ? new InputStreamReader(is) : new InputStreamReader(is, encoding), 1, 0);
   }

   public Scanner(@Nullable String fileName, Reader in) throws IOException {
      this(fileName, in, 1, 0);
   }

   public Scanner(@Nullable String fileName, Reader in, int initialLineNumber, int initialColumnNumber) throws IOException {
      this.sb = new StringBuilder();
      this.nextChar = -1;
      this.nextButOneChar = -1;
      if (fileName == null && Boolean.getBoolean("org.codehaus.janino.source_debugging.enable")) {
         String dirName = System.getProperty("org.codehaus.janino.source_debugging.dir");
         boolean keep = Boolean.getBoolean("org.codehaus.janino.source_debugging.keep");
         File dir = dirName == null ? null : new File(dirName);
         File temporaryFile = File.createTempFile("janino", ".java", dir);
         if (!keep) {
            temporaryFile.deleteOnExit();
         }

         in = Readers.teeReader(in, new FileWriter(temporaryFile), true);
         fileName = temporaryFile.getAbsolutePath();
      }

      this.fileName = fileName;
      this.in = new UnicodeUnescapeReader(in);
      this.nextCharLineNumber = initialLineNumber;
      this.nextCharColumnNumber = initialColumnNumber;
   }

   public void setIgnoreWhiteSpace(boolean value) {
      this.ignoreWhiteSpace = value;
   }

   @Nullable
   public String getFileName() {
      return this.fileName;
   }

   /** @deprecated */
   @Deprecated
   public void close() throws IOException {
      this.in.close();
   }

   public Location location() {
      return new Location(this.fileName, this.tokenLineNumber, this.tokenColumnNumber);
   }

   private Token token(TokenType type, String value) {
      return new Token(this.fileName, this.tokenLineNumber, this.tokenColumnNumber, type, value);
   }

   public Token produce() throws CompileException, IOException {
      if (this.peek() == -1) {
         return this.token(TokenType.END_OF_INPUT, "end-of-input");
      } else {
         if (this.ignoreWhiteSpace && Character.isWhitespace(this.peek())) {
            do {
               this.read();
               if (this.peek() == -1) {
                  return this.token(TokenType.END_OF_INPUT, "end-of-input");
               }
            } while(Character.isWhitespace(this.peek()));
         }

         this.tokenLineNumber = this.nextCharLineNumber;
         this.tokenColumnNumber = this.nextCharColumnNumber;
         this.sb.setLength(0);
         TokenType tokenType = this.scan();
         String tokenValue = this.sb.toString();
         if (tokenType == TokenType.KEYWORD || tokenType == TokenType.BOOLEAN_LITERAL || tokenType == TokenType.NULL_LITERAL || tokenType == TokenType.OPERATOR) {
            tokenValue = tokenValue.intern();
         }

         return this.token(tokenType, tokenValue);
      }
   }

   private TokenType scan() throws CompileException, IOException {
      if (Character.isWhitespace(this.peek())) {
         do {
            this.read();
         } while(this.peek() != -1 && Character.isWhitespace(this.peek()));

         return TokenType.WHITE_SPACE;
      } else if (this.peekRead(47)) {
         if (this.peekRead(-1)) {
            return TokenType.OPERATOR;
         } else if (this.peekRead(61)) {
            return TokenType.OPERATOR;
         } else if (!this.peekRead(47)) {
            if (this.peekRead(42)) {
               boolean asteriskPending = false;

               while(this.peek() != -1) {
                  char c = this.read();
                  if (asteriskPending) {
                     if (c == '/') {
                        return TokenType.C_STYLE_COMMENT;
                     }

                     if (c != '*') {
                        asteriskPending = false;
                     }
                  } else if (c == '*') {
                     asteriskPending = true;
                  }
               }

               throw new CompileException("Unexpected end-of-input in C-style comment", this.location());
            } else {
               return TokenType.OPERATOR;
            }
         } else {
            while(!this.peek("\r\n")) {
               this.read();
            }

            return TokenType.C_PLUS_PLUS_STYLE_COMMENT;
         }
      } else if (Character.isJavaIdentifierStart((char)this.peek())) {
         this.read();

         while(Character.isJavaIdentifierPart((char)this.peek())) {
            this.read();
         }

         String s = this.sb.toString();
         if ("true".equals(s)) {
            return TokenType.BOOLEAN_LITERAL;
         } else if ("false".equals(s)) {
            return TokenType.BOOLEAN_LITERAL;
         } else if ("null".equals(s)) {
            return TokenType.NULL_LITERAL;
         } else {
            return JAVA_KEYWORDS.contains(s) ? TokenType.KEYWORD : TokenType.IDENTIFIER;
         }
      } else if (!Character.isDigit((char)this.peek()) && (this.peek() != 46 || !Character.isDigit(this.peekButOne()))) {
         if (this.peekRead(34)) {
            while(!this.peekRead(34)) {
               this.scanLiteralCharacter();
            }

            return TokenType.STRING_LITERAL;
         } else if (this.peekRead(39)) {
            if (this.peek() == 39) {
               throw new CompileException("Single quote must be backslash-escaped in character literal", this.location());
            } else {
               this.scanLiteralCharacter();
               if (!this.peekRead(39)) {
                  throw new CompileException("Closing single quote missing", this.location());
               } else {
                  return TokenType.CHARACTER_LITERAL;
               }
            }
         } else if (!JAVA_OPERATORS.contains(String.valueOf((char)this.peek()))) {
            throw new CompileException("Invalid character input \"" + (char)this.peek() + "\" (character code " + this.peek() + ")", this.location());
         } else {
            do {
               this.read();
            } while(JAVA_OPERATORS.contains(this.sb.toString() + (char)this.peek()));

            return TokenType.OPERATOR;
         }
      } else {
         return this.scanNumericLiteral();
      }
   }

   private TokenType scanNumericLiteral() throws CompileException, IOException {
      if (this.peekRead(48)) {
         if (isOctalDigit(this.peek()) || this.peek() == 95 && (this.peekButOne() == 95 || isOctalDigit(this.peekButOne()))) {
            this.read();

            while(isOctalDigit(this.peek()) || this.peek() == 95 && (this.peekButOne() == 95 || isOctalDigit(this.peekButOne()))) {
               this.read();
            }

            if (this.peek("89")) {
               throw new CompileException("Digit '" + (char)this.peek() + "' not allowed in octal literal", this.location());
            } else {
               return this.peekRead("lL") ? TokenType.INTEGER_LITERAL : TokenType.INTEGER_LITERAL;
            }
         } else if (this.peekRead("lL")) {
            return TokenType.INTEGER_LITERAL;
         } else if (this.peekRead("fFdD")) {
            return TokenType.FLOATING_POINT_LITERAL;
         } else if (this.peek(".Ee")) {
            if (this.peekRead(46)) {
               while(isDecimalDigit(this.peek()) || this.peek() == 95 && (this.peekButOne() == 95 || isDecimalDigit(this.peekButOne()))) {
                  this.read();
               }
            }

            if (this.peekRead("eE")) {
               this.peekRead("-+");
               if (!isDecimalDigit(this.peek())) {
                  throw new CompileException("Exponent missing after \"E\"", this.location());
               }

               this.read();

               while(isDecimalDigit(this.peek()) || this.peek() == 95 && (this.peekButOne() == 95 || isDecimalDigit(this.peekButOne()))) {
                  this.read();
               }
            }

            this.peekRead("fFdD");
            return TokenType.FLOATING_POINT_LITERAL;
         } else if (this.peekRead("xX")) {
            while(isHexDigit(this.peek())) {
               this.read();
            }

            while(isHexDigit(this.peek()) || this.peek() == 95 && (this.peekButOne() == 95 || isHexDigit(this.peekButOne()))) {
               this.read();
            }

            if (!this.peek(".pP")) {
               return this.peekRead("lL") ? TokenType.INTEGER_LITERAL : TokenType.INTEGER_LITERAL;
            } else {
               if (this.peekRead(46) && isHexDigit(this.peek())) {
                  this.read();

                  while(isHexDigit(this.peek()) || this.peek() == 95 && (this.peekButOne() == 95 || isHexDigit(this.peekButOne()))) {
                     this.read();
                  }
               }

               if (!this.peekRead("pP")) {
                  throw new CompileException("\"p\" missing in hexadecimal floating-point literal", this.location());
               } else {
                  this.peekRead("-+");
                  if (!isDecimalDigit(this.peek())) {
                     throw new CompileException("Unexpected character \"" + (char)this.peek() + "\" in hexadecimal floating point literal", this.location());
                  } else {
                     this.read();

                     while(isDecimalDigit(this.peek()) || this.peek() == 95 && (isDecimalDigit(this.peekButOne()) || this.peekButOne() == 95)) {
                        this.read();
                     }

                     this.peekRead("fFdD");
                     return TokenType.FLOATING_POINT_LITERAL;
                  }
               }
            }
         } else if (!this.peekRead("bB")) {
            return TokenType.INTEGER_LITERAL;
         } else if (!isBinaryDigit(this.peek())) {
            throw new CompileException("Binary digit expected after \"0b\"", this.location());
         } else {
            this.read();

            while(isBinaryDigit(this.peek()) || this.peek() == 95 && (this.peekButOne() == 95 || isBinaryDigit(this.peekButOne()))) {
               this.read();
            }

            return this.peekRead("lL") ? TokenType.INTEGER_LITERAL : TokenType.INTEGER_LITERAL;
         }
      } else {
         if (this.peek() != 46 || !isDecimalDigit(this.peekButOne())) {
            if (!isDecimalDigit(this.peek())) {
               throw new CompileException("Numeric literal begins with invalid character '" + (char)this.peek() + "'", this.location());
            }

            this.read();

            while(isDecimalDigit(this.peek()) || this.peek() == 95 && (this.peekButOne() == 95 || isDecimalDigit(this.peekButOne()))) {
               this.read();
            }

            if (this.peekRead("lL")) {
               return TokenType.INTEGER_LITERAL;
            }

            if (this.peekRead("fFdD")) {
               return TokenType.FLOATING_POINT_LITERAL;
            }

            if (!this.peek(".eE")) {
               return TokenType.INTEGER_LITERAL;
            }
         }

         if (this.peekRead(46) && isDecimalDigit(this.peek())) {
            this.read();

            while(isDecimalDigit(this.peek()) || this.peek() == 95 && (this.peekButOne() == 95 || isDecimalDigit(this.peekButOne()))) {
               this.read();
            }
         }

         if (this.peekRead("eE")) {
            this.peekRead("-+");
            if (!isDecimalDigit(this.peek())) {
               throw new CompileException("Exponent missing after \"E\"", this.location());
            }

            this.read();

            while(isDecimalDigit(this.peek()) || this.peek() == 95 && (this.peekButOne() == 95 || isDecimalDigit(this.peekButOne()))) {
               this.read();
            }
         }

         this.peekRead("fFdD");
         return TokenType.FLOATING_POINT_LITERAL;
      }
   }

   private static boolean isDecimalDigit(int c) {
      return c >= 48 && c <= 57;
   }

   private static boolean isHexDigit(int c) {
      return c >= 48 && c <= 57 || c >= 65 && c <= 70 || c >= 97 && c <= 102;
   }

   private static boolean isOctalDigit(int c) {
      return c >= 48 && c <= 55;
   }

   private static boolean isBinaryDigit(int c) {
      return c == 48 || c == 49;
   }

   private void scanLiteralCharacter() throws CompileException, IOException {
      if (this.peek() == -1) {
         throw new CompileException("EOF in literal", this.location());
      } else if (this.peek() != 13 && this.peek() != 10) {
         if (!this.peekRead(92)) {
            this.read();
         } else {
            int idx = "btnfr\"'\\".indexOf(this.peek());
            if (idx != -1) {
               this.read();
            } else if (this.peek("01234567")) {
               idx = this.read();
               if (this.peekRead("01234567")) {
                  if (this.peekRead("01234567")) {
                     if ("0123".indexOf(idx) == -1) {
                        throw new CompileException("Invalid octal escape", this.location());
                     }
                  }
               }
            } else {
               throw new CompileException("Invalid escape sequence", this.location());
            }
         }
      } else {
         throw new CompileException("Line break in literal not allowed", this.location());
      }
   }

   private int peek() throws CompileException, IOException {
      if (this.nextChar != -1) {
         return this.nextChar;
      } else {
         try {
            return this.nextChar = this.internalRead();
         } catch (UnicodeUnescapeException ex) {
            throw new CompileException(ex.getMessage(), this.location(), ex);
         }
      }
   }

   private boolean peek(String expectedCharacters) throws CompileException, IOException {
      return expectedCharacters.indexOf((char)this.peek()) != -1;
   }

   private int peekButOne() throws CompileException, IOException {
      if (this.nextButOneChar != -1) {
         return this.nextButOneChar;
      } else {
         this.peek();

         try {
            return this.nextButOneChar = this.internalRead();
         } catch (UnicodeUnescapeException ex) {
            throw new CompileException(ex.getMessage(), this.location(), ex);
         }
      }
   }

   private char read() throws CompileException, IOException {
      this.peek();
      if (this.nextChar == -1) {
         throw new CompileException("Unexpected end-of-input", this.location());
      } else {
         char result = (char)this.nextChar;
         this.sb.append(result);
         this.nextChar = this.nextButOneChar;
         this.nextButOneChar = -1;
         return result;
      }
   }

   private boolean peekRead(int expected) throws CompileException, IOException {
      if (this.peek() == expected) {
         if (this.nextChar != -1) {
            this.sb.append((char)this.nextChar);
         }

         this.nextChar = this.nextButOneChar;
         this.nextButOneChar = -1;
         return true;
      } else {
         return false;
      }
   }

   private boolean peekRead(String expectedCharacters) throws CompileException, IOException {
      if (this.peek() == -1) {
         return false;
      } else if (expectedCharacters.indexOf((char)this.nextChar) == -1) {
         return false;
      } else {
         this.sb.append((char)this.nextChar);
         this.nextChar = this.nextButOneChar;
         this.nextButOneChar = -1;
         return true;
      }
   }

   private int internalRead() throws IOException, CompileException {
      int result;
      try {
         result = this.in.read();
      } catch (UnicodeUnescapeException ex) {
         throw new CompileException(ex.getMessage(), this.location(), ex);
      }

      if (result == 13) {
         ++this.nextCharLineNumber;
         this.nextCharColumnNumber = 0;
         this.crLfPending = true;
      } else if (result == 10) {
         if (this.crLfPending) {
            this.crLfPending = false;
         } else {
            ++this.nextCharLineNumber;
            this.nextCharColumnNumber = 0;
         }
      } else if (result == 9) {
         this.nextCharColumnNumber = this.nextCharColumnNumber - this.nextCharColumnNumber % 8 + 8;
         this.crLfPending = false;
      } else {
         ++this.nextCharColumnNumber;
         this.crLfPending = false;
      }

      return result;
   }
}
