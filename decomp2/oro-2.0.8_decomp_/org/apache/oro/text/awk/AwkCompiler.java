package org.apache.oro.text.awk;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;

public final class AwkCompiler implements PatternCompiler {
   public static final int DEFAULT_MASK = 0;
   public static final int CASE_INSENSITIVE_MASK = 1;
   public static final int MULTILINE_MASK = 2;
   static final char _END_OF_INPUT = '\uffff';
   private boolean __inCharacterClass;
   private boolean __caseSensitive;
   private boolean __multiline;
   private boolean __beginAnchor;
   private boolean __endAnchor;
   private char __lookahead;
   private int __position;
   private int __bytesRead;
   private int __expressionLength;
   private char[] __regularExpression;
   private int __openParen;
   private int __closeParen;

   private static boolean __isMetachar(char var0) {
      return var0 == '*' || var0 == '?' || var0 == '+' || var0 == '[' || var0 == ']' || var0 == '(' || var0 == ')' || var0 == '|' || var0 == '.';
   }

   static boolean _isWordCharacter(char var0) {
      return var0 >= 'a' && var0 <= 'z' || var0 >= 'A' && var0 <= 'Z' || var0 >= '0' && var0 <= '9' || var0 == '_';
   }

   static boolean _isLowerCase(char var0) {
      return var0 >= 'a' && var0 <= 'z';
   }

   static boolean _isUpperCase(char var0) {
      return var0 >= 'A' && var0 <= 'Z';
   }

   static char _toggleCase(char var0) {
      if (_isUpperCase(var0)) {
         return (char)(var0 + 32);
      } else {
         return _isLowerCase(var0) ? (char)(var0 - 32) : var0;
      }
   }

   private void __match(char var1) throws MalformedPatternException {
      if (var1 == this.__lookahead) {
         if (this.__bytesRead < this.__expressionLength) {
            this.__lookahead = this.__regularExpression[this.__bytesRead++];
         } else {
            this.__lookahead = '\uffff';
         }

      } else {
         throw new MalformedPatternException("token: " + var1 + " does not match lookahead: " + this.__lookahead + " at position: " + this.__bytesRead);
      }
   }

   private void __putback() {
      if (this.__lookahead != '\uffff') {
         --this.__bytesRead;
      }

      this.__lookahead = this.__regularExpression[this.__bytesRead - 1];
   }

   private SyntaxNode __regex() throws MalformedPatternException {
      SyntaxNode var1 = this.__branch();
      if (this.__lookahead == '|') {
         this.__match('|');
         return new OrNode(var1, this.__regex());
      } else {
         return var1;
      }
   }

   private SyntaxNode __branch() throws MalformedPatternException {
      SyntaxNode var2 = this.__piece();
      if (this.__lookahead == ')') {
         if (this.__openParen > this.__closeParen) {
            return var2;
         } else {
            throw new MalformedPatternException("Parse error: close parenthesis without matching open parenthesis at position " + this.__bytesRead);
         }
      } else if (this.__lookahead != '|' && this.__lookahead != '\uffff') {
         CatNode var1;
         CatNode var3 = var1 = new CatNode();
         var1._left = var2;

         while(true) {
            var2 = this.__piece();
            if (this.__lookahead == ')') {
               if (this.__openParen <= this.__closeParen) {
                  throw new MalformedPatternException("Parse error: close parenthesis without matching open parenthesis at position " + this.__bytesRead);
               }

               var1._right = var2;
               break;
            }

            if (this.__lookahead == '|' || this.__lookahead == '\uffff') {
               var1._right = var2;
               break;
            }

            var1._right = new CatNode();
            var1 = (CatNode)var1._right;
            var1._left = var2;
         }

         return var3;
      } else {
         return var2;
      }
   }

