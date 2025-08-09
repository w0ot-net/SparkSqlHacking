package scala.reflect.internal;

public final class Chars$ implements Chars {
   public static final Chars$ MODULE$ = new Chars$();
   private static char[] scala$reflect$internal$Chars$$char2uescapeArray;

   static {
      Chars.$init$(MODULE$);
   }

   public int digit2int(final char ch, final int base) {
      return Chars.digit2int$(this, ch, base);
   }

   public String char2uescape(final char c) {
      return Chars.char2uescape$(this, c);
   }

   public boolean isLineBreakChar(final char c) {
      return Chars.isLineBreakChar$(this, c);
   }

   public boolean isWhitespace(final char c) {
      return Chars.isWhitespace$(this, c);
   }

   public boolean isVarPart(final char c) {
      return Chars.isVarPart$(this, c);
   }

   public boolean isIdentifierStart(final char c) {
      return Chars.isIdentifierStart$(this, (char)c);
   }

   public boolean isIdentifierStart(final int c) {
      return Chars.isIdentifierStart$(this, (int)c);
   }

   public boolean isIdentifierPart(final char c) {
      return Chars.isIdentifierPart$(this, (char)c);
   }

   public boolean isIdentifierPart(final int c) {
      return Chars.isIdentifierPart$(this, (int)c);
   }

   public boolean isSpecial(final char c) {
      return Chars.isSpecial$(this, (char)c);
   }

   public boolean isSpecial(final int codePoint) {
      return Chars.isSpecial$(this, (int)codePoint);
   }

   public boolean isScalaLetter(final char c) {
      return Chars.isScalaLetter$(this, (char)c);
   }

   public boolean isScalaLetter(final int c) {
      return Chars.isScalaLetter$(this, (int)c);
   }

   public boolean isOperatorPart(final char c) {
      return Chars.isOperatorPart$(this, (char)c);
   }

   public boolean isOperatorPart(final int c) {
      return Chars.isOperatorPart$(this, (int)c);
   }

   public boolean isBiDiCharacter(final char c) {
      return Chars.isBiDiCharacter$(this, c);
   }

   public char[] scala$reflect$internal$Chars$$char2uescapeArray() {
      return scala$reflect$internal$Chars$$char2uescapeArray;
   }

   public final void scala$reflect$internal$Chars$_setter_$scala$reflect$internal$Chars$$char2uescapeArray_$eq(final char[] x$1) {
      scala$reflect$internal$Chars$$char2uescapeArray = x$1;
   }

   private Chars$() {
   }
}
