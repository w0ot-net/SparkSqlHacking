package scala.collection;

import scala.runtime.Statics;

public final class ArrayOps$ArrayIterator$mcF$sp extends ArrayOps.ArrayIterator {
   private static final long serialVersionUID = 3L;
   public final float[] xs$mcF$sp;

   public float next() {
      return this.next$mcF$sp();
   }

   public float next$mcF$sp() {
      if (this.scala$collection$ArrayOps$ArrayIterator$$pos >= this.xs$mcF$sp.length) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      float r = this.xs$mcF$sp[this.scala$collection$ArrayOps$ArrayIterator$$pos];
      ++this.scala$collection$ArrayOps$ArrayIterator$$pos;
      return r;
   }

   public ArrayOps$ArrayIterator$mcF$sp(final float[] xs$mcF$sp) {
      super(xs$mcF$sp);
      this.xs$mcF$sp = xs$mcF$sp;
      Statics.releaseFence();
   }
}
