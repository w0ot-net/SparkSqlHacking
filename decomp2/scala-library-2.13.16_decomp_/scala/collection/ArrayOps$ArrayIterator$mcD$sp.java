package scala.collection;

import scala.runtime.Statics;

public final class ArrayOps$ArrayIterator$mcD$sp extends ArrayOps.ArrayIterator {
   private static final long serialVersionUID = 3L;
   public final double[] xs$mcD$sp;

   public double next() {
      return this.next$mcD$sp();
   }

   public double next$mcD$sp() {
      if (this.scala$collection$ArrayOps$ArrayIterator$$pos >= this.xs$mcD$sp.length) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      double r = this.xs$mcD$sp[this.scala$collection$ArrayOps$ArrayIterator$$pos];
      ++this.scala$collection$ArrayOps$ArrayIterator$$pos;
      return r;
   }

   public ArrayOps$ArrayIterator$mcD$sp(final double[] xs$mcD$sp) {
      super(xs$mcD$sp);
      this.xs$mcD$sp = xs$mcD$sp;
      Statics.releaseFence();
   }
}
