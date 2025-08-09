package scala.collection;

public final class ArrayOps$ReverseIterator$mcB$sp extends ArrayOps.ReverseIterator {
   private static final long serialVersionUID = 3L;
   public final byte[] xs$mcB$sp;

   public byte next() {
      return this.next$mcB$sp();
   }

   public byte next$mcB$sp() {
      if (this.scala$collection$ArrayOps$ReverseIterator$$pos < 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      byte r = this.xs$mcB$sp[this.scala$collection$ArrayOps$ReverseIterator$$pos];
      --this.scala$collection$ArrayOps$ReverseIterator$$pos;
      return r;
   }

   public ArrayOps$ReverseIterator$mcB$sp(final byte[] xs$mcB$sp) {
      super(xs$mcB$sp);
      this.xs$mcB$sp = xs$mcB$sp;
   }
}
