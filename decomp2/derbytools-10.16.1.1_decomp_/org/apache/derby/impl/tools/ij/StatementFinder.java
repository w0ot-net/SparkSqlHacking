package org.apache.derby.impl.tools.ij;

import java.io.IOException;
import java.io.Reader;
import org.apache.derby.iapi.tools.i18n.LocalizedInput;
import org.apache.derby.iapi.tools.i18n.LocalizedOutput;

public class StatementFinder {
   private Reader source;
   private StringBuffer statement = new StringBuffer();
   private int state;
   private boolean atEOF = false;
   private boolean peekEOF = false;
   private char peekChar;
   private boolean peeked = false;
   private LocalizedOutput promptwriter;
   private boolean doPrompt;
   private boolean continuedStatement;
   private static final int IN_STATEMENT = 0;
   private static final int IN_STRING = 1;
   private static final int IN_SQLCOMMENT = 2;
   private static final int END_OF_STATEMENT = 3;
   private static final int END_OF_INPUT = 4;
   private static final char MINUS = '-';
   private static final char SINGLEQUOTE = '\'';
   private static final char DOUBLEQUOTE = '"';
   private static final char SEMICOLON = ';';
   private static final char NEWLINE = '\n';
   private static final char RETURN = '\r';
   private static final char SPACE = ' ';
   private static final char TAB = '\t';
   private static final char FORMFEED = '\f';
   private static final char SLASH = '/';
   private static final char ASTERISK = '*';

   public StatementFinder(LocalizedInput var1, LocalizedOutput var2) {
      this.source = var1;
      if (var2 != null && var1.isStandardInput()) {
         this.promptwriter = var2;
         this.doPrompt = true;
      } else {
         this.doPrompt = false;
      }

   }

   public void ReInit(LocalizedInput var1) {
      try {
         this.source.close();
      } catch (IOException var3) {
      }

      this.source = var1;
      this.state = 0;
      this.atEOF = false;
      this.peekEOF = false;
      this.peeked = false;
      if (var1.isStandardInput() && this.promptwriter != null) {
         this.doPrompt = true;
      } else {
         this.doPrompt = false;
      }

   }

   public void close() throws IOException {
      this.source.close();
   }

   public String nextStatement() {
      boolean var1 = false;
      this.statement.setLength(0);
      if (this.state == 4) {
         return null;
      } else {
         this.state = 0;
         char var2 = this.peekChar();
         if (this.peekEOF()) {
            this.state = 4;
            return null;
         } else {
            if (this.whiteSpace(var2)) {
               while(this.whiteSpace(this.peekChar()) && !this.peekEOF()) {
               }

               if (this.peekEOF()) {
                  this.state = 4;
                  return null;
               }
            }

            while(this.state != 3 && this.state != 4) {
               var2 = this.readChar();
               if (this.atEOF()) {
                  this.state = 4;
                  break;
               }

               if (var2 != '-') {
                  this.continuedStatement = true;
               }

               switch (var2) {
                  case '\n':
                  case '\r':
                     if (this.doPrompt) {
                        utilMain.doPrompt(false, this.promptwriter, "");
                        if (var2 == '\r' && this.peekChar() == '\n') {
                           this.readChar();
                        }
                     }
                     break;
                  case '"':
                  case '\'':
                     this.readString(var2);
                     break;
                  case '-':
                     this.readSingleLineComment(var2);
                     break;
                  case '/':
                     this.readBracketedComment();
                     break;
                  case ';':
                     var1 = true;
                     this.state = 3;
                     this.continuedStatement = false;
               }
            }

            if (var1) {
               this.statement.setLength(this.statement.length() - 1);
            }

            return this.statement.toString();
         }
      }
   }

   private boolean whiteSpace(char var1) {
      return var1 == ' ' || var1 == '\t' || var1 == '\r' || var1 == '\n' || var1 == '\f';
   }

   private void readBracketedComment() {
      char var1 = this.peekChar();
      if (!this.peekEOF()) {
         if (var1 != '*') {
            this.continuedStatement = true;
         } else {
            this.readChar();
            int var2 = 1;

            while(true) {
               var1 = this.readChar();
               if (this.atEOF()) {
                  this.state = 0;
                  return;
               }

               char var3 = this.peekChar();
               if (var1 == '/' && var3 == '*') {
                  this.readChar();
                  ++var2;
               } else if (var1 == '*' && var3 == '/') {
                  this.readChar();
                  --var2;
                  if (var2 == 0) {
                     this.state = 0;
                     return;
                  }
               } else if ((var1 == '\n' || var1 == '\r') && this.doPrompt) {
                  utilMain.doPrompt(false, this.promptwriter, "");
                  if (var1 == '\r' && var3 == '\n') {
                     this.readChar();
                  }
               }
            }
         }
      }
   }

   private void readSingleLineComment(char var1) {
      char var2 = this.peekChar();
      if (!this.peekEOF()) {
         if (var2 != var1) {
            this.continuedStatement = true;
         } else {
            this.readChar();
            this.state = 2;

            while(true) {
               var2 = this.peekChar();
               if (this.peekEOF()) {
                  this.state = 0;
                  return;
               }

               switch (var2) {
                  case '\n':
                  case '\r':
                     this.readChar();
                     this.state = 0;
                     if (this.doPrompt) {
                        if (this.continuedStatement) {
                           utilMain.doPrompt(false, this.promptwriter, "");
                        } else {
                           utilMain.doPrompt(true, this.promptwriter, "");
                        }

                        if (var2 == '\r' && this.peekChar() == '\n') {
                           this.readChar();
                        }
                     }

                     return;
                  default:
                     this.readChar();
                     if (this.state != 2) {
                        return;
                     }
               }
            }
         }
      }
   }

   private void readString(char var1) {
      this.state = 1;

      do {
         char var2 = this.readChar();
         if (this.atEOF()) {
            this.state = 4;
            return;
         }

         if (var2 == var1) {
            this.state = 0;
            return;
         }
      } while(this.state == 1);

   }

   private boolean atEOF() {
      return this.atEOF;
   }

   private boolean peekEOF() {
      return this.peekEOF;
   }

   private char readChar() {
      if (!this.peeked) {
         this.peekChar();
      }

      this.peeked = false;
      this.atEOF = this.peekEOF;
      if (!this.atEOF) {
         this.statement.append(this.peekChar);
      }

      return this.peekChar;
   }

   private char peekChar() {
      this.peeked = true;
      char var1 = 0;

      try {
         int var2 = this.source.read();
         this.peekEOF = var2 == -1;
         if (!this.peekEOF) {
            var1 = (char)var2;
         }
      } catch (IOException var3) {
         throw ijException.iOException(var3);
      }

      this.peekChar = var1;
      return var1;
   }
}
