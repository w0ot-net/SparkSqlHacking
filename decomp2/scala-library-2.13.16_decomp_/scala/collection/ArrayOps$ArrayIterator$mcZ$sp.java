package scala.collection;

import scala.runtime.Statics;

public final class ArrayOps$ArrayIterator$mcZ$sp extends ArrayOps.ArrayIterator {
   private static final long serialVersionUID = 3L;
   public final boolean[] xs$mcZ$sp;

   public boolean next() {
      return this.next$mcZ$sp();
   }

   public boolean next$mcZ$sp() {
      if (this.scala$collection$ArrayOps$ArrayIterator$$pos >= this.xs$mcZ$sp.length) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      boolean r = this.xs$mcZ$sp[this.scala$collection$ArrayOps$ArrayIterator$$pos];
      ++this.scala$collection$ArrayOps$ArrayIterator$$pos;
      return r;
   }

   public ArrayOps$ArrayIterator$mcZ$sp(final boolean[] xs$mcZ$sp) {
      super(xs$mcZ$sp);
      this.xs$mcZ$sp = xs$mcZ$sp;
      Statics.releaseFence();
   }
}
