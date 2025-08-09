package scala.collection.generic;

public final class CommonErrors$ {
   public static final CommonErrors$ MODULE$ = new CommonErrors$();

   public IndexOutOfBoundsException indexOutOfBounds(final int index, final int max) {
      return new IndexOutOfBoundsException((new StringBuilder(31)).append(index).append(" is out of bounds (min 0, max ").append(max).append(")").toString());
   }

   public IndexOutOfBoundsException indexOutOfBounds(final int index) {
      return new IndexOutOfBoundsException((new StringBuilder(38)).append(index).append(" is out of bounds (min 0, max unknown)").toString());
   }

   private CommonErrors$() {
   }
}