   private SyntaxNode __piece() throws MalformedPatternException {
      SyntaxNode var1 = this.__atom();
      switch (this.__lookahead) {
         case '*':
            this.__match('*');
            return new StarNode(var1);
         case '+':
            this.__match('+');
            return new PlusNode(var1);
         case '?':
            this.__match('?');
            return new QuestionNode(var1);
         case '{':
            return this.__repetition(var1);
         default:
            return var1;
      }
   }

   private int __parseUnsignedInteger(int var1, int var2, int var3) throws MalformedPatternException {
      int var5 = 0;

      StringBuffer var6;
      for(var6 = new StringBuffer(4); Character.digit(this.__lookahead, var1) != -1 && var5 < var3; ++var5) {
         var6.append(this.__lookahead);
         this.__match(this.__lookahead);
      }

      if (var5 >= var2 && var5 <= var3) {
         try {
            int var4 = Integer.parseInt(var6.toString(), var1);
            return var4;
         } catch (NumberFormatException var8) {
            throw new MalformedPatternException("Parse error: numeric value at position " + this.__bytesRead + " is invalid");
         }
      } else {
         throw new MalformedPatternException("Parse error: unexpected number of digits at position " + this.__bytesRead);
      }
   }

   private SyntaxNode __repetition(SyntaxNode var1) throws MalformedPatternException {
      SyntaxNode var5 = null;
      this.__match('{');
      int var2 = this.__parseUnsignedInteger(10, 1, Integer.MAX_VALUE);
      int[] var4 = new int[]{this.__position};
      if (this.__lookahead == '}') {
         this.__match('}');
         if (var2 == 0) {
            throw new MalformedPatternException("Parse error: Superfluous interval specified at position " + this.__bytesRead + ".  Number of occurences was set to zero.");
         }

         if (var2 == 1) {
            return var1;
         }

         CatNode var6;
         var5 = var6 = new CatNode();
         var6._left = var1;

         while(true) {
            --var2;
            if (var2 <= 1) {
               var6._right = var1._clone(var4);
               break;
            }

            var1 = var1._clone(var4);
            var6._right = new CatNode();
            var6 = (CatNode)var6._right;
            var6._left = var1;
         }
      } else {
         if (this.__lookahead != ',') {
            throw new MalformedPatternException("Parse error: unexpected character " + this.__lookahead + " in interval at position " + this.__bytesRead);
         }

         this.__match(',');
         if (this.__lookahead == '}') {
            this.__match('}');
            if (var2 == 0) {
               return new StarNode(var1);
            }

            if (var2 == 1) {
               return new PlusNode(var1);
            }

            CatNode var11;
            var5 = var11 = new CatNode();
            var11._left = var1;

            while(true) {
               --var2;
               if (var2 <= 0) {
                  var11._right = new StarNode(var1._clone(var4));
                  break;
               }

               var1 = var1._clone(var4);
               var11._right = new CatNode();
               var11 = (CatNode)var11._right;
               var11._left = var1;
            }
         } else {
            int var3 = this.__parseUnsignedInteger(10, 1, Integer.MAX_VALUE);
            this.__match('}');
            if (var3 < var2) {
               throw new MalformedPatternException("Parse error: invalid interval; " + var3 + " is less than " + var2 + " at position " + this.__bytesRead);
            }

            if (var3 == 0) {
               throw new MalformedPatternException("Parse error: Superfluous interval specified at position " + this.__bytesRead + ".  Number of occurences was set to zero.");
            }

            if (var2 == 0) {
               if (var3 == 1) {
                  return new QuestionNode(var1);
               }

               CatNode var12;
               var5 = var12 = new CatNode();
               var1 = new QuestionNode(var1);
               var12._left = var1;

               while(true) {
                  --var3;
                  if (var3 <= 1) {
                     var12._right = var1._clone(var4);
                     break;
                  }

                  var1 = var1._clone(var4);
                  var12._right = new CatNode();
                  var12 = (CatNode)var12._right;
                  var12._left = var1;
               }
            } else if (var2 == var3) {
               if (var2 == 1) {
                  return var1;
               }

               CatNode var13;
               var5 = var13 = new CatNode();
               var13._left = var1;

               while(true) {
                  --var2;
                  if (var2 <= 1) {
                     var13._right = var1._clone(var4);
                     break;
                  }

                  var1 = var1._clone(var4);
                  var13._right = new CatNode();
                  var13 = (CatNode)var13._right;
                  var13._left = var1;
               }
            } else {
               CatNode var14;
               var5 = var14 = new CatNode();
               var14._left = var1;

               for(int var7 = 1; var7 < var2; ++var7) {
                  var1 = var1._clone(var4);
                  var14._right = new CatNode();
                  var14 = (CatNode)var14._right;
                  var14._left = var1;
               }

               var1 = new QuestionNode(var1._clone(var4));
               int var16 = var3 - var2;
               if (var16 == 1) {
                  var14._right = var1;
               } else {
                  var14._right = new CatNode();
                  var14 = (CatNode)var14._right;
                  var14._left = var1;

                  while(true) {
                     --var16;
                     if (var16 <= 1) {
                        var14._right = var1._clone(var4);
                        break;
                     }

                     var1 = var1._clone(var4);
                     var14._right = new CatNode();
                     var14 = (CatNode)var14._right;
                     var14._left = var1;
                  }
               }
            }
         }
      }

      this.__position = var4[0];
      return var5;
   }

