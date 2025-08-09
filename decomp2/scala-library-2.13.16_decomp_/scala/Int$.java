package scala;

public final class Int$ implements AnyValCompanion {
   public static final Int$ MODULE$ = new Int$();

   public final int MinValue() {
      return Integer.MIN_VALUE;
   }

   public final int MaxValue() {
      return Integer.MAX_VALUE;
   }

   public Integer box(final int x) {
      throw new NotImplementedError();
   }

   public int unbox(final Object x) {
      throw new NotImplementedError();
   }

   public String toString() {
      return "object scala.Int";
   }

   /** @deprecated */
   public float int2float(final int x) {
      return (float)x;
   }

   public long int2long(final int x) {
      return (long)x;
   }

   public double int2double(final int x) {
      return (double)x;
   }

   private Int$() {
   }
}
