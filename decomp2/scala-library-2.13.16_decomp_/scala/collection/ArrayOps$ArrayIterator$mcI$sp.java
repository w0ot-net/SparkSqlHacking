package scala.collection;

import scala.runtime.Statics;

public final class ArrayOps$ArrayIterator$mcI$sp extends ArrayOps.ArrayIterator {
   private static final long serialVersionUID = 3L;
   public final int[] xs$mcI$sp;

   public int next() {
      return this.next$mcI$sp();
   }

   public int next$mcI$sp() {
      if (this.scala$collection$ArrayOps$ArrayIterator$$pos >= this.xs$mcI$sp.length) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      int r = this.xs$mcI$sp[this.scala$collection$ArrayOps$ArrayIterator$$pos];
      ++this.scala$collection$ArrayOps$ArrayIterator$$pos;
      return r;
   }

   public ArrayOps$ArrayIterator$mcI$sp(final int[] xs$mcI$sp) {
      super(xs$mcI$sp);
      this.xs$mcI$sp = xs$mcI$sp;
      Statics.releaseFence();
   }
}