   private SyntaxNode __backslashToken() throws MalformedPatternException {
      this.__match('\\');
      Object var1;
      if (this.__lookahead == 'x') {
         this.__match('x');
         var1 = this._newTokenNode((char)this.__parseUnsignedInteger(16, 2, 2), this.__position++);
      } else if (this.__lookahead == 'c') {
         this.__match('c');
         char var2 = Character.toUpperCase(this.__lookahead);
         var2 = (char)(var2 > '?' ? var2 - 64 : var2 + 64);
         var1 = new TokenNode(var2, this.__position++);
         this.__match(this.__lookahead);
      } else if (this.__lookahead >= '0' && this.__lookahead <= '9') {
         this.__match(this.__lookahead);
         if (this.__lookahead >= '0' && this.__lookahead <= '9') {
            this.__putback();
            int var7 = this.__parseUnsignedInteger(10, 2, 3);
            var7 = Integer.parseInt(Integer.toString(var7), 8);
            var1 = this._newTokenNode((char)var7, this.__position++);
         } else {
            this.__putback();
            if (this.__lookahead == '0') {
               this.__match('0');
               var1 = new TokenNode('\u0000', this.__position++);
            } else {
               int var3 = Character.digit(this.__lookahead, 10);
               var1 = this._newTokenNode(this.__lookahead, this.__position++);
               this.__match(this.__lookahead);
            }
         }
      } else if (this.__lookahead == 'b') {
         var1 = new TokenNode('\b', this.__position++);
         this.__match('b');
      } else {
         char var6 = this.__lookahead;
         switch (this.__lookahead) {
            case 'f':
               var6 = '\f';
               break;
            case 'n':
               var6 = '\n';
               break;
            case 'r':
               var6 = '\r';
               break;
            case 't':
               var6 = '\t';
         }

         switch (var6) {
            case 'D':
               NegativeCharacterClassNode var13 = new NegativeCharacterClassNode(this.__position++);
               ((CharacterClassNode)var13)._addTokenRange(48, 57);
               var1 = var13;
               break;
            case 'S':
               NegativeCharacterClassNode var12 = new NegativeCharacterClassNode(this.__position++);
               ((CharacterClassNode)var12)._addToken(32);
               ((CharacterClassNode)var12)._addToken(12);
               ((CharacterClassNode)var12)._addToken(10);
               ((CharacterClassNode)var12)._addToken(13);
               ((CharacterClassNode)var12)._addToken(9);
               var1 = var12;
               break;
            case 'W':
               NegativeCharacterClassNode var11 = new NegativeCharacterClassNode(this.__position++);
               ((CharacterClassNode)var11)._addTokenRange(48, 57);
               ((CharacterClassNode)var11)._addTokenRange(97, 122);
               ((CharacterClassNode)var11)._addTokenRange(65, 90);
               ((CharacterClassNode)var11)._addToken(95);
               var1 = var11;
               break;
            case 'd':
               CharacterClassNode var10 = new CharacterClassNode(this.__position++);
               var10._addTokenRange(48, 57);
               var1 = var10;
               break;
            case 's':
               CharacterClassNode var9 = new CharacterClassNode(this.__position++);
               var9._addToken(32);
               var9._addToken(12);
               var9._addToken(10);
               var9._addToken(13);
               var9._addToken(9);
               var1 = var9;
               break;
            case 'w':
               CharacterClassNode var4 = new CharacterClassNode(this.__position++);
               var4._addTokenRange(48, 57);
               var4._addTokenRange(97, 122);
               var4._addTokenRange(65, 90);
               var4._addToken(95);
               var1 = var4;
               break;
            default:
               var1 = this._newTokenNode(var6, this.__position++);
         }

         this.__match(this.__lookahead);
      }

      return (SyntaxNode)var1;
   }

