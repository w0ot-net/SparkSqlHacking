package scala;

public final class Char$ implements AnyValCompanion {
   public static final Char$ MODULE$ = new Char$();

   public final char MinValue() {
      return '\u0000';
   }

   public final char MaxValue() {
      return '\uffff';
   }

   public Character box(final char x) {
      throw new NotImplementedError();
   }

   public char unbox(final Object x) {
      throw new NotImplementedError();
   }

   public String toString() {
      return "object scala.Char";
   }

   public int char2int(final char x) {
      return x;
   }

   public long char2long(final char x) {
      return (long)x;
   }

   public float char2float(final char x) {
      return (float)x;
   }

   public double char2double(final char x) {
      return (double)x;
   }

   private Char$() {
   }
}
