package scala.collection;

import scala.runtime.Statics;

public final class ArrayOps$ArrayIterator$mcJ$sp extends ArrayOps.ArrayIterator {
   private static final long serialVersionUID = 3L;
   public final long[] xs$mcJ$sp;

   public long next() {
      return this.next$mcJ$sp();
   }

   public long next$mcJ$sp() {
      if (this.scala$collection$ArrayOps$ArrayIterator$$pos >= this.xs$mcJ$sp.length) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      long r = this.xs$mcJ$sp[this.scala$collection$ArrayOps$ArrayIterator$$pos];
      ++this.scala$collection$ArrayOps$ArrayIterator$$pos;
      return r;
   }

   public ArrayOps$ArrayIterator$mcJ$sp(final long[] xs$mcJ$sp) {
      super(xs$mcJ$sp);
      this.xs$mcJ$sp = xs$mcJ$sp;
      Statics.releaseFence();
   }
}
