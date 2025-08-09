package scala.collection;

public final class ArrayOps$ReverseIterator$mcD$sp extends ArrayOps.ReverseIterator {
   private static final long serialVersionUID = 3L;
   public final double[] xs$mcD$sp;

   public double next() {
      return this.next$mcD$sp();
   }

   public double next$mcD$sp() {
      if (this.scala$collection$ArrayOps$ReverseIterator$$pos < 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      double r = this.xs$mcD$sp[this.scala$collection$ArrayOps$ReverseIterator$$pos];
      --this.scala$collection$ArrayOps$ReverseIterator$$pos;
      return r;
   }

   public ArrayOps$ReverseIterator$mcD$sp(final double[] xs$mcD$sp) {
      super(xs$mcD$sp);
      this.xs$mcD$sp = xs$mcD$sp;
   }
}