   private SyntaxNode __atom() throws MalformedPatternException {
      Object var1;
      if (this.__lookahead == '(') {
         this.__match('(');
         ++this.__openParen;
         var1 = this.__regex();
         this.__match(')');
         ++this.__closeParen;
      } else if (this.__lookahead == '[') {
         var1 = this.__characterClass();
      } else if (this.__lookahead == '.') {
         this.__match('.');
         NegativeCharacterClassNode var2 = new NegativeCharacterClassNode(this.__position++);
         if (this.__multiline) {
            ((CharacterClassNode)var2)._addToken(10);
         }

         var1 = var2;
      } else if (this.__lookahead == '\\') {
         var1 = this.__backslashToken();
      } else {
         if (__isMetachar(this.__lookahead)) {
            throw new MalformedPatternException("Parse error: unexpected character " + this.__lookahead + " at position " + this.__bytesRead);
         }

         var1 = this._newTokenNode(this.__lookahead, this.__position++);
         this.__match(this.__lookahead);
      }

      return (SyntaxNode)var1;
   }

   private SyntaxNode __characterClass() throws MalformedPatternException {
      this.__match('[');
      this.__inCharacterClass = true;
      Object var4;
      if (this.__lookahead == '^') {
         this.__match('^');
         var4 = new NegativeCharacterClassNode(this.__position++);
      } else {
         var4 = new CharacterClassNode(this.__position++);
      }

      while(this.__lookahead != ']' && this.__lookahead != '\uffff') {
         char var1;
         if (this.__lookahead == '\\') {
            SyntaxNode var3 = this.__backslashToken();
            --this.__position;
            if (!(var3 instanceof TokenNode)) {
               CharacterClassNode var5 = (CharacterClassNode)var3;

               for(char var2 = 0; var2 < 256; ++var2) {
                  if (var5._matches(var2)) {
                     ((CharacterClassNode)var4)._addToken(var2);
                  }
               }
               continue;
            }

            var1 = ((TokenNode)var3)._token;
            ((CharacterClassNode)var4)._addToken(var1);
            if (!this.__caseSensitive) {
               ((CharacterClassNode)var4)._addToken(_toggleCase(var1));
            }
         } else {
            var1 = this.__lookahead;
            ((CharacterClassNode)var4)._addToken(this.__lookahead);
            if (!this.__caseSensitive) {
               ((CharacterClassNode)var4)._addToken(_toggleCase(this.__lookahead));
            }

            this.__match(this.__lookahead);
         }

         if (this.__lookahead == '-') {
            this.__match('-');
            if (this.__lookahead == ']') {
               ((CharacterClassNode)var4)._addToken(45);
               break;
            }

            char var6;
            if (this.__lookahead == '\\') {
               SyntaxNode var7 = this.__backslashToken();
               --this.__position;
               if (!(var7 instanceof TokenNode)) {
                  throw new MalformedPatternException("Parse error: invalid range specified at position " + this.__bytesRead);
               }

               var6 = ((TokenNode)var7)._token;
            } else {
               var6 = this.__lookahead;
               this.__match(this.__lookahead);
            }

            if (var6 < var1) {
               throw new MalformedPatternException("Parse error: invalid range specified at position " + this.__bytesRead);
            }

            ((CharacterClassNode)var4)._addTokenRange(var1 + 1, var6);
            if (!this.__caseSensitive) {
               ((CharacterClassNode)var4)._addTokenRange(_toggleCase((char)(var1 + 1)), _toggleCase(var6));
            }
         }
      }

      this.__match(']');
      this.__inCharacterClass = false;
      return (SyntaxNode)var4;
   }

