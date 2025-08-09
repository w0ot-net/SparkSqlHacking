package scala.collection;

import scala.runtime.Statics;

public final class ArrayOps$ArrayIterator$mcS$sp extends ArrayOps.ArrayIterator {
   private static final long serialVersionUID = 3L;
   public final short[] xs$mcS$sp;

   public short next() {
      return this.next$mcS$sp();
   }

   public short next$mcS$sp() {
      if (this.scala$collection$ArrayOps$ArrayIterator$$pos >= this.xs$mcS$sp.length) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      short r = this.xs$mcS$sp[this.scala$collection$ArrayOps$ArrayIterator$$pos];
      ++this.scala$collection$ArrayOps$ArrayIterator$$pos;
      return r;
   }

   public ArrayOps$ArrayIterator$mcS$sp(final short[] xs$mcS$sp) {
      super(xs$mcS$sp);
      this.xs$mcS$sp = xs$mcS$sp;
      Statics.releaseFence();
   }
}
