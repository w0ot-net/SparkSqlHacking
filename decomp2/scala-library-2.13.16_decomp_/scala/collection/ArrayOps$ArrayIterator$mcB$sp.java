package scala.collection;

import scala.runtime.Statics;

public final class ArrayOps$ArrayIterator$mcB$sp extends ArrayOps.ArrayIterator {
   private static final long serialVersionUID = 3L;
   public final byte[] xs$mcB$sp;

   public byte next() {
      return this.next$mcB$sp();
   }

   public byte next$mcB$sp() {
      if (this.scala$collection$ArrayOps$ArrayIterator$$pos >= this.xs$mcB$sp.length) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      byte r = this.xs$mcB$sp[this.scala$collection$ArrayOps$ArrayIterator$$pos];
      ++this.scala$collection$ArrayOps$ArrayIterator$$pos;
      return r;
   }

   public ArrayOps$ArrayIterator$mcB$sp(final byte[] xs$mcB$sp) {
      super(xs$mcB$sp);
      this.xs$mcB$sp = xs$mcB$sp;
      Statics.releaseFence();
   }
}
