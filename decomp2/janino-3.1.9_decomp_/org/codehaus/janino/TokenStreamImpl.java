package org.codehaus.janino;

import java.io.IOException;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.commons.compiler.Location;
import org.codehaus.commons.compiler.WarningHandler;
import org.codehaus.commons.nullanalysis.Nullable;

public class TokenStreamImpl implements TokenStream {
   private final Scanner scanner;
   @Nullable
   private String docComment;
   @Nullable
   private Token previousToken;
   @Nullable
   private Token nextToken;
   @Nullable
   private Token nextButOneToken;
   @Nullable
   private WarningHandler warningHandler;

   public TokenStreamImpl(Scanner scanner) {
      (this.scanner = scanner).setIgnoreWhiteSpace(true);
   }

   private Token produceToken() throws CompileException, IOException {
      while(true) {
         Token token = this.scanner.produce();
         switch (token.type) {
            case WHITE_SPACE:
            case C_PLUS_PLUS_STYLE_COMMENT:
               break;
            case C_STYLE_COMMENT:
               if (token.value.startsWith("/**") && this.docComment != null) {
                  this.warning("MDC", "Misplaced doc comment", this.scanner.location());
                  this.docComment = null;
               }
               break;
            default:
               return token;
         }
      }
   }

   @Nullable
   public String doc() {
      String s = this.docComment;
      this.docComment = null;
      return s;
   }

   public Token peek() throws CompileException, IOException {
      if (this.nextToken == null) {
         this.nextToken = this.produceToken();
      }

      assert this.nextToken != null;

      return this.nextToken;
   }

   public Token peekNextButOne() throws CompileException, IOException {
      if (this.nextToken == null) {
         this.nextToken = this.produceToken();
      }

      if (this.nextButOneToken == null) {
         this.nextButOneToken = this.produceToken();
      }

      assert this.nextButOneToken != null;

      return this.nextButOneToken;
   }

   public boolean peek(String suspected) throws CompileException, IOException {
      return this.peek().value.equals(suspected);
   }

   public int peek(String... suspected) throws CompileException, IOException {
      return indexOf(suspected, this.peek().value);
   }

   public boolean peek(TokenType suspected) throws CompileException, IOException {
      return this.peek().type == suspected;
   }

   public int peek(TokenType... suspected) throws CompileException, IOException {
      return indexOf(suspected, this.peek().type);
   }

   public boolean peekNextButOne(String suspected) throws CompileException, IOException {
      return this.peekNextButOne().value.equals(suspected);
   }

   public Token read() throws CompileException, IOException {
      if (this.nextToken == null) {
         return this.previousToken = this.produceToken();
      } else {
         Token result = this.nextToken;

         assert result != null;

         this.previousToken = result;
         this.nextToken = this.nextButOneToken;
         this.nextButOneToken = null;
         return result;
      }
   }

   public void read(String expected) throws CompileException, IOException {
      String s = this.read().value;
      if (!s.equals(expected)) {
         throw this.compileException("'" + expected + "' expected instead of '" + s + "'");
      }
   }

   public int read(String... expected) throws CompileException, IOException {
      Token t = this.read();
      String value = t.value;
      int idx = indexOf(expected, value);
      if (idx != -1) {
         return idx;
      } else {
         if (value.startsWith(">")) {
            int result = indexOf(expected, ">");
            if (result != -1) {
               Location loc = t.getLocation();
               this.nextToken = new Token(loc.getFileName(), loc.getLineNumber(), loc.getColumnNumber() + 1, TokenType.OPERATOR, value.substring(1));
               return result;
            }
         }

         throw this.compileException("One of '" + join(expected, " ") + "' expected instead of '" + value + "'");
      }
   }

   public String read(TokenType expected) throws CompileException, IOException {
      Token t = this.read();
      if (t.type != expected) {
         throw new CompileException(expected + " expected instead of '" + t.value + "'", t.getLocation());
      } else {
         return t.value;
      }
   }

