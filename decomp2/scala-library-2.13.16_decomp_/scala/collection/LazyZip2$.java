package scala.collection;

import scala.Tuple2;

public final class LazyZip2$ {
   public static final LazyZip2$ MODULE$ = new LazyZip2$();

   public View lazyZip2ToIterable(final LazyZip2 zipped2) {
      if (zipped2 == null) {
         throw null;
      } else {
         return new AbstractView() {
            // $FF: synthetic field
            private final LazyZip2 $outer;

            public AbstractIterator iterator() {
               return new AbstractIterator() {
                  private final Iterator elems1;
                  private final Iterator elems2;

                  public boolean hasNext() {
                     return this.elems1.hasNext() && this.elems2.hasNext();
                  }

                  public Tuple2 next() {
                     return new Tuple2(this.elems1.next(), this.elems2.next());
                  }

                  public {
                     this.elems1 = scala$collection$LazyZip2$$anon$$$outer().scala$collection$LazyZip2$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip2$$anon$$$outer().scala$collection$LazyZip2$$coll2.iterator();
                  }
               };
            }

            public int knownSize() {
               return this.$outer.scala$collection$LazyZip2$$zipKnownSize();
            }

            public boolean isEmpty() {
               return this.$outer.scala$collection$LazyZip2$$coll1.isEmpty() || this.$outer.scala$collection$LazyZip2$$coll2.isEmpty();
            }

            // $FF: synthetic method
            public LazyZip2 scala$collection$LazyZip2$$anon$$$outer() {
               return this.$outer;
            }

            public {
               if (LazyZip2$.this == null) {
                  throw null;
               } else {
                  this.$outer = LazyZip2$.this;
               }
            }
         };
      }
   }

   private LazyZip2$() {
   }
}
