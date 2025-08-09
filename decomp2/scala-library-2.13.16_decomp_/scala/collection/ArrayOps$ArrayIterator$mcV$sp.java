package scala.collection;

import scala.runtime.BoxedUnit;
import scala.runtime.Statics;

public final class ArrayOps$ArrayIterator$mcV$sp extends ArrayOps.ArrayIterator {
   private static final long serialVersionUID = 3L;
   public final BoxedUnit[] xs$mcV$sp;

   public void next() {
      this.next$mcV$sp();
   }

   public void next$mcV$sp() {
      if (this.scala$collection$ArrayOps$ArrayIterator$$pos >= this.xs$mcV$sp.length) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      BoxedUnit var1 = this.xs$mcV$sp[this.scala$collection$ArrayOps$ArrayIterator$$pos];
      ++this.scala$collection$ArrayOps$ArrayIterator$$pos;
   }

   public ArrayOps$ArrayIterator$mcV$sp(final BoxedUnit[] xs$mcV$sp) {
      super(xs$mcV$sp);
      this.xs$mcV$sp = xs$mcV$sp;
      Statics.releaseFence();
   }
}
