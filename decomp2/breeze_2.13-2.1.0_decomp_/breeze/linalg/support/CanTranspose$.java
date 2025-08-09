package breeze.linalg.support;

public final class CanTranspose$ {
   public static final CanTranspose$ MODULE$ = new CanTranspose$();

   public CanTranspose transposeOfScalarIsScalar() {
      return new CanTranspose() {
         public Object apply(final Object from) {
            return from;
         }
      };
   }

   private CanTranspose$() {
   }
}
