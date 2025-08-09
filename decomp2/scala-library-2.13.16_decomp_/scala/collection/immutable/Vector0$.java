package scala.collection.immutable;

import scala.Function1;
import scala.collection.IterableOnce;
import scala.runtime.Nothing$;

public final class Vector0$ extends BigVector {
   public static final Vector0$ MODULE$ = new Vector0$();

   public Nothing$ apply(final int index) {
      throw this.ioob(index);
   }

   public Vector updated(final int index, final Object elem) {
      throw this.ioob(index);
   }

   public Vector appended(final Object elem) {
      VectorInline$ var10002 = VectorInline$.MODULE$;
      Object[] wrap1_a = new Object[1];
      wrap1_a[0] = elem;
      var10002 = wrap1_a;
      wrap1_a = null;
      return new Vector1(var10002);
   }

   public Vector prepended(final Object elem) {
      VectorInline$ var10002 = VectorInline$.MODULE$;
      Object[] wrap1_a = new Object[1];
      wrap1_a[0] = elem;
      var10002 = wrap1_a;
      wrap1_a = null;
      return new Vector1(var10002);
   }

   public Vector map(final Function1 f) {
      return this;
   }

   public Vector tail() {
      throw new UnsupportedOperationException("empty.tail");
   }

   public Vector init() {
      throw new UnsupportedOperationException("empty.init");
   }

   public Vector slice0(final int lo, final int hi) {
      return this;
   }

   public int vectorSliceCount() {
      return 0;
   }

   public Object[] vectorSlice(final int idx) {
      return null;
   }

   public int vectorSlicePrefixLength(final int idx) {
      return 0;
   }

   public boolean equals(final Object o) {
      if (this == o) {
         return true;
      } else {
         return o instanceof Vector ? false : scala.collection.Seq.equals$(this, o);
      }
   }

   public Vector prependedAll0(final IterableOnce prefix, final int k) {
      return Vector$.MODULE$.from(prefix);
   }

   public Vector appendedAll0(final IterableOnce suffix, final int k) {
      return Vector$.MODULE$.from(suffix);
   }

   public IndexOutOfBoundsException ioob(final int index) {
      return new IndexOutOfBoundsException((new StringBuilder(32)).append(index).append(" is out of bounds (empty vector)").toString());
   }

   private Vector0$() {
      super(VectorStatics$.MODULE$.empty1(), VectorStatics$.MODULE$.empty1(), 0);
   }
}
