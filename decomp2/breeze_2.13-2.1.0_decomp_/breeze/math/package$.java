package breeze.math;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static final Complex i;

   static {
      i = Complex$.MODULE$.i();
   }

   public Complex i() {
      return i;
   }

   public package.RichField RichField(final double value) {
      return new package.RichField(value);
   }

   public package.RichField richInt(final int value) {
      return new package.RichField((double)value);
   }

   public package.RichField richFloat(final float value) {
      return new package.RichField((double)value);
   }

   private package$() {
   }
}