   SyntaxNode _newTokenNode(char var1, int var2) {
      if (this.__inCharacterClass || this.__caseSensitive || !_isUpperCase(var1) && !_isLowerCase(var1)) {
         return new TokenNode(var1, var2);
      } else {
         CharacterClassNode var3 = new CharacterClassNode(var2);
         var3._addToken(var1);
         var3._addToken(_toggleCase(var1));
         return var3;
      }
   }

   SyntaxTree _parse(char[] var1) throws MalformedPatternException {
      this.__openParen = this.__closeParen = 0;
      this.__regularExpression = var1;
      this.__bytesRead = 0;
      this.__expressionLength = var1.length;
      this.__inCharacterClass = false;
      this.__position = 0;
      this.__match(this.__lookahead);
      if (this.__lookahead == '^') {
         this.__beginAnchor = true;
         this.__match(this.__lookahead);
      }

      if (this.__expressionLength > 0 && var1[this.__expressionLength - 1] == '$') {
         --this.__expressionLength;
         this.__endAnchor = true;
      }

      SyntaxTree var2;
      if (this.__expressionLength <= 1 && (this.__expressionLength != 1 || this.__beginAnchor)) {
         var2 = new SyntaxTree(new TokenNode('Ā', 0), 1);
      } else {
         CatNode var3 = new CatNode();
         var3._left = this.__regex();
         var3._right = new TokenNode('Ā', this.__position++);
         var2 = new SyntaxTree(var3, this.__position);
      }

      var2._computeFollowPositions();
      return var2;
   }

   public Pattern compile(char[] var1, int var2) throws MalformedPatternException {
      this.__beginAnchor = this.__endAnchor = false;
      this.__caseSensitive = (var2 & 1) == 0;
      this.__multiline = (var2 & 2) != 0;
      SyntaxTree var3 = this._parse(var1);
      AwkPattern var4 = new AwkPattern(new String(var1), var3);
      var4._options = var2;
      var4._hasBeginAnchor = this.__beginAnchor;
      var4._hasEndAnchor = this.__endAnchor;
      return var4;
   }

   public Pattern compile(String var1, int var2) throws MalformedPatternException {
      this.__beginAnchor = this.__endAnchor = false;
      this.__caseSensitive = (var2 & 1) == 0;
      this.__multiline = (var2 & 2) != 0;
      SyntaxTree var3 = this._parse(var1.toCharArray());
      AwkPattern var4 = new AwkPattern(var1, var3);
      var4._options = var2;
      var4._hasBeginAnchor = this.__beginAnchor;
      var4._hasEndAnchor = this.__endAnchor;
      return var4;
   }

   public Pattern compile(char[] var1) throws MalformedPatternException {
      return this.compile((char[])var1, 0);
   }

   public Pattern compile(String var1) throws MalformedPatternException {
      return this.compile((String)var1, 0);
   }
}
