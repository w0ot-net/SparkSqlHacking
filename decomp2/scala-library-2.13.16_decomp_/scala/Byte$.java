package scala;

public final class Byte$ implements AnyValCompanion {
   public static final Byte$ MODULE$ = new Byte$();

   public final byte MinValue() {
      return -128;
   }

   public final byte MaxValue() {
      return 127;
   }

   public java.lang.Byte box(final byte x) {
      throw new NotImplementedError();
   }

   public byte unbox(final Object x) {
      throw new NotImplementedError();
   }

   public String toString() {
      return "object scala.Byte";
   }

   public short byte2short(final byte x) {
      return x;
   }

   public int byte2int(final byte x) {
      return x;
   }

   public long byte2long(final byte x) {
      return (long)x;
   }

   public float byte2float(final byte x) {
      return (float)x;
   }

   public double byte2double(final byte x) {
      return (double)x;
   }

   private Byte$() {
   }
}