   public int read(TokenType... expected) throws CompileException, IOException {
      Token t = this.read();
      TokenType type = t.type;
      int idx = indexOf(expected, type);
      if (idx != -1) {
         return idx;
      } else {
         throw this.compileException("One of '" + join(expected, " ") + "' expected instead of '" + type + "'");
      }
   }

   public boolean peekRead(String suspected) throws CompileException, IOException {
      if (this.nextToken != null) {
         if (!this.nextToken.value.equals(suspected)) {
            return false;
         } else {
            this.previousToken = this.nextToken;
            this.nextToken = this.nextButOneToken;
            this.nextButOneToken = null;
            return true;
         }
      } else {
         Token result = this.produceToken();
         if (result.value.equals(suspected)) {
            this.previousToken = result;
            return true;
         } else {
            this.nextToken = result;
            return false;
         }
      }
   }

   public int peekRead(String... suspected) throws CompileException, IOException {
      if (this.nextToken != null) {
         int idx = indexOf(suspected, this.nextToken.value);
         if (idx == -1) {
            return -1;
         } else {
            this.previousToken = this.nextToken;
            this.nextToken = this.nextButOneToken;
            this.nextButOneToken = null;
            return idx;
         }
      } else {
         Token t = this.produceToken();
         int idx = indexOf(suspected, t.value);
         if (idx != -1) {
            this.previousToken = t;
            return idx;
         } else {
            this.nextToken = t;
            return -1;
         }
      }
   }

   @Nullable
   public String peekRead(TokenType suspected) throws CompileException, IOException {
      Token nt = this.nextToken;
      if (nt != null) {
         if (nt.type != suspected) {
            return null;
         } else {
            this.previousToken = this.nextToken;
            this.nextToken = this.nextButOneToken;
            this.nextButOneToken = null;
            return nt.value;
         }
      } else {
         nt = this.produceToken();
         if (nt.type == suspected) {
            this.previousToken = nt;
            return nt.value;
         } else {
            this.nextToken = nt;
            return null;
         }
      }
   }

   public int peekRead(TokenType... suspected) throws CompileException, IOException {
      if (this.nextToken != null) {
         int idx = indexOf(suspected, this.nextToken.type);
         if (idx != -1) {
            this.previousToken = this.nextToken;
            this.nextToken = this.nextButOneToken;
            this.nextButOneToken = null;
         }

         return idx;
      } else {
         Token t = this.produceToken();
         int idx = indexOf(suspected, t.type);
         if (idx != -1) {
            this.previousToken = t;
            return idx;
         } else {
            this.nextToken = t;
            return -1;
         }
      }
   }

   public Location location() {
      return this.previousToken != null ? this.previousToken.getLocation() : this.scanner.location();
   }

   private static int indexOf(String[] strings, String subject) {
      for(int i = 0; i < strings.length; ++i) {
         if (strings[i].equals(subject)) {
            return i;
         }
      }

      return -1;
   }

   private static int indexOf(TokenType[] tta, TokenType subject) {
      for(int i = 0; i < tta.length; ++i) {
         if (tta[i].equals(subject)) {
            return i;
         }
      }

      return -1;
   }

   public void setWarningHandler(@Nullable WarningHandler warningHandler) {
      this.warningHandler = warningHandler;
   }

   public String toString() {
      return this.nextToken + "/" + this.nextButOneToken + "/" + this.scanner.location();
   }

   private void warning(String handle, String message, @Nullable Location location) throws CompileException {
      if (this.warningHandler != null) {
         this.warningHandler.handleWarning(handle, message, location);
      }

   }

   protected final CompileException compileException(String message) {
      return new CompileException(message, this.scanner.location());
   }

   private static String join(@Nullable Object[] oa, String glue) {
      if (oa == null) {
         return "(null)";
      } else if (oa.length == 0) {
         return "(zero length array)";
      } else {
         StringBuilder sb = (new StringBuilder()).append(oa[0]);

         for(int i = 1; i < oa.length; ++i) {
            sb.append(glue).append(oa[i]);
         }

         return sb.toString();
      }
   }
}
