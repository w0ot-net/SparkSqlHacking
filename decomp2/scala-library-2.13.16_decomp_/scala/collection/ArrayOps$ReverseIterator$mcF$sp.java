package scala.collection;

public final class ArrayOps$ReverseIterator$mcF$sp extends ArrayOps.ReverseIterator {
   private static final long serialVersionUID = 3L;
   public final float[] xs$mcF$sp;

   public float next() {
      return this.next$mcF$sp();
   }

   public float next$mcF$sp() {
      if (this.scala$collection$ArrayOps$ReverseIterator$$pos < 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      float r = this.xs$mcF$sp[this.scala$collection$ArrayOps$ReverseIterator$$pos];
      --this.scala$collection$ArrayOps$ReverseIterator$$pos;
      return r;
   }

   public ArrayOps$ReverseIterator$mcF$sp(final float[] xs$mcF$sp) {
      super(xs$mcF$sp);
      this.xs$mcF$sp = xs$mcF$sp;
   }
}
