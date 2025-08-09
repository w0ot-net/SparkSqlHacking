package scala;

public final class Short$ implements AnyValCompanion {
   public static final Short$ MODULE$ = new Short$();

   public final short MinValue() {
      return java.lang.Short.MIN_VALUE;
   }

   public final short MaxValue() {
      return 32767;
   }

   public java.lang.Short box(final short x) {
      throw new NotImplementedError();
   }

   public short unbox(final Object x) {
      throw new NotImplementedError();
   }

   public String toString() {
      return "object scala.Short";
   }

   public int short2int(final short x) {
      return x;
   }

   public long short2long(final short x) {
      return (long)x;
   }

   public float short2float(final short x) {
      return (float)x;
   }

   public double short2double(final short x) {
      return (double)x;
   }

   private Short$() {
   }
}
