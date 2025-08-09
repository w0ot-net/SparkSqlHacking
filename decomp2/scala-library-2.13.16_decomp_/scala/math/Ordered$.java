package scala.math;

public final class Ordered$ {
   public static final Ordered$ MODULE$ = new Ordered$();

   public Ordered orderingToOrdered(final Object x, final Ordering ord) {
      return new Ordered(ord, x) {
         private final Ordering ord$1;
         private final Object x$1;

         public boolean $less(final Object that) {
            return Ordered.$less$(this, that);
         }

         public boolean $greater(final Object that) {
            return Ordered.$greater$(this, that);
         }

         public boolean $less$eq(final Object that) {
            return Ordered.$less$eq$(this, that);
         }

         public boolean $greater$eq(final Object that) {
            return Ordered.$greater$eq$(this, that);
         }

         public int compareTo(final Object that) {
            return Ordered.compareTo$(this, that);
         }

         public int compare(final Object that) {
            return this.ord$1.compare(this.x$1, that);
         }

         public {
            this.ord$1 = ord$1;
            this.x$1 = x$1;
         }
      };
   }

   private Ordered$() {
   }
}
