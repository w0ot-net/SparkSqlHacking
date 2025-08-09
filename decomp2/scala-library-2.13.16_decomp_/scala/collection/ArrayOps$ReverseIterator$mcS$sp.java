package scala.collection;

public final class ArrayOps$ReverseIterator$mcS$sp extends ArrayOps.ReverseIterator {
   private static final long serialVersionUID = 3L;
   public final short[] xs$mcS$sp;

   public short next() {
      return this.next$mcS$sp();
   }

   public short next$mcS$sp() {
      if (this.scala$collection$ArrayOps$ReverseIterator$$pos < 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      short r = this.xs$mcS$sp[this.scala$collection$ArrayOps$ReverseIterator$$pos];
      --this.scala$collection$ArrayOps$ReverseIterator$$pos;
      return r;
   }

   public ArrayOps$ReverseIterator$mcS$sp(final short[] xs$mcS$sp) {
      super(xs$mcS$sp);
      this.xs$mcS$sp = xs$mcS$sp;
   }
}
